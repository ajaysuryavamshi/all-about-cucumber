package com.automation.steps;

import com.automation.config.ConfigReader;
import com.automation.drivers.DriverManager;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginSteps {

    private final WebDriver driver = DriverManager.getDriver();

    @Given("the user is on the OrangeHRM login page")
    public void userIsOnLoginPage() {
        driver.get(ConfigReader.getApplicationURL());
    }

    @When("the user enters username {string} and password {string}")
    public void userEntersCredentials(String username, String password) {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
    }

    @Then("the user should see a message {string}")
    public void userSeesMessage(String expectedResult) {
        if ("Dashboard visible".equals(expectedResult)) {
            Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"));
        } else if ("Invalid credentials".equals(expectedResult)) {
            String errorMsg = driver.findElement(By.cssSelector(".oxd-alert-content-text")).getText();
            Assert.assertEquals(errorMsg, "Invalid credentials");
        }
    }

    @When("clicks the login button")
    public void userClicksLogin() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

}

