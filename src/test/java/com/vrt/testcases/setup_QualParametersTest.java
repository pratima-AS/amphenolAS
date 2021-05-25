package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
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
import com.vrt.utility.assetCreationUtility;
import com.vrt.utility.setupCreationUtility;
import com.vrt.utility.userManagementUtility;
import com.vrt.pages.Setup_SensorDescriptionPage;

import com.vrt.pages.Setup_CalculationsPage;
import com.vrt.pages.Setup_QualParamPage;
import com.vrt.pages.Setup_ReviewPage;

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
	Setup_ReviewPage Setup_ReviewPage;

	// Before All the tests are conducted
	//@BeforeTest
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, AWTException, ParseException {

		extent = new ExtentReports(
				System.getProperty("user.dir") + "/test-output/ER_" + "setup_QualParametersTest" + ".html", true);
		extent.addSystemInfo("TestSuiteName", "setup_QualParametersTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name2"));
		System.out.println("setup_QualParametersTest Test is  in Progress..");

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
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		System.out.println(crntDate);
		assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath", "aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "2", "Weeks", "1st Asset Creation");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		AppClose();
		Thread.sleep(500);

	}

	// After All the tests are conducted
	//@AfterTest
	@AfterClass
	public void endReport_releaseMomory() {
		extent.flush();
		extent.close();
		// Setup_CalculationsPage.resetWebElements();
		// System.out.println("Reset Webelement memory released");
		System.out.println("setup_QualParametersTest Test Completed.");
	}

	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.Click_AssetTile2();
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
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
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
	public void QP001() throws InterruptedException, IOException {
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
		sa.assertEquals(Setup_QualParamPage.get_Txt_QStartDrpDwn(), "Time Of Day",
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

		sa.assertEquals(Setup_QualParamPage.fetch_Hour(), "00", "FAIL: default value for hour time is not 00");
		sa.assertEquals(Setup_QualParamPage.fetch_Min(), "00", "FAIL: default value for minute time is not 00");
		sa.assertEquals(Setup_QualParamPage.fetch_Sec(), "00", "FAIL: default value for second time is not 00");

		sa.assertAll();
	}

	// QP010.1-Verify that max 2 characters are allowed in HH MM SS time fields for
	// time of the day
	@Test(groups = {
			"Regression" }, description = "QP010_1-Verify that max 2 characters are allowed in HH MM SS time fields for time of the day")
	public void QP010_1() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP010_1-Verify that max 2 characters are allowed in HH MM SS time fields for time of the day");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();
		// Enter more than 2 chars
		Setup_QualParamPage.enter_Hour("1234");
		String HrTxt = Setup_QualParamPage.fetch_Hour();

		sa.assertEquals(HrTxt.length(), 2, "FAIL:  max 2 characters are not allowed in HH");

		Setup_QualParamPage.enter_Min("1234");
		String MnTxt = Setup_QualParamPage.fetch_Min();

		sa.assertEquals(MnTxt.length(), 2, "FAIL:  max 2 characters are not allowed in MM");

		Setup_QualParamPage.enter_Sec("1234");
		String ScTxt = Setup_QualParamPage.fetch_Hour();

		sa.assertEquals(ScTxt.length(), 2, "FAIL:  max 2 characters are not allowed in SS");

		sa.assertAll();

	}
	// QP011-Verify that only numbers are accepted in the time field in HH MM SS
	// text boxes for time of the day

	@Test(groups = {
			"Regression" }, dataProvider = "QP011", dataProviderClass = setupCreationUtility.class, description = "QP011-Verify that only numbers are accepted in the time field in HH MM SS text boxes for time of the day")
	public void QP011(String HH, String MM, String SS) throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP011-Verify that only numbers are accepted in the time field in HH MM SS text boxes for time of the day");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();
		Setup_QualParamPage.enter_Hour(HH);
		Setup_QualParamPage.enter_Min(MM);
		Setup_QualParamPage.enter_Sec(SS);

		// Setup_QualParamPage.click_Min();
		String HrTxt = Setup_QualParamPage.fetch_Hour();
		String MnTxt = Setup_QualParamPage.fetch_Min();
		String ScTxt = Setup_QualParamPage.fetch_Sec();

		sa.assertEquals(HrTxt, HH, "FAIL:Entered number is not reflecting in HH textfield");
		sa.assertEquals(MnTxt, MM, "FAIL: Entered number is not reflecting in MM textfield");
		sa.assertEquals(ScTxt, SS, "FAIL: Entered number is not reflecting in SS textfield");

		sa.assertAll();

	}

	// QP012-Verify that 24hr format is accepted in the time HH field for time of
	// the day option
	@Test(groups = {
			"Regression" }, dataProvider = "QP012", dataProviderClass = setupCreationUtility.class, description = "QP012-Verify that 24hr format is accepted in the time HH field for time of the day option")
	public void QP012(String HH) throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("QP012-Verify that 24hr format is accepted in the time HH field for time of the day option");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();

		Setup_QualParamPage.enter_Hour(HH);
		Setup_QualParamPage.click_Min();
		String HrTxt = Setup_QualParamPage.fetch_Hour();

		sa.assertEquals(HrTxt.length(), 2, "FAIL: Incorrect format allowed in HH");
		sa.assertAll();
	}

	// QP013-Verify that min 00 and max 23 is accepted in HH field for time of the
	// day

	@Test(groups = {
			"Regression" }, description = "QP013-Verify that min 00 and max 23 is accepted in HH field for time of the day")
	public void QP013() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("QP013-Verify that min 00 and max 23 is accepted in HH field for time of the day");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();
		// Enter less than min val
		Setup_QualParamPage.enter_Hour("0");
		Setup_QualParamPage.click_Min();

		String HrTxt1 = Setup_QualParamPage.fetch_Hour();
		sa.assertEquals(HrTxt1, "00", "FAIL:  minimum val is not  00 in HH");

		// Enter more than max val
		Setup_QualParamPage.enter_Hour("24");
		Setup_QualParamPage.click_Min();
		String HrTxt2 = Setup_QualParamPage.fetch_Hour();
		sa.assertEquals(HrTxt2, "00",
				"FAIL:  Application is not showing 00 when user entered more than maximum value in HH");

		Setup_QualParamPage.enter_Hour("23");
		Setup_QualParamPage.click_Min();
		String HrTxt3 = Setup_QualParamPage.fetch_Hour();
		sa.assertEquals(HrTxt3, "23", "FAIL:  Application is not accepting max value as 23 in HH");

		sa.assertAll();
	}

	// QP014A-Verify the invalid values for HH in time field for time of the day

	@Test(groups = {
			"Regression" }, dataProvider = "QP014A", dataProviderClass = setupCreationUtility.class, description = "QP014A-Verify the invalid values for HH in time field for time of the day")

	public void QP014A(String HH) throws InterruptedException, IOException {
		extentTest = extent.startTest("QP014A-Verify the invalid values for HH in time field for time of the day");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();

		Setup_QualParamPage.enter_Hour(HH);
		Setup_QualParamPage.click_Min();
		String Actmsg = Setup_QualParamPage.AlertMsg();
		String Expmsg = "Input string was not in a correct format.";
		sa.assertEquals(Actmsg, Expmsg, "Fail: Application is accepting Incorrect format ");
		sa.assertAll();
	}

	// QP014B-Do not enter anything in HH field and click on any other field or
	// anywhere in the screen

	@Test(groups = {
			"Regression" }, description = "QP014B-Do not enter anything in HH field and click on any other field or anywhere in the screen")
	public void QP014B() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP014B-Do not enter anything in HH field and click on any other field or anywhere in the screen");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();
		// Enter less than min val
		Setup_QualParamPage.enter_Hour("");
		Setup_QualParamPage.click_Min();

		String HrTxt1 = Setup_QualParamPage.fetch_Hour();
		sa.assertEquals(HrTxt1, "00", "FAIL: The field did not get to set to 00 automatically");

		sa.assertAll();
	}

	// QP015-Verify that min 00 and max 59 is accepted in MM field for time of the
	// day option
	@Test(groups = {
			"Regression" }, description = "QP015-Verify that min 00 and max 59 is accepted in MM field for time of the day option")
	public void QP015() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("QP015-Verify that min 00 and max 59 is accepted in MM field for time of the day option");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();
		// Enter less than min val
		Setup_QualParamPage.enter_Min("0");
		Setup_QualParamPage.click_Sec();

		String MnTxt1 = Setup_QualParamPage.fetch_Min();
		sa.assertEquals(MnTxt1, "00", "FAIL:  minimum val is not  00 in MM");

		// Enter more than max val
		Setup_QualParamPage.enter_Min("60");
		Setup_QualParamPage.click_Sec();
		String MnTxt2 = Setup_QualParamPage.fetch_Min();
		sa.assertEquals(MnTxt2, "00", "FAIL: The MM field did not get to set to 00 automatically");

		Setup_QualParamPage.enter_Min("59");
		Setup_QualParamPage.click_Sec();
		String MnTxt3 = Setup_QualParamPage.fetch_Min();
		sa.assertEquals(MnTxt3, "59", "FAIL:  Application is not accepting max value as 59 in MM textfield");

		sa.assertAll();
	}

	// QP016A-Verify the invalid values for MM in time field for time of the day

	@Test(groups = {
			"Regression" }, dataProvider = "QP016A", dataProviderClass = setupCreationUtility.class, description = "QP016A-Verify the invalid values for MM in time field for time of the day")

	public void QP016A(String MM) throws InterruptedException, IOException {
		extentTest = extent.startTest("QP016A-Verify the invalid values for MM in time field for time of the day");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();

		Setup_QualParamPage.enter_Min(MM);
		Setup_QualParamPage.click_Sec();
		String Actmsg = Setup_QualParamPage.AlertMsg();
		String Expmsg = "Input string was not in a correct format.";
		sa.assertEquals(Actmsg, Expmsg, "Fail: Application is accepting Incorrect format ");
		sa.assertAll();
	}

