/**
* @author ruchika
*
*/

package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;
//import java.util.ArrayList;
import java.util.Arrays;

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
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.Equipment_VRTLoggerHubPage;
import com.vrt.pages.AuditPage;
import com.vrt.pages.Equipment_IRTDHubPage;
import com.vrt.pages.Equuipment_VRTLoggersDetailspage;
import com.vrt.pages.FileManagementPage;
import com.vrt.pages.SyncInPage;
import com.vrt.pages.Equipment_IRTDDetailspage;
import com.vrt.pages.EquipmentHubPage;
import com.vrt.pages.SyncInAssetListPage;
import com.vrt.pages.NewEquipmentCreation_Page;
import com.vrt.utility.EqupmentUtility;
import com.vrt.utility.TestUtilities;
import com.vrt.utility.assetCreationUtility;
import com.vrt.utility.setupCreationUtility;

public class EquipmentListTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p

	public EquipmentListTest() throws IOException {
		super();
	}

	public ExtentReports extent;
	public ExtentTest extentTest;
	TestUtilities tu = new TestUtilities();

//Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	AuditPage AuditPage;
	NewEquipmentCreation_Page NewEquipmentCreation_Page;
	EquipmentHubPage EquipmentHubPage;
	Equipment_IRTDHubPage IRTDHubPage;
	Equipment_IRTDDetailspage Equipment_IRTDDetailspage;
	FileManagementPage FileManagementPage;
	SyncInPage SyncInPage;
	SyncInAssetListPage SyncInAssetListPage;
	Equipment_VRTLoggerHubPage VRTLoggerHubPage;
	Equuipment_VRTLoggersDetailspage Equuipment_VRTLoggersDetailspage;

	// Ensure the User has got rights to create Assets
	// @BeforeTest
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_" + "EquipmentListTest" + ".html",
				true);
		extent.addSystemInfo("TestSuiteName", "Asset Creation Test");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("EquipmentList Test in Progress..");

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

	// After All the tests are conducted
	// @AfterTest
	@AfterClass
	public void endReport() {
		extent.flush();
		extent.close();
		System.out.println("EquipmentHub Test Completed.");
	}

	// Before all tests are conducted
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();

	}

	// TearDown of the App
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

	// ~~~~~~~~~~
	// Test Cases
	// ~~~~~~~~~~

	// EL_001-Verify if IRTD Equipment type tile listed the newly created IRTD
	// equipment in the IRTD Equipment List page
	// EL_002-Verify if clicking on the IRTD Equipment type tiles displays all the
	// equipments of that particular type

	@Test(groups = {
			"Regression" }, dataProvider = "EL_002", dataProviderClass = EqupmentUtility.class, description = "EL_001_EL_002-Verify if clicking on the IRTD Equipment type tiles displays all the equipments of that particular type")
	public void EL_001_EL_002(String Etype, String serialNum, String modelNum, String id)
			throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"EL_001_EL_002-Verify if clicking on the IRTD Equipment type tiles displays all the equipments of that particular type");

		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();

		NewEquipmentCreation_Page.EqipCreation(Etype, serialNum, modelNum, id);

		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String Act_ManufacturingCalDate = tu.convert_StringDate_to_ActualDate_inCertainFormat7(
				NewEquipmentCreation_Page.FetchTxt_From_ManufacturingCalDate());
		String Act_ManufacturingCalDueDate = tu.convert_StringDate_to_ActualDate_inCertainFormat7(
				NewEquipmentCreation_Page.FetchTxt_From_ManufacturingCal_DueDate());

		EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo(serialNum);
		String actualModelNum = Equipment_IRTDDetailspage.fetch_ModelNumber();
		String actualID = Equipment_IRTDDetailspage.fetch_ID();
		String Exp_ManufacturingCalDate = Equipment_IRTDDetailspage.fetch_ManufacturingCalDate();
		String Exp_ManufacturingCalDueDate = Equipment_IRTDDetailspage.fetch_ManufacturingCalDueDate();
		sa.assertEquals(actualModelNum, modelNum,
				"FAIL: ModelNum is not same in Equipment_IRTDDetailspage which has entered in New Equipment creation ");
		sa.assertEquals(actualID, id,
				"FAIL: Id is not same in Equipment_IRTDDetailspage which has entered in New Equipment creation ");
		sa.assertEquals(Act_ManufacturingCalDate, Exp_ManufacturingCalDate,
				"FAIL: ManufacturingCalDate is not same in Equipment_IRTDDetailspage which has entered in New Equipment creation ");
		sa.assertEquals(Act_ManufacturingCalDueDate, Exp_ManufacturingCalDueDate,
				"FAIL: ManufacturingCalDueDate is not same in Equipment_IRTDDetailspage which has entered in New Equipment creation ");

		sa.assertAll();
	}

