package pages;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class ProductsPage {

	private WebDriver driver;

	@FindBy(tagName = "table")
	private List<WebElement> allProductsView;

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
	}

	@Step
	public ProductsPage checkAllProductsViewIsPresent() {
		Assertions.assertThat(allProductsView.size()).isEqualTo(1);
		return this;
	}

	@Step
	public String checkProductsCountIsEqualToExpected(int expectedProductsCount) {
		List<WebElement> actualTableRows = allProductsView.get(0)
				.findElements(By.tagName("tbody"))
				.get(0).findElements(By.tagName("tr"));
		String firstRowText = actualTableRows.get(0).getText();
		Assertions.assertThat(actualTableRows.size()).isEqualTo(expectedProductsCount);
		return firstRowText;
	}

}
