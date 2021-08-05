/**
* @author manoj.ghadei
*
*/

package com.vrt.pages;

import java.io.IOException;
import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class RW_EditParametersPage extends BaseClass {

	// Page element variable declaration definition
	WebElement EditCalculations = null;

	// Page element Initialize method
	private void initElements() {
		EditCalculations = driver.findElementByAccessibilityId("CustomizeCalculationsButton");

	}

	// Constructor for initializing the page elements
	RW_EditParametersPage() throws IOException {
		super();
		initElements();
	}

// Release memory
	public void resetWebElements() {
		EditCalculations = null;

	}

// click on next btn n select No Btn to navigate CustomizedCalculation Page
	public RW_CustomizedCalculationPage click_EditCalculations_Tab() throws IOException {
		clickOn(EditCalculations);
		return new RW_CustomizedCalculationPage();

	}

}
