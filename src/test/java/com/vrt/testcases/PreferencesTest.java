package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;

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
import com.vrt.utility.PreferencesUtility;
import com.vrt.utility.TestUtilities;
import com.vrt.pages.AuditPage;
import com.vrt.pages.EquipmentHubPage;
import com.vrt.pages.EquipmentPage;
import com.vrt.pages.IRTDHubPage;
import com.vrt.pages.IRTDDetailspage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.FileManagementPage;
import com.vrt.pages.PoliciesPage;
import com.vrt.pages.PreferencesPage;
import com.vrt.pages.Setup_CalculationsPage;
import com.vrt.pages.Setup_GroupSensorsPage;
import com.vrt.pages.Setup_QualParamPage;
import com.vrt.pages.Setup_ReviewPage;
import com.vrt.pages.Setup_SensorConfigPage;
import com.vrt.pages.Setup_defineSetupPage;
import com.vrt.pages.VRTLoggerHubPage;
import com.vrt.pages.VRTLoggersDetailspage;
import com.vrt.pages.SyncInPage;
import com.vrt.pages.SyncInAssetListPage;
import com.vrt.pages.Copyassetpage;
import com.vrt.pages.CopySetuppage;
import com.vrt.pages.RW_FileSelctionPage;
import com.vrt.pages.RW_CycleSelectionPage;
import com.vrt.pages.RW_DefineQualExposurePage;
import com.vrt.pages.RW_EditParametersPage;
import com.vrt.pages.RW_CustomizedCalculationPage;

public class PreferencesTest extends BaseClass {

