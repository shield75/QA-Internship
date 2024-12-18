package testCases;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class PlaySongTest extends BaseTest{
    @Test
    public void playSong() {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.login();
        homePage.hoverOnElement(homePage.sideControls);
        homePage.playNextSong();
        homePage.playResumeSong();
        homePage.verifySongPlaying();
    }
}
