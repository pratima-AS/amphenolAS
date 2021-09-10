package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;

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
import com.vrt.utility.TestUtilities;
import com.vrt.utility.setupCreationUtility;

public class setup_DefineSetupTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>SetupTestData.xlsx sheet for test data i/p

	public setup_DefineSetupTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	TestUtilities tu = new TestUtilities();
	public ExtentReports extent;
	public ExtentTest extentTest;

	// Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	Setup_defineSetupPage defineSetupPage;
	Setup_SensorConfigPage SensorConfigPage;

	// Before All the tests are conducted
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, ParseException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER" + "_Setup_defineSetupTest.html",
				true);
		extent.addSystemInfo("TestSuiteName", "Setup_DefineSetupTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name2"));
		System.out.println("setup_defineSetupTest in Progress..");

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
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath", "aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "2", "Weeks", "1st Asset Creation");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		AppClose();
		Thread.sleep(500);

	}

	// After All the tests are conducted
	@AfterClass
	public void endReport_releaseMomory() {
		extent.flush();
		extent.close();
		assetHubPage.resetWebElements();
		// //System.out.println("Reset Webelement memory released");
		System.out.println("setup_defineSetupTest Completed.");
	}

	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
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

	// **********
	// Test Cases
	// **********

	// SET 001-Verify the details displayed in _Define Setup_ screen
	@Test(groups = { "Regression" }, description = "SET 001-Verify the details displayed in _Define Setup_ screen")
	public void SET001() throws InterruptedException, IOException {
		extentTest = extent.startTest("SET 001-Verify the details displayed in _Define Setup_ screen");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(defineSetupPage.get_defineSetupPage_Nametext(), "Define Setup",
				"Fail: Define Setup page Name mismatch");
		sa.assertTrue(defineSetupPage.visible_SetupNameField(), "Fail: Setup Name field not displayed");
		sa.assertTrue(defineSetupPage.visible_SetupSensorCountField(), "Fail: Sensor Count field not displayed");
		sa.assertTrue(defineSetupPage.visible_SetupAssetIDField(), "Fail: Asset ID field not displayed");
		sa.assertTrue(defineSetupPage.visible_SOPField(), "Fail: SOP field not displayed");
		sa.assertTrue(defineSetupPage.visible_LoadDescField(), "Fail: Load Description field not displayed");
		sa.assertTrue(defineSetupPage.visible_Comments_Field(), "Fail: Comments field not displayed");
		sa.assertTrue(defineSetupPage.visible_SensConfig_NxtBtn(), "Fail: Sensor Config Next button not displayed");

		String DefineSetupTitleTxt = defineSetupPage.get_defineSetupPage_Titletext();
		String StHd1 = DefineSetupTitleTxt.split("-")[0];
		//// System.out.println(StHd1);
		String StHd2 = DefineSetupTitleTxt.split("-")[1];
		//// System.out.println(StHd2);
		String StHd3 = DefineSetupTitleTxt.split("-")[2];
		//// System.out.println(StHd3);

		defineSetupPage.click_defineSetupPage_backBtn();
		assetDetailsPage = defineSetupPage.click_YesofAlert_msg();
		String AstHeaderTxt = assetDetailsPage.assetDetail_PageTitle();
		String ADHd1 = AstHeaderTxt.split(" - ")[0];
		//// System.out.println(ADHd1);
		String ADHd2 = AstHeaderTxt.split(" - ")[1];
		//// System.out.println(ADHd2);

		sa.assertEquals(StHd1, "New Setup", "FAIL: SET 010-Define Setup Header text mismatches for New Setup String");
		sa.assertEquals(StHd2, ADHd1, "FAIL: SET 010-Define Setup Header text mismatches for Asset Type String");
		sa.assertEquals(StHd3, ADHd2, "FAIL: SET 010-Define Setup Header text mismatches for Asset Name String");

		sa.assertAll();
	}

	// SET002-Verify that current Date and Time is the default value for Setup Name field
	@Test(groups = { "Sanity",
			"Regression" }, description = "SET002-Verify that current Date and Time is the default value for Setup Name field")
	public void SET002() throws InterruptedException, ParseException, IOException {
		extentTest = extent
				.startTest("SET002-Verify that current Date and Time is the default value for Setup Name field");
		SoftAssert sa = new SoftAssert();

		String crntDate = tu.get_CurrentDate_inCertainFormat("dd:MMM:yyyy:HH:mm:ss");
		// System.out.println(crntDate);
		sa.assertEquals(defineSetupPage.get_setupName_txtData(), crntDate, "FAIL: Date is not matching ");

	}

	// SET003
	@Test(groups = { "Sanity",
			"Regression" }, dataProvider = "SET003", dataProviderClass = setupCreationUtility.class, description = "Verify that max 35 characters are allowed in Setup name field")
	public void SET003(Object... dataProvider) throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("SET003-Verify that max 35 characters are allowed in Setup name field");
		SoftAssert sa = new SoftAssert();

		String SetUpName = (String) dataProvider[0];
		// System.out.println(SetUpName);
		String SensorNumb = (String) dataProvider[1];

		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetUpName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();

		sa.assertEquals(SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration",
				"FAIL: Max 35 characters are allowed in Setup name field");

		sa.assertAll();
	}

