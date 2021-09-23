/**
* @author ruchika
*
*/

package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.net.UnknownHostException;

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

import com.vrt.utility.TestUtilities;
import com.vrt.utility.setupCreationUtility;

public class EquipmentHubTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p

	public EquipmentHubTest() throws IOException {
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

	// Ensure the User has got rights to create Assets
	// @BeforeTest
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_" + "EquipmentHubTest" + ".html",
				true);
		extent.addSystemInfo("TestSuiteName", "Asset Creation Test");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("EquipmentHub Test in Progress..");

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
		System.out.println("EquipmentHub Test Completed.");
	}

	// Before all tests are conducted
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));

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

	// EH_001- Verify if the Equipment tile is displayed in the Kaye Main Hub Page
	@Test(groups = {
			"Regression" }, description = "EH_001- Verify if the Equipment tile is displayed in the Kaye Main Hub Page")
	public void EH_001() throws InterruptedException {
		extentTest = extent.startTest("EH_001- Verify if the Equipment tile is displayed in the Kaye Main Hub Page");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(MainHubPage.IsEquipmentTile_Visible(), true,
				"Fail: Equipment tile in main hub page is not visible");
		sa.assertAll();
	}

	// EH_005- Verify if user can navigate to the Equipment page by clicking on the
	// equipment tile

	@Test(groups = {
			"Regression" }, description = "EH_005- Verify if user can navigate to the Equipment page by clicking on the equipment tile")
	public void EH_005() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EH_005- Verify if user can navigate to the Equipment page by clicking on the equipment tile");
		SoftAssert sa = new SoftAssert();
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();

		sa.assertEquals(EquipmentHubPage.IsEquipmentHeader_Visible(), true,
				"Fail: Equipment heder of Equipment Hub page is not visible");
		sa.assertAll();
	}

//EH_006- Verify if the different equipments are grouped according to the different equipment types
	@Test(groups = {
			"Regression" }, description = "EH_006- Verify if the different equipments are grouped according to the different equipment types")
	public void EH_006() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EH_006- Verify if the different equipments are grouped according to the different equipment types");
		SoftAssert sa = new SoftAssert();

		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();
		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "006", "006A");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();

		sa.assertEquals(EquipmentHubPage.Is_IRTDTileVisible(), true,
				"Fail: IRTD List Box  is not available in Equipment Hub page");

		sa.assertEquals(EquipmentHubPage.Is_VRTLoggerVisible(), true,
				"Fail: VRT List Box  is not available in Equipment Hub page");

		sa.assertAll();
	}

//EH_007- Verify if the VRT equipment group tile is by default present in the Equipment page, even before creation of any equipment

	@Test(groups = {
			"Regression" }, description = "EH_007- Verify if the VRT equipment group tile is by default present in the Equipment page, even before creation of any equipment")
	public void EH_007() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EH_007- Verify if the VRT equipment group tile is by default present in the Equipment page, even before creation of any equipment");
		SoftAssert sa = new SoftAssert();
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();

		sa.assertEquals(EquipmentHubPage.Is_VRTLoggerVisible(), true,
				"Fail: VRT equipment group tile is not by default present in the Equipment HUB page");

		sa.assertAll();
	}

//EH_009- Verify if clicking on the Back button redirects to the Main Hub Page

	@Test(groups = {
			"Regression" }, description = "EH_009- Verify if clicking on the Back button redirects to the Main Hub Page")
	public void EH_009() throws InterruptedException, IOException {
		extentTest = extent.startTest("EH_009- Verify if clicking on the Back button redirects to the Main Hub Page");
		SoftAssert sa = new SoftAssert();
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		MainHubPage = EquipmentHubPage.ClickBackBtn();
		sa.assertEquals(MainHubPage.IsEquipmentTile_Visible(), true,
				"Fail: Equipment tile in main hub page is not visible");
		sa.assertAll();
	}

//EH_010- Verify if user have a scope to add a new equipment from the Equipment screen

	@Test(groups = {
			"Regression" }, description = "EH_010- Verify if user have a scope to add a new equipment from the Equipment screen")
	public void EH_010() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("EH_010- Verify if user have a scope to add a new equipment from the Equipment screen");
		SoftAssert sa = new SoftAssert();

		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();
		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EH010", "10");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		String ExpAlrtMsg = "Data saved successfully";
		String ActAlertMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Equipment has not  created successfully");
		sa.assertAll();
	}
	// EH_011- Verify if only privileged users are allowed to add new equipments
	// This TC is covered in UM3 class.

	// EH_012- Verify if clicking on the Home button in the Equipment Hub Page
	// displays the main hub page

	@Test(description = "EH_012- Verify if clicking on the Home button in the Equipment Hub Page displays the main hub page")
	public void EH_012() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EH_012- Verify if clicking on the Home button in the Equipment Hub Page displays the main hub page");
		SoftAssert sa = new SoftAssert();

		EquipmentHubPage = MainHubPage.ClickEquipmentTile();

		MainHubPage = EquipmentHubPage.Click_Home_Icon_AppBar();

		sa.assertEquals(MainHubPage.Is_mainHubPageTitle_Visible(), true,
				"FAIL: Clicking Home icon/button in bottom app bar do not redirect to Mains Hub page");
		sa.assertAll();
	}

	// EH_013- Verify if clicking on the Help button in the Equipment Hub Page
	// displays the online help menu for that page

	@Test(description = "EH_013- Verify if clicking on the Help button in the Equipment Hub Page displays the online help menu for that page")
	public void CAL042() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EH_013- Verify if clicking on the Help button in the Equipment Hub Page displays the online help menu for that page");
		SoftAssert sa = new SoftAssert();

		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		tu.Click_Help_Icon_AppBar();
		sa.assertEquals(tu.get__HelpMenu_HdrText(), "The Equipment Hub",
				"FAIL: Clicking Help icon/button in bottom app bar"
						+ "do not display the SThe Equipment Hub Help context window");
		sa.assertAll();
	}

	// EH_014- Verify if clicking on the Windows Help button in the Equipment Hub
	// Page displays the Windows help menu document
	// This TC will handle manually

//EH_015- Verify if clicking on the About button in the Equipment Hub Page displays the current software version, Console IP           
	@Test(dataProvider = "SET033", dataProviderClass = setupCreationUtility.class, description = "EH_015- Verify if clicking on the About button in the Equipment Hub Page displays the current software version, Console IP")
	public void CAL044(String SWVer) throws InterruptedException, UnknownHostException {
		extentTest = extent.startTest(
				"EH_015- Verify if clicking on the About button in the Equipment Hub Page displays the current software version, Console IP");
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
}
