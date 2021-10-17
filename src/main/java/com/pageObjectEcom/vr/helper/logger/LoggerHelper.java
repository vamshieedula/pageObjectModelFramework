package com.pageObjectEcom.vr.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {
	
	private static boolean root = false;
	
	public static Logger getLogger(Class cls) {
		if(root) {
			return Logger.getLogger(cls);
		}
		PropertyConfigurator.configure(System.getProperty("user.dir") +"\\src\\main\\resources\\configfile\\log4j.properties");
		root = true;
		return Logger.getLogger(cls);
	}

}