//SET004-Verify the valid inputs accepted in Setup name field

	@Test(groups = {
			"Regression" }, dataProvider = "SET004", dataProviderClass = setupCreationUtility.class, description = "SET 004-Verify the valid inputs accepted in Setup name field")
	public void SET004(String SetUpName, String SensorNumb) throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("SET 004-Verify the valid inputs accepted in Setup name file");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetUpName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();

		sa.assertEquals(SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration",
				"FAIL: Setup Name do not accept other characters except space,-,_,?,. and : ");

		sa.assertAll();
	}

	// SET 005
	@Test(groups = {
			"Regression" }, dataProvider = "SET005", dataProviderClass = setupCreationUtility.class, description = "Verify the invalid inputs not accepted in Setup name field")

	public void SET005(String SetUpName, String SensorCount, String ErrorAlertMsg)
			throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("SET005-Verify the invalid inputs not accepted in Setup name field");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetUpName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorCount);
		defineSetupPage.Click_defineSetupPage_Nxtbtn_Alert();

		sa.assertEquals(defineSetupPage.get_ButtomBarAlertmsg_txt(), ErrorAlertMsg,
				"FAIL: Setup Name do not accept other characters except space,-,_,?,. and : ");

		sa.assertAll();

	}

	// SET007-Verify that the header on the top left will be changed to the entered

	@Test(groups = {
			"Regression" }, description = "Verify that the header on the top left will be changed to the entered")

	public void SET007() throws InterruptedException, IOException {
		extentTest = extent.startTest("SET007-Verify that the header on the top left will be changed to the entered");
		SoftAssert sa = new SoftAssert();
		String SetupNameEntered = defineSetupPage.get_defineSetupPage_setupName();
////System.out.println(SetupNameEntered);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.enter_defineSetupPage_SensorCount("1");
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		String SetupNameDisplayed = SensorConfigPage.get_SensorConfigurationPage_titletext();
////System.out.println(SetupNameDisplayed);

		sa.assertEquals(SetupNameEntered, SetupNameDisplayed,
				"FAIL: SET007-Setup Name not displayed in the header of Sensor Config page of Setup");
		sa.assertAll();
	}

	// SET008
	@Test(groups = { "Sanity",
			"Regression" }, description = "SET008-Verify that max 3 characters are allowed in No of Max Sensors field")
	public void SET008() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SET008-Verify that max 3 characters are allowed in No of Max Sensors field i.e. from 1 to 300");
		SoftAssert sa = new SoftAssert();

		String SensorNumb1 = "301";

		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb1);
		defineSetupPage.click_defineSetupPage_LoadDescField();
		String SnsrCount = defineSetupPage.get_Sensorcount_text();
		// System.out.println(SnsrCount);

		sa.assertEquals(SnsrCount.length(), 3,
				"FAIL:SET008 - Setup Sensor Count field should accept the data max 3 characters i.e from 1 to 300 ");
		sa.assertAll();

	}

	// SET009-Verify the valid inputs accepted in No. of Max Sensors field
	@Test(groups = { "Sanity",
			"Regression" }, dataProvider = "SET009", dataProviderClass = setupCreationUtility.class, description = "Verify the valid inputs accepted in No. of Max Sensors field")
	public void SET009(Object... dataProvider) throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("SET009-Verify the valid inputs accepted in No. of Max Sensors field");
		SoftAssert sa = new SoftAssert();

		String SensorNumb = (String) dataProvider[0];
		// System.out.println(SensorNumb);

		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();

		sa.assertEquals(SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration",
				"FAIL:SET009 - Setup Sensor Count field should accept the Valid data ");
		sa.assertAll();

	}

	// SET010
	@Test(groups = {
			"Regression" }, dataProvider = "SET010", dataProviderClass = setupCreationUtility.class, description = "SET010-Verify the invalid inputs not accepted in No. of Max Sensors field")
	public void SET010(String SensorCount, String ErrorAlertMsg)
			throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("SET010-Verify the invalid inputs not accepted in No. of Max Sensors field");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorCount);
		defineSetupPage.Click_defineSetupPage_Nxtbtn_Alert();

		sa.assertEquals(defineSetupPage.get_ButtomBarAlertmsg_txt(), ErrorAlertMsg,
				"FAIL:SET010- Setup Sensor Count field should not accepts the In-Valid characters");

		sa.assertAll();

	}

	// SET011-Verify that max 300 sensors should be allowed in No. of Max Sensors
	// field
	@Test(groups = {
			"Regression" }, description = "Verify that max 300 sensors should be allowed in No. of Max Sensors field")

	public void SET011() throws InterruptedException {
		extentTest = extent
				.startTest("SET011-Verify that max 300 sensors should be allowed in No. of Max Sensors field");
		SoftAssert sa = new SoftAssert();
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.enter_defineSetupPage_SensorCount("20112");
		String SensorcountEntered = defineSetupPage.get_Sensorcount_text();
		defineSetupPage.click_defineSetupPage_commentsField();
		Thread.sleep(500);
		String SensorcountDisplayed = defineSetupPage.get_Sensorcount_text();
		sa.assertEquals(SensorcountEntered, SensorcountDisplayed,
				"FAIL: SET012-Setup Name not displayed in the header of Sensor Config page of Setup");
		sa.assertAll();
	}

	// SET012
	@Test(groups = {
			"Regression" }, description = "SET012-Verify that when more than 300 is entered in No. of Max Sensors field, the count is rounded off to 300")

	public void SET012() throws InterruptedException {
		extentTest = extent.startTest(
				"SET012-Verify that when more than 300 is entered in No. of Max Sensors field, the count is rounded off to 300");
		SoftAssert sa = new SoftAssert();
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.enter_defineSetupPage_SensorCount("301");
		// String SensorcountEntered = defineSetupPage.get_Sensorcount_text();
		defineSetupPage.click_defineSetupPage_commentsField();
		Thread.sleep(2000);
		String SensorcountDisplayed = defineSetupPage.get_Sensorcount_text();
		sa.assertEquals(SensorcountDisplayed, "300", "FAIL: SET012- the count should rounded off to 300");
		sa.assertAll();
	}

	// SET013

	@Test(groups = { "Sanity",
			"Regression" }, description = "Verify that Asset ID field is prepopulated and disabled,Grayed out in Define setup screen")
	public void SET013() throws InterruptedException {
		extentTest = extent.startTest(
				"SET013-Verify that Asset ID field is prepopulated and disabled,Grayed out in Define setup screen");
		SoftAssert sa = new SoftAssert();
		//// System.out.println(defineSetupPage.AssetIDEnable());
		sa.assertEquals(defineSetupPage.visible_SetupAssetIDField(), true, "FAIL:AssetID should be in disable");

		sa.assertEquals(defineSetupPage.get_AssetID_text(), "01", "FAIL:AssetID should be in disable");

		sa.assertAll();
	}

	// SET014
	@Test(groups = { "Sanity",
			"Regression" }, description = "Verify that when edited the Asset ID in edit assets screen, it is reflected correctly in Define setup screen")
	public void SET014() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"SET014-Verify that when edited the Asset ID in edit assets screen, it is reflected correctly in Define setup screen");
		SoftAssert sa = new SoftAssert();

		String AssetIDTxtinSetup = defineSetupPage.get_AssetID_text();
		//// System.out.println(AssetIDTxtinSetup);
		defineSetupPage.click_defineSetupPage_backBtn();
		assetDetailsPage = defineSetupPage.click_YesofAlert_msg();
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		String AssetIDTxtinAssetEditPage = assetCreationPage.getEqpID();
		// System.out.println(AssetIDTxtinAssetEditPage);

		sa.assertEquals(AssetIDTxtinAssetEditPage, AssetIDTxtinSetup,
				"FAIL: Asset ID field Data do not match with the actual Asset ID created");
		sa.assertAll();
	}

