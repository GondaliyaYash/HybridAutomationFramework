package com.testautomation.hybrid.drivers;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.testautomation.hybrid.config.ConfigReader;

public class DriverFactory {

    private WebDriver driver;

    public WebDriver initDriver() {

        String browser = ConfigReader.getBrowser();

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } 
        else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } 
        else {
            throw new RuntimeException("Invalid browser value in config.properties: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWait")))
        );

        driver.manage().window().maximize();

        driver.get(ConfigReader.getURL());

        return driver;
    }
}
