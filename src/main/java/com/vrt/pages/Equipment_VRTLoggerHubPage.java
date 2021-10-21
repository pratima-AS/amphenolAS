/**
* @author ruchika.behura
*
*/

package com.vrt.pages;

import java.awt.AWTException;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.vrt.pages.NewEquipmentCreation_Page;
import com.vrt.base.BaseClass;

public class Equipment_VRTLoggerHubPage extends BaseClass {

	WebElement VRTLogger = null;
	WebElement Back_btn = null;
	WebElement SerialNo = null;
	WebElement Mfgcaldate = null;
	WebElement Mfgcalduedate = null;
	WebElement Lastverificationdate = null;
	WebElement LoggerType = null;
	WebElement filter_btn = null;
	WebElement Print = null;

	private void initElements() {

		VRTLogger = driver.findElementByName("VRTLogger");
		Back_btn = driver.findElementByAccessibilityId("ArrowGlyph");
		SerialNo = driver.findElementByName("Serial No: ");
		Mfgcaldate = driver.findElementByName("Mfg cal date: ");
		Mfgcalduedate = driver.findElementByName("Mfg cal due date: ");
		Lastverificationdate = driver.findElementByName("Last verification date: ");
		LoggerType = driver.findElementByName("Logger Type: ");
		filter_btn = driver.findElementByAccessibilityId("FilterEquipmentsButton");
		Print = driver.findElementByName("Print");

	}

	Equipment_VRTLoggerHubPage() throws IOException {
		super();
		initElements();

	}

	// Release memory
	public void resetWebElements() {
		VRTLogger = null;
		Back_btn = null;
		Mfgcaldate = null;
		Mfgcalduedate = null;
		Lastverificationdate = null;
		LoggerType = null;
		filter_btn = null;
		Print = null;
	}

	public Equuipment_VRTLoggersDetailspage Click_VRTSerialNo(String SN) throws InterruptedException, IOException {

		List<WebElement> VRTList = driver.findElementByAccessibilityId("loggersGrid")
				.findElements(By.className("GridViewItem"));

		// Loop for the different serial number created
		for (int i = 0; i < VRTList.size(); i++) {
			// System.out.println("serial number : " + IrtdList.get(i).getText());

			List<WebElement> VRTTileInfoList = VRTList.get(i).findElements(By.className("TextBlock"));
			// System.out.println(" VRT tile info count: " + VRTTileInfoList.size());

			// Fetch all the contents of the Asset tile
			for (int j = 0; j < VRTTileInfoList.size(); j++) {
				// System.out.println("VRTTileInfo: "+VRTTileInfoList.get(j).getText());

				String st = VRTTileInfoList.get(j).getText();
				if (st.equals(SN)) {
					VRTTileInfoList.get(j).click();
					Thread.sleep(2000);
					break;

				}
			}
		}
		return new Equuipment_VRTLoggersDetailspage();

	}

	// printLoggersGrid

