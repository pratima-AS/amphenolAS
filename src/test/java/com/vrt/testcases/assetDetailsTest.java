package com.vrt.testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

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
	
	CopySetuppage CopySetuppage;
	SelectBaseStationPage SelectBaseStationPage;
	OverlayWiringImagePage OverlayWiringImagePage;

	// Before All the tests are conducted
	//@BeforeTest
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, ParseException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_"+"_AssetDetailsTest"+".html", true);
		extent.addSystemInfo("TestSuiteName", "AssetDetailsTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("assetDetailsTest in Progress..");

		// Rename the User file (NgvUsers.uxx) if exists
	renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		// Rename the cache Asset file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
		// Rename the Asset folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		//Thread.sleep(1000);
		LoginPage = new LoginPage();
		extent.addSystemInfo("VRT Version", LoginPage.get_SWVersion_About_Text());
	// Method to Create Very 1st User with All privilege
		UserManagementPage=LoginPage.DefaultLogin();
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
		
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPage(getUID("adminFull"), getPW("adminFull"));
		SyncInPage.enter_Filepath("syncin");
		SyncInPage.click_FltrBtn();
		SyncInAssetListPage = SyncInPage.click_SyncInOK_btn();
		SyncInAssetListPage.click_EquipmentCheckBox();
		SyncInAssetListPage.click_SelectAllBtn();
		SyncInAssetListPage.click_OkBtn();
		SyncInAssetListPage.click_AlrtYesBtn();
		Thread.sleep(1000);
		SyncInAssetListPage.click_Success_alrtMeg_OkBtn();

		//AppClose();
		//Thread.sleep(1000);

	}

	// After All the tests are conducted	
	//@AfterTest
	@AfterClass
	public void endReport_releaseMomory() {
		extent.flush();
		extent.close();
		assetHubPage.resetWebElements();
		// System.out.println("Reset Webelement memory released");
		System.out.println("assetDetails Test  Completed.");
	}

	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		//Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();		
		//assetDetailsPage = assetHubPage.click_assetTile("Asset01");
		Thread.sleep(500);
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

	// ASST001-Verify the details displayed in Asset details screen - EDIT,COPY,DELETE
	 @Test(groups = {"Regression" }, description = "ASST001-Verify the details displayed in Asset details screen")
     public void ASST001() throws Exception {extentTest = extent.startTest("Verify the details displayed in Asset details screen");
    
     SoftAssert sa = new SoftAssert();
     sa.assertEquals(assetDetailsPage.assetEditBtn_state(), true, "FAIL: No UName field present");
     sa.assertEquals(assetDetailsPage.DeleteIcon_state(), true, "FAIL: No UName field present");
     sa.assertEquals(assetDetailsPage.CopyAsset_state(), true, "FAIL: No UName field present");
     sa.assertAll();
     
	}
	 
	// ASST002-Verify the details displayed on the 2 sections in Asset details screen
		@Test(groups = {
				"Regression" }, description = "ASST002-Verify the details displayed on the 2 sections in Asset details screen")
		public void ASST002() throws Exception {
			extentTest = extent.startTest(
					"Verify the details displayed on the 2 sections in Asset details screen");

			SoftAssert sa = new SoftAssert();
			sa.assertEquals(assetDetailsPage.AssetHub_ImgHldrPresence(), true, "FAIL: No Image field present");
			sa.assertEquals(assetDetailsPage.AssetIDPresence(), true, "FAIL: No Asset ID field present");
			sa.assertEquals(assetDetailsPage.ModelPresence(), true, "FAIL: No Model field present");
			sa.assertEquals(assetDetailsPage.ManufacturerPresence(), true, "FAIL: No Manufacturer field present");
			sa.assertEquals(assetDetailsPage.TypePresence(), true, "FAIL: No Type field present");
			sa.assertEquals(assetDetailsPage.LastValidatedPresence(), true, "FAIL: No LastValidated field present");
			Thread.sleep(200);
			sa.assertEquals(assetDetailsPage.setupTile_state(), true, "FAIL: No setupTile field present");
			sa.assertEquals(assetDetailsPage.qualTile_state(), true, "FAIL: No qualTile field present");
			sa.assertEquals(assetDetailsPage.reportsTile_state(), true, "FAIL: No reportsTile field present");
			sa.assertEquals(assetDetailsPage.docsTile_state(), true, "FAIL: No docsTile field present");
			sa.assertAll();
		}
		
	//ASST003-Verify the on-click functionality of edit icon for Asset
	
	@Test(groups = {
		"Regression" }, description = "ASST003-Verify the on-click functionality of edit icon for Asset")
      public void ASST003() throws Exception {
	  extentTest = extent.startTest("Verify the details displayed on the 2 sections in Asset details screen");
	   SoftAssert sa = new SoftAssert();
	
			if (assetDetailsPage.assetEditBtn_state()) {
				assetCreationPage = assetDetailsPage.click_assetEditBtn();
			}

			sa.assertEquals(assetCreationPage.get_newAssetCreatePagetitle(), "Edit Asset",
					"FAIL:Incorrect AssetCreation Page title in Asset Edit mode or landed into incorrect Page");
			sa.assertAll();
	}
	
