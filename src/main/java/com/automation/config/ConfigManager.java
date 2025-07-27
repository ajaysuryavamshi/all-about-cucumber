package com.automation.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static final Properties props = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private ConfigManager(){}

    public static String getProperty(String key) {
        String sysProp = System.getProperty(key);
        if (sysProp != null)
            return sysProp.trim();

        String envVar = System.getenv(key);
        if (envVar != null)
            return envVar.trim();

        String val = props.getProperty(key);
        if (val == null)
            throw new RuntimeException("Property '" + key + "' not found in config.properties");

        return val.trim();
    }

    public static String getBrowser() {
        String browser = getProperty("browser");
        if (browser.isEmpty())
            throw new RuntimeException("Property 'browser' not specified in config.properties");
        return browser.toUpperCase();
    }

    public static String getRunMode() {
        String runMode = getProperty("run.mode");
        if (runMode.isEmpty())
            throw new RuntimeException("Property 'run.mode' not specified in config.properties");
        return runMode.toUpperCase();
    }

    public static String getApplicationURL() {
        String applicationUrl = getProperty("application.url");
        if (applicationUrl.isEmpty())
            throw new RuntimeException("Property 'application.url' not specified in config.properties");
        return applicationUrl;
    }

    public static String getGridUrl() {
        String gridUrl = getProperty("grid.url");
        if (gridUrl.isEmpty())
            throw new RuntimeException("Property 'grid.url' not specified in config.properties");
        return gridUrl;
    }

}