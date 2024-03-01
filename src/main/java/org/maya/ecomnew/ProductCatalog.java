package org.maya.ecomnew;

import java.util.List;

import org.maya.abstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalog extends AbstractComponents {
	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = "div.card-body")
	private List<WebElement> prodList;
	
	private By productBy = By.cssSelector("div.card-body");
	
	private By animationWait = By.cssSelector(".ng-animating");
	
	private By AddedText = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementtoAppear(productBy);
		return prodList;
	}
	public MyCart getProductAndSelectByNames(List<WebElement> prodList, List<String> wantpro) throws InterruptedException {
		prodList.stream().forEach(s->{
			try {
				SelPro(s,wantpro);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		AbstractComponents obj3 = new AbstractComponents(driver);
		return obj3.goToMyCart();
	}
	private void SelPro(WebElement s, List<String> wantpro) throws InterruptedException {
		// TODO Auto-generated method stub
		if(wantpro.contains(s.findElement(By.cssSelector("h5 b")).getText())) {
				s.findElement(By.xpath("button[2]")).click();
				waitForElementtoAppear(animationWait);
				waitForElementtoDisappear(AddedText);
		}
	}
	
}
