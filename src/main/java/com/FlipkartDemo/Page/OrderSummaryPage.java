package com.FlipkartDemo.Page;

import static com.FlipkartDemo.util.PropertyFileReader.ObjRepoProperty;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.FlipkartDemo.custom.CustomFunction;
import com.FlipkartDemo.uiFunctions.GUIFunctions;

public class OrderSummaryPage {
	public final WebDriver driver;

	public OrderSummaryPage(WebDriver driver) {
		this.driver = driver;
		if (!(CustomFunction.isElementPresent(By.xpath(ObjRepoProperty.getProperty("OrderPg_OrderTitle_Xpath")),
				driver))) {

			throw new IllegalStateException("This is not the flipkart Cart page");
		}
	}

	// #***********************Locators*****************************************
	By OSP_Continue = By.xpath(ObjRepoProperty.getProperty("OrderPg_ContinueBtn_Xpath"));
	By OSP_ProductName = By.xpath(ObjRepoProperty.getProperty("OrderPg_OrderTitle_Xpath"));
	By OSP_ProductPrice = By.xpath(ObjRepoProperty.getProperty("OrderPg_OrderPrice_Xpath"));

	/**
	 * MethodName:Click on Continue Description:This function Click on continue btn
	 * * @return
	 * 
	 * @throws Exception
	 */
	public void clickOnContinue() throws Exception {
		// verify the name, price from product screen to ordersummary
		CustomFunction.waitFor(driver,OSP_ProductName);
		String actual = driver.findElement(OSP_ProductName).getText();
		
		Assert.assertTrue((Searchpage.expectedName.contains(actual)));
		System.out.println("Product Names are Mathcing from product page");
		CustomFunction.waitFor(driver,OSP_ProductPrice);
		String actualPrice = driver.findElement(OSP_ProductPrice).getText();
		Assert.assertTrue((Searchpage.expectedPrice.contains(actualPrice)));
		System.out.println("Product prices are Mathcing from prodcut page");
		CustomFunction.waitFor(driver,OSP_Continue);
		GUIFunctions.clickElement(driver, OSP_Continue, "Continue");
		
	}
}
