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

public class RWFileSelctionPage extends BaseClass {

	// Page element variable declaration definition
	WebElement AssetsName = null;
	WebElement AssetsID = null;

	// Page element Initialize method
	private void initElements() {
		AssetsName = driver.findElementByAccessibilityId("AssetsNameTextBlock");
		AssetsID = driver.findElementByAccessibilityId("EqIdTextBox");

	}

	// Constructor for initializing the page elements
	RWFileSelctionPage() throws IOException {
		super();
		initElements();
	}

// Release memory
	public void resetWebElements() {
		AssetsName = null;
		AssetsID = null;

	}

// verify the presence of  Asset Name

	public boolean assetDetailTitle_Visible() {
		return IsElementVisibleStatus(AssetsName);
	}

// verify the presence of Asset Id

	public boolean assetID_Visible() {
		return IsElementVisibleStatus(AssetsID);
	}

// verify the presence of Asset Type (HARD CODE because This asset  has sync In through application)

	public boolean assetType_Visible() {
		WebElement AssetsType = driver.findElementByName("HeatBath");

		return IsElementVisibleStatus(AssetsType);
	}

// verify the presence of Manufacturer  (HARD CODE because This asset  has sync In through application)

	public boolean assetManufacturer_Visible() {
		WebElement AssetsManufacturer = driver.findElementByName("Kaye");

		return IsElementVisibleStatus(AssetsManufacturer);
	}

}
