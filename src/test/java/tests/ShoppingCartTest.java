package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import page.CapsulesPage;
import page.CheckoutPage;

@DisplayName("Testes E2E")
public class ShoppingCartTest extends BaseTest {

	@Test
	public void testAddItemToCart() {
		
		try {
			
			CapsulesPage capsulesPage = new CapsulesPage(driver); 
			
			capsulesPage.navigateToUrlCapsulas();
			
			capsulesPage.clickBuyButton();
			
			int itemQuantity = capsulesPage.getItemQuantity();
			
			Assertions.assertEquals(1, itemQuantity);

		} catch (Exception e) {

			retryTestAddItemToCart(e);
			if (isHandlingFailed())
				throw e;
		}
	
	}

	@Test
	public void testEditCartItemQuantity() {
		try {

			CapsulesPage capsulesPage = new CapsulesPage(driver); 
			
			capsulesPage.navigateToUrlCapsulas();
			
			capsulesPage.clickBuyButton();

			capsulesPage.clickCardStoreQuantityRightButton();
			
			int itemQuantity = capsulesPage.getItemQuantity();
			
			Assertions.assertEquals(2, itemQuantity);

		} catch (Exception e) {

			retryTestEditCartItemQuantity(e);
			if (isHandlingFailed())
				throw e;

		}
	
	}

	@Test
	public void testRemoveItemFromCart() {
	
		try {
		
			CapsulesPage capsulesPage = new CapsulesPage(driver); 

			capsulesPage.navigateToUrlCapsulas();
			
			capsulesPage.clickBuyButton();
					
			capsulesPage.clickRemoveButton();
									
			Assertions.assertEquals(CapsulesPage.EXPECTED_EMPTY_CART_MESSAGE , capsulesPage.getEmptyCartMessage());

		} catch (Exception e) {

			retrytestRemoveItemFromCart(e);
			if (isHandlingFailed())
				throw e;

		}

	}

	
	@Test
	public void testCheckout() {
		try {

			CapsulesPage capsulesPage = new CapsulesPage(driver); 

			capsulesPage.navigateToUrlCapsulas();
			
			capsulesPage.clickBuyButton();
			
			capsulesPage.clickCheckoutButton();
			
			CheckoutPage checkoutPage = new CheckoutPage(driver);
			
			Assertions.assertTrue(checkoutPage.isCurrentPage());
			

		} catch (Exception e) {

			retryTestCheckout(e);
			if (isHandlingFailed())
				throw e;

		}

	}
	
	
	private void retryTestAddItemToCart(Exception exception) {
		super.handleExceptionWithRetryAndLog(exception, this::testAddItemToCart, "testAddItemToCarts");
	}
	
	private void retryTestEditCartItemQuantity(Exception exception) {
		super.handleExceptionWithRetryAndLog(exception, this::testEditCartItemQuantity, "testEditCartItemQuantity");
	}
	
	private void retryTestCheckout(Exception exception) {
		super.handleExceptionWithRetryAndLog(exception, this::testCheckout, "testCheckout");
	}

	private void retrytestRemoveItemFromCart(Exception exception) {
		super.handleExceptionWithRetryAndLog(exception, this::testRemoveItemFromCart, "testRemoveItemFromCart");
	}


}