//EL_003- Verify the contents of the  Equipment tiles (IRTD)

	@Test(groups = { "Regression" }, description = "EL_003- Verify the contents of the  Equipment tiles (IRTD)")
	public void EL_003() throws InterruptedException, IOException {
		extentTest = extent.startTest("EL_003- Verify the contents of the  Equipment tiles (IRTD)");

		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();

		sa.assertEquals(IRTDHubPage.isIRTDHeader_Visible(), true, "Fail: IRTD Header not displayed");
		sa.assertEquals(IRTDHubPage.isSerialNo_Visible(), true, "Fail: SerialNo not displayed");
		sa.assertEquals(IRTDHubPage.isMfgcaldate_Visible(), true, "Fail: Mfgcaldate not displayed");
		sa.assertEquals(IRTDHubPage.isMfgcalduedate_Visible(), true, "Fail: Mfgcalduedate  not displayed");

		sa.assertAll();

	}

	// EL_004- Verify if the filter button is available in the IRTD equipment type
	// screen

	@Test(groups = {
			"Regression" }, description = "EL_004- Verify if the filter button is available in the IRTD equipment type screen")
	public void EL_004() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("EL_004- Verify if the filter button is available in the IRTD equipment type screen");

		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		sa.assertEquals(IRTDHubPage.isfilter_btn_Visible(), true, "Fail: Filter button not displayed");
		sa.assertAll();
	}

	// EL_005- Verify if clicking on the filter button in IRTD equipment displays
	// the Filter pop up

	@Test(groups = {
			"Regression" }, description = "EL_005- Verify if clicking on the filter button in IRTD equipment displays the Filter pop up")
	public void EL_005() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_005- Verify if clicking on the filter button in IRTD equipment displays the Filter pop up");

		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDHubPage.clickOn_filterbtn();
		sa.assertEquals(IRTDHubPage.filter_popup_Visible(), true, "Fail:filter_popup is  not displayed");

		sa.assertAll();
	}

	// EL_006- Verify the contents of the Filter Pop Up in IRTD Equipment

	@Test(groups = { "Regression" }, description = "EL_006- Verify the contents of the Filter Pop Up in IRTD Equipment")
	public void EL_006() throws InterruptedException, IOException {
		extentTest = extent.startTest("EL_006- Verify the contents of the Filter Pop Up in IRTD Equipment");

		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDHubPage.clickOn_filterbtn();

		sa.assertEquals(IRTDHubPage.filter_By_Visible(), true, "Fail:filter_popup is  not displayed");
		sa.assertEquals(IRTDHubPage.OKButton_Visible(), true, "Fail:filter_popup is  not displayed");
		sa.assertEquals(IRTDHubPage.CancelButton_Visible(), true, "Fail:filter_popup is  not displayed");

		IRTDHubPage.clickOn_FilterByComboBox();

		sa.assertEquals(IRTDHubPage.FetchTxt_from_filterByDD(1), "Serial No.",
				"Fail: Serial No. filter_popup is  not displayed");
		sa.assertEquals(IRTDHubPage.FetchTxt_from_filterByDD(2), "Mfg Calibration Date",
				"Fail: Mfg Calibration Date option from filter_popup is  not displayed");
		sa.assertEquals(IRTDHubPage.FetchTxt_from_filterByDD(3), "Latest Mfg Calibration Due Date",
				"Fail: Latest Mfg Calibration Due Date filter_popup is  not displayed");

		sa.assertAll();
	}

	// EL_007- Verify if User can select the Kaye Serial Number option from the
	// Filter dropdown in IRTD Equipment

	@Test(groups = { "Regression" }, description = "EL_007- Verify the contents of the Filter Pop Up in IRTD Equipment")
	public void EL_007() throws InterruptedException, IOException {
		extentTest = extent.startTest("EL_007- Verify the contents of the Filter Pop Up in IRTD Equipment");

		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDHubPage.clickOn_filterbtn();
		IRTDHubPage.clickOn_FilterByComboBox();
		IRTDHubPage.SelectOptions_from_filterByDD(1);
		sa.assertEquals(IRTDHubPage.FetchTxt_filterBY_selectedOption(), "Serial No.",
				"Fail: Serial No. filter_popup is  not displayed");
		sa.assertAll();
	}

	// EL_008- Verify if User can filter the IRTD equipments by the Kaye Serial
	// Number, if the corresponding radio button is selected

	@Test(groups = {
			"Regression" }, description = "EL_008- Verify if User can filter the IRTD equipments by the Kaye Serial Number, if the corresponding radio button is selected")
	public void EL_008() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_008- Verify if User can filter the IRTD equipments by the Kaye Serial Number, if the corresponding radio button is selected");

		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDHubPage.clickOn_filterbtn();
		IRTDHubPage.clickOn_FilterByComboBox();
		IRTDHubPage.SelectOptions_from_filterByDD(1);

		IRTDHubPage.enterSerialNo("J1129");
		IRTDHubPage.click_OKbtn_Filterpopup();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		sa.assertEquals(Equipment_IRTDDetailspage.IRTD_DetailsHeaderPresence(), true,
				"FAIL: Application id not redirecting to IRTD Details page ");
		sa.assertAll();

	}

	// EL_009- Verify if User can filter the IRTD equipments by the Mfg Calibration
	// Date, if the corresponding option is selected

	@Test(groups = {
			"Regression" }, description = "EL_009- Verify if User can filter the IRTD equipments by the Mfg Calibration Date, if the corresponding option is selected")
	public void EL_009() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_009- Verify if User can filter the IRTD equipments by the Mfg Calibration Date, if the corresponding option is selected");

		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();
		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EL009b", "009b");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDHubPage.clickOn_filterbtn();
		IRTDHubPage.clickOn_FilterByComboBox();
		IRTDHubPage.SelectOptions_from_filterByDD(2);
		IRTDHubPage.Select_datepickerfield(0);
		IRTDHubPage.Select_datepickerfield(1);
		IRTDHubPage.click_OKbtn_Filterpopup();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("EL009b");
		sa.assertEquals(Equipment_IRTDDetailspage.IRTD_DetailsHeaderPresence(), true,
				"Fail: Irtd Details title not visible");
		sa.assertAll();

	}

	// EL_010- Verify if User can select the Mfg Calibration Due Date option from
	// the Filter dropdown in IRTD Equipment

	@Test(groups = {
			"Regression" }, description = "EL_010- Verify if User can select the Mfg Calibration Due Date option from the Filter dropdown in IRTD Equipment")
	public void EL_010() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_010- Verify if User can select the Mfg Calibration Due Date option from the Filter dropdown in IRTD Equipment");

		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDHubPage.clickOn_filterbtn();
		IRTDHubPage.clickOn_FilterByComboBox();
		IRTDHubPage.SelectOptions_from_filterByDD(3);

		sa.assertEquals(IRTDHubPage.FetchTxt_filterBY_selectedOption(), "Latest Mfg Calibration Due Date",
				"Fail:Serial No. option from filter_popup is  not selected");

		sa.assertAll();

	}

	// EL_011- Verify if User can filter the IRTD equipments by the Mfg Calibration
	// Due Date, if the corresponding radio button is selected

	// EL_012- Verify if user can select only one filter option to filter the
	// loggers in IRTD Equipment Filter Popup

	@Test(groups = {
			"Regression" }, description = "EL_012- Verify if user can select only one filter option to filter the loggers in IRTD Equipment Filter Popup")
	public void EL_012() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_012- Verify if user can select only one filter option to filter the loggers in IRTD Equipment Filter Popup");

		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDHubPage.clickOn_filterbtn();
		IRTDHubPage.clickOn_FilterByComboBox();
		IRTDHubPage.SelectOptions_from_filterByDD(1);

		sa.assertEquals(IRTDHubPage.FetchTxt_filterBY_selectedOption(), "Serial No.",
				"Fail: Serial No. option from filter_popup is  not selected");

		IRTDHubPage.clickOn_FilterByComboBox();
		IRTDHubPage.SelectOptions_from_filterByDD(2);

		sa.assertEquals(IRTDHubPage.FetchTxt_filterBY_selectedOption(), "Mfg Calibration Date",
				"Fail: Mfg Calibration Date option from filter_popup is  not selected");

		IRTDHubPage.clickOn_FilterByComboBox();
		IRTDHubPage.SelectOptions_from_filterByDD(3);

		sa.assertEquals(IRTDHubPage.FetchTxt_filterBY_selectedOption(), "Latest Mfg Calibration Due Date",
				"Fail: Latest Mfg Calibration Due Date Date option from filter_popup is  not selected");

		sa.assertAll();
	}

	// EL_013- Verify if clicking on the Cancel button in the Filter IRTD Equipments
	// pop up closes the pop up without filtering the equipments

	@Test(groups = {
			"Regression" }, description = "EL_013- Verify if clicking on the Cancel button in the Filter IRTD Equipments pop up closes the pop up without filtering the equipments")
	public void EL_013() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_013- Verify if clicking on the Cancel button in the Filter IRTD Equipments pop up closes the pop up without filtering the equipments");

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDHubPage.clickOn_filterbtn();
		IRTDHubPage.clickOn_FilterByComboBox();
		IRTDHubPage.SelectOptions_from_filterByDD(1);
		IRTDHubPage.enterSerialNo("J1129");
		IRTDHubPage.click_Cancelbtn_Filterpopup();
		IRTDHubPage.is_FilterPopup_Visible();

	}

	// EL_014- Verify if clicking on the Clear Filter option in the IRTD Equipment
	// List page clears all the existing filters

	@Test(groups = {
			"Regression" }, description = "EL_014- Verify if clicking on the Clear Filter option in the IRTD Equipment List page clears all the existing filters")
	public void EL_014() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_014- Verify if clicking on the Clear Filter option in the IRTD Equipment List page clears all the existing filters");

		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDHubPage.clickOn_filterbtn();
		IRTDHubPage.clickOn_FilterByComboBox();
		IRTDHubPage.SelectOptions_from_filterByDD(1);
		IRTDHubPage.enterSerialNo("J1129");
		IRTDHubPage.click_OKbtn_Filterpopup();
		IRTDHubPage.clickOn_RefreshFilterBtn();
		Thread.sleep(1000);
		sa.assertEquals(IRTDHubPage.isfilter_btn_Visible(), true, "FAIL: Filter button is not visible");
		sa.assertAll();
	}

	// EL_015- Verify if the Serial No and Mfg Cal Due Date in the IRTD equipment
	// tile displays yellow colour once the equipments are due for Calibration based
	// on the Instrument calibration Warning available in the Admin-Policies
	// EL_016- Verify if the Serial No and Mfg Cal Due Date in the IRTD equipment
	// tile displays red colour once the equipments due date is achieved

	// EL_017- Verify if clicking on the Help button in the IRTD Equipment List page
	// displays the online help menu for that page

	@Test(description = "EL_017- Verify if clicking on the Help button in the IRTD Equipment List page displays the online help menu for that page")
	public void EL_017() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_017- Verify if clicking on the Help button in the IRTD Equipment List page displays the online help menu for that page");
		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();

		tu.Click_Help_Icon_AppBar();
		sa.assertEquals(tu.get__HelpMenu_HdrText(), "The Equipment Hub",
				"FAIL: Clicking Help icon/button in bottom app bar"
						+ "do not display The Equipment Hub Help context window");
		sa.assertAll();
	}

