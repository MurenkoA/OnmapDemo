import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Waiters
 */
public class Wait {
    private WebDriver driver;

    public Wait(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Wait until element will be present in DOM
     *
     * @param by - locator
     */
    public void untilPresent(By by) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions
                        .presenceOfElementLocated(by));
    }

    /**
     * Waits for all active AJAX requests to finish during specified timeout. Works only for AJAX requests which are
     * instantiated using one of the following frameworks: jQuery, Prototype, Dojo. Don't work (immediately returns without
     * any errors) if standard AJAX API or one of other frameworks is used to send XML HTTP request.
     */
    public boolean waitForJSandJQueryToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }
}
