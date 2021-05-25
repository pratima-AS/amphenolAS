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
import com.vrt.utility.setupCreationUtility;
import com.vrt.utility.setupCreationUtility;
import com.vrt.utility.userManagementUtility;
import com.vrt.pages.Setup_SensorDescriptionPage;

import com.vrt.pages.Setup_CalculationsPage;
import com.vrt.pages.Setup_QualParamPage;
import com.vrt.pages.Setup_ReviewPage;
import com.vrt.pages.AuditPage;

public class setup_ReviewTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>SetupTestData.xlsx sheet for test data i/p

	public setup_ReviewTest() throws IOException {
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
	AuditPage AuditPage;

	// Before All the tests are conducted
	//@BeforeTest
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, AWTException, ParseException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_" + "setup_ReviewTest" + ".html",
				true);
		extent.addSystemInfo("TestSuiteName", "setup_ReviewTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name2"));
		System.out.println("setup_ReviewTest  is  in Progress..");

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
		System.out.println("setup_ReviewTest Test Completed.");
	}

	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache\\ValProbeRT", "Setup.txt");

		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTSetups");
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
		defineSetupPage.enter_defineSetupPage_LoadDesc("load");
		defineSetupPage.enter_defineSetupPage_comments("com");

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
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();

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
	/*
	 * // REV001-Verify the details displayed in Review screen
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * " REV001-Verify the details displayed in Review screen") public void REV001()
	 * throws InterruptedException, IOException { extentTest =
	 * extent.startTest("REV001-Verify the details displayed in Review screen");
	 * SoftAssert sa = new SoftAssert();
	 * 
	 * sa.assertEquals(Setup_ReviewPage.get_Setup_titletext(), "test",
	 * "FAIL: Set up title is not available");
	 * sa.assertEquals(Setup_ReviewPage.get_ReviewPage_titletext(), "Review",
	 * "FAIL: Review is not displaying");
	 * 
	 * sa.assertEquals(Setup_ReviewPage.get_SubHeader_titletext(),
	 * "Review and Save the Setup", "FAIL: sub header is not available");
	 * 
	 * sa.assertEquals(Setup_ReviewPage.CopyAsNewSetup_Btn_state(), false,
	 * "FAIL: CopyAsNewSetup_Btn  is not available");
	 * 
	 * sa.assertEquals(Setup_ReviewPage.CreateSetupReport_Btn_state(), false,
	 * "FAIL: CreateSetupReport_Btn_state  is not available");
	 * 
	 * sa.assertEquals(Setup_ReviewPage.get_AssetDetails_Section_titletext(),
	 * "Asset Details", "Asset Details section is not available");
	 * sa.assertEquals(Setup_ReviewPage.get_SensorDetails_Section_titletext(),
	 * "Sensor Details", "Asset Details section is not available");
	 * sa.assertEquals(Setup_ReviewPage.EditSensorDetailsButton_state(), true,
	 * "EditCalculationButton  is not available");
	 * 
	 * sa.assertEquals(Setup_ReviewPage.get_Groups_Section_titletext(), "Groups",
	 * "Groups Details section is not available");
	 * 
	 * sa.assertEquals(Setup_ReviewPage.EditGroupsButton_state(), true,
	 * "EditGroupsButton  is not available");
	 * 
	 * sa.assertEquals(Setup_ReviewPage.get_ReportHeader_Section_titletext(),
	 * "Report Header", "Report Header section is not available");
	 * sa.assertEquals(Setup_ReviewPage.EditReportButton_state(), true,
	 * "EditReportButton_state  is not available");
	 * 
	 * Setup_ReviewPage.click_bottom_Scrollbar(8);
	 * sa.assertEquals(Setup_ReviewPage.get_Groups_Qualification__Section_titletext(
	 * ), "Qualification Start Stop Condition",
	 * "Qualification Start Stop Condition section is not available");
	 * 
	 * sa.assertEquals(Setup_ReviewPage.get_Calculations__Section_titletext(),
	 * "Calculations", "Calculations section  is not available");
	 * sa.assertEquals(Setup_ReviewPage.EditCalculationButton_state(), true,
	 * "EditCalculationButton  is not available");
	 * sa.assertEquals(Setup_ReviewPage.EditQualificationParametersButton_state(),
	 * true, "EditQualificationParametersButton_state  is not available");
	 * 
	 * sa.assertAll(); }
	 * 
	 * // REV002-Verify the details displayed under Asset Details section in Review
	 * // screen
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV002-Verify the details displayed under Asset Details section in Review screen"
	 * ) public void REV002() throws InterruptedException, IOException { extentTest
	 * = extent
	 * .startTest("REV002-Verify the details displayed under Asset Details section in Review screen"
	 * ); SoftAssert sa = new SoftAssert(); //
	 * sa.assertEquals(Setup_ReviewPage.image_state(), true, "Image box is not //
	 * available"); sa.assertEquals(Setup_ReviewPage.Asset_ID_state(), true,
	 * "Asset_ID  is not available");
	 * 
	 * sa.assertEquals(Setup_ReviewPage.Model_state(), true,
	 * "Model   is not available");
	 * 
	 * sa.assertEquals(Setup_ReviewPage.Manufacturer_state(), true,
	 * "Manufacturer  is not available");
	 * 
	 * sa.assertEquals(Setup_ReviewPage.Type_state(), true,
	 * "Type  is not available"); sa.assertEquals(Setup_ReviewPage.Size_state(),
	 * true, "Size  is not available"); sa.assertAll(); }
	 * 
	 * //REV004-Verify the details displayed under Sensor Details section in Review
	 * screen
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV004-Verify the details displayed under Sensor Details section in Review screen"
	 * ) public void REV004() throws InterruptedException, IOException { extentTest
	 * = extent
	 * .startTest("REV004-Verify the details displayed under Sensor Details section in Review screen"
	 * ); SoftAssert sa = new SoftAssert();
	 * sa.assertEquals(Setup_ReviewPage.Temp_state(), true,
	 * "Temp  is not available"); sa.assertEquals(Setup_ReviewPage.Humidity_state(),
	 * true, "Humidity  is not available");
	 * 
	 * sa.assertEquals(Setup_ReviewPage.Pressure_state(), true,
	 * "Pressure   is not available"); sa.assertAll(); }
	 * 
	 * // REV005-Verify that all details under Sensor Details section in Review
	 * screen // are displayed as entered in Sensor Configuration screen
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV005-Verify that all details under Sensor Details section in Review screen are displayed as entered in Sensor Configuration screen"
	 * ) public void REV005() throws InterruptedException, IOException { extentTest
	 * = extent.startTest(
	 * "REV005-Verify that all details under Sensor Details section in Review screen are displayed as entered in Sensor Configuration screen"
	 * ); SoftAssert sa = new SoftAssert();
	 * 
	 * sa.assertEquals(Setup_ReviewPage.FetchTemp_val(), "5",
	 * "Temp value is not available");
	 * sa.assertEquals(Setup_ReviewPage.FetchPsr_val(), "5",
	 * "Pressure value  is not available");
	 * 
	 * sa.assertAll(); }
	 * 
	 * // REV006-Verify that on-click of the edit icon for Sensor details section in
	 * // Review screen, navigates to Sensor configuration screen with values //
	 * pre-populated
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV006-Verify that on-click of the edit icon for Sensor details section in Review screen, navigates to Sensor configuration screen with values pre-populated"
	 * ) public void REV006() throws InterruptedException, IOException { extentTest
	 * = extent.startTest(
	 * "REV006-Verify that on-click of the edit icon for Sensor details section in Review screen, navigates to Sensor configuration screen with values pre-populated"
	 * ); SoftAssert sa = new SoftAssert(); String TmpVal =
	 * Setup_ReviewPage.FetchTemp_val(); String PsrVal =
	 * Setup_ReviewPage.FetchPsr_val();
	 * 
	 * Setup_SensorConfigPage = Setup_ReviewPage.click_EditSensorDetailsButton();
	 * 
	 * String TmpCount = Setup_SensorConfigPage.get_Temperature_text(); String
	 * PsrCount = Setup_SensorConfigPage.get_Pressure_text();
	 * 
	 * sa.assertEquals(TmpVal, TmpCount,
	 * "Temp value at review screen is not matching in sensor config page");
	 * sa.assertEquals(PsrVal, PsrCount,
	 * "Psr value at review screen is not matching in sensor config page");
	 * sa.assertAll();
	 * 
	 * }
	 * 
	 * //REV007-Verify the details displayed under Report Header section in Review
	 * screen
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV007-Verify the details displayed under Report Header section in Review screen"
	 * ) public void REV007() throws InterruptedException, IOException { extentTest
	 * = extent
	 * .startTest("REV007-Verify the details displayed under Report Header section in Review screen"
	 * );
	 * 
	 * SoftAssert sa = new SoftAssert();
	 * sa.assertEquals(Setup_ReviewPage.Asset_ID_Report_state(), true,
	 * "Asset_ID in Report section is not available");
	 * sa.assertEquals(Setup_ReviewPage.SOP_Protocol_Report_state(), true,
	 * "SOP_Protocol  in Report section is not available");
	 * sa.assertEquals(Setup_ReviewPage.Load_Description_Report_State(), true,
	 * "Load_Description in Report section is not available");
	 * sa.assertEquals(Setup_ReviewPage.Comments_report_State(), true,
	 * "Comments_report_State in Report section is not available"); sa.assertAll();
	 * 
	 * }
	 * 
	 * // REV008-Verify that all details under Report Header section in Review
	 * screen are displayed as entered in Define Setup screen
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV008-Verify that all details under Report Header section in Review screen are displayed as entered in Define Setup screen"
	 * ) public void REV008() throws InterruptedException, IOException { extentTest
	 * = extent.startTest(
	 * "REV008-Verify that all details under Report Header section in Review screen are displayed as entered in Define Setup screen"
	 * ); SoftAssert sa = new SoftAssert();
	 * sa.assertEquals(Setup_ReviewPage.FetchAssetID_Value_Reportsection(), "01",
	 * "AssetID value is not matching");
	 * sa.assertEquals(Setup_ReviewPage.FetchSOP_Value_Reportsection(), "10",
	 * "SOP value is not matching");
	 * sa.assertEquals(Setup_ReviewPage.FetchLoad_Value_Reportsection(), "load",
	 * "load value is not matching");
	 * sa.assertEquals(Setup_ReviewPage.FetchCmnts_Value_Reportsection(), "com",
	 * "comments value is not matching"); sa.assertAll(); }
	 * 
	 * // REV009-Verify that on-click of the edit icon for Report Header section in
	 * // Review screen, navigates to Define Setup screen with values pre-populated
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV009-Verify that on-click of the edit icon for Report Header section in Review screen, navigates to Define Setup screen with values pre-populated"
	 * ) public void REV009() throws InterruptedException, IOException { extentTest
	 * = extent.startTest(
	 * "REV009-Verify that on-click of the edit icon for Report Header section in Review screen, navigates to Define Setup screen with values pre-populated"
	 * ); SoftAssert sa = new SoftAssert(); String AID =
	 * Setup_ReviewPage.FetchAssetID_Value_Reportsection(); String SOP =
	 * Setup_ReviewPage.FetchSOP_Value_Reportsection(); String LoadDesc =
	 * Setup_ReviewPage.FetchLoad_Value_Reportsection(); String cmnts =
	 * Setup_ReviewPage.FetchCmnts_Value_Reportsection();
	 * 
	 * defineSetupPage = Setup_ReviewPage.click_EditReportButton(); String setup_AID
	 * = defineSetupPage.get_AssetID_text(); String setup_SOP =
	 * defineSetupPage.Fetch_sop_text(); String setup_LoadDes =
	 * defineSetupPage.getLoadDesc_txt(); String setup_cmnts =
	 * defineSetupPage.get_defineSetupPage_comments_txtData();
	 * 
	 * sa.assertEquals(setup_AID, AID,
	 * "AID value in review screen  is not matching with definesetup page");
	 * sa.assertEquals(setup_SOP, SOP,
	 * "SOP value in review screen  is not matching with definesetup page");
	 * sa.assertEquals(setup_LoadDes, LoadDesc,
	 * "Load Description value in review screen  is not matching with definesetup page"
	 * ); sa.assertEquals(setup_cmnts, cmnts,
	 * "Comments value in review screen  is not matching with definesetup page");
	 * sa.assertAll(); }
	 * 
	 * // REV010-Verify the details displayed under Groups section in Review screens
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV010-Verify the details displayed under Groups section in Review screens")
	 * public void REV010() throws InterruptedException, IOException { extentTest =
	 * extent.
	 * startTest("REV010-Verify the details displayed under Groups section in Review screens"
	 * );
	 * 
	 * SoftAssert sa = new SoftAssert();
	 * sa.assertEquals(Setup_ReviewPage.TotalGroups_State(), true,
	 * "TotalGroups in group section is not available");
	 * sa.assertEquals(Setup_ReviewPage.NumberOf_Groups_State(), true,
	 * "NumberOf_Groups is not available");
	 * sa.assertEquals(Setup_ReviewPage.GroupName_State(), true,
	 * "GroupName is not available");
	 * sa.assertEquals(Setup_ReviewPage.Sensors_Grp_State(), true,
	 * "sensors ingroup section is not available");
	 * sa.assertEquals(Setup_ReviewPage.Type_Group_State(), true,
	 * "Type in Group section is not available"); sa.assertAll();
	 * 
	 * }
	 * 
	 * //REV011-Verify that all details under Groups section in Review screen are
	 * displayed as entered in Group Sensors screen
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV011-Verify that all details under Groups section in Review screen are displayed as entered in Group Sensors screen"
	 * ) public void REV011() throws InterruptedException, IOException { extentTest
	 * = extent.startTest(
	 * "REV011-Verify that all details under Groups section in Review screen are displayed as entered in Group Sensors screen"
	 * );
	 * 
	 * SoftAssert sa = new SoftAssert();
	 * sa.assertEquals(Setup_ReviewPage.Temperature_State(), true,
	 * "Temperature_State is not available");
	 * sa.assertEquals(Setup_ReviewPage.Pressure_State(), true,
	 * "Pressure_State is not available");
	 * 
	 * sa.assertAll(); }
	 * 
	 * //REV012-Verify that on-click of the edit icon for Groups section in Review
	 * screen, navigates to Group Sensors screen with values pre-populated
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV012-Verify that on-click of the edit icon for Groups section in Review screen, navigates to Group Sensors screen with values pre-populated"
	 * ) public void REV012() throws InterruptedException, IOException { extentTest
	 * = extent.startTest(
	 * "REV012-Verify that on-click of the edit icon for Groups section in Review screen, navigates to Group Sensors screen with values pre-populated"
	 * );
	 * 
	 * SoftAssert sa = new SoftAssert(); Setup_GroupSensorsPage =
	 * Setup_ReviewPage.EditGroupsButton_click(); }
	 * 
	 * // REV013-Verify the details displayed under Qualifications Parameters
	 * section // in Review screen // REV014-Verify that all details under
	 * Qualification Parameters section in // Review screen are displayed as entered
	 * in Qualification Parameters screen // both the test cases handeled in one
	 * script
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV013,REV014-Verify the details displayed under Qualifications Parameters section in Review screen,Verify that all details under Qualification Parameters section in Review screen are displayed as entered in Qualification Parameters screen"
	 * ) public void REV013() throws InterruptedException, IOException { extentTest
	 * = extent.startTest(
	 * "REV013,REV014-Verify the details displayed under Qualifications Parameters section in Review screen,Verify that all details under Qualification Parameters section in Review screen are displayed as entered in Qualification Parameters screen"
	 * );
	 * 
	 * SoftAssert sa = new SoftAssert();
	 * Setup_ReviewPage.click_bottom_Scrollbar(20);
	 * sa.assertEquals(Setup_ReviewPage.Fetch_StartQual_value(), "Manual",
	 * "Manual is not displaying");
	 * sa.assertEquals(Setup_ReviewPage.Fetch_StopQual_value(), "Manual",
	 * "Manual is not displaying");
	 * sa.assertEquals(Setup_ReviewPage.Fetch_samplingrate_value(), "5 Seconds",
	 * "5 Seconds is not displaying");
	 * sa.assertEquals(Setup_ReviewPage.Fetch_Transmissionrate_value(),
	 * "10 Seconds", "10 Seconds is not displaying");
	 * sa.assertEquals(Setup_ReviewPage.Fetch_RFInactive_value(), "-25 °C",
	 * "-25 °C is not displaying"); sa.assertAll(); }
	 * 
	 * // REV015-Verify that on-click of the edit icon for Qualification Parameters
	 * // section in Review screen, navigates to Qualification Parameters screen
	 * with // values pre-populated
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV015-Verify that on-click of the edit icon for Qualification Parameters section in Review screen, navigates to Qualification Parameters screen with values pre-populated"
	 * ) public void REV015() throws InterruptedException, IOException { extentTest
	 * = extent.startTest(
	 * "REV015-Verify that on-click of the edit icon for Qualification Parameters section in Review screen, navigates to Qualification Parameters screen with values pre-populated"
	 * );
	 * 
	 * SoftAssert sa = new SoftAssert();
	 * Setup_ReviewPage.click_bottom_Scrollbar(20); String startQual =
	 * Setup_ReviewPage.Fetch_StartQual_value(); String stopqual =
	 * Setup_ReviewPage.Fetch_StopQual_value(); String Srate =
	 * Setup_ReviewPage.Fetch_samplingrate_value(); String Trate =
	 * Setup_ReviewPage.Fetch_Transmissionrate_value();
	 * System.out.println(startQual); Setup_QualParamPage =
	 * Setup_ReviewPage.click_Edit_QualParametersBtn();
	 * sa.assertEquals(Setup_QualParamPage.get_Txt_QStartDrpDwn(), startQual,
	 * "Manual is not displaying");
	 * System.out.println(Setup_QualParamPage.get_Txt_QStartDrpDwn());
	 * sa.assertEquals(Setup_QualParamPage.get_Txt_Qstop_DrpDwnBox(), stopqual,
	 * "Manual is not displaying");
	 * sa.assertEquals(Setup_QualParamPage.fetch_DefaultTxt_SR(), Srate,
	 * "5 Seconds is not displaying");
	 * sa.assertEquals(Setup_QualParamPage.FetchDfltTxt_TR_DrpDwnBox(), Trate,
	 * "10 Seconds is not displaying"); sa.assertAll(); }
	 * 
	 * // REV016-Verify the details displayed under Calculations section in Review
	 * // screen // REV017-Verify that all details under Calculations section in
	 * Review screen // are displayed as entered in Calculations screen // This two
	 * Test cases has managed in one script
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV016,REV017-Verify the details displayed under Calculations section in Review screen,Verify that all details under Calculations section in Review screen are displayed as entered in Calculations screen"
	 * ) public void REV016() throws InterruptedException, IOException { extentTest
	 * = extent.startTest(
	 * "REV016,REV017-Verify the details displayed under Calculations section in Review screen,Verify that all details under Calculations section in Review screen are displayed as entered in Calculations screen"
	 * );
	 * 
	 * SoftAssert sa = new SoftAssert();
	 * Setup_ReviewPage.click_bottom_Scrollbar(20);
	 * sa.assertEquals(Setup_ReviewPage.Fetch_BaseTemp_value(), "121.1 °C",
	 * "Base Temp value is not 121.1 °C");
	 * sa.assertEquals(Setup_ReviewPage.Fetch_ZValueTextBlock(), "10.0",
	 * "Zvalue is not 10.0");
	 * sa.assertEquals(Setup_ReviewPage.Fetch_DValueTextBlock(), "1.00",
	 * "Dvalue is not 121.1 °C");
	 * sa.assertEquals(Setup_ReviewPage.Fetch_CL_TextBlock(), "Undefined",
	 * "CL value is not Undefined");
	 * sa.assertEquals(Setup_ReviewPage.Is_SaturationPressureOfSteam_IsDisplayed(),
	 * true, "Is_Saturation Pressure Of Steam is not displayed");
	 * sa.assertEquals(Setup_ReviewPage.Is_SaturationTemperatureOfSteam_IsDisplayed(
	 * ), true, "Is_Saturation Temperature Of Steam is not displayed");
	 * sa.assertAll(); }
	 * 
	 * //REV018-Verify that on-click of the edit icon for Calculations section in
	 * Review screen, navigates to Calculations screen with values pre-populated
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV016,REV017-Verify the details displayed under Calculations section in Review screen,Verify that all details under Calculations section in Review screen are displayed as entered in Calculations screen"
	 * ) public void REV018() throws InterruptedException, IOException { extentTest
	 * = extent.startTest(
	 * "REV016,REV017-Verify the details displayed under Calculations section in Review screen,Verify that all details under Calculations section in Review screen are displayed as entered in Calculations screen"
	 * );
	 * 
	 * SoftAssert sa = new SoftAssert(); Setup_CalculationsPage =
	 * Setup_ReviewPage.click_EditCalculationBtn();
	 * Setup_CalculationsPage.click_leth_Btn();
	 * 
	 * sa.assertEquals(Setup_CalculationsPage.BTemp_text(), "121.1",
	 * "Dvalue is not available");
	 * 
	 * sa.assertEquals(Setup_CalculationsPage.ZValueField_text(), "10.0",
	 * "BTemp value is not available");
	 * 
	 * sa.assertEquals(Setup_CalculationsPage.DValueField_text(), "1.00",
	 * "BTemp value is not available");
	 * sa.assertEquals(Setup_CalculationsPage.getText_From_ClethBox(), "Undefined",
	 * "Undefined value is not available");
	 * 
	 * sa.assertAll(); }
	 * 
	 * // REV019-Verify that on-click of Save button will save the details
	 * successfully
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV019-Verify that on-click of Save button will save the details successfully"
	 * ) public void REV019() throws InterruptedException, IOException { extentTest
	 * = extent.
	 * startTest("REV019-Verify that on-click of Save button will save the details successfully"
	 * );
	 * 
	 * SoftAssert sa = new SoftAssert();
	 * 
	 * Setup_ReviewPage.clickSaveBtn();
	 * 
	 * sa.assertEquals(Setup_ReviewPage.UserLoginPopupVisible(), true,
	 * "Fail : User Login Popup is not displaying"); sa.assertAll(); }
	 * 
	 * //REV020-Verify that after setup is saved, the Copy as new setup and Create
	 * setup report buttons are enabled
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV020-Verify that after setup is saved, the Copy as new setup and Create setup report buttons are enabled"
	 * ) public void REV020() throws InterruptedException, IOException { extentTest
	 * = extent.startTest(
	 * "REV020-Verify that after setup is saved, the Copy as new setup and Create setup report buttons are enabled"
	 * );
	 * 
	 * SoftAssert sa = new SoftAssert();
	 * sa.assertEquals(Setup_ReviewPage.CopyAsNewSetup_Btn_state(), false,
	 * "CopyAsNewSetup_Btn is enable before setup is saved");
	 * 
	 * sa.assertEquals(Setup_ReviewPage.CreateSetupReport_Btn_state(), false,
	 * "CreateSetupReport_Btn  is enable before setup is saved");
	 * Setup_ReviewPage.clickSaveBtn();
	 * Setup_ReviewPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
	 * sa.assertEquals(Setup_ReviewPage.CopyAsNewSetup_Btn_state(), true,
	 * "CopyAsNewSetup_Btn is  not enable after setup is saved");
	 * sa.assertEquals(Setup_ReviewPage.CreateSetupReport_Btn_state(), true,
	 * "CreateSetupReport_Btn  is not  enable after setup is saved");
	 * 
	 * sa.assertAll(); }
	 * 
	 * 
	 * //REV021-Verify that on-click of Copy as new setup button copies the same
	 * setup with new Setup name //REV022-Verify that the copied setup is displayed
	 * in the current Asset
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV021,REV022-Verify that on-click of Copy as new setup button copies the same setup with new Setup name,REV022-Verify that the copied setup is displayed in the current Asset"
	 * ) public void REV021() throws InterruptedException, IOException { extentTest
	 * = extent.startTest(
	 * "REV021,REV022-Verify that on-click of Copy as new setup button copies the same setup with new Setup name,REV022-Verify that the copied setup is displayed in the current Asset"
	 * );
	 * 
	 * SoftAssert sa = new SoftAssert(); Setup_ReviewPage.clickSaveBtn();
	 * Setup_ReviewPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
	 * Setup_ReviewPage.click_CopyAsNewSetup_Button();
	 * Setup_ReviewPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
	 * Setup_ReviewPage.Enter_NewSetupName("New Setup"); String Actmsg =
	 * Setup_ReviewPage.AlertMsg(); String Expmsg = "Data saved successfully";
	 * sa.assertEquals(Actmsg, Expmsg,
	 * "Fail : data saved message is not displaying");
	 * 
	 * assetDetailsPage = Setup_ReviewPage.click_backBtn();
	 * 
	 * sa.assertEquals(assetDetailsPage.setupcount(), 1,
	 * "copy as new setup has not happened"); sa.assertAll();
	 * 
	 * }
	 * 
	 * //REV023-Verify that on-click of Create Setup report button will generate the
	 * setup report
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV023-Verify that on-click of Create Setup report button will generate the setup report"
	 * ) public void REV023() throws InterruptedException, IOException, AWTException
	 * { extentTest = extent
	 * .startTest("REV023-Verify that on-click of Create Setup report button will generate the setup report"
	 * );
	 * 
	 * SoftAssert sa = new SoftAssert(); Setup_QualParamPage =
	 * Setup_ReviewPage.click_Edit_QualParametersBtn();
	 * Setup_QualParamPage.select_SR("3 Seconds"); Setup_ReviewPage =
	 * Setup_QualParamPage.Click_NxtBtn(); Setup_ReviewPage.clickSaveBtn();
	 * Setup_ReviewPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull")); //
	 * Setup_QualParamPage.Select_20sec_SR();
	 * Setup_ReviewPage.create_setupReport_Button();
	 * Setup_ReviewPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
	 * Setup_ReviewPage.click_PDFpopup_OkBtn(); Thread.sleep(3000);
	 * Setup_ReviewPage.perform_alt_tab_OP(); }
	 * 
	 * //REV024-Verify that on-click of Create Setup report button when edited the
	 * setup and not saved, will display a validation message to save the setup
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "'REV024-Verify that on-click of Create Setup report button when edited the setup and not saved, will display a validation message to save the setup"
	 * ) public void REV024() throws InterruptedException, IOException, AWTException
	 * { extentTest = extent
	 * .startTest("'REV024-Verify that on-click of Create Setup report button when edited the setup and not saved, will display a validation message to save the setup"
	 * );
	 * 
	 * SoftAssert sa = new SoftAssert(); Setup_ReviewPage.clickSaveBtn();
	 * Setup_ReviewPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
	 * Setup_QualParamPage = Setup_ReviewPage.click_QualificationparametersBtn();
	 * Setup_QualParamPage.select_SR("1 Second"); Setup_ReviewPage =
	 * Setup_QualParamPage.Click_NxtBtn();
	 * Setup_ReviewPage.create_setupReport_Button(); String ActMsg =
	 * Setup_ReviewPage.AlertMsg(); String ExpMsg =
	 * "Setup has been modified please save the setup before creating report";
	 * sa.assertEquals(ActMsg, ExpMsg,
	 * "Actual message and Exepected message is not matching"); sa.assertAll(); }
	 * 
	 * //REV025-Verify that on-click of Create Setup report button when edited the
	 * setup and saved, will save the setup successfully
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV025-Verify that on-click of Create Setup report button when edited the setup and saved, will save the setup successfully"
	 * ) public void REV025() throws InterruptedException, IOException, AWTException
	 * { extentTest = extent.startTest(
	 * "REV025-Verify that on-click of Create Setup report button when edited the setup and saved, will save the setup successfully"
	 * );
	 * 
	 * SoftAssert sa = new SoftAssert(); Setup_ReviewPage.clickSaveBtn();
	 * Setup_ReviewPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
	 * 
	 * Setup_QualParamPage = Setup_ReviewPage.click_QualificationparametersBtn();
	 * Setup_QualParamPage.select_SR("2 Seconds"); Setup_ReviewPage =
	 * Setup_QualParamPage.Click_NxtBtn(); Setup_ReviewPage.clickSaveBtn();
	 * Setup_ReviewPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
	 * 
	 * Setup_ReviewPage.create_setupReport_Button();
	 * Setup_ReviewPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
	 * Setup_ReviewPage.click_PDFpopup_OkBtn(); Thread.sleep(3000);
	 * Setup_ReviewPage.perform_alt_tab_OP(); }
	 * 
	 * //REV026-Verify that when edited any value by navigating through Review
	 * screen edit option, will be reflected correctly in Review screen
	 * 
	 * @Test(groups = { "Regression" }, description =
	 * "REV026-Verify that when edited any value by navigating through Review screen edit option, will be reflected correctly in Review screen"
	 * ) public void REV026() throws InterruptedException, IOException, AWTException
	 * { extentTest = extent.startTest(
	 * "REV026-Verify that when edited any value by navigating through Review screen edit option, will be reflected correctly in Review screen"
	 * );
	 * 
	 * SoftAssert sa = new SoftAssert();
	 * Setup_ReviewPage.click_bottom_Scrollbar(20); String BeforeEdit_srVal =
	 * Setup_ReviewPage.Fetch_samplingrate_value();
	 * System.out.println(BeforeEdit_srVal); Setup_QualParamPage =
	 * Setup_ReviewPage.click_QualificationparametersBtn();
	 * Setup_QualParamPage.select_SR("2 Seconds"); Setup_ReviewPage =
	 * Setup_QualParamPage.Click_NxtBtn(); String AfterEdit_srVal =
	 * Setup_ReviewPage.Fetch_samplingrate_value();
	 * System.out.println(AfterEdit_srVal); sa.assertNotEquals(BeforeEdit_srVal,
	 * AfterEdit_srVal, "Before edit value is matching with after edit value");
	 * sa.assertEquals(AfterEdit_srVal, "2 Seconds", "value has not updated");
	 * sa.assertAll();
	 * 
	 * }
	 */
	// REV027-Verify that on-click of copy as new setup button when edited the setup
	// and saved, will copy the setup with edited values

	@Test(groups = {
			"Regression" }, description = "REV027-Verify that on-click of copy as new setup button when edited the setup and saved, will copy the setup with edited values")
	public void REV027() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"REV027-Verify that on-click of copy as new setup button when edited the setup and saved, will copy the setup with edited values");

		SoftAssert sa = new SoftAssert();

		defineSetupPage = Setup_ReviewPage.click_EditReportButton();
		defineSetupPage.enter_defineSetupPage_LoadDesc("Updated");
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_NxtBtn();
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
		Setup_ReviewPage.clickSaveBtn();
		Setup_ReviewPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Setup_ReviewPage.click_CopyAsNewSetup_Button();
		Setup_ReviewPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Setup_ReviewPage.Enter_NewSetupName("TestSetup");
		Thread.sleep(1000);
		assetDetailsPage = Setup_ReviewPage.click_backBtn();
		defineSetupPage = assetDetailsPage.editStupBtn_Position_0();
		sa.assertEquals(defineSetupPage.getLoadDesc_txt(), "Updated", "value is not displaying ");
		sa.assertAll();

	}

	// REV028-Verify that on-click of the back icon, user is navigated to Asset
	// details screen
	@Test(groups = {
			"Regression" }, description = "REV028-Verify that on-click of the back icon, user is navigated to Asset details screen")
	public void REV028() throws InterruptedException, IOException, AWTException {
		extentTest = extent
				.startTest("REV028-Verify that on-click of the back icon, user is navigated to Asset details screen");

		SoftAssert sa = new SoftAssert();
		Setup_ReviewPage.clickSaveBtn();
		Setup_ReviewPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage = Setup_ReviewPage.click_backBtn();
		sa.assertEquals(assetDetailsPage.assetDetailPageTitle_Visible(), true,
				"asset Detail Page Title is not visible");
		sa.assertAll();
	}

	// REV028A-Verify that on-click of the back icon,Application will displayan
	// alert message
	@Test(groups = {
			"Regression" }, description = "REV028A-Verify that on-click of the back icon,Application will displayan alert message")
	public void REV028A() throws InterruptedException, IOException, AWTException {
		extentTest = extent
				.startTest("REV028A-Verify that on-click of the back icon,Application will displayan alert message");

		SoftAssert sa = new SoftAssert();
		assetDetailsPage = Setup_ReviewPage.click_backBtn_WithAlert();
		sa.assertEquals(assetDetailsPage.assetDetailPageTitle_Visible(), true,
				"asset Detail Page Title is not visible");
		sa.assertAll();
	}

	// REV029-Verify that on-click of the Qualification parameters navigator in
	// Review screen, will display the Qualification parameters screen
	@Test(groups = {
			"Regression" }, description = "REV029-Verify that on-click of the Qualification parameters navigator in Review screen, will display the Qualification parameters screen")
	public void REV029() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"REV029-Verify that on-click of the Qualification parameters navigator in Review screen, will display the Qualification parameters screen");

		SoftAssert sa = new SoftAssert();

		Setup_QualParamPage = Setup_ReviewPage.click_QualificationparametersBtn();

		sa.assertEquals(Setup_QualParamPage.QualParamsPage_state(), true, "Landed to wrong page");
		sa.assertAll();
	}

	// REV030-Verify that Audit trail displays the entry for setup creation

	@Test(groups = {
			"Regression" }, description = "REV030-Verify that Audit trail displays the entry for setup creation")
	public void REV030() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest("REV030-Verify that Audit trail displays the entry for setup creation");

		SoftAssert sa = new SoftAssert();
		Setup_ReviewPage.clickSaveBtn();
		Setup_ReviewPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage = Setup_ReviewPage.click_backBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);

		String Actionmsg = AuditPage.get_auditEvent_text();
		System.out.println(Actionmsg);
		String ExpectMSG = "  Setup \"test\" is created by User ID : \"1\", User Name : \"User1\"";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL:The Audit trail record for Edit Assets activity is not exist ");
		sa.assertAll();
	}

}
