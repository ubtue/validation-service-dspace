package de.uni_tuebingen.dspace.validation;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.dspace.app.util.SubmissionInfo;
import org.dspace.app.util.Util;
import org.dspace.authorize.AuthorizeException;
import org.dspace.content.Bitstream;
import org.dspace.content.Bundle;
import org.dspace.content.Item;
import org.dspace.core.Context;
import org.dspace.core.I18nUtil;
import org.dspace.eperson.EPerson;
import org.dspace.submit.AbstractProcessingStep;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import de.uni_tuebingen.dspace.representation.Batch;
import de.uni_tuebingen.dspace.representation.BatchReport;
import de.uni_tuebingen.dspace.representation.Check;
import de.uni_tuebingen.dspace.representation.ChecksPage;
import de.uni_tuebingen.dspace.representation.FileReport;
import de.uni_tuebingen.dspace.representation.FileReportsPage;
import de.uni_tuebingen.dspace.representation.Link;
import de.uni_tuebingen.dspace.representation.QueueItem;
import de.uni_tuebingen.dspace.representation.ValidationOrder;

/**
 * Processing class of the validation step.
 * This class submits the bitstreams of the item to the validation service, retrieves the results and stores 
 * them in the submission information for retrieval by the xmlui-binding class.
 * @author Fabian Hamm
 *
 */
public class ValidationProcessingStep extends AbstractProcessingStep {
	
	// Status codes
	/** Return code if validation of some files failed or are not valid */
	public static final int STATUS_COMPLETED_PROBLEMS = 30;
	/** Return code if something went wrong during processing or communication with validation service */
	public static final int STATUS_ERROR = 40;
	public static final int STATUS_ALREADY_PROCESSING = 50;
	
	// Configuration keys
	public static final String SERVER_URL_KEY = "validation.server.url";
	public static final String SERVER_USER_NAME_KEY = "validation.server.user.name";
	public static final String SERVER_USER_PASSWORD_KEY = "validation.server.user.password";
	public static final String VALIDATION_CONFIGURATION_PUBLIC_ID_KEY = "validation.configuration.public.id";
	public static final String VALIDATION_CLIENT_INTERVAL_KEY = "validation.client.interval";
	public static final String VALIDATION_IGNORE_BUNDLE_KEY = "validation.ignore.bundle.name";
	
	//Keys stored in Submission Info
	public static final String VALIDATION_RESULTS_KEY = "validationResults";
	public static final String SUBMISSION_RUNNING_KEY = "submissionRunning";
	
	private static final Logger logger = Logger.getLogger(ValidationProcessingStep.class);
	
	/** Base url of the dspace validation service */
	private String serviceBaseUrl;
	
	/** The id of the configuration to be used for validation */
	private String validationConfigurationId;
	
	/**Time to wait before next status update request to processing queue in milliseconds*/
	private long refreshIntervalMillis;
	
	/**The bundles to be ignored and not to be submitted*/
	private Set<String> ignoreBundleSet = new HashSet<>();
	/**The jwt authentication token*/
	private String bearerToken;
		
	
	/**
	 * Creates an instance of the step
	 */
	public ValidationProcessingStep() {
		super();
		initializeStep();
	}
	
