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
		pwdcombobox = driver.findElementByAccessibilityId("RequireMinLengthPassword_A_ID1");
		pwdcheckbox = driver.findElementByAccessibilityId("RequireMinLengthPasswordCheckBox");
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

	// FetchText from pwd char combobox
	public String pwdLengthcombobox_text() {
		return FetchText(pwdcombobox.findElement(By.className("TextBlock")));
	}

	// click on InstrumentCalibWarningComboBox
	public void Click_InstrumentCalibWarningComboBox() throws InterruptedException {
		clickOn(InstrumentCalibWarningComboBox);
	}

	// fetch text from InstrumentCalibWarningComboBox
	public String InstrumentCalibWarningComboBox_text() {
		return FetchText(InstrumentCalibWarningComboBox);

	}

	public void ICW_9Months() throws InterruptedException {
		Click_InstrumentCalibWarningComboBox();
		WebElement option1 = driver.findElementByName("9 Months");
		clickOn(option1);
	}

	// Select any minimum length pwd

	public void SelectAny_Option_FromPWDLengthBox(String CharLength) throws InterruptedException {
		String ch1 = pwdLengthcombobox_text();
		// System.out.println(ch1);
		String SPart = ch1.split(" ")[0];
		// System.out.println(SPart);

		PWDLengthBox_Click();
		PWDLengthBox_Click();
		Actions ac = new Actions(driver);

		if (CharLength.equals("6")) {
			Thread.sleep(1000);
			ClickSaveButton();
		} else {
			for (int i = 0; i <= 11; i++) {
				ac.sendKeys(Keys.ARROW_DOWN).build().perform();
				Thread.sleep(1000);
				String ch2 = pwdLengthcombobox_text().split(" ")[0];
				// System.out.println(ch2);
				if (ch2.equals(CharLength)) {
					Thread.sleep(1000);
					break;
				}
			}
			Thread.sleep(1000);
			ClickSaveButton();
		}

	}

	// Is password combobox visible
	public boolean IspwdcomboboxVisible() {
		return IsElementEnabledStatus(pwdcombobox);
	}

	// Is LoginFailuresComboBox enable
	public boolean IsLoginFailuresComboBox_Enable() {
		return IsElementEnabledStatus(LoginFailuresComboBox);
	}

	// DisplayUserIdEntryCheckBox
	public boolean IsUserIdEntryCheckBoxVisible() {
		return IsElementEnabledStatus(DisplayUserIdEntryCheckBox);
	}

	public boolean IsUserIdEntryCheckBox_Enabled() {
		return checkboxSelectStatus(DisplayUserIdEntryCheckBox);
	}

	public void UserIdEntryCheckBox_Status() throws InterruptedException {
		if (checkboxSelectStatus(DisplayUserIdEntryCheckBox) == true) {
			System.out.println("DisplayUserIdEntryCheckBox is selected");
		} else {
			clickOn(AllowDValueEditingCheckBox);
			ClickSaveButton();
		}
	}

	// DisablePasswordSystemCheckBox
	public boolean IsPwdsystemcheckBoxVisible() {
		return IsElementEnabledStatus(DisablePasswordSystemCheckBox);
	}

	// PasswordAplhaNumericSystemCheckBox
	public boolean IsSpecialCharCheckBoxVisible() {

		return IsElementEnabledStatus(PasswordAplhaNumericSystemCheckBox);
	}

	// click PasswordAplhaNumericSystemCheckBox
	public void click_RequireSpecialCharacters() {
		clickOn(PasswordAplhaNumericSystemCheckBox);
	}

	// InstrumentCalibWarningCheckBox
	public boolean IsInstrumentCalibWarningCheckBoxVisible() {
		return IsElementEnabledStatus(InstrumentCalibWarningCheckBox);
	}

	// ExpirePasswordComboBox is visible
	public boolean IsExpirePasswordComboBoxVisible() {
		return IsElementEnabledStatus(ExpirePasswordComboBox);
	}

	// AllowDValueEditingCheckBox
	public boolean IsAllowDValueEditingCheckBoxVisible() {
		return IsElementEnabledStatus(AllowDValueEditingCheckBox);
	}

	// is checkbox AllowDValueEditing is selected
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

	// AutoSyncOutCheckBox
	public boolean IsAutoSyncOutCheckBoxVisible() {
		return IsElementEnabledStatus(AutoSyncOutCheckBox);
	}

	public void EnterUID(String UN, String PW) {
		WebElement LgInUID = driver.findElementByAccessibilityId("UserIdTextBox");
		WebElement LgInPW = driver.findElementByAccessibilityId("PasswordTextBox");
		LgInUID.sendKeys(UN);
		LgInPW.sendKeys(PW);
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

	public void click_PWDcheckbox() throws InterruptedException {
		clickOn(pwdcheckbox);
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

	// DisableUserafterAttemptsCheckBox
	public void click_DisableUserafterAttemptsCheckBox() throws InterruptedException {

		clickOn(DisableUserafterAttemptsCheckBox);
	}

	//
	public void Select_From_LoginFailuresDD(int index) throws InterruptedException {

		clickOn(LoginFailuresComboBox);
		List<WebElement> options = driver.findElementByAccessibilityId("LoginFailuresComboBox")
				.findElements(By.className("ComboBoxItem"));
		options.get(index).click();
		Thread.sleep(1000);
		ClickSaveButton();
	}

	// Verify the Asset ID Field is enable or not

	public String UseID_Masked() {
		WebElement Idfield = driver.findElementByAccessibilityId("UserIdAsetrik");
		return Idfield.getAttribute("Value.Value");

	}

	public String Fetch_UseID() {
		WebElement Idfield = driver.findElementByAccessibilityId("UserIdTextBox");
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
		// System.out.println("ActPW: "+val);

		// String firstPart = pw.split(" ")[0];
		int val1 = Integer.parseInt(pw);
		// System.out.println("SetPW: "+val1);

		click_ExpirePasswordComboBox();
		click_ExpirePasswordComboBox();
		Actions ac = new Actions(driver);

		if (val1 < val) {
			for (int i = 0; i <= val; i++) {
				ac.sendKeys(Keys.ARROW_UP).build().perform();
				if (fetch_expirepwd_Defaulttext().split(" ")[0].equals(pw)) {
					Thread.sleep(1000);
					break;
				}
			}

		} else if (val1 > val) {
			for (int i = 0; i <= (368 - val); i++) {
				ac.sendKeys(Keys.ARROW_DOWN).build().perform();
				if (fetch_expirepwd_Defaulttext().split(" ")[0].equals(pw)) {
					Thread.sleep(1000);
					break;
				}
			}

		}
	}
	
	// Enable the  Instrument Calibration warning by clicking on Click_InstrumentCalibWarningCheckBox
	public void Enable_InstrumentCalibWarningCheckBox() throws InterruptedException {
		String InstruCalCheckBx_State =  InstrumentCalibWarningCheckBox.getAttribute("Toggle.ToggleState");
		//System.out.println(InstruCalCheckBx_State);
		//For enabling the Instru. Cal warning feature enter Yes as parameter
		if (InstruCalCheckBx_State.equals("0")) {
			clickOn(InstrumentCalibWarningCheckBox);
			ClickSaveButton();
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		}
		
	}
	
	// Disable the  Instrument Calibration warning by clicking on Click_InstrumentCalibWarningCheckBox
	public void Disable_InstrumentCalibWarningCheckBox() throws InterruptedException {
		String InstruCalCheckBx_State =  InstrumentCalibWarningCheckBox.getAttribute("Toggle.ToggleState");
		//System.out.println(InstruCalCheckBx_State);
		//For enabling the Instru. Cal warning feature enter Yes as parameter
		if (InstruCalCheckBx_State.equals("1")) {
			clickOn(InstrumentCalibWarningCheckBox);
			ClickSaveButton();
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		}
		
	}


}