// QP016B-Do not enter anything in MM field and click on any other field or
// anywhere in the screen

	@Test(groups = {
			"Regression" }, description = "QP016B-Do not enter anything in MM field and click on any other field or anywhere in the screen")
	public void QP016B() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP016B-Do not enter anything in MM field and click on any other field or anywhere in the screen");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();
		// Enter less than min val
		Setup_QualParamPage.enter_Min("");
		Setup_QualParamPage.click_Sec();

		String MnTxt1 = Setup_QualParamPage.fetch_Min();
		sa.assertEquals(MnTxt1, "00", "FAIL: The field did not get to set to 00 automatically");

		sa.assertAll();
	}

	// QP017-Verify that min 00 and max 59 is accepted in SS field for time of the
	// day option
	@Test(groups = {
			"Regression" }, description = "QP017-Verify that min 00 and max 59 is accepted in SS field for time of the day option")
	public void QP017() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("QP017-Verify that min 00 and max 59 is accepted in SS field for time of the day option");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();
		// Enter less than min val
		Setup_QualParamPage.enter_Sec("0");
		Setup_QualParamPage.click_Min();

		String MnTxt1 = Setup_QualParamPage.fetch_Sec();
		sa.assertEquals(MnTxt1, "00", "FAIL:  minimum val is not  00 in MM");

		// Enter more than max val
		Setup_QualParamPage.enter_Sec("60");
		Setup_QualParamPage.click_Min();
		String MnTxt2 = Setup_QualParamPage.fetch_Sec();
		sa.assertEquals(MnTxt2, "00", "FAIL:   The SS field did not get to set to 00 automatically");

		Setup_QualParamPage.enter_Sec("59");
		Setup_QualParamPage.click_Min();
		String MnTxt3 = Setup_QualParamPage.fetch_Sec();
		sa.assertEquals(MnTxt3, "59", "FAIL:  Application is not accepting max value as 59 in MM textfield");

		sa.assertAll();
	}

	// QP018-Verify the invalid values for SS in time field for time of the day
	@Test(groups = {
			"Regression" }, dataProvider = "QP018A", dataProviderClass = setupCreationUtility.class, description = "QP018-Verify the invalid values for SS in time field for time of the day")

	public void QP018A(String SS) throws InterruptedException, IOException {
		extentTest = extent.startTest("QP018A-Verify the invalid values for SS in time field for time of the day");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();

		Setup_QualParamPage.enter_Sec(SS);
		Setup_QualParamPage.click_Min();
		String Actmsg = Setup_QualParamPage.AlertMsg();
		String Expmsg = "Input string was not in a correct format.";
		sa.assertEquals(Actmsg, Expmsg, "Fail: Application is accepting Incorrect format ");
		sa.assertAll();
	}

