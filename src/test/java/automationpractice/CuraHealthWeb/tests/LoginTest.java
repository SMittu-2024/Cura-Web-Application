package automationpractice.CuraHealthWeb.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;

import automationpractice.CuraHealthWeb.pages.AppointmentPage;
import automationpractice.CuraHealthWeb.utils.ExcelUtils;

public class LoginTest extends BaseTest {

	@Test(dataProvider = "invalidExcelData")
	void InvalidUserLogInTest(String username, String password) {
		String alertMsg = "Login failed! Please ensure the username and password are valid.";

		// Verify alert message invalid username or password
		landingPage.loginApplication(username, password);
		Assert.assertEquals(alertMsg, landingPage.getAlertMessage());
		System.out.println("Checking invalid credentials - completed!");
	}
	
	@Test(dataProvider = "validExcelData")
	void ValidUserLogInTest(String username, String password) {
		// Verify successful login and user navigates to Appointment page
		AppointmentPage appointmentPage = landingPage.loginApplication(username, password);
		Assert.assertTrue(appointmentPage.getUrl().endsWith("#appointment"),
				"Validated user is navigated to Appointment page successfully.");
		System.out.println("appointmentPage.getUrl().endsWith(#appointment)"+appointmentPage.getUrl().endsWith("#appointment"));

	}
	
	@DataProvider(name = "validExcelData")
	public Object[][] validExcelDataProvider() throws IOException {
	    String filePath = System.getProperty("user.dir") + "/src/main/java/automationpractice/CuraHealthWeb/resources/TestData.xlsx";
	    List<List<String>> excelData = ExcelUtils.getSheetData(filePath, "ValidLogins");
	    Object[][] dataArr = new Object[excelData.size()][excelData.get(0).size()];
	    for (int i = 0; i < excelData.size(); i++) {
	        dataArr[i] = excelData.get(i).toArray(new String[0]);
	    }
	    return dataArr;
	}

	@DataProvider(name = "invalidExcelData")
	public Object[][] invalidExcelDataProvider() throws IOException {
	    String filePath = System.getProperty("user.dir") + "/src/main/java/automationpractice/CuraHealthWeb/resources/TestData.xlsx";
	    List<List<String>> excelData = ExcelUtils.getSheetData(filePath, "InvalidLogins");
	    Object[][] dataArr = new Object[excelData.size()][excelData.get(0).size()];
	    for (int i = 0; i < excelData.size(); i++) {
	        dataArr[i] = excelData.get(i).toArray(new String[0]);
	    }
	    return dataArr;
	}

}