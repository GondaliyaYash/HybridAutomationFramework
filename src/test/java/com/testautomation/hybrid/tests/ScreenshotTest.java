package com.testautomation.hybrid.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.testautomation.hybrid.utils.DriverManager;
import com.testautomation.hybrid.utils.ScreenshotUtils;

public class ScreenshotTest {

    @Test
    public void testScreenshotOnly() {
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        DriverManager.setDriver(driver);

        // Open any website
        driver.get("https://www.google.com");

        // Take screenshot
        String path = ScreenshotUtils.captureScreenshot(driver, "ManualTest");
        System.out.println("Screenshot saved at: " + path);

        // Close driver
        driver.quit();
//        DriverManager.unload();
    }
}
