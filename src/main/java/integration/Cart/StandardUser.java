package integration.Cart;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class StandardUser {

    WebDriver driver;

    private boolean isElementPresent(By by){
        try{
            driver.findElement(by);
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    @BeforeClass
    public void openBrowser(){
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

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @BeforeMethod
    public void smallPause() throws InterruptedException {
        Thread.sleep(500);
    }

    @Test(priority = 1)
    public void AddSingleItemToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("shopping_cart_container")).click();
        WebElement cart_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
        Assert.assertEquals(cart_no.getText(), "1");
    }

    @Test(priority = 2)
    public void AddMultipleItemsToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("shopping_cart_container")).click();
        WebElement item1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Sauce Labs Bike Light")));
        WebElement item2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Sauce Labs Bolt T-Shirt")));
        WebElement cart_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
        Assert.assertEquals(cart_no.getText(), "2");
        Assert.assertTrue(item1.isDisplayed());
        Assert.assertTrue(item2.isDisplayed());
    }

    @Test(priority = 3)
    public void RemoveItemFromCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("shopping_cart_container")).click();
        WebElement cart_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
        Assert.assertEquals(cart_no.getText(), "1");
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        boolean exists = isElementPresent(By.linkText("Sauce Labs Backpack"));
        Assert.assertFalse(exists);
    }

    @Test(priority = 4)
    public void CartBadgeCount(){
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        WebElement badge = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(badge.getText(), "2");
    }

    @Test(priority = 5)
    public void GoToCheckoutFromCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("shopping_cart_container")).click();
        driver.findElement(By.id("checkout")).click();

        WebElement checkoutTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("title")));

        Assert.assertEquals(checkoutTitle.getText(), "Checkout: Your Information");
    }

    @Test(priority = 6)
    public void ContinueShoppingFromCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("shopping_cart_container")).click();
        driver.findElement(By.id("continue-shopping")).click();

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test(priority = 7)
    public void CartIsEmptyInitially(){
        boolean badgeExists = isElementPresent(By.className("shopping_cart_badge"));
        Assert.assertFalse(badgeExists);
    }

    @AfterClass
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
