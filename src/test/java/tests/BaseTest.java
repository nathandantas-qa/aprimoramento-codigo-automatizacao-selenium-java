package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import helpers.ExceptionHandlingHelper;

public class BaseTest {

	protected static final Logger logger = LogManager.getLogger(ShoppingCartTest.class);
	
	
	protected WebDriver driver;

	private ExceptionHandlingHelper exceptionHandlingHelper;

	
    @BeforeEach
    public void setUpTestEnvironment() {
        configureWebDriver();
        configureExceptionHandling();
        navigateToHomePage();
        closeAnnouncementPopup();       
    }

    @AfterEach
    public void tearDownTestEnvironment() {
    	 closeWebDriver();
    }

    private void configureExceptionHandling() {
    	exceptionHandlingHelper = new ExceptionHandlingHelper();
    }
    
    private void configureWebDriver() {
    	driver = new ChromeDriver();
    }
    
    private void navigateToHomePage() {
    	driver.get("https://www.mercafe.com.br");
    }

    private void closeAnnouncementPopup() {
    	driver.findElement(By.xpath(
                "//button[@class='Button-codeby__sc-qkp43z-4 gUUsRN'][contains(.,'CONTINUAR E FECHAR')]"))
    		  .click();
    }
	
    private void closeWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
	
   protected void handleExceptionWithRetryAndLog(Exception exception, Runnable retryAction, String TestName) {
	   resetTestEnvironment();
	   exceptionHandlingHelper.handleRetryAndLog(exception, retryAction, TestName);
   }
   
   private void resetTestEnvironment() {
	   closeWebDriver();
	   configureWebDriver();
       navigateToHomePage();
       closeAnnouncementPopup();
   }
   
   protected boolean isHandlingFailed() {
	   return exceptionHandlingHelper.isHandlingFailed();
   }
 
   
}
