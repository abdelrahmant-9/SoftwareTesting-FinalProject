package HomePage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class End_to_End {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() throws InterruptedException {

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
        wait = new WebDriverWait(driver , Duration.ofSeconds(5));



        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        Thread.sleep(1000);

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);

        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);

        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
    }

    @Test(priority = 1)
    public void check_flow() throws InterruptedException
    {
         driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
         Thread.sleep(1000);
         driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
         Thread.sleep(1000);

        WebElement icon = driver.findElement(By.className("shopping_cart_link"));
        icon.click();
         Thread.sleep(1000);

          List <WebElement> products = driver.findElements(By.className("cart_list"));
         Assert.assertFalse(products.isEmpty());
         driver.findElement(By.id("checkout")).click();

        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("moahmed");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("akram");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("12445");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("finish")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("back-to-products")).click();
        Thread.sleep(1000);

    }

    @Test(priority = 2)
    public void check_flow2() throws InterruptedException
    {
        driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("back-to-products")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);

        WebElement icon = driver.findElement(By.className("shopping_cart_link"));

        icon.click();
        Thread.sleep(1000);

        List <WebElement> products = driver.findElements(By.className("cart_list"));
        Assert.assertFalse(products.isEmpty());
        driver.findElement(By.id("checkout")).click();

        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("moahmed");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("akram");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("12445");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("finish")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("back-to-products")).click();
        Thread.sleep(1000);

    }


    @Test(priority = 3)
    public void check_flow3() throws InterruptedException
    {
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();

        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        Thread.sleep(1000);

        WebElement icon = driver.findElement(By.className("shopping_cart_link"));
        icon.click();
        Thread.sleep(1000);

        List <WebElement> products = driver.findElements(By.className("cart_list"));
        driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("remove")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("back-to-products")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);

        Assert.assertFalse(products.isEmpty());
        driver.findElement(By.id("checkout")).click();

        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("moahmed");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("akram");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("12445");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("finish")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("back-to-products")).click();
        Thread.sleep(1000);

    }

    @Test(priority = 4)
    public void check_flow4() throws InterruptedException
    {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);

        WebElement icon = driver.findElement(By.className("shopping_cart_link"));
        icon.click();
        Thread.sleep(1000);

        List <WebElement> products = driver.findElements(By.className("cart_list"));
        Assert.assertFalse(products.isEmpty());
        driver.findElement(By.id("checkout")).click();

        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("tamer");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("tito");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("55555");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("react-burger-menu-btn")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("inventory_sidebar_link")));
        driver.findElement(By.id("inventory_sidebar_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        Thread.sleep(1000);

         driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("tamer");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("tito");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("55555");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("finish")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("back-to-products")).click();
        Thread.sleep(1000);

    }

    @Test(priority = 5)
    public void check_flow5() throws InterruptedException
    {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);

        WebElement icon = driver.findElement(By.className("shopping_cart_link"));
        icon.click();
        Thread.sleep(1000);

        List <WebElement> products = driver.findElements(By.className("cart_list"));
        Assert.assertFalse(products.isEmpty());
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);

        WebElement first = driver.findElement(By.id("first-name"));
        first.sendKeys("   ");
        Thread.sleep(1000);

        WebElement last = driver.findElement(By.id("last-name"));
        last.sendKeys("   ");
        Thread.sleep(1000);

        WebElement ZIP = driver.findElement(By.id("postal-code"));
        ZIP.sendKeys("   ");
        Thread.sleep(1000);

        WebElement BTN = driver.findElement(By.id("continue"));
        Assert.assertFalse(BTN.isEnabled());
        Thread.sleep(1000);
    }
    @Test(priority = 6)
    public void check_flow6() throws InterruptedException
    {
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        Thread.sleep(1000);

        WebElement icon = driver.findElement(By.className("shopping_cart_link"));
        icon.click();
        Thread.sleep(1000);

        driver.findElement(By.id("remove-sauce-labs-onesie")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("remove-test.allthethings()-t-shirt-(red)")).click();
        Thread.sleep(1000);

        String cartURL = driver.getCurrentUrl();
        driver.findElement(By.id("checkout")).click();

        String Url = driver.getCurrentUrl();
        Assert.assertEquals(cartURL , Url);

    }

    @AfterMethod
    public void close ()throws  InterruptedException
    {
        Thread.sleep(1000);
        driver.close();
    }

}
