package com.pageObjectEcom.vr.helper.alert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.pageObjectEcom.vr.helper.logger.LoggerHelper;

public class AlertHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(AlertHelper.class);

	public AlertHelper(WebDriver driver) {
		this.driver = driver;
		log.info("AlertHleper Object is created...");
		
	}
	
	
	public Alert getAlert() {
		log.info("alert test: "+driver.switchTo().alert().getText());
		return driver.switchTo().alert();
	}
	
	
	public void acceptAlert() {
		getAlert().accept();
		log.info("alert is accepted");
	}
	
	
	public void dismissAlert() {
		getAlert().dismiss();
		log.info("alert is dismissed");
	}
	
	
	public String getAlertText() {
		String text = getAlert().getText();
		log.info("alert text : "+text);
		return text;
	}
	
	
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			log.info("alert is present");
			return true;
		}catch(NoAlertPresentException e) {
			log.info(e.getCause());
			return false;
		}
	}
	
	
	public void acceptAlertIfPresent() {
		if(isAlertPresent()) {
			acceptAlert();
		}else {
			log.info("Alert is not present...");
		}
	}

	
	public void dismissAlertIfPresent() {
		if(isAlertPresent()) {
			dismissAlert();
		}else {
			log.info("Alert is not present...");
		}
	}
	
	
	public void acceptPrompt(String text) {
		if(isAlertPresent()) {
			Alert alert = getAlert();
			alert.sendKeys(text);
			alert.accept();
			log.info("alert text: "+text);
		}
	}
	
	
	
}
