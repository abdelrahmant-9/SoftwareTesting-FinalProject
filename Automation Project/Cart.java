package Testcases;

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

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments(new String[]{"--disable-save-password-bubble"});
        options.addArguments(new String[]{"--disable-notifications"});
        options.addArguments(new String[]{"--disable-infobars"});
        this.driver = new ChromeDriver(options);
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3L));
        this.driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test(
            priority = 1
    )
    public void Login() throws InterruptedException {
        this.slowSendKeys(By.cssSelector("[data-test='username']"), "standard_user");
        this.slowSendKeys(By.cssSelector("[data-test='password']"), "secret_sauce");
        this.slowClick(By.cssSelector("[data-test='login-button']"));
        this.waitSlow();
        Assert.assertTrue(this.driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test(
            priority = 2
    )
    public void CartBadgeCounter() throws InterruptedException {
        this.slowClick(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"));
        this.slowClick(By.cssSelector("[data-test='add-to-cart-sauce-labs-bike-light']"));
        this.slowClick(By.cssSelector("[data-test='add-to-cart-sauce-labs-bolt-t-shirt']"));
        this.waitSlow();
        String badge = this.driver.findElement(By.cssSelector("[data-test='shopping-cart-badge']")).getText();
        Assert.assertEquals(badge, "3");
        this.waitSlow();
        this.slowClick(By.cssSelector("[data-test='remove-sauce-labs-bike-light']"));
        this.slowClick(By.cssSelector("[data-test='remove-sauce-labs-bolt-t-shirt']"));
        this.waitSlow();
        badge = this.driver.findElement(By.cssSelector("[data-test='shopping-cart-badge']")).getText();
        Assert.assertEquals(badge, "1");
        this.waitSlow();
    }

    @Test(
            priority = 6
    )
    public void OpenCart() throws InterruptedException {
        this.slowClick(By.cssSelector("[data-test='shopping-cart-link']"));
        Assert.assertTrue(this.driver.getCurrentUrl().contains("cart.html"));
        this.waitSlow();
    }

    @Test(
            priority = 7
    )
    public void ButtonsInCart() {
        Assert.assertTrue(this.driver.findElement(By.cssSelector("[data-test='remove-sauce-labs-backpack']")).isDisplayed());
        Assert.assertTrue(this.driver.findElement(By.cssSelector("[data-test='continue-shopping']")).isDisplayed());
        Assert.assertTrue(this.driver.findElement(By.cssSelector("[data-test='checkout']")).isDisplayed());
    }

    @Test(
            priority = 8
    )
    public void ContinueShopping() throws InterruptedException {
        this.slowClick(By.cssSelector("[data-test='continue-shopping']"));
        Assert.assertTrue(this.driver.getCurrentUrl().contains("inventory.html"));
        this.waitSlow();
        this.slowClick(By.cssSelector("[data-test='shopping-cart-link']"));
        this.waitSlow();
    }

    @Test(
            priority = 9
    )
    public void Checkout() throws InterruptedException {
        this.slowClick(By.cssSelector("[data-test='checkout']"));
        this.waitSlow();
        Assert.assertTrue(this.driver.findElement(By.cssSelector("[data-test='cancel']")).isDisplayed());
        this.slowClick(By.cssSelector("[data-test='cancel']"));
        Assert.assertTrue(this.driver.getCurrentUrl().contains("cart.html"));
        this.waitSlow();
    }

    @Test(
            priority = 10
    )
    public void ProductDescription() throws InterruptedException {
        this.slowClick(By.cssSelector("[data-test='inventory-item-name']"));
        this.waitSlow();
        Assert.assertTrue(this.driver.findElement(By.cssSelector("[data-test='back-to-products']")).isDisplayed());
        Assert.assertTrue(this.driver.findElement(By.cssSelector("[data-test='remove']")).isDisplayed());
        this.slowClick(By.cssSelector("[data-test='back-to-products']"));
        this.waitSlow();
    }

    @Test(
            priority = 11
    )
    public void RemoveProduct() throws InterruptedException {
        this.slowClick(By.cssSelector("[data-test='shopping-cart-link']"));
        this.waitSlow();
        this.slowClick(By.cssSelector("[data-test='remove-sauce-labs-backpack']"));
        this.waitSlow();
        boolean empty = this.driver.findElements(By.className("cart_item")).isEmpty();
        Assert.assertTrue(empty);
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

