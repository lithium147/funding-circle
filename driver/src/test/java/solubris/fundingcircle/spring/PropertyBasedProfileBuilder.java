package solubris.fundingcircle.spring;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

public class PropertyBasedProfileBuilder {
    public static final String PROFILE_PROPERTIES = "profile-properties";
    private static final String DEFAULT_PROPERTIES = "default";

    private ConfigurableEnvironment environment;
    private String name;

    private PropertyBasedProfileBuilder(ConfigurableEnvironment environment) throws IOException {
        this.environment = environment;
        if(!environment.getPropertySources().contains(PROFILE_PROPERTIES)) {
            environment.getPropertySources().addLast(
                    classPathPropertySourceFor(DEFAULT_PROPERTIES)
            );
        }
    }

    private static ResourcePropertySource classPathPropertySourceFor(String name) throws IOException {
        return new ResourcePropertySource(
                PROFILE_PROPERTIES,
                new ClassPathResource("META-INF/profile/" + name + ".properties")
        );
    }

    private static ResourcePropertySource privatePropertySourceFor(String name) throws IOException {
        return new ResourcePropertySource(
                PROFILE_PROPERTIES,
                new FileSystemResource(System.getProperty("user.home") + "/private/funding-circle/" + name + ".properties")
        );
    }

    public PropertyBasedProfileBuilder named(String name) {
        this.name = name;
        return this;
    }

    public Profile build() throws IOException {
        environment.getPropertySources().replace(
                PROFILE_PROPERTIES,
                privatePropertySourceFor(name)
        );
        return new ProfileImpl();
    }

    public static PropertyBasedProfileBuilder aProfileUsing(ConfigurableEnvironment environment) throws IOException {
        return new PropertyBasedProfileBuilder(environment);
    }
}