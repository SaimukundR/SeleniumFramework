package AutomationFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AutomatioFrameworkDesign.AbstractComponents.AbstractComponent;

public class CartActions extends AbstractComponent {
	
	
WebDriver driver;
boolean validation;

	public CartActions(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//PageFactory
	@FindBy(css=".infoWrap h3")
	List<WebElement> cartProducts;
	
	
	public boolean cartValidate(String product1, String product2) {
		 Boolean validate1= cartProducts.stream().anyMatch(cartProduct->
         cartProduct.getText().equalsIgnoreCase(product1));
	     Boolean validate2= cartProducts.stream().anyMatch(cartProduct->
         cartProduct.getText().equalsIgnoreCase(product2));
	     if(validate1 == true && validate2 == true) {
		     validation = true;
		
	}
	     return validation;
	  
		
}
	public boolean cartValidateCucumberFormat(String product1) {
		 Boolean validate1= cartProducts.stream().anyMatch(cartProduct->
        cartProduct.getText().equalsIgnoreCase(product1));
	     if(validate1 == true ) {
		     validation = true;
		
	}
	     return validation;
	  		
}
}
