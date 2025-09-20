package automationpractice.CuraHealthWeb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends BasePage {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // Construction of elements is triggered after this method is called
	}

	// WebElements PageFactory
	@FindBy(id = "facility")
	WebElement confirmFacility;
	@FindBy(id = "visit_date")
	WebElement confirmDate;
	@FindBy(id = "hospital_readmission")
	WebElement confirmReadmission;
	@FindBy(css = ".fa.fa-bars")
	WebElement menuIcon;
	@FindBy(xpath = "@contains(text()='History')")
	WebElement historyLink;

	// By locators
	By facilityLocator = By.id("facility");
	
	public String getFacilityValue() {
		waitForElementToAppear(confirmFacility);
		return confirmFacility.getText();
	}
	
	public String getDateValue() {
		waitForElementToAppear(confirmDate);
		return confirmDate.getText();
	}
	
	public String getReAdmissionValue() {
		waitForElementToAppear(confirmReadmission);
		return confirmReadmission.getText();
	}
	
	public HistoryPage clickHistorylink() {
		click(menuIcon);
		click(historyLink);
		return (new HistoryPage(driver));
	}

}
