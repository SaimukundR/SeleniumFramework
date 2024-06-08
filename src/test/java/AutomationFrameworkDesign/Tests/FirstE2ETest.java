package AutomationFrameworkDesign.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AutomationFrameworkDesign.pageObjects.loginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstE2ETest {
	public static void main(String[] args) throws InterruptedException {
		String product1= "ZARA COAT 3";
		String product2="IPHONE 13 PRO";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		loginPage signIn = new loginPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");	
//		driver.findElement(By.cssSelector(".login-wrapper-footer-text")).click();
//		driver.findElement(By.cssSelector("[type='firstName']")).sendKeys("Saimukund");
//		driver.findElement(By.cssSelector("[type='lastName']")).sendKeys("Radhakrishna");
//		driver.findElement(By.cssSelector("[type='email']")).sendKeys("test454424@gmail.com");
//		driver.findElement(By.cssSelector("[type='text']")).sendKeys("7259298628");
//		WebElement occupationOptions= driver.findElement(By.cssSelector("[formcontrolname='occupation']"));
//		
//		Select occupation = new Select(occupationOptions);
//		occupation.selectByVisibleText("Engineer");
//		
//		driver.findElement(By.xpath("(//input[@formcontrolname='gender'])[1]")).click();
//		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Flashback@123");
//		driver.findElement(By.cssSelector("#confirmPassword")).sendKeys("Flashback@123");
//		driver.findElement(By.cssSelector("[formcontrolname='required']")).click();
//		driver.findElement(By.cssSelector(".btn.btn-block.login-btn")).click();
//		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		
		//Login
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("test454424@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Flashback@123");
		driver.findElement(By.cssSelector("#login")).click();
		
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	WebElement selectedProduct1=	products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(product1)).findFirst().orElse(null);
	WebElement selectedProduct2 = products.stream().filter(product->
	     product.findElement(By.cssSelector("b")).getText().equals(product2)).findFirst().orElse(null);
	
	selectedProduct1.findElement(By.cssSelector(".btn.w-10.rounded")).click();
	WebElement SuccessMsg= driver.findElement(By.id("toast-container"));
	WebElement addedToBasketAnimation= driver.findElement(By.cssSelector(".ng-animating"));
	//Explicit waits
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.invisibilityOf(addedToBasketAnimation));
	Assert.assertEquals(SuccessMsg.getText(),"Product Added To Cart");
	wait.until(ExpectedConditions.invisibilityOf(SuccessMsg));
	
	selectedProduct2.findElement(By.cssSelector(".btn.w-10.rounded")).click();
	wait.until(ExpectedConditions.invisibilityOf(addedToBasketAnimation));
	wait.until(ExpectedConditions.visibilityOf(SuccessMsg));
	Assert.assertEquals(SuccessMsg.getText(),"Product Added To Cart");
	wait.until(ExpectedConditions.invisibilityOf(SuccessMsg));
	driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
	
	//Cart
	List<WebElement> cartProducts= driver.findElements(By.cssSelector(".infoWrap h3"));
	  Boolean validate1= cartProducts.stream().anyMatch(cartProduct->
	            cartProduct.getText().equals(product1));
		Assert.assertTrue(validate1);
	  Boolean validate2= cartProducts.stream().anyMatch(cartProduct->
      cartProduct.getText().equals(product2));
		Assert.assertTrue(validate2);
		driver.findElement(By.cssSelector(".totalRow .btn.btn-primary")).click();
		
		//Checkout
		driver.findElement(By.xpath("(//*[@class='input txt'])[1]")).sendKeys("3432");
		driver.findElement(By.xpath("(//*[@class='input txt'])[2]")).sendKeys("Test");
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> countries = driver.findElements(By.cssSelector("[class*='ta-item']"));
		WebElement selectedCountry= countries.stream().filter(country->country.findElement(By.cssSelector("[class*='ta-item'] span")).getText().equalsIgnoreCase("India")).findFirst().orElse(null);
	    Actions a = new Actions(driver);
	    a.moveToElement(selectedCountry).click().build().perform();
	    a.moveToElement(driver.findElement(By.xpath("//*[text()='Place Order ']"))).click().build().perform();
	    
	    //Order confirmation
	    String confirmation = "Thankyou for the order.";
	    String expectedConfirmation= confirmation.toUpperCase();
	    
	    wait.until(ExpectedConditions.visibilityOf(SuccessMsg));
	 String actualConfirmation= driver.findElement(By.cssSelector(".hero-primary")).getText(); 
	    Assert.assertEquals(actualConfirmation,expectedConfirmation);
	    driver.quit();
	}  
		

}
