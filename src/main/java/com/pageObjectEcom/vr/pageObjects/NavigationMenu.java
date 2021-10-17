package com.pageObjectEcom.vr.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageObjectEcom.vr.helper.browserConfig.ObjectReader;
import com.pageObjectEcom.vr.helper.logger.LoggerHelper;
import com.pageObjectEcom.vr.helper.wait.WaitHelper;
import com.pageObjectEcom.vr.testBase.TestBase;

public class NavigationMenu {
	
	
	private WebDriver driver;
	private WaitHelper waitHelper;
	private final Logger log = LoggerHelper.getLogger(NavigationMenu.class);

	@FindBy(xpath="//*[@id=\"block_top_menu\"]/ul/li[1]/a")
	public WebElement womenMenu;
	
	@FindBy(xpath="//*[@id=\"block_top_menu\"]/ul/li[2]/a")
	public WebElement dressesMenu;
	
	@FindBy(xpath="//*[@id=\"block_top_menu\"]/ul/li[3]/a")
	public WebElement tshirtMenu;
	
	
	public NavigationMenu(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(womenMenu, ObjectReader.reader.getImplicitWait());
		TestBase.logExtentReport("NavigationMenu object created");
		new TestBase().getNavigationScreen(driver);
	}
	
	
	public void mouseOver(String data) {
		log.info("moving cursor to: "+data);
		TestBase.logExtentReport("moving cursor to: "+data);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[contains(text(), '"+data+"')]"))).build().perform();
	}
	
	public ProductCategoryPage clickOnItem(String data) {
		log.info("clicking on: "+data);
		TestBase.logExtentReport("clicking on: "+data);
		driver.findElement(By.xpath("//*[contains(text(),'"+data+"')]")).click();
		return new ProductCategoryPage(driver);
	}
	
	public ProductCategoryPage clickOnMenu(WebElement element) {
		log.info("clicking on: "+element.getText());
		TestBase.logExtentReport("clicking on: "+element.getText());
		element.click();
		return new ProductCategoryPage(driver);
	} 
	
	
	
}