	/**
	 * Initializes the step with required parameters defined in dspace configuration (i.e. local.cfg)
	 */
	private void initializeStep() {
		this.serviceBaseUrl = getProperty(SERVER_URL_KEY);
		
		if(this.serviceBaseUrl == null || this.serviceBaseUrl.length() == 0)
			logger.warn("Variable: " + SERVER_URL_KEY + " not defined in configuration" );
		
		if(getProperty(SERVER_USER_NAME_KEY) == null)
			logger.warn("Variable: " + SERVER_USER_NAME_KEY + " not defined in configuration" );
		
		if(getProperty(SERVER_USER_PASSWORD_KEY) == null)
			logger.warn("Variable: " + SERVER_USER_PASSWORD_KEY + " not defined in configuration" );
		
		this.validationConfigurationId = getProperty(VALIDATION_CONFIGURATION_PUBLIC_ID_KEY);
		
		if (this.validationConfigurationId == null) 
			logger.warn("Variable: " + VALIDATION_CONFIGURATION_PUBLIC_ID_KEY + " not defined in configuration" );
				
		String interval = getProperty(VALIDATION_CLIENT_INTERVAL_KEY);
		
		if(interval == null) {
			this.refreshIntervalMillis = 5000;
			logger.warn("Variable: " + VALIDATION_CLIENT_INTERVAL_KEY + " not defined in configuration, defaulting value to: 5000" );
		} else {
			this.refreshIntervalMillis = Long.valueOf(interval);
		}
		
		String [] ignoreField = getArrayProperty(VALIDATION_IGNORE_BUNDLE_KEY);
		if(ignoreField != null) {
			for (String bundleName : ignoreField) {
				this.ignoreBundleSet.add(bundleName.toLowerCase());
			}
		}	
	}

	@Override
	public int doProcessing(Context context, HttpServletRequest request, HttpServletResponse response,
			SubmissionInfo subInfo) throws ServletException, IOException, SQLException, AuthorizeException {		
		String buttonPressed = Util.getSubmitButton(request, NEXT_BUTTON);
		
		if (buttonPressed.startsWith(PROGRESS_BAR_PREFIX) 
			|| buttonPressed.startsWith(this.PREVIOUS_BUTTON)
			|| buttonPressed.startsWith(this.CANCEL_BUTTON)) {
			return STATUS_COMPLETE;
		}
		
		if(subInfo.get(SUBMISSION_RUNNING_KEY) != null)
        	return STATUS_ALREADY_PROCESSING;
			
		// Fetch streams from all bundles except the ignored bundles
        Item item = subInfo.getSubmissionItem().getItem();
        List<Bitstream> streams = new ArrayList<>();
        List<Bundle> bundles = item.getBundles();
        
        for (Bundle bundle : bundles) {
        	if(!this.ignoreBundleSet.contains(bundle.getName().toLowerCase()))
        		streams.addAll(bundle.getBitstreams());
		}
        
        // If no streams available, no validation required
        if(streams.size() == 0)
        	return STATUS_COMPLETE;
                  
        // Create client and validate files
        ClientConfig config = new ClientConfig();
        config.register(JacksonJsonProvider.class);
        Client client = null;
               
        try {
        	subInfo.put(SUBMISSION_RUNNING_KEY, SUBMISSION_RUNNING_KEY);
        	
        	client = JerseyClientBuilder.buildTrustAllClient(config)
        			.register(MultiPartFeature.class)
        			.property(ClientProperties.REQUEST_ENTITY_PROCESSING, "CHUNKED");
        	
        	// Get security token
        	this.bearerToken = login(client, this.serviceBaseUrl + "/users/login", 
        			getProperty(SERVER_USER_NAME_KEY), getProperty(SERVER_USER_PASSWORD_KEY));
        	
        	// Get E-Mail address of submitter and create an accordingly named batch on validation service
        	EPerson submitter = subInfo.getSubmissionItem().getSubmitter();
        	String description = submitter.getEmail() != null ? submitter.getEmail() : "Submitter without E-Mail address";
        	Batch batch = createBatch(client, description);

        	// Upload files and start validation
        	uploadFilesToBatch(client, batch, streams, context);
        	String queueLocation = submitValidationOrder(client, batch); 
        	
        	// Check queue and monitor progress of validation task
        	QueueItem queueItem = getQueueItem(client, queueLocation);
        	while (queueItem.getStatus().equals("processing") || queueItem.getStatus().equals("queued")) {
				Thread.sleep(this.refreshIntervalMillis);
				queueItem = getQueueItem(client, queueLocation);
			}
        	
        	// If no problematic files are present, validation was successful
        	BatchReport batchReport = getBatchReport(client, queueLocation);
        	if(batchReport.getSummary() == null) {
        		return STATUS_ERROR;
        	} else if (batchReport.getSummary().getProblematicFiles() < 1) {
        		return STATUS_COMPLETE;
			} 
        	
        	// Else we store all problematic validation results in the submission info. 
        	// This information will be displayed in the ui-binding class.
        	Locale locale = I18nUtil.getEPersonLocale(subInfo.getSubmissionItem().getSubmitter());
        	List<FileValidationResult> validationResults = getFileValidationResults(client, batchReport, locale.getLanguage());
        	subInfo.put(VALIDATION_RESULTS_KEY, validationResults);
        	
        	return STATUS_COMPLETED_PROBLEMS;
		}catch (Exception e) {
			if(!(e instanceof NullPointerException)) {
				logger.warn("Processing in submission validation step failed with exception: " + e.getMessage());
			}
			return STATUS_ERROR;
		} finally {
			if(client != null)
				client.close();
			subInfo.remove(SUBMISSION_RUNNING_KEY);
		}
	}

