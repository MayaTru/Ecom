package org.maya.ecomnew;

import java.util.List;
import java.util.stream.Collectors;

import org.maya.abstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MyCart extends AbstractComponents{
	
	WebDriver driver;
	
	String js1 = "window.scroll(0,500)";
	String js2 = "window.scroll(500,1000)";
	
	public MyCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	float expTotal = 0;
	
	@FindBy(css = ".cart ul li div:nth-child(2)")
	private List<WebElement> listofProducts;
	
	@FindBy(xpath = "//li[@class='totalRow']/button[text()='Checkout']")
	private WebElement checkOutButton;
	
	@FindBy(xpath = "//li[@class='totalRow']/span[text()='Total']/following-sibling::span")
	private WebElement actTotalElement;
	
	private By byCheckOutButton = By.xpath("//li[@class='totalRow']/button[text()='Checkout']");
	
	private By byactTotalElement = By.xpath("//li[@class='totalRow']/span[text()='Total']/following-sibling::span");
	
	public void CompareProductValueWithTotal() throws InterruptedException {
		waitForElementtoAppear(byactTotalElement);
		listofProducts.stream().map(s->getTotal(s)).collect(Collectors.toList());
		String temp =actTotalElement.getText();
		String temp2 = temp.substring(1,temp.length());
		long actTotal = Long.parseLong(temp2);
		Assert.assertEquals(actTotal, expTotal);
	}

	private Object getTotal(WebElement s) {
		// TODO Auto-generated method stub
		String[] temp = s.getText().split(" ");
		long pcost = Long.parseLong(temp[1].trim());
		expTotal += pcost;
		return null;
	}
	
	public Checkout goToCheckOut() throws InterruptedException {
		executeJS(js1);
		Thread.sleep(2000);
		executeJS(js2);
		waitForElementtobeClicakable(byCheckOutButton);
		System.out.println("->Check1<-");
		checkOutButton.click();
		Checkout obj5 = new Checkout(driver);
		return obj5;
	}
	

}
