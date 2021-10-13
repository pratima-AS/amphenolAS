/**
* @author ruchika.behura
*
*/

package com.vrt.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vrt.base.BaseClass;
import com.vrt.utility.TestUtilities;

public class NewEquipmentCreation_Page extends BaseClass {

	// NewEquipmentCreation_Page Element definition

	WebElement SerialNo = null;
	WebElement ModelNumber = null;
	WebElement EquipmentTypeUMDropDown = null;
	WebElement EquipmentSaveButton = null;
	WebElement backBtn = null;
	WebElement ID = null;
	WebElement ManufacturingCal_Date = null;
	WebElement ManufacturingCal_Due_Date = null;
	WebElement EquipmentImage_UploadButton = null;
	WebElement Equipment_Camera_UploadButton = null;
	WebElement EquipmentImage_Slot = null;
	WebElement ClearButton = null;

	private void initElements() {
		SerialNo = driver.findElementByAccessibilityId("EquipmentIDTextBox");

		ModelNumber = driver.findElementByAccessibilityId("ModelNumberTextBox");

		EquipmentTypeUMDropDown = driver.findElementByAccessibilityId("EquipmentTypeComboBox");

		ManufacturingCal_Date = driver.findElementByAccessibilityId("ManufacturingCalDateDatePicker");

		ManufacturingCal_Due_Date = driver.findElementByAccessibilityId("lstCAlDate");

		ID = driver.findElementByAccessibilityId("AssetNameTextBox");

		EquipmentImage_UploadButton = driver.findElementByAccessibilityId("EquipmentImageUploadButton");
		Equipment_Camera_UploadButton = driver.findElementByAccessibilityId("EquipmentCameraUploadButton");
		EquipmentImage_Slot = driver.findElementByAccessibilityId("EquipmentImage");

		EquipmentSaveButton = driver.findElementByName("Save");
		ClearButton = driver.findElementByAccessibilityId("CancelButton");

		backBtn = driver.findElementByAccessibilityId("ArrowGlyph");

	}

	NewEquipmentCreation_Page() throws IOException {
		super();
		initElements();
	}

	// Release memory
	public void resetWebElements() {
		SerialNo = null;
		ModelNumber = null;
		EquipmentTypeUMDropDown = null;
		EquipmentSaveButton = null;
		backBtn = null;
		ID = null;
		ManufacturingCal_Date = null;
		ManufacturingCal_Due_Date = null;
		EquipmentImage_UploadButton = null;
		Equipment_Camera_UploadButton = null;
		EquipmentImage_Slot = null;
		ClearButton = null;
	}

	/*----------------------
	Methods of Equipment Page
	------------------------*/
	public void enterSerialNo(String NewUID) {
		ClearText(SerialNo);
		enterText(SerialNo, NewUID);
	}

	public String Fetch_SerialNo_Txt() {
		return FetchText(SerialNo);
	}

	// ModelNumber
	public String Fetch_ModelNumber_Txt() {
		return FetchText(ModelNumber);
	}

	// Etype
	public String Fetch_EquipmentType_Txt() {
		return FetchText(EquipmentTypeUMDropDown);
	}

	// ID
	public String Fetch_ID() {
		return FetchText(ID);
	}

	// enter Serial Number
	public void enterSN(String SN) {
		ClearText(SerialNo);
		enterText(SerialNo, SN);
	}

	// enter NewModel Number

	public void enterNewModelNumber(String MNum) {
		ClearText(ModelNumber);
		enterText(ModelNumber, MNum);
	}

	// Enter Equipment ID

	public void enter_ID(String id) {
		ClearText(ID);
		enterText(ID, id);
	}

	// Is EquipmentID visible
	public boolean IsSerialNo_Visible() {
		return IsElementVisibleStatus(SerialNo);
	}

	// Is ModelNumber visible
	public boolean IsModelNumber_Visible() {
		return IsElementVisibleStatus(ModelNumber);
	}

	// Is EquipmentTypeUMDropDown visible
	public boolean IsEquipmentType_DropDown_Visible() {
		return IsElementVisibleStatus(EquipmentTypeUMDropDown);
	}

