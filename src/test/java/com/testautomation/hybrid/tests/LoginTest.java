package com.testautomation.hybrid.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.testautomation.hybrid.base.BaseTest;
import com.testautomation.hybrid.pages.HomePage;
import com.testautomation.hybrid.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login("standard_user", "secret_sauce");

        boolean result = homePage.isUserOnHomePage();
        Assert.assertTrue(result, "Login failed!");
    }
}
