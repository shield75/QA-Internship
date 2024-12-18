package testCases;

import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PlayListPage;

public class RenameAPlayListTest extends BaseTest{
    @Test
    public void renameAPlayList() throws InterruptedException {
        LoginPage loginPage  = new LoginPage(driver);
        PlayListPage playListPage = new PlayListPage(driver);
        loginPage.login();
        playListPage.renamePlayList();
    }
}
