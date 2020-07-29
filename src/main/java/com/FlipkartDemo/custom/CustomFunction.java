package com.FlipkartDemo.custom;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomFunction {

	/**
	 * Method Name: getRootDir Description: Method to get Root directory
	 * 
	 * @return :rootDir
	 */
	public static String getRootDir() {
		File path = new File("");
		String absPath = path.getAbsolutePath();
		File dir = new File(absPath);
		String rootDir = dir.getParent();
		System.out.println("Project Location is(CustomFunction)-rootDir = " + rootDir);
		System.out.println("Project Location is(CustomFunction)-absPath = " + absPath);
		System.out.println(rootDir + "/src/test/resources/testData/ObjectRepository.properties");
		return absPath;

	}

	/**
	 * Method Name: isElementPresent Description:Method to verify the Element is
	 * present or not
	 * 
	 * @param by     :by is the Element locator
	 * @param driver :WebDriver
	 * @return true: if element is present, return false: if element is not present
	 */
	public static boolean isElementPresent(By by, WebDriver driver) {
		try {
			System.out.println("element is present inside isElementPresent()");
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("catch of isElementPresent()");
			System.out.println("element is not present");
			return false;
		}

	}

	/**
	 * Method Name:initializeDriver Description:Method to intializedriver
	 * 
	 * @param driverName
	 * @param driver
	 * @param rootDir
	 * @throws Exception
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws MalformedURLException
	 */
	public static WebDriver initializeDriver(String driverName, WebDriver driver, String rootDir)
			throws Exception, IOException, InterruptedException, MalformedURLException {
		switch (driverName) {
		case "FF":
			// Initiating the Firefox driver
			// Webdriver manager replaces the driver setup
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			break;
		case "CHROME":
			// Chrome driver set up
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			break;
		case "IE":
			// IE driver set up
			WebDriverManager.iedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			break;
		default:
			// By default initiating the chrome driver.
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}
		return driver;
	}
	/**
	 * Method Name: waitFor:Method perfroms explicit wait for the webelements
	 * @param by     :by is the Element locator
	 * @param driver :WebDriver
	 */
	public static void  waitFor(WebDriver driver ,By element) {
		WebDriverWait wait = new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		// click on the compose button as soon as the "compose" button is visible
		}

}
