package automationpractice.CuraHealthWeb.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.SoftAssert;

import automationpractice.CuraHealthWeb.pages.AppointmentPage;
import automationpractice.CuraHealthWeb.pages.ConfirmationPage;
import automationpractice.CuraHealthWeb.pages.LandingPage;

import java.time.Duration;
import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
			
		//Driver SetUp
	    WebDriverManager.chromedriver().setup();
	    ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito"); 
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
		//Login		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		AppointmentPage appointmentPage = landingPage.loginApplication("John Doe", "ThisIsNotAPassword");
		
		//Make Appointment		
		LinkedHashMap<String,String> userSelection = new LinkedHashMap<>();	
		
		//Select Facility
		String selectedFacility = appointmentPage.selectFacility();
		userSelection.put("facility",selectedFacility);   
        
        //Select Readmission checkbox
		String isSelected = appointmentPage.selectReadmission();
		userSelection.put("readmission", isSelected);

		//Enter appointment date
		String enteredDate = appointmentPage.enterDate();
		userSelection.put("date",enteredDate);

		//Click Book Appointment button
		ConfirmationPage confirmationPage = appointmentPage.clickBookAppointnmentBtn();	
		System.out.println(userSelection);
				
		//Confirmation Page- Verify Appointment details			
		//Assert.assertTrue(confirmationPage.confirmAppointmentDetails(userSelection));	
		SoftAssert validation = new SoftAssert();
		validation.assertTrue(userSelection.get("facility").equals(confirmationPage.getFacilityValue()));
		validation.assertTrue(userSelection.get("date").equals(confirmationPage.getDateValue()));
		validation.assertTrue(userSelection.get("readmission").equals(confirmationPage.getReAdmissionValue()));

		validation.assertAll();
		
		driver.quit();		
	}
}
