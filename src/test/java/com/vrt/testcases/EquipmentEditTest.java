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
import java.util.List;

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
import com.vrt.pages.SyncInAssetListPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.AuditPage;
import com.vrt.pages.EquipmentHubPage;
import com.vrt.pages.NewEquipmentCreation_Page;
import com.vrt.pages.FileManagementPage;
import com.vrt.pages.Equipment_IRTDDetailspage;
import com.vrt.pages.IRTDHubPage;
import com.vrt.utility.EqupmentUtility;
import com.vrt.utility.TestUtilities;
import com.vrt.utility.assetCreationUtility;
import com.vrt.utility.setupCreationUtility;
import com.vrt.utility.EqupmentUtility;
import com.vrt.pages.SyncInPage;
import com.vrt.pages.SyncInAssetListPage;
import com.vrt.pages.VRTLoggerHubPage;
import com.vrt.pages.Equuipment_VRTLoggersDetailspage;

public class EquipmentEditTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p

	public EquipmentEditTest() throws IOException {
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
	IRTDHubPage IRTDHubPage;
	Equipment_IRTDDetailspage Equipment_IRTDDetailspage;
	FileManagementPage FileManagementPage;
	SyncInPage SyncInPage;
	SyncInAssetListPage SyncInAssetListPage;
	VRTLoggerHubPage VRTLoggerHubPage;
	Equuipment_VRTLoggersDetailspage Equuipment_VRTLoggersDetailspage;

	// Ensure the User has got rights to create Assets
	// @BeforeTest
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, AWTException {
		extent = new ExtentReports(
				System.getProperty("user.dir") + "/test-output/ER_" + "EquipmentCreationTest" + ".html", true);
		extent.addSystemInfo("TestSuiteName", "Asset Creation Test");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("EquipmentCreation Test in Progress..");

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

		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
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
		System.out.println("EquipmentCreation Test Completed.");
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

	// EEIRTD_001-Verify if user can review,edit and Save IRTD equipment by clicking
	// on Save button in IRTD-Details Screen

	@Test(groups = {
			"Regression" }, description = "EEIRTD_001- Verify  if the following contents are available in the Review Equipment screen")
	public void EEIRTD_001() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_001- Verify  if the following contents are available in the Review Equipment screen");
		SoftAssert sa = new SoftAssert();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");

		Thread.sleep(1000);

		sa.assertEquals(Equipment_IRTDDetailspage.is_IRTDserialNo_Visible(), true,
				"FAIL: Serial number is not available in Equipment_IRTDDetailspage");
		sa.assertEquals(Equipment_IRTDDetailspage.is_ID_Visible(), true,
				"FAIL: ID is not available in Equipment_IRTDDetailspage");
		sa.assertEquals(Equipment_IRTDDetailspage.is_ManufacturingCalDueDate_Visible(), true,
				"FAIL: ManufacturingCalDueDate is not available in Equipment_IRTDDetailspage");
		sa.assertEquals(Equipment_IRTDDetailspage.is_ManufacturingCalDate_Visible(), true,
				"FAIL: ManufacturingCalDate is not available in Equipment_IRTDDetailspage");
		sa.assertEquals(Equipment_IRTDDetailspage.is_Browse_btn_Visible(), true,
				"FAIL: Browse btn is not available in Equipment_IRTDDetailspage");
		sa.assertEquals(Equipment_IRTDDetailspage.is_Camera_Btn_Visible(), true,
				"FAIL: camera btn is not available in Equipment_IRTDDetailspage");

		sa.assertEquals(Equipment_IRTDDetailspage.is_SaveBtn_Visible(), true,
				"FAIL: save btn is not available in Equipment_IRTDDetailspage");

		sa.assertEquals(Equipment_IRTDDetailspage.is_CancelBtn_Visible(), true,
				"FAIL: cancel btn is not available in Equipment_IRTDDetailspage");

		sa.assertEquals(Equipment_IRTDDetailspage.is_Back_btn_Visible(), true,
				"FAIL: cancel btn is not available in Equipment_IRTDDetailspage");

		sa.assertEquals(Equipment_IRTDDetailspage.is_DeleteBtn_Visible(), true,
				"FAIL: Delete btn is not available in Equipment_IRTDDetailspage");
		sa.assertAll();
	}

	// EEIRTD_001A- Verify if user can review,edit and Save IRTD equipment by
	// clicking on Save button in IRTD-Details Screen

	@Test(groups = {
			"Regression" }, description = "EEIRTD_001- Verify if user can review,edit and Save IRTD equipment by clicking on Save button in IRTD-Details Screen")
	public void EEIRTD_001A() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_001- Verify if user can review,edit and Save IRTD equipment by clicking on Save button in IRTD-Details Screen");
		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		Equipment_IRTDDetailspage.enter_IRTDEquipName("test1");
		String expid = Equipment_IRTDDetailspage.fetch_ID();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String actmsg = Equipment_IRTDDetailspage.AlertMsg();
		String Expmsg = "Equipment \" J1129\" Updated successfully.";
		sa.assertEquals(actmsg, Expmsg, "FAIL: Alert message is not correct");

		IRTDHubPage = Equipment_IRTDDetailspage.click_Back_btn();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		String actid = Equipment_IRTDDetailspage.fetch_ID();
		sa.assertEquals(actid, expid, "FAIL: New ID has not updated");

		sa.assertAll();

	}

	// EEIRTD_002- Verify if clicking on the Home button in the IRTD-Details screen,
	// navigate to the Main Hub page

	@Test(groups = {
			"Regression" }, description = "EEIRTD_001-Verify if user can review,edit and Save IRTD equipment by clicking on Save button in IRTD-Details Screen")
	public void EEIRTD_002() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_001-Verify if user can review,edit and Save IRTD equipment by clicking on Save button in IRTD-Details Screen");
		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		MainHubPage = tu.Click_Home_Icon_AppBar();
		sa.assertEquals(MainHubPage.Is_mainHubPageTitle_Visible(), true,
				"FAIL: Clicking Home icon/button in bottom app bar do not redirect to Mains Hub page");
		sa.assertAll();
	}

	// EEIRTD_003 - Verify if clicking on the Help button in the IRTD-Details
	// Screen, displays the online help menu for that page

	@Test(description = "EEIRTD_003 - Verify if clicking on the Help button in the IRTD-Details Screen, displays the online help menu for that page")
	public void EEIRTD_003() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EEIRTD_003 - Verify if clicking on the Help button in the IRTD-Details Screen, displays the online help menu for that page");
		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		tu.Click_Help_Icon_AppBar();
		sa.assertEquals(tu.get__HelpMenu_HdrText(), "Equipment Details",
				"FAIL: Clicking Help icon/button in bottom app bar"
						+ "do not display the Equipment Details Help context window");
		sa.assertAll();
	}

	// EEIRTD_004-Verify if clicking on the Windows Help button in the IRTD-Details
	// screen, open the Windows Help document

	// EEIRTD_005-Verify if clicking on the About button in the IRTD-Details screen
	// displays the current software version, Console IP

	@Test(dataProvider = "SET033", dataProviderClass = setupCreationUtility.class, description = "EEIRTD_005-Verify if clicking on the About button in the IRTD-Details screen displays the current software version, Console IP")
	public void ASST090(String SWVer) throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_005-Verify if clicking on the About button in the IRTD-Details screen displays the current software version, Console IP");
		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		tu.Click_About_Icon_AppBar();

		sa.assertEquals(tu.SWversion_InAboutWndw(), SWVer,
				"FAIL: Incorrect SW version & Console IP adress displayed in About window");

		sa.assertEquals(tu.consoleIP_InAboutWndw(), tu.system_IPadress(),
				"FAIL: Incorrect SW version & Console IP adress displayed in About window");

		sa.assertAll();
	}

	// EEIRTD_006 - Verify if the Equipment Name field in IRTD-Details Screen
	// accepts Alphanumeric character and special characters like -, _ and slash
	// (both forward and backward)

	@Test(dataProvider = "EEIRTD_006", dataProviderClass = EqupmentUtility.class, description = "EEIRTD_006 - Verify if the Equipment Name field in IRTD-Details Screen accepts Alphanumeric character and special characters like -, _ and slash (both forward and backward)")
	public void EEIRTD_006(String Ename) throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_006 - Verify if the Equipment Name field in IRTD-Details Screen accepts Alphanumeric character and special characters like -, _ and slash (both forward and backward)");
		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		Equipment_IRTDDetailspage.enter_IRTDEquipName(Ename);
		sa.assertEquals(Equipment_IRTDDetailspage.UserLoginPopupVisible(), true,
				"FAIL:User Login Popup is not visible");
		sa.assertAll();
	}

	// EEIRTD_006 - Verify if the Equipment Name field in IRTD-Details Screen
	// accepts Alphanumeric character and special characters like -, _ and slash
	// (both forward and backward)

	@Test(dataProvider = "EEIRTD_006A", dataProviderClass = EqupmentUtility.class, description = "EEIRTD_006 - Verify if the Equipment Name field in IRTD-Details Screen accepts Alphanumeric character and special characters like -, _ and slash (both forward and backward)")
	public void EEIRTD_006A(String Ename, String Expalert) throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_006 - Verify if the Equipment Name field in IRTD-Details Screen accepts Alphanumeric character and special characters like -, _ and slash (both forward and backward)");
		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");

		Equipment_IRTDDetailspage.enter_IRTDEquipName(Ename);
		sa.assertEquals(Equipment_IRTDDetailspage.AlertMsg(), Expalert, "FAIL:User Login Popup is not visible");
		sa.assertAll();
	}

	// EEIRTD_007- Verify if Equipment Name field in IRTD-Details Screen accepts
	// valid character length

	@Test(description = "EEIRTD_007- Verify if Equipment Name  field in IRTD-Details Screen accepts valid character length")
	public void EEIRTD_007() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_007- Verify if Equipment Name  field in IRTD-Details Screen accepts valid character length");
		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		Equipment_IRTDDetailspage.enter_EquipName("12345678901234AB");
		Thread.sleep(1000);
		String s = Equipment_IRTDDetailspage.fetch_ID();
		sa.assertEquals(s.length(), 16,
				"FAIL:  character length of Equipment Name  field in IRTD-Details Screen is not 16 ");
		sa.assertAll();
	}

	// EEIRTD_007A- Verify if Equipment Name field in IRTD-Details Screen accepts
	// Invalid character length

	@Test(description = "EEIRTD_007A- Verify if Equipment Name  field in IRTD-Details Screen accepts Invalid character length")
	public void EEIRTD_007A() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_007A- Verify if Equipment Name  field in IRTD-Details Screen accepts Invalid character length");
		SoftAssert sa = new SoftAssert();

		// Enter more than 16 chars
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");

		Equipment_IRTDDetailspage.enter_EquipName("12345678901234ABdcjh");
		Thread.sleep(1000);
		String s = Equipment_IRTDDetailspage.fetch_ID();
		sa.assertEquals(s.length(), 16,
				"FAIL: Equipment Name  field in IRTD-Details Screen accepts more than 16  character length");
		sa.assertAll();
	}

	// EEIRTD_008- Verify if the Equipment Name field in IRTD-Details Screen is not
	// a mandatory field

	@Test(description = "EEIRTD_008- Verify if the Equipment Name field in IRTD-Details Screen is not a mandatory field")
	public void EEIRTD_008() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_008- Verify if the Equipment Name field in IRTD-Details Screen is not a mandatory field");
		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");

		Equipment_IRTDDetailspage.clear_IDname();
		Equipment_IRTDDetailspage.ClickSaveButton();
		sa.assertEquals(Equipment_IRTDDetailspage.UserLoginPopupVisible(), true,
				"FAIL:User Login Popup is not visible");
		sa.assertAll();
	}

	// EEIRTD_009- Verify if user has a scope to add a new equipment picture with
	// proper size in IRTD-Details Screen

	@Test(description = "EEIRTD_009- Verify if user has a scope to add a new equipment picture with proper size in IRTD-Details Screen")
	public void EEIRTD_009() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_009- Verify if user has a scope to add a new equipment picture with proper size in IRTD-Details Screen");
		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		Equipment_IRTDDetailspage.click_Browse_btn();
		tu.uploadDoc("VRT_Pro.JPG");

		// Capture the expected Image added to the Asset placeholder 1
		Equipment_IRTDDetailspage.Capture_EqpImg("Expected_VRT_Pro_Img");

		Equipment_IRTDDetailspage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		IRTDHubPage = Equipment_IRTDDetailspage.click_Back_btn();

		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");

		// Capture the actual Image saved to the Equipment
		Equipment_IRTDDetailspage.Capture_EqpImg("Actual_VRT_Pro_Img");

		boolean status_ImgCompare = tu.compareImage("Expected_VRT_Pro_Img", "Actual_VRT_Pro_Img");

		sa.assertEquals(status_ImgCompare, false, "FAIL: The Equipment Image saved is not same as what was selected");

		sa.assertAll();
	}

	// EEIRTD_010- Verify if User is allowed to upload or change an image of valid
	// image size in IRTD-Details Screen

	@Test(description = "EEIRTD_010- Verify if User is allowed to upload or change an image of valid image size in IRTD-Details Screen")
	public void EEIRTD_010() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_010- Verify if User is allowed to upload or change an image of valid image size in IRTD-Details Screen");
		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		Equipment_IRTDDetailspage.capture_Camera_Img();

		// Capture the expected Image added to the Asset placeholder 1
		Equipment_IRTDDetailspage.Capture_EqpImg("Expected_camera_Image");

		Equipment_IRTDDetailspage.ClickSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		IRTDHubPage = Equipment_IRTDDetailspage.click_Back_btn();

		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");

		// Capture the actual Image saved to the Equipment image holder
		Equipment_IRTDDetailspage.Capture_EqpImg("Acctual_camera_Image");

		boolean status_ImgCompare = tu.compareImage("Expected_camera_Image", "Acctual_camera_Image");

		sa.assertEquals(status_ImgCompare, false, "FAIL: The Equipment Image saved is not same as what was captured");

		sa.assertAll();

	}

	// EEIRTD_011- Verify if User is not allowed to upload or change an image of
	// invalid image size in IRTD-Details Screen

	@Test(description = "EEIRTD_011- Verify if User is not allowed to upload or change an image of invalid image size in IRTD-Details Screen")
	public void EEIRTD_011() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_011- Verify if User is not allowed to upload or change an image of invalid image size in IRTD-Details Screen");
		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		Equipment_IRTDDetailspage.click_Browse_btn();
		tu.uploadDoc("UserimageInValid.jpg");

		String ExpAlrtMsg = "Select image file with size less than 5 mb";
		String ActAlertMsg = Equipment_IRTDDetailspage.AlertMsg();

		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL:Alert message should be displayed as-Select image file with size less than 5 mb ");
		sa.assertAll();
	}

	// EEIRTD_012- Verify if adding an equipment image is not a mandatory field in
	// IRTD-Details Screen

	@Test(description = "EEIRTD_012- Verify if adding an equipment image is not a mandatory field in IRTD-Details Screen")
	public void EEIRTD_012() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_012- Verify if adding an equipment image is not a mandatory field in IRTD-Details Screen");
		SoftAssert sa = new SoftAssert();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		Equipment_IRTDDetailspage.ClickSaveButton();
		sa.assertEquals(Equipment_IRTDDetailspage.UserLoginPopupVisible(), true,
				"FAIL:User Login Popup is not visible");
		sa.assertAll();
	}

