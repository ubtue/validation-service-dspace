
package de.uni_tuebingen.dspace.representation;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "disallowEncryptInTrailer",
    "disallowOtherFormsOfEncrypt",
    "disallowEmbeddedFonts",
    "disallowEmbeddedFiles",
    "disallowFileAttachments",
    "disallowMultimediaAnnotations",
    "disallowNonParseableDocuments"
})
public class PdfPolicies {

    @JsonProperty("disallowEncryptInTrailer")
    private Boolean disallowEncryptInTrailer;
    @JsonProperty("disallowOtherFormsOfEncrypt")
    private Boolean disallowOtherFormsOfEncrypt;
    @JsonProperty("disallowEmbeddedFonts")
    private Boolean disallowEmbeddedFonts;
    @JsonProperty("disallowEmbeddedFiles")
    private Boolean disallowEmbeddedFiles;
    @JsonProperty("disallowFileAttachments")
    private Boolean disallowFileAttachments;
    @JsonProperty("disallowMultimediaAnnotations")
    private Boolean disallowMultimediaAnnotations;
    @JsonProperty("disallowNonParseableDocuments")
    private Boolean disallowNonParseableDocuments;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("disallowEncryptInTrailer")
    public Boolean getDisallowEncryptInTrailer() {
        return disallowEncryptInTrailer;
    }

    @JsonProperty("disallowEncryptInTrailer")
    public void setDisallowEncryptInTrailer(Boolean disallowEncryptInTrailer) {
        this.disallowEncryptInTrailer = disallowEncryptInTrailer;
    }

    @JsonProperty("disallowOtherFormsOfEncrypt")
    public Boolean getDisallowOtherFormsOfEncrypt() {
        return disallowOtherFormsOfEncrypt;
    }

    @JsonProperty("disallowOtherFormsOfEncrypt")
    public void setDisallowOtherFormsOfEncrypt(Boolean disallowOtherFormsOfEncrypt) {
        this.disallowOtherFormsOfEncrypt = disallowOtherFormsOfEncrypt;
    }

    @JsonProperty("disallowEmbeddedFonts")
    public Boolean getDisallowEmbeddedFonts() {
        return disallowEmbeddedFonts;
    }

    @JsonProperty("disallowEmbeddedFonts")
    public void setDisallowEmbeddedFonts(Boolean disallowEmbeddedFonts) {
        this.disallowEmbeddedFonts = disallowEmbeddedFonts;
    }

    @JsonProperty("disallowEmbeddedFiles")
    public Boolean getDisallowEmbeddedFiles() {
        return disallowEmbeddedFiles;
    }

    @JsonProperty("disallowEmbeddedFiles")
    public void setDisallowEmbeddedFiles(Boolean disallowEmbeddedFiles) {
        this.disallowEmbeddedFiles = disallowEmbeddedFiles;
    }

    @JsonProperty("disallowFileAttachments")
    public Boolean getDisallowFileAttachments() {
        return disallowFileAttachments;
    }

    @JsonProperty("disallowFileAttachments")
    public void setDisallowFileAttachments(Boolean disallowFileAttachments) {
        this.disallowFileAttachments = disallowFileAttachments;
    }

    @JsonProperty("disallowMultimediaAnnotations")
    public Boolean getDisallowMultimediaAnnotations() {
        return disallowMultimediaAnnotations;
    }

    @JsonProperty("disallowMultimediaAnnotations")
    public void setDisallowMultimediaAnnotations(Boolean disallowMultimediaAnnotations) {
        this.disallowMultimediaAnnotations = disallowMultimediaAnnotations;
    }

    @JsonProperty("disallowNonParseableDocuments")
    public Boolean getDisallowNonParseableDocuments() {
        return disallowNonParseableDocuments;
    }

    @JsonProperty("disallowNonParseableDocuments")
    public void setDisallowNonParseableDocuments(Boolean disallowNonParseableDocuments) {
        this.disallowNonParseableDocuments = disallowNonParseableDocuments;
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
