//--------------------------------------------------------------------------------------------------------------

// BEFORE EXECUTING THE SCRIPTS, PLEASE LAUNCH THE ECLIPSE IN ADMIN MODE.
// ENSURE POLICY 2 TEST SCRIPT IS RUN BEFORE THE POLICY 1 TEST SCRIPT TO AVOID 
// AUDIT(FUTUTRE DATE) RELATED CONFLICTS IN AUDIT TRAIL

// ---------------------------------------------------------------------------------------------------------------

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
import com.vrt.pages.AuditPage;
import com.vrt.pages.EquipmentHubPage;
import com.vrt.pages.NewEquipmentCreation_Page;
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

import com.vrt.utility.PoliciesUtility;
import com.vrt.utility.TestUtilities;

import javax.swing.JOptionPane;

public class PolicyTest2 extends BaseClass {

	public PolicyTest2() throws IOException {
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
	NewEquipmentCreation_Page EquipmentPage;
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

	// Before Class/Test
	// @BeforeTest
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, AWTException {

		//Verify if Eclipse is launched as Admin mode and if not STOP the execution
		int option = JOptionPane.showConfirmDialog(null, "Did you launch the Eclipse in Administrator mode? " + "\r\n"
				+ "Continue by clicking Yes, else click No to stop the process and relaunch Eclipse in Admin mode. "
				+ "\r\n" + "Test results will be impacted if Eclipse is not in Admin mode.", "Alert!!!",
				JOptionPane.YES_NO_OPTION);
		if (option == 1) {
			System.exit(1);
		}

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_" + "PolicyTest2" + ".html", true);
		extent.addSystemInfo("TestSuiteName", "PolicyTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name2"));
		System.out.println("PolicyTest2 in Progress..");

		// Rename the User file (NgvUsers.uxx) if exists
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

	}