//EEIRTD_013- Verify if User can delete the equipment image by clicking on the Delete option available in the equipment image slot in the IRTD-Details Screens

	@Test(description = "EEIRTD_013- Verify if User can delete the equipment image by clicking on the Delete option available in the equipment image slot in the IRTD-Details Screens")
	public void EEIRTD_013() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_013- Verify if User can delete the equipment image by clicking on the Delete option available in the equipment image slot in the IRTD-Details Screens");
		SoftAssert sa = new SoftAssert();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		Equipment_IRTDDetailspage.click_Browse_btn();
		tu.uploadDoc("VRT_Pro.JPG");

		Equipment_IRTDDetailspage.Capture_EqpImg("Expected_VRT_Pro_Img");
		Thread.sleep(1000);
		Equipment_IRTDDetailspage.click_DeleteEquipImage();

		IRTDHubPage = Equipment_IRTDDetailspage.click_Back_btn();

		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		// Capture the actual Image saved to the Equipment
		Equipment_IRTDDetailspage.Capture_EqpImg("Actual_VRT_Pro_Img");

		boolean status_ImgCompare = tu.compareImage("Expected_VRT_Pro_Img", "Actual_VRT_Pro_Img");

		sa.assertEquals(status_ImgCompare, true, "FAIL: The Equipment Image saved is not same as what was selected");

		sa.assertAll();
	}

	// EEIRTD_014- Verify if only priviledged users are allowed to edit the IRTD
	// equipments

	@Test(description = "EEIRTD_014- Verify if only priviledged users are allowed to edit the IRTD equipments")
	public void EEIRTD_014() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest("EEIRTD_014- This Tc has covered in scripts UM2- ADMN048B ");

	}

	// EEIRTD_015- Verify if user not having Edit Equipment permission is not
	// allowed to edit an IRTD equipment

	@Test(description = "EEIRTD_015- Verify if user not having Edit Equipment permission is not allowed to edit an IRTD equipment")
	public void EEIRTD_015() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest("EEIRTD_015- This Tc has covered in  scripts UM3-ADMN058B ");

	}

	// EEIRTD_016- Verify if the IRTD equipment is not edited for invalid login
	// credentials
	@Test(description = "EEIRTD_016- Verify if the IRTD equipment is not edited for invalid login credentials")
	public void EEIRTD_016() throws InterruptedException, AWTException, IOException {

		extentTest = extent
				.startTest("EEIRTD_016- Verify if the IRTD equipment is not edited for invalid login credentials");

		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		Equipment_IRTDDetailspage.enter_IRTDEquipName("test1");
		UserLoginPopup(getUID("6"), getPW("7232"));
		String actualmsg = Equipment_IRTDDetailspage.AlertMsg();
		String Expmsg = "Invalid Credential, Please try again";
		sa.assertEquals(actualmsg, Expmsg, "FAIL: Alert message is not correct");
		sa.assertAll();

	}
	// EEIRTD_017- Verify if user has the scope to delete a IRTD Equipment

	@Test(description = "EEIRTD_017- Verify if user has the scope to delete a IRTD Equipment")
	public void EEIRTD_017() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest("EEIRTD_017- Verify if user has the scope to delete a IRTD Equipment");

		SoftAssert sa = new SoftAssert();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		Equipment_IRTDDetailspage.clickDeleteEquipmentIcon();

		String Actmsg = Equipment_IRTDDetailspage.get_text_DeleteAst_popup();
		String Expmsg = "Do you want to delete the '  J1129 ' Equipment?";
		sa.assertEquals(Actmsg, Expmsg, "Fail: Alert Message is not displaying correctly");
		sa.assertAll();
	}

	// EEIRTD_018- Verify if priviledged user can delete the IRTD Equipment by
	// clicking on the Delete button

	@Test(description = "EEIRTD_018- Verify if priviledged user can delete the IRTD Equipment by clicking on the Delete button")
	public void EEIRTD_018() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest("EEIRTD_018- This TC has been handeled in UM2- ADMN064 Script");

	}

	// EEIRTD_019- Verify if user not having Delete Equipment permission is not
	// allowed to delete an IRTD equipment

	@Test(description = "EEIRTD_019- Verify if user not having Delete Equipment permission is not allowed to delete an IRTD equipment")
	public void EEIRTD_019() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest("EEIRTD_019- This TC has been handeled in UM3-CADMN5 Script");

	}

	// EEIRTD_020- Verify if clicking on the Back button from IRTD-Details page
	// redirects to the Equipments - IRTD List page

	@Test(description = "EEIRTD_020- Verify if clicking on the Back button from IRTD-Details page redirects to the Equipments - IRTD List page")
	public void EEIRTD_020() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_020- Verify if clicking on the Back button from IRTD-Details page redirects to the Equipments - IRTD List page");

		SoftAssert sa = new SoftAssert();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		IRTDHubPage = Equipment_IRTDDetailspage.click_Back_btn();
		sa.assertEquals(IRTDHubPage.isIRTDHeader_Visible(), true, "FAIL : Landed to wrong page");
		sa.assertAll();

	}

