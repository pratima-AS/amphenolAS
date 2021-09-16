/**
* @author manoj.ghadei
*
*/

package com.vrt.pages;

import java.io.IOException;
import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class RW_CustomizedCalculationPage extends BaseClass {

	// Page element variable declaration definition
	WebElement Customize = null;

	// Page element Initialize method
	private void initElements() {
		//Customize = driver.findElementByName("Customize");

	}

	// Constructor for initializing the page elements
	RW_CustomizedCalculationPage() throws IOException {
		super();
		initElements();
	}

// Release memory
	public void resetWebElements() {
		Customize = null;

	}

// Customize state

	public boolean is_Customize_Visible() {

		boolean status = false;
		try {
			status = driver.findElementByName("Customize").isDisplayed();
		} catch (Exception e) {
			System.out.println("Customize link is not available, as lethality is not allowed");

		}
		return status;
	}

}
