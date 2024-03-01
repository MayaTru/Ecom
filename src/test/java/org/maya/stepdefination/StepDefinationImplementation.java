package org.maya.stepdefination;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.maya.ecomnew.Checkout;
import org.maya.ecomnew.LandingPage;
import org.maya.ecomnew.MyCart;
import org.maya.ecomnew.OrderConfirmationPage;
import org.maya.ecomnew.ProductCatalog;
import org.maya.ecomnew.YourOrdersPage;
import org.maya.testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImplementation extends BaseTest {
	
	public LandingPage LPobj;
	public ProductCatalog PCobj;
	public MyCart MCobj;
	public Checkout COobj;
	public OrderConfirmationPage PCPobj;
	public YourOrdersPage YOPobj;
	
	List<String> checkList;
	String country = "ind";
	String[] wp = {"ZARA COAT 3","ADIDAS ORIGINAL"};
	List<String> wantpro = Arrays.asList(wp);
	String url = "https://rahulshettyacademy.com/client";
	String errmsg1;
	
	@Given("I landed on Ecommerece Page")
	public void i_landed_on_ecommerece_page() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		LPobj = launchApp(url);
	}

	@Given("^Login Application with (.+) and (.+)$")
	public void login_application_with_mayatru_mail_com_and_maya(String username, String password) {
	    // Write code here that turns the phrase above into concrete actions
		PCobj = LPobj.LoginApp(username, password);
	}

	@When("I add product to Cart")
	public void i_add_product_to_cart() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		List<WebElement> prodList = PCobj.getProductList();
		MCobj = PCobj.getProductAndSelectByNames(prodList,wantpro);
	}

	@When("Checkout and Submit Order")
	public void checkout_and_submit_order() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		MCobj.CompareProductValueWithTotal();
		COobj = MCobj.goToCheckOut();
		COobj.SelectCountryFromDropdwon(country);
		PCPobj =  COobj.goToOrderConfirmationPage();
		checkList = PCPobj.GetOrderId();
		YOPobj = PCPobj.goToOrders();
	}

	@Then("I verify the orderList")
	public void i_verify_the_order_list() {
	    // Write code here that turns the phrase above into concrete actions
		YOPobj.verifyOrders(checkList);
	}
	
	@Then("^I verify the error with (.+) and (.+)$")
	public void i_verify_the_error(String username, String password) {
	    // Write code here that turns the phrase above into concrete actions
		errmsg1 = LPobj.getErrorText(username, password);
		System.out.println(errmsg1);
	}

	@Then("Verfiy error text {string}")
	public void i_verify_the_order_list(String string) {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(string, "Incorrect email or password.");
	}
}
