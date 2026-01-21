package com.testautomation.hybrid.stepdefinitions;

import com.testautomation.hybrid.pages.HomePage;
import com.testautomation.hybrid.utils.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ProductFilterSteps {

    private HomePage getHomePage() {
        return new HomePage(DriverManager.getDriver());
    }

    @When("user applies filter {string}")
    public void user_applies_filter(String filterName) {
        getHomePage().applyFilter(filterName);
    }

    @Then("all filters should be applied successfully")
    public void all_filters_should_be_applied_successfully() {
        boolean isOnHome = getHomePage().isUserOnHomePage();
        Assert.assertTrue(isOnHome, "User is not on Home page after applying filters");
    }
    
    @Then("user logs out successfully")
    public void user_logs_out_successfully() {
        getHomePage().logout();
    }
}