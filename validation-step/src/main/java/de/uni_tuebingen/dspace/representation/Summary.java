
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
    "problematicFiles",
    "totalFiles",
    "validationOutcome"
})
public class Summary {

    @JsonProperty("problematicFiles")
    private Long problematicFiles;
    @JsonProperty("totalFiles")
    private Long totalFiles;
    @JsonProperty("validationOutcome")
    private String validationOutcome;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("problematicFiles")
    public Long getProblematicFiles() {
        return problematicFiles;
    }

    @JsonProperty("problematicFiles")
    public void setProblematicFiles(Long problematicFiles) {
        this.problematicFiles = problematicFiles;
    }

    @JsonProperty("totalFiles")
    public Long getTotalFiles() {
        return totalFiles;
    }

    @JsonProperty("totalFiles")
    public void setTotalFiles(Long totalFiles) {
        this.totalFiles = totalFiles;
    }

    @JsonProperty("validationOutcome")
    public String getValidationOutcome() {
        return validationOutcome;
    }

    @JsonProperty("validationOutcome")
    public void setValidationOutcome(String validationOutcome) {
        this.validationOutcome = validationOutcome;
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