	public PreferencesTest() throws IOException {
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
	AuditPage AuditPage;
	EquipmentHubPage EquipmentHubPage;
	EquipmentPage EquipmentPage;
	IRTDHubPage IRTDHubPage;
	IRTDDetailspage IRTDDetailspage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	FileManagementPage FileManagementPage;
	PoliciesPage PoliciesPage;
	VRTLoggerHubPage VRTLoggerHubPage;
	VRTLoggersDetailspage VRTLoggersDetailspage;
	SyncInPage SyncInPage;
	SyncInAssetListPage SyncInAssetListPage;
	Copyassetpage Copyassetpage;
	Setup_defineSetupPage defineSetupPage;
	Setup_SensorConfigPage Setup_SensorConfigPage;
	Setup_GroupSensorsPage Setup_GroupSensorsPage;
	Setup_CalculationsPage Setup_CalculationsPage;
	Setup_QualParamPage Setup_QualParamPage;
	Setup_ReviewPage Setup_ReviewPage;
	CopySetuppage CopySetuppage;
	PreferencesPage PreferencesPage;
	RW_FileSelctionPage RW_FileSelctionPage;
	RW_CycleSelectionPage CycleSelectionPage;
	RW_DefineQualExposurePage DefineQual_ExposurePage;
	RW_EditParametersPage EditParametersPage;
	RW_CustomizedCalculationPage CustomizedCalculationPage;

	static String un = "User1";

	// Before Class/Test
	// @BeforeTest
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_" + "PreferencesTest" + ".html",
				true);
		extent.addSystemInfo("TestSuiteName", "PreferencesTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name2"));
		System.out.println("PreferencesTest in Progress..");

		// Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		// Rename Preferences.ini file
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "Preferences.ini");
		// Rename Policy file
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "Policies.ini");
		// Rename the VRT folder if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTSetups");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\", "Cache");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTEquipments");

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		extent.addSystemInfo("VRT Version", LoginPage.get_SWVersion_About_Text());
		UserManagementPage = LoginPage.DefaultLogin();
		// Create the default Admin USer
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
		// Create a New Admin User but do not change its PW
		UserManagementPage.CreateAdminUser(getUID("adminFull"), getPW("adminFull"), "NewU1", getUID("Newuser"),
				getPW("Newuser"), "Admin", "12324", "abc@gmail.com");
		// tu.click_Close_alertmsg();
		MainHubPage = UserManagementPage.ClickBackButn();
		// Conduct a Syncin operation
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPage(getUID("adminFull"), getPW("adminFull"));
		SyncInPage.enter_Filepath("syncin");
		SyncInPage.click_FltrBtn();
		SyncInAssetListPage = SyncInPage.click_SyncInOK_btn();
		SyncInAssetListPage.click_EquipmentCheckBox();
		SyncInAssetListPage.click_SelectAllBtn();
		SyncInAssetListPage.click_OkBtn();
		SyncInAssetListPage.click_AlrtYesBtn();
		Thread.sleep(8000);
		SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
		Thread.sleep(2000);

	}

	// After Class/Test
	// @AfterTest
	@AfterClass
	public void endReport() throws InterruptedException {
		extent.flush();
		extent.close();
		System.out.println("PreferencesTest Completed.");
		Thread.sleep(500);
	}

	// Before Method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		// System.out.println("Synin Process check in UMI");
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		PreferencesPage = UserManagementPage.Click_PreferenceTab();
	}

	// After Method-TearDown
	@AfterMethod(alwaysRun = true)
	public void Teardown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			// to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getName() + " #");
			// to add error/exception in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getThrowable() + " #");

			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			// to add screenshot in extent report
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1));

			extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath1));
			// to add screenshot/video in extent report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName() + " #");
			String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2));
			// //to add screenshot in extent report

		}
		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report
		driver.quit();
	}

	/**********************
	 * Test Cases
	 * 
	 ***********************/
	
	// PREF001
	@Test(groups = { "Regression" }, description = "PREF001-Verify the details displayed in Preferences screen")
	public void PREF001() throws Exception {
		extentTest = extent.startTest("PREF001-Verify the details displayed in Preferences screen");
		SoftAssert sa = new SoftAssert();

		// Validate presence of Preferences screen
		sa.assertEquals(PreferencesPage.IsPreferences_screenDisplayed(), true, "FAIL:Landed to wrong page");

		sa.assertEquals(PreferencesPage.IsCompanyNameTextBox_Presence(), true,
				"FAIL: No CompanyNameTextBox_ field present");

		sa.assertEquals(PreferencesPage.IsTemparatureComboBox_Presence(), true,
				"FAIL: No Temparature ComboBox field present");

		sa.assertEquals(PreferencesPage.IsMaxGroupComboBox_Presence(), true,
				"FAIL: No   MaxGroupComboBox field present");

		sa.assertEquals(PreferencesPage.IsLineFrequencyComboBox_Presence(), true,
				"FAIL: No LineFrequencyComboBox field present");

		sa.assertEquals(PreferencesPage.IsPressureComboBox_Presence(), true, "FAIL: No PressureComboBox field present");

		sa.assertEquals(PreferencesPage.IsMachineIDTextBlock_Presence(), true,
				"FAIL: No MachineIDTextBlock field present");

		sa.assertEquals(PreferencesPage.IsAlternateMachineID_Presence(), true,
				"FAIL: No AlternateMachineID field present");

		sa.assertEquals(PreferencesPage.IsDataDictionaryTextBlock_Presence(), true,
				"FAIL: No DataDictionaryTextBlock field present");

		sa.assertEquals(PreferencesPage.IsAllowLethalityComboBox_Presence(), true,
				"FAIL: No AllowLethalityComboBox field present");

		sa.assertEquals(PreferencesPage.IsIRTDStudyTextBoxText_Presence(), true,
				"FAIL: No IRTDStudyTextBoxText field present");

		sa.assertEquals(PreferencesPage.IsChageUserImageTextBlock_Presence(), true,
				"FAIL: No ChageUserImageTextBlock field present");

		sa.assertEquals(PreferencesPage.IsBatteryWarningComboBox_Presence(), true,
				"FAIL: No BatteryWarningComboBox field present");

		sa.assertEquals(PreferencesPage.IsPerformedByTextBlock_Presence(), true,
				"FAIL: No PerformedByTextBlock field present");

		sa.assertEquals(PreferencesPage.IsReviewedByTextBlock_Presence(), true,
				"FAIL: No IsReviewedByTextBlock field present");

		sa.assertEquals(PreferencesPage.IsFooterFirstPage_Presence(), true, "FAIL: No FooterFirstPage field present");

		sa.assertEquals(PreferencesPage.IsFooterLastPage_Presence(), true, "FAIL: No FooterLastPage field present");

		sa.assertEquals(PreferencesPage.IsFooterAllPages_Presence(), true, "FAIL: No FooterAllPages field present");

		sa.assertAll();

	}

	// PREF002-Verify the validations for Comapny Name_field

	@Test(dataProvider = "PREF002", dataProviderClass = PreferencesUtility.class, groups = {
			"Regression" }, description = "PREF002-Verify  the validations for Comapny Name_field")
	public void PREF002(String CompanyName, String Expmsg) throws Exception {
		extentTest = extent.startTest("PREF002-Verify  the validations for Comapny Name_field");
		SoftAssert sa = new SoftAssert();
		PreferencesPage.enterCompanyName(CompanyName);
		PreferencesPage.click_SaveBtn();
		String Actmsg = PreferencesPage.AlertMsg();

		sa.assertEquals(Actmsg, Expmsg, "Fail: Alert message is not displaying");
		sa.assertAll();
	}

	// PREF002A-Verify if more than 100 characters are entered for company name
	// field

	@Test(groups = {
			"Regression" }, dataProvider = "PREF002A", dataProviderClass = PreferencesUtility.class, description = "PREF002A-Verify if more than 100 characters are entered for company name field")
	public void PREF002A(String CompanyName) throws Exception {
		extentTest = extent.startTest("PREF002A-Verify if more than 100 characters are entered for company name field");
		SoftAssert sa = new SoftAssert();
		// Entered More than 100 chars
		PreferencesPage.enterCompanyName(CompanyName);
		Thread.sleep(1000);
		String CN = PreferencesPage.Fetch_CompanyName();

		sa.assertEquals(CN.length(), 100, "Fail: Accepted  more than 100 characters in Company Name field");
		sa.assertAll();
	}

	// PREF002B-Verify if user Do not enter anything in the Company name field and
	// save

	@Test(groups = {
			"Regression" }, description = "PREF002B-Verify if user Do not enter anything in the Company name field and save")
	public void PREF002B() throws Exception {
		extentTest = extent
				.startTest("PREF002B-Verify if user Do not enter anything in the Company name field and save");
		SoftAssert sa = new SoftAssert();
		// Entered More than 100 chars

		PreferencesPage.enterCompanyName("");
		PreferencesPage.click_SaveBtn();
		String Actmsg = PreferencesPage.AlertMsg();
		String Expmsg = "Company name can not be blank, please provide valid company name";
		sa.assertEquals(Actmsg, Expmsg, "Fail: Alert message is not displaying");
		sa.assertAll();
	}

	// PREF002C-Verify default company name is displaying as "KAYE".

	@Test(groups = { "Regression" }, description = "PREF002C-Verify default company name is displaying as KAYE")
	public void PREF002C() throws Exception {
		extentTest = extent.startTest("PREF002C-Verify default company name is displaying as KAYE");
		SoftAssert sa = new SoftAssert();
		String CN = PreferencesPage.Fetch_CompanyName();
		sa.assertEquals(CN, "KAYE", "Fail: By default company name is not displaying as KAYE");
		sa.assertAll();
	}

	// PREF003-Verify if Temperature Units with dropdown menu is displayed is
	// default Celsius selected

	@Test(groups = {
			"Regression" }, description = "PREF003-Verify if Temperature Units with dropdown menu is displayed is default Celsius selected")
	public void PREF003() throws Exception {
		extentTest = extent.startTest(
				"PREF003-Verify if Temperature Units with dropdown menu is displayed is default Celsius selected");
		SoftAssert sa = new SoftAssert();
		String TU = PreferencesPage.Fetch_Defaultval_TemparatureUnits();
		// System.out.println(TU);
		sa.assertEquals(TU, "Celsius", "Fail: By default company name is not displaying as Celsius");
		sa.assertAll();
	}

	// PREF004-Verify if Max Groups with dropdown menu is displayed with default 20
	// selected

	@Test(groups = {
			"Regression" }, description = "PREF004-Verify if Max Groups with dropdown menu is displayed with default 20 selected")
	public void PREF004() throws Exception {
		extentTest = extent
				.startTest("PREF004-Verify if Max Groups with dropdown menu is displayed with default 20 selected");
		SoftAssert sa = new SoftAssert();
		String MG = PreferencesPage.Fetch_Defaultval_MaxGroupComboBox();
		// System.out.println(MG);
		sa.assertEquals(MG, "20", "Fail: By default  Max Groups dropdown menu is not displaying as 20");
		sa.assertAll();
	}

	// PREF005-Verify if Pressure Units with dropdown menu is displayed with Default
	// Bar selected
	@Test(groups = {
			"Regression" }, description = "PREF005-Verify if Pressure Units with dropdown menu is displayed with Default Bar selected")
	public void PREF005() throws Exception {
		extentTest = extent.startTest(
				"PREF005-Verify if Pressure Units with dropdown menu is displayed with Default Bar selected");
		SoftAssert sa = new SoftAssert();
		String Psr = PreferencesPage.Fetch_Defaultval_PressureComboBox();
		System.out.println(Psr);
		sa.assertEquals(Psr, "Bar", "Fail: By default  Bar  is not displaying as Pressure Units");
		sa.assertAll();
	}

	// PREF006-Verify if Allow Users to change Lethality with dropdown menu is
	// displayed with default value NO.
	@Test(groups = {
			"Regression" }, description = "PREF006-Verify if Allow Users to change Lethality with dropdown menu is displayed with default value NO.")
	public void PREF006() throws Exception {
		extentTest = extent.startTest(
				"PREF006-Verify if Allow Users to change Lethality with dropdown menu is displayed with default value NO.");
		SoftAssert sa = new SoftAssert();
		String AL = PreferencesPage.Fetch_Defaultval_AllowLethalityComboBox();
		System.out.println(AL);
		sa.assertEquals(AL, "No",
				"Fail: By default  NO is not displaying in Allow Users to change Lethality drop down box");
		sa.assertAll();
	}

	// PREF007-Verify the Line Frequency field has two options 50Hz and 60Hz with
	// default 50Hz selected-Verify the Line Frequency field has two options 50Hz
	// and 60Hz with default 50Hz selected

	@Test(groups = {
			"Regression" }, description = "PREF007-Verify  the Line Frequency field has two options 50Hz and 60Hz with default 50Hz selected")
	public void PREF007() throws Exception {
		extentTest = extent.startTest(
				"PREF007-Verify  the Line Frequency field has two options 50Hz and 60Hz with default 50Hz selected");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(PreferencesPage.Fetch_Defaultval_LineFrequencyComboBox(), "50 Hz",
				"Fail: By default  LineFrequency ComboBox is not displaying as 50 Hz");
		PreferencesPage.click_LineFrequency();
		Thread.sleep(3000);
		sa.assertEquals(PreferencesPage.get_LineFrequency_options(1), "50 Hz",
				"Fail: First option for Line Frequency field is not 50 HZ ");
		sa.assertEquals(PreferencesPage.get_LineFrequency_options(2), "60 Hz",
				"Fail: Second option for Line Frequency field is not 60 HZ ");

		sa.assertAll();
	}

	// PREF008-Verify if Machine ID field is displayed with default Machine ID
	// confirm the correctness of the ID Displayed
	@Test(groups = {
			"Regression" }, description = "PREF008-Verify if Machine ID field is displayed with "
					+ "default Machine ID confirm the correctness of the ID Displayed")
	public void PREF008() throws Exception {
		extentTest = extent.startTest(
				"PREF008-Verify if Machine ID field is displayed with default Machine ID confirm the correctness of the ID Displayed");
		SoftAssert sa = new SoftAssert();
		String MID = PreferencesPage.Fetch_MachineIDTextBlock();
		// System.out.println(MID);
		MainHubPage = PreferencesPage.ClickBackButn();

		PreferencesPage = MainHubPage.ClickAdminTile_Prefpage();
		String MID1 = PreferencesPage.Fetch_MachineIDTextBlock();
		sa.assertEquals(MID, MID1, "Fail: Incorrect Machine ID");
		sa.assertAll();
	}

