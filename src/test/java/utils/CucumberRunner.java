package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import pages.HomePage;
import pages.LoginPage;

@CucumberOptions(
        features = "src/test/resources/features", // Path to feature files
        glue = "stepDefinition", // Package containing step definitions
        plugin = {
                "pretty", // Prints Gherkin steps in the console
                "html:src/test/cucumber-reports/cucumber.html" // HTML report

        }
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @Before
    public void setUpCucumber() throws InterruptedException {
        // Initialize WebDriver or any setup needed before tests
        DriverManager.initializeDriver();
        DriverManager.getDriver().get("https://qa.koel.app/");
        System.out.println("WebDriver initialized");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        HomePage homePage = new HomePage(DriverManager.getDriver());

        loginPage.provideEmail("rumenul.rimon@testpro.io").providePassword("27041575").clickSubmit();
        Thread.sleep(5000);
        homePage.clickOnAvatar();
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
