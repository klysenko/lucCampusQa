package testApiTesing;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsersTest {

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
		log.info("END: getAllUsers");
	}
}
