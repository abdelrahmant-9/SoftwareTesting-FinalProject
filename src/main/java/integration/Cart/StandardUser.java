package integration.Cart;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;
import org.testng.annotations.*;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
    }

    // 1️⃣ Login
    @Test(priority = 1)
    public void loginSuccessfully() {
        sendKeys(By.cssSelector("[data-test='username']"), "standard_user");
        sendKeys(By.cssSelector("[data-test='password']"), "secret_sauce");
        click(By.cssSelector("[data-test='login-button']"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login failed!");
    }

    // 2️⃣ Add single item
    @Test(priority = 2, dependsOnMethods = "loginSuccessfully")
    public void addSingleItemToCart() {
        click(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"));
        Assert.assertEquals(getText(By.cssSelector(".shopping_cart_badge")), "1");
    }

    // 3️⃣ Add multiple items + remove one
    @Test(priority = 3, dependsOnMethods = "addSingleItemToCart")
    public void addMultipleItemsAndRemoveOne() {
        click(By.cssSelector("[data-test='add-to-cart-sauce-labs-bike-light']"));
        click(By.cssSelector("[data-test='add-to-cart-sauce-labs-bolt-t-shirt']"));
        Assert.assertEquals(getText(By.cssSelector(".shopping_cart_badge")), "3");

        click(By.cssSelector("[data-test='remove-sauce-labs-bike-light']"));
        Assert.assertEquals(getText(By.cssSelector(".shopping_cart_badge")), "2");
    }

    // 4️⃣ Navigate to cart
    @Test(priority = 4)
    public void navigateToCart() {
        click(By.cssSelector(".shopping_cart_link"));
        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
    }

    // 5️⃣ Proceed to checkout with valid data
    @Test(priority = 5, dependsOnMethods = "navigateToCart")
    public void completeCheckoutSuccessfully() {
        click(By.cssSelector("[data-test='checkout']"));
        sendKeys(By.cssSelector("[data-test='firstName']"), "QA");
        sendKeys(By.cssSelector("[data-test='lastName']"), "Tester");
        sendKeys(By.cssSelector("[data-test='postalCode']"), "12345");
        click(By.cssSelector("[data-test='continue']"));

        Assert.assertTrue(getText(By.cssSelector(".summary_total_label")).contains("Total"));
        click(By.cssSelector("[data-test='finish']"));
        Assert.assertTrue(getText(By.cssSelector(".complete-header")).contains("THANK YOU"));
    }

    // 6️⃣ Checkout with missing data
    @Test(priority = 6)
    public void checkoutWithMissingData() {
        driver.get("https://www.saucedemo.com/inventory.html");
        click(By.cssSelector("[data-test='add-to-cart-sauce-labs-fleece-jacket']"));
        click(By.cssSelector(".shopping_cart_link"));
        click(By.cssSelector("[data-test='checkout']"));
        click(By.cssSelector("[data-test='continue']"));
        Assert.assertTrue(getText(By.cssSelector("[data-test='error']")).contains("Error"));
    }

    // 7️⃣ Logout scenario
    @Test(priority = 7)
    public void logoutSuccessfully() {
        click(By.id("react-burger-menu-btn"));
        click(By.id("logout_sidebar_link"));
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    // 🔹 Helper methods
    private void click(By locator) {
        driver.findElement(locator).click();
    }

    private void sendKeys(By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    private String getText(By locator) {
        return driver.findElement(locator).getText();
    }
}
