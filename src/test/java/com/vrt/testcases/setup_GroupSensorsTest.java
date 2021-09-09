package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;
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

import com.vrt.pages.Setup_CalculationsPage;

public class setup_GroupSensorsTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>SetupTestData.xlsx sheet for test data i/p

	public setup_GroupSensorsTest() throws IOException {
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
	Setup_CalculationsPage Setup_CalculationsPage;
	String assetid = "01";

	// Before All the tests are conducted
	@BeforeTest
	public void PreSetup() throws InterruptedException, IOException, AWTException, ParseException {

		extent = new ExtentReports(
				System.getProperty("user.dir") + "/test-output/ER_" + "setup_GroupSensorsTest" + ".html", true);
		extent.addSystemInfo("TestSuiteName", "setup_GroupSensorsTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name2"));
		System.out.println("setup_SensorConfigTest in Progress..");

		// Rename the User file (NgvUsers.uxx) if exists

		// Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		// Rename the cache Asset file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
		// Rename the Asset folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		// Rename the cache Setup file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache\\ValProbeRT", "Setup.txt");
		// Rename the VRT Setups folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTSetups");

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		extent.addSystemInfo("VRT Version", LoginPage.get_SWVersion_About_Text());
		// Method to Create Very 1st User with All privilege
		UserManagementPage = LoginPage.DefaultLogin();
		LoginPage = UserManagementPage.FirstUserCreation("User1", getUID("adminFull"), getPW("adminFull"),
				getPW("adminFull"), "FullAdmin", "12345678", "abc@gmail.com");
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("User1");

		UserManagementPage.clickPrivCreateEditAsset();
		UserManagementPage.clickPrivCreateEditSetup();

		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		// Method to Create 1st Asset
		assetHubPage = MainHubPage.Click_AssetTile();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset01", assetid, "HeatBath", "aas", "Hyderabad", "VRT-RF",
				"2", "cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		AppClose();
		Thread.sleep(500);

	}

	// After All the tests are conducted
	@AfterTest
	public void endReport_releaseMomory() {
		extent.flush();
		extent.close();
		assetHubPage.resetWebElements();
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
		defineSetupPage.enter_defineSetupPage_SensorCount("300");
		defineSetupPage.enter_defineSetupPage_SOP("10");
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

	// Test Cases
	// GS001-Verify the details displayed in Group Sensor screen

	@Test(groups = { "Regression" }, description = "GS001-Verify the details displayed in Group Sensor screen")
	public void GS001() throws InterruptedException, IOException {
		extentTest = extent.startTest("GS001-Verify the details displayed in Group Sensor screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("10");
		Setup_SensorConfigPage.Enter_SensorLabel("SL");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		sa.assertEquals(Setup_GroupSensorsPage.get_Setup_titletext(), "test", "FAIL: Set up title is not available");
		sa.assertEquals(Setup_GroupSensorsPage.get_GrpsensorPage_titletext(), "Group Sensors",
				"FAIL: Group sensor page title is not displaying ");

		sa.assertEquals(Setup_GroupSensorsPage.DefaultGrp_Btn_state(), true,
				"FAIL: Default Group Btn is not available");

		sa.assertEquals(Setup_GroupSensorsPage.NewGroup_Btn_State(), true, "FAIL: NewGroup Btn Is not visible ");

		sa.assertEquals(Setup_GroupSensorsPage.SensorCount(), 15, "FAIL: sensor counts arenot displaying ");

		sa.assertEquals(Setup_GroupSensorsPage.SensorConfiguration_Tab_state(), true,
				"FAIL: SensorConfiguration tab is not available");

		sa.assertEquals(Setup_GroupSensorsPage.Calculationn_Tab_state(), true, "FAIL: New Group Btn is not available");

		sa.assertEquals(Setup_GroupSensorsPage.EditGrpHdr_state(), true, "FAIL: New Group Btn is not available");

		sa.assertEquals(Setup_GroupSensorsPage.GroupsList_Btn_state(), true, "FAIL: GroupsList_Btn is not available");

		sa.assertEquals(Setup_GroupSensorsPage.GroupWiring_Btn_state(), true, "FAIL:GroupWiring_Btn is not available");

		sa.assertEquals(Setup_GroupSensorsPage.DeleteGroup_Btn_state(), true, "FAIL:DeleteGroup_Btn is not available");

		sa.assertAll();
	}

	// GS002-Verify that when clicked on the Default button, the groups get created
	// as per the sensor type with edit icon against each group
	@Test(groups = {
			"Regression" }, description = "GS002-Verify that when clicked on the Default button, the groups get created as per the sensor type with edit icon against each group")
	public void GS002() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS002-Verify that when clicked on the Default button, the groups get created as per the sensor type with edit icon against each group");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("PSR");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		sa.assertEquals(Setup_GroupSensorsPage.get_groupTitle(0), "Temperature",
				"FAIL: New Group Btn is not available");
		// System.out.println(Setup_GroupSensorsPage.get_groupTitle());
		sa.assertEquals(Setup_GroupSensorsPage.get_groupTitle(1), "Humidity", "FAIL: New Group Btn is not available");
		sa.assertEquals(Setup_GroupSensorsPage.get_groupTitle(2), "Pressure", "FAIL: New Group Btn is not available");

		sa.assertEquals(Setup_GroupSensorsPage.EditGroupButton_state(), true, "FAIL: New Group Btn is not available");

		sa.assertAll();

	}

	// GS003-Verify that when clicked on the Temperature group, the temperature
	// sensors list is displayed
	@Test(groups = {
			"Regression" }, description = "GS003-Verify that when clicked on the Temperature group, the temperature sensors list is displayed")
	public void GS003() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS003-Verify that when clicked on the Temperature group, the temperature sensors list is displayed");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		String Tmpcount = Setup_SensorConfigPage.get_Tofield_text();
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		String Temp_SensorLabel = Setup_SensorConfigPage.get_SensorLabel_text();

		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("PSR");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		int Tmpcount1 = Integer.parseInt(Tmpcount);
		sa.assertEquals(Setup_GroupSensorsPage.Temp_Sensor_Txt(), Temp_SensorLabel,
				"FAIL:the Temperature  sensors label  is not displayed");

		sa.assertEquals(Setup_GroupSensorsPage.GroupSensorCount(), Tmpcount1, "Temp sensor count is not displayed ");

		sa.assertAll();
	}

	// GS004-Verify that when clicked on the Pressure group, the pressure sensors
	// list is displayed
	@Test(groups = {
			"Regression" }, description = "GS004-Verify that when clicked on the Pressure group, the pressure sensors list is displayed")
	public void GS004() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS004-Verify that when clicked on the Pressure group, the pressure sensors list is displayed");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_Num_To("5");
		String Psrcount = Setup_SensorConfigPage.get_Tofield_text();
		Setup_SensorConfigPage.Enter_SensorLabel("PSR");
		String Prsr_SensorLabel = Setup_SensorConfigPage.get_SensorLabel_text();
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.Selectgroup(1);
		sa.assertEquals(Setup_GroupSensorsPage.Prs_Sensor_Txt(), Prsr_SensorLabel,
				"FAIL: The pressure sensors  is not displayed");
		int Psrcount1 = Integer.parseInt(Psrcount);
		int Psrcount2 = Setup_GroupSensorsPage.GroupSensorCount();

		sa.assertEquals(Psrcount2, Psrcount1, "Pressure sensor count is not displayed ");
		sa.assertAll();
	}

	// GS005-Verify that when clicked on the Humidity group, the humidity sensors
	// list is displayed
	@Test(groups = {
			"Regression" }, description = "GS005-Verify that when clicked on the Humidity group, the humidity sensors list is displayed")
	public void GS005() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS005-Verify that when clicked on the Humidity group, the humidity sensors list is displayedGS004-Verify that when clicked on the Pressure group, the pressure sensors list is displayed");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");

		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		String Hmdcount = Setup_SensorConfigPage.get_Tofield_text();
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		String Hmd_SensorLabel = Setup_SensorConfigPage.get_SensorLabel_text();
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.Selectgroup(1);
		sa.assertEquals(Setup_GroupSensorsPage.Hmd_Sensor_Txt(), Hmd_SensorLabel,
				"FAIL:HMD sensor label is not displayed  ");
		int Hmdcount1 = Integer.parseInt(Hmdcount);
		sa.assertEquals(Setup_GroupSensorsPage.GroupSensorCount(), Hmdcount1, "HMD sensor count is not displayed ");

		sa.assertAll();
	}

	// GS005.1-Verify that when there are more number of sensors for a group, max 50
	// sensors are displayed for that group and next 50 sensors are divided into the
	// second group
	@Test(groups = {
			"Regression" }, description = "GS005.1-Verify that when there are more number of sensors for a group, max 50 sensors are displayed for that group and next 50 sensors are divided into the second group")
	public void GS005_1() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS005.1-Verify that when there are more number of sensors for a group, max 50 sensors are displayed for that group and next 50 sensors are divided into the second group");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("60");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("60");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		sa.assertEquals(Setup_GroupSensorsPage.GroupSensorCount(), 50,
				"FAIL: More than 50 sensors are displaying under one group");
		Thread.sleep(2000);
		Setup_GroupSensorsPage.Selectgroup(1);
		sa.assertEquals(Setup_GroupSensorsPage.GroupSensorCount(), 10,
				"FAIL: More than 10 sensors are displaying under one group");

		sa.assertAll();
	}

	// GS006-Verify that on-click of the edit icon for any group, the group name and
	// all the sensors of all sensor types are editable
	@Test(groups = {
			"Regression" }, description = "GS006-Verify that when clicked on the Pressure group, the pressure sensors list is displayed")
	public void GS006() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS006-Verify that when clicked on the Pressure group, the pressure sensors list is displayed");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		String Tmpcount = Setup_SensorConfigPage.get_Tofield_text();
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.Selectgroup(0);
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		Setup_GroupSensorsPage.Edit_GrpTitle_Save("TS");
		Thread.sleep(1000);
		int Tmpcount1 = Integer.parseInt(Tmpcount);
		sa.assertEquals(Setup_GroupSensorsPage.get_groupTitle(0), "TS", "FAIL: Modified group name not displaying");
		sa.assertEquals(Setup_GroupSensorsPage.GroupSensorCount(), Tmpcount1, "TEMP sensor count is not displayed ");
		sa.assertAll();

	}
