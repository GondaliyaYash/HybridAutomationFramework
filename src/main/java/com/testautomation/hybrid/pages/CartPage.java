package com.testautomation.hybrid.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; // Added this missing import
import org.openqa.selenium.JavascriptExecutor; // Added for cleaner JS calls
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By cartItems = By.className("cart_item");
    private By cartBadge = By.className("shopping_cart_badge");

    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isProductVisible(String productName) {
        try {
            System.out.println("Looking for product in cart: " + productName);
            
            By productLocator = By.xpath("//div[@class='inventory_item_name' and normalize-space()='" + productName + "']");
            
            boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator)).isDisplayed();
            
            System.out.println("Product found: " + productName);
            return isVisible;
        } catch (Exception e) {
            System.out.println("Product NOT found: " + productName + ". Current URL: " + driver.getCurrentUrl());
            return false;
        }
    }

    public int getCartItemCount(){
        pause(500);
        int count = driver.findElements(cartItems).size();
        System.out.println("Cart item count: " + count);
        return count;
    }
    
    public String getCartBadgeText() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartBadge)
            ).getText();
        } catch (Exception e) {
            return "0";
        }
    }
    
    public void openCart(){
        wait.until(ExpectedConditions.elementToBeClickable(
            By.className("shopping_cart_link")
        )).click();
        pause(1500);
    }

    public void removeProduct(String productName) {
        String removeBtnId = "remove-" + productName.toLowerCase().replace(" ", "-");
        wait.until(ExpectedConditions.elementToBeClickable(By.id(removeBtnId))).click();
        pause(1000);
        System.out.println("Removed product: " + productName);
    }
    
    public void clickCheckout() {
        System.out.println("Attempting to click Checkout button...");
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkoutBtn);
        
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
        System.out.println("Successfully navigated to Checkout Information page.");
    }
}