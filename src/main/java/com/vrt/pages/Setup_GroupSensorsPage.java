package com.vrt.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;
import com.vrt.utility.TestUtilities;

public class Setup_GroupSensorsPage extends BaseClass {

	TestUtilities tu = new TestUtilities();

	// Group Sensor page element variable declaration definition
	WebElement GrpSensorPageTitle = null;
	WebElement GrpWiring_Btn = null;
	WebElement NxtBtn = null;
	WebElement Back_Button = null;
	WebElement SetupHeaderTextBlock = null;
	WebElement NewGroupButton = null;
	WebElement GroupsListButton = null;
	WebElement DeleteGroupButton = null;
	WebElement PreviousButton = null;
	WebElement CalculationsTab = null;
	WebElement EditGrpHdrTextBlock = null;

	private void initializeEelements() {
		GrpSensorPageTitle = driver.findElementByName("Group Sensors");
		GrpWiring_Btn = driver.findElementByAccessibilityId("GroupsGraphButton");
		NxtBtn = driver.findElementByAccessibilityId("NextButton");
		Back_Button = driver.findElementByAccessibilityId("GoButton");
		SetupHeaderTextBlock = driver.findElementByAccessibilityId("SetupHeaderTextBlock");
		NewGroupButton = driver.findElementByAccessibilityId("NewGroupButton");
		GroupsListButton = driver.findElementByAccessibilityId("GroupsListButton");
		DeleteGroupButton = driver.findElementByAccessibilityId("DeleteGroupButton");
		PreviousButton = driver.findElementByAccessibilityId("PreviousButton");
		CalculationsTab = driver.findElementByAccessibilityId("NextButton");
		EditGrpHdrTextBlock = driver.findElementByAccessibilityId("EditGrpHdrTextBlock");
	}

	Setup_GroupSensorsPage() throws IOException {
		super();
		initializeEelements();
	}

