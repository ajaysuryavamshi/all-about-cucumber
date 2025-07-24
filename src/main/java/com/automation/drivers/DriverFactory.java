package com.automation.drivers;

import com.automation.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    public static WebDriver createInstance() {
        String browser = ConfigReader.getBrowser();
        String runMode = ConfigReader.getRunMode();

        return switch (runMode) {
            case "LOCAL" -> getLocalDriver(browser);
            case "GRID" -> getGridDriver(browser);
            default -> throw new RuntimeException("Unsupported run mode: " + runMode);
        };
    }

    private static WebDriver getLocalDriver(String browser) {
        return switch (browser) {
            case "CHROME" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--window-size=1920,1080");
                yield new ChromeDriver(chromeOptions);
            }
            case "FIREFOX" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                yield new FirefoxDriver(firefoxOptions);
            }
            case "EDGE" -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                yield new EdgeDriver(edgeOptions);
            }
            case "SAFARI" -> {
                SafariOptions safariOptions = new SafariOptions();
                yield new SafariDriver(safariOptions);
            }
            default -> throw new RuntimeException("Unsupported browser: " + browser);
        };
    }

    private static WebDriver getGridDriver(String browser) {
        DesiredCapabilities capabilities;
        switch (browser) {
            case "CHROME":
                capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", "chrome");
                ChromeOptions chromeOptions = new ChromeOptions();
                capabilities.merge(chromeOptions);
                break;
            case "FIREFOX":
                capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", "firefox");
                FirefoxOptions ffOptions = new FirefoxOptions();
                capabilities.merge(ffOptions);
                break;
            case "EDGE":
                capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", "MicrosoftEdge");
                EdgeOptions edgeOptions = new EdgeOptions();
                capabilities.merge(edgeOptions);
                break;
            default:
                throw new RuntimeException("Unsupported browser for GRID: " + browser);
        }
        try {
            return new RemoteWebDriver(new URL(ConfigReader.getGridUrl()), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid grid URL", e);
        }
    }
}
