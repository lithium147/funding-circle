package solubris.fundingcircle.selenium.driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class FirefoxWebDriverBuilder extends WebDriverBuilder {

    FirefoxWebDriverBuilder() {

    }

    public WebDriver build() {
//        URL firefoxProfileDir = SharedWebDriver.class.getClassLoader().getResource(PROFILE_LOCATION);
//        File profileDir = new File(firefoxProfileDir.getPath());
//        FirefoxProfile firefoxProfile = new FirefoxProfile(profileDir);
//        firefoxProfile.setPreference(PREFERENCE_PRIVATE_BROWSING, true);
//        firefoxProfile.setPreference(PREFERENCE_USER_AGENT, USER_AGENT_IPHONE5);

        WebDriver driver = new FirefoxDriver();

        applyDefaultsToDriver(driver);
        return driver;
    }

    public static FirefoxWebDriverBuilder aFirefoxWebDriver() {
        return new FirefoxWebDriverBuilder();
    }
}
