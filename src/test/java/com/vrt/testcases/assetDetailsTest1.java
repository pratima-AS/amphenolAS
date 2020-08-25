package com.vrt.testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import com.vrt.pages.Setup_defineSetupPage;
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
import com.vrt.utility.setupCreationUtility;

public class assetDetailsTest1 extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p

	public assetDetailsTest1() throws IOException {
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

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_" + "assetDetailsTest1" + ".html",
				true);
		extent.addSystemInfo("TestSuiteName", "assetDetailsTest1");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("AssetCreation Test in Progress..");
		// Delete all files from the AutoLogs folder
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
		tu.DeleteFiles(path);
		System.out.println(" AutoLogs Folder is cleared now ");

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

		// Method to Create 1st Asset
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetCreationPage.clickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();

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

	}

	// After All the tests are conducted
	// @AfterTest
	@AfterClass
	public void endReport_releaseMomory() {
		extent.flush();
		extent.close();
		assetDetailsPage.resetWebElements();
		System.out.println("assetDetails1 Test  Completed.");
	}

	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
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

	// ASST001-Verify the details displayed in Asset details screen -
	// EDIT,COPY,DELETE
	@Test(groups = { "Regression" }, description = "ASST001-Verify the details displayed in Asset details screen")
	public void ASST001() throws Exception {
		extentTest = extent.startTest("ASST001-Verify the details displayed in Asset details screen");

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(assetDetailsPage.assetDetail_PageTitle(), "HeatBath - Asset01",
				"FAIL:Incorrect AssetDetails Page title or landed into incorrect Page");
		sa.assertEquals(assetDetailsPage.assetEditBtn_state(), true, "FAIL: Edit field is not present");
		sa.assertEquals(assetDetailsPage.assetDeleteIcon_state(), true, "FAIL: No Delete Icon is  present");
		sa.assertEquals(assetDetailsPage.CopyAsset_state(), true, "FAIL: No Copy Asset Icon is present");
		sa.assertAll();
	}

	// ASST001A-Verify the reports are not displayed in Reports tile when they are
	// not generated
	@Test(groups = {
			"Regression" }, description = "ASST001A-Verify the reports are not displayed in Reports tile when they are not generated")
	public void ASST001A() throws InterruptedException, ParseException, IOException {
		extentTest = extent
				.startTest("ASST001A-Verify the reports are not displayed in Reports tile when they are not generated");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(assetDetailsPage.reportsTile_countdata(), "0",
				"FAIL:Reports tile count displayed >0 under Asset details page");
		sa.assertAll();
	}

	// ASST001B-Generate the setup report and verify the report count
	@Test(groups = {
			"Regression" }, dataProvider = "ASST001B", dataProviderClass = assetCreationUtility.class, description = "ASST001B-Generate the setup report and verify the report count")

	public void ASST001B(String SetupName, String SensorCount, String TempCount, String TCSensorLabel, String Qstart)
			throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("ASST001B-Generate the setup report and verify the report count");
		SoftAssert sa = new SoftAssert();

		// Define Setup
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetupName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorCount);
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
		Setup_ReviewPage.click_Save_Btn(Qstart, "Yes", getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage = Setup_ReviewPage.click_backBtn();

		assetDetailsPage.Click_SetupName("ASST001B");
		assetDetailsPage.Click_Print_Button();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(4000);
		assetDetailsPage.click_PDFpopup_OkBtn();
		assetDetailsPage.perform_alt_tab_OP();
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupReportsButton();
		sa.assertEquals(assetDetailsPage.SetupReportASST001B_Visible(), true,
				"FAIL:Set up Report is not visible under report section  in  Asset details page");

		sa.assertEquals(assetDetailsPage.reportsTile_countdata(), "1",
				"FAIL:Reports tile count displayed <0 under Asset details page");

		sa.assertAll();

	}

	// ASST002-Verify the details displayed on the 2 sections in Asset details
	// screen
	@Test(groups = {
			"Regression" }, description = "ASST002-Verify the details displayed on the 2 sections in Asset details screen")
	public void ASST002() throws Exception {
		extentTest = extent.startTest("ASST002-Verify the details displayed on the 2 sections in Asset details screen");

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(assetDetailsPage.AssetHub_ImgHldrPresence(), true, "FAIL: No Image field is present");
		sa.assertEquals(assetDetailsPage.assetDetail_PageTitle(), "HeatBath - Asset01",
				"FAIL: Asset Name is not  present");
		sa.assertEquals(assetDetailsPage.AssetIDPresence(), true, "FAIL: No Asset ID field is present");
		sa.assertEquals(assetDetailsPage.ModelPresence(), true, "FAIL: No Model field is  present");
		sa.assertEquals(assetDetailsPage.ManufacturerPresence(), true, "FAIL: No Manufacturer field is present");
		sa.assertEquals(assetDetailsPage.TypePresence(), true, "FAIL: No Type field is present");
		sa.assertEquals(assetDetailsPage.LastValidatedDatePresence(), true, "FAIL: No LastValidated field is present");
		Thread.sleep(1000);
		sa.assertEquals(assetDetailsPage.setupTile_state(), true, "FAIL: No setupTile  is present");
		sa.assertEquals(assetDetailsPage.qualTile_state(), true, "FAIL: No qualTile  is present");
		sa.assertEquals(assetDetailsPage.reportsTile_state(), true, "FAIL: No reportsTile  is present");
		sa.assertEquals(assetDetailsPage.docsTile_state(), true, "FAIL: No docsTile  is present");
		sa.assertAll();
	}

	// ASST003-Verify the on-click functionality of edit icon for Asset

	@Test(groups = { "Regression" }, description = "ASST003-Verify the on-click functionality of edit icon for Asset")
	public void ASST003() throws Exception {
		extentTest = extent.startTest("ASST003-Verify the on-click functionality of edit icon for Asset");
		SoftAssert sa = new SoftAssert();
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		sa.assertEquals(assetCreationPage.get_newAssetCreatePagetitle(), "Edit Asset",
				"FAIL:Incorrect AssetCreation Page title in Asset Edit mode or landed into incorrect Page");
		sa.assertAll();
	}

	// ASST004-Verify if the details are saved during Edit Asset post modification
	// ASST005-Verify the Audit trail for Edit Assets activity

	@Test(groups = {
			"Regression" }, description = "ASST004,ASST005-Verify if the details are saved during Edit Asset post modification,Verify the Audit trail for Edit Assets activity")
	public void ASST004() throws Exception {
		extentTest = extent.startTest(
				"ASST004,ASST005-Verify if the details are saved during Edit Asset post modification,Verify the Audit trail for Edit Assets activity");
		SoftAssert sa = new SoftAssert();

		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		String DefaultId = assetCreationPage.getEqpID();
		assetCreationPage.enterAssetID("02");
		String ActualEnteredID = assetCreationPage.getEqpID();
		sa.assertNotSame(ActualEnteredID, DefaultId, "default value has not edited ");

		assetCreationPage.clickSaveBtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		String Before_Clicking_Back = assetCreationPage.getEqpID();

		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		String After_Clicking_Back = assetCreationPage.getEqpID();

		sa.assertEquals(Before_Clicking_Back, After_Clicking_Back,
				"FAIL:The modified values is not displayed in the field");
		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();

		String IDvalue = assetHubPage.getAssetIDvalue();

		sa.assertEquals(IDvalue, "02", "FAIL:The modified values is not displayed in Asset Hub box");

		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		System.out.println(Actionmsg);
		String ExpectMSG = "Asset : \"Asset01\" \"Equipment ID\" field modified from \" 01 to 02 \" by  User ID : \"1\" , User Name : \"User1\".";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL:The Audit trail record for Edit Assets activity is not exist ");
		sa.assertAll();
	}

// ASST006-Verify the Back Button functionality in Edit Asset screen
	@Test(groups = { "Regression" }, description = "Verify the Back Button functionality in Edit Asset screen")
	public void ASST006() throws Exception {
		extentTest = extent.startTest("ASST006-Verify the Back Button functionality in Edit Asset screen");
		SoftAssert sa = new SoftAssert();

		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetDetailsPage = assetCreationPage.click_BackBtn();
		sa.assertEquals(assetDetailsPage.assetDetailPageTitle_Visible(), true, "Fail : Landed in wrong page");
		sa.assertAll();
	}

	// ASST007-Verify the clear button functionality in Edit Asset screen
	@Test(groups = { "Regression" }, description = "ASST007-Verify the clear button functionality in Edit Asset screen")
	public void ASST007() throws Exception {
		extentTest = extent.startTest("ASST007-Verify the clear button functionality in Edit Asset screen");
		SoftAssert sa = new SoftAssert();

		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		String BeforeclearValue = assetCreationPage.getEqpID();
		assetCreationPage.enterAssetID("04");
		assetCreationPage.clickClearBtn();
		String AfterclearValue = assetCreationPage.getEqpID();
		sa.assertEquals(BeforeclearValue, AfterclearValue,
				"FAIL:The modified values is not  cleared off and the previous original value is  displaying in the field");
		sa.assertAll();
	}

	// ASST008-Verify the display of Asset in Asset hub page when any Asset is
	// edited

	@Test(groups = {
			"Regression" }, description = "ASST008-Verify the display of Asset in Asset hub page when any Asset is edited")
	public void ASST008() throws Exception {
		extentTest = extent.startTest("ASST008-Verify the display of Asset in Asset hub page when any Asset is edited");
		SoftAssert sa = new SoftAssert();

		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.enterAssetID("01");
		assetCreationPage.clickSaveBtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");

		sa.assertEquals(assetDetailsPage.assetDetail_PageTitle(), "HeatBath - Asset01",
				"FAIL:Incorrect AssetDetails Page title or landed into incorrect Page");
		sa.assertAll();
	}

	// ASST009-Verify the on-click of Copy icon for Assets
	// ASST022STP-Verify the Audit trail for Copy Assets activity (This TC is also
	// covered under the below Script )

	@Test(groups = { "Regression" }, description = "ASST009,ASST022STP-Verify the on-click of Copy icon for Assets"
			+ " ASST022STP-Verify the Audit trail for Copy Assets activity")
	public void ASST009() throws Exception {
		extentTest = extent.startTest("ASST009,ASST022STP-Verify the on-click of Copy icon for Assets"
				+ " ASST022STP-Verify the Audit trail for Copy Assets activity");
		SoftAssert sa = new SoftAssert();

		Copyassetpage = assetDetailsPage.clickCopyasset();
		sa.assertEquals(Copyassetpage.IsCopyAssetPageTitle_presence(), true,
				"FAIL: Incorrect CopyAsset Page Title presence  or landed into incorrect Page");
		Copyassetpage.Enter_NewAssetNameField("test");
		Copyassetpage.Enter_NewAssetIDField("19");
		Copyassetpage.click_copy_Btn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Copyassetpage.clickBack_Button();
		// This is related to BUG
		assetDetailsPage = Copyassetpage.Yes_alert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "Asset : \"test\" is created by User Id : \"1\" , User Name : \"User1\".";
		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for Copy Assets activity");
		sa.assertAll();

	}

