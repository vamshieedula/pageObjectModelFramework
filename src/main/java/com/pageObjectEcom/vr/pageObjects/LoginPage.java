package com.pageObjectEcom.vr.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.pageObjectEcom.vr.helper.assertions.VerificationHelper;
import com.pageObjectEcom.vr.helper.browserConfig.ObjectReader;
import com.pageObjectEcom.vr.helper.javaScript.JavaScriptHelper;
import com.pageObjectEcom.vr.helper.logger.LoggerHelper;
import com.pageObjectEcom.vr.helper.wait.WaitHelper;
import com.pageObjectEcom.vr.testBase.TestBase;

public class LoginPage {
	
	
	private WebDriver driver;
	private WaitHelper waitHelper;
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);

	
	@FindBy(xpath="//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
	WebElement signin;
	
	
	@FindBy(xpath="//*[@id=\"email\"]")
	WebElement emailAddress;
	
	
	@FindBy(xpath="//*[@id=\"passwd\"]")
	WebElement password;
	
	
	@FindBy(xpath="//*[@id=\"SubmitLogin\"]")
	WebElement submitLogin;
	
	
	@FindBy(xpath="//*[@id='center_column']/p")
	WebElement successMsgObject;
	
	
	@FindBy(xpath="//*[@id=\"email_create\"]")
	WebElement registrationEmailAddress;
	
	
	@FindBy(xpath="//*[@id=\"SubmitCreate\"]")
	WebElement createAccount;
	
	
	@FindBy(xpath="//*[@id=\"center_column\"]/h1")
	WebElement authentication;
	
	
	@FindBy(xpath="//*[@id=\"create-account_form\"]/h3")
	WebElement createAnAccountMsg;
	
	
	@FindBy(xpath="//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")
	WebElement logout;
	
	
		
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(signin, ObjectReader.reader.getImplicitWait());
		new TestBase().getNavigationScreen(driver);
		TestBase.logExtentReport("loginPage object created");
	}
	
	public void clickOnSignInLink() {
		signin.click();
		log.info("clicked on the signin link");
		logExtentReport("clicked on the signin link");
		
	}
	
	public void enterEmailAddress(String emailAddress) {
		this.emailAddress.sendKeys(emailAddress);
		log.info("entering email as "+emailAddress);
		logExtentReport("entering email as "+emailAddress);
		
	}
	
	
	public void enterPassword(String password) {
		this.password.sendKeys(password);
		log.info("entering password as "+password);
		logExtentReport("entering password as "+password);

	}
	
	
	public NavigationMenu clickOnSubmitBtn() {
		new JavaScriptHelper(driver).scrollDownVertically();
		submitLogin.click();
		log.info("clicking on the submit button...");
		logExtentReport("clicking on the submit button...");
		return new NavigationMenu(driver);
		
	}
	
	
	public boolean verifySuccesLoginMsg() {
		return new VerificationHelper(driver).isDisplayed(successMsgObject);
	}
	
	public void enterRegistrationEmail() {
		String email = System.currentTimeMillis()+"@gmail.com";
		log.info("entering registration email: "+email);
		registrationEmailAddress.sendKeys(email);
	}
	
	public RegistrationPage clickOnCreateAccount() {
		createAccount.click();
		return new RegistrationPage(driver);
	}
	
	
	public void loginToApplication(String emailAddress, String password) {
		clickOnSignInLink();
		enterEmailAddress(emailAddress);
		enterPassword(password);
		clickOnSubmitBtn();
	}
	
	
	public void logout() {
		logout.click();
		waitHelper.waitForElement(signin, ObjectReader.reader.getImplicitWait());
	}
	
	
	public void logExtentReport(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}
	
	
}