//PREF009-Verify  the validations for Alternate Machine ID_ field

	@Test(groups = { "Regression" }, description = "PREF009-Verify  the validations for Alternate Machine ID_ field")
	public void PREF009() throws Exception {
		extentTest = extent.startTest("PREF009-Verify  the validations for Alternate Machine ID_ field");
		SoftAssert sa = new SoftAssert();
		PreferencesPage.enter_AlternateMachineID("ABCDE12345");
		PreferencesPage.click_SaveBtn();

		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PreferencesPage.ClickBackButn();
		PreferencesPage = MainHubPage.ClickAdminTile_Prefpage();

		sa.assertEquals(PreferencesPage.Fetch_AlternateMachineID(), "ABCDE12345",
				"Fail: User unable to enter alternative machine ID");
		sa.assertAll();

	}

	// PREF009A-Verify if more than 50 characters are entered for Alternate Machine
	// ID_ field

	@Test(groups = {
			"Regression" }, dataProvider = "PREF009A", dataProviderClass = PreferencesUtility.class, description = "PREF009A-Verify if more than 50 characters are entered for Alternate MachineID_ field")
	public void PREF009A(String AlternateMachineID) throws Exception {
		extentTest = extent
				.startTest("PREF009A-Verify if more than 50 characters are entered for Alternate MachineID_ field");
		SoftAssert sa = new SoftAssert();
		// Entered More than 50 chars
		PreferencesPage.enter_AlternateMachineID(AlternateMachineID);
		Thread.sleep(1000);
		String AM = PreferencesPage.Fetch_AlternateMachineID();

		sa.assertEquals(AM.length(), 50, "Fail: Accepted  more than 50 characters for Alternate Machine ID_ field");
		sa.assertAll();

	}

	// PREF009B-Verify the validations for Alternate Machine ID_ field

	@Test(dataProvider = "PREF009B", dataProviderClass = PreferencesUtility.class, groups = {
			"Regression" }, description = "PREF009B-Verify  the validations for Alternate Machine ID_ field                ")
	public void PREF009B(String AlternateMachineID, String Expmsg) throws Exception {
		extentTest = extent.startTest("PREF009B-Verify  the validations for Alternate Machine ID_ field   ");
		SoftAssert sa = new SoftAssert();
		PreferencesPage.enter_AlternateMachineID(AlternateMachineID);
		PreferencesPage.click_SaveBtn();
		String Actmsg = PreferencesPage.AlertMsg();

		sa.assertEquals(Actmsg, Expmsg, "Fail: Alert message is not displaying");
		sa.assertAll();
	}

