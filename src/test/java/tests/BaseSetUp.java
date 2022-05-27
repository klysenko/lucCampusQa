package tests;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class BaseSetUp {

	public static WebDriver driver;

	public BaseSetUp() {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.setHeadless(false);
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);

		driver = new ChromeDriver(options);
	}
}
