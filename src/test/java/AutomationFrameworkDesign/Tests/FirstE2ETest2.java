package AutomationFrameworkDesign.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomatioFrameworkDesign.AbstractComponents.AbstractComponent;
import AutomationFrameworkDesign.pageObjects.CartActions;
import AutomationFrameworkDesign.pageObjects.CheckoutPage;
import AutomationFrameworkDesign.pageObjects.OrderConfirmationPage;
import AutomationFrameworkDesign.pageObjects.OrderPage;
import AutomationFrameworkDesign.pageObjects.ProductCatalogue;
import AutomationFrameworkDesign.pageObjects.loginPage;
import AutomationTesting.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstE2ETest2 extends BaseTest {
//	public String product1= "ADIDAS ORIGINAL";
//	public String product2="IPHONE 13 PRO";
	@Test(dataProvider="getData",groups= {"Retail"})
	public void E2ETest(HashMap<String,String> input) throws IOException, InterruptedException{
		
	    String confirmationMsg = "Thankyou for the order.";
		String expectedConfirmationMsg = confirmationMsg.toUpperCase();
		//Login
		ProductCatalogue productCatalogue = logIn.loginAction(input.get("email"),input.get("password"));
		
		//Homepage
		productCatalogue.getProductByName1(input.get("product1"));
		productCatalogue.getProductByName2(input.get("product2"));
		productCatalogue.addToBasket(input.get("product1"), input.get("product2"));
		 CartActions cart= productCatalogue.goToBasket();
		 
	   //Cart
	    Boolean result= cart.cartValidate(input.get("product1"), input.get("product2"));
	    Assert.assertTrue(result);
	    CheckoutPage checkout = cart.goToCheckout();
		
	  //Checkout
		checkout.enterUserAndPaymentDetails("1234","test" ,"ind");
		OrderConfirmationPage confirmation =checkout.placeOrder();
		
	   //Order confirmation
	    Assert.assertEquals(confirmation.orderConfirmationValidation().getText(),expectedConfirmationMsg);
	}
	
	@Test(dataProvider="getData",dependsOnMethods= {"E2ETest"})
	public void orderHistory(HashMap<String,String> input) {
		ProductCatalogue productCatalogue = logIn.loginAction(input.get("email"), input.get("password"));
		productCatalogue.goToOrderHistory().orderHistoryValidate(input.get("product1"), input.get("product2"));
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")                         
				+ "\\src\\test\\java\\AutomationFrameworkDesign\\data\\RetailOrder.json");
		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "test454424@gmail.com");
//		map.put("password", "Flashback@123");
//		map.put("product1", "ADIDAS ORIGINAL");
//		map.put("product2", "IPHONE 13 PRO");
////		HashMap<String,String> map2 = new HashMap<String,String>();
////		map.put("email", "test454424@gmail.com");
////		map.put("password", "Flashback@123");
////		map.put("product1", "ZARA COAT 3");
////		map.put("product2", "IPHONE 13 PRO");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
		

}