	// After Class/Test
	// @AfterTest
	@AfterClass
	public void endReport() throws InterruptedException, IOException {
		tu.DeleteFiles("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData\\Audit");
		tu.DeleteFiles("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\VRTAuditBackup");
		tu.deleteFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "Policies.ini");
		// System.out.println(".......Cleared Audit Folder.....");
		extent.flush();
		extent.close();
		System.out.println("Policy Test2  Completed.");
		Thread.sleep(500);
	}

	// Before Method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		tu.deleteFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "Policies.ini");

		Thread.sleep(7000);
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
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
		driver.quit();
	}

	/***************
	 * TEST CASES
	 ***************/

	// Policies008-Verify the functionality when any value between 1 to 120 is
	// selected for _Expire pwd_ field
	@Test(dataProvider = "Policies008", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies008-Verify the"
					+ " functionality when any value between 1 to 120  is selected for _Expire pwd_ field ")
	public void Policies008(String Name, String UserID, String Password, String Title, String UserType,
			String NewPassword, String Expmsg, String exPw) throws Exception {

		extentTest = extent.startTest("Policies008-Verify the functionality when any value between 1 to 120 "
				+ "is selected for _Expire pwd_ field");

		SoftAssert sa = new SoftAssert();
		Thread.sleep(1000);
		PoliciesPage.selectExpirePassword(exPw);
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		Thread.sleep(1000);
		LoginPage = MainHubPage.UserSignOut();
		// Thread.sleep(3000);

		// Procedure to change System Date
		int PresentNoOfWeeks = Integer.parseInt(exPw) / 7;
		// System.out.println(PresentNoOfWeeks);
		// Adding 1 more week to the selected password expiry date
		String NewDate = tu
				.convert_StringDate_to_ActualDate_inCertainFormat4(tu.getFutureDate_weeks(PresentNoOfWeeks + 1));
		// System.out.println("NewDate: " + NewDate);
		String ActualcrntDate = tu.get_CurrentDate_inCertainFormat("dd-MM-yyyy");
		// Changing the System Date to Future time based on adding number of weeks
		tu.change_SystemDate(NewDate);
		Thread.sleep(5000);
		LoginPage.LoginEntry(UserID, NewPassword);
		LoginPage.ClickLoginBtn();
		String Actualmsg = tu.get_AlertMsg_text();
		// System.out.println(Actualmsg);
		sa.assertEquals(Actualmsg, Expmsg, "Fail: Message has not appeared");
		// Changing the System Date to Actual current system time
		tu.change_SystemDate(ActualcrntDate);
		Thread.sleep(1000);
		sa.assertAll();

	}

	// Policies008.1-Verify the functionality when any value between 121 to 240 is
	// selected for _Expire pwd_ field
	@Test(dataProvider = "Policies008_1", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies008.1-Verify the functionality when "
					+ "any value between 121 to 240  is selected for _Expire pwd_ field ")
	public void Policies008_1(String Name, String UserID, String Password, String Title, String UserType,
			String NewPassword, String Expmsg, String exPw) throws Exception {

		extentTest = extent.startTest("Policies008.1-Verify the functionality when any"
				+ " value between 121 to 240  is selected for _Expire pwd_ field");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.selectExpirePassword(exPw);
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		Thread.sleep(1000);
		LoginPage = MainHubPage.UserSignOut();
		// Thread.sleep(3000);

		// Procedure to change System Date
		int PresentNoOfWeeks = Integer.parseInt(exPw) / 7;
		// System.out.println(PresentNoOfWeeks);

		// Adding 1 more week to the selected password expiry date
		String NewDate = tu
				.convert_StringDate_to_ActualDate_inCertainFormat4(tu.getFutureDate_weeks(PresentNoOfWeeks + 1));
		// System.out.println("NewDate: " + NewDate);

		String ActualcrntDate = tu.get_CurrentDate_inCertainFormat("dd-MM-yyyy");

		// Changing the System Date to Future time based on adding number of weeks
		tu.change_SystemDate(NewDate);
		Thread.sleep(5000);

		LoginPage.LoginEntry(UserID, NewPassword);
		LoginPage.ClickLoginBtn();
		String Actualmsg = tu.get_AlertMsg_text();
		// System.out.println(Actualmsg);
		sa.assertEquals(Actualmsg, Expmsg, "Fail: Message has not appeared");

		// Changing the System Date to Actual current system time
		tu.change_SystemDate(ActualcrntDate);
		Thread.sleep(1000);
		sa.assertAll();
	}

	// Policies008.2-Verify the functionality when any value between 241 to 366 is
	// selected for _Expire pwd_ field
	@Test(dataProvider = "Policies008_2", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies008.2-Verify the functionality when any value "
					+ "between 241 to 366  is selected for _Expire pwd_ field")
	public void Policies008_2(String Name, String UserID, String Password, String Title, String UserType,
			String NewPassword, String Expmsg, String exPw) throws Exception {

		extentTest = extent.startTest(
				"Policies008.2-Verify the functionality when any value between 241 to 366  is selected for _Expire pwd_ field");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.selectExpirePassword(exPw);
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		Thread.sleep(1000);
		LoginPage = MainHubPage.UserSignOut();
		// Thread.sleep(3000);

		// Procedure to change System Date
		int PresentNoOfWeeks = Integer.parseInt(exPw) / 7;
		// System.out.println(PresentNoOfWeeks);
		// Adding 1 more week to the selected password expiry date
		String NewDate = tu
				.convert_StringDate_to_ActualDate_inCertainFormat4(tu.getFutureDate_weeks(PresentNoOfWeeks + 1));
		// System.out.println("NewDate: " + NewDate);
		String ActualcrntDate = tu.get_CurrentDate_inCertainFormat("dd-MM-yyyy");
		// Changing the System Date to Future time based on adding number of weeks
		tu.change_SystemDate(NewDate);
		Thread.sleep(5000);
		LoginPage.LoginEntry(UserID, NewPassword);
		LoginPage.ClickLoginBtn();
		String Actualmsg = tu.get_AlertMsg_text();
		// System.out.println(Actualmsg);
		sa.assertEquals(Actualmsg, Expmsg, "Fail: Message has not appeared");
		// Changing the System Date to Actual current system time
		tu.change_SystemDate(ActualcrntDate);
		Thread.sleep(1000);
		sa.assertAll();

	}

	// Policies008.3-Verify that the password gets expired after 730 days when 730
	// days is selected for _Expire pwd_ field
	@Test(dataProvider = "Policies008_3", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies008.3-Verify that the password gets expired "
					+ "after 500 days when 800 days is selected  for _Expire pwd_ field")
	public void Policies008_3(String Name, String UserID, String Password, String Title, String UserType,
			String NewPassword, String Expmsg, String exPw) throws Exception {

		extentTest = extent
				.startTest("Policies008.3-Verify that the password gets expired after 500 days when 800 days is "
						+ "selected  for _Expire pwd_ field");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.selectExpirePassword(exPw);
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		Thread.sleep(1000);
		LoginPage = MainHubPage.UserSignOut();
		// Thread.sleep(3000);

		// Procedure to change System Date
		int PresentNoOfWeeks = Integer.parseInt(exPw) / 7;
		// System.out.println(PresentNoOfWeeks);

		// Adding 1 more week to the selected password expiry date
		String NewDate = tu
				.convert_StringDate_to_ActualDate_inCertainFormat4(tu.getFutureDate_weeks(PresentNoOfWeeks + 1));
		// System.out.println("NewDate: " + NewDate);

		String ActualcrntDate = tu.get_CurrentDate_inCertainFormat("dd-MM-yyyy");

		// Changing the System Date to Future time based on adding number of weeks
		tu.change_SystemDate(NewDate);
		Thread.sleep(5000);

		LoginPage.LoginEntry(UserID, NewPassword);
		LoginPage.ClickLoginBtn();
		String Actualmsg = tu.get_AlertMsg_text();
		// System.out.println(Actualmsg);
		sa.assertEquals(Actualmsg, Expmsg, "Fail: Message has not appeared");

		// Changing the System Date to Actual current system time
		tu.change_SystemDate(ActualcrntDate);
		Thread.sleep(1000);
		sa.assertAll();
	}

	// Policies008.4-Verify that the password gets expired after 1000 days when 1000
	// days is selected for _Expire pwd_ field
	@Test(dataProvider = "Policies008_4", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies008.4-Verify that the password gets expired after 1000  days when 1000 days is selected  for _Expire pwd_ field")
	public void Policies008_4(String Name, String UserID, String Password, String Title, String UserType,
			String NewPassword, String Expmsg, String exPw) throws Exception {

		extentTest = extent.startTest(
				"Policies008.4-Verify that the password gets expired after 1000  days when 1000 days is selected  for _Expire pwd_ field");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.selectExpirePassword(exPw);
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		Thread.sleep(1000);
		LoginPage = MainHubPage.UserSignOut();
		// Thread.sleep(3000);

		// Procedure to change System Date
		int PresentNoOfWeeks = Integer.parseInt(exPw) / 7;
		// System.out.println(PresentNoOfWeeks);
		// Adding 1 more week to the selected password expiry date
		String NewDate = tu
				.convert_StringDate_to_ActualDate_inCertainFormat4(tu.getFutureDate_weeks(PresentNoOfWeeks + 1));
		// System.out.println("NewDate: " + NewDate);
		String ActualcrntDate = tu.get_CurrentDate_inCertainFormat("dd-MM-yyyy");
		// Changing the System Date to Future time based on adding number of weeks
		tu.change_SystemDate(NewDate);
		Thread.sleep(5000);
		LoginPage.LoginEntry(UserID, NewPassword);
		LoginPage.ClickLoginBtn();
		String Actualmsg = tu.get_AlertMsg_text();
		// System.out.println(Actualmsg);
		sa.assertEquals(Actualmsg, Expmsg, "Fail: Message has not appeared");
		// Changing the System Date to Actual current system time
		tu.change_SystemDate(ActualcrntDate);
		Thread.sleep(1000);
		sa.assertAll();
	}

	// Policies009-Verify the number of days when the password expiry error message
	// is displayed for the user

	@Test(dataProvider = "Policies009", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies009-Verify the number of days when the password expiry error message is displayed for the user")
	public void Policies009(String Expmsg, String exPw) throws Exception {

		extentTest = extent.startTest(
				"Policies009-Verify the number of days when the password expiry error message is displayed for the user");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.selectExpirePassword(exPw);
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(1000);

		// Procedure to change System Date
		int PresentNoOfdays = Integer.parseInt(exPw) - 5;
		// System.out.println(PresentNoOfdays);

		String NewDate = tu.convert_StringDate_to_ActualDate_inCertainFormat5(
				tu.add_to_Crrnt_DateandTimeStamp(PresentNoOfdays, 0, 0, 0, 0, 0));
		// System.out.println("NewDate: " + NewDate);

		String ActualcrntDate = tu.get_CurrentDate_inCertainFormat("dd-MM-yyyy");
		// System.out.println(ActualcrntDate);

		// Changing the System Date to Future time based on adding number of days

		tu.change_SystemDate(NewDate);
		Thread.sleep(5000);

		LoginPage.LoginEntry(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = LoginPage.ClickonLoginBtn();
		String Actualmsg = tu.get_AlertMsg_text();
		// System.out.println(Actualmsg);
		sa.assertEquals(Actualmsg, Expmsg, "Fail: Message has not appeared");

		// Changing the System Date to Actual current system time
		tu.change_SystemDate(ActualcrntDate);
		Thread.sleep(1000);
		sa.assertAll();
	}

	// Policies009.1-Verify the if the password expires as per 12 AM slot in a day
	// This is handeling manually

	// Policies010-Verify the functionality when an existing user password is about
	// to expire
	// Policies009 is similar as Policies010

	@Test(groups = {
			"Regression" }, description = "Policies010-Verify the functionality when an existing user password is about to expire")
	public void Policies010() throws Exception {

		extentTest = extent.startTest("Policies010-This TC is similar to Policies009");

	}

	// Policies011-Verify the functionality when an existing user password has
	// already expired
	@Test(groups = {
			"Regression" }, description = "Policies011-Verify  the functionality when an existing user password has already expired")
	public void Policies011() throws Exception {

		extentTest = extent.startTest("Policies011-This TC is similar to Policies008.4");

	}

	// Policies012-Verify the Audit trail entry for the changed password event when
	// password expired

	@Test(dataProvider = "Policies012", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies012-Verify the Audit trail entry for the changed password event when password expired")
	public void Policies012(String Name, String UserID, String Password, String Title, String UserType,
			String NewPassword, String Expmsg, String exPw, String changePwd) throws Exception {

		extentTest = extent.startTest(
				"Policies012-Verify the Audit trail entry for the changed password event when password expired");
		tu.DeleteFiles("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData\\Audit");
		tu.DeleteFiles("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\VRTAuditBackup");
		// System.out.println(".......Cleared Audit Folder.....");

		SoftAssert sa = new SoftAssert();
		PoliciesPage.selectExpirePassword(exPw);
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		Thread.sleep(1000);
		LoginPage = MainHubPage.UserSignOut();
		// Thread.sleep(3000);

		// Procedure to change System Date
		int PresentNoOfWeeks = Integer.parseInt(exPw) / 7;
		// System.out.println(PresentNoOfWeeks);

		// Adding 1 more week to the selected password expiry date
		String NewDate = tu
				.convert_StringDate_to_ActualDate_inCertainFormat4(tu.getFutureDate_weeks(PresentNoOfWeeks + 1));
		// System.out.println("NewDate: " + NewDate);

		String ActualcrntDate = tu.get_CurrentDate_inCertainFormat("dd-MM-yyyy");

		// Changing the System Date to Future time based on adding number of weeks
		tu.change_SystemDate(NewDate);
		Thread.sleep(5000);

		LoginPage.LoginEntry(UserID, NewPassword);
		LoginPage.ClickLoginBtn();
		String Actualmsg = tu.get_AlertMsg_text();
		// System.out.println(Actualmsg);
		sa.assertEquals(Actualmsg, Expmsg, "Fail: Message has not appeared");
		Thread.sleep(1000);
		// Changing the System Date to Actual current system time
		tu.change_SystemDate(ActualcrntDate);

		MainHubPage = LoginPage.EnterNewPWtext(changePwd);
		Thread.sleep(1000);
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();

		String ExpectMSG = "User ID : \"12PLB\",User Name : \"PLT0012B\" Logged in to System.";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exist for the changed password event when password expired");

		sa.assertAll();

	}

	// Policies013-Verify the functionality when a new user is created and password
	// is about to expire

	@Test(dataProvider = "Policies013", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies013-Verify the functionality when a new user is created and password is about to expire")
	public void Policies013(String Name, String UserID, String Password, String Title, String UserType,
			String NewPassword, String Expmsg, String exPw) throws Exception {

		extentTest = extent.startTest(
				"Policies013-Verify the functionality when a new user is created and password is about to expire");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.selectExpirePassword(exPw);
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		UserManagementPage = PoliciesPage.ClickUserManagement_TAB();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, Title, UserType);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(UserID, Password, NewPassword);
		Thread.sleep(1000);
		LoginPage = MainHubPage.UserSignOut();
		// Procedure to change System Date
		int PresentNoOfdays = Integer.parseInt(exPw) - 4;
		// System.out.println(PresentNoOfdays);

		String NewDate = tu.convert_StringDate_to_ActualDate_inCertainFormat5(
				tu.add_to_Crrnt_DateandTimeStamp(PresentNoOfdays, 0, 0, 0, 0, 0));
		// System.out.println("NewDate: " + NewDate);

		String ActualcrntDate = tu.get_CurrentDate_inCertainFormat("dd-MM-yyyy");
		// System.out.println(ActualcrntDate);
		// Changing the System Date to Future time based on adding number of days
		tu.change_SystemDate(NewDate);
		Thread.sleep(5000);

		LoginPage.LoginEntry(UserID, NewPassword);
		MainHubPage = LoginPage.ClickonLoginBtn();
		String Actualmsg = tu.get_AlertMsg_text();
		// System.out.println(Actualmsg);
		sa.assertEquals(Actualmsg, Expmsg, "Fail: Message has not appeared");
		Thread.sleep(1000);
		// Changing the System Date to Actual current system time
		tu.change_SystemDate(ActualcrntDate);
		Thread.sleep(1000);
		sa.assertAll();
	}

	// Policies014-Verify the functionality when user gives authentication inside
	// the app with password expired credentials

	@Test(dataProvider = "Policies014", dataProviderClass = PoliciesUtility.class, groups = {
			"Regression" }, description = "Policies014-Verify the functionality when user gives authentication inside the app with password expired credentials ")
	public void Policies014(String Expmsg, String exPw) throws Exception {

		extentTest = extent.startTest(
				"Policies014-Verify the functionality when user gives authentication inside the app with password expired credentials  ");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.selectExpirePassword(exPw);
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		// Procedure to change System Date
		int PresentNoOfWeeks = Integer.parseInt(exPw) / 7;
		// System.out.println(PresentNoOfWeeks);

		// Adding 1 more week to the selected password expiry date
		String NewDate = tu
				.convert_StringDate_to_ActualDate_inCertainFormat4(tu.getFutureDate_weeks(PresentNoOfWeeks + 1));
		// System.out.println("NewDate: " + NewDate);

		String ActualcrntDate = tu.get_CurrentDate_inCertainFormat("dd-MM-yyyy");

		// Changing the System Date to Future time based on adding number of weeks
		tu.change_SystemDate(NewDate);
		Thread.sleep(1000);
		PoliciesPage.clickOn_PWDcheckbox();
		Thread.sleep(1000);
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String Actualmsg = tu.get_AlertMsg_text();
		// System.out.println(Actualmsg);
		sa.assertEquals(Actualmsg, Expmsg, "Fail: Message has not appeared");

		// Changing the System Date to Actual current system time
		tu.change_SystemDate(ActualcrntDate);
		Thread.sleep(1000);
		sa.assertAll();

	}

}