//ASST010 and ASST011 are part of AssetDetailsTest-2 class

//ASST012-Verify the on-click of Delete icon for Assets which has files in it

	@Test(groups = {
			"Regression" }, description = "ASST012-Verify the on-click of Delete icon for Assets which has files in it")
	public void ASST012() throws Exception {
		extentTest = extent.startTest("ASST012-Verify the on-click of Delete icon for Assets which has files in it");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.DeleteAsset();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetDetailsPage.ClickOK_btn();

		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		sa.assertEquals(assetDetailsPage.assetDetailPageTitle_Visible(), true, "Fail : Page title is not diaplayed");
		sa.assertAll();

	}

	// here setup tile and report tile count is displaying 1 , because in above
	// script we are creating them
	// ASST013-Verify for a fresh asset with no activities - Setups, Qualifications
// Documents and Reports - as mentioned, all tiles should display 0
	@Test(groups = {
			"Regression" }, description = "ASST013-Verify for  a fresh asset with no activities - Setups, Qualifications Documents and  Reports -  as mentioned, all tiles should display 0")
	public void ASST013() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"ASST013-Verify for  a fresh asset with no activities - Setups, Qualifications Documents and  Reports -  as mentioned, all tiles should display 0");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(assetDetailsPage.setupTile_countdata(), "1",
				"FAIL: Setup tile count displayed >0 under Asset details page");
		sa.assertEquals(assetDetailsPage.qualTile_countdata(), "0",
				"FAIL: Qual tile count displayed >0 under Asset details page");
		sa.assertEquals(assetDetailsPage.reportsTile_countdata(), "1",
				"FAIL:Reports tile count displayed >0 under Asset details page");
		sa.assertEquals(assetDetailsPage.docsTile_countdata(), "0",
				"FAIL: Docs tile count displayed >0 under Asset details page");
		sa.assertAll();
	}

	// ASST014STP-Verify the details displayed under Setups tile
	@Test(groups = { "Regression" }, description = "ASST014-Verify the details displayed under Setups tile")
	public void ASST014() throws InterruptedException, ParseException, IOException {

		extentTest = extent.startTest("ASST014-Verify the details displayed under Setups tile");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		sa.assertEquals(assetDetailsPage.IsSetupTile_countdataPresence(), true,
				"FAIL:The setup tile is not displayed in the counter for number");
		sa.assertEquals(assetDetailsPage.SetupsHeader_state(), true,
				"FAIL:The Setups Header state is not displayed in the available setup");

		sa.assertEquals(assetDetailsPage.SetupName_Visible(), true,
				"FAIL:The Setup Name is not displayed in the available setup");
		sa.assertEquals(assetDetailsPage.DateUnder_Setup(), true, "FAIL:The Date Under Setup is not displayed");

		sa.assertEquals(assetDetailsPage.isInitiateQualBtn_Visible(), true,
				"FAIL:The InitiateQualBtn  is not displayed");

		sa.assertEquals(assetDetailsPage.NewSetupCreateBtn_State(), true,
				"FAIL:The New Setup Create Button is not displayed");

		sa.assertEquals(assetDetailsPage.CopySetup_Btn_State(), true, "FAIL:The Copy Setup Button is not displayed");

		sa.assertEquals(assetDetailsPage.PrintButton_state(), true, "FAIL:The Print Button is not displayed");

		sa.assertEquals(assetDetailsPage.SetupEditBtn_state(), true,
				"FAIL:The Setup Edit Btn is not displayed in the available setup");
		sa.assertEquals(assetDetailsPage.WiringImgButton_state(), true,
				"FAIL:The Setup WiringImg Button is not displayed for the available setup");
		sa.assertEquals(assetDetailsPage.DeleteBtn_state(), true,
				"FAIL:The Setup Delete Btn is not displayed for the available setup");

		sa.assertEquals(assetDetailsPage.setupcount(), 1, "Fail: setup list not found");

		sa.assertAll();
	}

	// ASST015STP-Verify the Setup date and time for a new Setup
	@Test(groups = { "Regression" }, description = "ASST015STP-Verify the Setup date and time for a new Setup")
	public void ASST015() throws InterruptedException, ParseException, IOException {

		extentTest = extent.startTest("ASST015STP-Verify the Setup date and time for a new Setup");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		sa.assertEquals(assetDetailsPage.DateUnder_Setup(), true,
				"FAIL:The setup tile is not displayed the counter for number");
		sa.assertAll();
	}

	// ASST017STP-Verify New button is available and clicking on button user should
	// able to define new setup

	@Test(groups = {
			"Regression" }, description = "ASST017STP-Verify New button is available and clicking on button user should able to define new setup")
	public void ASST017() throws InterruptedException, ParseException, IOException {

		extentTest = extent.startTest(
				"ASST017STP-Verify New button is available and clicking on button user should able to define new setup");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(assetDetailsPage.NewSetupCreateBtn_State(), true,
				"FAIL:The New Setup Create Button is not displayed");
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();

		sa.assertEquals(defineSetupPage.defineSetupPage_state(), true, "FAIL:The setup define page is not  displayed ");
		sa.assertAll();

	}

	// ASST018STP-Verify on-click of Copy button for a setup
	@Test(groups = { "Regression" }, description = "ASST018STP-Verify on-click of Copy button for a setup")
	public void ASST018() throws InterruptedException, ParseException, IOException {

		extentTest = extent.startTest("ASST018STP-Verify on-click of Copy button for a setup");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");

		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();

		sa.assertEquals(CopySetuppage.CopySetupPage_Title(), true, "FAIL:Application landed to wong page");
		sa.assertAll();
	}

	// ASST019STP this is handle in assetDetailsTest2 class

	// ASST020STP-Verify -Copy to drive- functionality of a setup for local drive

	@Test(groups = { "Regression" }, description = "Verify Copy to drive functionality of a setup for local drive")
	public void ASST020() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest("ASST020STP-Verify -Copy to drive- functionality of a setup for local drive");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.selectFolder_CopyToDrive("AutoLogs", "setup");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String foldrpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
		// System.out.println("Act Folderpath: "+filepath);
		String ExpAlrtMsg = "manual 1 min sampling has been copied successfully to " + foldrpath;
		// System.out.println("Exp Folderpath: "+ExpAlrtMsg);
		String ActAlrtMsg = tu.get_AlertMsg_text();
		// System.out.println("Act alert msg: "+ActAlrtMsg);

		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Copy to drive operation failed for Setup");

		// Confirm if the file got copied to the above destination folder or not
		List<String> fn = tu.get_fileNamesList(foldrpath);
		String expFileName = "1065306A4C9C5E7376FC.cfg";
		for (String filename : fn) {
			if (filename.contains(expFileName)) {
				sa.assertEquals(filename, expFileName, "FAIL: Incorrect file is copied or "
						+ "not all copeid during Copy to drive operation for Setup");
				sa.assertAll();
			}
		}

	}
	// ASST021STP-Verify -Copy to drive- functionality of a setup for USB drive
	// Because of Hardware dependency this will be tested manually

	// ASST022STP-Verify the Audit trail for Copy Assets activity
	// This TC has been addressed in ASST009 Script

	// ASST023STP-Verify the display of Initiate Qualification button in setup tile
	@Test(groups = { "Regression" }, description = "Verify the display of Initiate Qualification button in setup tile")
	public void ASST023() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest("ASST023STP-Verify the display of Initiate Qualification button in setup tile");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(assetDetailsPage.InitiateQualBtn_state(), false,
				"FAIL:The Initiate Qual Btn state is in enable mode");
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		sa.assertEquals(assetDetailsPage.InitiateQualBtn_state(), true,
				"FAIL:The Initiate Qual Btn state is  in disable mode");
		sa.assertAll();

	}

	// ASST024_1STP- Verify the field level validations for valid SOP protocol
	// number
	// field in Initiate qualification pop-up

	@Test(dataProvider = "ASST024_A", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "ASST024_A-Verify the filed level validations for valid SOP protocl number field in Initiate qualification pop-up")

	public void ASST024_A(String SNum) throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST024_A-Verify the filed level validations for valid SOP protocl number field in Initiate qualification pop-up");

		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		SelectBaseStationPage = assetDetailsPage.Enter_SOP(SNum);

		sa.assertEquals(SelectBaseStationPage.SelectBaseStationTitle_state(), true,
				"Fail: SOP did not accept valid data ");
		sa.assertAll();
	}

	// ASST024_B -Verify the invalid characters accepted in SOP protocol number
	// field

	@Test(dataProvider = "ASST024_B", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "ASST024_B-Verify the invalid characters accepted in SOP protocl number field")

	public void ASST024_B(String SNum) throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST024_B-Verify the invalid characters accepted in SOP protocl number field");

		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		assetDetailsPage.enter_SOP_InvalidData(SNum);

		String ExpAlrtMsg = "SOP Protocol Number accepts alpha numeric and special characters like space,-,_ ,.,?,slash (forward and backward).";
		String ActAlertMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: SOP protocl number field accepting invalid values");
		sa.assertAll();
	}

	// ASST024_C Verify the max character length for SOP protocol number field
	@Test(groups = {
			"Regression" }, description = "ASST024_C-Verify the max character length for SOP protocol number field")
	public void ASST024_C() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST024_C-Verify the max character length for SOP protocol number field");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		Thread.sleep(500);
		// 51 Characters has written to test
		String maxvalueEntered = "1234567890123456789012345678901234567890123456789012";
		assetDetailsPage.Enter_SOPNum(maxvalueEntered);
		String actualvaluAllowed = assetDetailsPage.GetSOPNumText();

		sa.assertEquals(actualvaluAllowed.length(), 50, "FAIL: SOP Protocol field  accepting more than  50 characters");
		sa.assertAll();
	}

	// ASST024.2STP-Verify the filed level validations for Run number field in
	// Initiate qualification pop-up-Valid value
	@Test(dataProvider = "ASST024_D", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "ASST024_D-Verify the filed level validations for Run number field in Initiate qualification pop-up-Valid value")

	public void ASST024_D(String RNum) throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST024_D-Verify the filed level validations for Run number field in Initiate qualification pop-up-Valid value");

		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		SelectBaseStationPage = assetDetailsPage.enter_RUN_number(RNum);
		sa.assertEquals(SelectBaseStationPage.SelectBaseStationTitle_state(), true,
				"Fail:application did not land to SelectBaseStation Page");
		sa.assertAll();
	}

	// ASST024_E-Verify the filed level validations for Run number field in Initiate
	// qualification pop-up-Valid value

	@Test(dataProvider = "ASST024_E", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "ASST024_E-Verify the filed level validations for Run number field in Initiate qualification pop-up-Valid value")

	public void ASST024_E(String RNum) throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST024_E-Verify the filed level validations for Run number field in Initiate qualification pop-up-Valid value");

		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		assetDetailsPage.RUN_InvalidData(RNum);

		String ExpAlrtMsg = "Run Number accepts only numeric values.";
		String ActAlertMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Run Number accepting Invalid values.");
		sa.assertAll();
	}
	// ASST024_F_Verify Max 4 characters should be allowed for Run number field

	@Test(groups = {
			"Regression" }, description = "ASST024_F-Verify Max 4 characters should be allowed for Run number field")
	public void ASST024_F() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST024_F-Verify Max 4 characters should be allowed for Run number field");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		Thread.sleep(500);
		String Invalidvalue = "99999"; // 5 chars has written
		// System.out.println("count of RUN NUM to be entered: " +
		// Invalidvalue.length());
		assetDetailsPage.Enter_RunNumber(Invalidvalue);
		String actualvaluAllowed = assetDetailsPage.GetRunNumText();
		// System.out.println("count of RUN NUM allowed : " +
		// actualvaluAllowed.length());

		sa.assertEquals(actualvaluAllowed.length(), 4, "FAIL: Run Number Text accepting more than 4 characters");
		sa.assertAll();
	}

	// ASST024STP-Verify Clicking on Initiate Qualification the user should start
	// Qualification Procedure

	@Test(groups = {
			"Regression" }, description = "ASST024STP-Verify Clicking on Initiate Qualification the user should start Qualification Procedure")
	public void ASST024STP() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST024STP-Verify Clicking on Initiate Qualification the user should start Qualification Procedure");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		sa.assertEquals(assetDetailsPage.IsSOPNumberField_Presence(), true, "Fail: SOP Field is not displayed");
		sa.assertEquals(assetDetailsPage.IsRunNumberField_Presence(), true, "Fail: Run Field is not displayed");
		SelectBaseStationPage = assetDetailsPage.Enter_SOP("1");
		sa.assertEquals(SelectBaseStationPage.SelectBaseStationTitle_state(), true,
				"Fail: SOP did not accept valid data ");
		sa.assertAll();
	}

	// ASST025STP-Verify the on-click on the Initiate Qualification button when the
	// Qual start is defined as _Start on date and time_

	@Test(dataProvider = "ASST025STP", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "ASST025STP-Verify the on-click on the Initiate Qualification btn when the Qual start is defined as _Start on date and time_")

	public void ASST025STP(String AName, String AID, String AType, String AManufaturer, String ALocation,
			String SetupName, String SensorCount, String TempCount, String TCSensorLabel, String Qstart, String TOD)
			throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"ASST025STP-Verify the on-click on the Initiate Qualification btn when the Qual start is defined as _Start on date and time_");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();

		assetCreationPage.assetCreation(AName, AID, AType, AManufaturer, ALocation);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile(AName);
		// Define Setup
