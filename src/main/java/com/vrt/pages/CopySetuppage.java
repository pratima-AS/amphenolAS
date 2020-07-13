/**
 * @author manoj.ghadei
 *
 */

package com.vrt.pages;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vrt.base.BaseClass;
import com.vrt.utility.TestUtilities;

public class CopySetuppage extends BaseClass {

	// Page element variable declaration definition
	WebElement SelectAll_ChkboxField = null;
	WebElement Copysetup_btn = null;
	WebElement CopySetupTitle = null;



	// Page element Initialize method
	private void initElements() {
		SelectAll_ChkboxField = driver.findElementByAccessibilityId("chkSelectAll");	
		
		Copysetup_btn = driver.findElementByAccessibilityId("CopySetupButton");	
		CopySetupTitle = driver.findElementByName("Copy Setup");
		
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
		}
	
	// Click on Selectall check box
	
	//
	
	// Verify copy successful message displayed
			public boolean CopySetupPage_Title() {
				return IsElementVisibleStatus(CopySetupTitle);
			}
			
	
	public void Click_Selectall_chkbox()
	{
		clickOn(SelectAll_ChkboxField);
	}
	
 //Click on copy button
	public void click_copy_Btn() {
		clickOn(Copysetup_btn);
	}
	
	// click on yes btn 
	
	public void Yes_alert() {
		WebElement YesAlert_btn = driver.findElementByName("Yes");
		clickOn(YesAlert_btn);
	}
	
	
	// Verify copy successful message displayed
		public boolean copysuccessfulmessage() {
			WebElement dmt = driver.findElementByAccessibilityId("displayMessageTextBlock");
			return IsElementVisibleStatus(dmt);
		}
		
	
}
