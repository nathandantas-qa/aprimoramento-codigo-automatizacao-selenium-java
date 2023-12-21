package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helpers.WebDriverHelper;

public class BasePage {

	protected static final String BASE_URL = "https://www.mercafe.com.br";

	protected WebDriver driver;
	protected WebDriverHelper webDriverHelper;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		webDriverHelper = new WebDriverHelper(driver);
    	webDriverHelper.configure();
	}

	protected void waitElementAndClick(By locator) {
		webDriverHelper.waitElementAndClick(locator);
	}

	protected String waitElementAndGetValue(By locator) {
		return webDriverHelper.waitElementAndGetValue(locator);
	}

	protected String waitElementAndGetText(By locator) {
		return webDriverHelper.waitElementAndGetText(locator);
	}

	protected boolean urlContains(String urlSubstring) {
		return webDriverHelper.urlContains(urlSubstring);
	}
}
