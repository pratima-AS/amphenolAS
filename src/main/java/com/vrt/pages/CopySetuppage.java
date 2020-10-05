/**
* @author manoj.ghadei
*
*/

package com.vrt.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;
import com.vrt.utility.TestUtilities;

public class CopySetuppage extends BaseClass {

	// Page element variable declaration definition
	WebElement SelectAll_ChkboxField = null;
	WebElement Copysetup_btn = null;
	WebElement CopySetupTitle = null;
	WebElement CopySetup_Back_Btn = null;
	WebElement SETUP_Header = null;

	// Page element Initialize method
	private void initElements() {
		SelectAll_ChkboxField = driver.findElementByAccessibilityId("chkSelectAll");
		Copysetup_btn = driver.findElementByAccessibilityId("CopySetupButton");
		CopySetupTitle = driver.findElementByName("Copy Setup");
		CopySetup_Back_Btn = driver.findElementByAccessibilityId("BackButton");
		SETUP_Header = driver.findElementByName("SETUP");

	}

	// Constructor for initializing the page elements
	CopySetuppage() throws IOException {
		super();
		initElements();
	}

	// Release memory
	public void resetWebElements() {
		SelectAll_ChkboxField = null;
		Copysetup_btn = null;
		CopySetupTitle = null;
		CopySetup_Back_Btn = null;
		SETUP_Header = null;
	}

	// Click on Copy Setup Back button
	public assetDetailsPage Click_Back_Btn() throws InterruptedException, IOException {
		clickOn(CopySetup_Back_Btn);
		Thread.sleep(2000);
		return new assetDetailsPage();
	}

	// Verify copy successful message displayed
	public boolean CopySetupPage_Title() {
		return IsElementVisibleStatus(CopySetupTitle);
	}

	// Verify select all check box is displayed
	public boolean SelectAll_ChkboxField_Isvisible() {
		return IsElementVisibleStatus(SelectAll_ChkboxField);
	}

	// Click on SETUP_Header to sort by alphabetical order
	public void Click_SETUP_Header() {
		clickOn(SETUP_Header);
	}

	// Click on select All checkBox
	public void Click_Selectall_chkbox() {
		clickOn(SelectAll_ChkboxField);
	}

	// Verify copy button is Enabled
	public boolean Copysetup_btn_IsEnable() {
		return IsElementEnabledStatus(Copysetup_btn);
	}

	// Click on copy button
	public void click_copy_Btn() throws InterruptedException {
		clickOn(Copysetup_btn);
		Thread.sleep(2000);
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

	// Get text of the copy asset page
	public String get_text_copyAst_popup() {
		WebElement copyAsset_popup = driver.findElementByAccessibilityId("Content_String");
		return copyAsset_popup.getAttribute("Name");
	}

	// Login Popup presence
	public boolean UserLoginPopupVisible() throws InterruptedException {
		WebElement LgInPopup = driver.findElementByName("Enter User Credentials");
		return IsElementVisibleStatus(LgInPopup);
	}

	// Verify copy successful message displayed
	public boolean copysuccessfulmessage() {
		WebElement dmt = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return IsElementVisibleStatus(dmt);
	}

	// ExpandIcon

	public void clickExpand_Icon() {
		WebElement CE_icon = driver.findElementByAccessibilityId("ExpandIcon");
		clickOn(CE_icon);
	}
	// Collapse and ExpandIcon

	public void clickCollapse_Icon() {
		WebElement CollapseIcon_icon = driver.findElementByAccessibilityId("CollapseIcon");
		clickOn(CollapseIcon_icon);
	}

	// Fetch the asset name
	public String get_assetname() {
		List<WebElement> Firstastname = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return FetchText(Firstastname.get(2));
	}

	// Fetch the setup name

	public String get_copysetupname() {
		List<WebElement> Firstsetupname = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return FetchText(Firstsetupname.get(3));
	}

	// Fetch the sensor number

	public String get_sensrNumber() {
		List<WebElement> snsrnum = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return FetchText(snsrnum.get(4));
	}

	// Fetch comment

	public String get_comment() {
		List<WebElement> comment = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return FetchText(comment.get(5));
	}

	// Fetch date
	public String get_date() {
		List<WebElement> setupdate = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return FetchText(setupdate.get(6));
	}

	// Is Date displayed
	public boolean IsdateDisplayed() {
		List<WebElement> date = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return date.get(6).isDisplayed();
	}

	public boolean Is_assetnameTiles_Empty() {
		List<WebElement> astlist = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return astlist.isEmpty();

	}

	//
	public int SetupCount() {
		List<WebElement> chkbox = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("CheckBox"));
		return chkbox.size();

	}

	// Is CheckBox displayed
	public boolean IsCheckBox_Displayed() {
		List<WebElement> chkbox = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("CheckBox"));
		return chkbox.get(0).isEnabled();
	}

	// Click ON CheckBox
	public void clickONCheckBOX_1() {
		List<WebElement> chkbox = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("CheckBox"));
		chkbox.get(0).click();
	}

	// Filter Functionality for SETUP
	public void filter_On_SETUP(String value1) throws InterruptedException {
		List<WebElement> filter_Setup = driver.findElementsByAccessibilityId("PART_FilterButton");
		filter_Setup.get(0).click();
		WebElement editSetup = driver.findElementByAccessibilityId("PART_ValueBox");
		clickOn(editSetup);
		enterText(editSetup, value1);

		WebElement FilterBtn = driver.findElementByAccessibilityId("PART_FilterButton");
		Thread.sleep(3000);
		FilterBtn.click();
	}

	// Filter Functionality for Sensors
	public void filter_On_Sensors(String sensrvalue) {
		List<WebElement> filter_Sensors = driver.findElementsByAccessibilityId("PART_FilterButton");
		filter_Sensors.get(1).click();
		WebElement editSensors = driver.findElementByAccessibilityId("PART_ValueBox");
		clickOn(editSensors);
		enterText(editSensors, sensrvalue);
		WebElement FilterBtn = driver.findElementByAccessibilityId("PART_FilterButton");
		clickOn(FilterBtn);
	}

	// Filter Functionality for comments
	public void filter_On_Comments(String commentvalue) {
		List<WebElement> filter_Comments = driver.findElementsByAccessibilityId("PART_FilterButton");
		filter_Comments.get(2).click();
		WebElement editcomments = driver.findElementByAccessibilityId("PART_ValueBox");
		clickOn(editcomments);
		enterText(editcomments, commentvalue);
		WebElement FilterBtn = driver.findElementByAccessibilityId("PART_FilterButton");
		clickOn(FilterBtn);
	}

}
