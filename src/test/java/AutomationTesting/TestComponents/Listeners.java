package AutomationTesting.TestComponents;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import AutomationFrameworkDesign.resourses.ExtentReporterNG;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

public class Listeners extends BaseTest implements ITestListener{
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal extentTest = new ThreadLocal();
	@Override
	public void onTestStart(ITestResult result) {
		test= extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // sets unique thread ID for each class that is run and will not confuse among the threads when providing result
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("test passed and listeners has executed");
		((ExtentTest) extentTest.get()).log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("failed the execution for listeners to run");
		((ExtentTest) extentTest.get()).fail(result.getThrowable()); 
		//getting driver object from the parent class
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// storing screenshot in a path and using that by attaching in extent object which will paste that screen shot in that report after our test failed
		String filePath = null;
		try {
			filePath = getScreeshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((ExtentTest) extentTest.get()).addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// ss of the failure
	}
	

	@Override
	public void onStart(ITestContext context) {
		// ss of the failure
}
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
}
}
