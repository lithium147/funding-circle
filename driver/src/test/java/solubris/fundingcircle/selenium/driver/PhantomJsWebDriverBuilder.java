package solubris.fundingcircle.selenium.driver;

import org.openqa.selenium.WebDriver;
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
 * doesn't work on Yosemite
 * http://stackoverflow.com/questions/28267809/phantomjs-getting-killed-9-for-anything-im-trying
 */
public class PhantomJsWebDriverBuilder extends WebDriverBuilder {
    private static final Logger logger = LoggerFactory.getLogger(PhantomJsWebDriverBuilder.class);

    PhantomJsWebDriverBuilder() {
    }

    public static PhantomJsWebDriverBuilder aPhantomJsWebDriver() {
        return new PhantomJsWebDriverBuilder();
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
        // trying to make it start faster
        caps.setCapability("phantomjs.cli.args", new String[]{"--proxy-type=none"});
        // log file should be in target dir, not in root dir
        // --webdriver-logfile=
        PhantomJSDriver driver = new PhantomJSDriver(caps);
        applyDefaultsToDriver(driver);
        return driver;
    }
}
