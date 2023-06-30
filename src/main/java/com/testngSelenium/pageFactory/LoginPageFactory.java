package com.testngSelenium.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory {

    WebDriver driver;

    @FindBy(id = "user-name")
    WebElement usernameElement;

    @FindBy(id = "password")
    WebElement passwordElement;

    @FindBy(id = "login-button")
    WebElement loginButtonElement;

    @FindBy(tagName = "h3")
    WebElement errorElement;


    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPageFactory setUsername(String username) {
        usernameElement.clear();
        usernameElement.sendKeys(username);
        System.out.println("Username is set to: "+username);
        return this;
    }

    public LoginPageFactory setPassword(String password) {
        passwordElement.clear();
        passwordElement.sendKeys(password);
        return this;
    }

    public ProductPageFactory clickLoginButton() {
        loginButtonElement.click();
        return new ProductPageFactory(driver);
    }

    public LoginPageFactory unsuccessfulLogin() {
        loginButtonElement.click();
        return this;
    }

    public String getErrorMessage() {
        return errorElement.getText();
    }

}
