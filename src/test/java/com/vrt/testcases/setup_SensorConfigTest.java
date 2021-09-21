package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.Setup_SensorConfigPage;
import com.vrt.pages.Setup_defineSetupPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.Setup_GroupSensorsPage;

import com.vrt.utility.TestUtilities;
import com.vrt.utility.setupCreationUtility;
import com.vrt.pages.Setup_SensorDescriptionPage;

public class setup_SensorConfigTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>SetupTestData.xlsx sheet for test data i/p

	public setup_SensorConfigTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExtentReports extent;
	public ExtentTest extentTest;
	TestUtilities tu = new TestUtilities();

	// Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	Setup_defineSetupPage defineSetupPage;
	Setup_SensorConfigPage Setup_SensorConfigPage;
	Setup_GroupSensorsPage Setup_GroupSensorsPage;
	Setup_SensorDescriptionPage Setup_SensorDescriptionPage;

	// Before All the tests are conducted
	@BeforeTest
	public void PreSetup() throws InterruptedException, IOException, AWTException, ParseException {

		extent = new ExtentReports(
				System.getProperty("user.dir") + "/test-output/ER_" + "setup_SensorConfigTest" + ".html", true);
		extent.addSystemInfo("TestSuiteName", "setup_SensorConfigTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name2"));
		System.out.println("setup_SensorConfigTest in Progress..");

		// Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		// Rename the cache Setup file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache\\ValProbeRT", "Setup.txt");
		// Rename the VRT Setups folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTSetups");

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		// Thread.sleep(500);
		LoginPage = new LoginPage();
		extent.addSystemInfo("VRT Version", LoginPage.get_SWVersion_About_Text());
		// Method to Create Very 1st User with All privilege
		UserManagementPage = LoginPage.DefaultLogin();
		LoginPage = UserManagementPage.FirstUserCreation("User1", getUID("adminFull"), getPW("adminFull"),
				getPW("adminFull"), "FullAdmin", "12345678", "abc@gmail.com");
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("User1");

		UserManagementPage.clickPrivRunQual();
		UserManagementPage.clickPrivCreateEditAsset();
		UserManagementPage.clickPrivCreateEditSetup();
		UserManagementPage.clickPrivRunCal();

		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		// Method to Create 1st Asset
		assetHubPage = MainHubPage.Click_AssetTile();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath", "aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		AppClose();
		Thread.sleep(500);

	}

	// After All the tests are conducted
	@AfterTest
	public void endReport_releaseMomory() {
		extent.flush();
		extent.close();
		//assetHubPage.resetWebElements();
		// System.out.println("Reset Webelement memory released");
		System.out.println("setup_SensorConfigTest Completed.");
	}

	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName("test");
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.enter_defineSetupPage_SensorCount("299");
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
	}

	// TearDown of the App
	@AfterMethod(alwaysRun = true)
	public void Teardown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			// to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getName() + " #");
			// to add error/exception in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getThrowable() + " #");
			// to add screenshot in extent report
			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1));
			// to add screencast/video in extent report
			// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName() + " #");
			// to add screenshot in extent report
			// String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver,
			// result.getName());
			// extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2));

		}
		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report

		driver.quit();
	}

	// Test Cases // SC001

	@Test(groups = { "Regression" }, description = "SC001-Verify the details displayed in Sensor Configuration screen")
	public void SC001() throws InterruptedException, IOException {
		extentTest = extent.startTest("SC001-Verify the details displayed in Sensor Configuration screen");
		SoftAssert sa = new SoftAssert();
		defineSetupPage = Setup_SensorConfigPage.DefineSetup_back_btn();
		String Sname_Definesetuppage = defineSetupPage.get_setupName_txtData();
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		String Sname_SensorConfigPage = Setup_SensorConfigPage.get_SensorConfigurationPage_titletext();
		sa.assertEquals(Sname_Definesetuppage, Sname_SensorConfigPage,
				"Fail: Wrong setup name is displayed as header on top left ");
		sa.assertEquals(Setup_SensorConfigPage.SensorConfig_setupname_state(), true,
				"FAIL: setup name as header is not available");
		sa.assertEquals(Setup_SensorConfigPage.sensorConfigPage_state(), true, "FAIL: It should be available");
		sa.assertEquals(Setup_SensorConfigPage.AddSensors_btn_state(), true,
				"FAIL: AddSensors_button is not available");
		sa.assertEquals(Setup_SensorConfigPage.ConfigureSensors_btn_state(), true,
				"FAIL:ConfigureSensors_button is not available");

		sa.assertEquals(Setup_SensorConfigPage.DefineSetup_btn_state(), true,
				"FAIL:DefineSetup_button is not available on the top left side");

		sa.assertEquals(Setup_SensorConfigPage.GroupSensors_btn_state(), true,
				"GroupSensors_button  is not available on the top right side");
		sa.assertAll();
	}

	// SC002-Verify that sensors pane on the left side will display no sensors and
	// will be blank till no sensor types are defined and selected

	@Test(groups = { "Regression" }, description = "Verify that sensors pane on the left side will display no sensors "
			+ "and will be blank till no sensor types are defined and selected")

	public void SC002() throws InterruptedException {
		extentTest = extent.startTest(
				"SC002:Verify that sensors pane on the left side will display no sensors and will be blank till no sensor types are defined and selected");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_GroupSensors_btn();
		String actalert = tu.get_AlertMsg_text();
		String expalert = "Please configure atleast one sensor to proceed.";
		sa.assertEquals(actalert, expalert,
				"FAIL: Application should display the alert message as there is no sensors added ");
		sa.assertAll();
	}

	// SC003-Verify that on-click of the Down arrow icon for Add Sensors section
	// expands and displays the Sensor types

	@Test(groups = {
			"Regression" }, description = "Verify that on-click of the Down arrow icon for Add Sensors section expands and displays the Sensor types")
	public void SC003() throws InterruptedException {
		extentTest = extent.startTest(
				"SC003-Verify that on-click of the Down arrow icon for Add Sensors section expands and displays the Sensor types");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		sa.assertEquals(Setup_SensorConfigPage.Temperature_Field_state(), true,
				"FAIL: Temperature_Field should be visible ");
		sa.assertEquals(Setup_SensorConfigPage.Humidity_Field_state(), true, "FAIL: Humidity_Field should be visible ");
		sa.assertEquals(Setup_SensorConfigPage.Pressure_Field_state(), true, "FAIL: Pressure_Field should be visible ");
		sa.assertEquals(Setup_SensorConfigPage.Add_btnField_state(), true, "FAIL: Add_btnField should be visible ");
		sa.assertAll();
	}

	// SC003_1- Verify the default value displayed in Temperature field under
	// Addsensors section

	@Test(groups = {
			"Regression" }, description = "Verify the default value displayed in Temperature field under Add sensors section")

	public void SC003_1() throws InterruptedException {
		extentTest = extent
				.startTest("SC003_1-Verify the default value displayed in Temperature field under Add sensors section");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		String TempcountDisplayed = Setup_SensorConfigPage.get_Temperature_text();
		System.out.println(TempcountDisplayed);
		sa.assertEquals(TempcountDisplayed, "0",
				"FAIL: SC003_1 - the default value should be displayed in Temperature field ");
		sa.assertAll();
	}

	// SC004-Verify that max 3 characters are accepted in Temperature field under
	// Add sensors section

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC004", dataProviderClass = setupCreationUtility.class, description = "Verify that max 3 characters are accepted in Temperature field under Add sensors section")

	public void SC004(String TempCount) throws InterruptedException {
		extentTest = extent.startTest(
				"SC004-Verify that max 3 characters are accepted in Temperature field under Add sensors section");

		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(TempCount);
		// More than 3 chars has
		String DisplayedTempcount = Setup_SensorConfigPage.get_Temperature_text();
		sa.assertEquals(DisplayedTempcount.length(), 3,
				"FAIL: Maximum 3 characters should be allowed in Temperature field ");
		sa.assertAll();

	}

	// SC005-Verify the valid inputs accepted in Temperature field under Add Sensors
	// section

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC005", dataProviderClass = setupCreationUtility.class, description = "Verify the valid inputs "
					+ "accepted in Temperature field under Add Sensors section")

	public void SC005(String TempCount) throws InterruptedException {
		extentTest = extent
				.startTest("SC005-Verify the valid inputs accepted in Temperature field under Add Sensors section");

		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(TempCount);
		Thread.sleep(2000);
		sa.assertEquals(Setup_SensorConfigPage.Temperature_Sensor_Titlestate(), true,
				"FAIL: Temperature_Sensor_Title should be visible ");
		sa.assertAll();
	}

	// SC006-Verify the invalid inputs that are not accepted in Temperature field
	// under Add Sensors section

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC006", dataProviderClass = setupCreationUtility.class, description = "Verify the invalid inputs that are not accepted in Temperature field under Add Sensors section")

	public void SC006(String TempCount) throws InterruptedException {
		extentTest = extent.startTest(
				"SC006-Verify the invalid inputs that are not accepted in Temperature field under Add Sensors section");

		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(TempCount);
		Thread.sleep(500);
		String DisplayedTempcount = Setup_SensorConfigPage.get_Temperature_text();
		sa.assertEquals(DisplayedTempcount, "0", "FAIL:Application by default should display 0 for invalid data entry");
		sa.assertAll();
	}

	// SC007-Verify that when more than 300 is entered in Temperature field, the
	// count is rounded off to 300

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC007", dataProviderClass = setupCreationUtility.class, description = "Verify that when more than 300 is entered in Temperature field, "
					+ "the count is rounded off to 300")

	public void SC007(String TempCount) throws InterruptedException {
		extentTest = extent.startTest(
				"SC007-Verify that when more than 300 is entered in Temperature field, the count is rounded off to 300 ");

		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(TempCount);
		Thread.sleep(500);
		String DisplayedTempcount = Setup_SensorConfigPage.get_Temperature_text();
		System.out.println(DisplayedTempcount);
		sa.assertEquals(DisplayedTempcount, "300", "FAIL:Temperature count more than 300 should be rounded off to 300");
		sa.assertAll();
	}

	// SC008-Verify that by clicking on Add button will add the number of sensors
	// on theleft pane forTemperature asmentioned in the field

	@Test(groups = { "Regression" }, description = "Verify that all the sensors added on the left pane "
			+ "will have label names as sensor 1,snesor 2 and so on until they are configured")

	public void SC008() throws InterruptedException {
		extentTest = extent.startTest(
				"SC027-Verify that all the sensors added on the left pane will have label names as sensor 1,snesor 2 and so on until they are configured");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");
		Setup_SensorConfigPage.SensorDetails();
	}

	// SC009-Verify that when 0 is added in the Temperature field it will delete the
	// sensors on the left pane

	@Test(groups = {
			"Regression" }, description = "Verify that sensors pane on the left side will display no sensors and will be blank till no sensor types are defined and selected")

	public void SC009() throws InterruptedException {
		extentTest = extent.startTest(
				"SC002:Verify that sensors pane on the left side will display no sensors and will be blank till no sensor types are defined and selected");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("4");
		sa.assertEquals(Setup_SensorConfigPage.Temperature_Sensor_Titlestate(), true,
				"FAIL: Temperature_Sensor_Titlestate should be visible ");
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("0");
		Setup_SensorConfigPage.Click_GroupSensors_btn();
		String actalert = tu.get_AlertMsg_text();
		String expalert = "Please configure atleast one sensor to proceed.";
		sa.assertEquals(actalert, expalert,
				"FAIL: Application should display the alert message as there is no sensors added ");
		sa.assertAll();
	}

	// SC010 - Verify that a validation message is displayed when more number of
	// sensors are added in the Temperature field than those defined in define setup
	// screen

	@Test(groups = {
			"Regression" }, description = "Verify that a validation message is displayed when more number of sensors are added in the Temperature field than those defined in define setup screen")

	public void SC010() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"SC010: Verify that a validation message is displayed when more number of sensors are added in the Temperature field than those defined in define setup screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("300");
		String Actalert = tu.get_AlertMsg_text();
		String Expectalert = "You are trying to add more number of sensors than defined";
		sa.assertEquals(Actalert, Expectalert, "FAIL:alert message not matched");
		sa.assertAll();
	}

	// SC011-Verify the default value displayed in Humidity field under Add sensors
	// section

	@Test(groups = {
			"Regression" }, description = "Verify the default value displayed in Humidity field under Add sensors section")

	public void SC011() throws InterruptedException {
		extentTest = extent
				.startTest("SC11-Verify the default value displayed in Humidity field under Add sensors section");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		String HmdcountDisplayed = Setup_SensorConfigPage.get_Humidity_text();
		System.out.println(HmdcountDisplayed);
		sa.assertEquals(HmdcountDisplayed, "0",
				"FAIL: SC11 - the default value should be displayed in Humididty field ");
		sa.assertAll();
	}

	// SC012-Verify that max 3 characters are accepted in Humidity field under Add
	// sensors section

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC012", dataProviderClass = setupCreationUtility.class, description = "Verify that max 3 characters are accepted in Humidity field under Add sensors section")

	public void SC012(String HmdCount) throws InterruptedException {
		extentTest = extent.startTest(
				"SC012-Verify that max 3 characters are accepted in Humidity field under Add sensors section");

		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_HumidityCount_textField(HmdCount);

		String EnteredTempcount = Setup_SensorConfigPage.get_Humidity_text();
		String DisplayedTempcount = Setup_SensorConfigPage.get_Humidity_text();
		sa.assertEquals(EnteredTempcount, DisplayedTempcount,
				"FAIL: Maximum 3 characters should be allowed in Humidity field ");
		sa.assertAll();

	}

	// SC013-Verify the valid inputs accepted in Humidity field under Add Sensors
	// section

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC013", dataProviderClass = setupCreationUtility.class, description = "Verify the valid inputs accepted in Humidity field under Add Sensors section")

	public void SC013(String HmdCount) throws InterruptedException {
		extentTest = extent
				.startTest("SC013-Verify the valid inputs accepted in Humidity field under Add Sensors section");

		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_HumidityCount_textField(HmdCount);
		Thread.sleep(2000);
		sa.assertEquals(Setup_SensorConfigPage.Humidity_Sensor_Titlestate(), true,
				"FAIL: Humidity_Sensor_Title should be visible ");
		sa.assertAll();
	}

	// SC014- Verify the invalid inputs that are not accepted in Humidity field
	// under Add Sensors section

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC014", dataProviderClass = setupCreationUtility.class, description = "Verify the invalid inputs that are not accepted in Humidity field under Add Sensors section")

	public void SC014(String HmdCount) throws InterruptedException {
		extentTest = extent.startTest(
				"SC014-Verify the invalid inputs that are not accepted in Humidity field under Add Sensors section");

		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_HumidityCount_textField(HmdCount);
		Thread.sleep(500);
		String DisplayedHmdcount = Setup_SensorConfigPage.get_Humidity_text();
		sa.assertEquals(DisplayedHmdcount, "0", "FAIL:Application by default should display 0 for invalid data entry");
		sa.assertAll();
	}

	// SC015-Verify that when more than 300 is entered in Humidity field, the
	// count is rounded off to 300

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC015", dataProviderClass = setupCreationUtility.class, description = "Verify that when more than 300 is entered in Humidity field, the count is rounded off to 300")

	public void SC015(String HmdCount) throws InterruptedException {
		extentTest = extent.startTest(
				"SC015-Verify that when more than 300 is entered in Humidity field, the count is rounded off to 300 ");

		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_HumidityCount_textField(HmdCount);
		Thread.sleep(500);
		String DisplayedHmdtextboxcount = Setup_SensorConfigPage.get_Humidity_text();
		System.out.println(DisplayedHmdtextboxcount);
		sa.assertEquals(DisplayedHmdtextboxcount, "300",
				"FAIL:Humidity count more than 300 should be rounded off to 300");
		sa.assertAll();
	}

	// SC016-Verify that by clicking on Add button will add the number of sensors
	// on the left pane for Humidity as mentioned in the field

	@Test(groups = { "Regression" }, description = "Verify that sensors pane on the left side will "
			+ "display no sensors and will be blank till no sensor types are defined and selected")

	public void SC016() throws InterruptedException {
		extentTest = extent.startTest("SC016:Verify that sensors pane on the left side will display "
				+ "no sensors and will be blank till no sensor types are defined and selected");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_HumidityCount_textField("4");
		String Enterhmdcount = Setup_SensorConfigPage.get_Humidity_text();
		System.out.println("Ented hmdcount " + Enterhmdcount);
		// Thread.sleep(500);
		// String[] Displayedsensorcnt = Setup_SensorConfigPage.SensorCount();
		sa.assertEquals(Enterhmdcount, "4", "FAIL:count should be same");
		sa.assertAll();

	}

	// SC017-Verify that when 0 is added in the Humidity field it will delete the
	// sensors on the left pane

	@Test(groups = { "Regression" }, description = "Verify that sensors pane on the left side will "
			+ "display no sensors and will be blank till no sensor types are defined and selected")

	public void SC017() throws InterruptedException {
		extentTest = extent.startTest(
				"SC017:Verify that sensors pane on the left side will display no sensors and will be blank till no sensor types are defined and selected");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_HumidityCount_textField("4");
		sa.assertEquals(Setup_SensorConfigPage.Humidity_Sensor_Titlestate(), true,
				"FAIL: Temperature_Sensor_Titlestate should be visible ");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("0");
		Setup_SensorConfigPage.Click_GroupSensors_btn();
		String actalert = tu.get_AlertMsg_text();
		String expalert = "Please configure atleast one sensor to proceed.";
		sa.assertEquals(actalert, expalert,
				"FAIL: Application should display the alert message as there is no sensors added ");
		sa.assertAll();
	}

	// SC018-Verify that a validation message is displayed when more number of
	// sensors are added in
	// the Humidity field than those defined in define setup screen

	@Test(groups = { "Regression" }, description = "Verify that a validation message is displayed when "
			+ "more number of sensors are added in the Humidity field than those defined in define setup screen")

	public void SC018() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("SC018-Verify that a validation message is displayed when more number of sensors are added "
						+ "in the Humidity field than those defined in define setup screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_HumidityCount_textField("300");
		String Actalert = tu.get_AlertMsg_text();
		String Expectalert = "You are trying to add more number of sensors than defined";
		sa.assertEquals(Actalert, Expectalert, "FAIL:alert message not matched");
		sa.assertAll();
	}

	// SC019-Verify the default value displayed in Pressure field under Add sensors
	// section

	@Test(groups = {
			"Regression" }, description = "Verify the default value displayed in Pressure field under Add sensors section")

	public void SC019() throws InterruptedException {
		extentTest = extent
				.startTest("SC019-Verify the default value displayed in Pressure field under Add sensors section");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		String PrescountDisplayed = Setup_SensorConfigPage.get_Pressure_text();
		System.out.println(PrescountDisplayed);
		sa.assertEquals(PrescountDisplayed, "0",
				"FAIL: SC019 - the default value should be displayed in pressure field ");
		sa.assertAll();
	}

	// SC020-Verify that max 3 characters are accepted in Pressure field underAdd
	// sensors section

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC020", dataProviderClass = setupCreationUtility.class, description = "Verify that max 3 characters are accepted in Pressure field under Add sensors section")

	public void SC020(String PrsrCount) throws InterruptedException {
		extentTest = extent.startTest(
				"SC020-Verify that max 3 characters are accepted in Pressure field under Add sensors section");

		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField(PrsrCount);

		String EnteredPresrcount = Setup_SensorConfigPage.get_Pressure_text();
		Thread.sleep(500);
		String DisplayedPresrcount = Setup_SensorConfigPage.get_Pressure_text();
		sa.assertEquals(EnteredPresrcount, DisplayedPresrcount,
				"FAIL: Maximum 3 characters should be allowed in pressure field ");
		sa.assertAll();

	}

	// SC021-Verify the valid inputs accepted in Pressure field under Add Sensors
	// section

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC021", dataProviderClass = setupCreationUtility.class, description = "Verify the valid inputs accepted in Pressure field under Add Sensors section")

	public void SC021(String PrsrCount) throws InterruptedException {
		extentTest = extent
				.startTest("SC021-Verify the valid inputs accepted in Pressure field under Add Sensors section");

		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField(PrsrCount);
		Thread.sleep(500);
		sa.assertEquals(Setup_SensorConfigPage.Pressure_Sensor_Titlestate(), true,
				"FAIL:Pressure_Sensor_Titlestate should be visible ");
		sa.assertAll();
	}

	// SC022-Verify the invalid inputs that are not accepted in Pressure field under
	// Add Sensors section

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC022", dataProviderClass = setupCreationUtility.class, description = "Verify the invalid inputs that are not accepted in Humidity field under Add Sensors section")

	public void SC022(String PrsrCount) throws InterruptedException {
		extentTest = extent.startTest(
				"SC022-Verify the invalid inputs that are not accepted in Humidity field under Add Sensors section");

		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField(PrsrCount);
		Thread.sleep(500);
		String DisplayedPrsrcount = Setup_SensorConfigPage.get_Pressure_text();
		sa.assertEquals(DisplayedPrsrcount, "0", "FAIL:Application by default should display 0 for invalid data entry");
		sa.assertAll();
	}

	// SC023-Verify that when more than 300 is entered in Pressure field, the count
	// is rounded off to 300

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC023", dataProviderClass = setupCreationUtility.class, description = "Verify that when more than 300 is entered in Pressure field, the count is rounded off to 300")

	public void SC023(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest(
				"SC023-Verify that when more than 300 is entered in Pressure field, the count is rounded off to 300");

		SoftAssert sa = new SoftAssert();
		String PrsrCount = (String) dataProvider[0];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField(PrsrCount);
		Thread.sleep(500);
		String DisplayedPresrtextboxcount = Setup_SensorConfigPage.get_Pressure_text();
		System.out.println(DisplayedPresrtextboxcount);
		sa.assertEquals(DisplayedPresrtextboxcount, "300",
				"FAIL: Pressure count more than 300 should be rounded off to 300");
		sa.assertAll();
	}

	// SC024-Verify that by clicking on Add button will add the number of sensors on
	// the
	// left pane for Pressure as mentioned in the field

	@Test(groups = {
			"Regression" }, description = "Verify that by clicking on Add button will add the number of sensors on "
					+ "the left pane for Pressure as mentioned in the field")

	public void SC024() throws InterruptedException {
		extentTest = extent.startTest("'SC024-Verify that by clicking on Add button will "
				+ "add the number of sensors on the left pane for Pressure as mentioned in the field");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField("4");
		String Enterprsrsencount = Setup_SensorConfigPage.get_Pressure_text();
		System.out.println("Ented TPressureCount " + Enterprsrsencount);
		// String[] Displayedsensorcnt = Setup_SensorConfigPage.SensorCount();
		sa.assertEquals(Enterprsrsencount, "4", "FAIL:count should be same");
		sa.assertAll();

	}

	// SC025-Verify that when 0 is added in the Pressure field it will delete the
	// sensors on the left pane

	@Test(groups = {
			"Regression" }, description = "Verify that when 0 is added in the Pressure field it will delete the sensors on the left pane")

	public void SC025() throws InterruptedException {
		extentTest = extent.startTest(
				"SC025-Verify that when 0 is added in the Pressure field it will delete the sensors on the left pane");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");
		sa.assertEquals(Setup_SensorConfigPage.Pressure_Sensor_Titlestate(), true,
				"FAIL: Temperature_Sensor_Titlestate should be visible ");
		Setup_SensorConfigPage.Enter_PressureCount_textField("0");
		Setup_SensorConfigPage.Click_GroupSensors_btn();
		String actalert = tu.get_AlertMsg_text();
		String expalert = "Please configure atleast one sensor to proceed.";
		sa.assertEquals(actalert, expalert,
				"FAIL: Application should display the alert message as there is no sensors added ");
		sa.assertAll();
	}

	// SC026-Verify that a validation message is displayed when more number of
	// sensors are added in the Pressure field than those defined
	// in define setup screen

	@Test(groups = { "Regression" }, description = "Verify that a validation message is displayed when "
			+ "more number of sensors are added in the Pressure field than those defined in define setup screen")

	public void SC026() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("SC026-Verify that a validation message is displayed when more number of sensors are "
						+ "added in the Pressure field than those defined in define setup screen");
		SoftAssert sa = new SoftAssert(); //

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField("300");
		String Actalert = tu.get_AlertMsg_text();
		String Expectalert = "You are trying to add more number of sensors than defined";
		sa.assertEquals(Actalert, Expectalert, "FAIL:alert message not matched");
		sa.assertAll();
	}

	// SC027

	@Test(groups = {
			"Regression" }, description = "Verify that all the sensors added on the left pane will have label names as sensor 1,snesor 2 and so on until they are configured")
	public void SC027() throws InterruptedException {
		extentTest = extent.startTest(
				"SC027-Verify that all the sensors added on the left pane will have label names as sensor 1,snesor 2 and so on until they are configured");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");
		Setup_SensorConfigPage.SensorDetails();
	}

	// SC028-Verify if Vertical scroll bar will be displayed when the number of rows
	// exceeds the display view

	@Test(groups = {
			"Regression" }, description = "Verify if Vertical scroll bar will be displayed when the number of rows exceeds the display view")

	public void SC028() throws InterruptedException {
		extentTest = extent.startTest(
				"SC028-Verify if Vertical scroll bar will be displayed when the number of rows exceeds the display view");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("299");

		sa.assertEquals(Setup_SensorConfigPage.IsDisplayingVerticalScrollBar(), true,
				"FAIL:Vertical scroll bar will be displayed as the number of rows exceeds the display view");
		sa.assertAll();
	}

	// SC029-Verify that on-click of the Up arrow icon for Add sensors section
	// will collapse the section

	@Test(groups = {
			"Regression" }, description = "Verify that on-click of the Up arrow icon for Add sensors section will collapse the section")

	public void SC029() throws InterruptedException {
		extentTest = extent.startTest(
				"SC029-Verify that on-click of the Up arrow icon for Add sensors section will collapse the section");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		sa.assertEquals(Setup_SensorConfigPage.Temperature_Field_state(), true,
				"FAIL: Temperature_Field should be visible ");
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();

	}

	// SC030-Verify that by clicking on the filters on the left pane for
	// temp,pressure and humidity, the respective sensors are displayed

	@Test(groups = {
			"Regression" }, description = "Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed")

	public void SC030() throws InterruptedException {
		extentTest = extent.startTest(
				"SC030-Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("2");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("2");
		Setup_SensorConfigPage.Enter_PressureCount_textField("2");
		sa.assertEquals(Setup_SensorConfigPage.Temperature_filters(), true,
				"FAIL: Temperature_filters should be filtered only emperature sensors ");
		sa.assertEquals(Setup_SensorConfigPage.Humidity_filters(), true,
				"FAIL: Humidity_filters should be filtered only Humidity sensors ");
		sa.assertEquals(Setup_SensorConfigPage.Pressure_filters(), true,
				"FAIL: Pressure_filters should be filtered only pressure sensors ");
		sa.assertAll();
	}

	// SC031-Verify that on-click of the Down arrow icon for Configure Sensors
	// section expands the section

	@Test(groups = {
			"Regression" }, description = "SC031-Verify that on-click of the Down arrow icon for Configure Sensors section expands the section")
	public void SC031() throws InterruptedException {
		extentTest = extent.startTest(
				"SC031-Verify that on-click of the Down arrow icon for Configure Sensors section expands the section");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		sa.assertEquals(Setup_SensorConfigPage.IsSensortypevisible(), true, "FAIL: Sensortype should be visible");
		sa.assertAll();
	}

	// SC032-Verify the details displayed under Configure sensors section

	@Test(groups = { "Regression" }, description = "SC032-Verify the details displayed under Configure sensors section")
	public void SC032() throws InterruptedException {
		extentTest = extent.startTest("SC032-Verify the details displayed under Configure sensors section");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		sa.assertEquals(Setup_SensorConfigPage.IsSensortypevisible(), true, "FAIL: Sensortype should be visible");
		sa.assertEquals(Setup_SensorConfigPage.IsFromfieldvisible(), true, "FAIL: Sensortype should be visible");
		sa.assertEquals(Setup_SensorConfigPage.IsTofieldvisible(), true, "FAIL: Sensortype should be visible");
		sa.assertEquals(Setup_SensorConfigPage.IsSensorLabelvisible(), true, "FAIL: Sensortype should be visible");

		sa.assertEquals(Setup_SensorConfigPage.IsAssign_Button_visible(), true, "FAIL: Sensortype should be visible");
		sa.assertEquals(Setup_SensorConfigPage.IsDescription_Button_visible(), true,
				"FAIL: Sensortype should be visible");
		sa.assertAll();
	}

	// SC033-Verify that after sensors are added,only the sensor type drop down
	// will be enabled and // all other fields will be disabled under Configure
	// sensors section on the right pane

	@Test(groups = {
			"Regression" }, description = "Verify that after sensors are added,only the sensor type drop down will be enabled and all other fields will be disabled under Configure sensors section on the right pane")

	public void SC033() throws InterruptedException {
		extentTest = extent.startTest(
				"SC033-Verify that after sensors are added,only the sensor type drop down will be enabled and all other fields will be disabled under Configure sensors section on the right pane");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		sa.assertEquals(Setup_SensorConfigPage.IsSensortype_Enable(), true, "FAIL: Sensortype should be Enable");
		sa.assertEquals(Setup_SensorConfigPage.IsFromfield_Enable(), false, "FAIL: Sensortype should not be Enable");
		sa.assertEquals(Setup_SensorConfigPage.IsTofield_Enable(), false, "FAIL: Sensortype should not be Enable");
		sa.assertEquals(Setup_SensorConfigPage.IsSensorLabel_Enable(), false, "FAIL: Sensortype should not be Enable");
		sa.assertEquals(Setup_SensorConfigPage.IsAssign_Button_Enable(), false,
				"FAIL: Sensortype should not be Enable");
		sa.assertEquals(Setup_SensorConfigPage.IsDescription_Button_Enable(), false,
				"FAIL: Sensortype should not be Enable");
		sa.assertAll();
	}

	// SC034-Verify that when clicked on the Sensors on the left pane, will enable
	// the Sensor type field and sensor label text boxes and disabled the
	// From and To fields.

	@Test(groups = {
			"Regression" }, description = "Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed")
	public void SC034() throws InterruptedException {
		extentTest = extent.startTest(
				"SC034-Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("2");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("2");
		Setup_SensorConfigPage.Enter_PressureCount_textField("2");

		Setup_SensorConfigPage.Clickon_Temperature();
		Setup_SensorConfigPage.Click_Temp_sensor();
		sa.assertEquals(Setup_SensorConfigPage.IsSensortype_Enable(), true, "FAIL: Sensortype should be Enable");
		sa.assertEquals(Setup_SensorConfigPage.IsFromfield_Enable(), false, "FAIL: Sensortype should not be Enable");
		sa.assertEquals(Setup_SensorConfigPage.IsTofield_Enable(), false, "FAIL: Sensortype should not be Enable");
		sa.assertAll();

	}

	// SC035 - MANUALLY

	// SC036-Verify that user can do multiple selections of sensors of same type

	@Test(groups = {
			"Regression" }, description = "SC036-Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed")
	public void SC036() throws InterruptedException {
		extentTest = extent.startTest(
				"SC036-Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("4");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("4");
		Setup_SensorConfigPage.Enter_PressureCount_textField("4");

		Setup_SensorConfigPage.Clickon_Temperature();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor_Multi();

		Setup_SensorConfigPage.Clickon_Humidity();
		Setup_SensorConfigPage.Click_Hmd_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor_Multi();

		Setup_SensorConfigPage.Click_Pressure();
		Setup_SensorConfigPage.Click_Prsr_sensor();
		Setup_SensorConfigPage.Click_Psr_sensor_Multi();
	}

	// SC037-Verify that when clicked sensors from different sensor types on the
	// left pane will display that sensor type in the sensor type drop down in
	// Configure sensors section

	@Test(groups = {
			"Regression" }, description = "SC037 - Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed")

	public void SC037() throws InterruptedException {
		extentTest = extent.startTest(
				"SC037-Verify that when clicked sensors from different sensor types on the left pane will display that sensor type in the sensor type drop down in Configure sensors section");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("2");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("2");
		Setup_SensorConfigPage.Enter_PressureCount_textField("2");

		Setup_SensorConfigPage.Clickon_Temperature();
		Setup_SensorConfigPage.Click_Temp_sensor();
		String ExpTempsenrtxt = Setup_SensorConfigPage.get_Sensortype_text();
		System.out.println(ExpTempsenrtxt);
		String ActTempsenrtxt = "Temperature     (°C)";
		sa.assertEquals(ActTempsenrtxt, ExpTempsenrtxt,
				"FAIL:Temperature(°C) should be displayed in Sensor type field");

		Setup_SensorConfigPage.Clickon_Humidity();
		Setup_SensorConfigPage.Click_Hmd_sensor();
		String ExpHmdsenrtxt = Setup_SensorConfigPage.get_Sensortype_text();
		System.out.println(ExpHmdsenrtxt);
		String ActHmdsenrtxt = "Humidity     (%RH)";
		sa.assertEquals(ActTempsenrtxt, ExpTempsenrtxt, "FAIL: Humidity(%RH) should be displayed in Sensor type field");
		Setup_SensorConfigPage.Click_Pressure();
		Setup_SensorConfigPage.Click_Prsr_sensor();
		String ExpPrsrsenrtxt = Setup_SensorConfigPage.get_Sensortype_text();
		System.out.println(ExpPrsrsenrtxt);
		String ActPrsrsenrtxt = "Pressure      (Bar)";
		sa.assertEquals(ActPrsrsenrtxt, ExpPrsrsenrtxt,
				"FAIL: Pressures(Bar) should be displayed in Sensor type field");
		sa.assertAll();
	}

	// SC038-Verify that the sensor type drop down displays Temperature only when
	// temperature sensors are added

	@Test(groups = {
			"Regression" }, description = "SC038-Verify that the sensor type drop down displays Temperature only when temperature sensors are added")
	public void SC038() throws InterruptedException {
		extentTest = extent.startTest(
				"SC038-Verify that the sensor type drop down displays Temperature only when temperature sensors are added");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("2");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		sa.assertEquals(Setup_SensorConfigPage.IsTemperature_visible_Drpdwnlist(), true,
				"FAIL: Temperature should be displayed in Sensor type drop down list");
		sa.assertAll();
	}

	// SC039-Verify that the sensor type drop down displays Pressure only when
	// pressure sensors are added

	@Test(groups = {
			"Regression" }, description = "Verify that the sensor type drop down displays Pressure only when pressure sensors are added")

	public void SC039() throws InterruptedException {
		extentTest = extent.startTest(
				"SC039-Verify that the sensor type drop down displays Pressure only when pressure sensors are added");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField("2");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		sa.assertEquals(Setup_SensorConfigPage.IsPressure_visible_Drpdwnlist(), true,
				"FAIL: Pressure should be displayed in Sensor type drop down list");
		sa.assertAll();
	}

	// SC040-Verify that the sensor type drop down displays Humidity only when
	// humidity sensors are added

	@Test(groups = {
			"Regression" }, description = "SC040-Verify that the sensor type drop down displays Humidity only when humidity sensors are added")
	public void SC040() throws InterruptedException {
		extentTest = extent.startTest(
				"SC040-Verify that the sensor type drop down displays Humidity only when humidity sensors are added");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();

		Setup_SensorConfigPage.Enter_HumidityCount_textField("2");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();

		sa.assertEquals(Setup_SensorConfigPage.IsHumidity_visible_Drpdwnlist(), true,
				"FAIL: Humidity should be displayed in Sensor type drop down list");
		sa.assertAll();
	}

	// SC041-Verify that the max char length for From field is 1 when the no. of
	// sensors added are in single digit

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC041", dataProviderClass = setupCreationUtility.class, description = "Verify that the max char length for From field is 1 when the no. of sensors added are in single digit")

	public void SC041(String sesrno) throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC041-Verify that the max char length for From field is 1 when the no. of sensors added are in single digit");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();

		String Dis_FromTextBox = Setup_SensorConfigPage.get_Fromfield_text();
		System.out.println(Dis_FromTextBox.length());

		sa.assertEquals(Dis_FromTextBox.length(), 1, "FAIL: max char length for From field should be 1");
		sa.assertAll();
	}

	// SC042-Verify that the max char length for From field is 2 when the no. of
	// sensors added are in double digits

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC042", dataProviderClass = setupCreationUtility.class, description = "Verify that the max char length for From field is 2 when the no. of sensors added are in double digits")

	public void SC042(String sesrno, String Fromcnt) throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC042-Verify that the max char length for From field is 2 when the no. of sensors added are in double digits");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
		Thread.sleep(500);
		String Dis_FromTextBox = Setup_SensorConfigPage.get_Fromfield_text();
		System.out.println(Dis_FromTextBox.length());

		sa.assertEquals(Dis_FromTextBox.length(), 2, "FAIL:  max char length for From field should be 2");
		sa.assertAll();
	}

	// SC043-Verify that the max char length for From field is 3 when the no. of
	// sensors added are in triple digits

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC043", dataProviderClass = setupCreationUtility.class, description = "Verify that the max char length for From field is 2 when the no. of sensors added are in double digits")

	public void SC043(String sesrno, String Fromcnt) throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC043-Verify that the max char length for From field is 3 when the no. of sensors added are in triple digits");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
		Thread.sleep(500);
		String Dis_FromTextBox = Setup_SensorConfigPage.get_Fromfield_text();
		System.out.println(Dis_FromTextBox.length());

		sa.assertEquals(Dis_FromTextBox.length(), 3, "FAIL:  max char length for From field should be 3");
		sa.assertAll();
	}

	// SC044-Verify the default value displayed in From field

	@Test(groups = { "Regression" }, description = "Verify the default value displayed in From field")
	public void SC044() throws InterruptedException {
		extentTest = extent.startTest("SC044-Verify the default value displayed in From field");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();

		Setup_SensorConfigPage.Enter_TemperatureCount_textField("2");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		String Dis_FromTextBox = Setup_SensorConfigPage.get_Fromfield_text();
		// System.out.println(Dis_FromTextBox);

		sa.assertEquals(Dis_FromTextBox, "1", "FAIL: 1 should be displayed by default");
		sa.assertAll();
	}

	// SC046-Verify the invalid inputs in From field

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC046", dataProviderClass = setupCreationUtility.class, description = "Verify the invalid inputs in From field")

	public void SC046(String sesrno, String Fromcnt) throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC046-Verify the invalid inputs in From field");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
		Thread.sleep(2000);
		String Dis_FromTextBox = Setup_SensorConfigPage.get_Fromfield_text();
		System.out.println(Dis_FromTextBox);

		sa.assertEquals(Dis_FromTextBox, "", "FAIL:  It should not accept invalid values");
		sa.assertAll();
	}

	// SC046a-Verify the invalid inputs in From field

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC046a", dataProviderClass = setupCreationUtility.class, description = "Verify the invalid inputs in From field")

	public void SC046a(String sesrno, String Fromcnt) throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC046a-Verify the invalid inputs in From field");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
		Thread.sleep(500);
		String Dis_FromTextBox = Setup_SensorConfigPage.get_Fromfield_text();
		System.out.println(Dis_FromTextBox);

		sa.assertEquals(Dis_FromTextBox, "5", "FAIL: It should not accept more than sensor values");
		sa.assertAll();
	}

	// SC047-Verify that when a greater value is entered in From field than the
	// To field the Assign button is disabled in sensor configuration screen

	@Test(description = "Verify that when a greater value is entered in From field than the To field the Assign button is disabled in sensor configuration screen")

	public void SC047() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC047-Verify that when a greater value is entered in From field than the To field the Assign button is disabled in sensor configuration screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_Num_From("10");
		Setup_SensorConfigPage.Enter_Num_To("2");
		Thread.sleep(500);
		sa.assertEquals(Setup_SensorConfigPage.IsAssignButton_Enable(), false,
				"FAIL: The Assign button should be disabled in snensor configuration screen");
		sa.assertAll();
	}

	// SC048-Verify that the max char length for To field is 1 when the no. of
	// sensors added are in single digit

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC048", dataProviderClass = setupCreationUtility.class, description = "Verify that the max char length for To field is 1 when the no. of sensors added are in single digit")

	public void SC048(String sesrno, String Fromcnt, String Tocnt) throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC048-Verify that the max char length for To field is 1 when the no. of sensors added are in single digit");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
		Setup_SensorConfigPage.Enter_Num_To(Tocnt);

		String Dis_ToTextBox = Setup_SensorConfigPage.get_Tofield_text();
		System.out.println(Dis_ToTextBox.length());

		sa.assertEquals(Dis_ToTextBox.length(), 1, "FAIL: max char length for To field should be 1");
		sa.assertAll();
	}

	// SC049-Verify that the max char length for To field is 2 when the no. of
	// sensors added are in double digits

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC049", dataProviderClass = setupCreationUtility.class, description = "Verify that the max char length for To field is 2 when the no. of sensors added are in double digits")

	public void SC049(String sesrno, String Fromcnt, String Tocnt) throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC049-Verify that the max char length for To field is 2 when the no. of sensors added are in double digits");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
		Setup_SensorConfigPage.Enter_Num_To(Tocnt);

		String Dis_ToTextBox = Setup_SensorConfigPage.get_Tofield_text();
		System.out.println(Dis_ToTextBox.length());

		sa.assertEquals(Dis_ToTextBox.length(), 2, "FAIL: max char length for To field should be 2");
		sa.assertAll();
	}

	// SC050-Verify that the max char length for To field is 3 when the no. of
	// sensors added are in triple digits

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC050", dataProviderClass = setupCreationUtility.class, description = "SC050-Verify that the max char length for To field is 3 when the no. of sensors added are in triple digits")

	public void SC050(String sesrno, String Fromcnt, String Tocnt) throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC050-Verify that the max char length for To field is 3 when the no. of sensors added are in triple digits");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
		Setup_SensorConfigPage.Enter_Num_To(Tocnt);

		Thread.sleep(500);
		String Dis_FromTextBox = Setup_SensorConfigPage.get_Tofield_text();
		// System.out.println(Dis_FromTextBox.length());

		sa.assertEquals(Dis_FromTextBox.length(), 3, "FAIL:  max char length for To field should be 3");
		sa.assertAll();
	}

	// SC051-Verify the default value displayed in To field

	@Test(groups = { "Regression" }, description = "Verify the default value displayed in To field")
	public void SC051() throws InterruptedException {
		extentTest = extent.startTest("SC044-Verify the default value displayed in To field");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();

		Setup_SensorConfigPage.Enter_TemperatureCount_textField("2");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		String Dis_ToTextBox = Setup_SensorConfigPage.get_Tofield_text();
		// System.out.println(Dis_ToTextBox);

		sa.assertEquals(Dis_ToTextBox, "1", "FAIL: 1 should be displayed by default");
		sa.assertAll();
	}

	// SC052-Verify the valid inputs in To field

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC052", dataProviderClass = setupCreationUtility.class, description = "SC052-Verify the valid inputs in To field")

	public void SC052(String sesrno, String Fromcnt, String Tocnt) throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC052-Verify the valid inputs in To field");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
		Setup_SensorConfigPage.Enter_Num_To(Tocnt);

		sa.assertEquals(Setup_SensorConfigPage.IsAssignButton_Enable(), true,
				"FAIL: The Assign button should be Enable in snensor configuration screen");
		sa.assertAll();
	}

	// SC053-Verify the invalid inputs in To field

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC053", dataProviderClass = setupCreationUtility.class, description = "Verify the invalid inputs in To field")

	public void SC053(String sesrno, String Fromcnt, String Tocnt) throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC053-Verify the invalid inputs in To field");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
		Setup_SensorConfigPage.Enter_Num_To(Tocnt);

		String Dis_ToTextBox = Setup_SensorConfigPage.get_Tofield_text();
		System.out.println(Dis_ToTextBox);

		sa.assertEquals(Dis_ToTextBox, "", "FAIL:  It should not accept invalid values");
		sa.assertAll();
	}

	// SC054-Verify that when clicked on the Sensors on the left pane, the first
	// text box of sensor lable field will display the label name of that sensor-
	// sensor 1,sensor2, etc as per selection

	@Test(groups = { "Regression" }, description = "Verify the invalid inputs in To field")

	public void SC054() throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC053-Verify the invalid inputs in To field");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("12");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();

		String ActualsnsrlblText = Setup_SensorConfigPage.get_SensorLabel_text();
		String ExptsnsrlblText = "Sensor 1";
		sa.assertEquals(ActualsnsrlblText, ExptsnsrlblText, "FAIL:  It should be sensor1");

		Setup_SensorConfigPage.Click_Temp_sensor2();

		String ActualsnsrlblText1 = Setup_SensorConfigPage.get_SensorLabel_text();
		String ExptsnsrlblText1 = "Sensor 2";

		sa.assertEquals(ActualsnsrlblText1, ExptsnsrlblText1, "FAIL:  It should be sensor2");

		sa.assertAll();
	}

	// SC055-Verify that when selected the sensor type from the drop down, the
	// first text box of sensor lable field will display blank

	@Test(groups = { "Regression" }, description = "Verify that when selected the sensor type from the drop down, "
			+ "the first text box of sensor lable field will display blank")

	public void SC055() throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC055-Verify that when selected the sensor type from "
				+ "the drop down, the first text box of sensor lable field will display blank");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.Click_Sensortype_field();
		String actsensttxt = Setup_SensorConfigPage.get_Sensortype_text();
		String ExptsensText = "";
		// System.out.println(actsensttxt);
		sa.assertEquals(actsensttxt, ExptsensText, "FAIL:  It should be blank");

		sa.assertAll();

	}

	// SC056-Verify that max 8 characters are allowed in Sensor Label-first text box

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC056", dataProviderClass = setupCreationUtility.class, description = "Verify that max 8 characters are allowed in Sensor Label-first text box")

	public void SC056(String sesrno, String sensrlbl) throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC056-Verify that max 8 characters are allowed in Sensor Label-first text box");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_SensorLabel(sensrlbl);

		String ActualsnsrlblText = Setup_SensorConfigPage.get_SensorLabel_text();
		sa.assertEquals(ActualsnsrlblText.length(), 8,
				"FAIL: max 8 characters are allowed in Sensor Label-first text box");

		sa.assertAll();

	}

	// SC057-Verify that when selected the sensor type from the drop down, the
	// sensor label- first text box will be blank and enabled

	@Test(groups = {
			"Regression" }, description = "Verify that when selected the sensor type from the drop down, the sensor label-first text box will be blank and enabled")

	public void SC057() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC057-Verify that when selected the sensor type from the drop down, the sensor label-first text box will be blank and enabled");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		String ActualsnsrlblText = Setup_SensorConfigPage.get_SensorLabel_text();

		sa.assertEquals(ActualsnsrlblText, "", "FAIL:the sensor label-first text box will be blank");
		sa.assertEquals(Setup_SensorConfigPage.IsSensorLabel_Enable(), true,
				"FAIL:the sensor label-first text box will be Enable");

		sa.assertAll();

	}

	// SC058-Verify the valid inputs for Sensor Label-First text box

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC058", dataProviderClass = setupCreationUtility.class, description = "Verify the valid inputs for Sensor Label-First text box")

	public void SC058(String sesrno, String sensrlbl) throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC058-Verify the valid inputs for Sensor Label-First text box");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_SensorLabel(sensrlbl);

		sa.assertEquals(Setup_SensorConfigPage.IsAssignButton_Enable(), true,
				"FAIL:Assign button should be enable for valid data ");

		sa.assertAll();
	}

	// SC059-Verify the invalid inputs for Sensor Label-First text box

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC059", dataProviderClass = setupCreationUtility.class, description = "Verify the valid inputs for Sensor Label-First text box")

	public void SC059(String sesrno, String sensrlbl) throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC059-Verify the valid inputs for Sensor Label-First text box");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_SensorLabel(sensrlbl);

		sa.assertEquals(Setup_SensorConfigPage.IsAssignButton_Enable(), false,
				"FAIL:Assign button should be disable forInvalid data ");

		sa.assertAll();
	}

	// SC060-Verify that when clicked on the Sensor on the left pane, the second
	// text box of sensor lable field will by default display 01

	@Test(groups = { "Regression" }, description = "SC060-Verify that when clicked on the Sensors "
			+ "on the left pane, the second text box of sensor lable field will by default display 01")

	public void SC056() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC060--Verify that when clicked on the Sensors on the left pane, the second text box of sensor label field will by default display 01");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Click_Temp_sensor();
		sa.assertEquals(Setup_SensorConfigPage.get_SensorLabel_Num_text(), "01",
				"FAIL: the second text box of sensor label field will by default display 01 ");

		sa.assertAll();
	}

	// SC061-Verify that when selected the sensor type from the drop down, the
	// second text box of sensor label field will by default display as 01

	@Test(groups = {
			"Regression" }, description = "Verify that when selected the sensor type from the drop down, the second text box of sensor label field will by default display as 01")

	public void SC061() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC061-SC061-Verify that when selected the sensor type from the drop down, the second text box of sensor label field will by default display as 01");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();

		sa.assertEquals(Setup_SensorConfigPage.get_SensorLabel_Num_text(), "01",
				"FAIL:the sensor label-first text box will be Enable");

		sa.assertAll();
	}

	// SC062-Verify that max 3 characters are allowed in Sensor Label-Second text
	// box

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC062", dataProviderClass = setupCreationUtility.class, description = "Verify that max 2 characters are allowed in Sensor Label-Second text box")

	public void SC062(String sesrno, String sensrlblno) throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC062-Verify that max 2 characters are allowed in Sensor Label-Second text box");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter__Num_SensorLabel(sensrlblno);
		String snlen = Setup_SensorConfigPage.get_SensorLabel_Num_text();

		sa.assertEquals(snlen.length(), 2, "FAIL: max 2 characters are allowed in Sensor Label-Second text box");

		sa.assertAll();
	}

	// SC063-Verify the valid inputs for Sensor Label-second text box
	@Test(groups = {
			"Regression" }, dataProvider = "TcSC063", dataProviderClass = setupCreationUtility.class, description = "Verify the valid inputs for Sensor Label-second text box")

	public void SC063(String sesrno, String sensrlblno) throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("SC063-Verify the valid inputs for Sensor Label-second text box");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Enter__Num_SensorLabel(sensrlblno);
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		sa.assertEquals(Setup_GroupSensorsPage.GrpsensorPage_state(), true,
				"FAIL: Group Sensors pageName should be displayed");

		sa.assertAll();
	}

	// SC064-Verify the invalid inputs for Sensor label-second text box

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC064", dataProviderClass = setupCreationUtility.class, description = "Verify the valid inputs for Sensor Label-second text box")

	public void SC064(String sesrno, String sensrlblno) throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("SC063-Verify the valid inputs for Sensor Label-second text box");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();

		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Enter__Num_SensorLabel(sensrlblno);
		Setup_SensorConfigPage.Click_assignBtn();

		String ActAlert = tu.get_AlertMsg_text();
		String ExpAlert = "Please provide valid Sensor Label Number.";

		sa.assertEquals(ActAlert, ExpAlert, "FAIL: Alert message should be displayed");
		sa.assertAll();
	}

	// SC065.1_Verify that unique sensor label names are allowed

	@Test(groups = { "Regression" }, description = "Verify that unique sensor label names are allowed")

	public void SC065_1() throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC065_1_Verify that unique sensor label names are allowed");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter__Num_SensorLabel("02");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorConfigPage.Click_assignBtn();

		String ActAlert = tu.get_AlertMsg_text();
		String ExpAlert = "Sensor Label should be unique..";
		sa.assertEquals(ActAlert, ExpAlert, "FAIL: Alert message should be displayed");
		sa.assertAll();
	}

	// SC065.2_Verify that validation message is displayed when selected one
	// sensor type and assigning label name for another sensor type

	@Test(groups = {
			"Regression" }, description = "Verify that validation message is displayed when selected one sensor type and assigning label name for another sensor type")

	public void SC065_2() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC065_2_Verify that validation message is displayed when selected one sensor type and assigning label name for another sensor type");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("4");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_SensorLabel("test");
		Setup_SensorConfigPage.Click_assignBtn();
		String ActAlert = tu.get_AlertMsg_text();
		String ExpAlert = "Selected sensors and selected sensor type are different. Please choose same type..";
		sa.assertEquals(ActAlert, ExpAlert, "FAIL: Alert message should be displayed");
		sa.assertAll();
	}

	// SC066-Verify if the _Assign_ button is activated only when the Sensor Label
	// and the Sensor Type text boxes inputs are provided.
	@Test(groups = {
			"Regression" }, description = "Verify if the _Assign_ button is activated only when the Sensor Label and the Sensor Type text boxes inputs are provided")

	public void SC066() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC066-Verify if the _Assign_ button is activated only when the Sensor Label and the Sensor Type text boxes inputs are provided.");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();

		sa.assertEquals(Setup_SensorConfigPage.IsAssign_Button_Enable(), false,
				"FAIL: Alert message should be displayed");
		Setup_SensorConfigPage.Click_Sensortype_field();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_SensorLabel("ABC");
		sa.assertEquals(Setup_SensorConfigPage.IsAssign_Button_Enable(), true,
				"FAIL: Alert message should be displayed");
		sa.assertAll();
	}

	// This script has some issues
	// SC066_1-Verify that on-click of the Assign button will assign the sensor
	// labels to the respective sensors on the left pane
	@Test(groups = {
			"Regression" }, description = "Verify that on-click of the Assign button will assign the sensor labels to the respective sensors on the left pane")

	public void SC066_1() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC066_1-Verify that on-click of the Assign button will assign the sensor labels to the respective sensors on the left pane");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_SensorLabel("test1");
		Setup_SensorConfigPage.Click_assignBtn();
		String ActsenTxt = Setup_SensorConfigPage.get_sensortext();
		// System.out.println(ActsenTxt);
		String ExpTxt = "test1";
		sa.assertEquals(ActsenTxt, ExpTxt, "FAIL: text should be matched");
		Setup_SensorConfigPage.Clickon_Humidity();
		Setup_SensorConfigPage.Click_Hmd_sensor();
		Setup_SensorConfigPage.Enter_SensorLabel("ABC");
		Setup_SensorConfigPage.Click_assignBtn();
		String ActHmdTxt1 = Setup_SensorConfigPage.get_sensortext();
		// System.out.println(ActHmdTxt1);
		String ExphmdTxt1 = "ABC";
		sa.assertEquals(ActHmdTxt1, ExphmdTxt1, "FAIL: text should be matched");

		sa.assertAll();
	}

	// SC067-Verify that when less number of sensors are configured than those
	// mentioned in Max sensors field in Define setup screen will display a
	// validation message

	@Test(groups = {
			"Regression" }, description = "Verify that when less number of sensors are configured than those mentioned in Max sensors field in Define setup scrfeen will display a validation message")

	public void SC067() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"Verify that when less number of sensors are configured than those mentioned in Max sensors field in Define setup scrfeen will display a validation message");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorConfigPage.Click_GroupSensors_btn();

		sa.assertEquals(Setup_SensorConfigPage.Is_lessnumberSenAlertBox_Visible(), true,
				"FAIL: text should be matched");
		sa.assertAll();
	}

	// SC068-Verify that Description button is enabled only when atleast one
	// sensor is configured

	@Test(groups = {
			"Regression" }, description = "Verify that Description button is enabled only when atleast one sensor is configured")

	public void SC068() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC068-Verify that Description button is enabled only when atleast one sensor is configured");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_assignBtn();

		sa.assertEquals(Setup_SensorConfigPage.IsDescription_Button_Enable(), true, "FAIL: Should be enabled");

		sa.assertAll();
	}

	// SC069-Verify that on-click of the Description button will display the
	// Sensor Description screen

	@Test(groups = {
			"Regression" }, description = "Verify that on-click of the Description button will display the Sensor Description screen")

	public void SC069() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC069-Verify that on-click of the Description button will display the Sensor Description screen");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_assignBtn();

		sa.assertEquals(Setup_SensorConfigPage.IsDescription_Button_Enable(), true, "FAIL: Should be enabled");

		sa.assertAll();
	}

	// SC070-Verify the details displayed in Description screen

	@Test(groups = { "Regression" }, description = "SC070-Verify the details displayed in Description screen")

	public void SC070() throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("SC070-Verify the details disp layed in Description screen");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("1");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();

		sa.assertEquals(Setup_SensorDescriptionPage.IsSensorDescriptionTitle_Visible(), true,
				"FAIL: Sensor Description Title is not Visible");
		sa.assertEquals(Setup_SensorDescriptionPage.IsSelectSensorTypeByHeader_Visible(), true,
				"FAIL:Select  Sensor Type By  header is not Visible");
		sa.assertEquals(Setup_SensorDescriptionPage.IsPageCloseIcon_Visible(), true,
				"FAIL: close button is not Visible");
		sa.assertAll();
	}

