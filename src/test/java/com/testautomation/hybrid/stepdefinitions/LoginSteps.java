package com.testautomation.hybrid.stepdefinitions;

import org.testng.Assert;

import com.testautomation.hybrid.pages.HomePage;
import com.testautomation.hybrid.pages.LoginPage;
import com.testautomation.hybrid.utils.DriverManager;

import io.cucumber.java.en.*;

public class LoginSteps {

    LoginPage loginPage;
    HomePage homePage;

    @Given("user is on login page")
    public void user_is_on_login_page() {
        loginPage = new LoginPage(DriverManager.getDriver());
        homePage = new HomePage(DriverManager.getDriver());
        Assert.assertTrue(loginPage.isUserOnLoginPage(), "Login page is not displayed");
    }

    @When("user logs in with {string} and {string}")
    public void user_logs_in_with_and(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("user should be redirected to home page")
    public void user_should_be_redirected_to_home_page() {
        Assert.assertTrue(homePage.isUserOnHomePage(),
                "User is not on home page after login");
    }
}
