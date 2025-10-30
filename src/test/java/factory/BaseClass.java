package factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import setup.DriverSetup;


public class BaseClass {

	static Logger logger;
	public static WebDriver driver;
	public static void initializeDriver() {
		driver = DriverSetup.getDriver();
	}
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static Logger getLogger() 
	{		 
		logger=LogManager.getLogger(); //Log4j
		return logger;
	}
	

	
	

	
	
}
