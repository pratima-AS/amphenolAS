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

public class PoliciesPage extends BaseClass {
	// Page element variable declaration definition
	WebElement PoliciesHeaderText = null;
	WebElement pwdcombobox = null;
	WebElement SaveBtn = null;
	WebElement Passwords = null;
	WebElement UserManagement_TAB = null;
	WebElement ExpirePasswordComboBox = null;
	WebElement LoginFailuresComboBox = null;
	WebElement DisplayUserIdEntryCheckBox = null;
	WebElement DisablePasswordSystemCheckBox = null;
	WebElement PasswordAplhaNumericSystemCheckBox = null;
	WebElement AllowDValueEditingCheckBox = null;
	WebElement InstrumentCalibWarningCheckBox = null;
	WebElement AutoSyncOutCheckBox = null;
	WebElement BackButton = null;
	WebElement pwdcheckbox = null;
	WebElement ExpirePasswordCheckBox = null;
	WebElement DisableUserafterAttemptsCheckBox = null;
	WebElement Preferences_TAB = null;
	WebElement InstrumentCalibWarningComboBox = null;
	WebElement CancelButton = null;

	// Page element Initialize method
	private void initElements() {
		PoliciesHeaderText = driver.findElementByAccessibilityId("PoliciesButton");
		// pwdcombobox =
		// driver.findElementByAccessibilityId("RequireMinLengthPasswordComboBox");

		pwdcombobox = driver.findElementByAccessibilityId("RequireMinLengthPassword_A_ID1");
		pwdcheckbox = driver.findElementByAccessibilityId("RequireMinLengthPasswordCheckBox");
		// ExpirePasswordComboBox =
		// driver.findElementByAccessibilityId("ExpirePasswordComboBox");
		ExpirePasswordComboBox = driver.findElementByAccessibilityId("ExpirePasswordComboBox_A_ID2");

		ExpirePasswordCheckBox = driver.findElementByAccessibilityId("ExpirePasswordCheckBox");
		SaveBtn = driver.findElementByName("Save");
		Passwords = driver.findElementByName("Passwords");
		UserManagement_TAB = driver.findElementByAccessibilityId("UserManagementButton");
		Preferences_TAB = driver.findElementByAccessibilityId("PreferencesButton");

		LoginFailuresComboBox = driver.findElementByAccessibilityId("LoginFailuresComboBox");
		DisplayUserIdEntryCheckBox = driver.findElementByName("Display user id during entry");
		DisablePasswordSystemCheckBox = driver.findElementByAccessibilityId("DisablePasswordSystemCheckBox");
		PasswordAplhaNumericSystemCheckBox = driver.findElementByAccessibilityId("PasswordAplhaNumericSystemCheckBox");
		AllowDValueEditingCheckBox = driver.findElementByAccessibilityId("AllowDValueEditingCheckBox");
		InstrumentCalibWarningCheckBox = driver.findElementByAccessibilityId("InstrumentCalibWarningCheckBox");
		AutoSyncOutCheckBox = driver.findElementByAccessibilityId("AutoSyncOutCheckBox");
		BackButton = driver.findElementByAccessibilityId("BackButton");
		DisableUserafterAttemptsCheckBox = driver.findElementByAccessibilityId("DisableUserafterAttemptsCheckBox");
		InstrumentCalibWarningComboBox = driver.findElementByAccessibilityId("InstrumentCalibWarning_A_ID3");
		CancelButton = driver.findElementByAccessibilityId("CancelButton");
	}

	// Constructor for initializing the page elements
	PoliciesPage() throws IOException {
		super();
		initElements();
	}

	// Release memory
	public void resetWebElements() {
		PoliciesHeaderText = null;
		pwdcheckbox = null;
		ExpirePasswordCheckBox = null;
		DisableUserafterAttemptsCheckBox = null;
		Preferences_TAB = null;
		InstrumentCalibWarningComboBox = null;
		CancelButton = null;
		Passwords = null;
	}

