package com.pageObjectEcom.vr.testScripts;

import org.testng.annotations.Test;

import com.pageObjectEcom.vr.helper.assertions.AssertionHelper;
import com.pageObjectEcom.vr.helper.browserConfig.ObjectReader;
import com.pageObjectEcom.vr.pageObjects.ApplicationTest;
import com.pageObjectEcom.vr.pageObjects.LoginPage;
import com.pageObjectEcom.vr.pageObjects.NavigationMenu;
import com.pageObjectEcom.vr.pageObjects.ProductCategoryPage;
import com.pageObjectEcom.vr.testBase.TestBase;

public class VerifyProductCounts extends TestBase {
	
	LoginPage loginPage;
	NavigationMenu navigationMenu;
	
	@Test
	public void testVerifyProductCounts(){
		
		getApplicationUrl(ObjectReader.reader.getUrl());
		
		navigationMenu = new NavigationMenu(driver);
		ProductCategoryPage pCate = navigationMenu.clickOnMenu(navigationMenu.womenMenu);
		
		pCate.selectColor(ApplicationTest.Orange);
		int count = pCate.getTotalProducts();
		
		if(count==3){
			AssertionHelper.markPass();
		}
		else{
			AssertionHelper.markFail("product count is not matching");
		}
	}

}
