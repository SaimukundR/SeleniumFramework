package AutomationFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AutomatioFrameworkDesign.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	
WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//PageFactory
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderedProducts;
	
	
	public boolean orderHistoryValidate(String product1, String product2) {
		boolean validation = false;
		 Boolean validate3= orderedProducts.stream().anyMatch(cartProduct->
         cartProduct.getText().equalsIgnoreCase(product1));
	     Boolean validate4= orderedProducts.stream().anyMatch(cartProduct->
         cartProduct.getText().equalsIgnoreCase(product2));
	     
	     if(validate3 == true && validate4 == true) {
	     validation = true;
	}
	     
		return validation;
	 
	  
	}		
}
