package solubris.fundingcircle.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages = "solubris.fundingcircle")
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