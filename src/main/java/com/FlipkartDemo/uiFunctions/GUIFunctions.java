package com.FlipkartDemo.uiFunctions;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.FlipkartDemo.custom.CustomFunction;

import java.util.Iterator;

public class GUIFunctions {

	/**
	 * Method Name: navigateBack Description: This method is used to go back to
	 * previous page
	 * 
	 * @param driver :WebDriver
	 * @return driver
	 */
	public static WebDriver navigateBack(WebDriver driver) {
		try {
			// Navigate back to Previous page
			driver.navigate().back();
		} catch (Exception e) {

		}

		return (driver);
	}

	/**
	 * Method Name: clickElement Description: This method clicks on WebElement
	 * specified
	 * 
	 * @param driver  : WebDriver
	 * @param ele     : WebElement locator
	 * @param eleName : Name of the element to be clicked
	 */

	public static void clickElement(WebDriver driver, By by, String eleName) {
		try {

			WebElement element = driver.findElement(by);

			// Click on element
			element.click();
		} catch (NoSuchElementException e) {
		}
	}

	/**
	 * Method Name:selectRadioButton Description: This method is used to select
	 * radio button
	 * 
	 * @param driver :WebDriver, By, String
	 * @return driver
	 */
	public static void selectRaidoButton(WebDriver driver, By by, String elementName) {
		// Initialize WebElement
		WebElement ele = driver.findElement(by);

		try {
			if (!ele.isSelected()) {
				ele.click();
			}

		}

		catch (NoSuchElementException e) {
		}

	}

	/**
	 * Method Name: enterValueIntoTextBox Description: This method is used to enter
	 * a value into text box
	 * 
	 * @param driver : WebDriver
	 * @param ele    : WebElement locator
	 * 
	 */
	public static void enterValueIntoTextBox(WebDriver driver, By by, String value) {
		try {

			// Click on element
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(value);
		} catch (NoSuchElementException e) {

		}
	}

	/**
	 * Method Name: SelectDropdownValue This method will select value from dropdown.
	 * 
	 * @param driver
	 * @param xpathOfElement
	 * @param value
	 */
	public static void SelectDropdownValue(WebDriver driver, By xpathOfElement, String value) {
		try

		{
			Select itemType = new Select(driver.findElement(xpathOfElement));
			itemType.selectByVisibleText(value);

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method Name: SelectDropdownValueByIndex This method will select value from
	 * dropdown.
	 * 
	 * @param driver
	 * @param xpathOfElement
	 * @param value
	 */
	public static void SelectDropdownValueByIndex(WebDriver driver, By xpathOfElement, int i) {
		try

		{
			Select itemType = new Select(driver.findElement(xpathOfElement));
			itemType.selectByIndex(i);

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method Name: assertVerify Description: This method is used to compare the
	 * actual and expected value
	 * 
	 * @param driver : WebDriver
	 * 
	 */

	public static void assertVerify(WebDriver driver, By by, String expectedValue) throws Exception

	{
		try {
			WebElement element = driver.findElement(by);
			String ActualValue = element.getText();

			System.out.println("actual = " + ActualValue);
			System.out.println("expected = " + expectedValue);
			boolean matches = false;
			if (ActualValue.contains(expectedValue)) {
				matches = true;
			}
			Assert.assertTrue(matches);

		} catch (NoSuchElementException e) {

		}

	}

	/**
	 * Method Name: clear Description: This method is used to clear
	 * 
	 * @param driver : WebDriver
	 * 
	 */

	public static void clear(WebDriver driver, By by) {
		driver.findElement(by).clear();

	}

	/**
	 * Method:DeleteCookies Description:To delete browser cookies * @param driver :
	 * WebDriver
	 * 
	 */
	public static void DeleteCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}

	/**
	 * Method Name: mouseHover Description: This method is used to mouse hover on
	 * element
	 * 
	 * @param driver : WebDriver
	 * 
	 */

	public static void mouseHover(WebDriver driver, By by, String eleName) throws Exception

	{
		try {

			WebElement mouseElement = driver.findElement(by);
			Actions builder = new Actions(driver);
			builder.moveToElement(mouseElement).build().perform();

		} catch (NoSuchElementException e) {

		}
	}

	/**
	 * Method Name: dropdownClick Description: This method is used to click on
	 * dropdown
	 * 
	 * @param driver : WebDriver
	 * 
	 */
	// Clicking on value from dropdown
	public static void dropdownClick(WebDriver driver, By by) {
		driver.findElement(by).click();
	}

	/**
	 * Method Name: enterKeyFromkeyBoard Description: This method is used to enter
	 * keys from keyboard
	 * 
	 * @param driver : WebDriver
	 * 
	 */
	public static void enterKeyFromkeyBoard(WebDriver driver, By by) {
		driver.findElement(by).sendKeys(Keys.ENTER);
	}

	/**
	 * Method Name: scrollpage Description: This method is used to scroll page
	 * 
	 * @param driver : WebDriver
	 * 
	 */
	public static void scrollpage(WebElement ele, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	/**
	 * Method Name: scrollpage Description: This method is used to click an element
	 * through js
	 * 
	 * @param driver : WebDriver
	 * 
	 */
	public static void JsClick(WebElement ele, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("return document.readyState").equals("complete");
		js.executeScript("arguments[0].click();", ele);
	}

	/**
	 * Method Name: SwitchtoWindow Description: This method is used to
	 * switchtoWindow
	 * 
	 * @param driver : WebDriver
	 * 
	 */
	public static void SwitchtoWindow(WebDriver driver) {
		Set<String> set = driver.getWindowHandles();
		System.out.println(driver.getWindowHandles().size());

		String mainWindow = driver.getWindowHandle();
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String childWindow = itr.next();
			// Compare whether the main windows is not equal to child window. If not equal,
			// we will close.
			if (!mainWindow.equals(childWindow)) {
				driver.switchTo().window(childWindow);
			}
		}
	}
}
