package testCases;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class RegistrationNavigationTest extends BaseTest {
    @Test
    public void registrationNavigationTest() {
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.clickOnRegistrationLink();

        String registrationUrl = "https://qa.koel.app/registration";
        wait.until(ExpectedConditions.urlToBe(registrationUrl));
        Assert.assertEquals(getDriver().getCurrentUrl(), registrationUrl, "URL does not match");

    }
}