//PREF010-Verify if Data Directory is displayed in the bottom of the Preferences screen

	@Test(groups = {
			"Regression" }, description = "PREF010-Verify if Data Directory is displayed in the bottom of the Preferences screen")
	public void PREF010() throws Exception {
		extentTest = extent
				.startTest("PREF010-Verify if Data Directory is displayed in the bottom of the Preferences screen");
		SoftAssert sa = new SoftAssert();
		String DD = PreferencesPage.Fetch_DataDictionaryText();
		System.out.println(DD);
		sa.assertEquals(DD, "C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\",
				"Fail: Incorrect Data Directory");
		sa.assertAll();
	}

	// PREF011-Verify if IRTD Stability Threshold lower Limit displayed with default
	// value 400.

	@Test(groups = {
			"Regression" }, description = "PREF011-Verify if IRTD Stability Threshold lower Limit displayed with default")
	public void PREF011() throws Exception {
		extentTest = extent.startTest("PREF011-Verify if IRTD Stability Threshold lower Limit displayed with default");
		SoftAssert sa = new SoftAssert();
		String IRTDstudytxt = PreferencesPage.Fetch_IRTDStudyTextBoxText();
		sa.assertEquals(IRTDstudytxt, "400",
				"Fail: IRTD Stability Threshold lower Limit with dropdown menu is  not displayed.with default 400 degrees celcius");
		sa.assertAll();
	}

	// PREF012-Verify if able to save the changes at Preferenes using SAVE button

	@Test(groups = {
			"Regression" }, description = "PREF012-Verify if able to save the changes at Preferenes using SAVE button")
	public void PREF012() throws Exception {
		extentTest = extent.startTest("PREF012-Verify if able to save the changes at Preferenes using SAVE button");
		SoftAssert sa = new SoftAssert();
		Thread.sleep(1000);
		PreferencesPage.enter_AlternateMachineID("ABCDE12345");
		PreferencesPage.enterCompanyName("xxx");
		String CN = PreferencesPage.Fetch_CompanyName();
		String AMID = PreferencesPage.Fetch_AlternateMachineID();

		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PreferencesPage.ClickBackButn();

		PreferencesPage = MainHubPage.ClickAdminTile_Prefpage();
		Thread.sleep(1000);
		String CN1 = PreferencesPage.Fetch_CompanyName();
		String AMID1 = PreferencesPage.Fetch_AlternateMachineID();
		sa.assertEquals(CN, CN1, "Fail: New Company name has not saved properly");
		sa.assertEquals(AMID, AMID1, "Fail: New Alternate MachineID name has not saved properly");

		sa.assertAll();

	}

