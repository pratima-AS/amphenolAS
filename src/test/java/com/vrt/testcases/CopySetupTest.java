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
import com.vrt.pages.RWFileSelctionPage;
import com.vrt.pages.AuditPage;

import com.vrt.utility.TestUtilities;
import com.vrt.utility.assetCreationUtility;

public class CopySetupTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p

	public CopySetupTest() throws IOException {
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
	RWFileSelctionPage RWFileSelctionPage;
	CopySetuppage CopySetuppage;
	SelectBaseStationPage SelectBaseStationPage;
	OverlayWiringImagePage OverlayWiringImagePage;
	AuditPage AuditPage;

	// Before All the tests are conducted
	// @BeforeTest
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, AWTException, ParseException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_" + "copySetupTest" + ".html",
				true);
		extent.addSystemInfo("TestSuiteName", "copySetupTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("copySetupTest Test in Progress..");

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
		assetDetailsPage.resetWebElements();
		System.out.println("copySetupTest  Completed.");
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
	 * Copy Asset Test cases/scripts	 * 
	 * 
	 ******************************/

	// CS001-Verify if the validation message is displayed when only one asset
	// exists and clicked on copy button

	@Test(groups = {
			"Regression" }, description = "CS001-Verify if the validation message is displayed when only one asset exists and clicked on copy button")
	public void CS001() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest(
				"CS001-Verify if the validation message is displayed when only one asset exists and clicked on copy button");
		SoftAssert sa = new SoftAssert();

		assetDetailsPage.CopyStupBtn_WITH_oneAsset();
		String ActAlrtMsg = assetDetailsPage.alertMeg_CopyAsset_WithOneAsset();
		String ExpAlrtMsg = "To perform Copy Setup more than 1 asset required.";
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Alert message is not displayed");
		sa.assertAll();
	}

	// CS002-Verify if clicking on _Copy_ button invokes _Copy Setup_ screen when
	// more than one Asset exists.
	// CS011-Verify if information related to the Setups inside of the table is
	// correct
	// This CS011 also cover under the below script

	@Test(dataProvider = "CS002", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "CS002-Verify if clicking on _Copy_ button invokes _Copy Setup_ screen when more than one Asset exists")

	public void CS002(String AName, String AID, String AType, String AManufaturer, String ALocation, String SetupName,
			String SensorCount, String comment, String TempCount, String TCSensorLabel, String Qstart)
			throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"CS002-Verify if clicking on _Copy_ button invokes _Copy Setup_ screen when more than one Asset exists");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();

		assetCreationPage.assetCreation(AName, AID, AType, AManufaturer, ALocation);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile(AName);
