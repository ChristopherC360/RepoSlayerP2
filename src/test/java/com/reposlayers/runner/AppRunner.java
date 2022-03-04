package com.reposlayers.runner;

import com.reposlayers.pages.AppPage;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "com.reposlayers.steps")
public class AppRunner {

    public static WebDriver driver;

    public static AppPage appPage;

    @BeforeClass
    public static void setUp() {
        File file = new File("src/test/resources/geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());

        driver = new FirefoxDriver();
        appPage = new AppPage(driver);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
    
}
