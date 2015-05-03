package solubris.fundingcircle.selenium.driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverBuilder {
    public static final int WIDTH = 320 * 4;
    public static final int HEIGHT = 480 * 2;

    protected void applyDefaultsToDriver(WebDriver driver) {
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(WIDTH, HEIGHT));
        driver.manage().deleteAllCookies();
    }
}