	public void Select_VRTSerialNo_printLoggers(String SN) throws InterruptedException, IOException {

		List<WebElement> VRTList = driver.findElementByAccessibilityId("printLoggersGrid")
				.findElements(By.className("GridViewItem"));

		// Loop for the different serial number created
		for (int i = 0; i < VRTList.size(); i++) {
			// System.out.println("serial number : " + IrtdList.get(i).getText());

			List<WebElement> VRTTileInfoList = VRTList.get(i).findElements(By.className("TextBlock"));
			// System.out.println(" VRT tile info count: " + VRTTileInfoList.size());

			// Fetch all the contents of the Asset tile
			for (int j = 0; j < VRTTileInfoList.size(); j++) {
				// System.out.println("VRTTileInfo: "+VRTTileInfoList.get(j).getText());

				String st = VRTTileInfoList.get(j).getText();
				if (st.equals(SN)) {
					VRTTileInfoList.get(j).click();
					Thread.sleep(2000);
					break;

				}
			}
		}

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

	public void click_loggertype() {
		WebElement LT = driver.findElementByAccessibilityId("LoggerTypeComboBox");
		clickOn(LT);
	}
	// select any logger from the LoggerTypeComboBox in filter window

	public void sL_From_LoggerTypeComboBox(int index) {

		List<WebElement> options = driver.findElementByAccessibilityId("LoggerTypeComboBox")
				.findElements(By.className("ComboBoxItem"));

		clickOn(options.get(index));
	}

	public boolean isMfgcaldate_Visible() {
		return IsElementVisibleStatus(Mfgcaldate);

	}

	public boolean isMfgcalduedate_Visible() {

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

	// click on RefreshButton to clear the filters

	public void clickOn_RefreshFilterBtn() throws InterruptedException {
		WebElement RefreshButton = driver.findElementByAccessibilityId("RefreshButton");
		clickOn(RefreshButton);
	}

	// Is IPrint visible

	public boolean Is_PrintBtnVisible() throws IOException {

		return IsElementVisibleStatus(Print);
	}
	// Is RefreshButton visible

	public boolean Is_RefreshButtonVisible() throws IOException {
		WebElement RefreshButton = driver.findElementByAccessibilityId("RefreshButton");

		return IsElementVisibleStatus(RefreshButton);
	}

	// click on Print btn
	public void clickOn_PrintBtn() throws IOException {

		clickOn(Print);
	}

	// Print Reports

	// Verify that Print Reports window is displayed or not

	public boolean is_PrintReportsWindow_Visible() throws InterruptedException {
		WebElement PrintReports_window = driver.findElementByName("Print Reports");
		return IsElementVisibleStatus(PrintReports_window);
	}

	// is LoggersListBtn Selected

	public boolean is_LoggersListBtn_selected() throws InterruptedException {
		WebElement LoggersListBtn = driver.findElementByName("Loggers List");
		return LoggersListBtn.isSelected();
	}

	// filter by options of radio button

	public String getTextOf_FilterBy_Options(int index) throws InterruptedException {
		List<WebElement> options = driver.findElementsByClassName("RadioButton");

		return FetchText(options.get(index));
	}

	// verify if the DeSelect All button is visible

	public boolean is_DeSelectAllbtn_Visible() throws InterruptedException {
		WebElement DeSelectAll = driver.findElementByName("DeSelect All");
		return IsElementVisibleStatus(DeSelectAll);
	}

	// Select All
	public void click_selectAll_btn() throws InterruptedException {
		WebElement SelectAll = driver.findElementByName("Select All");
		clickOn(SelectAll);
	}

	// DeSelect All
	public void click_DeSelectAll_btn() throws InterruptedException {
		WebElement DeSelectAll = driver.findElementByName("DeSelect All");
		clickOn(DeSelectAll);
	}
	// DeSelect All

	// PrintReport btn

	public boolean is_PrintReportBtn_Visible() throws InterruptedException {
		WebElement PrintReport_btn = driver.findElementByAccessibilityId("PrintReport");
		return IsElementVisibleStatus(PrintReport_btn);
	}

	// click on PrintReport btn

	public void clickOn_PrintReportBtn() throws InterruptedException {
		WebElement PrintReport_btn = driver.findElementByAccessibilityId("PrintReport");
		clickOn(PrintReport_btn);
	}

	// Cancel btn

	public boolean is_CancelBtn_Visible() throws InterruptedException {
		WebElement Cancel_btn = driver.findElementByName("Cancel");
		return IsElementVisibleStatus(Cancel_btn);
	}

	// Cancel button

	public void click_CancelBtn() throws InterruptedException {
		WebElement Cancel_btn = driver.findElementByName("Cancel");
		clickOn(Cancel_btn);
	}

	// RadioButton

	public void click_FilterBy_Options(int index) throws InterruptedException {
		List<WebElement> options = driver.findElementsByClassName("RadioButton");
		clickOn(options.get(index));
	}

	// is filter by RadioButton selected

	public boolean is_FilterByRadioButton_Selected(int index) throws InterruptedException {
		List<WebElement> options = driver.findElementsByClassName("RadioButton");
		return options.get(index).isSelected();
	}

	public boolean is_loggerList_selected(int i) throws InterruptedException, IOException {

		List<WebElement> VRTList = driver.findElementByAccessibilityId("printLoggersGrid")
				.findElements(By.className("GridViewItem"));

		// clickOn(VRTList.get(i));
		return VRTList.get(i).isSelected();

	}

}
