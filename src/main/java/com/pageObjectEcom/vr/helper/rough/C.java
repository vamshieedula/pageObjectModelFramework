package com.pageObjectEcom.vr.helper.rough;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjectEcom.vr.testBase.TestBase;

public class C extends TestBase {
	
	int i = 1;
	
	@Test
	public void testC() {
			i++;
			Assert.assertTrue(false);

		
	}

}
