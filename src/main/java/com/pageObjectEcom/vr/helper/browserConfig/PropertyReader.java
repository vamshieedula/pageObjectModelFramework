package com.pageObjectEcom.vr.helper.browserConfig;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader implements ConfigReader {

	private static FileInputStream file;
	public static Properties OR;
	
	public  PropertyReader() {
		try {
			String filepath = System.getProperty("user.dir")+"\\src\\main\\resources\\configfile\\config.properties";
			file = new FileInputStream(new File(filepath));
			OR = new Properties();
			OR.load(file);
			
			String filepath1 = System.getProperty("user.dir")+"\\src\\main\\resources\\configfile\\config1.properties";
			file = new FileInputStream(new File(filepath1));
			OR = new Properties();
			OR.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int getImplicitWait() {
		return Integer.parseInt(OR.getProperty("implicitwait"));
	}

	@Override
	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("explicitwait"));

	}

	@Override
	public int getPageLoadTime() {
		return Integer.parseInt(OR.getProperty("pageloadtime"));

	}

	@Override
	public BrowserType getBrowserType() {
		return BrowserType.valueOf(OR.getProperty("browserType"));
	}
	
	
	//if condition is to read the data from pom.xml file
	public String getUrl() {
		if(System.getProperty("url")!=null){
			return System.getProperty("url");
		}
		return OR.getProperty("applicationUrl");
	}

	public String getUserName() {
		if(System.getProperty("userName")!=null){
			return System.getProperty("userName");
		}
		return OR.getProperty("userName");
	}

	public String getPassword() {
		if(System.getProperty("password")!=null){
			return System.getProperty("password");
		}
		return OR.getProperty("password");
	}


}
