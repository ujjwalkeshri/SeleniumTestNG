package com.testngSelenium.OrangeHRM;


import com.testngSelenium.pageFactory.LoginPageFactory;
import com.testngSelenium.pageFactory.ProductPageFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;

public class AddEmployee {
    private static WebDriver driver;

    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        js = (JavascriptExecutor) driver;
    }

    private static JavascriptExecutor js;

    public void launchURL() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public void login() {
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
    }

    public void clickMenuLink(String menu) {
        driver.findElement(By.xpath("//span[normalize-space()='" + menu + "']")).click();
    }

    public static void main(String[] args) throws IOException {
        AddEmployee at = new AddEmployee();
        setUp();
        at.launchURL();
        at.login();
        at.clickMenuLink("PIM");
        driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();

        WebElement element = driver.findElement(By.cssSelector(".employee-image"));

        byte[] fileContent = FileUtils.readFileToByteArray(new File("C:\\Users\\user\\Pictures\\soap.PNG"));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, "src", "data:image/png;base64," + encodedString);

        driver.findElement(By.cssSelector("input[placeholder='First Name']")).sendKeys("Ujjwal");
    }
}
