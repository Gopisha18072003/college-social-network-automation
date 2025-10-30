package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ViewAllStudentPage {

    // 1. Login Page
    public static class LoginPage {
        WebDriver driver;
        WebDriverWait wait;

        By username = By.id("username");
        By password = By.id("password");
        By loginBtn = By.id("signIn");

        public LoginPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        public void open() {
            driver.get("http://webapps.tekstac.com:2121/");
        }

        public void login(String user, String pass) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(user);
            wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(pass);
            wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        }
    }

    // 2. Home Page
    public static class HomePage {
        WebDriver driver;
        WebDriverWait wait;

        By studentPortalBtn = By.id("student");
        By viewAllStudentLink = By.id("viewAllStudentLink");

        public HomePage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        public void openStudentPortal() {
            wait.until(ExpectedConditions.elementToBeClickable(studentPortalBtn)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(viewAllStudentLink));
        }

        public void navigateToModule(String moduleName) {
            if (moduleName.equalsIgnoreCase("View All Student")) {
                WebElement module = wait.until(ExpectedConditions.elementToBeClickable(viewAllStudentLink));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", module);
                try {
                    module.click();
                } catch (ElementClickInterceptedException e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", module);
                }
            }
        }
    }

    // 3. View All Student Page
    public static class ViewAllStudent {
        WebDriver driver;
        WebDriverWait wait;

        By studentTableRows = By.xpath("//*[@id='viewTable']/tr[position()>1]");
        By rowHeader = By.xpath("//*[@id='viewTable']/tr[1]");
        By table = By.xpath("//*[@id=\"viewTable\"]/tr[2]");

        public ViewAllStudent(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        public void waitForTableToLoad() throws InterruptedException {
            Thread.sleep(5000);
        }

        public List<WebElement> getTableRows() {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(studentTableRows));
        }

        public List<WebElement> getColumnHeaders() {
            // Wait for the header row to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(rowHeader));

            WebElement headerRow = driver.findElement(rowHeader);
            return headerRow.findElements(By.tagName("td")); // or "th" depending on actual HTML
        }


        public boolean allFieldsAreNotBlank() {
            List<WebElement> rows = getTableRows();
            for (WebElement row : rows) {
                List<WebElement> cols = row.findElements(By.tagName("td"));
                for (WebElement cell : cols) {
                    if (cell.getText().trim().isEmpty()) {
                        return false;
                    }
                }
            }
            return true;
        }

        public boolean columnHeadersAreVisible() {
            List<WebElement> headers = getColumnHeaders();
            return headers.size() >= 7;
        }

        public boolean isStudentListVisible() {
            return getTableRows().size() > 0;
        }
    }
}
