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


public class assetDetailsTest2 extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p

	public assetDetailsTest2() throws IOException {
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
	public void PreSetup() throws InterruptedException, IOException, ParseException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_" + "_AssetDetailsTest2" + ".html",
				true);
		extent.addSystemInfo("TestSuiteName", "AssetDetailsTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("assetDetailsTest2 in Progress..");

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

		// SUPERVISOR user creation
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.CreateSupervisorUser(getUID("adminFull"), getPW("adminFull"), "Sup1",
				getUID("SysSupervisor"), "4Start@4AM", "SUpNew", "123345678", "newSup@gmail.com");
		UserManagementPage.clickAnyUserinUserList("Sup1");
		UserManagementPage.CreateReports();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(500);
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysSupervisor"), "4Start@4AM",
				getPW("SysSupervisor"));
		LoginPage = MainHubPage.UserSignOut();

		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));

		// Sync IN Assets and setups
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
		// Verify if Synnin happened or not
		Thread.sleep(2000);
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		// System.out.println(MainHubPage.AssetCountInAssetTileOfMainHubPage());
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
	public void endReport_releaseMomory() {
		extent.flush();
		extent.close();
		assetDetailsPage.resetWebElements();
		System.out.println("assetDetails Test2  Completed.");
	}

	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		// assetDetailsPage = assetHubPage.click_assetTile("Asset01");
		// Thread.sleep(500);
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
	 * Asset Details Test cases/scripts
	 * 
	 * @throws AWTException
	 ******************************/
	// ASST019STP-Verify Copy setup functionality when there is only one Asset
	// available
	@Test(groups = {
			"Regression" }, description = "ASST019STP-Verify Copy setup functionality when there is only one Asset")
	public void ASST001() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest("ASST019STP-Verify Copy setup functionality when there is only one Asset");
		SoftAssert sa = new SoftAssert();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.CopyStupBtn_WITH_oneAsset();

		sa.assertEquals(assetDetailsPage.CopySetup_AlertMsg(), true,
				"FAIL: Application failed to display the alert message for  Copy setup functionality when there is only one Asset available ");
		sa.assertAll();

	}

	// ASST055_1REP- Verify the on-click functionality of Delete icon for Detailed
	// report under Reports tile-Qualifications sub tab
	// ASST056_1REP-Verify if Audit trial record exists for Deletion of a Detailed
	// report (This Test case also executing under ASST055_1REP tc )

	@Test(groups = {
			"Regression" }, description = "ASST055_1REP,ASST056_1REP-Verify the on-click functionality of Delete icon for Detailed report under Reports tile-Qualifications sub tab")
	public void ASST002() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest(
				"ASST055_1REP,ASST056_1REP-Verify the on-click functionality of Delete icon for Detailed report under Reports tile-Qualifications sub tab");
		SoftAssert sa = new SoftAssert();
		// assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min samplin");
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage.YesBtn_WithFiles();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_Actiontext();
		String ExpectMSG = "Qualification Report: \"manual 1 min samplin\"  deleted by User ID : \"1\", User Name : \"User1\"";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for Deletion of a Detailed report ");
		sa.assertAll();

	}

	// Here the method name has named as ASST101 in order to delete the reports and
	// asset after executing the above test cases
	// ASST057REP-Verify the on-click functionality of Delete icon under Reports
	// tile-Setups sub tab
	// ASST058REP-Verify if Audit trial record exists for deletion of a setup
	// report_under report section(This script is covering in the below script
	// ASST057REP )
	@Test(groups = {
			"Regression" }, description = "ASST057REP,ASST058REP-Verify the on-click functionality of Delete icon under Reports tile-Setups sub tab")
	public void ASST003() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST057REP-Verify the on-click functionality of Delete icon under Reports tile-Setups sub tab");
		SoftAssert sa = new SoftAssert();

		// assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min sampling");
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage.YesBtn_WithFiles();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_Actiontext();
		String ExpectMSG = "Setup Report : \"manual 1 min sampling\"  deleted by User ID : \"1\", User Name : \"User1\"";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record exists for deletion of a setup report-under report section");
		sa.assertAll();

	}

	//// Here the method name has named as ASST102 in order to delete the reports
	//// and asset after executing the above test cases
	// ASST057_1REP-Verify the on-click functionality of Delete icon for Summary
	// report under Reports tile-Qualifications sub tab

	// ASST058REP-Verify if Audit trial record exists for Deletion of a Summary
	// report (This Test case also executing under ASST057REP tc )

	@Test(groups = {
			"Regression" }, description = "ASST057_1REP,ASST058REP-Verify the on-click functionality of Delete icon for Summary report under Reports tile-Qualifications sub tab")
	public void ASST004() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest(
				"ASST057_1REP,ASST058REP-Verify the on-click functionality of Delete icon for Summary report under Reports tile-Qualifications sub tab");
		SoftAssert sa = new SoftAssert();

		// assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min samplin");
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage.YesBtn_WithFiles();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_Actiontext();
		String ExpectMSG = "Qualification Report: \"manual 1 min samplin\"  deleted by User ID : \"1\", User Name : \"User1\"";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for Deletion of a Summary report");
		sa.assertAll();

	}

	// Here the method name has named as ASST103 in order to delete the reports and
	// asset after executing the above test cases
	// ASST069REP-Verify the on-click functionality of Delete icon for Pass_Fail
	// report under Reports tile-Pass_Fail sub tab

	// ASST070REP-Verify if Audit trial record exists for Deletion of a Pass_Fail
	// report (This script is executing under ASST069REP script )

	@Test(groups = {
			"Regression" }, description = "ASST069REP,ASST070REP-Verify the on-click functionality of Delete icon for Pass_Fail report under Reports tile-Pass_Fail sub tab")
	public void ASST005() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST069REP,ASST070REP-Verify the on-click functionality of Delete icon for Pass_Fail report under Reports tile-Pass_Fail sub tab");
		SoftAssert sa = new SoftAssert();

		// assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_PassFailReportBtn();
		assetDetailsPage.Select_ReportFile("manual 1 min samplin");
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage.YesBtn_WithFiles();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_Actiontext();
		String ExpectMSG = "PassFailReport : \"manual 1 min samplin\"  deleted by User ID : \"1\", User Name : \"User1\"";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for Deletion of a Summary report");
		sa.assertAll();
	}

	// Here the method name has named as ASST104 in order to delete the reports and
	// asset after executing the above test cases
	// ASST082-Verify the on-click functionality of Delete icon for a document under
	// Documents tile
	// ASST083-Verify if Audit trial record exists for Deletion of a Document (This
	// script is covered under the below script ASST082)

	@Test(groups = {
			"Regression" }, description = "ASST082,ASST083-Verify the on-click functionality of Delete icon for a document under Documents tile")
	public void ASST006() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST082,ASST083-Verify the on-click functionality of Delete icon for a document under Documents tile");
		SoftAssert sa = new SoftAssert();

		// assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.Select_DocFile("LTR-40_Cooling.pdf");
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage.YesBtn_WithFiles();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_Actiontext();
		String ExpectMSG = "Document: \"LTR-40_Cooling.pdf\"  deleted by User ID : \"1\", User Name : \"User1\"";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for Deletion of a  document under Documents tile");
		sa.assertAll();

	}

	// Here the method name has named as ASST105 in order to delete the reports and
	// asset after executing the above test cases
	// ASST049-Verify the on-click of delete icon for a Qualification study file
	// ASST051-Verify if Audit trial record exists for Delete study file activity
	// (This script is covering under the script ASST049)

	@Test(priority = 6, groups = {
			"Regression" }, description = "ASST049,ASST051-Verify the on-click of delete icon for a Qualification study file")
	public void ASST007() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("ASST049,ASST051-Verify the on-click of delete icon for a Qualification study file");
		SoftAssert sa = new SoftAssert();

		// assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		assetDetailsPage.click_DeleteQualificationButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage.YesBtn_WithFiles();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_Actiontext();
		String ExpectMSG = "Qualification Study : \"manual 1 min sampling\"  deleted by User ID : \"1\", User Name : \"User1\"";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for Delete study file activity");
		sa.assertAll();
	}

	// Here the method name has named as ASST106 in order to delete the
	// setups,reports and asset after executing the above test cases
	// ASST040-Verify the on-click of delete icon for a setup
	// ASST042STP-Verify if Audit trial record exists for delete setup
	@Test(priority = 7, groups = {
			"Regression" }, description = "ASST040-Verify the on-click of delete icon for a setup")
	public void ASST008() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST040-Verify the on-click of delete icon for a setup");
		SoftAssert sa = new SoftAssert();

		// assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		assetDetailsPage.YesBtn_WithFiles();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_Actiontext();
		String ExpectMSG = "Setup : \"manual 1 min sampling\"  deleted by User ID : \"1\", User Name : \"User1\"";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for Delete study file activity");
		sa.assertAll();
	}

	// Here the method name has named as ASST107 in order to delete the reports and
	// asset after executing the above test cases
	// ASST010-Verify the on-click of Delete icon for Assets with no files in it
	// ASST011-Verify the Audit trail for Delete Assets activity this script is
	// covered under ASST010 script

	@Test(priority = 8, groups = {
			"Regression" }, description = "ASST010,ASST011-Verify the on-click of Delete icon for Assets with no files in it")
	public void ASST009() throws Exception {
		extentTest = extent
				.startTest("ASST010,ASST011-Verify the on-click of Delete icon for Assets with no files in it");
		SoftAssert sa = new SoftAssert();

		// As documents available under this asset we are deleting those document
		// inorder to proceed with Assets Deletion
		// assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min samplin");
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage.YesBtn_WithFiles();

		// Delete aseet Now

		assetDetailsPage.DeleteAssert();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetDetailsPage.Delete_ClickYesBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_Actiontext();
		String ExpectMSG = "Asset: \"SyncInAsset\" is deleted by User Id : \"1\" , User Name : \"User1\"";
		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for Delete asset activity");
		sa.assertAll();

	}

}
