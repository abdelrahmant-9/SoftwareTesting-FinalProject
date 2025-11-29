package integration.SideBar;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

public class Standard_user {
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
    @Test
    public void AllItemsHome(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("react-burger-menu-btn")).click();
        WebElement AllItems = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("All Items")));
        AllItems.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
    @Test
    public void AboutHome(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("react-burger-menu-btn")).click();
        WebElement About = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("About")));
        About.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }
    @Test
    public void LogOutHome(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("react-burger-menu-btn")).click();
        WebElement Logout = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        Logout.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
    //login with same user
    @Test
    public void LoginAfterLogout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("react-burger-menu-btn")).click();
        WebElement Logout = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        Logout.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        WebElement btn1 = driver.findElement(By.id("remove-test.allthethings()-t-shirt-(red)"));
        WebElement btn2 = driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt"));
        WebElement btn3 = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
        Assert.assertEquals(btn1.getText(), "Remove");
        Assert.assertEquals(btn2.getText(), "Remove");
        Assert.assertEquals(btn3.getText(), "Add to cart");

    }
    //login with different user
    @Test
    public void LoginAfterLogoutDifferentUser(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("react-burger-menu-btn")).click();
        WebElement Logout = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        Logout.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("problem_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        WebElement btn1 = driver.findElement(By.id("remove-test.allthethings()-t-shirt-(red)"));
        WebElement btn2 = driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt"));
        WebElement btn3 = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
        WebElement no = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
//        try{

            Assert.assertEquals(no.getText(), "0");
            Assert.assertNotEquals(btn1.getText(), "Remove");
            Assert.assertNotEquals(btn2.getText(), "Remove");
            Assert.assertEquals(btn3.getText(), "Add to cart");
//        }catch (AssertionError e){
//            System.out.println("data of standard user prersent in problem user ");
//        }

    }
    @Test
    public void ResetHome() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("react-burger-menu-btn")).click();
        WebElement Reset = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reset App State")));
        Reset.click();
        WebElement Close = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-cross-btn")));
        Close.click();
        try{
            WebElement b1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.
                    xpath("//*[@id=\"remove-sauce-labs-bike-light\"]")));
            WebElement b2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.
                    xpath("//*[@id=\"remove-sauce-labs-bolt-t-shirt\"]")));
            Assert.assertEquals(b1.getText(), "Add to cart");
            Assert.assertEquals(b2.getText(), "Add to cart");
        }catch (NoSuchElementException e){
            System.out.println("button 1 or 2 not found"+ e.getMessage());
        }

    }
    @Test
    public void AllItemsCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement AllItems = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("All Items")));
        AllItems.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
    @Test
    public void Aboutcart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement About = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("About")));
        About.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }
    @Test
    public void LogOutCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();;
        WebElement Logout = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        Logout.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
    @Test
    public void Resetcart(){
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
    public void AllItemsCheckOut(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.id("checkout")).click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement AllItems = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("All Items")));
        AllItems.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
    @Test
    public void AboutCheckOut(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.id("checkout")).click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement About = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("About")));
        About.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
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
    public void LogOutCheckout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.id("checkout")).click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement Logout = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        Logout.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
    @Test
    public void AllItemsOverView(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("mohamed");
        driver.findElement(By.id("last-name")).sendKeys("selem");
        driver.findElement(By.id("postal-code")).sendKeys("32511");
        driver.findElement(By.id("continue")).click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement AllItems = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("All Items")));
        AllItems.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
    @Test
    public void AboutOverView(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("mohamed");
        driver.findElement(By.id("last-name")).sendKeys("selem");
        driver.findElement(By.id("postal-code")).sendKeys("32511");
        driver.findElement(By.id("continue")).click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement About = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("About")));
        About.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }
    @Test
    public void LogOutOverView(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("mohamed");
        driver.findElement(By.id("last-name")).sendKeys("selem");
        driver.findElement(By.id("postal-code")).sendKeys("32511");
        driver.findElement(By.id("continue")).click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement Logout = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        Logout.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
    @Test
    public void ResetOverView(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("mohamed");
        driver.findElement(By.id("last-name")).sendKeys("selem");
        driver.findElement(By.id("postal-code")).sendKeys("32511");
        driver.findElement(By.id("continue")).click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement Reset = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reset App State")));
        Reset.click();
    }
    @Test
    public void AllItemsProduct(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.linkText("Sauce Labs Fleece Jacket")).click();
        WebElement p1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart")));
        p1.click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement AllItems = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("All Items")));
        AllItems.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
    @Test
    public void AboutProduct(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.linkText("Sauce Labs Fleece Jacket")).click();
        WebElement p1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart")));
        p1.click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement About = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("About")));
        About.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }
    @Test
    public void LogOutProduct(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.linkText("Sauce Labs Fleece Jacket")).click();
        WebElement p1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart")));
        p1.click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement Logout = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        Logout.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
    // reset after add product from product page and return back to the homepage
    @Test
    public void ResetAfterAddProduct(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.linkText("Sauce Labs Fleece Jacket")).click();
        WebElement p1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart")));
        p1.click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement AllItems = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("All Items")));
        AllItems.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        WebElement bar2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar2.click();
        WebElement Reset = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reset App State")));
        Reset.click();
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("remove-sauce-labs-fleece-jacket")));
        Assert.assertEquals(button.getText(), "Add to cart");
    }
    // reset page from product page before go to home page
    @Test
    public void ResetProductBefor(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.linkText("Sauce Labs Fleece Jacket")).click();
        WebElement p1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart")));
        p1.click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement Reset = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reset App State")));
        Reset.click();
        WebElement AllItems = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("All Items")));
        AllItems.click();
        WebElement addbutt = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(addbutt.getText(), "Add to cart");
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(1000);
        driver.close();
    }
}
