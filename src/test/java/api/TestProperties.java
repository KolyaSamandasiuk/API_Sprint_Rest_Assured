package api;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class TestProperties {
    private static Properties properties;

    public static String getBaseUrl() {
        return getProperties().getProperty("baseUrl");
    }

    private static Properties getProperties() {
        if (properties == null) {
            try (InputStream input = TestProperties.class.getClassLoader().getResourceAsStream("config.properties")) {
                properties = new Properties();
                properties.load(input);
            } catch (IOException ex) {
                log.error("Exception while reading test properties " + ex.getMessage());
            }
        }
        return properties;
    }

}
