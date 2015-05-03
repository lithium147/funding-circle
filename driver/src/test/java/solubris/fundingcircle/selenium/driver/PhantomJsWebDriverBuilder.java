package solubris.fundingcircle.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://phantomjs.org/download.html
 * unzip phantomjs-2.0.0-macosx.zip
 * mv phantomjs-2.0.0-macosx /opt/
 *
 */
public class PhantomJsWebDriverBuilder extends WebDriverBuilder {
    private static final Logger logger = LoggerFactory.getLogger(PhantomJsWebDriverBuilder.class);

    PhantomJsWebDriverBuilder() {
    }

    public WebDriver build() {
        logger.debug("using phontomjs: " + System.getProperty("phantomjs.binary"));
        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
//                "/Users/eeo2/O2/pm-responsive/acceptanceTests/target/phantomjs-maven-plugin/phantomjs-1.9.2-macosx/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                System.getProperty("phantomjs.binary", "/opt/phantomjs-2.0.0-macosx/bin/phantomjs"));
//                System.getProperty("phantomjs.binary", "/Users/walterst/workspace-ij/funding-circle/driver/target/phantomjs-maven-plugin/phantomjs-1.9.2-macosx/bin/phantomjs"));
//                System.getProperty("phantomjs.binary", "/Users/eeo2/O2/pm-responsive/acceptanceTests/target/phantomjs-maven-plugin/phantomjs-1.9.2-macosx/bin/phantomjs"));

        PhantomJSDriver driver = new PhantomJSDriver(caps);
        applyDefaultsToDriver(driver);
        return driver;
    }

    public static PhantomJsWebDriverBuilder aPhantomJsWebDriver() {
        return new PhantomJsWebDriverBuilder();
    }
}