//EEIRTD_039- Verify if clicking on the Documents tab in the IRTD-Details Screen displays all the documents (pdf and docx files) for that equipment
//EEIRTD_040- Verify if clicking on Upload Documents allows the user to upload documents from Local system (pdf and docx files) from the file systems in IRTD-Details Screen

	@Test(description = "EEIRTD_039_EEIRTD_040- Verify if clicking on Upload Documents allows the user to upload documents from Local system (pdf and docx files) from the file systems in IRTD-Details Screen")
	public void EEIRTD_039_EEIRTD_040() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_039_EEIRTD_040- Verify if clicking on Upload Documents allows the user to upload documents from Local system (pdf and docx files) from the file systems in IRTD-Details Screen");

		SoftAssert sa = new SoftAssert();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		Equipment_IRTDDetailspage.click_DocumentsButton();
		Equipment_IRTDDetailspage.click_UploadDocsBtn();
		tu.uploadDoc("HelpFileWord.docx");
		Thread.sleep(1000);
		sa.assertEquals(Equipment_IRTDDetailspage.docsTile_countdata(), "1",
				"FAIL:Reports tile count displayed <0 under Asset details page");
		sa.assertAll();

	}

	// EEIRTD_041- Verify if clicking on Upload Documents allows the user to upload
	// documents from USB Drive (pdf and docx files) from the file systems in
	// IRTD-Details Screen
	// EEIRTD_042- Verify if clicking on Upload Documents allows the user to upload
	// documents from Mapped or Direct Network paths(pdf and docx files) from the
	// file systems in IRTD-Details Screen

	// EEIRTD_041,EEIRTD_042 will Handel Manually

	@Test(description = "EEIRTD_041,EEIRTD_042-Manually- due to USB Drive,Mapped or Direct Network paths this will handel manually")
	public void EEIRTD_041_EEIRTD_042() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest("EEIRTD_041_EEIRTD_042_Manually- will Handel Manually");
	}

	// EEIRTD_043- Verify if selecting a file and clicking on Copy To Drive allows
	// to copy the report into Local system from IRTD-Details Screen

	@Test(description = "EEIRTD_043- Verify if selecting a file and clicking on Copy To Drive allows to copy the report into Local system from IRTD-Details Screen")
	public void EEIRTD_043() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_043- Verify if selecting a file and clicking on Copy To Drive allows to copy the report into Local system from IRTD-Details Screen");

		SoftAssert sa = new SoftAssert();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		Equipment_IRTDDetailspage.Select_DocName("HelpFileWord");
		Equipment_IRTDDetailspage.selectFolder_CopyToDrive("AutoLogs");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String foldrpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
		// System.out.println("Act Folderpath: "+filepath);
		String ExpAlrtMsg = "HelpFileWord has been copied successfully to " + foldrpath;
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

	// EEIRTD_044- Verify if selecting a file and clicking on Copy To Drive allows
	// to copy the report into USB Drive from IRTD-Details Screen
	// EEIRTD_045- Verify if selecting a file and clicking on Copy To Drive allows
	// to copy the report Mapped or Direct Network paths from IRTD-Details Screen
	// EEIRTD_044, EEIRTD_045 will handel Manually

	@Test(description = "EEIRTD_044,EEIRTD_045-Manually- due to USB Drive,Mapped or Direct Network paths this will handel manually")
	public void EEIRTD_044_EEIRTD_045() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest("EEIRTD_044_EEIRTD_045_Manually- will Handel Manually");
	}

	// EEIRTD_046- Verify if user can generate a report for selected file by
	// clicking on the PDF icon against to the file under Documents section in
	// IRTD-Details Screen

	@Test(description = "EEIRTD_046- Verify if user can generate a report for selected file by clicking on the PDF icon against to the file under Documents section in IRTD-Details Screen")
	public void EEIRTD_056() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_046- Verify if user can generate a report for selected file by clicking on the PDF icon against to the file under Documents section in IRTD-Details Screen");

		SoftAssert sa = new SoftAssert();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		Equipment_IRTDDetailspage.Select_DocName("HelpFileWord");
		Equipment_IRTDDetailspage.Click_Print_Button();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(4000);
		Equipment_IRTDDetailspage.click_PDFpopup_OkBtn();
	}

	// EEIRTD_047- Verify if clicking on the Delete option against the file under
	// Documents tab allows the user to delete a selected file in IRTD-Details
	// Screen

	@Test(description = "EEIRTD_047- Verify if clicking on the Delete option against the file under Documents tab allows the user to delete a selected file in IRTD-Details Screen")
	public void EEIRTD_047() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_047- Verify if clicking on the Delete option against the file under Documents tab allows the user to delete a selected file in IRTD-Details Screen");

		SoftAssert sa = new SoftAssert();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");

		Equipment_IRTDDetailspage.Select_DocName("HelpFileWord");

		Equipment_IRTDDetailspage.Click_Delete_DocumentButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String actmsgalert = Equipment_IRTDDetailspage.get_text_DeleteAst_popup();
		String expmsgalert = "Do you want to delete the '  J1129 ' Equipment?";
		sa.assertEquals(actmsgalert, expmsgalert, "FAIL: Alert Message is not correct");
		sa.assertAll();
	}

	// EEIRTD_048- Verify if user not having the Copy Files or Reports permission
	// user not able to copy a selected file to any location in IRTD-Details Screen

	@Test(description = "Verify if user not having the Copy Files or Reports permission "
			+ "user not able to copy a selected file to any location in IRTD-Details Screen")
	public void EEIRTD_048() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest("EEIRTD_048 - Verify if user not having the Copy Files or Reports permission "
				+ "user not able to copy a selected file to any location in IRTD-Details Screen");
		SoftAssert sa = new SoftAssert();

		MainHubPage = EquipmentHubPage.ClickBackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");

		Equipment_IRTDDetailspage.Select_DocName("HelpFileWord");
		Equipment_IRTDDetailspage.click_CopyToDrive();
		String ExpAlrtMsg1 = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg1 = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlertMsg1, ExpAlrtMsg1, "FAIL: User should not able to perform copy to drive Equipment");
		sa.assertAll();
	}

	// EEIRTD_049-Verify if user not having the Delete StudyFiles or Reports
	// ermission user not able to delete a selected file under Documents tab in
	// IRTD-Details Screen

	@Test(description = "EEIRTD_049-Verify if user not having the Delete StudyFiles or Reports ermission user not able to delete a selected file under Documents tab in IRTD-Details Screen")
	public void EEIRTD_049() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_049-Verify if user not having the Delete StudyFiles or Reports ermission user not able to delete a selected file under Documents tab in IRTD-Details Screen");
		SoftAssert sa = new SoftAssert();

		MainHubPage = EquipmentHubPage.ClickBackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");

		Equipment_IRTDDetailspage.Select_DocName("HelpFileWord");
		Equipment_IRTDDetailspage.Click_Delete_DocumentButton();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));

		String ExpAlrtMsg1 = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg1 = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlertMsg1, ExpAlrtMsg1,
				"FAIL: user is able to delete a selected file under Documents tab in IRTD-Details Screen");
		sa.assertAll();

	}

	// EEIRTD_050- Verify if the Audit event is recorded in the Audit screen for
	// editing and deleting of IRTD equipment

	@Test(description = "EEIRTD_050- Verify if the Audit event is recorded in the Audit screen for editing and deleting of IRTD equipment")
	public void EEIRTD_050() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_060- Verify if the Audit event is recorded in the Audit screen for editing and deleting of IRTD equipment");

		SoftAssert sa = new SoftAssert();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");

		Equipment_IRTDDetailspage.enter_IRTDEquipName("test2");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		IRTDHubPage = Equipment_IRTDDetailspage.click_Back_btn();
		EquipmentHubPage = IRTDHubPage.click_Back_btn();
		MainHubPage = EquipmentHubPage.ClickBackBtn();

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		System.out.println(Actionmsg);
		String ExpectMSG = "Asset : \"Asset01\" \"Equipment ID\" field modified from \" 01 to 02 \" by  User ID : \"1\" , User Name : \"User1\".";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL:The Audit trail record for Edit Assets activity is not exist ");
		sa.assertAll();

	}

	// EEIRTD_051- Verify if clicking on the History button in IRTD-Details Screen
	// displays all the verification and calibration reports for that IRTD

	@Test(description = "EEIRTD_051- Verify if clicking on the History button in IRTD-Details Screen displays all the verification and calibration reports for that IRTD")
	public void EEIRTD_051() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_061- Verify if clicking on the History button in IRTD-Details Screen displays all the verification and calibration reports for that IRTD");

		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		Equipment_IRTDDetailspage.Click_HistoryButton();

		sa.assertEquals(Equipment_IRTDDetailspage.is_PrintButton_visible(), true,
				"FAIL : Print Button is not available ");
		sa.assertEquals(Equipment_IRTDDetailspage.is_FILENAME_visible_HISTORYTab(), true,
				"FAIL : Print Button is not available ");
		sa.assertEquals(Equipment_IRTDDetailspage.is_STUDYTYPEvisible_HISTORYTab(), true,
				"FAIL : Print Button is not available ");
		sa.assertEquals(Equipment_IRTDDetailspage.is_DATEvisible_HISTORYTab(), true,
				"FAIL : Print Button is not available ");
		sa.assertEquals(Equipment_IRTDDetailspage.is_ACTIONvisible_HISTORYTab(), true,
				"FAIL : Print Button is not available ");
		sa.assertAll();

	}

