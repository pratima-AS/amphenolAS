package com.vrt.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class Setup_SensorConfigPage extends BaseClass {

	// Sensor Configuration page element variable declaration definition
	private WebElement SensorConfigPageTitle = null;
	private WebElement SensorConfigPageHeaderTxt = null;
	private WebElement AddSensors_btn = null;
	private WebElement ConfigureSensors_btn = null;
	private WebElement DefineSetup_btn = null;
	private WebElement GroupSensors_btn = null;

	private void initializeEelements() {
		SensorConfigPageTitle = driver.findElementByName("Sensors Configuration");
		SensorConfigPageHeaderTxt = driver.findElementByAccessibilityId("SetupHeaderTextBlock");
		AddSensors_btn = driver.findElementByName("Add Sensors");
		ConfigureSensors_btn = driver.findElementByName("Configure Sensors");
		DefineSetup_btn = driver.findElementByName("Define Setup");
		GroupSensors_btn = driver.findElementByAccessibilityId("NextButton");
	}

	Setup_SensorConfigPage() throws IOException {
		super();
		initializeEelements();
	}

	// Release memory
	public void resetWebElements() {
		SensorConfigPageTitle = null;
		SensorConfigPageHeaderTxt = null;
		AddSensors_btn = null;
		ConfigureSensors_btn = null;
		DefineSetup_btn = null;
		GroupSensors_btn = null;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Sensor Config Page methods
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~

	// Check the presence of Sensor Configuration page
	public boolean sensorConfigPage_state() {
		return IsElementVisibleStatus(SensorConfigPageTitle);
	}

	// Check the presence of Define Setup name
	public boolean SensorConfig_setupname_state() {
		return IsElementVisibleStatus(SensorConfigPageHeaderTxt);
	}

	// Check the presence ofAdd Sensors_btn
	public boolean AddSensors_btn_state() {
		return IsElementVisibleStatus(AddSensors_btn);
	}

	// Check the presence of Configure Sensors_btn
	public boolean ConfigureSensors_btn_state() {
		return IsElementVisibleStatus(ConfigureSensors_btn);
	}

	// Check the presence of DefineSetup_btn
	public boolean DefineSetup_btn_state() {
		return IsElementVisibleStatus(DefineSetup_btn);
	}

	// Check the presence of GroupSensors_btn
	public boolean GroupSensors_btn_state() {
		return IsElementVisibleStatus(GroupSensors_btn);
	}

	// Click on GroupSensors_btn to capture alert message for null sensors
	public void Click_GroupSensors_btn() {
		clickOn(GroupSensors_btn);
	}

	// click GroupSensors_btn to navigate Group Sensors page
	public Setup_GroupSensorsPage Click_nextbtn() throws IOException {
		clickOn(GroupSensors_btn);
		return new Setup_GroupSensorsPage();
	}

	// click GroupSensors_btn to navigate Group Sensors page click YES (less number
	// of sensor config)
	public Setup_GroupSensorsPage Click_nextbtn_LessSnsrconfig() throws IOException {
		clickOn(GroupSensors_btn);

		WebElement Yesbtn = driver.findElementByName("Yes");
		clickOn(Yesbtn);
		return new Setup_GroupSensorsPage();
	}

	// While editing setup if user edit the temp count and click on next button
	// application will display two alert message
	// Click on Yes for first alert message i.e changing the existing sensor
	// configuration
	// then click yes for second alert message i.e Less number of sensors are
	// configured than the definied

	public Setup_GroupSensorsPage Click_nxtbtn_ForChangingExistingSC() throws IOException, InterruptedException {
		clickOn(GroupSensors_btn);
		Thread.sleep(2000);
		WebElement Yesbtn = driver.findElementByName("Yes");
		clickOn(Yesbtn);
		Thread.sleep(2000);
		WebElement Yesbtn1 = driver.findElementByName("Yes");
		clickOn(Yesbtn1);
		return new Setup_GroupSensorsPage();

	}

	// Fetch the alert text message for the less number of sensor configure

	public boolean Is_lessnumberSenAlertBox_Visible() {
		clickOn(GroupSensors_btn);
		WebElement alert_text = driver.findElementByName(
				"Less number of sensors are configured than the defined/added number of sensors. Is this Intentional ?");
		return IsElementVisibleStatus(alert_text);
	}

// Get the Sensor Configuration page title text
	public String get_SensorConfigurationPage_titletext() {
		return FetchText(SensorConfigPageHeaderTxt);
	}

	// Get the Sensor Configuration page text
	public String get_SensorConfigurationPage_text() {
		return FetchText(SensorConfigPageTitle);
	}

	// ExpanderImage_btn
	public void Click_Addsensors_Expanderbtn() {
		List<WebElement> ExpandArrow = driver.findElementsByClassName("Image");
		// System.out.println(ExpandArrow.size());
		ExpandArrow.get(0).click();
	}

	// Check the presence of Temperature field
	public boolean Temperature_Field_state() {
		WebElement Temperature_Field = driver.findElementByName("Temperature #");
		return IsElementVisibleStatus(Temperature_Field);
	}

	// Check the presence of Humidity field
	public boolean Humidity_Field_state() {
		WebElement Humidity_Field = driver.findElementByName("Humidity #");
		return IsElementVisibleStatus(Humidity_Field);
	}

	// Check the presence of Pressure field
	public boolean Pressure_Field_state() {
		WebElement Pressure_Field = driver.findElementByName("Pressure #");
		return IsElementVisibleStatus(Pressure_Field);
	}

	// Check the presence of Add-Btn field
	public boolean Add_btnField_state() {
		WebElement AddBtn_Field = driver.findElementByAccessibilityId("SelectButton");
		return IsElementVisibleStatus(AddBtn_Field);
	}

	// Get the Temperature text
	public String get_Temperature_text() {
		List<WebElement> TextFields = driver.findElementsByClassName("TextBox");
		return FetchText(TextFields.get(0));

	}

	// Click on back btn
	public Setup_defineSetupPage DefineSetup_back_btn() throws IOException {
		clickOn(DefineSetup_btn);
		return new Setup_defineSetupPage();
	}

	// Get the Humidity text
	public String get_Humidity_text() {
		List<WebElement> TextFields = driver.findElementsByClassName("TextBox");
		return FetchText(TextFields.get(1));
	}

	// Get the Pressure text
	public String get_Pressure_text() {
		List<WebElement> TextFields = driver.findElementsByClassName("TextBox");
		return FetchText(TextFields.get(2));
	}

	// Enter Temp , press, Hmd all at a time and click add button
	public void Enter_AllSensortypes(String TempCount, String HmdCount, String PrsrCount) {
		List<WebElement> TextFields = driver.findElementsByClassName("TextBox");
		TextFields.get(0).clear();
		TextFields.get(0).sendKeys(TempCount);
		TextFields.get(1).clear();
		TextFields.get(1).sendKeys(HmdCount);
		TextFields.get(2).clear();
		TextFields.get(2).sendKeys(PrsrCount);
		WebElement AddBtn_Field = driver.findElementByAccessibilityId("SelectButton");
		clickOn(AddBtn_Field);
	}

	// Enter Temperature count to add Temp sensors
	public void Enter_TemperatureCount_textField(String TempCount) throws InterruptedException {
		List<WebElement> TextFields = driver.findElementsByClassName("TextBox");
		// System.out.println(TextFields.size());

		// Enter Temperature count data
		TextFields.get(0).clear();
		TextFields.get(0).sendKeys(TempCount);

		WebElement AddBtn_Field = driver.findElementByAccessibilityId("SelectButton");
		clickOn(AddBtn_Field);
	}

	// Enter Humidity count data
	public void Enter_HumidityCount_textField(String HmdCount) {
		List<WebElement> TextFields = driver.findElementsByClassName("TextBox");

		// Enter Humidity count data
		TextFields.get(1).clear();
		TextFields.get(1).sendKeys(HmdCount);

		WebElement AddBtn_Field = driver.findElementByAccessibilityId("SelectButton");
		clickOn(AddBtn_Field);

	}

	// Enter pressure count data
	public void Enter_PressureCount_textField(String PrsrCount) {
		List<WebElement> TextFields = driver.findElementsByClassName("TextBox");
		// System.out.println(TextFields.size());

		// Enter Pressure count data
		TextFields.get(2).clear();
		TextFields.get(2).sendKeys(PrsrCount);

		WebElement AddBtn_Field = driver.findElementByAccessibilityId("SelectButton");
		clickOn(AddBtn_Field);
	}

	// Check the presence of Header of Temperature sensors
	public boolean Temperature_Sensor_Titlestate() {
		WebElement Temperature_Sensor_title = driver.findElementByName("Temperature");
		// return IsElementVisibleStatus(Temperature_Sensor_title);
		return Temperature_Sensor_title.isDisplayed();
	}

	// Check the presence of Humidity sensors
	public boolean Humidity_Sensor_Titlestate() {
		WebElement Humidity_Sensor_title = driver.findElementByName("Humidity");
		return IsElementVisibleStatus(Humidity_Sensor_title);
	}

	// Check the presence of Pressure sensors
	public boolean Pressure_Sensor_Titlestate() {
		WebElement Pressure_Sensor_title = driver.findElementByName("Pressure");
		return IsElementVisibleStatus(Pressure_Sensor_title);
	}

	// Total Sensors created
	public String[] SensorCount() {
		List<WebElement> sensorList = driver.findElementByName("ValProbe RT")
				.findElements(By.className("GridViewItem"));
		// System.out.println("Total sensors created: " + sensorList.size());
		String[] a = null;
		return a;

	}

	// Total Sensors details
	public String[] SensorDetails() {
		List<WebElement> sensorList = driver.findElementByName("ValProbe RT")
				.findElements(By.className("GridViewItem"));
		String[] a = null;

		for (int i = 0; i < sensorList.size(); i++) {
			// System.out.println("Sensor type : " + AssetList.get(i).getText());
			List<WebElement> SensorTileInfoList = sensorList.get(i).findElements(By.className("TextBlock"));
			// System.out.println(" Sensor tile count: " + SensorTileInfoList.size());

			// Fetch all the contents of the tile
			for (int j = 0; j < SensorTileInfoList.size(); j++) {
				// System.out.println("SensorTileInfo: " + SensorTileInfoList.get(j).getText());
			}
		}

		return a;
	}

	// Check the presence of VerticalScrollBar field
	public boolean IsDisplayingVerticalScrollBar() {
		WebElement Vertical_ScrollBar = driver.findElementByAccessibilityId("VerticalScrollBar");
		return IsElementVisibleStatus(Vertical_ScrollBar);
	}

	// Click on Temperature filter
	public void Clickon_Temperature() {
		WebElement btn_Temperature = driver.findElementByAccessibilityId("btnTemperature");
		clickOn(btn_Temperature);
	}

	// Is Temperature filters sensors label visible
	public boolean Temperature_filters() {
		WebElement btn_Temperature = driver.findElementByAccessibilityId("btnTemperature");
		clickOn(btn_Temperature);
		WebElement label_Temperature = driver.findElementByName("Temperature");
		return IsElementVisibleStatus(label_Temperature);
	}

	// click sensors from Temperature filter
	public void Click_Temp_sensor() {
		// WebElement temp_senr = driver.findElementByName("Sensor 1");
		List<WebElement> Tmpsenr = driver.findElementsByClassName("GridViewItem");
		Tmpsenr.get(0).click();
	}

	// Get sensor text
	public String get_sensortext() {
		List<WebElement> senrList = driver.findElementByName("VRT.DataObjects.DataContracts.VRTSensors")
				.findElements(By.className("TextBlock"));
		return FetchText(senrList.get(0));
	}

	// click 2nd sensor from Temperature filter
	public void Click_Temp_sensor2() {
		// WebElement temp_senr = driver.findElementByName("Sensor 1");
		List<WebElement> Tmpsenr = driver.findElementsByClassName("GridViewItem");
		Tmpsenr.get(1).click();
	}

	// click Multiple sensors from Temperature filter
	public void Click_Temp_sensor_Multi() {
		// WebElement temp_senr = driver.findElementByName("Sensor 1");
		List<WebElement> Tmpsenr = driver.findElementsByClassName("GridViewItem");
		Tmpsenr.get(0).click();
		Tmpsenr.get(1).click();
		Tmpsenr.get(2).click();

	}

	// Click on Humidity filters
	public void Clickon_Humidity() {
		WebElement btn_Humidity = driver.findElementByAccessibilityId("btnHumidity");
		clickOn(btn_Humidity);
	}

	// click sensors from Humidity filter
	public void Click_Hmd_sensor() {
		// WebElement temp_senr = driver.findElementByName("Sensor 1");
		List<WebElement> Hmdsenr = driver.findElementsByClassName("GridViewItem");
		Hmdsenr.get(0).click();

	}

	// click Multiple sensors from Humidity filter
	public void Click_Hmd_sensor_Multi() {
		// WebElement temp_senr = driver.findElementByName("Sensor 1");
		List<WebElement> Hmdsenr = driver.findElementsByClassName("GridViewItem");
		Hmdsenr.get(0).click();
		Hmdsenr.get(1).click();
		Hmdsenr.get(2).click();

	}
	/*
	 * //Fetch humidity sensor text public String get_Hmd_sensortext() {
	 * List<WebElement> Hmdsenr =
	 * driver.findElementByName("VRT.DataObjects.DataContracts.VRTSensors").
	 * findElements(By.className("TextBlock")); //List<WebElement> Hmdsenr =
	 * driver.findElementsByClassName("TextBlock"); Hmdsenr.get(0).click(); return
	 * FetchText(Hmdsenr.get(0)); }
	 */

	// Is Humidity filters sensors label visible
	public boolean Humidity_filters() {
		WebElement btn_Humidity = driver.findElementByAccessibilityId("btnHumidity");
		clickOn(btn_Humidity);
		WebElement label_Humidity = driver.findElementByName("Humidity");
		return IsElementVisibleStatus(label_Humidity);
	}

	// Click on Pressure

	public void Click_Pressure() {
		WebElement btn_Pressure = driver.findElementByAccessibilityId("btnPressure");
		clickOn(btn_Pressure);
	}

	// Click on Pressure filters
	public void Click_Prsr_sensor() {
		List<WebElement> Prsrsenr = driver.findElementsByClassName("GridViewItem");
		Prsrsenr.get(0).click();

	}

	// click Multiple sensors from Pressure filter
	public void Click_Psr_sensor_Multi() {
		// WebElement temp_senr = driver.findElementByName("Sensor 1");
		List<WebElement> Hmdsenr = driver.findElementsByClassName("GridViewItem");
		Hmdsenr.get(0).click();
		Hmdsenr.get(1).click();
		Hmdsenr.get(2).click();

	}

	// Is Pressure filters sensors label visible
	public boolean Pressure_filters() {
		WebElement btn_Pressure = driver.findElementByAccessibilityId("btnPressure");
		clickOn(btn_Pressure);
		WebElement label_Pressure = driver.findElementByName("Pressure");
		return IsElementVisibleStatus(label_Pressure);
	}

	// Configuration sensors Expander
	public void Click_Configurationsensors_Expanderbtn() {
		List<WebElement> ExpandArrow = driver.findElementsByClassName("Image");
		// System.out.println(ExpandArrow.size());
		ExpandArrow.get(1).click();
	}

	// Is Sensor Type visible
	public boolean IsSensortypevisible() {
		WebElement Sensortype_state = driver.findElementByName("Sensor Type");
		return IsElementVisibleStatus(Sensortype_state);
	}

	// Is SensorType field Enable
	public boolean IsSensortype_Enable() {
		WebElement Sensortype_field = driver.findElementByAccessibilityId("SensorTypeSimComboBox");
		return IsElementEnabledStatus(Sensortype_field);
	}

	// Fetch the sensor type text
	public String get_Sensortype_text() {
		WebElement Sensortype_field = driver.findElementByAccessibilityId("SensorTypeSimComboBox");
		return FetchText(Sensortype_field);
	}

	// Click on sensor type field
	public void Click_Sensortype_field() {
		WebElement Sensortype_field = driver.findElementByAccessibilityId("SensorTypeSimComboBox");
		clickOn(Sensortype_field);
	}

	// Click on temp from sensortype
	public void select_Sensortype_temp() {
		WebElement Sensortype_field = driver.findElementByAccessibilityId("SensorTypeSimComboBox");
		clickOn(Sensortype_field);
		WebElement tempInlist = driver.findElementByName("Temperature     (°C)");
		clickOn(tempInlist);
	}

	// Is Temp visible under sensor type drop down list
	public boolean IsTemperature_visible_Drpdwnlist() {
		WebElement Sensortype_field = driver.findElementByAccessibilityId("SensorTypeSimComboBox");
		clickOn(Sensortype_field);
		WebElement tempInlist = driver.findElementByName("Temperature     (°C)");
		return IsElementVisibleStatus(tempInlist);
	}

	// Click on Humidity from sensor type
	public void select_Sensortype_Hmd() {
		WebElement Sensortype_field = driver.findElementByAccessibilityId("SensorTypeSimComboBox");
		clickOn(Sensortype_field);
		WebElement HmdInlist = driver.findElementByName("Humidity     (%RH)");
		clickOn(HmdInlist);
	}

	// Is Humidity visible under sensor type drop down list
	public boolean IsHumidity_visible_Drpdwnlist() {
		WebElement Sensortype_field = driver.findElementByAccessibilityId("SensorTypeSimComboBox");
		clickOn(Sensortype_field);
		WebElement HmdInlist = driver.findElementByName("Humidity     (%RH)");
		return IsElementVisibleStatus(HmdInlist);
	}

	// Is Pressure visible under sensor type drop down list
	public boolean IsPressure_visible_Drpdwnlist() {
		WebElement Sensortype_field = driver.findElementByAccessibilityId("SensorTypeSimComboBox");
		clickOn(Sensortype_field);
		WebElement PrsrInlist = driver.findElementByName("Pressure      (Bar)");
		return IsElementVisibleStatus(PrsrInlist);
	}

	// Select on Pressure from sensor type
	public void select_Sensortype_Pr() {
		WebElement Sensortype_field = driver.findElementByAccessibilityId("SensorTypeSimComboBox");
		clickOn(Sensortype_field);
		WebElement PrInlist = driver.findElementByName("Pressure      (Bar)");
		clickOn(PrInlist);
	}

	// Is SensorLabel visible
	public boolean IsSensorLabelvisible() {
		WebElement SensorLabel_state = driver.findElementByName("Sensor Label");
		return IsElementVisibleStatus(SensorLabel_state);
	}

	// Is from field label visible
	public boolean IsFromfieldvisible() {
		WebElement FromField_state = driver.findElementByName("From");
		return IsElementVisibleStatus(FromField_state);
	}

	// Is To field label visible
	public boolean IsTofieldvisible() {
		WebElement FromField_state = driver.findElementByName("To");
		return IsElementVisibleStatus(FromField_state);
	}

	// Is from field Enable
	public boolean IsFromfield_Enable() {
		WebElement FromField_state = driver.findElementByAccessibilityId("txtFrom");
		return IsElementEnabledStatus(FromField_state);
	}

	// Is To field Enable
	public boolean IsTofield_Enable() {
		WebElement ToField_state = driver.findElementByAccessibilityId("txtTo");
		return IsElementEnabledStatus(ToField_state);
	}

	// Fetch the From field text
	public String get_Fromfield_text() {
		WebElement Fromtxt_field = driver.findElementByAccessibilityId("txtFrom");
		return FetchText(Fromtxt_field);
	}

	// Enter From Count
	public void Enter_Num_From(String Num) {
		WebElement From_field = driver.findElementByAccessibilityId("txtFrom");
		clickOn(From_field);
		ClearText(From_field);
		enterText(From_field, Num);
	}

	// Fetch the To field text
	public String get_Tofield_text() {
		WebElement Totxt_field = driver.findElementByAccessibilityId("txtTo");
		return FetchText(Totxt_field);
	}

	// Enter Count to To text field
	public void Enter_Num_To(String Num) {
		WebElement To_field = driver.findElementByAccessibilityId("txtTo");
		clickOn(To_field);
		ClearText(To_field);
		enterText(To_field, Num);
	}

	// Is Assign Button visible
	public boolean IsAssign_Button_visible() {
		WebElement AssignButton_state = driver.findElementByAccessibilityId("AssignButton");
		return IsElementVisibleStatus(AssignButton_state);
	}

	// Is Description Button_state visible
	public boolean IsDescription_Button_visible() {
		WebElement DescriptionButton_state = driver.findElementByAccessibilityId("DescriptionButton");
		return IsElementVisibleStatus(DescriptionButton_state);
	}

	// Is SensorLabel field Enable
	public boolean IsSensorLabel_Enable() {
		WebElement SensorLabel_state = driver.findElementByAccessibilityId("SensorLabelTextBox");
		return IsElementEnabledStatus(SensorLabel_state);
	}

	// Is Assign Button Enable
	public boolean IsAssign_Button_Enable() {
		WebElement AssignButton_state = driver.findElementByAccessibilityId("AssignButton");
		return IsElementEnabledStatus(AssignButton_state);
	}

	// click on assign button
	public void Click_assignBtn() throws InterruptedException {
		WebElement AssignButton_clk = driver.findElementByAccessibilityId("AssignButton");
		clickOn(AssignButton_clk);
		Thread.sleep(500);
	}

	// Is Description Button_state Enable
	public boolean IsDescription_Button_Enable() {
		WebElement DescriptionButton_state = driver.findElementByAccessibilityId("DescriptionButton");
		return IsElementEnabledStatus(DescriptionButton_state);
	}

// Is Assign Button field Enable
	public boolean IsAssignButton_Enable() {
		WebElement AssignButton_field = driver.findElementByAccessibilityId("AssignButton");
		return IsElementEnabledStatus(AssignButton_field);
	}

	// Fetch the SensorLabel_field text
	public String get_SensorLabel_text() {
		WebElement SensorLabel_field = driver.findElementByAccessibilityId("SensorLabelTextBox");
		return FetchText(SensorLabel_field);
	}

	// Enter data into the SensorLabel field
	public void Enter_SensorLabel(String data) {
		WebElement SensorLabel_field = driver.findElementByAccessibilityId("SensorLabelTextBox");
		ClearText(SensorLabel_field);
		clickOn(SensorLabel_field);
		enterText(SensorLabel_field, data);
	}

	// Fetch the SensorLabel_Number text box
	public String get_SensorLabel_Num_text() {
		WebElement SensorLabelNum_field = driver.findElementByAccessibilityId("SensorLabelNumberTextBox");
		return FetchText(SensorLabelNum_field);
	}

	// Enter data into the SensorLabel field
	public void Enter__Num_SensorLabel(String data) {
		WebElement SensorLabelNum_field = driver.findElementByAccessibilityId("SensorLabelNumberTextBox");
		ClearText(SensorLabelNum_field);
		clickOn(SensorLabelNum_field);
		enterText(SensorLabelNum_field, data);
	}

	// Click on Description Button
	public Setup_SensorDescriptionPage Click_DescriptionButton() throws IOException {
		WebElement Description_Button = driver.findElementByAccessibilityId("DescriptionButton");
		clickOn(Description_Button);
		return new Setup_SensorDescriptionPage();
	}

}
