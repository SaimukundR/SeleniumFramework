package AutomationFrameworkDesign.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import AutomationFrameworkDesign.pageObjects.ProductCatalogue;
import AutomationTesting.TestComponents.BaseTest;
import AutomationTesting.TestComponents.Retry;

public class ErrorValidations extends BaseTest {
	ExtentReports extent = new ExtentReports();
	@Test(groups= {"errorHandling"}, retryAnalyzer= Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException{
		String productName1= "ZARA COAT 3";
		String productName2="IPHONE 13 PRO";
	    String confirmationMsg = "Thankyou for the order.";
		String expectedConfirmationMsg = confirmationMsg.toUpperCase();
		//Login
		ProductCatalogue productCatalogue = logIn.loginAction("test4524@gmail.com", "Flashback@13");
		Assert.assertEquals(logIn.errorValidation(),"Incorrect email or password.");
		ExtentTest test = extent.createTest("Demo");
		test.fail("error identified");
		

	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException{
		String productName1= "ZARA COAT 3";
		String productName2="IPHONE 13 PRO";
	    String confirmationMsg = "Thankyou for the order.";
		String expectedConfirmationMsg = confirmationMsg.toUpperCase();
		//Login
		ProductCatalogue productCatalogue = logIn.loginAction("test4524@gmail.com", "Flashback@13");
		Assert.assertEquals(logIn.errorValidation(),"Incorrect email or password.");
		ExtentTest test = extent.createTest("Demo");
		test.fail("error identified");
	}
}