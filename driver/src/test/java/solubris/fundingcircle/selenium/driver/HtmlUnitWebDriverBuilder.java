package solubris.fundingcircle.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HtmlUnitWebDriverBuilder extends WebDriverBuilder {

    HtmlUnitWebDriverBuilder() {

    }

    public WebDriver build() {
        WebDriver driver = new HtmlUnitDriver();
        applyDefaultsToDriver(driver);
        return driver;
    }

    public static HtmlUnitWebDriverBuilder aHtmlUnitWebDriver() {
        return new HtmlUnitWebDriverBuilder();
    }
}
