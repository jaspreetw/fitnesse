package com.rjil.cloud.appium.fitnesse;

import java.net.MalformedURLException;


import com.rjil.cloud.appium.logger.Logger;
import com.rjil.cloud.appium.startup.AppAutomationBootstrap;

public class jioapptesting extends AppAutomationBootstrap {

	static KeywordMethods keyword = new KeywordMethods();
	static AppiumMethods method = new AppiumMethods();
	private static org.apache.log4j.Logger logger = Logger.logger;

	public boolean launchAppiumServer(int portNo) {
		return keyword.launchAppium(portNo);
	}
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		jioapptesting jt =new jioapptesting();
		System.out.println("Test Started");
		jt.startApp("jiotej","ios", "/Users/jiotestdevice/Documents/Appinventory/Tej.ipa","http://127.0.0.1:4723/wd/hub","iPhone 5s","false","false","com.reliance.jio.tej","60ada5268a4afb9f11ee03a3602dc77e6aab7386","AppiumSuite.PropertyFile");
		jt.tapOnElement("Plus Button");		
	}

	public boolean stopAppiumServer() {
		return keyword.stopAppium();
	}

	public void startApp(String product, String platform, String apk,
			String url, String deviceName, String noReset, String fullReset,
			String bundleId, String udid, String propertyFile)
			throws InterruptedException, MalformedURLException {
		this.waitFor(20);
		super.appSetUp(product, platform, apk, url, deviceName, noReset,
				fullReset, bundleId, udid, propertyFile);
		logger.info("Test started");
	}

	public void endTest() throws InterruptedException, MalformedURLException {
		super.teardownApp();
		logger.info("Test ended");
	}

	public void waitFor(int iSec) {
		method.driverWait(iSec * 1);
	}
	
	public void tapOnElement(String elementName){
		method.tapOnElement(elementName);
	}

	public void clickOnElement(String xPath) {
		method.clickAppElement(xPath);
	}

	public void clickOnText(String tName) {
		method.clickText(tName);
	}

	public void enterTextOnMobileField(String xPath, String sText) {
		method.enterTextInAppField(xPath, sText);
	}

	public String assertTextOnDevice(String sText) {
		String result = keyword.assertText(sText);
		return result;
	}

	public void deviceKey(String sKey) {
		method.tapDeviceKey(sKey);
	}

	public void swipeOnScreen(String direction, int duration) {
		keyword.swipeScreen(direction, duration);
	}

	public void swipeOnFile(String eName) {
		keyword.swipeOnFileElem(eName);
	}

	public void scrollToElement(String eName) {
		method.scrollToElem(eName);
	}

	public String enabledElement(String xpath) {
		String result = keyword.enabledElem(xpath);
		return result;
	}

	public String checkedElement(String xpath) {
		String result = keyword.checkedElem(xpath);
		return result;
	}

	public String selectedElement(String xpath) {
		String result = keyword.selectedElem(xpath);
		return result;
	}

	public void selectImageToUpload(String iXpath, int count) {
		keyword.selectImage(iXpath, count);
	}

	public void longPressOnElement(String elem) {
		method.longPress(elem);
	}

	public String getTextOnAppElement(String xpath) {
		String text = method.getText(xpath);
		return text;
	}

	public void switchToPopUpWindow() throws Exception {
		method.switchToPopUp();
	}

}
