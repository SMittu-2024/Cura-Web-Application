package automationpractice.CuraHealthWeb.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automationpractice.CuraHealthWeb.pages.AppointmentPage;

public class LoginTest extends BaseTest {

	@Test(dataProvider = "invalidCredentials")
	void InvalidUserLogInTest(String username, String password) {
		String alertMsg = "Login failed! Please ensure the username and password are valid.";

		// Verify alert message invalid username or password
		landingPage.loginApplication("Johnny Doe", "ThisIsNotAPassword");
		Assert.assertEquals(alertMsg, landingPage.getAlertMessage());
		System.out.println("Checking invalid credentials - completed!");
	}
	
	@Test
	void ValidUserLogInTest() {
		// Verify successful login and user navigates to Appointment page
		AppointmentPage appointmentPage = landingPage.loginApplication("John Doe", "ThisIsNotAPassword");
		Assert.assertTrue(appointmentPage.getUrl().endsWith("#appointment"),
				"Validated user is navigated to Appointment page successfully.");
		System.out.println("appointmentPage.getUrl().endsWith(#appointment)"+appointmentPage.getUrl().endsWith("#appointment"));

	}

	@DataProvider(name = "invalidCredentials")
	public Object[][] invalidData() {
		return new Object[][] { { "Johnny Doe", "ThisIsNotAPassword" }, { "John Doe", "ThisIsWrongPassword" },
				{ "", "ThisIsNotAPassword" }, // empty username
				{ "John Doe", "" } // empty password
		};
	}

}