//Define Setup
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetupName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorCount);
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

		int Yr = Integer.parseInt(TOD.split(":")[0]);
		int Mn = Integer.parseInt(TOD.split(":")[1]);
		int Dt = Integer.parseInt(TOD.split(":")[2]);
		int Hr = Integer.parseInt(TOD.split(":")[3]);
		int Mnt = Integer.parseInt(TOD.split(":")[4]);
		int Se = Integer.parseInt(TOD.split(":")[5]);
		Setup_QualParamPage.select_QualStart_condition(Qstart, Yr, Mn, Dt, Hr, Mnt, Se);// Select TOD Qual Start

		String D = Setup_QualParamPage.fetch_Date();
		String H = Setup_QualParamPage.fetch_Hour(); // parameter // yr/Mnth/Dt/Hr/Mnt/Sec,
		String M = Setup_QualParamPage.fetch_Min();
		String S = Setup_QualParamPage.fetch_Sec();

		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();

		Setup_ReviewPage.click_Save_Btn("Manual", "Yes", "1", getPW("adminFull"));

		Setup_ReviewPage.click_Yes_TODAlertMsg_Btn("Yes");
		// Thread.sleep(1000);
		assetDetailsPage = Setup_ReviewPage.click_backBtn();
		assetDetailsPage.Click_SetupName(SetupName);
		assetDetailsPage.click_InitiateQualBtn();

		assetDetailsPage.SOP_Next("1");
		sa.assertEquals(assetDetailsPage.isTimeOfTheDay_alertbox_visible(), true,
				"Fail: time of the day is not visible");
		Thread.sleep(2000);

		String Date_TOD = assetDetailsPage.fetch_Date_TOD();
		String Hour_TOD = assetDetailsPage.fetch_Hour_TOD();
		String Min_TOD = assetDetailsPage.fetch_Min_TOD();
		String Sec_TOD = assetDetailsPage.fetch_Sec_TOD();

		sa.assertEquals(Date_TOD, D, "Fail: Expected hour and entered hour is not matched");
		sa.assertEquals(Hour_TOD, H, "Fail: Expected minute and entered mintute  is not displayed");
		sa.assertEquals(Min_TOD, M, "Fail: Expected second and entered second  is not displayed");
		sa.assertEquals(Sec_TOD, S, "Fail: Expected second and entered second  is not displayed");

		sa.assertAll();
	}

