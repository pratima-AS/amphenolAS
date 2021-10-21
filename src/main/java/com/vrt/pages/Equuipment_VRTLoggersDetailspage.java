/**
* @author ruchika.behura
*
*/

package com.vrt.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vrt.utility.TestUtilities;
import com.vrt.base.BaseClass;

public class Equuipment_VRTLoggersDetailspage extends BaseClass {

	Equipment_IRTDHubPage IRTDHubPage;
	TestUtilities tu = new TestUtilities();

	// EquipmentHubPage Element definition

	WebElement Documents_Tile = null;
	WebElement serialNo = null;
	WebElement Copytodrive = null;
	WebElement EquipmentID = null;
	WebElement Browse_btn = null;
	WebElement EquipmentImage = null;
	WebElement Backbtn = null;
	WebElement Camera_Btn = null;
	WebElement DeleteEquipments = null;

	WebElement DocsTile_Count = null;

	WebElement ModelNumber = null;
	WebElement ManufacturingCalDate = null;
	WebElement ManufacturingCalDueDate = null;
	WebElement LastVerificationdate = null;
	WebElement SaveButton = null;
	WebElement ClearButton = null;
	WebElement VerificationsTile = null;
	WebElement ReportsTile = null;
	WebElement DocumentsTile = null;
	WebElement HistoryTile = null;

	// Initializing the elements
	private void initElements() {

		Copytodrive = driver.findElementByAccessibilityId("CopyDocumentToDrive");
		Documents_Tile = driver.findElementByAccessibilityId("DocumentsButton");

		DocsTile_Count = driver.findElementByAccessibilityId("DocumentsCountTextBlock");
		Backbtn = driver.findElementByAccessibilityId("ArrowGlyph");
		ModelNumber = driver.findElementByAccessibilityId("ModelNumberTextBox");
		ManufacturingCalDate = driver.findElementByAccessibilityId("CalibrationDateTextBlockView");
		ManufacturingCalDueDate = driver.findElementByAccessibilityId("CalibrationDueDateTextBlockView");
		LastVerificationdate = driver.findElementByAccessibilityId("VerificationDateTextBlockView");
		SaveButton = driver.findElementByAccessibilityId("SaveButton");
		ClearButton = driver.findElementByAccessibilityId("CancelButton1");
		EquipmentID = driver.findElementByAccessibilityId("AssetNameTextBox");
		serialNo = driver.findElementByAccessibilityId("VRTEquipmentIDTextBlock");
		Camera_Btn = driver.findElementByAccessibilityId("EquipmentCameraUploadButton");
		Browse_btn = driver.findElementByAccessibilityId("EquipmentImageUploadButton");
		EquipmentImage = driver.findElementByAccessibilityId("EquipmentImage");
		DeleteEquipments = driver.findElementByAccessibilityId("DeleteEquipmentsButton");
		VerificationsTile = driver.findElementByAccessibilityId("VerificationsButton");
		ReportsTile = driver.findElementByAccessibilityId("ReportsButton");
		DocumentsTile = driver.findElementByAccessibilityId("DocumentsButton");
		HistoryTile = driver.findElementByAccessibilityId("HistoryButton");
	}

	Equuipment_VRTLoggersDetailspage() throws IOException {
		super();
		initElements();
	}

	// Release memory
	public void resetWebElements() {

		ManufacturingCalDate = null;
		ManufacturingCalDueDate = null;
		LastVerificationdate = null;
		ModelNumber = null;
		VerificationsTile = null;
		ReportsTile = null;
		DocumentsTile = null;
		HistoryTile = null;
		DocsTile_Count = null;

		Documents_Tile = null;
		Copytodrive = null;
		EquipmentID = null;
		Backbtn = null;
		Browse_btn = null;
		EquipmentImage = null;
		Camera_Btn = null;
		DeleteEquipments = null;
		serialNo = null;
		SaveButton = null;
		ClearButton = null;
	}

	/*********************
	 * //Page related Method definitions
	 *********************/

	public void Click_VRTVerification_studyFile(String SN) throws InterruptedException, IOException {

		List<WebElement> VRTVerificationList = driver
				.findElementByName("VRT.DataModule.Asset.ViewModels.ActivityItemViewModel")
				.findElements(By.className("ListViewItem"));

		// Loop for the different serial number created
		for (int i = 0; i < VRTVerificationList.size(); i++) {
			// System.out.println("serial number : " + IrtdList.get(i).getText());

			List<WebElement> VRT_VerificationInfoList = VRTVerificationList.get(i)
					.findElements(By.className("TextBlock"));

			for (int j = 0; j < VRT_VerificationInfoList.size(); j++) {
				// System.out.println("AssetTileInfo: "+IRTDTileInfoList.get(j).getText());

				if (VRT_VerificationInfoList.get(j).getText().contains(SN)) {
					clickOn(VRT_VerificationInfoList.get(j));
					Thread.sleep(1000);
					break;
				}
			}
		}
	}

	// VrtLoggerDetails_Title availabe

