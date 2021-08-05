/**
* @author manoj.ghadei
*
*/

package com.vrt.pages;

import java.io.IOException;
import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class RW_DefineQualExposurePage extends BaseClass {

	// Page element variable declaration definition
	WebElement ReportParameters = null;

	// Page element Initialize method
	private void initElements() {
		ReportParameters = driver.findElementByAccessibilityId("_nextStageButton");

	}

	// Constructor for initializing the page elements
	RW_DefineQualExposurePage() throws IOException {
		super();
		initElements();
	}

// Release memory
	public void resetWebElements() {
		ReportParameters = null;

	}

// click on next btn n select No Btn to navigate Edit parameters page
//Edit Parameters
	public RW_EditParametersPage click_ReportParameters_Btn() throws IOException, InterruptedException {

		clickOn(ReportParameters);

		WebElement No = driver.findElementByAccessibilityId("Button0");
		clickOn(No);
		return new RW_EditParametersPage();

	}

}
