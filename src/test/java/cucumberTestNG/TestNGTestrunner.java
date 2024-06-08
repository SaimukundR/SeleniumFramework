package cucumberTestNG;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src\\test\\java\\cucumberTestNG",glue="selenium.stepDefinitions", monochrome= true, tags="@errorValidation and not @Regression", plugin= {"html:target/cucumber.html"})
public class TestNGTestrunner extends AbstractTestNGCucumberTests {

}
  