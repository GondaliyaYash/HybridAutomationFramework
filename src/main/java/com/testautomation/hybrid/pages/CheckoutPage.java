package com.testautomation.hybrid.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By finishBtn = By.id("finish");
    private By completeHeader = By.className("complete-header");
    private By backHomeBtn = By.id("back-to-products");

    private void highlightAndSleep(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    public void enterShippingDetails(String fname, String lname, String zip) {
        WebElement fNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        highlightAndSleep(fNameField);
        fNameField.sendKeys(fname);

        WebElement lNameField = driver.findElement(lastName);
        highlightAndSleep(lNameField);
        lNameField.sendKeys(lname);

        WebElement zipField = driver.findElement(postalCode);
        highlightAndSleep(zipField);
        zipField.sendKeys(zip);

        WebElement cont = driver.findElement(continueBtn);
        highlightAndSleep(cont);
        cont.click();
        
        // Ensure we reach the Overview page before finishing
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }

    public void clickFinish() {
        // Wait for overview page to be ready
        WebElement finish = wait.until(ExpectedConditions.elementToBeClickable(finishBtn));
        
        // Scroll to it so you can see it highlight
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finish);
        
        highlightAndSleep(finish);
        finish.click();
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }

    public void clickBackHome() {
        WebElement backBtn = wait.until(ExpectedConditions.elementToBeClickable(backHomeBtn));
        highlightAndSleep(backBtn);
        backBtn.click();
    }
}