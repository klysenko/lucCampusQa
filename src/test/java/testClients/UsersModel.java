package testClients;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UsersModel {
	@JsonProperty("meta")
	private String meta;
	@JsonProperty("data")
	private ModelClient data;
}
