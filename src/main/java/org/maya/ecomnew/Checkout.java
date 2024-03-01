package org.maya.ecomnew;

import java.util.List;

import org.maya.abstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout extends AbstractComponents {
	
	WebDriver driver;

	public Checkout(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = "div.form-group input")
	private WebElement SelectCountry;
	
	@FindBy(css = "div.form-group section button span")
	private List<WebElement> countryList;
	
	@FindBy(css = "div.actions a")
	private WebElement placeOrderButton;
	
	private By successOrder = By.cssSelector("#toast-container");
	
	public void SelectCountryFromDropdwon(String country) {
		SelectCountry.sendKeys(country);
		for(int i=0;i<countryList.size();i++) {
			String temp = countryList.get(i).getText();
			if(temp.equalsIgnoreCase("India")) {
				countryList.get(i).click();
				break;
			}
		}
	}
	
	public OrderConfirmationPage goToOrderConfirmationPage() {
		placeOrderButton.click();
		waitForElementtoAppear(successOrder);
		OrderConfirmationPage obj6 = new OrderConfirmationPage(driver);
		return obj6;
	}

}
