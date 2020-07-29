package com.FlipkartDemo.config;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.FlipkartDemo.custom.*;
import com.FlipkartDemo.util.PropertyFileReader;

public class BaseTest {
	public static WebDriver driver;
	public String rootDir = CustomFunction.getRootDir();
	public static String homePageUrl;
	public static ITestResult result;
	public static String driver_Name;
	public static Process process;
	public static String dataSet_Config;
	public static String UserName;
	public static String Password;
	public static String Actual;
	public static String expected;
	public static String productName = "camera";

	@Parameters({ "PASSWORD", "TC_NAME", "DRIVER", "HOMEPAGE_URL", "USERNAME" })
	@BeforeTest
	public void setup(String password, String testCaseName, String nativeDriver, String homeUrl, String userName)
			throws Exception {

		System.out.println("Test Case name= " + testCaseName);
		// Assigning driver value
		driver_Name = nativeDriver;
		System.out.println("nativeDriverFromBase" + nativeDriver);
		// Assigning baseUrl value
		homePageUrl = homeUrl;
		System.out.println("baseUrl From Base= " + homePageUrl);
		// Assigning username
		UserName = userName;
		System.out.println("UserName= " + UserName);
		// Assigning password
		Password = password;
		System.out.println("Password= " + Password);

		// ************* Load Property File********************
		PropertyFileReader.loadProprtyFile();
		/*
		 * Select the relative driver
		 */
		driver = CustomFunction.initializeDriver(nativeDriver, driver, rootDir);

		if (driver_Name.equalsIgnoreCase("FF") || driver_Name.equalsIgnoreCase("CHROME")) {
			// Setting window size according to RS application on FF or chrome

			driver.manage().window().maximize();
		}
		else if (driver_Name.equalsIgnoreCase("IE")) {
			// Setting window size according to chanel application on FF
			driver.manage().window().maximize();
		}
	}

	@BeforeMethod
	public void methodLevelSetup() throws Exception {

		/*
		 * Checks for exceptions like IllegalStateException or SkipException or
		 * SessionNotFoundException or UnreachableBrowserException, If found skips the
		 * test method
		 */
		if (result != null) {
			if ((result.getThrowable().toString().contains("IllegalStateException")
					|| result.getThrowable().toString().contains("SkipException")
					|| result.getThrowable().toString().contains("SessionNotFoundException")
					|| result.getThrowable().toString().contains("UnreachableBrowserException"))) {

				throw new SkipException("Skip Methods");
			}
		}

	}

	@AfterMethod
	public void methodLevelTearDown() throws Exception {

		/*
		 * Checks for exceptions like IllegalStateException or SkipException or
		 * SessionNotFoundException or UnreachableBrowserException, If found skips the
		 * test method
		 */
		if (result != null) {
			if ((result.getThrowable().toString().contains("IllegalStateException")
					|| result.getThrowable().toString().contains("SkipException")
					|| result.getThrowable().toString().contains("SessionNotFoundException")
					|| result.getThrowable().toString().contains("UnreachableBrowserException"))) {

				throw new SkipException("Skip Methods");
			}
		}

	}

	@AfterClass
	public void classLevelTearDown() throws Exception {
		/*
		 * Checks for exceptions like IllegalStateException or SkipException or
		 * UnreachableBrowserException, If found skips the test cases
		 */
		if (result != null) {
			if ((result.getThrowable().toString().contains("IllegalStateException")
					|| result.getThrowable().toString().contains("SkipException")
					|| result.getThrowable().toString().contains("UnreachableBrowserException"))) {

				throw new SkipException("Skip Testcases");
			}
		}
	}
}
