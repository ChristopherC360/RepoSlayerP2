package com.reposlayers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppPage {

    private WebDriver driver;

    public AppPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locate the username and password input fields
    @FindBy(id = "loginUser")
    public WebElement usernameInput;

    @FindBy(id = "loginPassword")
    public WebElement passwordInput;

    @FindBy(id = "loginBtn")
    public WebElement loginButton;
    

    // Locate dashboard buttons
    @FindBy(id = "getAllClaims")
    public WebElement getAllClaimsButton;

    @FindBy(id = "approvalyByAgeBtn")
    public WebElement approvalyByAgeButton;

    @FindBy(id = "agentsAndApprovalsBtn")
    public WebElement agentsAndApprovalsButton;
}
