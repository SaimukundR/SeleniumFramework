package AutomationTesting.TestComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsInitialDemo {

		ExtentReports extent = new ExtentReports();
	  
	    @Test
	    public void demo()
	    {
	    	ExtentTest test = extent.createTest("Demo");
	    	WebDriver driver = new ChromeDriver();
	    	driver.get("https://www.google.com/");
	        System.out.println(driver.getTitle());
	        driver.close();
	        test.fail("test failed");
	        extent.flush();
	       
	    }
	    
	    @BeforeTest
	    public void config() {
	    	//Extent Reports, Extent sparkReporter
	    	String path = System.getProperty("user.dir") + "//reports//index.html";
	    	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	    	reporter.config().setReportName("Web Automation Results");
	    	reporter.config().setDocumentTitle("Test Results");
	    	
	    	
	    	extent.attachReporter(reporter);
	    	extent.setSystemInfo("Tester", "Sai");
	    }
	}

