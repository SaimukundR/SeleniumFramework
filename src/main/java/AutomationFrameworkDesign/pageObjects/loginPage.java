package AutomationFrameworkDesign.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomatioFrameworkDesign.AbstractComponents.AbstractComponent;

public class loginPage extends AbstractComponent
{
protected WebDriver driver;
	public loginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(css="#userEmail")
	WebElement emailBlock;
	
	@FindBy(css="#userPassword")
	WebElement passwordBlock;
	
	@FindBy(css="#login")
	WebElement loginButton;
	
	@FindBy(css=".toast-bottom-right")
	WebElement loginErrorMsg;
	
	public ProductCatalogue loginAction(String email, String password) {
		
		emailBlock.sendKeys(email);
		passwordBlock.sendKeys(password);
		loginButton.click();
		return new ProductCatalogue(driver);
		
	}
	public String errorValidation() {
		waitForElementToAppear(loginErrorMsg);
		return loginErrorMsg.getText();
		
	}
	public void goToUrl(String url) {
		driver.get(url);
	}
   
}
