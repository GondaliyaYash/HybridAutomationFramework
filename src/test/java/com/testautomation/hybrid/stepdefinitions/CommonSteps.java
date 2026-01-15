package com.testautomation.hybrid.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.testautomation.hybrid.pages.HomePage;
import com.testautomation.hybrid.pages.LoginPage;
import com.testautomation.hybrid.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CommonSteps {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    
    @Given("user is logged in")
    public void user_is_logged_in() {
        driver = DriverManager.getDriver();
        Assert.assertNotNull(driver, "Driver is NULL in CommonSteps");
        
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        loginPage.login("standard_user", "secret_sauce");
        
        Assert.assertTrue(
            homePage.isUserOnHomePage(),
            "Login failed, user is not on home page"
        );
    }
    
    @When("user logs out")
    public void user_logs_out() {
        homePage = new HomePage(DriverManager.getDriver());
        homePage.logout();
    }
}