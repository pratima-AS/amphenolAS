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
import com.vrt.pages.Setup_defineSetupPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.Copyassetpage;
import com.vrt.pages.FileManagementPage;
import com.vrt.pages.SyncInPage;
import com.vrt.pages.SyncInAssetListPage;
import com.vrt.pages.CopySetuppage;
import com.vrt.pages.SelectBaseStationPage;
import com.vrt.pages.Setup_CalculationsPage;
import com.vrt.pages.Setup_GroupSensorsPage;
import com.vrt.pages.Setup_QualParamPage;
import com.vrt.pages.Setup_ReviewPage;
import com.vrt.pages.Setup_SensorConfigPage;
import com.vrt.pages.OverlayWiringImagePage;
import com.vrt.pages.RW_FileSelctionPage;
import com.vrt.pages.AuditPage;

import com.vrt.utility.TestUtilities;
import com.vrt.utility.assetCreationUtility;

public class CopyAssetTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p

	public CopyAssetTest() throws IOException {
		super();
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
	Setup_CalculationsPage Setup_CalculationsPage;
	Setup_QualParamPage Setup_QualParamPage;
	Setup_ReviewPage Setup_ReviewPage;
	Copyassetpage Copyassetpage;
	FileManagementPage FileManagementPage;
	SyncInPage SyncInPage;
	SyncInAssetListPage SyncInAssetListPage;
	RW_FileSelctionPage RWFileSelctionPage;
	CopySetuppage CopySetuppage;
	SelectBaseStationPage SelectBaseStationPage;
	OverlayWiringImagePage OverlayWiringImagePage;
	AuditPage AuditPage;

	// Before All the tests are conducted
	// @BeforeTest
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, AWTException, ParseException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_" + "copyAssetTest" + ".html",
				true);
		extent.addSystemInfo("TestSuiteName", "copyAssetTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("copyAssetTest Test in Progress..");

		// Rename the file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		// Rename the VRT folder if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTSetups");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\", "Cache");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTEquipments");

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
		UserManagementPage.clickPrivRunQual();
		UserManagementPage.clickPrivCreateEditAsset();
		UserManagementPage.clickPrivCreateEditSetup();
		UserManagementPage.clickPrivRunCal();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();

		// Method to Create 1st Asset
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetCreationPage.clickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(2000);

	}

	// After All the tests are conducted
	// @AfterTest
	@AfterClass
	public void endReport_releaseMomory() {
		extent.flush();
		extent.close();
		//assetDetailsPage.resetWebElements();
		System.out.println("copyAssetTest Completed.");
	}

	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
	}

	// After Method: TearDown of the App
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

	/******************************
	 * Copy Asset Test cases/scripts
	 *
	 ******************************/

	// CA001-Verify the on-click of Copy icon for Assets
	@Test(groups = { "Regression" }, description = "CA001-Verify the on-click of Copy icon for Assets")
	public void CA001() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest("CA001-Verify the on-click of Copy icon for Assets");
		SoftAssert sa = new SoftAssert();
		Copyassetpage = assetDetailsPage.clickCopyasset();

		sa.assertEquals(Copyassetpage.IsCopyAssetPageTitle_presence(), true,
				"FAIL: Copy Asset title is not displaying");
		sa.assertAll();
	}

	// CA002-Verify the details displayed in Copy Asset screen

	// CA003-Verify the details displayed for each setup in Copy Asset screen
	// This test cases also covered under CA002 script
	@Test(groups = {
			"Regression" }, dataProvider = "CA002", dataProviderClass = assetCreationUtility.class, description = "CA002-Verify the details displayed in Copy Asset screen")

	public void CA002(String SetupName, String SensorCount, String Comments, String TempCount, String TCSensorLabel,
			String Qstart) throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("CA002-Verify the details displayed in Copy Asset screen");
		SoftAssert sa = new SoftAssert();

