package helpers;

import static java.lang.String.format;

import java.time.Duration;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

	private static final Logger logger = LogManager.getLogger(WaitHelper.class);
	
	private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(5);
    private static final int TIMES_TO_RETRY = 2;
    private static final String WEB_ELEMENT_NOT_LOCATED_MESSAGE = "The web element with locator %s was not located after %d attempts";

	
	private WebDriverWait wait;


	public WaitHelper(WebDriver driver) {
		super();
		this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
	}
	

	public boolean urlContains(String urlSubstring) {
		return wait.until(ExpectedConditions.urlContains(urlSubstring));
	}

	private <T> T retryLogic(Supplier<T> action, By locator) {
	    boolean shouldRetry = true;
	    int attempt = TIMES_TO_RETRY;

	    while (shouldRetry) {
	        try {
	            T result = action.get();
	            shouldRetry = false;
	            return result;
	        } catch (Exception exception) {
	            logger.info(format("%s - attempt: %d", locator.toString(), attempt));
	            attempt--;
	            if (attempt <= 0) {
	                shouldRetry = false;
	                throw new NoSuchElementException(format(WEB_ELEMENT_NOT_LOCATED_MESSAGE, locator, TIMES_TO_RETRY));
	            }
	        }
	    }
	    return null; //INFO: shouldRetry cannot start with false.
    }

	public WebElement waitElementToBeClickableWithShouldRetry(By locator) {
        return retryLogic(() -> wait.until(ExpectedConditions.elementToBeClickable(locator)), locator);
    }
	
	public WebElement waitVisibilityOfElementLocated(By locator) {
		return retryLogic(() -> wait.until(ExpectedConditions.visibilityOfElementLocated(locator)), locator);
	}
	 
}
