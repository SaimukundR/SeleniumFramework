package selenium.stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import AutomationFrameworkDesign.pageObjects.CartActions;
import AutomationFrameworkDesign.pageObjects.CheckoutPage;
import AutomationFrameworkDesign.pageObjects.OrderConfirmationPage;
import AutomationFrameworkDesign.pageObjects.ProductCatalogue;
import AutomationTesting.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RetailStepDefinition extends BaseTest {
	public AutomationFrameworkDesign.pageObjects.loginPage loginPage;
	public ProductCatalogue productCatalogue;
	public CartActions cart;
	public CheckoutPage checkout;
	public OrderConfirmationPage confirmation;
	public WebDriver driver;
	
	@Given("the user has landed on the Ecommerce page")
	public void UserLandedOnSite() throws IOException, InterruptedException {
		
		loginPage= launchApplication();
	}
	
	@Given("^user has logged in with (.+) and (.+)$")
	public void userLogsIn(String username,String password) {
		productCatalogue = loginPage.loginAction(username,password);
	}
	
	@When("^user adds (.+) to the basket$")
	public void useraddsProductToBasket(String productName) {
		productCatalogue.getProductByName1(productName);
		productCatalogue.addToBasketCucumberFormat(productName);
	    cart= productCatalogue.goToBasket();
	}
	
	@And("^checksout (.+) places the order$")
	public void userPlacesOrder(String productName) {
		Boolean result= cart.cartValidateCucumberFormat(productName);
	    Assert.assertTrue(result);
	    checkout = cart.goToCheckout();
	    checkout.enterUserAndPaymentDetails("1234","test" ,"ind");
		confirmation =checkout.placeOrder();
}
	@Then("^(.+) message is displayed$")
	public void orderConfirmation(String cnfrmMsg) {
		Assert.assertEquals(confirmation.orderConfirmationValidation().getText(),cnfrmMsg);
		
	}
	
	@Given("^user tries to login in with (.+) and (.+)$")
	public void userTriesToLogin(String username,String password) {
		productCatalogue = loginPage.loginAction(username,password);
	}
	@Then("^validate (.+) is displayed$")
	public void errorValidation(String errorMsg) {
		Assert.assertEquals(logIn.errorValidation(),errorMsg);
	}
	
	
	
}
