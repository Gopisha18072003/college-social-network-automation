package definitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;

public class LoginSteps {
	
	public static WebDriver driver;
	public static LoginPage loginPage;

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        driver= BaseClass.getDriver();
    	driver.get("http://webapps.tekstac.com:2121/");
    	loginPage = new LoginPage(driver);
    	Assert.assertTrue(loginPage.getHeading().isDisplayed());
    }

    @When("user enters credentials \\(username {string} and password {string})")
    public void user_enters_credentials_username_and_password(String username, String password) {
    	if(username.isEmpty() && !password.isEmpty()) {
    		loginPage.setPassword(password);
    	}else if(!username.isEmpty() && password.isEmpty()) {
    		loginPage.setUsername(username);
    	}else if(username.isEmpty() && password.isEmpty()) {
    		
    	}else {
    		loginPage.setUsername(username);
    		loginPage.setPassword(password);
    	}
    	
    }

    @When("user clicks the login button")
    public void user_clicks_the_sign_in_button() {
    	loginPage.clickLogin();
    	try {
    		Thread.sleep(2000);
    	}catch(InterruptedException e) {
    		
    	}
    }

    @Then("the user should be successfully authenticated.")
    public void the_user_should_be_successfully_authenticated() {
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Welcome Admin!')]")).isDisplayed());
        
        
    }

    @Then("it should show {string}.")
    public void it_should_show(String expectedMessage) {
    	String actualMessage = loginPage.getErrorMessage();
    	Assert.assertEquals(expectedMessage, actualMessage);
    	driver.quit();
    }
}
