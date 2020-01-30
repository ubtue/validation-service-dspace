
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
    "status",
    "creationDate",
    "finishedDate",
    "summary"
})
public class BatchReport {

    @JsonProperty("_links")
    private List<Link> links = null;
    @JsonProperty("_embedded")
    private Map<String, Object> embedded = new HashMap<String, Object>();
    @JsonProperty("id")
    private Long id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("creationDate")
    private Long creationDate;
    @JsonProperty("finishedDate")
    private Long finishedDate;
    @JsonProperty("summary")
    private Summary summary;
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

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("creationDate")
    public Long getCreationDate() {
        return creationDate;
    }

    @JsonProperty("creationDate")
    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    @JsonProperty("finishedDate")
    public Long getFinishedDate() {
        return finishedDate;
    }

    @JsonProperty("finishedDate")
    public void setFinishedDate(Long finishedDate) {
        this.finishedDate = finishedDate;
    }

    @JsonProperty("summary")
    public Summary getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	@Override
	public String toString() {
		return "BatchReport [links=" + links + ", embedded=" + embedded + ", id=" + id + ", status=" + status
				+ ", creationDate=" + creationDate + ", finishedDate=" + finishedDate + ", summary=" + summary
				+ ", additionalProperties=" + additionalProperties + "]";
	}
    
    

}