	// Release memory
	public void resetWebElements() {
		GrpSensorPageTitle = null;
		GrpWiring_Btn = null;
		NxtBtn = null;
		Back_Button = null;
		SetupHeaderTextBlock = null;
		NewGroupButton = null;
		GroupsListButton = null;
		DeleteGroupButton = null;
		PreviousButton = null;
		CalculationsTab = null;
		EditGrpHdrTextBlock = null;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Group Sensor Config Page methods
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// Check the presence of Group Sensor page
	public boolean GrpsensorPage_state() {
		return IsElementVisibleStatus(GrpSensorPageTitle);
	}

	// Check the presence of Default Group btn

	public boolean NewGroup_Btn_state() {
		return IsElementEnabledStatus(NewGroupButton);
	}

	// Check if New Group btn is enable or not

	public boolean NewGroup_Btn_IsEnable() {
		return IsElementEnabledStatus(NewGroupButton);
	}

	// NewGroupButton is displayed

	public boolean NewGroup_Btn_State() {
		return IsElementVisibleStatus(NewGroupButton);
	}

	// Check the presence of Default Group btn

	public boolean DefaultGrp_Btn_state() {
		WebElement DefaultGrp_Btn = driver.findElementByAccessibilityId("DefaultGroupButton");

		return IsElementVisibleStatus(DefaultGrp_Btn);
	}

	// Check the presence of Group List btn

	public boolean GroupsList_Btn_state() {
		return IsElementVisibleStatus(GroupsListButton);
	}

	// Check the presence of GrpWiring btn

	public boolean GroupWiring_Btn_state() {
		return IsElementVisibleStatus(GrpWiring_Btn);
	}

//Check the enable of  Group Wiring  btn

	public boolean GroupWiring_Btn_IsEnable() {
		return IsElementEnabledStatus(GrpWiring_Btn);
	}

	// Check the presence of Delete btn

	public boolean DeleteGroup_Btn_state() {
		return IsElementVisibleStatus(DeleteGroupButton);
	}

	// Click on delete group btn
	public void click_DeleteGroup_Btn() {
		clickOn(DeleteGroupButton);
	}

	// click on yes btn to delete the group
	public void DeleteGroup_Yes() {
		clickOn(DeleteGroupButton);
		WebElement Yesbtn = driver.findElementByName("Yes");
		clickOn(Yesbtn);
	}

	// click on yes btn for the sensor not assigned message while moving to
	// Calculation page after deleting a group
	public Setup_CalculationsPage click_YesBtn_AlertMsg_for_DeleteGroup_SensorUnassigned()
			throws IOException, InterruptedException {
		clickOn(CalculationsTab);
		WebElement Yesbtn = driver.findElementByName("Yes");
		clickOn(Yesbtn);
		return new Setup_CalculationsPage();
	}

	// Check the presence of back btn
	public boolean SensorConfiguration_Tab_state() {
		return IsElementVisibleStatus(PreviousButton);
	}

	// Check the presence of Default Group btn

	public boolean Calculationn_Tab_state() {
		return IsElementVisibleStatus(CalculationsTab);
	}

	// Check the presence of EditGrpHdrTextBlock
	public boolean EditGrpHdr_state() {
		return IsElementVisibleStatus(EditGrpHdrTextBlock);
	}

	// Get the SetupHeader title text
	public String get_Setup_titletext() {
		return FetchText(SetupHeaderTextBlock);
	}

	// Get the Group Sensor page title text
	public String get_GrpsensorPage_titletext() {
		return FetchText(GrpSensorPageTitle);
	}

	// Click the Default Group button
	public void click_DfltGrp_Btn() throws InterruptedException {
		WebElement DefaultGrp_Btn = driver.findElementByAccessibilityId("DefaultGroupButton");

		clickOn(DefaultGrp_Btn);
		Thread.sleep(1000);
	}

	// Click the Wiring Group button
	public void click_GrpWiring_Btn() throws InterruptedException {
		clickOn(GrpWiring_Btn);
		Thread.sleep(1000);
	}

	// Click the Default Temperature Group
	public void click_Dflt_TempGrp() {
		WebElement Dflt_TempGrp = driver.findElementByName("Temperature");
		clickOn(Dflt_TempGrp);
	}

	// Click the Default Pressure Group
	public void click_Dflt_PrGrp() {
		WebElement Dflt_PrGrp = driver.findElementByName("Pressure");
		clickOn(Dflt_PrGrp);
	}

	// Click the Wiring img place holder
	public void Click_WiringImgHoldr(int imgHldrNo) throws InterruptedException {
		List<WebElement> WiringImgHoldrs = driver.findElementsByClassName("Image");
		// System.out.println(WiringImgHoldrs.size());
		WiringImgHoldrs.get(imgHldrNo).click();
		Thread.sleep(1000);
	}

	/*
	 * // Click Image edit icon for adding/replacing image public void
	 * Click_ImgHoldr1_EditBtn() throws InterruptedException { WebElement
	 * ImgHoldr_EditBtn = driver.findElementByAccessibilityId("ChangeImageBrowser");
	 * clickOn(ImgHoldr_EditBtn); }
	 */
	// Upload Image for the image holder
	public void select_Img(String filename) throws InterruptedException, AWTException {
		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(500);

		// enter the filename
		String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\" + filename;
		// System.out.println(filepath);
		alert.sendKeys(filepath);
		Thread.sleep(1000);

		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);

		// switch back
		// driver.switchTo().activeElement();
		clickOn(GrpSensorPageTitle);
	}

	public void upload_Images(String filename) throws AWTException, IOException, InterruptedException {

		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(1000);

		// enter the filename
		String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\" + filename;
		System.out.println(filepath);
		alert.sendKeys(filepath);
		Thread.sleep(1000);

		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

		// switch back
		driver.switchTo().activeElement();
	}

	// Click the Next button to move to Setup Calculations page
	public assetDetailsPage Click_BackBtn() throws InterruptedException, IOException {
		clickOn(Back_Button);
		WebElement Yesbtn = driver.findElementByName("Yes");
		clickOn(Yesbtn);
		return new assetDetailsPage();
	}

