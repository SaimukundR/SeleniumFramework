package AutomatioFrameworkDesign.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AutomationFrameworkDesign.pageObjects.CartActions;
import AutomationFrameworkDesign.pageObjects.CheckoutPage;
import AutomationFrameworkDesign.pageObjects.OrderConfirmationPage;
import AutomationFrameworkDesign.pageObjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}
//	public String product1= "ZARA COAT 3";
//	public String product2="IPHONE 13 PRO";
	public String confirmation = "Thankyou for the order.";
	public String expectedConfirmation = confirmation.toUpperCase();
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	protected
	WebElement cart;

	@FindBy(css=".totalRow .btn.btn-primary")
	WebElement checkout;
	
	@FindBy(xpath="//*[text()='Place Order ']")
	WebElement placeOrder;
	
	@FindBy(id="toast-container")
	public
	WebElement successMsg;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHistory;
	public void waitImplicit(int value) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(value));
	}
	public void waitForElementToDisappear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(13));
		wait.until(ExpectedConditions.invisibilityOf(findBy));
	}
	public void waitForElementToBeClickable(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(13));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	public void waitForElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(13));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	public CartActions goToBasket() {
		waitForElementToBeClickable(cart);
		cart.click();
		return new CartActions(driver);
	}
	
	public OrderPage goToOrderHistory() {
		waitForElementToBeClickable(orderHistory);
		orderHistory.click();
		OrderPage orders = new OrderPage(driver);
		return orders;
	}
	
	public CheckoutPage goToCheckout() {
		  checkout.click();
		  return new CheckoutPage(driver);
		  
	  }
	
	public OrderConfirmationPage placeOrder() {
		  placeOrder.click();
		  return new OrderConfirmationPage(driver);
		  
	  }
	

}
