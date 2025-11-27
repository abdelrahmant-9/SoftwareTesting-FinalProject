package integration.Checkout;

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
public class Integration_Testing {
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


    //Verify successful  Removing products from cart  with username "standard_user"
    @Test (priority = 1 )
    public void Remove_cart() throws InterruptedException {
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
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);

        WebElement RemoveBtn1 = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Thread.sleep(1000);
        Assert.assertEquals(RemoveBtn1.getText(),"Remove");
        Thread.sleep(1000);
        RemoveBtn1.click();
        Thread.sleep(1000);
        WebElement RemoveBtn2 = driver.findElement(By.id("remove-sauce-labs-bike-light"));
        Thread.sleep(1000);
        Assert.assertEquals(RemoveBtn2.getText(),"Remove");
        Thread.sleep(1000);
        RemoveBtn2.click();
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
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-two.html");
        Thread.sleep(1000);
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Thread.sleep(1000);
        Assert.assertEquals(count.getText(),"1");
    }


    //Verify successful removing products from cart product description with username "standard_user"
    @Test (priority = 2 )
    public void Remove_Description() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);

        driver.findElement(By.className("inventory_item_name")).click();
        Thread.sleep(1000);
        WebElement RemoveBtn = driver.findElement(By.id("remove"));
        Thread.sleep(1000);
        Assert.assertEquals(RemoveBtn.getText(),"Remove");
        Thread.sleep(1000);
        RemoveBtn.click();
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
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-two.html");
        Thread.sleep(1000);
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Thread.sleep(1000);
        Assert.assertEquals(count.getText(),"1");
    }


    //verify that once a product is added to the cart Page and then removed, access to the Checkout page is disabled with username "standard_user"
    @Test (priority = 3)
    public void checkout_close () throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("remove-sauce-labs-fleece-jacket")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);

        String Checkout_URL = driver.getCurrentUrl();
        Assert.assertNotEquals(Checkout_URL,"https://www.saucedemo.com/checkout-step-one.html");
    }


    //verify that once a product is added to the cart Page and then removed from checkout Overview description page, it removed from cart, with username "standard_user"
    @Test (priority =  4)
    public void product_removed () throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
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
        Thread.sleep(1000);

        driver.findElement(By.className("inventory_item_name")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("remove")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        WebElement Backpack = driver.findElement(By.className("inventory_item_name"));
        Thread.sleep(1000);
        Assert.assertNotEquals(Backpack.getText(),"inventory_item_name");
    }


    //verify that number of products updated when remove product from checkout overview description page with username "standard_user"
    @Test (priority = 5)
    public void cart_count () throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
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
        Thread.sleep(1000);

        driver.findElement(By.className("inventory_item_name")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("remove")).click();
        Thread.sleep(1000);
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Thread.sleep(1000);
        Assert.assertEquals(count.getText(),"2");
    }


    //verify that number of products updated when remove product from cart description page with username "standard_user"
    @Test (priority = 6)
    public void cart_count2 () throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);

        driver.findElement(By.className("inventory_item_name")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("remove")).click();
        Thread.sleep(1000);
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Thread.sleep(1000);
        Assert.assertEquals(count.getText(),"2");
    }


    //verify that total price updated after removing products with username "standard_user"
    @Test (priority = 7)
    public void update_price () throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
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
        Thread.sleep(1000);

        driver.findElement(By.className("inventory_item_name")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("remove")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1500);

        WebElement price = driver.findElement(By.className("summary_total_label"));
        Thread.sleep(1000);
        Assert.assertNotEquals(price.getText(),"Total: $103.65");
    }


    //verify that cart empty after checkout process complete with username "standard_user"
    @Test (priority = 8)
    public void cart_empty() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
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
        Thread.sleep(1000);

        driver.findElement(By.id("finish")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
    }


    //Verify successful  Removing products from cart  with username: locked_out_user
    @Test (priority = 9 )
    public void Remove_cart2() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);

        WebElement RemoveBtn1 = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Thread.sleep(1000);
        Assert.assertEquals(RemoveBtn1.getText(),"Remove");
        Thread.sleep(1000);
        RemoveBtn1.click();
        Thread.sleep(1000);
        WebElement RemoveBtn2 = driver.findElement(By.id("remove-sauce-labs-bike-light"));
        Thread.sleep(1000);
        Assert.assertEquals(RemoveBtn2.getText(),"Remove");
        Thread.sleep(1000);
        RemoveBtn2.click();
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
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-two.html");
        Thread.sleep(1000);
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Thread.sleep(1000);
        Assert.assertEquals(count.getText(),"1");
    }


    //Verify successful  Removing products from cart  with username: problem_user
    @Test (priority = 10 )
    public void Remove_cart3() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("problem_user");
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

        WebElement RemoveBtn1 = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Thread.sleep(1000);
        Assert.assertEquals(RemoveBtn1.getText(),"Remove");
        Thread.sleep(1000);
        RemoveBtn1.click();
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
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-two.html");
        Thread.sleep(1000);
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Thread.sleep(1000);
        Assert.assertEquals(count.getText(),"1");
    }


    //Verify successful  Removing products from cart  with username: performance_glitch_user
    @Test (priority = 11 )
    public void Remove_cart4() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);

        WebElement RemoveBtn1 = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Thread.sleep(1000);
        Assert.assertEquals(RemoveBtn1.getText(),"Remove");
        Thread.sleep(1000);
        RemoveBtn1.click();
        Thread.sleep(1000);
        WebElement RemoveBtn2 = driver.findElement(By.id("remove-sauce-labs-bike-light"));
        Thread.sleep(1000);
        Assert.assertEquals(RemoveBtn2.getText(),"Remove");
        Thread.sleep(1000);
        RemoveBtn2.click();
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
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-two.html");
        Thread.sleep(1000);
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Thread.sleep(1000);
        Assert.assertEquals(count.getText(),"1");
    }


    //Verify successful  Removing products from cart  with username: error_user
    @Test (priority = 12 )
    public void Remove_cart5() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("error_user");
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

        WebElement RemoveBtn1 = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Thread.sleep(1000);
        Assert.assertEquals(RemoveBtn1.getText(),"Remove");
        Thread.sleep(1000);
        RemoveBtn1.click();
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

        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-two.html");
        Thread.sleep(1000);
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Thread.sleep(1000);
        Assert.assertEquals(count.getText(),"1");
    }


    //Verify successful  Removing products from cart  with username: visual_user
    @Test (priority = 13 )
    public void Remove_cart6() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("visual_user");
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

        WebElement RemoveBtn1 = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Thread.sleep(1000);
        Assert.assertEquals(RemoveBtn1.getText(),"Remove");
        Thread.sleep(1000);
        RemoveBtn1.click();
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

        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-two.html");
        Thread.sleep(1000);
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Thread.sleep(1000);
        Assert.assertEquals(count.getText(),"1");
    }


    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}