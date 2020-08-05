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
import com.vrt.pages.EquipmentPage;
import com.vrt.utility.TestUtilities;
import com.vrt.utility.userManagementUtility;
import com.vrt.pages.IRTDDetailspage;
import com.vrt.pages.IRTDHubPage;
import com.vrt.pages.FileManagementPage;
import com.vrt.pages.AuditPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.Setup_defineSetupPage;
import com.vrt.pages.SyncOutPage;
import com.vrt.pages.CopySetuppage;
import com.vrt.pages.SyncInPage;
import com.vrt.pages.SyncInAssetListPage;
import com.vrt.pages.RWFileSelctionPage;
import bsh.ParseException;

public class UM2 extends BaseClass {

	public UM2() throws IOException {
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
	EquipmentPage EquipmentPage;
	IRTDDetailspage IRTDDetailspage;
	IRTDHubPage IRTDHubPage;
	FileManagementPage FileManagementPage;
	AuditPage AuditPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	Setup_defineSetupPage Setup_defineSetupPage;
	SyncInPage SyncInPage;
	SyncOutPage SyncOutPage;
	CopySetuppage CopySetuppage;
	RWFileSelctionPage RWFileSelctionPage;
	SyncInAssetListPage SyncInAssetListPage;

	// Before Class/Test
	// @BeforeTest
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, AWTException {

		extent = new ExtentReports(
				System.getProperty("user.dir") + "/test-output/ER_" + "UM2_UserPrivilagesTest" + ".html", true);
		extent.addSystemInfo("TestSuiteName", "UM2_UserPrivilagesTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name2"));
		System.out.println("UM2: UserPrivilagesTest in Progress..");

		
		//Rename the User file (NgvUsers.uxx) if exists
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
		//Create the default Admin USer
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

		// ADMIN user creation
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.CreateAdminUser(getUID("adminFull"), getPW("adminFull"), "Adm1", getUID("SysAdmin"),
				"1Start@1AM", "AdminNew", "1234556", "abc@gmail.com");
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(500);
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysAdmin"), "1Start@1AM", getPW("SysAdmin"));
		LoginPage = MainHubPage.UserSignOut();

		// SUPERVISOR user creation
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.CreateSupervisorUser(getUID("adminFull"), getPW("adminFull"), "Sup1",
				getUID("SysSupervisor"), "4Start@4AM", "SUpNew", "123345678", "newSup@gmail.com");
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(500);
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysSupervisor"), "4Start@4AM",
				getPW("SysSupervisor"));
		LoginPage = MainHubPage.UserSignOut();

		// OPERATOR user creation
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.CreateOperatorUser(getUID("adminFull"), getPW("adminFull"), "Ope1", getUID("SysOperator"),
				"6Start@6AM", "OperatorNew", "12345678", "newOpe@gmail.com");
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(500);
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysOperator"), "6Start@6AM", getPW("SysOperator"));
		LoginPage = MainHubPage.UserSignOut();
		//Conduct a Syncin operation
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPage(getUID("adminFull"), getPW("adminFull"));
		SyncInPage.enter_Filepath("syncin");
		SyncInPage.click_FltrBtn();
		SyncInAssetListPage = SyncInPage.click_SyncInOK_btn();
		SyncInAssetListPage.click_EquipmentCheckBox();
		SyncInAssetListPage.click_SelectAllBtn();
		SyncInAssetListPage.click_OkBtn();
		SyncInAssetListPage.click_AlrtYesBtn();
		Thread.sleep(6000);
		SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
		Thread.sleep(2000);
		//Verify if Synnin happened or not
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		//System.out.println(MainHubPage.AssetCountInAssetTileOfMainHubPage());
		if (!(MainHubPage.AssetCountInAssetTileOfMainHubPage().contains("0"))) {
			AppClose();
			Thread.sleep(1000);
		} else {
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
			Thread.sleep(2000);
		}
		

	}

	// After All the tests are conducted
	// @AfterTest
	@AfterClass
	public void endReport() throws InterruptedException {
		extent.flush();
		extent.close();
		System.out.println("UM2: UserPrivilagesTest Completed.");
		Thread.sleep(500);
	}

	// Before Method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		//System.out.println("Synin Process check in UM2");
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
	}

	// After method
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
			// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));
			// //to add screen cast/video in extent report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName() + " #");
			// String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver,
			// result.getName());
			// extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2));
			// //to add screenshot in extent report

		}
		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report
		driver.quit();
	}