//GS007-Verify that when edited the groups, the sensors of the current group will be selected and Save and cancel buttons are displayed

	@Test(groups = {
			"Regression" }, description = "GS007:Verify that when edited the groups, the sensors of the current group will be selected and Save and cancel buttons are displayed")
	public void GS007() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS007:Verify that when edited the groups, the sensors of the current group will be selected and Save and cancel buttons are displayed");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		String Tmpcount = Setup_SensorConfigPage.get_Tofield_text();
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("PSR");
		String Prsr_SensorLabel = Setup_SensorConfigPage.get_SensorLabel_text();
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.Selectgroup(0);
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
//Setup_GroupSensorsPage.Edit_TmpGrp_Title("TS");
		Setup_GroupSensorsPage.sensorSelected_Count();

		sa.assertEquals(Setup_GroupSensorsPage.IsSaveBtn_Visible(), true, "SaveBtn is not displayed ");

		sa.assertEquals(Setup_GroupSensorsPage.IsCncelBtn_Visible(), true, "CancelBtn is not displayed ");
		sa.assertAll();

	}

// GS008-Verify that the wiring overlay icon will be non-clickable and new griup
// button is disabled when groups are in edit mode

	@Test(groups = {
			"Regression" }, description = "GS008-Verify that the wiring overlay icon will be non-clickable and new griup button is disabled when groups are in edit mode")
	public void GS008() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS008-Verify that the wiring overlay icon will be non-clickable and new griup button is disabled when groups are in edit mode");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.click_GroupEditIcon(0);

		sa.assertEquals(Setup_GroupSensorsPage.GroupWiring_Btn_IsEnable(), false, "FAIL: GroupsGraph_Btn is  Enable");

		sa.assertEquals(Setup_GroupSensorsPage.NewGroup_Btn_IsEnable(), false, "FAIL: NewGroup_Btn is IsEnable");
		sa.assertAll();
	}

	// GS009-Verify that when temperature group is in edit mode, the sensors of
	// different sensor type are not selectable

	@Test(groups = {
			"Regression" }, description = "GS009-Verify that when temperature group is in edit mode, the sensors of different sensor type are not selectable")
	public void GS009() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS009-Verify that when temperature group is in edit mode, the sensors of different sensor type are not selectable");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("hmd");
		String sensrlabel = Setup_SensorConfigPage.get_SensorLabel_text();

		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.Selectgroup(1);
		Setup_GroupSensorsPage.click_GroupEditIcon(0);

		String Nonselected_sensorname = Setup_GroupSensorsPage.Fetch_NonSelected_Sensorname();
//System.out.println(Nonselected_sensorname);
		sa.assertEquals(sensrlabel, Nonselected_sensorname,
				"Fail: the sensors of different sensor type are selectable");

	}
	// GS010-Verify that when pressure group is in edit mode, the sensors of
	// different sensor type are not selectable

	@Test(groups = {
			"Regression" }, description = "GS010-Verify that when pressure group is in edit mode, the sensors of different sensor type are not selectable")
	public void GS010() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS010-Verify that when pressure group is in edit mode, the sensors of different sensor type are not selectable");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		String sensrlabel = Setup_SensorConfigPage.get_SensorLabel_text();

		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("presr");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.Selectgroup(1);
		Setup_GroupSensorsPage.click_GroupEditIcon(1);
		String Nonselected_sensorname = Setup_GroupSensorsPage.Fetch_NonSelected_Sensorname();

		sa.assertEquals(sensrlabel, Nonselected_sensorname,
				"Fail: the sensors of different sensor type are selectable");

	}

	// GS011-Verify that when humidity group is in edit mode, the sensors of
	// different sensor type are not selectable
	@Test(groups = {
			"Regression" }, description = "GS011-Verify that when humidity group is in edit mode, the sensors of different sensor type are not selectable")
	public void GS011() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS011-Verify that when humidity group is in edit mode, the sensors of different sensor type are not selectable");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		String tmplabel = Setup_SensorConfigPage.get_SensorLabel_text();
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("hmd");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		// Setup_GroupSensorsPage.Click_hmd_Group();
		Setup_GroupSensorsPage.Selectgroup(1);
		Setup_GroupSensorsPage.click_GroupEditIcon(1);
		String Nonselected_sensorname = Setup_GroupSensorsPage.Fetch_NonSelected_Sensorname();

		// System.out.println("sensor name " +Nonselected_sensorname);
		sa.assertEquals(tmplabel, Nonselected_sensorname, "Fail: the sensors of different sensor type are  selectable");
		sa.assertAll();
	}

	// GS012-Verify that the max number of characters allowed in group name field
	// are 35 char
	@Test(groups = {
			"Regression" }, description = "GS012-Verify that the max number of characters allowed in group name field are 35 char")
	public void GS012() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("GS012-Verify that the max number of characters allowed in group name field are 35 char");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("PSR");
		String Prsr_SensorLabel = Setup_SensorConfigPage.get_SensorLabel_text();
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.Selectgroup(0);
		Setup_GroupSensorsPage.click_GroupEditIcon(0);

		// We are entering 40 chars , but it should accept 35 chars

		Setup_GroupSensorsPage.Edit_GrpTitle_Save("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMN");
		String name = Setup_GroupSensorsPage.get_groupTitle(0);

		sa.assertEquals(name.length(), 35, "FAIL: Modified group name not displaying");
		sa.assertAll();

	}

