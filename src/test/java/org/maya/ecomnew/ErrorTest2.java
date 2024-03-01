package org.maya.ecomnew;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import org.maya.testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ErrorTest2 extends BaseTest {
	String url = "https://rahulshettyacademy.com/client";
	String username = "mayatru@mail.com";
	String password = "Maya@0812";
	String country = "ind";
	String[] wp = {"ZARA COAT 33","ADIDAS ORIGINAL"};
	List<String> wantpro = Arrays.asList(wp);
	@Test(groups = {"Smoke"},retryAnalyzer = org.maya.testcomponents.Retry.class)
	public void Test1_Err() throws InterruptedException, IOException {
		String username = "mayatru@mail.com";
		String password = "Maya@0811";
		LandingPage obj1 = launchApp(url);
		String errmsg1 = obj1.getErrorText(username, password);
		System.out.println(errmsg1);
	}
	
	//@Test
	public void Test2_Err() throws InterruptedException, IOException {
		LandingPage obj1 = launchApp(url);
		ProductCatalog obj2 = obj1.LoginApp(username, password);
		List<WebElement> prodList = obj2.getProductList();
		MyCart obj4 = obj2.getProductAndSelectByNames(prodList,wantpro);
		obj4.CompareProductValueWithTotal();
		Checkout obj5 = obj4.goToCheckOut();
		obj5.SelectCountryFromDropdwon(country);
		OrderConfirmationPage obj6 =  obj5.goToOrderConfirmationPage();
		List<String> checkList = obj6.GetOrderId();
		YourOrdersPage obj7 = obj6.goToOrders();
		obj7.verifyOrders(checkList);
		Thread.sleep(5000);
		driver.quit();
	}
}