//EEIRTD_054- Verify that the history grid contents can be printed with the help of print button in IRTD screen

	@Test(description = "EEIRTD_054- Verify that the history grid contents can be printed with the help of print button in IRTD screen")
	public void EEIRTD_054() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEIRTD_054- Verify that the history grid contents can be printed with the help of print button in IRTD screen");

		SoftAssert sa = new SoftAssert();

		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		Equipment_IRTDDetailspage.Click_HistoryButton();

		Equipment_IRTDDetailspage.Click_Print_Button();

		Thread.sleep(4000);
		Equipment_IRTDDetailspage.click_PDFpopup_OkBtn();

	}

	// EEVRTL_055A Verify if the following contents are available in the Review
	// Equipment screen

	@Test(groups = {
			"Regression" }, description = "EEVRTL_055A Verify if the following contents are available in the Review Equipment screen")
	public void EEVRTL_055A() throws InterruptedException, IOException {

		extentTest = extent
				.startTest("EEVRTL_055A Verify if the following contents are available in the Review Equipment screen");
		SoftAssert sa = new SoftAssert();
		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");

		Thread.sleep(1000);

		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_serialNo_Visible(), true,
				"FAIL: Serial number is not available in VRTLoggers Details page");
		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_ID_Visible(), true,
				"FAIL: ID is not available in Equuipment_VRTLoggersDetailspage");
		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_ManufacturingCalDueDate_Visible(), true,
				"FAIL: ManufacturingCalDueDate is not available in Equuipment_VRTLoggersDetailspage");
		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_ManufacturingCalDate_Visible(), true,
				"FAIL: ManufacturingCalDate is not available in Equuipment_VRTLoggersDetailspage");
		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_LastVerificationdate_Visible(), true,
				"FAIL: ManufacturingCalDate is not available in Equuipment_VRTLoggersDetailspage");

		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_Browse_btn_Visible(), true,
				"FAIL: Browse btn is not available in Equuipment_VRTLoggersDetailspage");
		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_Camera_Btn_Visible(), true,
				"FAIL: camera btn is not available in Equuipment_VRTLoggersDetailspage");

		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_SaveBtn_Visible(), true,
				"FAIL: save btn is not available in Equuipment_VRTLoggersDetailspage");

		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_ClearBtn_Visible(), true,
				"FAIL: clear btn is not available in Equuipment_VRTLoggersDetailspage");

		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_Backbtn_Visible(), true,
				"FAIL: bacck btn is not available in Equuipment_VRTLoggersDetailspage");

		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_DeleteBtn_Visible(), true,
				"FAIL: Delete btn is not available in Equuipment_VRTLoggersDetailspage");

		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_VerificationsTile_Visible(), true,
				"FAIL:Verifications Tile is not available in Equuipment_VRTLoggersDetailspage");

		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_ReportsTile_Visible(), true,
				"FAIL: ReportsTile is not available in Equuipment_VRTLoggersDetailspage");
		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_DocumentsTile_Visible(), true,
				"FAIL: Documents Tile is not available in Equuipment_VRTLoggersDetailspage");
		sa.assertEquals(Equuipment_VRTLoggersDetailspage.is_HistoryTile_Visible(), true,
				"FAIL: _HistoryTileis not available in Equuipment_VRTLoggersDetailspage");

		sa.assertAll();
	}

