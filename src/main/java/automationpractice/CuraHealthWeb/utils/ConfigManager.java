package automationpractice.CuraHealthWeb.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();

    static {
        String env = System.getProperty("env", "qa"); // default to qa if not set
        String fileName = String.format("automationpractice/CuraHealthWeb/resources/GlobalData-%s.properties", env);
        System.out.println("Loading configuration from: " + fileName);
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException(fileName + " file not found in classpath. Make sure it is in src/main/resources/automationpractice/CuraHealthWeb/resources/");
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load configuration: " + ex.getMessage(), ex);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}