	// fetch the text from EquipmentTypeUMDropDown

	public String Selected_EquipmentType_text() {
		return FetchText(EquipmentTypeUMDropDown);
	}

	// Is AssetNameTextBoxn visible
	public boolean Is_IDTextBox_Visible() {
		return IsElementVisibleStatus(ID);
	}

	// Is ManufacturingCal_DueDate visible
	public boolean IsManufacturingCal_Date_Visible() {
		return IsElementVisibleStatus(ManufacturingCal_Date);
	}

	// Is Equipment_Camera_UploadButton visible
	public boolean IsEquipment_Camera_UploadButton_Visible() {
		return IsElementVisibleStatus(Equipment_Camera_UploadButton);
	}

	// Is EquipmentImage_Slot visible
	public boolean IsEquipmentImage_Slot_Visible() {
		return IsElementVisibleStatus(EquipmentImage_Slot);
	}

	// click on EquipmentImage_Slot

	public void EquipmentImage_Slot_Btn() {
		clickOn(EquipmentImage_Slot);
	}

	// Is EquipmentImage_UploadButton visible
	public boolean IsEquipmentImage_UploadButton_Visible() {
		return IsElementVisibleStatus(EquipmentImage_UploadButton);
	}

	// click on EquipmentImage_UploadButton

	public void click_EquipmentImage_UploadButton() {
		clickOn(EquipmentImage_UploadButton);
	}

	public void Capture_ImgButton1(String Img_Name) throws IOException {
		TestUtilities tu = new TestUtilities();
		WebElement EquipmentImage = driver.findElementByAccessibilityId("EquipmentImage");
		tu.capture_element_screenshot(driver, EquipmentImage, "TestData", Img_Name);

	}

	// PopupChangeImageButton
	public void click_ImageEditbtn() {
		WebElement PopupChangeImageButton = driver.findElementByAccessibilityId("PopupChangeImageButton");
		clickOn(PopupChangeImageButton);
	}

	// PopupRemoveImageButton
	public void click_RemoveImagebtn() {
		WebElement PopupRemoveImageButton = driver.findElementByAccessibilityId("PopupRemoveImageButton");
		clickOn(PopupRemoveImageButton);
	}

	// click on EquipmentImage_CameraButton

	public void click_EquipmentCamera_UploadButton() {
		clickOn(Equipment_Camera_UploadButton);
	}

	// close camera btn visible

	public boolean is_CloseCamBtn_Visible() {
		WebElement closecamera = driver.findElementByName("Close Camera");
		return IsElementVisibleStatus(closecamera);
	}

	// click close camera
	public void close_camera() {
		WebElement closecamera = driver.findElementByName("Close Camera");
		clickOn(closecamera);
	}

	// Is EquipmentSaveButton visible
	public boolean IsEquipmentSaveButton_Visible() {
		return IsElementVisibleStatus(EquipmentSaveButton);
	}

	// Is ClearButton visible

	public boolean IsClearButton_Visible() {
		return IsElementVisibleStatus(ClearButton);
	}

	// ClearButton click
	public void click_ClearButton() {

		clickOn(ClearButton);
	}

	// Select Equipment Type

	public void select_EquipmentType(String Etype) throws InterruptedException {
		// System.out.println(Etype);
		clickOn(EquipmentTypeUMDropDown);
		Thread.sleep(2000);

		WebElement EPSelect = driver.findElementByName("Select");
		// WebElement EPValidator = driver.findElementByName("Validator");
		WebElement EPVRTLogger = driver.findElementByName("VRT Logger");
		// WebElement EPAVS = driver.findElementByName("AVS");
		WebElement EPIRTD = driver.findElementByName("IRTD");
		// WebElement EPBath = driver.findElementByName("Calibration Bath");
		// WebElement EPBaseStation = driver.findElementByName("Base Station");

		if (Etype.equals(EPSelect.getText())) {
			clickOn(EPSelect);
			Thread.sleep(500);
		} else if (Etype.equals(EPVRTLogger.getText())) {
			clickOn(EPVRTLogger);
			Thread.sleep(500);
		} else if (Etype.equals(EPIRTD.getText())) {
			clickOn(EPIRTD);
			Thread.sleep(500);
		} else {
			System.out.println("Incorrect Equipment selected or incorrect text given as input");
		}

	}

