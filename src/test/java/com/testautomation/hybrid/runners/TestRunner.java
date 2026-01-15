package com.testautomation.hybrid.runners;

import org.testng.annotations.Listeners;

import com.testautomation.hybrid.listeners.ExtentTestListener;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@Listeners(ExtentTestListener.class)
@CucumberOptions(
        features = "src/test/resources/features",
        	glue = {"com.testautomation.hybrid.stepdefinitions", "com.testautomation.hybrid.hooks"},
        	    plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@smoke",
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
