package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CapsulesPage extends BasePage{
	
	public static final String EXPECTED_EMPTY_CART_MESSAGE = "Seu carrinho de compras está vazio";

	private static final String CAPSULAS_URL = BASE_URL + "/capsulas";

	private static final By BUY_BUTTON_SELECTOR = By.cssSelector("[data-testid='buy-button']");
	
	private static final By STORE_INPUT_SELECTOR = By.cssSelector("[data-testid='store-input']");

	private static final By CARD_STORE_QUANTITY_RIGHT_BUTTON_SELECTOR = By.cssSelector(
			"section[data-testid='store-card-content'] button[data-testid='store-quantity-selector-right']");
	private static final By REMOVE_BUTTON_SELECTOR = By.cssSelector("button[data-testid='remove-from-cart-button']");

	private static final By EMPTY_CART_MESSAGE_SELECTOR = By.xpath(
			"//strong[@class='BoldText-codeby__sc-j4zszc-2 hITtQO'][contains(.,'"+EXPECTED_EMPTY_CART_MESSAGE+"')]");
	private static final By CHECKOUT_BUTTON_SELECTOR = By.cssSelector("[data-testid='checkout-button']");

	
	public CapsulesPage(WebDriver driver) {
		super(driver);		
	}


	public void clickBuyButton() {
		super.waitElementAndClick(BUY_BUTTON_SELECTOR);
	}

	public void clickCardStoreQuantityRightButton() {
		super.waitElementAndClick(CARD_STORE_QUANTITY_RIGHT_BUTTON_SELECTOR);
	}

	public void clickRemoveButton() {
		super.waitElementAndClick(REMOVE_BUTTON_SELECTOR);
	}

	public void clickCheckoutButton() {
		super.waitElementAndClick(CHECKOUT_BUTTON_SELECTOR);
	}

	public int getItemQuantity() {
		String itemQuantity = waitElementAndGetValue(STORE_INPUT_SELECTOR);
		int result = Integer.parseInt(itemQuantity);
		return result;

	}

	public String getEmptyCartMessage() {
		return waitElementAndGetText(EMPTY_CART_MESSAGE_SELECTOR);
	}

	public void navigateToUrlCapsulas() {
		driver.get(CAPSULAS_URL);
	}
	
}
