package automationpractice.CuraHealthWeb.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import automationpractice.CuraHealthWeb.pages.AppointmentPage;
import automationpractice.CuraHealthWeb.pages.ConfirmationPage;
import automationpractice.CuraHealthWeb.pages.HistoryPage;

import java.util.LinkedHashMap;

public class BookAppointmentTest extends BaseTest {

	LinkedHashMap<String, String> userSelection;
	HistoryPage historyPage;

	@Test
	public void bookAppointmentTest(){

		// Login
		AppointmentPage appointmentPage = landingPage.loginApplication("John Doe", "ThisIsNotAPassword");

		// Appointment page
		// Select Facility
		String selectedFacility = appointmentPage.selectFacility();

		// Add selection to hashmap
		userSelection = new LinkedHashMap<>();
		userSelection.put("facility", selectedFacility);

		// Select Readmission checkbox
		String isSelected = appointmentPage.selectReadmission();
		userSelection.put("readmission", isSelected);

		// Enter appointment date
		String enteredDate = appointmentPage.enterDate();
		userSelection.put("date", enteredDate);

		// Click Book Appointment button
		ConfirmationPage confirmationPage = appointmentPage.clickBookAppointnmentBtn();
		System.out.println(userSelection);

		// Confirmation Page - Verify Appointment details
		SoftAssert validation = new SoftAssert();
		validation.assertTrue(userSelection.get("facility").equals(confirmationPage.getFacilityValue()));
		validation.assertTrue(userSelection.get("date").equals(confirmationPage.getDateValue()));
		validation.assertTrue(userSelection.get("readmission").equals(confirmationPage.getReAdmissionValue()));

		validation.assertAll();
	}

}
