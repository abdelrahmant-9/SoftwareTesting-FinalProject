package testcases;

import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class Standar_user {
    private boolean IsElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
    WebDriver driver;
    @BeforeMethod
    public void  openBrowser(){
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
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test(priority = 1)
    public void AllItemsDisplayed (){
        try{
            WebElement AllItems = driver.findElement(By.id("inventory_sidebar_link"));
            driver.findElement(By.id("react-burger-menu-btn")).click();
            Assert.assertTrue(!AllItems.isDisplayed());
        }catch (NoSuchElementException e){
            System.out.println("Not found");
        }
    }
    @Test(priority = 1)
    public void AboutDisplayed (){
        try{
            WebElement About  = driver.findElement(By.id("about_sidebar_link"));
            driver.findElement(By.id("react-burger-menu-btn")).click();
            Assert.assertFalse(About.isDisplayed());
        }catch (NoSuchElementException e){
            System.out.println("Not found");
        }
    }

    @Test(priority = 1)
    public void ResetDisplayed (){
        try{
            WebElement Reset = driver.findElement(By.id("reset_sidebar_link"));
            driver.findElement(By.id("react-burger-menu-btn")).click();
            Assert.assertTrue(!Reset.isDisplayed());
        }catch (NoSuchElementException e){
            System.out.println("Not found");
        }
    }
    @Test(priority = 1)
    public void LogoutDisplayed (){
        try{
            WebElement Logout = driver.findElement(By.id("logout_sidebar_link"));
            driver.findElement(By.id("react-burger-menu-btn")).click();
            Assert.assertTrue(!Logout.isDisplayed());
        }catch (NoSuchElementException e){
            System.out.println("Not found");
        }
    }

    @Test(priority = 1)
    public void enabled() throws InterruptedException {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        WebElement AllItems = driver.findElement(By.id("inventory_sidebar_link"));
        WebElement About  = driver.findElement(By.id("about_sidebar_link"));
        WebElement Reset = driver.findElement(By.id("reset_sidebar_link"));
        WebElement Logout = driver.findElement(By.id("logout_sidebar_link"));
        WebElement exit = driver.findElement(By.id("react-burger-cross-btn"));
        Assert.assertTrue(AllItems.isEnabled());
        Assert.assertTrue(About.isEnabled());
        Assert.assertTrue(Logout.isEnabled());
        Assert.assertTrue(Reset.isEnabled());
        Assert.assertTrue(exit.isEnabled());
    }

    @Test(priority = 1)
    public void TestAllitemsLink() {
        WebElement AllItems = driver.findElement(By.id("inventory_sidebar_link"));
        Assert.assertEquals(AllItems.getAttribute("href"), "https://www.saucedemo.com/inventory.html#");
    }
    @Test(priority = 1)
    public void TestAboutLink() {
        WebElement About  = driver.findElement(By.id("about_sidebar_link"));
        Assert.assertEquals(About.getAttribute("href"), "https://saucelabs.com/");
    }
    @Test(priority = 1)
    public void TestResetLink() {
        WebElement Reset = driver.findElement(By.id("reset_sidebar_link"));
        Assert.assertEquals(Reset.getAttribute("href"), "https://www.saucedemo.com/inventory.html#");
    }
    @Test(priority = 1)
    public void TestLogoutLink() {
        WebElement Logout = driver.findElement(By.id("logout_sidebar_link"));
        Assert.assertEquals(Logout.getAttribute("href"), "https://www.saucedemo.com/");
    }

    @Test(priority = 2 )
    public void clicks() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement b1 = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        WebElement b2 = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        WebElement bar = driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]"));
        Actions builder = new Actions(driver);
        builder.click(b1).click(b2).click(bar).perform();
        Thread.sleep(5000);
        WebElement reset = wait.until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link")));
        reset.click();
        WebElement exit= wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-cross-btn")));
        Thread.sleep(5000);
        exit.click();
        WebElement b = driver.findElement(By.id("remove-sauce-labs-backpack"));
        WebElement b11 = driver.findElement(By.id("remove-sauce-labs-bike-light"));
        Assert.assertEquals(b.getText(),"Add to cart");
        Assert.assertEquals(b11.getText(),"Add to cart");
    }
    @Test
    public void ReseCcart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement reset = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reset App State")));
        reset.click();
            WebElement no = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
            Assert.assertFalse(no.isDisplayed());

    }

    @Test
    public void AllitemsFromCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("All Items")));
        WebElement Allitems = driver.findElement(By.linkText("All Items"));
        Thread.sleep(3000);
        Allitems.click();
       wait.until(ExpectedConditions.urlContains("https://www.saucedemo.com/inventory.html"));
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }
    @Test
    public void AllitemsFromCheckout() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.id("checkout")).click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("All Items")));
        WebElement Allitems = driver.findElement(By.linkText("All Items"));
        Thread.sleep(1000);
        Allitems.click();
        wait.until(ExpectedConditions.urlContains("https://www.saucedemo.com/inventory.html"));
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void AllitemsFromOverview() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("mohamed");
        driver.findElement(By.id("last-name")).sendKeys("selem");
        driver.findElement(By.id("postal-code")).sendKeys("32511");
        driver.findElement(By.id("continue")).click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("All Items")));
        WebElement Allitems = driver.findElement(By.linkText("All Items"));
        Thread.sleep(3000);
        Allitems.click();
        wait.until(ExpectedConditions.urlContains("https://www.saucedemo.com/inventory.html"));
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void ResetCheckout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("mohamed");
        driver.findElement(By.id("last-name")).sendKeys("selem");
        driver.findElement(By.id("postal-code")).sendKeys("32511");
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement reset = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Reset App State")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        reset.click();
        WebElement tname = driver.findElement(By.id("first-name"));
        String name = tname.getAttribute("value");
        WebElement LName = driver.findElement(By.id("last-name"));
        String lName = LName.getAttribute("value");
        WebElement code = driver.findElement(By.id("postal-code"));
        String Pcode = code.getAttribute("value");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assert.assertEquals(name,"");
        Assert.assertEquals(lName,"");
        Assert.assertEquals(Pcode,"");
    }

    @Test
    public void ResetProductState(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.findElement(By.xpath("//*[@id=\"item_0_title_link\"]/div")).click();
        driver.findElement(By.id("add-to-cart")).click();
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("reset_sidebar_link")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement b1 = driver.findElement(By.id("remove"));
        Assert.assertEquals(b1.getText(),"Add to cart");
    }
    @Test
    public void CheckLogout(){
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("logout_sidebar_link")).click();
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        Assert.assertEquals(username.getText(),"");
        Assert.assertEquals(password.getText(),"");
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
    }
}

