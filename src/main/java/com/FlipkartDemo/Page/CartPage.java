package com.FlipkartDemo.Page;

import static com.FlipkartDemo.util.PropertyFileReader.ObjRepoProperty;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.FlipkartDemo.custom.CustomFunction;
import com.FlipkartDemo.uiFunctions.GUIFunctions;

public class CartPage {
	public final WebDriver driver;
	public int i;

	// constructor
	public CartPage(WebDriver driver) {
		// intialize the instance variable
		this.driver = driver;
		if (!(CustomFunction.isElementPresent(By.xpath(ObjRepoProperty.getProperty("CartPg_PriceTitle_Xpath")),
				driver))) {

			throw new IllegalStateException("This is not the flipkart Cart page");
		}
	}

	// #***********************Locators*****************************************
	By COP_PlaceOrder = By.xpath(ObjRepoProperty.getProperty("CartPg_PlaceOrderBtn_Xpath"));

	/**
	 * MethodName:Click on placeorder Description:This function Click on placeorder
	 * from header 
	 *  @return OrderSummaryPage
	 * @throws InterruptedException 
	 * Author:Anupriya
	 */
	public void clickOnPlaceOrder() throws InterruptedException {
		CustomFunction.waitFor(driver,COP_PlaceOrder);
		GUIFunctions.clickElement(driver, COP_PlaceOrder, "PlaceOrder");
	
	}
}
