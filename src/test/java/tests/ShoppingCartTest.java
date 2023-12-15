package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

@DisplayName("Testes E2E")
public class ShoppingCartTest extends BaseTest {

	private static final String PRODUCT_URL = BASE_URL + "/colecoes-cafes/metodo/capsulas";
	private static final String EXPECTED_EMPTY_CART_MESSAGE = "Seu carrinho de compras está vazio";
	private static final String EXPECTED_URL_SUBSTRING = "secure.mercafe.com.br";

	private static final By BUY_BUTTON_SELECTOR = By.cssSelector("[data-testid='buy-button']");
	private static final By STORE_INPUT_SELECTOR = By.cssSelector("[data-testid='store-input']");

	private static final By CARD_STORE_QUANTITY_RIGHT_BUTTON_SELECTOR = By.cssSelector(
			"section[data-testid='store-card-content'] button[data-testid='store-quantity-selector-right']");
	private static final By REMOVE_BUTTON_SELECTOR = By.cssSelector("button[data-testid='remove-from-cart-button']");

	private static final By EMPTY_CART_MESSAGE_SELECTOR = By.xpath(
			"//strong[@class='BoldText-codeby__sc-j4zszc-2 hITtQO'][contains(.,'"+EXPECTED_EMPTY_CART_MESSAGE+"')]");
	private static final By CHECKOUT_BUTTON_SELECTOR = By.cssSelector("[data-testid='checkout-button']");


	protected static final Logger logger = LogManager.getLogger(ShoppingCartTest.class);

	@Test
	public void testAddItemToCart() {
		try {

			navigateToUrlProduct();

			clickBuyButton();

			Assertions.assertEquals(1, getItemQuantity());

		} catch (Exception e) {

			retryTestAddItemToCart(e);
			if (isHandlingFailed())
				throw e;

		}
	}

	private void retryTestAddItemToCart(Exception exception) {
		super.handleExceptionWithRetryAndLog(exception, this::testAddItemToCart, "testAddItemToCarts");
	}

	@Test
	public void testEditCartItemQuantity() {
		try {

			navigateToUrlProduct();

			clickBuyButton();

			clickCardStoreQuantityRightButton();

			Assertions.assertEquals(2, getItemQuantity());

		} catch (Exception e) {

			retryTestEditCartItemQuantity(e);
			if (isHandlingFailed())
				throw e;

		}
	}

	private void retryTestEditCartItemQuantity(Exception exception) {
		super.handleExceptionWithRetryAndLog(exception, this::testEditCartItemQuantity, "testEditCartItemQuantity");
	}

	@Test
	public void testRemoveItemFromCart() {
		try {
			navigateToUrlProduct();
			clickBuyButton();
			clickRemoveButton();
			Assertions.assertEquals(EXPECTED_EMPTY_CART_MESSAGE, getEmptyCartMessage());

		} catch (Exception e) {

			retrytestRemoveItemFromCart(e);
			if (isHandlingFailed())
				throw e;

		}
	}

	private void retrytestRemoveItemFromCart(Exception exception) {
		super.handleExceptionWithRetryAndLog(exception, this::testRemoveItemFromCart, "testRemoveItemFromCart");
	}

	@Test
	public void testCheckout() {
		try {

			navigateToUrlProduct();
			clickBuyButton();
			clickCheckoutButton();
			Assertions.assertTrue(urlContains(EXPECTED_URL_SUBSTRING));

		} catch (Exception e) {

			retryTestCheckout(e);
			if (isHandlingFailed())
				throw e;

		}

	}
	
	private void retryTestCheckout(Exception exception) {
		super.handleExceptionWithRetryAndLog(exception, this::testCheckout, "testCheckout");
	}

	private void clickBuyButton() {
		super.waitElementAndClick(BUY_BUTTON_SELECTOR);
	}

	private void clickCardStoreQuantityRightButton() {
		super.waitElementAndClick(CARD_STORE_QUANTITY_RIGHT_BUTTON_SELECTOR);
	}

	private void clickRemoveButton() {
		super.waitElementAndClick(REMOVE_BUTTON_SELECTOR);
	}

	private void clickCheckoutButton() {
		super.waitElementAndClick(CHECKOUT_BUTTON_SELECTOR);
	}

	private int getItemQuantity() {
		String itemQuantity = waitElementAndGetValue(STORE_INPUT_SELECTOR);
		int result = Integer.parseInt(itemQuantity);
		return result;

	}

	private String getEmptyCartMessage() {
		return waitElementAndGetText(EMPTY_CART_MESSAGE_SELECTOR);
	}

	private void navigateToUrlProduct() {
		driver.get(PRODUCT_URL);
	}

}
