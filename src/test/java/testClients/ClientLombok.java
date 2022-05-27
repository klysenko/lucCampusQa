package testClients;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class ClientLombok {
	private Long id;
	private String name;
	private String lastName;
	private String address;
	private Long balance;
	private Long rate;
	private String login;
}
