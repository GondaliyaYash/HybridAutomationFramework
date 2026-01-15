package com.testautomation.hybrid.stepdefinitions;

import org.testng.Assert;

import com.testautomation.hybrid.pages.HomePage;
import com.testautomation.hybrid.pages.LoginPage;
import com.testautomation.hybrid.utils.DriverManager;
import com.testautomation.hybrid.utils.ExcelUtils;
import com.testautomation.hybrid.utils.ScreenshotUtils;

import io.cucumber.java.en.*;

public class LoginSteps {

    LoginPage loginPage;
    HomePage homePage;

    String username;
    String password;

    @Given("user is on login page")
    public void user_is_on_login_page() {

        loginPage = new LoginPage(DriverManager.getDriver());
        homePage = new HomePage(DriverManager.getDriver());

        String excelPath = "src/test/resources/testdata/LoginData.xlsx";
        Object[][] data = ExcelUtils.getTestData(excelPath, "Sheet1");

        username = data[0][0].toString();
        password = data[0][1].toString();
    }

    @When("user enters valid credentials from excel")
    public void user_enters_valid_credentials_from_excel() {
        loginPage.login(username, password);
    }


    @Then("user should be redirected to home page") 
    public void user_should_be_redirected_to_home_page() { 
    	boolean isHome = homePage.isUserOnHomePage(); 
    	Assert.assertTrue(isHome, "User is not on home page after login"); 
    	}

    @Then("take manual screenshot")
    public void take_manual_screenshot() {
        String path = ScreenshotUtils.captureScreenshot(
                DriverManager.getDriver(), "ManualTest");
        System.out.println("Screenshot saved at: " + path);
    }
}
