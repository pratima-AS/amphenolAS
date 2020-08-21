package com.vrt.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vrt.base.BaseClass;

public class OverlayWiringImagePage extends BaseClass {

	// Calculation page element variable declaration definition
	WebElement OverlayWiringImage_Title = null;
	WebElement Print_Button = null;
	WebElement OverlayImageClose_button = null;
	WebElement Group_Name = null;

	private void initElements() {
		OverlayWiringImage_Title = driver.findElementByName("Overlay Wiring Image");
		Print_Button = driver.findElementByAccessibilityId("PrintButton");
		OverlayImageClose_button = driver.findElementByAccessibilityId("OverlayImageClosebutton");
		Group_Name = driver.findElementByAccessibilityId("tblName");
	}

	OverlayWiringImagePage() throws IOException {
		super();
		initElements();
	}

	// Release memory
	public void resetWebElements() {
		OverlayWiringImage_Title = null;
		Print_Button = null;
		OverlayImageClose_button = null;
		Group_Name = null;
	}

// Check the presence of Select Base Station page
	public boolean IsOverlayWiringPageTitle_state() {
		return IsElementVisibleStatus(OverlayWiringImage_Title);
	}

//Print_Button_Visible
	public boolean PrintButton_State() {
		return IsElementVisibleStatus(Print_Button);
	}

	// Close_Button_Visible
	public boolean Close_button_State() {
		return IsElementVisibleStatus(OverlayImageClose_button);
	}

	// Click On Close btn
	public assetDetailsPage click_OverlayImage_Close_button() throws IOException {
		clickOn(OverlayImageClose_button);
		return new assetDetailsPage();
	}

	// Group_Visible_Visible
	public boolean Group_Visible() {
		return IsElementVisibleStatus(Group_Name);
	}

	// Click on PrintIcon
	public void Click_PrintIcon() {
		clickOn(Print_Button);
	}

	// GroupWiring_Report_Visible
	public boolean GroupWiring_Report_State() {
		WebElement GroupWiringReportButton = driver.findElementByAccessibilityId("GroupWiringReportButton");
		return IsElementVisibleStatus(GroupWiringReportButton);
	}

	// All_GroupOverlayReport_State
	public boolean All_GroupOverlayReport_State() throws AWTException {
		WebElement All_GroupOverlayReport = driver.findElementByAccessibilityId("CreateFullWiringReportButton");
		return IsElementVisibleStatus(All_GroupOverlayReport);
	}

	// GroupOverlayRprtGenerate_Popupvisible
	public void click_GroupOverlayRprtGenerate_Popup() throws InterruptedException, AWTException

	{
		Click_PrintIcon();
		Thread.sleep(2000);
		WebElement GroupWiringReport_Btn = driver.findElementByAccessibilityId("GroupWiringReportButton");

		clickOn(GroupWiringReport_Btn);
		Thread.sleep(2000);
		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

	}

//All_GroupOverlayReportGenerate_Popupvisible
	public void click_All_GroupOverlayReportGenerate_printOption() throws InterruptedException, AWTException

	{
		Click_PrintIcon();
		Thread.sleep(2000);
		WebElement CreateFullWiringReport_Button = driver.findElementByAccessibilityId("CreateFullWiringReportButton");

		clickOn(CreateFullWiringReport_Button);
		Thread.sleep(2000);
		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

}