	/*----------------------
	Methods of Policies Page
	------------------------*/
	// Check if Policies page is displayed
	public boolean IsPolicies_screenDisplayed() {
		return IsElementEnabledStatus(PoliciesHeaderText);
	}

	// click on clickOn(pwdcombobox);
	public void PWDLengthBox_Click() throws InterruptedException {
		clickOn(pwdcombobox);
	}

	// FetchText from pwdcombobox

	public String pwdcombobox_text() {
		return FetchText(pwdcombobox);

	}

	// click on InstrumentCalibWarningComboBox

	public void Click_InstrumentCalibWarningComboBox() throws InterruptedException {
		clickOn(InstrumentCalibWarningComboBox);
	}

	// fetch text from InstrumentCalibWarningComboBox
	public String InstrumentCalibWarningComboBox_text() {
		return FetchText(InstrumentCalibWarningComboBox);

	}

	public String PWD_text() {
		return FetchText(pwdcombobox);

	}

	public void ICW_9Months() throws InterruptedException {
		Click_InstrumentCalibWarningComboBox();
		WebElement option1 = driver.findElementByName("9 Months");
		clickOn(option1);
	}

	// Select any minimum length pwd

	public void SelectAny_Option_FromPWDLengthBox(int index) throws InterruptedException {
		List<WebElement> options = driver.findElementByAccessibilityId("RequireMinLengthPassword_A_ID1")
				.findElements(By.className("ComboBoxItem"));
		options.get(index).click();
		Thread.sleep(1000);
		ClickSaveButton();
	}

	// Is password combobox visible

	public boolean IspwdcomboboxVisible() {

		return IsElementEnabledStatus(pwdcombobox);
	}

	// Is LoginFailuresComboBox enable

	public boolean IsLoginFailuresComboBox_Enable() {

		return IsElementEnabledStatus(LoginFailuresComboBox);
	}

//DisplayUserIdEntryCheckBox
	public boolean IsUserIdEntryCheckBoxVisible() {

		return IsElementEnabledStatus(DisplayUserIdEntryCheckBox);
	}

//DisablePasswordSystemCheckBox

	public boolean IsPwdsystemcheckBoxVisible() {

		return IsElementEnabledStatus(DisablePasswordSystemCheckBox);
	}

//PasswordAplhaNumericSystemCheckBox

	public boolean IsSpecialCharCheckBoxVisible() {

		return PasswordAplhaNumericSystemCheckBox.isEnabled();
	}

//click PasswordAplhaNumericSystemCheckBox
	public void click_RequireSpecialCharacters() {
		clickOn(PasswordAplhaNumericSystemCheckBox);
	}

//InstrumentCalibWarningCheckBox

	public boolean IsInstrumentCalibWarningCheckBoxVisible() {

		return IsElementEnabledStatus(InstrumentCalibWarningCheckBox);
	}

//ExpirePasswordComboBox is visible

	public boolean IsExpirePasswordComboBoxVisible() {

		return IsElementEnabledStatus(ExpirePasswordComboBox);
	}

//AllowDValueEditingCheckBox

	public boolean IsAllowDValueEditingCheckBoxVisible() {

		return IsElementEnabledStatus(AllowDValueEditingCheckBox);
	}

//is checkbox AllowDValueEditing is selected

	public boolean IsDValueCheckBox_selected() {

		return checkboxSelectStatus(AllowDValueEditingCheckBox);
	}

	public void Enable_Editing_Dvalue() throws InterruptedException {

		if (checkboxSelectStatus(AllowDValueEditingCheckBox) == true) {
			System.out.println("D Value Checkbox is selected");
		} else {
			clickOn(AllowDValueEditingCheckBox);
			ClickSaveButton();
		}
	}

	// click on AllowDValueEditingCheckBox
	public void clickOnAllowDValueEditingCheckBox() {
		clickOn(AllowDValueEditingCheckBox);
	}

//AutoSyncOutCheckBox

	public boolean IsAutoSyncOutCheckBoxVisible() {

		return IsElementEnabledStatus(AutoSyncOutCheckBox);
	}

