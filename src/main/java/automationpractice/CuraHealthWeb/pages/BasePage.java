package automationpractice.CuraHealthWeb.pages;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	WebDriver driver;
	WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Explicit wait for an element
	public void waitForElementToAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForElementToAppear(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	/**
	 * Reusable method to select a random option from the drop down
	 *
	 * @param element The WebElement which is associated with drop down.
	 */
	public String selectFromDropdown(WebElement element) {
		waitForElementToAppear(element);
		Select dropDown = new Select(element);
		int CountOfOptions = dropDown.getAllSelectedOptions().size();
		Random random = new Random();
		int randomNumber = random.nextInt(CountOfOptions);
		dropDown.selectByIndex(randomNumber);
		String selectedOption = dropDown.getFirstSelectedOption().getText();
		return selectedOption;
	}

	/**
	 * Reusable method to perform click on a WebElement.
	 *
	 * @param element The WebElement which needs to be clicked.
	 */
	public void click(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			if (element != null && element.isDisplayed() && element.isEnabled()) {
				element.click();
				System.out.println("Element clicked successfully: " + element.getText());
			}
		} catch (TimeoutException e) {
			System.out.println("Element not clickable within timeout: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Failed to click element: " + e.getMessage());
		}
	}

	/**
	 * Reusable method to perform click on a WebElement.
	 *
	 * @param locator The locator of the WebElement which needs to be clicked.
	 */
	public void click(By locator) {
		try {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			if (element != null && element.isDisplayed() && element.isEnabled()) {
				element.click();
				System.out.println("Element clicked successfully: " + element.getText());
			}
		} catch (TimeoutException e) {
			System.out.println("Element not clickable within timeout: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Failed to click element: " + e.getMessage());
		}
	}

	/**
	 * Reusable method to perform click on a risky WebElement. use selectively when
	 * there are DOM reloads or dynamic updates
	 * 
	 * @param locator The locator of the WebElement which needs to be clicked.
	 */
	public void safeClick(By locator) {
		int attempts = 0;
		while (attempts < 3) {
			try {
				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
				element.click();
				System.out.println("Element clicked successfully: " + element.getText());
				return;
			} catch (StaleElementReferenceException e) {
				System.out.println("Retry due to stale element...");
			}
			attempts++;
		}
		throw new RuntimeException("Unable to click after retries: " + locator.toString());
	}

	/*
	 * try { WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 * wait.until(ExpectedConditions.elementToBeClickable(element));
	 * element.click(); logger.info("Element clicked successfully"); } catch
	 * (TimeoutException e) { logger.error("Element not clickable within timeout: "
	 * + e.getMessage()); } catch (Exception e) {
	 * logger.error("Failed to click element: " + e.getMessage()); }
	 */
	/**
	 * Reusable method to enter text into a WebElement.
	 *
	 * @param element The WebElement where text needs to be entered.
	 * @param text    The text to be entered.
	 */
	public void enterText(WebElement element, String text) {
		try {
			waitForElementToAppear(element);
			if (element != null && element.isDisplayed() && element.isEnabled()) {
				element.clear(); // Clear any existing text
				element.sendKeys(text); // Enter the new text
				System.out.println("Text entered successfully for " + element.getText() + ": " + text);
			} else {
				System.err.println("Element is either null, not displayed, or not enabled.");
			}
		} catch (TimeoutException e) {
			System.out.println("Element not visible within timeout: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error while entering text: " + e.getMessage());
		}
	}

}
