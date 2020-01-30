
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
    "publicIdentifier",
    "fitsEnabled",
    "description",
    "creationDate",
    "fitsTimeOut",
    "veraPdfTimeOut",
    "veraPdfMaxHeapSize"
})
public class Configuration {

    @JsonProperty("_links")
    private List<Link> links = null;
    @JsonProperty("_embedded")
    private Map<String, Object> embedded = new HashMap<String, Object>();
    @JsonProperty("id")
    private Long id;
    @JsonProperty("publicIdentifier")
    private String publicIdentifier;
    @JsonProperty("fitsEnabled")
    private Boolean fitsEnabled;
    @JsonProperty("description")
    private String description;
    @JsonProperty("creationDate")
    private Long creationDate;
    @JsonProperty("fitsTimeOut")
    private Long fitsTimeOut;
    @JsonProperty("veraPdfTimeOut")
    private Long veraPdfTimeOut;
    @JsonProperty("veraPdfMaxHeapSize")
    private Long veraPdfMaxHeapSize;
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

    
    @JsonProperty("publicIdentifier")
    public String getPublicIdentifier() {
		return publicIdentifier;
	}

    @JsonProperty("publicIdentifier")
	public void setPublicIdentifier(String publicIdentifier) {
		this.publicIdentifier = publicIdentifier;
	}

	@JsonProperty("fitsEnabled")
    public Boolean getFitsEnabled() {
        return fitsEnabled;
    }

    @JsonProperty("fitsEnabled")
    public void setFitsEnabled(Boolean fitsEnabled) {
        this.fitsEnabled = fitsEnabled;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("creationDate")
    public Long getCreationDate() {
        return creationDate;
    }

    @JsonProperty("creationDate")
    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    @JsonProperty("fitsTimeOut")
    public Long getFitsTimeOut() {
        return fitsTimeOut;
    }

    @JsonProperty("fitsTimeOut")
    public void setFitsTimeOut(Long fitsTimeOut) {
        this.fitsTimeOut = fitsTimeOut;
    }

    @JsonProperty("veraPdfTimeOut")
    public Long getVeraPdfTimeOut() {
        return veraPdfTimeOut;
    }

    @JsonProperty("veraPdfTimeOut")
    public void setVeraPdfTimeOut(Long veraPdfTimeOut) {
        this.veraPdfTimeOut = veraPdfTimeOut;
    }

    @JsonProperty("veraPdfMaxHeapSize")
    public Long getVeraPdfMaxHeapSize() {
        return veraPdfMaxHeapSize;
    }

    @JsonProperty("veraPdfMaxHeapSize")
    public void setVeraPdfMaxHeapSize(Long veraPdfMaxHeapSize) {
        this.veraPdfMaxHeapSize = veraPdfMaxHeapSize;
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