//EL_018- Verify if clicking on the About button in the IRTD Equipment List page displays the current software version, Console IP

	@Test(dataProvider = "SET033", dataProviderClass = setupCreationUtility.class, description = "EL_018- Verify if clicking on the About button in the IRTD Equipment List page displays the current software version, Console IP")
	public void EL_018(String SWVer) throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_018- Verify if clicking on the About button in the IRTD Equipment List page displays the current software version, Console IP");
		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		tu.Click_About_Icon_AppBar();

		sa.assertEquals(tu.SWversion_InAboutWndw(), SWVer,
				"FAIL: Incorrect SW version & Console IP adress displayed in About window");

		sa.assertEquals(tu.consoleIP_InAboutWndw(), tu.system_IPadress(),
				"FAIL: Incorrect SW version & Console IP adress displayed in About window");

		sa.assertAll();
	}

//EL_019- Verify if clicking on the Home button in the IRTD Equipment List page navigate to the Main Hub page

	@Test(groups = {
			"Regression" }, description = "EL_019- Verify if clicking on the Home button in the IRTD Equipment List page navigate to the Main Hub page")
	public void EL_019() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_019- Verify if clicking on the Home button in the IRTD Equipment List page navigate to the Main Hub page");
		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		MainHubPage = tu.Click_Home_Icon_AppBar1();
		sa.assertEquals(MainHubPage.Is_mainHubPageTitle_Visible(), true,
				"FAIL: Clicking Home icon/button in bottom app bar do not redirect to Mains Hub page");
		sa.assertAll();
	}

	// EL_020- Verify if clicking on the Windows Help button in the IRTD Equipment
	// List page open the Windows Help document

	@Test(groups = {
			"Regression" }, description = "EL_020-MANUALLY- This Windows Help button will handel manually for PDF functionality ")
	public void EL_020() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_020-MANUALLY- Verify if clicking on the Windows Help button in the IRTD Equipment List page open the Windows Help document");

	}

	// EL_021-Verify if VRT Logger Equipment type tile listed the newly created VRT
	// Logger equipment in the VRT Logger Equipment List page
	// EL_022-Verify if clicking on the VRT Logger Equipment type tiles displays all
	// the equipments of that particular type

	@Test(groups = {
			"Regression" }, description = "EL_021_EL_022-EL_021-Verify if VRT Logger Equipment type tile listed the newly created VRT Logger equipment in the VRT Logger Equipment List page"
					+ "Verify if clicking on the VRT Logger Equipment type tiles displays all the equipments of that particular type")
	public void EL_021_EL_022() throws InterruptedException, IOException, ParseException {
		extentTest = extent.startTest(
				"EL_021_EL_022-EL_021-Verify if VRT Logger Equipment type tile listed the newly created VRT Logger equipment in the VRT Logger Equipment List page"
						+ "Verify if clicking on the VRT Logger Equipment type tiles displays all the equipments of that particular type");

		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();

		NewEquipmentCreation_Page.EqipCreation_VRTDLogger("VRT Logger", "ZZZ12");
		NewEquipmentCreation_Page.selectAnylogger_From_LoggerTypeComboBox(2);
		Thread.sleep(1000);
		String Mcdate = NewEquipmentCreation_Page.FetchTxt_From_ManufacturingCalDate();
		String Mcduedate = NewEquipmentCreation_Page.FetchTxt_From_ManufacturingCal_DueDate();
		NewEquipmentCreation_Page.ClickSaveButton();

		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();
		Thread.sleep(1000);

		String Act_ManufacturingCalDate = tu.convert_StringDate_to_ActualDate_inCertainFormat7(Mcdate);
		String Act_ManufacturingCalDueDate = tu.convert_StringDate_to_ActualDate_inCertainFormat7(Mcduedate);
		// String modelNum = NewEquipmentCreation_Page.Fetch_ModelNumber_Txt();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();

		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  ZZZ12");

		// String actualModelNum = Equuipment_VRTLoggersDetailspage.fetch_ModelNumber();
		String actualID = Equuipment_VRTLoggersDetailspage.fetch_ModelNumber();
		String Exp_ManufacturingCalDate = Equuipment_VRTLoggersDetailspage.fetch_ManufacturingCalDate();

		String Exp_ManufacturingCalDueDate = Equuipment_VRTLoggersDetailspage.fetch_ManufacturingCalDueDate();

		sa.assertEquals(actualID, "12",
				"FAIL: Id is not same in Equipment_VRTLogerDetailspage which has entered in New Equipment creation ");
		sa.assertEquals(Act_ManufacturingCalDate, Exp_ManufacturingCalDate,
				"FAIL: ManufacturingCalDate is not same in Equipment_IRTDDetailspage which has entered in New Equipment creation ");
		sa.assertEquals(Act_ManufacturingCalDueDate, Exp_ManufacturingCalDueDate,
				"FAIL: ManufacturingCalDueDate is not same in Equipment_IRTDDetailspage which has entered in New Equipment creation ");

		sa.assertAll();

	}

