/**
* @author ruchika.behura
*
*/
package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;
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
import com.vrt.pages.EquipmentHubPage;
import com.vrt.pages.NewEquipmentCreation_Page;
import com.vrt.utility.TestUtilities;

import bsh.ParseException;

import com.vrt.pages.Equipment_IRTDDetailspage;
import com.vrt.pages.IRTDHubPage;
import com.vrt.pages.FileManagementPage;
import com.vrt.pages.AuditPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.Setup_defineSetupPage;
import com.vrt.pages.SyncInPage;
import com.vrt.pages.SyncOutPage;
import com.vrt.pages.SyncInAssetListPage;

public class UM3 extends BaseClass {

	public UM3() throws IOException {
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
	EquipmentHubPage EquipmentHubPage;
	NewEquipmentCreation_Page EquipmentPage;
	Equipment_IRTDDetailspage IRTDDetailspage;
	IRTDHubPage IRTDHubPage;
	FileManagementPage FileManagementPage;
	AuditPage AuditPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	Setup_defineSetupPage Setup_defineSetupPage;
	SyncInPage SyncInPage;
	SyncOutPage SyncOutPage;
	SyncInAssetListPage SyncInAssetListPage;

	// @BeforeTest
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, AWTException {

		extent = new ExtentReports(
				System.getProperty("user.dir") + "/test-output/ER_" + "UM3_customized_UserPrivilagesTest" + ".html",
				true);
		extent.addSystemInfo("TestSuiteName", "UM3_customized_UserPrivilagesTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name2"));
		System.out.println("UM3_customized_UserPrivilagesTest in Progress..");

		// Rename the User file (NgvUsers.uxx) if exists
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

		// ADMIN user creation
		UserManagementPage.ClickNewUser();
		UserManagementPage.CreateAdminUser(getUID("adminFull"), getPW("adminFull"), "Admintest1", getUID("SysAdmin"),
				"1Start@1AM", "AdminNew", "123345678", "newAdmin@gmail.com");

		UserManagementPage.clickAnyUserinUserList("Admintest1");
		Thread.sleep(1000);
		UserManagementPage.Click_AllCheckBox();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(500);
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysAdmin"), "1Start@1AM", getPW("SysAdmin"));
		LoginPage = MainHubPage.UserSignOut();

		// SUPERVISOR user creation
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.CreateSupervisorUser(getUID("adminFull"), getPW("adminFull"), "Suptest1",
				getUID("SysSupervisor"), "3Welcome3@AM", "SUpNew", "123345678", "newSup@gmail.com");
		UserManagementPage.clickAnyUserinUserList("Suptest1");
		UserManagementPage.Click_AllCheckBox();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(500);
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysSupervisor"), "3Welcome3@AM",
				getPW("SysSupervisor"));
		LoginPage = MainHubPage.UserSignOut();