	public boolean is_VrtLoggerDetailsTitle_Available() {
		WebElement VrtLoggerDetails_Title = driver.findElementByName(" - Details");
		return IsElementVisibleStatus(VrtLoggerDetails_Title);

	}

	// fetch the Equip ID

	public String fetch_ID() {
		return FetchText(EquipmentID);
	}

	// Fetch text from ManufacturingCalDate

	public String fetch_ManufacturingCalDate() {
		return FetchText(ManufacturingCalDate);
	}

	// Fetch text from ManufacturingCalDueDate

	public String fetch_ManufacturingCalDueDate() {
		return FetchText(ManufacturingCalDueDate);
	}

	// is model no visible
	public String fetch_ModelNumber() {
		return FetchText(ModelNumber);
	}

	// is serialNo visible
	public boolean is_serialNo_Visible() {
		return IsElementVisibleStatus(serialNo);
	}

	// is serialNo visible
	public boolean is_ID_Visible() {
		return IsElementVisibleStatus(EquipmentID);
	}

// is ManufacturingCalDate visible

	public boolean is_ManufacturingCalDate_Visible() {
		return IsElementVisibleStatus(ManufacturingCalDate);
	}

	// is ManufacturingCalDueDate visible

	public boolean is_ManufacturingCalDueDate_Visible() {
		return IsElementVisibleStatus(ManufacturingCalDueDate);
	}

	// LastVerificationdate
	public boolean is_LastVerificationdate_Visible() {
		return IsElementVisibleStatus(LastVerificationdate);
	}

	// Browse_btn
	public boolean is_Browse_btn_Visible() {
		return IsElementVisibleStatus(Browse_btn);
	}

	// Camera_Btn
	public boolean is_Camera_Btn_Visible() {
		return IsElementVisibleStatus(Camera_Btn);
	}

	// is saveBtn visible
	public boolean is_SaveBtn_Visible() {
		return IsElementVisibleStatus(SaveButton);
	}

	// is ClearButton visible
	public boolean is_ClearBtn_Visible() {
		return IsElementVisibleStatus(ClearButton);
	}

	// Back_btn

	public boolean is_Backbtn_Visible() {
		return IsElementVisibleStatus(Backbtn);
	}

	// DeleteBtn
	public boolean is_DeleteBtn_Visible() {
		return IsElementVisibleStatus(DeleteEquipments);
	}

	// VerificationsTile

	public boolean is_VerificationsTile_Visible() {
		return IsElementVisibleStatus(VerificationsTile);
	}
	// ReportsTile

	public boolean is_ReportsTile_Visible() {
		return IsElementVisibleStatus(ReportsTile);
	}

	// DocumentsTile

	public boolean is_DocumentsTile_Visible() {
		return IsElementVisibleStatus(DocumentsTile);
	}

	// is HistoryTile visible

	public boolean is_HistoryTile_Visible() {
		return IsElementVisibleStatus(HistoryTile);
	}

	// Click on Delete Icon against any study/reports files
	public void Click_DeleteBtn() throws InterruptedException {
		WebElement DeleteBtn = driver.findElementByAccessibilityId("DeleteButton");
		clickOn(DeleteBtn);
		Thread.sleep(1000);
	}

	// Click on reports tile
	public void Click_reporttile() {
		WebElement Reports_Button = driver.findElementByAccessibilityId("ReportsButton");
		clickOn(Reports_Button);
	}

	// Click on Documents tile
	public void Click_Documentstile() {
		clickOn(Documents_Tile);
	}

	// UploadDocumentsButton
	public void Upload_Documents(String filename) throws AWTException, IOException, InterruptedException {

		WebElement UploadDocumentsButton = driver.findElementByAccessibilityId("UploadDocumentsButton");
		clickOn(UploadDocumentsButton);
		Thread.sleep(2000);
		// TestUtilities tu = new TestUtilities();
		tu.uploadDoc("uploadnote.txt");

	}

	// Click the Copy to drive button
	public void click_CopyToDrive() throws InterruptedException {
		clickOn(Copytodrive);
		Thread.sleep(2000);
	}

	// Select any folder present in the resource folder TestData for copying any
	// eqp. files
	public void selectFolder_CopyToDrive(String folderName) throws AWTException, IOException, InterruptedException {
		click_CopyToDrive();
		// Select any folder present in the resource folder TestData
		tu.selectFolder(folderName);

	}

	// Modify / Enter EquipmentName
	public void Enter_EquipmentID(String EN) {

		clickOn(EquipmentID);
		ClearText(EquipmentID);
		enterText(EquipmentID, EN);
	}

	// Delete Equipment
	public void clickDeleteEquipmentIcon() {
		clickOn(DeleteEquipments);
	}

	// Login Popup presence
	public boolean UserLoginPopupVisible() throws InterruptedException {
		WebElement LgInPopup = driver.findElementByName("Enter User Credentials");
		return IsElementVisibleStatus(LgInPopup);
	}

	// Save button

	public void Click_Save() {
		WebElement Save_Button = driver.findElementByAccessibilityId("SaveButton");
		clickOn(Save_Button);
	}