// Define Setup
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetupName);
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorCount);
		defineSetupPage.enter_defineSetupPage_comments(Comments);
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(TempCount);
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To(TempCount);
		Setup_SensorConfigPage.Enter_SensorLabel(TCSensorLabel);
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
		Setup_ReviewPage.click_Save_Btn(Qstart, "Yes", getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage = Setup_ReviewPage.click_backBtn();

		assetDetailsPage.Click_SetupName(SetupName);
		Copyassetpage = assetDetailsPage.clickCopyasset();

		String sname_CApage = Copyassetpage.get_SETUPname();
		String sensors_CApage = Copyassetpage.get_Sensors();
		String comments_CApage = Copyassetpage.get_comments();

		sa.assertEquals(Copyassetpage.IsNewAssetName_Field_Visible(), true, "FAIL: Asset name  is not visible");
		sa.assertEquals(Copyassetpage.IsNewAssetID_Field_Visible(), true, "FAIL: Asset ID  is not visible");

		sa.assertEquals(SetupName, sname_CApage, "FAIL: Displayed Setup name  is not same with the created setp name");
		sa.assertEquals(SensorCount, sensors_CApage,
				"FAIL: Displayed Setup name  is not same with the created setp name");
		sa.assertEquals(Comments, comments_CApage,
				"FAIL: Displayed Setup name  is not same with the created setp name");

		sa.assertAll();
	}

	// CA003.1-Verify the max characters allowed in Asset name field
	@Test(groups = { "Regression" }, description = "CA003.1-Verify the max characters allowed in Asset name field")
	public void CA003A() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest("CA003.1-Verify the max characters allowed in Asset name field");
		SoftAssert sa = new SoftAssert();
		Copyassetpage = assetDetailsPage.clickCopyasset();

		// Here we have entered more than 25 numbers i.e 29

		Copyassetpage.Enter_NewAssetNameField("1ABc567b90123456789012345Ab");

		// Application will allow upto 25 chars
		String DisplayedAstname = Copyassetpage.get_NewAssetName_Text();
		sa.assertEquals(DisplayedAstname.length(), 25, "FAIL:Asset name  accepting more than 25 chars");

		sa.assertAll();
	}

	// CA004-Verify the valid inputs accepted in Asset name field
	@Test(groups = {
			"Regression" }, dataProvider = "CA004", dataProviderClass = assetCreationUtility.class, description = "CA004-Verify the valid inputs accepted in Asset name field")

	public void CA004(String NewAssetName, String NewAssetid)
			throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("CA004-Verify the valid inputs accepted in Asset name field");
		SoftAssert sa = new SoftAssert();
		Copyassetpage = assetDetailsPage.clickCopyasset();
		Copyassetpage.copyAsset_Creation(NewAssetName, NewAssetid);
		sa.assertEquals(Copyassetpage.UserLoginPopupVisible(), true,
				"FAIL: Asset name and Id  do not accept valid characters");
		sa.assertAll();
	}

	// CA005-Verify the Invalid inputs accepted in Asset name field
	@Test(groups = {
			"Regression" }, dataProvider = "CA005", dataProviderClass = assetCreationUtility.class, 
					description = "CA005-Verify the Invalid inputs accepted in Asset name field")
	public void CA005(String NewAssetName) throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("CA005-Verify the Invalid inputs accepted in Asset name field");
		SoftAssert sa = new SoftAssert();
		Copyassetpage = assetDetailsPage.clickCopyasset();
		Copyassetpage.Enter_NewAssetNameField(NewAssetName);
		Copyassetpage.Enter_NewAssetIDField("ca005");
		Copyassetpage.click_copy_Btn();

		String ActAlrtMsg = tu.get_AlertMsg_text();
		String ExpAlrtMsg = "Asset name accepts alpha numeric and special characters like "
				+ "space, -,_ ,slash (forward and backward)";
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Alert message is not displayed");
		sa.assertAll();

	}

	// This CA005A test case is a part of CA005 test case step

	// CA005A- Keep Asset name field blank and enter valid values in all other
	// fields and save changes
	@Test(groups = {
			"Regression" }, description = "CA005A- Keep Asset name field blank and enter valid values "
					+ "in all other fields and save changes")

	public void CA005A() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"CA005A- Keep Asset name field blank and enter valid values in all other fields and save changes");
		SoftAssert sa = new SoftAssert();
		Copyassetpage = assetDetailsPage.clickCopyasset();
		Copyassetpage.copyAsset_Creation("", "4");
		String ActAlrtMsg = tu.get_AlertMsg_text();
		String ExpAlrtMsg = "New Asset Name is mandatory, please enter New Asset Name";
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Alert message is not displayed");
		sa.assertAll();
	}

	// CA005B- verify when user Enter an Asset name which is already existing and
	// Enter new valid Asset ID and select any setup. Click on copy button

	@Test(groups = {
			"Regression" }, description = "CA005B- verify when user Enter an Asset name which is already existing and Enter new valid Asset ID and select any setup. Click on copy button")

	public void CA005B() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"CA005B- verify when user Enter an Asset name which is already existing and Enter new valid Asset ID and select any setup. Click on copy button");

		SoftAssert sa = new SoftAssert();
		assetDetailsPage.Click_SetupName("CA002");
		Copyassetpage = assetDetailsPage.clickCopyasset();
		Copyassetpage.copyAsset_Creation("CA005B", "5");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage = Copyassetpage.clickBack_Button();

		Copyassetpage = assetDetailsPage.clickCopyasset();
		Copyassetpage.copyAsset_Creation("CA005B", "12");
		Copyassetpage.Click_Checkbox();

		String ActAlrtMsg = tu.get_AlertMsg_text();
		String ExpAlrtMsg = "New Asset Name should be unique";
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Alert message is not displayed");
		sa.assertAll();
	}

	// CA005C-Verify when user enter only special characters to the Asset name field

	@Test(groups = {
			"Regression" }, description = "CA005C-Verify when user enter only special charecters to the Asset name field")

	public void CA005C() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("CA005C-Verify when user enter only special charecters to the Asset name field");
		SoftAssert sa = new SoftAssert();
		Copyassetpage = assetDetailsPage.clickCopyasset();
		Copyassetpage.Enter_NewAssetNameField("!@#!$#%$^#%$&^*");
		Copyassetpage.click_copy_Btn();
		String ActAlrtMsg = tu.get_AlertMsg_text();
		String ExpAlrtMsg = "New Asset Name should have at least one letter or one digit";
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Alert message is not displayed");
		sa.assertAll();
	}

	// CA006-Verify the max characters allowed in Asset ID field
	@Test(groups = { "Regression" }, description = "CA006-Verify the max characters allowed in Asset ID field")
	public void CA006() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest("CA006-Verify the max characters allowed in Asset ID field");
		SoftAssert sa = new SoftAssert();
		Copyassetpage = assetDetailsPage.clickCopyasset();

		// Here we have entered more than 15 numbers i.e 19

		Copyassetpage.Enter_NewAssetIDField("1ABc567b90123456");

		// Application will allow upto 15 chars
		String DisplayedAstID = Copyassetpage.get_NewAssetID_Text();
		sa.assertEquals(DisplayedAstID.length(), 15, "FAIL: Asset Id accepting more than 15 chars");

		sa.assertAll();
	}

	// CA007-Verify the valid inputs accepted in Asset ID field
	@Test(groups = {
			"Regression" }, dataProvider = "CA007", dataProviderClass = assetCreationUtility.class, description = "CA007-Verify the valid inputs accepted in Asset ID field")

	public void CA007(String NewAssetName, String NewAssetid)
			throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("CA007-Verify the valid inputs accepted in Asset ID field");
		SoftAssert sa = new SoftAssert();
		Copyassetpage = assetDetailsPage.clickCopyasset();
		Copyassetpage.copyAsset_Creation(NewAssetName, NewAssetid);
		sa.assertEquals(Copyassetpage.UserLoginPopupVisible(), true,
				"FAIL: Asset name and Id  do not accept valid characters");
		sa.assertAll();
	}

	// CA008-Verify the invalid inputs in Asset ID field

	@Test(groups = {
			"Regression" }, dataProvider = "CA008", dataProviderClass = assetCreationUtility.class, description = "CA008-Verify the invalid inputs in Asset ID field")

	public void CA008(String NewAssetid) throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("CA008-Verify the invalid inputs in Asset ID field");
		SoftAssert sa = new SoftAssert();
		Copyassetpage = assetDetailsPage.clickCopyasset();
		Copyassetpage.Enter_NewAssetIDField(NewAssetid);
		Copyassetpage.click_copy_Btn();

		String ActAlrtMsg = tu.get_AlertMsg_text();
		String ExpAlrtMsg = "New Asset Name is mandatory, please enter New Asset Name";
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Alert message is not displayed");
		sa.assertAll();

	}

	// This CA008A test case is a part of CA008 test case step

	// CA008A- Keep Asset ID field blank and enter valid values in all other fields
	// and save changes

	@Test(groups = {
			"Regression" }, description = "CA008A- Keep Asset ID field blank and enter valid values in all other fields and save changes")

	public void CA008A() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"CA008A- Keep Asset ID field blank and enter valid values in all other fields and save changes");
		SoftAssert sa = new SoftAssert();
		Copyassetpage = assetDetailsPage.clickCopyasset();
		Copyassetpage.copyAsset_Creation("Atxt", "");
		String ActAlrtMsg = tu.get_AlertMsg_text();
		String ExpAlrtMsg = "New Asset ID  is mandatory, please enter New Asset ID ";
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Alert message is not displayed");
		sa.assertAll();
	}

	// CA008B-Verify when user enter only special characters to the Asset ID field

	@Test(groups = {
			"Regression" }, description = "CA008B-Verify when user enter only special charecters to the Asset ID field")

	public void CA008B() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("CA005C-Verify when user enter only special charecters to the Asset name field");
		SoftAssert sa = new SoftAssert();
		Copyassetpage = assetDetailsPage.clickCopyasset();
		Copyassetpage.copyAsset_Creation("Atxt", "!@#!$#%$^#%$&^*");
		Copyassetpage.click_copy_Btn();
		String ActAlrtMsg = tu.get_AlertMsg_text();
		String ExpAlrtMsg = "New Asset ID  should have at least one letter or one digit";
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Alert message is not displayed");
		sa.assertAll();
	}

	// CA009-Verify that Asset is copied even when no setups are selected
	@Test(groups = { "Regression" }, description = "CA009-Verify that Asset is copied even when no setups are selected")

	public void CA009() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("CA009-Verify that Asset is copied even when no setups are selected");
		SoftAssert sa = new SoftAssert();
		Copyassetpage = assetDetailsPage.clickCopyasset();
		Copyassetpage.copyAsset_Creation("CA009", "12");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage = Copyassetpage.clickBack_Button();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("CA009");
		sa.assertEquals(assetDetailsPage.IsSetupTile_Empty(), true, "FAIL: Alert message is not displayed");
		sa.assertAll();

	}

	// CA010-Verify if clicking on back button in copy asset screen in user
	// navigates to Assets page
	@Test(groups = {
			"Regression" }, description = "CA010-Verify if clicking on back button in copy asset screen in user navigates to Assets page")

	public void CA010() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"CA010-Verify if clicking on back button in copy asset screen in user navigates to Assets page");
		SoftAssert sa = new SoftAssert();
		Copyassetpage = assetDetailsPage.clickCopyasset();
		Copyassetpage.Enter_NewAssetNameField("abcc");
		Copyassetpage.clickBackBtn_alertMsg();
		String ActalertTxt = Copyassetpage.get_text_back_popup();
		String ExpalertTxt = "You are about to lose your changes.Do you want to continue ?";
		sa.assertEquals(ActalertTxt, ExpalertTxt, "FAIL: Alert message is not displayed");
		Copyassetpage.click_No_fromAlert();
		sa.assertEquals(Copyassetpage.IsCopyAssetPageTitle_presence(), true,
				"FAIL: Copy Asset title is not displaying");
		Copyassetpage.clickBackBtn_alertMsg();
		assetDetailsPage = Copyassetpage.Yes_Alert();

		sa.assertEquals(assetDetailsPage.assetDetail_PageTitle(), "HeatBath - Asset01",
				"FAIL:Incorrect AssetDetails Page title or landed into incorrect Page");
		sa.assertAll();
	}

	// CA011-Verify the on-click functionality of the copy button in Copy Asset
	// screen
	// CA014-Verify if Audit trial record exists for copy asset.
	// This Test Case also covered under CA011
	@Test(groups = {
			"Regression" }, description = "CA011-Verify the on-click functionality of the copy button in Copy Asset screen")
	public void CA011() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent
				.startTest("CA011-Verify the on-click functionality of the copy button in Copy Asset screen");
		SoftAssert sa = new SoftAssert();
		Copyassetpage = assetDetailsPage.clickCopyasset();

		Copyassetpage.copyAsset_Creation("CA011", "11");
		Thread.sleep(2000);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(2000);
		String ActAlrtMsg = tu.get_AlertMsg_text();
		String ExpAlrtMsg = "CA011 Created Successfully";
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Alert message is not displayed");
		assetDetailsPage = Copyassetpage.clickBack_Button();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "Asset : \"CA011\" is created by User Id : \"1\" , User Name : \"User1\".";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL:The Audit trail record for copy Assets activity is not exist ");
		sa.assertAll();

	}

	// CA012 -Verify if the Asset has been created with copied setups
	@Test(groups = { "Regression" }, description = "CA012 -Verify if the Asset has been created with copied setups")

	public void CA012() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent
				.startTest("CA011-Verify the on-click functionality of the copy button in Copy Asset screen");
		SoftAssert sa = new SoftAssert();

		Copyassetpage = assetDetailsPage.clickCopyasset();

		Copyassetpage.Enter_NewAssetNameField("CA012");
		Copyassetpage.Enter_NewAssetIDField("12");
		Copyassetpage.Click_Checkbox();
		Copyassetpage.click_copy_Btn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		String ActAlrtMsg = tu.get_AlertMsg_text();
		String ExpAlrtMsg = "CA012 asset created and 1 setup(s) copied successfully.";
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Alert message is not displayed");
		sa.assertAll();
		assetDetailsPage = Copyassetpage.clickBack_Button();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("CA012");

		assetDetailsPage.Click_SetupName("CA002");
		sa.assertEquals(assetDetailsPage.Get_Setup_Name(), "CA002", "FAIL: Alert message is not displayed");
		sa.assertAll();
	}

	// CA013-Verify the date and time of the copied setups in the copied Asset

	@Test(groups = {
			"Regression" }, description = "CA013-Verify the date and time of the copied setups in the copied Asset              ")

	public void CA013() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("CA013-Verify the date and time of the copied setups in the copied Asset      ");
		SoftAssert sa = new SoftAssert();

		String CreatedSetupDateFormat = assetDetailsPage.Get_Setup_Date();
		// System.out.println(CreatedSetupDateFormat);

		Copyassetpage = assetDetailsPage.clickCopyasset();
		Copyassetpage.Enter_NewAssetNameField("CA013");
		Copyassetpage.Enter_NewAssetIDField("13");
		Copyassetpage.Click_Checkbox();
		Copyassetpage.click_copy_Btn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(3000);
		assetDetailsPage = Copyassetpage.clickBack_Button();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("CA013");

		String SetupDateFormat_CopiedAseet = assetDetailsPage.Get_Setup_Date();

		sa.assertEquals(SetupDateFormat_CopiedAseet, CreatedSetupDateFormat, "FAIL: Format is not matched");

		sa.assertAll();

	}

	// CA014-Verify if Audit trial record exists for copy asset.
	// This Test Case also covered under CA011

	// CA015-Verify user is unable to delete the copied asset if it has any files in
	// it
	@Test(groups = {
			"Regression" }, description = "CA015-Verify user is unable to delete the copied asset if it has any files in it")

	public void CA015() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent
				.startTest("CA015-Verify user is unable to delete the copied asset if it has any files in it");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("CA012");
		assetDetailsPage.DeleteAsset();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetDetailsPage.ClickOK_btn();
		assetDetailsPage = assetHubPage.click_assetTile("CA012");
		String IDinfo = assetDetailsPage.get_asset_IDinfo();
		sa.assertEquals(IDinfo, "12", "FAIL: Format is not matched");
		sa.assertAll();

	}

	// CA016-Verify user is able to delete the copied asset if it has no files in it

	@Test(groups = {
			"Regression" }, description = "CA016-Verify user is able to delete the copied asset if it has no files in it ")

	public void CA016() throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"CA016-Verify user is able to delete the copied asset if it has no files in it               ");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("CA005B");
		assetDetailsPage.DeleteAsset();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetDetailsPage.Delete_ClickYesBtn();
		assetHubPage.click_serachAstBtn();
		assetHubPage.enter_serachAsttxt("CA005B");
		sa.assertEquals(assetHubPage.IsNoRecordFoundVisible(), true, "FAIL: Asset is visible");
		sa.assertAll();
	}

}
