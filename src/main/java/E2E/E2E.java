package E2E;

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

public class E2E {
    WebDriver driver;
    @BeforeGroups("happy1")
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
@Test(groups = "happy1",priority = 1)
public void AddProduct(){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
    driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
    WebElement cart_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
            xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
    Assert.assertEquals(cart_no.getText(), "3");
}
@Test(groups = "happy1",priority = 2)
public void ViewProduct(){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement Jacket_Befor =   driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div"));
     String TBefore =   Jacket_Befor.getText();
     Jacket_Befor.click();
WebElement Jacket_After = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
        xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")));
String TAfter =   Jacket_After.getText();
    WebElement back = wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products")));
    back.click();
    Assert.assertEquals(TAfter,TBefore);
}
@Test(groups = "happy1",priority = 3)
public void ViewandAdd(){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    driver.findElement(By.xpath("//*[@id=\"item_2_title_link\"]/div")).click();
    WebElement Add = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart")));
    Add.click();
    WebElement back = wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products")));
    back.click();
}
@Test(priority = 4,groups = "haapy1")
public void removeProduct(){
        driver.findElement(By.id("remove-test.allthethings()-t-shirt-(red)")).click();
}
@Test(groups = "happy1",priority = 5)
public void GotoCart(){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        WebElement cart_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
    Assert.assertEquals(cart_no.getText(), "3");
}
@Test(groups = "happy1",priority = 6)
    public void RemoveFromCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Remove = wait.until(ExpectedConditions.elementToBeClickable(By.id("remove-sauce-labs-bike-light")));
        Remove.click();
    WebElement cart_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
            xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
    Assert.assertEquals(cart_no.getText(), "2");
}
@Test(groups = "happy1" ,priority = 7)
public void BackToHome(){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
driver.findElement(By.id("continue-shopping")).click();
    WebElement cart_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
            xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
    Assert.assertEquals(cart_no.getText(), "2");
}
@Test(groups = "happy1",priority = 8)
    public void LogOut(){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
    bar.click();
    WebElement Logout = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
    Logout.click();
    Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
}
@Test(priority = 9,groups = "happy1")
    public void LogIn(){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    driver.findElement(By.id("user-name")).sendKeys("standard_user");
    driver.findElement(By.id("password")).sendKeys("secret_sauce");
    driver.findElement(By.id("login-button")).click();
    WebElement cart_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
            xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
    Assert.assertEquals(cart_no.getText(), "2");
}
    @Test(groups = "happy1",priority = 10)
    public void GotoCartAgain(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        WebElement cart_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
        Assert.assertEquals(cart_no.getText(), "2");
    }
    @Test(groups = "happy1",priority = 11)
    public void GoToCheckout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkout.click();
    }
    @Test(groups = "happy1",priority = 12)
    public void AddInformation(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("first-name")).sendKeys("mohamed");
        driver.findElement(By.id("last-name")).sendKeys("selem");
        driver.findElement(By.id("postal-code")).sendKeys("32511");
        driver.findElement(By.id("continue")).click();
    }
@Test(groups = "happy1" ,priority = 13)
    public void Finish(){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("finish")).click();
        try {
            WebElement cart_no = driver.findElement(By.
                    xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
        }catch (NoSuchElementException e){
            System.out.println("passes");
        }
WebElement HomeBack = wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products")));
        HomeBack.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
}

    @AfterGroups(groups = "happy1")
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
    }
}
