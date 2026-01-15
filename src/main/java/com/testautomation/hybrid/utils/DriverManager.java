package com.testautomation.hybrid.utils;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    // ThreadLocal ensures thread safety for parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void unload() {
        driver.remove();
    }
}