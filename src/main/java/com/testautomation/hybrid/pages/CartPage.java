package com.testautomation.hybrid.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    public boolean isProductVisible(String productName){
        try {
            pause(1000);
            
            System.out.println("Looking for product in cart: " + productName);
            
            // Primary XPath - using inventory_item_name class
            boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']"))
            ).isDisplayed();
            
            System.out.println("Product found: " + productName);
            pause(500);
            return isVisible;
            
        } catch(Exception e){
            System.out.println("Product NOT found using primary XPath, trying fallback...");
            
            // Fallback XPath
            try {
                boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'cart_item')]//div[contains(text(),'" + productName + "')]"))
                ).isDisplayed();
                
                System.out.println("Product found with fallback: " + productName);
                return isVisible;
                
            } catch(Exception ex) {
                System.out.println("Product NOT found: " + productName);
                ex.printStackTrace();
                return false;
            }
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
}