// SET015
	@Test(groups = {
			"Regression" }, dataProvider = "SET015", dataProviderClass = setupCreationUtility.class, description = "Verify that max 50 characters are allowed in SOP Protocol field")

	public void SET015(Object... dataProvider) throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("SET015-Verify that max 50 characters are allowed in SOP Protocol field");
		SoftAssert sa = new SoftAssert();

		String SensorNumb = (String) dataProvider[0];
		String SOP = (String) dataProvider[1];
		//// System.out.println(SOP);

		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		defineSetupPage.click_defineSetupPage_SOPField();
		defineSetupPage.clear_defineSetupPage_SOP();
		defineSetupPage.enter_defineSetupPage_SOP(SOP);
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();

		sa.assertEquals(SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration",
				"FAIL:SET015-Setup SOP field do NOT accept more than 50 characters data ");
		sa.assertAll();

	}

	// SET016-Verify the valid inputs accepted in SOP Protocol field

	@Test(groups = {
			"Regression" }, dataProvider = "SET016", dataProviderClass = setupCreationUtility.class, description = "Verify the valid inputs accepted in SOP Protocol field")

	public void SET016(Object... dataProvider) throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("SET016-Verify the valid inputs accepted in SOP Protocol field");
		SoftAssert sa = new SoftAssert();

		String SensorNumb = (String) dataProvider[0];
		String SOP = (String) dataProvider[1];
		// System.out.println(SOP);

		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		defineSetupPage.click_defineSetupPage_SOPField();
		defineSetupPage.clear_defineSetupPage_SOP();
		defineSetupPage.enter_defineSetupPage_SOP(SOP);
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();

		sa.assertEquals(SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration",
				"FAIL:SET015-Setup SOP field should accept the valid data ");
		sa.assertAll();

	}