//<<<<<<< HEAD
	//ASST004-Verify if the details are saved during Edit Asset post modification


 //ASST006-Verify the Back Button functionality in Edit Asset screen
	@Test(groups = {
			"Regression" }, description = "Verify the Back Button functionality in Edit Asset screen")
	public void ASST006() throws Exception {
		extentTest = extent.startTest("Verify the Back Button functionality in Edit Asset screen");
		SoftAssert sa = new SoftAssert();

		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetDetailsPage = assetCreationPage.click_BackBtn();
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
				"FAIL:The modified values should be cleared off and the previous original values should be displayed in the field");
		sa.assertAll();
	}
	
	//ASST008-Verify the display of Asset in Asset hub page when any Asset is edited
	
	@Test(groups = { "Regression" }, description = "ASST008-Verify the display of Asset in Asset hub page when any Asset is edited")
	    public void ASST008() throws Exception {
		extentTest = extent.startTest("Verify the display of Asset in Asset hub page when any Asset is edited");
		SoftAssert sa = new SoftAssert();

		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.enterAssetID("01");
		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
		
		sa.assertEquals(assetDetailsPage.assetDetail_PageTitle(), "HeatBath - Asset01",
				"FAIL: TC-ASST016 -Incorrect AssetDetails Page title or landed into incorrect Page");
		sa.assertAll();
}

	//ASST009-Verify the on-click of Copy icon for Assets
	   
	   @Test(groups = { "Regression" }, description = "ASST009-Verify the display of Asset in Asset hub page when any Asset is edited")
	    public void ASST009() throws Exception {
		extentTest = extent.startTest("Verify the display of Asset in Asset hub page when any Asset is edited");
		SoftAssert sa = new SoftAssert();

		Copyassetpage = assetDetailsPage.clickCopyasset();
		sa.assertEquals(Copyassetpage.IsCopyAssetPageTitle_presence(),true,
				"FAIL: Incorrect CopyAsset Page Title presence title or landed into incorrect Page");
		sa.assertAll();
		
	}
	
	//ASST010-Verify the on-click of Delete icon for Assets with no files in it
	@Test(groups = { "Regression" }, description = "ASST010-Verify the on-click of Delete icon for Assets with no files in it")
    public void ASST010() throws Exception {
	extentTest = extent.startTest("Verify the on-click of Delete icon for Assets with no files in it");
	SoftAssert sa = new SoftAssert();
	
	assetHubPage = assetDetailsPage.ClickBackBtn();
	assetCreationPage = assetHubPage.ClickAddAssetBtn();
	String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
	assetCreationPage.assetCreationWithAllFieldEntry("DLTAsset", "001", "HeatBath", "TAS", "Hyderabad", "VRT-RF", "2",
			"cu", crntDate, "5", "Weeks", "1st Asset Creation");
	UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
	
	assetHubPage = assetCreationPage.clickBackBtn();
	assetDetailsPage = assetHubPage.click_assetTile("DLTAsset");
	assetDetailsPage.DeleteAssert();
	UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
	assetHubPage = assetDetailsPage.Delete_ClickYesBtn();
	assetHubPage.click_serachAstBtn();
	assetHubPage.enter_serachAsttxt("DLTAsset");
	sa.assertEquals(assetHubPage.IsNoRecordFoundVisible(),true,
			"FAIL: No Record Found Message should be displayed ");
	sa.assertAll();

	}
	
