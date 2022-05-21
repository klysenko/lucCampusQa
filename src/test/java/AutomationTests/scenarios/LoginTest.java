package AutomationTests.scenarios;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import AutomationTests.pages.LoginPage;

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
		loginPage = new LoginPage(driver);
	}

	@Test
	public void testClick() {
		//GIVEN
		String existingUserEmail = "test@test.com";
		String existingUserPassword = "test";
		//WHEN
		loginPage.openLoginPage();

		LoginTest.loginPage.setEmail(existingUserEmail);
		LoginTest.loginPage.setPassword(existingUserPassword);

		LoginTest.loginPage.submit();
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
