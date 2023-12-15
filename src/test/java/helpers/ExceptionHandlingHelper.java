package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExceptionHandlingHelper {

	private final int MAX_RETRIES = 2;
	private int remainingRetries;
	private boolean handlingFailed;

	private static final Logger logger = LogManager.getLogger(ExceptionHandlingHelper.class);

	public ExceptionHandlingHelper() {
		remainingRetries = MAX_RETRIES;
		handlingFailed = false;
	}

	public void handleRetryAndLog(Exception exception, Runnable retryAction, String testName) {
		if (isHandling()) {
			registerAttempts(exception, testName);
			retryAction.run();
		} else {
			registerAttemptsExhausted(exception, testName);
			handlingFailed = true;
		}
	}

	public boolean isHandlingFailed() {
		return handlingFailed;
	}

	private boolean isHandling() {
		return remainingRetries <= 0 ? false : true;
	}

	private void registerAttempts(Exception exception, String testName) {
		logger.info("Test: {} - Exception occurred: {}. Remaining attempts: {}.", testName,
				exception.getClass().getSimpleName(), remainingRetries);

		remainingRetries--;
	}

	private void registerAttemptsExhausted(Exception exception, String testName) {
		logger.error("Test: {} - Attempts exhausted, throwing exception: {}. Exception details: {}", testName,
				exception.getClass().getSimpleName(), exception.getMessage());
	}

}