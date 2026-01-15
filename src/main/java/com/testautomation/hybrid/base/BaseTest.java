package com.testautomation.hybrid.base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.testautomation.hybrid.drivers.DriverFactory;

public class BaseTest {

    public static WebDriver driver; // ← static so Listener can access
    protected DriverFactory driverFactory;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driverFactory = new DriverFactory();
        driver = driverFactory.initDriver(); // same driver instance
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