//QP018-Do not enter anything in SS field and click on any other field or anywhere in the screen

	@Test(groups = {
			"Regression" }, description = "QP018B-Do not enter anything in MM field and click on any other field or anywhere in the screen")
	public void QP018B() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP018B-Do not enter anything in MM field and click on any other field or anywhere in the screen");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();
		Setup_QualParamPage.enter_Sec("");
		Setup_QualParamPage.click_Min();

		String ScTxt1 = Setup_QualParamPage.fetch_Sec();
		sa.assertEquals(ScTxt1, "00", "FAIL: The field did not get to set to 00 automatically");

		sa.assertAll();
	}

	// QP019-Verify that validation message is displayed when elapsed time is
	// entered in time of the day field and proceeded to next step

	@Test(groups = {
			"Regression" }, description = "QP019-Verify that validation message is displayed when elapsed time is entered in time of the day field and proceeded to next step")
	public void QP019() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP019-Verify that validation message is displayed when elapsed time is entered in time of the day field and proceeded to next step");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.select_TOD_QStart_DrpDwn();
		Setup_QualParamPage.Click_NxtBtn_Alert();
// Here we are moving with the default time as elapsed time
		String Actmsg = Setup_QualParamPage.AlertMsg();
		String Expmsg = "Time of the day should be greater than current time.";
		sa.assertEquals(Actmsg, Expmsg, "Fail: Application is accepting elapsed time ");
		sa.assertAll();
	}
	// QP019.1-Verify that a validation message is displayed when entered more than
	// 72 hrs for time of the day

//QP020-Verify if the ‘Stop Qualification’ drop down option displays ‘Manual’ as the default selection.

	@Test(groups = {
			"Regression" }, description = "QP020-Verify if the ‘Stop Qualification’ drop down option displays ‘Manual’ as the default selection.")
	public void QP020() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"QP020-Verify if the ‘Stop Qualification’ drop down option displays ‘Manual’ as the default selection.");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(Setup_QualParamPage.get_Txt_Qstop_DrpDwnBox(), "Manual",
				"FAIL: In stop Qualification drop down 'Manual' is not displaying as the default selection");
		sa.assertAll();
	}

//QP021-Verify that _Manual_ and _Cycle time_ options are displayed in Stop Qualification drop down

	@Test(groups = {
			"Regression" }, description = "QP021-Verify that _Manual_ and _Cycle time_ options are displayed in Stop Qualification drop down")
	public void QP021() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"QP021-Verify that _Manual_ and _Cycle time_ options are displayed in Stop Qualification drop down");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstop_DrpDwnBox();

		sa.assertEquals(Setup_QualParamPage.Fetch_Firstoption_QStop_DrpDwn(), "Manual",
				"FAIL: 'Manual' option is not available in Start Qualification drop down ");

		sa.assertEquals(Setup_QualParamPage.Fetch_Secondoption_QStop_DrpDwn(), "Cycle Time",
				"FAIL: 'Manual' option is not available in Start Qualification drop down ");
		sa.assertAll();
	}

	// QP022-Verify that on-click of the _Manual_ option in the list, will display
	// the Stop Quall as Manual

	@Test(groups = {
			"Regression" }, description = "QP022-Verify that on-click of the _Manual_ option in the list, will display the Stop Quall as Manual")
	public void QP022() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"QP022-Verify that on-click of the _Manual_ option in the list, will display the Stop Quall as Manual");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_Manual_QStop_DrpDwn();
		sa.assertEquals(Setup_QualParamPage.get_Txt_Qstop_DrpDwnBox(), "Manual",
				"FAIL: In Start Qualification drop down 'Manual' is not displaying as the default selection");
		sa.assertAll();
	}

	// QP023-Verify that on-click of the _Cycle Time_ option in the list, will
	// display the time fields for stop Qual

	@Test(groups = {
			"Regression" }, description = "QP023-Verify that on-click of the _Cycle Time_ option in the list, will display the time fields for Stop Qual")
	public void QP023() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"QP023-Verify that on-click of the _Cycle Time_ option in the list, will display the time fields for Stop Qual");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();
		sa.assertEquals(Setup_QualParamPage.get_Txt_Qstop_DrpDwnBox(), "Cycle Time",
				"FAIL: In Start Qualification drop down 'Cycle Time' is not displaying as the default selection");

		sa.assertEquals(Setup_QualParamPage.HoursStopQual_State(), true, "FAIL: Hours field is not visible");

		sa.assertEquals(Setup_QualParamPage.MinStopQual_State(), true, "FAIL: min field is not visible");

		sa.assertEquals(Setup_QualParamPage.SecStopQual_State(), true, "FAIL: sec field is not visible");

		sa.assertAll();
	}

	// QP024-Verify that the time field has three text boxes for Hours,Minutes and
	// Seconds under Cycle time option

	@Test(groups = {
			"Regression" }, description = "QP024-Verify that the time field has three text boxes for Hours,Minutes and Seconds under Cycle time option")
	public void QP024() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP024-Verify that the time field has three text boxes for Hours,Minutes and Seconds under Cycle time option");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();

		sa.assertEquals(Setup_QualParamPage.HoursStopQual_State(), true, "FAIL: Hours field is not visible");

		sa.assertEquals(Setup_QualParamPage.MinStopQual_State(), true, "FAIL: min field is not visible");

		sa.assertEquals(Setup_QualParamPage.SecStopQual_State(), true, "FAIL: sec field is not visible");

		sa.assertEquals(Setup_QualParamPage.HHMMSS_text_State(), true, "FAIL: HHMMSS_text  field is not visible");

		sa.assertAll();
	}

	// QP025-Verify that the default value for time is 00 in all the three text
	// boxes for Cycle time
	@Test(groups = {
			"Regression" }, description = "QP025-Verify that the default value for time is 00 in all the three text boxes for Cycle time")
	public void QP025() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP025-Verify that the default value for time is 00 in all the three text boxes for Cycle time");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();

		sa.assertEquals(Setup_QualParamPage.FetchTxt_HH_Stopqual(), "0000",
				"FAIL: Default value for  HH field is not set to 0000");

		sa.assertEquals(Setup_QualParamPage.FetchTxt_MM_Stopqual(), "00",
				"FAIL:  Default value for  MM field is not set to 00");

		sa.assertEquals(Setup_QualParamPage.FetchTxt_SS_Stopqual(), "00",
				"FAIL:  Default value for SS field is not set to 00");
		sa.assertAll();
	}