// SET017-Verify the invalid inputs not accepted in SOP Protocol field
	@Test(groups = {
			"Regression" }, dataProvider = "SET017", dataProviderClass = setupCreationUtility.class, description = "Verify the invalid inputs not accepted in SOP Protocol field")

	public void SET017(String SensorCount, String SOP, String ExpAlrtMsg)
			throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("SET017-Verify the invalid inputs not accepted in SOP Protocol field");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorCount);
		defineSetupPage.click_defineSetupPage_SOPField();
		defineSetupPage.clear_defineSetupPage_SOP();
		defineSetupPage.enter_defineSetupPage_SOP(SOP);
		defineSetupPage.Click_defineSetupPage_Nxtbtn_Alert();

		sa.assertEquals(defineSetupPage.get_ButtomBarAlertmsg_txt(), ExpAlrtMsg,
				"FAIL: SET017-Verify the invalid inputs not accepted in SOP Protocol field");
		sa.assertAll();

	}

	// SET018
	@Test(groups = { "Sanity",
			"Regression" }, dataProvider = "SET018", dataProviderClass = setupCreationUtility.class, description = "SET018-Verify that max 50 characters are allowed in Load Description field")
	public void SET027a(String SensorNumb, String LD) throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("SET018-Verify that max 50 characters are allowed in Load Description field");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		defineSetupPage.click_defineSetupPage_LoadDescField();
		defineSetupPage.clear_defineSetupPage_LoadDesc();
		defineSetupPage.enter_defineSetupPage_LoadDesc(LD);
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();

		sa.assertEquals(SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration",
				"FAIL:SET018 - Setup Load Description field should allow max 50 characters ");
		sa.assertAll();

	}