	// click on SensorConfig tab
	public Setup_SensorConfigPage Click_SensorConfigTab() throws InterruptedException, IOException {
		clickOn(PreviousButton);
		return new Setup_SensorConfigPage();
	}

	// click on SensorConfig tab
	public Setup_CalculationsPage Click_CalculationsTab() throws InterruptedException, IOException {
		clickOn(CalculationsTab);
		return new Setup_CalculationsPage();
	}

	// Do not enter anything in Group name field. Click on calculations
	public void Click_CalculationsTab_Alrt() throws InterruptedException, IOException {
		clickOn(CalculationsTab);

	}

	// edit icon should display for each group:

	public boolean EditGroupButton_state() {
		WebElement EditGroup_Btn = driver.findElementByAccessibilityId("EditGroupButton");

		return IsElementVisibleStatus(EditGroup_Btn);
	}

	// Enter Text while creating new group

	public void enter_groupname(String GN) {
		WebElement EditGroup_Btn = driver.findElementByAccessibilityId("EditGroupButton");
		clickOn(EditGroup_Btn);
		WebElement Entertxt = driver.findElementByAccessibilityId("txtEditTemp");
		enterText(Entertxt, GN);

	}

	// fetch the title of the group

	public String get_groupTitle(int index) {
		List<WebElement> Ttxt = driver.findElementByAccessibilityId("GroupsListBox")
				.findElements(By.className("TextBlock"));
		return Ttxt.get(index).getText();
	}

	// sensors list are displaying that were assigned under Temperature in group
	// Sensor screen

	public String Temp_Sensor_Txt() {
		List<WebElement> senrList = driver.findElementByClassName("GridViewItem")
				.findElements(By.className("TextBlock"));
		return FetchText(senrList.get(0));
	}

	// sensors list are displaying that were assigned under Pressure in group Sensor
	// screen

	public String Prs_Sensor_Txt() {
		List<WebElement> senrList = driver.findElementByClassName("GridViewItem")
				.findElements(By.className("TextBlock"));
		return FetchText(senrList.get(0));
	}
	// sensors list are displaying that were assigned under Humidity in group Sensor
	// screen

	public String Hmd_Sensor_Txt() {
		List<WebElement> senrList = driver.findElementByClassName("GridViewItem")
				.findElements(By.className("TextBlock"));
		return FetchText(senrList.get(0));
	}

	// Get count of temp sensors under temp group

	public int GroupSensorCount() {
		List<WebElement> senrList = driver.findElementByAccessibilityId("SensorGrid2")
				.findElements(By.className("GridViewItem"));
		return senrList.size();
	}

	// Group sensor count before clicking on the dafault group

	public int SensorCount() {
		List<WebElement> senrList = driver.findElementByAccessibilityId("SensorGrid1")
				.findElements(By.className("GridViewItem"));
		return senrList.size();
	}

	// Select any group
	public void Selectgroup(int Groupnumber) {
		List<WebElement> GroupList = driver.findElementByAccessibilityId("GroupsListBox")
				.findElements(By.className("ListBoxItem"));
		// System.out.println(GroupList.size());
		GroupList.get(Groupnumber).click();
	}

	// Count of group
	public int Countof_group() {
		List<WebElement> GroupList = driver.findElementByAccessibilityId("GroupsListBox")
				.findElements(By.className("ListBoxItem"));
		return GroupList.size();
	}

	// Verify the second group title is displaying , when user add more than 50
	// sensors and click default group
	public void Click_Temp_2_Group() {
		WebElement groupList = driver.findElementByName("Temperature_2");
		clickOn(groupList);

	}

	// Verify the second group title is displaying , when user add more than 50
	// sensors and click default group
	public void Click_Temp_1_Group() {
		WebElement groupList = driver.findElementByName("Temperature_1");
		clickOn(groupList);

	}

	// Click edit icon for temp group
	public void click_GroupEditIcon(int groupIndex) {
		List<WebElement> Edit = driver.findElementByAccessibilityId("GroupsListBox")
				.findElements(By.className("Image"));
		Edit.get(groupIndex).click();
	}

