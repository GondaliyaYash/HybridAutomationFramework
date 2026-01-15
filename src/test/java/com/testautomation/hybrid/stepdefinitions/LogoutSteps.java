package com.testautomation.hybrid.stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.testautomation.hybrid.pages.HomePage;
import com.testautomation.hybrid.pages.LoginPage;
import com.testautomation.hybrid.utils.DriverManager;

import io.cucumber.java.en.*;

public class LogoutSteps {

    HomePage homePage;
    WebDriver driver;
    LoginPage loginPage;

    @When("user clicks logout")
    public void user_clicks_logout() {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        homePage.logout();
    }

    @Then("user should be redirected to login page")
    public void verifyLogout() {
        // GET THE ACTIVE DRIVER HERE
        this.driver = DriverManager.getDriver(); 
        
        // Initialize the page object with the active driver
        loginPage = new LoginPage(this.driver);
        
        System.out.println("=== Verifying redirection to Login Page ===");
        
        // Now findElement won't throw NullPointerException
        Assert.assertTrue(loginPage.isUserOnLoginPage(), 
            "Logout failed! User is not on the login page.");
    }
}
