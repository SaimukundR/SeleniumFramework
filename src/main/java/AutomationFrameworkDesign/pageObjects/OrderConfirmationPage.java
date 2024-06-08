package AutomationFrameworkDesign.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import AutomatioFrameworkDesign.AbstractComponents.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent {
	
	WebDriver driver;
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(css=".hero-primary")
	WebElement actualConfirmation;
	 //Order confirmation
   
    public WebElement orderConfirmationValidation() {
    	waitForElementToAppear(successMsg);
    	return actualConfirmation;
    }
    
}
