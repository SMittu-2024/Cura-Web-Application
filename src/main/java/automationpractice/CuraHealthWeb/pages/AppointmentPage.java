package automationpractice.CuraHealthWeb.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AppointmentPage extends BasePage {

	WebDriver driver;
	LinkedHashMap<String, String> userSelection = new LinkedHashMap<>();

	public AppointmentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // Construction of elements is triggered after this method is called
	}

	// PageFactory Elements
	@FindBy(id = "combo_facility")
	WebElement facilityEle;
	@FindBy(id = "chk_hospotal_readmission")
	WebElement readmissionChkBx;
	@FindBy(id = "txt_visit_date")
	WebElement calendarInput;

	// By Locators
	By bookApptLocator = By.id("btn-book-appointment");
	By facilityLocator = By.id("combo_facility");

	public String selectFacility() {
		String selectedFacility = selectFromDropdown(facilityEle);
		return selectedFacility;
	}

	public String selectReadmission() {
		click(readmissionChkBx);
		String isSelected = readmissionChkBx.isSelected() ? "Yes" : "No";
		return isSelected;
	}

	// Enter current date to Date field
	public String enterDate() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = currentDate.format(formatter);
		calendarInput.sendKeys(formattedDate);
		return formattedDate;
	}

	public ConfirmationPage clickBookAppointnmentBtn() {
		click(bookApptLocator);
		return (new ConfirmationPage(driver));
	}

	public String getUrl() {
		wait.until(ExpectedConditions.urlContains("#appointment"));
		return driver.getCurrentUrl();
	}

	/*
	 * Get missing date alert message using JavascriptExecutor
	 */
	public String getAlertMissingDate() {
		String validationMessage = "";
		click(bookApptLocator);
		// Validates if user input is valid (not missing or invalid date)
		Boolean isValid = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();",
				calendarInput);
		if (!isValid) {
			validationMessage = (String) ((JavascriptExecutor) driver)
					.executeScript("return arguments[0].validationMessage;", calendarInput);
			System.out.println("Validation message: " + validationMessage);
		}
		return validationMessage;
	}
}