//ASST026STP-Verify on-click functionality of Edit icon for a setup
	@Test(dataProvider = "ASST026STP", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "ASST026STP-Verify on-click functionality of Edit icon for a setup")

	public void ASST026STP(String SetupName, String SensorCount, String SOP, String LoadDescription, String Comments,
			String TempCount, String TCSensorLabel)
			throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("ASST026STP-Verify on-click functionality of Edit icon for a setup");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetupName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorCount);
		defineSetupPage.click_defineSetupPage_SOPField();
		defineSetupPage.clear_defineSetupPage_SOP();
		defineSetupPage.enter_defineSetupPage_SOP(SOP);
		defineSetupPage.click_defineSetupPage_LoadDescField();
		defineSetupPage.clear_defineSetupPage_LoadDesc();
		defineSetupPage.enter_defineSetupPage_LoadDesc(LoadDescription);
		defineSetupPage.click_defineSetupPage_commentsField();
		defineSetupPage.clear_defineSetupPage_comments();
		defineSetupPage.enter_defineSetupPage_comments(Comments);
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

		// Thread.sleep(1000);
		assetDetailsPage = Setup_ReviewPage.click_backBtn();
		assetDetailsPage.Click_SetupName(SetupName);
		defineSetupPage = assetDetailsPage.editStupBtn_Position_0();

		sa.assertEquals(defineSetupPage.defineSetupPage_state(), true, "Fail: Define setup  Page is not displayed");
		Thread.sleep(1000);
		String DisplayedSetupName = defineSetupPage.get_setupName_txtData();
		String ExpectedSetupName = SetupName;

		sa.assertEquals(DisplayedSetupName, ExpectedSetupName, "Fail: Expected set up name  is not displayed");

		String DisplayedSensorcount = defineSetupPage.get_Sensorcount_text();
		String ExpectedSensorcount = SensorCount;

		sa.assertEquals(DisplayedSensorcount, ExpectedSensorcount, "Fail: Expected Sensor count  is not displayed");

		String DisplayedSOPNumber = defineSetupPage.Fetch_sop_text();
		String ExpectedSOPnumber = SOP;
		sa.assertEquals(DisplayedSOPNumber, ExpectedSOPnumber, "Fail: Expected SOP number  is not displayed");

		String DisplayedLoadDesc = defineSetupPage.getLoadDesc_txt();
		String ExpectedLoadDesc = LoadDescription;
		sa.assertEquals(DisplayedLoadDesc, ExpectedLoadDesc, "Fail: Expected Load Description  is not displayed");

		String DisplayedCommentsTxt = defineSetupPage.get_defineSetupPage_comments_txtData();
		String ExpectedCommentsTxt = Comments;
		sa.assertEquals(DisplayedCommentsTxt, ExpectedCommentsTxt, "Fail: Expected Cooment text  is not displayed");
		sa.assertAll();

	}

	// ASST027STP-Verify the edit setup functionality
	// ASST028STP-Verify if Audit trial record exists for edit setup

	@Test(groups = {
			"Regression" }, dataProvider = "ASST027STP", dataProviderClass = assetCreationUtility.class, description = "Verify the edit setup functionality")

	public void ASST027(String SetupName, String SensorCount, String SOP, String LoadDescription, String Comments,
			String TempCount, String TCSensorLabel)
			throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("ASST027STP,ASST028STP-Verify the edit setup functionality"
				+ "ASST028STP-Verify if Audit trial record exists for edit setup");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName(SetupName);

