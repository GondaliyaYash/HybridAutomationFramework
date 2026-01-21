package com.testautomation.hybrid.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.testautomation.hybrid.utils.DriverManager;
import com.testautomation.hybrid.utils.ScreenshotUtils;

public class ScreenshotTest {

    @Test
    public void testScreenshotOnly() {
        WebDriver driver = new ChromeDriver();
        DriverManager.setDriver(driver);

        driver.get("https://www.google.com");

        String path = ScreenshotUtils.captureScreenshot(driver, "ManualTest");
        System.out.println("Screenshot saved at: " + path);

        driver.quit();
    }
}
