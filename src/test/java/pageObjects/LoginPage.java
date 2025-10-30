package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "userName")
    WebElement usernameInput;

    @FindBy(name = "password")
    WebElement passwordInput;

    @FindBy(id = "signIn")
    WebElement loginButton;
    
    @FindBy(xpath="//*[@id=\"loginImage\"]/h1")
    WebElement heading;

    @FindBy(id = "error")  // Adjust based on actual HTML
    WebElement errorMessage;

    public void setUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }
    
    public WebElement getHeading() {
    	return heading;
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}