	public void click_EquipmentType_DD() {
		clickOn(EquipmentTypeUMDropDown);
	}

	// fetch the equipment type
	public String EquipmentType_options(int index) {

		List<WebElement> Etype = driver.findElementByAccessibilityId("EquipmentTypeComboBox")
				.findElements(By.className("ComboBoxItem"));
		return FetchText(Etype.get(index));
	}

	// Click on Save button
	public void ClickSaveButton() throws InterruptedException {
		clickOn(EquipmentSaveButton);
		Thread.sleep(1000);
	}
//

	// Login Pop up presence
	public boolean UserLoginPopupVisible() throws InterruptedException {
		WebElement LgInPopup = driver.findElementByName("Enter User Credentials");
		return IsElementVisibleStatus(LgInPopup);
	}

	// Enter Mandatory fields and create Equipment
	public void EqipCreation_MandatoryFields(String Etype, String ESN, String EMN) throws InterruptedException {
		select_EquipmentType(Etype);
		enterSN(ESN);
		enterNewModelNumber(EMN);
		ClickSaveButton();
	}

	public void EqipCreation_WithoutClickingSaveBtn(String Etype, String ESN, String EMN) throws InterruptedException {
		select_EquipmentType(Etype);
		enterSN(ESN);
		enterNewModelNumber(EMN);
	}

	// Enter Mandatory fields and create Equipment
	public void EqipCreation(String Etype, String ESN, String EMN, String id) throws InterruptedException {
		select_EquipmentType(Etype);
		enterSN(ESN);
		enterNewModelNumber(EMN);
		enter_ID(id);
		ClickSaveButton();
	}

	public void EqipCreation_VRTDLogger(String Etype, String ESN, String EMN) throws InterruptedException {
		// select_EquipmentType(Etype);
		// Thread.sleep(1000);
		// selectAnylogger_From_LoggerTypeComboBox(1);
		enterSN(ESN);
		enterNewModelNumber(EMN);

		ClickSaveButton();
	}

	// Enter Mandatory fields and create BS Equipment
	public void BaseStation_EqipCreation_MandatoryFields(String Etype, String EID, String EMnum, String McAddress)
			throws InterruptedException {
		select_EquipmentType(Etype);
		enterSerialNo(EID);
		enterNewModelNumber(EMnum);
		WebElement BS_McAdd = driver.findElementByAccessibilityId("MacAddressTextBox");
		ClearText(BS_McAdd);
		enterText(BS_McAdd, McAddress);
		ClickSaveButton();
	}

	// Click on back button
	public EquipmentHubPage ClickBackBtn() throws InterruptedException, IOException {
		clickOn(backBtn);
		boolean status = false;
		try {
			status = driver.findElementByName("Yes").isDisplayed();
			clickOn(driver.findElementByName("Yes"));
		} catch (Exception e) {
			System.out.println("Alert message is not available");
		}

		return new EquipmentHubPage();
	}

	// is LoggerTypeComboBox visible

	public boolean is_LoggerTypeComboBox_Visible() {
		WebElement LoggerTypeComboBox = driver.findElementByAccessibilityId("LoggerTypeComboBox");
		return IsElementVisibleStatus(LoggerTypeComboBox);
	}

	// click on LoggerTypeComboBox

	public void click_LoggerTypeComboBox() {
		WebElement LoggerTypeComboBox = driver.findElementByAccessibilityId("LoggerTypeComboBox");
		clickOn(LoggerTypeComboBox);
	}

	// Select any logger

	public void selectAnylogger_From_LoggerTypeComboBox(int index) {

		WebElement LT = driver.findElementByAccessibilityId("LoggerTypeComboBox");
		clickOn(LT);
		List<WebElement> options = driver.findElementByAccessibilityId("LoggerTypeComboBox")
				.findElements(By.className("ComboBoxItem"));

		clickOn(options.get(index));
	}

//Fetch text of the options from LoggerTypeComboBox

