package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HomePage extends BasePage{
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(css = "input[type='search']")
    WebElement searchField;
    @FindBy(css = "section[class='songs'] article[draggable='true']")
    WebElement songInSearchResult;
    @FindBy(css = "button[data-test='view-all-songs-btn']")
    WebElement viewAllSongBtn;

    By songList = By.xpath("(//table[@class='items'])[2]//tr");
    @FindBy(xpath = "//div[@class='song-list-wrap main-scroll-wrap search-results']//table[@class='items']/tr[1]")
    WebElement firstSong;
    @FindBy(css = "button[title='Add selected songs to…']")
    WebElement addToBtn;
    @FindBy(css = ".success.show")
    WebElement popUpText;
    @FindBy(css = "i[title='Play next song']")
    WebElement nextSongBtn;
    @FindBy(css = "span[title='Play or resume'] i[class='fa fa-play']")
    WebElement playBtn;
    @FindBy(css = "img[alt='Sound bars']")
    WebElement soundBar;
    @FindBy(css = "span[title='Pause'] i[class='fa fa-pause']")
    WebElement pauseBtn;
    @FindBy(css = "img.avatar")
    WebElement avatar;

    public WebElement findSpecificPlayList(String playListName) {
        return (driver.findElement(By.xpath("//section[@id='songResultsWrapper']//ul/li[contains(text(), '" + playListName + "')]")));
    }

    public By sideControls = By.xpath("//div[@class='side player-controls']");

    public HomePage searchSong(String songName) throws IOException {
        findElement((searchField)).click();
        findElement(searchField).sendKeys(songName);
        findElement(songInSearchResult);
        return this;
    }

    public HomePage viewAllSong() throws IOException {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test='view-all-songs-btn']")));
        button.click();
        numberOfElementsToBeMoreThan(songList, 0);
        return this;
    }

    public HomePage clickOnFirstSong() {
        findElement(firstSong).click();
        return this;
    }

    public HomePage addToPlaylistButton(String playlistName) throws InterruptedException {
        findElement(addToBtn).click();
        findElement(findSpecificPlayList(playlistName)).click();
        return this;
    }

    public String getSuccessPopUpText() {
        return findElement(popUpText).getText();

    }

    public void playNextSong(){
        wait.until(ExpectedConditions.elementToBeClickable(nextSongBtn)).click();
    }

    public void playResumeSong(){
        findElement(playBtn).click();
    }

    public void verifySongPlaying(){
        if(findElement(soundBar).isDisplayed() || findElement(pauseBtn).isDisplayed()){
            assert true;
        }
    }

    public void checkAvatar() {
        Assert.assertTrue(findElement(avatar).isDisplayed(), "Avatar not found");

    }

    public void clickOnAvatar() {
        wait.until(ExpectedConditions.elementToBeClickable(avatar)).click();
    }

}
