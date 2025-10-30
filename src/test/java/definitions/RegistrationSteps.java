package definitions;

import io.cucumber.java.en.*;
import pageObjects.RegistrationPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import factory.BaseClass;
import io.cucumber.datatable.DataTable;

public class RegistrationSteps {
	public static WebDriver driver;
	public static RegistrationPage registrationPage;
	
	@Given("User is logged in and on registration form")
	public void user_is_logged_in_and_on_registration_form() {
	    driver = BaseClass.getDriver();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Login
	    driver.findElement(By.name("userName")).sendKeys("admin");
	    driver.findElement(By.name("password")).sendKeys("admin123");
	    driver.findElement(By.id("signIn")).click();

	    // Wait until 'student' link is visible and click
	    WebElement studentLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("student")));
	    studentLink.click();

	    // Wait until 'registerLink' is visible and click
	    WebElement registerLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("registerLink")));
	    registerLink.click();

	    // Initialize page object
	    registrationPage = new RegistrationPage(driver);
	    BaseClass.getLogger().info("Login Successfully and Registration Form is opened!.......");
	}

	@When("User fills all fields with valid data")
	public void user_fills_all_fields_with_valid_data(DataTable dataTable) {
		Map<String, String> dataMap = dataTable.asMap(String.class, String.class);

	    if (dataMap.get("Student Name") != null) {
	        registrationPage.setStudentNameInput(dataMap.get("Student Name"));
	    }

	    if (dataMap.get("Mobile Number") != null) {
	        registrationPage.setMobileNumberInput(dataMap.get("Mobile Number"));
	    }

	    if (dataMap.get("Email Id") != null) {
	        registrationPage.setEmailIdInput(dataMap.get("Email Id"));
	    }

	    if (dataMap.get("CGPA") != null) {
	        registrationPage.setCgpaInput(dataMap.get("CGPA"));
	    }

	    if (dataMap.get("Department Name") != null) {
	        registrationPage.setDeptNameInput(dataMap.get("Department Name"));
	    }

	    if (dataMap.get("Backlog Count") != null) {
	        registrationPage.setBacklogCountInput(dataMap.get("Backlog Count"));
	    }

	    
	}

	@When("Clicks on {string} button")
	public void clicks_on_button(String string) {
		registrationPage.clickRegisterBtn();
		BaseClass.getLogger().info("Clicked Register Button!.......");
	    
	}

	@Then("Registration should be successful with a success message")
	public void registration_should_be_successful_with_a_success_message() {
	    try {
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registrationPage.getSuccessElement());
	    	Thread.sleep(2000);
	        String successMsg = registrationPage.getSuccessMessage();

	        // Null or empty check
	        if (successMsg == null || successMsg.trim().isEmpty()) {
	            Assert.fail("Registration success message is missing or empty.");
	        }


	        // Optional: validate specific expected content
	        Assert.assertTrue("Success message does not contain expected text.",
	            successMsg.toLowerCase().contains("registration successful") || successMsg.toLowerCase().contains("success"));

	    } catch (Exception e) {
	        Assert.fail("Exception while verifying success message: " + e.getMessage());
	    }
	}


	@When("User leaves all fields blank:")
	public void user_leaves_all_fields_blank(io.cucumber.datatable.DataTable dataTable) {
		// Intentionally not doing any thing
	}

	@Then("An error message should appear for each required field")
	public void an_error_message_should_appear_for_each_required_field() {
	    try {
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registrationPage.getErrorElement());
	    	String errorMsg = registrationPage.getErrorMessage();
	    	String[] errors = errorMsg.split(", ");
	    	Assert.assertTrue(errors.length == 6);
	    }catch(Exception e) {
	    	Assert.fail();
	    }
	    
	}

	@Then("An error message should appear for invalid input")
	public void an_error_message_should_appear_for_invalid_input() {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registrationPage.getErrorElement());
			Thread.sleep(2000);
	        String errorMsg = registrationPage.getErrorMessage(); // Assuming this returns the div's inner text
	        String[] errors = Arrays.stream(errorMsg.split(","))
	                                .map(String::trim)
	                                .filter(s -> !s.isEmpty())
	                                .toArray(String[]::new);

	        if (errors.length == 0) {
	            Assert.fail("Expected at least one error message, but none found.");
	        }

	        Assert.assertTrue("Error messages not detected properly.", errors.length > 0);

	    } catch (Exception e) {
	        Assert.fail("Exception while verifying error message: " + e.getMessage());
	    }
	}

	@Then("An error message should appear for the required field")
	public void an_error_message_should_appear_for_the_required_field() {
		an_error_message_should_appear_for_invalid_input();
	}

	@When("User registers another student with the same Email Id")
	public void user_registers_another_student_with_the_same_email_id(DataTable dataTable) {
		user_fills_all_fields_with_valid_data(dataTable);
	    
	}

	@Then("An error message should appear for duplicate email")
	public void an_error_message_should_appear_for_duplicate_email() {
		an_error_message_should_appear_for_invalid_input();
	    
	}

	@When("User registers another student with the same Mobile Number")
	public void user_registers_another_student_with_the_same_mobile_number(DataTable dataTable) {
		user_fills_all_fields_with_valid_data(dataTable);
	}

	@Then("An error message should appear for duplicate mobile number")
	public void an_error_message_should_appear_for_duplicate_mobile_number() {
		an_error_message_should_appear_for_invalid_input();
	    
	}



	


}
