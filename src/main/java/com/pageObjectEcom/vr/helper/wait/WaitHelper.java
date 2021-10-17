package com.pageObjectEcom.vr.helper.wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pageObjectEcom.vr.helper.logger.LoggerHelper;

public class WaitHelper {
	
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);
	
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
		log.info("waitHelper object created");
	}
	
	/**
	 * This is Inmplicit wait method
	 * @param timeout
	 * @param unit
	 */
	public void setImplicitWait(long timeout, TimeUnit unit) {
		
		log.info("implicitWait set to: "+timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit);
		
	}
	
	/**
	 * This will help us to webdriverwait object
	 * @param timeOutInSeconds
	 * @param pollingEveryInMilliSec
	 * @return
	 */
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMilliSec) {
		
		WebDriverWait wait  = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(Duration.ofMillis(pollingEveryInMilliSec));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}
	
	/**
	 * this method will makes element to be visible
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMilliSec
	 */
			
	public void waitForElementVisible(WebElement element, int timeOutInSeconds, int pollingEveryInMilliSec) {
		log.info("waiting for: "+element.toString()+"for : "+timeOutInSeconds+" seconds");
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMilliSec);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}
	
	/**
	 * This method will make your element is clickable
	 * @param element
	 * @param timeOutInSeconds
	 */
	
	public void waitForElementClickable(WebElement element, int timeOutInSeconds) {
		log.info("waiting for: "+element.toString()+"for : "+timeOutInSeconds+" seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("element is clickable now");
	}
	
	/**
	 * this method will make sure invisibilityof element
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	
	public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds) {
		log.info("waiting for: "+element.toString()+"for : "+timeOutInSeconds+" seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("element is invisible now");
		return status;
	
	}
	
	/**
	 * this method wait for frameToBeAvailableAndSwitchToIt 
	 * @param element
	 * @param timeOutInSeconds
	 */
	
	public void waitForframeToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds) {
		log.info("waiting for: "+element.toString()+"for : "+timeOutInSeconds+" seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("frame is available and switched");
	
	}
	
	/**
	 * this method will give us fluentwait object
	 * @param timeOutInSeconds
	 * @param pollingEveryInMilliSec
	 * @return
	 */
	private Wait<WebDriver> getfluentWait(int timeOutInSeconds, int pollingEveryInMilliSec) {
		Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(pollingEveryInMilliSec)).ignoring(NoSuchElementException.class);
		return fWait;
	}
	
	/**
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMilliSec
	 */
	public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMilliSec) {
		Wait<WebDriver> fWait = getfluentWait(timeOutInSeconds, pollingEveryInMilliSec);
		fWait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	
	
	public void pageLoadTime(long timeout, TimeUnit unit) {
		log.info("waiting for page to load for : "+unit+ "seconds");
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
		log.info("page is loaded");
		
	}
	
	
	public void waitForElement(WebElement element, int timeOutInSeconds) {
		log.info("waiting for: "+element.toString()+" for:"+timeOutInSeconds+" seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}
	
	
	
	
}
