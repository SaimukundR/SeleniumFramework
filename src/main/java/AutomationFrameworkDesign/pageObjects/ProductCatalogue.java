package AutomationFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import AutomatioFrameworkDesign.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent
{
WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement addedToBasketAnimation;
	
	
	By addTocart= By.cssSelector(".btn.w-10.rounded");
	
	public List<WebElement> getProductList() {
		
		return products;
		
	}
	
	public WebElement getProductByName1(String product1) {
		WebElement selectedProduct1= getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(product1)).findFirst().orElse(null);
		
		return selectedProduct1;
	}
	public WebElement getProductByName2(String product2) {
		
		WebElement selectedProduct2 = products.stream().filter(product->
	     product.findElement(By.cssSelector("b")).getText().equals(product2)).findFirst().orElse(null);
		return selectedProduct2;
	}
	
	public void addToBasket(String product1, String product2) {
		getProductByName1(product1).findElement(addTocart).click();
		waitForElementToDisappear(addedToBasketAnimation);
		waitForElementToDisappear(successMsg);
		getProductByName2(product2).findElement(addTocart).click();
		waitForElementToDisappear(addedToBasketAnimation);
		waitForElementToDisappear(successMsg);
	}
	
	public void addToBasketCucumberFormat(String product1) {
		getProductByName1(product1).findElement(addTocart).click();
		waitForElementToDisappear(addedToBasketAnimation);
		waitForElementToDisappear(successMsg);
	}
	


}
