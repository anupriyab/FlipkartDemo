package com.FlipkartDemo.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.FlipkartDemo.custom.CustomFunction;
import com.FlipkartDemo.uiFunctions.GUIFunctions;

import static com.FlipkartDemo.util.PropertyFileReader.ObjRepoProperty;

public class HomePage {
	private final WebDriver driver;
	// For logging

	// home Page Constructor
	public HomePage(WebDriver driver) {

		this.driver = driver;
		if (!(CustomFunction.isElementPresent(
				By.className(ObjRepoProperty.getProperty("HomePg_SearchTxtBox_ClassName")), driver))) {
			System.out.println("Inside home page*****************");
			throw new IllegalStateException("This is not the flipkart home page");
		}
	}

	// *******************************Locators*************************************
	By HOP_Logo = By.xpath(ObjRepoProperty.getProperty("HomePage_Logo_Xpath"));
	By HOP_SearchBox = By.className(ObjRepoProperty.getProperty("HomePg_SearchTxtBox_ClassName"));
	By HOP_CloseModal = By.xpath(ObjRepoProperty.getProperty("HomePg_closeModal_Xpath"));
	By HOP_SearchIcon = By.xpath(ObjRepoProperty.getProperty("HomePg_SearchIcon_Xpath"));
	By HOP_cartBtn = By.xpath(ObjRepoProperty.getProperty("HomePg_cartBtn_Xpath"));
	By HOP_Myacc = By.xpath(ObjRepoProperty.getProperty("HomePg_Myacc_Xpath"));
	By HOP_Logout = By.xpath(ObjRepoProperty.getProperty("HomePg_Myacc_Logout_Xpath"));

	/**
	 * MethodName:navigateToApplication_URL Description:This function navigates To
	 * the Application URL
	 * 
	 * @return Home page
	 * @throws InterruptedException Author:Anupriya
	 */
	public static HomePage navigateToApplication_URL(WebDriver driver, String homeUrl, String driverName)
			throws InterruptedException {
		System.out.println("before loading url");
		GUIFunctions.DeleteCookies(driver);
		driver.get(homeUrl);
		System.out.println("Page Title:" + driver.getTitle());
		return new HomePage(driver);
	}

	/**
	 * MethodName:Click on Logo Description:This function Click on logo
	 * 
	 * @return Home page
	 * @throws InterruptedException Author:Anupriya
	 */
	public void clickOnLogo() throws InterruptedException {
		CustomFunction.waitFor(driver,HOP_Logo);
		GUIFunctions.clickElement(driver, HOP_Logo, "Logo");
		
	}

	/**
	 * MethodName:Click on search txtbox from header Description:This function Click
	 * on Sign in from header
	 * 
	 * @return SearchPage
	 * @throws InterruptedException Author:Anupriya
	 */
	public void searchForProduct(String searchItem) throws InterruptedException {
		CustomFunction.waitFor(driver,HOP_SearchBox);
		GUIFunctions.enterValueIntoTextBox(driver, HOP_SearchBox, searchItem);
		CustomFunction.waitFor(driver,HOP_SearchBox);
		GUIFunctions.enterKeyFromkeyBoard(driver, HOP_SearchBox);
	

	}

	/**
	 * MethodName:Click on close Modal Description:This function Click on close
	 * modal from header
	 * 
	 * @return HomePage
	 * @throws InterruptedException Author:Anupriya
	 */
	public void clickonCloseModal() throws InterruptedException {
		CustomFunction.waitFor(driver,HOP_CloseModal);
		GUIFunctions.clickElement(driver, HOP_CloseModal, "CloseModal");
		

	}

	/**
	 * MethodName:Click on cart Description:This function Click on cart from header
	 * * @return CartPage
	 * 
	 * @throws InterruptedException Author:Anupriya
	 */
	public void clickonCart() throws InterruptedException {
		CustomFunction.waitFor(driver,HOP_cartBtn);
		GUIFunctions.clickElement(driver, HOP_cartBtn, "Cartbtn");
	
	}

	/**
	 * MethodName:MouseHover on Myacc Description:This function Click on logo
	 * 
	 * @return Home page
	 * @throws Exception
	 */

	public void hoverOnMyacc() throws Exception {
		CustomFunction.waitFor(driver,HOP_Myacc);
		GUIFunctions.mouseHover(driver, HOP_Myacc, "My account");
	
	}

	/**
	 * MethodName:Click On Logout Description:This function Click on logout
	 * 
	 * @return LoginPage
	 * @throws Exception
	 */
	public void ClickOnLogout() throws Exception {
		CustomFunction.waitFor(driver,HOP_Logout);
		GUIFunctions.clickElement(driver, HOP_Logout, "Logout");
	

	}
}
