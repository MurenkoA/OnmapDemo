import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.jetty9.server.Server;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 */
public class Tests {
    private Server webServer;
    private ChromeDriver chDriver;

    /**
     * @param url full url address
     */
    private String url = "",
    /**
     * @param driverLoc chromedriver location a relatively the location of the project folder or full path
     */
    driverLoc = "";

    @BeforeMethod
    public void beforeSuite() throws Exception {
        System.setProperty("webdriver.chrome.driver", driverLoc);
        chDriver = new ChromeDriver();
        chDriver.manage().window().maximize();
        chDriver.navigate().to(url);
    }

    private String searchReq = "Tel Aviv-Yafo";

    @Test
    /**
     * 1 open home page
     * 2 type @searchReq in search input
     * 3 select first element in autocomplete
     * 4 click Search button
     * 5 on Search result page make sure that we find this http://joxi.ru/vAWZp8OIkp3z32
     */
    public void searchTest() throws Exception {
        SearchPage searchPage = new HomePage(chDriver, url).search(searchReq);
        searchPage.checkSearchResylt(searchReq);
    }

    @AfterMethod
    /**
     * close browser
     */
    public void afterSuite() throws Exception {
        chDriver.quit();
    }
}