// GS013-Verify the valid inputs for Group name field
	@Test(groups = {
			"Regression" }, dataProvider = "GS013", dataProviderClass = setupCreationUtility.class, description = "GS013-Verify the valid inputs for Group name field"
					+ " accepted in Temperature field under Add Sensors section")

	public void GS013(String GName) throws InterruptedException, IOException {
		extentTest = extent.startTest("GS013-Verify the valid inputs for Group name field");

		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.Selectgroup(0);
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		Setup_GroupSensorsPage.Edit_GrpTitle_Save(GName);
		String GName1 = Setup_GroupSensorsPage.get_groupTitle(0);
		sa.assertEquals(GName1, GName, "Fail: Group name is not valid or  value has not saved properly");
		sa.assertAll();

	}
	// GS014-Verify the invalid inputs for Group name field

	@Test(groups = {
			"Regression" }, dataProvider = "GS014", dataProviderClass = setupCreationUtility.class, description = "GS014-Verify the invalid inputs for Group name field"
					+ "accepted in Temperature field under Add Sensors section")

	public void GS014(String InvalidGName, String ExpAlrtMsg) throws InterruptedException, IOException {
		extentTest = extent.startTest("GS014-Verify the invalid inputs for Group name field");

		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.Selectgroup(0);
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		Setup_GroupSensorsPage.Edit_GrpTitle_Save(InvalidGName);

		String ActAlrtMsg = tu.get_AlertMsg_text();

		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Alert message is not matching ");
		sa.assertAll();

	}

	// GS014B-Do not enter anything in Group name field. Click on calculations Tab

	@Test(groups = {
			"Regression" }, description = "GS014B-Do not enter anything in Group name field. Click on calculations Tab")

	public void GS014B() throws InterruptedException, IOException {
		extentTest = extent.startTest("GS014B-Do not enter anything in Group name field. Click on calculations Tab");

		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("PSR");
		String Prsr_SensorLabel = Setup_SensorConfigPage.get_SensorLabel_text();
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.Selectgroup(1);
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		Setup_GroupSensorsPage.Edit_GrpTitle_DontClickSave("");
		Setup_GroupSensorsPage.Click_CalculationsTab_Alrt();

		String ExpAlrtMsg = "Please Enter Group Name";

		String ActAlrtMsg = tu.get_AlertMsg_text();

		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Alert message is not matching ");
		sa.assertAll();

	}

	// GS015-Verify that the sensors can be selected or de-selected for a group in
	// edit mode

	@Test(groups = {
			"Regression" }, description = "GS015-Verify that the sensors can be selected or de-selected for a group in edit mode")
	public void GS015() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("GS015-Verify that the sensors can be selected or de-selected for a group in edit mode");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("temp");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		Setup_GroupSensorsPage.clickOn_NewGroupButton();
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		Setup_GroupSensorsPage.Edit_GrpTitle_DontClickSave("GS");
		Setup_GroupSensorsPage.clickon_Sensors(1);// select two sensor
		Setup_GroupSensorsPage.click_SaveBtn();

		sa.assertEquals(Setup_GroupSensorsPage.GroupSensorCount(), 2);
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		Setup_GroupSensorsPage.clickon_Sensors(0); // Now deselect one sensor
		Setup_GroupSensorsPage.click_SaveBtn();
		sa.assertEquals(Setup_GroupSensorsPage.GroupSensorCount(), 1);

	}

	// GS016-Verify that when selected more than 50 sensors for a group of same
	// sensor type in edit mode, a validation message is displayed that max 50
	// sensors are allowed for each group

	@Test(groups = {
			"Regression" }, description = "GS016-Verify that when selected more than 50 sensors for a group of same sensor type in edit mode, a validation message is displayed that max 50 sensors are allowed for each group")
	public void GS016() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS016-Verify that when selected more than 50 sensors for a group of same sensor type in edit mode, a validation message is displayed that max 50 sensors are allowed for each group");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("60");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("60");
		Setup_SensorConfigPage.Enter_SensorLabel("SL");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		Setup_GroupSensorsPage.Click_Sensor51();

		String ExpAlrtMsg = "Maximum(50) sensors selection has reached in this group";

		String ActAlrtMsg = tu.get_AlertMsg_text();

		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Alert message is not matching ");
		sa.assertAll();

	}

	// GS017-Verify that when clicked on the cancel button for groups in edit mode,
	// will discard the changes
	@Test(groups = {
			"Regression" }, description = "GS017-Verify that when clicked on the cancel button for groups in edit mode, will discard the changes")
	public void GS017() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS017-Verify that when clicked on the cancel button for groups in edit mode, will discard the changes");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("SL");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		int count1 = Setup_GroupSensorsPage.SensorCount();

		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.click_GroupEditIcon(0);

		Setup_GroupSensorsPage.Edit_GrpTitle_DontClickSave("abc");
		Setup_GroupSensorsPage.click_CancelBtn();
		sa.assertEquals(Setup_GroupSensorsPage.get_groupTitle(0), "Temperature", "FAIL: Changes are not discarded");
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		int count2 = Setup_GroupSensorsPage.sensorSelected_Count();

		Setup_GroupSensorsPage.clickon_Sensors(0); // click on sensor to deselect the sensor
		Setup_GroupSensorsPage.click_CancelBtn();
		sa.assertEquals(count1, count2,
				"Fail: clicked on the cancel button for groups in edit mode but failed  to  discard the changes");
		sa.assertAll();
	}

	// GS018-Verify that on-click of save button for group in edit mode will edit
	// the group successfully
	@Test(groups = {
			"Regression" }, description = "GS018-Verify that on-click of save button for group in edit mode will edit the group successfully")
	public void GS018() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS018-Verify that on-click of save button for group in edit mode will edit the group successfully");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("SL");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.click_GroupEditIcon(0);

		Setup_GroupSensorsPage.Edit_GrpTitle_Save("abc");
		sa.assertEquals(Setup_GroupSensorsPage.get_groupTitle(0), "abc", "FAIL:Group Edited Success");
		sa.assertAll();
	}

	// GS019-Verify that when clicked on new group button, an empty group name field
	// is displayed along with all sensors of all sensor types displayed
	@Test(groups = {
			"Regression" }, description = "GS019-Verify that when clicked on new group button, an empty group name field is displayed along with all sensors of all sensor types displayed")
	public void GS019() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS019-Verify that when clicked on new group button, an empty group name field is displayed along with all sensors of all sensor types displayed");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");

		Setup_SensorConfigPage.Enter_SensorLabel("SL");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		Setup_GroupSensorsPage.clickOn_NewGroupButton();
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		String Gt = Setup_GroupSensorsPage.get_groupTitle(0);
		// System.out.println(Gt);
		sa.assertEquals(Gt, " Enter Text ", "FAIL:Empty Group Name field is not dispalying ");
		sa.assertEquals(Setup_GroupSensorsPage.SensorCount(), 5, "Fail: all sensors are  not displayed");
		sa.assertAll();
	}

	// GS020-Verify that when creating a new group, the wiring overlay icon is
	// disabled and Save and cancel buttons are displayed
	@Test(groups = {
			"Regression" }, description = "GS020-Verify that when creating a new group, the wiring overlay icon is disabled and Save and cancel buttons are displayed")
	public void GS020() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS020-Verify that when creating a new group, the wiring overlay icon is disabled and Save and cancel buttons are displayed");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("SL");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		Setup_GroupSensorsPage.clickOn_NewGroupButton();
		Setup_GroupSensorsPage.click_GroupEditIcon(0);

		sa.assertEquals(Setup_GroupSensorsPage.GroupWiring_Btn_IsEnable(), false,
				"FAIL: GroupWiring button Is  Enable");
		sa.assertEquals(Setup_GroupSensorsPage.IsSaveBtn_Visible(), true, "FAIL:Save Button is not visible");
		sa.assertEquals(Setup_GroupSensorsPage.IsCncelBtn_Visible(), true, "FAIL: Cancel Button is not visible");
		sa.assertAll();
	}

	// GS021-Verify that when creating a new group, when one sensor is selected of a
	// particular sensor type, a sensor of another sensor type is not selectable

	@Test(groups = {
			"Regression" }, description = "GS021-Verify that when creating a new group, when one sensor is selected of a particular sensor type, a sensor of another sensor type is not selectable")

	public void GS021() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS021-Verify that when creating a new group, when one sensor is selected of a particular sensor type, a sensor of another sensor type is not selectable");

		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.clickOn_NewGroupButton();
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		Setup_GroupSensorsPage.Click_Sensors_ByName("HMD");

		sa.assertEquals(Setup_GroupSensorsPage.Fetch_NonSelected_Sensorname(), "TMP",
				"Fail : User is able to select another sensor type ");
		sa.assertAll();

	}

	// GS022-Verify that the sensors can be selected or de-selected for a group in
	// create mode

	@Test(groups = {
			"Regression" }, description = "GS022-Verify that the sensors can be selected or de-selected for a group in create mode")

	public void GS022() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("GS022-Verify that the sensors can be selected or de-selected for a group in create mode");

		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.clickOn_NewGroupButton();
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		Setup_GroupSensorsPage.Edit_GrpTitle_DontClickSave("AXX");
		Setup_GroupSensorsPage.clickon_Sensors(1);
		// Setup_GroupSensorsPage.clickon_Sensor(1);// select two sensor
		Setup_GroupSensorsPage.click_SaveBtn();

		sa.assertEquals(Setup_GroupSensorsPage.GroupSensorCount(), 2);
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		Setup_GroupSensorsPage.clickon_Sensors(0); // Now deselect one sensor
		Setup_GroupSensorsPage.click_SaveBtn();
		sa.assertEquals(Setup_GroupSensorsPage.GroupSensorCount(), 1);
		sa.assertAll();

	}

	// GS023-Verify that when selected more than 50 sensors for a group of same
	// sensor type in create mode, a validation message is displayed that max 50
	// sensors are allowed for each group
	@Test(groups = {
			"Regression" }, description = "Verify that when selected more than 50 sensors for a group of same sensor type in create mode, a validation message is displayed that max 50 sensors are allowed for each group")

	public void GS023() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("GS022-Verify that the sensors can be selected or de-selected for a group in create mode");

		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("55");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("55");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("55");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("55");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.clickOn_NewGroupButton();
		Setup_GroupSensorsPage.Click_MoreThan50Sensors();
		String ExpAlrtMsg = "Maximum(50) sensors selection has reached in this group";

		String ActAlrtMsg = tu.get_AlertMsg_text();

		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Alert message is not matching ");
		sa.assertAll();

	}

	// GS024-Verify that when clicked on the cancel button for groups in create
	// mode, will discard the changes
	@Test(groups = {
			"Regression" }, description = "GS024-Verify that when clicked on the cancel button for groups in create mode, will discard the changes")
	public void GS024() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS024-Verify that when clicked on the cancel button for groups in create mode, will discard the changes");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("SL");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.clickOn_NewGroupButton();
		Setup_GroupSensorsPage.click_GroupEditIcon(0);

		Setup_GroupSensorsPage.Edit_GrpTitle_DontClickSave("abc");
		Setup_GroupSensorsPage.clickon_Sensors(0);
		Setup_GroupSensorsPage.click_CancelBtn();

		sa.assertEquals(Setup_GroupSensorsPage.NewGroup_Btn_IsEnable(), true, "FAIL: NewGroup Btn IsEnable ");
		sa.assertAll();

	}

