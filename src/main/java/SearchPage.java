import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Base Search page class
 */
public class SearchPage {
    private WebDriver driver;
    private By locName = By.xpath("//div[@id='propertiesList']/div/div/div/div[2]/div/div[2]");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkSearchResylt(String searchRequest) {
        new Wait(driver).untilPresent(locName);
        return driver.findElements(locName).equals(searchRequest);
    }
}
