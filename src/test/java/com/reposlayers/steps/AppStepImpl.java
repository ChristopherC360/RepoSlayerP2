package com.reposlayers.steps;

import com.reposlayers.pages.AppPage;
import com.reposlayers.runner.AppRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
import org.openqa.selenium.By;

public class AppStepImpl {

    public static WebDriver driver = AppRunner.driver;
    public static AppPage appPage = AppRunner.appPage;

    @Given("The User is on the Login Page")
    public void the_User_is_on_the_Login_Page() {
        driver.get("http://localhost:8080/");
    }

    @When("The User inputs information and clicks Login")
    public void the_User_inputs_information_and_clicks_Login() {
        appPage.usernameInput.sendKeys("admin");
        appPage.passwordInput.sendKeys("admin");
        appPage.loginButton.click();
    }

    @Then("The User should be on the Dashboard Page")
    public void the_User_should_be_on_the_Dashboard_Page() {
        Assert.assertEquals("Dashboard", driver.findElement(By.xpath("//h1")).getText());
    }
    
}