	// Click on yes button for delete confirmation
	public void click_Alrt_YesBtn() {
		WebElement alrtMeg_YesBtn = driver.findElementByAccessibilityId("Button1");
		clickOn(alrtMeg_YesBtn);
	}

	// Fetch the alert message when Supervisor not have the privilege to edit the
	// equipments
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

	public String get_text_DeleteAst_popup() {
		WebElement deleteAsset_popup = driver.findElementByAccessibilityId("Content_String");
		return deleteAsset_popup.getAttribute("Name");
	}

	public Equipment_VRTLoggerHubPage click_Back_btn() throws IOException {
		clickOn(Backbtn);
		return new Equipment_VRTLoggerHubPage();
	}

	// fetch the Equip ID

	public String fetch_eqipID() {
		return FetchText(EquipmentID);
	}

	// click on Browse_btn

	public void click_Browse_btn() {
		clickOn(Browse_btn);
	}

	// click on Capture_EqpImg

	public void Capture_EqpImg(String Img_Name) throws IOException {

		TestUtilities tu = new TestUtilities();
		tu.capture_element_screenshot(driver, EquipmentImage, "TestData", Img_Name);

	}

	// click Backbtn
	public Equipment_VRTLoggerHubPage Backbtn() throws IOException {
		clickOn(Backbtn);
		return new Equipment_VRTLoggerHubPage();
	}

	// Click Camera Image button
	public void click_Img_Camera_Btn() throws InterruptedException {
		clickOn(Camera_Btn);
		Thread.sleep(5000);
	}

	public void capture_Camera_Img() throws InterruptedException {
		click_Img_Camera_Btn();

		WebElement Click_Camera_Wdw_capture_Btn = driver.findElementByName("Take Photo");
		clickOn(Click_Camera_Wdw_capture_Btn);
		Thread.sleep(500);
		WebElement Click_Camera_Wdw_Accept_Btn = driver.findElementByName("Accept");
		clickOn(Click_Camera_Wdw_Accept_Btn);
		Thread.sleep(500);

	}
	// Delete Equipment Image

	public void click_DeleteEquipImage() throws InterruptedException {
		clickOn(EquipmentImage);
		Thread.sleep(500);
		WebElement RemoveImageButton = driver.findElementByAccessibilityId("PopupRemoveImageButton");
		clickOn(RemoveImageButton);

	}

	// click on DocumentsButton

	public void click_DocumentsButton() throws InterruptedException {
		clickOn(Documents_Tile);

	}
	// UploadDocumentsButton

	public void click_UploadDocsBtn() throws InterruptedException {
		WebElement UploadDocs_Btn = driver.findElementByAccessibilityId("UploadDocumentsButton");
		clickOn(UploadDocs_Btn);
		Thread.sleep(2000);
	}

	// Get the Docs count data form the Docs tile
	public String docsTile_countdata() {
		return FetchText(DocsTile_Count);
	}

	public void Select_DocName(String DocName) throws IOException {
		List<WebElement> SetupList = driver.findElementByClassName("ListView")
				.findElements(By.className("ListViewItem"));

		for (int i = 0; i < SetupList.size(); i++) {

			List<WebElement> DocTileInfoList = SetupList.get(i).findElements(By.className("TextBlock"));

			for (int j = 0; j < DocTileInfoList.size(); j++) {

				String st = DocTileInfoList.get(j).getText();
				if (st.equals(DocName)) {
					DocTileInfoList.get(j).click();

					break;
				} else {
					Actions ac = new Actions(driver);
					ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				}

			}
		}

	}

	// Click_Print_Button

	public void Click_Print_Button() throws AWTException, IOException, InterruptedException {
		WebElement Print_Button = driver.findElementByAccessibilityId("ViewButton");
		Thread.sleep(2000);
		clickOn(Print_Button);
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

	// click on Delete_Button
	public void Click_Delete_DocumentButton() throws AWTException, IOException, InterruptedException {
		WebElement Delete_Button = driver.findElementByAccessibilityId("DeleteButton");
		Thread.sleep(2000);
		clickOn(Delete_Button);
	}

	// ckick on yes button in delete windows popup

	public void Click_Yes_DeleteWindowPopup() throws AWTException, IOException, InterruptedException {
		WebElement Delete_Button = driver.findElementByAccessibilityId("Button1");
		Thread.sleep(2000);
		clickOn(Delete_Button);
	}

	// HistoryButton

	public void Click_HistoryButton() throws IOException {
		WebElement Historybtn = driver.findElementByAccessibilityId("HistoryButton");
		clickOn(Historybtn);
	}

	// Is PrintButton visible when user clicked on History tab
	public boolean is_PrintButton_visible() throws AWTException, IOException, InterruptedException {
		WebElement Print_Button = driver.findElementByAccessibilityId("PrintButton");
		return IsElementVisibleStatus(Print_Button);
	}

	// click on Print_Button
	public void clickon_PrintBtn() throws AWTException, IOException, InterruptedException {
		WebElement Print_Button = driver.findElementByAccessibilityId("PrintButton");
		clickOn(Print_Button);
	}

}
