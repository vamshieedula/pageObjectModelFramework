package com.pageObjectEcom.vr.helper.windows;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.pageObjectEcom.vr.helper.logger.LoggerHelper;

public class WindowHelper {
	
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WindowHelper.class);

	public WindowHelper(WebDriver driver) {
		this.driver = driver;
	}

	
	/**
	 * this method will switch to parent window
	 */
	public void switchToParentWindow() {
		log.info("switching to parent window");
		driver.switchTo().defaultContent();
	}
	
	
	/**
	 * this method will switch to child window based in the index	
	 * @param index
	 */
	public void swithToWindow(int index) {
		Set<String> windows = driver.getWindowHandles();
		int i = 1;
		for(String window : windows) {
			if(i == index) {
				log.info("switched to : "+index+ " window");
				driver.switchTo().window(window);
			}else {
				i++;
			}
		}
		
	}
		
	
	/**
	 * this method closes all the windows and switch to main window
	 */
	public void closeAllTabsandSwitchToMainWindow() {
		
		Set<String> windows = driver.getWindowHandles();
		String mainWindow = driver.getWindowHandle();
		for(String window : windows) {
			if(!window.equalsIgnoreCase(mainWindow)) {
				driver.close();
			}
		}
		log.info("closed all child windows and swiched to mainwindow");
		driver.switchTo().window(mainWindow);
	}
	
	
	public void navigateBack() {
		log.info("navigatind back");
		driver.navigate().back();
	}
	
	public void navigateForward() {
		log.info("navigating forward");
		driver.navigate().forward();
	}
	
	
}