//ASST012-Verify the on-click of Delete icon for Assets which has files in it
	
	@Test(groups = {
			"Regression" }, description = "ASST012-Verify the on-click of Delete icon for Assets which has files in it")
	public void ASST012() throws Exception {
		extentTest = extent.startTest("Verify the on-click of Delete icon for Assets which has files in it");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.DeleteAssert();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage.YesBtn_WithFiles();
		assetDetailsPage.ClickOK_btn();
		assetHubPage = assetDetailsPage.ClickBackBtn();

	}
	
	//ASST013-Verify for  a fresh asset with no activities - Setups, Qualifications Documents and  Reports -  as mentioned, all tiles should display 0
	@Test(groups = { "Regression" }, description = "ASST013-Verify for  a fresh asset with no activities - Setups, Qualifications Documents and  Reports -  as mentioned, all tiles should display 0")
	public void ASST013() throws InterruptedException, ParseException {
		extentTest = extent
				.startTest("Verify for  a fresh asset with no activities - Setups, Qualifications Documents and  Reports -  as mentioned, all tiles should display 0");
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
	

 //ASST014STP-Verify the details displayed under Setups tile
	@Test(groups = { "Regression" }, description = "ASST014-Verify for  a fresh asset with no activities - Setups, Qualifications Documents and  Reports -  as mentioned, all tiles should display 0")
	   public void ASST014() throws InterruptedException, ParseException, IOException {
		
		extentTest = extent.startTest("Verify for  a fresh asset with no activities - Setups, Qualifications Documents and  Reports -  as mentioned, all tiles should display 0");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		sa.assertEquals(assetDetailsPage.IsSetupTile_countdataPresence(), true,
				"FAIL:The setup tile should display the counter for number");
		sa.assertEquals(assetDetailsPage.SetupsHeader_state(), true,
				"FAIL:The Setups Header state should display for the available setup");
		sa.assertEquals(assetDetailsPage.SetupEditBtn_state(), true,
				"FAIL:The Setup Edit Btn should be display for the available setup");
		sa.assertEquals(assetDetailsPage.WiringImgButton_state(), true,
				"FAIL:The Setup WiringImg Button should be display for the available setup");
		sa.assertEquals(assetDetailsPage.DeleteBtn_state(), true,
				"FAIL:The Setup Delete Btn should be display for the available setup");
		sa.assertAll();
	}
 	 
	
	//ASST015STP-Verify the Setup date and time for a new Setup
		@Test(groups = { "Regression" }, description = "ASST015STP-Verify the Setup date and time for a new Setup")
		   public void ASST015() throws InterruptedException, ParseException, IOException {
			
			extentTest = extent.startTest("ASST015STP-Verify the Setup date and time for a new Setup");
			SoftAssert sa = new SoftAssert();
			assetHubPage = assetDetailsPage.ClickBackBtn();
			assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
			sa.assertEquals(assetDetailsPage.DateUnder_Setup(), true,
					"FAIL:The setup tile should display the counter for number");
			sa.assertAll();
}
		
		//ASST017STP-Verify New button is available and clicking on button user should able to define new setup
		
		@Test(groups = { "Regression" }, description = "ASST015STP-Verify the Setup date and time for a new Setup")
		   public void ASST017() throws InterruptedException, ParseException, IOException {
			
			extentTest = extent.startTest("ASST015STP-Verify the Setup date and time for a new Setup");
			SoftAssert sa = new SoftAssert();
			defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
			sa.assertEquals(defineSetupPage.defineSetupPage_state(), true,
					"FAIL:The setup define page should be displayed ");
			sa.assertAll();
			
		}	
			
	//ASST018STP-Verify on-click of Copy button for a setup
	@Test(groups = { "Regression" }, description = "ASST018STP-Verify on-click of Copy button for a setup")
	   public void ASST018() throws InterruptedException, ParseException, IOException {
		
		extentTest = extent.startTest("ASST018STP-Verify on-click of Copy button for a setup");
		SoftAssert sa = new SoftAssert();
		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		sa.assertEquals(CopySetuppage.CopySetupPage_Title(), true,
				"FAIL:The Copy setup tile should be displayed");
		sa.assertAll();
	}	
	
	//ASST019STP-Verify Copy setup functionality when there is only one Asset available
	
	//ASST020STP-Verify -Copy to drive- functionality of a setup for local drive
	@Test(groups = { "Regression" }, description = "Verify Copy to drive functionality of a setup for local drive")
	   public void ASST020() throws InterruptedException, ParseException, IOException, AWTException {
		
		extentTest = extent.startTest("ASST020STP-Verify -Copy to drive- functionality of a setup for local drive");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.SETUP_CopyToDrive_Btn("CopySetups");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		String ExpAlrtMsg = "manual 1 min sampling has been copied successfully to D:\\Stored-Code\\TestAutomation\\Development\\Root\\Source\\VRT\\src\\test\\resources\\TestData\\CopySetups";
		String ActAlertMsg = tu.get_AlertMsg_text();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Disable user should not be able to create equipments");
		sa.assertAll();
	
	}
	
  //ASST023STP-Verify the display of Initiate Qualification button in setup tile
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
				"FAIL:The Initiate Qual Btn state should be in enable mode");
		sa.assertAll();
	
	}
	
	//ASST024_1STP- Verify the filed level validations for SOP protocl number field in Initiate qualification pop-up
	
	@Test(dataProvider = "ASST024_1_TC", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "ASST024_1STP-Verify the filed level validations for SOP protocl number field in Initiate qualification pop-up")

	public void ASST024_1STP(Object... dataProvider) throws InterruptedException, IOException {
		extentTest = extent
				.startTest("ASST024_1STP-Verify the filed level validations for SOP protocl number field in Initiate qualification pop-up");

		SoftAssert sa = new SoftAssert();

		String SNum = (String) dataProvider[0];
		String RNum = (String) dataProvider[1];

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		SelectBaseStationPage=assetDetailsPage.Create_SOP(SNum, RNum);
		sa.assertEquals(SelectBaseStationPage.SelectBaseStation_Title_Visible(), true, "Fail: Landed to Incorrect page ");
		sa.assertAll();
	}
	
	
	//ASST024_1A-Verify the invalid characters accepted in SOP protocl number field

	@Test(dataProvider = "ASST024_1A_TC", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "ASST024_1A-Verify the filed level validations for SOP protocl number field in Initiate qualification pop-up")

	public void ASST024_1A(Object... dataProvider) throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST024_1A-Verify the filed level validations for SOP protocl number field in Initiate qualification pop-up");

		SoftAssert sa = new SoftAssert();

		String SNum = (String) dataProvider[0];
		String RNum = (String) dataProvider[1];

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
	
	//ASST024_1B_Verify the max character length for SOP protocol number field
	@Test(groups = {
			"Regression" }, description = "ASST024_1B_Verify the max character length for SOP protocol number field")
	public void ASST024_1B() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("ASST024_1B_Verify the max character length for SOP protocol number field");
		SoftAssert sa = new SoftAssert();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		Thread.sleep(500);
		String expectedvalue = "1234567890123456789012345678901234567890123456789012";
		System.out.println("count of SOP NUM to be entered: " + expectedvalue.length());
		assetDetailsPage.Enter_SOPNum(expectedvalue);
		String actualvaluAllowed = assetDetailsPage.GetSOPNumText();
		System.out.println("count of SOP NUM allowed : " + actualvaluAllowed.length());

		sa.assertEquals(actualvaluAllowed.length(), 50, "FAIL: SOP Protocol field  accepting more than  50 characters");
		sa.assertAll();
	}
	
	
	//ASST024.2STP-Verify the filed level validations for Run number field in Initiate qualification pop-up-Valid value
	@Test(dataProvider = "ASST024_2_TC", dataProviderClass = assetCreationUtility.class, groups = {
			"Regression" }, description = "ASST024.2-Verify the filed level validations for Run number field in Initiate qualification pop-up_Valid value")

	public void ASST024_2(Object... dataProvider) throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"ASST024_2A-Verify the filed level validations for SOP protocl number field in Initiate qualification pop-up_Valid value");

		SoftAssert sa = new SoftAssert();

		String SNum = (String) dataProvider[0];
		String RNum = (String) dataProvider[1];

		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.click_InitiateQualBtn();
		SelectBaseStationPage=assetDetailsPage.Create_SOP(SNum, RNum);
		sa.assertEquals(SelectBaseStationPage.SelectBaseStation_Title_Visible(), true, "Fail:landed into incorrect Page");
		sa.assertAll();
	}
	
	//ASST024.2ASTP-Verify the filed level validations for Run number field in Initiate qualification pop-up_Invalid Value
		@Test(dataProvider = "ASST024_2A_TC", dataProviderClass = assetCreationUtility.class, groups = {
				"Regression" }, description = "ASST024_1A-Verify the filed level validations for SOP protocl number field in Initiate qualification pop-up_Invalid Value")

		public void ASST024_2A(Object... dataProvider) throws InterruptedException, IOException {
			extentTest = extent.startTest(
					"ASST024_2A-Verify the filed level validations for SOP protocl number field in Initiate qualification pop-up_Invalid Value");

			SoftAssert sa = new SoftAssert();

			String SNum = (String) dataProvider[0];
			String RNum = (String) dataProvider[1];

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
		
		//ASST024_2B_Verify Max 4 characters should be allowed for Run number field
	
		@Test(groups = {"Regression" }, description = "ASST024_2B_Verify Max 4 characters should be allowed for Run number field")
		    public void ASST024_2B() throws InterruptedException, IOException {
			extentTest = extent.startTest("ASST024_2B_Verify Max 4 characters should be allowed for Run number field");
			SoftAssert sa = new SoftAssert();
			assetHubPage = assetDetailsPage.ClickBackBtn();
			assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
			assetDetailsPage.Click_SetupName("manual 1 min sampling");
			assetDetailsPage.click_InitiateQualBtn();
			Thread.sleep(500);
			String Invalidvalue = "99999"; // 5 char
			System.out.println("count of RUN NUM to be entered: " + Invalidvalue.length());
			assetDetailsPage.Enter_RunNumber(Invalidvalue);
			String actualvaluAllowed = assetDetailsPage.GetRunNumText();
			System.out.println("count of RUN NUM allowed : " + actualvaluAllowed.length());

			sa.assertEquals(actualvaluAllowed.length(), 4, "FAIL: Run Number Text shpuld  accepts 4 characters");
			sa.assertAll();
		    }
		
	
	//ASST024STP-Verify Clicking on Initiate Qualification the user should start Qualification Procedure
	
      @Test(groups = {"Regression" }, description = "ASST024STP-Verify Clicking on Initiate Qualification the user should start Qualification Procedure")
         public void ASST024STP() throws InterruptedException, IOException {
	     extentTest = extent.startTest("ASST024STP-Verify Clicking on Initiate Qualification the user should start Qualification Procedure");
	     SoftAssert sa = new SoftAssert();
	     assetHubPage = assetDetailsPage.ClickBackBtn();
	     assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
	     assetDetailsPage.Click_SetupName("manual 1 min sampling");
	     assetDetailsPage.click_InitiateQualBtn();
	     sa.assertEquals( assetDetailsPage.IsSOPNumberField_Presence(), true, "Fail: SOP Field should be displayed");
	     sa.assertEquals( assetDetailsPage.IsRunNumberField_Presence(), true, "Fail: Run Field should be displayed");

	     sa.assertAll();
	 
	}
	
  

//ASST026STP-Verify on-click functionality of Edit icon for a setup
      @Test(groups = {"Regression" }, description = "ASST026STP-Verify on-click functionality of Edit icon for a setup")
      public void ASST026STP() throws InterruptedException, IOException {
	     extentTest = extent.startTest("ASST026STP-Verify on-click functionality of Edit icon for a setup");
	     SoftAssert sa = new SoftAssert();
	     assetHubPage = assetDetailsPage.ClickBackBtn();
	     assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
	     assetDetailsPage.Click_SetupName("manual 1 min sampling");
	     defineSetupPage =  assetDetailsPage.Click_SetupEditBtn();
	    
	     sa.assertEquals(  defineSetupPage.defineSetupPage_state(), true, "Fail: landed into incorrect Page");
	     sa.assertAll();
      }  

	// ASST027STP-Verify the edit setup functionality
	@Test(groups = {
			"Regression" }, dataProvider = "SetupCreation_5", dataProviderClass = setupCreationUtility.class, description = "Create Setups")

	public void ADMN037E(String Comments, String Qstart)
			throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"Verify the functionality when disabled user credentials are given in authentication window of Setups screen");
		SoftAssert sa = new SoftAssert();
