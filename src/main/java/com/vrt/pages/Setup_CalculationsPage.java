package com.vrt.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class Setup_CalculationsPage extends BaseClass {

	// Calculation page element variable declaration definition
	WebElement CalculationPageTitle = null;

	WebElement SetupHeaderTextBlock = null;
	WebElement NxtBtn = null;
	WebElement leth_Btn = null;
	WebElement Statistical_Calculation = null;
	WebElement SatTP_btn = null;
	WebElement PreviousButton = null;
	WebElement BTemp_textfield = null;
	WebElement Dvalue_textfield = null;
	WebElement Zvalue_textfield = null;
	WebElement Cleth_DrpDwn = null;
	WebElement BT_UnitsTextBlock = null;
	WebElement Back_btn = null;

	WebElement SaveSetupButton = null;

	private void initializeEelements() {
		CalculationPageTitle = driver.findElementByName("Calculations");
		SetupHeaderTextBlock = driver.findElementByAccessibilityId("SetupHeaderTextBlock");
		PreviousButton = driver.findElementByAccessibilityId("PreviousButton");
		NxtBtn = driver.findElementByAccessibilityId("NextButton");
		Back_btn = driver.findElementByAccessibilityId("GoButton");
		leth_Btn = driver.findElementByName("Lethality Calculation");
		Statistical_Calculation = driver.findElementByName("Statistical Calculation");
		SatTP_btn = driver.findElementByName("Saturation P/T Calculation");
		BTemp_textfield = driver.findElementByAccessibilityId("BaseTemperatureTextBox");
		Dvalue_textfield = driver.findElementByAccessibilityId("DValueTexBox");
		Zvalue_textfield = driver.findElementByAccessibilityId("ZValueTextBox");
		Cleth_DrpDwn = driver.findElementByAccessibilityId("CalculateLethalityComboBox");
		BT_UnitsTextBlock = driver.findElementByAccessibilityId("BaseTempUnitsTextBlock");

	}

	Setup_CalculationsPage() throws IOException {
		super();
		initializeEelements();
	}

	// Release memory
	public void resetWebElements() {
		CalculationPageTitle = null;
		PreviousButton = null;
		NxtBtn = null;
		SetupHeaderTextBlock = null;
		leth_Btn = null;
		Statistical_Calculation = null;
		SatTP_btn = null;
		BTemp_textfield = null;
		Dvalue_textfield = null;
		Zvalue_textfield = null;
		Cleth_DrpDwn = null;
		BT_UnitsTextBlock = null;
		Back_btn = null;

	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Setup_Calculations Page methods
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Check the presence of Calculation page
	public boolean CalculationPage_state() {
		return IsElementVisibleStatus(CalculationPageTitle);
	}

	// Check the presence of leth_Btn state
	public boolean leth_Btnstate() {
		return IsElementVisibleStatus(leth_Btn);
	}

	// click on click on leth_Btn
	public void click_leth_Btn() {
		clickOn(leth_Btn);
	}

	// Check the presence of Statistical_CalculationBtn state
	public boolean Statistical_CalculationBtn_state() {
		return IsElementVisibleStatus(Statistical_Calculation);
	}

	// Check the presence of Saturation_CalculationBtn state
	public boolean Saturation_CalculationBtn_state() {
		return IsElementVisibleStatus(SatTP_btn);
	}

	// Check the presence of BaseTemperature TextBox
	public boolean BTemp_textfield_state() {
		return IsElementVisibleStatus(BTemp_textfield);
	}

	// Check the presence of Dvalue TextBox
	public boolean Dvalue_textfield_state() {
		return IsElementVisibleStatus(Dvalue_textfield);
	}

	// Check the presence of Zvalue TextBox
	public boolean Zvalue_textfield_state() {
		return IsElementVisibleStatus(Zvalue_textfield);
	}

	// Check the presence of Cleth_DrpDwn
	public boolean Cleth_DrpDwn_state() {
		return IsElementVisibleStatus(Cleth_DrpDwn);
	}

	// Get the Cleth_DrpDwn title text
	public String getText_From_ClethBox() {
		return FetchText(Cleth_DrpDwn);
	}

	// Get the Calculation page title text
	public String get_CalculationPage_titletext() {
		return FetchText(CalculationPageTitle);
	}

	// Get the SetupHeader title text
	public String get_Setup_titletext() {
		return FetchText(SetupHeaderTextBlock);
	}

	// Verify that degrees centigrade is displayed beside Base temperature field
	public String get_BaseTempUnit_text() {
		return FetchText(BT_UnitsTextBlock);
	}

	// click on Calculation leth_DrpDwn
	public void Click_Cleth_DrpDwn() {
		clickOn(Cleth_DrpDwn);

	}

	// Select Aleth condition
	public void select_AlethCondition(String ALethCond) throws InterruptedException {
		clickOn(Cleth_DrpDwn);
		Thread.sleep(1000);
		WebElement ALeth_Undefined = driver.findElementByName("Undefined");
		WebElement ALeth_entireStudy = driver.findElementByName("During Entire Study");
		WebElement ALeth_Exposure = driver.findElementByName("During Exposure Cycle");

		if (ALethCond.contains("Entire Study")) {
			clickOn(ALeth_entireStudy);
			Thread.sleep(500);
		} else if (ALethCond.contains("Exposure Cycle")) {
			clickOn(ALeth_Exposure);
			Thread.sleep(500);
		} else {
			clickOn(ALeth_Undefined);
			Thread.sleep(500);
		}

	}

	// Click Dvalue_textfield
	public void click_Dvalue_textfield() {
		clickOn(Dvalue_textfield);
	}

	// Enter D-Value
	public void Enter_Dvalue_textfield(String Dval) {
		clickOn(Dvalue_textfield);
		ClearText(Dvalue_textfield);
		enterText(Dvalue_textfield, Dval);

	}

	// Click the Sat TP button in left pane
	public void click_SatTP_btn() {
		clickOn(SatTP_btn);
		// clickOn(SatTP_btn);
	}

	// fetch text from PressureSensorsComboBox
	public String FetchTxt_PressureSensorsComboBox() {
		WebElement SatP_TempDrpDwnBox = driver.findElementByAccessibilityId("PressureSensorsComboBox");
		return FetchText(SatP_TempDrpDwnBox);
	}

//click on PressureSensorsComboBox

	public void clickon_PressureSensorsComboBox() {
		WebElement SatP_TempDrpDwnBox = driver.findElementByAccessibilityId("PressureSensorsComboBox");
		clickOn(SatP_TempDrpDwnBox);

	}

//select sensor
	public void selectSensor_SaturationPressureOfSteam_DD(int index) throws InterruptedException {
		clickon_PressureSensorsComboBox();
		List<WebElement> senrList = driver.findElements(By.className("ComboBoxItem"));
		clickOn(senrList.get(index));
	}

	// get the sensor text from Pressure Sensors ComboBox
	public String getSensorTxt_SaturationPressureOfSteam(int index) throws InterruptedException {

		List<WebElement> senrList = driver.findElementByAccessibilityId("PressureSensorsComboBox")
				.findElements(By.className("ComboBoxItem"));
		// System.out.println(senrList.size());
		return senrList.get(index).getText();
		// return FetchText(senrList.get(index));
	}

	// verify is PressureSensorsComboBox enable
	public boolean PressureSensorsComboBox_IsEnable() {
		WebElement SatP_TempDrpDwnBox = driver.findElementByAccessibilityId("PressureSensorsComboBox");
		return SatP_TempDrpDwnBox.isEnabled();
	}

	// Get the default value displayed in Base Temperature field
	public String BTemp_text() {
		return FetchText(BTemp_textfield);
	}

	// Get the default value displayed in D Value field

	public String get_DValueField_text() {
		return FetchText(Dvalue_textfield);
	}

	// click on Btemp
	public void click_BTemp() {
		clickOn(BTemp_textfield);
	}

	// Enter ALeth Base Temp data
	public void enter_bTemp(String bTemp) {
		clickOn(BTemp_textfield);
		ClearText(BTemp_textfield);
		enterText(BTemp_textfield, bTemp);
	}

	// Zvalue_textfield
	public String ZValueField_text() {
		return FetchText(Zvalue_textfield);
	}

	// Enter Z Value
	public void enter_Zval(String zval) {
		clickOn(Zvalue_textfield);
		ClearText(Zvalue_textfield);
		enterText(Zvalue_textfield, zval);
	}

	// selectSensorFrom SaturationTemperatureOfSteam_DD

	public void selectSensor_SaturationTemperatureOfSteam_DD(int index) throws InterruptedException {
		WebElement SatP_PsrDrpDwnBox = driver.findElementByAccessibilityId("TemperatureSenosrsComboBox");
		clickOn(SatP_PsrDrpDwnBox);
		List<WebElement> senrList = driver.findElements(By.className("ComboBoxItem"));
		clickOn(senrList.get(index));
	}

//get text Temperature Sensors ComboBox
	public void Clickon_TemperatureSenosrsComboBox() throws InterruptedException {
		WebElement SatP_PrDrpDwnBox = driver.findElementByAccessibilityId("TemperatureSenosrsComboBox");
		clickOn(SatP_PrDrpDwnBox);

	}

	// fetch text from TemperatureSenosrsComboBox
	public String FetchTxt_TempSensorsComboBox() {
		WebElement SatP_PsrDrpDwnBox = driver.findElementByAccessibilityId("TemperatureSenosrsComboBox");
		return FetchText(SatP_PsrDrpDwnBox);
	}

	// get Sensor Txt from SaturationTemperatureOfSteam

	public String getSensorTxt_SaturationTemperatureOfSteam(int index) throws InterruptedException {

		List<WebElement> senrList = driver.findElementByAccessibilityId("TemperatureSenosrsComboBox")
				.findElements(By.className("ComboBoxItem"));

		return senrList.get(index).getText();
		// return FetchText(senrList.get(index));
	}

	// verify is TemperatureSenosrsComboBox enable
	public boolean Temp_SensorsComboBox_IsEnable() {
		WebElement SatP_TempDrpDwnBox = driver.findElementByAccessibilityId("TemperatureSenosrsComboBox");
		return SatP_TempDrpDwnBox.isEnabled();
	}

	// Check the presence of NxtBtn
	public boolean Is_QualificationParameters_Visible() {
		return IsElementVisibleStatus(NxtBtn);
	}

	// Is_GroupSensors_Visible
	public boolean Is_GroupSensors_Visible() {
		return IsElementVisibleStatus(PreviousButton);
	}

	// click on group sensor btn
	public Setup_GroupSensorsPage click_groupsensorTab() throws IOException {
		clickOn(PreviousButton);
		return new Setup_GroupSensorsPage();
	}

	// Click the Next button to move to Setup Qualification page
	public Setup_QualParamPage Click_NxtBtn() throws IOException, InterruptedException {
		clickOn(NxtBtn);
		Thread.sleep(1000);
		return new Setup_QualParamPage();
	}

	// For alert msg
	public void NxtBtn_alert() throws IOException, InterruptedException {
		clickOn(NxtBtn);
	}

	// Fetch alert msg
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

	public boolean cal_Lethality_options() {
		WebElement ALeth_Undefined = driver.findElementByName("Undefined");
		ALeth_Undefined.isDisplayed();

		WebElement ALeth_entireStudy = driver.findElementByName("During Entire Study");
		ALeth_entireStudy.isDisplayed();
		WebElement ALeth_Exposure = driver.findElementByName("During Exposure Cycle");
		ALeth_Exposure.isDisplayed();
		return true;
	}

	// Is Saturation pressure of steam displayed
	public boolean Is_TempSensorDD_AgainstSATp_displayed() {
		WebElement satrnprsrofstm = driver.findElementByName("Saturation pressure of steam");
		return satrnprsrofstm.isDisplayed();

	}

	// Is Saturation temp of steam displayed
	public boolean Is_PsrSensorDD_AgainstSATp_displayed() {
		WebElement satrntmprofstm = driver.findElementByName("Saturation temperature of steam");
		return satrntmprofstm.isDisplayed();

	}

//click back
	public assetDetailsPage Click_back_Btn() throws IOException {
		clickOn(Back_btn);

		WebElement Yesbtn = driver.findElementByName("Yes");
		clickOn(Yesbtn);
		return new assetDetailsPage();
	}

}