// EEVRTL_055- Verify if user can review,edit and Save VRT Logger equipment by
// clicking on Save button in VRT Logger-Details Screen

	@Test(description = "EEVRTL_055- Verify if user can review,edit and Save VRT Logger equipment by clicking on Save button in VRT Logger-Details Screen")
	public void EEVRTL_055() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_055- Verify if user can review,edit and Save VRT Logger equipment by clicking on Save button in VRT Logger-Details Screen");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");

		Equuipment_VRTLoggersDetailspage.Enter_EquipmentID("A12");
		String Expid = Equuipment_VRTLoggersDetailspage.fetch_eqipID();
		Equuipment_VRTLoggersDetailspage.Click_Save();

		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		String actmsg = Equuipment_VRTLoggersDetailspage.AlertMsg();
		String Expmsg = "Equipment \" U88A\" Updated successfully.";
		sa.assertEquals(actmsg, Expmsg, "FAIL: Alert message is not correct");
		VRTLoggerHubPage = Equuipment_VRTLoggersDetailspage.click_Back_btn();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");
		String Actid = Equuipment_VRTLoggersDetailspage.fetch_eqipID();
		sa.assertEquals(Expid, Actid, "FAIL: Alert message is not correct");
		sa.assertAll();

	}

	// EEVRTL_056- Verify if clicking on the Home button in the VRT Logger-Details
	// screen, navigate to the Main Hub page

	@Test(description = "EEVRTL_056- Verify if clicking on the Home button in the VRT Logger-Details screen, navigate to the Main Hub page")
	public void EEVRTL_056() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_056- Verify if clicking on the Home button in the VRT Logger-Details screen, navigate to the Main Hub page");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");

		MainHubPage = tu.Click_Home_Icon_AppBar();
		sa.assertEquals(MainHubPage.Is_mainHubPageTitle_Visible(), true,
				"FAIL: Clicking Home icon/button in bottom app bar do not redirect to Mains Hub page");
		sa.assertAll();
	}

//EEVRTL_057- Verify if clicking on the Help button in the VRT Logger-Details Screen, displays the online help menu for that page

	@Test(description = "EEVRTL_057- Verify if clicking on the Help button in the VRT Logger-Details Screen, displays the online help menu for that page")
	public void EEVRTL_057() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"EEVRTL_057- Verify if clicking on the Help button in the VRT Logger-Details Screen, displays the online help menu for that page");
		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");

		tu.Click_Help_Icon_AppBar();
		sa.assertEquals(tu.get__HelpMenu_HdrText(), "Equipment Details",
				"FAIL: Clicking Help icon/button in bottom app bar"
						+ "do not display the Equipment Details Help context window");
		sa.assertAll();
	}