//EL_023- Verify the contents of the  Equipment tiles (for VRT Logger)

	@Test(groups = {
			"Regression" }, description = "EL_023- Verify the contents of the  Equipment tiles (for VRT Logger)")
	public void EL_023() throws InterruptedException, IOException {
		extentTest = extent.startTest("EL_023- Verify the contents of the  Equipment tiles (for VRT Logger)");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();

		sa.assertEquals(VRTLoggerHubPage.isVRTLogger_Title_Visible(), true, "Fail: IRTD Header not displayed");
		sa.assertEquals(VRTLoggerHubPage.isSerialNo_Visible(), true, "Fail: SerialNo not displayed");
		sa.assertEquals(VRTLoggerHubPage.isMfgcaldate_Visible(), true, "Fail: Mfgcaldate not displayed");
		sa.assertEquals(VRTLoggerHubPage.isMfgcalduedate_Visible(), true, "Fail: Mfgcalduedate  not displayed");
		sa.assertEquals(VRTLoggerHubPage.isLastverificationdate_Visible(), true,
				"Fail: Lastverificationdate not displayed");
		sa.assertEquals(VRTLoggerHubPage.isLoggerType_Visible(), true, "Fail: isLoggerType_Visible  not displayed");

		sa.assertAll();

	}

	// EL_024- Verify if the filter button is available in the VRT Logger equipment
	// type screen

	@Test(groups = {
			"Regression" }, description = "EL_024- Verify if the filter button is available in the VRT Logger equipment type screen")
	public void EL_024() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("EL_024- Verify if the filter button is available in the VRT Logger equipment type screen");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		sa.assertEquals(VRTLoggerHubPage.isfilter_btn_Visible(), true, "Fail: Filter button is  not displayed");
		sa.assertAll();
	}

	// EL_025- Verify if clicking on the filter button in VRT Logger equipment
	// displays the Filter pop up

	@Test(groups = {
			"Regression" }, description = "EL_025- Verify if clicking on the filter button in VRT Logger equipment displays the Filter pop up")
	public void EL_025() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_025- Verify if clicking on the filter button in VRT Logger equipment displays the Filter pop up");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();

		VRTLoggerHubPage.clickOn_filterbtn();

		sa.assertEquals(VRTLoggerHubPage.filter_By_Visible(), true,
				"Fail: Filter by option is  not displayed in filter window pop up");
		sa.assertEquals(VRTLoggerHubPage.is_OKbtnVisible_inFilterpopup(), true,
				"Fail: ok button is  not displayed in filter window pop up");

		sa.assertEquals(VRTLoggerHubPage.is_CancelbtnVisible_inFilterpopup(), true,
				"Fail: cancel button is  not displayed in filter window pop up");

		sa.assertAll();
	}

	// EL_026- Verify the contents of the Filter Pop Up in VRT Logger Equipment

	@Test(groups = {
			"Regression" }, description = "EL_026- Verify the contents of the Filter Pop Up in VRT Logger Equipment")
	public void EL_026() throws InterruptedException, IOException {
		extentTest = extent.startTest("EL_026- Verify the contents of the Filter Pop Up in VRT Logger Equipment");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_filterbtn();
		VRTLoggerHubPage.clickOn_FilterByComboBox();

		sa.assertEquals(VRTLoggerHubPage.FetchTxt_from_filterByDD(1), "Serial No.",
				"Fail: Serial No. filter_popup is  not displayed");
		sa.assertEquals(VRTLoggerHubPage.FetchTxt_from_filterByDD(2), "Logger Type",
				"Fail: Logger Type  option from filter_popup is  not displayed");
		sa.assertEquals(VRTLoggerHubPage.FetchTxt_from_filterByDD(3), "Mfg Calibration Date",
				"Fail: Mfg Calibration Date option from filter_popup is  not displayed");
		sa.assertEquals(VRTLoggerHubPage.FetchTxt_from_filterByDD(4), "Latest Mfg Calibration Due Date",
				"Fail: Latest Mfg Calibration Due Date filter_popup is  not displayed");

		sa.assertEquals(VRTLoggerHubPage.FetchTxt_from_filterByDD(5), "Verification Date",
				"Fail: Verification  Date filter_popup is  not displayed");

		sa.assertAll();

	}

	// EL_027- Verify if User can select the Kaye Serial Number option from the
	// Filter dropdown in VRT Logger Equipment

	@Test(groups = {
			"Regression" }, description = "EL_027- Verify if User can select the Kaye Serial Number option from the Filter dropdown in VRT Logger Equipment")
	public void EL_027() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_027- Verify if User can select the Kaye Serial Number option from the Filter dropdown in VRT Logger Equipment");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_filterbtn();
		VRTLoggerHubPage.clickOn_FilterByComboBox();
		VRTLoggerHubPage.SelectOptions_from_filterByDD(1);
		sa.assertEquals(VRTLoggerHubPage.FetchTxt_filterBY_selectedOption(), "Serial No.",
				"Fail: Serial No. filter_popup is  not displayed");
		sa.assertAll();
	}

	// EL_028- Verify if User can filter the VRT Logger equipments by the Kaye
	// Serial Number, if the corresponding radio button is selected

	@Test(groups = {
			"Regression" }, description = "EL_028- Verify if User can filter the VRT Logger equipments by the Kaye Serial Number, if the corresponding radio button is selected")
	public void EL_028() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_028- Verify if User can filter the VRT Logger equipments by the Kaye Serial Number, if the corresponding radio button is selected");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_filterbtn();
		VRTLoggerHubPage.clickOn_FilterByComboBox();
		VRTLoggerHubPage.SelectOptions_from_filterByDD(1);

		VRTLoggerHubPage.enterSerialNo("  U88A");
		VRTLoggerHubPage.click_OKbtn_Filterpopup();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");
		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_VrtLoggerDetailsTitle_Available(), true,
				"FAIL: Application id not redirecting to VRT Logger Details page ");
		sa.assertAll();

	}

	// EL_029-Verify if User can select the Logger Type option from the Filter
	// dropdown in VRT Logger Equipment

	@Test(groups = {
			"Regression" }, description = "EL_029-Verify if User can select the Logger Type option from the Filter dropdown in VRT Logger Equipment")
	public void EL_029() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_029-Verify if User can select the Logger Type option from the Filter dropdown in VRT Logger Equipment");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_filterbtn();
		VRTLoggerHubPage.clickOn_FilterByComboBox();
		VRTLoggerHubPage.SelectOptions_from_filterByDD(2);

		sa.assertEquals(VRTLoggerHubPage.FetchTxt_filterBY_selectedOption(), "Logger Type",
				"Fail: Logger Type filter_popup is  not displayed");
		sa.assertAll();

	}

	// EL_030- Verify if User can filter the VRT Logger equipments by the Logger
	// Type, if the corresponding option is selected

	@Test(groups = {
			"Regression" }, description = "EL_030- Verify if User can filter the VRT Logger equipments by the Logger Type, if the corresponding option is selecte")
	public void EL_030() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_030- Verify if User can filter the VRT Logger equipments by the Logger Type, if the corresponding option is selecte");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_filterbtn();
		VRTLoggerHubPage.clickOn_FilterByComboBox();
		VRTLoggerHubPage.SelectOptions_from_filterByDD(2);
		VRTLoggerHubPage.click_loggertype();
		VRTLoggerHubPage.sL_From_LoggerTypeComboBox(2);
		VRTLoggerHubPage.click_OKbtn_Filterpopup();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");
		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_VrtLoggerDetailsTitle_Available(), true,
				"FAIL: Application id not redirecting to VRT Logger Details page");
		sa.assertAll();

	}

	// EL_031- Verify if User can select the Mfg Calibration Date option from the
	// Filter dropdown in VRT Logger Equipment

	@Test(groups = {
			"Regression" }, description = "EL_031- Verify if User can select the Mfg Calibration Date option from the Filter dropdown in VRT Logger Equipment")
	public void EL_031() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_031- Verify if User can select the Mfg Calibration Date option from the Filter dropdown in VRT Logger Equipment");

		SoftAssert sa = new SoftAssert();
		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_filterbtn();
		VRTLoggerHubPage.clickOn_FilterByComboBox();
		VRTLoggerHubPage.SelectOptions_from_filterByDD(3);

		sa.assertEquals(VRTLoggerHubPage.FetchTxt_filterBY_selectedOption(), "Mfg Calibration Date",
				"Fail: Mfg Calibration Date filter popup is  not displayed");
		sa.assertAll();

	}

	// EL_032- Verify if User can filter the VRT Logger equipments by the Mfg
	// Calibration Date, if the corresponding option is selected

	@Test(groups = {
			"Regression" }, description = "EL_032- Verify if User can filter the VRT Logger equipments by the Mfg Calibration Date, if the corresponding  option is selected")
	public void EL_032() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_032- Verify if User can filter the VRT Logger equipments by the Mfg Calibration Date, if the corresponding  option is selected");

		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();

		NewEquipmentCreation_Page.EqipCreation_VRTDLogger("VRT Logger", "AL32");
		NewEquipmentCreation_Page.selectAnylogger_From_LoggerTypeComboBox(2);
		Thread.sleep(1000);
		NewEquipmentCreation_Page.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();
		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_filterbtn();
		VRTLoggerHubPage.clickOn_FilterByComboBox();
		VRTLoggerHubPage.SelectOptions_from_filterByDD(3);
		VRTLoggerHubPage.Select_datepickerfield(0);
		VRTLoggerHubPage.Select_datepickerfield(1);
		VRTLoggerHubPage.click_OKbtn_Filterpopup();

		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  AL32");

		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_VrtLoggerDetailsTitle_Available(), true,
				"Fail: Irtd Details title not visible");
		sa.assertAll();

	}

