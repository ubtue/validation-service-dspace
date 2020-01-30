package de.uni_tuebingen.dspace.validation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import org.apache.avalon.framework.parameters.Parameters;
import org.apache.cocoon.ProcessingException;
import org.apache.cocoon.environment.SourceResolver;
import org.dspace.app.xmlui.aspect.submission.AbstractSubmissionStep;
import org.dspace.app.xmlui.utils.UIException;
import org.dspace.app.xmlui.wing.Message;
import org.dspace.app.xmlui.wing.WingException;
import org.dspace.app.xmlui.wing.element.Body;
import org.dspace.app.xmlui.wing.element.Division;
import org.dspace.app.xmlui.wing.element.Item;
import org.dspace.app.xmlui.wing.element.List;
import org.dspace.authorize.AuthorizeException;
import org.dspace.content.Collection;
import org.dspace.submit.AbstractProcessingStep;
import org.xml.sax.SAXException;

/**
 * Submission page of validation step for xmlui.
 * @author Fabian Hamm
 *
 */
public class ValidationXmlUiStep extends AbstractSubmissionStep{
	
	protected static final Message T_MAIN_HEADING =
	        message("xmlui.Submission.submit.ValidationStep.heading");
	
	protected static final Message T_INTRODUCTION =
	        message("xmlui.Submission.submit.ValidationStep.introduction");
	
	protected static final Message T_MESSAGE_SYSTEM_ERROR =
	        message("xmlui.Submission.submit.ValidationStep.system.error");
	
	protected static final Message T_MESSAGE_PROBLEMS_IDENTIFIED_FIXABLE =
	        message("xmlui.Submission.submit.ValidationStep.problems.identified.fixable");
	
	protected static final Message T_MESSAGE_PROBLEMS_IDENTIFIED_UNFIXABLE =
	        message("xmlui.Submission.submit.ValidationStep.problems.identified.unfixable");
	
	protected static final Message T_MESSAGE_PROBLEMS_STAFF_REQUIRED_HEADING =
	        message("xmlui.Submission.submit.ValidationStep.problems.staff.required.heading");

		
	/**
	 * Constructor
	 */
	public ValidationXmlUiStep() {
		super();
	}

	@Override
	public void setup(SourceResolver resolver, Map objectModel, String src, Parameters parameters)
			throws ProcessingException, SAXException, IOException {
		super.setup(resolver, objectModel, src, parameters);		 
	}
	
	@Override
	public void addBody(Body body) throws SAXException, WingException, UIException, SQLException, IOException,
			AuthorizeException, ProcessingException {
		Collection collection = submission.getCollection();
        String actionURL = contextPath + "/handle/"+collection.getHandle() + "/submit/" + knot.getId() + ".continue";
		
        Division maindiv = body.addInteractiveDivision("submit-describe",actionURL,Division.METHOD_POST,"primary submission");
		maindiv.setHead(T_submission_head);
        addSubmissionProgressList(maindiv);
        
        List review = maindiv.addList("submit-review", List.TYPE_FORM);
        review.setHead(T_MAIN_HEADING); 
        List unfixable = maindiv.addList("submit-unfixable", List.TYPE_FORM);
        List nav = maindiv.addList("submit-nav", List.TYPE_FORM);
        
        if(this.errorFlag == ValidationProcessingStep.STATUS_ERROR) {
			review.addItem(T_MESSAGE_SYSTEM_ERROR);
        } else if(this.submissionInfo.get(ValidationProcessingStep.VALIDATION_RESULTS_KEY) == null) {
			review.addItem(T_INTRODUCTION);
		} else {
			java.util.List<FileValidationResult> fixableProblems = new ArrayList<FileValidationResult>();
			java.util.List<FileValidationResult> unFixableProblems = new ArrayList<FileValidationResult>();
		    for (FileValidationResult fileValidationResult : (java.util.List<FileValidationResult>) this.submissionInfo.get(ValidationProcessingStep.VALIDATION_RESULTS_KEY)) {
		    	if (fileValidationResult.canBeFixedByUser()) {
		    		fixableProblems.add(fileValidationResult);
				} else {
					unFixableProblems.add(fileValidationResult);
				}
			}
		    
		    if(fixableProblems.size() != 0) {
		    	review.addItem(T_MESSAGE_PROBLEMS_IDENTIFIED_FIXABLE);
		    	for (FileValidationResult fileValidationResult : fixableProblems) {
		    		List fix = review.addList(String.valueOf(UUID.randomUUID()), List.TYPE_FORM);
		    		fix.setHead(fileValidationResult.getFileName());
		    		for (String message : fileValidationResult.getProblemMessages()) {
						fix.addItem(message);
					}
		    		fix.addItem(" ");
				}
		    }
		    
		    if(unFixableProblems.size() != 0) {
		    	unfixable.setHead(T_MESSAGE_PROBLEMS_STAFF_REQUIRED_HEADING);
		    	unfixable.addItem(T_MESSAGE_PROBLEMS_IDENTIFIED_UNFIXABLE);
		    	List fix = unfixable.addList(String.valueOf(UUID.randomUUID()), List.TYPE_FORM);
		    	for (FileValidationResult fileValidationResult : unFixableProblems) {
		    		
		    		fix.addItem(fileValidationResult.getFileName());
				}
		    }
		}
        
		this.addControlButtons(nav);
        return;
	}
	
    public void addControlButtons(List controls) throws WingException {
    	Item actions = controls.addItem();
            
        //only have "<-Previous" button if not first step
        if(!isFirstStep()){
        	actions.addButton(AbstractProcessingStep.PREVIOUS_BUTTON).setValue(T_previous);
        }
            
        //always show "Save/Cancel"
        actions.addButton(AbstractProcessingStep.CANCEL_BUTTON).setValue(T_save);
            
        //If last step, show "Complete Submission"
        if(this.errorFlag != ValidationProcessingStep.STATUS_COMPLETED_PROBLEMS) {
        	if(isLastStep()){
                actions.addButton(AbstractProcessingStep.NEXT_BUTTON).setValue(T_complete);
            } //otherwise, show "Next->"
            else{
                actions.addButton(AbstractProcessingStep.NEXT_BUTTON).setValue(T_next);
            }
        }
    }
	
	@Override
	public List addReviewSection(List reviewList)
			throws SAXException, WingException, UIException, SQLException, IOException, AuthorizeException {
		List uploadSection = reviewList.addList("submit-review-" + this.stepAndPage, List.TYPE_FORM);
        uploadSection.setHead( message("xmlui.Submission.submit.UploadStep.head"));
        return uploadSection;
	}

}
