package com.pageObjectEcom.vr.testBase;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;
import com.pageObjectEcom.vr.helper.browserConfig.BrowserType;
import com.pageObjectEcom.vr.helper.browserConfig.ChromeBrowser;
import com.pageObjectEcom.vr.helper.browserConfig.FirefoxBrowser;
import com.pageObjectEcom.vr.helper.browserConfig.IExploreBrowser;
import com.pageObjectEcom.vr.helper.browserConfig.ObjectReader;
import com.pageObjectEcom.vr.helper.browserConfig.PropertyReader;
import com.pageObjectEcom.vr.helper.excel.ExcelHelper;
import com.pageObjectEcom.vr.helper.javaScript.JavaScriptHelper;
import com.pageObjectEcom.vr.helper.logger.LoggerHelper;
import com.pageObjectEcom.vr.helper.wait.WaitHelper;
import com.pageObjectEcom.vr.utils.ExtentManager;

public class TestBase {
	
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public static File reportDirectory;

	
	
	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.getInstance();
	}
	
	@BeforeTest
	public void beforeTest() {
		ObjectReader.reader = new PropertyReader();
		reportDirectory = new File(System.getProperty("user.dir")+"\\test-output\\ScreenShots");
		setUpDriver(ObjectReader.reader.getBrowserType());
		test = extent.createTest(getClass().getSimpleName());

	}
	
	
	
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		test.log(Status.INFO, method.getName()+" test stasted");
		log.info(method.getName()+" test stasted");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
			String imagePath = captureScreenShot(result.getName(), driver);
			test.addScreenCaptureFromPath(imagePath);
		}else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName()+" is pass");
			String imagePath = captureScreenShot(result.getName(), driver);
			test.addScreenCaptureFromPath(imagePath);
		}else if(result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable());
		}
		
		extent.flush();
		
	}
	
	
	@AfterTest
	public void afterTest() throws Exception{
		if(driver!=null) {
			driver.quit();
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public WebDriver getBrowserObject(BrowserType btype) {
		try {
			switch(btype) {
			case Chrome:
				//get object chrome Browser options
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions option = chrome.getChromeOptions();
				return chrome.getChromeDriver(chrome.getChromeOptions());		
			
			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				FirefoxOptions options = firefox.getFirefoxOptions();
				return firefox.getFirefoxDriver(options);
			
			case Iexplorer:
				IExploreBrowser ie = IExploreBrowser.class.newInstance();
				InternetExplorerOptions cap = ie.getIExplorerCapabilities();
				return ie.getIExplorerDriver(cap);
			default:
				throw new Exception("Driver not Found: "+btype.name());
			}	
				
		}catch(Exception e) {
			log.info(e.getMessage());
			
		} 
		return null;
		
	}
	
	
	public void setUpDriver(BrowserType btype) {
		driver = getBrowserObject(btype);
		log.info("Initialised webdriver: "+driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	public String captureScreenShot(String fileName, WebDriver driver) {
		if(driver == null) {
			log.info("driver is null..");
			return null;
		}
		if(fileName == "") {
			fileName = "blank";
		}
		File destFile = null;
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File screFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			destFile = new File(reportDirectory+"\\"+fileName+"_"+formater.format(calender.getTime())+".png");
			Files.copy(screFile, destFile);
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width='100'/></a>");
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		return destFile.toString();
	}
	
	
	
	
	public void getNavigationScreen(WebDriver driver) {
		log.info("capturing UI navigation screen...");
		new JavaScriptHelper(driver).zoomInBy60Percentage();
		String screen = captureScreenShot("", driver);
		new JavaScriptHelper(driver).zoomInBy100Percentage();
		test.addScreenCaptureFromPath(screen);
		
	}
	
	public static void logExtentReport(String s1) {
		test.log(Status.INFO, s1);
	}
	
	public void getApplicationUrl(String url) {
		driver.get(url);
		logExtentReport("navigating to: "+url);
	}
	
	
	public Object[][] getExcelData(String excelName, String sheetName){
		String excelLocation = System.getProperty("user.dir")+"/src/main/resources/exceldata/"+excelName;
		log.info("excel location "+excelLocation);
		ExcelHelper excelHelper = new ExcelHelper();
		Object[][] data = excelHelper.getExcelData(excelLocation, sheetName);
		return data;
	}
	
}
