package solubris.fundingcircle.selenium;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import solubris.fundingcircle.selenium.control.MyLending;
import solubris.fundingcircle.selenium.driver.WebDriverProvider;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Utility class for waiting for elements
 */
public class Waiter {
    private final WebDriver driver;
    private WebDriverWait driverWait;

    public Waiter(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, 10, 100);
    }

    public Waiter(WebDriverProvider webDriverProvider) {
        this(webDriverProvider.getWebDriver());
    }

    public WebElement findVisibileWebElement(By by) {
        WebElement element = driverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> findVisibleWebElements(By by) {
		return findVisibleWebElements(by,  10);
    }

	public List<WebElement> findVisibleWebElements(By by, int timeOutInSeconds) {
		driverWait.withTimeout(timeOutInSeconds, TimeUnit.SECONDS);

		List<WebElement> webElements;
		try {
			webElements = driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			webElements = driverWait.until(ExpectedConditions.visibilityOfAllElements(webElements));
		} catch (TimeoutException toe) {
			return null;
		} finally {
			driverWait.withTimeout(10, TimeUnit.SECONDS);
		}
		return webElements;
	}

    public static <T> T eventuallyNotNull(Callable<T> callable) throws Exception {
        return eventuallyMeetsCondition(callable, Predicates.<T>notNull());
    }

    private static final Duration defaultTimeout = Duration.ofSeconds(10);
    private static final Duration defaultInterval = Duration.ofMillis(100);

    public static <T> T eventuallyMeetsCondition(Callable<T> callable, Predicate<T> condition) throws Exception {
        Instant endTime = Instant.now().plus(defaultTimeout);
        do {
            T result = callable.call();
            if(condition.apply(result)) {
                return result;
            } else {
                if(Instant.now().isAfter(endTime)) {
                    throw new java.util.concurrent.TimeoutException("timedout waiting for callable to meet condition " + callable.toString());
                }
                Thread.sleep(defaultInterval.toMillis());
                if(Instant.now().isAfter(endTime)) {
                    throw new java.util.concurrent.TimeoutException("timedout waiting for callable to meet condition " + callable.toString());
                }
            }
        } while (true);
    }

    public void clickAndWaitForNewPage(By by) {
        clickAndWaitForNewPage(driver.findElement(by));
    }

    /**
     * Wait for new page to load using the stale element concept
     *
     * Solution came from this:
     * http://www.obeythetestinggoat.com/how-to-get-selenium-to-wait-for-page-load-after-a-click.html
     * http://stackoverflow.com/questions/797960/extending-an-activexobject-in-javascript/3202098#3202098
     * @param elementToClick the element to click
     * @return
     */
    public void clickAndWaitForNewPage(WebElement elementToClick) {
        ChangingReferenceCondition condition = new ChangingReferenceCondition(By.tagName("html"));
        condition.prepare(driver);

        elementToClick.click();
        driverWait.withTimeout(20, TimeUnit.SECONDS).until(condition);
    }

    public void clickAndWaitForNewBody(WebElement elementToClick) {
        ChangingReferenceCondition condition = new ChangingReferenceCondition(By.tagName("body"));
        condition.prepare(driver);

        elementToClick.click();
        driverWait.withTimeout(20, TimeUnit.SECONDS).until(condition);
    }

    public void clickAndWaitForAjaxToComplete(WebElement elementToClick) {
        elementToClick.click();

        waitForAjaxToComplete();
    }

    public void waitForAjaxToComplete() {
        AjaxConnectionsCondition condition = new AjaxConnectionsCondition(1);
        driverWait.withTimeout(20, TimeUnit.SECONDS).until(condition);
        condition = new AjaxConnectionsCondition(0);
        driverWait.withTimeout(20, TimeUnit.SECONDS).until(condition);
    }

    public static Waiter aWaiter(WebDriver driver) {
        return new Waiter(driver);
    }

    public void clickAndWaitForNewElement(WebElement elementToClick, By xpathThatWillChange) {
        ChangingReferenceCondition condition = new ChangingReferenceCondition(xpathThatWillChange);
        condition.prepare(driver);

        elementToClick.click();
        driverWait.withTimeout(20, TimeUnit.SECONDS).until(condition);
    }

    public void clickAndWaitForCondition(WebElement elementToClick, Predicate<WebDriver> predicate) {
        elementToClick.click();
        driverWait.withTimeout(20, TimeUnit.SECONDS).until(predicate);
    }

    public WebElement clickAndWaitForCondition(WebElement elementToClick, ExpectedCondition<WebElement> condition) {
        elementToClick.click();
        return driverWait.withTimeout(20, TimeUnit.SECONDS).until(condition);
    }
}
