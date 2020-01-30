
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
    "executionError",
    "validationProfile",
    "compliant",
    "encrypted",
    "passedRules",
    "failedRules",
    "passedChecks",
    "failedChecks",
    "failedPolicyChecks",
    "errorMessage"
})
public class VerapdfResult {

    @JsonProperty("_links")
    private List<Link> links = null;
    @JsonProperty("_embedded")
    private Map<String, Object> embedded = new HashMap<String, Object>();
    @JsonProperty("id")
    private Long id;
    @JsonProperty("executionError")
    private Boolean executionError;
    @JsonProperty("validationProfile")
    private String validationProfile;
    @JsonProperty("compliant")
    private Boolean compliant;
    @JsonProperty("encrypted")
    private Boolean encrypted;
    @JsonProperty("passedRules")
    private Long passedRules;
    @JsonProperty("failedRules")
    private Long failedRules;
    @JsonProperty("passedChecks")
    private Long passedChecks;
    @JsonProperty("failedChecks")
    private Long failedChecks;
    @JsonProperty("failedPolicyChecks")
    private Long failedPolicyChecks;
    @JsonProperty("errorMessage")
    private Object errorMessage;
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
    public Map<String, Object> getEmbedded() {
		return embedded;
	}

    @JsonProperty("_embedded")
	public void setEmbedded(Map<String, Object> embedded) {
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

    @JsonProperty("executionError")
    public Boolean getExecutionError() {
        return executionError;
    }

    @JsonProperty("executionError")
    public void setExecutionError(Boolean executionError) {
        this.executionError = executionError;
    }

    @JsonProperty("validationProfile")
    public String getValidationProfile() {
        return validationProfile;
    }

    @JsonProperty("validationProfile")
    public void setValidationProfile(String validationProfile) {
        this.validationProfile = validationProfile;
    }

    @JsonProperty("compliant")
    public Boolean getCompliant() {
        return compliant;
    }

    @JsonProperty("compliant")
    public void setCompliant(Boolean compliant) {
        this.compliant = compliant;
    }

    @JsonProperty("encrypted")
    public Boolean getEncrypted() {
        return encrypted;
    }

    @JsonProperty("encrypted")
    public void setEncrypted(Boolean encrypted) {
        this.encrypted = encrypted;
    }

    @JsonProperty("passedRules")
    public Long getPassedRules() {
        return passedRules;
    }

    @JsonProperty("passedRules")
    public void setPassedRules(Long passedRules) {
        this.passedRules = passedRules;
    }

    @JsonProperty("failedRules")
    public Long getFailedRules() {
        return failedRules;
    }

    @JsonProperty("failedRules")
    public void setFailedRules(Long failedRules) {
        this.failedRules = failedRules;
    }

    @JsonProperty("passedChecks")
    public Long getPassedChecks() {
        return passedChecks;
    }

    @JsonProperty("passedChecks")
    public void setPassedChecks(Long passedChecks) {
        this.passedChecks = passedChecks;
    }

    @JsonProperty("failedChecks")
    public Long getFailedChecks() {
        return failedChecks;
    }

    @JsonProperty("failedChecks")
    public void setFailedChecks(Long failedChecks) {
        this.failedChecks = failedChecks;
    }

    @JsonProperty("failedPolicyChecks")
    public Long getFailedPolicyChecks() {
        return failedPolicyChecks;
    }

    @JsonProperty("failedPolicyChecks")
    public void setFailedPolicyChecks(Long failedPolicyChecks) {
        this.failedPolicyChecks = failedPolicyChecks;
    }

    @JsonProperty("errorMessage")
    public Object getErrorMessage() {
        return errorMessage;
    }

    @JsonProperty("errorMessage")
    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
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
