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

public class EquipmentHubPage extends BaseClass {
	// IRTDHubPage IRTDHubPage;
	// EquipmentHubPage Element definition
	WebElement AddButton = null;
	WebElement VRTLogger = null;
	WebElement EquipmentHeaderTextBlock = null;
	WebElement Back_btn = null;

	private void initElements() {
		AddButton = driver.findElementByAccessibilityId("AddEquipmentsButton");
		VRTLogger = driver.findElementByAccessibilityId("TitleTextBlock1");
		EquipmentHeaderTextBlock = driver.findElementByAccessibilityId("EquipmentHeaderTextBlock");
		Back_btn = driver.findElementByAccessibilityId("ArrowGlyph");

	}

	EquipmentHubPage() throws IOException {
		super();
		initElements();

	}

	// Release memory
	public void resetWebElements() {
		AddButton = null;
		VRTLogger = null;
		EquipmentHeaderTextBlock = null;
		Back_btn = null;

	}

	// Click AddButton
	public NewEquipmentCreation_Page ClickAddButton() throws InterruptedException, IOException {
		clickOn(AddButton);
		// Thread.sleep(1000);
		return new NewEquipmentCreation_Page();
	}

	// IRTD
	// Click on IRTD List box of Equipment page
	public IRTDHubPage click_IRTDTile() throws IOException {
		WebElement irtdbox = driver.findElementByName("IRTD");
		clickOn(irtdbox);
		return new IRTDHubPage();
	}

	// Is IRTD Tile visible

	public boolean Is_IRTDTileVisible() throws IOException {
		WebElement irtdbox = driver.findElementByName("IRTD");
		return IsElementVisibleStatus(irtdbox);
	}

	// Is VRT Tile visible

	public boolean Is_VRTLoggerVisible() throws IOException {

		return IsElementVisibleStatus(VRTLogger);
	}

	// Click on VRT List box of Equipment page
	public VRTLoggerHubPage Click_VRTLogger_listbox() throws IOException {
		clickOn(VRTLogger);
		return new VRTLoggerHubPage();
	}

	// Click AddButton to get Alert message when supervisor does not have default
	// privilege
	public void Alert_ClickAddBtn() throws InterruptedException {
		clickOn(AddButton);
		Thread.sleep(1000);
	}

	// Alert message for non default privilege of supervisor
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

	// Click on back button
	public MainHubPage ClickBackBtn() throws IOException {
		clickOn(Back_btn);
		return new MainHubPage();
	}

	// click Initiate Verification Tab
	public void ClickInitiateVerification() throws IOException {
		WebElement InitiateVerificationTab = driver.findElementByAccessibilityId("SaveButton1");
		clickOn(InitiateVerificationTab);
	}

	// Is EquipmentHeaderTextBlock visible
	public boolean IsEquipmentHeader_Visible() {
		return IsElementVisibleStatus(EquipmentHeaderTextBlock);
	}

	// is InitiateVerification button visible

	// Fetch EquipmentDueCalibration_Count and IRTD Equipment type text

	public String FetchTxt_DueCalibrationCount_IRTDtype(int j) throws InterruptedException, IOException {
		List<WebElement> Listcounts = driver.findElementByName("IRTD").findElements(By.className("TextBlock"));
		return Listcounts.get(j).getText();

	}

	// Fetch EquipmentDueCalibration_Count and IRTD Equipment type text

	public String FetchTxt_DueCalibrationCount_VRTLoggertype(int j) throws InterruptedException, IOException {
		List<WebElement> Listcounts = driver.findElementByName("VRTLogger").findElements(By.className("TextBlock"));
		// System.out.println(Listcounts.size());
		return Listcounts.get(j).getText();

	}
}
