package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
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
import com.vrt.pages.PoliciesPage;
import com.vrt.pages.Setup_SensorConfigPage;
import com.vrt.pages.Setup_defineSetupPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.Setup_GroupSensorsPage;
import com.vrt.utility.TestUtilities;
import com.vrt.utility.setupCreationUtility;
import com.vrt.pages.Setup_SensorDescriptionPage;
import com.vrt.pages.Setup_CalculationsPage;
import com.vrt.pages.Setup_QualParamPage;

public class setup_CalculationTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>SetupTestData.xlsx sheet for test data i/p

	public setup_CalculationTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
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
	Setup_SensorDescriptionPage Setup_SensorDescriptionPage;
	Setup_CalculationsPage Setup_CalculationsPage;
	Setup_QualParamPage Setup_QualParamPage;
	PoliciesPage PoliciesPage;

	// Before All the tests are conducted
	@BeforeTest
	public void PreSetup() throws InterruptedException, IOException, AWTException, ParseException {

		extent = new ExtentReports(
				System.getProperty("user.dir") + "/test-output/ER_" + "setup_CalculationTest" + ".html", true);
		extent.addSystemInfo("TestSuiteName", "setup_CalculationTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name2"));
		System.out.println("Setup_Calculation Test is  in Progress..");

		// Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		// Rename the cache Setup file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache\\ValProbeRT", "Setup.txt");
		// Rename the VRT Setups folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTSetups");

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		// Thread.sleep(500);
		LoginPage = new LoginPage();

		// Method to Create Very 1st User with All privilege UserManagementPage =
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
		MainHubPage = UserManagementPage.ClickBackButn();		

		// Method to Create 1st Asset
		assetHubPage = MainHubPage.Click_AssetTile();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath", "aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		
		//Method to enable D value editing in Policies page.
        assetHubPage = assetCreationPage.clickBackBtn();
        MainHubPage  = assetHubPage.click_BackBtn();
        UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
        PoliciesPage = UserManagementPage.Click_Policy();
        PoliciesPage.Enable_Editing_Dvalue();
        

		AppClose();
		Thread.sleep(500);

	}

	// After All the tests are conducted
	@AfterTest
	public void endReport_releaseMomory() {
		extent.flush();
		extent.close();
		// Setup_CalculationsPage.resetWebElements();
		// System.out.println("Reset Webelement memory released");
		System.out.println("Setup_Calculations Test Completed.");
	}

	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName("test");
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.enter_defineSetupPage_SensorCount("300");
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
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

	// Test Cases
	// CAL001-Verify the details displayed in Calculations screen
	@Test(groups = { "Regression" }, description = "CAL001-Verify the details displayed in Calculations screen")
	public void CAL001() throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL001-Verify the details displayed in Calculations screens");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(Setup_CalculationsPage.get_CalculationPage_titletext(), "Calculations",
				"FAIL: Calculations header is not displaying");

		sa.assertEquals(Setup_CalculationsPage.get_Setup_titletext(), "test", "FAIL: Set up title is not available");

		sa.assertEquals(Setup_CalculationsPage.leth_Btnstate(), true, "FAIL: leth_Btn  is not available");

		sa.assertEquals(Setup_CalculationsPage.Statistical_CalculationBtn_state(), true,
				"FAIL: Statistical_Calculation  Btn is not available");

		sa.assertEquals(Setup_CalculationsPage.Saturation_CalculationBtn_state(), true,
				"FAIL:Saturation_Calculation Btn is not available");
		sa.assertEquals(Setup_CalculationsPage.Is_QualificationParameters_Visible(), true,
				"FAIL:Qualification Parameters  is not available");

		sa.assertEquals(Setup_CalculationsPage.Is_GroupSensors_Visible(), true,
				"FAIL:Qualification Parameters  is not available");

		sa.assertAll();
	}

	// CAL002-Verify the details displayed on the right pane on-click of the
	// _Lethality Calculations_ option
	@Test(groups = {
			"Regression" }, description = "CAL002-Verify the details displayed on the right pane on-click of the _Lethality Calculations_ option")
	public void CAL002() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL002-Verify the details displayed on the right pane on-click of the _Lethality Calculations_ option");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(Setup_CalculationsPage.BTemp_textfield_state(), true,
				"FAIL: Base Temperature TextBox is not available");
		sa.assertEquals(Setup_CalculationsPage.Dvalue_textfield_state(), true, "FAIL: DValue TexBox  is not available");
		sa.assertEquals(Setup_CalculationsPage.Zvalue_textfield_state(), true, "FAIL: ZValue TextBox is not available");
		sa.assertEquals(Setup_CalculationsPage.Cleth_DrpDwn_state(), true,
				"FAIL: Calculate Lethality ComboBox is not available");
		sa.assertAll();
	}

	// CAL003-Verify that 121.1 is the default value displayed in Base Temperature field
	@Test(groups = {
			"Regression" }, description = "CAL003-Verify that 121.1 is the default value displayed in Base Temperature field")
	public void CAL003() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("CAL003-Verify that 121.1 is the default value displayed in Base Temperature field");
		SoftAssert sa = new SoftAssert();
		System.out.println(Setup_CalculationsPage.BTemp_text());
		sa.assertEquals(Setup_CalculationsPage.BTemp_text(), "121.1",
				"Fail:the default value is not displayed in Base Temperature field ");
		sa.assertAll();
	}

	// CAL004-Verify that degrees centi grade is displayed beside Base temperature
	// field
	@Test(groups = {
			"Regression" }, description = "CAL004-Verify that degrees centigrade is displayed beside Base temperature field")
	public void CAL004() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("CAL004-Verify that degrees centigrade is displayed beside Base temperature field");
		SoftAssert sa = new SoftAssert();
		System.out.println(Setup_CalculationsPage.get_BaseTempUnit_text());
		sa.assertEquals(Setup_CalculationsPage.get_BaseTempUnit_text(), "°C",
				"Fail:degrees centigrade is not displayed beside Base temperature field");
		sa.assertAll();
	}

	// CAL005-Verify that max 5 characters are allowed in Base Temperature field
	@Test(groups = {
			"Regression" }, description = "CAL005-Verify that max 5 characters are allowed in Base Temperature field")
	public void CAL005() throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL005-Verify that max 5 characters are allowed in Base Temperature field");
		SoftAssert sa = new SoftAssert();
		// Enter 6 chars
		Setup_CalculationsPage.enter_bTemp("123456");
		String BT = Setup_CalculationsPage.BTemp_text();
		sa.assertEquals(BT.length(), 5, "Fail: Base Temperature field allowing more than 5 chars");
		sa.assertAll();
	}

	// CAL006-Verify that min 60 and max 400 is allowed in base temperature field
	@Test(groups = {
			"Regression" }, description = "CAL006A-Verify that min 60 and max 400 is allowed in base temperature field")
	public void CAL006A() throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL006A-Verify that min 60 and max 400 is allowed in base temperature field");
		SoftAssert sa = new SoftAssert();
		// Enter value less than 60
		Setup_CalculationsPage.enter_bTemp("59");
		Setup_CalculationsPage.click_Dvalue_textfield();
		sa.assertEquals(Setup_CalculationsPage.BTemp_text(), "60.0",
				"Fail: Base Temp field accepting value less than 60 ");
		sa.assertAll();
	}

	// CAL006A-Verify that min 60 and max 400 is allowed in base temperature field
	@Test(groups = {
			"Regression" }, description = "CAL006B-Verify that min 60 and max 400 is allowed in base temperature field")
	public void CAL006B() throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL006B-Verify that min 60 and max 400 is allowed in base temperature field");
		SoftAssert sa = new SoftAssert();
		// Enter value more than 400
		Setup_CalculationsPage.enter_bTemp("450");
		Setup_CalculationsPage.click_Dvalue_textfield();
		sa.assertEquals(Setup_CalculationsPage.BTemp_text(), "400.0",
				"Fail: Base Temp field accepting value more than 400 ");
		sa.assertAll();
	}

	// CAL007-Verify the valid values for Base temperature field
	@Test(groups = {
			"Regression" }, dataProvider = "CAL007", dataProviderClass = setupCreationUtility.class, description = "CAL007-Verify the valid values for Base temperature field"
					+ "the count is rounded off to 300")

	public void CAL007(String BaseTemp) throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL007-Verify the valid values for Base temperature field");

		SoftAssert sa = new SoftAssert();

		Setup_CalculationsPage.enter_bTemp(BaseTemp);
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();

		sa.assertEquals(Setup_QualParamPage.QualParamsPage_state(), true, "Fail: Landed to wrong page");
		sa.assertAll();

	}

	// CAL008-Verify the invalid values for Base temperature field
	@Test(groups = {
			"Regression" }, dataProvider = "CAL008", dataProviderClass = setupCreationUtility.class, description = "CAL008-Verify the invalid values for Base temperature field")

	public void CAL008(String BaseTemp) throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL008-Verify the invalid values for Base temperature field");

		SoftAssert sa = new SoftAssert();

		Setup_CalculationsPage.enter_bTemp(BaseTemp);
		Setup_CalculationsPage.NxtBtn_alert();

		String Actmsg = Setup_CalculationsPage.AlertMsg();
		String Expmsg = "Base Temperature is mandatory, please enter Base Temperature";
		sa.assertEquals(Actmsg, Expmsg, "Fail: Alert message is not displaying");
		sa.assertAll();

	}

	// CAL008A-Verify the invalid values for Base temperature field - Negative
	// values
	@Test(groups = {
			"Regression" }, description = "CAL008A-Verify the invalid values for Base temperature field - Negative values")
	public void CAL008A() throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL008A-Verify the invalid values for Base temperature field - Negative values");
		SoftAssert sa = new SoftAssert();
		// Enter Negative value
		Setup_CalculationsPage.enter_bTemp("-78");
		Setup_CalculationsPage.click_Dvalue_textfield();
		sa.assertEquals(Setup_CalculationsPage.BTemp_text(), "60.0",
				"Fail: Base Temp field accepting negative value and not getting back to 60 autometically ");
		sa.assertAll();
	}

	// CAL008B-Verify the invalid values for Base temperature field - Empty Field
	@Test(groups = {
			"Regression" }, description = "CAL008B-Verify the invalid values for Base temperature field - Empty Field")
	public void CAL008B() throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL008B-Verify the invalid values for Base temperature field - Empty Field");
		SoftAssert sa = new SoftAssert();

		// Do not enter anything
		Setup_CalculationsPage.enter_bTemp("");
		Setup_CalculationsPage.NxtBtn_alert();
		String Actmsg = Setup_CalculationsPage.AlertMsg();
		String Expmsg = "Base Temperature is mandatory, please enter Base Temperature";
		sa.assertEquals(Actmsg, Expmsg, "Fail: Alert message is not displaying");
		sa.assertAll();
	}

	// CAL009-Verify that 1.00 is the default value displayed in D Value field
	@Test(groups = {
			"Regression" }, description = "CAL009-Verify that 1.00 is the default value displayed in D Value field")
	public void CAL009() throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL009-Verify that 1.00 is the default value displayed in D Value field");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(Setup_CalculationsPage.get_DValueField_text(), "1.00",
				" Fail:the default value is not displayed in BD Value field ");
		sa.assertAll();
	}

	// CAL010-Verify that max 5 characters are allowed in D Value field
	// Enable the Editing Dvalue option in Policy page
	@Test(groups = { "Regression" }, description = "CAL010-Verify that max 5 characters are allowed in D Value field")
	public void CAL010() throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL010-Verify that max 5 characters are allowed in D Value field");
		SoftAssert sa = new SoftAssert();

		// Enter more than 5 chars
		Setup_CalculationsPage.Enter_Dvalue_textfield("123456");

		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_CalculationsPage = Setup_QualParamPage.click_Backbtn();
		Thread.sleep(1000);
		String Dtxt = Setup_CalculationsPage.get_DValueField_text();

		System.out.println(Dtxt.length());
		sa.assertEquals(Dtxt.length(), 5, " Fail:the default value is not displayed in BD Value field ");
		sa.assertAll();
	}

	// CAL011A-Verify that min 0.10 and max 99.99 are allowed in D Value field
	@Test(groups = { "Regression" }, description = "CAL011A-Verify that min 0.10 allowed in D Value field")
	public void CAL011A() throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL011A-Verify that min 0.10  allowed in D Value field");

		SoftAssert sa = new SoftAssert();
		Setup_CalculationsPage.Enter_Dvalue_textfield("-10");
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_CalculationsPage = Setup_QualParamPage.click_Backbtn();
		Thread.sleep(1000);
		sa.assertEquals(Setup_CalculationsPage.get_DValueField_text(), "0.10",
				" Fail: Minimum value 0.10 is not allowed in D Value field");
		sa.assertAll();

	}

	// CAL011B-Verify that min 0.10 and max 99.99 are allowed in D Value field
	@Test(groups = { "Regression" }, description = "CAL011B-Verify that max 99.99  allowed in D Value field")
	public void CAL011B() throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL011B-Verify that max 99.99  allowed in D Value field");

		SoftAssert sa = new SoftAssert();
		Setup_CalculationsPage.Enter_Dvalue_textfield("999999");
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_CalculationsPage = Setup_QualParamPage.click_Backbtn();
		String Dval = Setup_CalculationsPage.get_DValueField_text();
		// System.out.println(Dval);
		sa.assertEquals(Dval, "99.99", "Fail:Minimum value 99.99 is not allowed in D Value field ");
		sa.assertAll();

	}

	// CAL012-Verify the valid values for D Value field
	@Test(groups = {
			"Regression" }, dataProvider = "CAL012", dataProviderClass = setupCreationUtility.class, description = "CAL012-Verify the valid values for D Value field")

	public void CAL012(String Dvalue) throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL012-Verify the valid values for D Value field");

		SoftAssert sa = new SoftAssert();
		Setup_CalculationsPage.Enter_Dvalue_textfield(Dvalue);
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();

		sa.assertEquals(Setup_QualParamPage.QualParamsPage_state(), true,
				"Fail:Setup Qual Paramter Page is not displaying ");
		sa.assertAll();

	}

	// CAL013-Verify the invalid values for D Value
	@Test(groups = {
			"Regression" }, dataProvider = "CAL013", dataProviderClass = setupCreationUtility.class, 
					description = "CAL013-Verify the invalid values for D Value")

	public void CAL013(String Dvalue, String ExpDValue) throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL013-Verify the invalid values for D Value");

		SoftAssert sa = new SoftAssert();
		//String Dval = Setup_CalculationsPage.DValueField_text();
		Setup_CalculationsPage.Enter_Dvalue_textfield(Dvalue);
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_CalculationsPage = Setup_QualParamPage.click_Backbtn();
		Thread.sleep(1000);
		String Dval1 = Setup_CalculationsPage.get_DValueField_text();

		// Here we have entered specialchars and characters as invalid values so here
		// application will restrict and
		// will navigate to next page

		sa.assertEquals(Dval1, ExpDValue, "Fail: Applicationa accepted inviad D values");
		sa.assertAll();
	}

	// CAL014-Verify that 10.0 is the default value displayed in Z Value field
	@Test(groups = {
			"Regression" }, description = "CAL014-Verify that 10.0 is the default value displayed in Z Value field")
	public void CAL014() throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL014-Verify that 10.0 is the default value displayed in Z Value field");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(Setup_CalculationsPage.ZValueField_text(), "10.0",
				" Fail:the default value is not displayed in Z Value field ");
		sa.assertAll();
	}

	// CAL015-Verify that max 5 characters are allowed in Z Value field
	@Test(groups = { "Regression" }, description = "CAL015-Verify that max 5 characters are allowed in Z Value field")
	public void CAL015() throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL015-Verify that max 5 characters are allowed in Z Value field");
		SoftAssert sa = new SoftAssert();

		// Enter more than 4 chars
		Setup_CalculationsPage.enter_Zval("123456");
		String Ztxt = Setup_CalculationsPage.ZValueField_text();
		sa.assertEquals(Ztxt.length(), 5, " Fail:the default value is not displayed in  ZValue field ");
		sa.assertAll();
	}

	// CAL016A-Verify that min 1.0 and max 99.0 are allowed in Z Value field
	@Test(groups = { "Regression" }, description = "CAL016A-Verify that min 1.0  allowed in Z Value field")
	public void CAL016A() throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL016A-Verify that min 1.0  allowed in Z Value field");

		SoftAssert sa = new SoftAssert();
		Setup_CalculationsPage.enter_Zval("0.11");
		Setup_CalculationsPage.Click_Cleth_DrpDwn();

		sa.assertEquals(Setup_CalculationsPage.ZValueField_text(), "1.0",
				" Fail: Minimum value 1.0 is not allowed in D Value field");
		sa.assertAll();

	}

	// CAL016B-Verify that max 99.0 are allowed in Z Value field
	@Test(groups = { "Regression" }, description = "CAL016B-Verify that max 99.0  allowed in Z Value field")
	public void CAL016B() throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL016B-Verify that max 99.0  allowed in Z Value field");

		SoftAssert sa = new SoftAssert();
		Setup_CalculationsPage.enter_Zval("9999");
		Setup_CalculationsPage.Click_Cleth_DrpDwn();

		sa.assertEquals(Setup_CalculationsPage.ZValueField_text(), "99.0",
				"Fail:Maximum value 99.0 is not allowed in Z Value field ");
		sa.assertAll();

	}

	// CAL017-Verify the valid values of Z Value field
	@Test(groups = {
			"Regression" }, dataProvider = "CAL017", dataProviderClass = setupCreationUtility.class, description = "CAL017-Verify the valid values of Z Value field")

	public void CAL017(String Zvalue) throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL012-Verify the valid values for D Value field");

		SoftAssert sa = new SoftAssert();
		Setup_CalculationsPage.enter_Zval(Zvalue);
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();

		sa.assertEquals(Setup_QualParamPage.QualParamsPage_state(), true,
				"Fail:Setup Qual Paramter Page is not displaying ");
		sa.assertAll();

	}

	// CAL018-Verify the invalid values of Z Value field
	@Test(groups = {
			"Regression" }, dataProvider = "CAL018A", dataProviderClass = setupCreationUtility.class, description = "CAL018A-Verify the invalid values of  Z Value field")

	public void CAL018A(String Zvalue) throws InterruptedException, IOException {
		extentTest = extent.startTest("CAL018A-Verify the invalid values of  Z Value field");

		SoftAssert sa = new SoftAssert();
		Setup_CalculationsPage.enter_Zval(Zvalue);
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();

		// Here we have entered specialchars and characters as invalid values so here
		// application will restrict and
		// will navigate to next page

		sa.assertEquals(Setup_QualParamPage.QualParamsPage_state(), true,
				"Fail:Setup Qual Paramter Page is not displaying ");
		sa.assertAll();
	}

	// CAL018B- verify when user Do not enter any value in Z Value field and proceed
	// to next step with valid values in all other fields
	@Test(groups = {
			"Regression" }, description = "CAL018B- verify when user Do not enter any value in Z Value field and proceed to next step with valid values in all other fields               ")

	public void CAL018B() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL013B- verify when user Do not enter any value in Z Value field and proceed to next step with valid values in all other fields         ");

		SoftAssert sa = new SoftAssert();
		Setup_CalculationsPage.enter_Zval("");
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();

		Setup_CalculationsPage = Setup_QualParamPage.click_Backbtn();

		sa.assertEquals(Setup_CalculationsPage.ZValueField_text(), "10.0",
				" Fail: Minimum value 10.0 is not allowed in D Value field");
		sa.assertAll();

	}

	// CAL018C-Verify when user Enter negative value in Z Value field and click on
	// any other field ,The value in the field should get set to 1.0 automatically
	@Test(groups = {
			"Regression" }, description = "CAL018C-Verify when user Enter negative value in Z Value field and click on any other field ,The value in the field should get set to 1.0 automatically")

	public void CAL018C() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL018C-Verify when user Enter negative value in Z Value field and click on any other field ,The value in the field should get set to 1.0 automatically");

		SoftAssert sa = new SoftAssert();
		Setup_CalculationsPage.enter_Zval("-67");
		Setup_CalculationsPage.Click_Cleth_DrpDwn();

		sa.assertEquals(Setup_CalculationsPage.ZValueField_text(), "1.0",
				" Fail: The value in the field should get set to 1.0 automatically");
		sa.assertAll();
	}

	// CAL018D-Verify when user Enter value greater than 99.0 in Z Value field and
	// click on any other field ,
	// The value in the field should get set to 99.0 automatically
	@Test(groups = {
			"Regression" }, description = " CAL018D-Verify when user Enter value greater than 99.0 in Z Value field and click on any other field ,The value in the field should get set to 99.0 automatically")

	public void CAL018D() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				" CAL018D-Verify when user Enter value greater than 99.0 in Z Value field and click on any other field ,The value in the field should get set to 99.0 automatically");

		SoftAssert sa = new SoftAssert();
		Setup_CalculationsPage.enter_Zval("999");
		Setup_CalculationsPage.Click_Cleth_DrpDwn();

		sa.assertEquals(Setup_CalculationsPage.ZValueField_text(), "99.0",
				" Fail: The value in the field should get set to 99.0 automatically");
		sa.assertAll();
	}

	// CAL019-Verify that Undefined, During entire cycle and During Exposure cycle
	// are displayed in the drop down for Calculate lethality field
	@Test(groups = {
			"Regression" }, description = "CAL019-Verify that Undefined, During entire cycle and During Exposure cycle are displayed in the drop down for Calculate lethality field")

	public void CAL019() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL019-Verify that Undefined, During entire cycle and During Exposure cycle are displayed in the drop down for Calculate lethality field");

		SoftAssert sa = new SoftAssert();
		Setup_CalculationsPage.Click_Cleth_DrpDwn();

		sa.assertEquals(Setup_CalculationsPage.cal_Lethality_options(), true, "fail:options are not displayed");
		sa.assertAll();
	}

	//CAL029-Verify that on click of each option in the list of calculate lethality drop down, will reflect correctly in the field
	@Test(groups = {
			"Regression" }, description = "CAL019-Verify that Undefined, During entire cycle and During Exposure cycle are displayed in the drop down for Calculate lethality field")

	public void CAL029() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL019-Verify that Undefined, During entire cycle and During Exposure cycle are displayed in the drop down for Calculate lethality field");

		SoftAssert sa = new SoftAssert();
		Setup_CalculationsPage.select_AlethCondition("During Entire Study");
		sa.assertEquals(Setup_CalculationsPage.getText_From_ClethBox(), "During Entire Study",
				"fail: During Entire Study option is not displayed");

		Setup_CalculationsPage.select_AlethCondition("");
		sa.assertEquals(Setup_CalculationsPage.getText_From_ClethBox(), "Undefined",
				"fail: Undefined option is not displayed");

		Setup_CalculationsPage.select_AlethCondition("During Exposure Cycle");
		sa.assertEquals(Setup_CalculationsPage.getText_From_ClethBox(), "During Exposure Cycle",
				"fail: During Exposure Cycle option is not displayed");

		sa.assertAll();
	}

	// CAL030-Verify the details displayed on the right pane on-click of the
	// _Saturation PT Calculation_ option
	@Test(groups = {
			"Regression" }, description = "CAL030-Verify the details displayed on the "
					+ "right pane on-click of the _Saturation PT Calculation_ option")
	public void CAL030() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL030-Verify the details displayed on the right pane on-click of "
				+ "the _Saturation PT Calculation_ option");

		SoftAssert sa = new SoftAssert();

		Setup_GroupSensorsPage = Setup_CalculationsPage.click_groupsensorTab();
		Setup_SensorConfigPage = Setup_GroupSensorsPage.Click_SensorConfigTab();
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");
		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("PSR");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nxtbtn_ForChangingExistingSC();
		Thread.sleep(1000);
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();

		Setup_CalculationsPage.click_SatTP_btn();
		sa.assertEquals(Setup_CalculationsPage.Is_TempSensorDD_AgainstSATp_displayed(), true,
				"fail:Saturation pressure of steam is not displayed");
		sa.assertEquals(Setup_CalculationsPage.Is_PsrSensorDD_AgainstSATp_displayed(), true,
				"fail:Saturation Temperature of steam is not displayed");
		sa.assertAll();
	}

	//CAL031-Verify that the drop down of Saturation pressure of steam will display the 
	//list of all Temperature sensors
	@Test(groups = {
			"Regression" }, description = "CAL031-Verify that the drop down of Saturation "
					+ "pressure of steam will display the list of all Temperature sensors")

	public void CAL031() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL031-Verify that the drop down of Saturation pressure of steam will "
				+ "display the list of all Temperature sensors");

		SoftAssert sa = new SoftAssert();
		Setup_CalculationsPage.click_SatTP_btn();
		Setup_CalculationsPage.clickon_PressureSensorsComboBox();
		Thread.sleep(3000);
		sa.assertEquals(Setup_CalculationsPage.getSensorTxt_SaturationPressureOfSteam(0), "TMP1",
				"fail:Temp sensors are not displaying");
		sa.assertEquals(Setup_CalculationsPage.getSensorTxt_SaturationPressureOfSteam(1), "TMP2",
				"fail:Temp sensors are not displaying");
		sa.assertEquals(Setup_CalculationsPage.getSensorTxt_SaturationPressureOfSteam(2), "TMP3",
				"fail:Temp sensors are not displaying");
		sa.assertEquals(Setup_CalculationsPage.getSensorTxt_SaturationPressureOfSteam(3), "TMP4",
				"fail:Temp sensors are not displaying");
		sa.assertEquals(Setup_CalculationsPage.getSensorTxt_SaturationPressureOfSteam(4), "TMP5",
				"fail:Temp sensors are not displaying");

		sa.assertAll();
	}

	//CAL032-Verify that the drop down of Saturation temperature of steam will display 
	//the list of all Pressure sensors
	@Test(groups = {
			"Regression" }, description = "CAL032-Verify that the drop down of "
					+ "Saturation temperature of steam will display the list of all Pressure sensors")
	public void CAL032() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL032-Verify that the drop down of Saturation temperature of steam will "
				+ "display the list of all Pressure sensors");

		SoftAssert sa = new SoftAssert();
		
		Setup_GroupSensorsPage = Setup_CalculationsPage.click_groupsensorTab();
		Setup_SensorConfigPage = Setup_GroupSensorsPage.Click_SensorConfigTab();
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");
		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("PSR");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nxtbtn_ForChangingExistingSC();
		Thread.sleep(1000);
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();

		Setup_CalculationsPage.click_SatTP_btn();
		Setup_CalculationsPage.Clickon_TemperatureSenosrsComboBox();
		Thread.sleep(3000);
		sa.assertEquals(Setup_CalculationsPage.getSensorTxt_SaturationTemperatureOfSteam(0), "PSR1",
				"fail:Pressure sensors are not displaying");
		sa.assertEquals(Setup_CalculationsPage.getSensorTxt_SaturationTemperatureOfSteam(1), "PSR2",
				"fail:Pressure sensors are not displaying");
		sa.assertEquals(Setup_CalculationsPage.getSensorTxt_SaturationTemperatureOfSteam(2), "PSR3",
				"fail:Pressure sensors are not displaying");
		sa.assertEquals(Setup_CalculationsPage.getSensorTxt_SaturationTemperatureOfSteam(3), "PSR4",
				"fail:Pressure sensors are not displaying");
		sa.assertEquals(Setup_CalculationsPage.getSensorTxt_SaturationTemperatureOfSteam(4), "PSR5",
				"fail:Pressure sensors are not displaying");

		sa.assertAll();

	}

	// CAL033-Verify that the Saturation pressure of steam field will be disabled
	// when there are no temperature sensors defined in the setup
	@Test(groups = {
			"Regression" }, description = "CAL033-Verify that the Saturation pressure of "
					+ "steam field will be disabled when there are no temperature sensors defined in the setup")
	public void CAL033() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL033-Verify that the Saturation pressure of steam field will be disabled "
				+ "when there are no temperature sensors defined in the setup");

		SoftAssert sa = new SoftAssert();
		Setup_GroupSensorsPage = Setup_CalculationsPage.click_groupsensorTab();
		Setup_SensorConfigPage = Setup_GroupSensorsPage.Click_SensorConfigTab();
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");
		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("PSR");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nxtbtn_ForChangingExistingSC();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_SensorConfigPage=Setup_GroupSensorsPage.Click_SensorConfigTab();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("0");
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nxtbtn_ForChangingExistingSC();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
		Setup_CalculationsPage.click_SatTP_btn();
		sa.assertEquals(Setup_CalculationsPage.PressureSensorsComboBox_IsEnable(), false,
				"fail:PressureSensorsComboBox will not be Enable");

		sa.assertAll();

	}

	// CAL034-Verify that the Saturation temperature of steam field will be disabled
	// when there are no pressure sensors defined in the setup
	@Test(groups = {
			"Regression" }, description = "CAL034-Verify that the Saturation temperature of steam field "
					+ "will be disabled when there are no pressure sensors defined in the setup")

	public void CAL034() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL034-Verify that the Saturation temperature of steam field will be disabled when "
				+ "there are no pressure sensors defined in the setup");

		SoftAssert sa = new SoftAssert();
		Setup_CalculationsPage.click_SatTP_btn();
		sa.assertEquals(Setup_CalculationsPage.Temp_SensorsComboBox_IsEnable(), false,
				"fail:PressureSensorsComboBox will not be Enable");

		sa.assertAll();

	}
	
	// CAL035-Verify that the value selected from the drop down of Saturation
	// pressure of steam field is reflected correctly
	@Test(groups = {
			"Regression" }, description = "CAL035-Verify that the value selected from the "
					+ "drop down of Saturation pressure of steam field is relfected correctly")

	public void CAL035() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL035-Verify that the value selected from the drop down of Saturation pressure "
				+ "of steam field is relfected correctly");

		SoftAssert sa = new SoftAssert();
		Setup_CalculationsPage.click_SatTP_btn();
		Setup_CalculationsPage.selectSensor_SaturationPressureOfSteam_DD(1);

		sa.assertEquals(Setup_CalculationsPage.FetchTxt_PressureSensorsComboBox(), "TMP1",
				"fail:Pressure sensors are not displaying");

		sa.assertAll();
	}

	// CAL036-Verify that the value selected from the drop down of Saturation
	// temperature of steam field is reflected correctly
	@Test(groups = {
			"Regression" }, description = "CAL036-Verify that the value selected from the "
					+ "drop down of Saturation temperature of steam field is reflected correctly")
	public void CAL036() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL036-Verify that the value selected from the drop down of "
				+ "Saturation temperature of steam field is reflected correctly");

		SoftAssert sa = new SoftAssert();

		Setup_GroupSensorsPage = Setup_CalculationsPage.click_groupsensorTab();
		Setup_SensorConfigPage = Setup_GroupSensorsPage.Click_SensorConfigTab();
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");
		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("PSR");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nxtbtn_ForChangingExistingSC();
		Thread.sleep(1000);
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
		Setup_CalculationsPage.click_SatTP_btn();
		
		Setup_CalculationsPage.selectSensor_SaturationTemperatureOfSteam_DD(1);

		sa.assertEquals(Setup_CalculationsPage.FetchTxt_TempSensorsComboBox(), "PSR1",
				"fail:Pressure sensors are not displaying");

		sa.assertAll();
	}

	// CAL037-Verify that on-click of the Group sensors navigator on the top left
	// will navigate to Group sensor screen
	@Test(groups = {
			"Regression" }, description = "CAL037-Verify that on-click of the "
					+ "Group sensors navigator on the top left will navigate to Group sensor screen")

	public void CAL037() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"/CAL037-Verify that on-click of the Group sensors navigator on the "
				+ "top left will navigate to Group sensor screen");

		SoftAssert sa = new SoftAssert();

		Setup_GroupSensorsPage = Setup_CalculationsPage.click_groupsensorTab();
		sa.assertEquals(Setup_GroupSensorsPage.GrpsensorPage_state(), true,
				"fail: group sensor page is not displaying");

		sa.assertAll();
	}

	//CAL038-Verify that on-click of the Qualification Parameters navigator on the 
	//top right will navigate to Qualification Parameters screen
	@Test(groups = {
			"Regression" }, description = "CAL038-Verify that on-click of the Qualification "
					+ "Parameters navigator on the top right will navigate to Qualification Parameters screen")

	public void CAL038() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL038-Verify that on-click of the Qualification Parameters navigator on the "
				+ "top right will navigate to Qualification Parameters screen");

		SoftAssert sa = new SoftAssert();
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();

		sa.assertEquals(Setup_QualParamPage.QualParamsPage_state(), true,
				"fail: Qual parameter page is not displaying");
		sa.assertAll();
	}

	// CAL039-Verify that on-click of the back icon on the screen will navigate to
	// Asset details screen upon confirmation
	@Test(groups = {
			"Regression" }, description = "CAL039-Verify that on-click of the back icon "
					+ "on the screen will navigate to Asset details screen upon confirmation")

	public void CAL039() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL039-Verify that on-click of the back icon on the screen will navigate to "
				+ "Asset details screen upon confirmation");

		SoftAssert sa = new SoftAssert();
		assetDetailsPage = Setup_CalculationsPage.Click_back_Btn();

		sa.assertEquals(assetDetailsPage.assetDetailPageTitle_Visible(), true,
				"fail: asset Detail Page Title  page is not displaying");

		sa.assertAll();
	}

	// CAL040-Verify the bottom menu options displayed in Calculation screen
	@Test(description = "CAL040-Verify the bottom menu options displayed in Qualification Parameters screen")
	public void CAL040() throws InterruptedException {
		extentTest = extent
				.startTest("CAL040-Verify the bottom menu options displayed in Qualification Parameters screen");
		SoftAssert sa = new SoftAssert();

		tu.Right_Click__Buttom_Menuoptions();

		sa.assertEquals(tu.check_Home_Buttom_AppBar_Presence(), true,
				"FAIL: Home icon/button missing in bottom app bar");
		sa.assertEquals(tu.check_Help_Buttom_AppBar_Presence(), true,
				"FAIL: Help icon/button missing in bottom app bar");
		sa.assertEquals(tu.check_WndsHelp_Buttom_AppBar_Presence(), true,
				"FAIL: Windows Help icon/button missing in bottom app bar");
		sa.assertEquals(tu.check_About_wndw_Presence(), true, "FAIL: About icon/button missing in bottom app bar");
		sa.assertAll();
	}

	// CAL041-Verify that on-click of home btn in bottom menu options is navigated
	// to main hub page
	@Test(description = "CAL041-Verify that on-click of home btn in bottom menu options is navigated to main hub page")
	public void CAL041() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL041-Verify that on-click of home btn in bottom menu options is navigated to main hub page");
		SoftAssert sa = new SoftAssert();

		MainHubPage = tu.Click_Home_Icon_AppBar();

		sa.assertEquals(MainHubPage.mainPageTitle(), true,
				"FAIL: Clicking Home icon/button in bottom app bar do not redirect to Mains Hub page");
		sa.assertAll();
	}

	// CAL042-Verify that on-click of help btn in bottom menu options displays
	// information about the Qualification parameters screen
	@Test(description = "CAL042-Verify that on-click of help btn in bottom menu options displays information about the Qualification parameters screen")
	public void CAL042() throws InterruptedException {
		extentTest = extent.startTest(
				"CAL042-Verify that on-click of help btn in bottom menu options displays information about the Qualification parameters screen");
		SoftAssert sa = new SoftAssert();
		tu.Click_Help_Icon_AppBar();
		sa.assertEquals(tu.get__HelpMenu_HdrText(), "Calculations", "FAIL: Clicking Help icon/button in bottom app bar"
				+ "do not display the Sensors Configuration Help context window");
		sa.assertAll();
	}
	
	
	// CAL043-Verify that on-click of windows help btn in bottom menu options
	// generates a PDF with information
	// This TC will handle manually

	// CAL044-Verify that on-click of About btn in bottom menu options displays the
	// software version and the console IP address
	@Test(dataProvider = "SET033", dataProviderClass = setupCreationUtility.class, 
			description = "Verify that on-click of About btn in bottom menu options displays "
			+ "the software version and the console IP address")
	public void CAL044(String SWVer) throws InterruptedException, UnknownHostException {
		extentTest = extent.startTest("CAL044-Verify that on-click of About btn in bottom menu options displays "
				+ "the software version and the console IP address");
		SoftAssert sa = new SoftAssert();

		tu.Click_About_Icon_AppBar();
		// System.out.println(SWVer);
		// System.out.println(tu.SWversion_InAboutWndw());
		sa.assertEquals(tu.SWversion_InAboutWndw(), SWVer,
				"FAIL: Incorrect SW version & Console IP adress displayed in About window");
		// System.out.println("Console IP Address in About window:
		// "+tu.consoleIP_InAboutWndw());
		// System.out.println(tu.system_IPadress());
		sa.assertEquals(tu.consoleIP_InAboutWndw(), tu.system_IPadress(),
				"FAIL: Incorrect SW version & Console IP adress displayed in About window");
		sa.assertAll();

	}

	// CAL045-Verify that when edited the setup and removed Temeprature group, an
	// alert message should be displayed that Lethality condition should be
	// Undefined when there are no Temperature sensors
	@Test(groups = {
			"Regression" }, description = "CAL045-Verify that when edited the setup and "
					+ "removed Temeprature group, an alert message should be displayed that "
					+ "Lethality condition should be Undefined when there are no Temperature sensors")

	public void CAL045() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"CAL045-Verify that when edited the setup and removed Temeprature group, an alert message "
				+ "should be displayed that Lethality condition should be Undefined when there are no Temperature sensors");

		SoftAssert sa = new SoftAssert();
		Setup_GroupSensorsPage = Setup_CalculationsPage.click_groupsensorTab();
		Setup_SensorConfigPage = Setup_GroupSensorsPage.Click_SensorConfigTab();
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");
		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("PSR");
		Setup_SensorConfigPage.Click_assignBtn();	
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nxtbtn_ForChangingExistingSC();
		Thread.sleep(1000);
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_GroupSensorsPage.click_DeleteGroup_Btn();
		Setup_GroupSensorsPage.DeleteGroup_Yes();
		Setup_CalculationsPage = Setup_GroupSensorsPage.click_YesBtn_AlertMsg_for_DeleteGroup_SensorUnassigned();
		Setup_CalculationsPage.select_AlethCondition("During Entire Study");
		Setup_CalculationsPage.NxtBtn_alert();

		String Actmsg = Setup_CalculationsPage.AlertMsg();
		String Expmsg = "Lethality will be Undefined when temperature sensors are not selected.";
		sa.assertEquals(Actmsg, Expmsg, "Fail: Alert message is not displaying");
		sa.assertAll();
	}

}
