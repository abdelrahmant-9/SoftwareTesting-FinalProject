package Testcases;

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

public class Checkout {
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

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);

        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);

        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
    }


    //verify that checkout information page open
    @Test (priority = 1)
    public void checkout_IformationPage_open() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
       Thread.sleep(1000);
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-one.html");
    }


    //enter valid inputs at checkout information page fields
        @Test (priority = 2)
    public void valid_inputs() throws InterruptedException {
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
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-two.html");
    }


    //leave checkout information page fields empty
    @Test (priority = 3)
    public void fields_empty() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("");
        driver.findElement(By.id("last-name")).sendKeys("");
        driver.findElement(By.id("postal-code")).sendKeys("");
        Thread.sleep(2000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-one.html");
    }


    //don't select products verify that checkout don't open
    @Test (priority = 5)
    public void donot_select_products() throws InterruptedException {
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertNotEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-one.html");
    }


    //enter only spaces at checkout information page fields
    @Test (priority = 4)
    public void only_spaces_FirstName() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("             ");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("              ");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("            ");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-step-one.html");
    }


    // verify that DescriptionPage open
    @Test (priority = 6)
    public void DescriptionPage_open() throws InterruptedException {
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
        Thread.sleep(2000);
        driver.findElement(By.className("inventory_item_name")).click();
        Thread.sleep(1000);
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/inventory-item.html?id=4");
    }


    //verify remove button is active in DescriptionPage open
    @Test (priority = 7)
    public void Remove_button() throws InterruptedException {
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
        driver.findElement(By.className("inventory_item_name")).click();
        Thread.sleep(1000);
        WebElement removeBtn =  driver.findElement(By.id("remove"));
        Thread.sleep(1000);
        Assert.assertEquals(removeBtn.getText(),"Remove");
        Thread.sleep(1000);
        removeBtn.click();
        Thread.sleep(1000);
    }


    //verify Add button is active in DescriptionPage open
    @Test (priority = 8)
    public void Add_products() throws InterruptedException {
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
        driver.findElement(By.className("inventory_item_name")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("remove")).click();
        Thread.sleep(1000);
        WebElement AddBtn = driver.findElement(By.id("add-to-cart"));
        Thread.sleep(1000);
        Assert.assertEquals(AddBtn.getText(),"Add to cart");
        Thread.sleep(1000);
        AddBtn.click();
        Thread.sleep(1000);
    }


    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}