	// Sensor size when group is in edit mode
	public int sensorSelected_Count() {
		List<WebElement> snsrFields = driver.findElementByAccessibilityId("SensorGrid1")
				.findElements(By.className("GridViewItem"));

		for (int i = 0; i < snsrFields.size(); i++) {
			if (snsrFields.get(i).isSelected() == true) {
				// System.out.println("SELECTED");

				return snsrFields.size();

			}

		}
		return 0;
	}

	// Fetch_NonSelected_Sensorname when group is in edit mode
	public String Fetch_NonSelected_Sensorname() {

		List<WebElement> snsrFields = driver.findElementByAccessibilityId("SensorGrid1")
				.findElements(By.className("GridViewItem"));

		for (int i = 0; i < snsrFields.size(); i++) {
			if (snsrFields.get(i).isSelected() == false) {
				List<WebElement> TtxtList = snsrFields.get(i).findElements(By.className("TextBlock"));

				for (int j = 0; j < TtxtList.size(); j++) {
					String st = TtxtList.get(j).getText();
					// System.out.println(st);
					return st;

				}
			}
		}
		return null;

	}

//Search and click via sensor name

	public void Click_Sensors_ByName(String SensorName) throws IOException {
		List<WebElement> snsrFields = driver.findElementByAccessibilityId("SensorGrid1")
				.findElements(By.className("GridViewItem"));
		for (int i = 0; i < snsrFields.size(); i++) {
			List<WebElement> SensorTileInfoList = snsrFields.get(i).findElements(By.className("TextBlock"));

			for (int j = 0; j < SensorTileInfoList.size(); j++) {

				String st = SensorTileInfoList.get(j).getText();
				if (st.equals(SensorName)) {
					SensorTileInfoList.get(j).click();

					break;
				}
			}
		}
	}

	// Edit Temp group

	public void Edit_GrpTitle_Save(String GName) {
		List<WebElement> Fields = driver.findElementByAccessibilityId("GroupsListBox")
				.findElements(By.name(" Enter Text "));
		Fields.get(0).clear();
		Fields.get(0).sendKeys(GName);
		click_SaveBtn();
	}

	public void Edit_GrpTitle_DontClickSave(String TempName) {
		List<WebElement> TempFields = driver.findElementByAccessibilityId("GroupsListBox")
				.findElements(By.name(" Enter Text "));
		TempFields.get(0).clear();
		TempFields.get(0).sendKeys(TempName);
	}

	// Click on Save Btn

	public void click_SaveBtn() {
		WebElement SaveBtn_Field = driver.findElementByAccessibilityId("SaveSelectedGroupButton");
		clickOn(SaveBtn_Field);
	}

	// GS016-Verify that when selected more than 50 sensors for a group of same
	// sensor type in edit mode, a validation message is displayed that max 50
	// sensors are allowed for each group
	public void Click_Sensor51() {
		WebElement SensorsField = driver.findElementByName("51");
		clickOn(SensorsField);

	}

	// when clicked on the cancel button for groups in edit mode, will discard the
	// changes
	public void click_CancelBtn() {
		WebElement Cancel = driver.findElementByAccessibilityId("CancelSelectedGroupButton");
		clickOn(Cancel);

	}

	// NewGroupButton
	public void clickOn_NewGroupButton() {
		clickOn(NewGroupButton);

	}

	// Is save btn present

	public boolean IsSaveBtn_Visible() {
		WebElement Save_Btn = driver.findElementByAccessibilityId("SaveSelectedGroupButton");
		return IsElementVisibleStatus(Save_Btn);

	}

	// Is cancel btn present
	public boolean IsCncelBtn_Visible() {
		WebElement Cancel_Btn = driver.findElementByAccessibilityId("CancelSelectedGroupButton");
		return IsElementVisibleStatus(Cancel_Btn);

	}

//select single / multiple sensors
	public void clickon_Sensors(int count) {
		List<WebElement> snsrFields = driver.findElementByAccessibilityId("SensorGrid1")
				.findElements(By.className("GridViewItem"));
		for (int i = 0; i <= count; i++) {

			snsrFields.get(i).click();
		}
	}

