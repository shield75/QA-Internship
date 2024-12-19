package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features", // Path to feature files
        glue = "stepDefinition", // Package containing step definitions
        tags = "@Regression or @Smoke", // Execute specific tags
        plugin = {
                "pretty", // Prints Gherkin steps in the console
                "html:target/cucumber-reports/cucumber.html" // HTML report

        },
        monochrome = true, // Makes console output readable
        dryRun = false // Set to true to check step definitions without executing tests
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @Before
    public void setUpCucumber() {
        // Initialize WebDriver or any setup needed before tests
        DriverManager.initializeDriver();
        DriverManager.getDriver().get("https://qa.koel.app/");
        System.out.println("WebDriver initialized");
    }

    @DataProvider(parallel = true) // Enable parallel execution of scenarios
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @After
    public void tearDownClass() {
        // Quit WebDriver or perform cleanup
        DriverManager.quitDriver();
        System.out.println("WebDriver quit");
    }
}
