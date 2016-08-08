package com.rjil.cloud.appium.fitnesse;

import io.appium.java_client.MobileElement;

/**
 * author kalpesh.pingale
 * 
 * This class contains ONLY the basic function with no logic defined.
 */

import io.appium.java_client.TouchAction;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rjil.cloud.appium.logger.Logger;
import com.rjil.cloud.appium.startup.AppAutomationBootstrap;
import com.rjil.cloud.appium.utility.AppiumInventory;
import com.rjil.cloud.appium.utility.PropertiesReader;

public class AppiumMethods extends AppAutomationBootstrap {

	// Appium methods which can be reuse

	private static org.apache.log4j.Logger logger = Logger.logger;
	static PropertiesReader propertiesReader = new PropertiesReader();

	public void driverWait(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public Dimension getSizeOfElement(String sElement){ return
	 * driver.findElement(By.name(sElement)).getSize(); }
	 */

	public Dimension getSizeOfWindow() {
		return driver.manage().window().getSize();
	}

	public void setContext(String context) {
		driver.context(context);
	}
	
	public void tapOnElement(String sElement) {
		MobileElement btn = (MobileElement) getMobileElement(sElement);
		   btn.tap(1, 1000);
	}

	public void swipe(int startx, int starty, int endx, int endy, int durations) {
		driver.swipe(startx, starty, endx, endy, durations);
	}

	public void enterTextInAppField(String sElement, String sText) {
		WebElement webElem = getMobileElement(sElement);
		webElem.clear();
		webElem.sendKeys(sText);
		logger.info("Enter text in Mobile Field is-->" + sText);
	}

	public void clickAppElement(String sElement) {
		WebElement webElem = getMobileElement(sElement);
		webElem.click();
		logger.info("Element click on App is-->" + sElement);
	}

	public void clickText(String tName) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement webElem = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.name(tName)));
		webElem.click();
		logger.info("Text clicked on App is-->" + tName);

	}

	public String getXpathOfElement(String sElement, String sPropertyFile) {
		String xPath = propertiesReader.getKeyValues(sElement, sPropertyFile);
		logger.info(sElement + " xPath is-->" + xPath);
		return xPath;
	}

	public void longPress(String sElem) {
		WebElement webElem = getMobileElement(sElem);
		TouchAction action = new TouchAction(driver);
		action.longPress(webElem).release().perform();
		logger.info("Long Press On element-->" + sElem);
	}

	public String getText(String xpath) {
		WebElement webElem = getMobileElement(xpath);
		logger.info("Text on element " + xpath + "webElem.getText()");
		return webElem.getText();
	}

	public boolean isElementEnabled(String xpath) {
		WebElement webElem = getMobileElement(xpath);
		logger.info("Text on element " + xpath + "webElem.getText()");
		return webElem.isEnabled();
	}

	public String isElementChecked(String xpath) {
		WebElement webElem = getMobileElement(xpath);
		logger.info("Text on element " + xpath + "webElem.getText()");
		return webElem.getAttribute("checked");
	}

	public boolean isElementSelected(String xpath) {
		WebElement webElem = getMobileElement(xpath);
		logger.info("Text on element " + xpath + "webElem.getText()");
		return webElem.isSelected();
	}

	public void scrollToElem(String eName) {
		driver.context("NATIVE_APP");
		driver.scrollToExact(eName);
	}

	public void tapDeviceKey(String bName) {
		if (bName.equalsIgnoreCase("back")) {
			driver.navigate().back();
		}
	}

	public void switchToPopUp() throws Exception {
//		WebDriverWait wait = new WebDriverWait(driver, 15);
//		wait.until(ExpectedConditions.alertIsPresent());
//		Alert alert = driver.switchTo().alert();
//		alert.accept();
	   
//	        driver.findElement(By.xpath("//UIAButton[2]")).click();
//	        Alert alert = driver.switchTo().alert();
//	        //check if title of alert is correct
//	        assertEquals("Cool title this alert is so cool.", alert.getText());
//	        alert.accept();
	}

	public WebElement getElementByXpath(String path) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(path)));
	}

	public WebElement getElementById(String id) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.id(id)));
	}

	public WebElement getElementByName(String name) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.name(name)));
	}

	public WebElement getMobileElement(String elementId) {
		String xPath = getXpathOfElement(elementId, AppiumInventory.AndroidId);
		logger.info(elementId + " xPath/ID is-->" + xPath);
		WebElement webElem;
		if (xPath.startsWith("//")) {
			webElem = getElementByXpath(xPath);
		} else {
			webElem = getElementById(xPath);
		}
		return webElem;
	}
}
