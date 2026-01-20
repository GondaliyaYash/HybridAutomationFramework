package com.testautomation.hybrid.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.testautomation.hybrid.config.ConfigReader;
import com.testautomation.hybrid.drivers.DriverFactory;

public class BaseTest {

    public static WebDriver driver; // static → Listener access
    protected WebDriverWait wait;
    protected DriverFactory driverFactory;

    @BeforeMethod
    public void setUp() {
        driverFactory = new DriverFactory();

        // Initialize driver based on config.properties
        driver = driverFactory.initDriver();

        // Explicit wait from config
        wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(ConfigReader.getExplicitWait())
        );

        // Navigate to application URL
        driver.get(ConfigReader.getURL());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
