package com.FlipkartDemo.Page;

import static com.FlipkartDemo.util.PropertyFileReader.ObjRepoProperty;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.FlipkartDemo.custom.CustomFunction;
import com.FlipkartDemo.uiFunctions.GUIFunctions;

public class LoginPage {

	final WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;

		if (!com.FlipkartDemo.custom.CustomFunction
				.isElementPresent(By.xpath(ObjRepoProperty.getProperty("LoginPg_UsernameTxtBox_Xpath")), driver)) {
			throw new IllegalStateException("This is not the flipkart login page");
		}

	}

	By Login_FpLoginEmailaddress = By.xpath(ObjRepoProperty.getProperty("LoginPg_UsernameTxtBox_Xpath"));
	By Login_FpLoginPswd = By.xpath(ObjRepoProperty.getProperty("LoginPg_PasswordTxtBox_Xpath"));
	By Login_Fpsignbtn = By.xpath(ObjRepoProperty.getProperty("LoginPg_LoginBtn_Xpath"));

	/**
	 * MethodName=Navigate to App URL Description:This funtions navigates to app url
	 * 
	 * @return HomePage
	 * @throws InterruptedException Author: Anupriya
	 */
	public static void navigateToApplication_URL(WebDriver driver, String homeURL, String driverName)
			throws InterruptedException {
		System.out.println("before loading url --->Login url");
		GUIFunctions.DeleteCookies(driver);
		// navigate to RS URL
		driver.get(homeURL);
		System.out.println("Page Title:" + driver.getTitle());

	}

	/**
	 * MethodName=EnterEmail Description:This function enters Email
	 * 
	 * @return LoginPage
	 * @throws InterruptedException Author: Anupriya
	 */

	public void EnterEmail(String emailaddress) throws InterruptedException {
		System.out.println("inside login page **********************");
		CustomFunction.waitFor(driver,Login_FpLoginEmailaddress);
		GUIFunctions.enterValueIntoTextBox(driver, Login_FpLoginEmailaddress, emailaddress);
	

	}

	/**
	 * MethodName=EnterPassword Description:This function enters Password
	 * 
	 * @return LoginPage
	 * @throws InterruptedException Author: Anupriya
	 */

	public void EnterPassword(String password) throws InterruptedException {
		CustomFunction.waitFor(driver,Login_FpLoginPswd);
		GUIFunctions.enterValueIntoTextBox(driver, Login_FpLoginPswd, password);

	}

	/**
	 * MethodName=click on Sign in Btn Description:This function Clicks On Sign
	 * inButton
	 * 
	 * @return HomePage
	 * @throws InterruptedException Author: Anupriya
	 */

	public void clickOnSignBtn() throws InterruptedException {
		CustomFunction.waitFor(driver,Login_Fpsignbtn);
		GUIFunctions.clickElement(driver, Login_Fpsignbtn, "Login");	
		System.out.println(" sign in button clicked");


	}

}
