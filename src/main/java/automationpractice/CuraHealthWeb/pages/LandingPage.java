package automationpractice.CuraHealthWeb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LandingPage extends BasePage {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // Construction of elements is triggered after this method is called
	}

	// PageFactory Elements
	@FindBy(id = "btn-make-appointment")
	WebElement makeApptBtn;
	@FindBy(css = "#txt-username")
	WebElement usernameInput;
	@FindBy(xpath = "//input[@type = 'password']")
	WebElement passwordInput;

	// By Locators
	By alertMsgLocator = By.cssSelector(".lead.text-danger");
	By loginLocator = By.xpath("//button[@type='submit']");

	public AppointmentPage loginApplication(String username, String password) {
		// Click Make Appointment button to go to login screen
		click(makeApptBtn);
		wait.until(ExpectedConditions.urlContains("login"));
		enterText(usernameInput, username);
		enterText(passwordInput, password);
		safeClick(loginLocator);
		return (new AppointmentPage(driver));
	}

	public void goTo() {
		driver.get("https://katalon-demo-cura.herokuapp.com");
	}

	public String getAlertMessage() {
		waitForElementToAppear(alertMsgLocator);
		WebElement alertMsg = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".lead.text-danger")));
		return alertMsg.getText();
	}

}
