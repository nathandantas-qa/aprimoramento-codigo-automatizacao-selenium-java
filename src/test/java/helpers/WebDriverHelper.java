package helpers;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebDriverHelper {

	private WebDriver driver;
	private WaitHelper waitHelper;

	public WebDriverHelper(WebDriver driver) {
		this.driver = driver;
		waitHelper = new WaitHelper(driver);
	}

	public void configure() {
		maximizeWindow();
		setImplicitWait(Duration.ofSeconds(2));
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void setImplicitWait(Duration duration) {
		driver.manage().timeouts().implicitlyWait(duration);
	}

	public void waitElementAndClick(By locator) {
		waitHelper.waitElementToBeClickableWithShouldRetry(locator).click();
	}

	public String waitElementAndGetValue(By locator) {
		return waitHelper.waitVisibilityOfElementLocated(locator).getAttribute("value");
	}

	public String waitElementAndGetText(By locator) {
		return waitHelper.waitVisibilityOfElementLocated(locator).getText();
	}

	public boolean urlContains(String urlSubstring) {
		return waitHelper.urlContains(urlSubstring);
	}
}