	public void Click_MoreThan50Sensors() {
		List<WebElement> snsrFields = driver.findElementsByName("HMD");

		for (int i = 0; i < snsrFields.size(); i++) {
			snsrFields.get(i).click();
		}
	}

	// The list of sensors displayed below the groups on left pane in Group Wiring
	// page

	public boolean islistofsensors_Empty() {
		List<WebElement> snsrFields = driver.findElementByClassName("ScrollViewer")
				.findElements(By.className("TextBlock"));
		return snsrFields.isEmpty();
	}

	public int listofsensors_GrpWiringpage() {
		List<WebElement> snsrFields = driver.findElementByClassName("ScrollViewer")
				.findElements(By.className("TextBlock"));

		return snsrFields.size();

	}

	// List of groups created in group sensors screen on the left pane in Group
	// Wiring page

	public int countOfgroups_GroupWiringpage() {
		List<WebElement> Groups = driver.findElementByClassName("ListBox").findElements(By.className("ListBoxItem"));
		return Groups.size();
	}

	// Five image selection options
	public boolean imageselectionoption5_isdisplayed() {
		WebElement image5 = driver.findElementByAccessibilityId("Image5Button");
		return image5.isDisplayed();

	}

	// Check the presence of Print icon in Group Wiring page
	public boolean Print_state() {
		WebElement print = driver.findElementByAccessibilityId("PrintButton");
		return IsElementVisibleStatus(print);
	}

	// Click on print btn

	public void Click_Print() {
		WebElement print_Btn = driver.findElementByAccessibilityId("PrintButton");
		clickOn(print_Btn);
	}

	// Verify Image1Button is enable and visible

	public boolean Is_1stImageBtn_Visible() {
		WebElement FirststImage = driver.findElementByAccessibilityId("Image1Button");

		return FirststImage.isEnabled();
	}

	// click on Image1Button
	public void click_1stImageBtn() {
		WebElement FirststImage = driver.findElementByAccessibilityId("Image1Button");
		clickOn(FirststImage);
	}
	
	// Capture image from the group image holder save it in Data folder
	public void Capture_GroupImgHolder(String Img_Name) throws IOException {		
		WebElement FirststImage = driver.findElementByAccessibilityId("Image1Button");
		tu.capture_element_screenshot(driver, FirststImage, "TestData", Img_Name);
	}

	// click on Image2Button
	public void click_2ndImageBtn() {
		WebElement SecondImage = driver.findElementByAccessibilityId("Image2Button");
		clickOn(SecondImage);
	}

	// Verify ChangeImage option displayed
	public boolean is_ChangeImageBtn_displayed() {
		WebElement EditImage = driver.findElementByAccessibilityId("ChangeImageBrowser");
		return EditImage.isDisplayed();
	}

	// Verify Remove Image option displayed
	public boolean is_RemoveImageBtn_displayed() {
		WebElement RemoveImage = driver.findElementByAccessibilityId("RemoveImageButton");
		return RemoveImage.isDisplayed();
	}

	// Verify Remove Image option displayed
	public boolean is_ChangeImageCamera_displayed() {
		WebElement ChangeImageCamera = driver.findElementByAccessibilityId("ChangeImageCamera");
		return ChangeImageCamera.isDisplayed();
	}

	// click on EditImage
	public void click_EditImageBtn() throws InterruptedException {
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		WebElement EditImage = driver.findElementByAccessibilityId("ChangeImageBrowser");
		clickOn(EditImage);
		Thread.sleep(2000);
	}

	public boolean IsClosebtn_visible() {
		WebElement alert = driver.switchTo().activeElement();
		WebElement EditImage = driver.findElementByAccessibilityId("Close");
		return EditImage.isEnabled();
	}

	// click on RemoveImageBtn
	public void click_RemoveImageBtn() throws InterruptedException {
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		WebElement DeleteImage = driver.findElementByAccessibilityId("RemoveImageButton");
		clickOn(DeleteImage);
	}

	// Fetch the Save Alert message
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

