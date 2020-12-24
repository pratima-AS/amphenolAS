package com.vrt.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class Setup_SensorDescriptionPage extends BaseClass {

	// Define Setup page element variable declaration definition
	private WebElement SensorDescriptionPageName = null;
	private WebElement SelectSensorTypeBy_field = null;
	private WebElement PageCloseIcon_field = null;
	private WebElement SerialNo_field = null;
	private WebElement Label_field = null;
	private WebElement SensorType_field = null;
	private WebElement Description_rightpanefield = null;
	private WebElement Description_Leftpanefield = null;
	private WebElement Type_field = null;
	private WebElement Close_button = null;
	private WebElement Ok_button = null;
	private WebElement AutoNumberCheckBox = null;
	private WebElement SensorType_label = null;
	private WebElement SensorType_ComboBox = null;
	private WebElement Description_Txtfield = null;
	private WebElement clear_btn = null;

	private void initializeEelements() {
		SensorDescriptionPageName = driver.findElementByName("Sensor Description");
		SelectSensorTypeBy_field = driver.findElementByName("Select Sensor Type By :");
		PageCloseIcon_field = driver.findElementByAccessibilityId("CloseButton");
		SerialNo_field = driver.findElementByName("No.");
		Label_field = driver.findElementByName("Label");
		Type_field = driver.findElementByName("Type");
		Description_Leftpanefield = driver.findElementByName("Description");
		Description_rightpanefield = driver.findElementByName("Description: ");
		Description_Txtfield = driver.findElementByAccessibilityId("ContentElement");
		Close_button = driver.findElementByName("Close");
		Ok_button = driver.findElementByName("OK");
		AutoNumberCheckBox = driver.findElementByAccessibilityId("SensorLabelAutoNumberCheckBox");
		SensorType_label = driver.findElementByName("Sensor Type");
		//SensorType_ComboBox = driver.findElementByAccessibilityId("SensorTypeSimComboBox");
		SensorType_ComboBox = driver.findElementByClassName("ComboBox");
		clear_btn = driver.findElementByAccessibilityId("DescriptionClearButton");
		

	}

	Setup_SensorDescriptionPage() throws IOException {
		super();
		initializeEelements();
	}
	
	// Release memory
	public void resetWebElements() {
		SensorDescriptionPageName = null;
		SelectSensorTypeBy_field = null;
		PageCloseIcon_field = null;
		SerialNo_field = null;
		Label_field = null;
		SensorType_field = null;
		Description_rightpanefield = null;
		Description_Leftpanefield = null;
		Type_field = null;
		Close_button = null;
		Ok_button = null;
		AutoNumberCheckBox = null;
		SensorType_label = null;
		SensorType_ComboBox = null;
		Description_Txtfield = null;
		clear_btn = null;
	}
	
	// Check the presence of SensorDescription page
	public boolean IsSensorDescriptionTitle_Visible() {
		return IsElementVisibleStatus(SensorDescriptionPageName);
	}

	// Check the presence of SelectSensorTypeBy_field
	public boolean IsSelectSensorTypeByHeader_Visible() {
		return IsElementVisibleStatus(SelectSensorTypeBy_field);
	}

	// Check the presence of Close Icon
	public boolean IsPageCloseIcon_Visible() {
		return IsElementVisibleStatus(PageCloseIcon_field);
	}

	// Check the presence of Serial No
	public boolean IsSerialNo_field_Visible() {
		return IsElementVisibleStatus(SerialNo_field);
	}

	// Check the presence of Label
	public boolean IsLabel_Visible() {
		return IsElementVisibleStatus(Label_field);
	}

	// Check the presence of Type_field
	public boolean IsType_field_Visible() {
		return IsElementVisibleStatus(Type_field);
	}

	// Check the presence of SensorType_field
	public boolean IsSensorType_field_Visible() {
		return IsElementVisibleStatus(SensorType_field);
	}

	// Check the presence of Description_field
	public boolean IsLeftPane_Description_field_Visible() {
		return IsElementVisibleStatus(Description_Leftpanefield);
	}

	// Check the presence of Right pane Description title
	public boolean IsRightpane_Description_field_Visible() {
		return IsElementVisibleStatus(Description_rightpanefield);
	}

	// Check the presence of Right pane Description_Txtfield
	public boolean is_Description_Txtfield_Visible() {
		return IsElementVisibleStatus(Description_Txtfield);
	}

	// Check is clear btn Enable
	public boolean IsClear_btnEnable() {
		return IsElementEnabledStatus(clear_btn);
	}

	// Click on clear btn
	public void clickOnClear_btn() {
		clickOn(clear_btn);
	}

	// Check the presence of Right pane Description_Txtfield
	public void Click_Description_Txtfield() {
		clickOn(Description_Txtfield);
	}

	// Check the presence of Close_button
	public boolean IsClose_buttonVisible() {
		return IsElementVisibleStatus(Close_button);
	}

	// Check the presence of OK_button
	public boolean IsOk_buttonVisible() {
		return IsElementVisibleStatus(Ok_button);
	}

	// Check the presence of OK_button
	public boolean IsOk_btnEnable() {
		return IsElementEnabledStatus(Ok_button);
	}

	// Click OK btn
	public void clickOk() {
		clickOn(Ok_button);
	}

	// Click on close button to navigate Setup_SensorConfigPage
	public Setup_SensorConfigPage clickClose() throws IOException {
		clickOn(Close_button);
		return new Setup_SensorConfigPage();
	}

	// Click on PageCloseIcon button to navigate Setup_SensorConfigPage

	public Setup_SensorConfigPage click_PageCloseIcon() throws IOException {
		clickOn(PageCloseIcon_field);
		return new Setup_SensorConfigPage();
	}

	// Check the presence of AutoNumberCheckBox
	public boolean Is_SensorLabelAutoNumber_checked() {
		return checkboxSelectStatus(AutoNumberCheckBox);
	}
	
	// verify the state of AutoNumber check box
	public boolean Is_Autonumber_checkedIn() {
		return checkboxSelectStatus(AutoNumberCheckBox);
	}

	// Is AutoNumber field Enable
	public boolean IsAutoNumber_Enable() {
		return IsElementEnabledStatus(AutoNumberCheckBox);
	}

	// Check the presence of SensorType ComboBox
	public boolean is_SensorType_ComboBoxVisible() {
		return IsElementVisibleStatus(SensorType_ComboBox);
	}

	// get text from type field
	public String get_Typetext() {
		List<WebElement> senrList = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return FetchText(senrList.get(2));
	}

	// get label name from label field
	public String get_Labelext() {
		List<WebElement> senrList = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return FetchText(senrList.get(1));
	}
	

	// Click on SensorType_ComboBox
	public void click_SensorType_Field() throws InterruptedException {
		List<WebElement> listof_Comboox = driver.findElementsByClassName("ComboBox");
		listof_Comboox.get(1).click();
		Thread.sleep(2000);
	}

	// Check Temp displayed in combo box
	public boolean IsTemperature_SensorType_Visible() {
		WebElement TemperatureIncombobox = driver.findElementByName("Temperature");
		return TemperatureIncombobox.isEnabled();

	}

	// Check Hmd displayed in combo box
	public boolean IsHumidity_SensorType_Visible() {
		WebElement HmdIncombobox = driver.findElementByName("Humidity");
		return HmdIncombobox.isEnabled();

	}

	// Check Pressure displayed in combo box
	public boolean IsPressure_SensorType_Visible() {
		WebElement PressureIncombobox = driver.findElementByName("Pressure");
		return PressureIncombobox.isEnabled();
	}
	
	//Select sensor type from the drop down box
	public void select_SensorTypee(String sensorType) throws InterruptedException {
		clickOn(SelectSensorTypeBy_field);
		Thread.sleep(2000);
		click_SensorType_Field();
		//WebElement Temperature_sensorType = driver.findElementByName("Temperature");
		List<WebElement> sensorType_Comboox_items = driver.findElementsByClassName("ComboBoxItem");
		//System.out.println(sensorType_Comboox_items.get(3).getText());
		
		/*for (WebElement sensorItems : sensorType_Comboox_items) {
			System.out.println(sensorItems.getText());
		}*/

		if (sensorType.contains("Temperature")) {
			sensorType_Comboox_items.get(2).click();
			//clickOn(Temperature_sensorType);
		} else if (sensorType.contains("Pressure")) {
			sensorType_Comboox_items.get(3).click();
		} else {
			System.out.println("Incorrect sensor type selected");
		}
	}

	// Presence of txtFrom Range box
	public boolean is_From_textVisible() {
		WebElement txtFrom_field = driver.findElementByAccessibilityId("txtFrom");
		return IsElementVisibleStatus(txtFrom_field);
	}

	// Presence of txtTo Range box
	public boolean is_To_textVisible() {
		WebElement txtTo_field = driver.findElementByAccessibilityId("txtTo");
		return IsElementVisibleStatus(txtTo_field);
	}

	// Enter num into textFrom
	public void Enter_Num_From(String Num) {
		List<WebElement> From_field = driver.findElementsByAccessibilityId("txtFrom");
		From_field.get(1).clear();
		From_field.get(1).click();
		From_field.get(1).sendKeys(Num);
	}

	// Enter num into textTo
	public void Enter_TxtTo(String Num) {
		List<WebElement> To_field = driver.findElementsByAccessibilityId("txtTo");
		To_field.get(1).clear();
		To_field.get(1).click();
		To_field.get(1).sendKeys(Num);
	}

//Fetch data from txtFrom field
	public String get_from_text() {
		List<WebElement> From_field = driver.findElementsByAccessibilityId("txtFrom");
		return From_field.get(1).getText();
	}

//Fetch data from txtTo field
	public String get_To_text() {
		List<WebElement> To_field = driver.findElementsByAccessibilityId("txtTo");
		return To_field.get(1).getText();
	}

	// Description_Leftpanefield
	public void click_Description() {
		clickOn(Description_Leftpanefield);
	}

	// Enter text into Description Field
	public void Enter_Description(String Dsc) {
		WebElement Description_field = driver.findElementByAccessibilityId("ContentElement");
		clickOn(Description_field);
		ClearText(Description_field);
		enterText(Description_field, Dsc);
	}

	// Fetch data from txtTo field
	public String get_Description_text() {
		return FetchText(Description_Leftpanefield);
	}

//Check Pressure displayed in combo box when only pressure added in Sensor config page
	public boolean OnlyPressure_SensorType_Visible() {
		List<WebElement> PresIncombobox = driver.findElementsByClassName("ComboBoxItem");
		return IsElementVisibleStatus(PresIncombobox.get(0));
	}

	// Check humidity displayed in combo box when only humidity added in Sensor
	// config page
	public String Fetch_Humidity_SensorType_Txt() {
		List<WebElement> HmdIncombobox = driver.findElementsByClassName("ComboBoxItem");
		return HmdIncombobox.get(0).getText();
	}

	// Verify that by default first sensor should be selected on the left pane
	public boolean Firstsensor_Selected() {
		List<WebElement> senrList = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return senrList.get(0).isEnabled();
	}

	public boolean Secondsensor_Selected() {
		List<WebElement> senrList = driver.findElementByAccessibilityId("PART_ScrollViewer").findElements(By.name("2"));
		return senrList.get(1).isSelected();
	}

	// Click on First sensor
	public void Firstsensor_click() {
		WebElement senr1 = driver.findElementByName("1");
		clickOn(senr1);
	}

	// Click on second sensor
	public void Secondsensor_click() {
		WebElement senr2 = driver.findElementByName("2");
		clickOn(senr2);
	}

	// Click on fifth sensor
	public void Fifthsensor_click() {
		WebElement senr5 = driver.findElementByName("5");
		clickOn(senr5);
	}

//Click on eight sensor   
	public void Eightsensor_click() {
		WebElement senrList = driver.findElementByName("8");
		senrList.click();
	}

	// Fetch the description text from row 1
	public String Description_txt_Row1() {
		List<WebElement> desclist = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return desclist.get(3).getText();
	}

	// Fetch the description text from row 2
	public String Description_txt_Row2() {
		List<WebElement> desclist = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return desclist.get(7).getText();
	}

	// Fetch the description text from row 3
	public String Description_txt_Row3() {
		List<WebElement> desclist = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return desclist.get(11).getText();
	}

	// Fetch the description text from row 4
	public String Description_txt_Row4() {
		List<WebElement> desclist = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return desclist.get(15).getText();
	}

	// Fetch the description text from row 5
	public String Description_txt_Row5() {
		List<WebElement> desclist = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return desclist.get(19).getText();
	}

	// Fetch the description text from row 8
	public String Description_txt_Row8() {
		List<WebElement> desclist = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return desclist.get(31).getText();
	}

	// Presence of txtFrom Range box enable
	public boolean is_From_textEnable() {
		List<WebElement> From_field = driver.findElementsByAccessibilityId("txtFrom");
		return From_field.get(1).isEnabled();
	}

// Presence of txtTo Range box enable
	public boolean is_To_textEnable() {
		List<WebElement> To_field = driver.findElementsByAccessibilityId("txtTo");
		return To_field.get(1).isEnabled();
	}
	
	// Get text of the Button Bar Alert message
	public String Alertmsg_txt() {
		WebElement alrtmsg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(alrtmsg);
	}

}