//EL_033- Verify if User can select the Mfg Calibration Due Date option from the Filter dropdown in VRT Logger Equipment

	@Test(groups = {
			"Regression" }, description = "EL_033- Verify if User can select the Mfg Calibration Due Date option from the Filter dropdown in VRT Logger Equipment")
	public void EL_033() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_033- Verify if User can select the Mfg Calibration Due Date option from the Filter dropdown in VRT Logger Equipment");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_filterbtn();
		VRTLoggerHubPage.clickOn_FilterByComboBox();
		VRTLoggerHubPage.SelectOptions_from_filterByDD(4);

		sa.assertEquals(VRTLoggerHubPage.FetchTxt_filterBY_selectedOption(), "Latest Mfg Calibration Due Date",
				"Fail: Logger Type filter_popup is  not displayed");
		sa.assertAll();

	}

	// EL_034- Verify if User can filter the VRT Logger equipments by the Mfg
	// Calibration Due Date, if the corresponding option is selected

	// EL_035- Verify if User can select the Verification Date option from the
	// Filter dropdown in VRT Logger Equipment

	@Test(groups = {
			"Regression" }, description = "EL_035- Verify if User can select the Last Verification Date option from the Filter dropdown in VRT Logger Equipment")
	public void EL_035() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_035- Verify if User can select the Last Verification Date option from the Filter dropdown in VRT Logger Equipment");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_filterbtn();
		VRTLoggerHubPage.clickOn_FilterByComboBox();
		VRTLoggerHubPage.SelectOptions_from_filterByDD(5);

		sa.assertEquals(VRTLoggerHubPage.FetchTxt_filterBY_selectedOption(), "Verification Date",
				"Fail: Logger Type filter_popup is  not displayed");
		sa.assertAll();

	}

