package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests {

    @Test
    public void testLoginInvalidUsername(){
        WebDriver driver = new ChromeDriver();

        driver.get("https://practicetestautomation.com/practice-test-login/");
        WebElement usernameInputField = driver.findElement(By.id("username"));
        usernameInputField.sendKeys("wrong_username");

        WebElement submitButton = driver.findElement(By.className("btn"));
        submitButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed());

        String expected_text_error = "Your username is invalid!";
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, expected_text_error);
    }

    @Test
    public void testLoginInvalidPassword(){
        WebDriver driver = new ChromeDriver();

        driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement usernameInputField = driver.findElement(By.id("username"));
        usernameInputField.sendKeys("student");

        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("wrong_password");
                      
        WebElement submitButton = driver.findElement(By.className("btn"));
        submitButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed());

        String expected_text_error = "Your password is invalid!";
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, expected_text_error);
    }



    @Test
    public void testLoginFunctionality() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement usernameInputField = driver.findElement(By.id("username"));
        usernameInputField.sendKeys("student");

        WebElement usernamePasswordField = driver.findElement(By.id("password"));
        usernamePasswordField.sendKeys("Password123");

        WebElement submitButton = driver.findElement(By.className("btn"));
        submitButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String expected_url = "https://practicetestautomation.com/logged-in-successfully/";

        String actual_url = driver.getCurrentUrl();
        Assert.assertEquals(actual_url, expected_url);

        String expected_text = "Congratulations student. You successfully logged in!";

        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(expected_text));

        WebElement logoutButton = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logoutButton.isDisplayed());
        driver.quit();
    }
}


