package com.vrt.testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Alert;
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

public class assetDetailsTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p

	public assetDetailsTest() throws IOException {
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

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_" + "_AssetDetailsTest" + ".html",
				true);
		extent.addSystemInfo("TestSuiteName", "AssetDetailsTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("assetDetailsTest in Progress..");
		/*
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
*/
	}

	// After All the tests are conducted
	// @AfterTest
	@AfterClass
	public void endReport_releaseMomory() {
		extent.flush();
		extent.close();
		assetDetailsPage.resetWebElements();
		System.out.println("assetDetails Test  Completed.");
	}

	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		// Thread.sleep(1000);
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
	 ******************************/

	// 01-ASST016
	@Test(groups = { "Sanity", "Regression" }, description = "ASST016-Verify if selecting the target Asset "
			+ "tile in Asset hub page , user is navigated to the target Asset Details screen "
			+ "with Asset name & Asset Type info displayed in the Header")
	public void ASST016() throws InterruptedException {
		extentTest = extent.startTest("ASST016-Verify whether the type of the asset - eg- "
				+ "sterilizer and the name of the asset is displayed at the left top of the "
				+ "Asset details page is as per selected asset");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(assetDetailsPage.assetDetail_PageTitle(), "HeatBath - Asset01",
				"FAIL: TC-ASST016 -Incorrect AssetDetails Page title or landed into incorrect Page");
		sa.assertAll();
	}

	// ASST001-Verify the details displayed in Asset details screen -
	// EDIT,COPY,DELETE
	@Test(groups = { "Regression" }, description = "ASST001-Verify the details displayed in Asset details screen")
	public void ASST001() throws Exception {
		extentTest = extent.startTest("ASST001-Verify the details displayed in Asset details screen");

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(assetDetailsPage.assetEditBtn_state(), true, "FAIL: No UName field present");
		sa.assertEquals(assetDetailsPage.DeleteIcon_state(), true, "FAIL: No UName field present");
		sa.assertEquals(assetDetailsPage.CopyAsset_state(), true, "FAIL: No UName field present");
		sa.assertAll();

	}

	// ASST071REP-Verify the reports are not displayed in Reports tile when they are
	// not generated
	@Test(groups = {
			"Regression" }, description = "ASST071REP-Verify the reports are not displayed in Reports tile when they are not generated")
	public void ASST071REP() throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest(
				"ASST071REP-Verify the reports are not displayed in Reports tile when they are not generated");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(assetDetailsPage.reportsTile_countdata(), "0",
				"FAIL:Reports tile count displayed >0 under Asset details page");
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		Thread.sleep(1000);
		sa.assertEquals(assetDetailsPage.reportsTile_countdata(), "5",
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
		sa.assertEquals(assetDetailsPage.AssetIDPresence(), true, "FAIL: No Asset ID field is present");
		sa.assertEquals(assetDetailsPage.ModelPresence(), true, "FAIL: No Model field is  present");
		sa.assertEquals(assetDetailsPage.ManufacturerPresence(), true, "FAIL: No Manufacturer field is present");
		sa.assertEquals(assetDetailsPage.TypePresence(), true, "FAIL: No Type field is present");
		sa.assertEquals(assetDetailsPage.LastValidatedPresence(), true, "FAIL: No LastValidated field is present");
		Thread.sleep(200);
		sa.assertEquals(assetDetailsPage.setupTile_state(), true, "FAIL: No setupTile field is present");
		sa.assertEquals(assetDetailsPage.qualTile_state(), true, "FAIL: No qualTile field is present");
		sa.assertEquals(assetDetailsPage.reportsTile_state(), true, "FAIL: No reportsTile field is present");
		sa.assertEquals(assetDetailsPage.docsTile_state(), true, "FAIL: No docsTile field is present");
		sa.assertAll();
	}

	// ASST003-Verify the on-click functionality of edit icon for Asset

	@Test(groups = { "Regression" }, description = "ASST003-Verify the on-click functionality of edit icon for Asset")
	public void ASST003() throws Exception {
		extentTest = extent.startTest("ASST003-Verify the details displayed on the 2 sections in Asset details screen");
		SoftAssert sa = new SoftAssert();

		if (assetDetailsPage.assetEditBtn_state()) {
			assetCreationPage = assetDetailsPage.click_assetEditBtn();
		}

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
		assetCreationPage.enterAssetID("02");
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
		String Actionmsg = AuditPage.get_Actiontext();
		System.out.println(Actionmsg);
		String ExpectMSG = "Asset : \"Asset01\"  is modified by User ID : \"1 \", User Name : \"User1\" .";

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
		extentTest = extent.startTest("Verify the clear button functionality in Edit Asset screen");
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

	@Test(groups = { "Regression" }, description = "ASST009,ASST022STP-Verify the on-click of Copy icon for Assets")
	public void ASST009() throws Exception {
		extentTest = extent.startTest("ASST009,ASST022STP-Verify the on-click of Copy icon for Assets");
		SoftAssert sa = new SoftAssert();

		Copyassetpage = assetDetailsPage.clickCopyasset();
		sa.assertEquals(Copyassetpage.IsCopyAssetPageTitle_presence(), true,
				"FAIL: Incorrect CopyAsset Page Title presence title or landed into incorrect Page");
		Copyassetpage.Enter_NewAssetNameField("test");
		Copyassetpage.Enter_NewAssetIDField("19");
		Copyassetpage.click_copy_Btn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Copyassetpage.clickBack_Button();
		assetDetailsPage = Copyassetpage.Yes_alert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_Actiontext();
		String ExpectMSG = "Asset : \"test\" is created by User Id : \"1\" , User Name : \"User1\".";
		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for Copy Assets activity");
		sa.assertAll();
		sa.assertAll();

	}

//ASST012-Verify the on-click of Delete icon for Assets which has files in it

	@Test(groups = {
			"Regression" }, description = "ASST012-Verify the on-click of Delete icon for Assets which has files in it")
	public void ASST012() throws Exception {
		extentTest = extent.startTest("ASST012-Verify the on-click of Delete icon for Assets which has files in it");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.DeleteAssert();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage.ClickOK_btn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		sa.assertAll();
	}

	// ASST013-Verify for a fresh asset with no activities - Setups, Qualifications
	// Documents and Reports - as mentioned, all tiles should display 0
	@Test(groups = {
			"Regression" }, description = "ASST013-Verify for  a fresh asset with no activities - Setups, Qualifications Documents and  Reports -  as mentioned, all tiles should display 0")
	public void ASST013() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"ASST013-Verify for  a fresh asset with no activities - Setups, Qualifications Documents and  Reports -  as mentioned, all tiles should display 0");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(assetDetailsPage.setupTile_countdata(), "0",
				"FAIL: TC-ASST022 - Setup tile count displayed >0 under Asset details page");
		sa.assertEquals(assetDetailsPage.qualTile_countdata(), "0",
				"FAIL: TC-ASST022 - Qual tile count displayed >0 under Asset details page");
		sa.assertEquals(assetDetailsPage.reportsTile_countdata(), "0",
				"FAIL: TC-ASST022 - Reports tile count displayed >0 under Asset details page");
		sa.assertEquals(assetDetailsPage.docsTile_countdata(), "0",
				"FAIL: TC-ASST022 - Docs tile count displayed >0 under Asset details page");
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
		sa.assertEquals(assetDetailsPage.SetupEditBtn_state(), true,
				"FAIL:The Setup Edit Btn is not displayed in the available setup");
		sa.assertEquals(assetDetailsPage.WiringImgButton_state(), true,
				"FAIL:The Setup WiringImg Button is not displayed for the available setup");
		sa.assertEquals(assetDetailsPage.DeleteBtn_state(), true,
				"FAIL:The Setup Delete Btn is not displayed for the available setup");
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

	@Test(groups = { "Regression" }, description = "ASST015STP-Verify the Setup date and time for a new Setup")
	public void ASST017() throws InterruptedException, ParseException, IOException {

		extentTest = extent.startTest("ASST015STP-Verify the Setup date and time for a new Setup");
		SoftAssert sa = new SoftAssert();
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		sa.assertEquals(defineSetupPage.defineSetupPage_state(), true, "FAIL:The setup define page is not  displayed ");
		sa.assertAll();

	}

	// ASST018STP-Verify on-click of Copy button for a setup
	@Test(groups = { "Regression" }, description = "ASST018STP-Verify on-click of Copy button for a setup")
	public void ASST018() throws InterruptedException, ParseException, IOException {

		extentTest = extent.startTest("ASST018STP-Verify on-click of Copy button for a setup");
		SoftAssert sa = new SoftAssert();
		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		sa.assertEquals(CopySetuppage.CopySetupPage_Title(), true, "FAIL:The Copy setup tile not displayed");
		sa.assertAll();
	}

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

			}
		}
		sa.assertAll();

	}

	// ASST023STP-Verify the display of Initiate Qualification button in setup tile
	@Test(groups = { "Regression" }, description = "Verify the display of Initiate Qualification button in setup tile")
	public void ASST023() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest("ASST023STP-Verify the display of Initiate Qualification button in setup tile");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(assetDetailsPage.InitiateQualBtn_state(), false,
				"FAIL:The Initiate Qual Btn state should be in disable mode");
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		sa.assertEquals(assetDetailsPage.InitiateQualBtn_state(), true,
				"FAIL:The Initiate Qual Btn state is not in enable mode");
		sa.assertAll();

	}

	// ASST024_1STP- Verify the filed level validations for valid SOP protocl number
	// field in Initiate qualification pop-up

	@Test(dataProvider = "ASST024_1STP", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "ASST024_1STP-Verify the filed level validations for valid SOP protocl number field in Initiate qualification pop-up")

	public void ASST024_1STP(String SNum, String RNum) throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST024_1STP-Verify the filed level validations for valid SOP protocl number field in Initiate qualification pop-up");

		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		SelectBaseStationPage = assetDetailsPage.Create_SOP(SNum, RNum);
		sa.assertEquals(SelectBaseStationPage.SelectBaseStationTitle_state(), true,
				"Fail: application  did not land to SelectBaseStation page ");
		sa.assertAll();
	}

	// ASST024_1A-Verify the invalid characters accepted in SOP protocl number field

	@Test(dataProvider = "ASST024_1A_TC", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "ASST024_1A-Verify the invalid characters accepted in SOP protocl number field")

	public void ASST024_1A(String SNum, String RNum) throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST024_1A-Verify the invalid characters accepted in SOP protocl number field");

		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		assetDetailsPage.Create_SOP_InvalidData(SNum, RNum);

		String ExpAlrtMsg = "SOP Protocol Number accepts alpha numeric and special characters like space,-,_ ,.,?,slash (forward and backward).";
		String ActAlertMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: SOP protocl number field accepting invalid values");
		sa.assertAll();
	}

	// ASST024_1B_Verify the max character length for SOP protocol number field
	@Test(groups = {
			"Regression" }, description = "ASST024_1B_Verify the max character length for SOP protocol number field")
	public void ASST024_1B() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST024_1B_Verify the max character length for SOP protocol number field");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		Thread.sleep(500);
		String expectedvalue = "1234567890123456789012345678901234567890123456789012";
		// System.out.println("count of SOP NUM to be entered: " +
		// expectedvalue.length());
		assetDetailsPage.Enter_SOPNum(expectedvalue);
		String actualvaluAllowed = assetDetailsPage.GetSOPNumText();
		// System.out.println("count of SOP NUM allowed : " +
		// actualvaluAllowed.length());

		sa.assertEquals(actualvaluAllowed.length(), 50, "FAIL: SOP Protocol field  accepting more than  50 characters");
		sa.assertAll();
	}

	// ASST024.2STP-Verify the filed level validations for Run number field in
	// Initiate qualification pop-up-Valid value
	@Test(dataProvider = "ASST024_2STP", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "ASST024_2STP-Verify the filed level validations for Run number field in Initiate qualification pop-up_Valid value")

	public void ASST024_2STP(String SNum, String RNum) throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST024_2STP-Verify the filed level validations for SOP protocl number field in Initiate qualification pop-up_Valid value");

		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		SelectBaseStationPage = assetDetailsPage.Create_SOP(SNum, RNum);
		sa.assertEquals(SelectBaseStationPage.SelectBaseStationTitle_state(), true,
				"Fail:application did not land to SelectBaseStation Page");
		sa.assertAll();
	}

	// ASST024_2ASTP-Verify the filed level validations for SOP protocl number field
	// in Initiate qualification pop-up_Invalid Value
	@Test(dataProvider = "ASST024_2ASTP", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "ASST024_2ASTP-Verify the filed level validations for SOP protocl number field in Initiate qualification pop-up_Invalid Value")

	public void ASST024_2ASTP(String SNum, String RNum) throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST024_2A-Verify the filed level validations for SOP protocl number field in Initiate qualification pop-up_Invalid Value");

		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		assetDetailsPage.Create_SOP_InvalidData(SNum, RNum);

		String ExpAlrtMsg = "Run Number accepts only numeric values.";
		String ActAlertMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Run Number accepting Invalid values.");
		sa.assertAll();
	}

	// ASST024_2B_Verify Max 4 characters should be allowed for Run number field

	@Test(groups = {
			"Regression" }, description = "ASST024_2B_Verify Max 4 characters should be allowed for Run number field")
	public void ASST024_2B() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST024_2B_Verify Max 4 characters should be allowed for Run number field");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		Thread.sleep(500);
		String Invalidvalue = "99999"; // 5 char
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
		Thread.sleep(500);
		sa.assertEquals(assetDetailsPage.IsSOPNumberField_Presence(), true, "Fail: SOP Field should be displayed");
		sa.assertEquals(assetDetailsPage.IsRunNumberField_Presence(), true, "Fail: Run Field should be displayed");

		sa.assertAll();

	}

