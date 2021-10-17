package com.pageObjectEcom.vr.helper.browserConfig;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


public class ChromeBrowser {
	
public ChromeOptions getChromeOptions() {
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--test-type");
		option.addArguments("--disable-popup-blocking");
		
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		
		option.setCapability(ChromeOptions.CAPABILITY, chrome);	
		//Linux
		if(System.getProperty("os.name").contains("Linux")){
			option.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}
		return option;
	}

	public WebDriver getChromeDriver(ChromeOptions cap) {

		if (System.getProperty("os.name").contains("Mac")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver");
			return new ChromeDriver(cap);
		}
		else if(System.getProperty("os.name").contains("Window")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver.exe");
			return new ChromeDriver(cap);
		}
		else if(System.getProperty("os.name").contains("Linux")){
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chrome");
			return new ChromeDriver(cap);
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		ChromeBrowser obj = new ChromeBrowser();
//		WebDriver driver = obj.getChromeDriver(obj.getChromeOptions());
//		driver.get("https://www.google.com/");
//		//System.out.println(System.getProperty("user.dir") +"\\src\\main\\resources\\drivers\\chromedriver.exe");
//		
//	}

}