//Define Setup
		defineSetupPage = assetDetailsPage.editStupBtn_Position_0();

		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorCount);
		defineSetupPage.click_defineSetupPage_SOPField();
		defineSetupPage.clear_defineSetupPage_SOP();
		defineSetupPage.enter_defineSetupPage_SOP(SOP);
		defineSetupPage.click_defineSetupPage_LoadDescField();
		defineSetupPage.clear_defineSetupPage_LoadDesc();
		defineSetupPage.enter_defineSetupPage_LoadDesc(LoadDescription);
		defineSetupPage.click_defineSetupPage_commentsField();
		defineSetupPage.clear_defineSetupPage_comments();
		defineSetupPage.enter_defineSetupPage_comments(Comments);
		Setup_SensorConfigPage = defineSetupPage.click_Nxt_WithalertYes();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(TempCount);
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To(TempCount);
		Setup_SensorConfigPage.Enter_SensorLabel(TCSensorLabel);
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_withAlert();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_NxtBtn();

		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();

		Setup_ReviewPage.click_Save_Btn("Manual", "Yes", "1", getPW("adminFull"));
		assetDetailsPage = Setup_ReviewPage.click_backBtn();

		assetDetailsPage.Click_SetupName(SetupName);
		defineSetupPage = assetDetailsPage.editStupBtn_Position_0();
		String DisplayedSetupName = defineSetupPage.get_setupName_txtData();
		String ExpectedSetupName = SetupName;

		sa.assertEquals(DisplayedSetupName, ExpectedSetupName, "Fail: Expected set up name  is not displayed");

		String DisplayedSensorcount = defineSetupPage.get_Sensorcount_text();
		String ExpectedSensorcount = SensorCount;

		sa.assertEquals(DisplayedSensorcount, ExpectedSensorcount, "Fail: Expected Sensor count  is not displayed");

		String DisplayedSOPNumber = defineSetupPage.Fetch_sop_text();
		String ExpectedSOPnumber = SOP;
		sa.assertEquals(DisplayedSOPNumber, ExpectedSOPnumber, "Fail: Expected SOP number  is not displayed");

		String DisplayedLoadDesc = defineSetupPage.getLoadDesc_txt();
		String ExpectedLoadDesc = LoadDescription;
		sa.assertEquals(DisplayedLoadDesc, ExpectedLoadDesc, "Fail: Expected Load Description  is not displayed");

		String DisplayedCommentsTxt = defineSetupPage.get_defineSetupPage_comments_txtData();
		String ExpectedCommentsTxt = Comments;
		sa.assertEquals(DisplayedCommentsTxt, ExpectedCommentsTxt, "Fail: Expected Comment text  is not displayed");
		sa.assertAll();

		assetDetailsPage = defineSetupPage.defineSetupPage_backBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		System.out.println(Actionmsg);
		String ExpectMSG = "Setup: \"ASST026STP\" is modified in Tab : \"Define Setup & Sensors Configuration & Group Sensors & Qualification Parameters\" by User ID : \"1\" , User Name: \"User1\"";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record doess not exists for edit setup ");
		sa.assertAll();

	}

	// ASST029WO-Verify the on-click functionality of the wiring icon for a setup

	@Test(groups = {
			"Regression" }, description = "ASST029WO-Verify the on-click functionality of the wiring icon for a setup")
	public void ASST029() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST029WO-Verify the on-click functionality of the wiring icon for a setup");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		OverlayWiringImagePage = assetDetailsPage.Click_WiringImgButton();
		Thread.sleep(1000);
		sa.assertEquals(OverlayWiringImagePage.IsOverlayWiringPageTitle_state(), true,
				"Fail: application Landed to Incorrect page");
		sa.assertAll();
	}

	// ASST030WO-Verify the details displayed in Wiring overlay screen for a setup
	@Test(groups = {
			"Regression" }, description = "ASST030WO-Verify the details displayed in Wiring overlay screen for a setup")
	public void ASST030() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST030WO-Verify the details displayed in Wiring overlay screen for a setup");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		OverlayWiringImagePage = assetDetailsPage.Click_WiringImgButton();
		Thread.sleep(3000);

		sa.assertEquals(OverlayWiringImagePage.IsOverlayWiringPageTitle_state(), true,
				"Fail:Overlay Wiring Page Title is not Visible ");
		sa.assertEquals(OverlayWiringImagePage.PrintButton_State(), true, "Fail: Print_Button is not Visible");
		sa.assertEquals(OverlayWiringImagePage.Close_button_State(), true, "Fail: Close_button is not Visible");
		sa.assertEquals(OverlayWiringImagePage.Group_Visible(), true,
				"Fail: Groups that were created during setup is not Visible");

		assetDetailsPage = OverlayWiringImagePage.click_OverlayImage_Close_button();
		sa.assertEquals(assetDetailsPage.assetDetail_PageTitle(), "HeatBath - SyncInAsset",
				"FAIL:Incorrect AssetDetails Page title or landed into incorrect Page");
		sa.assertAll();

	}

	// ASST032WO-Verify the on-click functionality of the print icon in the wiring
	// overlay screen for a setup
	@Test(groups = {
			"Regression" }, description = "ASST032WO-Verify the on-click functionality of the print icon in the wiring overlay screen for a setup")
	public void ASST032() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST032WO-Verify the on-click functionality of the print icon in the wiring overlay screen for a setup");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		OverlayWiringImagePage = assetDetailsPage.Click_WiringImgButton();
		OverlayWiringImagePage.Click_PrintIcon();
		sa.assertEquals(OverlayWiringImagePage.GroupWiring_Report_State(), true,
				"Fail:GroupWiring_Report is not Visible ");
		sa.assertEquals(OverlayWiringImagePage.All_GroupOverlayReport_State(), true,
				"Fail: All_GroupOverlay_Report is not Visible");
		sa.assertAll();
	}

	// ASST033WO-Verify the on-click functionality of the _Group Overlay Report_ btn
	// in the wiring overlay screen for a setup
	@Test(groups = {
			"Regression" }, description = "ASST033WO-Verify the on-click functionality of the _Group Overlay Report_ btn in the wiring overlay screen for a setup")
	public void ASST033() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST033WO-Verify the on-click functionality of the _Group Overlay Report_ btn in the wiring overlay screen for a setup");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		OverlayWiringImagePage = assetDetailsPage.Click_WiringImgButton();
		String downloadPath1 = System.getProperty("user.home")
				+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\WiringLayOut.pdf";
		File f1 = new File(downloadPath1);
		f1.delete();
		OverlayWiringImagePage.click_GroupOverlayRprtGenerate_Popup();
		Thread.sleep(3000);
		String downloadPath = System.getProperty("user.home")
				+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\WiringLayOut.pdf";
		File f = new File(downloadPath);
		if (f.exists()) {
			sa.assertEquals(f.getName(), "WiringLayOut.pdf", " File is not available");
			sa.assertAll();
		} else {
			sa.assertEquals(f.getName(), "WiringLayOut.pdf", " File is not available");
			sa.assertAll();
			System.out.println("fail to find the File");
		}

	}

	// ASST034WO-Verify the on-click functionality of the _All Group Overlay Report_
	// btn in the wiring overlay screen for a setup
	@Test(groups = {
			"Regression" }, description = "ASST034WO-Verify the on-click functionality of the _All Group Overlay Report_ btn in the wiring overlay screen for a setup")
	public void ASST034() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST034WO-Verify the on-click functionality of the _All Group Overlay Report_ btn in the wiring overlay screen for a setup");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		OverlayWiringImagePage = assetDetailsPage.Click_WiringImgButton();
		String downloadPath1 = System.getProperty("user.home")
				+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\GroupWiringLayOut.pdf";
		File f1 = new File(downloadPath1);
		f1.delete();
		OverlayWiringImagePage.click_GroupOverlayRprtGenerate_Popup();
		Thread.sleep(3000);
		String downloadPath = System.getProperty("user.home")
				+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\GroupWiringLayOut.pdf";
		File f = new File(downloadPath);
		if (f.exists()) {
			sa.assertEquals(f.getName(), "GroupWiringLayOut.pdf", " File is not available");
			sa.assertAll();
			// System.out.println("success");
		} else {
			sa.assertEquals(f.getName(), "GroupWiringLayOut.pdf", " File is not available");
			sa.assertAll();
			System.out.println("fail to find the File");
		}

	}
	// ASST035WO-Verify the details displayed in Wiring overlay reports
	// This ASST035WO will test manually as its related to PDF content

	// ASST036WO-Verify user is unable to generate the wiring overlay report from
	// Asset details screen when there is no report generation privilege given
	// This test cases is not implemented yet and not valid for build 1.1.0.8

	// ASST037-Verify the on-click functionality of the print icon for a setup
	// ASST039STP-Verify the Audit trail for print Setup report activity (This Test
	// case is covering under ASST037 )

	@Test(groups = {
			"Regression" }, dataProvider = "ASST037", dataProviderClass = assetCreationUtility.class, description = "ASST037,ASST039STP-Verify the on-click functionality of the print icon for a setup")

	public void ASST037(String AName, String AID, String AType, String AManufaturer, String ALocation, String SetupName,
			String SensorCount, String SOP, String TempCount, String TCSensorLabel)
			throws InterruptedException, IOException, AWTException {
		extentTest = extent
				.startTest("ASST037,ASST039STP-Verify the on-click functionality of the print icon for a setup");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();

		assetCreationPage.assetCreation(AName, AID, AType, AManufaturer, ALocation);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile(AName);
		// Define Setup
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetupName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorCount);
		defineSetupPage.click_defineSetupPage_SOPField();
		defineSetupPage.clear_defineSetupPage_SOP();
		defineSetupPage.enter_defineSetupPage_SOP(SOP);
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

		// Thread.sleep(1000);
		assetDetailsPage = Setup_ReviewPage.click_backBtn();
		assetDetailsPage.Click_SetupName(SetupName);
		assetDetailsPage.Click_Print_Button();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(4000);
		assetDetailsPage.click_PDFpopup_OkBtn();
		assetDetailsPage.perform_alt_tab_OP();
		Thread.sleep(2000);
		assetDetailsPage.Click_reportsTile();
		// assetDetailsPage.Click_SetupReportsButton();
		String Rname = assetDetailsPage.Get_ReportFile_Name();

		sa.assertEquals(assetDetailsPage.setupReport_Count(), 1,
				"FAIL:Reports tile count displayed <0 under Asset details page");
		sa.assertEquals(Rname, SetupName, "FAIL:Report Name and setup name is not matching");

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_auditEvent_text();

		String ExpectMSG = "Asset Details:  Setup report creation performed by User ID : \"1\", User Name : \"User1\"";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Search results is not available for print Setup report activity");
		sa.assertAll();

	}

	// ASST038-Verify if user is unable to generate the setup report when there is
	// no privilege given

	@Test(groups = {
			"Regression" }, description = "ASST038-Verify if user is unable to generate the setup report when there is no privilege given")
	public void ASST038() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST038-Verify if user is unable to generate the setup report when there is no privilege given");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_Print_Button();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String actualmsg = assetDetailsPage.AlertMsg();
		String Expectmsg = "User do not have permission to perform this operation";
		sa.assertEquals(actualmsg, Expectmsg,
				"FAIL: User is able to generate the setup report when there is no privilege given");
		sa.assertAll();
	}

	// ASST039STP-Verify the Audit trail for print Setup report activity (This Test
	// case is covering under ASST037 )

	// ASST040-Verify the on-click of delete icon for a setup
	// This test cases is handled under assetDetailsTest2 class

	// ASST041-Verify if user is not able to delete the setup when there are no
	// privileges given
	// ASST041 test cases is similar to ADMN079 which is handling in UM4 class.

	// ASST042STP-Verify if Audit trial record exists for delete setup
	// This test cases is handled under assetDetailsTest2 class

	// ASST043-Verify the details displayed under Qualifications tile

	@Test(groups = { "Regression" }, description = "ASST043-Verify the details displayed under Qualifications tile")
	public void ASST043() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST043-Verify the details displayed under Qualifications tile");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		Thread.sleep(1000);
		sa.assertEquals(assetDetailsPage.qualTile_Header_Text(), true, "FAIL:  qualTile Header Btn is not Present");
		// List of Qualification studies
		sa.assertEquals(assetDetailsPage.QualCount(), 1, "FAIL:  qualTile  count is < 1");

		sa.assertEquals(assetDetailsPage.qual_StudyFilename_text(), "manual 1 min sampling",
				"FAIL: Qual Study File name is not Present");
		sa.assertEquals(assetDetailsPage.get_DateUnder_Qual(), "18-Mar-2020 13:12:41",
				"FAIL: Date under Qual Study File name is not displaying");
		sa.assertEquals(assetDetailsPage.get_QualStudyFile_Runnumberfield_(), "1",
				"FAIL: Run Number# under Qual Study File name is not available ");
		sa.assertEquals(assetDetailsPage.qual_StudyFile_Comments_txt(), "1 min sampling with lethality",
				"FAIL: Comments under Qual Study File name is not displaying");
		sa.assertEquals(assetDetailsPage.Qual_DeleteBtn_state(), true, "FAIL: Qual Delete Button is not Present");
		sa.assertEquals(assetDetailsPage.IsGenerateReportsBtn_Visible(), true,
				"FAIL: Generate Reports Btn is not Present");

		sa.assertEquals(assetDetailsPage.CopyQualToDrive_State(), true, "FAIL: Copy Qual to drive Btn is not Present");
		sa.assertAll();
	}

	// ASST044-Verify -Copy to drive- functionality of a Qualification study file
	// for local drive
	// This ASST044 Test Case is merged with ASST046

	// ASST045-Verify -Copy to drive- functionality of a Qualification study file
	// for USB drive
	// (Because of Hardware dependency this will be tested manually )

	// ASST046-Verify if Audit trial record exists for Copy of a study file

	// This Test Case is executed under ASST044
	@Test(groups = {
			"Regression" }, description = "ASST044,ASST046-Verify -Copy to drive- functionality of a Qualification study file for local drive"
					+ "Verify if Audit trial record exists for Copy of a study file")
	public void ASST044() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest(
				"ASST044,ASST046-Verify -Copy to drive- functionality of a Qualification study file for local drive"
						+ ",Verify if Audit trial record exists for Copy of a study file");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");

		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		assetDetailsPage.selectFolder_CopyToDrive("AutoLogs", "qual");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String foldrpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
		String ExpAlrtMsg = "manual 1 min sampling has been copied successfully to " + foldrpath;
		String ActAlrtMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Copy to drive operation failed for Qual");
		// Confirm if the file got copied to the above destination folder or not
		List<String> fn = tu.get_fileNamesList(foldrpath);
		String expFileName = "20200318131241_20200318155600_60_637201438503438416.rtq";
		for (String filename : fn) {
			if (filename.contains(expFileName)) {
				sa.assertEquals(filename, expFileName, "FAIL: Incorrect file is copied or "
						+ "not all copeid during Copy to drive operation for Qual");
				sa.assertAll();
			}
		}
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		System.out.println(Actionmsg);
		String ExpectMSG = "User ID : \"1\" ,  User Name: \"User1\" logged in to do  \"CopyFilesReports\" operation in \"Asset Details \" screen";
		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL:Audit trial record does not exists for Copy of a qual study file ");
		sa.assertAll();
	}

	// ASST047-Verify the display of Generate Reports button in Qualifications tile

	@Test(groups = {
			"Regression" }, description = "ASST047-Verify the display of Generate Reports button in Qualifications tile")
	public void ASST047() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST047-Verify the display of Generate Reports button in Qualifications tile");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		sa.assertEquals(assetDetailsPage.IsGenerateReportsBtn_Enable(), false,
				"FAIL: Generate Reports Btn is not in disable mode");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		sa.assertEquals(assetDetailsPage.IsGenerateReportsBtn_Enable(), true,
				"FAIL: Generate Reports Btn is not Present");
		sa.assertAll();
	}

