package com.pageObjectEcom.vr.testScripts;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.pageObjectEcom.vr.helper.assertions.AssertionHelper;
import com.pageObjectEcom.vr.helper.browserConfig.ObjectReader;
import com.pageObjectEcom.vr.helper.logger.LoggerHelper;
import com.pageObjectEcom.vr.pageObjects.LoginPage;
import com.pageObjectEcom.vr.testBase.TestBase;

public class LoginTest extends TestBase {
	
	private final Logger log = LoggerHelper.getLogger(LoginTest.class);
	
	@Test(description="Login test with valid credentials")
	public void testLoginToApplication(){
		getApplicationUrl(ObjectReader.reader.getUrl());
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		
		boolean status = loginPage.verifySuccesLoginMsg();
		
		AssertionHelper.updateTestStatus(status);
	}
	

}
