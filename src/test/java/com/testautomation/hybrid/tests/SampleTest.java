package com.testautomation.hybrid.tests;

import org.testng.annotations.Test;
import com.testautomation.hybrid.base.BaseTest;

public class SampleTest extends BaseTest {

    @Test
    public void openAUT() {
        System.out.println("Title of page: " + driver.getTitle());
    }
}
