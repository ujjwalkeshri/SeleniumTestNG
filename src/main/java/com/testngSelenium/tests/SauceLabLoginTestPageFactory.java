package com.testngSelenium.tests;


import com.testngSelenium.pageFactory.LoginPageFactory;
import com.testngSelenium.pageFactory.ProductPageFactory;
import com.testngSelenium.utilities.ReadLoginDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;


import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;

public class SauceLabLoginTestPageFactory {

    private static WebDriver driver;

    LoginPageFactory loginPage;
    ProductPageFactory productPageFactory;

    @BeforeClass
    public static void readTestData() throws IOException, URISyntaxException {
        new ReadLoginDetails().readData();
    }

    @BeforeTest
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPageFactory(driver);
        productPageFactory = new ProductPageFactory(driver);

    }

    @AfterMethod
    public void tearDown() {
        //driver.quit();
    }

    @Test
    public void successfulLoginTest() {
        String title = loginPage.setUsername(ReadLoginDetails.loginDataList.get(0).getUsername())
                .setPassword(ReadLoginDetails.loginDataList.get(0).getPassword())
                .clickLoginButton()
                .getTitle();
        System.out.println("title: " + title);
        Assert.assertEquals(title, "Products");
    }

    @Test(dependsOnMethods = {"successfulLoginTest"})
    public void addToCartTest() {
        productPageFactory.clickAddToCartButtonForBAckPack().logout();
    }

    @Test
    public void locked_out_user_Test() {
        String errorMessage = loginPage.setUsername(ReadLoginDetails.loginDataList.get(1).getUsername())
                .setPassword(ReadLoginDetails.loginDataList.get(1).getPassword())
                .unsuccessfulLogin()
                .getErrorMessage();

        Assert.assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void locked_out_user_Test2() {
        String errorMessage = loginPage.setUsername(ReadLoginDetails.loginDataList.get(1).getUsername())
                .setPassword(ReadLoginDetails.loginDataList.get(1).getPassword())
                .unsuccessfulLogin()
                .getErrorMessage();

        Assert.assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out.");
    }


}
