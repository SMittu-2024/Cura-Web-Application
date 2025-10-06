package automationpractice.CuraHealthWeb.utils;

/**
 * Utility class for executing JavaScript operations on web elements using Selenium WebDriver.
 * <p>
 * Provides helper methods to validate user input and retrieve validation messages from web elements
 * by leveraging the browser's built-in HTML5 validation via JavaScript.
 * <p>
 * Usage example:
 * <pre>
 *     JavaScriptUtils jsUtils = new JavaScriptUtils(driver);
 *     boolean isValid = jsUtils.checkIfValidInput(element);
 *     String message = jsUtils.getValidationMsg(element);
 * </pre>
 *
 * Dependencies:
 * - Selenium WebDriver (org.openqa.selenium)
 *
 * Author: Shruti Mittu
 * Date: October 6, 2025
 */

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

    private JavascriptExecutor jsExecutor;
    
    public JavaScriptUtils(WebDriver driver) {
        this.jsExecutor = (JavascriptExecutor) driver;
    }
    
    /**
     * Checks if the specified web element's input is valid according to HTML5 validation constraints.
     * Uses the browser's built-in checkValidity() method.
     *
     * @param element the WebElement to validate
     * @return true if the input is valid; false otherwise
     */
	public boolean checkIfValidInput(WebElement element) {
		Boolean isValid = (Boolean) jsExecutor.executeScript("return arguments[0].checkValidity();",
				element);
		return isValid;
	}

	/**
	 * Retrieves the browser-generated validation message for the specified web element.
	 * Returns an empty string if the element is valid.
	 *
	 * @param element the WebElement to check
	 * @return the validation message if invalid; otherwise, an empty string
	 */
	public String getValidationMsg(WebElement element) {
		String validationMessage = (String) jsExecutor.executeScript("return arguments[0].validationMessage;", 
				element);
		return validationMessage;
	}
}