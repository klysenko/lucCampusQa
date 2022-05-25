package AutomationTests.scenarios;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import AutomationTests.pages.LoginPage;
import AutomationTests.pages.testClient.Client;

public class LoginTest {

	private static WebDriver driver;
	private static LoginPage loginPage;

	public LoginTest() {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.setHeadless(false);
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);

		driver = new ChromeDriver(options);
		loginPage = PageFactory.initElements(driver, LoginPage.class);
	}

	@Test
	public void testLogin() {
		//GIVEN
		Client client = new Client.ClientBuilder(123L)
				.withName("test")
				.withLastName("testLastName")
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

	private void checkUserIsRedirectedToProducts() {
		String currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("http://online-sh.herokuapp.com/products", currentUrl);
	}

	@AfterEach
	public void cleanUp() {
		driver.close();
		driver.quit();
	}
}