//GS025-Verify that on-click of save button for group creation will create the group successfully  
	@Test(groups = {
			"Regression" }, description = "GS025-Verify that on-click of save button for group creation will create the group successfully")
	public void GS025() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS025-Verify that on-click of save button for group creation will create the group successfully");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("SL");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.clickOn_NewGroupButton();
		Setup_GroupSensorsPage.click_GroupEditIcon(0);

		Setup_GroupSensorsPage.Edit_GrpTitle_Save("abc");
		sa.assertEquals(Setup_GroupSensorsPage.get_groupTitle(0), "abc", "FAIL:Group Edited Success");
		sa.assertAll();
	}

//GS026-Verify that on-click of the wiring overlay icon, will display the wiring overlay screen

	@Test(groups = {
			"Regression" }, description = "GS026-Verify that on-click of the wiring overlay icon, will display the wiring overlay screen")
	public void GS026() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS026-Verify that on-click of the wiring overlay icon, will display the wiring overlay screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("SL");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_GrpWiring_Btn();

		sa.assertEquals(Setup_GroupSensorsPage.Is_1stImageBtn_Visible(), true,
				"Failed: the wiring overlay screen is not displayed ");
		sa.assertAll();
	}

//GS027-Verify the details displayed in Wiring overlay screen ScrollViewer
	@Test(groups = {
			"Regression" }, description = "GS027-Verify the details displayed in Wiring overlay screen ScrollViewer")
	public void GS027() throws InterruptedException, IOException {
		extentTest = extent.startTest("GS027-Verify the details displayed in Wiring overlay screen ScrollViewer");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.click_GrpWiring_Btn();
		// System.out.println( Setup_GroupSensorsPage.listofsensors_GrpWiringpage());

		sa.assertEquals(Setup_GroupSensorsPage.countOfgroups_GroupWiringpage(), 2, "FAIL:Group are not displayed");

		sa.assertEquals(Setup_GroupSensorsPage.islistofsensors_Empty(), false,
				"FAIL:list of sensors are not displayed");

		sa.assertEquals(Setup_GroupSensorsPage.GroupWiring_Btn_IsEnable(), true, "FAIL:Group Wiring Btn Is not Enable");

		sa.assertEquals(Setup_GroupSensorsPage.GroupsList_Btn_state(), true, "FAIL:GroupsList Btn is not visible");

		sa.assertEquals(Setup_GroupSensorsPage.imageselectionoption5_isdisplayed(), true,
				"countOf_imageselectionoptions Btn is not 5");

		sa.assertEquals(Setup_GroupSensorsPage.Print_state(), true, "Print button is not visible");

		sa.assertAll();

	}

