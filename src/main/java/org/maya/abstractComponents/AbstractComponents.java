package org.maya.abstractComponents;

import java.time.Duration;

import org.maya.ecomnew.MyCart;
import org.maya.ecomnew.YourOrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	WebDriver driver;
	
	String js1 = "window.scroll(0,0)";
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement MyCartButton;
	
	By ByMyCartButton = By.xpath("//button[@routerlink='/dashboard/cart']");
	
	@FindBy(xpath = "//ul[1]/li[3]/button[1]")
	WebElement OrdersButton;
	
	By ByOrdersButton = By.xpath("//ul[1]/li[3]/button[1]");
	
	public MyCart goToMyCart() throws InterruptedException {
		Thread.sleep(1000);
		waitForElementtobeClicakable(ByMyCartButton);
		MyCartButton.click();
		MyCart obj4 = new MyCart(driver);
		return obj4;
	}
	
	public YourOrdersPage goToOrders() throws InterruptedException {
		executeJS(js1);
		Thread.sleep(1000);
		executeJS(js1);
		waitForElementtobeClicakable(ByOrdersButton);
		OrdersButton.click();
		YourOrdersPage obj7 = new YourOrdersPage(driver);
		return obj7;
	}
	
	public void waitForElementtoAppear(By findBy) {
		WebDriverWait w1 = new WebDriverWait(driver, Duration.ofSeconds(8));
		w1.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementtoDisappear(By findBy) {
		WebDriverWait w1 = new WebDriverWait(driver, Duration.ofSeconds(8));
		w1.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void waitForElementtobeClicakable(By findBy) {
		WebDriverWait w1 = new WebDriverWait(driver, Duration.ofSeconds(8));
		w1.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	
	public void executeJS(String myquery) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(myquery);
	}
	
}
