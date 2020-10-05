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

public class Copyassetpage extends BaseClass {

	// Page element variable declaration definition
	WebElement NewAssetName_Field = null;
	WebElement NewAssetID_Field = null;
	WebElement CopyAsset_btn = null;
	WebElement Copyassetpage_Title = null;

	// Page element Initialize method
	private void initElements() {
		NewAssetName_Field = driver.findElementByAccessibilityId("txtAssetName");
		NewAssetID_Field = driver.findElementByAccessibilityId("txtAMachaineID");
		CopyAsset_btn = driver.findElementByAccessibilityId("CopyAssetButton");
		Copyassetpage_Title = driver.findElementByAccessibilityId("AssetsNameTextBlock");

	}

	// Constructor for initializing the page elements
	Copyassetpage() throws IOException {
		super();
		initElements();
	}

	// Release memory
	public void resetWebElements() {
		NewAssetName_Field = null;
		NewAssetID_Field = null;
	}

	// Check the presence of Copy Asset Page tile
	public boolean IsCopyAssetPageTitle_presence() {
		return IsElementVisibleStatus(Copyassetpage_Title);
	}

	// Is New AssetName Field is available
	public boolean IsNewAssetName_Field_Visible() {
		return IsElementVisibleStatus(NewAssetName_Field);
	}

	// Is New AssetID Field is available
	public boolean IsNewAssetID_Field_Visible() {
		return IsElementVisibleStatus(NewAssetID_Field);
	}

	// Fetch New AssetName_Field text
	public String get_NewAssetName_Text() {
		return FetchText(NewAssetName_Field);
	}

	// Fetch New AssetID_Field text
	public String get_NewAssetID_Text() {
		return FetchText(NewAssetID_Field);
	}

	// User Login Popup Visible
	public boolean UserLoginPopupVisible() throws InterruptedException {
		WebElement LgInPopup = driver.findElementByName("Enter User Credentials");
		return IsElementVisibleStatus(LgInPopup);
	}

	// Enter New AssetName Field
	public void Enter_NewAssetNameField(String AN) {
		ClearText(NewAssetName_Field);
		clickOn(NewAssetName_Field);
		enterText(NewAssetName_Field, AN);
	}

	public void Enter_NewAssetIDField(String AID) {
		ClearText(NewAssetID_Field);
		clickOn(NewAssetID_Field);
		enterText(NewAssetID_Field, AID);
	}

	// Enter assetID and asset name and click copy
	public void copyAsset_Creation(String Aname, String AID) {
		Enter_NewAssetNameField(Aname);
		Enter_NewAssetIDField(AID);
		click_copy_Btn();
	}

	// Click on copy button
	public void click_copy_Btn() {
		clickOn(CopyAsset_btn);
	}

//Click on Back button to move asset details page
	public assetDetailsPage clickBack_Button() throws IOException {
		WebElement Back_Button = driver.findElementByAccessibilityId("BackButton");

		clickOn(Back_Button);
		return new assetDetailsPage();
	}

	// Click on Back button
	public void clickBackBtn_alertMsg() throws IOException {
		WebElement Back_Button = driver.findElementByAccessibilityId("BackButton");

		clickOn(Back_Button);

	}

	// Get text of the Delete Alert message
	public String get_text_back_popup() {
		WebElement deleteAsset_popup = driver.findElementByAccessibilityId("Content_String");
		return deleteAsset_popup.getAttribute("Name");
	}

	// click on yes btn
	public assetDetailsPage Yes_Alert() throws IOException {
		WebElement Yes_Alert_btn = driver.findElementByName("Yes");
		clickOn(Yes_Alert_btn);
		return new assetDetailsPage();
	}

	// click on no btn it will stay in that page
	public void click_No_fromAlert() throws IOException {
		WebElement No_Alert_btn = driver.findElementByName("No");
		clickOn(No_Alert_btn);
	}

//get setup name from copy set up page

	public String get_SETUPname() {
		List<WebElement> setuplist = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return FetchText(setuplist.get(0));
	}

	// get sensors from copy set up page

	public String get_Sensors() {
		List<WebElement> setuplist = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return FetchText(setuplist.get(1));
	}

	// get comments text from copy set up page

	public String get_comments() {
		List<WebElement> setuplist = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return FetchText(setuplist.get(2));
	}

	// get date text from copy set up page
	public String get_date() {
		List<WebElement> setuplist = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return FetchText(setuplist.get(3));
	}

	// Is check box for each setup is available
	public boolean IsCheckbox_Field_Visible() {
		WebElement Checkbox_field = driver.findElementByClassName("CheckBox");
		return IsElementVisibleStatus(Checkbox_field);
	}

	// click on check box for any setup is available
	public void Click_Checkbox() {
		List<WebElement> checkboxlist = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("CheckBox"));
		checkboxlist.get(0).click();
	}
}
