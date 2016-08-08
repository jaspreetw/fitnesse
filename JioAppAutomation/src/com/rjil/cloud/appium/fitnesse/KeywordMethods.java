package com.rjil.cloud.appium.fitnesse;

/**
 * @author kalpesh.pingale
 *The class consist of device specific functions with actual logic
 *
 */
import io.appium.java_client.MobileElement;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import com.github.genium_framework.appium.support.server.AppiumServer;
import com.github.genium_framework.server.ServerArguments;
import com.rjil.cloud.appium.logger.Logger;
import com.rjil.cloud.appium.utility.AppiumInventory;

public class KeywordMethods {

	private static org.apache.log4j.Logger logger = Logger.logger;
	static AppiumMethods method = new AppiumMethods();
	static AppiumServer appiumServer = null;

	public String assertText(String sText) {
		String result = "False";
		try {
			if (method.getElementByName(sText).getSize() != null) {
				logger.info("Text " + sText + " is Present On Screen ");
				result = "True";
			}
		} catch (Exception e) {
			logger.info("Text " + sText + " is Not present On Screen ");
			result = "False";
		}
		return result;
	}

	public boolean launchAppium(int portNo) {
		ServerArguments serverArguments = new ServerArguments();
		serverArguments.setArgument("--address", "0.0.0.0");
		// serverArguments.setArgument("--chromedriver-port", 9516);
		// serverArguments.setArgument("--bootstrap-port", 4725);
		serverArguments.setArgument("--port", portNo);
		serverArguments.setArgument("--no-reset", true);
		serverArguments.setArgument("--local-timezone", true);
		// File apkDirectory = new File("D:/Appium/)");
		// AppiumServer appiumServer = new AppiumServer(serverArguments);
		appiumServer = new AppiumServer(serverArguments);
		logger.info("launching appium server");
		appiumServer.startServer();
		logger.info("appium started");
		System.out.println("is appium server running - "
				+ appiumServer.isServerRunning());
		return appiumServer.isServerRunning();
	}

	public boolean stopAppium() {
		appiumServer.stopServer();
		System.out.println("is appium server running - "
				+ appiumServer.isServerRunning());
		return appiumServer.isServerRunning();

	}

	/*
	 * Left - x1=.8, x2=.2, y1=2, y2=2 Right- x1=.2, x2=.8, y1=2, y2=2 Up -
	 * x1=2, x2=2, y1=.8, y2=.2 Down - x1=.8, x2=.2, y1=2, y2=2
	 */
	// TODO
	public void swipeScreen(String direction, int durations) {
		Dimension size = method.getSizeOfWindow();
		logger.info("Window Size--->" + size);
		method.setContext("NATIVE_APP");
		int startx = 0, endx = 0, starty = 0, endy = 0;
		if (direction.equalsIgnoreCase("down")) {
			logger.info("Scrolling Down");
			startx = (int) (size.width / 2);
			endx = (int) (size.width / 2);
			starty = (int) (size.height * .8);
			endy = (int) (size.height * .2);
			logger.info("Start X- " + startx + " Start Y- " + starty
					+ " End X-" + endx + " End Y-" + endx);
		} else if (direction.equalsIgnoreCase("up")) {
			logger.info("Scrolling Up");
			startx = (int) (size.width / 2);
			endx = (int) (size.width / 2);
			starty = (int) (size.height * .2);
			endy = (int) (size.height * .8);
			logger.info("Start X- " + startx + " Start Y- " + starty
					+ " End X-" + endx + " End Y-" + endx);
		} else if (direction.equalsIgnoreCase("left")) {
			logger.info("Scrolling Left");
			startx = (int) (size.width * 0.2);
			endx = (int) (size.width * 0.8);
			starty = (int) (size.height / 2);
			endy = (int) (size.height / 2);
			logger.info("Start X- " + startx + " Start Y- " + starty
					+ " End X-" + endx + " End Y-" + endx);
		} else if (direction.equalsIgnoreCase("right")) {
			logger.info("Scrolling Right");
			startx = (int) (size.width * 0.8);
			endx = (int) (size.width * 0.2);
			starty = (int) (size.height / 2);
			endy = (int) (size.height / 2);
			logger.info("Start X- " + startx + " Start Y- " + starty
					+ " End X-" + endx + " End Y-" + endx);
		}
		method.swipe(startx, starty, endx, endy, durations);
	}

	public void swipeOnFileElem(String sText) {
		MobileElement elem = (MobileElement) method.getElementByName(sText);
		Point p = elem.getCenter();
		logger.info("Center Point-->" + p);
		// Point location = elem.getLocation();
		// Dimension size = elem.getSize();
		method.swipe(p.getX(), p.getY(), 0, p.getY(), 200);

	}

	public String enabledElem(String xpath) {
		boolean result = method.isElementEnabled(xpath);
		logger.info("Enabled Element value is-->" + result);
		return result + "";
	}

	public String checkedElem(String xpath) {
		String result = method.isElementChecked(xpath);
		logger.info("Checked Element value is-->" + result);
		return result;
	}

	public void selectImage(String xpath, int number) {
		for (int i = 1; i <= number; i++) {
			String xPathString = method.getXpathOfElement(xpath,
					AppiumInventory.AndroidId);
			xPathString = xPathString.replaceFirst("imageno", i + "");
			logger.info("Xpath after Replace->>" + xPathString);
			method.getElementByXpath(xPathString).click();
		}
	}

	public String selectedElem(String xpath) {
		boolean result = method.isElementSelected(xpath);
		logger.info("Selected Element value is-->" + result);
		return result + "";
	}
}
