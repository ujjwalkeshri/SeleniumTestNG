package com.testngSelenium.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPageFactory {

    WebDriver driver;

    public ProductPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")
    WebElement titleElement;
    //WebElement titleElement = driver.findElement(By.className("title");

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement backPackAddToCartElement;

    @FindBy(css = "#react-burger-menu-btn")
    WebElement bergerMenu;

    @FindBy(css = "#logout_sidebar_link")
    WebElement logoutOption;


    public String getTitle() {
        return titleElement.getText();
    }

    public ProductPageFactory clickAddToCartButtonForBAckPack() {
        backPackAddToCartElement.click();
        return this;
    }

    public void logout() {
        bergerMenu.click();
        logoutOption.click();
    }
}