//GS028-Verify that when clicked on any image upload option on the right pane should display the edit,delete and camera icons in a small pop-up window

	@Test(groups = {
			"Regression" }, description = "GS028-Verify that when clicked on any image upload option on the right pane should display the edit,delete and camera icons in a small pop-up window")
	public void GS028() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS028-Verify that when clicked on any image upload option on the right pane should display the edit,delete and camera icons in a small pop-up window");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.click_GrpWiring_Btn();
		Setup_GroupSensorsPage.click_1stImageBtn();

		sa.assertEquals(Setup_GroupSensorsPage.is_ChangeImageBtn_displayed(), true,
				"Fail: Change Image Btn is not displayed");

		sa.assertEquals(Setup_GroupSensorsPage.is_RemoveImageBtn_displayed(), true,
				"Fail: Remove Image Btn is not displayed");

		sa.assertEquals(Setup_GroupSensorsPage.is_ChangeImageCamera_displayed(), true,
				"Fail: Remove Image Btn is not displayed");
		sa.assertAll();
	}

//GS029-Verify that on-click of the edit icon will display the window to browse the image

	@Test(groups = {
			"Regression" }, description = "GS029-Verify that on-click of the edit icon will display the window to browse the image")
	public void GS029() throws InterruptedException, IOException, AWTException {
		extentTest = extent
				.startTest("GS029-Verify that on-click of the edit icon will display the window to browse the image");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.click_GrpWiring_Btn();
		Setup_GroupSensorsPage.click_1stImageBtn();

		Setup_GroupSensorsPage.click_EditImageBtn();
		sa.assertEquals(Setup_GroupSensorsPage.IsClosebtn_visible(), true);

		sa.assertAll();

	}

//GS030-Verify that after browsing and selecting the image for the selected group, will upload the image and display in wiring overlay screen
	@Test(groups = {
			"Regression" }, description = "GS030-Verify that after browsing and selecting the image for the selected group, will upload the image and display in wiring overlay screen")
	public void GS030() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"GS030-Verify that after browsing and selecting the image for the selected group, will upload the image and display in wiring overlay screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.click_GrpWiring_Btn();
		Setup_GroupSensorsPage.click_1stImageBtn();

		Setup_GroupSensorsPage.click_EditImageBtn();
		tu.uploadDoc("VRT_Pro.JPG");
		Setup_GroupSensorsPage.Capture_ImgButton1("Expected_VRT_Pro_Img_1stbutton");
		Setup_GroupSensorsPage.click_GroupListBtn();
		Setup_GroupSensorsPage.click_GrpWiring_Btn();

//Capture the actual Image saved to the  placeholder 1
		Setup_GroupSensorsPage.Capture_ImgButton1("Actual_VRT_Pro_Img_2ndbutton");

		boolean status_ImgCompare = tu.compareImage("Expected_VRT_Pro_Img_1stbutton", "Actual_VRT_Pro_Img_2ndbutton");

		sa.assertEquals(status_ImgCompare, false, "FAIL:  Image saved is not same as what was selected");

		sa.assertAll();
	}

//GS031-Verify that on-click of the camera icon will open the device camera

	@Test(groups = {
			"Regression" }, description = "GS031-Verify that on-click of the camera icon will open the device camera")
	public void GS031() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest("GS031-Verify that on-click of the camera icon will open the device camera");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.click_GrpWiring_Btn();
		Setup_GroupSensorsPage.click_1stImageBtn();
		Setup_GroupSensorsPage.click_CameraBtn();
		sa.assertEquals(Setup_GroupSensorsPage.is_devicecamera_displayed(), true,
				"Fail: device camera is not displayed");
		sa.assertAll();
		Setup_GroupSensorsPage.click_Cameraclose_btn();
	}

//GS032-Verify that by taking a picture in device camera will upload the image and display in wiring overlay screen
	@Test(groups = {
			"Regression" }, description = "GS032-Verify that by taking a picture in device camera will upload the image and display in wiring overlay screen")
	public void GS032() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"GS032-Verify that by taking a picture in device camera will upload the image and display in wiring overlay screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.click_GrpWiring_Btn();
		Setup_GroupSensorsPage.click_1stImageBtn();
		Setup_GroupSensorsPage.click_CameraBtn();
		Setup_GroupSensorsPage.click_CaptureBtn();
		Setup_GroupSensorsPage.click_AcceptButton();

	}

//GS033-Verify that on-click of the delete icon will delete the image on the left and right panes in wiring overlay screen
	@Test(groups = {
			"Regression" }, description = "GS033-Verify that on-click of the delete icon will delete the image on the left and right panes in wiring overlay screen")
	public void GS033() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"GS033-Verify that on-click of the delete icon will delete the image on the left and right panes in wiring overlay screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.click_GrpWiring_Btn();
		Setup_GroupSensorsPage.click_1stImageBtn();
		Setup_GroupSensorsPage.click_EditImageBtn();
		Setup_GroupSensorsPage.upload_Images("UserimageValid.jpg");
		Thread.sleep(2000);
		Setup_GroupSensorsPage.click_1stImageBtn();
		Setup_GroupSensorsPage.click_RemoveImageBtn();
	}

//GS034-Verify that when selected any existing image in wiring overlay screen can be applied for any group
//GS034 will handel manually 

//GS035-Verify that the sensors for a group can be moved and placed anywhere on the image on the left pane

//GS036-Verify the details displayed for each group in the Wiring overlay screen
	@Test(groups = {
			"Regression" }, description = "GS036-Verify the details displayed for each group in the Wiring overlay screen")
	public void GS036() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest("GS036-Verify the details displayed for each group in the Wiring overlay screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.click_GrpWiring_Btn();
		// System.out.println(Setup_GroupSensorsPage.listofsensors_GrpWiringpage());

		sa.assertEquals(Setup_GroupSensorsPage.Fetchgroupname_GWpage(0), "Temperature",
				"FAIL:group name is not available");

		sa.assertEquals(Setup_GroupSensorsPage.islistofsensors_Empty(), false,
				"FAIL:list of sensors are not displayed");
		sa.assertEquals(Setup_GroupSensorsPage.Sensor_Name(), "TMP1", "FAIL:lable name is not displaying");

		sa.assertAll();
	}

//GS037-Verify that on-click of the print icon in the wiring overlay screen will print the wiring overlay report for the current selected group
	@Test(groups = {
			"Regression" }, description = "GS037-Verify that on-click of the print icon in the wiring overlay screen will print the wiring overlay report for the current selected group")
	public void GS037() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"GS037-Verify that on-click of the print icon in the wiring overlay screen will print the wiring overlay report for the current selected group");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.click_GrpWiring_Btn();
		Setup_GroupSensorsPage.Click_Print();

		Setup_GroupSensorsPage.click_PDFpopup_OkBtn();
	}

//GS038-Verify the details displayed in Wiring overlay reports

//GS039-Verify that more than 5mb size image cannot be uploaded and validation message is displayed
	@Test(groups = {
			"Regression" }, description = "GS039-Verify that more than 5mb size image cannot be uploaded and validation message is displayed")
	public void GS039() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"GS039-Verify that more than 5mb size image cannot be uploaded and validation message is displayed");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.click_GrpWiring_Btn();
		Setup_GroupSensorsPage.click_1stImageBtn();
		Setup_GroupSensorsPage.click_EditImageBtn();
		tu.uploadDoc("UserimageInValid.jpg");

		String ExpAlrtMsg = "Select image file with size less than 5 mb";
		String ActAlertMsg = Setup_GroupSensorsPage.AlertMsg();

		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL:Alert message should be displayed as-Select image file with size less than 5 mb ");
		sa.assertAll();
	}
	// GS040-Verify that the uploaded images in wiring overlay are reflected in
	// setup reports
	// GS041-Verify that the uploaded images in wiring overlay are reflected in Qual
	// live screen

	// GS042-Verify that when clicked on the square icon in wiring overlay screen
	// will display the group sensors screen

	@Test(groups = {
			"Regression" }, description = "GS042-Verify that when clicked on the square icon in wiring overlay screen will display the group sensors screen")
	public void GS042() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"GS042-Verify that when clicked on the square icon in wiring overlay screen will display the group sensors screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.click_GrpWiring_Btn();
		Setup_GroupSensorsPage.click_GroupListBtn();
		sa.assertEquals(Setup_GroupSensorsPage.NewGroup_Btn_state(), true, "FAIL: NewGroup_Btn  is not displaying ");
		sa.assertAll();
	}

