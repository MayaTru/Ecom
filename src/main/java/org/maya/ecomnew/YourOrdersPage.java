package org.maya.ecomnew;

import java.util.List;

import org.maya.abstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourOrdersPage extends AbstractComponents {
	
	WebDriver driver;
	
	public YourOrdersPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//table/tbody/tr/th")
	private List<WebElement> TableOrderId;
	
	public void verifyOrders(List<String> checkList) {
		for(int i=0;i<TableOrderId.size();i++) {
			if(checkList.contains(TableOrderId.get(i).getText())) {
				System.out.println(TableOrderId.get(i).getText());
			}
		}
	}
}
