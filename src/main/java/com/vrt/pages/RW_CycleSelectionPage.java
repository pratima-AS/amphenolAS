/**
* @author ruchika behura
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

public class RW_CycleSelectionPage extends BaseClass {

	// Page element variable declaration definition
	WebElement DefineAddlCycles = null;

	// Page element Initialize method
	private void initElements() {
		DefineAddlCycles = driver.findElementByAccessibilityId("_nextStageButton");

	}

	// Constructor for initializing the page elements
	RW_CycleSelectionPage() throws IOException {
		super();
		initElements();
	}

// Release memory
	public void resetWebElements() {
		DefineAddlCycles = null;

	}

// click on next btn

	public RW_DefineQualExposurePage click_DefineAddlCycles_Btn() throws IOException {
		clickOn(DefineAddlCycles);
		return new RW_DefineQualExposurePage();
	}

}