//GS043-Verify that move icon is displayed in group sensors screen only when there are minimum 2 groups
	@Test(groups = {
			"Regression" }, description = "GS043-Verify that move icon is displayed in group sensors screen only when there are minimum 2 groups")
	public void GS043() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS043-Verify that move icon is displayed in group sensors screen only when there are minimum 2 groups");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		sa.assertEquals(Setup_GroupSensorsPage.IsMoveBtn_Visible(), true, "FAIL: Move Btn  is not available");
		sa.assertAll();
		Setup_GroupSensorsPage.Selectgroup(0);
		Setup_GroupSensorsPage.DeleteGroup_Yes();
		sa.assertEquals(Setup_GroupSensorsPage.IsMoveBtn_Visible(), true, "FAIL: Move Btn  is not available");

	}

	// GS043_1-Verify that on-click of the move icon will display the move sensors
	// window

	@Test(groups = {
			"Regression" }, description = "GS043_1-Verify that on-click of the move icon will display the move sensors window")
	public void GS043_1() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("GS043_1-Verify that on-click of the move icon will display the move sensors window");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.Click_MoveIcon();

		sa.assertEquals(Setup_GroupSensorsPage.IsMovSensors_Title_Visible(), true,
				"FAIL: Move sensor page title   is not available");
		sa.assertAll();

	}

//GS044-Verify the details displayed in Move sensors window

	@Test(groups = { "Regression" }, description = "GS044-Verify the details displayed in Move sensors window")
	public void GS044() throws InterruptedException, IOException {
		extentTest = extent.startTest("GS044-Verify the details displayed in Move sensors window");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.Click_MoveIcon();

		sa.assertEquals(Setup_GroupSensorsPage.IsMovSensors_Title_Visible(), true,
				"FAIL: Move sensor page title   is not available");
		sa.assertEquals(Setup_GroupSensorsPage.IsFromdropdown_Visible(), true,
				"FAIL: Move sensor page title   is not available");
		sa.assertEquals(Setup_GroupSensorsPage.IsTodropdown_Visible(), true,
				"FAIL: Move sensor page title   is not available");
		sa.assertEquals(Setup_GroupSensorsPage.Is_MoveBtn_Visible(), true,
				"FAIL: Move sensor page title   is not available");

		sa.assertEquals(Setup_GroupSensorsPage.Is_CloseBtn_Visible(), true,
				"FAIL: Move sensor page title   is not available");

		sa.assertAll();

	}

	// GS045-Verify that when clicked on From drop down in move sensors screen will
	// display the list of groups
	@Test(groups = {
			"Regression" }, description = "GS045-Verify that when clicked on From drop down in move sensors screen will display the list of groups")
	public void GS045() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS045-Verify that when clicked on From drop down in move sensors screen will display the list of groups");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.Click_MoveIcon();
		Setup_GroupSensorsPage.click_On_From_dropdown();

		sa.assertEquals(Setup_GroupSensorsPage.Countofitem_from_dd(), 2,
				"FAIL: Move sensor page title   is not available");
		sa.assertEquals(Setup_GroupSensorsPage.fetchgroupname_fromDropdown(0), "Temperature",
				"FAIL: Temperature   is not available in from dropdown list box");
		sa.assertEquals(Setup_GroupSensorsPage.fetchgroupname_fromDropdown(1), "Humidity",
				"FAIL: Humidity group  is not available in From dropdown list box ");

		sa.assertAll();

	}
	// GS046-Verify that when clicked on To drop down in move sensors screen will
	// display the list of groups

	@Test(groups = {
			"Regression" }, description = "GS046-Verify that when clicked on To drop down in move sensors screen will display the list of groups")
	public void GS046() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS046-Verify that when clicked on To drop down in move sensors screen will display the list of groups");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.Click_MoveIcon();
		Setup_GroupSensorsPage.click_On_To_dropdown();

		sa.assertEquals(Setup_GroupSensorsPage.Countofitem_to_dd(), 2,
				"FAIL: Move sensor page title   is not available");
		sa.assertEquals(Setup_GroupSensorsPage.fetchgroupname_Todropdown(0), "Temperature",
				"FAIL: Temperature   is not available in To dropdown list box");

		sa.assertEquals(Setup_GroupSensorsPage.fetchgroupname_Todropdown(1), "Humidity",
				"FAIL: Temperature   is not available in To dropdown list box");

		sa.assertAll();
	}

	// GS047-Verify that when selected any group in the From drop down will display
	// the corresponding sensors of that group on the left pane
	@Test(groups = {
			"Regression" }, description = "GS047-Verify that when selected any group in the From drop down will display the corresponding sensors of that group on the left pane")
	public void GS047() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS047-Verify that when selected any group in the From drop down will display the corresponding sensors of that group on the left pane");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		String SL = Setup_SensorConfigPage.get_SensorLabel_text();
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.Click_MoveIcon();
		Setup_GroupSensorsPage.click_On_From_dropdown();
		// System.out.println(Setup_GroupSensorsPage.get_sensortext_FromDD(0));

		sa.assertEquals(SL, Setup_GroupSensorsPage.get_sensortext_FromDD(0),
				"FAIL: The corresponding sensors of that group on the left pane is not displaying");

		sa.assertAll();

	}

	// GS048-Verify that when selected any group in the To drop down will display
	// the corresponding sensors of that group on the right pane

	@Test(groups = {
			"Regression" }, description = "GS048-Verify that when selected any group in the To drop down will display the corresponding sensors of that group on the right pane")
	public void GS048() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS048-Verify that when selected any group in the To drop down will display the corresponding sensors of that group on the right pane");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMPR");
		String SenstTxt = Setup_SensorConfigPage.get_SensorLabel_text();

		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.Click_MoveIcon();
		Setup_GroupSensorsPage.click_On_To_dropdown();

		// System.out.println(Setup_GroupSensorsPage.get_sensortext_ToDD(0));
		sa.assertEquals(SenstTxt, Setup_GroupSensorsPage.get_sensortext_ToDD(0),
				"FAIL: The corresponding sensors of that group on the right pane is not displaying");

		sa.assertAll();

	}

