package solubris.fundingcircle.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class DriverConfig {
    @Bean
    public WebDriver webDriver() throws IOException {
        return SharedWebDriver.aWebDriver(ChromeWebDriverBuilder.aChromeWebDriver().build());
    }
}