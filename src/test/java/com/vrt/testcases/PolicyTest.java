package com.vrt.testcases;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

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
import com.vrt.pages.UserManagementPage;
import com.vrt.utility.PoliciesUtility;
import com.vrt.utility.TestUtilities;
import com.vrt.utility.setupCreationUtility;
import com.vrt.utility.userManagementUtility;
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

import java.lang.Object;

public class PolicyTest extends BaseClass {

	public PolicyTest() throws IOException {
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
	RW_FileSelctionPage RWFileSelctionPage;
	RW_CycleSelectionPage CycleSelectionPage;
	RW_DefineQualExposurePage DefineQual_ExposurePage;
	RW_EditParametersPage EditParametersPage;
	RW_CustomizedCalculationPage CustomizedCalculationPage;

	static String un = "User1";

	// Before Class/Test
	// @BeforeTest
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_" + "PolicyTest" + ".html", true);
		extent.addSystemInfo("TestSuiteName", "PolicyTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name2"));
		System.out.println("PolicyTest in Progress..");

	/*	// Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
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
*/
	}

	// After Class/Test
	// @AfterTest
	@AfterClass
	public void endReport() throws InterruptedException {
		extent.flush();
		extent.close();
		System.out.println("Policy Test Completed.");
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
		PoliciesPage = UserManagementPage.Click_Policy();
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
		PoliciesPage.resetWebElements();
		driver.quit();
	}

	// Policies002
	@Test(groups = {
			"Regression" }, description = "Policies002-Verify  the fields to be displayed with default values in Policies screen")
	public void Policies002() throws Exception {
		extentTest = extent
				.startTest("Policies002-Verify  the fields to be displayed with default values in Policies screen");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(PoliciesPage.IsPolicies_screenDisplayed(), true, "FAIL:Landed to wrong page");
		sa.assertEquals(PoliciesPage.IspwdcomboboxVisible(), true,
				"Fail: Require Min Length Password ComboBox is not visibe");
		sa.assertEquals(PoliciesPage.IsLoginFailuresComboBox_Enable(), true,
				"Fail:Disable user account for consecutive login failures is not visibe");
		sa.assertEquals(PoliciesPage.IsUserIdEntryCheckBoxVisible(), true,
				"Fail: User Id Entery check box is not visibe");
		sa.assertEquals(PoliciesPage.IsPwdsystemcheckBoxVisible(), true, "Fail: password system is not visibe");
		sa.assertEquals(PoliciesPage.IsSpecialCharCheckBoxVisible(), true, "Fail: Special charecters are not visibe");
		sa.assertEquals(PoliciesPage.IsAllowDValueEditingCheckBoxVisible(), true,
				"Fail: Allow editing of DValue in lethality equation CheckBoxVisible is not visibe");
		sa.assertEquals(PoliciesPage.IsDValueCheckBox_selected(), false,
				"Fail: Allow editing of DValue CheckBox is  selected");
		sa.assertEquals(PoliciesPage.IsInstrumentCalibWarningCheckBoxVisible(), true,
				"Fail:Instrument Calibration Warning CheckBox is not Visible");

		sa.assertEquals(PoliciesPage.IsAutoSyncOutCheckBoxVisible(), true,
				"Fail:SyncIn Users Before Auto SyncOut CheckBox is not Visible");

		sa.assertAll();

	}

	// Policies003 Verify that when _Require min len pwd_ field is selected and
	// given as 6 char, the password will accept a value with minimum 6 char
	@Test(dataProvider = "Policies003", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies003 Verify that when _Require min len "
					+ "pwd_ field is selected and given as 6 char, the password will accept a value with minimum 6 char")
	public void Policies003(String Name, String UserID, String Password, String Title,
			String UserType, String NewPassword) throws Exception {
		extentTest = extent.startTest(
				"Policies003 Verify that when _Require min len pwd_ field is selected and given as 6 char, "
				+ "the password will accept a value with minimum 6 char");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(0);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		sa.assertEquals(UserManagementPage.UserLoginPopupVisible(), true,
				"Fail :  Password Field is not accepting a value with minimum 6 chars");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		sa.assertEquals(MainHubPage.mainPageTitle(), true, "Fail: MainHubPage is not available");
		sa.assertAll();
	}

	// Policies003.01-Verify that when _Require min len pwd_ field is selected and
	// given as 7 char, the password will accept a value with minimum 7 char
	@Test(dataProvider = "Policies003", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies003.01-Verify that when _Require min len "
					+ "pwd_ field is selected and given as 7 char, the password will accept a value with minimum 7 char")
	public void Policies003_01(String Name, String UserID, String Password, String Title,
			String UserType, String NewPassword) throws Exception {
		extentTest = extent.startTest(
				"Policies003_01-Verify that when _Require min len pwd_ field is selected and given as 7 char, "
				+ "the password will accept a value with minimum 7 char");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(1);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		sa.assertEquals(UserManagementPage.UserLoginPopupVisible(), true,
				"Fail :  Password Field is not accepting a value with minimum 6 chars");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		sa.assertEquals(MainHubPage.mainPageTitle(), true, "Fail: MainHubPage is not available");
		sa.assertAll();
	}

	// Policies003.02-Verify that when _Require min len pwd_ field is selected and
	// given as 8 char, the password will accept a value with minimum 8 char

	@Test(dataProvider = "Policies003_02", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies003.01-Verify that when _Require min len pwd_ field is selected and given as 8 char, the password will accept a value with minimum 8 char")
	public void Policies003_02(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String NewPassword) throws Exception {
		extentTest = extent.startTest(
				"Policies003_02-Verify that when _Require min len pwd_ field is selected and given as 8 char, the password will accept a value with minimum 8char");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(2);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		sa.assertEquals(MainHubPage.mainPageTitle(), true,
				"Fail: Password Field is not accepting a value with minimum 8 charss");
		sa.assertAll();
	}

	// Policies003.03-Verify that when _Require min len pwd_ field is selected and
	// given as 9 char, the password will accept a value with minimum 9 char

	@Test(dataProvider = "Policies003_03", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies003.02-Verify that when _Require min len pwd_ field is selected and given as 9 char, the password will accept a value with minimum 9 char")
	public void Policies003_03(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String NewPassword) throws Exception {
		extentTest = extent.startTest(
				"Policies003_02-Verify that when _Require min len pwd_ field is selected and given as 9 char, the password will accept a value with minimum 9char");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(3);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		sa.assertEquals(MainHubPage.mainPageTitle(), true,
				"Fail: Password Field is not accepting a value with minimum 9 charss");
		sa.assertAll();
	}

	// Policies003.04-Verify that when _Require min len pwd_ field is selected and
	// given as 10 char, the password will accept a value with minimum 8 char

	@Test(dataProvider = "Policies003_04", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies003.02-Verify that when _Require min len pwd_ field is selected and given as 10 char, the password will accept a value with minimum 10 char")
	public void Policies003_04(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String NewPassword) throws Exception {
		extentTest = extent.startTest(
				"Policies003_04-Verify that when _Require min len pwd_ field is selected and given as 10 char, the password will accept a value with minimum 10 char");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(4);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		sa.assertEquals(MainHubPage.mainPageTitle(), true,
				"Fail: Password Field is not accepting a value with minimum 10 charss");
		sa.assertAll();
	}

//Policies003.05-Verify that when _Require min len pwd_ field is selected and given as 11 char, the password will accept a value with minimum 11 char

	@Test(dataProvider = "Policies003_05", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies003.02-Verify that when _Require min len pwd_ field is selected and given as 11 char, the password will accept a value with minimum 11 char")
	public void Policies003_05(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String NewPassword) throws Exception {
		extentTest = extent.startTest(
				"Policies003_05-Verify that when _Require min len pwd_ field is selected and given as 11 char, the password will accept a value with minimum 11 char");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(5);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		sa.assertEquals(MainHubPage.mainPageTitle(), true,
				"Fail: Password Field is not accepting a value with minimum 11 charss");
		sa.assertAll();
	}

//Policies003.06-Verify that when _Require min len pwd_ field is selected and given as 12 char, the password will accept a value with minimum 12 char

	@Test(dataProvider = "Policies003_06", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies003.06-Verify that when _Require min len pwd_ field is selected and given as 12 char, the password will accept a value with minimum 12 char")
	public void Policies003_06(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String NewPassword) throws Exception {
		extentTest = extent.startTest(
				"Policies003_06-Verify that when _Require min len pwd_ field is selected and given as 12 char, the password will accept a value with minimum 12 char");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(6);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		sa.assertEquals(MainHubPage.mainPageTitle(), true,
				"Fail: Password Field is not accepting a value with minimum 12 charss");
		sa.assertAll();
	}
//Policies003.07-Verify that when _Require min len pwd_ field is selected and given as 13 char, the password will accept a value with minimum 11 char

	@Test(dataProvider = "Policies003_07", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies003.02-Verify that when _Require min len pwd_ field is selected and given as 13 char, the password will accept a value with minimum 13 char")
	public void Policies003_07(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String NewPassword) throws Exception {
		extentTest = extent.startTest(
				"Policies003_07-Verify that when _Require min len pwd_ field is selected and given as 13 char, the password will accept a value with minimum 13 char");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(6);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		sa.assertEquals(MainHubPage.mainPageTitle(), true,
				"Fail: Password Field is not accepting a value with minimum 13 charss");
		sa.assertAll();
	}

//Policies003.08-Verify that when _Require min len pwd_ field is selected and given as 14 char, the password will accept a value with minimum 12 char

	@Test(dataProvider = "Policies003_08", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies003.02-Verify that when _Require min len pwd_ field is selected and given as 14 char, the password will accept a value with minimum 14 char")
	public void Policies003_08(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String NewPassword) throws Exception {
		extentTest = extent.startTest(
				"Policies003_05-Verify that when _Require min len pwd_ field is selected and given as 14 char, the password will accept a value with minimum 14 char");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(7);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		sa.assertEquals(MainHubPage.mainPageTitle(), true,
				"Fail: Password Field is not accepting a value with minimum 14 charss");
		sa.assertAll();
	}
//Policies003.09-Verify that when _Require min len pwd_ field is selected and given as 15 char, the password will accept a value with minimum 15 char

	@Test(dataProvider = "Policies003_09", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies003.02-Verify that when _Require min len pwd_ field is selected and given as 15 char, the password will accept a value with minimum 15 char")
	public void Policies003_09(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String NewPassword) throws Exception {
		extentTest = extent.startTest(
				"Policies003_05-Verify that when _Require min len pwd_ field is selected and given as 15 char, the password will accept a value with minimum 15 char");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(8);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		sa.assertEquals(MainHubPage.mainPageTitle(), true,
				"Fail: Password Field is not accepting a value with minimum 15 charss");
		sa.assertAll();
	}

//Policies003.10-Verify that when _Require min len pwd_ field is selected and given as 16 char, the password will accept a value with minimum 16 char

	@Test(dataProvider = "Policies003_10", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies003.06-Verify that when _Require min len pwd_ field is selected and given as 16 char, the password will accept a value with minimum 16 char")
	public void Policies003_10(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String NewPassword) throws Exception {
		extentTest = extent.startTest(
				"Policies003_06-Verify that when _Require min len pwd_ field is selected and given as 16 char, the password will accept a value with minimum 16 char");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.PWDLengthBox_Click();

		PoliciesPage.SelectAny_Option_FromPWDLengthBox(9);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		sa.assertEquals(MainHubPage.mainPageTitle(), true,
				"Fail: Password Field is not accepting a value with minimum 16 charss");
		sa.assertAll();
	}

	// Policies004-Verify the Audit Trail for the modified value of _Require min len
	// pwd_ field

	@Test(groups = {
			"Regression" }, description = "Policies004-Verify  the Audit Trail for the modified value of _Require min len pwd_ field")
	public void Policies004() throws Exception {
		extentTest = extent
				.startTest("Policies004-Verify  the Audit Trail for the modified value of _Require min len pwd_ field");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(1);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);

		PoliciesPage.SelectAny_Option_FromPWDLengthBox(0);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = PoliciesPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "Policies :  \"Require Minimum Length Password\"  field updated from \"7 to 6\" by User ID: \"1\", User Name: \"User1\".";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for the modified value of _Require min len pwd_ field           ");
		sa.assertAll();
	}

//Policies005-Verify the functionality when _Require min len pwd_ field is not selected

	@Test(dataProvider = "Policies005", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies005-Verify the functionality when _Require min len pwd_ field is not selected")
	public void Policies005K(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String NewPassword) throws Exception {
		extentTest = extent
				.startTest("Policies005-Verify the functionality when _Require min len pwd_ field is not selected");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.clickOn_PWDcheckbox();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		sa.assertEquals(MainHubPage.mainPageTitle(), true, "Fail: Require min len pwd_ field is  selected");
		sa.assertAll();

	}
	// Policies005_1-Verify if Unchecking _Required minimun lengh password_ check
	// box will also uncheck _Require Special Charaters_ check box and disabled it.

	@Test(groups = {
			"Regression" }, description = "Policies005_1-Verify if Unchecking _Required minimun lengh password_ check box will also uncheck _Require Special Charaters_ check box and disabled it")
	public void Policies005_1() throws Exception {
		extentTest = extent.startTest(
				"Policies005_1-Verify if Unchecking _Required minimun lengh password_ check box will also uncheck _Require Special Charaters_ check box and disabled it");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(PoliciesPage.IsSpecialCharCheckBoxVisible(), false,
				"Fail : Required Special Charecters check box is  enabled");
		sa.assertAll();
	}

//Policies005_2-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 6
	@Test(dataProvider = "Policies005_2", dataProviderClass = PoliciesUtility.class, groups = {"Regression" }, 
			description = "Policies005_2-Verify the functionality when incorrect pwd len is given for a "
					+ "user when _Require min len pwd_ is selected as 6")
	public void Policies005_2(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String InvalidPassword, String Expmsg) throws Exception {
		extentTest = extent.startTest(
				"Policies005_2-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 6");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.Enable_Editing_PWDcheckbox();

		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(0);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, InvalidPassword, Title, UserType);
		String msg = tu.get_popup_text();
		sa.assertEquals(msg, Expmsg, "Fail : Password Field is  accepting a value with minimum 6 char in UM page");
		UserManagementPage.click_okBtn();
		Thread.sleep(1000);
		UserManagementPage.ClickCancelBtn();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.ChangeNewPW_getalertmsg(UserID, Password, InvalidPassword);
		Thread.sleep(1000);
		String msg1 = tu.get_AlertMsg_text();
		sa.assertEquals(msg1, Expmsg, "Fail : Password Field is  accepting a value with minimum 6 char in Login page");

		sa.assertAll();

	}

//Policies005_3-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 7

	@Test(dataProvider = "Policies005_3", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies005_3-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 7")
	public void Policies005_3(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String InvalidPassword, String Expmsg) throws Exception {
		extentTest = extent.startTest(
				"Policies005_3-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 7");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.Enable_Editing_PWDcheckbox();

		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(1);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, InvalidPassword, Title, UserType);
		String msg = tu.get_popup_text();
		sa.assertEquals(msg, Expmsg, "Fail : Password Field is  accepting a value with minimum 7 char in UM page");
		UserManagementPage.click_okBtn();
		Thread.sleep(1000);
		UserManagementPage.ClickCancelBtn();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.ChangeNewPW_getalertmsg(UserID, Password, InvalidPassword);
		Thread.sleep(1000);
		String msg1 = tu.get_AlertMsg_text();
		sa.assertEquals(msg1, Expmsg, "Fail : Password Field is  accepting a value with minimum 7 char in Login page");

		sa.assertAll();

	}

//Policies005_4-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 8
	@Test(dataProvider = "Policies005_4", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies005_4-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 8")
	public void Policies005_4(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String InvalidPassword, String Expmsg) throws Exception {
		extentTest = extent.startTest(
				"Policies005_4-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 8");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.Enable_Editing_PWDcheckbox();

		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(2);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, InvalidPassword, Title, UserType);
		String msg = tu.get_popup_text();
		sa.assertEquals(msg, Expmsg, "Fail : Password Field is  accepting a value with minimum 8 char in UM page");
		UserManagementPage.click_okBtn();
		Thread.sleep(1000);
		UserManagementPage.ClickCancelBtn();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.ChangeNewPW_getalertmsg(UserID, Password, InvalidPassword);
		Thread.sleep(1000);
		String msg1 = tu.get_AlertMsg_text();
		sa.assertEquals(msg1, Expmsg, "Fail : Password Field is  accepting a value with minimum 8 char in Login page");

		sa.assertAll();
	}

//Policies005_5-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 9

	@Test(dataProvider = "Policies005_5", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies005_5-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 9")
	public void Policies005_5(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String InvalidPassword, String Expmsg) throws Exception {
		extentTest = extent.startTest(
				"Policies005_5-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 9");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.Enable_Editing_PWDcheckbox();
		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(3);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, InvalidPassword, Title, UserType);
		String msg = tu.get_popup_text();
		sa.assertEquals(msg, Expmsg, "Fail : Password Field is  accepting a value with minimum 9 char in UM page");
		UserManagementPage.click_okBtn();
		Thread.sleep(1000);
		UserManagementPage.ClickCancelBtn();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.ChangeNewPW_getalertmsg(UserID, Password, InvalidPassword);
		Thread.sleep(1000);
		String msg1 = tu.get_AlertMsg_text();
		sa.assertEquals(msg1, Expmsg, "Fail : Password Field is  accepting a value with minimum 9 char in Login page");

		sa.assertAll();

	}
//Policies005_6-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 10

	@Test(dataProvider = "Policies005_6", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies005_6-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 10")
	public void Policies005_6(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String InvalidPassword, String Expmsg) throws Exception {
		extentTest = extent.startTest(
				"Policies005_6-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 10");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.Enable_Editing_PWDcheckbox();
		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(4);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, InvalidPassword, Title, UserType);
		String msg = tu.get_popup_text();
		sa.assertEquals(msg, Expmsg, "Fail : Password Field is  accepting a value with minimum 10 char in UM page");
		UserManagementPage.click_okBtn();
		Thread.sleep(1000);
		UserManagementPage.ClickCancelBtn();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.ChangeNewPW_getalertmsg(UserID, Password, InvalidPassword);
		Thread.sleep(1000);
		String msg1 = tu.get_AlertMsg_text();
		sa.assertEquals(msg1, Expmsg, "Fail : Password Field is  accepting a value with minimum 10 char in Login page");

		sa.assertAll();
	}

//Policies005_7-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 11

	@Test(dataProvider = "Policies005_7", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies005_7-Verify the functionality when incorrect pwd len is given for a user "
					+ "when _Require min len pwd_ is selected as 11")
	public void Policies005_7(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String InvalidPassword, String Expmsg) throws Exception {
		extentTest = extent
				.startTest("Policies005_7-Verify the functionality when incorrect pwd len is given for a user"
						+ " when _Require min len pwd_ is selected as 11");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.Enable_Editing_PWDcheckbox();
		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(5);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, InvalidPassword, Title, UserType);
		String msg = tu.get_popup_text();
		sa.assertEquals(msg, Expmsg, "Fail : Password Field is  accepting a value with minimum 11 char in UM page");
		UserManagementPage.click_okBtn();
		Thread.sleep(1000);
		UserManagementPage.ClickCancelBtn();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.ChangeNewPW_getalertmsg(UserID, Password, InvalidPassword);
		Thread.sleep(1000);
		String msg1 = tu.get_AlertMsg_text();
		sa.assertEquals(msg1, Expmsg, "Fail : Password Field is  accepting a value with minimum 11 char in Login page");

		sa.assertAll();

	}
//Policies005_8-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 12

	@Test(dataProvider = "Policies005_8", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies005_8-Verify the functionality when incorrect pwd len is given"
					+ " for a user when _Require min len pwd_ is selected as 12")
	public void Policies005_8(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String InvalidPassword, String Expmsg) throws Exception {
		extentTest = extent
				.startTest("Policies005_8-Verify the functionality when incorrect pwd len is given for a user "
						+ "when _Require min len pwd_ is selected as 12");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.Enable_Editing_PWDcheckbox();
		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(6);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, InvalidPassword, Title, UserType);
		String msg = tu.get_popup_text();
		sa.assertEquals(msg, Expmsg, "Fail : Password Field is  accepting a value with minimum 12 char in UM page");
		UserManagementPage.click_okBtn();
		Thread.sleep(1000);
		UserManagementPage.ClickCancelBtn();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.ChangeNewPW_getalertmsg(UserID, Password, InvalidPassword);
		Thread.sleep(1000);
		String msg1 = tu.get_AlertMsg_text();
		sa.assertEquals(msg1, Expmsg, "Fail : Password Field is  accepting a value with minimum 12 char in Login page");

		sa.assertAll();

	}
//Policies005_9-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is selected as 13

	@Test(dataProvider = "Policies005_9", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies005_9-Verify the functionality when incorrect pwd len is given"
					+ " for a user when _Require min len pwd_ is selected as 13")
	public void Policies005_9(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String InvalidPassword, String Expmsg) throws Exception {
		extentTest = extent.startTest(
				"Policies005_9-Verify the functionality when incorrect pwd len is given for a user when _Require min len pwd_ is "
						+ "selected as 13");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.Enable_Editing_PWDcheckbox();
		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(7);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, InvalidPassword, Title, UserType);
		String msg = tu.get_popup_text();
		sa.assertEquals(msg, Expmsg, "Fail : Password Field is  accepting a value with minimum 13 char in UM page");
		UserManagementPage.click_okBtn();
		Thread.sleep(1000);
		UserManagementPage.ClickCancelBtn();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.ChangeNewPW_getalertmsg(UserID, Password, InvalidPassword);
		Thread.sleep(1000);
		String msg1 = tu.get_AlertMsg_text();
		sa.assertEquals(msg1, Expmsg, "Fail : Password Field is  accepting a value with minimum 13 char in Login page");

		sa.assertAll();

	}

//Policies005_10-Verify the functionality when incorrect pwd len is given for a user when 
//_Require min len pwd_ is selected as 14

	@Test(dataProvider = "Policies005_10", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies005_10-Verify the functionality when incorrect pwd len is given for a user "
					+ "when _Require min len pwd_ is selected as 14")
	public void Policies005_10(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String InvalidPassword, String Expmsg) throws Exception {
		extentTest = extent
				.startTest("Policies005_10-Verify the functionality when incorrect pwd len is given for a user"
						+ " when _Require min len pwd_ is selected as 14");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.Enable_Editing_PWDcheckbox();
		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(8);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, InvalidPassword, Title, UserType);
		String msg = tu.get_popup_text();
		sa.assertEquals(msg, Expmsg, "Fail : Password Field is  accepting a value with minimum 14 char in UM page");
		UserManagementPage.click_okBtn();
		Thread.sleep(1000);
		UserManagementPage.ClickCancelBtn();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.ChangeNewPW_getalertmsg(UserID, Password, InvalidPassword);
		Thread.sleep(1000);
		String msg1 = tu.get_AlertMsg_text();
		sa.assertEquals(msg1, Expmsg, "Fail : Password Field is  accepting a value with minimum 14 char in Login page");

		sa.assertAll();
	}

//Policies005_11-Verify the functionality when incorrect pwd 
//len is given for a user when _Require min len pwd_ is selected as 15

	@Test(dataProvider = "Policies005_11", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies005_11-Verify the functionality when incorrect pwd len is"
					+ " given for a user when _Require min len pwd_ is selected as 15")
	public void Policies005_11(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String InvalidPassword, String Expmsg) throws Exception {
		extentTest = extent.startTest("Policies005_11-Verify the functionality when incorrect pwd len is given"
				+ " for a user when _Require min len pwd_ is selected as 15");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.Enable_Editing_PWDcheckbox();
		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(9);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, InvalidPassword, Title, UserType);
		String msg = tu.get_popup_text();
		sa.assertEquals(msg, Expmsg, "Fail : Password Field is  accepting a value with minimum 15 char in UM page");
		UserManagementPage.click_okBtn();
		Thread.sleep(1000);
		UserManagementPage.ClickCancelBtn();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.ChangeNewPW_getalertmsg(UserID, Password, InvalidPassword);
		Thread.sleep(1000);
		String msg1 = tu.get_AlertMsg_text();
		sa.assertEquals(msg1, Expmsg, "Fail : Password Field is  accepting a value with minimum 15 char in Login page");

		sa.assertAll();

	}
//Policies005_12-Verify the functionality when incorrect pwd len is given for a user 
//when _Require min len pwd_ is selected as 16

	@Test(dataProvider = "Policies005_12", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies005_12 Verify the functionality when"
					+ " incorrect pwd len is given for a user when _Require min len pwd_ is selected as 16")
	public void Policies005_12(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String InvalidPassword, String Expmsg) throws Exception {

		extentTest = extent
				.startTest("Policies005_12-Verify the functionality when incorrect pwd len is given for a user"
						+ " when _Require min len pwd_ is selected as 16");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.Enable_Editing_PWDcheckbox();
		PoliciesPage.PWDLengthBox_Click();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(10);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, InvalidPassword, Title, UserType);
		String msg = tu.get_popup_text();
		sa.assertEquals(msg, Expmsg, "Fail : Password Field is  accepting a value with minimum 16 char in UM page");
		UserManagementPage.click_okBtn();
		Thread.sleep(1000);
		UserManagementPage.ClickCancelBtn();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.ChangeNewPW_getalertmsg(UserID, Password, InvalidPassword);
		Thread.sleep(1000);
		String msg1 = tu.get_AlertMsg_text();
		sa.assertEquals(msg1, Expmsg, "Fail : Password Field is  accepting a value with minimum 16 char in Login page");

		sa.assertAll();
	}
//Policies006-Verify  the drop down values for _Expire password_ field

	@Test(groups = {
			"Regression" }, description = "Policies006-Verify  the drop down values for _Expire password_ field")
	public void Policies006() throws Exception {
		extentTest = extent.startTest("Policies006-Verify  the drop down values for _Expire password_ field");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(PoliciesPage.FetchTxt_from_ExpirePwdDD(0), "1 Day");
		// sa.assertEquals(PoliciesPage.FetchTxt_from_ExpirePwdDD(10), "9 Days");
		sa.assertAll();
	}

//Policies007-Verify  the Audit Trail for the modifications made to _Expire pwd_ field

	@Test(groups = {
			"Regression" }, description = "Policies006-Verify  the drop down values for _Expire password_ field")
	public void Policies007() throws Exception {
		extentTest = extent.startTest("Policies006-Verify  the drop down values for _Expire password_ field");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.selectExpirePassword("179 Days");
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);

		PoliciesPage.selectExpirePassword("177 Days");
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = PoliciesPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();

		String ExpectMSG = "Policies: \"Password Expiration\" field updated from \"179 to 177\" by User ID:  \"1\",User Name: \"user1\" ";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for the modifications made to _Expire pwd_ field ");
		sa.assertAll();

	}

	//Policies008-Verify the functionality when any value between 1 to 120  is selected for _Expire pwd_ field
	@Test(dataProvider = "Policies008", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies008-Verify the"
					+ " functionality when any value between 1 to 120  is selected for _Expire pwd_ field ")
	public void Policies008(String Name, String UserID, String Password, String Title,
			String UserType, String NewPassword, String Expmsg) throws Exception {

		extentTest = extent.startTest("Policies008-Verify the functionality when any value between 1 to 120 "
				+ "is selected for _Expire pwd_ field");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.selectExpirePassword("150 Days");
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		Thread.sleep(1000);
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(3000);

		String ActualcrntDate = tu.get_CurrentDate_inCertainFormat("dd-MM-yyyy");
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		//c.add(Calendar.DATE, 119);
		c.add(Calendar.DATE, 151);

		// System.out.println(dateFormat.format(c.getTime()));

		String xx = dateFormat.format(c.getTime());
		String command = "cmd /c start date " + xx;

		try {

			System.out.println(command);
			Process p = Runtime.getRuntime().exec(command);
			Thread.sleep(1000);
			p = Runtime.getRuntime().exec("taskkill /f /im cmd.exe");

			Thread.sleep(5000);

			LoginPage.LoginEntry(UserID, NewPassword);
			LoginPage.ClickLoginBtn();
			String Actualmsg = tu.get_AlertMsg_text();
			System.out.println(Actualmsg);
			sa.assertEquals(Actualmsg, Expmsg, "Fail: Message has not appeared");

			command = "cmd /c start date " + ActualcrntDate;
			System.out.println(command);
			Process p1 = Runtime.getRuntime().exec(command);
			Thread.sleep(1000);
			p1 = Runtime.getRuntime().exec("taskkill /f /im cmd.exe");

		}
		catch (IOException ex) {
			System.out.println(ex);
		}
		sa.assertAll();
	}

//Policies008.1-Verify the functionality when any value between 121 to 240  is selected for _Expire pwd_ field

	@Test(dataProvider = "Policies008_1", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies008.1-Verify the functionality when any value between 121 to 240  is selected for _Expire pwd_ field")
	public void Policies008_1(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String NewPassword, String Expmsg) throws Exception {

		extentTest = extent.startTest(
				"Policies008.1-Verify the functionality when any value between 121 to 240  is selected for _Expire pwd_ field");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.selectExpirePassword("200 Days");
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		Thread.sleep(1000);
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(3000);

		String ActualcrntDate = tu.get_CurrentDate_inCertainFormat("dd-MM-yyyy");
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 201);

// System.out.println(dateFormat.format(c.getTime()));

		String xx = dateFormat.format(c.getTime());
		String command = "cmd /c start date " + xx;

		try {

			System.out.println(command);
			Process p = Runtime.getRuntime().exec(command);
			Thread.sleep(1000);
			p = Runtime.getRuntime().exec("taskkill /f /im cmd.exe");

			Thread.sleep(5000);

			LoginPage.LoginEntry(UserID, NewPassword);

			LoginPage.ClickLoginBtn();
			String Actualmsg = tu.get_AlertMsg_text();
			System.out.println(Actualmsg);
			sa.assertEquals(Actualmsg, Expmsg, "Fail: Message has not appeared");

			command = "cmd /c start date " + ActualcrntDate;
			System.out.println(command);
			Process p1 = Runtime.getRuntime().exec(command);
			Thread.sleep(1000);
			p1 = Runtime.getRuntime().exec("taskkill /f /im cmd.exe");

		}

		catch (IOException ex) {

		}

		sa.assertAll();
	}

//Policies008.2-Verify the functionality when any value between 241 to 366  is selected for _Expire pwd_ field

	@Test(dataProvider = "Policies008_2", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies008.2-Verify the functionality when any value between 241 to 366  is selected for _Expire pwd_ field")
	public void Policies008_2(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String NewPassword, String Expmsg) throws Exception {

		extentTest = extent.startTest(
				"Policies008.2-Verify the functionality when any value between 241 to 366  is selected for _Expire pwd_ field");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.selectExpirePassword("300 Days");
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		Thread.sleep(1000);
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(3000);

		String ActualcrntDate = tu.get_CurrentDate_inCertainFormat("dd-MM-yyyy");
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 301);

//System.out.println(dateFormat.format(c.getTime()));

		String xx = dateFormat.format(c.getTime());
		String command = "cmd /c start date " + xx;

		try {

			System.out.println(command);
			Process p = Runtime.getRuntime().exec(command);
			Thread.sleep(1000);
			p = Runtime.getRuntime().exec("taskkill /f /im cmd.exe");

			Thread.sleep(5000);

			LoginPage.LoginEntry(UserID, NewPassword);

			LoginPage.ClickLoginBtn();
			String Actualmsg = tu.get_AlertMsg_text();
			System.out.println(Actualmsg);
			sa.assertEquals(Actualmsg, Expmsg, "Fail: Message has not appeared");

			command = "cmd /c start date " + ActualcrntDate;
			System.out.println(command);
			Process p1 = Runtime.getRuntime().exec(command);
			Thread.sleep(1000);
			p1 = Runtime.getRuntime().exec("taskkill /f /im cmd.exe");

		}

		catch (IOException ex) {

		}

		sa.assertAll();
	}

//Policies008.3-Verify that the password gets expired after 500 days when 800 days is selected  for _Expire pwd_ field

	@Test(dataProvider = "Policies008_3", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies008_3-Verify that the password gets expired after 500 days when 800 days is selected  for _Expire pwd_ field")
	public void Policies008_3(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String NewPassword, String Expmsg) throws Exception {

		extentTest = extent.startTest(
				"Policies008_3-Verify that the password gets expired after 500 days when 800 days is selected  for _Expire pwd_ field");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.selectExpirePassword("600 Days");
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		Thread.sleep(1000);
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(3000);

		String ActualcrntDate = tu.get_CurrentDate_inCertainFormat("dd-MM-yyyy");
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 601);

//System.out.println(dateFormat.format(c.getTime()));

		String xx = dateFormat.format(c.getTime());
		String command = "cmd /c start date " + xx;

		try {

			System.out.println(command);
			Process p = Runtime.getRuntime().exec(command);
			Thread.sleep(1000);
			p = Runtime.getRuntime().exec("taskkill /f /im cmd.exe");

			Thread.sleep(5000);

			LoginPage.LoginEntry(UserID, NewPassword);

			LoginPage.ClickLoginBtn();
			String Actualmsg = tu.get_AlertMsg_text();
			System.out.println(Actualmsg);
			sa.assertEquals(Actualmsg, Expmsg, "Fail: Message has not appeared");

			command = "cmd /c start date " + ActualcrntDate;
			System.out.println(command);
			Process p1 = Runtime.getRuntime().exec(command);
			Thread.sleep(1000);
			p1 = Runtime.getRuntime().exec("taskkill /f /im cmd.exe");

		}

		catch (IOException ex) {

		}

		sa.assertAll();
	}

//Policies008.4-Verify that the password gets expired after 1000  days when 1000 days is selected  for _Expire pwd_ field

	@Test(dataProvider = "Policies008_4", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies008_4-Verify that the password gets expired after 1000  days when 1000 days is selected  for _Expire pwd_ fiel")
	public void Policies008_4(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String NewPassword, String Expmsg) throws Exception {

		extentTest = extent.startTest(
				"Policies008_4-Verify that the password gets expired after 1000  days when 1000 days is selected  for _Expire pwd_ fiel");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.selectExpirePassword("1000 Days");
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		Thread.sleep(1000);
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(3000);

		String ActualcrntDate = tu.get_CurrentDate_inCertainFormat("dd-MM-yyyy");
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1001);

//System.out.println(dateFormat.format(c.getTime()));

		String xx = dateFormat.format(c.getTime());
		String command = "cmd /c start date " + xx;

		try {

			System.out.println(command);
			Process p = Runtime.getRuntime().exec(command);
			Thread.sleep(1000);
			p = Runtime.getRuntime().exec("taskkill /f /im cmd.exe");

			Thread.sleep(5000);

			LoginPage.LoginEntry(UserID, NewPassword);

			LoginPage.ClickLoginBtn();
			String Actualmsg = tu.get_AlertMsg_text();
			System.out.println(Actualmsg);
			sa.assertEquals(Actualmsg, Expmsg, "Fail: Message has not appeared");

			command = "cmd /c start date " + ActualcrntDate;
			System.out.println(command);
			Process p1 = Runtime.getRuntime().exec(command);
			Thread.sleep(1000);
			p1 = Runtime.getRuntime().exec("taskkill /f /im cmd.exe");

		}

		catch (IOException ex) {

		}

		sa.assertAll();
	}
/*
//Policies009-Verify the number of days when the password expiry error message is displayed for the user

//Policies014-Verify the functionality when _Expire password_ field is not selected

	@Test(groups = {
			"Regression" }, description = "Policies014-Verify the functionality when _Expire password_ field is not selected")
	public void Policies014() throws Exception {
		extentTest = extent
				.startTest("Policies014-Verify the functionality when_Expire password_ field is not selected");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.click_ExpirePasswordCheckBox();
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "Policies : \"Expire Passwords\"  field modified from \" True to False \"  by User ID : \"1\" , User Name : \"user1\"\"";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for unchecking the  _Expire password_ field ");
		sa.assertAll();
	}

//Policies015-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 3, the account gets disabled after failure of 3 consecutive logins
	@Test(dataProvider = "Policies015", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies015-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 3, the account gets disabled after failure of 3 consecutive logins")
	public void Policies015(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws Exception {
		extentTest = extent.startTest(
				"Policies015-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 3, the account gets disabled after failure of 3 consecutive logins");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.click_DisableUserafterAttemptsCheckBox();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

// if we selected  the Disable User after Attempts CheckBox then the value 3 will be  By Default selected 

		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();

		LoginPage = MainHubPage.UserSignOut();
		LoginPage.LoginEntry(UserID, "1213332221");
		LoginPage.ClickLoginBtn();
		tu.click_Close_alertmsg();
		LoginPage.ClickLoginBtn();
		tu.click_Close_alertmsg();
		LoginPage.ClickLoginBtn();
		Thread.sleep(500);
		String Actalert = LoginPage.Alert_PopUp_Content();
		String Expalert = "User account has been disabled, please contact administrator";
		sa.assertEquals(Actalert, Expalert,
				"FAIL: The user account is not  disabled  even after failure of 3 consecutive logins");
		sa.assertAll();

	}

//Policies015.1-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 4, the account gets disabled after failure of 4 consecutive logins

	@Test(dataProvider = "Policies015A", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies015.1-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 4, the account gets disabled after failure of 4 consecutive logins")
	public void Policies015A(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws Exception {
		extentTest = extent.startTest(
				"Policies015.1-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 4, the account gets disabled after failure of 4 consecutive logins");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.LoginFailure_4();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();

		LoginPage = MainHubPage.UserSignOut();
		LoginPage.LoginEntry(UserID, "1213332221");
		LoginPage.click_loginBtn_Loginfail(4);
		Thread.sleep(500);
		String Actalert = LoginPage.Alert_PopUp_Content();
		String Expalert = "User account has been disabled, please contact administrator";
		sa.assertEquals(Actalert, Expalert,
				"FAIL: The user account is not  disabled  even after failure of 4 consecutive logins");
		sa.assertAll();

	}

//Policies015.2-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 5, the account gets disabled after failure of 5 consecutive logins
	@Test(dataProvider = "Policies015B", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies015.2-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 5, the account gets disabled after failure of 5 consecutive logins")
	public void Policies015B(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws Exception {
		extentTest = extent.startTest(
				"Policies015.2-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 5, the account gets disabled after failure of 5 consecutive logins");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.LoginFailure_5();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();

		LoginPage = MainHubPage.UserSignOut();
		LoginPage.LoginEntry(UserID, "5646546476668");
		LoginPage.click_loginBtn_Loginfail(5);
		Thread.sleep(500);
		String Actalert = LoginPage.Alert_PopUp_Content();
		String Expalert = "User account has been disabled, please contact administrator";
		sa.assertEquals(Actalert, Expalert,
				"FAIL: The user account is not  disabled  even after failure of 5 consecutive logins");
		sa.assertAll();

	}
//Policies015.3-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 6, the account gets disabled after failure of 6 consecutive logins

	@Test(dataProvider = "Policies015C", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies015.3-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 6, the account gets disabled after failure of 5 consecutive logins")
	public void Policies015C(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws Exception {
		extentTest = extent.startTest(
				"Policies015.3-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 6, the account gets disabled after failure of 5 consecutive logins");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.LoginFailure_6();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();

		LoginPage = MainHubPage.UserSignOut();
		LoginPage.LoginEntry(UserID, "4654654656");
		LoginPage.click_loginBtn_Loginfail(6);
		Thread.sleep(500);
		String Actalert = LoginPage.Alert_PopUp_Content();
		String Expalert = "User account has been disabled, please contact administrator";
		sa.assertEquals(Actalert, Expalert,
				"FAIL: The user account is not  disabled  even after failure of 6 consecutive logins");
		sa.assertAll();

	}
//Policies015.4-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 7, the account gets disabled after failure of 6 consecutive logins

	@Test(dataProvider = "Policies015D", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies015.3-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 7, the account gets disabled after failure of 5 consecutive logins")
	public void Policies015D(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws Exception {
		extentTest = extent.startTest(
				"Policies015.3-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 7, the account gets disabled after failure of 5 consecutive logins");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.LoginFailure_7();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();

		LoginPage = MainHubPage.UserSignOut();
		LoginPage.LoginEntry(UserID, "4654654656");
		LoginPage.click_loginBtn_Loginfail(7);
		Thread.sleep(500);
		String Actalert = LoginPage.Alert_PopUp_Content();
		String Expalert = "User account has been disabled, please contact administrator";
		sa.assertEquals(Actalert, Expalert,
				"FAIL: The user account is not  disabled  even after failure of 7 consecutive logins");
		sa.assertAll();

	}
//Policies015.5-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 8, the account gets disabled after failure of 8 consecutive logins

	@Test(dataProvider = "Policies015E", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies015.3-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 8, the account gets disabled after failure of 5 consecutive logins")
	public void Policies015E(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws Exception {
		extentTest = extent.startTest(
				"Policies015.3-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 8, the account gets disabled after failure of 5 consecutive logins");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.LoginFailure_8();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();

		LoginPage = MainHubPage.UserSignOut();
		LoginPage.LoginEntry(UserID, "88874654656");
		LoginPage.click_loginBtn_Loginfail(8);
		Thread.sleep(500);
		String Actalert = LoginPage.Alert_PopUp_Content();
		String Expalert = "User account has been disabled, please contact administrator";
		sa.assertEquals(Actalert, Expalert,
				"FAIL: The user account is not  disabled  even after failure of 8 consecutive logins");
		sa.assertAll();

	}

//Policies015.6-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 9, the account gets disabled after failure of 8 consecutive logins

	@Test(dataProvider = "Policies015F", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies015.3-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 9, the account gets disabled after failure of 5 consecutive logins")
	public void Policies015F(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws Exception {
		extentTest = extent.startTest(
				"Policies015.3-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 9, the account gets disabled after failure of 5 consecutive logins");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.LoginFailure_9();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();

		LoginPage = MainHubPage.UserSignOut();
		LoginPage.LoginEntry(UserID, "99874654656");
		LoginPage.click_loginBtn_Loginfail(9);
		Thread.sleep(500);
		String Actalert = LoginPage.Alert_PopUp_Content();
		String Expalert = "User account has been disabled, please contact administrator";
		sa.assertEquals(Actalert, Expalert,
				"FAIL: The user account is not  disabled  even after failure of 9 consecutive logins");
		sa.assertAll();

	}

//Policies015.7-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 10, the account gets disabled after failure of 8 consecutive logins

	@Test(dataProvider = "Policies015G", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies015.3-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 10, the account gets disabled after failure of 5 consecutive logins")
	public void Policies015G(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws Exception {
		extentTest = extent.startTest(
				"Policies015.3-Verify that when _Disable user acct for consecutive login failures_ field is selected and value selected is 10, the account gets disabled after failure of 5 consecutive logins");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.LoginFailure_10();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();

		LoginPage = MainHubPage.UserSignOut();
		LoginPage.LoginEntry(UserID, "18874654656");
		LoginPage.click_loginBtn_Loginfail(10);
		Thread.sleep(500);
		String Actalert = LoginPage.Alert_PopUp_Content();
		String Expalert = "User account has been disabled, please contact administrator";
		sa.assertEquals(Actalert, Expalert,
				"FAIL: The user account is not  disabled  even after failure of 10 consecutive logins");
		sa.assertAll();

	}

//Policies015.8 - Verify the Audit message for changing the values from  the  disable user account for consecutive login failures drop down box

	@Test(dataProvider = "Policies015G", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies015.8 - Verify the Audit message for changing the values from  the  disable user account for consecutive login failures drop down box")
	public void Policies015H(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws Exception {
		extentTest = extent.startTest(
				"Policies015.8 - Verify the Audit message for changing the values from  the  disable user account for consecutive login failures drop down box");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.LoginFailure_10();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = PoliciesPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();

		String Expalert = "User ID : \"1\" ,  User Name: \"user1\" logged in to do  \"Policies\" operation in \"Policies\" screen";
		sa.assertEquals(Actionmsg, Expalert,
				"FAIL: Audit trial record does not exists for the modified value of _Require min len pwd_ field                ");
		sa.assertAll();

	}

//Policies016-Verify the functionality when _Disable user acct for consecutive login failures_ field is de-selected

	@Test(groups = {
			"Regression" }, description = "Policies016-Verify the functionality when _Disable user acct for consecutive login failures_ field is de-selected")
	public void Policies016() throws Exception {
		extentTest = extent.startTest(
				"Policies016-Verify the functionality when _Disable user acct for consecutive login failures_ field is de-selected");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.click_DisableUserafterAttemptsCheckBox();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = PoliciesPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();

		String Expalert = "Policies: \"Disable Account on 3 Failed Attempts\" field modified from \"True to False\" by User ID : \"1\",  User Name : \"user1\" .\"";
		sa.assertEquals(Actionmsg, Expalert,
				"FAIL: Audit trial record does not exists for the modified value of _Require min len pwd_ field                ");
		sa.assertAll();

	}

//Policies016_1- Verify the functionality when disabled acct user logs in with correct credentials

	@Test(dataProvider = "Policies016A", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies016_1- Verify the functionality when disabled acct user logs in with correct credentials")
	public void Policies016A(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws Exception {
		extentTest = extent.startTest(
				"Policies016_1- Verify the functionality when disabled acct user logs in with correct credentials");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.LoginFailure_3();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();

		LoginPage = MainHubPage.UserSignOut();
		LoginPage.LoginEntry(UserID, "18874654656");
		LoginPage.click_loginBtn_Loginfail(3);
		Thread.sleep(500);

		String Actalert = LoginPage.Alert_PopUp_Content();
		String Expalert = "User account has been disabled, please contact administrator";
		sa.assertEquals(Actalert, Expalert,
				"FAIL: The user account is not  disabled  even after failure of 10 consecutive logins");
		sa.assertAll();
		// String Actalert = tu.get_AlertMsg_text();

	}

//Policies016_1- Verify the functionality when disabled acct user logs in with correct credentials

	@Test(groups = {
			"Regression" }, description = "Policies016_1- Verify the functionality when disabled acct user logs in with correct credentials")
	public void Policies016B() throws Exception {
		extentTest = extent.startTest(
				"Policies016_1- Verify the functionality when disabled acct user logs in with correct credentials");
		SoftAssert sa = new SoftAssert();

		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.InvalidLogin("16B3", "112345678");
		Thread.sleep(500);

		String Actalert = tu.get_AlertMsg_text();
		String Expalert = "User account has been disabled, please contact administrator";
		sa.assertEquals(Actalert, Expalert,
				"FAIL: The user account is not  disabled  even after failure of 10 consecutive logins");
		sa.assertAll();

	}

//Policies017-Verify the functionality when _Display user ID during entry_ field is selected

	@Test(groups = {
			"Regression" }, description = "Policies017-Verify the functionality when _Display user ID during entry_ field is selected")
	public void Policies017() throws Exception {
		extentTest = extent.startTest(
				"Policies017-Verify the functionality when _Display user ID during entry_ field is selected");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.ClickSaveButton();
		sa.assertEquals(PoliciesPage.UseID_Masked(), "1",
				"FAIL: User ID field is msked  when _Display user ID during entry_ field is selected");
		sa.assertAll();

	}

	// Policies022-Verify the functionality when _Require special char for password_
	// field is selected

	@Test(groups = {
			"Regression" }, description = "Policies017-Verify the functionality when _Display user ID during entry_ field is selected")
	public void Policies022() throws Exception {
		extentTest = extent.startTest(
				"Policies017-Verify the functionality when _Display user ID during entry_ field is selected");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.Select_PWDcheckbox();
		PoliciesPage.click_RequireSpecialCharacters();
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "Policies :  \"Require Special Character Password\"  field updated from \"False to True\" by User ID: \"1\", User Name: \"User1\".";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for the modified value of _Require min len pwd_ field           ");
		sa.assertAll();

	}

	// Policies022-Verify the functionality when _Require special char for password_
	// field is selected

	@Test(dataProvider = "Policies022A", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies022-Verify the functionality when _Require special char for password_ field is selected")
	public void Policies022A(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws Exception {
		extentTest = extent.startTest(
				"Policies022-Verify the functionality when _Require special char for password_ field is selected");
		SoftAssert sa = new SoftAssert();
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		sa.assertEquals(UserManagementPage.UserLoginPopupVisible(), true, "Fail : user login pop up is not visible");
		sa.assertAll();
	}

//Policies022.1-Verify the valid input criteria for _Require special characters_ field            
	@Test(dataProvider = "Policies022_1", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies022.1-Verify the valid input criteria for _Require special characters_ field")
	public void Policies022_1(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws Exception {
		extentTest = extent
				.startTest("Policies022.1-Verify the valid input criteria for _Require special characters_ field");
		SoftAssert sa = new SoftAssert();
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		sa.assertEquals(UserManagementPage.UserLoginPopupVisible(), true, "Fail : user login pop up is not visible");
		sa.assertAll();
	}

//Policies022.2-Verify the invalid input criteria for _Require special characters_ field

	@Test(dataProvider = "Policies022_2", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies022.2-Verify the invalid input criteria for _Require special characters_ field")
	public void Policies022_2(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws Exception {
		extentTest = extent
				.startTest("Policies022.2-Verify the invalid input criteria for _Require special characters_ field");
		SoftAssert sa = new SoftAssert();
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);

		String Actionmsg = tu.get_popup_text();
		String ExpectMSG = "Password must contain atleast one special character ,one uppercase and one numeric";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Application accpting the password when _Require special char for password_ field is selected and given pwd without special characters");
		sa.assertAll();

	}

	// Policies023-Verify the functionality when _Require special char for password_
	// field is selected and given pwd without special characters
	@Test(dataProvider = "Policies023", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies023-Verify the functionality when _Require special char for password_ field is selected and given pwd without special characters")
	public void Policies023(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws Exception {
		extentTest = extent.startTest(
				"Policies023-Verify the functionality when _Require special char for password_ field is selected and given pwd without special characters");
		SoftAssert sa = new SoftAssert();
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);

		String Actionmsg = tu.get_popup_text();
		String ExpectMSG = "Password must contain atleast one special character ,one uppercase and one numeric";

		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Application accpting the password when _Require special char for password_ field is selected and given pwd without special characters");
		sa.assertAll();

	}

	// Policies023A-Verify the functionality when _Require special char for
	// password_ field is selected and given pwd without special characters
	@Test(dataProvider = "Policies023A", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Policies023-Verify the functionality when _Require special char for password_ field is selected and given pwd without special characters_ Login page")
	public void Policies023A(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String NewPwd) throws Exception {
		extentTest = extent.startTest(
				"Policies023-Verify the functionality when _Require special char for password_ field is selected and given pwd without special characters_Login page");
		SoftAssert sa = new SoftAssert();
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(500);
		LoginPage.ChangeNewPW_getalertmsg(UserID, Password, NewPwd);

		String Actionmsg = LoginPage.Alert_PopUp_Content();
		String ExpectMSG = "Password must contain atleast one special character ,one uppercase and one numeric";

		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Application accpting the password when _Require special char for password_ field is selected and given pwd without special characters");
		sa.assertAll();

	}

	// Policies023.1-Verify the functionality when _Require special char for
	// password_ field is de-selected
	@Test(groups = {
			"Regression" }, description = "Policies023.1-Verify the functionality when _Require special char for password_ field is de-selected")
	public void Policies023_1() throws Exception {
		extentTest = extent.startTest(
				"Policies023.1-Verify the functionality when _Require special char for password_ field is de-selected");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.click_RequireSpecialCharacters();
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "Policies :  \"Require Special Character Password\"  field updated from \"True to False\" by User ID: \"1\", User Name: \"User1\".";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for the modified value of _Require min len pwd_ field           ");
		sa.assertAll();

	}

// Policies029-Verify Cancel button Implementaion at the Policies screen

	@Test(groups = {
			"Regression" }, description = "Policies023.1-Verify the functionality when _Require special char for password_ field is de-selected")
	public void Policies029() throws Exception {
		extentTest = extent.startTest(
				"Policies023.1-Verify the functionality when _Require special char for password_ field is de-selected");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.click_DisableUserafterAttemptsCheckBox();
		sa.assertEquals(PoliciesPage.IsLoginFailuresComboBox_Enable(), true,
				"Fail : IsLoginFailuresComboBox is not Enable");
		PoliciesPage.ClickCancelButton();
		Thread.sleep(500);
		sa.assertEquals(PoliciesPage.IsLoginFailuresComboBox_Enable(), false,
				"Fail :After clicking cancel button value is not returning to the previous selected value");
		sa.assertAll();

	}

//Policies030-Verify Save button implementation at the Policies screen

	@Test(groups = {
			"Regression" }, description = "Policies023.1-Verify the functionality when _Require special char for password_ field is de-selected")
	public void Policies030() throws Exception {
		extentTest = extent.startTest(
				"Policies023.1-Verify the functionality when _Require special char for password_ field is de-selected");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.SelectAny_Option_FromPWDLengthBox(0);
		PoliciesPage.ICW_9Months();
		PoliciesPage.ClickSaveButton();

		sa.assertEquals(PoliciesPage.UserLoginPopupVisible(), true,
				"Fail : Application not displaying the User Login Popup");
		sa.assertAll();

	}
*/
}