//SC071-Verify that on the left pane the sensor descriptions are displayed along with other details in Description screen
	@Test(groups = {
			"Regression" }, description = "SC071-Verify that on the left pane the sensor descriptions are displayed along with other details in Description screen")

	public void SC071() throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest(
				"SC071-Verify that on the left pane the sensor descriptions are displayed along with other details in Description screen");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("Sensorr");
		Setup_SensorConfigPage.Enter__Num_SensorLabel("11");
		Thread.sleep(3000);
		String SensorTypeTxt_SCpage = Setup_SensorConfigPage.get_Sensortype_text();

		System.out.println(SensorTypeTxt_SCpage);
		String s1 = Setup_SensorConfigPage.get_SensorLabel_text();
		String s2 = Setup_SensorConfigPage.get_SensorLabel_Num_text();
		String LabelName_SCpage = s1.concat(s2);
		// System.out.println(LabelName_SCpage);
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();

		sa.assertEquals(Setup_SensorDescriptionPage.IsSerialNo_field_Visible(), true,
				"FAIL: Serial number is not found");
		String LabelName_SDpage = Setup_SensorDescriptionPage.get_Labelext();
		// System.out.println("sd" +LabelName_SDpage);
		String SensorTypeTxt_SDpage = Setup_SensorDescriptionPage.get_Typetext();

		sa.assertEquals((LabelName_SCpage.contains(LabelName_SDpage)), true, "sensor type not matched");

		sa.assertEquals((SensorTypeTxt_SCpage.contains(SensorTypeTxt_SDpage)), true, "sensor type not matched");
		// sa.assertEquals(SensorTypeTxt_SCpage,SensorTypeTxt_SDpage ,"FAIL: Sensor type
		// is not same");

		sa.assertEquals(Setup_SensorDescriptionPage.IsPageCloseIcon_Visible(), true,
				"FAIL: close button is not Visible");
		sa.assertAll();
	}

	// SC072-Verify that on the right pane the selection and description of the
	// sensors are assigned in Description screen
	@Test(groups = {
			"Regression" }, description = "SC072-Verify that on the right pane the selection and description of the sensors are assigned in Description screen")

	public void SC072() throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest(
				"SC072-Verify that on the right pane the selection and description of the sensors are assigned in Description screen");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("1");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();

		sa.assertEquals(Setup_SensorDescriptionPage.is_SensorType_ComboBoxVisible(), true,
				"FAIL: SensorType ComboBox is not Visible");
		sa.assertEquals(Setup_SensorDescriptionPage.is_From_textVisible(), true,
				"FAIL: Fromtext ComboBox is not Visible");

		sa.assertEquals(Setup_SensorDescriptionPage.is_To_textVisible(), true,
				"FAIL: Totext range ComboBox is not Visible");

		sa.assertEquals(Setup_SensorDescriptionPage.IsRightpane_Description_field_Visible(), true,
				"FAIL: Description_Txtfield is not Visible");

		sa.assertEquals(Setup_SensorDescriptionPage.Is_SensorLabelAutoNumber_checked(), false,
				"FAIL: SensorLabelAutoNumber is not checked");

		sa.assertEquals(Setup_SensorDescriptionPage.IsOk_buttonVisible(), true,
				"FAIL: Description_Txtfield is not Visible");

		sa.assertEquals(Setup_SensorDescriptionPage.IsClose_buttonVisible(), true,
				"FAIL: Description_Txtfield is not Visible");

		sa.assertAll();

	}

	// SC073-Verify that the sensor type drop down displays Temperature only in
	// Description screen when Temperature sensors are added
	@Test(groups = {
			"Regression" }, description = "SC073-Verify that the sensor type drop down displays Temperature only  in Description screen when Temperature sensors are added")

	public void SC073() throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest(
				"SC073-Verify that the sensor type drop down displays Temperature only  in Description screen when Temperature sensors are added");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("1");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.click_SensorType_Field();
		sa.assertEquals(Setup_SensorDescriptionPage.IsTemperature_SensorType_Visible(), true,
				"FAIL: Temperature SensorType  is not Visible in sersor type drop down on the right pane ");
		sa.assertAll();

	}
	// SC074-Verify that the sensor type drop down displays Pressure only in
	// Description screen when Pressure sensors are added

	@Test(groups = {
			"Regression" }, description = "SC074-Verify that the sensor type drop down displays Pressure only  in Description screen when Pressure sensors are added")

	public void SC074() throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest(
				"SC074-Verify that the sensor type drop down displays Pressure only  in Description screen when Pressure sensors are added");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField("1");
		Setup_SensorConfigPage.Click_Prsr_sensor();
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.click_SensorType_Field();
		sa.assertEquals(Setup_SensorDescriptionPage.OnlyPressure_SensorType_Visible(), true,
				"FAIL: Pressure SensorType  is not Visible in sersor type drop down on the right pane ");

		sa.assertAll();

	}

	// SC075-Verify that the sensor type drop down displays Humidity only in
	// Description screen when Humidity sensors are added
	@Test(groups = {
			"Regression" }, description = "SC075-Verify that the sensor type drop down displays Humidity only  in Description screen when Humidity sensors are added")

	public void SC075() throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest(
				"SC075-Verify that the sensor type drop down displays Humidity only  in Description screen when Humidity sensors are added");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_HumidityCount_textField("1");
		Setup_SensorConfigPage.Click_Hmd_sensor();
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.click_SensorType_Field();
		sa.assertEquals(Setup_SensorDescriptionPage.Fetch_Humidity_SensorType_Txt(), "Humidity     (%RH)",
				"FAIL: Temperature SensorType  is not Visible in sersor type drop down on the right pane ");
		sa.assertAll();

	}

	// SC076-Verify that the sensor type drop down displays All in Description
	// screen
	@Test(groups = {
			"Regression" }, description = "SC076-Verify that the sensor type drop down displays All in Description screen")
	public void SC076() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("SC076-Verify that the sensor type drop down displays All in Description screen");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_AllSensortypes("1", "1", "1");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_SensorLabel("Sensor2");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_SensorLabel("Sensor3");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.click_SensorType_Field();

		Thread.sleep(3000);

		sa.assertEquals(Setup_SensorDescriptionPage.IsTemperature_SensorType_Visible(), true);
		sa.assertEquals(Setup_SensorDescriptionPage.IsHumidity_SensorType_Visible(), true);
		sa.assertEquals(Setup_SensorDescriptionPage.IsPressure_SensorType_Visible(), true);

		sa.assertAll();

	}

	// SC076.1-Verify that by default first sensor should be selected on the left
	// pane and it should reflect range as 1 to 1 on right pane in Description
	// screen
	@Test(groups = { "Regression" }, description = "SC076_1-Verify that by default first sensor should be "
			+ "selected on the left pane and it should reflect range as 1 to 1 on right pane in Description screen")
	public void SC076_1() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("SC076_1-Verify that by default first sensor should be selected on the left pane and it "
						+ "should reflect range as 1 to 1 on right pane in Description screen");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_AllSensortypes("1", "1", "1");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_SensorLabel("Sensor1");

		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_SensorLabel("Sensor2");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_SensorLabel("Sensor3");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();

		// sa.assertEquals(Setup_SensorDescriptionPage.Firstsensor_Selected(), true);
		sa.assertEquals(Setup_SensorDescriptionPage.get_from_text(), "1", "From text value is not displaying 1");
		sa.assertEquals(Setup_SensorDescriptionPage.get_To_text(), "1", "To text value is not displaying 1");
		sa.assertAll();
	}

	// SC077-Verify that the max char length for Range- first text box is 1 when the
	// no. of sensors added are in single digit
	@Test(groups = { "Regression" }, description = "SC077-Verify that the max char length for Range- first "
			+ "text box is 1 when the no. of sensors added are in single digit")
	public void SC077() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"SC077-Verify that the max char length for Range- first text box is 1 when the no. of sensors added are in single digit");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("9");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("9");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		String ToVal = Setup_SensorConfigPage.get_Tofield_text();

		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();

		// Enter 2 chars
		String Invalidvalue = "21";
		Setup_SensorDescriptionPage.Enter_Num_From(Invalidvalue);
		String FromVal_SD = Setup_SensorDescriptionPage.get_from_text();
		// System.out.println(FromVal_SD);
		sa.assertEquals(FromVal_SD.length(), 1,
				"the max char length for Range- first text box is not 1 when the no. of sensors added are in single digit");
		sa.assertEquals(FromVal_SD, ToVal,
				"Fail: From value in SD page is not displaying  the entered to value in SC page");

		sa.assertAll();

	}

	// SC078-Verify that the max char length for Range- first text box is 2 when the
	// no. of sensors added are in double digit
	@Test(groups = { "Regression" }, description = "SC078-Verify that the max char length for Range- first "
			+ "text box is 2 when the no. of sensors added are in double digit")
	public void SC078() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"SC078-Verify that the max char length for Range- first text box is 2 when the no. of sensors added are in double digit");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("22");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("22");
		String ToVal = Setup_SensorConfigPage.get_Tofield_text();
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");

		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		// Enter 3 chars

		String Invalidvalue = "311";
		Setup_SensorDescriptionPage.Enter_Num_From(Invalidvalue);
		String FromVal_SD = Setup_SensorDescriptionPage.get_from_text();
		System.out.println(FromVal_SD);
		sa.assertEquals(FromVal_SD.length(), 2,
				"the max char length for Range- first text box is not 2 when the no. of sensors added are in single digit");
		sa.assertEquals(FromVal_SD, ToVal, "From value on SD page  is not matching with To text value from SC page");
		sa.assertAll();

	}

	// SC079-Verify that the max char length for Range- first text box is 3 when the
	// no. of sensors added are in triple digit
	@Test(groups = { "Regression" }, description = "SC079-Verify that the max char length for Range- first "
			+ "text box is 3 when the no. of sensors added are in triple digit")

	public void SC079() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"SC079-Verify that the max char length for Range- first text box is 3 when the no. of sensors added are in triple digit");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("110");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("110");
		String ToVal = Setup_SensorConfigPage.get_Tofield_text();
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");

		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();

		// Enter 4 chars
		String Invalidvalue = "3111";
		Setup_SensorDescriptionPage.Enter_Num_From(Invalidvalue);
		String FromVal_SD = Setup_SensorDescriptionPage.get_from_text();
		System.out.println(FromVal_SD);
		sa.assertEquals(FromVal_SD.length(), 3,
				"the max char length for Range- first text box is not 3 when the no. of sensors added are in single digit");
		sa.assertEquals(FromVal_SD, ToVal, "From value on SD page  is not matching with To text value from SC page");
		sa.assertAll();

	}

	// SC080-Verify that when a greater value is entered in From field than the To
	// field the OK button is
	// disabled and a validation message is displayed in Description screen
	@Test(groups = { "Regression" }, description = "SC080-Verify that when a greater value is entered in From field "
			+ "than the To field, the OK button is disabled and a validation message is displayed in Description screen")
	public void SC080() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("SC080-Verify that when a greater value is entered in From field than the To field "
						+ "the OK button is disabled and a validation message is displayed in Description screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("15");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("15");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.Enter_Num_From("10");
		Setup_SensorDescriptionPage.Enter_TxtTo("1");
		Setup_SensorDescriptionPage.click_Description();
		String ExpAlrtMsg = "From value should be lessthan To value,Please give Correct Range";

		String ActAlrtMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg,
				"FAIL: Error message is not displayed when when a greater value is entered in From field than the To field");
		sa.assertEquals(Setup_SensorDescriptionPage.IsOk_btnEnable(), false, "FAIL: Ok Button is enable");
		sa.assertAll();

	}

	// SC081-Verify that when a Range is entered for a sensor type, the sensors on
	// the left pane within that range should be selected
	@Test(groups = { "Regression" }, description = "SC081-Verify that when a Range is entered for a sensor type, "
			+ "the sensors on the left pane within that range should be selected")
	public void SC081() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"SC081-Verify that when a Range is entered for a sensor type, the sensors on the left pane within that range should be selected");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("3");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("3");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.Enter_Num_From("1");
		Setup_SensorDescriptionPage.Enter_TxtTo("2");
		Setup_SensorDescriptionPage.clickOk();
		sa.assertEquals(Setup_SensorDescriptionPage.Firstsensor_Selected(), true, "FAIL: Sensor is not selected");

		sa.assertEquals(Setup_SensorDescriptionPage.Secondsensor_Selected(), true, "FAIL: Sensor is not selected");
		sa.assertAll();
	}

	// SC082-Verify the valid inputs in Range- First text box field in Description
	// screen
	@Test(dataProvider = "SC082", dataProviderClass = setupCreationUtility.class, groups = {
			"Regression" }, description = "SC082-Verify the valid inputs in Range- First text box field in Description screen")
	public void SC082(String FromText, String ExpAlrtMsg)
			throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent
				.startTest("SC082-Verify the valid inputs in Range- First text box field in Description screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("10");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.Enter_Num_From(FromText);
		Setup_SensorDescriptionPage.Enter_TxtTo("10");
		Setup_SensorDescriptionPage.clickOk();

		String ActAlrtMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Valid inputs not accepted by first text field");

		sa.assertAll();

	}

	// SC083-Verify the invalid inputs in Range- First text box in Description
	// screen
	@Test(dataProvider = "SC083", dataProviderClass = setupCreationUtility.class, groups = {
			"Regression" }, description = "SC083-Verify the invalid inputs in Range- First text box in Description screen")

	public void SC083(String FromText) throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("SC083-Verify the invalid inputs in Range- First text box in Description screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.Enter_Num_From(FromText);
		Setup_SensorDescriptionPage.clickOk();
		String ExpAlrtMsg = "Range field should not be empty.";
		String ActAlrtMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: InValid inputs  accepted by first text field");

		sa.assertAll();

	}

