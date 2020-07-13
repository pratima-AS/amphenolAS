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
import com.vrt.utility.TestUtilities;




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
	Copyassetpage Copyassetpage;
	FileManagementPage FileManagementPage;
	SyncInPage SyncInPage;
	SyncInAssetListPage SyncInAssetListPage;
	Setup_defineSetupPage Setup_defineSetupPage;
	CopySetuppage CopySetuppage;

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
	/*	renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
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

		// Method to Create 1st Asset 
		assetHubPage=MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath", "AAS", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetCreationPage.clickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		
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
*/
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
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
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

	
	/*// 01-ASST016
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
	}*/
	/*
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

<<<<<<< HEAD
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
=======
	
	// 02-ASST017
	@Test(groups = { "Sanity", "Regression" }, description = "ASST017-Verify if Edit Icon is present"
			+ " at the right top corner of assets detail page and opens the Edit asset - asset details "
			+ "screen with the possibility to edit the selected asset")
	public void ASST017() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("ASST017-Verify if clicking Edit Icon in assets " + "detail page opens the Edit asset");
		SoftAssert sa = new SoftAssert();
>>>>>>> branch 'master' of https://github.com/amphenolAS/VRT.git

		}
		
	//ASST003-Verify the on-click functionality of edit icon for Asset
	
	@Test(groups = {
		"Regression" }, description = "ASST002-Verify the details displayed on the 2 sections in Asset details screen")
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
	
<<<<<<< HEAD
	//ASST004-Verify if the details are saved during Edit Asset post modification

	@Test(groups = {
			"Regression" }, description = "ASST004-Verify if the details are saved during Edit Asset post modification")
	public void ASST004() throws Exception {
		extentTest = extent.startTest("Verify if the details are saved during Edit Asset post modification");
=======
	// 03-ASST018
	@Test(groups = { "Sanity", "Regression" }, description = "ASST018-Verify if clicking on "
			+ "Back Button at the left top to return to Assets Hub page")
	public void ASST018() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("ASST018-Verify if clicking on Back Button at the left top to return to Assets Hub page");
>>>>>>> branch 'master' of https://github.com/amphenolAS/VRT.git
		SoftAssert sa = new SoftAssert();

<<<<<<< HEAD
=======
		assetHubPage = assetDetailsPage.ClickBackBtn();

		sa.assertEquals(assetHubPage.assetPageTitle(), "Assets",
				"FAIL: TC-ASST018 -Incorrect Asset Hub Page title or landed into incorrect Page");
		sa.assertAll();
	}

	
	// ASST019 = Manual Test

	
	// 04-ASST020
	@Test(groups = { "Sanity", "Regression" }, description = "ASST020-Verify if the data displayed "
			+ "in the assets detail page is exactly same as the information given for asset in Create new Asset page")
	public void ASST020() throws InterruptedException, ParseException, IOException {
		extentTest = extent.startTest("ASST020-Verify if the data displayed in the assets detail page "
				+ "is exactly same as the information given for asset in Create new Asset page");
		SoftAssert sa = new SoftAssert();

		String[] act_AssetDetailData = assetDetailsPage.get_assetinfo();
		// System.out.println(Arrays.toString(act_AssetDetailData));

>>>>>>> branch 'master' of https://github.com/amphenolAS/VRT.git
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.enterAssetID("02");
		assetCreationPage.clickSaveBtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		String EditedValue = assetCreationPage.getEqpID();
		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		String displayValue = assetHubPage.getAssetIDvalue();
		sa.assertEquals(EditedValue,displayValue,
				"FAIL:Edited value should be displayed in Asset Creation page ");
		sa.assertAll();
		
		System.out.println(displayValue);	
		
	}
	
 //ASST006-Verify the Back Button functionality in Edit Asset screen
	@Test(groups = {
			"Regression" }, description = "ASST004-Verify if the details are saved during Edit Asset post modification")
	public void ASST004() throws Exception {
		extentTest = extent.startTest("Verify if the details are saved during Edit Asset post modification");
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
	*/
	//ASST012-Verify the on-click of Delete icon for Assets which has files in it
/*	
		@Test(groups = { "Regression" }, description = "ASST012-Verify the on-click of Delete icon for Assets which has files in it")
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
	@Test(groups = { "Regression" }, description = "ASST013-Verify for  a fresh asset with no activities - Setups, Qualifications Documents and  Reports -  as mentioned, all tiles should display 0")
	   public void ASST013() throws InterruptedException, ParseException, IOException {
		
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
}
		
		//ASST017STP-Verify New button is available and clicking on button user should able to define new setup
		
		@Test(groups = { "Regression" }, description = "ASST015STP-Verify the Setup date and time for a new Setup")
		   public void ASST017() throws InterruptedException, ParseException, IOException {
			
			extentTest = extent.startTest("ASST015STP-Verify the Setup date and time for a new Setup");
			SoftAssert sa = new SoftAssert();
			Setup_defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
			sa.assertEquals(Setup_defineSetupPage.defineSetupPage_state(), true,
					"FAIL:The setup define page should be displayed ");
			
		}	
		*/	
	//ASST018STP-Verify on-click of Copy button for a setup
	@Test(groups = { "Regression" }, description = "ASST018STP-Verify on-click of Copy button for a setup")
	   public void ASST018() throws InterruptedException, ParseException, IOException {
		
		extentTest = extent.startTest("ASST018STP-Verify on-click of Copy button for a setup");
		SoftAssert sa = new SoftAssert();
		CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
		sa.assertEquals(CopySetuppage.CopySetupPage_Title(), true,
				"FAIL:The Copy setup tile should be displayed");
		
	}	
	//ASST019STP-Verify Copy setup functionality when there is only one Asset available
	
	
}