/**
* @author ruchika
*
*/

package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;
//import java.util.ArrayList;
import java.util.Arrays;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.AuditPage;
import com.vrt.pages.EquipmentHubPage;
import com.vrt.pages.NewEquipmentCreation_Page;
import com.vrt.utility.EqupmentUtility;
import com.vrt.utility.TestUtilities;
import com.vrt.utility.setupCreationUtility;
import com.vrt.pages.IRTDHubPage;
import com.vrt.pages.Equipment_IRTDDetailspage;

public class EquipmentCreationTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p

	public EquipmentCreationTest() throws IOException {
		super();
	}

	public ExtentReports extent;
	public ExtentTest extentTest;
	TestUtilities tu = new TestUtilities();

//Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	AuditPage AuditPage;
	NewEquipmentCreation_Page NewEquipmentCreation_Page;
	EquipmentHubPage EquipmentHubPage;
	IRTDHubPage IRTDHubPage;
	Equipment_IRTDDetailspage IRTDDetailspage;

	// Ensure the User has got rights to create Assets
	// @BeforeTest
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, AWTException {
		extent = new ExtentReports(
				System.getProperty("user.dir") + "/test-output/ER_" + "EquipmentCreationTest" + ".html", true);
		extent.addSystemInfo("TestSuiteName", "Asset Creation Test");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("EquipmentCreation Test in Progress..");

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
		AppClose();
		Thread.sleep(1000);

	}

	// After All the tests are conducted
	// @AfterTest
	@AfterClass
	public void endReport() {
		extent.flush();
		extent.close();
		System.out.println("EquipmentCreation Test Completed.");
	}

	// Before all tests are conducted
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();

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

	// ~~~~~~~~~~
	// Test Cases
	// ~~~~~~~~~~

	// EN_001- Verify the contents of the New Equipment screen
	@Test(groups = { "Regression" }, description = "EN_001- Verify the contents of the New Equipment screen")
	public void EH_001() throws InterruptedException {
		extentTest = extent.startTest("EN_001- Verify the contents of the New Equipment screen");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(NewEquipmentCreation_Page.IsSerialNo_Visible(), true, "Fail: EquipmentID Not Displayed ");

		sa.assertEquals(NewEquipmentCreation_Page.IsModelNumber_Visible(), true, "Fail: Model Number Not Displayed ");

		sa.assertEquals(NewEquipmentCreation_Page.IsEquipmentType_DropDown_Visible(), true,
				"Fail: Equipment Type DD Not Displayed ");

		sa.assertEquals(NewEquipmentCreation_Page.Is_IDTextBox_Visible(), true, "Fail: IDTextBox Not Displayed ");

		sa.assertEquals(NewEquipmentCreation_Page.IsManufacturingCal_Date_Visible(), true,
				"Fail: ManufacturingCal_DueDate Not Displayed ");

		sa.assertEquals(NewEquipmentCreation_Page.IsEquipmentImage_Slot_Visible(), true,
				"Fail: Equipment Image_Slot Not Displayed ");

		sa.assertEquals(NewEquipmentCreation_Page.IsEquipmentImage_UploadButton_Visible(), true,
				"Fail: Equipment Upload Image Not Displayed ");

		sa.assertEquals(NewEquipmentCreation_Page.IsEquipment_Camera_UploadButton_Visible(), true,
				"Fail: Equipment_Camera_UploadButton Not Displayed ");

		sa.assertEquals(NewEquipmentCreation_Page.IsEquipmentSaveButton_Visible(), true,
				"Fail: Equipment SaveButton Not Displayed ");

		sa.assertEquals(NewEquipmentCreation_Page.IsEquipment_Camera_UploadButton_Visible(), true,
				"Fail: Equipment_Camera_UploadButton Not Displayed ");

		sa.assertEquals(NewEquipmentCreation_Page.IsEquipmentSaveButton_Visible(), true,
				"Fail: Save button  Not Displayed ");
		sa.assertEquals(NewEquipmentCreation_Page.IsClearButton_Visible(), true, "Fail: Clear button  Not Displayed ");

		sa.assertAll();
	}

	// EN_002 - Verify if the Kaye Serial No. field accepts Alphanumeric characters

	@Test(groups = {
			"Regression" }, dataProvider = "EN_002", dataProviderClass = EqupmentUtility.class, description = "EN_002-Verify if the Kaye Serial No. field accepts Alphanumeric characters")
	public void EN_002(String SerialNo) throws InterruptedException {
		extentTest = extent.startTest("EN_002-Verify if the Kaye Serial No. field accepts Alphanumeric characters");
		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", SerialNo, "1");

		sa.assertEquals(NewEquipmentCreation_Page.UserLoginPopupVisible(), true);

		sa.assertAll();
	}

	// EN_003 - Verify if the Kaye Serial No. field do not accept Special characters

	@Test(groups = {
			"Regression" }, dataProvider = "EN_003", dataProviderClass = EqupmentUtility.class, description = "EN_003 - Verify if the Kaye Serial No. field do not accept Special characters")
	public void EN_003(String SerialNo, String Expmsg) throws InterruptedException {
		extentTest = extent.startTest("EN_003 - Verify if the Kaye Serial No. field do not accept Special characters");
		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", SerialNo, "1");
		String Actmsg = tu.get_AlertMsg_text();
		sa.assertEquals(Actmsg, Expmsg, "Fail : application displaying wrong alert message");

		sa.assertAll();
	}

	// EN_004 -Verify if Kaye Serial No. field accepts valid character length of 16
	// characters

	@Test(groups = {
			"Regression" }, dataProvider = "EN_004", dataProviderClass = EqupmentUtility.class, description = "EN_004- Verify if Kaye Serial No. field accepts valid character length of 17 characters")
	public void EN_004(String SerialNo) throws InterruptedException {

		extentTest = extent
				.startTest("EN_004 - Verify if Kaye Serial No. field accepts valid character length of 17 characters");
		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", SerialNo, "1");
		String SN = NewEquipmentCreation_Page.Fetch_SerialNo_Txt();
		sa.assertEquals(NewEquipmentCreation_Page.UserLoginPopupVisible(), true);

		sa.assertEquals(SN.length(), 16,
				"Fail : Application not accepting valid character length of 16 characters  in serial number field");

		sa.assertAll();

	}