//EEVRTL_058-Verify if clicking on the Windows Help button in the VRT Logger-Details screen, open the Windows Help document

//EEVRTL_059-Verify if clicking on the About button in the VRT Logger-Details screen displays the current software version, Console IP

	@Test(dataProvider = "SET033", dataProviderClass = setupCreationUtility.class, description = "EEVRTL_059-Verify if clicking on the About button in the VRT Logger-Details screen displays the current software version, Console IP")
	public void EEVRTL_059(String SWVer) throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_059-Verify if clicking on the About button in the VRT Logger-Details screen displays the current software version, Console IP");
		SoftAssert sa = new SoftAssert();
		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");

		tu.Click_About_Icon_AppBar();

		sa.assertEquals(tu.SWversion_InAboutWndw(), SWVer,
				"FAIL: Incorrect SW version & Console IP adress displayed in About window");

		sa.assertEquals(tu.consoleIP_InAboutWndw(), tu.system_IPadress(),
				"FAIL: Incorrect SW version & Console IP adress displayed in About window");

		sa.assertAll();

	}

//EEVRTL_060 - Verify if the Equipment ID field in VRT Logger-Details Screen accepts Alphanumeric character and special characters like -, _ and slash (both forward and backward)

	@Test(dataProvider = "EEVRTL_060", dataProviderClass = EqupmentUtility.class, description = "EEVRTL_060-  Verify if the Equipment ID field in VRT Logger-Details Screen accepts Alphanumeric character and special characters like -, _ and slash (both forward and backward)")
	public void EEVRTL_060(String Eid) throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_060-  Verify if the Equipment ID field in VRT Logger-Details Screen accepts Alphanumeric character and special characters like -, _ and slash (both forward and backward)");
		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");
		Equuipment_VRTLoggersDetailspage.Enter_EquipmentID(Eid);
		Equuipment_VRTLoggersDetailspage.Click_Save();

		sa.assertEquals(Equuipment_VRTLoggersDetailspage.UserLoginPopupVisible(), true,
				"FAIL:User Login Popup is not visible");

		sa.assertAll();
	}

//EEVRTL_060A - Verify if the Equipment ID field in VRT Logger-Details Screen _Invalid data

	@Test(dataProvider = "EEVRTL_060A", dataProviderClass = EqupmentUtility.class, description = "EEVRTL_060A - Verify if the Equipment ID field in VRT Logger-Details Screen _Invalid data")
	public void EEVRTL_006A(String Eid, String Expalert) throws InterruptedException, IOException {

		extentTest = extent
				.startTest("EEVRTL_060A - Verify if the Equipment ID field in VRT Logger-Details Screen _Invalid data");
		SoftAssert sa = new SoftAssert();
		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");
		Equuipment_VRTLoggersDetailspage.Enter_EquipmentID(Eid);
		Equuipment_VRTLoggersDetailspage.Click_Save();

		sa.assertEquals(Equipment_IRTDDetailspage.AlertMsg(), Expalert, "FAIL:User Login Popup is not visible");
		sa.assertAll();
	}

	// EEVRTL_061- Verify if Equipment Name field in VRT Logger-Details Screen
	// accepts valid character length

	@Test(description = "EEVRTL_061- Verify if Equipment Name  field in VRT Logger-Details Screen accepts valid character length")
	public void EEVRTL_061() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_061- Verify if Equipment Name  field in VRT Logger-Details Screen accepts valid character length");
		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");
		Equuipment_VRTLoggersDetailspage.Enter_EquipmentID("12345678901234AB");

		Thread.sleep(1000);
		String s = Equuipment_VRTLoggersDetailspage.fetch_eqipID();
		sa.assertEquals(s.length(), 16,
				"FAIL:  character length of Equipment Name  field in VRTLogger-Details Screen is not 16 ");
		sa.assertAll();
	}

	// EEVRTL_061A- Verify if Equipment Name field in VRT Logger-Details Screen for
	// Invalid character length

	@Test(description = "EEVRTL_061A- Verify if Equipment Name  field in VRT Logger-Details Screen for Invalid character length")
	public void EEVRTL_061A() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_061A- Verify if Equipment Name  field in VRT Logger-Details Screen for Invalid character length");
		SoftAssert sa = new SoftAssert();

		// Enter more than 16 chars
		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");
		Equuipment_VRTLoggersDetailspage.Enter_EquipmentID("12345678901234ABdcjh");

		Thread.sleep(1000);
		String s = Equuipment_VRTLoggersDetailspage.fetch_eqipID();
		sa.assertEquals(s.length(), 16,
				"FAIL: Equipment Name  field in VRTLogger-Details Screen accepts more than 16  character length");
		sa.assertAll();
	}

	// EEVRTL_062- Verify if the Equipment Name field in VRT Logger-Details Screen
	// is not a mandatory field

	@Test(description = "EEVRTL_062- Verify if the Equipment Name field in VRT Logger-Details Screen is not a mandatory field")
	public void EEVRTL_008() throws InterruptedException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_062- Verify if the Equipment Name field in VRT Logger-Details Screen is not a mandatory field");
		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");
		Equuipment_VRTLoggersDetailspage.Enter_EquipmentID("");
		Equuipment_VRTLoggersDetailspage.Click_Save();
		sa.assertEquals(Equuipment_VRTLoggersDetailspage.UserLoginPopupVisible(), true,
				"FAIL:User Login Popup is not visible");
		sa.assertAll();
	}

	// EEVRTL_063- Verify if user has a scope to add a new equipment picture in VRT
	// Logger-Details Screen

	@Test(description = "EEVRTL_063- Verify if user has a scope to add a new equipment picture in VRT Logger-Details Screen")
	public void EEVRTL_063() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_063- Verify if user has a scope to add a new equipment picture in VRT Logger-Details Screen");
		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");
		Equuipment_VRTLoggersDetailspage.click_Browse_btn();
		tu.uploadDoc("VRT_Pro.JPG");

		// Capture the expected Image added to the Asset placeholder 1
		Equuipment_VRTLoggersDetailspage.Capture_EqpImg("Expected_VRT_Pro_Img");

		Equuipment_VRTLoggersDetailspage.Click_Save();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		VRTLoggerHubPage = Equuipment_VRTLoggersDetailspage.Backbtn();

		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");

		// Capture the actual Image saved to the Equipment
		Equuipment_VRTLoggersDetailspage.Capture_EqpImg("Actual_VRT_Pro_Img");

		boolean status_ImgCompare = tu.compareImage("Expected_VRT_Pro_Img", "Actual_VRT_Pro_Img");

		sa.assertEquals(status_ImgCompare, false, "FAIL: The Equipment Image saved is not same as what was selected");

		sa.assertAll();
	}

