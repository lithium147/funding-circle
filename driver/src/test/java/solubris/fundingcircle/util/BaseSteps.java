package solubris.fundingcircle.util;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import solubris.fundingcircle.selenium.driver.WebDriverProvider;
import solubris.fundingcircle.spring.AppConfig;

@ContextConfiguration(classes = AppConfig.class)
public abstract class BaseSteps implements WebDriverProvider {

    @Autowired
    protected WebDriver driver;

    public WebDriver getWebDriver() {
        return driver;
    }
}
