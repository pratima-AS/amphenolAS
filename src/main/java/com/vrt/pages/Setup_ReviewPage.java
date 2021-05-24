package com.vrt.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class Setup_ReviewPage extends BaseClass {

	// Review page element variable declaration definition
	WebElement ReviewPageTitle = null;
	WebElement SaveSetup_Btn = null;
	WebElement QStart_Text = null;
	WebElement Bottom_VScrollBar = null;
	WebElement Back_btn = null;
	WebElement CopyAsNewSetup_Button = null;
	WebElement SetupHeaderTextBlock = null;
	WebElement sub_header = null;
	WebElement Previous_Btn = null;
	WebElement CreateSetupReport_Button = null;
	WebElement AssetDetails_Section = null;
	WebElement SensorDetails_Section = null;
	WebElement Groups_Section = null;
	WebElement Qualification_StartStop_Condition_Section = null;
	WebElement EditCalculationButton = null;
	WebElement Calculations_Section = null;
	WebElement EditSensorDetailsButton = null;
	WebElement EditGroupsButton = null;
	WebElement ReportHeader_Section = null;
	WebElement EditReportButton = null;
	WebElement EditQualificationParametersButton = null;
	// WebElement image = null;
	WebElement Asset_ID = null;
	WebElement Model = null;
	WebElement Manufacturer = null;
	WebElement Type = null;
	WebElement Size = null;
	WebElement Temp = null;
	WebElement Humidity = null;
	WebElement Pressure = null;
	WebElement Temp_val = null;
	WebElement Asset_ID_Report = null;
	WebElement SOP_Protocol_Report = null;
	WebElement Load_Description_Report = null;
	WebElement Comments_report = null;
	WebElement AssetIDvalue_Report = null;
	WebElement SopProtocolValue_Report = null;
	WebElement LoadDescValue_Report = null;
	WebElement CommentValue_Report = null;
	WebElement TotalGroups = null;
	WebElement No_Groups = null;
	WebElement GroupName = null;
	WebElement Sensors_Grp = null;
	WebElement Type_Group = null;
	WebElement SaveSetupButton = null;

	private void initializeEelements() {
		ReviewPageTitle = driver.findElementByName("Review");
		sub_header = driver.findElementByName("Review and Save the Setup");
		SetupHeaderTextBlock = driver.findElementByAccessibilityId("SetupHeaderTextBlock");

		QStart_Text = driver.findElementByAccessibilityId("StartQualificationTextBlock");
		SaveSetup_Btn = driver.findElementByAccessibilityId("SaveSetupButton");
		CopyAsNewSetup_Button = driver.findElementByAccessibilityId("CopySetupButton");
		CreateSetupReport_Button = driver.findElementByAccessibilityId("CreateSetupReportButton");

		Bottom_VScrollBar = driver.findElementByAccessibilityId("VerticalSmallIncrease");
		Back_btn = driver.findElementByAccessibilityId("GoButton");
		Previous_Btn = driver.findElementByAccessibilityId("PreviousButton");
		AssetDetails_Section = driver.findElementByName("Asset Details");
		EditSensorDetailsButton = driver.findElementByAccessibilityId("EditSensorDetailsButton");

		SensorDetails_Section = driver.findElementByName("Sensor Details");

		Groups_Section = driver.findElementByName("Groups");

		EditGroupsButton = driver.findElementByAccessibilityId("EditGroupsButton");

		EditReportButton = driver.findElementByAccessibilityId("EditReportButton");

		ReportHeader_Section = driver.findElementByName("Report Header");

		Qualification_StartStop_Condition_Section = driver.findElementByName("Qualification Start Stop Condition");
		EditCalculationButton = driver.findElementByAccessibilityId("EditCalculationButton");
		EditQualificationParametersButton = driver.findElementByAccessibilityId("EditQualificationParametersButton");
		Calculations_Section = driver.findElementByName("Calculations");
		// image = driver.findElementByName("image");
		Asset_ID = driver.findElementByName("Asset ID");
		Model = driver.findElementByName("Model");
		Manufacturer = driver.findElementByName("Manufacturer");
		Type = driver.findElementByName("Type");
		Size = driver.findElementByName("Size");
		Temp = driver.findElementByName("Temp");
		Humidity = driver.findElementByName("Humidity");
		Pressure = driver.findElementByName("Pressure");
		Temp_val = driver.findElementByAccessibilityId("TempkValueTextBlock");

		Asset_ID_Report = driver.findElementByName("Asset ID #");

		SOP_Protocol_Report = driver.findElementByName("SOP Protocol");
		Load_Description_Report = driver.findElementByName("Load Description");
		Comments_report = driver.findElementByName("Comments");
		AssetIDvalue_Report = driver.findElementByAccessibilityId("VessalIDValueTextBlock");
		SopProtocolValue_Report = driver.findElementByAccessibilityId("SopProtocolNoValueTextBlock");
		LoadDescValue_Report = driver.findElementByAccessibilityId("LoadDescValueTextBlock");
		CommentValue_Report = driver.findElementByAccessibilityId("CommentValueTextBlock");

		TotalGroups = driver.findElementByName("Total Groups");
		No_Groups = driver.findElementByName("No.");
		GroupName = driver.findElementByName("Group Name");
		Sensors_Grp = driver.findElementByName("Sensors");
		Type_Group = driver.findElementByName("Type");
		SaveSetupButton = driver.findElementByAccessibilityId("SaveSetupButton");

	}

	Setup_ReviewPage() throws IOException {
		super();
		initializeEelements();
	}

	// Release memory

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Review Page methods
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// Check the presence of Review page
	public boolean ReviewPage_state() {
		return IsElementVisibleStatus(ReviewPageTitle);
	}

	// Check the presence of Qualification parameters navigator
	public boolean Qualificationparameters_state() {
		return IsElementVisibleStatus(Previous_Btn);
	}

	// Click on Previous_Btn (Qualificationparameters)
	public Setup_QualParamPage click_QualificationparametersBtn() throws IOException {
		clickOn(Previous_Btn);
		return new Setup_QualParamPage();
	}

	// Check the presence of CopyAsNewSetup_Button
	public boolean CopyAsNewSetup_Btn_state() {
		return CopyAsNewSetup_Button.isEnabled();
	}

	// Check the presence of CreateSetupReport_Button
	public boolean CreateSetupReport_Btn_state() {
		return CreateSetupReport_Button.isEnabled();
	}

	// Get the SetupHeader title text
	public String get_Setup_titletext() {
		return FetchText(SetupHeaderTextBlock);
	}

	// Get the AssetDetails_Section title text
	public String get_AssetDetails_Section_titletext() {
		return FetchText(AssetDetails_Section);
	}

	// Get the SensorDetails_Section title text
	public String get_SensorDetails_Section_titletext() {
		return FetchText(SensorDetails_Section);
	}

	// Get the Groups_Section title text
	public String get_Groups_Section_titletext() {
		return FetchText(Groups_Section);
	}

	// Get the Qualification_StartStop_Condition_Section title text
	public String get_Groups_Qualification__Section_titletext() {
		return FetchText(Qualification_StartStop_Condition_Section);
	}

	// Get the Calculations_Section title text
	public String get_Calculations__Section_titletext() {
		return FetchText(Calculations_Section);
	}

	// Get the ReportHeader_Section title text
	public String get_ReportHeader_Section_titletext() {
		return FetchText(ReportHeader_Section);
	}

	// Edit Calculation Button is displayed
	public boolean EditCalculationButton_state() {
		return IsElementVisibleStatus(EditCalculationButton);
	}

	// click on Edit Calculation Button

	public Setup_CalculationsPage click_EditCalculationBtn() throws IOException {
		clickOn(EditCalculationButton);
		return new Setup_CalculationsPage();

	}

	// Edit SensorDetails Button is displayed
	public boolean EditSensorDetailsButton_state() {
		return IsElementVisibleStatus(EditSensorDetailsButton);
	}

	// EditGroupsButton is displayed
	public boolean EditGroupsButton_state() {
		return IsElementVisibleStatus(EditGroupsButton);
	}
	// click on edit group btn

	public Setup_GroupSensorsPage EditGroupsButton_click() throws IOException {
		clickOn(EditGroupsButton);
		return new Setup_GroupSensorsPage();
	}

	// EditReportButton_state is displayed
	public boolean EditReportButton_state() {
		return IsElementVisibleStatus(EditReportButton);
	}

	// EditQualificationParametersButton is displayed
	public boolean EditQualificationParametersButton_state() {
		return IsElementVisibleStatus(EditQualificationParametersButton);
	}

	// click on EditQualificationParametersButton

	public Setup_QualParamPage click_Edit_QualParametersBtn() throws IOException {
		clickOn(EditQualificationParametersButton);
		return new Setup_QualParamPage();
	}

	// Get the Review page title text
	public String get_ReviewPage_titletext() {
		return FetchText(ReviewPageTitle);
	}

	// Get the Qual Start text text
	public String get_QStart_text() {
		return FetchText(QStart_Text);
	}

	// sub-header as "Review and save the setup" on the top left
	public String get_SubHeader_titletext() {
		return FetchText(sub_header);
	}

	// Click expected TOD action option like "Yes or No or Cancel" as input to the
	// method
	// to perform the corresponding TOD Save Alert message
	// Click save button
	public void click_Save_Btn(String QStart, String TODAlertAction, String UID, String PW)
			throws InterruptedException {

		if (!QStart.equals("Manual")) {
			clickOn(SaveSetup_Btn);
			UserLoginPopup(UID, PW);
			click_Yes_TODAlertMsg_Btn(TODAlertAction);
			Thread.sleep(1000);
		} else {
			clickOn(SaveSetup_Btn);
			UserLoginPopup(UID, PW);
			Thread.sleep(1000);
		}
	}

	// Click expected action option like "Yes or No or Cancel" as input to the
	// method
	// to perform the corresponding TOD Save Alert message
	public void click_Yes_TODAlertMsg_Btn(String Alert_Action) throws InterruptedException {
		WebElement TOD_Alert_Yes_Btn = driver.findElementByName("Yes");
		WebElement TOD_Alert_No_Btn = driver.findElementByName("No");
		WebElement TOD_Alert_Cancel_Btn = driver.findElementByName("Cancel");

		if (Alert_Action.contains("Yes")) {
			clickOn(TOD_Alert_Yes_Btn);
			Thread.sleep(1000);
		} else if (Alert_Action.contains("No")) {
			clickOn(TOD_Alert_No_Btn);
			Thread.sleep(1000);
		} else {
			clickOn(TOD_Alert_Cancel_Btn);
			Thread.sleep(1000);
		}
	}

	// Click the bottom vertical scroll bar
	public void click_bottom_Scrollbar(int numberofclicks) {
		for (int i = 1; i <= numberofclicks; i++) {
			clickOn(Bottom_VScrollBar);
		}

	}

	// Click the Back button of Review page
	public void click_back_Btn() throws InterruptedException {
		clickOn(Back_btn);
		Thread.sleep(2000);

	}

	public assetDetailsPage click_backBtn_WithAlert() throws InterruptedException, IOException {
		clickOn(Back_btn);
		WebElement Alert_Yes_Btn = driver.findElementByName("Yes");
		clickOn(Alert_Yes_Btn);
		return new assetDetailsPage();
	}

	// click on back button to navigate assetdetails page after saving the edited
	// details in review screen

	public assetDetailsPage click_backBtn() throws InterruptedException, IOException {
		clickOn(Back_btn);
		Thread.sleep(1000);
		return new assetDetailsPage();

	}

	// Click the Copy As New Setup button of Review page
	public void click_CopyAsNewSetup_Button() throws InterruptedException {
		clickOn(CopyAsNewSetup_Button);

	}

	// Enter setup name after clicking on copy as new setup

	public void Enter_NewSetupName(String SName) throws InterruptedException {

		WebElement SetupNameTextBox = driver.findElementByAccessibilityId("SetupNameTextBox");

		clickOn(SetupNameTextBox);
		enterText(SetupNameTextBox, SName);
		WebElement setupsave = driver.findElementByAccessibilityId("CopySetupSaveButton");
		clickOn(setupsave);
	}

	// Click the Create Setup Report_Button of Review page
	public void create_setupReport_Button() throws InterruptedException {
		clickOn(CreateSetupReport_Button);

	}

	// Verify the presence of "how do you want to open this file" window is
	// displaying
	public void click_PDFpopup_OkBtn() throws InterruptedException, AWTException {
		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		r = null;

	}

	// Verify Application switch from PDF window to Application
	public void perform_alt_tab_OP() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_TAB);
		// robot.delay(100);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot = null;
	}

	/*
	 * // verify image is displaying in Assets section public boolean image_state()
	 * { return image.isEnabled(); }
	 */