//EEVRTL_064- Verify if User is allowed to upload or change an image of valid image size in VRT Logger-Details Screen

	@Test(description = "EEVRTL_064- Verify if User is allowed to upload or change an image of valid image size in VRT Logger-Details Screen")
	public void EEVRTL_064() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_064- Verify if User is allowed to upload or change an image of valid image size in VRT Logger-Details Screen");
		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");
		Equuipment_VRTLoggersDetailspage.capture_Camera_Img();

		// Capture the expected Image added to the Asset placeholder 1
		Equuipment_VRTLoggersDetailspage.Capture_EqpImg("Expected_camera_Image");

		Equuipment_VRTLoggersDetailspage.Click_Save();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		VRTLoggerHubPage = Equuipment_VRTLoggersDetailspage.Backbtn();

		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");

		// Capture the actual Image saved to the Equipment image holder
		Equuipment_VRTLoggersDetailspage.Capture_EqpImg("Acctual_camera_Image");

		boolean status_ImgCompare = tu.compareImage("Expected_camera_Image", "Acctual_camera_Image");

		sa.assertEquals(status_ImgCompare, false, "FAIL: The Equipment Image saved is not same as what was captured");

		sa.assertAll();

	}
	// EEVRTL_065- Verify if User is not allowed to upload or change an image of
	// invalid image size in VRT Logger-Details Screen

	@Test(description = "EEVRTL_065- Verify if User is not allowed to upload or change an image of invalid image size in VRT Logger-Details Screen")
	public void EEVRTL_065() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_065- Verify if User is not allowed to upload or change an image of invalid image size in VRT Logger-Details Screen");
		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");
		Equuipment_VRTLoggersDetailspage.click_Browse_btn();
		tu.uploadDoc("UserimageInValid.jpg");

		String ExpAlrtMsg = "Select image file with size less than 5 mb";
		String ActAlertMsg = Equuipment_VRTLoggersDetailspage.AlertMsg();

		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL:Alert message should be displayed as-Select image file with size less than 5 mb ");
		sa.assertAll();
	}

	// EEVRTL_066- Verify if adding an equipment image is not a mandatory field in
	// VRT Logger-Details Screen

	@Test(description = "EEVRTL_066- Verify if adding an equipment image is not a mandatory field in VRT Logger-Details Screen")
	public void EEVRTL_066() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_012- Verify if adding an equipment image is not a mandatory field in VRT Logger-Details Screen");
		SoftAssert sa = new SoftAssert();
		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");
		Equuipment_VRTLoggersDetailspage.Click_Save();
		sa.assertEquals(Equuipment_VRTLoggersDetailspage.UserLoginPopupVisible(), true,
				"FAIL:User Login Popup is not visible");
		sa.assertAll();
	}

	// EEVRTL_067- Verify if User can delete the equipment image by clicking on the
	// Delete option available in the equipment image slot in the VRT Logger-Details
	// Screen

	@Test(description = "EEVRTL_067- Verify if User can delete the equipment image by clicking on the Delete option available in the equipment image slot in the VRT Logger-Details Screen")
	public void EEVRTL_067() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_067- Verify if User can delete the equipment image by clicking on the Delete option available in the equipment image slot in the VRT Logger-Details Screen");
		SoftAssert sa = new SoftAssert();
		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");

		Equuipment_VRTLoggersDetailspage.click_Browse_btn();
		tu.uploadDoc("VRT_Pro.JPG");

		Equuipment_VRTLoggersDetailspage.Capture_EqpImg("Expected_VRT_Pro_Img");
		Thread.sleep(1000);
		Equuipment_VRTLoggersDetailspage.click_DeleteEquipImage();

		VRTLoggerHubPage = Equuipment_VRTLoggersDetailspage.Backbtn();

		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");
		// Capture the actual Image saved to the Equipment
		Equuipment_VRTLoggersDetailspage.Capture_EqpImg("Actual_VRT_Pro_Img");

		boolean status_ImgCompare = tu.compareImage("Expected_VRT_Pro_Img", "Actual_VRT_Pro_Img");

		sa.assertEquals(status_ImgCompare, true, "FAIL: The Equipment Image saved is not same as what was selected");

		sa.assertAll();
	}

	// EEVRTL_068- Verify if only priviledged users are allowed to edit the VRT
	// Logger equipments

	@Test(description = "EEVRTL_068- Verify if only priviledged users are allowed to edit the VRT Logger equipments")

	public void EEVRTL_068() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest("EEVRTL_068-This Tc has covered in scripts UM2- ADMN048B ");

	}

	// EEVRTL_069- Verify if user not having Edit Equipment permission is not
	// allowed to edit an VRT Logger equipment

	@Test(description = "EEVRTL_069- Verify if user not having Edit Equipment permission is not allowed to edit an VRT Logger equipment")

	public void EEVRTL_069() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest("EEVRTL_068-This TC has been handeled in UM3-CADMN5 Script ");

	}

	// EEVRTL_070- Verify if the VRT Logger equipment is not edited for invalid
	// login credentials

	@Test(description = "EEVRTL_070- Verify if the VRT Logger equipment is not edited for invalid login credentials")
	public void EEVRTL_070() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_070- Verify if the VRT Logger equipment is not edited for invalid login credentials");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");

		Equuipment_VRTLoggersDetailspage.Enter_EquipmentID("A121");
		UserLoginPopup(getUID("6"), getPW("7232"));
		String actmsg = Equuipment_VRTLoggersDetailspage.AlertMsg();
		String Expmsg = "Invalid Credential, Please try again";
		sa.assertEquals(actmsg, Expmsg, "FAIL: Alert message is not correct");
		sa.assertAll();

	}

	// EEVRTL_071- Verify if user has the scope to delete a VRT Logger Equipment

	@Test(description = "EEVRTL_071- Verify if user has the scope to delete a VRT Logger Equipment")
	public void EEVRTL_071() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest("EEVRTL_017- Verify if user has the scope to delete a VRT Logger Equipment");

		SoftAssert sa = new SoftAssert();
		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");
		Equuipment_VRTLoggersDetailspage.clickDeleteEquipmentIcon();

		String actmsg = Equuipment_VRTLoggersDetailspage.get_text_DeleteAst_popup();
		String Expmsg = "Can't delete equipment: \" U88A \". Cal/Ver/Documents/Reports are associated.";
		sa.assertEquals(actmsg, Expmsg, "Fail: Alert Message is not displaying correctly");
		sa.assertAll();
	}

	// EEVRTL_072- Verify if priviledged user can delete the VRT Logger Equipment by
	// clicking on the Delete button
	// This TC already handelled in UM Privillage Test script

	@Test(description = "EEVRTL_072- Verify if priviledged user can delete the VRT Logger Equipment by clicking on the Delete button")
	public void EEVRTL_072() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest("EEVRTL_072-This TC has been handeled in UM2- ADMN064 Script");

	}

	// EEVRTL_073- Verify if user not having Delete Equipment permission is not
	// allowed to delete an VRT Logger equipment
	// This TC already handelled in UM Privillage Test script

	@Test(description = "EEVRTL_073- Verify if user not having Delete Equipment permission is not allowed to delete an VRT Logger equipment")
	public void EEVRTL_073() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest("EEIRTD_019- This TC has been handeled in UM3-CADMN5 Script");

	}

	// EEVRTL_074- Verify if clicking on the Back button from VRT Logger-Details
	// page redirects to the Equipments - VRT Logger List page

	@Test(description = "EEVRTL_074- Verify if clicking on the Back button from VRT Logger-Details page redirects to the Equipments - VRT Logger List page")
	public void EEVRTL_074() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"EEVRTL_074- Verify if clicking on the Back button from VRT Logger-Details page redirects to the Equipments - VRT Logger List page");

		SoftAssert sa = new SoftAssert();
		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();

		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");
		VRTLoggerHubPage = Equuipment_VRTLoggersDetailspage.click_Back_btn();

		sa.assertEquals(VRTLoggerHubPage.isVRTLogger_Title_Visible(), true, "FAIL : Landed to wrong page");
		sa.assertAll();

	}

	// EEVRTL_093- Verify if clicking on the Documents tab in the VRT Logger-Details
	// Screen displays all the documents (pdf and docx files) for that equipment

	// EEVRTL_094- Verify if clicking on Upload Documents allows the user to upload
	// documents from Local system (pdf and docx files) from the file systems in
	// VRTL-Details Screen

	@Test(description = "EEVRTL_050- Verify if clicking on Upload Documents allows the user to upload documents from Local system (pdf and docx files) from the file systems in VRTL-Details Screen")
	public void EEVRTL_050() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_050- Verify if clicking on Upload Documents allows the user to upload documents from Local system (pdf and docx files) from the file systems in VRTL-Details Screen");

		SoftAssert sa = new SoftAssert();
		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();

		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");

		Equuipment_VRTLoggersDetailspage.click_DocumentsButton();
		Equuipment_VRTLoggersDetailspage.click_UploadDocsBtn();
		tu.uploadDoc("HelpFileWord.docx");
		Thread.sleep(1000);
		sa.assertEquals(Equuipment_VRTLoggersDetailspage.docsTile_countdata(), "1",
				"FAIL:Reports tile count displayed <0 under Asset details page");
		sa.assertAll();

	}

	// EEVRTL_095- Verify if clicking on Upload Documents allows the user to upload
	// documents from USB Drive (pdf and docx files) from the file systems in
	// VRTL-Details Screen
	// EEVRTL_096- Verify if clicking on Upload Documents allows the user to upload
	// documents from Mapped or Direct Network paths(pdf and docx files) from the
	// file systems in VRTL-Details Screen

	// EEVRTL_095,EEVRTL_096 will Handel Manually

	// EEVRTL_097-- Verify if selecting a file and clicking on Copy To Drive allows
	// to copy the report into Local system from VRTL-Details Screen

	@Test(description = "EEVRTL_097-Verify if selecting a file and clicking on Copy To Drive allows to copy the report into Local system from VRTL-Details Screen")
	public void EEVRTL_097() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_097-Verify if selecting a file and clicking on Copy To Drive allows to copy the report into Local system from VRTL-Details Screen");

		SoftAssert sa = new SoftAssert();
		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();

		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");

		Equuipment_VRTLoggersDetailspage.Select_DocName("HelpFileWord");
		Equuipment_VRTLoggersDetailspage.selectFolder_CopyToDrive("AutoLogs");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String foldrpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
		// System.out.println("Act Folderpath: "+filepath);
		String ExpAlrtMsg = "HelpFileWord has been copied successfully to " + foldrpath;
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

	// EEVRTL_098- Verify if selecting a file and clicking on Copy To Drive allows
	// to copy the report into USB Drive from VRTL-Details Screen

	// EEVRTL_099- Verify if selecting a file and clicking on Copy To Drive allows
	// to copy the report Mapped or Direct Network paths from VRTL-Details Screen

	// EEVRTL_100- Verify if user can generate a report for selected file by
	// clicking on the PDF icon against to the file under Documents section in VRT
	// Logger-Details Screen

	// IRTD-Details Screen

	@Test(description = "EEVRTL_100- Verify if user can generate a report for selected file by clicking on the PDF icon against to the file under Documents section in VRT Logger-Details Screen")
	public void EEVRTL_100() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_100- Verify if user can generate a report for selected file by clicking on the PDF icon against to the file under Documents section in VRT Logger-Details Screen");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");

		Equuipment_VRTLoggersDetailspage.Select_DocName("HelpFileWord");
		Equuipment_VRTLoggersDetailspage.Click_Print_Button();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(4000);
		Equuipment_VRTLoggersDetailspage.click_PDFpopup_OkBtn();
	}

	// EEVRTL_101- Verify if clicking on the Delete option against the file under
	// Documents tab allows the user to delete a selected file in VRT Logger-Details
	// Screen

	@Test(description = "EEVRTL_101- Verify if clicking on the Delete option against the file under Documents tab allows the user to delete a selected file in VRT Logger-Details Screen")
	public void EEVRTL_101() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_057- Verify if clicking on the Delete option against the file under Documents tab allows the user to delete a selected file in VRT Logger-Details Screen");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");

		Equuipment_VRTLoggersDetailspage.Select_DocName("HelpFileWord");

		Equipment_IRTDDetailspage.Click_Delete_DocumentButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		String actmsgalert = Equipment_IRTDDetailspage.get_text_DeleteAst_popup();
		String expmsgalert = "Do you want to delete the '  J1129 ' Equipment?";
		sa.assertEquals(actmsgalert, expmsgalert, "FAIL: Alert Message is not correct");
		sa.assertAll();
	}

	// EEVRTL_102- Verify if user not having the Copy Files or Reports permission
	// user not able to copy a selected file to any location in VRT Logger-Details
	// Screen
	// EEVRTL_103-Verify if user not having the Delete StudyFiles or Reports
	// permission user not able to delete a selected file under Documents tab in VRT
	// Logger-Details Screen

