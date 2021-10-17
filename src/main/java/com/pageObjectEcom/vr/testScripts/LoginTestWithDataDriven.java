package com.pageObjectEcom.vr.testScripts;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageObjectEcom.vr.helper.assertions.AssertionHelper;
import com.pageObjectEcom.vr.helper.browserConfig.ObjectReader;
import com.pageObjectEcom.vr.helper.logger.LoggerHelper;
import com.pageObjectEcom.vr.pageObjects.LoginPage;
import com.pageObjectEcom.vr.pageObjects.NavigationMenu;
import com.pageObjectEcom.vr.testBase.TestBase;

public class LoginTestWithDataDriven extends TestBase {
	
	private final Logger log = LoggerHelper.getLogger(LoginTestWithDataDriven.class);

	LoginPage login;
	NavigationMenu navigationMenu;
	
	@DataProvider(name="testData")
	public Object[][] testData(){
		Object[][] data = getExcelData("testData.xlsx", "loginData");
		return data;
	}
	
	@BeforeClass
	public void beforeClass() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		login = new LoginPage(driver);
	}
	
	@Test(dataProvider="testData")
	public void loginTest(String userName, String password, String runMode) {
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("RunMode for this set of data is marked as N");
		}
		login.loginToApplication(userName, password);
		boolean status = login.verifySuccesLoginMsg();
		AssertionHelper.updateTestStatus(status);
		login.logout();//should be written in navigation page
	}
	
	
	
}