// Verification Date is not available in Equipment list

//EL_036- Verify if User can filter the VRT Logger equipments by the Last Verification Date, if the corresponding radio button is selected
//EL_037- Verify if user can select only one filter option to filter the loggers in VRT Logger Equipment Filter Popup

	@Test(groups = {
			"Regression" }, description = "EL_036-EL_037- Verify if User can filter the VRT Logger equipments by the Last Verification Date, if the corresponding radio button is selected")
	public void EL_036_EL_037() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_036-EL_037- Verify if User can filter the VRT Logger equipments by the Last Verification Date, if the corresponding radio button is selected");

		SoftAssert sa = new SoftAssert();

		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();

		NewEquipmentCreation_Page.EqipCreation_VRTDLogger("VRT Logger", "AB36");
		NewEquipmentCreation_Page.selectAnylogger_From_LoggerTypeComboBox(2);
		NewEquipmentCreation_Page.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_filterbtn();
		VRTLoggerHubPage.clickOn_FilterByComboBox();
		VRTLoggerHubPage.SelectOptions_from_filterByDD(5);
		VRTLoggerHubPage.Select_datepickerfield(0);
		VRTLoggerHubPage.Select_datepickerfield(1);
		VRTLoggerHubPage.click_OKbtn_Filterpopup();

		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  AB36");

		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_VrtLoggerDetailsTitle_Available(), true,
				"Fail: Irtd Details title not visible");
		sa.assertAll();

	}

//EL_038- Verify if clicking on the cancel button in the Filter VRT Logger Equipments pop up closes the pop up without filtering the equipments

	@Test(groups = {
			"Regression" }, description = "EL_038- Verify if clicking on the cancel button in the Filter VRT Logger Equipments pop up closes the pop up without filtering the equipments")
	public void EL_038() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_038- Verify if clicking on the cancel button in the Filter VRT Logger Equipments pop up closes the pop up without filtering the equipments");
		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_filterbtn();
		VRTLoggerHubPage.clickOn_FilterByComboBox();
		VRTLoggerHubPage.SelectOptions_from_filterByDD(2);
		Thread.sleep(1000);
		VRTLoggerHubPage.click_loggertype();
		VRTLoggerHubPage.sL_From_LoggerTypeComboBox(2);
		VRTLoggerHubPage.click_Cancelbtn_Filterpopup();
		VRTLoggerHubPage.is_FilterPopup_Visible();

	}

//EL_039- Verify if clicking on the Clear Filter option in the VRT Logger Equipment List page clears all the existing filters

	@Test(groups = {
			"Regression" }, description = "EL_039- Verify if clicking on the Clear Filter option in the VRT Logger Equipment List page clears all the existing filters")
	public void EL_039() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_039- Verify if clicking on the Clear Filter option in the VRT Logger Equipment List page clears all the existing filters");
		SoftAssert sa = new SoftAssert();
		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_filterbtn();
		VRTLoggerHubPage.clickOn_FilterByComboBox();
		VRTLoggerHubPage.SelectOptions_from_filterByDD(2);
		Thread.sleep(1000);
		VRTLoggerHubPage.click_loggertype();
		VRTLoggerHubPage.sL_From_LoggerTypeComboBox(2);

		VRTLoggerHubPage.click_OKbtn_Filterpopup();
		VRTLoggerHubPage.clickOn_RefreshFilterBtn();
		EquipmentHubPage = VRTLoggerHubPage.click_Back_btn();
		Thread.sleep(1000);

		sa.assertEquals(EquipmentHubPage.IsEquipmentHeader_Visible(), true, "FAIL: Filter button is not dispplaying ");
		sa.assertAll();

	}

//EL_042- Verify if clicking on the Help button in the VRT Logger Equipment List page displays the online help menu for that page

	@Test(description = "EL_042- Verify if clicking on the Help button in the VRT Logger Equipment List page displays the online help menu for that page")
	public void EL_042() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EL_042- Verify if clicking on the Help button in the VRT Logger Equipment List page displays the online help menu for that page");
		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_filterbtn();
		tu.Click_Help_Icon_AppBar();
		sa.assertEquals(tu.get__HelpMenu_HdrText(), "The Equipment Hub",
				"FAIL: Clicking Help icon/button in bottom app bar"
						+ "do not display the Equipment Details Help context window");
		sa.assertAll();
	}

//EL_043- Verify if clicking on the About button in the VRT Logger Equipment List page displays the current software version, Console IP

	@Test(dataProvider = "SET033", dataProviderClass = setupCreationUtility.class, description = "EL_043- Verify if clicking on the About button in the VRT Logger Equipment List page displays the current software version, Console IP")
	public void EL_043(String SWVer) throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_043- Verify if clicking on the About button in the VRT Logger Equipment List page displays the current software version, Console IP");
		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_filterbtn();
		tu.Click_About_Icon_AppBar();

		sa.assertEquals(tu.SWversion_InAboutWndw(), SWVer,
				"FAIL: Incorrect SW version & Console IP adress displayed in About window");

		sa.assertEquals(tu.consoleIP_InAboutWndw(), tu.system_IPadress(),
				"FAIL: Incorrect SW version & Console IP adress displayed in About window");

		sa.assertAll();
	}

