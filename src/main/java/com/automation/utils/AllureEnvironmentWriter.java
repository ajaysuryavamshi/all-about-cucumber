package com.automation.utils;

import com.automation.config.ConfigManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class AllureEnvironmentWriter {

    public static void writeEnvDetails() {
        Properties props = new Properties();
        props.setProperty("Browser", ConfigManager.getBrowser());
        props.setProperty("Environment", ConfigManager.getRunMode());
        props.setProperty("Base URL", ConfigManager.getApplicationURL());
        props.setProperty("Execution Type", ConfigManager.getRunMode());

        File dir = new File("allure-results");
        if (!dir.exists())
            dir.mkdirs();

        try (FileWriter writer = new FileWriter("allure-results/environment.properties")) {
            props.store(writer, "Allure Environment Details");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
