package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pages.LoginPage;
import testClients.Client;
import testClients.ClientLombok;

@Feature("Login functionality")
public class LoginTest {

	private static LoginPage loginPage;

	@BeforeAll
	@Step("Set up driver before tests")
	static void init() {
		BaseSetUp baseSetUp = new BaseSetUp();
		loginPage = PageFactory.initElements(BaseSetUp.driver, LoginPage.class);
	}

	@RegisterExtension
	ScreenshotOnFailure watcher = new ScreenshotOnFailure(BaseSetUp.driver, "target/surefire-reports");

	@Test
	@Story("Login successful")
	@Issue("issue-12345")
	@Description("Check login is successful after entering valid credentials")
	public void testLogin() {
		//GIVEN
		//example client with manual builder
		Client client = new Client.ClientBuilder(123L)
				.withName("test")
				.withLastName("testLastName")
				.build();

		//example of client with lombok builder
		ClientLombok clientLombok = ClientLombok.builder()
				.id(123L)
				.name("name")
				.build();

		String existingUserEmail = "test@test.com";
		String existingUserPassword = "test";
		//WHEN
		loginPage.openLoginPage();

		loginPage.setEmail(existingUserEmail);
		loginPage.setPassword(existingUserPassword);

		loginPage.submit();
		//THEN
		checkUserIsRedirectedToProducts();
	}

	@Step("Check user is redirected to /products")
	private void checkUserIsRedirectedToProducts() {
		String currentUrl = BaseSetUp.driver.getCurrentUrl();
		Assertions.assertEquals("http://online-sh.herokuapp.com/products", currentUrl);
	}

	@AfterAll
	@Step("Quit browser")
	static void tearDown(){
		BaseSetUp.driver.quit();
	}
}