//EL_044- Verify if clicking on the Home button in the VRT Logger Equipment List page navigate to the Main Hub page

	@Test(groups = {
			"Regression" }, description = "EL_044- Verify if clicking on the Home button in the VRT Logger Equipment List page navigate to the Main Hub page")
	public void EL_044() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_044- Verify if clicking on the Home button in the VRT Logger Equipment List page navigate to the Main Hub page");
		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_filterbtn();
		MainHubPage = tu.Click_Home_Icon_AppBar1();
		sa.assertEquals(MainHubPage.Is_mainHubPageTitle_Visible(), true,
				"FAIL: Clicking Home icon/button in bottom app bar do not redirect to Mains Hub page");
		sa.assertAll();
	}

//EL_046- Verify if the Print button is available in the VRT Logger equipment list screen

	@Test(groups = {
			"Regression" }, description = "EL_046- Verify if the Print button is available in the VRT Logger equipment list screen")
	public void EL_046() throws InterruptedException, IOException {

		extentTest = extent
				.startTest("EL_046- Verify if the Print button is available in the VRT Logger equipment list screen");
		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();

		sa.assertEquals(VRTLoggerHubPage.Is_PrintBtnVisible(), true, "FAIL: print button is not available ");
		sa.assertAll();
	}

//EL_047- Verify if clicking on the Print button in VRT Logger equipment opens the Print selection window

	@Test(groups = {
			"Regression" }, description = "EL_047- Verify if clicking on the Print button in VRT Logger equipment opens the Print selection window")
	public void EL_047() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_047- Verify if clicking on the Print button in VRT Logger equipment opens the Print selection window");
		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_PrintBtn();
		sa.assertEquals(VRTLoggerHubPage.is_PrintReportsWindow_Visible(), true,
				"FAIL: Print Reports Window is not available");
		sa.assertAll();
	}

	// EL_048 - Verify the contents of the VRT Logger Print selection window

	@Test(groups = {
			"Regression" }, description = "EL_048- Verify the contents of the VRT Logger Print selection window")

	public void EL_048() throws InterruptedException, IOException {

		extentTest = extent.startTest("EL_048- Verify the contents of the VRT Logger Print selection window");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();

		VRTLoggerHubPage.clickOn_PrintBtn();

		sa.assertEquals(VRTLoggerHubPage.is_PrintReportsWindow_Visible(), true,
				"FAIL: Print Reports Window is not available");

		sa.assertEquals(VRTLoggerHubPage.getTextOf_FilterBy_Options(0), "Loggers List",
				"FAIL:Loggers List option is not available");

		sa.assertEquals(VRTLoggerHubPage.getTextOf_FilterBy_Options(1), "Mfg Calibration",
				"FAIL: Mfg Calibration is not available");

		sa.assertEquals(VRTLoggerHubPage.getTextOf_FilterBy_Options(2), "Last Verification",
				"FAIL: Last Verification is not available");

		sa.assertEquals(VRTLoggerHubPage.is_DeSelectAllbtn_Visible(), true,
				"FAIL: De select all button  is not available");

		sa.assertEquals(VRTLoggerHubPage.is_PrintReportBtn_Visible(), true,
				"FAIL: Print Report Button  is not available");

		sa.assertEquals(VRTLoggerHubPage.is_CancelBtn_Visible(), true, "FAIL: Print Report Button  is not available");

		sa.assertAll();
	}

	// EL_049- Verify if user can filter the VRT Logger equipment available by
	// Logger list

	@Test(groups = {
			"Regression" }, description = "EL_049- Verify if user can filter the VRT Logger equipment available by Logger list")

	public void EL_049() throws InterruptedException, IOException {

		extentTest = extent
				.startTest("EL_049- Verify if user can filter the VRT Logger equipment available by Logger list");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();

	}

	// EL_050- Verify if user can filter the VRT Logger equipment available by Mfg Calibration Date

	@Test(groups = {
			"Regression" }, description = "EL_050- Verify if user can filter the VRT Logger "
					+ "equipment available by Mfg Calibration Date")

	public void EL_050() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_050- Verify if user can filter the VRT Logger equipment available by Mfg Calibration Date");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();

		NewEquipmentCreation_Page.EqipCreation_VRTDLogger("VRT Logger", "A50");
		NewEquipmentCreation_Page.selectAnylogger_From_LoggerTypeComboBox(2);
		Thread.sleep(1000);
		NewEquipmentCreation_Page.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_PrintBtn();
		VRTLoggerHubPage.click_FilterBy_Options(1);
		VRTLoggerHubPage.Select_datepickerfield(0);
		VRTLoggerHubPage.Select_VRTSerialNo_printLoggers("  A50");
		// VRTLoggerHubPage.clickOn_PrintReportBtn();
		sa.assertEquals(VRTLoggerHubPage.is_loggerList_selected(0), true, "Fail: Irtd Details title not visible");
		sa.assertAll();

	}

	// EL_051- Verify if user can filter the VRT Logger equipment available by
	// Verification Date
	@Test(groups = {
			"Regression" }, description = "EL_051- Verify if user can filter the VRT Logger equipment available by Verification Date")

	public void EL_051() throws InterruptedException, IOException {

		extentTest = extent
				.startTest("EL_051- Verify if user can filter the VRT Logger equipment available by Verification Date");

		SoftAssert sa = new SoftAssert();
		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();

		NewEquipmentCreation_Page.EqipCreation_VRTDLogger("VRT Logger", "FL051");
		NewEquipmentCreation_Page.selectAnylogger_From_LoggerTypeComboBox(2);
		Thread.sleep(1000);
		NewEquipmentCreation_Page.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_PrintBtn();
		VRTLoggerHubPage.click_FilterBy_Options(2);
		VRTLoggerHubPage.Select_datepickerfield(1);
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  FL051");

		sa.assertEquals(VRTLoggerHubPage.is_loggerList_selected(0), true, "Fail: Irtd Details title not visible");
		sa.assertAll();

	}

	// EL_052- Verify if to filter the VRT Logger equipment, Application will auto
	// select the radio box for the corresponding filter option

	@Test(groups = {
			"Regression" }, description = "EL_052- Verify if to filter the VRT Logger equipment, Application will auto  select the radio box for the corresponding filter option")

	public void EL_052() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_052- Verify if to filter the VRT Logger equipment, Application will auto  select the radio box for the corresponding filter option");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();

		VRTLoggerHubPage.clickOn_PrintBtn();
		VRTLoggerHubPage.Select_datepickerfield(1);

		sa.assertEquals(VRTLoggerHubPage.is_FilterByRadioButton_Selected(2), true,
				"Fail: the Mfg Calibration date  radio button is not auto selected");
		sa.assertAll();
	}

	// EL_053- Verify if Logger List radio button is selected by default in the VRT
	// Logger Print screen

	@Test(groups = {
			"Regression" }, description = "EL_053- Verify if Logger List radio button is selected by default in the VRT Logger Print screen")

	public void EL_053() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_053- Verify if Logger List radio button is selected by default in the VRT Logger Print screen");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();

		VRTLoggerHubPage.clickOn_PrintBtn();

		sa.assertEquals(VRTLoggerHubPage.is_LoggersListBtn_selected(), true,
				"FAIL: Logger List radio button is not selected by default");
		sa.assertAll();
	}