// SET019
	@Test(groups = { "Sanity",
			"Regression" }, dataProvider = "SET019", dataProviderClass = setupCreationUtility.class, description = "SET019-Verify the valid inputs accepted in Load Description field")
	public void SET019(String SensorNumb, String LD) throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("SET019-Verify the valid inputs accepted in Load Description field");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		defineSetupPage.click_defineSetupPage_LoadDescField();
		defineSetupPage.clear_defineSetupPage_LoadDesc();
		defineSetupPage.enter_defineSetupPage_LoadDesc(LD);
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();

		sa.assertEquals(SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration",
				"FAIL:SET019 - Verify the valid inputs accepted in Load Description field ");
		sa.assertAll();

	}

	// SET020
	@Test(groups = {
			"Regression" }, dataProvider = "SET020", dataProviderClass = setupCreationUtility.class, description = "Verify the invalid inputs not accepted in Load Description field")
	public void SET020(String SensorCount, String LoadDescription, String ExpAlrtMsg)
			throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("SET020-Verify the invalid inputs not accepted in Load Description field");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorCount);
		defineSetupPage.click_defineSetupPage_LoadDescField();
		defineSetupPage.clear_defineSetupPage_LoadDesc();
		defineSetupPage.enter_defineSetupPage_LoadDesc(LoadDescription);
		defineSetupPage.Click_defineSetupPage_Nxtbtn_Alert();

		sa.assertEquals(defineSetupPage.get_ButtomBarAlertmsg_txt(), ExpAlrtMsg,
				"FAIL: SET020 - Setup Load Description field should not accepts In-Valid data ");
		sa.assertAll();

	}

// SET021

	@Test(groups = { "Regression" }, description = "Verify that max 25 characters are allowed in Comments field")
	public void SET021() throws InterruptedException, ParseException {
		extentTest = extent.startTest("SET021-Verify that max 25 characters are allowed in Comments field");
		SoftAssert sa = new SoftAssert();

		String expectedtxt = "1234567890123456789012345a"; // 26 Char input

		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount("1");
		defineSetupPage.click_defineSetupPage_commentsField();
		defineSetupPage.clear_defineSetupPage_comments();
		defineSetupPage.enter_defineSetupPage_comments(expectedtxt);
		String actualtextentered = defineSetupPage.get_defineSetupPage_comments_txtData();

		sa.assertEquals(actualtextentered.length(), 25,
				"FAIL: SET021- Setup Comment field  should not accepts more than 25 Character ");
		sa.assertAll();

	}