// verify asset ID is displaying in Assets section
	public boolean Asset_ID_state() {
		return Asset_ID.isEnabled();
	}

	// verify Model is displaying in Assets section
	public boolean Model_state() {
		return Model.isEnabled();
	}

	// verify Manufacturer is displaying in Assets section
	public boolean Manufacturer_state() {
		return Manufacturer.isEnabled();
	}

	// verify Type is displaying in Assets section
	public boolean Type_state() {
		return Type.isEnabled();
	}

	// verify Type is displaying in Assets section
	public boolean Size_state() {
		return Size.isEnabled();
	}

	// verify Temp is displaying in Assets section
	public boolean Temp_state() {
		return IsElementVisibleStatus(Temp);
	}

	// Fetch tmp val
	public String FetchTemp_val() {
		return FetchText(Temp_val);
	}

	// Fetch pressure val
	public String FetchPsr_val() {
		WebElement Psr_val = driver.findElementByAccessibilityId("PressureValueTextBlock");
		return FetchText(Psr_val);
	}

	// verify Humidity is displaying in Assets section
	public boolean Humidity_state() {
		return IsElementVisibleStatus(Humidity);
	}

	// verify Pressure is displaying in Assets section
	public boolean Pressure_state() {
		return IsElementVisibleStatus(Pressure);
	}

	// Asset_ID_Report is displayed

	public boolean Asset_ID_Report_state() {
		return IsElementVisibleStatus(Asset_ID_Report);
	}

	// Fetch Asset ID value from report section
	public String FetchAssetID_Value_Reportsection() {
		return FetchText(AssetIDvalue_Report);
	}

	// SOP_Protocol_Report is displayed

	public boolean SOP_Protocol_Report_state() {
		return IsElementVisibleStatus(SOP_Protocol_Report);
	}

	// Fetch SOP value from report section
	public String FetchSOP_Value_Reportsection() {
		return FetchText(SopProtocolValue_Report);
	}

	// Fetch Load Desc Value
	public String FetchLoad_Value_Reportsection() {
		return FetchText(LoadDescValue_Report);
	}

	// Load Description report
	public boolean Load_Description_Report_State() {
		return IsElementVisibleStatus(Load_Description_Report);
	}

	// Comments_report
	public boolean Comments_report_State() {
		return IsElementVisibleStatus(Comments_report);
	}

	// Fetch Comments Value
	public String FetchCmnts_Value_Reportsection() {
		return FetchText(CommentValue_Report);
	}

	// click EditSensorDetailsButton
	public Setup_SensorConfigPage click_EditSensorDetailsButton() throws IOException {
		clickOn(EditSensorDetailsButton);
		return new Setup_SensorConfigPage();
	}

	// click on EditReportButton

	public Setup_defineSetupPage click_EditReportButton() throws IOException {
		clickOn(EditReportButton);
		return new Setup_defineSetupPage();
	}

	// Is TotalGroups visible
	public boolean TotalGroups_State() {
		return IsElementVisibleStatus(TotalGroups);
	}

	// Is No_Groups visible
	public boolean NumberOf_Groups_State() {
		return IsElementVisibleStatus(No_Groups);
	}

	// is GroupName visible
	public boolean GroupName_State() {
		return IsElementVisibleStatus(GroupName);
	}

	// is SensorsName visible
	public boolean Sensors_Grp_State() {
		return IsElementVisibleStatus(Sensors_Grp);
	}

	// is SensorsName visible
	public boolean Type_Group_State() {
		return IsElementVisibleStatus(Type_Group);
	}

	//

	// Group name temp is displayed
	public boolean Temperature_State() {
		WebElement Temperaturer_GrpName = driver.findElementByName("Temperature");

		return IsElementVisibleStatus(Temperaturer_GrpName);
	}

	// Group name temp is displayed
	public boolean Pressure_State() {
		WebElement Pressure_GrpName = driver.findElementByName("Pressure");

		return IsElementVisibleStatus(Pressure_GrpName);
	}

	// fetch value from start qual value
	public String Fetch_StartQual_value() {
		WebElement startQualVal = driver.findElementByAccessibilityId("StartQualificationTextBlock");
		return FetchText(startQualVal);
	}

	// fetch value from stop qual value
	public String Fetch_StopQual_value() {
		WebElement stopQualVal = driver.findElementByAccessibilityId("StopQualificationTextBlock");
		return FetchText(stopQualVal);
	}

	// fetch value from sampling rate value
	public String Fetch_samplingrate_value() {
		WebElement samplingrateVal = driver.findElementByAccessibilityId("FileRateTextBlock");
		return FetchText(samplingrateVal);
	}

	// fetch value from Transmission rate value
	public String Fetch_Transmissionrate_value() {
		WebElement TransmissionrateVal = driver.findElementByAccessibilityId("DisplayRateTextBlock");
		return FetchText(TransmissionrateVal);
	}

	// RFTemperatureMinTextBlock
	public String Fetch_RFInactive_value() {
		WebElement RFTemperatureMinTextBlock = driver.findElementByAccessibilityId("RFTemperatureMinTextBlock");
		return FetchText(RFTemperatureMinTextBlock);
	}

	//

	// fetch value of BaseTemp value
	public String Fetch_BaseTemp_value() {
		WebElement BaseTempVal = driver.findElementByAccessibilityId("BaseTemperatureTextBlock");
		return FetchText(BaseTempVal);
	}
	// ZValueTextBlock

	// fetch value of BaseTemp value
	public String Fetch_ZValueTextBlock() {
		WebElement Zvalue = driver.findElementByAccessibilityId("ZValueTextBlock");
		return FetchText(Zvalue);
	}

	// fetch value of DValueTextBlock value
	public String Fetch_DValueTextBlock() {
		WebElement Zvalue = driver.findElementByAccessibilityId("DValueTextBlock");
		return FetchText(Zvalue);
	}

	// fetch value of Calculate Lethality TextBlock value
	public String Fetch_CL_TextBlock() {
		WebElement CLvalue = driver.findElementByAccessibilityId("CalculateLethalityTextBlock");
		return FetchText(CLvalue);
	}

	// Is_SaturationPressureOfSteam_IsDisplayed
	public boolean Is_SaturationPressureOfSteam_IsDisplayed() {
		WebElement SPofsteam = driver.findElementByName("Saturation Pressure of steam :");
		return SPofsteam.isDisplayed();
	}

	// Is_SaturationTemperatureOfSteam_IsDisplayed
	public boolean Is_SaturationTemperatureOfSteam_IsDisplayed() {
		WebElement STofsteam = driver.findElementByName("Saturation Temperature of steam :");
		return STofsteam.isDisplayed();
	}

	// Click Save button
	public void clickSaveBtn() throws InterruptedException {
		clickOn(SaveSetupButton);
	}

	// Fetch alert msg
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

	// Login Popup presence
	public boolean UserLoginPopupVisible() throws InterruptedException {
		WebElement LgInPopup = driver.findElementByName("Enter User Credentials");
		return IsElementVisibleStatus(LgInPopup);
	}

}