//ASST026STP-Verify on-click functionality of Edit icon for a setup
	@Test(groups = { "Regression" }, description = "ASST026STP-Verify on-click functionality of Edit icon for a setup")
	public void ASST026STP() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST026STP-Verify on-click functionality of Edit icon for a setup");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		defineSetupPage = assetDetailsPage.click_editStupBtn();

		sa.assertEquals(defineSetupPage.defineSetupPage_state(), true, "Fail: Define setup  Page is not displayed");
		sa.assertAll();
	}

	// ASST027STP-Verify the edit setup functionality
	@Test(groups = {
			"Regression" }, dataProvider = "ASST027STP", dataProviderClass = assetCreationUtility.class, description = "Verify the edit setup functionality")

	public void ASST027STP(String Comments) throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest("ASST027STP-Verify the edit setup functionality");
		SoftAssert sa = new SoftAssert();
//Create Asset
		MainHubPage = UserManagementPage.ClickBackButn();
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		defineSetupPage = assetDetailsPage.click_editStupBtn();

//Define Setup
		defineSetupPage.click_defineSetupPage_commentsField();
		defineSetupPage.clear_defineSetupPage_comments();
		defineSetupPage.enter_defineSetupPage_comments(Comments);
		String CmntTxt1 = defineSetupPage.get_defineSetupPage_comments_txtData();
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();

		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn();

		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_NxtBtn();

		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();

		Setup_ReviewPage.click_Save_Btn("Manual", "Yes", "1", getPW("adminFull"));

		assetDetailsPage = Setup_ReviewPage.click_backBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		defineSetupPage = assetDetailsPage.click_editStupBtn();
		String CmntTxt2 = defineSetupPage.get_defineSetupPage_comments_txtData();
		sa.assertEquals(CmntTxt1, CmntTxt2, "FAIL:Editted comment is not displaying");

		sa.assertAll();

	}

	// The below Audit message will reflect as per the Functionality of the Test
	// case ASST027STP
	// ASST028STP-Verify if Audit trial record exists for edit setup

	@Test(groups = { "Regression" }, description = "Verify if Audit trial record exists for edit setup")
	public void ASST028STP() throws Exception {
		extentTest = extent.startTest("ASST028STP-Verify if Audit trial record exists for edit setup");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		AuditPage.Click_ActionFilter_Icon();
		AuditPage.EnterTxt_ActionFilter(
				"Setup: \"manual 1 min sampling\" is modified in Tab : \"Define Setup\" by User ID : \"1\" , User Name: \"User1\"");

		AuditPage.click_Action_FilterBtn();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_Actiontext();
		System.out.println(Actionmsg);
		String ExpectMSG = "Setup: \"manual 1 min sampling\" is modified in Tab : \"Define Setup\" by User ID : \"1\" , User Name: \"User1\"";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record doess not exists for edit setup ");
		sa.assertAll();
	}

	// ASST029WO-Verify the on-click functionality of the wiring icon for a setup

	@Test(groups = {
			"Regression" }, description = "ASST029WO-Verify the on-click functionality of the wiring icon for a setup")
	public void ASST029WO() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST029WO-Verify the on-click functionality of the wiring icon for a setup");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		OverlayWiringImagePage = assetDetailsPage.Click_WiringImgButton();
		sa.assertEquals(OverlayWiringImagePage.IsOverlayWiringPageTitle_state(), true,
				"Fail: application Landed to Incorrect page");
		sa.assertAll();
	}

	// ASST030WO-Verify the details displayed in Wiring overlay screen for a setup
	@Test(groups = {
			"Regression" }, description = "ASST030WO-Verify the details displayed in Wiring overlay screen for a setup")
	public void ASST030WO() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST030WO-Verify the details displayed in Wiring overlay screen for a setup");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		OverlayWiringImagePage = assetDetailsPage.Click_WiringImgButton();
		sa.assertEquals(OverlayWiringImagePage.IsOverlayWiringPageTitle_state(), true,
				"Fail:IsOverlayWiringPage_Title is not Visible ");
		sa.assertEquals(OverlayWiringImagePage.PrintButton_State(), true, "Fail: Print_Button is not Visible");
		sa.assertEquals(OverlayWiringImagePage.Close_button_State(), true, "Fail: Close_button is not Visible");
		sa.assertEquals(OverlayWiringImagePage.Group_Visible(), true,
				"Fail: Groups that were created during setup is not Visible");
		sa.assertAll();

	}

	// ASST032WO-Verify the on-click functionality of the print icon in the wiring
	// overlay screen for a setup
	@Test(groups = {
			"Regression" }, description = "ASST030WO-Verify the details displayed in Wiring overlay screen for a setup")
	public void ASST032WO() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST030WO-Verify the details displayed in Wiring overlay screen for a setup");
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
	public void ASST033WO() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST033WO-Verify the on-click functionality of the _Group Overlay Report_ btn in the wiring overlay screen for a setup");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		OverlayWiringImagePage = assetDetailsPage.Click_WiringImgButton();

		sa.assertEquals(OverlayWiringImagePage.GroupOverlayRprtGenerate_Popupvisible(), true,
				"Fail:GroupOverlayRprtGenerate Popup window is not Visible ");
		sa.assertAll();
	}

	// ASST034WO-Verify the on-click functionality of the _All Group Overlay Report_
	// btn in the wiring overlay screen for a setup
	@Test(groups = {
			"Regression" }, description = "ASST034WO-Verify the on-click functionality of the _All Group Overlay Report_ btn in the wiring overlay screen for a setup")
	public void ASST034WO() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST034WO-Verify the on-click functionality of the _All Group Overlay Report_ btn in the wiring overlay screen for a setup");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		OverlayWiringImagePage = assetDetailsPage.Click_WiringImgButton();
		sa.assertEquals(OverlayWiringImagePage.All_GroupOverlayReportGenerate_Popupvisible(), true,
				"Fail:AllGroupOverlayRprtGenerate Popup window is not Visible ");
		sa.assertAll();
	}

	// ASST036WO-Verify user is unable to generate the wiring overlay report from
	// Asset details screen when there is no report generation privilege given

	@Test(groups = {
			"Regression" }, description = "ASST036WO-Verify user is unable to generate the wiring overlay report from Asset details screen when there is no report generation privilege given")
	public void ASST036WO() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST036WO-Verify user is unable to generate the wiring overlay report from Asset details screen when there is no report generation privilege given");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
		Thread.sleep(1000);
		sa.assertEquals(assetDetailsPage.NewSetupCreateBtn_State(), true, "FAIL: New SetUp Button is not Present");
		sa.assertAll();
	}

	// ASST037-Verify the on-click functionality of the print icon for a setup

	@Test(groups = {
			"Regression" }, description = "ASST037-Verify the on-click functionality of the print icon for a setup")
	public void ASST037() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest("ASST037-Verify the on-click functionality of the print icon for a setup");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.Click_Print_Button();
		sa.assertEquals(assetDetailsPage.UserLoginPopupVisible(), true, "FAIL: User Login Popup Button is not Present");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
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

	// The below audit message will display after the execution of the above test
	// case ASST037
	// ASST039STP-Verify the Audit trail for print Setup report activity

	@Test(groups = { "Regression" }, description = "ASST039STP-Verify the Audit trail for print Setup report activity")
	public void ASST039STP() throws Exception {
		extentTest = extent.startTest("ASST039STP-Verify the Audit trail for print Setup report activity");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		AuditPage.Click_ActionFilter_Icon();
		AuditPage.EnterTxt_ActionFilter(
				"Asset Details:  Setup report creation performed by User ID : \"1\", User Name : \"User1\"");
		AuditPage.click_Action_FilterBtn();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_Actiontext();

		String ExpectMSG = "Asset Details:  Setup report creation performed by User ID : \"1\", User Name : \"User1\"";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Search results is not available for print Setup report activity");
		sa.assertAll();

	}

	// ASST041-Verify if user is not able to delete the setup when there are no
	// privileges given
	@Test(groups = {
			"Regression" }, description = "ASST041-Verify if user is not able to delete the setup when there are no privileges given")
	public void ASST041() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("ASST041-Verify if user is not able to delete the setup when there are no privileges given");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));

		String actualmsg = assetDetailsPage.AlertMsg();
		String Expectmsg = "User do not have permission to perform this operation";
		sa.assertEquals(actualmsg, Expectmsg, "FAIL: User is able to delete the set up file");
		sa.assertAll();

	}

	// ASST043-Verify the details displayed under Qualifications tile
	@Test(groups = { "Regression" }, description = "ASST043-Verify the details displayed under Qualifications tile")
	public void ASST043() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST043-Verify the details displayed under Qualifications tile");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		sa.assertEquals(assetDetailsPage.Qual_DeleteBtn_state(), true, "FAIL: Qual Delete Button is not Present");
		sa.assertEquals(assetDetailsPage.IsGenerateReportsBtn_Visible(), true,
				"FAIL: Generate Reports Btn is not Present");
		sa.assertEquals(assetDetailsPage.CopyQualToDrive_State(), true, "FAIL: Copy Qual to drive Btn is not Present");
		sa.assertEquals(assetDetailsPage.qualTile_Header_Text(), true, "FAIL:  qualTile Header Btn is not Present");
		sa.assertAll();
	}

	// ASST044-Verify -Copy to drive- functionality of a Qualification study file
	// for local drive
	@Test(groups = {
			"Regression" }, description = "ASST044-Verify -Copy to drive- functionality of a Qualification study file for local drive")
	public void ASST044() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest(
				"ASST044-Verify -Copy to drive- functionality of a Qualification study file for local drive");
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
			}
		}
		sa.assertAll();

	}

	// ASST046-Verify if Audit trial record exists for Copy of a study file

	@Test(groups = {
			"Regression" }, description = "ASST046-Verify if Audit trial record exists for Copy of a study file")
	public void ASST046() throws Exception {
		extentTest = extent.startTest("ASST046-Verify if Audit trial record exists for Copy of a study file");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		AuditPage.Click_ActionFilter_Icon();
		AuditPage.EnterTxt_ActionFilter(
				"User ID : \"1\" ,  User Name: \"User1\" logged in to do  \"CopyFilesReports\" operation in \"Asset Details \" screen");
		AuditPage.click_Action_FilterBtn();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_Actiontext();
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
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		Thread.sleep(1000);
		sa.assertEquals(assetDetailsPage.IsGenerateReportsBtn_Visible(), true,
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

		RWFileSelctionPage = assetDetailsPage.GenerateReportsBtn_Nextpage();
		sa.assertEquals(RWFileSelctionPage.assetDetailTitle_Visible(), true, "FAIL: Landed to wrong page ");
		sa.assertAll();
	}

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

	// ASST052.1REP-Verify the details displayed under reports tile- sub tab-Setup

	@Test(groups = {
			"Regression" }, description = "ASST052.1REP-Verify the details displayed under reports tile- sub tab-Setup")
	public void ASST052_1REP() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST052.1REP-Verify the details displayed under reports tile- sub tab-Setup");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min sampling");
		sa.assertEquals(assetDetailsPage.ReportsHeaderText_state(), true, "FAIL: Report Heaser  is not Present");
		sa.assertEquals(assetDetailsPage.ReportView_Btn_State(), true, "FAIL: print Button is not Present");
		sa.assertEquals(assetDetailsPage.DeleteBtn_state(), true, "FAIL: Report Delete Button is not Present");
		sa.assertAll();
	}

	// ASST052REP-Verify the details displayed under reports tile
	@Test(groups = { "Regression" }, description = "ASST052REP-Verify the details displayed under reports tile")
	public void ASST052REP() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST052REP-Verify the details displayed under reports tile");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min sampling");
		sa.assertEquals(assetDetailsPage.ReportsTile_Count_state(), true,
				"FAIL: Repoer tile count is not displayng under report tile");
		sa.assertEquals(assetDetailsPage.SetupReportsButton_State(), true, "FAIL: setup Report button is not Present");
		sa.assertEquals(assetDetailsPage.QualReportsButton_State(), true, "FAIL: qual Report button is not Present");
		sa.assertEquals(assetDetailsPage.PassFailReportButton_State(), true,
				"FAIL: passfail Report button is not Present");
		sa.assertAll();
	}

	// ASST053REP-Verify -Copy to drive- functionality of a Setup Report for local
	// drive

	@Test(groups = { "Regression" }, description = "ASST053REP-Verify -Copy to drive- "
			+ "functionality of a Setup Report for local drive")
	public void ASST053REP() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent
				.startTest("ASST053REP-Verify -Copy to drive- functionality " + "of a Setup Report for local drive");
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

			}

		}
		sa.assertAll();
	}

	// ASST053_1REP-Verify the on-click functionality of PDF icon for Detailed
	// report under Reports tile-Qualifications sub tab

	@Test(groups = { "Regression" }, description = "ASST053_1REP-Verify the on-click "
			+ "functionality of PDF icon for Detailed report under Reports tile-Qualifications sub tab")
	public void ASST053_1REP() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest("ASST053_1REP-Verify the on-click functionality of PDF icon "
				+ "for Detailed report under Reports tile-Qualifications sub tab");
		// SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min samplin");
		assetDetailsPage.click_printBtn_Report();
		Thread.sleep(2000);
		//UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage.check_openfile_window_Presence();

	}

	// ASST054REP- Verify the on-click functionality of PDF icon for Summary report
	// under Reports tile-Qualifications sub tab

	@Test(groups = {
			"Regression" }, description = "ASST054REP- Verify the on-click functionality of PDF icon for Summary report under Reports tile-Qualifications sub tab")
	public void ASST054REP() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST054REP- Verify the on-click functionality of PDF icon for Summary report under Reports tile-Qualifications sub tab");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min samplin");
		assetDetailsPage.click_printBtn_Report();
		Thread.sleep(2000);
		sa.assertEquals(assetDetailsPage.ReportView_Popupvisible(), true,
				"FAIL:ReportView Popup is not visible in Asset details page");
		sa.assertAll();
	}

	// ASST055REP-Verify if Audit trial record exists for Copy of a setup report

	@Test(groups = { "Regression" }, description = "Verify if Audit trial record exists for Copy of a setup report")
	public void ASST055REP() throws Exception {
		extentTest = extent.startTest("ASST055REP-Verify if Audit trial record exists for Copy of a setup report");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		AuditPage.Click_ActionFilter_Icon();

		AuditPage.EnterTxt_ActionFilter(
				"User ID : \"1\" ,  User Name: \"User1\" logged in to do  \"CopyFilesReports\" operation in \"Asset Details \" screen");
		AuditPage.click_Action_FilterBtn();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_Actiontext();
		System.out.println(Actionmsg);
		String ExpectMSG = "User ID : \"1\" ,  User Name: \"User1\" logged in to do  \"CopyFilesReports\" operation in \"Asset Details \" screen";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for Copy of a setup report ");
		sa.assertAll();

	}

	// ASST056REP-Verify the on-click functionality of PDF icon under Reports
	// tile-Setups sub tab

	@Test(groups = {
			"Regression" }, description = "ASST056REP-Verify the on-click functionality of PDF icon under Reports tile-Setups sub tab")
	public void ASST056REP() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST056REP-Verify the on-click functionality of PDF icon under Reports tile-Setups sub tab");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min sampling");
		assetDetailsPage.click_printBtn_Report();

		sa.assertEquals(assetDetailsPage.ReportView_Popupvisible(), true,
				"FAIL:ReportView Popup is not visible in Asset details page");
		sa.assertAll();
	}

