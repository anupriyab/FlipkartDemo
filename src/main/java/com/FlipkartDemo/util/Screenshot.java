  package com.FlipkartDemo.util;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import com.FlipkartDemo.config.*;

import com.FlipkartDemo.custom.CustomFunction;


	public class Screenshot extends TestListenerAdapter
	{
		// Get the root directory path of the project
		public static String rootDir= CustomFunction.getRootDir();
		public static File srcFile;
		static Date today= Calendar.getInstance().getTime();

		//For logger
	

		// create our "formatter" (our custom format)
		String datePattern="MM/dd/yyyy";

		static SimpleDateFormat formatter=new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ss");
		Date date=new Date();
		long a =date.getTime();
		String screenshots= null;

		@Override
		public void onTestFailure(ITestResult result)
		{
			//Handling Android/IOS Appium server skipping at runtime.
			if((BaseTest.driver_Name.equalsIgnoreCase("ANDROID") || BaseTest.driver_Name.equalsIgnoreCase("IOS")))
			{
				if((result.getThrowable().toString().contains("UnreachableBrowserException")))
				{
					BaseTest.result = result;



				}
			}

			if (result.getThrowable().toString().contains("IllegalStateException")) 
			{

				Reporter.log("<br />" 
						+ "<a href=\"" 
						+ rootDir 
						+ "/target/screenshots/" 
						+ result.getName() 
						+ result.getStartMillis() 
						+ " .png\" class=\"highslide\" rel=\"highslide\">"
						+ " <img src=\""
						+ rootDir
						+ "/target/screenshots/"
						+ result.getName()
						+ result.getStartMillis()
						+ " .png\" alt=\"Highslide JS\" title=\"Click to enlarge\" hight=\'100\' width=\'100\' /> </a>"
						+ "<br/>");

				Reporter.log("<a href=\"" + rootDir + "/target/screenshots/"
						+ result.getName() + result.getStartMillis() + ".png\">"
						+ result.getName() + result.getStartMillis() + " </a>"
						+ "<br/>");


				// For RemoteWebDriver - Argument the RemoteWebDriver to WebDriver
				// to achieve the screenshot capturing.


				if (BaseTest.driver.getClass().getName().equalsIgnoreCase("org.openqa.selenium.remote.RemoteWebDriver"))
				{
					WebDriver augmentedDriver = new Augmenter().augment(BaseTest.driver);
					srcFile = ((TakesScreenshot) augmentedDriver)
							.getScreenshotAs(OutputType.FILE);
				}
				else 
				{
					srcFile = ((TakesScreenshot) BaseTest.driver)
							.getScreenshotAs(OutputType.FILE);
				}
				try 
				{
					FileUtils.copyFile(srcFile, new File(rootDir + "/target/screenshots/"
							+ result.getName() + result.getStartMillis() + ".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			
				BaseTest.result = result;

				BaseTest.driver.quit();
			}
			else
			{
				if (!(result.getThrowable().toString()
						.contains("UnreachableBrowserException: "))
						&& !(result.getThrowable().toString()
								.contains("SessionNotFoundException"))) {
					Reporter.log("<br />"
							+ "<a href=\""
							+ rootDir
							+ "/target/screenshots/"
							+ result.getName()
							+ result.getStartMillis()
							+ ".png\" class=\"highslide\" rel=\"highslide\">"
							+ " <img src=\""
							+ rootDir
							+ "/target/screenshots/"
							+ result.getName()
							+ result.getStartMillis()
							+ ".png\"  alt=\"Highslide JS\" title=\"Click to enlarge\" hight=\'100\' width=\'100\'/> </a>"
							+ "<br/>");
					Reporter.log("<a href=\"" + rootDir + "/target/screenshots/"
							+ result.getName() + result.getStartMillis()
							+ ".png\">" + result.getName()
							+ result.getStartMillis() + " </a>" + "<br/>");
					// For RemoteWebDriver - Argument the RemoteWebDriver to
					// WebDriver
					// to achieve the screenshot capturing.
					if (BaseTest.driver.getClass().getName().equalsIgnoreCase("org.openqa.selenium.remote.RemoteWebDriver")) 
					{
						WebDriver augmentedDriver = new Augmenter()
						.augment(BaseTest.driver);
						srcFile = ((TakesScreenshot) augmentedDriver)
								.getScreenshotAs(OutputType.FILE);
					}
					else
					{
						srcFile = ((TakesScreenshot) BaseTest.driver)
								.getScreenshotAs(OutputType.FILE);
					}
					try
					{
						FileUtils.copyFile(srcFile,new File(rootDir + "/target/screenshots/"
										+ result.getName()
										+ result.getStartMillis() + ".png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}



		}

		@Override
		public void onTestSkipped(ITestResult result) {
			Reporter.log("Skip");
			try {
				// Close all browsers
				BaseTest.driver.quit();

			} catch (NullPointerException e) {
				result.setThrowable(e);
				e.printStackTrace();

			}

		}


		@Override
		public void onTestSuccess(ITestResult result) 
		{
			try {
				
			//	System.out.println("Result contains= " + result);


				Reporter.log("<br />"
						+ "<a href=\""
						+ rootDir
						+ "/target/screenshots/"
						+ result.getName()
						+ result.getStartMillis()
						+ ".png\" class=\"highslide\" rel=\"highslide\">"
						+ " <img src=\""
						+ rootDir
						+ "/screenshots/"
						+ result.getName()
						+ result.getStartMillis()
						+ ".png\"  alt=\"Highslide JS\" title=\"Click to enlarge\" hight=\'100\' width=\'100\'/> </a>"
						+ "<br/>");
				Reporter.log("<a href=\"" + rootDir + "/target/screenshots/"
						+ result.getName() + result.getStartMillis() + ".png\">"
						+ result.getName() + result.getStartMillis() + " </a>"
						+ "<br/>");
				System.setProperty("org.uncommons.reportng.escape-output", "false");    
				// For RemoteWebDriver - Argument the RemoteWebDriver to WebDriver
				// to achieve the screenshot capturing.
				if (BaseTest.driver.getClass().getName().equalsIgnoreCase("org.openqa.selenium.remote.RemoteWebDriver"))
				{
					WebDriver augmentedDriver = new Augmenter().augment(BaseTest.driver);
					srcFile = ((TakesScreenshot) augmentedDriver)
							.getScreenshotAs(OutputType.FILE);
				} else {
					srcFile = ((TakesScreenshot) BaseTest.driver)
							.getScreenshotAs(OutputType.FILE);
				}
				FileUtils.copyFile(srcFile, new File(rootDir + "/target/screenshots/"
						+ result.getName() + result.getStartMillis() + ".png"));

				FileUtils.copyFile(srcFile, new File(rootDir + "/target/screenshots/"
						+ result.getName() + result.getStartMillis() + ".png"));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}