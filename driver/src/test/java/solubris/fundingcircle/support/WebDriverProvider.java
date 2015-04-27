package solubris.fundingcircle.support;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * provides the webdriver for support classes,
 * but hides it from the steps classes
 */
public abstract class WebDriverProvider {
    @Autowired
    private WebDriver driver;

//    public WebDriverProvider(WebDriver driver) {
//        this.driver = driver;
//    }

    WebDriver getWebDriver() {
        return driver;
    }
}