//Define Setup 
//Define Setup
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetupName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorCount);
		defineSetupPage.enter_defineSetupPage_comments(comment);
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
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_NxtBtn();
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();

		Setup_ReviewPage.click_Save_Btn("Manual", "Yes", "1", getPW("adminFull"));

		Thread.sleep(1000);
		assetDetailsPage = Setup_ReviewPage.click_backBtn();
		String SetupCreatedDate = assetDetailsPage.Get_Setup_Date();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();

		sa.assertEquals(CopySetuppage.CopySetupPage_Title(), true, "FAIL:Copy Setup Page Title is not displayed ");
		String AN = CopySetuppage.get_assetname();

		sa.assertEquals(AN, "AS002", "FAIL:Asset is not displayed ");
		String SN = CopySetuppage.get_copysetupname();

		sa.assertEquals(SN, SetupName, "FAIL:Copy Setup name  is not displayed ");
		String SensrNum = CopySetuppage.get_sensrNumber();
		sa.assertEquals(SensrNum, SensorCount, "FAIL:Sensor number is not displayed ");

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		String cmnt = CopySetuppage.get_comment();
		sa.assertEquals(cmnt, comment, "FAIL: comment  is not displayed ");
		String SetupCopiedDate = CopySetuppage.get_date();

		sa.assertEquals(SetupCreatedDate, SetupCopiedDate, "FAIL:date is not matching ");
		sa.assertAll();

	}

	// CS003-Verify if all available Assets are listed on the Copy Setup screen
	@Test(groups = {
			"Regression" }, description = "CS003-Verify if all available Assets are listed on the Copy Setup  screen")
	public void CS003() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest("CS003-Verify if all available Assets are listed on the Copy Setup  screen");
		SoftAssert sa = new SoftAssert();

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		String AstN = CopySetuppage.get_assetname();
		System.out.println(AstN);
		sa.assertEquals(AstN, "AS002", "FAIL:Asset is not displayed ");
		sa.assertAll();

	}

	// CS004-Verify if the Asset list can be collapsed or expanded in the Copy Setup
	// screen.
	@Test(groups = {
			"Regression" }, description = "CS004-Verify if the Asset list can be collapsed or expanded in the Copy Setup screen.")
	public void CS004() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent
				.startTest("CS004-Verify if the Asset list can be collapsed or expanded in the Copy Setup screen.");
		SoftAssert sa = new SoftAssert();

		// By default the assets will be expanded
		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();

		sa.assertEquals(CopySetuppage.IsCheckBox_Displayed(), true, "FAIL:Assetnames are expanded ");

		CopySetuppage.clickCollapse_Icon();
		// sa.assertEquals(CopySetuppage.Is_assetnameTiles_Empty(),true,"FAIL:Assetnames
		// are collapsed ");
		sa.assertAll();

	}

	// CS006-Verify if the _Setup_ table is displayed in the table.

	@Test(groups = { "Regression" }, description = "CS006-Verify if the _Setup_ table is displayed in the table.")
	public void CS006() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest("CS006-Verify if the _Setup_ table is displayed in the table.");
		SoftAssert sa = new SoftAssert();

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		String CSName = CopySetuppage.get_copysetupname();
		// System.out.println(CSName);
		sa.assertEquals(CSName, "CS002", "FAIL:Copy Setup name  is not displayed ");
		sa.assertAll();

	}

	// CS007-Verify if _Sensors_ column is displayed in the table.

	@Test(groups = { "Regression" }, description = "CS007-Verify if _Sensors_ column is displayed in the table.")
	public void CS007() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest("CS007-Verify if _Sensors_ column is displayed in the table.");
		SoftAssert sa = new SoftAssert();

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		String SNum = CopySetuppage.get_sensrNumber();
		// System.out.println(SNum);
		sa.assertEquals(SNum, "1", "FAIL:Sensor number is not displayed ");
		sa.assertAll();

	}

	// CS008-Verify if _Comment_ column is displayed in the table.

	@Test(groups = { "Regression" }, description = "CS008-Verify if _Comment_ column is displayed in the table.")
	public void CS008() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest("CS008-Verify if _Comment_ column is displayed in the table.");
		SoftAssert sa = new SoftAssert();

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		String com = CopySetuppage.get_comment();
		// System.out.println(com);
		sa.assertEquals(com, "text", "FAIL: comment  is not displayed ");
		sa.assertAll();

	}

	// CS009-Verify if _Date_ column is displayed in the table.
	@Test(groups = { "Regression" }, description = "CS009-Verify if _Date_ column is displayed in the table.")
	public void CS009() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest("CS009-Verify if _Date_ column is displayed in the table.");
		SoftAssert sa = new SoftAssert();

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		sa.assertEquals(CopySetuppage.IsdateDisplayed(), true, "FAIL: date  is not displayed ");
		sa.assertAll();

	}

	// CS010-Verify if _Select all_ column is displayed in the table.

	@Test(groups = { "Regression" }, description = "CS010-Verify if _Select all_ column is displayed in the table.")
	public void CS010() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest("CS010-Verify if _Select all_ column is displayed in the table.");
		SoftAssert sa = new SoftAssert();

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		sa.assertEquals(CopySetuppage.SelectAll_ChkboxField_Isvisible(), true, "FAIL: date  is not displayed ");
		sa.assertAll();

	}
	// CS011-Verify if information related to the Setups inside of the table is
	// correct
	// This TC covering under CS002 script

	// CS012-Verify if the _Sort_ option for each column but _Selected_ is available
	// and functions correctly

	@Test(dataProvider = "CS012", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "CS012-Verify if the _Sort_ option for each column but _Selected_ is available and functions correctly")

	public void CS012(String SetupName, String SensorCount, String comment, String TempCount, String TCSensorLabel,
			String Qstart) throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"CS012-Verify if the _Sort_ option for each column but _Selected_ is available and functions correctly");
		SoftAssert sa = new SoftAssert();