// SC083A-Enter only space in From field and verify the Assign button
	@Test(groups = { "Regression" }, description = "SC083A-Enter only space in From field and verify the Assign button")
	public void SC083A() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("SC083A-Enter only space in From field and verify the Assign button");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.Enter_Num_From("  ");
		Setup_SensorDescriptionPage.clickOk();
		String ExpAlrtMsg = "Range field should not be empty.";
		String ActAlrtMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Space accepted by first text field");

		sa.assertAll();

	}

//SC083B - Enter the value in From field more than defined in the Add sensors section
	@Test(groups = {
			"Regression" }, description = "SC083B - Enter the value in From field more than defined in the Add sensors section")
	public void SC083B() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent
				.startTest("SC083B - Enter the value in From field more than defined in the Add sensors section");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		String ToVal = Setup_SensorConfigPage.get_Tofield_text();
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.Enter_Num_From("10");
		String FrmTxt = Setup_SensorDescriptionPage.get_from_text();

		sa.assertEquals(ToVal, FrmTxt,
				"FAIL: The number  automatically not change to the number defined in the Add sensors section");

		sa.assertAll();

	}

//SC084-Verify the valid inputs in Range- Second text box field in Description screen
	@Test(dataProvider = "SC084", dataProviderClass = setupCreationUtility.class, groups = {
			"Regression" }, description = "SC084-Verify the valid inputs in Range- Second text box field in Description screen")

	public void SC084(String ToText) throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent
				.startTest("SC084-Verify the valid inputs in Range- Second text box field in Description screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("10");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.Enter_TxtTo(ToText);

		Setup_SensorDescriptionPage.clickOk();
		String ExpAlrtMsg = "Please Give Description for Update";

		String ActAlrtMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Valid inputs not accepted by first text field");

		sa.assertAll();

	}

	// SC084A-Verify the valid inputs in Range- Second text box field in Description
	// screen
	@Test(groups = {
			"Regression" }, description = "SC084A-Verify the valid inputs in Range- Second text box field in Description screen")
	public void SC084A() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent
				.startTest("SC084A-Verify the valid inputs in Range- Second text box field in Description screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("2");
		Setup_SensorConfigPage.Enter_PressureCount_textField("2");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("2");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_Num_To("2");
		Setup_SensorConfigPage.Enter_SensorLabel("pre");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.select_Sensortype_Pr();
		// System.out.println(Setup_SensorDescriptionPage.gettext_for_opt_2());
		Setup_SensorDescriptionPage.Enter_TxtTo("2");
		Setup_SensorDescriptionPage.click_Description();
		Setup_SensorDescriptionPage.Enter_Description("ABC1");
		Setup_SensorDescriptionPage.clickOk();

	}

