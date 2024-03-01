package org.maya.ecomnew;

import java.util.List;
import java.util.stream.Collectors;

import org.maya.abstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage extends AbstractComponents{
	
	WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = ".ng-star-inserted td label.ng-star-inserted")
	private List<WebElement> OrderList;
	
	private By OrdLst = By.cssSelector(".ng-star-inserted td label.ng-star-inserted");
	
	public List<String> GetOrderId() {
		waitForElementtoAppear(OrdLst);
		List<String> oid = OrderList.stream().map(s->s.getText().split(" ")).map(s->s[1]).collect(Collectors.toList());
		return oid;
	}
	
	public void goToOrd() throws InterruptedException {
		AbstractComponents obj3 = new AbstractComponents(driver);
		obj3.goToOrders();
	}

}
