package Unit.RemainingPages;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Cart {

    WebDriver driver;

    public void clickWithDelay(By locator) throws InterruptedException {
        driver.findElement(locator).click();
        Thread.sleep(500);
    }

    public void sendKeysWithDelay(By locator, String text) throws InterruptedException {
        for (char c : text.toCharArray()) {
            driver.findElement(locator).sendKeys(String.valueOf(c));
            Thread.sleep(50);
        }
    }

    public void shortPause() throws InterruptedException {
        Thread.sleep(1000);
    }

    @BeforeClass
    public void setup() {
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
    }

    @Test(priority = 1)
    public void Login() throws InterruptedException {
        sendKeysWithDelay(By.cssSelector("[data-test='username']"), "standard_user");
        sendKeysWithDelay(By.cssSelector("[data-test='password']"), "secret_sauce");
        clickWithDelay(By.cssSelector("[data-test='login-button']"));
        shortPause();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test(priority = 2)
    public void CartBadgeCounter() throws InterruptedException {
        clickWithDelay(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"));
        clickWithDelay(By.cssSelector("[data-test='add-to-cart-sauce-labs-bike-light']"));
        clickWithDelay(By.cssSelector("[data-test='add-to-cart-sauce-labs-bolt-t-shirt']"));
        shortPause();

        String badge = driver.findElement(By.cssSelector("[data-test='shopping-cart-badge']")).getText();
        Assert.assertEquals(badge, "3");

        shortPause();
        clickWithDelay(By.cssSelector("[data-test='remove-sauce-labs-bike-light']"));
        clickWithDelay(By.cssSelector("[data-test='remove-sauce-labs-bolt-t-shirt']"));
        shortPause();

        badge = driver.findElement(By.cssSelector("[data-test='shopping-cart-badge']")).getText();
        Assert.assertEquals(badge, "1");
        shortPause();
    }

    @Test(priority = 6)
    public void OpenCart() throws InterruptedException {
        clickWithDelay(By.cssSelector("[data-test='shopping-cart-link']"));
        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
        shortPause();
    }

    @Test(priority = 7)
    public void ButtonsInCart() {
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='remove-sauce-labs-backpack']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='continue-shopping']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='checkout']")).isDisplayed());
    }

    @Test(priority = 8)
    public void ContinueShopping() throws InterruptedException {
        clickWithDelay(By.cssSelector("[data-test='continue-shopping']"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        shortPause();

        clickWithDelay(By.cssSelector("[data-test='shopping-cart-link']"));
        shortPause();
    }

    @Test(priority = 9)
    public void Checkout() throws InterruptedException {
        clickWithDelay(By.cssSelector("[data-test='checkout']"));
        shortPause();

        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='cancel']")).isDisplayed());
        clickWithDelay(By.cssSelector("[data-test='cancel']"));

        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
        shortPause();
    }

    @Test(priority = 10)
    public void ProductDescription() throws InterruptedException {
        clickWithDelay(By.cssSelector("[data-test='inventory-item-name']"));
        shortPause();

        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='back-to-products']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='remove']")).isDisplayed());

        clickWithDelay(By.cssSelector("[data-test='back-to-products']"));
        shortPause();
    }

    @Test(priority = 11)
    public void RemoveProduct() throws InterruptedException {
        clickWithDelay(By.cssSelector("[data-test='shopping-cart-link']"));
        shortPause();

        clickWithDelay(By.cssSelector("[data-test='remove-sauce-labs-backpack']"));
        shortPause();

        boolean empty = driver.findElements(By.className("cart_item")).isEmpty();
        Assert.assertTrue(empty);
    }

    @AfterClass
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
