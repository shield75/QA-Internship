package testCases;

import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PlayListPage;


public class CreatePlayListTest extends BaseTest{
    @Test
    public void addSongToPlaylist() {
    LoginPage loginPage = new LoginPage(getDriver());
    PlayListPage playListPage = new PlayListPage(getDriver());
    loginPage.login();
    playListPage.CreatePlayList();


    }

}