// EL_054- Verify user can select all the available equipment by clicking on the
// Select All button

	@Test(groups = {
			"Regression" }, description = "EL_054- Verify user can select all the available equipment by clicking on the Select All button")

	public void EL_054() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_054- Verify user can select all the available equipment by clicking on the Select All button");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();

		VRTLoggerHubPage.clickOn_PrintBtn();
		VRTLoggerHubPage.click_DeSelectAll_btn();
		VRTLoggerHubPage.click_selectAll_btn();
		sa.assertEquals(VRTLoggerHubPage.is_loggerList_selected(0), true,
				"FAIL: logger displayed in the screen is not selected");
		sa.assertAll();
	}

// EL_055- Verify if on selecting all the loggers, the Select All button changes
// to Deselect All
// EL_056- Verify if clicking on the Deselect All button removes all the
// equipment selection

	@Test(groups = {
			"Regression" }, description = "EL_055_EL_056- Verify if on selecting all the loggers, the Select All button changes to Deselect All")

	public void EL_055_EL_056() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_055_EL_056- Verify if on selecting all the loggers, the Select All button changes to Deselect All");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();

		VRTLoggerHubPage.clickOn_PrintBtn();
		VRTLoggerHubPage.click_FilterBy_Options(1);
		VRTLoggerHubPage.Select_datepickerfield(0);
		VRTLoggerHubPage.click_selectAll_btn();
		sa.assertEquals(VRTLoggerHubPage.is_loggerList_selected(0), true,
				"FAIL: logger displayed in the screen is not selected");

		VRTLoggerHubPage.click_DeSelectAll_btn();

		sa.assertEquals(VRTLoggerHubPage.is_loggerList_selected(0), false,
				"FAIL: logger displayed in the screen is not selected");
		sa.assertAll();
	}

//EL_057- Verify if user can select individual loggers by clicking on the available equipment tiles
//EL_058 - Verify if user can deselect a selected logger individually by clicking the selected equipment tile

	@Test(groups = {
			"Regression" }, description = "EL_057_EL_058- Verify if user can select/deselect individual loggers by clicking on the available equipment tiles")

	public void EL_057_EL_058() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_057_EL_058- Verify if user can select/deselect individual loggers by clicking on the available equipment tiles");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();

		VRTLoggerHubPage.clickOn_PrintBtn();

		VRTLoggerHubPage.click_DeSelectAll_btn();

		Thread.sleep(1000);

		sa.assertEquals(VRTLoggerHubPage.is_loggerList_selected(0), false,
				"FAIL: logger displayed in the screen is not selected");

		sa.assertAll();
	}

//EL_059- Verify if a selected equipment is displayed with a red tick on the right top corner of the equipment tile
//EL_059 MANUAL 

//EL_060- Verify if user is not allowed to print a report without selecting any equipment

	@Test(groups = {
			"Regression" }, description = "EL_060- Verify if user is not allowed to print a report without selecting any equipment")

	public void EL_060() throws InterruptedException, IOException {

		extentTest = extent
				.startTest("EL_060- Verify if user is not allowed to print a report without selecting any equipment");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_PrintBtn();
		VRTLoggerHubPage.click_DeSelectAll_btn();
		VRTLoggerHubPage.clickOn_PrintReportBtn();
		sa.assertEquals(tu.get_AlertMsg_text(), "Please select atleast one Equipment/Logger.",
				"Fail: Alert message is not correct");
		sa.assertAll();
	}

//EL_061- Verify if clicking on the Print Report button generates the PDF report after selecting required equipment by Logger List

	@Test(groups = {
			"Regression" }, description = "EL_061- Verify if clicking on the Print Report button generates the PDF report after selecting required equipment by Logger List")

	public void EL_061() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_061- Verify if clicking on the Print Report button generates the PDF report after selecting required equipment by Logger List");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();

		VRTLoggerHubPage.clickOn_PrintBtn();
		VRTLoggerHubPage.click_DeSelectAll_btn();

		VRTLoggerHubPage.clickOn_PrintReportBtn();
		Thread.sleep(1000);
		sa.assertEquals(tu.get_AlertMsg_text(), "Please select atleast one Equipment/Logger.",
				"Fail: Alert message is not correct");

		sa.assertAll();

	}

//EL_064- Verify if clicking on the Cancel button in the Print report screen redirects to the corresponding Equipment List screen

	@Test(groups = {
			"Regression" }, description = "EL_064- Verify if clicking on the Cancel button in the Print report screen redirects to the corresponding Equipment List screen")

	public void EL_064() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EL_064- Verify if clicking on the Cancel button in the Print report screen redirects to the corresponding Equipment List screen");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		VRTLoggerHubPage.clickOn_PrintBtn();
		VRTLoggerHubPage.click_CancelBtn();

		sa.assertEquals(VRTLoggerHubPage.isVRTLogger_Title_Visible(), true, "Fail:  VRTLogger title is not visible");
		sa.assertAll();
	}

}
