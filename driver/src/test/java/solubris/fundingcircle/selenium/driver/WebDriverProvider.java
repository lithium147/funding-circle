package solubris.fundingcircle.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * provides the webdriver for control classes,
 * but hides it from the cucumber classes
 */
public interface WebDriverProvider {

//    public WebDriverProvider(WebDriver driver) {
//        this.driver = driver;
//    }

    WebDriver getWebDriver();
}
