package E2E;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

public class EndToEnd {
    WebDriver driver ;

    @BeforeMethod
    public void  openBrowser() throws InterruptedException {
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

        driver = new ChromeDriver(options);
        driver.navigate().to("https://www.saucedemo.com/");
    }

    //verify that checkout process complete with username: standard_user
    @Test (priority = 1 )
    public void checkout_process_complete() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("finish")).click();
        Thread.sleep(1000);
       String checkout_URL = driver.getCurrentUrl();
       Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    //verify that checkout process complete with Removing products from cart page username: standard_user
    @Test (priority = 2)
    public void Remove_products() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("finish")).click();
        Thread.sleep(1000);
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    //verify that checkout process complete with Removing products from Description page username: standard_user
    @Test (priority = 3)
    public void Remove_products2() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("inventory_item_name")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("remove")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("back-to-products")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("finish")).click();
        Thread.sleep(1000);
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    //verify that checkout process don't complete with empty cart username: standard_user
    @Test (priority = 4)
    public void empty_cart() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("finish")).click();
        Thread.sleep(2000);
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertNotEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    //verify that checkout process don't complete with entering only spaces at checkout fields username: standard_user
    @Test (priority = 5)
    public void fields_with_space() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("         ");
        Thread.sleep(1500);
        driver.findElement(By.id("last-name")).sendKeys("          ");
        Thread.sleep(1500);
        driver.findElement(By.id("postal-code")).sendKeys("        ");
        Thread.sleep(1500);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("finish")).click();
        Thread.sleep(1000);
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertNotEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    //verify that checkout process don't complete with empty checkout fields username: standard_user
    @Test (priority = 6)
    public void empty_fields() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("");
        Thread.sleep(1500);
        driver.findElement(By.id("last-name")).sendKeys("");
        Thread.sleep(1500);
        driver.findElement(By.id("postal-code")).sendKeys("");
        Thread.sleep(1500);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertNotEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    //verify that checkout process complete with Reset App State username: standard_user
    @Test (priority = 7)
    public void Reset_App_State() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("reset_sidebar_link")).click();
        Thread.sleep(1200);
        WebElement AddBtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        Thread.sleep(1000);
        Assert.assertEquals(AddBtn.getText(), "Add to cart");
        Thread.sleep(1000);
        AddBtn.click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("finish")).click();
    }

    //verify that checkout process complete with username: locked_out_user
    @Test (priority =8)
    public void locked_out_user() throws InterruptedException {
            driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
            Thread.sleep(1000);
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            Thread.sleep(1000);
            driver.findElement(By.id("login-button")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            Thread.sleep(1000);
            driver.findElement(By.className("shopping_cart_link")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("checkout")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("first-name")).sendKeys("Mohamed");
            Thread.sleep(1000);
            driver.findElement(By.id("last-name")).sendKeys("Yasser");
            Thread.sleep(1000);
            driver.findElement(By.id("postal-code")).sendKeys("35934");
            Thread.sleep(1000);
            driver.findElement(By.id("continue")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("finish")).click();
            Thread.sleep(1000);
            String checkout_URL = driver.getCurrentUrl();
            Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
        }

    //verify that checkout process complete with username: problem_user
    @Test (priority =9)
    public void problem_user() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("problem_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("finish")).click();
        Thread.sleep(1000);
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    //verify that checkout process complete with username: performance_glitch_user
    @Test (priority =10)
    public void performance_glitch_user() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("finish")).click();
        Thread.sleep(1000);
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    //verify that checkout process complete with username: error_user
    @Test (priority =11)
    public void error_user() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("error_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("finish")).click();
        Thread.sleep(1000);
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    //verify that checkout process complete with username: visual_user
    @Test (priority =12)
    public void visual_user() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("visual_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("finish")).click();
        Thread.sleep(1000);
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
