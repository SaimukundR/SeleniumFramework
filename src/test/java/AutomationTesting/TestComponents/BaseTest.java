package AutomationTesting.TestComponents;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AutomatioFrameworkDesign.AbstractComponents.AbstractComponent;
import AutomationFrameworkDesign.pageObjects.loginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public loginPage logIn;
	public WebDriver initiallizeDriver() throws IOException, InterruptedException{
		// properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\RK\\Desktop\\SeleniumFrameworkDesign\\src\\main\\java\\AutomationFrameworkDesign\\resourses\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		//String browserName =prop.getProperty("browser");
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver=new ChromeDriver(options);
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webDriver.gecko.driver", "C:\\Users\\RK\\Downloads");
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			//
		}
		
		 //   driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(1440,900));
		   return driver;
  }
	@BeforeMethod(alwaysRun =true)
	public loginPage launchApplication() throws IOException, InterruptedException {
	    driver = initiallizeDriver();
	    AbstractComponent wait = new AbstractComponent(driver);
	    driver.manage().window().maximize();
		wait.waitImplicit(3);
		logIn = new loginPage(driver);
		logIn.goToUrl("https://rahulshettyacademy.com/client/");
		return logIn;
		
	}
	public List<HashMap<String, String>> getJsonDataToMap(String jsonFilePath) throws IOException {
		//Converting Json to string 
		String jsonContent = 
				FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
		//String to HashMap using Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>(){
		});
		
        return data;
		
	}
	public String getScreeshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return (System.getProperty("user.dir")+"//reports//" + testCaseName + ".png");
	}
	@AfterMethod(alwaysRun =true)
	public void closeTabs() {
		driver.quit();
	}

}
		
