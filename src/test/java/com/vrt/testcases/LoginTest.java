/**
 * @author manoj.ghadei
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
//import com.vrt.Listners.AllureReportListner;
import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.utility.TestUtilities;
import com.vrt.utility.setupCreationUtility;
import com.vrt.utility.userManagementUtility;


public class LoginTest extends BaseClass{
	
	public LoginTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


	public ExtentReports extent;
	public ExtentTest extentTest;
	TestUtilities tu = new TestUtilities();
	
	//Initialization of pages
	LoginPage MainLoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	
	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_LoginTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("Login Test in Progress..");
		
		/*
		//Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		// Rename the VRT folder if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTSetups");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\", "Cache");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTEquipments");
		*/
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		MainLoginPage = new LoginPage();
		extent.addSystemInfo("VRT Version", MainLoginPage.get_SWVersion_About_Text());
		AppClose();
		Thread.sleep(1000);
		
	}
	
	//After All the tests are conducted
	//@AfterTest
	@AfterClass
	public void endReport(){
		extent.flush();
		extent.close();
		System.out.println("Login Test Completed.");
	}
	
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		MainLoginPage= new LoginPage();
	}

	@AfterMethod(alwaysRun=true)
	public void Teardown(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # "+result.getName()+" #"); //to add name in extent report
			// TearDown of the App
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
			//String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver, result.getName());
			//extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2)); //to add screenshot in extent report
		}		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		//MainLoginPage.resetWebElements();
		driver.quit();
	}
	
	
	/********
	Test Cases
	*********/
	
	
	//installation with default provided credentials
	@Test(groups = {"Regression"}, description="LOGIN_001- Verify if user can log "
			+ "into the Kaye Application after installation with default provided credentials")
	public void LOGIN_001() throws InterruptedException, IOException {
		extentTest = extent.startTest("LOGIN_001- Verify if user can log into the Kaye Application"
				+ " after installation with default provided credentials");

		SoftAssert sa = new SoftAssert();
		UserManagementPage = MainLoginPage.DefaultLogin();

		sa.assertEquals(UserManagementPage.IsUMscreenDisplayed(), true, "FAIL: Unable to Login"
				+" with defualt Kaye/411 login credentials");
		//LOGIN_001- Verify if user can log into the Kaye Application after 
		
		sa.assertAll();
	}
	
	//LOGIN_001b-Verify that Policies,Preferences screens are disabled for login with First installation
	@Test(groups = {"Regression"}, description="LOGIN_001b-Verify that Policies,Preferences screens "
			+ "are disabled for login with First installation")
	public void LOGIN_001b() throws InterruptedException, IOException {
		extentTest = extent.startTest("LOGIN_001b-Verify that Policies,Preferences screens are disabled"
				+ " for login with First installation");

		SoftAssert sa = new SoftAssert();
		UserManagementPage = MainLoginPage.DefaultLogin();

		sa.assertEquals(UserManagementPage.IsPreferenceTab_Enabled(), false, 
				"FAIL: Preference tab appears to be Enabled");
		sa.assertEquals(UserManagementPage.IspoliciesTab_Enabled(), false, 
				"FAIL: Policies tab appears to be Enabled");
		
		sa.assertAll();
	}
	
	//LOGIN_001_2 -Verify the first time app install login credentials with incorrect password
	@Test(groups = {"Regression"}, description="LOGIN_001_2 -Verify the first time app install"
			+ " login credentials with incorrect password")
	public void LOGIN_001c() throws InterruptedException, IOException {
		extentTest = extent.startTest("LOGIN_001_2 -Verify the first time app install login "
				+ "credentials with incorrect password");

		SoftAssert sa = new SoftAssert();
		MainLoginPage.LoginEntry("Kaye", "123");
		MainLoginPage.ClickLoginBtn();
		
		String exp_alertMsg = "Invalid Credential, Please try again";
		
		sa.assertEquals(tu.get_AlertMsg_text(), exp_alertMsg, "FAIL:Either wrong PW is "
				+ "accepted for default Login else no alert message is displayed");
		sa.assertAll();
	}
	
	//LOGIN_002- Verify if clicking on the Kaye application tab opens the Login Screen of the application
	@Test(groups = {"Regression", "Sanity"},description="LOGIN_002- Verify if clicking on the Kaye "
			+ "application tab opens the Login Screen of the application")
	public void LOGIN_002() throws Exception {		
		extentTest = extent.startTest("LOGIN_002- Verify if clicking on the Kaye application tab opens "
				+ "the Login Screen of the application");
		SoftAssert sa = new SoftAssert();
		
		boolean state = MainLoginPage.Is_VRTAppLoginScreen_Displayed();
				
		sa.assertEquals(state, true, "FAIL: VRT App either didn't launch" 
		+" or Launched but not into LOGIN SCREEN");
		
		sa.assertAll();		
	}
	
	//LOGIN_003- Verify  the contents of the Kaye application Login Screen
	@Test(groups = {"Regression"}, description="LOGIN_003- Verify  the contents of the Kaye application "
			+ "Login Screen")
	public void LOGIN_003() throws Exception {	
		extentTest = extent.startTest("LOGIN_003- Verify  the contents of the Kaye application "
				+ "Login Screen");
		SoftAssert sa = new SoftAssert();
		//Validate Product Name
		String expectedAppName = "ValProbe RT System";
		//String ActualAppName = MainLoginPage.AppName();
		sa.assertEquals(MainLoginPage.AppName(), expectedAppName, "FAIL: Invalid Product Name displayed");		
		// Validate presence of UserID text field
		sa.assertEquals(MainLoginPage.UserIDFieldPresence(), true, "FAIL: No UID field present");		
		//Validate for Password text field presence
		sa.assertEquals(MainLoginPage.UserPWFieldPresence(), true, "FAIL: No PW field present");		
		//Validate for Login Button presence
		sa.assertEquals(MainLoginPage.LoginBtnPresence(), true, "FAIL: LOGIN button is not Present");		
		// Check for CANCEL Button presence
		sa.assertEquals(MainLoginPage.CancelBtnPresence(), true, "FAIL: CANCEL button is not displayed");
				
		sa.assertAll();
	}
	
	//LOGIN_004- Verify if the input data in the Password field is displayed as asterisk
	@Test(groups = {"Regression"}, description="LOGIN_004- Verify if the input data in the"
			+ " Password field is displayed as asterisk")
	public void LOGIN_004() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_004- Verify if the input data in the Password "
				+ "field is displayed as asterisk");
		SoftAssert sa4 = new SoftAssert();
		
		MainLoginPage.EnterUserPW("abc");
		Thread.sleep(1000);
		String actualPWTxt = MainLoginPage.GetTextUserPWField();
		System.out.println(actualPWTxt);
				
		sa4.assertNotEquals(actualPWTxt, "abc", "FAIL: The PW field data is not displayed in Astrisk");
		sa4.assertAll();
	}
	
	//LOGIN_005- Verify if user can login into the application by entering UserID and 
	//Password and then clicking on Login button
	@Test(groups = {"Regression", "Sanity"}, description="LOGIN_005- Verify if user can login into the "
			+ "application by entering UserID and Password and then clicking on Login button")
	public void LOGIN_005() throws InterruptedException, IOException {
		extentTest = extent.startTest("LOGIN_005- Verify if user can login into the application by "
				+ "entering UserID and Password and then clicking on Login button");
		SoftAssert sa5 = new SoftAssert();
		
		//Login using Default Kaye/411
		UserManagementPage=MainLoginPage.DefaultLogin();
		//Create the 1st User
		MainLoginPage = UserManagementPage.FirstUserCreation("User1", getUID("adminFull"), getPW("adminFull"),
				getPW("adminFull"), "FullAdmin", "123456789", "abc@gmail.com");
		//Login with new User Credentials
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), getPW("adminFull"));
				
		//Verify if New User is logged in correctly
		sa5.assertEquals(MainHubPage.LoggedinUserName(), "User1", "FAIL: Incorrect User "
				+ "is logged to the system or unable to Login in");
		
		sa5.assertAll();
	}

	//LOGIN_006- Verify if on clicking the Cancel button, app gets closed
	@Test(groups = "Regression", description="LOGIN_006- Verify if on clicking the "
			+ "Cancel button, app gets closed")
	public void LOGIN_006() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_006");
		SoftAssert sa = new SoftAssert();
		
		MainLoginPage.EnterUserID("a");
		MainLoginPage.EnterUserPW("abc");
		MainLoginPage.ClickCancelBtn();
		Thread.sleep(1000);
		sa.assertEquals(MainLoginPage.Is_VRTAppLoginScreen_Displayed(), false, "FAIL: App does not SHUTDOWN "
				+"on entering 3 times INVALID User Credentials");

	}
	
	//LOGIN_007- Verify if user is not allowed to login with invalid credentials
	@Test(groups = {"Regression"}, description="LOGIN_007- Verify if user is not allowed to "
			+ "login with invalid credentials")
	public void LOGIN_007() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_007- Verify if user is not allowed to login "
				+ "with invalid credentials");
		SoftAssert sa7 = new SoftAssert();
		
		MainLoginPage.EnterUserID("a");
		MainLoginPage.EnterUserPW("123");
		MainLoginPage.ClickLoginBtn();
		Thread.sleep(1000);
		
		sa7.assertEquals(MainLoginPage.InvalidLoginAlertmsgPresence(), true, "FAIL: App allowing"
				+" to login with INVALID LOGIN credentials");
		sa7.assertAll();
	}
	
	//LOGIN_008- Verify if user is not allowed to login if the UserId or Password 
	//field is left blank
	@Test(groups = {"Regression"}, description="LOGIN_008- Verify if user is not "
			+ "allowed to login if the UserId or Password field is left blank")
	public void LOGIN_008() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_008- Verify if user is not allowed to "
				+ "login if the UserId or Password field is left blank");
		SoftAssert sa8 = new SoftAssert();
		
		sa8.assertEquals(MainLoginPage.LoginBtnEnablestatus(), false, "FAIL: Login button enabled"
				+ " without any UID/PW entry");
		
		sa8.assertAll();
	}
		
	//LOGIN_009- Verify if the application closes on three unsuccessful login attempts
	@Test(groups = {"Regression"}, description="LOGIN_009- Verify if the application "
			+ "closes on three unsuccessful login attempts")
	public void LOGIN_009() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_009- Verify if the application closes on three "
				+ "unsuccessful login attempts");
		SoftAssert sa = new SoftAssert();
		
		MainLoginPage.EnterUserID("1");
		MainLoginPage.EnterUserPW("123");
		MainLoginPage.ClickLoginBtn();
		MainLoginPage.EnterUserID("2");
		MainLoginPage.EnterUserPW("123");
		MainLoginPage.ClickLoginBtn();
		MainLoginPage.EnterUserID("3");
		MainLoginPage.EnterUserPW("123");
		MainLoginPage.ClickLoginBtn();
		
		//System.out.println(MainLoginPage.Is_VRTAppLoginScreen_Displayed());
		sa.assertEquals(MainLoginPage.Is_VRTAppLoginScreen_Displayed(), false, "FAIL: App does not SHUTDOWN "
				+"on entering 3 times INVALID User Credentials");
		sa.assertAll();
	}
	
	//LOGIN_010- Verify if the first created admin user is not forced to change his 
	//password during first login instance after user creation
	@Test(groups = {"Regression"}, description="LOGIN_010- Verify if the first created "
			+ "admin user is not forced to change his password during first login instance after user creation")
	public void LOGIN_010() throws InterruptedException, IOException {
		extentTest = extent.startTest("LOGIN_010- Verify if the first created admin user is "
				+ "not forced to change his password during first login instance after user creation");
		SoftAssert sa = new SoftAssert();
		
		MainLoginPage.EnterUserID(getUID("adminFull"));
		MainLoginPage.EnterUserPW(getPW("adminFull"));
		
		MainHubPage=MainLoginPage.ClickonLoginBtn();
		if (MainHubPage.Is_mainHubPageTitle_Visible()) {
			//System.out.println(MainHubPage.Is_mainHubPageTitle_Visible());
			sa.assertEquals(true, true, 
					"FAIL: Very 1st User Forced to change PW instaed of redirecting to Main Hub Page");
			sa.assertAll();
		}else {
			sa.assertEquals(false, true, 
					"FAIL: Very 1st User Forced to change PW instaed of redirecting to Main Hub Page");
			sa.assertAll();
		}	
		
		sa.assertAll();
	
	}
	
	//LOGIN_011- Verify if the Change Password tick-box is in Enabled state during first time login 
	//after creating the first admin user
	@Test(groups = {"Regression"}, description="LOGIN_011- Verify if the Change Password tickbox is in "
			+ "Enabled state during first time login after creating the first admin user")
	public void LOGIN_011() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_011- Verify if the Change Password tickbox is in Enabled state "
				+ "during first time login after creating the first admin user");
		SoftAssert sa = new SoftAssert();
		
		MainLoginPage.EnterUserID(getUID("adminFull"));
		MainLoginPage.EnterUserPW("Welcome1@AM");
		
		sa.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), true, "FAIL: The 1st User is "
		+"Not allowed to Change its PW on 1st time Login");
		sa.assertAll();		
	}

	//LOGIN_012- Verify if the Change Password tickbox is in enabled state during consecutive 
	//logins by the first admin user
	@Test(groups = {"Regression"}, description="LOGIN_012- Verify if the Change Password tickbox"
			+ " is in enabled state during consecutive logins by the first admin user")
	public void LOGIN_012() throws InterruptedException, IOException {
		extentTest = extent.startTest("LOGIN_012- Verify if the Change Password tickbox is "
				+ "in enabled state during consecutive logins by the first admin user");
		SoftAssert sa = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		MainLoginPage=MainHubPage.UserSignOut();
		MainLoginPage.EnterUserID(getUID("adminFull"));
		MainLoginPage.EnterUserPW(getPW("adminFull"));
		
		sa.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), true, "FAIL: The 1st User is "
		+"NOT allowed to Change its PW with Change PW option in disbaled state");
		sa.assertAll();	
	}
	
	//LOGIN_013- Verify if checking the Change Password tickbox allows the user to change his password
	@Test(groups = {"Regression"}, description="LOGIN_013- Verify if checking the Change Password"
			+ " tickbox allows the user to change his password")
	public void LOGIN_013() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_013- Verify if checking the Change Password tickbox "
				+ "allows the user to change his password");
		SoftAssert sa = new SoftAssert();
		
		MainLoginPage.EnterUserID(getUID("adminFull"));
		MainLoginPage.EnterUserPW(getPW("adminFull"));
		MainLoginPage.ClickChangePWCheckbox();
		MainLoginPage.ClickLoginBtn();

		sa.assertEquals(MainLoginPage.NewPWFieldPresence(), true, "FAIL: New PW field "
				+ "is not enabled/displayed to Change PW");
		sa.assertAll();
	}
	
	//LOGIN_014- Verify if unchecking the Change Password tickbox restricts the user 
	//from changing his password
	@Test(groups = {"Regression"}, description="LOGIN_014- Verify if unchecking the "
			+ "Change Password tickbox restricts the user from changing his password")
	public void LOGIN_014() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_014- Verify if unchecking the Change "
				+ "Password tickbox restricts the user from changing his password");
		SoftAssert sa = new SoftAssert();
		
		MainLoginPage.EnterUserID(getUID("adminFull"));
		MainLoginPage.EnterUserPW(getPW("adminFull"));
		MainLoginPage.ClickChangePWCheckbox();
		MainLoginPage.ClickLoginBtn();
		
		
		try {
			MainLoginPage.ClickChangePWCheckbox();
			
			sa.assertEquals(MainLoginPage.NewPWFieldPresence(), false, "FAIL: New PW field is enabled/displayed to Change PW"
					+ " even if the Change PW checkbox is unchecked");
			sa.assertAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	//LOGIN_015- Verify if user can change the password by entering new password and clicking on the OK button
	@Test(groups = {"Regression"}, description="LOGIN_015- Verify if user can change the password "
			+ "by entering new password and clicking on the OK button")
	public void LOGIN_015() throws InterruptedException, IOException {
		extentTest = extent.startTest("LOGIN_015- Verify if user can change the password by "
				+ "entering new password and clicking on the OK button");
		SoftAssert sa = new SoftAssert();

		
		//Login using very 1st User credentials
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		
		//Create the 2nd Admin User
		UserManagementPage.CreateAdminUser(getUID("adminFull"), getPW("adminFull"), "AdminUser2", getUID("CustoAdmin"), 
				"Welcome1@AM", "CustoAdmin", "123456789", "abc@gmail.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry(getUID("CustoAdmin"), "Welcome1@AM");
		MainLoginPage.ClickLoginBtn();
		//Change the forced password
		MainHubPage=MainLoginPage.EnterNewPWtext(getPW("CustoAdmin"));
		MainLoginPage=MainHubPage.UserSignOut();		
		MainLoginPage.LoginEntry(getUID("CustoAdmin"), getPW("CustoAdmin"));
		//Click the Change password option
		MainLoginPage.ClickChangePWCheckbox();
		MainLoginPage.ClickLoginBtn();
		MainHubPage=MainLoginPage.EnterNewPWtext("Welcome3@AM");				
		
		sa.assertEquals(MainHubPage.LoggedinUserName(), "AdminUser2", "FAIL: Password did not change for the User");
		sa.assertAll();
	}
		
	//LOGIN_016- Verify if clicking on the Cancel button in the Change password field 
	//restores the previous password
	@Test(groups = {"Regression"}, description="LOGIN_016- Verify if clicking on the Cancel button"
			+ " in the Change password field restores the previous password")
	public void LOGIN_016() throws InterruptedException, IOException {
		extentTest = extent.startTest("LOGIN_016- Verify if clicking on the Cancel button in the "
				+ "Change password field restores the previous password");
		SoftAssert sa = new SoftAssert();
		
		MainLoginPage.LoginEntry(getUID("adminFull"), getPW("adminFull"));
		MainLoginPage.ClickChangePWCheckbox();
		MainLoginPage.ClickLoginBtn();
		MainLoginPage.enterNewPW("Welcome3@AM");
		MainLoginPage.enterConfNewPW("Welcome3@AM");
		MainLoginPage.ClickCancelBtn();
		Thread.sleep(1000);
		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		MainLoginPage= new LoginPage();		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), getPW("adminFull"));

		sa.assertEquals(MainHubPage.LoggedinUserName(), "User1", "FAIL: Cancel button"
				+ " at New PW change did not work as intended");
		sa.assertAll();
	}
	
	//LOGIN_017- Verify if subsequent users created are forced to change their password during first login instance
	@Test(groups = {"Regression", "Sanity"}, description="LOGIN_017- Verify if subsequent users created are "
			+ "forced to change their password during first login instance")
	public void LOGIN_017() throws InterruptedException, IOException {
		extentTest = extent.startTest("LOGIN_017- Verify if subsequent users created are forced to change "
				+ "their password during first login instance");
		SoftAssert sa = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();
		
		UserManagementPage.CreateAdminUser(getUID("adminFull"), getPW("adminFull"), "User2", 
				getUID("SysAdmin"), "Welcome1@AM", "SysAdmin", "123456789", "user1@aas.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry(getUID("SysAdmin"), "Welcome1@AM");
		MainLoginPage.ClickLoginBtn();

		sa.assertEquals(MainLoginPage.NewPWFieldPresence(), true, "FAIL: A New User created" 
				+ " is not forced to change PW on logging in for 1st time with New PW field in Disabled/Invisible state");

		sa.assertAll();
	}
	
	//LOGIN_018- Verify if the Change Password tickbox is in disabled state during first  
	//login instance for subsequent users
	@Test(groups = {"Regression", "Sanity"}, description="LOGIN_018- Verify if the "
			+ "Change Password tickbox is in disabled state during first  login instance for subsequent users")
	public void LOGIN_018() throws InterruptedException, IOException {
		extentTest = extent.startTest("LOGIN_018- Verify if the Change Password tickbox is in "
				+ "disabled state during first  login instance for subsequent users");
		SoftAssert sa = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();
		
		UserManagementPage.CreateAdminUser(getUID("adminFull"), getPW("adminFull"), "User18", 
				getUID("TestAdmin"), "Welcome1@AM", "TestAdmin", "123456789", "user18@aas.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry(getUID("TestAdmin"), "Welcome1@AM");
		MainLoginPage.ClickLoginBtn();
		
		sa.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), false, "FAIL: A New User created"
				+ " is not forced to change PW on logging in for 1st time with ChangePWCheck box "
				+ "in Enabled state and in Unchecked state");

		sa.assertAll();
	}
	
	//LOGIN_019- Verify if the Change Password tickbox is in enabled state during further login 
	//attempts by the subsequent users	
	@Test(groups = {"Regression"}, description="LOGIN_019- Verify if the Change Password "
			+ "tickbox is in enabled state during furthur login attempts by the subsequent users")
	public void LOGIN_019() throws InterruptedException, IOException {
		extentTest = extent.startTest("LOGIN_019- Verify if the Change Password tickbox is in "
				+ "enabled state during furthur login attempts by the subsequent users");
		SoftAssert sa = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();
		
		UserManagementPage.CreateSupervisorUser(getUID("adminFull"), getPW("adminFull"), "User3", 
				getUID("SysSupervisor"), "Welcome1@AM", "SysSupervisor", "123456789", "user2@aas.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry(getUID("SysSupervisor"), "Welcome1@AM");
		MainLoginPage.ClickLoginBtn();
		MainHubPage=MainLoginPage.EnterNewPWtext(getPW("SysSupervisor"));
		MainLoginPage=MainHubPage.UserSignOut();
		MainLoginPage.LoginEntry(getUID("SysSupervisor"), getPW("SysSupervisor"));

		sa.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), true, "FAIL: ChangePWCheck box "
				+ "appear to be in Disable state");
		sa.assertAll();	
	}
	
	//LOGIN_020- Verify if a user is forced to change his password while login, 
	//if his password has been changed by the admin user	
	@Test(groups = {"Regression"}, description="LOGIN_020- Verify if a user is "
			+ "forced to change his password while login, if his password has been changed by the admin user")
	public void LOGIN_020() throws InterruptedException, IOException {
		extentTest = extent.startTest("LOGIN_020- Verify if a user is forced to change his password while login,"
				+ " if his password has been changed by the admin user");
		SoftAssert sa = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();
		
		UserManagementPage.CreateOperatorUser(getUID("adminFull"), getPW("adminFull"), 
				"User4", getUID("SysOperator"), "Welcome1@AM", "SysOperator", "123456789", "user8@aas.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry(getUID("SysOperator"), "Welcome1@AM");
		MainLoginPage.ClickLoginBtn();
		MainHubPage=MainLoginPage.EnterNewPWtext("Welcome2@AM"); //User8 forced to Rest PW
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();		
		UserManagementPage.clickAnyUserinUserList("User4");
		UserManagementPage.enterNewUserPW(getPW("SysSupervisor"));
		UserManagementPage.enterNewUserConfPW(getPW("SysSupervisor"));
		UserManagementPage.ClickTitlefield();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry(getUID("SysOperator"), getPW("SysSupervisor"));
		MainLoginPage.ClickLoginBtn();

		sa.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), false, "FAIL: User is NOT "
				+ "forced to change his password while login, if his password has been changed by the admin user");
		sa.assertEquals(MainLoginPage.NewPWFieldPresence(), true, "FAIL: User is NOT " 
				+ " forced to change his password while login with New PW field in Disabled/Invisible state");

		sa.assertAll();
	}

	//LOGIN_021- Verify if a Disabled user is not allowed to login to the system	
	@Test(groups = {"Regression"}, description="LOGIN_021- Verify if a Disabled user "
			+ "is not allowed to login to the system")
	public void LOGIN_021() throws InterruptedException, IOException {
		extentTest = extent.startTest("LOGIN_021- Verify if a Disabled user is not allowed "
				+ "to login to the system");
		SoftAssert sa = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();
		
		UserManagementPage.CreateOperatorUser(getUID("adminFull"), getPW("adminFull"), 
				"Dsbluser", getUID("Dsbluser"), getPW("Dsbluser"), "Operator", "123456789", "user8@aas.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();		
		UserManagementPage.clickAnyUserinUserList("Dsbluser");
		//Click on the disable checkbox
		UserManagementPage.Select_DisableUserCheckBox();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry(getUID("Dsbluser"), getPW("Dsbluser"));
		MainLoginPage.ClickLoginBtn();
		
		String expAlertMsg = "User account has been disabled, please contact administrator";

		sa.assertEquals(tu.get_AlertMsg_text(), expAlertMsg, "FAIL: User is not Disabled");
		sa.assertAll();
	}
	
	//LOGIN_022- Verify if on 3 unsuccessful login attempts with the same UserId, 
	//the User account is blocked	
	@Test(groups = {"Regression"}, description="LOGIN_022- Verify if on 3 unsuccessful "
			+ "login attempts with the same UserId, the User account is blocked")
	public void LOGIN_022() throws InterruptedException, IOException {
		extentTest = extent.startTest("LOGIN_022- Verify if on 3 unsuccessful login "
				+ "attempts with the same UserId, the User account is blocked");
		SoftAssert sa = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();
		
		UserManagementPage.CreateSupervisorUser(getUID("adminFull"), getPW("adminFull"), 
				"Newuser", getUID("Newuser"), getPW("Newuser"), "Supervisor", "123456789", "CustoSup@aas.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		//Enter wrong PW for the above user created
		MainLoginPage.InvalidLogin(getUID("Newuser"), "a");
		//MainLoginPage.ClickLoginBtn();
		tu.click_Close_alertmsg();
		MainLoginPage.ClickLoginBtn();
		tu.click_Close_alertmsg();
		MainLoginPage.ClickLoginBtn();
		
		String expAlertMsg = "User account has been disabled, please contact administrator";

		sa.assertEquals(tu.get_popup_text(), expAlertMsg, "FAIL: User is not Disabled");
		sa.assertAll();
	}
	
	//LOGIN_024- Verify the password change in login screen by using last 20 passwords	
	@Test(groups = {"Regression"}, dataProvider = "LOGIN_024", dataProviderClass = userManagementUtility.class,
			description="LOGIN_024- Verify the password change in "
			+ "login screen by using last 20 passwords")
	public void LOGIN_024(String UName, String UID, String Pwd, String Title,
			String Utype, String phno, String Email, String ImageName) throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest("LOGIN_024- Verify the password change in login screen "
				+ "by using last 20 passwords");
		SoftAssert sa = new SoftAssert();
				
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();
		
		//1st PW created
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_AllFields(UName, UID, Pwd,  Title,
				Utype, phno, Email, ImageName);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_Close_alertmsg();
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		//Enter newly created user ID & 1st PW
		MainLoginPage.LoginEntry(UID, Pwd);
		MainLoginPage.ClickLoginBtn();
		//2nd PW created
		MainHubPage=MainLoginPage.EnterNewPWtext("111112"); //User8 forced to Rest PW		
		MainLoginPage=MainHubPage.UserSignOut();
		
		String pwd = null;
		String npw = null;
		for (int i = 111112; i < 111132; i++) {
			//int pw1 = Integer.parseInt(pw);
			pwd = Integer.toString(i);
			npw = Integer.toString(i+1);
			MainHubPage=MainLoginPage.ChangeNewPW(UID, pwd, npw);//Method to change PW forcibly in Login Pg
			MainLoginPage=MainHubPage.UserSignOut();
			System.out.println(pwd);
		}
		
		System.out.println(pwd);
		//Enter newly created user ID & the 21st PW
		MainHubPage=MainLoginPage.ChangeNewPW(UID, "111132", "111112");
		//MainHubPage = MainLoginPage.Login(UID, "111111");
		sa.assertEquals(MainHubPage.LoggedinUserName(), UName, "FAIL: Incorrect User "
				+ "is logged to the system or unable to Login in");
		
		sa.assertAll();
	}
	
	
}