//PREF014-Verify if able to discard the changes usind CANCEL button and previous saved changes are retained

	@Test(groups = {
			"Regression" }, description = "PREF014-Verify if able to discard the changes usind CANCEL button and previous saved changes are retained")
	public void PREF013() throws Exception {
		extentTest = extent.startTest(
				"PREF014-Verify if able to discard the changes usind CANCEL button and previous saved changes are retained");
		SoftAssert sa = new SoftAssert();
		PreferencesPage.enter_AlternateMachineID("ABCD");

		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		PreferencesPage.enter_AlternateMachineID("A46547D");
		PreferencesPage.click_CancelBtn();
		String AMID = PreferencesPage.Fetch_AlternateMachineID();
		sa.assertEquals(AMID, "ABCD", "Fail: Application is not able to retained the entered value");
		sa.assertAll();

	}

	// PREF013-Verify if the saved changes are reflected at Audit trail
	// PREF015-Verify the Audit entry for modifications made for company name field
	@Test(groups = {
			"Regression" }, description = "PREF013,PREF015-Verify the Audit entry for modifications made for company name field")
	public void PREF013_PREF015() throws Exception {
		extentTest = extent
				.startTest("PREF013,PREF015-Verify the Audit entry for modifications made for company name field");
		SoftAssert sa = new SoftAssert();

		PreferencesPage.enterCompanyName("Test1");
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		PreferencesPage.enterCompanyName("Test2");
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = PreferencesPage.ClickBackButn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(3000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "Preferences : \"Company Name\" field modified from \"Test1 to Test2\" by User ID : \"1\", User Name : \"User1\"";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for company name modification ");
		sa.assertAll();
	}

	// PREF016-Verify the default values for Temperature field

	@Test(groups = { "Regression" }, description = "PREF016-Verify the default values  for Temperature field")
	public void PREF016() throws Exception {
		extentTest = extent.startTest("PREF016-Verify the default values  for Temperature field");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(PreferencesPage.Fetch_Defaultval_TemparatureUnits(), "Celsius",
				"FAIL: Temperature unit is not displaying as  Celsius ");
		sa.assertAll();
	}

	// PREF017- This Test Case will test Manually as its contains Report
	// verification

	// PREF018-Verify the Audit trail for the modifications made for _Max Groups_
	// field

	@Test(groups = {
			"Regression" }, description = "PREF018-Verify the Audit trail for the modifications made for _Max Groups_ field")
	public void PREF018() throws Exception {
		extentTest = extent
				.startTest("PREF018-Verify the Audit trail for the modifications made for _Max Groups_ field");
		SoftAssert sa = new SoftAssert();

		PreferencesPage.clickOn_MaxGroupComboBox();
		PreferencesPage.Select_maxgroup_Limit(0);
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		PreferencesPage.clickOn_MaxGroupComboBox();
		PreferencesPage.Select_maxgroup_Limit(1);
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = PreferencesPage.ClickBackButn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "Preferences : Max Groups modified from \"10 to 20 \" by User ID : \"1\" , User Name : \"User1\"";
		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for maxgroup modification ");
		sa.assertAll();
	}

	// PREF019-Verify the functionality when 10 is selected for _Max groups_ field

	@Test(groups = {
			"Regression" }, description = "PREF019-Verify the functionality when 10 is selected for _Max groups_ field")
	public void PREF019() throws Exception {
		extentTest = extent.startTest("PREF019-Verify the functionality when 10 is selected for _Max groups_ field");
		SoftAssert sa = new SoftAssert();

		PreferencesPage.clickOn_MaxGroupComboBox();
		PreferencesPage.Select_maxgroup_Limit(0);
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = PreferencesPage.ClickBackButn();
		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName("test");
		defineSetupPage.enter_defineSetupPage_SensorCount("300");
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.create_MaxGroup(11);
		String Actmsg = Setup_GroupSensorsPage.AlertMsg();
		String Expmsg = "Cannot create new group. Maximum limit of 10 group's already reached.";
		sa.assertEquals(Actmsg, Expmsg, "FAIL: Wrong alert message appeared");

		sa.assertEquals(Setup_GroupSensorsPage.Countof_group(), 10, "FAIL: group count is more than 10");

		sa.assertAll();

	}

//PREF020-Verify the functionality when 20 is selected for _Max groups_ field

	@Test(groups = {
			"Regression" }, description = "PREF020-Verify the functionality when 20 is selected for _Max groups_ field")
	public void PREF020() throws Exception {
		extentTest = extent.startTest("PREF020-Verify the functionality when 20 is selected for _Max groups_ field");
		SoftAssert sa = new SoftAssert();

		PreferencesPage.clickOn_MaxGroupComboBox();
		PreferencesPage.Select_maxgroup_Limit(1);
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = PreferencesPage.ClickBackButn();
		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName("test");
		defineSetupPage.enter_defineSetupPage_SensorCount("300");
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.create_MaxGroup(21);
		String Actmsg = Setup_GroupSensorsPage.AlertMsg();
		String Expmsg = "Cannot create new group. Maximum limit of 20 group's already reached.";
		sa.assertEquals(Actmsg, Expmsg, "FAIL:More than 20 groups got allowed");

		sa.assertEquals(Setup_GroupSensorsPage.Countof_group(), 20, "FAIL: group count is more than 10");
		sa.assertAll();

	}

	// PREF021-Verify the functionality when 25 is selected for _Max groups_ field

	@Test(groups = {
			"Regression" }, description = "PREF021-Verify the functionality when 25 is selected for _Max groups_ field")
	public void PREF021() throws Exception {
		extentTest = extent.startTest("PREF021-Verify the functionality when 25 is selected for _Max groups_ field");
		SoftAssert sa = new SoftAssert();

		PreferencesPage.clickOn_MaxGroupComboBox();
		PreferencesPage.Select_maxgroup_Limit(2);
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = PreferencesPage.ClickBackButn();
		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName("test");
		defineSetupPage.enter_defineSetupPage_SensorCount("300");
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.create_MaxGroup(26);
		String Actmsg = Setup_GroupSensorsPage.AlertMsg();
		String Expmsg = "Cannot create new group. Maximum limit of 25 group's already reached.";
		sa.assertEquals(Actmsg, Expmsg, "FAIL:More than 25 groups got allowed");

		sa.assertEquals(Setup_GroupSensorsPage.Countof_group(), 25, "FAIL: group count is more than 10");
		sa.assertAll();

	}

	// PREF022-Verify the drop down values for _Pressure_ field

	@Test(groups = { "Regression" }, description = "PREF022-Verify  the drop down values for _Pressure_ field")
	public void PREF22() throws Exception {
		extentTest = extent.startTest("PREF022-Verify  the drop down values for _Pressure_ field");
		SoftAssert sa = new SoftAssert();
		PreferencesPage.click_PressureComboBox();
		Thread.sleep(1000);
		sa.assertEquals(PreferencesPage.gettxt_from_pressurecombobox(1), "Pascal",
				" Fail : Pascal is not available in Pressure combobox");
		sa.assertEquals(PreferencesPage.gettxt_from_pressurecombobox(2), "Kilopascal",
				" Fail : Kilopascal is not available in Pressure combobox");
		sa.assertEquals(PreferencesPage.gettxt_from_pressurecombobox(3), "Millibar",
				" Fail : Millibar is not available in Pressure combobox");
		sa.assertEquals(PreferencesPage.gettxt_from_pressurecombobox(4), "Bar",
				" Fail : Bar is not available in Pressure combobox");
		sa.assertEquals(PreferencesPage.gettxt_from_pressurecombobox(5), "PSIA",
				" Fail : PSIA is not available in Pressure combobox");
		sa.assertEquals(PreferencesPage.gettxt_from_pressurecombobox(6), "Atmosphere",
				" Fail : Atmosphere is not available in Pressure combobox");

		sa.assertAll();
	}

	// PREF022 to PREF029 will test manually , as they are related to reports
	// functionality

	// PREF030-Verify the Audit trail when yes is selected for _Allow Users to
	// change Lethality_ field

	@Test(groups = {
			"Regression" }, description = "PREF030-Verify  the Audit trail when yes is selected for _Allow Users to change Lethality_ field")
	public void PREF030() throws Exception {
		extentTest = extent.startTest(
				"PREF030-Verify  the Audit trail when yes is selected for _Allow Users to change Lethality_ field");
		SoftAssert sa = new SoftAssert();
		PreferencesPage.click_AllowLethalityComboBox();
		PreferencesPage.selectValue_From_AllowLethalityComboBox(2);
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		PreferencesPage.click_AllowLethalityComboBox();
		PreferencesPage.selectValue_From_AllowLethalityComboBox(1);
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PreferencesPage.ClickBackButn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "Preferences : \"Allow Users to change Lethality\" field modified from \"No to Yes \"  by User ID : \"1\" , User Name : \"User1\".";
		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for Pressure modification ");
		sa.assertAll();
	}

//PREF031-Verify  the Reports when  yes is selected for _Allow Users to change Lethality_ field

	@Test(groups = {
			"Regression" }, description = "PREF031-Verify  the Reports when  yes is selected for _Allow Users to change Lethality_ field")
	public void PREF031() throws Exception {
		extentTest = extent.startTest(
				"PREF031-Verify  the Reports when  yes is selected for _Allow Users to change Lethality_ field");
		SoftAssert sa = new SoftAssert();
		PreferencesPage.click_AllowLethalityComboBox();
		PreferencesPage.selectValue_From_AllowLethalityComboBox(1);
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PreferencesPage.ClickBackButn();
		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		RW_FileSelctionPage = assetDetailsPage.Click_GenerateReportsBtn_RWpage();
		RW_FileSelctionPage.Click_ReportButton();
		CycleSelectionPage = RW_FileSelctionPage.Select_Temperature_ClickOK();
		DefineQual_ExposurePage = CycleSelectionPage.click_DefineAddlCycles_Btn();
		Thread.sleep(1000);
		EditParametersPage = DefineQual_ExposurePage.click_ReportParameters_Btn();
		CustomizedCalculationPage = EditParametersPage.click_EditCalculations_Tab();

		sa.assertEquals(CustomizedCalculationPage.is_Customize_Visible(), true,
				" Fail : Customize option is  available in Customized Calculation Page");
		sa.assertAll();
	}

//PREF032-Verify  the Audit trail when No is selected for _Allow Users to change Lethality_ field
	@Test(groups = {
			"Regression" }, description = "PREF032-Verify  the Audit trail when No is selected for _Allow Users to change Lethality_ field")
	public void PREF032() throws Exception {
		extentTest = extent.startTest(
				"PREF032-Verify  the Audit trail when No is selected for _Allow Users to change Lethality_ field");
		SoftAssert sa = new SoftAssert();
		PreferencesPage.click_AllowLethalityComboBox();
		PreferencesPage.selectValue_From_AllowLethalityComboBox(1);
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		PreferencesPage.click_AllowLethalityComboBox();
		PreferencesPage.selectValue_From_AllowLethalityComboBox(2);
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PreferencesPage.ClickBackButn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "Preferences : \"Allow Users to change Lethality\" field modified from \"Yes to No \"  by User ID : \"1\" , User Name : \"User1\".";
		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for Pressure modification ");
		sa.assertAll();
	}

	//PREF033-Verify  the Reports when  No is selected for _Allow Users to change Lethality_ field
	@Test(groups = {
			"Regression" }, description = "PREF033-Verify  the Reports when  No is selected "
					+ "for _Allow Users to change Lethality_ field")
	public void PREF033() throws Exception {
		extentTest = extent.startTest(
				"PREF033-Verify  the Reports when  No is selected for _Allow Users to change Lethality_ field");
		SoftAssert sa = new SoftAssert();
		PreferencesPage.click_AllowLethalityComboBox();
		PreferencesPage.selectValue_From_AllowLethalityComboBox(2);			
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PreferencesPage.ClickBackButn();
		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		RW_FileSelctionPage = assetDetailsPage.Click_GenerateReportsBtn_RWpage();
		RW_FileSelctionPage.Click_ReportButton();
		CycleSelectionPage = RW_FileSelctionPage.Select_Temperature_ClickOK();
		DefineQual_ExposurePage = CycleSelectionPage.click_DefineAddlCycles_Btn();
		Thread.sleep(1000);
		EditParametersPage = DefineQual_ExposurePage.click_ReportParameters_Btn();
		CustomizedCalculationPage = EditParametersPage.click_EditCalculations_Tab();
		Thread.sleep(1000);
		sa.assertEquals(CustomizedCalculationPage.is_Customize_Visible(), false,
				" Fail : Customize option is  available in Customized Calculation Page");

		sa.assertAll();
		 
		 
	}

//PREF034-Verify  the celcius values allowed for _IRTD Stability Threshold Limit_ field

	@Test(groups = {
			"Regression" }, dataProvider = "PREF034", dataProviderClass = PreferencesUtility.class, description = "PREF034-Verify  the celcius values allowed for _IRTD Stability Threshold Limit_ field")

	public void PREF034(String IRTD) throws Exception {
		extentTest = extent
				.startTest("PREF034-Verify  the celcius values allowed for _IRTD Stability Threshold Limit_ field");
		SoftAssert sa = new SoftAssert();

		PreferencesPage.enter_IRTDStudyText(IRTD);
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PreferencesPage.ClickBackButn();
		PreferencesPage = MainHubPage.ClickAdminTile_Prefpage();

		sa.assertEquals(PreferencesPage.Fetch_IRTDStudyTextBoxText(), IRTD, "Fail : value not displaying as 300");
		sa.assertAll();

	}

//PREF035-Verify  the Audit trail for _IRTD Stability Threshold Limit_ field

	@Test(groups = {
			"Regression" }, description = "PREF035-Verify  the Audit trail for _IRTD Stability Threshold Limit_ field")
	public void PREF035() throws Exception {
		extentTest = extent.startTest("PREF035-Verify  the Audit trail for _IRTD Stability Threshold Limit_ field");
		SoftAssert sa = new SoftAssert();

		PreferencesPage.enter_IRTDStudyText("251");
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		PreferencesPage.enter_IRTDStudyText("260");
		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = PreferencesPage.ClickBackButn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "Preferences : \"IRTD Stability Threshold Lower Limit\" field modified from \"251 to 260 \"  by User ID : \"1\", User Name : \"User1\".";

		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for IRTD Stability Threshold Limit_ field modification ");
		sa.assertAll();
	}

//PREF036-Verify  the functionality when values are entered out of range for _IRTD Stability Threshold Limit_ field

	@Test(groups = {
			"Regression" }, description = "PREF036-Verify  the functionality when values are entered out of range for _IRTD Stability Threshold Limit_ field")

	public void PREF036() throws Exception {
		extentTest = extent.startTest(
				"PREF036-Verify  the functionality when values are entered out of range for _IRTD Stability Threshold Limit_ field");
		SoftAssert sa = new SoftAssert();

		// Enter less than 250 i.e 249

		PreferencesPage.enter_IRTDStudyText("249");
		PreferencesPage.clickOn_CompanyName();
		sa.assertEquals(PreferencesPage.Fetch_IRTDStudyTextBoxText(), "250", " Fail : value not returning to 250");

//Enter More than 400 i.e 401

		PreferencesPage.enter_IRTDStudyText("401");
		PreferencesPage.clickOn_CompanyName();
		sa.assertEquals(PreferencesPage.Fetch_IRTDStudyTextBoxText(), "400", " Fail : value not returning to 400");

		sa.assertAll();
	}

//PREF038-Verify if the user is able to change logo in the Preferences section of Admin module.

//PREF039-Verify if audit entry is displayed for logo change by user in the prferences section of Admin module
//PREF038 and PREF039 both are handeled in one script below 

	@Test(groups = {
			"Regression" }, description = "PREF038_39-Verify if the user is able to change logo in the Preferences section of Admin module,PREF039-Verify if audit entry is displayed for logo change by user in the prferences section of Admin module")
	public void PREF038_39() throws Exception {
		extentTest = extent.startTest(
				"PREF038_39-Verify if the user is able to change logo in the Preferences section of Admin module,PREF039-Verify if audit entry is displayed for logo change by user in the prferences section of Admin module");
		SoftAssert sa = new SoftAssert();
		PreferencesPage.clickOn_ChangeImage();
		tu.uploadDoc("VRT_Pro.JPG");

		PreferencesPage.click_SaveBtn();
		PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		MainHubPage = PreferencesPage.ClickBackButn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "Preferences : \"Logo\" field modified  by  User ID : \"1\" , User Name : \"User1\"";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for change logo in the Preferences section of Admin module");
		sa.assertAll();
	}

}
