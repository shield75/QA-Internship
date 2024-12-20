package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfileAndPreferences;
import utils.DriverManager;

public class UpdateEmailStepDefinition {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    ProfileAndPreferences profileAndPreferences;

    public UpdateEmailStepDefinition() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        profileAndPreferences = new ProfileAndPreferences(driver);
    }

    @Given("the user is logged into the koel app using and is in the profile and preference page")
    public void theUserIsLoggedIntoTheKoelAppUsingAndIsInTheProfileAndPreferencePage() {

    }

    @When("Enter a new email address {string} and save")
    public void enterANewEmailAddressAndSave(String email) throws InterruptedException {
        Thread.sleep(5000);
        profileAndPreferences.provideCurrentPassword("27041575");
        profileAndPreferences.provideNewEmailAddress(email);
        profileAndPreferences.clickOnSaveButton();
    }

    @Then("the mail should be validated with {string}")
    public void theMailShouldBeValidatedWith(String expectedMessage) {
        profileAndPreferences.verifyResponseMessage(expectedMessage);
    }
}


//    @Given("the user is logged into the koel app and is in the profile and preference page")
//    public void theUserIsLoggedIntoTheKoelAppAndIsInTheProfileAndPreferencePage() {
//
//    }
//
//    @When("the user updates the email to {string}")
//    public void theUserUpdatesTheEmailTo(String email) {
//    }
//
//    @Then("the message {string} should be displayed")
//    public void theMessageShouldBeDisplayed(String expectedMessage) {
//    }
//
//    @And("the email should not be updated")
//    public void theEmailShouldNotBeUpdated() {
//    }
//
//    @Then("the message {string} should not be displayed")
//    public void theMessageShouldNotBeDisplayed(String unexpectedMessage) {
//    }
//
//    @When("the user updates the email to an existing email address from the database")
//    public void theUserUpdatesTheEmailToAnExistingEmailAddressFromTheDatabase() {
//    }
//
//    @Given("the user has updated their email to {string}")
//    public void theUserHasUpdatedTheirEmailTo(String email) {
//    }
//
//    @When("the user logs out")
//    public void theUserLogsOut() {
//    }
//
//    @And("the user logs in with {string}")
//    public void theUserLogsInWith(String email) {
//    }
//
//    @Then("the user should be able to log in")
//    public void theUserShouldBeAbleToLogIn() {
//    }
//
//    @Given("the user has updated their email from {string} to {string}")
//    public void theUserHasUpdatedTheirEmailFromTo(String oldEmail, String newEmail) {
//    }
//
//    @Then("the user should not be able to log in")
//    public void theUserShouldNotBeAbleToLogIn() {
//    }
//
//    @When("the user checks their account details in the database")
//    public void theUserChecksTheirAccountDetailsInTheDatabase() {
//        // Simulate a database query for verification
//    }
//
//    @Then("the email should be {string}")
//    public void theEmailShouldBe(String expectedEmail) {
//    }
