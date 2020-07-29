package com.FlipkartDemo.PageManagers;

import org.openqa.selenium.WebDriver;

import com.FlipkartDemo.Page.CartPage;
import com.FlipkartDemo.Page.HomePage;
import com.FlipkartDemo.Page.LoginPage;
import com.FlipkartDemo.Page.OrderSummaryPage;
import com.FlipkartDemo.Page.Searchpage;

public class PageObjecManager {
	private WebDriver driver;

	private LoginPage loginPage;

	private HomePage homePage;

	private Searchpage searchPage;

	private CartPage cartPage;
	private OrderSummaryPage orderPage;

	// constructor
	public PageObjecManager(WebDriver driver) {

		this.driver = driver;

	}

	// HomePage object Creation
	public HomePage getHomePage() {

		return (homePage == null) ? homePage = new HomePage(driver) : homePage;
	}

	public LoginPage getLoginPage() {

		return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
	}

	public Searchpage getSearchPage() {

		return (searchPage == null) ? searchPage = new Searchpage(driver) : searchPage;
	}

	public CartPage getCartPage() {

		return (cartPage == null) ? cartPage = new CartPage(driver) : cartPage;
	}

	public OrderSummaryPage getOrderPage() {

		return (orderPage == null) ? orderPage = new OrderSummaryPage(driver) : orderPage;
	}
}