//ASST059REP-Verify -Copy to drive- functionality of a Detailed Report for local drive

	@Test(groups = {
			"Regression" }, description = "ASST059REP-Verify -Copy to drive- functionality of a Detailed Report for local drive")
	public void ASST059REP() throws InterruptedException, IOException, AWTException {
		extentTest = extent
				.startTest("ASST059REP-Verify -Copy to drive- functionality of a Detailed Report for local drive");
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

			}
		}
		sa.assertAll();
	}

	// ASST061REP-Verify if Audit trial record exists for Copy of a Detailed report

	@Test(groups = {
			"Regression" }, description = "ASST061REP-Verify if Audit trial record exists for Copy of a Detailed report")
	public void ASST061REP() throws Exception {
		extentTest = extent.startTest("ASST061REP-Verify if Audit trial record exists for Copy of a Detailed report");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		AuditPage.Click_ActionFilter_Icon();
		AuditPage.EnterTxt_ActionFilter(
				"User ID : \"1\" ,  User Name: \"User1\" logged in to do  \"CopyFilesReports\" operation in \"Asset Details \" screen");
		AuditPage.click_Action_FilterBtn();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_Actiontext();
		String ExpectMSG = "User ID : \"1\" ,  User Name: \"User1\" logged in to do  \"CopyFilesReports\" operation in \"Asset Details \" screen";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for Copy of a Detailed report");
		sa.assertAll();

	}

	// ASST062REP-Verify -Copy to drive- functionality of a Summary Report for local
	// drive
	@Test(groups = {
			"Regression" }, description = "ASST062REP-Verify -Copy to drive- functionality of a Summary Report for local")
	public void ASST062REP() throws InterruptedException, IOException, AWTException {
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

			}
		}
		sa.assertAll();
	}

	// ASST064REP-Verify if Audit trial record exists for Copy of a Summary report

	@Test(groups = {
			"Regression" }, description = "ASST064REP-Verify if Audit trial record exists for Copy of a Summary report")
	public void ASST064REP() throws Exception {
		extentTest = extent.startTest("ASST064REP-Verify if Audit trial record exists for Copy of a Summary report");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		AuditPage.Click_ActionFilter_Icon();
		AuditPage.EnterTxt_ActionFilter(
				"User ID : \"1\" ,  User Name: \"User1\" logged in to do  \"CopyFilesReports\" operation in \"Asset Details \" screen");
		AuditPage.click_Action_FilterBtn();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_Actiontext();
		String ExpectMSG = "User ID : \"1\" ,  User Name: \"User1\" logged in to do  \"CopyFilesReports\" operation in \"Asset Details \" screen";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for Copy of a Summary report ");
		sa.assertAll();

	}

	// ASST065REP-Verify -Copy to drive- functionality of a Pass_Fail Report for
	// local drive

	// ASST067REP-Verify if Audit trial record exists for Copy of a Pass_Fail report
	// (This audit test cases also covered under the below test script)

	@Test(groups = {
			"Regression" }, description = "ASST065REP,ASST067REP-Verify -Copy to drive- functionality of a Pass_Fail Report for local drive")
	public void ASST065REP() throws InterruptedException, IOException, AWTException {
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

			}
		}

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_Actiontext();
		System.out.println(Actionmsg);
		String ExpectMSG = "User ID : \"1\" ,  User Name: \"User1\" logged in to do  \"CopyFilesReports\" operation in \"Asset Details \" screen";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for Copy of a Pass_Fail report");
		sa.assertAll();
	}

	// ASST068REP-Verify the on-click functionality of PDF icon for Pass_Fail report
	// under Reports tile-Pass_Fail sub tab

	@Test(groups = {
			"Regression" }, description = "ASST068REP-Verify the on-click functionality of PDF icon for Pass_Fail report under Reports tile-Pass_Fail sub tab")
	public void ASST068REP() throws Exception {
		extentTest = extent.startTest(
				"ASST068REP-Verify the on-click functionality of PDF icon for Pass_Fail report under Reports tile-Pass_Fail sub tab");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_PassFailReportBtn();
		assetDetailsPage.Select_ReportFile("manual 1 min samplin");
		assetDetailsPage.click_printBtn_Report();
		Thread.sleep(1000);
		sa.assertEquals(assetDetailsPage.ReportView_Popupvisible(), true, "FAIL: ReportView Popup is not visible ");
		sa.assertAll();

	}

	// ASST073REP-Verify if user is not able to delete the reports when there are no
	// privileges given

	@Test(groups = {
			"Regression" }, description = "ASST073REP-Verify if user is not able to delete the reports when there are no privileges given")
	public void ASST073REP() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST073REP-Verify if user is not able to delete the reports when there are no privileges given");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min sampling");
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String actualmsg = assetDetailsPage.AlertMsg();
		String Expectmsg = "User do not have permission to perform this operation";
		sa.assertEquals(actualmsg, Expectmsg, "FAIL: User is able to delete the selected studyfile");
		sa.assertAll();

	}

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
		sa.assertEquals(assetDetailsPage.docsTile_countdata(), "0",
				"FAIL:Reports tile count displayed >0 under Asset details page");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_UploadDocsBtn();

		assetDetailsPage.uploadDoc_Assetdetails("HelpFileWord.docx");
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
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_UploadDocsBtn();
		assetDetailsPage.uploadDoc_Assetdetails("VPRT-UserManual-Chapter 5.pdf");

		sa.assertEquals(assetDetailsPage.docsTile_countdata(), "2",
				"FAIL:Reports tile count is not updating  under Asset details page");
		sa.assertAll();
	}