	// Click Save button
	public void ClickSaveButton() throws InterruptedException {
		Thread.sleep(1000);
		clickOn(SaveBtn);
	}

	// Click Cancel Button
	public void ClickCancelButton() throws InterruptedException {
		Thread.sleep(1000);
		clickOn(CancelButton);
	}

	// Click on um tab
	public UserManagementPage ClickUserManagement_TAB() throws InterruptedException, IOException {
		clickOn(UserManagement_TAB);
		return new UserManagementPage();
	}

	// Click on preference tab
	public PreferencesPage Clickpreference_TAB() throws InterruptedException, IOException {
		clickOn(Preferences_TAB);
		return new PreferencesPage();
	}

	public boolean UserLoginPopupVisible() throws InterruptedException {
		WebElement LgInPopup = driver.findElementByName("Enter User Credentials");
		return IsElementVisibleStatus(LgInPopup);
	}

	// click on BackButton
	public MainHubPage click_BackBtn() throws InterruptedException, IOException {
		clickOn(BackButton);
		Thread.sleep(1000);
		return new MainHubPage();
	}

	// click on pwdcombobox for check/Uncheck combo box

	public void clickOn_PWDcheckbox() throws InterruptedException {

		clickOn(pwdcheckbox);
		Thread.sleep(1000);
		ClickSaveButton();
	}

	public void Enable_Editing_PWDcheckbox() throws InterruptedException {
		if (checkboxSelectStatus(pwdcheckbox) == true) {
			System.out.println("Min Length Password CheckBox is selected");
		} else {
			clickOn(pwdcheckbox);
			ClickSaveButton();
		}
	}

	public void Select_PWDcheckbox() throws InterruptedException {

		clickOn(pwdcheckbox);

	}

//Get text of the Delete Alert message
	public String get_text_from_Alertpopup() {
		WebElement deleteAsset_popup = driver.findElementByAccessibilityId("Content_String");
		return deleteAsset_popup.getAttribute("Name");
	}

//Fetch the  Alert message
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

//fetch text
	public String fetch_expirepwd_Defaulttext() {
		return ExpirePasswordComboBox.findElement(By.className("TextBlock")).getText();
	}

	public void SelectAny_Value_fromExpirePwd_DD(int index) throws InterruptedException {
		List<WebElement> options = driver.findElementByAccessibilityId("ExpirePasswordComboBox_A_ID2")
				.findElements(By.className("ComboBoxItem"));
		options.get(index).click();
		Thread.sleep(1000);
		ClickSaveButton();
	}

	public String FetchTxt_from_ExpirePwdDD(int index) throws InterruptedException {
		List<WebElement> options = driver.findElementByAccessibilityId("ExpirePasswordComboBox_A_ID2")
				.findElements(By.className("ComboBoxItem"));

		return FetchText(options.get(index));
	}

//click On ExpirePasswordCheckBox
	public void click_ExpirePasswordCheckBox() throws InterruptedException {

		clickOn(ExpirePasswordCheckBox);
	}

//DisableUserafterAttemptsCheckBox
	public void click_DisableUserafterAttemptsCheckBox() throws InterruptedException {

		clickOn(DisableUserafterAttemptsCheckBox);
	}

//
	public void LoginFailure_3() throws InterruptedException {

		clickOn(LoginFailuresComboBox);
		WebElement option1 = driver.findElementByName("3");
		clickOn(option1);
		Thread.sleep(1000);
		ClickSaveButton();

	}

	public void LoginFailure_4() throws InterruptedException {

		clickOn(LoginFailuresComboBox);
		WebElement option2 = driver.findElementByName("4");
		clickOn(option2);
		Thread.sleep(1000);
		ClickSaveButton();

	}

	public void LoginFailure_5() throws InterruptedException {

		clickOn(LoginFailuresComboBox);
		WebElement option2 = driver.findElementByName("5");
		clickOn(option2);
		Thread.sleep(1000);
		ClickSaveButton();

	}