//EN_005 - Verify if Kaye Serial No. field do not accept invalid character length of more than 16 characters             

	@Test(groups = {
			"Regression" }, dataProvider = "EN_005", dataProviderClass = EqupmentUtility.class, description = "EN_005- Verify if Kaye Serial No. field do not accept invalid character length of more than 16 characters")
	public void EN_005(String SerialNo) throws InterruptedException {

		extentTest = extent.startTest(
				"EN_005- Verify if Kaye Serial No. field do not accept invalid character length of more than 16 characters");
		SoftAssert sa = new SoftAssert();

//More than 16 chars have entered

		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", SerialNo, "1");
		String SN = NewEquipmentCreation_Page.Fetch_SerialNo_Txt();

		sa.assertEquals(SN.length(), 16,
				"Fail : Application  accepting more than  16 characters in serial number field");
		sa.assertAll();

	}

	// EN_006- Verify if the Kaye Serial Number field accepts unique value only

	@Test(groups = {
			"Regression" }, description = "EN_006- Verify if the Kaye Serial Number field accepts unique value only")
	public void EN_006() throws InterruptedException, IOException {

		extentTest = extent.startTest("EN_006- Verify if the Kaye Serial Number field accepts unique value only");
		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();
		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EN006", "10");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();

		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();
		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EN006", "11");

		String Actmsg = tu.get_AlertMsg_text();
		String Expmsg = "Equipment \"EN006\" already exists, Please use a different Equipment SNO";

		sa.assertEquals(Actmsg, Expmsg, "Fail : application displaying wrong alert message");
		sa.assertAll();

	}

	// EN_007-Verify if the Model No. field accepts Alphanumeric characters

	@Test(groups = {
			"Regression" }, dataProvider = "EN_007", dataProviderClass = EqupmentUtility.class, description = "EN_007-Verify if the Model No. field accepts Alphanumeric characters")
	public void EN_007(String ModelNo) throws InterruptedException {
		extentTest = extent.startTest("EN_007-Verify if the Model No. field accepts Alphanumeric characters");
		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EN", ModelNo);

		sa.assertEquals(NewEquipmentCreation_Page.UserLoginPopupVisible(), true,
				"Fail : Loginpop up has not displayed");
		sa.assertAll();
	}

	// EN_008- Verify if the Model No. field accepts Special characters

	@Test(groups = {
			"Regression" }, dataProvider = "EN_008", dataProviderClass = EqupmentUtility.class, description = "EN_008- Verify if the Model No. field accepts Special characters")
	public void EN_008(String ModelNo, String Expmsg) throws InterruptedException {
		extentTest = extent.startTest("EN_008- Verify if the Model No. field accepts Special characters");
		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EN", ModelNo);

		String Actmsg = tu.get_AlertMsg_text();
		sa.assertEquals(Actmsg, Expmsg, "Fail : Application displaying wrong alert message");
		sa.assertAll();

	}

	// EN_009- Verify if Model No. field accepts valid character length of 16
	// charcaters

	@Test(groups = {
			"Regression" }, dataProvider = "EN_009", dataProviderClass = EqupmentUtility.class, description = "EN_009- Verify if Model No. "
					+ " field accepts valid character length of 16 charcaters")
	public void EN_009(String ModelNo) throws InterruptedException {

		extentTest = extent
				.startTest("EN_009- Verify if Model No.field accepts valid character length of 16 charcaters");
		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EN", ModelNo);
		String MN = NewEquipmentCreation_Page.Fetch_ModelNumber_Txt();

		sa.assertEquals(NewEquipmentCreation_Page.UserLoginPopupVisible(), true);

		sa.assertEquals(MN.length(), 16,
				"Fail : Application not accepting valid character length of 16 characters in model number field ");

		sa.assertAll();

	}

	// EN_010- Verify if Model No. field do not accept invalid character length of
	// more than 16 charcaters

	@Test(groups = {
			"Regression" }, dataProvider = "EN_010", dataProviderClass = EqupmentUtility.class, description = "EN_010- Verify if Model No. field do not accept  invalid character length of more than 16 charcaters")
	public void EN_010(String ModelNo) throws InterruptedException {

		extentTest = extent.startTest(
				"EN_010- Verify if Model No. field do not accept  invalid character length of more than 16 charcaters");
		SoftAssert sa = new SoftAssert();

		// Here we are entering more than 16 chars of test data in modelno field

		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EN", ModelNo);
		String MN = NewEquipmentCreation_Page.Fetch_ModelNumber_Txt();

		sa.assertEquals(NewEquipmentCreation_Page.UserLoginPopupVisible(), true);

		sa.assertEquals(MN.length(), 16,
				"Fail : Application not accepting valid character length of 16 characters in model number field ");

		sa.assertAll();

	}

	// EN_011- Verify if the Equipment ID field accepts Alphanumeric characters

	@Test(groups = {
			"Regression" }, dataProvider = "EN_011", dataProviderClass = EqupmentUtility.class, description = "EN_011-  Verify if the Equipment ID field accepts Alphanumeric characters")
	public void EN_011(String id) throws InterruptedException {
		extentTest = extent.startTest("EN_011-  Verify if the Equipment ID field accepts Alphanumeric characters");
		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.EqipCreation("IRTD", "EN", "12", id);

		sa.assertEquals(NewEquipmentCreation_Page.UserLoginPopupVisible(), true,
				"Fail : Loginpop up has not displayed");
		sa.assertAll();
	}

	// EN_012- Verify if the Equipment ID field do not accept Special characters
	// except -, _ and slash (both forward and backward)

	@Test(groups = {
			"Regression" }, dataProvider = "EN_012", dataProviderClass = EqupmentUtility.class, description = "EN_012- Verify if the Equipment ID field do not accept Special characters except -, _ and slash (both forward and backward)")
	public void EN_012(String id, String Expmsg) throws InterruptedException {
		extentTest = extent.startTest(
				"EN_012- Verify if the Equipment ID field do not accept Special characters except -, _ and slash (both forward and backward)");
		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.EqipCreation("IRTD", "EN", "12", id);

		String Actmsg = tu.get_AlertMsg_text();
		sa.assertEquals(Actmsg, Expmsg, "Fail : Application displaying wrong alert message");
		sa.assertAll();
	}

	// EN_013- Verify if Equipment ID field accepts valid character length of 16
	// characters

	@Test(groups = {
			"Regression" }, dataProvider = "EN_013", dataProviderClass = EqupmentUtility.class, description = "EN_013- Verify if Equipment ID  field accepts valid character length of 16 characters")
	public void EN_013(String id) throws InterruptedException {

		extentTest = extent
				.startTest("EN_013- Verify if Equipment ID  field accepts valid character length of 16 characters");
		SoftAssert sa = new SoftAssert();

		// Here we are entering more than 16 chars of test data in ID field

		NewEquipmentCreation_Page.EqipCreation("IRTD", "EN", "12", id);
		String ID = NewEquipmentCreation_Page.Fetch_ID();

		sa.assertEquals(NewEquipmentCreation_Page.UserLoginPopupVisible(), true);

		sa.assertEquals(ID.length(), 16,
				"Fail : Application not accepting valid character length of 16 characters in ID field");

		sa.assertAll();

	}

	// EN_014- Verify if the Equipment ID field is not a mandatory field

	@Test(groups = { "Regression" }, description = "EN_014- Verify if the Equipment ID  field is not a mandatory field")
	public void EN_014() throws InterruptedException {

		extentTest = extent.startTest("EN_014-Verify if the Equipment ID  field is not a mandatory field");

		SoftAssert sa = new SoftAssert();

		// Here we are keeping ID field blank

		NewEquipmentCreation_Page.EqipCreation("IRTD", "EN", "12", "");
		sa.assertEquals(NewEquipmentCreation_Page.UserLoginPopupVisible(), true,
				"Fail: Application not displaying the user login pop up ");

		sa.assertAll();
	}

	// EN_015- Verify if the User has scope to select different Kaye equipment type
	// from the Equipment type dropdown

	@Test(groups = {
			"Regression" }, description = "EN_015- Verify if the User has scope to select different Kaye equipment type from the Equipment type dropdown")
	public void EN_015() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EN_015- Verify if the User has scope to select different Kaye equipment type from the Equipment type dropdown");

		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page.click_EquipmentType_DD();
		Thread.sleep(1000);
		sa.assertEquals(NewEquipmentCreation_Page.EquipmentType_options(1), "IRTD",
				"FAIL : IRTD is not available in Equipment Type ");
		sa.assertEquals(NewEquipmentCreation_Page.EquipmentType_options(2), "VRT Logger",
				"FAIL : VRT Logger is not available in Equipment Type ");
		sa.assertAll();
	}

	// EN_016- Verify if on selecting Base Station Equipment Type Mac Address field
	// is added and Remaining fields are removed from the New Equipment Screen
	// content
	// BS Equipment type not available in app

	// EN_017- Verify if Logger Type field is added to the New Equipment Screen
	// content for VRT Logger Equipment Type

	@Test(groups = {
			"Regression" }, description = "EN_017- Verify if Logger Type field is added to the New Equipment Screen content for VRT Logger  Equipment Type")
	public void EN_017() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EN_017- Verify if Logger Type field is added to the New Equipment Screen content for VRT Logger  Equipment Type");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.select_EquipmentType("VRT Logger");
		sa.assertEquals(NewEquipmentCreation_Page.Selected_EquipmentType_text(), "VRT Logger",
				"FAIL : selected Equipment Type is not displayed");
		sa.assertEquals(NewEquipmentCreation_Page.is_LoggerTypeComboBox_Visible(), true,
				"FAIL : Logger Type combo box  is not available in new Equipment page ");

		sa.assertAll();

	}

	// EN_018- Verify if Last Verification Date field is added to the New Equipment
	// Screen content for VRT Logger Equipment Type

	@Test(groups = {
			"Regression" }, description = "EN_018- Verify if Last Verification Date field is added to the New Equipment Screen content for VRT Logger  Equipment Type")
	public void EN_018() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EN_018- Verify if Last Verification Date field is added to the New Equipment Screen content for VRT Logger  Equipment Type");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.select_EquipmentType("VRT Logger");
		sa.assertEquals(NewEquipmentCreation_Page.is_LastVerificationDate_Visible(), true,
				"FAIL : LLast Verification date  is not available in new Equipment page for VRT Logger equipment type");
		sa.assertAll();
	}

	// EN_019- Verify if User can select different logger type from the Logger Type
	// dropdown in the New Equipment screen for VRT Loggers

	@Test(groups = { "Regression" }, description = "EN_019- Verify if User can select different logger type from the "
			+ "Logger Type dropdown in the New Equipment screen for VRT Loggers")
	public void EN_019() throws InterruptedException, IOException {

		extentTest = extent.startTest("EN_019- Verify if User can select different logger type from "
				+ "the Logger Type dropdown in the New Equipment screen for VRT Loggers");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.select_EquipmentType("VRT Logger");
		NewEquipmentCreation_Page.click_LoggerTypeComboBox();
		Thread.sleep(2000);
		sa.assertEquals(NewEquipmentCreation_Page.Fetch_TxtofOptions_fromLoggerTypeCombobox(1), "1 Ch Temp-Rigid",
				"FAIL: 1 Ch Temp-Rigid option not available");
		sa.assertEquals(NewEquipmentCreation_Page.Fetch_TxtofOptions_fromLoggerTypeCombobox(2), "1 Ch Temp-Bendable",
				"FAIL: 1 Ch Temp-Rigid option not available");
		sa.assertEquals(NewEquipmentCreation_Page.Fetch_TxtofOptions_fromLoggerTypeCombobox(3), "2 Ch Temp-Bendable",
				"FAIL: 1 Ch Temp-Rigid option not available");
		sa.assertEquals(NewEquipmentCreation_Page.Fetch_TxtofOptions_fromLoggerTypeCombobox(4), "2 Ch Temp-Flexible",
				"FAIL: 1 Ch Temp-Rigid option not available");
		sa.assertEquals(NewEquipmentCreation_Page.Fetch_TxtofOptions_fromLoggerTypeCombobox(5), "5 Ch Temp-Flexible",
				"FAIL: 1 Ch Temp-Rigid option not available");
		sa.assertEquals(NewEquipmentCreation_Page.Fetch_TxtofOptions_fromLoggerTypeCombobox(6), "1 Ch Temp-Surface",
				"FAIL: 1 Ch Temp-Rigid option not available");
		sa.assertEquals(NewEquipmentCreation_Page.Fetch_TxtofOptions_fromLoggerTypeCombobox(7), "2 Ch Humidity Temp",
				"FAIL: 1 Ch Temp-Rigid option not available");
		sa.assertEquals(NewEquipmentCreation_Page.Fetch_TxtofOptions_fromLoggerTypeCombobox(8), "1 Ch Temp-Flexible",
				"FAIL: 1 Ch Temp-Rigid option not available");
		sa.assertEquals(NewEquipmentCreation_Page.Fetch_TxtofOptions_fromLoggerTypeCombobox(9), "2 Ch Pressure Temp",
				"FAIL: 1 Ch Temp-Rigid option not available");
		sa.assertAll();

	}
	// EN_019A- Verify that the clicked logger type gets selected

	@Test(groups = { "Regression" }, description = "EN_019A- Verify that the clicked logger type gets selected")
	public void EN_019A() throws InterruptedException, IOException {

		extentTest = extent.startTest("EN_019A- Verify that the clicked logger type gets selected");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.select_EquipmentType("VRT Logger");
		NewEquipmentCreation_Page.selectAnylogger_From_LoggerTypeComboBox(2);
		sa.assertEquals(NewEquipmentCreation_Page.LoggerTypeComboBox_SelectedValueTxt(), "1 Ch Temp-Bendable",
				"Fail: Selected Values is not displaying");
		sa.assertAll();

	}

	// EN_020- Verify if the Manufacturing Cal date field displays the current
	// system date and time by default

	@Test(groups = {
			"Regression" }, description = "EN_020 - Verify if the Manufacturing Cal date field displays the current system date and time by default")
	public void EN_020() throws InterruptedException, IOException, ParseException {

		extentTest = extent.startTest(
				"EN_020 - Verify if the Manufacturing Cal date field displays the current system date and time by default");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.select_EquipmentType("IRTD");
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		Thread.sleep(1000);
		// System.out.println(NewEquipmentCreation_Page.FetchTxt_From_ManufacturingCalDate());
		sa.assertEquals(NewEquipmentCreation_Page.FetchTxt_From_ManufacturingCalDate(), crntDate,
				"Fail: Current date is not maatching with ManufacturingCalDueDate ");
		sa.assertAll();
	}

	// EN_021- Verify if clicking on the Manufacturing Cal date field displays a
	// date time picker to select the required date

	@Test(groups = {
			"Regression" }, description = "EN_021- Verify if clicking on the Manufacturing Cal date field displays a date time picker to select the required date")
	public void EN_021() throws InterruptedException, IOException, ParseException {

		extentTest = extent.startTest(
				"EN_021- Verify if clicking on the Manufacturing Cal date field displays a date time picker to select the required date");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.select_EquipmentType("IRTD");
		NewEquipmentCreation_Page.ClickOn_ManufacturingCalDate();
		Thread.sleep(1000);

		sa.assertEquals(NewEquipmentCreation_Page.Is_SelectDate_Visible(), true,
				"Fail: DatePicker field is not appeared");
		sa.assertAll();
	}

