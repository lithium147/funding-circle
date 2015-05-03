package solubris.fundingcircle.selenium.driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChromeWebDriverBuilder extends WebDriverBuilder {

    ChromeWebDriverBuilder() {

    }

    public WebDriver build() {
//        URL firefoxProfileDir = SharedWebDriver.class.getClassLoader().getResource(PROFILE_LOCATION);
//        File profileDir = new File(firefoxProfileDir.getPath());
//        FirefoxProfile firefoxProfile = new FirefoxProfile(profileDir);
//        firefoxProfile.setPreference(PREFERENCE_PRIVATE_BROWSING, true);
//        firefoxProfile.setPreference(PREFERENCE_USER_AGENT, USER_AGENT_IPHONE5);

        // need to install driver from here:
        // http://chromedriver.storage.googleapis.com/index.html?path=2.14/
        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");

        WebDriver driver = new ChromeDriver();
//		driver = new HtmlUnitDriver();
        applyDefaultsToDriver(driver);
        return driver;
    }

    public static ChromeWebDriverBuilder aChromeWebDriver() {
        return new ChromeWebDriverBuilder();
    }
}
