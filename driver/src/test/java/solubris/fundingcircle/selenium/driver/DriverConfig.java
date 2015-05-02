package solubris.fundingcircle.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import solubris.fundingcircle.spring.Profile;
import solubris.fundingcircle.spring.PropertyBasedProfileBuilder;

import java.io.IOException;

@Configuration
public class DriverConfig {
    @Bean
    public WebDriver webDriver() throws IOException {
        return ChromeSharedWebDriver.aWebDriver();
    }
}