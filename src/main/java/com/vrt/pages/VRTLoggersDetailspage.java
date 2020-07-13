/**
 * @author ruchika.behura
 *
 */

package com.vrt.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.vrt.pages.EquipmentPage;
import com.vrt.pages.IRTDHubPage;
import com.vrt.utility.TestUtilities;
import com.vrt.base.BaseClass;

public class VRTLoggersDetailspage extends BaseClass {

	IRTDHubPage IRTDHubPage;
	//TestUtilities tu = new TestUtilities();
	//TestUtilities tu;
	// EquipmentHubPage Element definition
	WebElement Reports_Button = null;
	WebElement Documents_Tile = null;
	

	// Button1
	private void initElements() {
	
	Reports_Button = driver.findElementByAccessibilityId("ReportsButton");
	Documents_Tile = driver.findElementByAccessibilityId("DocumentsButton");

	}
	
	VRTLoggersDetailspage() throws IOException {
		super();
		initElements();

	}

	public void Click_VRTVerification_Report(String SN) throws InterruptedException, IOException {

		List<WebElement> VRTVerificationList = driver.findElementByName("VRT.DataModule.Asset.ViewModels.ActivityItemViewModel")
				.findElements(By.className("ListViewItem"));
		
		// Loop for the different serial number created
		for (int i = 0; i < VRTVerificationList.size(); i++) {
			// System.out.println("serial number : " + IrtdList.get(i).getText());

			List<WebElement> VRT_VerificationInfoList = VRTVerificationList.get(i).findElements(By.className("TextBlock"));

			for (int j = 0; j < VRT_VerificationInfoList.size(); j++) {
				// System.out.println("AssetTileInfo: "+IRTDTileInfoList.get(j).getText());

				if (VRT_VerificationInfoList.get(j).getText().contains(SN)) {
					clickOn(VRT_VerificationInfoList.get(j));
					Thread.sleep(1000);
					break;
				}
			}
		}
}

	// Click on Delete Icon
	public void Click_DeleteBtn() throws InterruptedException {
		WebElement DeleteBtn = driver.findElementByAccessibilityId("DeleteButton");
		clickOn(DeleteBtn);

	}

	// Click on reports btn
	public void Click_reporttile() {
		clickOn(Reports_Button);
	}

	// UploadDocumentsButton
	public void Upload_Documents(String filename) throws AWTException, IOException, InterruptedException {

		clickOn(Documents_Tile);
		WebElement UploadDocumentsButton = driver.findElementByAccessibilityId("UploadDocumentsButton");
		clickOn(UploadDocumentsButton);
		Thread.sleep(2000);
		TestUtilities tu = new TestUtilities();
		tu.uploadDoc("uploadnote.txt");
		// tu.uploadDoc("uploadnote.txt");
	}
	
	public void CopyToDrive(String filename) throws AWTException, IOException, InterruptedException {
		WebElement Copytodrive = driver.findElementByAccessibilityId("CopyDocumentToDrive");
		clickOn(Copytodrive);
		Thread.sleep(1000);
		TestUtilities tu = new TestUtilities();
		tu.uploadDoc("VRTLoggers");
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

	}
	
  //Modify / Enter EquipmentName
	public void Enter_EquipmentName(String EN)
	{
		WebElement EqpName = driver.findElementByAccessibilityId("AssetNameTextBox");
        clickOn(EqpName);
        ClearText(EqpName);
        enterText(EqpName,EN);
	}
	
	// Save button
	
	public void Click_Save()
	{
		WebElement Save_Button = driver.findElementByAccessibilityId("SaveButton");
        clickOn(Save_Button);
	}
	
	//Click on yes button for delete confirmation
	public void click_Alrt_YesBtn() {
		WebElement alrtMeg_YesBtn = driver.findElementByAccessibilityId("Button1");
		clickOn(alrtMeg_YesBtn);
	}
}





