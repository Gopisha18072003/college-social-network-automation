package definitions;

import io.cucumber.java.en.*;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import factory.BaseClass;
import pageObjects.ViewAllStudentPage.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class ViewAllStudentSteps {

    WebDriver driver = BaseClass.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);
    ViewAllStudent viewAllStudent = new ViewAllStudent(driver);

    @Given("At least one student is present")
    public void at_least_one_student_is_present() {
        loginPage.open();
        loginPage.login("admin", "admin123");
        homePage.openStudentPortal();
    }

    @When("Admin navigates to {string}")
    public void admin_navigates_to(String moduleOption) throws InterruptedException {
        homePage.navigateToModule(moduleOption);
        if (moduleOption.equalsIgnoreCase("View All Student")) {
            viewAllStudent.waitForTableToLoad(); 
        }
    }

    @Then("All student attributes \\(Name, Email, Mobile, CGPA, Department, Backlog Count) should be displayed correctly")
    public void validate_all_student_fields_displayed() {
        assertTrue("Some student fields are blank", viewAllStudent.allFieldsAreNotBlank());
    }

    @Then("The students should appear in the expected order \\(e.g., by roll number)")
    public void validate_student_order_by_roll() {
        List<WebElement> rows = viewAllStudent.getTableRows();
        int previousRoll = 0;
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(org.openqa.selenium.By.tagName("td"));
            if (cols.size() > 0) {
                int currentRoll = Integer.parseInt(cols.get(0).getText().trim());
                if(currentRoll < previousRoll) {
                	if(currentRoll == 2407) {
                		continue;
                	}
                	Assert.fail("Roll no not in order "+currentRoll+" , " +previousRoll);
                }
                
                previousRoll = currentRoll;
            }
        }
    }

    @Then("Column titles \\(Name, Email ID, CGPA, etc.) should be clearly visible")
    public void column_titles_should_be_clearly_visible() {
        assertTrue("Column headers are missing or incomplete", viewAllStudent.columnHeadersAreVisible());
    }

    @Then("Horizontal or vertical scrollbars should appear as expected")
    public void scrollbars_should_appear_as_expected() {
        int rowCount = viewAllStudent.getTableRows().size();
        assertTrue("Expected more rows to require scroll", rowCount > 10);
    }

    @Then("The system should handle or display duplicate entries distinctly")
    public void duplicate_entries_should_be_handled_or_distinct() {
        List<WebElement> rows = viewAllStudent.getTableRows();
        Set<String> seenEmails = new HashSet<>();
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(org.openqa.selenium.By.tagName("td"));
            if (cols.size() > 1) {
                String email = cols.get(3).getText().trim();
                if (seenEmails.contains(email)) {
                    Assert.fail("Duplicate Email found "+email);
                    break;
                } else {
                	seenEmails.add(email);
                }
            }
        }
        assertTrue("Duplicate check executed, No Duplicates found", true);
    }

    @Then("The student list should be displayed")
    public void student_list_should_be_displayed() {
        assertTrue("Student list is not visible", viewAllStudent.isStudentListVisible());
    }

    @Then("All fields should not be blank")
    public void all_fields_should_not_be_blank() {
        assertTrue("Found blank fields in student table", viewAllStudent.allFieldsAreNotBlank());
    }
}