//ASST077-Verify the invalid formats of the documents that should not be visible while uploading

	@Test(groups = {
			"Regression" }, description = "ASST077-Verify the invalid formats of the documents that should not be visible while uploading")
	public void ASST077() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST077-Verify the invalid formats of the documents that should not be visible while uploading");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
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
		String Actionmsg = AuditPage.get_Actiontext();
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
		sa.assertEquals(assetDetailsPage.ReportView_Popupvisible(), true, "FAIL: Report View Popup is not visible ");
		sa.assertAll();
	}

// ASST085-Verify if user is not able to delete the document when there are no privileges given

	@Test(groups = {
			"Regression" }, description = "'ASST085-Verify if user is not able to delete the document when there are no privileges given")
	public void ASST085() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST085-Verify if user is not able to delete the document when there are no privileges given");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.Select_DocFile("LTR-40_Cooling.pdf");
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String actualmsg = assetDetailsPage.AlertMsg();
		String Expectmsg = "User do not have permission to perform this operation";
		sa.assertEquals(actualmsg, Expectmsg, "FAIL: able to delete the document when there are no privileges given");
		sa.assertAll();

	}

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

	// ASST086-Verify the bottom menu options in Asset details screen
	@Test(description = "'ASST086-Verify the bottom menu options in Asset details screen")
	public void ASST50() throws InterruptedException {
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
	public void ASST51() throws InterruptedException, IOException {
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

	// ASST090-Verify the About btn functionality in bottom menu options in Asset
	// details screen
	@Test(description = "ASST54-Verify the About btn functionality in bottom menu options in Asset creation screen")
	public void ASST090() throws InterruptedException {
		extentTest = extent
				.startTest("ASST54-Verify the About btn functionality in bottom menu options in Asset creation screen");
		SoftAssert sa = new SoftAssert();

		assetDetailsPage.Click_About_Icon_AppBar();
		sa.assertEquals(assetDetailsPage.check_About_wndw_Presence(), true,
				"FAIL: Clicking About icon/button in bottom app bar do not display the About window");
		sa.assertAll();
	}

	// THE BELOW SCRIPTS ARE BASED ON DELETE REPORTS,ASSET,SETUPS AND THE RESPECTIVE
	// AUDIT RECORD

	// ASST055_1REP- Verify the on-click functionality of Delete icon for Detailed
	// report under Reports tile-Qualifications sub tab
	// ASST056_1REP-Verify if Audit trial record exists for Deletion of a Detailed
	// report (This Test case also executing under ASST055_1REP tc )

	@Test(groups = {
			"Regression" }, description = "ASST055_1REP,ASST056_1REP-Verify the on-click functionality of Delete icon for Detailed report under Reports tile-Qualifications sub tab")
	public void ASST055_1REP() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest(
				"ASST055_1REP,ASST056_1REP-Verify the on-click functionality of Delete icon for Detailed report under Reports tile-Qualifications sub tab");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
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

	// ASST057REP-Verify the on-click functionality of Delete icon under Reports
	// tile-Setups sub tab
	// ASST058REP-Verify if Audit trial record exists for deletion of a setup
	// report_under report section(This script is covering in the below script
	// ASST057REP )
	@Test(groups = {
			"Regression" }, description = "ASST057REP,ASST058REP-Verify the on-click functionality of Delete icon under Reports tile-Setups sub tab")
	public void ASST057REP() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST057REP-Verify the on-click functionality of Delete icon under Reports tile-Setups sub tab");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
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

	// ASST057_1REP-Verify the on-click functionality of Delete icon for Summary
	// report under Reports tile-Qualifications sub tab

	// ASST058REP-Verify if Audit trial record exists for Deletion of a Summary
	// report (This Test case also executing under ASST057REP tc )

	@Test(groups = {
			"Regression" }, description = "ASST057REP,ASST058REP-Verify the on-click functionality of Delete icon for Summary report under Reports tile-Qualifications sub tab")
	public void ASST057_1REP() throws InterruptedException, ParseException, IOException, AWTException {

		extentTest = extent.startTest(
				"ASST057REP,ASST058REP-Verify the on-click functionality of Delete icon for Summary report under Reports tile-Qualifications sub tab");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
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

	// ASST069REP-Verify the on-click functionality of Delete icon for Pass_Fail
	// report under Reports tile-Pass_Fail sub tab

	// ASST070REP-Verify if Audit trial record exists for Deletion of a Pass_Fail
	// report (This script is executing under ASST069REP script )

	@Test(groups = {
			"Regression" }, description = "ASST069REP,ASST070REP-Verify the on-click functionality of Delete icon for Pass_Fail report under Reports tile-Pass_Fail sub tab")
	public void ASST069REP() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"ASST069REP,ASST070REP-Verify the on-click functionality of Delete icon for Pass_Fail report under Reports tile-Pass_Fail sub tab");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
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

	// ASST082-Verify the on-click functionality of Delete icon for a document under
	// Documents tile
	// ASST083-Verify if Audit trial record exists for Deletion of a Document (This
	// script is covered under the below script ASST082)

	@Test(groups = {
			"Regression" }, description = "ASST082,ASST083-Verify the on-click functionality of Delete icon for a document under Documents tile")
	public void ASST082() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST082,ASST083-Verify the on-click functionality of Delete icon for a document under Documents tile");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
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

	// ASST049-Verify the on-click of delete icon for a Qualification study file
	// ASST051-Verify if Audit trial record exists for Delete study file activity
	// (This script is covering under the script ASST049)

	@Test(groups = {
			"Regression" }, description = "ASST049,ASST051-Verify the on-click of delete icon for a Qualification study file")
	public void ASST049() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("ASST049,ASST051-Verify the on-click of delete icon for a Qualification study file");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
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

	// ASST040-Verify the on-click of delete icon for a setup
	// ASST042STP-Verify if Audit trial record exists for delete setup
	@Test(groups = { "Regression" }, description = "ASST040-Verify the on-click of delete icon for a setup")
	public void ASST040() throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST040-Verify the on-click of delete icon for a setup");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
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

	// ASST010-Verify the on-click of Delete icon for Assets with no files in it
	// ASST011-Verify the Audit trail for Delete Assets activity this script is
	// covered under ASST010 script

	@Test(groups = {
			"Regression" }, description = "ASST010,ASST011-Verify the on-click of Delete icon for Assets with no files in it")
	public void ASST010() throws Exception {
		extentTest = extent
				.startTest("ASST010,ASST011-Verify the on-click of Delete icon for Assets with no files in it");
		SoftAssert sa = new SoftAssert();

		// As documents available under this asset we are deleting those document
		// inorder to proceed with Assets Deletion
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualReportsButton();
		assetDetailsPage.Select_ReportFile("manual 1 min samplin");
		assetDetailsPage.click_DeleteBtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage.YesBtn_WithFiles();

		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.Select_DocFile("LTR-40_Cooling.pd");
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

	// ASST019STP-Verify Copy setup functionality when there is only one Asset
	// available
	@Test(groups = {
			"Regression" }, dataProvider = "ASST019STP", dataProviderClass = assetCreationUtility.class, description = "ASST019STP-Verify Copy setup functionality when there is only one Asset available")
	public void ASST019STP(String SetupName, String SensorCount, String TempCount, String TCSensorLabel, String Qstart)
			throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent
				.startTest("ASST019STP-Verify Copy setup functionality when there is only one Asset available");
		SoftAssert sa = new SoftAssert();

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
		assetDetailsPage.Click_SetupName("ASST019STP");
		assetDetailsPage.click_CopyStup_Btn();
		String Actmsg = assetDetailsPage.AlertMsg();
		String Expmsg = "To perform Copy Setup more than 1 asset required.";

		sa.assertEquals(Actmsg, Expmsg,
				"FAIL: Application failed to display the alert message for  Copy setup functionality when there is only one Asset available ");
		sa.assertAll();

	}

}
