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
import com.vrt.pages.IRTDDetailspage;
import com.vrt.pages.IRTDHubPage;
import com.vrt.pages.FileManagementPage;
import com.vrt.pages.AuditPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.Setup_defineSetupPage;
import com.vrt.pages.SyncInPage;
import com.vrt.pages.SyncInAssetListPage;


import bsh.ParseException;

public class UM4 extends BaseClass {
	
	public UM4() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExtentReports extent;
	public ExtentTest extentTest;

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
	SyncInAssetListPage SyncInAssetListPage;
	

	//@BeforeTest
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER_"+"UM4_DeleteStudyAndReportAcessTest"+".html", true);
		extent.addSystemInfo("TestSuiteName", "UM4_DeleteStudyAndReportAcessTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name2"));
		System.out.println("UM4_DeleteStudyAndReportAcessTest in Progress..");
		
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
		//UserManagementPage.Click_AllCheckBox();
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
		//UserManagementPage.Click_AllCheckBox();
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
		//UserManagementPage.Click_AllCheckBox();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(500);
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysOperator"), "4Welcome4@AM",
				getPW("SysOperator"));
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(500);
		
		//SyncIn Assets method
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
		//Verify if Syncin happened or not
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
			Thread.sleep(6000);
			SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
			Thread.sleep(2000);
		}
	}

	// After All the tests are conducted
	//@AfterTest
	@AfterClass
	public void endReport() throws InterruptedException {
		extent.flush();
		extent.close();
		Thread.sleep(500);
	}

	// Before Method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		//System.out.println("Synin Process check in UM4");
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
	}

	//@AfterMethod
	@AfterMethod(alwaysRun = true)
	public void Teardown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getName() + " #");
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getThrowable() + " #");
			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1));
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

	//***************
	//Tests
	//***************
	
	/*// Check
	@Test(description = "check for SyncIn im UM1")
	public void Check() throws Exception {
		extentTest = extent.startTest("Syncin Process check in UM1");
		System.out.println("Syncin Process check in UM1");
	}*/
	
	
	// ADMN051	
	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to "
					+ "access the default privilege-Delete  Pass_Fail reports")
	public void ADMN051() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN051_Verify if Administrator is able to access the default "
				+ "privilege-Delete Delete Pass_Fail reports");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_PassFailReportBtn();
		assetDetailsPage.Select_ReportFile("manual 1 min samplin");
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		s.assertEquals(assetDetailsPage.DeletePopupWindowVisible(), true, 
				"Fail: Admin User not allowed to delete Pass-Fail Report");
		s.assertAll();
	}
	
	// ADMN063
	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-Delete Setups")
	public void ADMN063() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN063_Verify if Administrator is able to access the default privilege-Delete Setups");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage. Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		s.assertEquals(assetDetailsPage.DeletePopupWindowVisible(), true, 
				"Fail: Admin User not allowed to delete Setup file under Steup tile");
		s.assertAll();
	}
		
	
	//ADMN065	
	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-Delete StudyFiles")
	public void ADMN065() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN065A_Verify if Administrator is able to access the default privilege-Delete StudyFiles");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		s.assertEquals(assetDetailsPage.DeletePopupWindowVisible(), true, 
				"Fail: Admin User not allowed to delete Qual Study file");
		s.assertAll();
	}
	
	//ADMN065A	
	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-Delete Reports_qualification sub bar")
	public void ADMN065A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN065B_Verify if Administrator is able to access the default privilege-Delete Reports_qualification sub bar");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualReportsButton();
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		s.assertEquals(assetDetailsPage.DeletePopupWindowVisible(), true,
						"Fail: Admin User not allowed to delete Study file reports");
		s.assertAll();
					
	}	
	
	// ADMN065B	
	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-Delete_SetUpReports")
	public void ADMN065B() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN065C_Verify if Administrator is able to access the default privilege-Delete_SetUpReports");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupReportsButton();
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		s.assertEquals(assetDetailsPage.DeletePopupWindowVisible(), true,
				"Fail: Admin User not allowed to delete Setup file reports");
		s.assertAll();
	}
	
	// ADMN065C
	@Test(groups = { "Regression" }, description = "Verify if Administrator is able to Delete documents"
			+ "from Asset Details-Documents tile")
	public void ADMN065C() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN065E-Verify if Administrator is able to Delete documents"
				+ " from Asset Details-Documents tile");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		s.assertEquals(assetDetailsPage.DeletePopupWindowVisible(), true,
				"Fail: Admin User not allowed to delete document files");
		s.assertAll();
	}


	// ADMN079	
	@Test(groups = {
			"Regression" }, description = "Verify Supervisor is unable to access the non-default privilege-Delete Setups")
	public void ADMN079() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN079_Verify Supervisor is unable to access the non-default privilege-Delete Setups");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage. Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, 
				"FAIL:Supervisor should be unable to access- Delete Setups");
		sa.assertAll();
	}


	// ADMN081A	
	@Test(groups = {
			"Regression" }, description = "ADMN081A-Verify Supervisor is unable to access the non-default privilege-Delete StudyFiles")
	public void ADMN081A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN081A_Verify Supervisor is unable to access the non-default privilege-Delete StudyFiles");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access- Delete Qual StudyFiles");
		sa.assertAll();

	}

	// ADMN081B
	@Test(groups = {
			"Regression" }, description = "ADMN081B-Verify Supervisor is unable to access the default privilege-Delete_Reports")
	public void ADMN081B() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN081B-Verify Supervisor is unable to access the default privilege-Delete_Reports");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualReportsButton();
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access- Delete Qual pdf reports");
		sa.assertAll();
	}

	// ADMN081C
	@Test(groups = {
			"Regression" }, description = "ADMN081C-Verify Supervisor is unable to access the default privilege-Delete_SetUpReports")
	public void ADMN081C() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN081C_Verify Supervisor is unable to access the default privilege-Delete_SetUpReports");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupReportsButton();
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access- Delete setup pdf reports");
		s.assertAll();
	}
	
	// ADMN081D
	@Test(groups = {
			"Regression" }, description = "ADMN081D-Verify if Supervisor is able to access the default privilege-Delete Pass_Fail Report")
	public void ADMN081D() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN081D-Verify if Supervisor is able to access the default privilege-Delete Pass_Fail Report");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_PassFailReportBtn();
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access-Delete Pass_Fail reports");
		s.assertAll();
	}

	// ADMN081E
	@Test(groups = { "Regression" }, description = "ADMN081E-Verify if Supervisor is unable to Delete documents from -Documents tile")
	public void ADMN081E() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN081E_Verify if Supervisor is unable to Delete documents from -Documents tile");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access-Delete Pass_Fail reports");
		s.assertAll();
	}
	
	
	// ADMN103	
	@Test(groups = {
			"Regression" }, description = "Verify Operator is unable to access the non-default privilege-Delete StudyFiles")
	public void ADMN103() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN103A_Verify Operator is unable to access the non-default privilege-Delete StudyFiles");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Operator should be unable to access- Delete Asset StudyFiles");
		sa.assertAll();

	}
	

	// ADMN103A
	@Test(groups = {
			"Regression" }, description = "ADMN103A-Verify Operator is unable to access the "
					+ "non-default privilege-Delete Reports-Qual")
	public void ADMN103A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN103A-Verify Operator is unable to access the non-default privilege-Delete Reports-Qual");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualReportsButton();
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Operator should be unable to access- Delete Asset reports");
		sa.assertAll();
	}

	// ADMN103B

	@Test(groups = {
			"Regression" }, description = "Verify Operator is unable to access the default privilege-Delete_SetUpReports")
	public void ADMN103B() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN103C_Verify Operator is unable to access the default privilege-Delete_SetUpReports");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupReportsButton();
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Operator should be unable to access- Delete Asset reports");
		s.assertAll();
	}

	// ADMN103C
	
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is unable to Delete documents from Asset Details-Documents tile")
	public void ADMN103C() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN103D_Verify if Operator is unable to Delete documents from Asset Details-Documents tile");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Operator should not be able to access-Delete Pass_Fail reports");
		s.assertAll();
	}
	
	
	// ADMN103D
	
	@Test(groups = {
			"Regression" }, description = "Verify if Operator is able to access the default privilege-Delete Pass_Fail Report")
	public void ADMN103D() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN103E-Verify if Operator is able to access the default privilege-Delete Pass_Fail Report");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_PassFailReportBtn();
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Operator should be unable to access-Delete Pass_Fail reports");
		s.assertAll();
	}

	// ADMN120
	@Test(groups = {
			"Regression" }, description = "Verify Operator is unable to access the non-default privilege-Delete Setups",alwaysRun = true)
	public void ADMN120() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN120_Verify Operator is unable to access the non-default privilege-Delete Setups");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_SetupTile();
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Operator should be unable to access- Delete Setups");
		sa.assertAll();
	}
	
}
