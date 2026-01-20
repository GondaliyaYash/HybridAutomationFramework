package com.testautomation.hybrid.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features", // Req 4 path
    glue = {"com.testautomation.hybrid.stepdefinitions", "com.testautomation.hybrid.hooks"}, 
    plugin = {
        "pretty", 
        "html:target/cucumber-reports.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" // Links to Extent Report
    },
    tags = "@smoke or @e2e", // Matches your feature tags for Req 5
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = false) // Requirement 9 bonus point option
    public Object[][] scenarios() {
        return super.scenarios();
    }
}