//SC084.1-Verify the invalid inputs in Range- Second text box field in Description screen
	@Test(dataProvider = "SC084_1", dataProviderClass = setupCreationUtility.class, groups = {
			"Regression" }, description = "SC084.1-Verify the invalid inputs in Range- Second text box field in Description screen")

	public void SC084_1(String ToText) throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent
				.startTest("SC084.1-Verify the invalid inputs in Range- Second text box field in Description screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.Enter_TxtTo(ToText);
		Setup_SensorDescriptionPage.clickOk();
		String ExpAlrtMsg = "Range field should not be empty.";
		String ActAlrtMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: InValid inputs  accepted by second text field");

		sa.assertAll();

	}

//SC084_1A-Enter only space in From field and verify the Assign button
	@Test(groups = {
			"Regression" }, description = "SC084_1A-Enter only space in From field and verify the Assign button")

	public void SC084_1A() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("SC084_1A-Enter only space in From field and verify the Assign button");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.Enter_TxtTo("  ");
		Setup_SensorDescriptionPage.clickOk();
		String ExpAlrtMsg = "Range field should not be empty.";
		String ActAlrtMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Space accepted by second text field");

		sa.assertAll();

	}

//SC084_1B - Enter the value in To field more than defined in the Add sensors section
	@Test(groups = {
			"Regression" }, description = "SC084_1B - Enter the value in to field more than defined in the Add sensors section")

	public void SC084_1B() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent
				.startTest("SC083B - Enter the value in to field more than defined in the Add sensors section");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		String ToVal = Setup_SensorConfigPage.get_Tofield_text();
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.Enter_TxtTo("10");
		String ToTxt = Setup_SensorDescriptionPage.get_To_text();

		sa.assertEquals(ToVal, ToTxt,
				"FAIL: The number  automatically not change to the number defined in the Add sensors section");

		sa.assertAll();

	}