	public void LoginFailure_6() throws InterruptedException {

		clickOn(LoginFailuresComboBox);
		WebElement option3 = driver.findElementByName("6");
		clickOn(option3);
		Thread.sleep(1000);
		ClickSaveButton();

	}

	public void LoginFailure_7() throws InterruptedException {

		clickOn(LoginFailuresComboBox);
		WebElement option7 = driver.findElementByName("7");
		clickOn(option7);
		Thread.sleep(1000);
		ClickSaveButton();

	}

	public void LoginFailure_8() throws InterruptedException {

		clickOn(LoginFailuresComboBox);
		WebElement option8 = driver.findElementByName("8");
		clickOn(option8);
		Thread.sleep(1000);
		ClickSaveButton();

	}

	public void LoginFailure_9() throws InterruptedException {

		clickOn(LoginFailuresComboBox);
		WebElement option9 = driver.findElementByName("9");
		clickOn(option9);
		Thread.sleep(1000);
		ClickSaveButton();

	}

	public void LoginFailure_10() throws InterruptedException {

		clickOn(LoginFailuresComboBox);
		WebElement option10 = driver.findElementByName("10");
		clickOn(option10);
		Thread.sleep(1000);
		ClickSaveButton();

	}

// Verify the Asset ID Field is enable or not
	public String UseID_Masked() {
		WebElement Idfield = driver.findElementByAccessibilityId("UserIdAsetrik");
		return Idfield.getAttribute("Value.Value");

	}

	// click on DisplayUserIdEntryCheckBox

	public void click_DisplayUserIdEntryCheckBox() {
		clickOn(DisplayUserIdEntryCheckBox);
	}

	// click on DisablePasswordSystemCheckBox

	public void click_DisablePasswordSystemCheckBox() {
		clickOn(DisablePasswordSystemCheckBox);
	}

	// click on yes or No btn in the alert message
	public LoginPage alertOptionYES() throws IOException {

		WebElement Yes_Alert_btn = driver.findElementByName("Yes");
		clickOn(Yes_Alert_btn);
		return new LoginPage();
	}

	public void clickalertYes() throws IOException {
		WebElement Yes_Alert_btn = driver.findElementByName("Yes");
		clickOn(Yes_Alert_btn);

	}

	// click on ExpirePasswordComboBox

	public void click_ExpirePasswordComboBox() {
		clickOn(ExpirePasswordComboBox);

	}

	// Select Expire Password
	public void selectExpirePassword(String pw) throws InterruptedException {

		String pw1 = fetch_expirepwd_Defaulttext();
		String SPart = pw1.split(" ")[0];
		int val = Integer.parseInt(SPart);
		// System.out.println(val);

		String firstPart = pw.split(" ")[0];
		int val1 = Integer.parseInt(firstPart);
		// System.out.println(val1);

		click_ExpirePasswordComboBox();
		click_ExpirePasswordComboBox();
		Actions ac = new Actions(driver);

		if (val1 < val) {
			for (int i = 0; i <= val; i++) {
				//click_ExpirePasswordComboBox();
				//Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_UP).build().perform();
				//ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ENTER).build().perform();
				//Thread.sleep(1000);
				// System.out.println((fetch_expirepwd_Defaulttext()));
				//Thread.sleep(500);
				if (ExpirePasswordComboBox.findElement(By.className("TextBlock")).getText().equals(pw)) {
					// clickOn(Passwords);
					Thread.sleep(1000);
					break;
					
				}
			}

		} else if (val1 > val) {
			for (int i = 0; i <= (368-val); i++) {
				//click_ExpirePasswordComboBox();
				// Thread.sleep(500);
				//ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
				ac.sendKeys(Keys.ARROW_DOWN).build().perform();
				//Thread.sleep(2000);
				// System.out.println((fetch_expirepwd_Defaulttext()));
				// Thread.sleep(500);
				if (ExpirePasswordComboBox.findElement(By.className("TextBlock")).getText().equals(pw)) {
					// clickOn(Passwords);
					Thread.sleep(1000);
					break;
				}
			}
			
		}
	}

}
