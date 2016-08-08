package com.rjil.cloud.appium.utility;

/**
 * @author kalpesh.pingle
 * 
 * This class is used to read the properties from the property file.
 * 
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.rjil.cloud.appium.logger.Logger;

public class PropertiesReader {
	Properties mobileAppIDs = new Properties();
//	Properties classesOfDropDown = new Properties();
//	Properties idsOfInputFields = new Properties();
//	Properties config = new Properties();
	private static org.apache.log4j.Logger logger = Logger.logger;

	public String getKeyValues(String key, String fileName) {
		try {
			InputStream input = null;
			String value = "";
			switch (fileName) {
			case "mobileElemIDs":
				logger.info("Key and File-->" + key + " and " + fileName);
				// TODO - need to change properties file
				input = new FileInputStream(AppiumInventory.Mobile_Element_Id);
				mobileAppIDs.load(input);
				value = mobileAppIDs.getProperty(key);
				break;
			default:
				value = "No element";
				break;
			}
			return value;
		} catch (IOException e) {
			e.printStackTrace();
			return "No ID captured";
		}
	}// end of getKeyValues
}
