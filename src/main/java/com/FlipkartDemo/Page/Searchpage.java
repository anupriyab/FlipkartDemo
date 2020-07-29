package com.FlipkartDemo.Page;



import static com.FlipkartDemo.util.PropertyFileReader.ObjRepoProperty;
import com.FlipkartDemo.uiFunctions.GUIFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.FlipkartDemo.custom.CustomFunction;

public class Searchpage {
	public static String expectedName;
	public static String expectedPrice;
	public final WebDriver driver;
	public int i;
	
	public Searchpage(WebDriver driver)
	{
		this.driver = driver;
		if (!(CustomFunction.isElementPresent(
				By.xpath(ObjRepoProperty.getProperty("SearchPage_Fliter_Xpath")),
				driver))) {

			throw new IllegalStateException("This is not the flipkart search result page");
		}
	}
	//#***********************Locators*****************************************
	By SOP_SelectProduct=By.xpath(ObjRepoProperty.getProperty("SearchPage_Camera_Xpath"));
	By SOP_ProductName=By.xpath(ObjRepoProperty.getProperty("ProductPage_Name_Xpath"));
	By SOP_ProductPrice=By.xpath(ObjRepoProperty.getProperty("ProductPage_Price_Xpath"));
	By SOP_BuyNowBtn=By.xpath(ObjRepoProperty.getProperty("ProductPage_BuyNowBtn_Xpath"));
	
	/**MethodName:SelectProductFromResult
	 * Description:This function selects an product from the search result
	 * @return searchpage
	 * @throws InterruptedException 
	 * Author:Anupriya
	 */
	public void SelectProductFromResult()throws InterruptedException
	{
		WebElement ele = driver.findElement(SOP_SelectProduct);
		CustomFunction.waitFor(driver,SOP_SelectProduct);
		GUIFunctions.scrollpage(ele, driver);
		CustomFunction.waitFor(driver,SOP_SelectProduct);
		System.out.println("scrolling works");
		CustomFunction.waitFor(driver,SOP_SelectProduct);
		GUIFunctions.JsClick(ele, driver);
	}
	/**MethodName:Clickon BuyNow
	 * Description:This function clickks an buy now button
	 * @return search resultpage
	 * @throws InterruptedException 
	 * Author:Anupriya
	 */
	public void ClickonBuyNow()throws InterruptedException
	{
		expectedName=driver.findElement(SOP_ProductName).getText();
		expectedPrice=driver.findElement(SOP_ProductPrice).getText();
		CustomFunction.waitFor(driver,SOP_BuyNowBtn);
		GUIFunctions.clickElement(driver, SOP_BuyNowBtn, "Buy Now");
	
	}
	
}