//ASST048-Verify the on-click functionality of generate reports button in Qualification tile

	@Test(groups = {
			"Regression" }, description = "'ASST048-Verify the on-click functionality of generate reports button in Qualification tile")
	public void ASST048() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"'ASST048-Verify the on-click functionality of generate reports button in Qualification tile");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");

		RWFileSelctionPage = assetDetailsPage.Click_GenerateReportsBtn_RWpage();
		// Current Asset name in which the study file exists
		sa.assertEquals(RWFileSelctionPage.assetDetailTitle_Visible(), true,
				"FAIL:Current Asset name in which the study file exists is not visible ");

		// Current Asset type in which the study file exists
		sa.assertEquals(RWFileSelctionPage.assetType_Visible(), true,
				"FAIL: Current Asset type in which the study file exists is not Visible ");

		// Current Asset ID in which the study file exists

		sa.assertEquals(RWFileSelctionPage.assetID_Visible(), true,
				"FAIL: Current Asset ID in which the study file exists is not Visible ");

		// Current Manufacturer of the asset in which the study file exists
		sa.assertEquals(RWFileSelctionPage.assetManufacturer_Visible(), true,
				"FAIL: Current Manufacturer  in which the study file exists is not Visible ");
		sa.assertAll();
	}

	// ASST049-Verify the on-click of delete icon for a Qualification study file
	// This Test case is handled in assetDetailsTest2 class

	// ASST050-Verify if user is not able to delete the study file when there are no
	// privileges given
	@Test(groups = {
			"Regression" }, description = "ASST050-Verify if user is not able to delete the study file when there are no privileges given")
	public void ASST050() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST050-Verify if user is not able to delete the study file when there are no privileges given");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		assetDetailsPage.click_DeleteQualificationButton();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String actualmsg = assetDetailsPage.AlertMsg();
		String Expectmsg = "User do not have permission to perform this operation";
		sa.assertEquals(actualmsg, Expectmsg, "FAIL: User is able to delete the selected studyfile");
		sa.assertAll();

	}
	// ASST051-Verify if Audit trial record exists for Delete study file activity
	// This Test case is handled in assetDetailsTest2 class and this test case is
	// covered under ASST049 test case

	// ASST052.1REP-Verify the details displayed under reports tile- sub tab-Setup

	@Test(groups = {
			"Regression" }, description = "ASST052.1REP-Verify the details displayed under reports tile- sub tab-Setup")
	public void ASST052A() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST052.1REP-Verify the details displayed under reports tile- sub tab-Setup");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min sampling");

		sa.assertEquals(assetDetailsPage.ReportsHeaderText_state(), true, "FAIL: Report Heaser  is not Present");
		sa.assertEquals(assetDetailsPage.Get_ReportFile_Name(), "manual 1 min sampling",
				"FAIL: Report Heaser  is not Present");
		sa.assertEquals(assetDetailsPage.setupReport_Count(), 1, "FAIL: Report Header  count has not fetched");
		sa.assertEquals(assetDetailsPage.get_Date_SetupReport(), "19-Mar-2020 13:52:45",
				"FAIL: Date under Qual Study File name is not displaying");

		sa.assertEquals(assetDetailsPage.ReportView_Btn_State(), true, "FAIL: print Button is not Present");
		sa.assertEquals(assetDetailsPage.DeleteBtn_state(), true, "FAIL: Report Delete Button is not Present");
		sa.assertAll();
	}

	// ASST052REP-Verify the details displayed under reports tile
	@Test(groups = { "Regression" }, description = "ASST052REP-Verify the details displayed under reports tile")
	public void ASST052B() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST052REP-Verify the details displayed under reports tile");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		sa.assertEquals(assetDetailsPage.ReportsTile_Count_state(), true,
				"FAIL: Repoert tile count is not displayng under report tile");

		sa.assertEquals(assetDetailsPage.CopyToDrive_Btn_state(), true, "FAIL: Copy To Drive button is not Present");

		sa.assertEquals(assetDetailsPage.SetupReportsButton_State(), true, "FAIL: setup Report button is not Present");
		sa.assertEquals(assetDetailsPage.QualReportsButton_State(), true, "FAIL: qual Report button is not Present");
		sa.assertEquals(assetDetailsPage.PassFailReportButton_State(), true,
				"FAIL: passfail Report button is not Present");
		sa.assertAll();
	}

	// ASST053REP-Verify -Copy to drive- functionality of a Setup Report for local
	// drive
	// ASST055REP-Verify if Audit trial record exists for Copy of a setup report

	@Test(groups = { "Regression" }, description = "ASST053REP-Verify -Copy to drive- "
			+ "functionality of a Setup Report for local drive")
	public void ASST053A() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest(
				"ASST053REP,ASST055REP-Verify -Copy to drive- functionality of a Setup Report for local drive"
						+ "Verify if Audit trial record exists for Copy of a setup report");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min sampling");
		assetDetailsPage.selectFolder_CopyToDrive("AutoLogs", "reports");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String foldrpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
		// System.out.println("Act Folderpath: "+filepath);
		String ExpAlrtMsg = "manual 1 min sampling has been copied successfully to " + foldrpath;
		// System.out.println("Exp Folderpath: "+ExpAlrtMsg);
		String ActAlrtMsg = tu.get_AlertMsg_text();
		// System.out.println("Act alert msg: "+ActAlrtMsg);

		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Copy to drive operation failed for report");

		// Confirm if the file got copied to the above destination folder or not
		List<String> fn = tu.get_fileNamesList(foldrpath);
		String expFileName = "Se=(manual 1 min sampling)=()=0=19-Mar-2020 13-52-45=.pdf";
		for (String filename : fn) {
			if (filename.contains(expFileName)) {
				sa.assertEquals(filename, expFileName, "FAIL: Incorrect file is copied or "
						+ "not all copeid during Copy to drive operation for report");
				sa.assertAll();
				break;
			}

		}

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "User ID : \"1\" ,  User Name: \"User1\" logged in to do  \"CopyFilesReports\" operation in \"Asset Details \" screen";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for Copy of a Detailed report");
		sa.assertAll();

	}

	// ASST053_1REP-Verify the on-click functionality of PDF icon for Detailed
	// report under Reports tile-Qualifications sub tab
	@Test(groups = { "Regression" }, description = "ASST053_1REP-Verify the on-click "
			+ "functionality of PDF icon for Detailed report under Reports tile-Qualifications sub tab")
	public void ASST053B() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest("ASST053_1REP-Verify the on-click functionality of PDF icon "
				+ "for Detailed report under Reports tile-Qualifications sub tab");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");

		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualReportsButton();
		assetDetailsPage.Select_DetailedReport();

		String downloadPath1 = System.getProperty("user.home")
				+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\D=(manual 1 min samplin)=1=17-Jul-2020 16-45-25=.pdf";
		File f1 = new File(downloadPath1);
		f1.delete();

		assetDetailsPage.click_printBtn_Report();
		Thread.sleep(2000);
		assetDetailsPage.click_PDFpopup_OkBtn();
		Thread.sleep(3000);
		String downloadPath = System.getProperty("user.home")
				+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\D=(manual 1 min samplin)=1=17-Jul-2020 16-45-25=.pdf";
		File f = new File(downloadPath);
		if (f.exists()) {
			sa.assertEquals(f.getName(), "D=(manual 1 min samplin)=1=17-Jul-2020 16-45-25=.pdf",
					" File is not available");
			sa.assertAll();
			// System.out.println("success");
		} else {
			sa.assertEquals(f.getName(), "D=(manual 1 min samplin)=1=17-Jul-2020 16-45-25=.pdf",
					" File is not available");
			sa.assertAll();
			// System.out.println("fail to find the File");
		}

	}

	// ASST054REP-Verify -Copy to drive- functionality of a Setup Report for USB
	// drive
	// This test cases will handle manually because of hardware dependency

	// ASST054A_REP- Verify the on-click functionality of PDF icon for Summary
	// report
	// under Reports tile-Qualifications sub tab

	@Test(groups = {
			"Regression" }, description = "ASST054A_REP- Verify the on-click functionality of PDF icon for Summary report under Reports tile-Qualifications sub tab")
	public void ASST054A_REP() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST054A_REP- Verify the on-click functionality of PDF icon for Summary report under Reports tile-Qualifications sub tab");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualReportsButton();
		assetDetailsPage.Select_SummaryReport();
		assetDetailsPage.click_printBtn_Report();
		Thread.sleep(4000);
		assetDetailsPage.click_PDFpopup_OkBtn();
		Thread.sleep(3000);
		String downloadPath = System.getProperty("user.home")
				+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\S=(manual 1 min samplin)=1=09-Jun-2020 15-27-34=.pdf";
		File f = new File(downloadPath);
		if (f.exists()) {
			sa.assertEquals(f.getName(), "S=(manual 1 min samplin)=1=09-Jun-2020 15-27-34=.pdf",
					" File is not available");
			sa.assertAll();
			// System.out.println("success");
		} else {
			sa.assertEquals(f.getName(), "S=(manual 1 min samplin)=1=09-Jun-2020 15-27-34=.pdf",
					" File has not downloded");
			sa.assertAll();
			// System.out.println("fail to find the File");
		}

	}
	// ASST055REP-Verify if Audit trial record exists for Copy of a setup report
	// ASST055REP is handling under ASST053REP

	// ASST056REP-Verify the on-click functionality of PDF icon under Reports
	// tile-Setups sub tab

	@Test(groups = {
			"Regression" }, description = "ASST056REP-Verify the on-click functionality of PDF icon under Reports tile-Setups sub tab")
	public void ASST056() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST056REP-Verify the on-click functionality of PDF icon under Reports tile-Setups sub tab");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min sampling");
		String downloadPath1 = System.getProperty("user.home")
				+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\D=(manual 1 min samplin)=1=17-Jul-2020 16-45-25=.pdf";
		File f1 = new File(downloadPath1);
		f1.delete();

		assetDetailsPage.click_printBtn_Report();
		Thread.sleep(1000);
		assetDetailsPage.click_PDFpopup_OkBtn();
		Thread.sleep(3000);
		String downloadPath = System.getProperty("user.home")
				+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\Se=(manual 1 min sampling)=()=0=10-Aug-2020 20-57-33=.pdf";
		File f = new File(downloadPath);
		if (f.exists()) {
			sa.assertEquals(f.getName(), "Se=(manual 1 min sampling)=()=0=10-Aug-2020 20-57-33=.pdf",
					" File is not available");
			sa.assertAll();
			// System.out.println("success");
		} else {
			sa.assertEquals(f.getName(), "Se=(manual 1 min sampling)=()=0=10-Aug-2020 20-57-33=.pdf",
					" File has not downloded");
			sa.assertAll();
			// System.out.println("fail to find the File");
		}

	}

