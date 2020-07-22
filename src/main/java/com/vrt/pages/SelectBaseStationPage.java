package com.vrt.pages;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vrt.base.BaseClass;

public class SelectBaseStationPage extends BaseClass{
	
	// Calculation page element variable declaration definition
	WebElement SelectBaseStationTitle = null;
	
	
	private void initializeEelements() {
		SelectBaseStationTitle = driver.findElementByName("Select Base Station");
		
		
	}
	
	SelectBaseStationPage() throws IOException {
		super();
		initializeEelements();
	}
	
	// Release memory
	public void resetWebElements() {
		SelectBaseStationTitle = null;
		
	}
	
 // Check the presence of Select Base Station header title
	 public boolean SelectBaseStationTitle_state() {
	 return IsElementVisibleStatus(SelectBaseStationTitle);
	}

}
