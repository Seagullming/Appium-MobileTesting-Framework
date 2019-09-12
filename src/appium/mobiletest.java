package appium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TakesScreenshot;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class mobiletest {

	AndroidDriver driver;
	Dimension Size;
	String destDir;
	DateFormat dateFormat;

	@BeforeTest

	public void Set() throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "OnePlus 7 Pro");
		caps.setCapability("udid", "38fd59ca"); // Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "9.0");
		caps.setCapability("appPackage", "com.android.chrome");
		caps.setCapability("appActivity", "org.chromium.chrome.browser.ChromeTabbedActivity");
		caps.setCapability("noReset", "true");

		try {
			 driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),
					caps);
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		//takeScreenShot();
	}

	@Test
	public void takeScreenShot() {
		destDir = "screenshots";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		new File(destDir).mkdirs();
		String destFile = dateFormat.format(new Date()) + ".png";
		
		try {
			 FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		}catch (IOException e){
			 e.printStackTrace();
			}
		}
	
	@AfterTest
	public void End() {
		driver.quit();
	}

}
