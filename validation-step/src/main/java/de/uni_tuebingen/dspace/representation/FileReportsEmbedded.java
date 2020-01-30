
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
    "fits-results",
    "file",
    "verapdf-result"
})
public class FileReportsEmbedded {

    @JsonProperty("fits-results")
    private List<FitsResult> fitsResults = null;
    @JsonProperty("file")
    private File file;
    @JsonProperty("verapdf-result")
    private VerapdfResult verapdfResult;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fits-results")
    public List<FitsResult> getFitsResults() {
        return fitsResults;
    }

    @JsonProperty("fits-results")
    public void setFitsResults(List<FitsResult> fitsResults) {
        this.fitsResults = fitsResults;
    }

    @JsonProperty("file")
    public File getFile() {
        return file;
    }

    @JsonProperty("file")
    public void setFile(File file) {
        this.file = file;
    }

    @JsonProperty("verapdf-result")
    public VerapdfResult getVerapdfResult() {
        return verapdfResult;
    }

    @JsonProperty("verapdf-result")
    public void setVerapdfResult(VerapdfResult verapdfResult) {
        this.verapdfResult = verapdfResult;
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
