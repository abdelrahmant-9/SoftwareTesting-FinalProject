package E2E;

import org.openqa.selenium.By;
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

public class E2E {

    WebDriver driver;
    WebDriverWait wait;
    int expectedCartCount = 0;

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickWhenReady(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void smallWait() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void openBrowser() {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://www.saucedemo.com/");
        smallWait();

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        clickWhenReady(By.id("login-button"));
    }

    @Test(priority = 1, groups = {"e2e"})
    public void CartShouldBeEmptyAtStart() {
        boolean result;
        try {
            smallWait();
            boolean badgeExists = driver.findElements(By.className("shopping_cart_badge")).size() > 0;
            Assert.assertFalse(badgeExists);
            result = true;
        } catch (AssertionError e) {
            result = false;
            Assert.fail("CartShouldBeEmptyAtStart failed: " + e.getMessage());
        }
        System.out.println("CartShouldBeEmptyAtStart Result = " + result);
    }

    @Test(priority = 2, groups = {"e2e"})
    public void AddFirstProduct() {
        boolean result;
        try {
            smallWait();
            clickWhenReady(By.id("add-to-cart-sauce-labs-backpack"));
            expectedCartCount++;

            waitForElement(By.className("shopping_cart_badge"));
            WebElement badge = driver.findElement(By.className("shopping_cart_badge"));

            Assert.assertEquals(badge.getText(), String.valueOf(expectedCartCount));
            result = true;
        } catch (AssertionError e) {
            result = false;
            Assert.fail("AddFirstProduct failed: " + e.getMessage());
        }
        System.out.println("AddFirstProduct Result = " + result);
    }

    @Test(priority = 3, groups = {"e2e"})
    public void ViewProductDescription() {

        boolean result;

        try {
            clickWhenReady(By.linkText("Sauce Labs Backpack"));

            waitForElement(By.className("inventory_details_name"));
            waitForElement(By.className("inventory_details_desc"));
            waitForElement(By.className("inventory_details_price"));

            String name = driver.findElement(By.className("inventory_details_name")).getText();
            String desc = driver.findElement(By.className("inventory_details_desc")).getText();
            String price = driver.findElement(By.className("inventory_details_price")).getText();

            Assert.assertFalse(name.isEmpty());
            Assert.assertFalse(desc.isEmpty());
            Assert.assertTrue(price.contains("$"));

            clickWhenReady(By.id("back-to-products"));

            result = true;

        } catch (AssertionError e) {
            result = false;
            Assert.fail("ViewProductDescription failed: " + e.getMessage());
        }

        System.out.println("ViewProductDescription Result = " + result);
    }

    @Test(priority = 4, groups = {"e2e"})
    public void AddMoreProducts() {
        boolean result;
        try {
            smallWait();
            clickWhenReady(By.id("add-to-cart-sauce-labs-bike-light"));
            expectedCartCount++;

            smallWait();
            clickWhenReady(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
            expectedCartCount++;

            WebElement badge = driver.findElement(By.className("shopping_cart_badge"));
            Assert.assertEquals(badge.getText(), String.valueOf(expectedCartCount));
            result = true;
        } catch (AssertionError e) {
            result = false;
            Assert.fail("AddMoreProducts failed: " + e.getMessage());
        }
        System.out.println("AddMoreProducts Result = " + result);
    }

    @Test(priority = 5, groups = {"e2e"})
    public void GoToCartAndValidateItems() {
        boolean result;
        try {
            clickWhenReady(By.className("shopping_cart_link"));
            waitForElement(By.linkText("Sauce Labs Backpack"));

            Assert.assertTrue(driver.findElement(By.linkText("Sauce Labs Backpack")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.linkText("Sauce Labs Bike Light")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.linkText("Sauce Labs Bolt T-Shirt")).isDisplayed());

            result = true;
        } catch (AssertionError e) {
            result = false;
            Assert.fail("GoToCartAndValidateItems failed: " + e.getMessage());
        }
        System.out.println("GoToCartAndValidateItems Result = " + result);
    }

    @Test(priority = 6, groups = {"e2e"})
    public void RemoveItemAndValidateCount() {
        boolean result;
        try {
            clickWhenReady(By.id("remove-sauce-labs-bike-light"));
            expectedCartCount--;

            WebElement badge = driver.findElement(By.className("shopping_cart_badge"));
            Assert.assertEquals(badge.getText(), String.valueOf(expectedCartCount));
            result = true;
        } catch (AssertionError e) {
            result = false;
            Assert.fail("RemoveItemAndValidateCount failed: " + e.getMessage());
        }
        System.out.println("RemoveItemAndValidateCount Result = " + result);
    }

    @Test(priority = 7, groups = {"e2e"})
    public void ProceedToCheckout() {
        boolean result;
        try {
            clickWhenReady(By.id("checkout"));
            WebElement title = driver.findElement(By.className("title"));
            Assert.assertEquals(title.getText(), "Checkout: Your Information");
            result = true;
        } catch (AssertionError e) {
            result = false;
            Assert.fail("ProceedToCheckout failed: " + e.getMessage());
        }
        System.out.println("ProceedToCheckout Result = " + result);
    }

    @Test(priority = 8, groups = {"e2e"})
    public void CompleteCheckout() {
        boolean result;
        try {
            driver.findElement(By.id("first-name")).sendKeys("Abdelrahman");
            driver.findElement(By.id("last-name")).sendKeys("Tarek");
            driver.findElement(By.id("postal-code")).sendKeys("35111");

            clickWhenReady(By.id("continue"));
            clickWhenReady(By.id("finish"));

            Assert.assertTrue(driver.getCurrentUrl().contains("checkout-complete"));
            result = true;
        } catch (AssertionError e) {
            result = false;
            Assert.fail("CompleteCheckout failed: " + e.getMessage());
        }
        System.out.println("CompleteCheckout Result = " + result);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}