package testClients;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Product {
	private Long id;
	private String name;
	private Long price;
	private String date;
}
