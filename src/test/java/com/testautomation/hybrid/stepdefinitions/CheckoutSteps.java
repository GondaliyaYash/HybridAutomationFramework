package com.testautomation.hybrid.stepdefinitions;

import com.testautomation.hybrid.pages.CartPage;
import com.testautomation.hybrid.pages.CheckoutPage;
import com.testautomation.hybrid.utils.DriverManager;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class CheckoutSteps {
    CheckoutPage checkoutPage;
    CartPage cartPage;

    @When("user clicks checkout button")
    public void user_clicks_checkout_button() {
        cartPage = new CartPage(DriverManager.getDriver());
        cartPage.clickCheckout();
    }

    @When("user enters shipping details as {string}, {string}, {string}")
    public void user_enters_shipping_details(String f, String l, String z) {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        checkoutPage.enterShippingDetails(f, l, z);
    }

    @When("user clicks finish button")
    public void user_clicks_finish_button() {
        if(checkoutPage == null) checkoutPage = new CheckoutPage(DriverManager.getDriver());
        checkoutPage.clickFinish();
    }

    @Then("{string} message should be displayed")
    public void message_should_be_displayed(String expectedMsg) {
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), expectedMsg);
        try { Thread.sleep(2000); } catch (InterruptedException e) {} // Final pause to see success
    }

    @When("user clicks back home button")
    public void user_clicks_back_home_button() {
        checkoutPage.clickBackHome();
    }
}