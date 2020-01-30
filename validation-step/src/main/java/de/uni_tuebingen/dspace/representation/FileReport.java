
package de.uni_tuebingen.dspace.representation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "_links",
    "_embedded",
    "id",
    "validationOutcome",
    "fitsExecutionOutcome",
    "veraPdfExecutionOutcome",
    "failedNameChecks",
    "failedFitsChecks",
    "errorMessages"
})
public class FileReport {

    @JsonProperty("_links")
    private List<Link> links = null;
    @JsonProperty("_embedded")
    private FileReportsEmbedded embedded;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("validationOutcome")
    private String validationOutcome;
    @JsonProperty("fitsExecutionOutcome")
    private String fitsExecutionOutcome;
    @JsonProperty("veraPdfExecutionOutcome")
    private String veraPdfExecutionOutcome;
    @JsonProperty("failedNameChecks")
    private Long failedNameChecks;
    @JsonProperty("failedFitsChecks")
    private Long failedFitsChecks;
    @JsonProperty("errorMessages")
    private List<Object> errorMessages = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("_links")
    public List<Link> getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @JsonProperty("_embedded")
    public FileReportsEmbedded getEmbedded() {
        return embedded;
    }

    @JsonProperty("_embedded")
    public void setEmbedded(FileReportsEmbedded embedded) {
        this.embedded = embedded;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("validationOutcome")
    public String getValidationOutcome() {
        return validationOutcome;
    }

    @JsonProperty("validationOutcome")
    public void setValidationOutcome(String validationOutcome) {
        this.validationOutcome = validationOutcome;
    }

    @JsonProperty("fitsExecutionOutcome")
    public String getFitsExecutionOutcome() {
        return fitsExecutionOutcome;
    }

    @JsonProperty("fitsExecutionOutcome")
    public void setFitsExecutionOutcome(String fitsExecutionOutcome) {
        this.fitsExecutionOutcome = fitsExecutionOutcome;
    }

    @JsonProperty("veraPdfExecutionOutcome")
    public String getVeraPdfExecutionOutcome() {
        return veraPdfExecutionOutcome;
    }

    @JsonProperty("veraPdfExecutionOutcome")
    public void setVeraPdfExecutionOutcome(String veraPdfExecutionOutcome) {
        this.veraPdfExecutionOutcome = veraPdfExecutionOutcome;
    }

    @JsonProperty("failedNameChecks")
    public Long getFailedNameChecks() {
        return failedNameChecks;
    }

    @JsonProperty("failedNameChecks")
    public void setFailedNameChecks(Long failedNameChecks) {
        this.failedNameChecks = failedNameChecks;
    }

    @JsonProperty("failedFitsChecks")
    public Long getFailedFitsChecks() {
        return failedFitsChecks;
    }

    @JsonProperty("failedFitsChecks")
    public void setFailedFitsChecks(Long failedFitsChecks) {
        this.failedFitsChecks = failedFitsChecks;
    }

    @JsonProperty("errorMessages")
    public List<Object> getErrorMessages() {
        return errorMessages;
    }

    @JsonProperty("errorMessages")
    public void setErrorMessages(List<Object> errorMessages) {
        this.errorMessages = errorMessages;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
