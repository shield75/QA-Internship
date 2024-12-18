package testCases;

import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PlayListPage;

public class DeletePlaylistTest extends BaseTest{
    @Test
    public void deletePlaylist() throws InterruptedException {
        LoginPage loginPage  = new LoginPage(driver);
        PlayListPage playListPage = new PlayListPage(driver);

        loginPage.provideEmail("rumenul.rimon@testpro.io");
        loginPage.providePassword("27041575");
        loginPage.clickSubmit();
        playListPage.deleteAPlaylist();
    }
}