//ASST059REP-Verify -Copy to drive- functionality of a Detailed Report for local drive
// ASST061REP-Verify if Audit trial record exists for Copy of a Detailed report (This test cases is executing under ASST059REP)

	@Test(groups = {
			"Regression" }, description = "ASST059REP,ASST061REP-Verify -Copy to drive- functionality of a Detailed Report for local drive")
	public void ASST059() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST059REP,ASST061REP-Verify -Copy to drive- functionality of a Detailed Report for local drive");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min samplin");
		assetDetailsPage.selectFolder_CopyToDrive("AutoLogs", "reports");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String foldrpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
		// System.out.println("Act Folderpath: "+filepath);
		String ExpAlrtMsg = "manual 1 min samplin has been copied successfully to " + foldrpath;
		// System.out.println("Exp Folderpath: "+ExpAlrtMsg);
		String ActAlrtMsg = tu.get_AlertMsg_text();
		// System.out.println("Act alert msg: "+ActAlrtMsg);

		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Copy to drive operation failed for report");

		// Confirm if the file got copied to the above destination folder or not
		List<String> fn = tu.get_fileNamesList(foldrpath);
		String expFileName = "1065306A4C9C5E7376FC.cfg";
		for (String filename : fn) {
			if (filename.contains(expFileName)) {
				sa.assertEquals(filename, expFileName, "FAIL: Incorrect file is copied or "
						+ "not all copeid during Copy to drive operation for report");
				break;
			}
		}
		sa.assertAll();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "User ID : \"1\" ,  User Name: \"User1\" logged in to do  \"CopyFilesReports\" operation in \"Asset Details \" screen";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for Copy of a Detailed report");
		sa.assertAll();

	}

	// ASST062REP-Verify -Copy to drive- functionality of a Summary Report for local
	// drive
	// ASST064REP-Verify if Audit trial record exists for Copy of a Summary
	// report(This Test Case Executed under )
	@Test(groups = {
			"Regression" }, description = "ASST062REP-Verify -Copy to drive- functionality of a Summary Report for local")
	public void ASST062() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest("ASST062REP-Verify -Copy to drive- functionality of a Summary Report for local");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min samplin");
		assetDetailsPage.selectFolder_CopyToDrive("AutoLogs", "reports");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String foldrpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
		// System.out.println("Act Folderpath: "+filepath);
		String ExpAlrtMsg = "manual 1 min samplin has been copied successfully to " + foldrpath;
		// System.out.println("Exp Folderpath: "+ExpAlrtMsg);
		String ActAlrtMsg = tu.get_AlertMsg_text();
		// System.out.println("Act alert msg: "+ActAlrtMsg);

		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Copy to drive operation failed for report");

		// Confirm if the file got copied to the above destination folder or not
		List<String> fn = tu.get_fileNamesList(foldrpath);
		String expFileName = "1065306A4C9C5E7376FC.cfg";
		for (String filename : fn) {
			if (filename.contains(expFileName)) {
				sa.assertEquals(filename, expFileName, "FAIL: Incorrect file is copied or "
						+ "not all copeid during Copy to drive operation for report");
				sa.assertAll();

			}
		}
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "User ID : \"1\" ,  User Name: \"User1\" logged in to do  \"CopyFilesReports\" operation in \"Asset Details \" screen";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for Copy of a Summary report ");
		sa.assertAll();

	}

	// ASST063REP-Verify -Copy to drive- functionality of a Summary Report for USB
	// drive This TC will handle Manually due to hard drive dependency
	// ASST065REP-Verify -Copy to drive- functionality of a Pass_Fail Report for
	// local drive

	// ASST067REP-Verify if Audit trial record exists for Copy of a Pass_Fail report
	// (This audit test cases also covered under the below test script)

	@Test(groups = {
			"Regression" }, description = "ASST065REP,ASST067REP-Verify -Copy to drive- functionality of a Pass_Fail Report for local drive")
	public void ASST065() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST065REP,ASST067REP-Verify -Copy to drive- functionality of a Pass_Fail Report for local drive");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_PassFailReportBtn();
		assetDetailsPage.Select_ReportFile("manual 1 min samplin");
		assetDetailsPage.selectFolder_CopyToDrive("AutoLogs", "reports");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String foldrpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
		// System.out.println("Act Folderpath: "+filepath);
		String ExpAlrtMsg = "manual 1 min samplin has been copied successfully to " + foldrpath;
		// System.out.println("Exp Folderpath: "+ExpAlrtMsg);
		String ActAlrtMsg = tu.get_AlertMsg_text();
		// System.out.println("Act alert msg: "+ActAlrtMsg);

		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Copy to drive operation failed for reports");

		// Confirm if the file got copied to the above destination folder or not
		List<String> fn = tu.get_fileNamesList(foldrpath);
		String expFileName = "1065306A4C9C5E7376FC.cfg";
		for (String filename : fn) {
			if (filename.contains(expFileName)) {
				sa.assertEquals(filename, expFileName, "FAIL: Incorrect file is copied or "
						+ "not all copeid during Copy to drive operation for report");
				sa.assertAll();

			}
		}

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		System.out.println(Actionmsg);
		String ExpectMSG = "User ID : \"1\" ,  User Name: \"User1\" logged in to do  \"CopyFilesReports\" operation in \"Asset Details \" screen";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for Copy of a Pass_Fail report");
		sa.assertAll();
	}
////ASST066REP-Verify -Copy to drive- functionality of a Pass_Fail Report for USB drive
	// This ASST066REP TC will handle manually as its depend on hard drive

	// ASST067REP is handling under ASST065REP

	// ASST068REP-Verify the on-click functionality of PDF icon for Pass_Fail report
	// under Reports tile-Pass_Fail sub tab

	@Test(groups = {
			"Regression" }, description = "ASST068REP-Verify the on-click functionality of PDF icon for Pass_Fail report under Reports tile-Pass_Fail sub tab")
	public void ASST068() throws Exception {
		extentTest = extent.startTest(
				"ASST068REP-Verify the on-click functionality of PDF icon for Pass_Fail report under Reports tile-Pass_Fail sub tab");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_PassFailReportBtn();
		assetDetailsPage.Select_ReportFile("manual 1 min samplin");
		String downloadPath1 = System.getProperty("user.home")
				+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\D=(manual 1 min samplin)=1=17-Jul-2020 16-45-25=.pdf";
		File f1 = new File(downloadPath1);
		f1.delete();

		assetDetailsPage.click_printBtn_Report();
		Thread.sleep(2000);
		assetDetailsPage.click_PDFpopup_OkBtn();
		Thread.sleep(3000);
		String downloadPath = System.getProperty("user.home")
				+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\P=(manual 1 min samplin)=(1 min sampling with )=1=19-Mar-2020 13-51-43=.pdf";
		File f = new File(downloadPath);
		if (f.exists()) {
			sa.assertEquals(f.getName(), "P=(manual 1 min samplin)=(1 min sampling with )=1=19-Mar-2020 13-51-43=.pdf",
					" File is not available");
			sa.assertAll();
			// System.out.println("success");
		} else {
			sa.assertEquals(f.getName(), "P=(manual 1 min samplin)=(1 min sampling with )=1=19-Mar-2020 13-51-43=.pdf",
					" File is not available");
			sa.assertAll();
			System.out.println("fail to find the File");
		}
	}
	// ASST069REP,ASST070REP is handling in AssetDetailsTest-2 class

	// ASST072REP-Verify the reports are not displayed in Reports tile when the
	// files are edited unless they are re-generated
	// This ASST072REP will handel manually as its related to report
	// ASST073REP-Verify if user is not able to delete the reports when there are no
	// privileges given
	// This test case similar as ADMN081B test case which is present in UM4 class

	// ASST074-Verify the details displayed under Documents tile
	@Test(groups = { "Regression" }, description = "ASST074-Verify the details displayed under Documents tile")
	public void ASST074() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST074-Verify the details displayed under Documents tile");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.Select_DocFile("LTR-40_Cooling.pdf");
		sa.assertEquals(assetDetailsPage.DocsTileCount_state(), true,
				"FAIL: Docs Tile Count is not displaying under docs tile");

		sa.assertEquals(assetDetailsPage.UploadDocumentsButton_state(), true,
				"FAIL: Upload Documents Button is not displaying under docs tile");
		sa.assertEquals(assetDetailsPage.CopyDocumentToDrive_State(), true,
				"FAIL: Copy Document To Drive is not displaying under docs tile");
		sa.assertEquals(assetDetailsPage.ReportView_Btn_State(), true,
				"FAIL: Report View Btn  is not displaying under docs tile");
		sa.assertAll();

	}

//ASST075-Verify the on-click functionality of Upload Documents button
	@Test(groups = {
			"Regression" }, description = "ASST075-Verify the on-click functionality of Upload Documents button")
	public void ASST075() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest("ASST075-Verify the on-click functionality of Upload Documents button");
		SoftAssert sa = new SoftAssert();
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_UploadDocsBtn();
		assetDetailsPage.uploadDoc_Assetdetails("HelpFileWord.docx");
		Thread.sleep(1000);
		sa.assertEquals(assetDetailsPage.docsTile_countdata(), "1",
				"FAIL:Reports tile count displayed <0 under Asset details page");
		sa.assertAll();
	}

