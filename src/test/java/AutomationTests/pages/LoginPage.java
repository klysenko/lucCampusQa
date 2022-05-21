package AutomationTests.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;

	By emailInput = By.id("exampleInputEmail1");
	By passwordInput = By.id("exampleInputPassword1");
	By submitButton= By.xpath("//button[contains(@class,'btn-primary')]");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage setEmail(String emailText) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
		wait.until(ExpectedConditions.presenceOfElementLocated(emailInput)).sendKeys(emailText);
		return this;
	}

	public LoginPage setPassword(String passwordText) {
		driver.findElement(passwordInput).sendKeys(passwordText);
		return this;
	}

	public LoginPage submit() {
		driver.findElement(submitButton).click();
		return this;
	}

	public LoginPage openLoginPage() {
		driver.get("http://online-sh.herokuapp.com/login");
		return this;
	}
}
