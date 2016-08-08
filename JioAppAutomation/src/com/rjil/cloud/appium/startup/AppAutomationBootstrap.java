package com.rjil.cloud.appium.startup;

/**
 * author kalpesh.pingale
 * This class used to: 
 * 1.Set capabilities of appium 
 * 2.Set the project specific element-id mapping file
 * 3.Initialize drivers
 */

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.rjil.cloud.appium.logger.Logger;
import com.rjil.cloud.appium.utility.AppiumInventory;

public class AppAutomationBootstrap {
	//protected static AppiumDriver driver;
	protected static AppiumDriver driver;
	private static org.apache.log4j.Logger logger = Logger.logger;
		
	public static void appSetUp(String product, String platform, String appName,
			String url, String deviceName, String onReset, String fullReset, String bundleId, String udid,String propertyFile) throws MalformedURLException {

		//To replace name of fitnesse property File
		String fitnessePropFile=propertyFile.replace(".", "//");
		logger.info("**********"+fitnessePropFile+"*********");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		logger.info("App is -->"+appName);
		//File app = new File(appName);
		//File app = new File("/Users/jiotestdevice/Documents/Appinventory/Tej.ipa");
		capabilities.setCapability("platformName", platform);
		capabilities.setCapability("deviceName", deviceName); // Mandatory
		//capabilities.setCapability("app", app);
		capabilities.setCapability("fullReset", fullReset);   //if true- uninstall app for every run
		capabilities.setCapability("noReset",onReset);    //if true- doesn't clear data app data for every run
		if (platform.equalsIgnoreCase("Android")) {
			logger.info("In Android");
			capabilities.setCapability("platformVersion", "17"); // Not mandatory
			driver = new AndroidDriver(new URL(url), capabilities);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} 
		else if (platform.equalsIgnoreCase("ios")) {
			capabilities.setCapability("platformVersion", "8.4");
			capabilities.setCapability("bundleId", bundleId);
			capabilities.setCapability("udid",udid);
			System.out.println("url is "+url);
			//driver = new IOSDriver(new URL(url),capabilities);
			driver = new IOSDriver(new URL(url),capabilities);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		
		//TODO - change the property file
		if (product.equalsIgnoreCase("jiotej")) {
			try {
				logger.info("in jiotej");
				FileUtils.copyFile(new File(
						AppiumInventory.FitnesseRoot+fitnessePropFile+"//content.txt"), new File(
								AppiumInventory.Mobile_Element_Id));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (product.equalsIgnoreCase("jiodrive")) {
			try {
				logger.info("in jio drive");
				FileUtils.copyFile(
						new File("..//AppiumPropertyFile//jiodrive.properties"),
						new File("..//AppiumPropertyFile//mobileElemIDs.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (product.equalsIgnoreCase("ourpics")) {
			try {
				logger.info("in ourpics");
				FileUtils.copyFile(
						new File("..//AppiumPropertyFile//ourpics.properties"),
						new File("..//AppiumPropertyFile//mobileElemIDs.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (product.equalsIgnoreCase("switchNwalk")) {
			try {
				logger.info("in switchnwalk");
				FileUtils.copyFile(
						new File("..//AppiumPropertyFile//switchNwalk.properties"),
						new File("..//AppiumPropertyFile//mobileElemIDs.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			logger.info("no product specified..");
		}

	}

	public static void teardownApp() {
		driver.closeApp();
		driver.quit();
	}

}
