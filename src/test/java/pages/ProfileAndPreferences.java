package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ProfileAndPreferences extends BasePage{
    public ProfileAndPreferences(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(css = "input#inputProfileCurrentPassword")
    WebElement currentPasswordField;

    @FindBy(css = "input#inputProfileEmail")
    WebElement emailAddressField;

    @FindBy(css = "input#inputProfileNewPassword")
    WebElement newPasswordField;

    @FindBy(css = ".btn-submit")
    WebElement saveButton;

    @FindBy(css = ".alertify-logs.right.top")
    WebElement alertMessage;


    public void provideCurrentPassword(String password) {
        findElement(currentPasswordField).click();
        currentPasswordField.sendKeys(password);
    }

    public void provideNewEmailAddress(String email) {
        findElement(emailAddressField).click();
        emailAddressField.clear();
        emailAddressField.sendKeys(email);
    }

    public void provideNewPassword(String password) {
        findElement(newPasswordField).click();
        newPasswordField.sendKeys(password);
    }

    public void clickOnSaveButton() {
        saveButton.click();
    }

    public void verifyResponseMessage(String expectedMessage) {
        try {
            findElement(alertMessage).isDisplayed();
            Assert.assertEquals(alertMessage.getText(), expectedMessage, "Response message mismatched");
        }catch (Exception e) {
            Assert.assertEquals(emailAddressField.getAttribute("validationMessage"), expectedMessage, "Validation message mismatched");
        }
    }
}
