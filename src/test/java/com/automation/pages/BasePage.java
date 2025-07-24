package com.automation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final int WAIT_TIME = 30;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }
    protected WebElement find(WebElement element) {
        return element;
    }

    public void click(By locator) {
        WebElement element = waitForClickable(locator);
        highlightElement(element);
        element.click();
    }

    public void click(WebElement element) {
        waitForClickable(element);
        highlightElement(element);
        element.click();
    }

    public void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        highlightElement(element);
        element.clear();
        element.sendKeys(text);
    }

    public void type(WebElement element, String text) {
        waitForVisibility(element);
        highlightElement(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    public String getText(WebElement element) {
        return waitForVisibility(element).getText();
    }

    public boolean isDisplayed(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return waitForVisibility(element).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean waitForTitle(String title) {
        return wait.until(ExpectedConditions.titleContains(title));
    }

    public void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException ignored) {}
    }

    protected void highlightElement(WebElement element) {
        String style = "border:3px solid #f7af04; background: #fbfde7;";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, style);
    }

    protected void removeHighlights() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "var elems=document.querySelectorAll('[style*=border]');" +
                        "for(var i=0;i<elems.length;i++){elems[i].setAttribute('style','');}"
        );
    }

}

