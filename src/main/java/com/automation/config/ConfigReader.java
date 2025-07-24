package com.automation.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    private static final String PROPERTY_FILE_PATH = "src/main/resources/config.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream(PROPERTY_FILE_PATH)) {
            properties.load(input);
        } catch (IOException ex) {
            System.err.println("Error loading properties file: " + PROPERTY_FILE_PATH);
            throw new ExceptionInInitializerError("Failed to load configuration file.");
        }
    }

    private ConfigReader() {}

    public static String getBrowser() {
        String browser = properties.getProperty("browser");
        if (browser == null || browser.trim().isEmpty())
            throw new RuntimeException("Property 'browser' not specified in config.properties");
        return browser.trim().toUpperCase();
    }

    public static String getRunMode() {
        String runMode = properties.getProperty("run.mode");
        if (runMode == null || runMode.trim().isEmpty())
            throw new RuntimeException("Property 'run.mode' not specified in config.properties");
        return runMode.trim().toUpperCase();
    }

    public static String getApplicationURL() {
        String applicationUrl = properties.getProperty("application.url");
        if (applicationUrl == null || applicationUrl.trim().isEmpty())
            throw new RuntimeException("Property 'application.url' not specified in config.properties");
        return applicationUrl.trim();
    }

    public static String getGridUrl() {
        String gridUrl = properties.getProperty("grid.url");
        if (gridUrl == null || gridUrl.trim().isEmpty())
            throw new RuntimeException("Property 'grid.url' not specified in config.properties");
        return gridUrl.trim();
    }

    public static String getProperty(String key) {
        String val = properties.getProperty(key);
        if (val == null)
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        return val.trim();
    }

}

