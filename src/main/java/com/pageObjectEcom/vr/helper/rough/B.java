package com.pageObjectEcom.vr.helper.rough;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjectEcom.vr.testBase.TestBase;

public class B extends TestBase {
	
	int i = 1;
	
	@Test
	public void testB() {
		if(i==3) {
			Assert.assertTrue(true);
			
		}else {
			i++;
			Assert.assertTrue(false);

		}
	}

}
