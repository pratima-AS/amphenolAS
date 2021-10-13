/**
* @author ruchika.behura
*
*/

package com.vrt.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.vrt.base.BaseClass;

public class VRTLoggerHubPage extends BaseClass {

	WebElement VRTLogger = null;

	WebElement Back_btn = null;
	WebElement SerialNo = null;
	WebElement Mfgcaldate = null;
	WebElement Lastverificationdate = null;
	WebElement LoggerType = null;
	WebElement filter_btn = null;

	private void initElements() {

		VRTLogger = driver.findElementByName("VRTLogger");
		Back_btn = driver.findElementByAccessibilityId("ArrowGlyph");
		SerialNo = driver.findElementByName("Serial No: ");
		Mfgcaldate = driver.findElementByName("Mfg cal date: ");
		Lastverificationdate = driver.findElementByName("Last verification date: ");
		LoggerType = driver.findElementByName("Logger Type: ");
		filter_btn = driver.findElementByAccessibilityId("FilterEquipmentsButton");
	}

	VRTLoggerHubPage() throws IOException {
		super();
		initElements();

	}

	// Release memory
	public void resetWebElements() {
		VRTLogger = null;
		Back_btn = null;
		Mfgcaldate = null;
		Lastverificationdate = null;
		LoggerType = null;
		filter_btn = null;
	}

	public Equuipment_VRTLoggersDetailspage Click_VRTSerialNo(String SN) throws InterruptedException, IOException {

		List<WebElement> VRTList = driver.findElementByName("VRT.DataObjects.DataContracts.EquipmentMasterData")
				.findElements(By.className("GridViewItem"));

		// Loop for the different serial number created
		for (int i = 0; i < VRTList.size(); i++) {
			// System.out.println("serial number : " + IrtdList.get(i).getText());

			List<WebElement> VRTTileInfoList = VRTList.get(i).findElements(By.className("TextBlock"));
			// System.out.println(" VRT tile info count: " + VRTTileInfoList.size());

			// Fetch all the contents of the Asset tile
			for (int j = 0; j < VRTTileInfoList.size(); j++) {
				// System.out.println("VRTTileInfo: "+VRTTileInfoList.get(j).getText());

				if (VRTTileInfoList.get(j).getText().contains(SN)) {
					clickOn(VRTTileInfoList.get(j));
					Thread.sleep(2000);
					break;
				}
			}
		}
		return new Equuipment_VRTLoggersDetailspage();

	}

	// is VRTLogger _Title_Visible

	public boolean isVRTLogger_Title_Visible() {
		return IsElementVisibleStatus(VRTLogger);

	}

	public EquipmentHubPage click_Back_btn() throws IOException {
		clickOn(Back_btn);
		return new EquipmentHubPage();
	}

	public boolean isSerialNo_Visible() {
		return IsElementVisibleStatus(SerialNo);

	}

	// select any logger from the LoggerTypeComboBox in filter window

	public void sL_From_LoggerTypeComboBox(int index) {

		WebElement LT = driver.findElementByAccessibilityId("LoggerTypeComboBox");
		clickOn(LT);
		List<WebElement> options = driver.findElementByAccessibilityId("LoggerTypeComboBox")
				.findElements(By.className("ComboBoxItem"));

		clickOn(options.get(index));
	}

	public boolean isMfgcaldate_Visible() {
		return IsElementVisibleStatus(Mfgcaldate);

	}

	public boolean isMfgcalduedate_Visible() {
		WebElement Mfgcalduedate = driver.findElementByName("Mfg cal due date:");
		return IsElementVisibleStatus(Mfgcalduedate);

	}

	public boolean isLastverificationdate_Visible() {
		return IsElementVisibleStatus(Lastverificationdate);

	}

	public boolean isLoggerType_Visible() {
		return IsElementVisibleStatus(LoggerType);

	}
	// is filter_btn visible

	public boolean isfilter_btn_Visible() {
		return IsElementVisibleStatus(filter_btn);

	}
	// click on filter_btn

	public void clickOn_filterbtn() {
		clickOn(filter_btn);
	}

	// Filter popup visible

	public boolean filter_popup_Visible() {
		WebElement Filter_popup = driver.findElementByName("Filter");
		return IsElementVisibleStatus(Filter_popup);

	}

	// click on FilterByComboBox
	public void clickOn_FilterByComboBox() throws InterruptedException {
		WebElement FilterByComboBox = driver.findElementByAccessibilityId("FilterByComboBox");
		clickOn(FilterByComboBox);
	}

	// is OK Button visible in filter popup

	public boolean is_OKbtnVisible_inFilterpopup() throws InterruptedException {
		WebElement ok = driver.findElementByAccessibilityId("OKButton");
		return IsElementVisibleStatus(ok);
	}

	// is cancel Button visible in filter popup
	public boolean is_CancelbtnVisible_inFilterpopup() throws InterruptedException {
		WebElement CancelButton = driver.findElementByAccessibilityId("CancelButton");
		return IsElementVisibleStatus(CancelButton);
	}

	// FilterByBlock

	public boolean filter_By_Visible() {
		WebElement FilterBy = driver.findElementByAccessibilityId("FilterByBlock");
		return IsElementVisibleStatus(FilterBy);

	}

	// OKButton under filter window

	public void click_OKbtn_Filterpopup() throws InterruptedException {
		WebElement ok = driver.findElementByAccessibilityId("OKButton");
		clickOn(ok);
	}

	// CancelButton under filter window
	public void click_Cancelbtn_Filterpopup() throws InterruptedException {
		WebElement CancelButton = driver.findElementByAccessibilityId("CancelButton");
		clickOn(CancelButton);
	}

	// fetch the options text

	public String FetchTxt_from_filterByDD(int index) throws InterruptedException {
		List<WebElement> options = driver.findElementByAccessibilityId("FilterByComboBox")
				.findElements(By.className("ComboBoxItem"));

		return FetchText(options.get(index));
	}

	// FilterByComboBox
	public String FetchTxt_filterBY_selectedOption() throws InterruptedException {
		WebElement filterbytxt = driver.findElementByAccessibilityId("FilterByComboBox");

		return FetchText(filterbytxt);
	}

	// Select the filter options

	public void SelectOptions_from_filterByDD(int index) throws InterruptedException {
		List<WebElement> options = driver.findElementByAccessibilityId("FilterByComboBox")
				.findElements(By.className("ComboBoxItem"));

		clickOn(options.get(index));
	}

	// enter the serial no to filter

	public void enterSerialNo(String sl) {

		WebElement eqpSL = driver.findElementByAccessibilityId("EquipmentIDTextBox");
		ClearText(eqpSL);
		enterText(eqpSL, sl);

	}

	// Select Date and click ok
	public void Select_datepickerfield(int index) throws InterruptedException {
		List<WebElement> options = driver.findElementsByAccessibilityId("PART_PickerButton");
		clickOn(options.get(index));
		Thread.sleep(1000);
		WebElement ok = driver.findElementByAccessibilityId("PART_SelectorOKButton");
		clickOn(ok);

	}
	// FilterPopup

	public boolean is_FilterPopup_Visible() {

		boolean status = false;
		try {
			status = driver.findElementByAccessibilityId("FilterPopup").isDisplayed();
		}

		catch (Exception e) {
			System.out.println("FilterPopup is closed now");

		}
		return status;
	}

}
