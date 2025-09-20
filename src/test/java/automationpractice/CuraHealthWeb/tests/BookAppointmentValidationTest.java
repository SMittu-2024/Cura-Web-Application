package automationpractice.CuraHealthWeb.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import automationpractice.CuraHealthWeb.pages.AppointmentPage;

/* Negative test(s) related to Book Appointment page
 * Verify alert message for Missing date
 */
public class BookAppointmentValidationTest extends BaseTest {

	@Test
	public void verifyAlertOnBookAppointment() {
		String alertMessage = "Please fill out this field.";
		AppointmentPage appointmentPage = landingPage.loginApplication("John Doe", "ThisIsNotAPassword");
		Assert.assertTrue(appointmentPage.getAlertMissingDate().equals(alertMessage));
	}
}