//ASST076-Verify the valid formats of the documents that can be uploaded
	@Test(groups = {
			"Regression" }, description = "ASST076-Verify the valid formats of the documents that can be uploaded")
	public void ASST076() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest("ASST076-Verify the valid formats of the documents that can be uploaded");
		SoftAssert sa = new SoftAssert();
		// assetHubPage = assetDetailsPage.ClickBackBtn();
		// assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_UploadDocsBtn();
		assetDetailsPage.uploadDoc_Assetdetails("VPRT-UserManual-Chapter 5.pdf");
		Thread.sleep(1000);
		sa.assertEquals(assetDetailsPage.docsTile_countdata(), "2",
				"FAIL:Reports tile count is not updating  under Asset details page");
		// System.out.println(assetDetailsPage.docsTile_countdata());
		sa.assertAll();
	}

//ASST077-Verify the invalid formats of the documents that should not be visible while uploading

	@Test(groups = {
			"Regression" }, description = "ASST077-Verify the invalid formats of the documents that should not be visible while uploading")
	public void ASST077() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST077-Verify the invalid formats of the documents that should not be visible while uploading");
		SoftAssert sa = new SoftAssert();
		// assetHubPage = assetDetailsPage.ClickBackBtn();
		// assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_UploadDocsBtn();
		assetDetailsPage.uploadDoc_Assetdetails("U88A.eqp");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_UploadDocsBtn();
		assetDetailsPage.uploadDoc_Assetdetails("42234379E40F5E6A2E84.ast");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_UploadDocsBtn();
		assetDetailsPage.uploadDoc_Assetdetails("1065306A4C9C5E7376FC.cfg");

		sa.assertEquals(assetDetailsPage.docsTile_countdata(), "2",
				"FAIL:Reports tile count is  increasing  under Asset details page");
		sa.assertAll();
	}

	// ASST078-Verify -Copy to drive- functionality of an uploaded document for
	// local drive

	// ASST079-Verify -Copy to drive- functionality of an uploaded document for USB
	// drive
	// This TC will handel manuaaly

	// ASST080-Verify if Audit trial record exists for Copy of a Document (This test
	// case has covered under ASST078 test case )

	@Test(groups = {
			"Regression" }, description = "ASST078,ASST080-Verify -Copy to drive- functionality of an uploaded document for local drive      ")
	public void ASST078() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST078,ASST080-Verify -Copy to drive- functionality of an uploaded document for local drive");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.Select_DocFile("LTR-40_Cooling.pdf");
		assetDetailsPage.selectFolder_CopyToDrive("AutoLogs", "docs");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String foldrpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
		// System.out.println("Act Folderpath: "+filepath);
		String ExpAlrtMsg = "LTR-40_Cooling.pdf has been copied successfully to " + foldrpath;
		// System.out.println("Exp Folderpath: "+ExpAlrtMsg);
		String ActAlrtMsg = tu.get_AlertMsg_text();
		// System.out.println("Act alert msg: "+ActAlrtMsg);

		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Copy to drive operation failed for documents");

		// Confirm if the file got copied to the above destination folder or not
		List<String> fn = tu.get_fileNamesList(foldrpath);
		String expFileName = "LTR-40_Cooling.pdf";
		for (String filename : fn) {
			if (filename.contains(expFileName)) {
				sa.assertEquals(filename, expFileName, "FAIL: Incorrect file is copied or "
						+ "not all copeid during Copy to drive operation for documents");

			}
		}
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		System.out.println(Actionmsg);
		String ExpectMSG = "User ID : \"1\" ,  User Name: \"User1\" logged in to do  \"CopyFilesReports\" operation in \"Asset Details \" screen";
		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Search results is not available ");
		sa.assertAll();

	}

	// ASST081-Verify the on-click functionality of PDF icon for a document under
	// Documents tile

	@Test(groups = {
			"Regression" }, description = "ASST081-Verify the on-click functionality of PDF icon for a document under Documents tile")
	public void ASST081() throws InterruptedException, IOException, AWTException {
		extentTest = extent
				.startTest("ASST081-Verify the on-click functionality of PDF icon for a document under Documents tile");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.Select_DocFile("LTR-40_Cooling.pdf");
		assetDetailsPage.click_printBtn_Report();
		Thread.sleep(1000);
		assetDetailsPage.click_PDFpopup_OkBtn();
		Thread.sleep(3000);
		String downloadPath = System.getProperty("user.home")
				+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\LTR-40_Cooling.pdf";
		File f = new File(downloadPath);
		if (f.exists()) {
			sa.assertEquals(f.getName(), "LTR-40_Cooling.pdf", " File is not available");
			sa.assertAll();
			// System.out.println("success");
		} else {
			sa.assertEquals(f.getName(), "LTR-40_Cooling.pdf", " File is not available");
			sa.assertAll();
			System.out.println("fail to find the File");
		}

	}

	// ASST082-Verify the on-click functionality of Delete icon for a document under
	// Documents tile
	// ASST083-Verify if Audit trial record exists for Deletion of a Document
	// This ASST082 and ASST083 are handled in AssetDetailsTest-2 class

	// ASST084-Verify if the application declines uploading documents that has same
	// name

	@Test(groups = {
			"Regression" }, description = "ASST084-Verif if the application declines uploading documents that has same name")
	public void ASST084() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ASST084-Verif if the application declines uploading documents that has same name");
		SoftAssert sa = new SoftAssert();

		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_UploadDocsBtn();

		// HelpFileWord.docx this file already has uploaded

		assetDetailsPage.uploadDoc_Assetdetails("HelpFileWord.docx");

		String actAlert_msg = assetDetailsPage.alertMeg_duplicateDocupload_Assetdetails();
		String expAlert_msg = "Another file with same name already exists. Please use different name.";
		sa.assertEquals(actAlert_msg, expAlert_msg,
				"FAIL: Duplicate alert message not displayed while uploading duplicate document");
		sa.assertAll();
	}
	// ASST085-Verify if user is not able to delete the document when there are no
	// privileges given

	// This ASST085 Test cases is similar to ADMN081E test case which is present in
	// UM4 class

	// ASST086-Verify the bottom menu options in Asset details screen

	@Test(description = "'ASST086-Verify the bottom menu options in Asset details screen")
	public void ASST086() throws InterruptedException {
		extentTest = extent.startTest("'ASST086-Verify the bottom menu options in Asset details screen");
		SoftAssert sa = new SoftAssert();

		assetDetailsPage.Rt_Click_AstDetails_Buttom_AppBar();

		sa.assertEquals(assetDetailsPage.check_Home_Buttom_AppBar_Presence(), true,
				"FAIL: Home icon/button missing in bottom app bar");
		sa.assertEquals(assetDetailsPage.check_Help_Buttom_AppBar_Presence(), true,
				"FAIL: Help icon/button missing in bottom app bar");
		sa.assertEquals(assetDetailsPage.check_WndsHelp_Buttom_AppBar_Presence(), true,
				"FAIL: Windows Help icon/button missing in bottom app bar");
		sa.assertEquals(assetDetailsPage.check_About_Buttom_AppBar_Presence(), true,
				"FAIL: About icon/button missing in bottom app bar");
		sa.assertAll();
	}

	// ASST087-Verify the home btn functionality in bottom menu options in Asset
	// details screen

	@Test(description = "ASST087-Verify the home btn functionality in bottom menu options in Asset details screen")
	public void ASST087() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("ASST087-Verify the home btn functionality in bottom menu options in Asset details screen");
		SoftAssert sa = new SoftAssert();

		MainHubPage = assetDetailsPage.Click_Home_Icon_AppBar();

		sa.assertEquals(MainHubPage.mainPageTitle(), true,
				"FAIL: Clicking Home icon/button in bottom app bar do not redirect to Mains Hub page");
		sa.assertAll();
	}

	// ASST088-Verify the help btn functionality in bottom menu options in Asset
	// details screen
	@Test(description = "ASST088-Verify the help btn functionality in bottom menu options in Asset details screen")
	public void ASST088() throws InterruptedException {
		extentTest = extent
				.startTest("ASST088-Verify the help btn functionality in bottom menu options in Asset details screen");
		SoftAssert sa = new SoftAssert();

		assetDetailsPage.Click_Help_Icon_AppBar();
		sa.assertEquals(assetDetailsPage.get_Asstdetails_HelpMenu_HdrText(), "Asset Details",
				"FAIL: Clicking Help icon/button in bottom app bar"
						+ "do not display the Asset Creation Help context window");
		sa.assertAll();
	}

	// ASST089-Verify the windows help btn functionality in bottom menu options in
	// Asset details screen

	// This ASST089 test case will handle manually

	// ASST090-Verify the About btn functionality in bottom menu options in Asset
	// details screen
	@Test(description = "ASST090-Verify the About btn functionality in bottom menu options in Asset creation screen")
	public void ASST090() throws InterruptedException {
		extentTest = extent.startTest(
				"ASST090-Verify the About btn functionality in bottom menu options in Asset creation screen");
		SoftAssert sa = new SoftAssert();

		assetDetailsPage.Click_About_Icon_AppBar();
		sa.assertEquals(assetDetailsPage.check_About_wndw_Presence(), true,
				"FAIL: Clicking About icon/button in bottom app bar do not display the About window");
		sa.assertAll();
	}

}