//SET022

	@Test(groups = { "Sanity",
			"Regression" }, dataProvider = "SET022", dataProviderClass = setupCreationUtility.class, description = "SET022-Verify the valid inputs accepted in Comments field")
	public void SET022(String SensorNumb, String Cmnt) throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("SET022-Verify the inputs accepted in Comments field");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		defineSetupPage.click_defineSetupPage_commentsField();
		defineSetupPage.clear_defineSetupPage_comments();
		defineSetupPage.enter_defineSetupPage_comments(Cmnt);
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();

		sa.assertEquals(SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration",
				"FAIL: SET022-valid inputs should be accepted in Comments field ");
		sa.assertAll();
	}

	// SET026
	@Test(groups = { "Sanity",
			"Regression" }, description = "SET026-Verify that on click of back button, user is navigated back to asset details screen with confirmation")
	public void SET026() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SET026-Verify that on click of back button, user is navigated back to asset details screen with confirmation_ with Yes and No buttons.");
		SoftAssert sa = new SoftAssert();
		defineSetupPage.click_defineSetupPage_backBtn();

		sa.assertEquals(defineSetupPage.visible_AlertMsg_state(), true,
				"FAIL: SET026- ALert message should be displayed on clicking the Setup Back Button");
		sa.assertAll();

	}

	// SET026A
	@Test(groups = { "Sanity",
			"Regression" }, description = "SET026A-Verify that on click of Yes button, user is navigated back to asset details screen with confirmation")
	public void SET026A() throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest(
				"SET026A-Verify that on click of yes button, user is navigated back to asset details screen with confirmation");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.click_defineSetupPage_backBtn();
		assetDetailsPage = defineSetupPage.click_YesofAlert_msg();

		sa.assertEquals(assetDetailsPage.get_Setupheader_txt(), "Setups",
				"FAIL: SET026A- it should take one to the Asset Details page");
		sa.assertAll();

	}

	// SET026B
	@Test(groups = {
			"Regression" }, description = "SET026B - Verify if clicking on _No_ allows the user to stay in the current page")
	public void SET026B() throws InterruptedException, ParseException {
		extentTest = extent
				.startTest("SET026B - Verify if clicking on _No_ allows the user to stay in the current page.");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.click_defineSetupPage_backBtn();
		defineSetupPage.click_NoofAlert_msg();
		;

		sa.assertEquals(defineSetupPage.defineSetupPage_state(), true,
				"FAIL: SET026B- licking on No should allows the user to stay in the current page");
		sa.assertAll();

	}

	// SET027
	@Test(groups = { "Sanity",
			"Regression" }, description = "Verify that on click of Sensor Configuration navigator, navigates user to Sensor Configuration screen")
	public void SET027() throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest(
				"SET027-Verify that on click of Sensor Configuration navigator, navigates user to Sensor Configuration screen");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.enter_defineSetupPage_SensorCount("2");
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();

		sa.assertEquals(SensorConfigPage.sensorConfigPage_state(), true,
				"FAIL: SET027- On click of Sensor Configuration button, user should navigate to Sensor Configuration screen");
		sa.assertAll();

	}

	// SET029
	@Test(description = "Verify the bottom menu options displayed in Define setup screen")
	public void SET029() throws InterruptedException {
		extentTest = extent.startTest("SET029-Verify the bottom menu options displayed in Define setup screen");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.Rt_Click_AstCreation_Buttom_AppBar();

		sa.assertEquals(defineSetupPage.check_Home_Buttom_AppBar_Presence(), true,
				"FAIL: Home icon/button missing in bottom app bar");
		sa.assertEquals(defineSetupPage.check_Help_Buttom_AppBar_Presence(), true,
				"FAIL: Help icon/button missing in bottom app bar");
		sa.assertEquals(defineSetupPage.check_WndsHelp_Buttom_AppBar_Presence(), true,
				"FAIL: Windows Help icon/button missing in bottom app bar");
		sa.assertEquals(defineSetupPage.check_About_Buttom_AppBar_Presence(), true,
				"FAIL: About icon/button missing in bottom app bar");
		sa.assertAll();
	}

	// SET030-Verify that on-click of home btn in bottom menu options is navigated
	// to main hub page

	@Test(description = "Verify that on-click of home btn in bottom menu options is navigated to main hub page")
	public void SET030() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"SET030-Verify that on-click of home btn in bottom menu options is navigated to main hub page");
		SoftAssert sa = new SoftAssert();

		MainHubPage = defineSetupPage.Click_Home_Icon_AppBar();

		sa.assertEquals(MainHubPage.Is_mainHubPageTitle_Visible(), true,
				"FAIL: Clicking Home icon/button in bottom app bar should be  redirect to Mains Hub page");
		sa.assertAll();
	}

	// SET031-Verify that on-click of help btn in bottom menu options displays
	// information about the Define setup screen

	@Test(description = "SET031-Verify the help btn functionality in bottom menu options " + "in Asset creation screen")
	public void SET031() throws InterruptedException {
		extentTest = extent.startTest(
				"ASST52-Verify the help btn functionality in bottom " + "menu options in Asset creation screen");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.Click_Help_Icon_AppBar();
		//// System.out.println(defineSetupPage.get_AsstCreation_HelpMenu_HdrText());
		sa.assertEquals(defineSetupPage.get_setupdefine_HelpMenu_HdrText(), "Define Setup",
				"FAIL: Clicking Help icon/button in bottom app bar should  display the setup define Help context window");
		sa.assertAll();
	}

	// SET032-Verify that on-click of windows help btn in bottom menu options
	// generates a PDF with information
	// SET032 will be handle manually

	// SET033
	@Test(description = "Verify that on-click of About btn in bottom menu options displays the software version and the console IP address")
	public void SET033() throws InterruptedException {
		extentTest = extent.startTest(
				"SET033-Verify that on-click of About btn in bottom menu options displays the software version and the console IP address");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.Click_About_Icon_AppBar();
		sa.assertEquals(defineSetupPage.check_About_wndw_Presence(), true,
				"FAIL: Clicking About icon/button in bottom app bar do not display the About window");
		sa.assertAll();
	}

}
