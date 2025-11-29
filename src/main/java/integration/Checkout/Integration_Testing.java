package Integration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
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
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
    }


    //Verify successful  Removing products from cart  with username "standard_user"
    @Test (priority = 1 )
    public void Remove_cart()   {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        WebElement RemoveBtn1 = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Assert.assertEquals(RemoveBtn1.getText(),"Remove");
        RemoveBtn1.click();
        WebElement RemoveBtn2 = driver.findElement(By.id("remove-sauce-labs-bike-light"));
        Assert.assertEquals(RemoveBtn2.getText(),"Remove");
        RemoveBtn2.click();

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-two.html");
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(count.getText(),"1");
    }


    //Verify successful removing products from cart product description with username "standard_user"
    @Test (priority = 2 )
    public void Remove_Description()   {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.className("inventory_item_name")).click();
        WebElement RemoveBtn = driver.findElement(By.id("remove"));
        Assert.assertEquals(RemoveBtn.getText(),"Remove");
        RemoveBtn.click();
        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");

        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-two.html");
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(count.getText(),"1");
    }


    //verify that once a product is added to the cart Page and then removed, access to the Checkout page is disabled with username "standard_user"
    @Test (priority = 3)
    public void checkout_close ()   {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        driver.findElement(By.id("remove-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("checkout")).click();

        String Checkout_URL = driver.getCurrentUrl();
        Assert.assertNotEquals(Checkout_URL,"https://www.saucedemo.com/checkout-step-one.html");
    }


    //verify that once a product is added to the cart Page and then removed from checkout Overview description page, it removed from cart, with username "standard_user"
    @Test (priority =  4)
    public void product_removed ()   {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();

        driver.findElement(By.className("inventory_item_name")).click();
        driver.findElement(By.id("remove")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        WebElement Backpack = driver.findElement(By.className("inventory_item_name"));
        Assert.assertNotEquals(Backpack.getText(),"inventory_item_name");
    }


    //verify that number of products updated when remove product from checkout overview description page with username "standard_user"
    @Test (priority = 5)
    public void cart_count ()   {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();

        driver.findElement(By.className("inventory_item_name")).click();
        driver.findElement(By.id("remove")).click();
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(count.getText(),"2");
    }


    //verify that number of products updated when remove product from cart description page with username "standard_user"
    @Test (priority = 6)
    public void cart_count2 ()   {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.className("inventory_item_name")).click();
        driver.findElement(By.id("remove")).click();
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(count.getText(),"2");
    }


    //verify that total price updated after removing products with username "standard_user"
    @Test (priority = 7)
    public void update_price ()   {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();

        driver.findElement(By.className("inventory_item_name")).click();
        driver.findElement(By.id("remove")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();

        WebElement price = driver.findElement(By.className("summary_total_label"));
        Assert.assertNotEquals(price.getText(),"Total: $103.65");
    }


    //verify that cart empty after checkout process complete with username "standard_user"
    @Test (priority = 8)
    public void cart_empty()   {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();

        driver.findElement(By.id("finish")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
    }


    //Verify successful  Removing products from cart  with username: locked_out_user
    @Test (priority = 9 )
    public void Remove_cart2()   {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        WebElement RemoveBtn1 = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Assert.assertEquals(RemoveBtn1.getText(),"Remove");
        RemoveBtn1.click();
        WebElement RemoveBtn2 = driver.findElement(By.id("remove-sauce-labs-bike-light"));
        Assert.assertEquals(RemoveBtn2.getText(),"Remove");
        RemoveBtn2.click();

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-two.html");
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(count.getText(),"1");
    }


    //Verify successful  Removing products from cart  with username: problem_user
    @Test (priority = 10 )
    public void Remove_cart3()   {
        driver.findElement(By.id("user-name")).sendKeys("problem_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        WebElement RemoveBtn1 = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Assert.assertEquals(RemoveBtn1.getText(),"Remove");
        RemoveBtn1.click();

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();

        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-two.html");
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(count.getText(),"1");
    }


    //Verify successful  Removing products from cart  with username: performance_glitch_user
    @Test (priority = 11 )
    public void Remove_cart4()   {
        driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        WebElement RemoveBtn1 = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Assert.assertEquals(RemoveBtn1.getText(),"Remove");
        RemoveBtn1.click();
        WebElement RemoveBtn2 = driver.findElement(By.id("remove-sauce-labs-bike-light"));
        Assert.assertEquals(RemoveBtn2.getText(),"Remove");
        RemoveBtn2.click();

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();

        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-two.html");
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(count.getText(),"1");
    }


    //Verify successful  Removing products from cart  with username: error_user
    @Test (priority = 12 )
    public void Remove_cart5()   {
        driver.findElement(By.id("user-name")).sendKeys("error_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        WebElement RemoveBtn1 = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Assert.assertEquals(RemoveBtn1.getText(),"Remove");
        RemoveBtn1.click();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();

        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-two.html");
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(count.getText(),"1");
    }


    //Verify successful  Removing products from cart  with username: visual_user
    @Test (priority = 13 )
    public void Remove_cart6()   {
        driver.findElement(By.id("user-name")).sendKeys("visual_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        WebElement RemoveBtn1 = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Assert.assertEquals(RemoveBtn1.getText(),"Remove");
        RemoveBtn1.click();

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();

        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-two.html");
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(count.getText(),"1");
    }


    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}