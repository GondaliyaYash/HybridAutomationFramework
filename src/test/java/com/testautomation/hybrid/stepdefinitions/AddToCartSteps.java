package com.testautomation.hybrid.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.testautomation.hybrid.pages.HomePage;
import com.testautomation.hybrid.pages.CartPage;
import com.testautomation.hybrid.utils.DriverManager;
import io.cucumber.java.en.*;

public class AddToCartSteps {

    HomePage homePage;
    CartPage cartPage;
    WebDriver driver;

    @When("user adds {string} to the cart")
    public void user_adds_product_to_the_cart(String productName) {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        
        System.out.println("=== Adding product to cart: " + productName + " ===");
        homePage.addProductToCart(productName);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @When("user opens the cart")
    public void user_opens_the_cart() {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        
        System.out.println("=== Opening cart ===");
        homePage.openCart();
        
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("cart badge should display {string}")
    public void cart_badge_should_display(String expectedCount) {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        String actualCount = homePage.getCartBadgeCount();
        
        System.out.println("=== Cart Badge Check - Expected: " + expectedCount + ", Actual: " + actualCount + " ===");
        
        Assert.assertEquals(actualCount, expectedCount, 
            "Cart badge count mismatch! Expected: " + expectedCount + ", Actual: " + actualCount);
    }

    @Then("{string} should be visible in the cart")
    public void product_should_be_visible_in_the_cart(String productName) {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver); 
        cartPage = new CartPage(driver);
        
        // 1. Navigate to cart if we are not there
        if (!driver.getCurrentUrl().contains("cart.html")) {
            System.out.println("Currently on " + driver.getCurrentUrl() + ". Moving to Cart...");
            homePage.openCart();
        }
        
        // 2. Perform Assertion
        boolean isVisible = cartPage.isProductVisible(productName);
        Assert.assertTrue(isVisible, "Product '" + productName + "' not visible in cart!");
        
        // IMPORTANT: DO NOT navigate back to inventory here. 
        // Stay in the cart so the next product check can see the list.
    }

    @Then("{string} add button should change to {string}")
    public void add_button_should_change_to_remove(String productName, String expectedButtonText) {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("=== Checking Remove button for: " + productName + " ===");
        
        boolean isRemoveBtn = homePage.isRemoveButtonDisplayed(productName);
        
        Assert.assertTrue(isRemoveBtn, 
            "'" + productName + "' button did not change to '" + expectedButtonText + "'!");
    }
}