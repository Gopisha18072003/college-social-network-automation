package definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class Hooks {
	public static WebDriver driver;
	public static String baseURL = "http://webapps.tekstac.com:2121/";
	@Before
	public void setup() {
		BaseClass.initializeDriver();
		driver = BaseClass.getDriver();
		driver.get(baseURL);
		driver.manage().window().maximize();
	}
	
	@After
	public void tearDown(Scenario scenario) {
		driver.quit();
	}
	
	@AfterStep
	public void addScreenshot(Scenario scenario) {
		if(scenario.isFailed()) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
	}
}
