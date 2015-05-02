package solubris.fundingcircle.spring;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import solubris.fundingcircle.selenium.driver.DriverConfig;

import java.io.IOException;

@Import(DriverConfig.class)
@Configuration
public class AppConfig {
    @Autowired
    private ConfigurableEnvironment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Profile solubris() throws IOException {
        return PropertyBasedProfileBuilder.aProfileUsing(env).named("solubris").build();
    }

    @Bean
    public Profile tim() throws IOException {
        return PropertyBasedProfileBuilder.aProfileUsing(env).named("tim").build();
    }
}