/***************************
 * Test Methods for UM module
****************************/	
	
	// ADMN046
	@Test(groups = { "Regression" }, description = "Verify the default privileges for Administrator", alwaysRun = true)
	public void ADMN046() throws Exception {
		extentTest = extent.startTest("ADMN046-Verify the default privileges for Administrator");
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Adm1");
		Thread.sleep(500);
		SoftAssert sa1 = new SoftAssert();
		sa1.assertEquals(UserManagementPage.Adminstatus(), true, "FAIL:Adminstatus Not Checked");
		sa1.assertEquals(UserManagementPage.CreateAndEditEquipmentstatus(), true,
				"FAIL: CreateAndEditEquipmentstatus CheckBox Not Checked");
		sa1.assertEquals(UserManagementPage.CreateReportsstatus(), true,
				"FAIL: CreateReportsstatus CheckBox Not Checked");

		sa1.assertEquals(UserManagementPage.DeleteAssetsstatus(), true, "FAIL: DeleteAssetsstatus Not Checked");
		sa1.assertEquals(UserManagementPage.DeleteSetupstatus(), true, "FAIL: DeleteSetupstatus CheckBox Not Checked");
		sa1.assertEquals(UserManagementPage.DeleteEquipmentstatus(), true,
				"FAIL:DeleteEquipmentstatus CheckBox Not Checked");
		sa1.assertEquals(UserManagementPage.DeleteStudyFilesReportsstatus(), true,
				"FAIL: DeleteStudyFilesReportsstatus CheckBox Not Checked");

		sa1.assertEquals(UserManagementPage.CopyFilesReportsstatus(), true, "FAIL:CopyFilesReportsstatus Not Checked");
		sa1.assertEquals(UserManagementPage.Archivedatastatus(), true, "FAIL: Archivedatastatus CheckBox Not Checked");
		sa1.assertEquals(UserManagementPage.ManualSyncstatus(), true, "FAIL:ManualSyncstatus CheckBox Not Checked");
		sa1.assertEquals(UserManagementPage.CameraAccessstatus(), true, "FAIL:CameraAccessstatus CheckBox Not Checked");
		sa1.assertEquals(UserManagementPage.Iscancelvisible(), true, "FAIL:CameraAccessstatus CheckBox Not Checked");
		// UserManagementPage.ClkscrollBar_down();
		sa1.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), true,
				"FAIL:Deletepassfailtemplatestatus CheckBox Not Checked");
		sa1.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), true,
				"FAIL:EditPassFailtemplatestatus CheckBox Not Checked");
		sa1.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), true,
				"FAIL:CreatePassFailtemplatestatus CheckBox Not Checked");
		UserManagementPage.ClkscrollBar_down();
		sa1.assertEquals(UserManagementPage.Audittrailstatus(), true, "FAIL: Audittrailstatus CheckBox Not Checked");
		sa1.assertAll();
	}

	// ADMN047
	@Test(groups = { "Regression" }, description = "Verify the non- default privileges for Administrator")
	public void ADMN047() throws Exception {
		extentTest = extent.startTest("ADMN047-Verify the non- default privileges for Administrator");
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.ClickNewUser();
		UserManagementPage.select_UserType("System Administrator");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), false, "FAIL: CheckBox Checked");
		sa.assertEquals(UserManagementPage.RunQualificationstatus(), false, "FAIL: CheckBox Checked");
		sa.assertEquals(UserManagementPage.RunCalibrationstatus(), false, "FAIL: CheckBox Checked");
		sa.assertEquals(UserManagementPage.PrivCreateEditSetupstatus(), false, "FAIL: CheckBox Checked");
		sa.assertAll();

	}

	// ADMN048A
	@Test(groups = {
			"Regression" }, description = "ADMN048A-Verify if Administrator is able to access the "
					+ "default privilege-Create Equipment")
	public void ADMN048A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN048A if Administrator is able to access the "
						+ "default privilege-Create Equipment");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("IRTD", "ADMN048A", "48A");
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		String ExpAlrtMsg = "Data saved successfully";
		String ActAlertMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Equipment should be created successfully");
		sa.assertAll();
	}

	// ADMN048B
	@Test(groups = {
			"Regression" }, description = "ADMN048B-Verify if Administrator is able to access "
					+ "the default privilege Edit Equipment")
	public void ADMN048B() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN048B_Verify if Administrator is able to access the "
						+ "default privilege Edit Equipment");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("IRTD", "ADMN048B", "48B");
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		tu.click_Close_alertmsg();
		EquipmentHubPage = EquipmentPage.ClickBackBtn();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("ADMN048B");
		IRTDDetailspage.enter_IRTDEquipName("Editing");
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		String ExpAlrtMsg = "Equipment \"ADMN048B\" Updated successfully.";
		String ActAlertMsg = EquipmentHubPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Saved Successfully message should be displayed");
		sa.assertAll();
	}

	// ADMN049
	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-Archive")
	public void ADMN049() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN049-Verify if Administrator is able to access the default privilege-Archive");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickArchiveBtn();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		sa.assertEquals(FileManagementPage.ArchiveTextBoxVisible(), true, "FAIL: Archive window should be Visible");
		sa.assertAll();
	}

	// ADMN050
	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-SyncIn")
	public void ADMN050() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN050_Verify if Administrator is able to access the default privilege-SyncIn");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPage(getUID("SysAdmin"), getPW("SysAdmin"));
		sa.assertEquals(SyncInPage.SyncInTextBoxVisible(), true, "FAIL:Sync In TextBox should be Visible");
		sa.assertAll();
	}

	// ADMN050A
	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-SyncOut")
	public void ADMN050A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN050A_Verify if Administrator is able to access the default privilege-SyncOut");
		SoftAssert s5 = new SoftAssert();
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncOutPage = FileManagementPage.ClickSyncOutBtn_SyncOutPage(getUID("SysAdmin"), getPW("SysAdmin"));

		s5.assertEquals(SyncOutPage.SyncOutTextBoxVisible(), true, "FAIL: Sync Out window not present");
		s5.assertAll();
	}

	// ADMN052
	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-Access Audit Trail")
	public void ADMN052() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN052-Verify if Administrator is able to access the default privilege-Access Audit Trail");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		AuditPage = MainHubPage.ClickAuditTitle();
		sa.assertEquals(AuditPage.AuditHeadTitleVisible(), true, "FAIL: Title not present");
		sa.assertAll();
	}

	// ADMN053
	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-Camera Access")
	public void ADMN053() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN053_Verify if Administrator is able to access the default privilege-Camera Access");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Adm1");
		UserManagementPage.click_UserImageTile();
		UserManagementPage.click_CameraIcon();
		sa.assertEquals(UserManagementPage.IsCameracloseBtn_Enable(), true, "FAIL:Camera window should be Visible");
		sa.assertAll();
	}

	// ADMN054
	@Test(groups = {
			"Regression" }, description = "Verify Administrator is unable to access the non-default privilege-Create Asset")
	public void ADMN054() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN054-Verify Administrator is unable to access the non-default privilege-Create Asset");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetHubPage.Click_AddButton();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetHubPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL:User should be unable to access the non-default privilege-Create Asset");
		sa.assertAll();
	}

	// ADMN054A
	@Test(groups = {
			"Regression" }, description = "Verify Administrator is unable to access the non-default privilege-Edit Asset")
	public void ADMN054A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN054A_Verify Administrator is unable to access the non-default privilege Edit Asset");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_assetEditBtn_alrt();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();

		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		sa.assertAll();
	}

	// ADMN055
	@Test(groups = {
			"Regression" }, description = "Verify Administrator is unable to access the non-default privilege-Create Setups")
	public void ADMN055() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN055-Verify Administrator is unable to access the non-default privilege-Create Setups");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_NewStupCreateBtn_alert();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: user should be unable to access the non-default privilege-Create Setups");
		sa.assertAll();
	}

	// ADMN059-Verify if Administrator is able to access the default
	// privilege-Create Reports
	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-Create Reports")
	public void ADMN059() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN059-Verify if Administrator is able to access the default privilege-Create Reports");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		RWFileSelctionPage = assetDetailsPage.GenerateReportsBtn_Nextpage();

		sa.assertEquals(RWFileSelctionPage.assetDetailTitle_Visible(), true, "AssetsNameText  should be Visible");
		sa.assertAll();
	}

	// ADMN062
	@Test(groups = {
			"Regression" }, dataProvider = "ADMIN062", dataProviderClass = userManagementUtility.class,
					description = "ADMN062-Verify if Administrator is able to access the "
							+ "default privilege-Delete Assets")
	public void ADMN062(String AName, String AID, String AType, String AManufacturer, String ALocation) 
			throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN062-Verify if Administrator is able to access the default privilege-Delete Assets");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation(AName, AID, AType, AManufacturer, ALocation);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_Close_alertmsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile(AName);
		assetDetailsPage.DeleteAsset();		
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		Thread.sleep(2000);
		String alrtMsg = assetDetailsPage.get_text_DeleteAst_popup();
		//System.out.println(alrtMsg);
		String[] wordlist = alrtMsg.split(":");
		//System.out.println(wordlist[0]);
		sa.assertEquals(wordlist[0], "Do you want to delete asset", "FAIL:Admin unable to delete Asset or"
				+ " a diferent popup is observed");
		sa.assertAll();
	}
	
	// ADMN064
	@Test(groups = {
			"Regression" }, description = "ADMN064-Verify if Administrator is able to access the "
					+ "default privilege-Delete Equipment")
	public void ADMN064() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN064_Verify if Administrator is able to access the default"
						+ " privilege-Delete Equipment");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		IRTDDetailspage.clickDeleteEquipmentIcon();
		sa.assertEquals(IRTDDetailspage.IRTD_DeletePopupWindow(), true, 
				"FAIL:IRTD_Delete Popup Window should present");
		sa.assertAll();
	}

	// ADMN071
	@Test(groups = { "Regression" }, description = "ADMN071-Verify User Creation for Supervisor User")
	public void ADMN133() throws InterruptedException, IOException {
		extentTest = extent.startTest("ADMN071-Verify User Creation for Supervisor User");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Sup1");
		String ExpAlrtMsg = "Sup1";
		String ActAlertMsg = UserManagementPage.GetUserNametext();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:User should be available");
		sa.assertAll();
	}

	// ADMN072
	@Test(groups = { "Regression" }, description = "ADMN072-Verify default privileges  for Supervisor User")
	public void ADMN072() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN072-Verify default privileges  for Supervisor User");
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.ClickNewUser();
		UserManagementPage.select_UserType("Supervisor");
		SoftAssert sa = new SoftAssert();
		// Validate check boxes are checked
		sa.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), true,
				"FAIL:CreaeteEditAssetPrivstatus should Checked");
		sa.assertEquals(UserManagementPage.CreaeteEditSetupstatus(), true,
				"FAIL:CreaeteEditSetupstatus should Checked");
		sa.assertEquals(UserManagementPage.CreateReportsstatus(), true, "FAIL :CreateReportsstatus should Checked");
		sa.assertEquals(UserManagementPage.RunQualificationstatus(), true,
				"FAIL: RunQualificationstatus should Checked");
		sa.assertEquals(UserManagementPage.RunCalibrationstatus(), true, "FAIL: RunCalibrationstatus should Checked");
		sa.assertEquals(UserManagementPage.ManualSyncstatus(), true, "FAIL: ManualSyncstatus should Checked");
		sa.assertEquals(UserManagementPage.CameraAccessstatus(), true, "FAIL:CameraAccessstatus should Checked");
		UserManagementPage.ClkscrollBar_down();
		UserManagementPage.ClkscrollBar_down();
		sa.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), true,
				"FAIL: CreatePassFailtemplatestatus checkbox should  be Checked");
		sa.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), true,
				"FAIL: EditPassFailtemplatestatus checkbox should  be Checked");
		sa.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), true,
				"FAIL: Deletepassfailtemplatestatus checkbox should  be Checked");
		sa.assertEquals(UserManagementPage.Audittrailstatus(), true, "FAIL: Audittrailstatus should Checked");
		sa.assertAll();
	}

	// ADMN073
	@Test(groups = { "Regression" }, description = "Verify the non- default privileges  for Supervisor User")
	public void ADMN073() throws Exception {
		extentTest = extent.startTest("ADMN073-Verify the non- default privileges  for Supervisor User");
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.ClickNewUser();
		UserManagementPage.select_UserType("Supervisor");
		SoftAssert sa = new SoftAssert();
		// Validate check boxes are not checked
		sa.assertEquals(UserManagementPage.Adminstatus(), false, "FAIL:Adminstatus checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.CreateAndEditEquipmentstatus(), false,
				"FAIL:CreateAndEditEquipmentstatus CheckBox should not be Checked");

		sa.assertEquals(UserManagementPage.DeleteAssetsstatus(), false,
				"FAIL:DeleteAssetsstatus checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.DeleteSetupstatus(), false,
				"FAIL: DeleteSetupstatus checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.DeleteEquipmentstatus(), false,
				"FAIL:DeleteEquipmentstatus checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.DeleteStudyFilesReportsstatus(), false,
				"FAIL:DeleteStudyFilesReportsstatus checkbox should not be Checked");

		sa.assertEquals(UserManagementPage.CopyFilesReportsstatus(), false,
				"FAIL:CopyFilesReportsstatus checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.Archivedatastatus(), false,
				"FAIL:Archivedatastatus CheckBox checkbox should not be Checked");

		sa.assertAll();

	}

	// ADMN074
	@Test(groups = {
			"Regression" }, dataProvider = "ADMIN074", dataProviderClass = userManagementUtility.class,
					description = "Verify if Supervisor is able to access the default privilege-Create Assets")
	public void ADMN074(String AName, String AID, String AType, String AManufacturer, String ALocation) 
			throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN074-Verify if Supervisor is able to access the default privilege-Create Assets");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation(AName, AID, AType, AManufacturer, ALocation);
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpMsg = "Data saved successfully";
		String ActMsg = assetCreationPage.AlertMsg();
		sa.assertEquals(ActMsg, ExpMsg, "FAIL:Data saved successfully alert msg should be displayed ");
		sa.assertAll();
	}

	// ADMN074A
	@Test(groups = {
			"Regression" }, description = "Verify if Supervisor is able to access the default privilege-Edit Assets")
	public void ADMN074A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN074A-Verify if Supervisor is able to access the default privilege-Edit Access");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		sa.assertEquals(assetCreationPage.IsEditAssetscreenDisplayed(), true,
				"FAIL:EditAssetscreen should be Displayed");
		sa.assertAll();
	}

	// ADMN075
	@Test(groups = {
			"Regression" }, description = "Verify if Supervisor is able to access the default privilege-Audit Trail")
	public void ADMN075() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN202_c-Verify if Supervisor is able to access the default privilege-Audit Trail");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		AuditPage = MainHubPage.ClickAuditTitle();
		sa.assertEquals(AuditPage.AuditHeadTitleVisible(), true, "FAIL: Title not present");
		sa.assertAll();
	}

	// dependsOnMethods = "ADMN074",
	// ADMN076
	@Test(groups = {
			"Regression" }, description = "Verify if Supervisor is able to access the default privilege-Camera Access")
	public void ADMN076() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN076-Verify if Supervisor is able to access the default privilege-Camera Access");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.click_CameraIcon();
		sa.assertEquals(assetCreationPage.CameraOnTitleVisible(), true, "FAIL:CameraOn window should be Visible");
		sa.assertAll();

	}

	// ADMN077A
	@Test(groups = {
			"Regression" }, description = "ADMN077A-Verify Supervisor is unable to access the non-default "
					+ "privilege-Create Equipment")
	public void ADMN077A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN077A:Verify Supervisor is unable to access the non-default "
						+ "privilege-Create Equipment");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentHubPage.Alert_ClickAddBtn();
		Thread.sleep(2000);
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = EquipmentHubPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Supervisor was able to Create Equipment");
		sa.assertAll();
	}

	// ADMN077B
	@Test(groups = {
			"Regression" }, description = "ADMN077B-Verify Supervisor is unable to access "
					+ "the non-default privilege-Edit Equipment")
	public void ADMN077B() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN077B-Verify Supervisor is unable to access the "
						+ "non-default privilege-Create Equipment");
		SoftAssert sa = new SoftAssert();

		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("  J1129");
		IRTDDetailspage.enter_IRTDEquipName("Editing");
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		sa.assertAll();
	}
	
	// ADMN078
	@Test(groups = {
			"Regression" }, description = "Verify Supervisor is unable to access the "
					+ "non-default privilege-Delete Asset")
	public void ADMN078() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN078-Verify Supervisor is unable to access the non-default privilege-Delete Asset");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.DeleteAsset();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access- Delete Asset");
		sa.assertAll();
	}

	// ADMN080
	@Test(groups = {
			"Regression" }, description = "Verify Supervisor is unable to access the non-default "
					+ "privilege-Delete Equipments")
	public void ADMN080() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN080:Verify Supervisor is unable to access the"
						+ " non-default privilege-Delete Equipments");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		IRTDDetailspage.clickDeleteEquipmentIcon();
		IRTDDetailspage.ClickYesBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access Delete Equipments");
		sa.assertAll();
	}

	// ADMN082

	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-Copy Files_Reports - Copy Setups")
	public void ADMN082() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN067-Verify if Administrator is able to access the default privilege-Copy Files_Reports - Copy Setups");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("xAst1", "2B1", "HeatBath", "HYdd", "Ind");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("xAst1");
		assetDetailsPage.click_SetupTile();
		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		CopySetuppage.Click_Selectall_chkbox();
		CopySetuppage.click_copy_Btn();
		CopySetuppage.select_alertOption("Yes");
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Operator should be unable to access Delete Equipments");
		sa.assertAll();
	}

	// ADMN083
	@Test(groups = {
			"Regression" }, description = "Verify Supervisor is unable to access the non-default privilege-Archive data")
	public void ADMN083() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN083-Verify Supervisor is unable to access the non-default privilege-Archive data");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickArchiveBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = FileManagementPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Supervisor should be unable to access Archive data");
		sa.assertAll();
	}

	// ADMN084
	@Test(groups = { "Regression" }, description = "Verify if Supervisor user is unable to access Admin")
	public void ADMN084() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN084-Verify if Supervisor is unable to access the non-default privilege-Admin");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		MainHubPage.ClickAdminTile();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = MainHubPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Supervisor should be unable to access the non-default privilege-Admin");
		sa.assertAll();
	}

	// ADMN086
	@Test(groups = {
			"Regression" }, description = "Verify if Supervisor is able to access the default privilege-Create Setups")
	public void ADMN086() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN086-_Verify if Supervisor is able to access the default privilege-Create Setups");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		Setup_defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		sa.assertEquals(Setup_defineSetupPage.defineSetupPage_state(), true, "FAIL: set up page should be displayed");
		sa.assertAll();

	}

	// ADMN088
	@Test(groups = {
			"Regression" }, description = "Verify if Supervisor is able to access the default privilege-Create Reports")
	public void ADMN088() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN088-Verify if Supervisor is able to access the default privilege-Create Reports");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		RWFileSelctionPage = assetDetailsPage.GenerateReportsBtn_Nextpage();

		sa.assertEquals(RWFileSelctionPage.assetDetailTitle_Visible(), true, "AssetsNameText  should be Visible");
		sa.assertAll();
	}

	// ADMN094
	@Test(groups = {
			"Regression" }, description = "Verify if Supervisor is able to access the default privilege-SyncIn")
	public void ADMN094() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN094_Verify if Supervisor is able to access the default privilege-SyncIn");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPage(getUID("SysSupervisor"), getPW("SysSupervisor"));
		sa.assertEquals(SyncInPage.SyncInTextBoxVisible(), true, "FAIL: SyncIn TextBox should be Visible ");
		sa.assertAll();
	}

	// ADMN094A
	@Test(groups = {
			"Regression" }, description = "Verify if Supervisor is able to access the default privilege-SyncOut")
	public void ADMN094A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN094A_Verify if Supervisor is able to access the default privilege-SyncOut");
		SoftAssert s22 = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncOutPage = FileManagementPage.ClickSyncOutBtn_SyncOutPage(getUID("SysSupervisor"), getPW("SysSupervisor"));
		s22.assertEquals(SyncOutPage.SyncOutTextBoxVisible(), true, "FAIL: SyncOut TextBox should be Visible ");
		s22.assertAll();
	}

	// ADMN096
	@Test(groups = { "Regression" }, description = "Verify the User Creation for Operator user from User management")
	public void ADMN096() throws InterruptedException, IOException {
		extentTest = extent.startTest("ADMN096-Verify the User Creation for Operator user from User management");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Ope1");
		String ExpAlrtMsg = "Ope1";
		String ActAlertMsg = UserManagementPage.GetUserNametext();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:User should be available");
		sa.assertAll();

	}

	// ADMN097
	@Test(groups = { "Regression" }, description = "Verify default privileges  for Operator User")
	public void ADMN097() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN097-Verify default privileges  for Operator User");
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.ClickNewUser();
		UserManagementPage.select_UserType("Operator");
		SoftAssert sa = new SoftAssert();
		// Validate check boxes are checked
		sa.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), true,
				"FAIL: CreaeteEditAssetPrivstatus should be Checked");
		sa.assertEquals(UserManagementPage.CreateReportsstatus(), true, "FAIL: CreateReportsstatus should be Checked");
		sa.assertEquals(UserManagementPage.RunQualificationstatus(), true,
				"FAIL: RunQualificationstatus should be Checked");
		sa.assertEquals(UserManagementPage.RunCalibrationstatus(), true,
				"FAIL: RunCalibrationstatus should be Checked");
		sa.assertEquals(UserManagementPage.CameraAccessstatus(), true, "FAIL: CameraAccessstatus should be Checked");

		UserManagementPage.ClkscrollBar_down();
		sa.assertEquals(UserManagementPage.Audittrailstatus(), true, "FAIL: Audittrailstatus should be Checked");
		sa.assertAll();

	}

	// ADMN098
	@Test(groups = { "Regression" }, description = "Verify non-default privileges  for Operator User")
	public void ADMN098() throws Exception {
		extentTest = extent.startTest("ADMN098-Verify non-default privileges  for Operator User");

		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.ClickNewUser();
		UserManagementPage.select_UserType("Operator");
		SoftAssert sa = new SoftAssert();
		// Validate check boxes are not checked
		sa.assertEquals(UserManagementPage.Adminstatus(), false, "FAIL:Adminstatus should not be Checked");
		sa.assertEquals(UserManagementPage.CreateAndEditEquipmentstatus(), false,
				"FAIL:CreateAndEditEquipmentstatus CheckBox should not be Checked");
		sa.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), false,
				"FAIL: CreatePassFailtemplatestatus CheckBox should not be Checked");
		sa.assertEquals(UserManagementPage.DeleteAssetsstatus(), false,
				"FAIL:DeleteAssetsstatus should not be Checked");
		sa.assertEquals(UserManagementPage.DeleteSetupstatus(), false, "FAIL:DeleteSetupstatus should not be Checked");
		sa.assertEquals(UserManagementPage.DeleteEquipmentstatus(), false,
				"FAIL:DeleteEquipmentstatus should not be Checked");
		sa.assertEquals(UserManagementPage.DeleteStudyFilesReportsstatus(), false,
				"FAIL:DeleteStudyFilesReportsstatus should not be Checked");
		sa.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), false,
				"FAIL:EditPassFailtemplatestatus should not be Checked");
		sa.assertEquals(UserManagementPage.CopyFilesReportsstatus(), false,
				"FAIL: CopyFilesReportsstatus should not be Checked");
		sa.assertEquals(UserManagementPage.Archivedatastatus(), false, "FAIL: Archivedatastatus should not be Checked");
		sa.assertEquals(UserManagementPage.ManualSyncstatus(), false, "FAIL: ManualSyncstatus should not be Checked");
		sa.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), false,
				"FAIL: Deletepassfailtemplatestatus should not be Checked");
		sa.assertAll();
	}

	// ADMN099
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is able to access the default privilege-Create  Assets")
	public void ADMN099() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN099-Verify if Operator is able to access the default privilege-Create  Assets");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("oppo2", "1012", "HeatBath", "HYdd", "Ind");
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpMsg = "Data saved successfully";
		String ActMsg = assetCreationPage.AlertMsg();
		sa.assertEquals(ActMsg, ExpMsg, "FAIL: Alert message Not Matched");
		sa.assertAll();
	}

	// ADMN100
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is able to access the default privilege-Edit Assets")
	public void ADMN100() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN100-Verify if Operator is able to access the default privilege-Edit  Assets");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		s.assertEquals(assetCreationPage.IsEditAssetscreenDisplayed(), true,
				"FAIL:User should be able to access the default privilege-Edit Assets");
		s.assertAll();
	}

	// ADMN101
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is able to access the default privilege-Create Reports")
	public void ADMN101() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN101-Verify if Operator is able to access the default privilege-Create Reports");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		RWFileSelctionPage = assetDetailsPage.GenerateReportsBtn_Nextpage();

		sa.assertEquals(RWFileSelctionPage.assetDetailTitle_Visible(), true, "AssetsNameText  should be Visible");
		sa.assertAll();
	}

	// ADMN105
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is able to access the default privilege-Camera access")
	public void ADMN105() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN105-Verify if Operator is able to access the default privilege-Camera access");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.click_CameraIcon();
		s.assertEquals(assetCreationPage.CameraOnTitleVisible(), true,
				"FAIL: Operator should be able to access Camera access");
		s.assertAll();
	}

	// ADMN106
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is unable to access the non-default privilege-Delete Assets")
	public void ADMN106() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN106-Verify if Operator is unable to access the non-default privilege-Delete Assets");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.DeleteAsset();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User is unable to access Delete Assets");
		s.assertAll();
	}

	// ADMN107
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is  unable to access the non-default privilege-Archive data")
	public void ADMN107() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN107-Verify if Operator is  unable to access the non-default privilege-Archive data");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickArchiveBtn();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = FileManagementPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Operator should be unable to access Archive data");
		s.assertAll();
	}

	// ADMN108
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is unable to access the non-default privilege-Admin")
	public void ADMN108() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN108-Verify if Operator is unable to access the non-default privilege-Admin");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		MainHubPage.ClickAdminTile();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = MainHubPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Operator should not be able to access Admin");
		sa.assertAll();
	}

	// ADMN109
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is able to access the non-default privilege-Manual syncIn")
	public void ADMN109() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN109-Verify if Operator is able to access the non-default privilege-Manual syncIn");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncInBtn(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsgForSyncIn = "User do not have permission to perform this operation";
		String ActAlertMsgForSyncIn = FileManagementPage.AlertMsg();
		s.assertEquals(ExpAlrtMsgForSyncIn, ActAlertMsgForSyncIn,
				"FAIL: Operator is able to access the non-default privilege-Manual sync In");
		s.assertAll();
	}

	// ADMN109A
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is able to access the non-default privilege-Manual syncOut")
	public void ADMN109A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN109A-Verify if Operator is able to access the non-default privilege-Manual syncOut");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncInBtn(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsgForSyncOut = "User do not have permission to perform this operation";
		String ActAlertMsgForSyncOut = FileManagementPage.AlertMsg();
		s.assertEquals(ExpAlrtMsgForSyncOut, ActAlertMsgForSyncOut,
				"FAIL: Operator is able to access the non-default privilege-Manual syncOut");
		s.assertAll();
	}

	// ADMN115
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is able to access the default privilege-Access Audit trail")
	public void ADMN115() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN115-Verify if Operator is able to access the default privilege-Access Audit trail");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		AuditPage = MainHubPage.ClickAuditTitle();
		sa.assertEquals(AuditPage.AuditHeadTitleVisible(), true, "FAIL:Operator should be able to access Audit trail");
		sa.assertAll();
	}

	// ADMN116
	@Test(groups = {
			"Regression" }, description = "Verify  Operator is unable to access the non-default privilege-Create Setups")
	public void ADMN116() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN116A- Verify Operator is unable to access the non-default privilege-Create Setups");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_NewStupCreateBtn_alert();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User Is able to access the non-default privilege-Delete Assets");
		s.assertAll();
	}

	// ADMN117A
	@Test(groups = {
			"Regression" }, description = "ADMN117A-Verify if Operator is unable to access the "
					+ "non-default privilege-Create- Equipments")
	public void ADMN117A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN117A-Verify if Operator is able to access the "
						+ "non-default privilege-Create- Equipments");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentHubPage.Alert_ClickAddBtn();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = EquipmentHubPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Operator User is able to create Equipment ");
		s.assertAll();
	}

	// ADMN117B
	@Test(groups = {
			"Regression" }, description = "ADMN117B-Verify if Operator is not able to access the "
					+ "non-default privilege-Edit Equipments")
	public void ADMN117B() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN117B-Verify if Operator is able to access the non-default privilege-Edit Equipments");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		IRTDDetailspage.enter_IRTDEquipName("Editing");
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = EquipmentHubPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Operator user able to edit equipment");
		s.assertAll();
	}

	// ADMN118
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is unable to access the "
					+ "non-default privilege-Delete Equipments")
	public void ADMN118() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN118-Verify if Operator is unable to access the non-default "
				+ "privilege-Delete Equipments");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		IRTDDetailspage.clickDeleteEquipmentIcon();
		IRTDDetailspage.ClickYesBtn();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		Thread.sleep(1000);
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Operator able to Delete Equipments");
		sa.assertAll();
	}

	// ADMN119-Verify if Operator is unable to access the non-default privilege-Copy
	// Files_Reports
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is unable to access the "
					+ "non-default privilege-Copy Files_Reports_Setup reports")
	public void ADMN119() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN119-Verify if Operator is unable to access the non-default "
				+ "privilege-Copy Files_Reports_Setup reports");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min sampling");
		assetDetailsPage.selectFolder_CopyToDrive("syncin", "reports");
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = tu.get_AlertMsg_text();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Operator User is able to perform copy to drive ");
		s.assertAll();
	}

	// ADMN119A-Verify if Operator is unable to access the non-default
	// privilege-Copy Files_Reports-QualReport
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is unable to access the non-default privilege-Copy Files_Reports_QualReport")
	public void ADMN119A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN119-Verify if Operator is unable to access the non-default privilege-Copy Files_Reports_QualReport");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min samplin");
		assetDetailsPage.selectFolder_CopyToDrive("syncin", "reports");
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = tu.get_AlertMsg_text();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User is able to create Equipment ");
		s.assertAll();
	}

	// ADMN119B-Verify if Operator is unable to access the non-default
	// privilege-Copy Files_Reports
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is unable to access the non-default privilege-Copy Files_Reports_Setup reports", alwaysRun = true)
	public void ADMN119B() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN119-Verify if Operator is unable to access the non-default privilege-Copy Files_Reports_Setup reports");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_PassFailReportBtn();
		assetDetailsPage.Select_ReportFile("manual 1 min samplin");
		assetDetailsPage.selectFolder_CopyToDrive("syncin", "reports");
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = tu.get_AlertMsg_text();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User is able to create Equipment ");
		s.assertAll();
	}

}
