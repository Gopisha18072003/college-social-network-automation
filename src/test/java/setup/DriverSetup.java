package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSetup {
public static WebDriver driver;
	
	public static WebDriver getDriver() {
		System.setProperty("webdriver.gecko.driver", "D:\\Chrome_Download\\geckodriver-v0.36.0-win32\\geckodriver.exe");
		driver = new ChromeDriver();
		return driver;
	}
}