// Define Setup
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("AS002");
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetupName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorCount);
		defineSetupPage.enter_defineSetupPage_comments(comment);
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
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_NxtBtn();
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();

		Setup_ReviewPage.click_Save_Btn("Manual", "Yes", "1", getPW("adminFull"));

		Thread.sleep(1000);
		assetDetailsPage = Setup_ReviewPage.click_backBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		CopySetuppage.Click_SETUP_Header();
		String SN = CopySetuppage.get_copysetupname();
		sa.assertEquals(SN, "Atest", "FAIL: Setup name not sorted by starting A-Z order");
		CopySetuppage.Click_SETUP_Header();
		String SN1 = CopySetuppage.get_copysetupname();
		sa.assertEquals(SN1, "CS002", "FAIL: Setup name not sorted by starting Z-A order");

	}

	// CS013-Verify if the _Filter_ functionality is implemented in each column but
	// _Selected_

	@Test(dataProvider = "CS013", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "CS013-Verify if the _Filter_ functionality is implemented in each column but _Selected_")

	public void CS013(String SetupName, String SensorCount, String comment, String TempCount, String TCSensorLabel,
			String Qstart) throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent
				.startTest("CS013-Verify if the _Filter_ functionality is implemented in each column but _Selected_");
		SoftAssert sa = new SoftAssert();

		// Define Setup
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("AS002");
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetupName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorCount);
		defineSetupPage.enter_defineSetupPage_comments(comment);
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
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_NxtBtn();
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();

		Setup_ReviewPage.click_Save_Btn("Manual", "Yes", "1", getPW("adminFull"));

		Thread.sleep(1000);
		assetDetailsPage = Setup_ReviewPage.click_backBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();

		CopySetuppage.filter_On_SETUP("CS002");
		String SN = CopySetuppage.get_copysetupname();
		sa.assertEquals(SN, "CS002", "FAIL: Searched Set up name is not exist");

		assetDetailsPage = CopySetuppage.Click_Back_Btn();
		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		CopySetuppage.filter_On_Sensors("1");
		String Sensr = CopySetuppage.get_sensrNumber();
		System.out.println(Sensr);
		sa.assertEquals(Sensr, "1", "FAIL: Searched sensor number is not exist");

		assetDetailsPage = CopySetuppage.Click_Back_Btn();
		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		CopySetuppage.filter_On_Comments("text");
		String cmnt = CopySetuppage.get_comment();
		System.out.println(cmnt);
		sa.assertEquals(cmnt, "text", "FAIL: Searched comments is not exist");

		sa.assertAll();

	}

	// CS014-Verify if the _Copy_ button on _Copy Setup_ page is visible but if no
	// one setup file is selected, should show message _Please select at least one
	// setup to copy_

	@Test(groups = {
			"Regression" }, description = "CS014-Verify if the _Copy_ button on _Copy Setup_ page is visible but if no one setup file is selected, should show message _Please select at least one setup to copy_")
	public void CS014() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest(
				"CS014-Verify if the _Copy_ button on _Copy Setup_ page is visible but if no one setup file is selected, should show message _Please select at least one setup to copy_");
		SoftAssert sa = new SoftAssert();

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();

		sa.assertEquals(CopySetuppage.Copysetup_btn_IsEnable(), true, "FAIL: date  is not displayed ");
		CopySetuppage.click_copy_Btn();

		String ExpAlrtMsg = "Please select at least one setup to copy";

		String ActAlrtMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "Fail : Actual Alert message is not match with expected alert contain");
		sa.assertAll();
	}

	// CS015-Verify if single or multiple Setups selection can be done on _Copy
	// Setup_ screen
	// CS016-Verify if only selected setups files are copied to the Asset
	// This CS016 test case also covered under CS015.

	@Test(groups = {
			"Regression" }, description = "CS015A-Verify if single Setups selection can be done on _Copy Setup_ screen")
	public void CS015A() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest("CS015A-Verify if single Setups selection can be done on _Copy Setup_ screen");
		SoftAssert sa = new SoftAssert();

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();

		CopySetuppage.clickONCheckBOX_1();
		String selectedSetupFile = CopySetuppage.get_copysetupname();
		CopySetuppage.click_copy_Btn();
		CopySetuppage.select_alertOption("Yes");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String ExpAlrtMsg = "1 setup(s) copied successfully.";

		String ActAlrtMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "Fail : Actual Alert message is not match with expected alert contain");
		assetDetailsPage = CopySetuppage.Click_Back_Btn();
		String sname = assetDetailsPage.Get_Setup_Name();
		sa.assertEquals(selectedSetupFile, sname, "Fail : Setup name is not matching with copied setup name");

		sa.assertAll();
	}

	// CS015B-Verify if single or multiple Setups selection can be done on _Copy
	// Setup_ screen

	@Test(groups = {
			"Regression" }, description = "CS015B-Verify if multiple Setups selection can be done on _Copy Setup_ screen")
	public void CS015B() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest("CS015B-Verify if multiple Setups selection can be done on _Copy Setup_ screen");
		SoftAssert sa = new SoftAssert();

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		CopySetuppage.Click_Selectall_chkbox();
		CopySetuppage.click_copy_Btn();
		CopySetuppage.select_alertOption("Yes");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		CopySetuppage.select_alertOption("Yes");

		String ExpAlrtMsg = "3 setup(s) copied successfully.";

		String ActAlrtMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "Fail : Actual Alert message is not match with expected alert contain");
		sa.assertAll();
	}

	// This CS016 test case also covered under CS015.

	// CS017-Verify if selecting the file (s) and clicking on _Copy_ button invokes
	// _Do you want to copy the setup(s)_ with -Yes- and -No- buttons.

	@Test(groups = {
			"Regression" }, description = "CS017-Verify if selecting the file (s) and clicking on _Copy_ button invokes _Do you want to copy the setup(s)_ with -Yes- and -No- buttons.")
	public void CS017() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest(
				"CS017-Verify if selecting the file (s) and clicking on _Copy_ button invokes _Do you want to copy the setup(s)_ with -Yes- and -No- buttons.");
		SoftAssert sa = new SoftAssert();

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		CopySetuppage.Click_Selectall_chkbox();
		CopySetuppage.click_copy_Btn();

		String ActcopysetupAlert = CopySetuppage.get_text_copyAst_popup();
		String ExpAlrtMsg = "Do you want to copy the setup(s)?";
		sa.assertEquals(ActcopysetupAlert, ExpAlrtMsg,
				"Fail : Actual Alert message is not match with expected alert contain");
		sa.assertAll();

	}

	// CS018-Verify if clicking -Yes- invokes -Enter user Credentials- box. and
	// clicking No will not perform copy operation
	@Test(groups = {
			"Regression" }, description = "CS018A-Verify if clicking -Yes- invokes -Enter user Credentials- box. and clicking No will not perform copy operation")
	public void CS018A() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest(
				"CS018A-Verify if clicking -Yes- invokes -Enter user Credentials- box. and clicking No will not perform copy operation");
		SoftAssert sa = new SoftAssert();

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		CopySetuppage.Click_Selectall_chkbox();
		CopySetuppage.click_copy_Btn();
		CopySetuppage.select_alertOption("Yes");
		sa.assertEquals(CopySetuppage.UserLoginPopupVisible(), true, "FAIL: pop up not visible");
		sa.assertAll();

	}

	// CS018B-Verify if clicking -Yes- invokes -Enter user Credentials- box. and
	// clicking No will not perform copy operation
	@Test(groups = {
			"Regression" }, description = "CS018B-Verify if clicking -NO- invokes -Enter user Credentials- box. and clicking No will not perform copy operation")
	public void CS018B() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest(
				"CS018B-Verify if clicking -NO- invokes -Enter user Credentials- box. and clicking No will not perform copy operation");
		SoftAssert sa = new SoftAssert();

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		CopySetuppage.Click_Selectall_chkbox();
		CopySetuppage.click_copy_Btn();
		CopySetuppage.select_alertOption("No");

		sa.assertEquals(CopySetuppage.Copysetup_btn_IsEnable(), true, "FAIL: pop up not visible");
		sa.assertAll();

	}

	// CS019-Verify the setup is copied to the correct selected location for Copy
	// CS020-Verify if the Copied file is available at the Asset details setups
	// screen
	// CS022-Verify if Audit trail captures the name of the setup that was copied,
	// along with user id and user name, and date_time

	@Test(groups = {
			"Regression" }, description = "CS019,CS020,CS021-Verify the setup is copied to the correct selected location for Copy")
	public void CS019() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent
				.startTest("CS019,CS020,CS021-Verify the setup is copied to the correct selected location for Copy");
		SoftAssert sa = new SoftAssert();

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();

		CopySetuppage.clickONCheckBOX_1();
		String selectedSetupFile = CopySetuppage.get_copysetupname();
		CopySetuppage.click_copy_Btn();
		CopySetuppage.select_alertOption("Yes");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		CopySetuppage.select_alertOption("Yes");
		Thread.sleep(1000);
		assetDetailsPage = CopySetuppage.Click_Back_Btn();

		String sname = assetDetailsPage.Get_Setup_Name();
		sa.assertEquals(selectedSetupFile, sname,
				"Fail :  Copied file is not  available at the Asset details setups screen");
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "\"1\" setup(s) copied successfully by User ID : \"1\" , User Name: \"User1\"";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for Copy of a Detailed report");
		sa.assertAll();
	}

	// CS021-Verify if the same setup can be copied to the same location multiple
	// times
	@Test(groups = {
			"Regression" }, description = "CS021-Verify if the same setup can be copied to the same location multiple times")
	public void CS021() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent
				.startTest("CS021-Verify if the same setup can be copied to the same location multiple times");
		SoftAssert sa = new SoftAssert();

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		CopySetuppage.clickONCheckBOX_1();
		CopySetuppage.click_copy_Btn();
		CopySetuppage.select_alertOption("Yes");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String ActcopysetupAlert = CopySetuppage.get_text_copyAst_popup();
		String ExpAlrtMsg = "Setup \"CS002\" already exists, do you want to overwrite it?";
		sa.assertEquals(ActcopysetupAlert, ExpAlrtMsg,
				"Fail : overwrite Alert message is not match with expected alert contain");
		sa.assertAll();

	}

	// CS022-Verify if Audit trail captures the name of the setup that was copied,
	// along with user id and user name, and date_time
	// This TC has covered under CS019 script

	// CS005-Verify if the numbers of the Setup files in each Assets are displayed
	// correctly

	@Test(groups = {
			"Regression" }, description = "CS005-Verify if the numbers of the Setup files in each Assets are displayed correctly")
	public void CS0023() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent
				.startTest("CS005-Verify if the numbers of the Setup files in each Assets are displayed correctly");
		SoftAssert sa = new SoftAssert();

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		int setupcount = CopySetuppage.SetupCount();
		System.out.println(setupcount);
		sa.assertEquals(setupcount, 1, "Fail : Set up count is not fetched ");
		sa.assertAll();

	}

}
