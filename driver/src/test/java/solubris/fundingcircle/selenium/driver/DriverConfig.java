package solubris.fundingcircle.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import static solubris.fundingcircle.selenium.driver.ChromeWebDriverBuilder.aChromeWebDriver;
import static solubris.fundingcircle.selenium.driver.HtmlUnitWebDriverBuilder.aHtmlUnitWebDriver;
import static solubris.fundingcircle.selenium.driver.PhantomJsWebDriverBuilder.aPhantomJsWebDriver;

@Configuration
public class DriverConfig {
    @Bean
    public WebDriver webDriver() throws IOException {
        return SharedWebDriver.aWebDriver(aPhantomJsWebDriver().build());
    }
}