// EN_022- Verify if the Manufacturing Cal due date field displays the one year ahead timestamp of Manufacturing Cal date by default                 

	@Test(groups = {
			"Regression" }, description = "EN_022- Verify if the Manufacturing Cal due date field displays the one year ahead timestamp of Manufacturing Cal date by default")
	public void EN_022() throws InterruptedException, IOException, ParseException {

		extentTest = extent.startTest(
				"EN_022- Verify if the Manufacturing Cal due date field displays the one year ahead timestamp of Manufacturing Cal date by default");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.select_EquipmentType("IRTD");

		String Manufacturing_Cal_Date = NewEquipmentCreation_Page.FetchTxt_From_ManufacturingCalDate();

		System.out.println("Manufacturing_Cal_Date " + Manufacturing_Cal_Date);
		String crntdate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");

		String ManufacturingCal_Due_Dat = NewEquipmentCreation_Page.FetchTxt_From_ManufacturingCal_DueDate();

		System.out.println("Actual ManufacturingCal_Due_Dat " + ManufacturingCal_Due_Dat);

		String Futuredate = tu.convert_StringDate_to_ActualDate_inCertainFormat6(tu.getFutureDate_weeks(52));
		System.out.println("Futuredate " + Futuredate);

		sa.assertEquals(crntdate, Manufacturing_Cal_Date, "FAIL : ManufacturingCalDate is not same with current date");

		sa.assertEquals(Futuredate, ManufacturingCal_Due_Dat,
				"Fail: By default Manufacturing Cal due date field not displaying one year ahead timestamp of Manufacturing Cal date");
		sa.assertAll();
	}

	// EN_023 - Verify if user able not able to edit the Manufacturing Cal due date
	// field

	@Test(groups = {
			"Regression" }, description = "EN_023- Verify if user able not able to edit the Manufacturing Cal due date field")
	public void EN_023() throws InterruptedException, IOException, ParseException {

		extentTest = extent
				.startTest("EN_023- Verify if user able not able to edit the Manufacturing Cal due date field");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.select_EquipmentType("IRTD");
		sa.assertEquals(NewEquipmentCreation_Page.is_ManufacturingCal_DueDate_Enabled(), false,
				"Fail:ManufacturingCal_DueDate is Enabled ");
		sa.assertAll();
	}

	// EN_024- Verify if the Last verification date field do display the current
	// system date and time by default

	@Test(groups = {
			"Regression" }, description = "EN_024- Verify if the Last verification date field should display the current system date and time by default")
	public void EN_024() throws InterruptedException, IOException, ParseException {

		extentTest = extent.startTest(
				"EN_024- Verify if the Last verification date field do not display the current system date and time by default");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.select_EquipmentType("VRT Logger");

		String crntdate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		String LastVerificationDate = NewEquipmentCreation_Page.FetchTxt_LastVerificationDate();

		sa.assertEquals(crntdate, LastVerificationDate,
				"Fail: By default Last Verification Date field is not  displaying the current date");
		sa.assertAll();
	}

	// EN_025 - Verify if clicking on the Last verification date field displays a
	// date time picker to select the required date

	@Test(groups = {
			"Regression" }, description = "EN_025- Verify if clicking on the Last verification date field displays a date time picker to select the required date")
	public void EN_025() throws InterruptedException, IOException, ParseException {

		extentTest = extent.startTest(
				"EN_025- Verify if clicking on the Last verification date field displays a date time picker to select the required date");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.select_EquipmentType("VRT Logger");

		NewEquipmentCreation_Page.click_LastVerificationdate_field();

		sa.assertEquals(NewEquipmentCreation_Page.Is_SelectDate_Visible(), true,
				"FAIL: date time picker to select the required date is not visible by clicking on the Last verification date field displays ");
		sa.assertAll();
	}

	// EN_026- Verify if User able to select the Manufacturing Cal Date is equal to
	// the Last Verification Date for VRT Logger equipment type

	@Test(groups = {
			"Regression" }, description = "EN_028- Verify if User is not allowed to save the equipment  if any one of the mandatory fields are left blank")
	public void EN_026() throws InterruptedException {
		extentTest = extent.startTest(
				"EN_028- Verify if User is not allowed to save the equipment  if any one of the mandatory fields are left blank");
		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page.select_EquipmentType("VRT Logger");
		NewEquipmentCreation_Page.selectAnylogger_From_LoggerTypeComboBox(2);
		NewEquipmentCreation_Page.enterSN("039");
		NewEquipmentCreation_Page.enterNewModelNumber("040");

		NewEquipmentCreation_Page.selectManufacturingCalDate_Yr("2020");

		NewEquipmentCreation_Page.selectLastVerificationDate_Yr("2020");
		NewEquipmentCreation_Page.ClickSaveButton();

		sa.assertEquals(NewEquipmentCreation_Page.UserLoginPopupVisible(), true);

		sa.assertAll();

	}

	// EN_027- Verify if an alert message is displayed if the Manufacturing Cal Date
	// is greater than the Last Verification Date for VRT Logger equipment type

	@Test(groups = {
			"Regression" }, description = "EN_027- Verify if an alert message is displayed if the Manufacturing Cal Date is greater than the Last Verification Date for VRT Logger equipment type")
	public void EN_027() throws InterruptedException {
		extentTest = extent.startTest(
				"EN_028- Verify if User is not allowed to save the equipment  if any one of the mandatory fields are left blank");
		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page.select_EquipmentType("VRT Logger");
		NewEquipmentCreation_Page.selectAnylogger_From_LoggerTypeComboBox(2);
		NewEquipmentCreation_Page.enterSN("039");
		NewEquipmentCreation_Page.enterNewModelNumber("040");

		NewEquipmentCreation_Page.selectLastVerificationDate_Yr("2020");
		NewEquipmentCreation_Page.ClickSaveButton();

		String ActualMsg = tu.get_AlertMsg_text();
		String ExpMsg = "Verification Date should be greater than Manufacturing Cal Date .";
		sa.assertEquals(ActualMsg, ExpMsg, "FAIL: Alert message is not displaying correctly");
		sa.assertAll();
	}

	// EN_028- Verify if User is not allowed to save the equipment if any one of the
	// mandatory fields are left blank

	@Test(groups = {
			"Regression" }, dataProvider = "EN_028", dataProviderClass = EqupmentUtility.class, description = "EN_028- Verify if User is not allowed to save the equipment  if any one of the mandatory fields are left blank")
	public void EN_028(String Etype, String SerialNo, String EMN, String Expmsg) throws InterruptedException {
		extentTest = extent.startTest(
				"EN_028- Verify if User is not allowed to save the equipment  if any one of the mandatory fields are left blank");
		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page.EqipCreation_MandatoryFields(Etype, SerialNo, EMN);

		String Actmsg = tu.get_AlertMsg_text();
		sa.assertEquals(Actmsg, Expmsg, "Fail : application displaying wrong alert message");
		sa.assertAll();
	}

	// EN_029- Verify if user has a scope to add a new equipment picture

	@Test(groups = { "Regression" }, description = "EN_029- This TC is equivalant to EN_030")
	public void EN_029() throws InterruptedException, IOException, ParseException, AWTException {

		extentTest = extent.startTest("EN_029- This TC is equivalant to EN_030");

		SoftAssert sa = new SoftAssert();
	}

	// EN_030- Verify if User is allowed to upload an image of valid image size of
	// less than 5 M

	@Test(groups = {
			"Regression" }, description = "EN_029,EN_030 - Verify if user has a scope to add a new equipment picture")
	public void EN_029_EN_030() throws InterruptedException, IOException, ParseException, AWTException {

		extentTest = extent.startTest("EN_029,EN_030 - Verify if user has a scope to add a new equipment picture");

		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page.EqipCreation_WithoutClickingSaveBtn("IRTD", "111", "12As");
		NewEquipmentCreation_Page.click_EquipmentImage_UploadButton();
		Thread.sleep(1000);
		tu.uploadDoc("VRT_Pro.JPG");
		NewEquipmentCreation_Page.Capture_ImgButton1("Expected_VRT_Pro_Img");

		NewEquipmentCreation_Page.Capture_ImgButton1("Actual_VRT_Pro_Img");

		boolean status_ImgCompare = tu.compareImage("Expected_VRT_Pro_Img", "Actual_VRT_Pro_Img");

		sa.assertEquals(status_ImgCompare, false, "FAIL:  Image saved is not same as what was selected");

		sa.assertAll();
	}

	// EN_029A- Verify if user has a scope to add a new equipment picture by
	// clicking camera btn

	@Test(groups = {
			"Regression" }, description = "EN_029- Verify if user has a scope to add a new equipment picture by clicking camera btn")
	public void EN_029A() throws InterruptedException, IOException, ParseException, AWTException {

		extentTest = extent
				.startTest("EN_029A- Verify if user has a scope to add a new equipment picture by clicking camera btn");

		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page.EqipCreation_WithoutClickingSaveBtn("IRTD", "111", "12As");
		NewEquipmentCreation_Page.click_EquipmentCamera_UploadButton();

		sa.assertEquals(NewEquipmentCreation_Page.is_CloseCamBtn_Visible(), true,
				"FAIL: Close camera btn is not visible");
		NewEquipmentCreation_Page.close_camera();
		sa.assertAll();
	}

	// EN_031- Verify if User is not allowed to upload an image of invalid image
	// size of more than 5 MB

	@Test(groups = {
			"Regression" }, description = "EN_031- Verify if User is not allowed to upload an image of invalid image size of more than 5 MB      ")
	public void EN_031() throws InterruptedException, IOException, ParseException, AWTException {

		extentTest = extent.startTest(
				"EN_031- Verify if User is not allowed to upload an image of invalid image size of more than 5 MB               ");

		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page.select_EquipmentType("IRTD");
		NewEquipmentCreation_Page.click_EquipmentImage_UploadButton();
		Thread.sleep(1000);
		tu.uploadDoc("UserimageInValid.jpg");
		String Actmsg = tu.get_AlertMsg_text();
		String Expmsg = "Select image file with size less than 5 mb";
		sa.assertEquals(Actmsg, Expmsg, "FAIL : Alert message not displaying");
		sa.assertAll();

	}

	// EN_032- Verify if User can edit the equipment image by clicking on the Edit
	// option available in the equipment image slot in the New Equipment screen

	@Test(groups = {
			"Regression" }, description = "EN_032- Verify if User can edit the equipment image by clicking on the Edit option available in the equipment image slot in the New Equipment screen   ")
	public void EN_032() throws InterruptedException, IOException, ParseException, AWTException {

		extentTest = extent.startTest(
				"EN_032- Verify if User can edit the equipment image by clicking on the Edit option available in the equipment image slot in the New Equipment screen        ");

		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page.select_EquipmentType("IRTD");
		NewEquipmentCreation_Page.click_EquipmentImage_UploadButton();
		Thread.sleep(1000);
		tu.uploadDoc("VRT_Pro.JPG");
		NewEquipmentCreation_Page.Capture_ImgButton1("Expected_VRT_Pro_Img");
		NewEquipmentCreation_Page.EquipmentImage_Slot_Btn();
		Thread.sleep(1000);
		NewEquipmentCreation_Page.click_ImageEditbtn();
		Thread.sleep(3000);
		tu.uploadDoc("Pressure.jpg");
		NewEquipmentCreation_Page.Capture_ImgButton1("Actual_PSR_Pro_Img");

		boolean status_ImgCompare = tu.compareImage("Expected_VRT_Pro_Img", "Actual_PSR_Pro_Img");

		sa.assertEquals(status_ImgCompare, true, "FAIL:  Image saved is not same as what was selected");

		sa.assertAll();

	}

	// EN_033- Verify if User can remove the equipment image by clicking on the
	// Delete option available in the equipment image slot in the New Equipment
	// screen

	@Test(groups = {
			"Regression" }, description = "EN_033- Verify if User can remove the equipment image by clicking on the Delete option available in the equipment image slot in the New Equipment screen")
	public void EN_033() throws InterruptedException, IOException, ParseException, AWTException {

		extentTest = extent.startTest(
				"EN_033- Verify if User can remove the equipment image by clicking on the Delete option available in the equipment image slot in the New Equipment screen");

		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page.select_EquipmentType("IRTD");
		NewEquipmentCreation_Page.click_EquipmentImage_UploadButton();
		Thread.sleep(1000);
		tu.uploadDoc("VRT_Pro.JPG");
		NewEquipmentCreation_Page.Capture_ImgButton1("Expected_VRT_Pro_Img");
		NewEquipmentCreation_Page.EquipmentImage_Slot_Btn();
		Thread.sleep(1000);
		NewEquipmentCreation_Page.click_RemoveImagebtn();
		NewEquipmentCreation_Page.enterSerialNo("1a");
		NewEquipmentCreation_Page.Capture_ImgButton1("Actual_Img");

		boolean status_ImgCompare = tu.compareImage("Expected_VRT_Pro_Img", "Actual_Img");

		sa.assertEquals(status_ImgCompare, true, "FAIL:  Image saved is not same as what was selected");

		sa.assertAll();
	}

	// EN_035- Verify if user can create a new IRTD equipment by clicking on the
	// Save button after filling up all the mandatory fields

	@Test(groups = {
			"Regression" }, dataProvider = "EN_035", dataProviderClass = EqupmentUtility.class, description = "EN_035- Verify if user can create a new IRTD equipment by clicking on the  Save button after filling up all the mandatory fields")
	public void EN_035(String Etype, String SerialNo, String EMN) throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EN_035- Verify if user can create a new IRTD equipment by clicking on the  Save button after filling up all the mandatory fields");

		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page.EqipCreation_MandatoryFields(Etype, SerialNo, EMN);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo(SerialNo);

		sa.assertEquals(IRTDDetailspage.IRTD_DetailsHeaderPresence(), true, "FAIL - Landed to Wrong page");

		sa.assertAll();

	}

	// EN_036 to EN_038 TCs are OBSOLATE

	// EN_039- Verify if user can create a new VRT Logger equipment by clicking on
	// the Save button after filling up all the mandatory fields
	// EN_040- Verify if a Login validation window appears on clicking the Save
	// button to create a new equipment

	@Test(groups = {
			"Regression" }, description = "    EN_039- Verify if user can create a new VRT Logger equipment by clicking on the Save button after filling up all the mandatory fields, "
					+ "EN_040- Verify if a Login validation window appears on clicking the Save button to create a new equipmen")
	public void EN_039_EN_040() throws InterruptedException {

		extentTest = extent.startTest(
				"EN_039- Verify if user can create a new VRT Logger equipment by clicking on the Save button after filling up all the mandatory fields,"
						+ "EN_040- Verify if a Login validation window appears on clicking the Save button to create a new equipmen");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.select_EquipmentType("VRT Logger");
		NewEquipmentCreation_Page.selectAnylogger_From_LoggerTypeComboBox(2);
		NewEquipmentCreation_Page.enterSN("039");
		NewEquipmentCreation_Page.enterNewModelNumber("040");
		NewEquipmentCreation_Page.ClickSaveButton();
		// NewEquipmentCreation_Page.EqipCreation_VRTDLogger("VRT Logger", "0391",
		// "0391");

		sa.assertEquals(NewEquipmentCreation_Page.UserLoginPopupVisible(), true,
				"EN_039- Verify if user can create a new VRT Logger equipment by clicking on the Save button after filling up all the mandatory fields");

		sa.assertAll();

	}

	// EN_041- Verify if the new equipment is created for valid login credentials
	//// EN_046- Verify if the Audit event is recorded in the Audit screen for
	// creating a new Equpment

	@Test(groups = {
			"Regression" }, description = "EN_041,EN_046- Verify if the new equipment is created for valid login credentials")
	public void EN_041_EN_046() throws InterruptedException, IOException, ParseException, AWTException {

		extentTest = extent
				.startTest("EN_041,EN_046- Verify if the new equipment is created for valid login credentials");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EN041", "041");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();

		MainHubPage = EquipmentHubPage.ClickBackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actualnmsg = AuditPage.get_auditEvent_text();

		String ExpectMSG = "Equipment : \"EN041\" is created by  User ID : \"1\" , User Name : \"User1\"";

		sa.assertEquals(Actualnmsg, ExpectMSG, "FAIL:The Audit trail record for Equipment creation is not exist ");
		sa.assertAll();

	}

	// EN_042- Verify if the new equipment is not created for invalid login
	// credentials

	@Test(groups = {
			"Regression" }, description = "EN_042- Verify if the new equipment is not created for invalid login crede")
	public void EN_042() throws InterruptedException, IOException, ParseException, AWTException {

		extentTest = extent.startTest("EN_042- Verify if the new equipment is not created for invalid login crede");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EN042", "042");
		UserLoginPopup("2", "123240");

		String Actmsg = tu.get_AlertMsg_text();
		String Expmsg = "Invalid Credential, Please try again";
		sa.assertEquals(Actmsg, Expmsg, "FAIL : Incorrect Alert Message");
		sa.assertAll();

	}

	// EN_043- Verify if an alert message is displayed on clicking the Save button
	// by keeping any mandatory fields blank

	@Test(groups = {
			"Regression" }, description = "EN_043- Verify if an alert message is displayed on clicking the Save button by keeping any mandatory fields blank")
	public void EN_043() throws InterruptedException, IOException, ParseException, AWTException {

		extentTest = extent.startTest(
				"EN_043- Verify if an alert message is displayed on clicking the Save button by keeping any mandatory fields blank");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.ClickSaveButton();

		String Actmsg = tu.get_AlertMsg_text();
		String Expmsg = "Serial No. is mandatory, please enter Serial No.";
		sa.assertEquals(Actmsg, Expmsg, "FAIL : Incorrect Alert Message");
		sa.assertAll();
	}

	// EN_044- Verify if the Cancel button resets all the entries in the equipment
	// text fields to blank

	@Test(groups = {
			"Regression" }, description = "EN_044- Verify if the clear button resets all the entries in the equipment text fields to blank")
	public void EN_044() throws InterruptedException, IOException, ParseException, AWTException {

		extentTest = extent.startTest(
				"EN_044- Verify if the clear button resets all the entries in the equipment text fields to blank");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page.EqipCreation_WithoutClickingSaveBtn("IRTD", "EN044", "044");
		NewEquipmentCreation_Page.click_ClearButton();
		sa.assertEquals(NewEquipmentCreation_Page.Fetch_SerialNo_Txt(), "",
				"FAIL : Serial number field is not cleared");
		sa.assertEquals(NewEquipmentCreation_Page.Fetch_ModelNumber_Txt(), "",
				"FAIL : Model number field is not cleared");

		sa.assertEquals(NewEquipmentCreation_Page.Fetch_EquipmentType_Txt(), "IRTD",
				"FAIL : Etype field is not displayed");

		sa.assertAll();

	}

	// EN_045- Verify if clicking on the Back button redirects to the Equipment
	// Types Page

	@Test(groups = {
			"Regression" }, description = "EN_045- Verify if clicking on the Back button redirects to the Equipment Types Page")
	public void EN_045() throws InterruptedException, IOException, ParseException, AWTException {

		extentTest = extent
				.startTest("EN_045- Verify if clicking on the Back button redirects to the Equipment Types Page");

		SoftAssert sa = new SoftAssert();
		EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();

		sa.assertEquals(EquipmentHubPage.IsEquipmentHeader_Visible(), true, "FAIL: Landed to Wrong page");
		sa.assertAll();

	}

	// EN_046- Verify if the Audit event is recorded in the Audit screen for
	// creating a new Equpment

	// EN_046 handelled in EN_041

	// EN_048-Verify the bottom menu options in Asset details screen

	@Test(description = "EN_048-Verify the bottom menu options in New Equipment  screen")
	public void EN_048() throws InterruptedException {
		extentTest = extent.startTest("EN_048-Verify the bottom menu options in New Equipment  screen");
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

	// EN_048- Verify if clicking on the Home button in the New Equipment page
	// navigate to the Main Hub page

	@Test(description = "EN_048_1- Verify if clicking on the Home button in the New Equipment page navigate to the Main Hub page")
	public void EN_048_1() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EN_048_1- Verify if clicking on the Home button in the New Equipment page navigate to the Main Hub page");
		SoftAssert sa = new SoftAssert();

		MainHubPage = tu.Click_Home_Icon_AppBar1();

		sa.assertEquals(MainHubPage.Is_mainHubPageTitle_Visible(), true,
				"FAIL: Clicking Home icon/button in bottom app bar do not redirect to Mains Hub page");

		sa.assertAll();
	}

	// EN_049- Verify if clicking on the Help button in the New Equipment page
	// displays the online help menu for that page

	@Test(description = "EN_049- Verify if clicking on the Help button in the New Equipment page displays the online help menu for that page")
	public void EN_049() throws InterruptedException {
		extentTest = extent.startTest(
				"EN_049- Verify if clicking on the Help button in the New Equipment page displays the online help menu for that page");
		SoftAssert sa = new SoftAssert();

		tu.Click_Help_Icon_AppBar();

		sa.assertEquals(tu.get__HelpMenu_HdrText(), "New Equipment or Edit Equipment ",
				"FAIL: Clicking Help icon/button in bottom app bar"
						+ "do not display the Equipment Creation Help context window");
		sa.assertAll();
	}

	// EN_050-Verify if clicking on the Windows Help button in the New Equipment
	// page open the Windows Help document

	// This EN_050 test case will handle manually

	// EN_051- Verify if clicking on the About button in the New Equipment page
	// displays the current software version, Console IP

	@Test(dataProvider = "SET033", dataProviderClass = setupCreationUtility.class, description = "EN_051- Verify if clicking on the About button in the New Equipment page displays the current software version, Console IP")
	public void EN_051(String SWVer) throws InterruptedException, UnknownHostException {
		extentTest = extent.startTest(
				"EN_051- Verify if clicking on the About button in the New Equipment page displays the current software version, Console IP");
		SoftAssert sa = new SoftAssert();

		tu.Click_About_Icon_AppBar();

		sa.assertEquals(tu.SWversion_InAboutWndw(), SWVer,
				"FAIL: Incorrect SW version & Console IP adress displayed in About window");

		sa.assertEquals(tu.consoleIP_InAboutWndw(), tu.system_IPadress(),
				"FAIL: Incorrect SW version & Console IP adress displayed in About window");
		sa.assertAll();
	}

}
