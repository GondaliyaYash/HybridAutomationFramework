package com.testautomation.hybrid.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.testautomation.hybrid.config.ConfigReader;

import java.time.Duration;

public class DriverFactory {

    private WebDriver driver;
    private ConfigReader config;

    public WebDriver initDriver() {
        config = new ConfigReader();
        String browser = config.getBrowser();

        if (browser.equalsIgnoreCase("chrome")) {
            // Selenium 4.6+ will handle the path automatically!
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }

        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get(config.getURL());
        }
        
        return driver;
    }
}