	// click on camera btn
	public void click_CameraBtn() throws InterruptedException {
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		WebElement cameraIcon = driver.findElementByAccessibilityId("ChangeImageCamera");
		clickOn(cameraIcon);
	}

	// is_devicecamera_displayed
	public boolean is_devicecamera_displayed() {
		WebElement Camera = driver.findElementByAccessibilityId("TitleBar");
		return Camera.isDisplayed();
	}

	// click on camera close button

	public void click_Cameraclose_btn() {
		WebElement closebtn = driver.findElementByAccessibilityId("Close");
		clickOn(closebtn);

	}

	// click CaptureButton_0
	public void click_CaptureBtn() {
		WebElement CaptureBtn = driver.findElementByAccessibilityId("CaptureButton_0");
		clickOn(CaptureBtn);
	}

	// Click AcceptButton

	public void click_AcceptButton() {
		WebElement AcptBtn = driver.findElementByAccessibilityId("AcceptButton");
		clickOn(AcptBtn);
	}

	public String Fetchgroupname_GWpage(int index) {
		List<WebElement> Groups = driver.findElementByClassName("ListBoxItem").findElements(By.className("TextBlock"));
		return Groups.get(index).getText();
	}

	public String Sensor_Name() {
		WebElement snsrName = driver.findElementByAccessibilityId("1");
		return snsrName.getText();
	}

	// Click ok button for PDF pop up
	public void click_PDFpopup_OkBtn() throws InterruptedException, AWTException {
		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		r = null;

	}

	// Click on GroupsListButton
	public void click_GroupListBtn() {
		WebElement grouplistbtn = driver.findElementByAccessibilityId("GroupsListButton");
		clickOn(grouplistbtn);
	}

	// if IsMoveBtn Visible
	public boolean IsMoveBtn_Visible() {
		boolean status = false;
		try {
			status = driver.findElementByAccessibilityId("MoveSensorsButton").isDisplayed();
		} catch (Exception e) {
			System.out.println("Move Sensors Button is not available because of single group");
		}
		return status;
	}

	//Is move icon visible 
	public boolean is_MoveIconVisible() {
		WebElement Move_Btn = driver.findElementByAccessibilityId("MoveSensorsButton");
		return IsElementVisibleStatus(Move_Btn);

	}

	// click on MoveBtn
	public void Click_MoveIcon() {
		WebElement Move_Btn = driver.findElementByAccessibilityId("MoveSensorsButton");
		clickOn(Move_Btn);

	}

	// is Move_snsr title visible
	public boolean IsMovSensors_Title_Visible() {
		WebElement Move_snsr = driver.findElementByName("Move Sensors");
		return IsElementVisibleStatus(Move_snsr);

	}

	// is From_dropdown visible in move sensor window
	public boolean IsFromdropdown_Visible() {
		WebElement from_dd = driver.findElementByAccessibilityId("FromGroupComboBox");
		return IsElementVisibleStatus(from_dd);

	}

	// is To_dropdown visible in move sensor window
	public boolean IsTodropdown_Visible() {
		WebElement to_dd = driver.findElementByAccessibilityId("ToGroupComboBox");
		return IsElementVisibleStatus(to_dd);

	}

	// is from From drop down displayed

	public String fetchgroupname_fromDropdown(int index) {
		click_On_From_dropdown();
		List<WebElement> from_dd = driver.findElementByAccessibilityId("FromGroupComboBox")
				.findElements(By.className("ComboBoxItem"));
		// return from_dd.size();
		return from_dd.get(index).getText();
		// return FetchText(from_dd.get(index));

	}

	// click on from dd btn

	public void click_On_From_dropdown() {
		WebElement from_dd = driver.findElementByAccessibilityId("FromGroupComboBox");
		clickOn(from_dd);

	}

	// click on to dd btn

	public void click_On_To_dropdown() {
		WebElement to_dd = driver.findElementByAccessibilityId("ToGroupComboBox");
		clickOn(to_dd);

	}

	public String fetchgroupname_Todropdown(int index) {
		click_On_To_dropdown();
		List<WebElement> to_dd = driver.findElementByAccessibilityId("ToGroupComboBox")
				.findElements(By.className("ComboBoxItem"));
		// return from_dd.size();
		return to_dd.get(index).getText();
		// return FetchText(from_dd.get(index));

	}

