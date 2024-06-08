package AutomationFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomatioFrameworkDesign.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//PageFactory
	@FindBy(xpath="(//*[@class='input txt'])[1]")
	WebElement cvv;
	
	@FindBy(xpath="(//*[@class='input txt'])[2]")
	WebElement cardName;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement countryBlock;
	
	@FindBy(css="[class*='ta-item']")
	List<WebElement> countryOptions;
	
	public void enterUserAndPaymentDetails(String cvvNo, String nameOnCard, String countryName) {
		cvv.sendKeys(cvvNo);
		cardName.sendKeys(nameOnCard);
		countryBlock.sendKeys(countryName);
		WebElement selectedCountry= countryOptions.stream().filter(country->country.findElement(By.cssSelector("[class*='ta-item'] span")).getText().equalsIgnoreCase("India")).findFirst().orElse(null);
	    Actions a = new Actions(driver);
	    a.moveToElement(selectedCountry).click().build().perform();
	    
	}
	
	
}
