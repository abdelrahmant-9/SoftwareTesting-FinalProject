package integration.Cart;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StandardUser {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.saucedemo.com/");
    }

    // 1 - Login
    @Test(priority = 1)
    public void loginSuccessfully() throws InterruptedException {
        slowSendKeys(By.cssSelector("[data-test='username']"), "standard_user");
        slowSendKeys(By.cssSelector("[data-test='password']"), "secret_sauce");
        slowClick(By.cssSelector("[data-test='login-button']"));

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    // 2 - Verify Home Loaded
    @Test(priority = 2)
    public void verifyHomePageLoaded() {
        Assert.assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed());
    }

    // 3 - Add Products
    @Test(priority = 3)
    public void addProductsToCart() throws InterruptedException {
        slowClick(By.id("add-to-cart-sauce-labs-backpack"));
        slowClick(By.id("add-to-cart-sauce-labs-bike-light"));
    }

    // 4 - Verify Badge Count
    @Test(priority = 4)
    public void verifyCartBadgeCount() {
        String badge = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(badge, "2");
    }

    // 5 - Open Cart
    @Test(priority = 5)
    public void openCartPage() throws InterruptedException {
        slowClick(By.className("shopping_cart_link"));
        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
    }

    // 6 - Verify Cart Items Details
    @Test(priority = 6)
    public void verifyCartItemsDetails() {
        List<WebElement> items = driver.findElements(By.className("cart_item"));
        Assert.assertEquals(items.size(), 2);
    }

    // 7 - Verify Cart Buttons
    @Test(priority = 7)
    public void verifyCartButtons() {
        Assert.assertTrue(driver.findElement(By.id("checkout")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("continue-shopping")).isDisplayed());
    }

    // 8 - Continue Shopping
    @Test(priority = 8)
    public void continueShoppingFlow() throws InterruptedException {
        slowClick(By.id("continue-shopping"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    // 9 - Verify Products Still Exist
    @Test(priority = 9)
    public void verifyCartStillHasItems() {
        String badge = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(badge, "2");
    }

    // 10 - Open Product Details
    @Test(priority = 10)
    public void openProductDetails() throws InterruptedException {
        slowClick(By.className("inventory_item_name"));
        Assert.assertTrue(driver.findElement(By.id("back-to-products")).isDisplayed());
    }

    // 11 - Back To Products
    @Test(priority = 11)
    public void backToCart() throws InterruptedException {
        slowClick(By.id("back-to-products"));
        slowClick(By.className("shopping_cart_link"));
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
    }

    // 12 - Go To Checkout
    @Test(priority = 12)
    public void proceedToCheckout() throws InterruptedException {
        slowClick(By.id("checkout"));
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"));
    }

    // 13 - Back To Cart Again
    @Test(priority = 13)
    public void returnToCartFromCheckout() throws InterruptedException {
        slowClick(By.id("cancel"));
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
    }

    // 14 - Remove Product (آخر خطوة)
    @Test(priority = 14)
    public void removeProductAndVerifyEmptyCart() throws InterruptedException {
        slowClick(By.id("remove-sauce-labs-backpack"));
        slowClick(By.id("remove-sauce-labs-bike-light"));

        boolean empty = driver.findElements(By.className("cart_item")).isEmpty();
        Assert.assertTrue(empty);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    // -------- Helper Methods --------

    public void slowClick(By locator) throws InterruptedException {
        driver.findElement(locator).click();
        Thread.sleep(1000);
    }

    public void slowSendKeys(By locator, String text) throws InterruptedException {
        for(char c : text.toCharArray()) {
            driver.findElement(locator).sendKeys(String.valueOf(c));
            Thread.sleep(100);
        }
    }
}
