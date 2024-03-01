package org.maya.ecomnew;

import org.maya.abstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(id = "userEmail")
	private WebElement userEmail;
	
	@FindBy(css = "#userPassword")
	private WebElement password;
	
	private By productBy = By.cssSelector("div.card-body");
	
	private By byErrMsg = By.cssSelector("#toast-container div");
	
	@FindBy(xpath = "//input[@id='login']")
	private WebElement login;
	
	@FindBy(css = "#toast-container div")
	private WebElement errorMsg;
	
	public ProductCatalog LoginApp(String usrname, String paswrd) {
		userEmail.sendKeys(usrname);
		password.sendKeys(paswrd);
		login.click();
		waitForElementtoAppear(productBy);
		ProductCatalog obj2 = new ProductCatalog(driver);
		return obj2;
	}
	
	public void GoTo(String url) {
		driver.get(url);
	}
	
	public String getErrorText(String usrname, String paswrd) {
		userEmail.sendKeys(usrname);
		password.sendKeys(paswrd);
		login.click();
		waitForElementtoAppear(byErrMsg);
		String errmsg = errorMsg.getText();
		return errmsg;
	}
}
