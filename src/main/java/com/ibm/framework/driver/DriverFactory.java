package com.ibm.framework.driver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.ibm.framework.config.ConfigReader;

public final class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {}

public static void initDriver() {

    String browser = System.getProperty(
            "browser",
            ConfigReader.get("browser")
    ).trim();

    boolean headless = Boolean.parseBoolean(
            System.getProperty(
                    "headless",
                    ConfigReader.get("headless")
            )
    );

    WebDriver webDriver;

    switch (browser.toLowerCase()) {

        case "chrome":
            ChromeOptions chromeOptions = new ChromeOptions();
            if (headless) {
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--window-size=1920,1080");
            }
            webDriver = new ChromeDriver(chromeOptions);
            break;

        case "firefox":
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            if (headless) {
                firefoxOptions.addArguments("-headless");
                firefoxOptions.addArguments("--width=1920");
                firefoxOptions.addArguments("--height=1080");
            }
            webDriver = new FirefoxDriver(firefoxOptions);
            break;

        default:
            throw new RuntimeException(
                "Unsupported browser: " + browser +
                ". Supported: chrome, firefox"
            );
    }

    driver.set(webDriver);

    getDriver().manage().timeouts().pageLoadTimeout(
            Duration.ofSeconds(
                    Integer.parseInt(
                            ConfigReader.get("pageLoadTimeout")
                    )
            )
    );
    
}


    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new RuntimeException("Driver not initialized. Call initDriver() first.");
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
    

}
