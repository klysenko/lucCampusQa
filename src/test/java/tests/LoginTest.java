package tests;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pages.LoginPage;
import pages.ProductsPage;
import testClients.Product;

@Feature("Login functionality")
@ExtendWith(ScreenTestWatcher.class)
public class LoginTest {

	private static LoginPage loginPage;
	private static ProductsPage productPage;

	@BeforeAll
	@Step("Set up driver before tests")
	static void init() {
		BaseSetUp baseSetUp = new BaseSetUp();
		loginPage = PageFactory.initElements(BaseSetUp.driver, LoginPage.class);
		productPage = PageFactory.initElements(BaseSetUp.driver, ProductsPage.class);
	}

	@BeforeEach
	@AfterEach
	void cleanUp() {
		//dbSteps.cleanProducts();
	}

	@RegisterExtension
	ScreenshotOnFailure watcher = new ScreenshotOnFailure(BaseSetUp.driver, "target/surefire-reports");

	@Test
	@Story("Login successful")
	@Issue("issue-12345")
	@Description("Check login is successful after entering valid credentials")
	public void testLogin() {
		//GIVEN
		String existingUserEmail = "test@test.com";
		String existingUserPassword = "test";
		Product product1 = Product.builder()
				.id(1L)
				.name("Product2022-05-31")
				.price(9L)
				.build();
		Product product2 = Product.builder()
				.id(1L).build();

		List<Product> products = List.of(product1, product2);
		//dbSteps.setProductsToDb(products);
		//WHEN
		loginPage.openLoginPage();

		loginPage.setEmail(existingUserEmail);
		loginPage.setPassword(existingUserPassword);

		loginPage.submit();

		//THEN
		checkUserIsRedirectedToProducts();
		productPage.checkAllProductsViewIsPresent();
		String firstRowText = productPage.checkProductsCountIsEqualToExpected(8);
		org.assertj.core.api.Assertions.assertThat(firstRowText)
				.contains(product1.getName());
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
