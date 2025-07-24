package com.automation.steps;

import com.automation.config.ConfigReader;
import com.automation.drivers.DriverManager;
import com.automation.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Map;

public class UserManagementSteps {
    WebDriver driver = DriverManager.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    LoginPage loginPage = new LoginPage(driver);

    @Given("the admin is logged in to OrangeHRM")
    public void adminIsLoggedIn() {
        driver.get(ConfigReader.getApplicationURL());
        loginPage.login("Admin", "admin123");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Dashboard']")));
    }

    @And("the admin navigates to the Admin - User Management - Users page")
    public void adminNavigatesToUserManagement() {
        driver.findElement(By.xpath("//span[text()='Admin']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='System Users']")));
    }

    @When("the admin clicks the Add button")
    public void adminClicksAddButton() {
        driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Add User']")));
    }

    @And("fills in the user details with:")
    public void adminFillsUserDetails(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        // User Role
        driver.findElement(By.xpath("//label[text()='User Role']/following::div[contains(@class,'oxd-select-wrapper')][1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']")));
        driver.findElement(By.xpath("//div[@role='listbox']//span[text()='" + data.get("userRole") + "']")).click();

        // Employee Name Auto-complete
        WebElement empInput = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
        empInput.sendKeys(data.get("employee"));
        // Wait for suggestions and choose (first) match
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']//span[contains(text(),'" + data.get("employee") + "')]")));
        driver.findElement(By.xpath("//div[@role='option']//span[contains(text(),'" + data.get("employee") + "')]")).click();

        // Status
        driver.findElement(By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-wrapper')][1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']")));
        driver.findElement(By.xpath("//div[@role='listbox']//span[text()='" + data.get("status") + "']")).click();

        // Username
        driver.findElement(By.xpath("//label[text()='Username']/../following-sibling::div/input")).sendKeys(data.get("username"));

        // Password
        driver.findElement(By.xpath("//label[text()='Password']/../following-sibling::div/input")).sendKeys(data.get("password"));

        // Confirm Password
        driver.findElement(By.xpath("//label[text()='Confirm Password']/../following-sibling::div/input")).sendKeys(data.get("confirmpass"));
    }

    @And("clicks the Save button")
    public void adminClicksSaveButton() {
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
    }

    @Then("the new user {string} should be present in the user list")
    public void userShouldBePresentInList(String username) {
        // Wait for possible toast or user list reload
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='System Users']")));
        // Search for the created username
        WebElement searchBox = driver.findElement(By.xpath("//label[text()='Username']/../following-sibling::div/input"));
        searchBox.clear();
        searchBox.sendKeys(username);
        // Click Search
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Wait and assert
        By userCell = By.xpath("//div[@class='oxd-table-cell' and text()='" + username + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(userCell));
        Assert.assertTrue(driver.findElement(userCell).isDisplayed(), "User is present in the list");
    }
}

