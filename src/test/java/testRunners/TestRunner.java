package testRunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@SuppressWarnings("deprecation")
@RunWith(Cucumber.class)
@CucumberOptions(
		features= {".//features/Registration.feature", ".//features/login.feature", ".//features/viewAllStudent.feature"}, 
		glue="definitions",
		plugin= {"pretty", "html:reports/myreport.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		},
		dryRun=false,
		publish=true
	)
public class TestRunner {

}
