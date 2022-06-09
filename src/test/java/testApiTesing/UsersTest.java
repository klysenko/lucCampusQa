package testApiTesing;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.bytebuddy.utility.RandomString;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import testClients.ModelClient;
import testClients.UsersModel;
import tests.UsersInterface;

public class UsersTest {
	private static final Logger log = LoggerFactory.getLogger(UsersTest.class);

	private static final String BASE_URI = "https://gorest.co.in/public/v1";

	@Test
	void checkGetAllUsersEndpoint() {
		log.info("START: gerAllUsers");
		Response response = given()
				.baseUri(BASE_URI)
				.when()
				.get("/users");

		response.prettyPrint();

		response.then().statusCode(200);
		response.then().statusLine("HTTP/1.1 200 OK");
		response.then().body(Matchers.not(Matchers.empty()));
		response.then().header("Content-type", "application/json; charset=utf-8");

		List<Object> firstPageClients = response.jsonPath().getList("data");
		Assertions.assertThat(firstPageClients.size()).isEqualTo(20);
		Object firstClientName = response.jsonPath().get("data[0].name");
		Assertions.assertThat(firstClientName).isEqualTo("test");
		org.junit.jupiter.api.Assertions.assertEquals("test", firstClientName);
		log.info("END: getAllUsers");
	}

	@Test
	void checkAddNewUserEndpoint_returns_401_not_authorized() {
		Response postResponse = given().baseUri(BASE_URI)
				.when()
				.post("/users");
		postResponse.then().statusCode(401);
	}

	@Test
	void checkAddNewUserEndpoint_success() {
		String uniqueEmailGenerated = RandomString.make(5) + "@test.com";
		String name = "Test name";

		ModelClient testClient = new ModelClient();
		testClient.setName(name);
		testClient.setEmail(uniqueEmailGenerated);
		testClient.setGender("male");
		testClient.setStatus("active");

		Response postResponse = given().baseUri(BASE_URI)
				.when()
				.header("Authorization", "Bearer 6a2e66915f5232398603c71eda843f6076c46a853840ec5046ae6b7190db7f36")
				.header("Content-type", ContentType.JSON)
				.body(testClient)
				.post("/users");

		postResponse.prettyPrint();
		postResponse.then().statusCode(201);

		UsersModel actualModel = postResponse.body().as(UsersModel.class);

		//with assertions
		Assertions.assertThat(actualModel.getData().getName()).isEqualTo(name);
		Assertions.assertThat(actualModel.getData().getEmail()).isEqualTo(uniqueEmailGenerated);
		Assertions.assertThat(actualModel.getData().getGender()).isEqualTo(testClient.getGender());
		Assertions.assertThat(actualModel.getData().getStatus()).isEqualTo(testClient.getStatus());
		org.junit.jupiter.api.Assertions.assertNotNull(actualModel.getData().getId());

		//with matchers
		postResponse.then().body("data", Matchers.hasEntry("email", uniqueEmailGenerated));
		postResponse.then().body("data", Matchers.hasEntry("name", testClient.getName()));
		postResponse.then().body("data", Matchers.hasEntry("gender", testClient.getGender()));
		postResponse.then().body("data", Matchers.hasEntry("status", testClient.getStatus()));
		postResponse.then().body("data.id", Matchers.not(Matchers.empty()));

		String actualName = postResponse.jsonPath().getString("data.name");
		Assertions.assertThat(actualName).isEqualTo(testClient.getName());

	}

	@Test
	void checkUpdateNewUserEndpoint_success() {
		String uniqueEmailGenerated = RandomString.make(5) + "@test.com";
		String name = "Test name";
		Long id = 7730L;

		ModelClient testClient = new ModelClient();
		testClient.setName(name);
		testClient.setEmail(uniqueEmailGenerated);
		testClient.setGender("female");
		testClient.setStatus("active");
		testClient.setId(id);

		Response postResponse = given().baseUri(BASE_URI)
				.when()
				.header("Authorization", "Bearer 6a2e66915f5232398603c71eda843f6076c46a853840ec5046ae6b7190db7f36")
				.header("Content-type", ContentType.JSON)
				.body(testClient)
				.put(String.format("/users/%s", id));

		postResponse.prettyPrint();
		postResponse.then().statusCode(200);

		UsersModel actualModel = postResponse.body().as(UsersModel.class);

		//with assertions
		Assertions.assertThat(actualModel.getData().getName()).isEqualTo(name);
		Assertions.assertThat(actualModel.getData().getEmail()).isEqualTo(uniqueEmailGenerated);
		Assertions.assertThat(actualModel.getData().getGender()).isEqualTo(testClient.getGender());
		Assertions.assertThat(actualModel.getData().getStatus()).isEqualTo(testClient.getStatus());
		Assertions.assertThat(actualModel.getData().getId()).isEqualTo(testClient.getId());

	}
	@Test
	void checkDeleteUserEndpoint_success() {
		Long id = 7730L;

		Response postResponse = given().baseUri(BASE_URI)
				.when()
				.header("Authorization", "Bearer 6a2e66915f5232398603c71eda843f6076c46a853840ec5046ae6b7190db7f36")
				.header("Content-type", ContentType.JSON)
				.delete(String.format("/users/%s", id));

		postResponse.prettyPrint();
		postResponse.then().statusCode(204);
	}

	@Test
	void getOneUser() throws IOException {
		//GIVEN
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		Retrofit client = new Retrofit.Builder()
				.baseUrl("https://gorest.co.in/")
				.addConverterFactory(GsonConverterFactory.create())
				.client(builder.build())
				.build();
		UsersInterface usersInterface = client.create(UsersInterface.class);
		//WHEN
		retrofit2.Response<ModelClient> result =
				usersInterface.getUser("7757", "Bearer 6a2e66915f5232398603c71eda843f6076c46a853840ec5046ae6b7190db7f36").execute();
		//THEN
		Assertions.assertThat(result.code()).isEqualTo(200);

	}

}
