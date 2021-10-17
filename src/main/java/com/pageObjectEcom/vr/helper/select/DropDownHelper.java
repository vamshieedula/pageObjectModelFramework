package com.pageObjectEcom.vr.helper.select;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.pageObjectEcom.vr.helper.logger.LoggerHelper;

public class DropDownHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(DropDownHelper.class);

	public DropDownHelper(WebDriver driver) {
		this.driver = driver;
		log.info("DropDown Helper object created..");
	}
	
	public void selectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("selectUsingValue and value is: "+value);
		select.selectByValue(value);
	}

	
	public void selectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("selectIndexUsingIndex and index is: "+index);
		select.selectByIndex(index);
	}
	
	
	public void selectUsingVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		log.info("selectUsingVisibleText and text is: "+visibleText);
		select.selectByVisibleText(visibleText);
	}
	
	
	public void deselectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("deselectedUsingValue and value is: "+value);
		select.deselectByValue(value);
	}
	
	
	public void deselectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("deselectedUsingValue and value is: "+index);
		select.deselectByIndex(index);
	}
	
	
	public void deselectUsingVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		log.info("deselectUsingVisibleText and text is: "+visibleText);
		select.selectByVisibleText(visibleText);
	}
	
	
	public List<String> getAllDropDownData(WebElement element){
		Select select = new Select(element);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		for(WebElement ele: elementList) {
			log.info(ele.getText());
			valueList.add(ele.getText());
		}
		return valueList;
	}
	
	
	
}