//SC085-Verify the max number of characters allowed in Description field
	@Test(groups = {
			"Regression" }, description = "SC085-Verify the max number of characters allowed in Description field")

	public void SC085() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("SC085-Verify the max number of characters allowed in Description field");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.click_Description();
		Setup_SensorDescriptionPage.Enter_Description("ABCDEFGHIJ1234567890AB111QEW");
		Setup_SensorDescriptionPage.clickOk();
		String ExpAlrtMsg = "Description cannot be more than 23 characters";
		String ActAlrtMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "Description accepting more than 23 charatecrs");

		sa.assertAll();
	}

//SC086-Verify the inputs in Description field
	@Test(dataProvider = "SC086", dataProviderClass = setupCreationUtility.class, groups = {
			"Regression" }, description = "SC086-Verify the inputs in Description field")

	public void SC086(String Description) throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("SC086-Verify the inputs in Description field");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.click_Description();
		Setup_SensorDescriptionPage.Enter_Description(Description);
		Setup_SensorDescriptionPage.clickOk();
		sa.assertEquals(Setup_SensorDescriptionPage.get_LeftpaneDescription_txt(1), Description,
				"Description accepting invalid values");

		sa.assertAll();
	}

//SC087-Verify that by manually selecting the sensors on the left pane and entering the description on the right pane will assign to the selected sensors on the left pane
	@Test(groups = {
			"Regression" }, description = "SC087-Verify that by manually selecting the sensors on the left pane and entering the description on the right pane will assign to the selected sensors on the left pane")

	public void SC087() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC087-Verify that by manually selecting the sensors on the left pane and entering the description on the right pane will assign to the selected sensors on the left pane");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("10");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.Enter_TxtTo("5");
		Setup_SensorDescriptionPage.click_Description();
		Setup_SensorDescriptionPage.Enter_Description("ABC");
		Setup_SensorDescriptionPage.clickOk();
		Setup_SensorDescriptionPage.Select_Row("2");
		Setup_SensorDescriptionPage.click_Description();
		Setup_SensorDescriptionPage.Enter_Description("NewDesc");
		Setup_SensorDescriptionPage.clickOk();
		sa.assertEquals(Setup_SensorDescriptionPage.get_LeftpaneDescription_txt(2), "NewDesc2",
				"Description text is not matching");

		sa.assertAll();
	}

