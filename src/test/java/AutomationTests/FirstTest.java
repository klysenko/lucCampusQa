package AutomationTests;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest {
	private static WebDriver driver;

	public FirstTest() {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.setHeadless(false);

		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000L));
	}

	@Test
	public void testSearchResultPresent_seleniumInput() {
		//GIVEN
		driver.get("https://www.google.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
		WebElement input = driver.findElement(By.xpath(".//input[@name='q']"));
		//WHEN
		input.sendKeys("QA automation");
		input.sendKeys(Keys.ENTER);
		//THEN
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//ul")));
		driver.close();
		driver.quit();

	}

	@Test
	public void testSearchResultPresent_JsInput() {
		//GIVEN
		driver.get("https://www.google.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
		WebElement input = driver.findElement(By.xpath(".//input[@name='q']"));
		//WHEN
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		// set the text
		jsExecutor.executeScript("arguments[0].value='QA'", input);
		input.sendKeys(Keys.ENTER);

		//THEN
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//ul")));
		driver.close();
		driver.quit();

	}
}