//Create Asset
		MainHubPage = UserManagementPage.ClickBackButn();
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		defineSetupPage = assetDetailsPage.Click_SetupEditBtn();

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

		assetDetailsPage = Setup_ReviewPage.click_back_Btn();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		defineSetupPage = assetDetailsPage.Click_SetupEditBtn();
		String CmntTxt2 = defineSetupPage.get_defineSetupPage_comments_txtData();
		sa.assertEquals(CmntTxt1, CmntTxt2, "FAIL:User should be available");
		sa.assertAll();

	}
     
	//ASST029WO-Verify the on-click functionality of the wiring icon for a setup
	
    @Test(groups = {"Regression" }, description = "ASST029WO-Verify the on-click functionality of the wiring icon for a setup")
    public void ASST029WO() throws InterruptedException, IOException {
	     extentTest = extent.startTest("ASST029WO-Verify the on-click functionality of the wiring icon for a setup");
	     SoftAssert sa = new SoftAssert();
	    // assetHubPage = assetDetailsPage.ClickBackBtn();
	     assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
	     assetDetailsPage.Click_SetupName("manual 1 min sampling");
	     OverlayWiringImagePage = assetDetailsPage.Click_WiringImgButton();
	     sa.assertEquals(  OverlayWiringImagePage.IsOverlayWiringPage_Title_Visible(), true, "Fail: Landed to Incorrect page");
	     sa.assertAll();
    }  

	//ASST030WO-Verify the details displayed in Wiring overlay screen for a setup
    @Test(groups = {"Regression" }, description = "ASST030WO-Verify the details displayed in Wiring overlay screen for a setup")
    public void ASST030WO() throws InterruptedException, IOException {
	     extentTest = extent.startTest("ASST030WO-Verify the details displayed in Wiring overlay screen for a setup");
	     SoftAssert sa = new SoftAssert();
	     //assetHubPage = assetDetailsPage.ClickBackBtn();
	     assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
	     assetDetailsPage.Click_SetupName("manual 1 min sampling");
	     OverlayWiringImagePage = assetDetailsPage.Click_WiringImgButton();
	     sa.assertEquals(  OverlayWiringImagePage.IsOverlayWiringPage_Title_Visible(), true, "Fail:IsOverlayWiringPage_Title is not Visible ");
	     sa.assertEquals(  OverlayWiringImagePage.Print_Button_Visible(), true, "Fail: Print_Button is not Visible");
	     sa.assertEquals(  OverlayWiringImagePage.Close_button_Visible(), true, "Fail: Close_button is not Visible");
	     sa.assertEquals(  OverlayWiringImagePage.Group_Visible(), true, "Fail: Groups that were created during setup is not Visible");
	     sa.assertAll();
	
    }
    
    //ASST032WO-Verify the on-click functionality of the print icon in the wiring overlay screen for a setup
	 @Test(groups = {"Regression" }, description = "ASST030WO-Verify the details displayed in Wiring overlay screen for a setup")
	    public void ASST032WO() throws InterruptedException, IOException {
		     extentTest = extent.startTest("ASST030WO-Verify the details displayed in Wiring overlay screen for a setup");
		     SoftAssert sa = new SoftAssert();
		     //assetHubPage = assetDetailsPage.ClickBackBtn();
		     assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		     assetDetailsPage.Click_SetupName("manual 1 min sampling");
		     OverlayWiringImagePage = assetDetailsPage.Click_WiringImgButton();
		     OverlayWiringImagePage.Click_PrintIcon();
		     sa.assertEquals(OverlayWiringImagePage.GroupWiring_Report_Visible(), true, "Fail:GroupWiring_Report_Visible is not Visible ");
		     sa.assertEquals(OverlayWiringImagePage.All_GroupOverlay_Report_Visible(),true, "Fail: All_GroupOverlay_Report_Visible is not Visible");
		     sa.assertAll();
	 }
	 
   //ASST033WO-Verify the on-click functionality of the _Group Overlay Report_ btn in the wiring overlay screen for a setup
	 @Test(groups = {"Regression" }, description = "ASST033WO-Verify the on-click functionality of the _Group Overlay Report_ btn in the wiring overlay screen for a setup")
	         public void ASST033WO() throws InterruptedException, IOException {
		     extentTest = extent.startTest("ASST033WO-Verify the on-click functionality of the _Group Overlay Report_ btn in the wiring overlay screen for a setup");
		     SoftAssert sa = new SoftAssert();
		    // assetHubPage = assetDetailsPage.ClickBackBtn();
		     assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		     assetDetailsPage.Click_SetupName("manual 1 min sampling");
		     OverlayWiringImagePage = assetDetailsPage.Click_WiringImgButton();
		     OverlayWiringImagePage.Click_PrintIcon();
		     sa.assertEquals(OverlayWiringImagePage.GroupOverlayRprtGenerate_Popupvisible(), true, "Fail:GroupOverlayRprtGenerate Popup window visible is not Visible ");
		     sa.assertAll();
	 }
	  
  //ASST034WO-Verify the on-click functionality of the _All Group Overlay Report_ btn in the wiring overlay screen for a setup
	 @Test(groups = {"Regression" }, description = "ASST034WO-Verify the on-click functionality of the _All Group Overlay Report_ btn in the wiring overlay screen for a setup")
	         public void ASST034WO() throws InterruptedException, IOException {
		     extentTest = extent.startTest("ASST034WO-Verify the on-click functionality of the _All Group Overlay Report_ btn in the wiring overlay screen for a setup");
		     SoftAssert sa = new SoftAssert();
		   //assetHubPage = assetDetailsPage.ClickBackBtn();
		     assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		     assetDetailsPage.Click_SetupName("manual 1 min sampling");
		     OverlayWiringImagePage = assetDetailsPage.Click_WiringImgButton();
		     OverlayWiringImagePage.Click_PrintIcon();
		     OverlayWiringImagePage.All_GroupOverlayReportGenerate_Pop_click();
		     sa.assertEquals(OverlayWiringImagePage.All_GroupOverlayReportGenerate_Popupvisible(), true, "Fail:AllGroupOverlayRprtGenerate Popup window is not Visible ");
		     sa.assertAll();
	 }
	
	
//	ASST036WO-Verify user is unable to generate the wiring overlay report from Asset details screen when there is no report generation privilege given
  
	
		 @Test(groups = {"Regression" }, description = "ASST036WO-Verify user is unable to generate the wiring overlay report from Asset details screen when there is no report generation privilege given")
         public void ASST036WO() throws InterruptedException, IOException {
	extentTest = extent.startTest("Verify the display of Asset in Asset hub page when any Asset is edited");
	SoftAssert sa = new SoftAssert();

		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.enterAssetID("01");
		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
		
		sa.assertEquals(assetDetailsPage.NewSetupCreate_Btn(), "true",
				"FAIL: New SetUp Button is not Present");
		sa.assertAll();;
}
	
	//ASST037-Verify the on-click functionality of the print icon for a setup
}

