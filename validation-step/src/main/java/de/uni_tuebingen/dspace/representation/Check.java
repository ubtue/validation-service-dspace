
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
    "checkType",
    "test",
    "outcome",
    "resultMessage"
})
public class Check {

    @JsonProperty("_links")
    private List<Link> links = null;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("checkType")
    private String checkType;
    @JsonProperty("test")
    private Object test;
    @JsonProperty("outcome")
    private String outcome;
    @JsonProperty("resultMessage")
    private String resultMessage;
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

    @JsonProperty("checkType")
    public String getCheckType() {
        return checkType;
    }

    @JsonProperty("checkType")
    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    @JsonProperty("test")
    public Object getTest() {
        return test;
    }

    @JsonProperty("test")
    public void setTest(Object test) {
        this.test = test;
    }

    @JsonProperty("outcome")
    public String getOutcome() {
        return outcome;
    }

    @JsonProperty("outcome")
    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    @JsonProperty("resultMessage")
    public String getResultMessage() {
        return resultMessage;
    }

    @JsonProperty("resultMessage")
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
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
