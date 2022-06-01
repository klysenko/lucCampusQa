package tests;

import static tests.BaseSetUp.driver;

import java.lang.reflect.Method;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.qameta.allure.Attachment;
public class ScreenTestWatcher implements AfterTestExecutionCallback {

	@Override
	public void afterTestExecution(ExtensionContext context) {
		Method method = context.getRequiredTestMethod();
		if (context.getExecutionException().isPresent()) {
			makeScreenshot(method.getName());
		}

	}

	@Attachment(value = "{testName} - screen", type = "image/png")
	private byte[] makeScreenshot(String testName) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

}