	@Override
	public int getNumberOfPages(HttpServletRequest request, SubmissionInfo subInfo) throws ServletException {
		return 1;
	}
	
	private String getProperty(String identifier) {
		return this.configurationService.getProperty(identifier);
	}
	
	private String [] getArrayProperty(String identifier) {
		return this.configurationService.getArrayProperty(identifier);
	}
	
	
	public Batch createBatch(Client cl ,String description) throws IOException {
		String batchesUrl = this.serviceBaseUrl + "/batches"; 
		WebTarget target = cl.target(batchesUrl);
		Batch batch = new Batch();
		batch.setDescription(description);
		Response response = null;
		try {
			response = target.request().header(HttpHeaders.AUTHORIZATION, getBearerToken()).post(Entity.entity(batch, MediaType.APPLICATION_JSON));
			return response.readEntity(Batch.class);
		} finally {
			if(response != null)
				response.close();
		}
	}
	
	public void uploadFilesToBatch(Client cl, Batch batch, List<Bitstream> streams, Context context) throws IOException {
    	String filesUrl = null;
    	
    	for (Link link : batch.getLinks()) {
			if(link.getRel().equals("files"))
				filesUrl = link.getHref();
		}
    	
		WebTarget target = cl.target(filesUrl);
		Response response = null;
		for (Bitstream bitstream : streams) {	   
	        try (InputStream stream = bitstreamService.retrieve(context, bitstream);) {
	        	final StreamDataBodyPart filePart = new StreamDataBodyPart("file", stream, bitstream.getName());
	        	final FormDataMultiPart multipart = new FormDataMultiPart();
	        	multipart.bodyPart(filePart);
	        	multipart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);	        		
	        	response = target
	        			.request()
	        			.header(HttpHeaders.AUTHORIZATION, getBearerToken())
	        			.post(Entity.entity(multipart, multipart.getMediaType()));       
	    	} catch (IOException | SQLException | AuthorizeException e) {
	    		logger.warn("File upload in submission validation step failed with exception: " + e.getMessage());
			} finally {
				if(response != null)
					response.close();
			}
		}
	}
	
	public String submitValidationOrder(Client cl, Batch batch) {
		WebTarget target = cl.target(this.serviceBaseUrl + "/queue");
		Response response = null;
		ValidationOrder order = new ValidationOrder();
		order.setBatchId(batch.getId());
		order.setConfigurationIdentifier(this.validationConfigurationId);
		
		try {
			response = target
					.request()
					.header(HttpHeaders.AUTHORIZATION, getBearerToken())
					.post(Entity.entity(order, MediaType.APPLICATION_JSON));
			String location = response.getLocation().toString();
			return location;
		} finally {
			if(response != null)
				response.close();
		}
	}
	
	public QueueItem getQueueItem(Client cl, String location) {
		WebTarget target = cl.target(location);
		Response response = null;
		
		try {
			response = target.request().header(HttpHeaders.AUTHORIZATION, getBearerToken()).get();
			QueueItem item = response.readEntity(QueueItem.class);
			return item;	
		} finally {
			if(response != null)
				response.close();
		}
	}
	
	public BatchReport getBatchReport(Client cl, String location) {
		WebTarget target = cl.target(location);
		Response response = null;
		
		try {
			response = target.request().header(HttpHeaders.AUTHORIZATION, getBearerToken()).get();
			BatchReport report = response.readEntity(BatchReport.class);
			return report;	
		} finally {
			if(response != null)
				response.close();
		} 
	}
	
	public String getHrefForRel(List<Link> links, String rel) {
		String href = null;
    	for (Link link : links) {
			if(link.getRel().equals(rel))
				href = link.getHref();
		}
    	return href;
	}
	
    public List<FileReport> getFailedFileReports(Client client, BatchReport report) {
    	List<FileReport> reportsList = new ArrayList<>();
    	String reportsUrl = getHrefForRel(report.getLinks(), "file-reports");
    	reportsUrl += "&outcomeFilter=notValid";
    	
    	WebTarget target = client.target(reportsUrl);
    	Response response = null;
    	FileReportsPage page = null;
    	
    	try {
    		response = target.request().header(HttpHeaders.AUTHORIZATION, getBearerToken()).get();
        	page = response.readEntity(FileReportsPage.class);
        	reportsList.addAll(page.getEmbedded().getFileReports());
		} finally {
			if(response != null)
				response.close();
		}
    	    	 
    	while (getHrefForRel(page.getLinks(), "next") != null) {
    		target = client.target(getHrefForRel(page.getLinks(), "next"));
    		try {
    			response = target.request().header(HttpHeaders.AUTHORIZATION, getBearerToken()).get();
            	page = response.readEntity(FileReportsPage.class);
            	reportsList.addAll(page.getEmbedded().getFileReports());
			} finally {
				if(response != null)
					response.close();
			}
		}
    	
    	return reportsList;
    }
    
    public List<Check> getChecksForFileReport(Client client, FileReport report, String languageCode) {
    	List<Check> checkList = new ArrayList<>();
    	String checksUrl = getHrefForRel(report.getLinks(), "checks");
    	checksUrl += "&locale=" + languageCode;
    	
    	WebTarget target = client.target(checksUrl);
    	Response response = null;
    	ChecksPage page = null;
    	
    	try {
    		response = target.request().header(HttpHeaders.AUTHORIZATION, getBearerToken()).get();
    	    page = response.readEntity(ChecksPage.class);
    	    checkList.addAll(page.getEmbedded().getChecks());
		} finally {
			if(response != null)
				response.close();
		}
    	
    	while (getHrefForRel(page.getLinks(), "next") != null) {
    		target = client.target(getHrefForRel(page.getLinks(), "next"));
    		try {
    			response = target.request().header(HttpHeaders.AUTHORIZATION, getBearerToken()).get();
            	page = response.readEntity(ChecksPage.class);
            	checkList.addAll(page.getEmbedded().getChecks());
			} finally {
				if(response != null)
					response.close();
			}        	
		}
    	
    	return checkList;
    }
    
    public List<FileValidationResult> getFileValidationResults(Client client, BatchReport batchReport, String languageCode) {
    	List<FileValidationResult> resultList = new ArrayList<>();
    	List<FileReport> failedReports = getFailedFileReports(client, batchReport);
    	
    	for (FileReport fileReport : failedReports) {
			List<Check> checks = getChecksForFileReport(client, fileReport, languageCode);
			FileValidationResult res = new FileValidationResult();
			res.setFileName(fileReport.getEmbedded().getFile().getFileName());
			for (Check check : checks) {
				res.getProblemMessages().add(check.getResultMessage());
			}
			resultList.add(res);
		}
    	return resultList;
    }
    
    public static String login(Client client, String targetUrl, String userName, String password) {
		WebTarget target = client.target(targetUrl);
		Response response = null;
		Form form = new Form()
				.param("login", userName)
				.param("password", password);
		try {
			response = target.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);
			return response.getHeaderString(HttpHeaders.AUTHORIZATION);
		} finally {
			if(response != null)
				response.close();
		}
	}

	public String getBearerToken() {
		return bearerToken;
	}

	public void setBearerToken(String bearerToken) {
		this.bearerToken = bearerToken;
	}
	
	
	
}
