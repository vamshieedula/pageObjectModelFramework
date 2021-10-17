package com.pageObjectEcom.vr.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.pageObjectEcom.vr.helper.assertions.VerificationHelper;
import com.pageObjectEcom.vr.helper.browserConfig.ObjectReader;
import com.pageObjectEcom.vr.helper.logger.LoggerHelper;
import com.pageObjectEcom.vr.helper.wait.WaitHelper;
import com.pageObjectEcom.vr.testBase.TestBase;

public class MyAccountPage {

	
	private WebDriver driver;
	private WaitHelper waitHelper;
	private final Logger log = LoggerHelper.getLogger(MyAccountPage.class);

	
	@FindBy(xpath="//*[contains(text(),'Welcome to your account. Here you can manage all of your personal information and orders.')]")
	public WebElement yourAccountPageMessage;
	
	@FindBy(xpath="//*[contains(text(),'Order history and details')]")
	public WebElement orderHistoryAndDetail;
	
	@FindBy(xpath="//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")
	public WebElement myPersonalInformation;
	
	@FindBy(xpath="//*[@id=\"center_column\"]/div/div[2]/ul/li/a/span")
	public WebElement wishList;
	
	@FindBy(xpath="//*[@id=\"center_column\"]/h1")
	public WebElement myAccount;
	
	
	public MyAccountPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(orderHistoryAndDetail, ObjectReader.reader.getImplicitWait());
		TestBase.test.log(Status.INFO, "MyAccountPage object created");
		new TestBase().getNavigationScreen(driver);
	}
	
	
	public void clickOnWishLists() {
		wishList.click();
		log.info("clicked on "+wishList.getText());
		TestBase.test.log(Status.INFO, "clicked on "+wishList.getText());
	}
	
	public void clickOnOrederHistoryAndDetails() {
		orderHistoryAndDetail.click();
		log.info("clicked on "+orderHistoryAndDetail.getText());
		TestBase.test.log(Status.INFO, "clicked on "+orderHistoryAndDetail.getText());
	
	}
	
	
	public boolean isYourAccountPageMessage() {
		return new VerificationHelper(driver).isDisplayed(yourAccountPageMessage);
	}
	
	
}