//GS049-Verify that the sensors on the left pane can be selectable
	@Test(groups = { "Regression" }, description = "GS049-Verify that the sensors on the left pane can be selectable")
	public void GS049() throws InterruptedException, IOException {
		extentTest = extent.startTest("GS049-Verify that the sensors on the left pane can be selectable");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.Click_MoveIcon();

		sa.assertEquals(Setup_GroupSensorsPage.is_sensor_selected_leftpane(0), true,
				"Fail: 1st sensor is not selected");

		sa.assertAll();
	}

	// GS050-Verify that on-click of the move button will move the sensors from the
	// _From_ group to the _To_ group

	@Test(groups = {
			"Regression" }, description = "GS050-Verify that on-click of the move button will move the sensors from the _From_ group to the _To_ group")
	public void GS050() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS050-Verify that on-click of the move button will move the sensors from the _From_ group to the _To_ group");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.clickOn_NewGroupButton();
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		Setup_GroupSensorsPage.Edit_GrpTitle_DontClickSave("Tempnew");
		Setup_GroupSensorsPage.clickon_Sensors(1);
		Setup_GroupSensorsPage.click_SaveBtn();
		// Setup_GroupSensorsPage.Click_6thSensor();

		Setup_GroupSensorsPage.Click_MoveIcon();
		// Setup_GroupSensorsPage.click_On_From_dropdown();
		Setup_GroupSensorsPage.select_options_fromDDL(1);
		Setup_GroupSensorsPage.ClickOnsensor_leftpane_Movewindow(2);
		Setup_GroupSensorsPage.click_MoveBtn();
		sa.assertEquals(Setup_GroupSensorsPage.get_sensrCount_ToDD(), 3,
				"Fail:The sensors from the _From_ group to the _To_ group has not moved");
		sa.assertAll();
	}

	// GS051-Verify that when same groups are selected in From and To fields,
	// sensors cannot be moved and validation message is displayed

	@Test(groups = {
			"Regression" }, description = "GS051-Verify that when same groups are selected in From and To fields, sensors cannot be moved and validation message is displayed")
	public void GS051() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS051-Verify that when same groups are selected in From and To fields, sensors cannot be moved and validation message is displayed");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.Click_MoveIcon();
		Setup_GroupSensorsPage.click_MoveBtn();
		String ActualMSG = Setup_GroupSensorsPage.Alertmsg_txt();
		String ExpectedMSG = "Source group and Destination group cannot be same.";
		sa.assertEquals(ActualMSG, ExpectedMSG, "Fail: alert message content is not matching");
		sa.assertAll();
	}

	// GS052-Verify that when different groups of same sensor types are selected in
	// From and To fields, sensors are moved successfully
	// This above test case is similar to GS050.

//GS053-Verify that when groups of different sensor types are selected in From and To fields, sensors are not moved and a validation message is displayed
	@Test(groups = {
			"Regression" }, description = "GS053-Verify that when groups of different sensor types are selected in From and To fields, sensors are not moved and a validation message is displayed")
	public void GS053() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS053-Verify that when groups of different sensor types are selected in From and To fields, sensors are not moved and a validation message is displayed");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.Click_MoveIcon();
		Setup_GroupSensorsPage.select_options_fromDDL(1);

		Setup_GroupSensorsPage.ClickOnsensor_leftpane_Movewindow(0);
		Setup_GroupSensorsPage.click_MoveBtn();

		String ActualMSG = Setup_GroupSensorsPage.Alertmsg_txt();
		String ExpectedMSG = "Cannot move sensor(s). A group can only contain homogeneous sensor type.";
		sa.assertEquals(ActualMSG, ExpectedMSG, "Fail: alert message content is not matching");
		sa.assertAll();
	}

	// GS054-Verify that when trying to move the sensors that already exists in
	// destination group, displays a validation message that sensors already exists

	@Test(groups = {
			"Regression" }, description = "GS054-Verify that when trying to move the sensors that already exists in destination group, displays a validation message that sensors already exists\r\n"
					+ "")
	public void GS054() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS054-Verify that when trying to move the sensors that already exists in destination group, displays a validation message that sensors already exists");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.clickOn_NewGroupButton();
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		Setup_GroupSensorsPage.Edit_GrpTitle_DontClickSave("Temp");
		Setup_GroupSensorsPage.clickon_Sensors(1);
		Setup_GroupSensorsPage.click_SaveBtn();

		Setup_GroupSensorsPage.Click_MoveIcon();
		Setup_GroupSensorsPage.select_options_fromDDL(1);
		Setup_GroupSensorsPage.ClickOnsensor_leftpane_Movewindow(0);
		Setup_GroupSensorsPage.click_MoveBtn();

		String ActualMSG = Setup_GroupSensorsPage.Alertmsg_txt();
		String ExpectedMSG = "The sensor(s) you are trying to move is already present in the group  Temp";
		sa.assertEquals(ActualMSG, ExpectedMSG,
				"Fail: Validation message is not displaying when user trying to move a sensor which is already present in the group");
		sa.assertAll();

	}

//GS055-Verify that more than 50 sensors cannot be moved to the destination group
	@Test(groups = {
			"Regression" }, description = "GS055-Verify that more than 50 sensors cannot be moved to the destination group")
	public void GS055() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("GS055-Verify that more than 50 sensors cannot be moved to the destination group");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("55");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("51");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();

		Setup_GroupSensorsPage.Click_MoveIcon();
		Setup_GroupSensorsPage.select_options_fromDDL(1);
		Setup_GroupSensorsPage.ClickOnsensor_leftpane_Movewindow(0);
		Setup_GroupSensorsPage.click_MoveBtn();

		String ActualMSG = Setup_GroupSensorsPage.Alertmsg_txt();
		String ExpectedMSG = "Destination group already have maximum(50) sensors";
		sa.assertEquals(ActualMSG, ExpectedMSG,
				"Fail: more than 50 sensors is able to be moved to the destination group");
		sa.assertAll();
	}

//GS056-Verify that on-click of the close button in move sensors screen will close the window and navigate to group sensors screen

	@Test(groups = {
			"Regression" }, description = "GS056-Verify that on-click of the close button in move sensors screen will close the window and navigate to group sensors screen")
	public void GS056() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS056-Verify that on-click of the close button in move sensors screen will close the window and navigate to group sensors screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("55");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("53");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.Click_MoveIcon();
		Setup_GroupSensorsPage.click_CloseBtn();

		sa.assertEquals(Setup_GroupSensorsPage.is_MoveIconVisible(), true,
				"FAIL:  move icon  is not displaying in Group sensor page ");
		sa.assertAll();
	}

	// GS057-Verify that on-click of the close icon on the top right in move sensors
	// screen will close the window and navigate to group sensors screen

	@Test(groups = {
			"Regression" }, description = "GS057-Verify that on-click of the close icon on the top right in move sensors screen will close the window and navigate to group sensors screen")
	public void GS057() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS057-Verify that on-click of the close icon on the top right in move sensors screen will close the window and navigate to group sensors screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("55");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("53");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();

		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.Click_MoveIcon();
		Setup_GroupSensorsPage.click_CloseIcon();

		sa.assertEquals(Setup_GroupSensorsPage.is_MoveIconVisible(), true,
				"FAIL:  move icon  is not displaying in Group sensor page ");
		sa.assertAll();
	}

