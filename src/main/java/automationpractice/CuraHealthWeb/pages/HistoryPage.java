package automationpractice.CuraHealthWeb.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HistoryPage extends BasePage {

	WebDriver driver;

	public HistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // Construction of elements is triggered after this method is called
	}

	// PageFactory Elements
	@FindBy(id = "facility")
	WebElement facility;
	@FindBy(id = "hospotal_readmission")
	WebElement readmission;
	@FindBy(className = "panel-heading")
	WebElement date;
	
	public String getFacility() {
		waitForElementToAppear(facility);
		return facility.getText();
	}
	
	public String getReAdmissionStatus() {
		return readmission.getText();
	}
	
	public String getDate() {
		return readmission.getText();
	}

}