//SC088-Verify that when selected the sensors manually on the left pane will disable the two Range fields on the right pane
	@Test(groups = {
			"Regression" }, description = "SC088-Verify that when selected the sensors manually on the left pane will disable the two Range fields on the right pane")

	public void SC088() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC088-Verify that when selected the sensors manually on the left pane will disable the two Range fields on the right pane");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("10");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();

		Setup_SensorDescriptionPage.Select_Row("4");
		Thread.sleep(1000);
		sa.assertEquals(Setup_SensorDescriptionPage.is_From_textEnable(), false,
				"From field on the right pane is not disable");
		sa.assertEquals(Setup_SensorDescriptionPage.is_To_textEnable(), false,
				"To field on the right pane is not disable");

		sa.assertAll();
	}

//SC089-Verify that while adding the description to the sensors on the left pane, numbers from 1 will get appended to the description for the sensors
	@Test(groups = {
			"Regression" }, description = "SC089-Verify that while adding the description to the sensors on the left pane, numbers from 1 will get appended to the description for the sensors")

	public void SC089() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC089-Verify that while adding the description to the sensors on the left pane, numbers from 1 will get appended to the description for the sensors");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("10");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();

		Setup_SensorDescriptionPage.Enter_TxtTo("5");
		Setup_SensorDescriptionPage.click_Description();
		Setup_SensorDescriptionPage.Enter_Description("ABC");
		Setup_SensorDescriptionPage.clickOk();
		Thread.sleep(2000);

		sa.assertEquals(Setup_SensorDescriptionPage.get_LeftpaneDescription_txt(1), "ABC1",
				"Description text is not matching in first row");
		sa.assertEquals(Setup_SensorDescriptionPage.get_LeftpaneDescription_txt(2), "ABC2",
				"Description text is not matching is not matching in second row");
		sa.assertEquals(Setup_SensorDescriptionPage.get_LeftpaneDescription_txt(3), "ABC3",
				"Description text is not matching is not matching in third row");

		sa.assertAll();

	}

	// SC090-Verify that description gets assigned to the sensors on the left pane
	// depending on the order of selection of the sensors
	@Test(groups = {
			"Regression" }, description = "SC090-Verify that description gets assigned to the sensors on the left pane depending on the order of selection of the sensors")

	public void SC090() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC090-Verify that description gets assigned to the sensors on the left pane depending on the order of selection of the sensors");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("8");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();

		// By default first row will remain selected , so here we are clicking on 1st
		// row to deselect it
		Setup_SensorDescriptionPage.Select_Row("1");

		Setup_SensorDescriptionPage.Select_Row("2");
		Setup_SensorDescriptionPage.Select_Row("5");
		Setup_SensorDescriptionPage.Select_Row("8");

		Setup_SensorDescriptionPage.click_Description();
		Setup_SensorDescriptionPage.Enter_Description("ABC");
		Setup_SensorDescriptionPage.clickOk();

		sa.assertEquals(Setup_SensorDescriptionPage.get_LeftpaneDescription_txt(2), "ABC1",
				"Description text is not matching in second row");
		sa.assertEquals(Setup_SensorDescriptionPage.get_LeftpaneDescription_txt(5), "ABC2",
				"Description text is not matching is not matching with fifth row");
		sa.assertEquals(Setup_SensorDescriptionPage.get_LeftpaneDescription_txt(8), "ABC3",
				"Description text is not matching is not matching in eight row");

		sa.assertAll();

	}

	// SC091-Verify that description is added to the sensors based on the Range
	// given
	@Test(groups = {
			"Regression" }, description = "SC091-Verify that description is added to the sensors based on the Range given")

	public void SC091() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("SC091-Verify that description is added to the sensors based on the Range given");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("20");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("10");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();

		Setup_SensorDescriptionPage.Enter_TxtTo("10");
		Setup_SensorDescriptionPage.click_Description();
		Setup_SensorDescriptionPage.Enter_Description("ABC");
		Setup_SensorDescriptionPage.clickOk();

		sa.assertEquals(Setup_SensorDescriptionPage.get_LeftpaneDescription_txt(1), "ABC1",
				"Description text is not matching in first row");
		sa.assertEquals(Setup_SensorDescriptionPage.get_LeftpaneDescription_txt(2), "ABC2",
				"Description text is not matching is not matching in second row");
		sa.assertEquals(Setup_SensorDescriptionPage.get_LeftpaneDescription_txt(3), "ABC3",
				"Description text is not matching is not matching in third row");
		sa.assertEquals(Setup_SensorDescriptionPage.get_LeftpaneDescription_txt(4), "ABC4",
				"Description text is not matching is not matching in fourth row");

		sa.assertAll();

	}

	// SC092-Verify that a validation message is displayed when trying to add
	// description when no sensor is selected on the left pane and the range fields
	// are empty
	@Test(groups = {
			"Regression" }, description = "SC092-Verify that a validation message is displayed when trying to add description when no sensor is selected on the left pane and the range fields are empty")

	public void SC092() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC092-Verify that a validation message is displayed when trying to add description when no sensor is selected on the left pane and the range fields are empty");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("10");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.Enter_Num_From("");
		Setup_SensorDescriptionPage.Enter_TxtTo("");
		Setup_SensorDescriptionPage.clickOk();
		String ExpAlrtMsg = "Range field should not be empty.";
		String ActAlrtMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Space accepted by first text field");

		sa.assertAll();

	}

	// SC093-Verify that when description is not entered for any sensor a validation
	// message is displayed
	@Test(groups = {
			"Regression" }, description = "SC093-Verify that when description is not entered for any sensor a validation message is displayed")

	public void SC093() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC093-Verify that when description is not entered for any sensor a validation message is displayed");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("4");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("4");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.Enter_Num_From("1");
		Setup_SensorDescriptionPage.Enter_TxtTo("4");
		Setup_SensorDescriptionPage.clickOk();
		String ExpAlrtMsg = "Please Give Description for Update";

		String ActAlrtMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Valid inputs not accepted by first text field");

		sa.assertAll();

	}

	/*****************************************
	 * //SC094 To SC100 will be handle manually as these test cases are related to
	 * Reports content .
	 ******************************************/

