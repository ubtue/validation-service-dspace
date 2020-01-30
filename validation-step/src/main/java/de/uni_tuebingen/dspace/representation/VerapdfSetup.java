
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
    "id",
    "executionMode",
    "validationProfile",
    "reportPassedRules",
    "failedChecksThreshold",
    "failedChecksPerRuleDisplayed",
    "failOnInvalidPdfA",
    "pdfPolicies"
})
public class VerapdfSetup {

    @JsonProperty("_links")
    private List<Link> links = null;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("executionMode")
    private String executionMode;
    @JsonProperty("validationProfile")
    private String validationProfile;
    @JsonProperty("reportPassedRules")
    private Boolean reportPassedRules;
    @JsonProperty("failedChecksThreshold")
    private Long failedChecksThreshold;
    @JsonProperty("failedChecksPerRuleDisplayed")
    private Long failedChecksPerRuleDisplayed;
    @JsonProperty("failOnInvalidPdfA")
    private Boolean failOnInvalidPdfA;
    @JsonProperty("pdfPolicies")
    private PdfPolicies pdfPolicies;
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

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("executionMode")
    public String getExecutionMode() {
        return executionMode;
    }

    @JsonProperty("executionMode")
    public void setExecutionMode(String executionMode) {
        this.executionMode = executionMode;
    }

    @JsonProperty("validationProfile")
    public String getValidationProfile() {
        return validationProfile;
    }

    @JsonProperty("validationProfile")
    public void setValidationProfile(String validationProfile) {
        this.validationProfile = validationProfile;
    }

    @JsonProperty("reportPassedRules")
    public Boolean getReportPassedRules() {
        return reportPassedRules;
    }

    @JsonProperty("reportPassedRules")
    public void setReportPassedRules(Boolean reportPassedRules) {
        this.reportPassedRules = reportPassedRules;
    }

    @JsonProperty("failedChecksThreshold")
    public Long getFailedChecksThreshold() {
        return failedChecksThreshold;
    }

    @JsonProperty("failedChecksThreshold")
    public void setFailedChecksThreshold(Long failedChecksThreshold) {
        this.failedChecksThreshold = failedChecksThreshold;
    }

    @JsonProperty("failedChecksPerRuleDisplayed")
    public Long getFailedChecksPerRuleDisplayed() {
        return failedChecksPerRuleDisplayed;
    }

    @JsonProperty("failedChecksPerRuleDisplayed")
    public void setFailedChecksPerRuleDisplayed(Long failedChecksPerRuleDisplayed) {
        this.failedChecksPerRuleDisplayed = failedChecksPerRuleDisplayed;
    }

    @JsonProperty("failOnInvalidPdfA")
    public Boolean getFailOnInvalidPdfA() {
        return failOnInvalidPdfA;
    }

    @JsonProperty("failOnInvalidPdfA")
    public void setFailOnInvalidPdfA(Boolean failOnInvalidPdfA) {
        this.failOnInvalidPdfA = failOnInvalidPdfA;
    }

    @JsonProperty("pdfPolicies")
    public PdfPolicies getPdfPolicies() {
        return pdfPolicies;
    }

    @JsonProperty("pdfPolicies")
    public void setPdfPolicies(PdfPolicies pdfPolicies) {
        this.pdfPolicies = pdfPolicies;
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