//EEVRTL_102 ,EEVRTL_103 Already Covered in UM privillages test script

	// EEVRTL_060- Verify if the Audit event is recorded in the Audit screen for
	// editing and deleting of VRT Logger equipment

	@Test(description = "EEVRTL_104- Verify if the Audit event is recorded in the Audit screen for editing and deleting of VRT Logger equipment")
	public void EEVRTL_104() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"EEVRTL_104- Verify if the Audit event is recorded in the Audit screen for editing and deleting of VRT Logger equipment");

		SoftAssert sa = new SoftAssert();

		VRTLoggerHubPage = EquipmentHubPage.Click_VRTLogger_listbox();
		Equuipment_VRTLoggersDetailspage = VRTLoggerHubPage.Click_VRTSerialNo("  U88A");

		Equuipment_VRTLoggersDetailspage.Enter_EquipmentID("12");
		Equuipment_VRTLoggersDetailspage.Click_Save();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		Equuipment_VRTLoggersDetailspage.Enter_EquipmentID("13");
		Equuipment_VRTLoggersDetailspage.Click_Save();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);

		VRTLoggerHubPage = Equuipment_VRTLoggersDetailspage.click_Back_btn();
		EquipmentHubPage = VRTLoggerHubPage.click_Back_btn();
		MainHubPage = EquipmentHubPage.ClickBackBtn();

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		System.out.println(Actionmsg);
		String ExpectMSG = "“Equipment Name” field of  “U88A” updated from “12” to “13” modified by User ID : \"1\" , User Name: \"User1\"";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL:The Audit trail record for Edit Assets activity is not exist ");
		sa.assertAll();

	}

}
