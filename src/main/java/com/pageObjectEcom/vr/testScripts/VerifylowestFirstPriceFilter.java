package com.pageObjectEcom.vr.testScripts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pageObjectEcom.vr.helper.assertions.AssertionHelper;
import com.pageObjectEcom.vr.helper.browserConfig.ObjectReader;
import com.pageObjectEcom.vr.helper.logger.LoggerHelper;
import com.pageObjectEcom.vr.pageObjects.NavigationMenu;
import com.pageObjectEcom.vr.pageObjects.ProductCategoryPage;
import com.pageObjectEcom.vr.testBase.TestBase;

public class VerifylowestFirstPriceFilter extends TestBase {

private final Logger log = LoggerHelper.getLogger(VerifylowestFirstPriceFilter.class);
	
	@Test
	public void verifyLowestFirstPriceListInProduct_deatilsPage() throws InterruptedException {

		getApplicationUrl(ObjectReader.reader.getUrl());
		
		NavigationMenu navigationMenu = new NavigationMenu(driver);
		
		ProductCategoryPage pcategoryPage = navigationMenu.clickOnMenu(navigationMenu.womenMenu);
		// select price filter
		pcategoryPage.selectSortByFilter("Price: Lowest first");
		
		// wait for some time to make sure price is sorted.
		Thread.sleep(8000);
		
		List<WebElement> price = pcategoryPage.getAllProductsPrice();
		
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		Iterator<WebElement> itr = price.iterator();
		
		ArrayList<Integer> data = pcategoryPage.getPriceMassagedData(itr);
		boolean status = pcategoryPage.verifyArrayHasAscendingData(data);
		AssertionHelper.updateTestStatus(status);

	/*	
		//price comes in "$16.40" format
		// remove $ from beginning and change to int for sorting order verification
		// Store it in array list
		while (itr.hasNext()) {
			String p = itr.next().getText();
			if (p.contains("$")) {
				String actualData = p.substring(1);
				log.info(actualData);
				double p1 = Double.parseDouble(actualData);
				int productPrice = (int) (p1);
				array.add(productPrice);
			}
		}
		
		
		
        log.info(array);
        //[16, 16, 26, 27, 28, 30, 50]
		for (int i = 0; i < array.size() - 1; i++) {
			// this condition will check all next price should be smaller than previous one.
			// next price can be grater and equal
			if (array.get(i) <= array.get(i + 1)) {
				log.info("obj.get(i):-" + array.get(i));
				log.info("obj.get(i+1):-" + array.get(i + 1));
			} else {
				Assert.assertTrue(false,"price filter is not working");
			}
		}
		
		*/
	}
	
}
