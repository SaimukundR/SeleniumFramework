package AutomationFrameworkDesign.resourses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	static ExtentReports extent = new ExtentReports();
	public static ExtentReports getReportObject() {
    	//Extent Reports, Extent sparkReporter
    	String path = System.getProperty("user.dir") + "//reports//index.html";
    	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
    	reporter.config().setReportName("Web Automation Results");
    	reporter.config().setDocumentTitle("Test Results");
    	extent.attachReporter(reporter);
    	extent.setSystemInfo("Tester", "Sai");
    	return extent;
    }
}
