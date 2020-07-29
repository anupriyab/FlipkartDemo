package com.FlipkartDemo.test;

import org.testng.annotations.Test;
import com.FlipkartDemo.Page.*;
import com.FlipkartDemo.config.BaseTest;
import com.FlipkartDemo.uiFunctions.GUIFunctions;
import com.FlipkartDemo.PageManagers.*;

public class EndtoEndTest extends BaseTest {

	LoginPage login;
	HomePage home;
	Searchpage search;
	CartPage cart;
	OrderSummaryPage order;
	PageObjecManager pageObject;

	@Test(priority = 1, description = "Precondition:  Open browser,Navigate to the flipkart URL")
	public void Step01_NavigeteToURL() throws Exception {
		System.out.println("step 1 begin");
		LoginPage.navigateToApplication_URL(driver, homePageUrl, driver_Name);
		System.out.println("Successfully navigated to application URL");
	}
	/*
	 * @Test( priority=2,description="Step 2: ClickonClosemodal") public void
	 * Step02_ClickonClosemodal() throws InterruptedException {
	 * System.out.println("step 2 begin"); HomePage hp=new HomePage(driver);
	 * System.out.println("Inside mail***********"+UserName);
	 * hp.ClickonCloseModal();
	 * System.out.println("Successfully Entered email address");
	 * 
	 * }
	 */

	@Test(priority = 2, description = "Step 2: Enter email address")
	public void Step02_EnterEmailAddress() throws InterruptedException {
		pageObject = new PageObjecManager(driver);
		login = pageObject.getLoginPage();
		login.EnterEmail(UserName);
		System.out.println("Successfully Entered email address");

	}

	@Test(priority = 3, description = "Step 3: Enter password")
	public void Step03_EnterPassword() throws InterruptedException {

		login.EnterPassword(Password);
		System.out.println("Successfully Entered password");
	}

	@Test(priority = 4, description = "Step 4: Click on SignIn Btn")
	public void Step04_ClickOnSignInButton() throws InterruptedException {

		login.clickOnSignBtn();
		System.out.println("Successfully Clicked on SignIn Btn");

	}

	@Test(priority = 5, description = "Step 5: Search on the textbox")
	public void Step05_SearchforProduct() throws InterruptedException {
		home = pageObject.getHomePage();
		home.searchForProduct(productName);
		System.out.println("Successfully searched the prodcut");

	}

	@Test(priority = 6, description = "Step 6: Select an product from search result")
	public void Step06_SelectFromResult1() throws InterruptedException {
		search = pageObject.getSearchPage();
		search.SelectProductFromResult();
		System.out.println("Successfully selected from the search result");

	}

	@Test(priority = 7, description = "Step 7: Click on Buy Now")
	public void Step07_ClickonBuyNow() throws InterruptedException {
		// move to child window
		GUIFunctions.SwitchtoWindow(driver);
		search.ClickonBuyNow();
		System.out.println("Successfully clicked on buy now");
	}

	/*
	 * @Test(priority=8,description="Step 8: Click oncart from header") public void
	 * Step08_ClickonCart() throws InterruptedException { home.clickonCart();
	 * System.out.println("Successfully clicked on cart"); }
	 */
	/*
	 * @Test(priority=9,description="Step 9: Click on place order") public void
	 * Step09_ClickonPlaceOrder() throws InterruptedException {
	 * cart=pageObject.getCartPage(); cart.clickOnPlaceOrder();
	 * System.out.println("Successfully clicked on Place order"); }
	 */
	@Test(priority = 8, description = "Step 8: Click on Continue")
	public void Step08_ClickOnContinue() throws Exception {
		order = pageObject.getOrderPage();
		order.clickOnContinue();
		System.out.println("Successfully clicked on Continue");
	}

	// payment skipped
	@Test(priority = 9, description = "Step 9: Naviagate to Home Page")
	public void Step9_NaviagateToHomePage() throws Exception {
		home.clickOnLogo();
		System.out.println("Successfully Navigated back to Home Page");
	}

	@Test(priority = 10, description = "Step 10: Mouse Hover on MY account")
	public void Step10_HoverOnMyAccMenu() throws Exception {
		home.hoverOnMyacc();
		System.out.println("Successfully clicked on MY account menu");
	}

	@Test(priority = 11, description = "Step 11: Click on Logout")
	public void Step11_ClickOnLogout() throws Exception {
		home.ClickOnLogout();
		System.out.println("Successfully logged out from the flipkart");
	}

}
