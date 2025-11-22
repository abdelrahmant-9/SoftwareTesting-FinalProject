package Testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

public class Testcase2 {
    WebDriver driver = new ChromeDriver();
    @BeforeTest
    public void beforetest (){
        driver.navigate().to("https://www.saucedemo.com/");
    }

    WebDriver driver1;
    @BeforeMethod
    public void  openBrowser(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("profile.credentials_enable_autosignin", false);
        prefs.put("password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-features=PasswordManagerEnabled");
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--disable-features=SafeBrowsingSecurityToken");
        options.addArguments("--disable-features=SafetyTipUI");

        driver1 = new ChromeDriver(options);}



    @BeforeMethod
    public void beforemethod() throws InterruptedException {
        Thread.sleep(5000);


    }

    @Test (priority = 1)
    public void checkout_IformationPage_open(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();

    }



        @Test (priority = 2)
    public void valid_inputs(){
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        driver.findElement(By.id("back-to-products")).click();
    }

    /*@BeforeMethod
    public void beforemethod1() throws InterruptedException {
        Thread.sleep(5000);


    }*/

    @Test (priority = 3)
    public void fields_empty(){
       // driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("");
        driver.findElement(By.id("last-name")).sendKeys("");
        driver.findElement(By.id("postal-code")).sendKeys("");
        driver.findElement(By.id("continue")).click();
    }

    @Test (priority = 4)
    public void donot_select_products(){
        driver.findElement(By.id("cancel")).click();
        driver.findElement(By.id("continue-shopping")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
    }


    @AfterClass
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000L);
        this.driver.quit();
    }

    public void slowClick(By locator) throws InterruptedException {
        this.driver.findElement(locator).click();
        Thread.sleep(1200L);
    }

    public void slowSendKeys(By locator, String text) throws InterruptedException {
        for(char c : text.toCharArray()) {
            this.driver.findElement(locator).sendKeys(new CharSequence[]{String.valueOf(c)});
            Thread.sleep(120L);
        }

    }

    public void waitSlow() throws InterruptedException {
        Thread.sleep(2000L);
    }

}




/*
package Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testcase2 {
    WebDriver driver = new ChromeDriver();
    @BeforeTest
    public void beforetest (){
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @BeforeMethod
    public void beforemethod() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
        driver.navigate().to("https://www.saucedemo.com/");

    }

    @Test
    public void Valid(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

    }
    @Test
    public void inValid(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauc");
        driver.findElement(By.id("login-button")).click();

    }

}

 */





