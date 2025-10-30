package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

	@FindBy(id="studentName")
	WebElement studentNameInput;
	
	@FindBy(id="mobileNumber")
	WebElement mobileNumberInput;
	
	@FindBy(id="emailId")
	WebElement emailIdInput;
	
	@FindBy(id="cgpa")
	WebElement cgpaInput;
	
	@FindBy(id="deptName")
	WebElement deptNameInput;
	
	@FindBy(id="backlogCount")
	WebElement backlogCountInput;
	
	@FindBy(id="register")
	WebElement registerBtn;
	
	@FindBy(id="error")
	WebElement errorMessage;
	
	@FindBy(id="result")
	WebElement successMessage;
	
	public RegistrationPage() {
		
	}
	public RegistrationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public WebElement getStudentNameInput() {
		return studentNameInput;
	}
	
	public void setStudentNameInput(String studentName) {
		studentNameInput.sendKeys(studentName);;
	}
	public String getMobileNumberInput() {
		return mobileNumberInput.getText();
	}
	public void setMobileNumberInput(String mobileNumber) {
		mobileNumberInput.sendKeys(mobileNumber);;
	}
	public String getEmailIdInput() {
		return emailIdInput.getText();
	}
	public void setEmailIdInput(String emailId) {
		emailIdInput.sendKeys(emailId);;
	}
	public String getCgpaInput() {
		return cgpaInput.getText();
	}
	public void setCgpaInput(String cgpa) {
		cgpaInput.clear();
		cgpaInput.sendKeys(cgpa);
	}
	public String getDeptNameInput() {
		return deptNameInput.getText();
	}
	public void setDeptNameInput(String deptName) {
		deptNameInput.sendKeys(deptName);
	}
	public String getBacklogCountInput() {
		return backlogCountInput.getText();
	}
	public void setBacklogCountInput(String backlogCount) {
		backlogCountInput.clear();
		backlogCountInput.sendKeys(backlogCount);
	}

	public WebElement getRegisterBtn() {
	    return registerBtn;
	}
	
	public void clickRegisterBtn() {
		registerBtn.click();
	}
	
	public String getErrorMessage() {
		return errorMessage.getText();
	}
	
	public String getSuccessMessage()
	{
		return successMessage.getText();
	}
	public void clearAllFields() {
		studentNameInput.clear();
		mobileNumberInput.clear();
		emailIdInput.clear();
		cgpaInput.clear();
		deptNameInput.clear();
		backlogCountInput.clear();
		
	}
	public WebElement getErrorElement() {
		return errorMessage;
	}
	public WebElement getSuccessElement() {
		return successMessage;
	}
}