//SC101-Verify that when clicked on close button in description screen will close the window and sensor configuration is displayed
	@Test(groups = {
			"Regression" }, description = "SC101-Verify that when clicked on close button in description screen will close the window and sensor configuration is displayed")

	public void SC101() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC101-Verify that when clicked on close button in description screen will close the window and sensor configuration is displayed");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("4");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("4");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorConfigPage = Setup_SensorDescriptionPage.clickClose();
		sa.assertEquals(Setup_SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration",
				"Fil:Landed to wrong page");

		sa.assertAll();
	}

//SC102-Verify that when clicked on close icon on top right in description screen will close the window and sensor configuration is displayed
	@Test(groups = {
			"Regression" }, description = "SC102-Verify that when clicked on close icon on top right in description screen will close the window and sensor configuration is displayed")

	public void SC102() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC102-Verify that when clicked on close icon on top right in description screen will close the window and sensor configuration is displayed");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("4");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("4");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorConfigPage = Setup_SensorDescriptionPage.click_PageCloseIcon();
		sa.assertEquals(Setup_SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration",
				"Fil:Landed to wrong page");

		sa.assertAll();
	}

//SC103-Verify that when clicked on Define Setup navigator on top left in sensor configuration screen will navigate to Define setup screen
	@Test(groups = {
			"Regression" }, description = "SC103-Verify that when clicked on Define Setup navigator on top left in sensor configuration screen will navigate to Define setup screen")

	public void SC103() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC103-Verify that when clicked on Define Setup navigator on top left in sensor configuration screen will navigate to Define setup screen");
		SoftAssert sa = new SoftAssert();

		defineSetupPage = Setup_SensorConfigPage.DefineSetup_back_btn();
		sa.assertEquals(defineSetupPage.get_defineSetupPage_Nametext(), "Define Setup",
				"Fail: Define Setup page Name is not correct");
		sa.assertAll();
	}