	// is move button displayed

	public boolean Is_MoveBtn_Visible() {
		WebElement move_btn = driver.findElementByAccessibilityId("MoveButton");
		return IsElementVisibleStatus(move_btn);

	}

	// click on move btn
	public void click_MoveBtn() {
		WebElement move_btn = driver.findElementByAccessibilityId("MoveButton");
		clickOn(move_btn);

	}

	// is close button displayed

	public boolean Is_CloseBtn_Visible() {
		WebElement close_btn = driver.findElementByAccessibilityId("CustomiseSensorCancelButton");
		return IsElementVisibleStatus(close_btn);

	}

	// Click on close btn

	public void click_CloseBtn() {
		WebElement close_btn = driver.findElementByAccessibilityId("CustomiseSensorCancelButton");
		clickOn(close_btn);

	}

	// click on close icon in move screen

	public void click_CloseIcon() {
		WebElement close_icon = driver.findElementByAccessibilityId("CloseButton");
		clickOn(close_icon);

	}

	// count of items listed in from drop down

	public int Countofitem_from_dd() {
		List<WebElement> items = driver.findElementByAccessibilityId("FromGroupComboBox")
				.findElements(By.className("ComboBoxItem"));
		return items.size();
	}

	// Select any option from from drop down list
	public void select_options_fromDDL(int index) {
		WebElement from_dd = driver.findElementByAccessibilityId("FromGroupComboBox");
		clickOn(from_dd);
		List<WebElement> items = driver.findElementByAccessibilityId("FromGroupComboBox")
				.findElements(By.className("ComboBoxItem"));
		items.get(index).click();
	}

	// Select Second option of To drop down list box
	public void click_2ndoption_To_DDL() {
		List<WebElement> items = driver.findElementByAccessibilityId("ToGroupComboBox")
				.findElements(By.className("ComboBoxItem"));
		items.get(1).click();
	}

	// count of items listed in to drop down

	public int Countofitem_to_dd() {
		List<WebElement> item = driver.findElementByAccessibilityId("ToGroupComboBox")
				.findElements(By.className("ComboBoxItem"));
		return item.size();
	}

	// fetch the label name ofsensors when user clicked on from Dropdown

	public String get_sensortext_FromDD(int index) {
		List<WebElement> items = driver.findElementByAccessibilityId("FromGroupSensorsGridView")
				.findElements(By.className("TextBlock"));
		return FetchText(items.get(index));
	}

	// fetch the label name of sensors when user clicked on To Dropdown
	public String get_sensortext_ToDD(int index) {
		List<WebElement> items = driver.findElementByAccessibilityId("ToGroupSensorsGridView")
				.findElements(By.className("TextBlock"));
		return FetchText(items.get(index));
	}

	// count of sensors in right pane

	public int get_sensrCount_ToDD() {
		List<WebElement> items = driver.findElementByAccessibilityId("ToGroupSensorsGridView")
				.findElements(By.className("GridViewItem"));
		return items.size();
	}

	// Click on sensors in left pane
	public void Clicksensor_FromDD_leftpane(int Index) {
		List<WebElement> items = driver.findElementByAccessibilityId("FromGroupSensorsGridView")
				.findElements(By.className("GridViewItem"));
		items.get(Index).click();

	}

	// Click on second sensor in left pane
	public void ClickOnsensor_leftpane_Movewindow(int index) {
		List<WebElement> items = driver.findElementByAccessibilityId("FromGroupSensorsGridView")
				.findElements(By.className("GridViewItem"));
		items.get(index).click();

	}

	// click on the sensor and check weathe its selectable or not
	public boolean is_sensor_selected_leftpane(int index) {
		List<WebElement> items = driver.findElementByAccessibilityId("FromGroupSensorsGridView")
				.findElements(By.className("GridViewItem"));
		items.get(index).click();
		return items.get(index).isSelected();

	}

