package org.maya.ecomnew;

import java.io.IOException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.maya.resources.ExtentReporterNG;
import org.maya.testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class SubmitOrderTest extends BaseTest {
	String url = "https://rahulshettyacademy.com/client";
	//String username = "mayatru@mail.com";
	//String password = "Maya@0812";
	String path = "\\src\\test\\java\\org\\maya\\testdata\\SelectOrder.json";
	String pathtoss = "\\reports\\index.html";
	String country = "ind";
	String[] wp = {"ZARA COAT 3","ADIDAS ORIGINAL"};
	List<String> wantpro = Arrays.asList(wp);

	//ExtentReports extents1 = ExtentReporterNG.initReport();
	
	@Test(dataProvider = "getData")
	public void Test1(HashMap<String, String> inputStr) throws InterruptedException, IOException{
	//public void Test1(String emailuser,String password) throws InterruptedException, IOException {
		//ExtentTest test =  extents1.createTest("My First Test");
		LandingPage obj1 = launchApp(url);
		ProductCatalog obj2 = obj1.LoginApp(inputStr.get("email"), inputStr.get("password"));
		//ProductCatalog obj2 = obj1.LoginApp(emailuser, password);
		List<WebElement> prodList = obj2.getProductList();
		MyCart obj4 = obj2.getProductAndSelectByNames(prodList,wantpro);
		obj4.CompareProductValueWithTotal();
		Checkout obj5 = obj4.goToCheckOut();
		obj5.SelectCountryFromDropdwon(country);
		OrderConfirmationPage obj6 =  obj5.goToOrderConfirmationPage();
		List<String> checkList = obj6.GetOrderId();
		YourOrdersPage obj7 = obj6.goToOrders();
		obj7.verifyOrders(checkList);
		//test.pass("Passed");
		//extents1.flush();
	}
	@Test(dependsOnMethods = {"Test1"}, groups = {"Smoke"})
	public void Test2() throws InterruptedException, IOException {
		System.out.println("Check3");
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		/*
		 * HashMap<String, String> hmp = new HashMap<String, String>();
		 * hmp.put("emailuser", "mayatru@mail.com"); hmp.put("password", "Maya@0812");
		 * HashMap<String, String> hmp2 = new HashMap<String, String>();
		 * hmp2.put("emailuser", "mayatru@mail.com"); hmp2.put("password", "Maya@0811");
		 * Object[][] obj1 = new Object[][] {{hmp},{hmp2}};
		 */
		
		List<HashMap<String, String>> dataitrs = initData(path);
		//HashMap<String, String> newhm = new HashMap<String, String>();
		//Object[][] obj1 = new Object[][] {{"mayatru@mail.com","Maya@0812"},{"mayatru@mail.com","Maya@0811"}};
		Object[][] obj1 = new Object[][] {{dataitrs.get(0)},{dataitrs.get(1)}};
		//Object[][] obj1 = new Object[dataitrs.size()][2];
		//for(int i=0;i<dataitrs.size();i++) {
		//	newhm.put(dataitrs.get(i).entrySet().getKey(),entry.getValue());
		//	obj1[i][0] = dataitrs.get(i).get("email");
		//	obj1[i][1] = dataitrs.get(i).get("password");
		//}
		return obj1;
	}
}