	public String Fetch_TxtofOptions_fromLoggerTypeCombobox(int index) {
		List<WebElement> options = driver.findElementByAccessibilityId("LoggerTypeComboBox")
				.findElements(By.className("ComboBoxItem"));

		return FetchText(options.get(index));
	}

	// Fetch SelectedText from LoggerTypeComboBox

	public String LoggerTypeComboBox_SelectedValueTxt()

	{
		WebElement LoggerTypeComboBox = driver.findElementByAccessibilityId("LoggerTypeComboBox");
		return FetchText(LoggerTypeComboBox);
	}

//Is Last Verification Date visible
	public boolean is_LastVerificationDate_Visible() {
		WebElement LastVerificationDate = driver.findElementByName("Last Verification Date");
		return IsElementVisibleStatus(LastVerificationDate);
	}

	// Fetch Text from Last Verification date field

	public String FetchTxt_LastVerificationDate() {

		List<WebElement> LastVerificationDate = driver.findElementByAccessibilityId("lastVerDate")
				.findElements(By.className("TextBlock"));
		return FetchText(LastVerificationDate.get(0));

	}

	// click on Last Verification date field
	public void click_LastVerificationdate_field() {
		WebElement LastVerificationDate = driver.findElementByAccessibilityId("lastVerDate");
		clickOn(LastVerificationDate);

	}

	// click on ManufacturingCalDueDate

	public void ClickOn_ManufacturingCalDate() {

		clickOn(ManufacturingCal_Date);
	}

	// click on ManufacturingCalDueDate

	public void ClickOn_ManufacturingCal_Due_Date() {

		clickOn(ManufacturingCal_Due_Date);
	}

	// is date picker field is avilable
	public boolean Is_SelectDate_Visible() {
		WebElement selectDate = driver.findElementByName("Select Date");
		return IsElementVisibleStatus(selectDate);
	}

	// Fetch Text from ManufacturingCal_Date

	public String FetchTxt_From_ManufacturingCalDate() {

		List<WebElement> List = driver.findElementByAccessibilityId("ManufacturingCalDateDatePicker")
				.findElements(By.className("TextBlock"));
		return FetchText(List.get(0));

	}

	// Fetch Text from ManufacturingCal_Due_Date

	public String FetchTxt_From_ManufacturingCal_DueDate() {
		List<WebElement> List = driver.findElementByAccessibilityId("lstCAlDate")
				.findElements(By.className("TextBlock"));
		return FetchText(List.get(0));
		// return FetchText(ManufacturingCal_Due_Date);
	}

	// Verify ManufacturingCal_Due_Date is Enabled
	public boolean is_ManufacturingCal_DueDate_Enabled() {

		return ManufacturingCal_Due_Date.isEnabled();
	}

	public void selectManufacturingCalDate_Yr(String Yr) throws InterruptedException {

		// click_AsstValidationDatePkr_Btn();
		Actions ac = new Actions(driver);
		String Date = FetchTxt_From_ManufacturingCalDate();
		String expDate = Date.substring(Date.length() - 4);
		// String[] expDate = Date.split("/");
		// System.out.println(expDate);

		while (!expDate.equals(Yr)) {
			ClickOn_ManufacturingCalDate();
			Thread.sleep(500);
			ac.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN)
					.build().perform();
			Thread.sleep(500);
			Date = FetchTxt_From_ManufacturingCalDate();
			expDate = Date.substring(Date.length() - 4);
			// System.out.println(expDate);
		}
	}

	public void selectLastVerificationDate_Yr(String Yr) throws InterruptedException {

		// click_AsstValidationDatePkr_Btn();
		Actions ac = new Actions(driver);
		String Date = FetchTxt_LastVerificationDate();
		String expDate = Date.substring(Date.length() - 4);
		// String[] expDate = Date.split("/");
		// System.out.println(expDate);

		while (!expDate.equals(Yr)) {
			click_LastVerificationdate_field();
			Thread.sleep(500);
			ac.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN)
					.build().perform();
			Thread.sleep(500);
			Date = FetchTxt_LastVerificationDate();
			expDate = Date.substring(Date.length() - 4);
			// System.out.println(expDate);
		}
	}
}
