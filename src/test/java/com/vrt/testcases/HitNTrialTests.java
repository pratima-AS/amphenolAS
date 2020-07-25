/**
 * @author manoj.ghadei
 *
 */

package com.vrt.testcases;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ClickAction;
import org.testng.Assert;
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
import com.vrt.pages.Setup_CalculationsPage;
import com.vrt.pages.Setup_GroupSensorsPage;
import com.vrt.pages.Setup_QualParamPage;
import com.vrt.pages.Setup_ReviewPage;
import com.vrt.pages.Setup_SensorConfigPage;
import com.vrt.pages.Setup_defineSetupPage;
import com.vrt.pages.SyncInAssetListPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.FileManagementPage;
import com.vrt.pages.EquipmentHubPage;
import com.vrt.pages.VRTLoggerHubPage;
import com.vrt.pages.VRTLoggersDetailspage;
import com.vrt.pages.SyncInPage;
import com.vrt.pages.CopySetuppage;
import com.vrt.pages.Copyassetpage;

import com.vrt.utility.TestUtilities;
import com.vrt.utility.assetCreationUtility;
import com.vrt.utility.setupCreationUtility;
import com.vrt.utility.userManagementUtility;


public class HitNTrialTests extends BaseClass {
	
	public HitNTrialTests() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	TestUtilities tu = new TestUtilities();
	public ExtentReports extent;
	public ExtentTest extentTest;
	
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
	EquipmentHubPage EquipmentHubPage;
	VRTLoggerHubPage VRTLoggerHubPage;
	VRTLoggersDetailspage VRTLoggersDetailspage;
	FileManagementPage FileManagementPage;
	SyncInPage SyncInPage;
	SyncInAssetListPage SyncInAssetListPage;
	CopySetuppage CopySetuppage;
	Copyassetpage Copyassetpage;

	//Before test/Class
	@BeforeClass
	private void testsetup() throws IOException, InterruptedException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_" + "UserManagementTest" + ".html",
				true);
		extent.addSystemInfo("TestSuiteName", "UserManagementTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name2"));
		System.out.println("UserManagementTest in Progress..");

		/*
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
		UserManagementPage.clickPrivRunQual();
		UserManagementPage.clickPrivCreateEditAsset();
		UserManagementPage.clickPrivCreateEditSetup();
		UserManagementPage.clickPrivRunCal();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("dsbl1", "1D", getPW("Dsbluser"), getPW("Dsbluser"),
				"AdminNew", "System Administrator");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		//tu.click_Close_alertmsg();
		UserManagementPage.clickAnyUserinUserList("dsbl1");
		UserManagementPage.Select_DisableUserCheckBox();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		//SyncIn Method
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
		*/
		
	}
	
	
	//After All the tests are conducted
	@AfterClass
	//@AfterTest
	public void endReport(){
		//System.out.println("Check end");
		extent.flush();
		extent.close();
	}
	
	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		//MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		//UserManagementPage = MainHubPage.ClickAdminTile_UMpage();		
	}
	

	// AfterMethod- TearDown of the App
	@AfterMethod
	public void Teardown(ITestResult result) throws IOException {
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # "+result.getName()+" #"); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # "+result.getThrowable()+" #"); //to add error/exception in extent report
			
			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName()+" #");
			String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2)); //to add screenshot in extent report

		}		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		//driver.quit();
	}


	/*
	//Max 500 User creation script
	// ADMN0132
	@Test(dataProvider = "maxuser", dataProviderClass = userManagementUtility.class)

	public void ADMN012(String UName, String UID, String Pwd, String Title,
			String Utype, String phno, String Email, String ImageName) throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"ADMN012-Verify Invalid inputs that are not allowed for User ID Field at User management screen");

		SoftAssert sa8 = new SoftAssert();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_AllFields(UName, UID, Pwd,  Title,
				Utype, phno, Email, ImageName);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		//String ActInvalidUIDAlertMsg = UserManagementPage.AlertMsg();

		System.out.println("User " +UID+ " created");
	}*/
	
	// ADMN062
	@Test(groups = {
			"Regression" }, description = "ADMN062-Verify if Administrator is able to access the default privilege-Delete Assets")
	public void ADMN062() 
			throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN062-Verify if Administrator is able to access the default privilege-Delete Assets");

		String filepath= System.getProperty("user.dir") +  "\\src\\test\\resources\\TestData\\AutoLogs";
		List<String> fn = tu.get_fileNamesList(filepath);
		String expFileName= "1065306A4C9C5E7376FC.cfg";
		for (String filename : fn) {
			System.out.println(filename);
			if (!filename.contains(expFileName)) {
				System.out.println("Expected setup file is not copied to the destination folder");
			}
		}
	}

}
