package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;

	@FindBy(id="exampleInputEmail1")
	private WebElement emailInput;

	@FindBy(how = How.ID, using = "exampleInputPassword1")
	@CacheLookup
	private WebElement passwordInput;

	@FindBy(how = How.XPATH, using = "//button[contains(@class,'btn-primary')]")
	private WebElement submitButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Set email as {emailText}")
	public LoginPage setEmail(String emailText) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
		wait.until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys(emailText);
		return this;
	}

	@Step("Set password as {passwordText}")
	public LoginPage setPassword(String passwordText) {
		passwordInput.sendKeys(passwordText);
		return this;
	}

	@Step("Click submit button")
	public LoginPage submit() {
		submitButton.click();
		return this;
	}

	@Step("Open login page")
	public LoginPage openLoginPage() {
		driver.get("http://online-sh.herokuapp.com/login");
		return this;
	}
}
