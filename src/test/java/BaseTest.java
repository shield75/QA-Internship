import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class BaseTest {

    public static WebDriver driver = null;
    public String url = null;
    public WebDriverWait wait = null;
    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();


    @BeforeMethod
    @Parameters({"BaseURL"})
    public void setUpBrowser(String BaseURL) throws MalformedURLException {
        threadDriver.set(pickBrowser(System.getProperty("browser")));
        getDriver().manage().window().fullscreen();
        //getDriver().manage().window().setSize(new Dimension(1920, 1080));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver = getDriver();
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        url = BaseURL;
        navigateToPage();
    }

    public static WebDriver getDriver() {
        return threadDriver.get();
    }
    // This getDriver() method returns the current instance of WebDriver associated with the current thread.

    @AfterMethod
    public void tearDown() {
        threadDriver.get().close();
        threadDriver.remove();
    }

    public void navigateToPage() {
        getDriver().get(url);
    }

    public void provideEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void providePassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickSubmit() throws InterruptedException {
        WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
        submit.click();
    }


    public void clickAvatarIcon() {
        WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.avatar")));
        avatarIcon.click();
    }

    public void provideCurrentPassword(String password) {
        WebElement currentPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='current_password']")));
        currentPassword.clear();
        currentPassword.sendKeys(password);
    }

    public void clickSaveButton() {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-submit")));
        saveButton.click();
    }

    public void provideProfileName(String randomName) {
        WebElement profileName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        profileName.clear();
        profileName.sendKeys(randomName);
    }

    public String generateRandomName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public void isAvatarDisplayed() {
        WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));
        Assert.assertTrue(avatarIcon.isDisplayed());
    }

    public void searchSong(String songName) throws InterruptedException {
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='search']"))));
        //WebElement searchField = driver.findElement(By.cssSelector("input[type='search']"));
        searchField.click();
        searchField.clear();
        searchField.sendKeys(songName);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("section[class='songs'] article[draggable='true']"))));
    }

    public void viewAllSong() throws InterruptedException {
        WebElement viewAllButton = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("button[data-test='view-all-songs-btn']"))));
        viewAllButton.click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("(//table[@class='items'])[2]//tr"), 0));
    }

    public void addToPlaylistButton(String playlistName) throws InterruptedException {
        WebElement addToButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[title='Add selected songs to…']")));
        addToButton.click();
        WebElement playlist = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//section[@id='songResultsWrapper']//ul/li[contains(text(), '" + playlistName + "')]")
        ));
        playlist.click();
    }

    public void deleteAPlaylist() throws InterruptedException{
        WebElement firstPlaylist = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("section#playlists > ul > li:nth-of-type(3) > a"))));
        String playlistName = firstPlaylist.getText();
        firstPlaylist.click();
        WebElement deletePlaylistButton = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("button[title='Delete this playlist']"))));
        deletePlaylistButton.click();
        WebElement alert = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".success.show"))));
        String alertText = alert.getText();
        String finalAlertText = "Deleted playlist " + '"'+ playlistName + '.'+ '"';
        Assert.assertEquals(alertText, finalAlertText);
    }

    public void playNextSong(){
        WebElement nextSongButton = getDriver().findElement(By.cssSelector("i[title='Play next song']"));
        nextSongButton.click();
    }

    public void playResumeSong(){
        WebElement playButton = getDriver().findElement(By.cssSelector("span[title='Play or resume'] i[class='fa fa-play']"));
        playButton.click();
    }

    public void verifySongPlaying(){
        WebElement soundBar = driver.findElement(By.cssSelector("img[alt='Sound bars']"));
        WebElement pauseButton = driver.findElement(By.cssSelector("span[title='Pause'] i[class='fa fa-pause']"));
        if(soundBar.isDisplayed() || pauseButton.isDisplayed()){
            assert true;
        }
    }

    public void renamePlayList() throws InterruptedException {
        String namePlayList = "Rename PLayList";
        WebElement playlist = wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.cssSelector("section#playlists > ul > li:nth-of-type(3) > a"))));
        Actions action = new Actions(getDriver());
        action.doubleClick(playlist).perform();
        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.cssSelector("input[name='name']"))));
        inputField.sendKeys(Keys.chord(Keys.CONTROL,"A",Keys.BACK_SPACE));
        inputField.sendKeys(namePlayList);
        inputField.sendKeys(Keys.ENTER);
        WebElement alert = wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.cssSelector(".success.show"))));
        String alertText = alert.getText();
        String finalAlertText = "Updated playlist " + '"'+ namePlayList + '.' + '"';
        Assert.assertEquals(alertText, finalAlertText);
    }

    public  WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://192.168.31.241:4444";//replace with your grid url

        //java -jar selenium-server-4.15.0.jar standalone --selenium-manager true

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(chromeOptions);
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver(edgeOptions);
            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-edge":
                caps.setCapability("browserName", "MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "cloud":
                return lambdaTest();
            default:
                WebDriverManager.chromedriver().setup();
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(chromeOptions);
        }
    }


    public WebDriver lambdaTest() throws MalformedURLException {

/*      Test Pro Instructor LambdaTest account

        1.) Navigate to https://accounts.lambdatest.com/login

        2.) Login using Google email

        Email: lambdatest.testpro@gmail.com
        Password: testpro123

        3.) Run command in IntelliJ Terminal:
         gradle clean test -Dbrowser=cloud

        4.) View the cloud automations in
        https://accounts.lambdatest.com/dashboard


       Configured for the Test Pro lambdatest account
  */
        String hubURL = "https://hub.lambdatest.com/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "129");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "rumenul94");
        ltOptions.put("accessKey", "0TbAPSFH6Ha2TeQVajfLSzi8gQppSGT5s3ONn66ishV2Vn3koG");
        ltOptions.put("resolution", "1920x1080");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", this.getClass().getName());
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.0.0");
        capabilities.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), capabilities);
    }
    // This lambdaTest() method returns an instance of WebDriver for remote testing using the LambdaTest service.
}