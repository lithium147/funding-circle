package solubris.fundingcircle.support;

import org.openqa.selenium.WebDriver;

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