//QP026-Verify that max 4 characters are allowed in HH  time fields for Cycle time                             

	@Test(groups = {
			"Regression" }, description = "QP026-Verify that max 4 characters are allowed in HH  time fields for Cycle time")
	public void QP026() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("QP026-Verify that max 4 characters are allowed in HH  time fields for Cycle time");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();
		// Enter more than 4 chars
		Setup_QualParamPage.enterTxt_HH("1234567");
		String HHTxt = Setup_QualParamPage.FetchTxt_HH_Stopqual();

		sa.assertEquals(HHTxt.length(), 4, "FAIL: max 4 characters are not allowed in HH");
		sa.assertAll();
	}

//QP027A-Verify that max 2 characters are allowed in MM time field for Cycle time

	@Test(groups = {
			"Regression" }, description = "QP027A-Verify that max 2 characters are allowed in MM and SS  time fields for Cycle time")
	public void QP027A() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("QP027A-Verify that max 2 characters are allowed in MM and SS  time fields for Cycle time");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();
		// Enter more than 2 chars
		Setup_QualParamPage.enterTxt_MM("1234");
		String MMTxt = Setup_QualParamPage.FetchTxt_MM_Stopqual();

		sa.assertEquals(MMTxt.length(), 2, "FAIL: MM field accepting more than 2 chars");
		sa.assertAll();
	}
	// QP027B-Verify that max 2 characters are allowed in MM time field for Cycle
	// time

	@Test(groups = {
			"Regression" }, description = "QP027B-Verify that max 2 characters are allowed in SS  time fields for Cycle time")
	public void QP027B() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("QP027A-Verify that max 2 characters are allowed in SS  time fields for Cycle time");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();
		// Enter more than 2 chars
		Setup_QualParamPage.enterTxt_SS("1234");
		String SSTxt = Setup_QualParamPage.FetchTxt_SS_Stopqual();

		sa.assertEquals(SSTxt.length(), 2, "FAIL: SS field accepting more than 2 chars");
		sa.assertAll();
	}

	// QP028-Verify that only numbers are accepted in the time field in HH MM SS
	// text boxes for Cycle time
	@Test(groups = {
			"Regression" }, dataProvider = "QP028", dataProviderClass = setupCreationUtility.class, description = "QP028-Verify that only numbers are accepted in the time field in HH MM SS text boxes for Cycle time")

	public void QP028(String HH, String MM, String SS) throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"QP028-Verify that only numbers are accepted in the time field in HH MM SS text boxes for Cycle time");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();

		Setup_QualParamPage.enterTxt_HH(HH);
		String HHTxt = Setup_QualParamPage.FetchTxt_HH_Stopqual();
		sa.assertEquals(HHTxt, HH, "FAIL: HH field not accepting numbers");

		Setup_QualParamPage.enterTxt_MM(MM);
		String MMTxt = Setup_QualParamPage.FetchTxt_MM_Stopqual();
		sa.assertEquals(MMTxt, MM, "FAIL: MM field not accepting numbers");

		Setup_QualParamPage.enterTxt_SS(SS);
		String SSTxt = Setup_QualParamPage.FetchTxt_SS_Stopqual();
		sa.assertEquals(SSTxt, SS, "FAIL: SS field not accepting numbers");
		sa.assertAll();
	}

	// QP029-Verify that min 0000 and max 9999 hrs are allowed in HH field of Cycle
	// time
	@Test(groups = {
			"Regression" }, description = "QP029-Verify that min 0000 and max 9999 hrs are allowed in HH field of Cycle time")
	public void QP029() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("QP029-Verify that min 0000 and max 9999 hrs are allowed in HH field of Cycle time");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();

		// Enter less than min val
		Setup_QualParamPage.enterTxt_HH("000");

		Setup_QualParamPage.click_MM();

		String HrTxt1 = Setup_QualParamPage.FetchTxt_HH_Stopqual();
		sa.assertEquals(HrTxt1, "0000", "FAIL:  minimum val is not  0000 in HH");

		// Enter more than max val
		Setup_QualParamPage.enterTxt_HH("99991");
		Setup_QualParamPage.click_MM();
		String HrTxt2 = Setup_QualParamPage.FetchTxt_HH_Stopqual();
		sa.assertEquals(HrTxt2, "9999",
				"FAIL:  Application is not showing 9999 when user entered more than maximum value in HH");

		Setup_QualParamPage.enterTxt_HH("9999");
		Setup_QualParamPage.click_MM();
		String HrTxt3 = Setup_QualParamPage.FetchTxt_HH_Stopqual();
		sa.assertEquals(HrTxt3, "9999", "FAIL:  Application is not accepting max value as 9999 in HH");

		sa.assertAll();
	}

	// QP030-Verify the invalid values for HH in time field for Cycle time

	@Test(groups = {
			"Regression" }, dataProvider = "QP030", dataProviderClass = setupCreationUtility.class, description = "QP030-Verify the invalid values for HH in time field for Cycle time")
	public void QP030(String HH) throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest("QP030-Verify the invalid values for HH in time field for Cycle time");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();

		Setup_QualParamPage.enterTxt_HH(HH);
		Setup_QualParamPage.click_MM();

		String HrTxt1 = Setup_QualParamPage.FetchTxt_HH_Stopqual();
		sa.assertEquals(HrTxt1, "0000", "FAIL:  Application is not set back to  0000 in HH field");
	}

	// QP030A-Verify the negative value for HH field

	@Test(groups = {
			"Regression" }, description = "QP030A-Verify the invalid values for HH in time field for Cycle time")
	public void QP030A() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest("QP030A-Verify the invalid values for HH in time field for Cycle time");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();

		Setup_QualParamPage.enterTxt_HH("-1234");
		Setup_QualParamPage.click_MM();

		String HrTxt1 = Setup_QualParamPage.FetchTxt_HH_Stopqual();
		sa.assertEquals(HrTxt1, "1234", "FAIL:  Application is accepting negative value in HH field");
		sa.assertAll();
	}

	// QP031-Verify that min 00 and max 59 is accepted in MM field for Cycle time
	// option
	@Test(groups = {
			"Regression" }, description = "QP031-Verify that min 00 and max 59 is accepted in MM field for Cycle time option")
	public void QP031() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("QP031-Verify that min 00 and max 59 is accepted in MM field for Cycle time option");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();

		// Enter less than min val
		Setup_QualParamPage.enterTxt_MM("0");

		Setup_QualParamPage.click_SS();

		String MMTxt1 = Setup_QualParamPage.FetchTxt_MM_Stopqual();
		sa.assertEquals(MMTxt1, "00", "FAIL:  minimum val is not  00 in MM");

		// Enter more than max val
		Setup_QualParamPage.enterTxt_MM("60");
		Setup_QualParamPage.click_SS();
		String MMTxt2 = Setup_QualParamPage.FetchTxt_MM_Stopqual();
		sa.assertEquals(MMTxt2, "00", "FAIL: Accepting more than ma value in MM");

		Setup_QualParamPage.enterTxt_MM("59");
		Setup_QualParamPage.click_SS();
		String MMTxt3 = Setup_QualParamPage.FetchTxt_MM_Stopqual();
		sa.assertEquals(MMTxt3, "59", "FAIL:  Application is not accepting max value as 59 in MM");

		sa.assertAll();
	}

	// QP032-Verify the invalid values for MM in time field for Cycle time
	@Test(groups = {
			"Regression" }, dataProvider = "QP032", dataProviderClass = setupCreationUtility.class, description = "QP032-Verify the invalid values for MM in time field for Cycle time")
	public void QP032(String MM) throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest("QP032-Verify the invalid values for MM in time field for Cycle time");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();

		Setup_QualParamPage.enterTxt_MM(MM);
		Setup_QualParamPage.click_SS();

		String mmTxt1 = Setup_QualParamPage.FetchTxt_MM_Stopqual();
		sa.assertEquals(mmTxt1, "00", "FAIL:  Application is not set back to  00 in MM field");
	}

	// QP032A-Verify the negative value for MM field

	@Test(groups = { "Regression" }, description = "QP032A-Verify the negative value for MM field")
	public void QP032A() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest("QP032A-Verify the negative value for MM field");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();

		Setup_QualParamPage.enterTxt_MM("-12");
		Setup_QualParamPage.click_SS();

		String mmtxt = Setup_QualParamPage.FetchTxt_MM_Stopqual();
		sa.assertEquals(mmtxt, "12", "FAIL:  Application is accepting negative value in MM field");
		sa.assertAll();
	}

	// QP033-Verify that min 00 and max 59 is accepted in SS field for Cycle time
	// option

	@Test(groups = {
			"Regression" }, description = "QP033-Verify that min 00 and max 59 is accepted in SS field for Cycle time option")
	public void QP033() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("QP033-Verify that min 00 and max 59 is accepted in SS field for Cycle time option");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();

		// Enter less than min val
		Setup_QualParamPage.enterTxt_SS("0");

		Setup_QualParamPage.click_MM();

		String SSTxt1 = Setup_QualParamPage.FetchTxt_SS_Stopqual();
		sa.assertEquals(SSTxt1, "00", "FAIL:  minimum val is not  00 in SS");

		// Enter more than max val
		Setup_QualParamPage.enterTxt_SS("60");
		Setup_QualParamPage.click_MM();
		String SSTxt2 = Setup_QualParamPage.FetchTxt_SS_Stopqual();
		sa.assertEquals(SSTxt2, "00", "FAIL: Accepting more than max value in MM");

		Setup_QualParamPage.enterTxt_SS("59");
		Setup_QualParamPage.click_MM();
		String SSTxt3 = Setup_QualParamPage.FetchTxt_SS_Stopqual();
		sa.assertEquals(SSTxt3, "59", "FAIL:  Application is not accepting max value as 59 in MM");

		sa.assertAll();
	}

	// QP034-Verify the invalid values for SS in time field for Cycle time
	@Test(groups = {
			"Regression" }, dataProvider = "QP034", dataProviderClass = setupCreationUtility.class, description = "QP034-Verify the invalid values for SS in time field for Cycle time")
	public void QP034(String SS) throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest("QP034-Verify the invalid values for SS in time field for Cycle time");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();

		Setup_QualParamPage.enterTxt_SS(SS);
		Setup_QualParamPage.click_MM();

		String ssTxt1 = Setup_QualParamPage.FetchTxt_SS_Stopqual();
		sa.assertEquals(ssTxt1, "00", "FAIL:  Application is not set back to  00 in SS field");
	}

	// QP034A-Verify the negative value for SS field

	@Test(groups = { "Regression" }, description = "QP034A-Verify the negative value for SS field")
	public void QP034A() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest("QP034A-Verify the negative value for SS field");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();

		Setup_QualParamPage.enterTxt_SS("-12");
		Setup_QualParamPage.click_MM();

		String sstxt = Setup_QualParamPage.FetchTxt_SS_Stopqual();
		sa.assertEquals(sstxt, "12", "FAIL:Application is accepting negative value in SS field");
		sa.assertAll();
	}

	// QP035-Verify that a validation message is displayed when cycle time is given
	// less than the selected sampling rate

	@Test(groups = {
			"Regression" }, description = "QP035-Verify that a validation message is displayed when cycle time is given less than the selected sampling rate")
	public void QP035() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP035-Verify that a validation message is displayed when cycle time is given less than the selected sampling rate");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.select_CT_QStop_DrpDwn();

		Setup_QualParamPage.enterTxt_SS("02");
		Setup_QualParamPage.Click_NxtBtn_Alert();
		String ActMsg = Setup_QualParamPage.AlertMsg();
		String ExpMsg = "Selected Cycle Time should be greater than Sampling Rate.";
		sa.assertEquals(ActMsg, ExpMsg, "Fail:Incorrect  Alert message is displaying ");
		sa.assertAll();
	}
	// QP036-Verify that the default value for Sampling Rate is 5 sec

	@Test(groups = { "Regression" }, description = "QP036-Verify that the default value for Sampling Rate is 5 sec")
	public void QP036() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest("QP036-Verify that the default value for Sampling Rate is 5 sec");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_SR_DrpDwnBox();

		sa.assertEquals(Setup_QualParamPage.fetch_DefaultTxt_SR(), "5 Seconds",
				"Fail:Incorrect default value  is displaying ");

		sa.assertAll();
	}

	// QP037-Verify if the application provides sampling rate Drop down list in
	// Seconds as 1,2, 3, 5, 10, 20, 30

	@Test(groups = { "Regression" }, description = "QP036-Verify that the default value for Sampling Rate is 10 sec")
	public void QP037() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest("QP036-Verify that the default value for Sampling Rate is 10 sec");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.click_SR_DrpDwnBox();
		Thread.sleep(2000);
		sa.assertEquals(Setup_QualParamPage.fetch_DefaultTxt_SR(), "5 Seconds",
				"Fail:Incorrect default value  is displaying ");

		sa.assertEquals(Setup_QualParamPage.Fetch_1optn_SR_DrpDwn(), "1 Second",
				"Fail:Incorrect default value  is displaying ");

		sa.assertEquals(Setup_QualParamPage.Fetch_2optn_SR_DrpDwn(), "2 Seconds",
				"Fail:Incorrect default value  is displaying ");

		sa.assertEquals(Setup_QualParamPage.Fetch_7optn_SR_DrpDwn(), "30 Seconds",
				"Fail:Incorrect default value  is displaying ");

		sa.assertAll();
	}

	// QP038-Verify if the application provides sampling rate Drop down list in
	// Minutes as 1, 2, 3, 5, 10

	@Test(groups = {
			"Regression" }, description = "QP038-Verify if the application provides sampling rate Drop down list in Minutes as  1, 2, 3, 5, 10        ")
	public void QP038() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP038-Verify if the application provides sampling rate Drop down list in Minutes as  1, 2, 3, 5, 10        ");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_SR_DrpDwnBox();
		Thread.sleep(2000);
		sa.assertEquals(Setup_QualParamPage.Fetch_8optn_SR_DrpDwn(), "1 Minute",
				"Fail:Incorrect default value  is displaying ");

		sa.assertEquals(Setup_QualParamPage.Fetch_9thoptn_SR_DrpDwn(), "2 Minutes",
				"Fail:Incorrect default value  is displaying ");
		sa.assertAll();
	}

	// QP039-Verify that the default value for Transaction Rate is 10 sec

	@Test(groups = { "Regression" }, description = "QP039-Verify that the default value for Transaction Rate is 10 sec")
	public void QP039() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest("QP039-Verify that the default value for Transaction Rate is 10 sec");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_SR_DrpDwnBox();
		Thread.sleep(2000);
		sa.assertEquals(Setup_QualParamPage.FetchDfltTxt_TR_DrpDwnBox(), "10 Seconds",
				"Fail: The default value for Transaction Rate is not 10 sec");

		sa.assertAll();
	}

	// QP040-Verify that for 1 sec SR, the TR values in drop down are 3,4 and 5 sec
	// is selected by default

	@Test(groups = {
			"Regression" }, description = "QP040-Verify that for 1 sec SR, the TR values in drop down are 3,4 and 5 sec is selected by default")
	public void QP040() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP040-Verify that for 1 sec SR, the TR values in drop down are 3,4 and 5 sec is selected by default");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_SR_DrpDwnBox();
		Setup_QualParamPage.Select_1sec_SR();
		sa.assertEquals(Setup_QualParamPage.FetchDfltTxt_TR_DrpDwnBox(), "4 Seconds",
				"Fail: 4 sec is not selected by default");
		Setup_QualParamPage.click_TR_DrpDwnBox();
		Thread.sleep(2000);
		sa.assertEquals(Setup_QualParamPage.is3secs_visible(), true, "Fail: 3 sec is not selected by default");
		sa.assertEquals(Setup_QualParamPage.is4secs_visible(), true, "Fail: 4 sec is selected by default");
		sa.assertEquals(Setup_QualParamPage.is5secs_visible(), true, "Fail:5 sec is selected by default");

		sa.assertAll();
	}

	// QP041-Verify that for 2 sec SR, the TR values in drop down are 6,8 and 10 sec
	// and 8 sec is selected by default
	@Test(groups = {
			"Regression" }, description = "QP041-Verify that for 2 sec SR, the TR values in drop down are 6,8 and 10 sec and 8 sec is selected by default")
	public void QP041() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP041-Verify that for 2 sec SR, the TR values in drop down are 6,8 and 10 sec and 8 sec is selected by default");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_SR_DrpDwnBox();
		Setup_QualParamPage.Select_2sec_SR();
		sa.assertEquals(Setup_QualParamPage.FetchDfltTxt_TR_DrpDwnBox(), "8 Seconds",
				"Fail: 8 sec is not selected by default");
		Setup_QualParamPage.click_TR_DrpDwnBox();
		Thread.sleep(2000);
		sa.assertEquals(Setup_QualParamPage.is6secs_visible(), true, "Fail: 6 sec is not selected by default");
		sa.assertEquals(Setup_QualParamPage.is8secs_visible(), true, "Fail: 8 sec is selected by default");
		sa.assertEquals(Setup_QualParamPage.is10secs_visible(), true, "Fail: 10 sec is selected by default");

		sa.assertAll();
	}

	// QP042-Verify that for 3 sec SR, the TR values in drop down are 6,9 and 12 sec
	// and 9 sec is selected by default
	@Test(groups = {
			"Regression" }, description = "QP042-Verify that for 3 sec SR, the TR values in drop down are 6,9 and 12 sec and 9 sec is selected by default")
	public void QP042() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP042-Verify that for 3 sec SR, the TR values in drop down are 6,9 and 12 sec and 9 sec is selected by default");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_SR_DrpDwnBox();
		Setup_QualParamPage.Select_3sec_SR();
		sa.assertEquals(Setup_QualParamPage.FetchDfltTxt_TR_DrpDwnBox(), "9 Seconds",
				"Fail: 9 sec is not selected by default");
		Setup_QualParamPage.click_TR_DrpDwnBox();
		Thread.sleep(2000);
		sa.assertEquals(Setup_QualParamPage.is6secs_visible(), true, "Fail: 6 sec is not selected by default");
		sa.assertEquals(Setup_QualParamPage.is9secs_visible(), true, "Fail: 9 sec is selected by default");
		sa.assertEquals(Setup_QualParamPage.is12secs_visible(), true, "Fail:12 sec is selected by default");

		sa.assertAll();
	}

	// QP043-Verify that for 5 sec SR, the TR values in drop down are 5,10 and 20
	// sec and 10 sec is selected by default

	@Test(groups = {
			"Regression" }, description = "QP043-Verify that for 5 sec SR, the TR values in drop down are 5,10 and 20 sec and 10 sec is selected by default")
	public void QP043() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP043-Verify that for 5 sec SR, the TR values in drop down are 5,10 and 20 sec and 10 sec is selected by default");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_SR_DrpDwnBox();
		Setup_QualParamPage.Select_5sec_SR();
		sa.assertEquals(Setup_QualParamPage.FetchDfltTxt_TR_DrpDwnBox(), "10 Seconds",
				"Fail: 10 sec is not selected by default");
		Setup_QualParamPage.click_TR_DrpDwnBox();
		Thread.sleep(2000);
		sa.assertEquals(Setup_QualParamPage.is5secs_visible(), true, "Fail: 5 sec is not selected by default");
		sa.assertEquals(Setup_QualParamPage.is10secs_visible(), true, "Fail: 10 sec is selected by default");
		sa.assertEquals(Setup_QualParamPage.is20secs_visible(), true, "Fail:20 sec is selected by default");

		sa.assertAll();
	}

	// QP044-Verify that for 10 sec SR, the TR values in drop down are 10,20 and 30
	// sec and 10 sec is selected by default
	@Test(groups = {
			"Regression" }, description = "QP044-Verify that for 10 sec SR, the TR values in drop down are 10,20 and 30 sec and 20 sec is selected by default")
	public void QP044() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP044-Verify that for 10 sec SR, the TR values in drop down are 10,20 and 30 sec and 20 sec is selected by default");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_SR_DrpDwnBox();
		Setup_QualParamPage.Select_10sec_SR();
		sa.assertEquals(Setup_QualParamPage.FetchDfltTxt_TR_DrpDwnBox(), "10 Seconds",
				"Fail: 10 sec is not selected by default");
		Setup_QualParamPage.click_TR_DrpDwnBox();
		Thread.sleep(2000);
		sa.assertEquals(Setup_QualParamPage.is10secs_visible(), true, "Fail:  10 sec is not selected by default");
		sa.assertEquals(Setup_QualParamPage.is20secs_visible(), true, "Fail: 20 sec is selected by default");
		sa.assertEquals(Setup_QualParamPage.is30secs_visible(), true, "Fail: 30 sec is selected by default");

		sa.assertAll();
	}

	// QP045-Verify that for 20 sec SR, the TR values in drop down are 10,20 and 30
	// sec and 10 sec is selected by default
	@Test(groups = {
			"Regression" }, description = "QP045-Verify that for 20 sec SR, the TR values in drop down are 10,20 and 30 sec and 20 sec is selected by default")
	public void QP045() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP045-Verify that for 20 sec SR, the TR values in drop down are 10,20 and 30 sec and 20 sec is selected by default");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_SR_DrpDwnBox();
		Setup_QualParamPage.Select_20sec_SR();
		sa.assertEquals(Setup_QualParamPage.FetchDfltTxt_TR_DrpDwnBox(), "10 Seconds",
				"Fail: 10 sec is not selected by default");

		Setup_QualParamPage.click_TR_DrpDwnBox();
		Thread.sleep(2000);
		sa.assertEquals(Setup_QualParamPage.is10secs_visible(), true, "Fail:  10 sec is not selected by default");
		sa.assertEquals(Setup_QualParamPage.is20secs_visible(), true, "Fail: 20 sec is selected by default");
		sa.assertEquals(Setup_QualParamPage.is30secs_visible(), true, "Fail: 30 sec is selected by default");

		sa.assertAll();
	}

	// QP046-Verify that for 30 sec SR, the TR values in drop down are 10,20 and 30
	// sec and 10 sec is selected by default

	@Test(groups = {
			"Regression" }, description = "QP046-Verify that for 30 sec SR, the TR values in drop down are 10,20 and 30 sec and 10 sec is selected by default")
	public void QP046() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP046-Verify that for 30 sec SR, the TR values in drop down are 10,20 and 30 sec and 10 sec is selected by default");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_SR_DrpDwnBox();
		Setup_QualParamPage.Select_30sec_SR();
		sa.assertEquals(Setup_QualParamPage.FetchDfltTxt_TR_DrpDwnBox(), "10 Seconds",
				"Fail: 10 sec is not selected by default");

		Setup_QualParamPage.click_TR_DrpDwnBox();
		Thread.sleep(2000);
		sa.assertEquals(Setup_QualParamPage.is10secs_visible(), true, "Fail:  10 sec is not selected by default");
		sa.assertEquals(Setup_QualParamPage.is20secs_visible(), true, "Fail: 20 sec is selected by default");
		sa.assertEquals(Setup_QualParamPage.is30secs_visible(), true, "Fail: 30 sec is selected by default");

		sa.assertAll();
	}

	// QP047-Verify that for 1 min SR, the TR values in drop down are 10,20 and 30
	// sec and 20 sec is selected by default
	@Test(groups = {
			"Regression" }, description = "QP047-Verify that for 1 min SR, the TR values in drop down are 10,20 and 30 sec and 20 sec is selected by default")
	public void QP047() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP047-Verify that for 1 min SR, the TR values in drop down are 10,20 and 30 sec and 20 sec is selected by default");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_SR_DrpDwnBox();
		Setup_QualParamPage.Select_1min_SR();
		sa.assertEquals(Setup_QualParamPage.FetchDfltTxt_TR_DrpDwnBox(), "20 Seconds",
				"Fail: 1 Minute is not selected by default");

		Setup_QualParamPage.click_TR_DrpDwnBox();
		Thread.sleep(3000);
		sa.assertEquals(Setup_QualParamPage.is10secs_visible(), true, "Fail:  10 sec is not selected by default");
		sa.assertEquals(Setup_QualParamPage.is20secs_visible(), true, "Fail: 20 sec is selected by default");
		sa.assertEquals(Setup_QualParamPage.is30secs_visible(), true, "Fail: 30 sec is selected by default");

		sa.assertAll();

	}

	// QP048-Verify that for 2 min SR, the TR values in drop down are 10,20 and 30
	// sec and 20 sec is selected by default

	@Test(groups = {
			"Regression" }, description = "QP048-Verify that for 2 mins SR, the TR values in drop down are 10,20 and 30 sec and 20 sec is selected by default             ")
	public void QP048() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP048-Verify that for 2 mins SR, the TR values in drop down are 10,20 and 30 sec and 20 sec is selected by default               ");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_SR_DrpDwnBox();
		Setup_QualParamPage.Select_2min_SR();
		sa.assertEquals(Setup_QualParamPage.FetchDfltTxt_TR_DrpDwnBox(), "20 Seconds",
				"Fail: 2 Minutes is not selected by default");

		Setup_QualParamPage.click_TR_DrpDwnBox();
		Thread.sleep(3000);
		sa.assertEquals(Setup_QualParamPage.is10secs_visible(), true, "Fail:  10 sec is not selected by default");
		sa.assertEquals(Setup_QualParamPage.is20secs_visible(), true, "Fail: 20 sec is selected by default");
		sa.assertEquals(Setup_QualParamPage.is30secs_visible(), true, "Fail: 30 sec is selected by default");

		sa.assertAll();
	}

	// QP052-Verify the drop down values for Active.Inactive field under RF Transmit
	// Threshold section

	@Test(groups = {
			"Regression" }, description = "QP052-Verify the drop down values for Active.Inactive field under RF Transmit Threshold section")
	public void QP052() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP052-Verify the drop down values for Active.Inactive field under RF Transmit Threshold section");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.click_RFT_DrpDwnBox();
		sa.assertEquals(Setup_QualParamPage.fetch_1stoptionTxt_RFT(), "-15,-10",
				"Fail: -15,-10 is not displaying under RF Transmit Threshold section");
		sa.assertEquals(Setup_QualParamPage.fetch_2ndoptionTxt_RFT(), "-25,-20",
				"Fail: -25,-20 is not displaying under RF Transmit Threshold section");
		sa.assertEquals(Setup_QualParamPage.fetch_3rdoptionTxt_RFT(), "-35,-30",
				"Fail: -35,-30 is not displaying under RF Transmit Threshold section");
		sa.assertEquals(Setup_QualParamPage.fetch_4thoptionTxt_RFT(), "-45,-40",
				"Fail: -45,-40 is not displaying under RF Transmit Threshold section");
		sa.assertEquals(Setup_QualParamPage.fetch_5thoptionTxt_RFT(), "-125,-120",
				"Fail: -125,-120 is not displaying under RF Transmit Threshold section");
		sa.assertAll();
	}

	// QP053-Verify that on-click on the Review navigator on th top right will
	// navigate to Review screen

	@Test(groups = {
			"Regression" }, description = "QP053-Verify that on-click on the Review navigator on th top right will navigate to Review screen")
	public void QP053() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP053-Verify that on-click on the Review navigator on th top right will navigate to Review screen");
		SoftAssert sa = new SoftAssert();

		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
		sa.assertEquals(Setup_ReviewPage.ReviewPage_state(), true, "Fail: ReviewPage is not available");
		sa.assertAll();
	}

	// QP054-Verify that on-click of the Calculations navigator on the top left will
	// navigate to Calculations screen

	@Test(groups = {
			"Regression" }, description = "QP054-Verify that on-click of the Calculations navigator on the top left will navigate to Calculations screen")
	public void QP054() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP054-Verify that on-click of the Calculations navigator on the top left will navigate to Calculations screen");
		SoftAssert sa = new SoftAssert();

		Setup_CalculationsPage = Setup_QualParamPage.click_Backbtn();
		sa.assertEquals(Setup_CalculationsPage.CalculationPage_state(), true,
				"Fail: Calculation Page  is not available");
		sa.assertAll();
	}

	// QP055-Verify that on-click of the back icon on the screen will navigate to
	// Asset details screen upon confirmation
	@Test(groups = {
			"Regression" }, description = "QP055-Verify that on-click of the back icon on the screen will navigate to Asset details screen upon confirmation")
	public void QP055() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"QP055-Verify that on-click of the back icon on the screen will navigate to Asset details screen upon confirmation");
		SoftAssert sa = new SoftAssert();

		assetDetailsPage = Setup_QualParamPage.Click_back_Btn();

		sa.assertEquals(assetDetailsPage.assetDetailPageTitle_Visible(), true,
				"Fail: assetDetailPageTitle is not available");
		sa.assertAll();
	}

	// QP056-Verify the bottom menu options displayed in Qualification Parameters
	// screen

	@Test(description = "QP056-Verify the bottom menu options displayed in Qualification Parameters screen")
	public void SC106() throws InterruptedException {
		extentTest = extent
				.startTest("QP056-Verify the bottom menu options displayed in Qualification Parameters screen");
		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage.Rt_Click_Buttom_AppBar();

		sa.assertEquals(Setup_QualParamPage.check_Home_Buttom_AppBar_Presence(), true,
				"FAIL: Home icon/button missing in bottom app bar");
		sa.assertEquals(Setup_QualParamPage.check_Help_Buttom_AppBar_Presence(), true,
				"FAIL: Help icon/button missing in bottom app bar");
		sa.assertEquals(Setup_QualParamPage.check_WndsHelp_Buttom_AppBar_Presence(), true,
				"FAIL: Windows Help icon/button missing in bottom app bar");
		sa.assertEquals(Setup_QualParamPage.check_About_Buttom_AppBar_Presence(), true,
				"FAIL: About icon/button missing in bottom app bar");
		sa.assertAll();
	}

	// QP057-Verify that on-click of home btn in bottom menu options is navigated to
	// main hub page
	@Test(description = "QP057-Verify that on-click of home btn in bottom menu options is navigated to main hub page")
	public void CAL041() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"QP057-Verify that on-click of home btn in bottom menu options is navigated to main hub page");
		SoftAssert sa = new SoftAssert();

		MainHubPage = Setup_QualParamPage.Click_Home_Icon_AppBar();

		sa.assertEquals(MainHubPage.mainPageTitle(), true,
				"FAIL: Clicking Home icon/button in bottom app bar do not redirect to Mains Hub page");
		sa.assertAll();
	}

	// 'QP058-Verify that on-click of help btn in bottom menu options displays
	// information about the Qualification parameters screen
	@Test(description = "QP058-Verify that on-click of help btn in bottom menu options displays information about the Qualification parameters screen")
	public void QP058() throws InterruptedException {
		extentTest = extent.startTest(
				"QP058-Verify that on-click of help btn in bottom menu options displays information about the Qualification parameters screen");
		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage.Click_Help_Icon_AppBar();

		sa.assertEquals(Setup_QualParamPage.get__HelpMenu_HdrText(), "Qualification Parameters",
				"FAIL: Clicking Help icon/button in bottom app bar"
						+ "do not display the Qualification Parameters Help context window");
		sa.assertAll();
	}

	// QP059-Verify that on-click of windows help btn in bottom menu options
	// generates a PDF with information
	// This tc will handel manually

	// QP060-Verify that on-click of About btn in bottom menu options displays the
	// software version and the console IP address

	@Test(description = "QP060-Verify that on-click of About btn in bottom menu options displays the software version and the console IP address")
	public void SC110() throws InterruptedException {
		extentTest = extent.startTest(
				"QP060-Verify that on-click of About btn in bottom menu options displays the software version and the console IP address");
		SoftAssert sa = new SoftAssert();

		Setup_CalculationsPage.Click_About_Icon_AppBar();
		sa.assertEquals(Setup_QualParamPage.check_About_wndw_Presence(), true,
				"FAIL: Clicking About icon/button in bottom app bar do not display the About window");
		sa.assertAll();
	}

}
