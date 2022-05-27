package testClients;

public class Client {
	private final Long id;
	private final String name;
	private final String lastName;
	private final String address;
	private final Long balance;
	private final Long rate;
	private final String login;

	private Client(ClientBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.lastName = builder.lastName;
		this.address = builder.address;
		this.balance = builder.balance;
		this.rate = builder.rate;
		this.login = builder.login;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public Long getBalance() {
		return balance;
	}

	public Long getRate() {
		return rate;
	}

	@Override
	public String toString() {
		return "Client{" +
				"id=" + id +
				", name='" + name + '\'' +
				", lastName='" + lastName + '\'' +
				", address='" + address + '\'' +
				", balance=" + balance +
				", rate=" + rate +
				'}';
	}

	public static class ClientBuilder {

		private final Long id;
		private String name;
		private String lastName;
		private String address;
		private Long balance;
		private Long rate;
		private String login;

		public ClientBuilder(Long id)
		{
			this.id = id;
		}

		public ClientBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public ClientBuilder withLastName(String name) {
			this.lastName = name;
			return this;
		}

		public ClientBuilder withAddress(String address) {
			this.address = address;
			return this;
		}

		public ClientBuilder withBalance(Long balance) {
			this.balance = balance;
			return this;
		}

		public ClientBuilder withRate(Long rate) {
			this.rate = rate;
			return this;
		}

		public ClientBuilder withLogin(String login) {
			this.login = login;
			return this;
		}

		public Client build() {
			return new Client(this);

		}
	}


}
