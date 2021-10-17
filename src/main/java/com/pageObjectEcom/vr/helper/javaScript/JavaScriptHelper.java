package com.pageObjectEcom.vr.helper.javaScript;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pageObjectEcom.vr.helper.logger.LoggerHelper;

public class JavaScriptHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);
	
	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
		log.info("JavaScriptHelper has been initialised");
	}
	
	
	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		return exe.executeScript(script);
	}
	
	
	public Object executeScript(String script, Object...args) {
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		return exe.executeScript(script);
	}
	
	
	public void scrollToElement(WebElement element) {
		log.info("scrolling to element...");
		executeScript("window.scrollTo(arguments[0],arguments[1]",element.getLocation().x, element.getLocation().y);
	}
	
	
	public void scrollToElementandClick(WebElement element) {
		scrollToElement(element);
		element.click();
		log.info("clicked on the element: "+element.toString());
	}
	
	
	public void scrollIntoView(WebElement element) {
		log.info("scroll till the webelement");
		executeScript("argument[0].scrollIntoView()", element);
	}
	
	
	public void scrollIntoViewandClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info("clicked on the element: "+element.toString());
	}
	
	
	public void scrollDownVertically() {
		log.info("scrolling down vertically...");
		executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	
	public void scrollUpVertically() {
		log.info("scrolling up vertically...");
		executeScript("window.scrollTo(0,-document.body.scrollHeight)");
	}
	
	
	public void scrollDownByPixel(int pixel) {
		executeScript("window.scrollBy(0,"+pixel+")");
	}
	
	
	public void scrollUpByPixel(int pixel) {
		executeScript("window.scrollBy(0,-"+pixel+")");
	}
	
	
	public void zoomInBy100Percentage() {
		executeScript("document.body.style.zoom='100%'");
	}
	
	
	public void zoomInBy60Percentage() {
		executeScript("document.body.style.zoom='40%'");
	}
	
	
	public void clickElement(WebElement element) {
		executeScript("arguments[0].click();",element);
	}

}
