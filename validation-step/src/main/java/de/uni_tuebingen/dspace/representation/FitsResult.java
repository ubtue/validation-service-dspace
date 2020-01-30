
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
    "tool",
    "toolVersion",
    "formatName",
    "puid",
    "mime",
    "wellFormed",
    "valid"
})
public class FitsResult {

    @JsonProperty("_links")
    private List<Link> links = null;
    @JsonProperty("_embedded")
    private Map<String, Object> embedded = new HashMap<String, Object>();
    @JsonProperty("id")
    private Long id;
    @JsonProperty("tool")
    private String tool;
    @JsonProperty("toolVersion")
    private String toolVersion;
    @JsonProperty("formatName")
    private String formatName;
    @JsonProperty("puid")
    private Object puid;
    @JsonProperty("mime")
    private String mime;
    @JsonProperty("wellFormed")
    private Object wellFormed;
    @JsonProperty("valid")
    private Object valid;
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

    @JsonProperty("tool")
    public String getTool() {
        return tool;
    }

    @JsonProperty("tool")
    public void setTool(String tool) {
        this.tool = tool;
    }

    @JsonProperty("toolVersion")
    public String getToolVersion() {
        return toolVersion;
    }

    @JsonProperty("toolVersion")
    public void setToolVersion(String toolVersion) {
        this.toolVersion = toolVersion;
    }

    @JsonProperty("formatName")
    public String getFormatName() {
        return formatName;
    }

    @JsonProperty("formatName")
    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    @JsonProperty("puid")
    public Object getPuid() {
        return puid;
    }

    @JsonProperty("puid")
    public void setPuid(Object puid) {
        this.puid = puid;
    }

    @JsonProperty("mime")
    public String getMime() {
        return mime;
    }

    @JsonProperty("mime")
    public void setMime(String mime) {
        this.mime = mime;
    }

    @JsonProperty("wellFormed")
    public Object getWellFormed() {
        return wellFormed;
    }

    @JsonProperty("wellFormed")
    public void setWellFormed(Object wellFormed) {
        this.wellFormed = wellFormed;
    }

    @JsonProperty("valid")
    public Object getValid() {
        return valid;
    }

    @JsonProperty("valid")
    public void setValid(Object valid) {
        this.valid = valid;
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
