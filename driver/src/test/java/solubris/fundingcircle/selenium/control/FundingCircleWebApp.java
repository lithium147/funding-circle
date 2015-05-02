package solubris.fundingcircle.selenium.control;

import org.openqa.selenium.WebDriver;
import solubris.fundingcircle.selenium.driver.WebDriverProvider;

/**
 * Created by eeo2 on 14/09/2014.
 */
public class FundingCircleWebApp {
    private final WebDriver driver;

    public FundingCircleWebApp(WebDriverProvider provider) {
        driver = provider.getWebDriver();
    }

    public void open() {
        driver.get("http://www.fundingcircle.com/");
    }
}
