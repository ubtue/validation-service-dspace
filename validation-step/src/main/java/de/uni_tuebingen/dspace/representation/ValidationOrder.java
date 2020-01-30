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
"configurationIdentifier",
"batchId"
})
public class ValidationOrder {

	@JsonProperty("configurationIdentifier")
	private String configurationIdentifier;
	@JsonProperty("batchId")
	private Long batchId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	@JsonProperty("configurationIdentifier")
	public String getConfigurationIdentifier() {
		return configurationIdentifier;
	}
	
	@JsonProperty("configurationIdentifier")
	public void setConfigurationIdentifier(String configurationIdentifier) {
		this.configurationIdentifier = configurationIdentifier;
	}
	
	@JsonProperty("batchId")
	public Long getBatchId() {
		return batchId;
	}
	
	@JsonProperty("batchId")
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
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