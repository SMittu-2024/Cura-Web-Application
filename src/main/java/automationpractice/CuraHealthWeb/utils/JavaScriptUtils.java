package automationpractice.CuraHealthWeb.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

    private JavascriptExecutor jsExecutor;
    
    public JavaScriptUtils(WebDriver driver) {
        this.jsExecutor = (JavascriptExecutor) driver;
    }
    
	// Validates if user input is valid (not missing or invalid format)
	public boolean checkIfValidInput(WebElement element) {
		Boolean isValid = (Boolean) jsExecutor.executeScript("return arguments[0].checkValidity();",
				element);
		return isValid;
	}

	// Returns validation message for any element
	public String getValidationMsg(WebElement element) {
		String validationMessage = (String) jsExecutor.executeScript("return arguments[0].validationMessage;", 
				element);
		return validationMessage;
	}
}
