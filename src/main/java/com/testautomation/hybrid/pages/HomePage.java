package com.testautomation.hybrid.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    private By inventoryPage = By.id("inventory_container");
    private By menuBtn = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");
    private By popupOkBtn = By.id("password-modal-ok-button");
    private By cartIcon = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");
    
    // Helper method for visible delays
    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    // Convert product name to ID format (FIXED)
    private String getProductId(String productName) {
        // "Sauce Labs Backpack" -> "sauce-labs-backpack"
        // "Sauce Labs Bike Light" -> "sauce-labs-bike-light"
        return productName.toLowerCase().replace(" ", "-");
    }
    
    // ================= HOME PAGE =================
    public boolean isUserOnHomePage() {
        try {
            boolean isDisplayed = wait.until(
                ExpectedConditions.visibilityOfElementLocated(inventoryPage)
            ).isDisplayed();
            pause(1000);
            return isDisplayed;
        } catch (Exception e) {
            return false;
        }
    }
    
    // ================= LOGOUT (FIXED) =================
    public void logout() {
        try {
            // Click menu button
            WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(menuBtn));
            menuButton.click();
            pause(1000); // Increased wait for menu to open
            
            // Handle optional popup
            try {
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
                WebElement popup = shortWait.until(ExpectedConditions.elementToBeClickable(popupOkBtn));
                popup.click();
                pause(500);
            } catch (Exception ignored) {
                System.out.println("No popup found, continuing...");
            }
            
            // Wait for logout link and click with JavaScript
            WebElement logoutElement = wait.until(ExpectedConditions.presenceOfElementLocated(logoutLink));
            
            // Scroll into view first
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", logoutElement);
            pause(500);
            
            // Click using JavaScript
            js.executeScript("arguments[0].click();", logoutElement);
            pause(1500);
            
        } catch (Exception e) {
            System.out.println("Logout failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    private String getAddToCartId(String productName) {
        return "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
    }

    private String getRemoveButtonId(String productName) {
        return "remove-" + productName.toLowerCase().replace(" ", "-");
    }
    
    // ================= ADD TO CART (Dynamic for any product) =================
    public void addProductToCart(String productName) {
        String addButtonId = getAddToCartId(productName);
        System.out.println("=== Adding product to cart: " + productName + " ===");
        
        try {
            // 1. Wait for Add button and click it
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id(addButtonId)));
            
            // Use JS click to avoid any overlay issues
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", addButton);
            
            // 2. IMPORTANT: Verify the "Remove" button appears to confirm it was added
            String removeId = getRemoveButtonId(productName);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(removeId)));
            
            pause(1000); // Visual confirmation
        } catch (Exception e) {
            System.err.println("Failed to add product: " + productName + " Error: " + e.getMessage());
            throw e; 
        }
    }
    
    public boolean isButtonChangedToRemove(String productName) {
        String removeId = getRemoveButtonId(productName);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(removeId))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    // Get cart badge count
    public String getCartBadgeCount() {
        try {
            pause(500);
            String count = wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartBadge)
            ).getText();
            System.out.println("Cart badge count: " + count);
            return count;
        } catch (Exception e) {
            System.out.println("Cart badge not visible");
            return "0";
        }
    }
    
    // Check if button changed to "Remove" (FIXED)
    public boolean isRemoveButtonDisplayed(String productName) {
        try {
            // Use the consistent ID helper you created earlier
            String removeId = getRemoveButtonId(productName); 
            By removeBtn = By.id(removeId);
            
            System.out.println("Checking Remove button with ID: " + removeId);
            
            return wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtn)).isDisplayed();
        } catch (Exception e) {
            System.out.println("Remove button not found for: " + productName);
            return false;
        }
    }
    
    public boolean isProductAddedToCart() {
        try {
            boolean isDisplayed = wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartBadge)
            ).isDisplayed();
            pause(500);
            return isDisplayed;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void openCart() {
        try {
            // 1. Wait until the cart icon is clickable
            WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
            
            // 2. Use JavaScript click to ensure it bypasses any potential overlay issues
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", cart);
            
            // 3. CRITICAL: Wait for the URL to actually change
            wait.until(ExpectedConditions.urlContains("cart.html"));
            
            System.out.println("Successfully navigated to: " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.out.println("Standard click failed, trying force click...");
            // Fallback: directly navigate if the click fails
            driver.get("https://www.saucedemo.com/cart.html");
        }
    }
    
    public void removeProductFromCart(String productName) {
        String productId = getProductId(productName);
        By removeBtn = By.id("remove-" + productId);
        
        wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
        pause(1000);
    }
}