//SC104-Verify that when clicked on Group sensors navigator on top right in sensor configuration screen will navigate to Group sensors screen
	@Test(groups = {
			"Regression" }, description = "SC104-Verify that when clicked on Group sensors navigator on top right in sensor configuration screen will navigate to Group sensors screen")

	public void SC104() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC104-Verify that when clicked on Group sensors navigator on top right in sensor configuration screen will navigate to Group sensors screen");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("1");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		sa.assertEquals(Setup_GroupSensorsPage.get_GrpsensorPage_titletext(), "Group Sensors",
				"Fail: Group Sensors page name is not correct");
		sa.assertAll();
	}

//SC105--Verify that on-click of the back icon on the screen will navigate to Asset details screen upon confirmation
	@Test(groups = {
			"Regression" }, description = "SC105--Verify that on-click of the back icon on the screen will navigate to Asset details screen upon confirmation")

	public void SC105() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC105--Verify that on-click of the back icon on the screen will navigate to Asset details screen upon confirmation");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("1");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		assetDetailsPage = Setup_GroupSensorsPage.Click_BackBtn();

		sa.assertEquals(assetDetailsPage.assetDetail_PageTitle(), "HeatBath - Asset01",
				"Fail: asset Details Page name is not correct");
		sa.assertAll();
	}

//SC106-Verify the bottom menu options displayed in Sensor configuration screen
	@Test(groups = {
			"Regression" }, description = "SC106-Verify the bottom menu options displayed in Sensor configuration screen")
	public void SC106() throws InterruptedException {
		extentTest = extent.startTest("SC106-Verify the bottom menu options displayed in Sensor configuration screen");
		SoftAssert sa = new SoftAssert();

		tu.Right_Click__Buttom_Menuoptions();

		sa.assertEquals(tu.check_Home_Buttom_AppBar_Presence(), true,
				"FAIL: Home icon/button missing in bottom app bar");
		sa.assertEquals(tu.check_Help_Buttom_AppBar_Presence(), true,
				"FAIL: Help icon/button missing in bottom app bar");
		sa.assertEquals(tu.check_WndsHelp_Buttom_AppBar_Presence(), true,
				"FAIL: Windows Help icon/button missing in bottom app bar");
		sa.assertEquals(tu.check_About_wndw_Presence(), true, "FAIL: About icon/button missing in bottom app bar");
		sa.assertAll();
	}

//SC107-Verify that on-click of home btn in bottom menu options is navigated to main hub page
	@Test(groups = {
			"Regression" }, description = "SC107-Verify that on-click of home btn in bottom menu options is navigated to main hub page")
	public void SC107() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"SC107-Verify that on-click of home btn in bottom menu options is navigated to main hub page");
		SoftAssert sa = new SoftAssert();

		MainHubPage = tu.Click_Home_Icon_AppBar();

		sa.assertEquals(MainHubPage.Is_mainHubPageTitle_Visible(), true,
				"FAIL: Clicking Home icon/button in bottom app bar do not redirect to Mains Hub page");
		sa.assertAll();
	}

	// SC108-Verify that on-click of help btn in bottom menu options displays
	// information about the Sensor configuration screen
	@Test(groups = {
			"Regression" }, description = "Verify that on-click of help btn in bottom menu options displays information about the Sensor configuration screen")
	public void SC108() throws InterruptedException {
		extentTest = extent.startTest(
				"SC108-Verify that on-click of help btn in bottom menu options displays information about the Sensor configuration screen");
		SoftAssert sa = new SoftAssert();

		tu.Click_Help_Icon_AppBar();
		sa.assertEquals(tu.get__HelpMenu_HdrText(), "Sensors Configuration",
				"FAIL: Clicking Help icon/button in bottom app bar"
						+ "do not display the Sensors Configuration Help context window");
		sa.assertAll();
	}

	// SC109-Verify that on-click of windows help btn in bottom menu options
	// generates a PDF with information
	// This TC will handle manually

	// SC110-Verify that on-click of About btn in bottom menu options displays the
	// software version and the console IP address
	@Test(groups = {
			"Regression" }, dataProvider = "SC110", dataProviderClass = setupCreationUtility.class, description = "Verify that on-click of About btn in bottom menu options displays "
					+ "the software version and the console IP address")
	public void SC110(String SWVer) throws InterruptedException, UnknownHostException {
		extentTest = extent.startTest("SC110-Verify that on-click of About btn in bottom menu options displays "
				+ "the software version and the console IP address");
		SoftAssert sa = new SoftAssert();

		tu.Click_About_Icon_AppBar();
		// System.out.println(SWVer);
		// System.out.println(tu.SWversion_InAboutWndw());
		sa.assertEquals(tu.SWversion_InAboutWndw(), SWVer,
				"FAIL: Incorrect SW version & Console IP adress displayed in About window");
		// System.out.println("Console IP Address in About window:
		// "+tu.consoleIP_InAboutWndw());
		// System.out.println(tu.system_IPadress());
		sa.assertEquals(tu.consoleIP_InAboutWndw(), tu.system_IPadress(),
				"FAIL: Incorrect SW version & Console IP adress displayed in About window");
		sa.assertAll();
	}

//SC111-Verify that Auto Numbering field is in Disable Mode as Default
	@Test(groups = {
			"Regression" }, description = "SC111-Verify that Auto Numbering field is in Disable Mode as Default")

	public void SC111() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("SC111-Verify that Auto Numbering field is in Disable Mode as Default");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("2");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("2");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();

		sa.assertEquals(Setup_SensorDescriptionPage.IsAutoNumber_Enable(), false,
				"Fail : Auto Numbering field is not in Disable Mode as Default");
		sa.assertAll();
	}

//SC112-Verify that Auto Numbering Field should not be in checked when one sensor is selected
	@Test(groups = {
			"Regression" }, description = "SC112-Verify that Auto Numbering Field should not be in checked when one sensor is selected")

	public void SC112() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC112-Verify that Auto Numbering Field should not be in checked when one sensor is selected");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("2");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("2");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();

		sa.assertEquals(Setup_SensorDescriptionPage.Is_Autonumber_checkedIn(), false,
				"Fail : Auto Numbering Field is checked when one sensor is selected");
		sa.assertAll();
	}

//SC113-Verify that Auto Numbering Field should be in checked when more than one sensor is selected
	@Test(groups = {
			"Regression" }, description = "SC113-Verify that Auto Numbering Field should be in checked when more than one sensor is selected")

	public void SC113() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC113-Verify that Auto Numbering Field should be in checked when more than one sensor is selected");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("3");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("3");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.Select_Row("2");
		sa.assertEquals(Setup_SensorDescriptionPage.Is_Autonumber_checkedIn(), true,
				"Fail : Auto Numbering Field is not checked  when more than one sensor is selected");
		sa.assertAll();
	}

//SC114-Verify that Clear button should be in disable mode when more than one sensor is selected
	@Test(groups = {
			"Regression" }, description = "SC114-Verify that Clear button should be in disable mode when more than one sensor is selected")

	public void SC114() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC114-Verify that Clear button should be in disable mode when more than one sensor is selected");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("3");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("3");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.Select_Row("2");
		sa.assertEquals(Setup_SensorDescriptionPage.IsClear_btnEnable(), false,
				"Fail : Clear button is not in disable mode when more than one sensor is selected");
		sa.assertAll();
	}

//SC116-Verify that Clear button should be in enable mode when one sensor is selected
	@Test(groups = {
			"Regression" }, description = "SC116-Verify that Clear button should be in enable mode when one sensor is selected")

	public void SC116() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent
				.startTest("SC116-Verify that Clear button should be in enable mode when one sensor is selected");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("3");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("3");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		sa.assertEquals(Setup_SensorDescriptionPage.IsClear_btnEnable(), true,
				"Fail : Clear button is not in enable mode when one sensor is selected");
		sa.assertAll();
	}

//SC117-Verify that Description should be clear on clicking on the Clear button when the sensor having the description
	@Test(groups = {
			"Regression" }, description = "SC117-Verify that Description should be clear on clicking on the Clear button when the sensor having the description")

	public void SC117() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC117-Verify that Description should be clear on clicking on the Clear button when the sensor having the description");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.click_Description();
		Setup_SensorDescriptionPage.Enter_Description("ABC1");
		Setup_SensorDescriptionPage.clickOk();
		Setup_SensorDescriptionPage.clickOnClear_btn();
		Thread.sleep(2000);
		sa.assertEquals(Setup_SensorDescriptionPage.get_Description_text(), "",
				"Fail : Clear button is not in enable mode when one sensor is selected");
		sa.assertAll();

	}

//SC118-Verify that Validation message should be displayed on clicking on the Clear button when the sensor does not having the description
	@Test(groups = {
			"Regression" }, description = "SC118-Verify that Validation message should be displayed on clicking on the Clear button when the sensor does not having the description")

	public void SC118() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"SC118-Verify that Validation message should be displayed on clicking on the Clear button when the sensor does not having the description");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorDescriptionPage = Setup_SensorConfigPage.Click_DescriptionButton();
		Setup_SensorDescriptionPage.clickOnClear_btn();
		String Actualresult = Setup_SensorDescriptionPage.Alertmsg_txt();
		String Expectedresult = "The Description is empty for selected row";

		sa.assertEquals(Actualresult, Expectedresult, "Fail : alert message is not displaying ");
		sa.assertAll();

	}

}
