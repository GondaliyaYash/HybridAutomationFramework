package com.testautomation.hybrid.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            String dirPath = System.getProperty("user.dir")
                    + "/screenshots/LoginTest";
            Files.createDirectories(Paths.get(dirPath));

            String filePath = dirPath + "/"
                    + testName + "_"
                    + System.currentTimeMillis() + ".png";

            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Files.copy(src.toPath(), Paths.get(filePath));

            System.out.println("Screenshot saved at: " + filePath);

            return filePath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
