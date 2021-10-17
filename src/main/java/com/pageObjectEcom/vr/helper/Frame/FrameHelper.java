package com.pageObjectEcom.vr.helper.Frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pageObjectEcom.vr.helper.logger.LoggerHelper;

public class FrameHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(FrameHelper.class);

	public FrameHelper(WebDriver driver) {
		this .driver = driver;
	}
	
	
	/**
	 * this will switch to frame on basis of index
	 * @param frameIndex
	 */
	public void switchToFrame(int frameIndex) {
		driver.switchTo().frame(frameIndex);
		log.info("switched to : " + frameIndex + " frame");
	}

	/**
	 * this will switch to frame on basis of frameName
	 * @param frameName
	 */
	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
		log.info("switched to : "+frameName+" frame");
	}
	
	
	/**
	 * this will switch to frame on basis of element
	 * @param element
	 */
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
		log.info("switched to frame : "+element.toString());
	}
	
	
}
