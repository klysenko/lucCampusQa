package testClients;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ModelClient {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("status")
	private String status;
}
