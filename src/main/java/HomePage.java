import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Base Home page class
 */
public class HomePage {
    private WebDriver driver;
    private String url;
    private Wait wait;

    @FindBy(name = "SearchInput")
    private WebElement searchInput;

    @FindBy(css = "button.btn-search.btn.btn-default")
    private WebElement searchButton;

    /**
     * First element in search autocomplete
     */
    By firstLi = By.id("react-autowhatever-1--item-0");

    /**
     * Open base url address and initialize PageFactory
     *
     * @param driver - your webDriver
     * @param url    - base url address
     */
    public HomePage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
        this.wait = new Wait(driver);
        driver.navigate().to(url);
        PageFactory.initElements(driver, this);
    }

    /**
     * Input some search request in search input -> select first element from search selector -> click search button
     *
     * @param searchReq - search request
     * @return new object of Search page
     */
    public SearchPage search(String searchReq) {
        searchInput.sendKeys(searchReq);

        wait.untilPresent(firstLi);
        driver.findElement(firstLi).click();

        searchButton.click();
        wait.waitForJSandJQueryToLoad();
        return new SearchPage(driver);
    }
}
