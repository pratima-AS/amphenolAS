package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;

import org.testng.Assert;
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
import com.vrt.pages.Setup_SensorDescriptionPage;

import com.vrt.pages.Setup_CalculationsPage;
import com.vrt.pages.Setup_QualParamPage;

public class setup_QualParametersTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>SetupTestData.xlsx sheet for test data i/p

	public setup_QualParametersTest() throws IOException {
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
	Setup_QualParamPage Setup_QualParamPage;

	// Before All the tests are conducted
	@BeforeTest
	public void PreSetup() throws InterruptedException, IOException, AWTException {

		extent = new ExtentReports(
				System.getProperty("user.dir") + "/test-output/ER_" + "setup_QualParametersTest" + ".html", true);
		extent.addSystemInfo("TestSuiteName", "setup_CalculationTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name2"));
		System.out.println("Setup_Calculation Test is  in Progress..");

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

		// Method to Create Very 1st User with All privilege UserManagementPage =
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
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath", "AAS", "Hyderabad", "VRT-RF", "2",
				"cu", "11/20/2019", "5", "Weeks", "1st Asset Creation");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		AppClose();
		Thread.sleep(500);

	}

	// After All the tests are conducted
	@AfterTest
	public void endReport_releaseMomory() {
		extent.flush();
		extent.close();
		// Setup_CalculationsPage.resetWebElements();
		// System.out.println("Reset Webelement memory released");
		System.out.println("Setup_Calculations Test Completed.");
	}

	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName("test");
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.enter_defineSetupPage_SensorCount("300");
		defineSetupPage.enter_defineSetupPage_SOP("10");
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
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
		Setup_SensorConfigPage.Enter_SensorLabel("PSR");
		Setup_SensorConfigPage.Click_assignBtn();
		// Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_withAlert();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_NxtBtn();
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();

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

	// QP001-Verify the details displayed in Qualification Parameters screen

	@Test(groups = {
			"Regression" }, description = "QP001-Verify the details displayed in Qualification Parameters screen")
	public void CAL001() throws InterruptedException, IOException {
		extentTest = extent.startTest("QP001-Verify the details displayed in Qualification Parameters screen");
		SoftAssert sa = new SoftAssert();

		System.out.println(Setup_QualParamPage.get_QualParamsPage_titletext());

		sa.assertEquals(Setup_QualParamPage.get_QualParamsPage_titletext(), "Qualification Parameters",
				"FAIL: Qualification Parameters is not displaying");

		sa.assertEquals(Setup_QualParamPage.get_Setup_titletext(), "test", "FAIL: Set up title is not available");

		sa.assertEquals(Setup_QualParamPage.get_SubHeader_titletext(), "Define The Qualification Details",
				"FAIL: sub header is not available");

		sa.assertEquals(Setup_QualParamPage.start_Stopcond_state(), true,
				"FAIL: start_Stopcond state is not available");

		sa.assertEquals(Setup_QualParamPage.Data_Storage_state(), true, "Data_Storage_state Btn is not available");
		sa.assertEquals(Setup_QualParamPage.RF_Transmit_state(), true, "FAIL: RF_Transmit_state  is not available");

		sa.assertEquals(Setup_QualParamPage.NxtBtn_state(), true, "FAIL: NxtBtn  is not available");

		sa.assertEquals(Setup_QualParamPage.PreviousButton_state(), true, "FAIL: PreviousButton is not available");

		sa.assertAll();
	}

	// QP002-Verify that the Start and Stop Qual drop downs are displayed under
	// _Start Stop Conditions_ section

	@Test(groups = {
			"Regression" }, description = "QP002-Verify that the Start and Stop Qual drop downs are displayed under _Start Stop Conditions_ section")
	public void QP002() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"QP002-Verify that the Start and Stop Qual drop downs are displayed under _Start Stop Conditions_ section");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(Setup_QualParamPage.QStart_text_state(), true,
				"FAIL: Start Qualification text is not displaying");

		sa.assertEquals(Setup_QualParamPage.QStop_text_state(), "Qualification Parameters",
				"FAIL: Stop Qualification text is not displaying");
	}

	// QP002.1-Verify if the ‘Start Qualification’ drop down option displays
	// ‘Manual’ as the default selection

	@Test(groups = {
			"Regression" }, description = "QP002.1-Verify if the ‘Start Qualification’ drop down option displays ‘Manual’ as the default selection")
	public void QP002_1() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"QP002.1-Verify if the ‘Start Qualification’ drop down option displays ‘Manual’ as the default selection");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(Setup_QualParamPage.get_Txt_QStartDrpDwn(), "Manual",
				"FAIL: In Start Qualification drop down 'Manual' is not displaying as the default selection");
		sa.assertAll();
	}

	// QP003-Verify that _Manual_ and _Time of the day_ options are displayed in
	// Start Qualification drop down

	@Test(groups = {
			"Regression" }, description = "QP003-Verify that _Manual_ and _Time of the day_ options are displayed in Start Qualification drop down")
	public void QP003() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"QP003-Verify that _Manual_ and _Time of the day_ options are displayed in Start Qualification drop down");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		// System.out.println(Setup_QualParamPage.Fetch_Firstoption_QStart_DrpDwn());

		sa.assertEquals(Setup_QualParamPage.Fetch_Firstoption_QStart_DrpDwn(), "Manual",
				"FAIL: 'Manual' option is not available in Start Qualification drop down ");

		sa.assertEquals(Setup_QualParamPage.Fetch_Secondoption_QStart_DrpDwn(), "Time Of Day",
				"FAIL: 'Manual' option is not available in Start Qualification drop down ");
		sa.assertAll();
	}

	// QP004-Verify that on-click of the _Manual_ option in the list, will display
	// the Start Qual as Manual

	@Test(groups = {
			"Regression" }, description = "QP004-Verify that on-click of the _Manual_ option in the list, will display the Start Qual as Manual")
	public void QP004() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"QP004-Verify that on-click of the _Manual_ option in the list, will display the Start Qual as Manual");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_Manual_QStart_DrpDwn();
		sa.assertEquals(Setup_QualParamPage.get_Txt_QStartDrpDwn(), "Manual",
				"FAIL: In Start Qualification drop down 'Manual' is not displaying as the default selection");
		sa.assertAll();
	}

	// QP005-Verify that on-click of the _Time of the Day_ option in the list, will
	// display the date and time fields for Start Qual

	@Test(groups = {
			"Regression" }, description = "QP005-Verify that on-click of the _Time of the Day_ option in the list, will display the date and time fields for Start Qual")
	public void QP005() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"QP005-Verify that on-click of the _Time of the Day_ option in the list, will display the date and time fields for Start Qual ");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();
		sa.assertEquals(Setup_QualParamPage.get_Txt_QStartDrpDwn(), "Time of the Day",
				"FAIL: In Start Qualification drop down 'Manual' is not displaying as the default selection");

		sa.assertEquals(Setup_QualParamPage.Is_DateField_Visible(), true, "FAIL:Date field is not visible");

		sa.assertAll();
	}

	// QP006-Verify that the Date field for Start Qual displays the current date

	@Test(groups = {
			"Regression" }, description = "QP006-Verify that the Date field for Start Qual displays the current date")
	public void QP006() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest("QP006-Verify that the Date field for Start Qual displays the current date");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();

		String crntDate = tu.get_CurrentDate_inCertainFormat("dd-MMM-yyyy");
		System.out.println(crntDate);
		sa.assertEquals(Setup_QualParamPage.fetch_Date(), crntDate, "FAIL: Date is not matching ");

		sa.assertAll();
	}

	// QP007-Verify that the Date selector is available for start qual-time of the
	// day option

	@Test(groups = {
			"Regression" }, description = "QP007-Verify that the Date selector is available for start qual-time of the day option")
	public void QP007() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("QP007-Verify that the Date selector is available for start qual-time of the day option");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();
		sa.assertEquals(Setup_QualParamPage.Is_SelectDate_Visible(), true, "FAIL: Date selector is not available  ");

		sa.assertAll();

	}

	// QP007.1-Verify that on-click of the date,month,year in the date selector will
	// display the values accordingly for time of the day

	// QP008-Verify that past dates are not selectable in the date selector for time
	// of the day

	// QP009-Verify that the time field has three text boxes for Hours,Minutes and
	// Seconds under time of the day

	@Test(groups = {
			"Regression" }, description = "QP009-Verify that the time field has three text boxes for Hours,Minutes and Seconds under time of the day")
	public void QP009() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP009-Verify that the time field has three text boxes for Hours,Minutes and Seconds under time of the day");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();
		sa.assertEquals(Setup_QualParamPage.Is_Hours_visible(), true, "FAIL: Hours is not available");

		sa.assertEquals(Setup_QualParamPage.Is_Min_visible(), true, "FAIL: minute is not available");

		sa.assertEquals(Setup_QualParamPage.Is_Sec_visible(), true, "FAIL: sec is not available");

		sa.assertAll();

	}

	// QP010-Verify that the default value for time is 00 in all the three text
	// boxes for time of the day

	@Test(groups = {
			"Regression" }, description = "QP010-Verify that the default value for time is 00 in all the three text boxes for time of the day")
	public void QP010() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP010-Verify that the default value for time is 00 in all the three text boxes for time of the day");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();

		sa.assertEquals(Setup_QualParamPage.fetch_Hour(), "00", "FAIL: Hours is not available");

		sa.assertAll();
	}

}
