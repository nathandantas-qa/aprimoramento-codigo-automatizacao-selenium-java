package page;

import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{

	private static final String BASE_URL_SECURE= "secure.mercafe.com.br";
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean isCurrentPage() {
		return urlContains(BASE_URL_SECURE);
	}
	
}
