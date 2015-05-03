package solubris.fundingcircle.selenium.driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * need to install driver from here:
 * http://chromedriver.storage.googleapis.com/index.html?path=2.14/
 */
public class ChromeWebDriverBuilder extends WebDriverBuilder {

    ChromeWebDriverBuilder() {

    }

    public WebDriver build() {
        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");

        WebDriver driver = new ChromeDriver();
        applyDefaultsToDriver(driver);
        return driver;
    }

    public static ChromeWebDriverBuilder aChromeWebDriver() {
        return new ChromeWebDriverBuilder();
    }
}
