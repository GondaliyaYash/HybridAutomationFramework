package com.testautomation.hybrid.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    // Static block → loads config once
    static {
        try {
            FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/config.properties"
            );
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    // Generic property reader (recommended)
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    // Specific getters (readability)
    public static String getBrowser() {
        return getProperty("browser");
    }

    public static String getURL() {
        return getProperty("url");
    }

    public static String getEnvironment() {
        return getProperty("environment");
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicitWait"));
    }
}