		// OPERATOR User creation
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.CreateOperatorUser(getUID("adminFull"), getPW("adminFull"), "OPE1", getUID("SysOperator"),
				"4Welcome4@AM", "OperatorNew", "12345678", "newOpe@gmail.com");
		UserManagementPage.clickAnyUserinUserList("OPE1");
		UserManagementPage.Click_AllCheckBox();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(500);
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysOperator"), "4Welcome4@AM",
				getPW("SysOperator"));
		LoginPage = MainHubPage.UserSignOut();

		// Conduct a Syncin operation
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPage(getUID("adminFull"), getPW("adminFull"));
		SyncInPage.enter_Filepath("syncin");
		SyncInPage.click_FltrBtn();
		SyncInAssetListPage = SyncInPage.click_SyncInOK_btn();
		SyncInAssetListPage.click_EquipmentCheckBox();
		SyncInAssetListPage.click_SelectAllBtn();
		SyncInAssetListPage.click_OkBtn();
		SyncInAssetListPage.click_AlrtYesBtn();
		Thread.sleep(7000);
		SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
		// Verify if Syncin happened or not
		Thread.sleep(2000);

	}

	// After All the tests are conducted
	// @AfterTest
	@AfterClass
	public void endReport() throws InterruptedException {
		extent.flush();
		extent.close();
		System.out.println("UM3_customized_UserPrivilagesTest Completed.");
		Thread.sleep(500);
	}

	// Before Method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		// System.out.println("Synin Process check in UM3");
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
	}

	// After Method
	@AfterMethod(alwaysRun = true)
	public void Teardown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getName() + " #");
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getThrowable() + " #");
			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1));
			// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));
			// //to add screen cast/video in extent report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName() + " #");
		}
		extent.endTest(extentTest);
		driver.quit();
	}

	/*
	 * // Check
	 * 
	 * @Test(description = "check for SyncIn im UM1") public void Check() throws
	 * Exception { extentTest = extent.startTest("Syncin Process check in UM1");
	 * System.out.println("Syncin Process check in UM1"); }
	 */

	// CADMN1
	@Test(groups = { "Regression" }, description = "Verify the customized (non defult) "
			+ "privileges for Administrator")
	public void CADMN1() throws Exception {
		extentTest = extent.startTest("CADMN1-Verify the customized unchecked privileges for Administrator");
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Admintest1");
		SoftAssert sa1 = new SoftAssert();
		sa1.assertEquals(UserManagementPage.Adminstatus(), false, "FAIL:Adminstatus should not Checked");
		sa1.assertEquals(UserManagementPage.CreateAndEditEquipmentstatus(), false,
				"FAIL: CreateAndEditEquipmentstatus CheckBox should not Checked");
		sa1.assertEquals(UserManagementPage.CreateReportsstatus(), false,
				"FAIL: CreateReportsstatus CheckBox should not Checked");
		sa1.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), false,
				"FAIL:CreatePassFailtemplatestatus CheckBox should not Checked");
		sa1.assertEquals(UserManagementPage.DeleteAssetsstatus(), false, "FAIL: DeleteAssetsstatus should not Checked");
		sa1.assertEquals(UserManagementPage.DeleteSetupstatus(), false, "FAIL: DeleteSetupstatus should not Checked");
		sa1.assertEquals(UserManagementPage.DeleteEquipmentstatus(), false,
				"FAIL:DeleteEquipmentstatus should not Checked");
		sa1.assertEquals(UserManagementPage.DeleteStudyFilesReportsstatus(), false,
				"FAIL: DeleteStudyFilesReportsstatus should not Checked");
		sa1.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), false,
				"FAIL:EditPassFailtemplatestatus CheckBox should not Checked");
		sa1.assertEquals(UserManagementPage.CopyFilesReportsstatus(), false,
				"FAIL:CopyFilesReportsstatus should not Checked");
		sa1.assertEquals(UserManagementPage.Archivedatastatus(), false,
				"FAIL: Archivedatastatus CheckBox should not Checked");
		sa1.assertEquals(UserManagementPage.CameraAccessstatus(), false,
				"FAIL:CameraAccessstatus CheckBox should not Checked");
		sa1.assertEquals(UserManagementPage.ManualSyncstatus(), false,
				"FAIL:ManualSyncstatus CheckBox should not Checked");
		sa1.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), false,
				"FAIL:Deletepassfailtemplatestatus CheckBox should not Checked");
		UserManagementPage.ClkscrollBar_down();
		sa1.assertEquals(UserManagementPage.Audittrailstatus(), false,
				"FAIL: Audittrailstatus CheckBox should be Checked");
		sa1.assertAll();
	}

	// CADMN2
	@Test(groups = { "Regression" }, description = "Verify the customized default privileges for Administrator")
	public void CADMN2() throws Exception {
		extentTest = extent.startTest("CADMN2-Verify the customized privileges for Administrator");
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Admintest1");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), true, "FAIL: CheckBox should be Checked");
		sa.assertEquals(UserManagementPage.RunQualificationstatus(), true, "FAIL: CheckBox should be Checked");
		sa.assertEquals(UserManagementPage.RunCalibrationstatus(), true, "FAIL: CheckBox should be Checked");
		sa.assertEquals(UserManagementPage.PrivCreateEditSetupstatus(), true, "FAIL: CheckBox should be Checked");

		sa.assertAll();
	}

	// ADMN058A
	@Test(groups = { "Regression" }, description = "ADMN058A-Verify if Administrator is Unable to access the "
			+ "customized unchecked privilege-Create_Equipment")
	public void ADMN058A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN058A-Verify if Administrator is Unable to access customized unchecked "
				+ "privilege-Create_Equipment");
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentHubPage.Alert_ClickAddBtn();
		SoftAssert s = new SoftAssert();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = EquipmentHubPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to create Equipment");
		s.assertAll();
	}

	// ADMN058B
	@Test(groups = { "Regression" }, description = "ADMN058B-Verify Administrator is unable to access the "
			+ "customized unchecked privilege-Edit Equipment")
	public void ADMN058B() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN058B-Verify if Administrator is able to access the customized "
				+ "unchecked privilege-Edit_ Equipment");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		IRTDDetailspage.enter_IRTDEquipName("Editing");
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = tu.get_AlertMsg_text();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to edit Equipment");
		s.assertAll();
	}

	// CADMN5
	@Test(groups = { "Regression" }, description = "CADMN5-Verify Admin is unable to access the customized unchecked "
			+ "privilege-Delete Equipments")
	public void CADMN5() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN5_Verify Admin is unable to access the customized unchecked privilege-Delete Equipments");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		IRTDDetailspage.clickDeleteEquipmentIcon();
		IRTDDetailspage.ClickYesBtn();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = tu.get_AlertMsg_text();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		s.assertAll();
	}

	// CADMN6
	@Test(groups = { "Regression" }, description = "Verify if Administrator is unable to access the "
			+ "customized unchecked privilege-Archive")
	public void CADMN6() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN6-Verify if Administrator is unable to access the customized unchecked privilege-Archive");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickArchiveBtn();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = FileManagementPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to access Archive");
		s.assertAll();
	}

	// CADMN7
	@Test(groups = { "Regression" }, description = "Verify if Administrator is able to access the "
			+ "customized unchecked privilege-SyncIn")
	public void CADMN7() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN7_Verify if Administrator is able to access the customized unchecked privilege-Sync In");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncInBtn(getUID("SysAdmin"), getPW("SysAdmin"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = FileManagementPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to access SyncIn");
		s.assertAll();
	}

	// CADMN8
	@Test(groups = { "Regression" }, description = "CADMN8-Verify if Administrator is able to access the "
			+ "customized unchecked privilege-SyncOut")
	public void CADMN8() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN8_Verify if Administrator is able to access the customized unchecked privilege-SyncOut");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncOutBtn(getUID("SysAdmin"), getPW("SysAdmin"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = FileManagementPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to access SyncOut");
		s.assertAll();
	}

	// CADMN9
	@Test(groups = { "Regression" }, description = "CADMN9-Verify if Administrator is able to access the "
			+ "customized unchecked privilege-Access Audit Trail")
	public void CADMN9() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("CADMN9-Verify if Administrator is able to access the customized unchecked "
				+ "privilege-Access Audit Trail");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		MainHubPage.Alert_AuditTitle();
		String ActAlertMsg = MainHubPage.AlertMsg();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to access Audit");
		s.assertAll();
	}

	// CADMN10
	@Test(groups = { "Regression" }, description = "CADMN10-Verify Administrator is able to access the "
			+ "Customized checked privilege-Create Asset")
	public void CADMN10() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN10_Verify Administrator is able to access the Customized checked privilege-Create Asset");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("CAssert22", "402A", "HeatBath", "HYdd", "Ind");
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		String ExpMsg = "Data saved successfully";
		String ActMsg = assetCreationPage.AlertMsg();
		s.assertEquals(ActMsg, ExpMsg, "FAIL: Data saved successfully Msg should be displaying");
		s.assertAll();
	}

	// CADMN11
	@Test(groups = { "Regression" }, description = "CADMN11-Verify Administrator is able to access the "
			+ "Customized chcked privilege-Edit Asset")
	public void CADMN11() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN11_Verify Administrator is able to access the Customized checked privilege-Edit Asset");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		s.assertEquals(assetCreationPage.IsEditAssetscreenDisplayed(), true,
				"FAIL: Admin should be able to access the Customized privilege-Edit Asset");
		s.assertAll();
	}

	// CADMN12
	@Test(groups = { "Regression" }, description = "CADMN12-Verify if Administrator is unable to access the "
			+ "Customized unchecked privilege-Camera Access in edit asset")
	public void CADMN12() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN12_Verify if Administrator is unable to access the Customized unchecked privilege-Camera Access");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.click_CameraIcon();
		String ExpMsg = "User does not have sufficient privileges to perform this operation";
		String ActMsg = assetCreationPage.AlertMsg();
		s.assertEquals(ActMsg, ExpMsg,
				"FAIL:Administrator should be unable to access the Customized privilege-Camera Access");
		s.assertAll();
	}

	// dependsOnMethods = "CADMN10",
	// ADMN060
	@Test(groups = { "Regression" }, description = "Verify Administrator is able to access the "
			+ "customized privilege-Create Setups")
	public void ADMN060() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN060_Verify Administrator is able to access the customized privilege-Create Setups");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		Setup_defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		s.assertEquals(Setup_defineSetupPage.defineSetupPage_state(), true, "FAIL: setup page should be displayed");
		s.assertAll();
	}

	// dependsOnMethods = "CADMN10",
	// CADMN14
	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is unable to access the Customized non default privilege-Delete Assets")
	public void CADMN14() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN14-Verify if Administrator is unable to access the Customized unchecked privilege-Delete Assets");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.DeleteAsset();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL:Administrator should unable to access the default privilege-Delete Assets");
		s.assertAll();
	}

	// CADMN15
	@Test(groups = {
			"Regression" }, description = "CADMN15-Verify the Customized checked privileges for Supervisor User")
	public void CADMN15() throws Exception {
		extentTest = extent.startTest("CADMN15-Verify the Customized default privileges  for Supervisor User");
		SoftAssert sa = new SoftAssert();

		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Suptest1");

		// Validate check boxes are checked
		sa.assertEquals(UserManagementPage.Adminstatus(), true, "FAIL:Adminstatus checkbox should be Checked");
		sa.assertEquals(UserManagementPage.CreateAndEditEquipmentstatus(), true,
				"FAIL:CreateAndEditEquipmentstatus CheckBox should  be Checked");

		sa.assertEquals(UserManagementPage.DeleteAssetsstatus(), true,
				"FAIL:DeleteAssetsstatus checkbox should  be Checked");
		sa.assertEquals(UserManagementPage.DeleteSetupstatus(), true,
				"FAIL: DeleteSetupstatus checkbox should  be Checked");
		sa.assertEquals(UserManagementPage.DeleteEquipmentstatus(), true,
				"FAIL:DeleteEquipmentstatus checkbox should  be Checked");
		sa.assertEquals(UserManagementPage.DeleteStudyFilesReportsstatus(), true,
				"FAIL:DeleteStudyFilesReportsstatus checkbox should  be Checked");

		sa.assertEquals(UserManagementPage.CopyFilesReportsstatus(), true,
				"FAIL:CopyFilesReportsstatus checkbox should be Checked");
		sa.assertEquals(UserManagementPage.Archivedatastatus(), true,
				"FAIL:Archivedatastatus CheckBox checkbox should be Checked");
		sa.assertAll();
	}

	// CADMN16
	@Test(groups = { "Regression" }, description = "Verify the Customized Non-default privileges  for Supervisor User")
	public void CADMN16() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("CADMN16-Verify Customized Non-default privileges for Supervisor User");
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Suptest1");
		SoftAssert sa = new SoftAssert();
		// Validate check boxes are not checked
		sa.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), false,
				"FAIL:CreaeteEditAssetPrivstatus should not Checked");
		sa.assertEquals(UserManagementPage.CreaeteEditSetupstatus(), false,
				"FAIL:CreaeteEditSetupstatus should not Checked");
		sa.assertEquals(UserManagementPage.CreateReportsstatus(), false,
				"FAIL :CreateReportsstatus should not Checked");

		sa.assertEquals(UserManagementPage.RunQualificationstatus(), false,
				"FAIL: RunQualificationstatus should not Checked");
		sa.assertEquals(UserManagementPage.RunCalibrationstatus(), false,
				"FAIL: RunCalibrationstatus should not Checked");
		sa.assertEquals(UserManagementPage.ManualSyncstatus(), false, "FAIL: ManualSyncstatus should not Checked");
		sa.assertEquals(UserManagementPage.CameraAccessstatus(), false, "FAIL:CameraAccessstatus should not Checked");

		UserManagementPage.ClkscrollBar_down();
		sa.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), false,
				"FAIL: CreatePassFailtemplatestatus checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), false,
				"FAIL: EditPassFailtemplatestatus checkbox should not  be Checked");
		sa.assertEquals(UserManagementPage.Audittrailstatus(), false,
				"FAIL: Audittrailstatus CheckBox should not be Checked");
		sa.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), false,
				"FAIL: Deletepassfailtemplatestatus checkbox should not be Checked");
		sa.assertAll();
	}

	// ADMN085A
	@Test(groups = { "Regression" }, description = "ADMN085A-Verify Supervisor is Unable to access the "
			+ "customized  privilege-Create Assets")
	public void ADMN085A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN085A if Supervisor is Unable to access the " + "customized  privilege-Create Assets");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetHubPage.Click_AddButton();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetHubPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		s.assertAll();
	}

	// ADMN085B
	@Test(groups = { "Regression" }, description = "ADMN085B-Verify Supervisor is unable to "
			+ "access the customized privilege-Edit Asset")
	public void ADMN085B() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN085B-Verify Supervisor is unable to access the customized privilege-Edit Asset");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_assetEditBtn_alrt();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Supervisor should be unable to access the customized privilege-Edit Asset");
		s.assertAll();
	}

	// CADMN19
	@Test(groups = {
			"Regression" }, description = "Verify Supervisor is able to access the customized default privilege-Delete Asset")
	public void CADMN19() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CADMN19-Verify Supervisor is able to access the customized default privilege-Delete Asset");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("Supcas5", "503A", "HeatBath", "HYdd", "Ind");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetCreationPage.clickBackBtn();
		MainHubPage = assetHubPage.clickBackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("Supcas5");
		assetDetailsPage.DeleteAsset();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		s.assertEquals(assetDetailsPage.Deletepopupwindow(), true, "FAIL:Delete popup window should be Visible");
		s.assertAll();
	}

	// CADMN20
	@Test(groups = {
			"Regression" }, description = "Verify Operator is unable to access the customized unchecked privilege-Camera Access")
	public void CADMN20() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN39-Verify Operator is unable to access the customized checked privilege-Camera Access");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Suptest1");
		UserManagementPage.click_UserImageTile();
		UserManagementPage.click_CameraIcon();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Operator should be unable to access the customized unchecked privilege-Camera Access");
		s.assertAll();
	}

	// CADMN21
	@Test(groups = { "Regression" }, description = "CADMN21-Verify Supervisor is able to access the "
			+ "customized non-default privilege-Create Equipment")
	public void CADMN21() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN21-Verify Supervisor is able to access the customized non-default privilege-Create Equipment");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("IRTD", "CADMN21", "21");
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "Data saved successfully";
		String ActAlertMsg = tu.get_AlertMsg_text();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Equipment should be created successfully");
		s.assertAll();
	}

	// CADMN22
	@Test(groups = { "Regression" }, description = "CADMN22-Verify Supervisor is able to access the "
			+ "customized non-default privilege-Edit Equipment")
	public void CADMN22() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN22-Verify Supervisor is able to access the customized " + "non-default privilege-Edit Equipment");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("IRTD", "CADMN22", "22");
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		tu.click_Close_alertmsg();
		EquipmentHubPage = EquipmentPage.ClickBackBtn();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("CADMN22");
		IRTDDetailspage.enter_IRTDEquipName("Editing");
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "Equipment \"CADMN22\" Updated successfully.";
		String ActAlertMsg = EquipmentHubPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Saved Successfully message should be displayed");
		s.assertAll();
	}

	// CADMN23A
	@Test(groups = { "Regression" }, description = "CADMN23A-Verify Supervisor is able to access the "
			+ "customized non-default privilege-Delete Equipments")
	public void CADMN23A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("CADMN23A_Verify Supervisor is able to access the customized "
				+ "non-default privilege-Delete Equipments");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("IRTD", "CADMN23A", "23A");
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		tu.click_Close_alertmsg();
		EquipmentHubPage = EquipmentPage.ClickBackBtn();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("CADMN23A");
		IRTDHubPage = IRTDDetailspage.delete_IRTD(getUID("SysSupervisor"), getPW("SysSupervisor"));
		// System.out.println(IRTDHubPage.Is_Irtd_visible("CADMN23"));
		sa.assertEquals(IRTDHubPage.Is_Irtd_visible("CADMN23A"), false, "FAIL:Supervisor Unable to delete IRTD");
		sa.assertAll();
	}

	// CADMN23B
	@Test(groups = { "Regression" }, description = "CADMN23B-Verify Supervisor is able to access the "
			+ "customized default privilege-Archive")
	public void CADMN23B() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CADMN23B-Verify Supervisor is able to access the customized default privilege-Archive");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickArchiveBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		sa.assertEquals(FileManagementPage.ArchiveTextBoxVisible(), true, "FAIL: Archive window should be Visible");
		sa.assertAll();
	}

	// CADMN24
	@Test(groups = { "Regression" }, description = "CADMN24-Verify Supervisor is able to access the customized "
			+ "non-default privilege-Manual sync In")
	public void CADMN24() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN24-Verify Supervisor is able to access the customized non-default privilege-Manual sync In");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncInBtn(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsgForSyncIn = "User do not have permission to perform this operation";
		String ActAlertMsgForSyncIn = FileManagementPage.AlertMsg();
		s.assertEquals(ExpAlrtMsgForSyncIn, ActAlertMsgForSyncIn,
				"FAIL: Operator is able to access the non-default privilege-Manual sync In");
		s.assertAll();
	}

	// CADMN25
	@Test(groups = {
			"Regression" }, description = "Verify Supervisor is able to access the customized non-default privilege-Manual sync Out")
	public void CADMN25() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN25-Verify Supervisor is able to access the customized non-default privilege-Manual sync Out");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncInBtn(getUID("SysSupervisor"), getPW("SysSupervisor"));
		// UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsgForSyncOut = "User do not have permission to perform this operation";
		String ActAlertMsgForSyncOut = FileManagementPage.AlertMsg();
		s.assertEquals(ExpAlrtMsgForSyncOut, ActAlertMsgForSyncOut,
				"FAIL: Operator is able to access the non-default privilege-Manual sync Out");
		s.assertAll();
	}

	// CADMN26
	@Test(groups = { "Regression" }, description = "CADMN26-Verify if Supervisor is unable to access the "
			+ "customized Non-default privilege-Access Audit Trail")
	public void CADMN26() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("CADMN26-Verify if Supervisor is able to access the customized Non-default "
				+ "privilege-Access Audit Trail");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));

		MainHubPage.Alert_AuditTitle();

		String ActAlertMsg = MainHubPage.AlertMsg();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";

		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to access for Audit Trail");
		s.assertAll();

	}

	// CADMN27
	@Test(groups = { "Regression" }, description = "Verify customized checked privileges  for Operator User")
	public void CADMN27() throws Exception {
		extentTest = extent.startTest("CADMN27-Verify customized checked privileges  for Operator User");
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("OPE1");
		SoftAssert sa = new SoftAssert();
		// Validate check boxes checked
		sa.assertEquals(UserManagementPage.Adminstatus(), true, "FAIL:Adminstatus should be Checked");
		sa.assertEquals(UserManagementPage.CreateAndEditEquipmentstatus(), true,
				"FAIL:CreateAndEditEquipmentstatus CheckBox should be Checked");
		sa.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), true,
				"FAIL: CreatePassFailtemplatestatus CheckBox should be Checked");
		sa.assertEquals(UserManagementPage.DeleteAssetsstatus(), true, "FAIL:DeleteAssetsstatus should be Checked");
		sa.assertEquals(UserManagementPage.DeleteSetupstatus(), true, "FAIL:DeleteSetupstatus should be Checked");
		sa.assertEquals(UserManagementPage.DeleteEquipmentstatus(), true,
				"FAIL:DeleteEquipmentstatus should be Checked");
		sa.assertEquals(UserManagementPage.DeleteStudyFilesReportsstatus(), true,
				"FAIL:DeleteStudyFilesReportsstatus should be Checked");
		sa.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), true,
				"FAIL:EditPassFailtemplatestatus should be Checked");
		sa.assertEquals(UserManagementPage.CopyFilesReportsstatus(), true,
				"FAIL: CopyFilesReportsstatus should be Checked");
		sa.assertEquals(UserManagementPage.Archivedatastatus(), true, "FAIL: Archivedatastatus should be Checked");
		sa.assertEquals(UserManagementPage.ManualSyncstatus(), true, "FAIL: ManualSyncstatus should be Checked");
		sa.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), true,
				"FAIL: Deletepassfailtemplatestatus should be Checked");
		sa.assertAll();
	}

	// CADMN28
	@Test(groups = { "Regression" }, description = "Verify customized unchecked privileges for Operator User")
	public void CADMN28() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("CADMN28-Verify customized unchecked privileges for Operator User");
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("OPE1");
		SoftAssert sa = new SoftAssert();
		// Validate check boxes are unchecked
		sa.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), false,
				"FAIL: CreaeteEditAssetPrivstatus should not be Checked");
		sa.assertEquals(UserManagementPage.CreateReportsstatus(), false,
				"FAIL: CreateReportsstatus should not be Checked");
		sa.assertEquals(UserManagementPage.RunQualificationstatus(), false,
				"FAIL: RunQualificationstatus should not be Checked");
		sa.assertEquals(UserManagementPage.RunCalibrationstatus(), false,
				"FAIL: RunCalibrationstatus should not be Checked");
		sa.assertEquals(UserManagementPage.CameraAccessstatus(), false,
				"FAIL: CameraAccessstatus should not be Checked");
		UserManagementPage.ClkscrollBar_down();
		sa.assertEquals(UserManagementPage.Audittrailstatus(), false, "FAIL: Audit trail status should not be Checked");
		sa.assertAll();
	}

	// CADMN29
	@Test(groups = {
			"Regression" }, description = "Verify Operator is able to access the customized checked privileges-Admin")
	public void CADMN29() throws Exception {
		extentTest = extent
				.startTest("CADMN29-Verify Operator is able to access the customized checked privileges-Admin");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		sa.assertEquals(UserManagementPage.IsUMscreenDisplayed(), true,
				"FAIL: User Management screen should be displayed");
		sa.assertAll();
	}

	// CADMN30
	@Test(groups = { "Regression" }, description = "CADMN30-Verify Operator is able to access the "
			+ "customized checked privileges-Create Equipment")
	public void CADMN30() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN30-Verify Operator is able to access the customized checked privilege-Create Equipment");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("IRTD", "CADMN30", "30");
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "Data saved successfully";
		String ActAlertMsg = tu.get_AlertMsg_text();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Equipment should be created successfully");
		EquipmentHubPage = EquipmentPage.ClickBackBtn();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		// System.out.println(IRTDHubPage.Is_Irtd_visible("CADMN30"));
		s.assertEquals(IRTDHubPage.Is_Irtd_visible("CADMN30"), true, "FAIL:IRTD_is not created");
		s.assertAll();

	}

	// CADMN31
	@Test(groups = { "Regression" }, description = "CADMN31-Verify Operator is able to access the "
			+ "customized checked privilege-Edit Equipment")
	public void CADMN31() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CADMN31-Verify Operator is able to access the customized checked-Edit Equipment");
		SoftAssert s = new SoftAssert();

		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		IRTDDetailspage.enter_IRTDEquipName("Editing");
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "Equipment \" J1129\" Updated successfully.";
		String ActAlertMsg = EquipmentHubPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Saved Successfully message should be displayed");
		s.assertAll();
	}

	// CADMN32
	@Test(groups = { "Regression" }, description = "CADMN32-Verify Operator is able to access the "
			+ "customized checked privilege-Delete Equipments")
	public void CADMN32() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN32_Verify Operator is able to access the customized checked privilege-Delete Equipments");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("IRTD", "CADMN32", "CADMN32");
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		tu.click_Close_alertmsg();
		EquipmentHubPage = EquipmentPage.ClickBackBtn();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("CADMN32");
		IRTDHubPage = IRTDDetailspage.delete_IRTD(getUID("SysOperator"), getPW("SysOperator"));
		// System.out.println(IRTDHubPage.Is_Irtd_visible("CADMN32"));
		s.assertEquals(IRTDHubPage.Is_Irtd_visible("CADMN32"), false, "FAIL:Operator unable to delete equipment");
		s.assertAll();
	}

	// ADMN111
	@Test(groups = {
			"Regression" }, description = "Verify Operator is unable to access the customized unchecked-Create Asset")
	public void ADMN111() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CADMN33-Verify Operator is unable to access the customized unchecked-Create Asset");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetHubPage.Click_AddButton();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetHubPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL:User should be unable to access the customized unchecked-Create Asse");
		sa.assertAll();
	}

	// ADMN111A
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is unable to access the customized Unchecked privilege-Edit Assets")
	public void ADMN111A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN34-Verify if Operator is unable to access the customized unchecked privilege-Edit Assets");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("CuOpAst5", "73NB", "HeatBath", "HYdd", "Ind");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetCreationPage.clickBackBtn();
		MainHubPage = assetHubPage.clickBackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("CuOpAst5");
		assetDetailsPage.click_assetEditBtn_alrt();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();

		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Operator should be unable to access the customized unchecked privilege-Edit Assets");
		sa.assertAll();
	}

	// dependsOnMethods = "CADMN34",
	// CADMN35
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is able to access the customized checked privilege-Delete Assets")
	public void CADMN35() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN35-Verify if Operator is able to access the customized checked privilege-Delete Assets");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("CuOpAst5");
		assetDetailsPage.DeleteAsset();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		sa.assertEquals(assetDetailsPage.Deletepopupwindow(), true, "FAIL:Delete popup window should be Visible");
		System.out.println("Deleted successfully");
		sa.assertAll();
	}

	// CADMN36
	@Test(groups = {
			"Regression" }, description = "Verify Operator is able to access the customized checked privilege -Archive")
	public void CADMN36() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CADMN36-Verify Operator is able to access the customized checked privilege-Archive");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickArchiveBtn();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		sa.assertEquals(FileManagementPage.ArchiveTextBoxVisible(), true, "FAIL: Archive window should be Visible");
		sa.assertAll();
	}

	// CADMN37
	@Test(groups = {
			"Regression" }, description = "Verify Operator is able to access the customized checked privilege-SyncIn")
	public void CADMN37() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CADMN37_Verify if Administrator is able to access the default privilege-Sync In");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPage(getUID("SysOperator"), getPW("SysOperator"));
		// UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		s.assertEquals(SyncInPage.SyncInTextBoxVisible(), true, "FAIL:Sync In TextBox should be Visible");
		s.assertAll();
	}

	// CADMN38
	@Test(groups = {
			"Regression" }, description = "Verify Operator is able to access the customized checked privilege-SyncOut")
	public void CADMN38() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CADMN38_Verify if Administrator is able to access the default privilege-SyncOut");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncOutPage = FileManagementPage.ClickSyncOutBtn_SyncOutPage(getUID("SysOperator"), getPW("SysOperator"));
		// SyncOutPage =
		// FileManagementPage.ClickSyncOutBtn_SyncOutPage(getUID("SysOperator"),
		// getPW("SysOperator"));
		// UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		s.assertEquals(SyncOutPage.SyncOutTextBoxVisible(), true, "FAIL:Sync Out TextBox should be Visible");
		s.assertAll();
	}

	// CADMN39
	@Test(groups = {
			"Regression" }, description = "Verify Operator is unable to access the customized unchecked privilege-Camera Access")
	public void CADMN39() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN39-Verify Operator is unable to access the customized checked privilege-Camera Access");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("OPE1");
		UserManagementPage.click_UserImageTile();
		UserManagementPage.click_CameraIcon();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Operator should be unable to access the customized unchecked privilege-Camera Access");
		s.assertAll();
	}

	// CADMN40
	@Test(groups = {
			"Regression" }, description = "Verify operator  is unable to access the customized unchecked privilege-Access Audit Trail")
	public void CADMN40() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN40-Verify operator is unable to access the customized unchecked privilege-Access Audit Trail");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		MainHubPage.Alert_AuditTitle();

		String ActAlertMsg = MainHubPage.AlertMsg();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";

		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to access for Audit Trail");
		s.assertAll();
	}

	// ADMN087
	@Test(groups = { "Regression" }, description = "ADMN087-Verify if Supervisor is able to access "
			+ "the customised non-default privilege-Delete setup")
	public void ADMN087() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"Verify if Supervisor is able to access the customised " + "non-default privilege-Delete setup");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.Click_DeleteBtn_report();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		s.assertEquals(assetDetailsPage.DeletePopupWindowVisible(), true, "FAIL: User should able to delete");
		s.assertAll();
	}

	// ADMN112
	@Test(groups = { "Regression" }, description = "ADMN112-Verify Operator is able to access the "
			+ "customised non-default privilege-Delete setup")
	public void ADMN112() throws InterruptedException, IOException, AWTException {
		extentTest = extent
				.startTest("Verify Operator is able to access the customised " + "non-default privilege-Delete setup");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.Click_DeleteBtn_report();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		s.assertEquals(assetDetailsPage.DeletePopupWindowVisible(), true, "FAIL: User should able to delete");
		s.assertAll();
	}

}