//GS058-Verify that on-click of the delete icon for any group will delete the group successfully

	@Test(groups = {
			"Regression" }, description = "GS058-Verify that on-click of the delete icon for any group will delete the group successfully")
	public void GS058() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS058-Verify that on-click of the delete icon for any group will delete the group successfully");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.Selectgroup(1);
		Setup_GroupSensorsPage.click_DeleteGroup_Btn();
		Setup_GroupSensorsPage.select_alertOption("Yes");
		sa.assertEquals(Setup_GroupSensorsPage.Countof_group(), 1, "Fail deleted group is displaying");
		sa.assertAll();
	}
	// GS059 and GS060 will handel manually as these tcs are related to live n
	// report generation

	// GS061-Verify that the edit group header section at the bottom of the screen
	// can be expanded and collapsed
	@Test(groups = {
			"Regression" }, description = "GS061-Verify that the edit group header section at the bottom of the screen can be expanded and collapsed")
	public void GS061() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS061-Verify that the edit group header section at the bottom of the screen can be expanded and collapsed");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_ExpanderIcon();

		sa.assertEquals(Setup_GroupSensorsPage.Is_AssetIDVisible(), true, "Fail: AssetID is not displaying");
		Setup_GroupSensorsPage.click_ExpanderIcon();
		sa.assertEquals(Setup_GroupSensorsPage.Is_AssetIDVisible(), false, "Fail: AssetID is not displaying");

		sa.assertAll();
	}

	// GS062-Verify that Asset ID,SOP Protocol, Description and Comments are
	// displayed in the Edit group header section

	@Test(groups = {
			"Regression" }, description = "GS062-Verify that Asset ID,SOP Protocol, Description and Comments are displayed in the Edit group header section")
	public void GS062() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS062-Verify that Asset ID,SOP Protocol, Description and Comments are displayed in the Edit group header section");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_ExpanderIcon();

		sa.assertEquals(Setup_GroupSensorsPage.Is_AssetIDVisible(), true, "Fail: AssetID is not displaying");
		sa.assertEquals(Setup_GroupSensorsPage.Is_Description_Visible(), true, "Fail: AssetID is not displaying");
		sa.assertEquals(Setup_GroupSensorsPage.Is_SOP_Protocol_Visible(), true, "Fail: AssetID is not displaying");
		sa.assertEquals(Setup_GroupSensorsPage.Is_Comments_Visible(), true, "Fail: AssetID is not displaying");
		sa.assertAll();

	}

	// GS063-Verify that the Asset ID and SOP protocol values are pre populated from
	// Define setup screen
	@Test(groups = {
			"Regression" }, description = "GS063-Verify that the Asset ID and SOP protocol values are pre populated from Define setup screen")
	public void GS063() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS063-Verify that the Asset ID and SOP protocol values are pre populated from Define setup screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("HMD");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.click_ExpanderIcon();
		Thread.sleep(1000);
		sa.assertEquals(Setup_GroupSensorsPage.getTxt_from_AssetID(), assetid,
				"Fail:  Asset ID value is not pre populated from Define setup screen");
		sa.assertEquals(Setup_GroupSensorsPage.getTxt_from_SOP(), "10",
				"Fail: SOP protocol value is not pre populated from Define setup screen");

		sa.assertAll();
	}

	// GS064-Verify that all the fields under edit group header section are editable
	// when the current selected group is in edit mode

	@Test(groups = {
			"Regression" }, description = "GS064-Verify that the Asset ID and SOP protocol values are pre populated from Define setup screen")
	public void GS064() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS063-Verify that the Asset ID and SOP protocol values are pre populated from Define setup screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_ExpanderIcon();
		sa.assertEquals(Setup_GroupSensorsPage.IsAssetIDReadonlymode_ON(), true, "Fail: Landed to wrong page");
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.click_Dflt_TempGrp();
		Setup_GroupSensorsPage.Selectgroup(0);
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		Setup_GroupSensorsPage.click_ExpanderIcon();
		sa.assertEquals(Setup_GroupSensorsPage.IsAssetIDReadonlymode_Off(), false, "Fail: Landed to wrong page");
		sa.assertAll();
	}

	// GS065-Verify that when edited the details in Edit group header section and
	// clicked on save button will save changes successfully

	@Test(groups = {
			"Regression" }, description = "GS065-Verify that when edited the details in Edit group header section and clicked on save button will save changes successfully")
	public void GS065() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS065-Verify that when edited the details in Edit group header section and clicked on save button will save changes successfully");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		// Setup_GroupSensorsPage.click_Dflt_TempGrp();
		Setup_GroupSensorsPage.Selectgroup(0);
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		Setup_GroupSensorsPage.click_ExpanderIcon();
		Setup_GroupSensorsPage.Enter_Valuesinto_AssetID("AID");
		Setup_GroupSensorsPage.click_SaveBtn();

		sa.assertEquals(Setup_GroupSensorsPage.getTxt_from_AssetID(), "AID", "Fail:  Asset ID value is not editable");

		sa.assertAll();
	}

	// GS066 to GS072 will handle manually as these are related to reports

	// GS073-Verify that on-click of the Sensors configuration navigator on the top
	// left will navigate to Sensor configurations screen

	@Test(groups = {
			"Regression" }, description = "GS073-Verify that on-click of the Sensors configuration navigator on the top left will navigate to Sensor configurations screen")
	public void GS073() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS073-Verify that on-click of the Sensors configuration navigator on the top left will navigate to Sensor configurations screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_SensorConfigPage = Setup_GroupSensorsPage.Click_SensorConfigTab();
		sa.assertEquals(Setup_SensorConfigPage.sensorConfigPage_state(), true, "Fail: Landed to wrong page");
		sa.assertAll();
	}

	// GS074-Verify that on-click of the Calculations navigator on the top right
	// will navigate to Calculations screen

	@Test(groups = {
			"Regression" }, description = "GS074-Verify that on-click of the Calculations navigator on the top right will navigate to Calculations screen")
	public void GS074() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS074-Verify that on-click of the Calculations navigator on the top right will navigate to Calculations screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
		sa.assertEquals(Setup_CalculationsPage.CalculationPage_state(), true, "Fail: Landed to wrong page");
		sa.assertAll();
	}

	// GS075-Verify that on-click of the back icon on the screen will navigate to
	// Asset details screen upon confirmation
	@Test(groups = {
			"Regression" }, description = "GS075-Verify that on-click of the back icon on the screen will navigate to Asset details screen upon confirmation")
	public void GS075() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS075-Verify that on-click of the back icon on the screen will navigate to Asset details screen upon confirmation");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		assetDetailsPage = Setup_GroupSensorsPage.Click_BackBtn();
		sa.assertEquals(assetDetailsPage.assetDetailPageTitle_Visible(), true, "Fail: Landed to wrong page");
		sa.assertAll();
	}
	// GS076-Verify the bottom menu options displayed in Group sensors screen

	@Test(groups = {
			"Regression" }, description = "GS076-Verify the bottom menu options displayed in Group sensors screen")
	public void SC106() throws InterruptedException, IOException {
		extentTest = extent.startTest("GS076-Verify the bottom menu options displayed in Group sensors screen");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
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

	// GS077-Verify that on-click of home btn in bottom menu options is navigated to
	// main hub page
	@Test(description = "GS077-Verify that on-click of home btn in bottom menu options is navigated to main hub page")
	public void SC107() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS077-Verify that on-click of home btn in bottom menu options is navigated to main hub page");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		MainHubPage = tu.Click_Home_Icon_AppBar();

		sa.assertEquals(MainHubPage.Is_mainHubPageTitle_Visible(), true,
				"FAIL: Clicking Home icon/button in bottom app bar do not redirect to Mains Hub page");
		sa.assertAll();
	}

	// GS078-Verify that on-click of help btn in bottom menu options displays
	// information about the Group sensors screen
	@Test(description = "GS078-Verify that on-click of help btn in bottom menu options displays information about the Group sensors screen")
	public void SC108() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"GS078-Verify that on-click of help btn in bottom menu options displays information about the Group sensors screen");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		tu.Click_Help_Icon_AppBar();
		sa.assertEquals(tu.get__HelpMenu_HdrText(), "Group Sensors", "FAIL: Clicking Help icon/button in bottom app bar"

				+ "do not display the Group Sensors Help context window");
		sa.assertAll();
	}

	// GS079-Verify that on-click of windows help btn in bottom menu options
	// generates a PDF with information

	// GS080-Verify that on-click of About btn in bottom menu options displays the
	// software version and the console IP address

	@Test(dataProvider = "SET033", dataProviderClass = setupCreationUtility.class, 
			description = "Verify that on-click of About btn in bottom menu options displays "
			+ "the software version and the console IP address")
	public void GS080(String SWVer) throws InterruptedException, IOException {
		extentTest = extent.startTest("GS080-Verify that on-click of About btn in bottom menu options displays "
				+ "the software version and the console IP address");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
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
}