	// Is 2nd sensor of right pane is displayed
	public boolean is_sensor_displayed_rightpane() {
		List<WebElement> items = driver.findElementByAccessibilityId("ToGroupSensorsGridView")
				.findElements(By.className("GridViewItem"));
		return items.get(1).isDisplayed();

	}

	// Get text of the Button Bar Alert message
	public String Alertmsg_txt() {
		WebElement alrtmsg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(alrtmsg);
	}

	// Get text of the Delete Alert message
	public String get_text_DeleteAst_popup() {
		WebElement deleteAsset_popup = driver.findElementByAccessibilityId("Content_String");
		return deleteAsset_popup.getAttribute("Name");
	}

	// click on yes or No btn in the alert message
	public void select_alertOption(String choice) {

		WebElement Yes_Alert_btn = driver.findElementByName("Yes");
		WebElement No_Alert_btn = driver.findElementByName("No");
		if (choice.equalsIgnoreCase("Yes")) {
			clickOn(Yes_Alert_btn);
		} else {
			clickOn(No_Alert_btn);
		}
	}

	// click on Expander icon

	public void click_ExpanderIcon() {
		WebElement Expander = driver.findElementByAccessibilityId("ExpanderImage");
		clickOn(Expander);
	}

	// is Is_AssetIDVisible

	public boolean Is_AssetIDVisible() {
		boolean status = false;
		try {
			status = driver.findElementByName("Asset ID").isDisplayed();
		} catch (Exception e) {
			System.out.println("Asset ID Button is not available as the expander is closed");
		}
		return status;
	}

	// Verify asset ID is in non edit mode
	// Value.IsReadOnly
	public boolean IsAssetIDReadonlymode_ON() {
		List<WebElement> assetid = driver.findElementsByClassName("TextBox");
		// return assetid.get(0).IsReadOnly();
		return assetid.get(0).getAttribute("Value.IsReadOnly").equals("True");

	}

	public boolean IsAssetIDReadonlymode_Off() {
		List<WebElement> assetid = driver.findElementsByClassName("TextBox");
		// return assetid.get(0).IsReadOnly();
		return assetid.get(0).getAttribute("Value.IsReadOnly").equals("false");

	}

	// Fetch Text from asset id field
	public String getTxt_from_AssetID() {
		List<WebElement> assetid = driver.findElementsByClassName("TextBox");
		return FetchText(assetid.get(0));
	}

	// Enter data to AseetId
	public void Enter_Valuesinto_AssetID(String text) {
		List<WebElement> assetid = driver.findElementsByClassName("TextBox");
		assetid.get(0).clear();
		assetid.get(0).click();
		assetid.get(0).sendKeys(text);
	}

	// Fetch Text from sop field
	public String getTxt_from_SOP() {
		List<WebElement> sop = driver.findElementsByClassName("TextBox");
		return FetchText(sop.get(1));
	}

	// is Description field visible

	public boolean Is_Description_Visible() {
		WebElement Description = driver.findElementByName("Description");
		return Description.isDisplayed();
	}
	// is SOP Protocol visible

	public boolean Is_SOP_Protocol_Visible() {
		WebElement SOPProtocol = driver.findElementByName("SOP Protocol");
		return SOPProtocol.isDisplayed();
	}

	// is Comments visible

	public boolean Is_Comments_Visible() {
		WebElement Comments = driver.findElementByName("Comments");
		return Comments.isDisplayed();
	}

	// 10 groups can be created when 10 is selected for _Max groups_ field in
	// preference page

	public void create_MaxGroup(int numberofGroups) {
		int j = 1;

		for (int i = 1; i <= numberofGroups; i++) {
			clickOn_NewGroupButton();

			String gn = "abc" + j;

			enter_groupname(gn);
			click_SaveBtn();
			j++;
		}
	}

	//
	public void Capture_ImgButton1(String Img_Name) throws IOException {
		TestUtilities tu = new TestUtilities();
		WebElement FirststImage = driver.findElementByAccessibilityId("Image1Button");
		tu.capture_element_screenshot(driver, FirststImage, "TestData", Img_Name);

	}
}
