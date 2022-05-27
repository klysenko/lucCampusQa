package tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

import io.qameta.allure.Attachment;

public class ScreenshotOnFailure implements TestWatcher {

	private final WebDriver driver;
	private String path;

	public ScreenshotOnFailure(WebDriver driver, String path) {
		this.driver = driver;
		this.path = path;
	}

	@Override
	public void testDisabled(ExtensionContext context, Optional<String> reason) {
		//no actions
	}

	@Override
	public void testSuccessful(ExtensionContext context) {

	}

	@Override
	public void testAborted(ExtensionContext context, Throwable cause) {

	}

	@Override
	public void testFailed(ExtensionContext context, Throwable cause) {
		try {
			captureScreenshot(driver, context.getDisplayName());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void captureScreenshot(WebDriver driver, String fileName) throws IOException {
		try {
			new File(path).mkdirs();
			try ( FileOutputStream out = new FileOutputStream(path + File.separator + "screenshot-" + fileName + ".png")) {
				out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			}
		} catch (IOException | WebDriverException e) {
			System.out.println("screenshot failed:" + e.getMessage());
		}
	}
}
