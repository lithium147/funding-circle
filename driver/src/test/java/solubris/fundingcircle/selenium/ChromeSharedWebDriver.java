package solubris.fundingcircle.selenium;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Example of a WebDriver implementation that has delegates all methods to a static instance (REAL_DRIVER) that is only
 * created once for the duration of the JVM. The REAL_DRIVER is automatically closed when the JVM exits. This makes
 * scenarios a lot faster since opening and closing a browser for each scenario is pretty slow.
 * To prevent browser state from leaking between scenarios, cookies are automatically deleted before every scenario.
 * </p>
 * <p>
 * A new instance of SharedDriver is created for each Scenario and passed to yor Stepdef classes via Dependency Injection
 * </p>
 * <p>
 * As a bonus, screenshots are embedded into the report for each scenario. (This only works
 * if you're also using the HTML formatter).
 * </p>
 * <p>
 * A new instance of the SharedDriver is created for each Scenario and then passed to the Step Definition classes'
 * constructor. They all receive a reference to the same instance. However, the REAL_DRIVER is the same instance throughout
 * the life of the JVM.
 * </p>
 */
@Component
public class ChromeSharedWebDriver extends EventFiringWebDriver {
    private static final WebDriver REAL_DRIVER = initDriver();
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.close();
        }
    };
    public static final int WIDTH = 320*4;
    public static final int HEIGHT = 480*2;

    public static WebDriver initDriver() {
//        URL firefoxProfileDir = SharedWebDriver.class.getClassLoader().getResource(PROFILE_LOCATION);
//        File profileDir = new File(firefoxProfileDir.getPath());
//        FirefoxProfile firefoxProfile = new FirefoxProfile(profileDir);
//        firefoxProfile.setPreference(PREFERENCE_PRIVATE_BROWSING, true);
//        firefoxProfile.setPreference(PREFERENCE_USER_AGENT, USER_AGENT_IPHONE5);

        // need to install driver from here:
        // http://chromedriver.storage.googleapis.com/index.html?path=2.14/
        System.setProperty("webdriver.chrome.driver","/opt/chromedriver");

        WebDriver driver = new ChromeDriver();
//		driver = new HtmlUnitDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(WIDTH, HEIGHT));
        driver.manage().deleteAllCookies();
        return driver;
    }

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public ChromeSharedWebDriver() {
        super(REAL_DRIVER);
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

    @Before
    public void deleteAllCookies() {
        manage().deleteAllCookies();
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        if(scenario.isFailed()) {
            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
    }
}