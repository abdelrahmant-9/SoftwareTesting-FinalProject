package E2E;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompleteOrder {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.navigate().to("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    public void slowType(WebElement element, String text, int delay) throws InterruptedException {
        element.clear();
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            Thread.sleep(delay);
        }
    }

    @Test
    public void AddProduct(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        WebElement cart_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
        Assert.assertEquals(cart_no.getText(), "3");
    }

    @Test
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

    @Test
    public void ViewandAdd(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"item_2_title_link\"]/div")).click();
        WebElement Add = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart")));
        Add.click();
        WebElement back = wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products")));
        back.click();
    }

    @Test
    public void removeProduct(){
        driver.findElement(By.id("remove-test.allthethings()-t-shirt-(red)")).click();
    }

    @Test
    public void GotoCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        WebElement cart_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
        Assert.assertEquals(cart_no.getText(), "3");
    }

    @Test
    public void RemoveFromCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Remove = wait.until(ExpectedConditions.elementToBeClickable(By.id("remove-sauce-labs-bike-light")));
        Remove.click();
        WebElement cart_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
        Assert.assertEquals(cart_no.getText(), "2");
    }

    @Test
    public void BackToHome(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("continue-shopping")).click();
        WebElement cart_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
        Assert.assertEquals(cart_no.getText(), "2");
    }

    @Test
    public void LogOut(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement Logout = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        Logout.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void LogIn(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        WebElement cart_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
        Assert.assertEquals(cart_no.getText(), "2");
    }

    @Test
    public void GotoCartAgain(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        WebElement cart_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
        Assert.assertEquals(cart_no.getText(), "2");
    }

    @Test
    public void GoToCheckout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkout.click();
    }

    @Test
    public void AddInformation(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("first-name")).sendKeys("mohamed");
        driver.findElement(By.id("last-name")).sendKeys("selem");
        driver.findElement(By.id("postal-code")).sendKeys("32511");
        driver.findElement(By.id("continue")).click();
    }

    @Test
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
    //============================================================================================================

    /*@Test
    public void check_flow() throws InterruptedException
    {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        WebElement icon = driver.findElement(By.className("shopping_cart_link"));
        icon.click();

        List<WebElement> products = driver.findElements(By.className("cart_list"));
        Assert.assertFalse(products.isEmpty());
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("moahmed");
        driver.findElement(By.id("last-name")).sendKeys("akram");
        driver.findElement(By.id("postal-code")).sendKeys("12445");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        driver.findElement(By.id("back-to-products")).click();
    }

    @Test
    public void check_flow2() throws InterruptedException
    {
        driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).click();

        driver.findElement(By.id("add-to-cart")).click();
        driver.findElement(By.id("back-to-products")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        WebElement icon = driver.findElement(By.className("shopping_cart_link"));

        icon.click();

        List <WebElement> products = driver.findElements(By.className("cart_list"));
        Assert.assertFalse(products.isEmpty());
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("moahmed");
        driver.findElement(By.id("last-name")).sendKeys("akram");
        driver.findElement(By.id("postal-code")).sendKeys("12445");
        driver.findElement(By.id("continue")).click();

        driver.findElement(By.id("finish")).click();
        driver.findElement(By.id("back-to-products")).click();

    }

    @Test
    public void checkout_process_complete() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    @Test
    public void Remove_products() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    @Test
    public void Remove_products2() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
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
        driver.findElement(By.id("back-to-products")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    @Test
    public void empty_cart() throws InterruptedException {
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertNotEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    @Test
    public void fields_with_space() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("         ");
        driver.findElement(By.id("last-name")).sendKeys("          ");
        driver.findElement(By.id("postal-code")).sendKeys("        ");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertNotEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    @Test
    public void empty_fields() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("");
        driver.findElement(By.id("last-name")).sendKeys("");
        driver.findElement(By.id("postal-code")).sendKeys("");
        driver.findElement(By.id("continue")).click();
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertNotEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    @Test
    public void Reset_App_State() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("reset_sidebar_link")).click();
        WebElement AddBtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        Assert.assertEquals(AddBtn.getText(), "Add to cart");
        AddBtn.click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
    }

    @Test
    public void locked_out_user() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    @Test
    public void problem_user() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    @Test
    public void performance_glitch_user() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    @Test
    public void error_user() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    @Test
    public void visual_user() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        String checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }

    @Test
    public void check_flow3() throws InterruptedException
    {
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        WebElement icon = driver.findElement(By.className("shopping_cart_link"));
        icon.click();

        List <WebElement> products = driver.findElements(By.className("cart_list"));
        driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div")).click();

        driver.findElement(By.id("remove")).click();
        driver.findElement(By.id("back-to-products")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        Assert.assertFalse(products.isEmpty());
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("moahmed");
        driver.findElement(By.id("last-name")).sendKeys("akram");
        driver.findElement(By.id("postal-code")).sendKeys("12445");
        driver.findElement(By.id("continue")).click();

        driver.findElement(By.id("finish")).click();
        driver.findElement(By.id("back-to-products")).click();
    }

    @Test
    public void check_flow4() throws InterruptedException
    {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        WebElement icon = driver.findElement(By.className("shopping_cart_link"));
        icon.click();

        List <WebElement> products = driver.findElements(By.className("cart_list"));
        Assert.assertFalse(products.isEmpty());
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("tamer");
        driver.findElement(By.id("last-name")).sendKeys("tito");
        driver.findElement(By.id("postal-code")).sendKeys("55555");
        driver.findElement(By.id("continue")).click();

        driver.findElement(By.id("react-burger-menu-btn")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("inventory_sidebar_link")));
        driver.findElement(By.id("inventory_sidebar_link")).click();
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("tamer");
        driver.findElement(By.id("last-name")).sendKeys("tito");
        driver.findElement(By.id("postal-code")).sendKeys("55555");
        driver.findElement(By.id("continue")).click();

        driver.findElement(By.id("finish")).click();
        driver.findElement(By.id("back-to-products")).click();
    }

    @Test
    public void check_flow5() throws InterruptedException
    {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        WebElement icon = driver.findElement(By.className("shopping_cart_link"));
        icon.click();
        List <WebElement> products = driver.findElements(By.className("cart_list"));
        Assert.assertFalse(products.isEmpty());
        driver.findElement(By.id("checkout")).click();
        WebElement first = driver.findElement(By.id("first-name"));
        first.sendKeys("   ");
        WebElement last = driver.findElement(By.id("last-name"));
        last.sendKeys("   ");
        WebElement ZIP = driver.findElement(By.id("postal-code"));
        ZIP.sendKeys("   ");
        WebElement BTN = driver.findElement(By.id("continue"));
        Assert.assertFalse(BTN.isEnabled());
    }
    @Test
    public void check_flow6() throws InterruptedException
    {
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();

        WebElement icon = driver.findElement(By.className("shopping_cart_link"));
        icon.click();

        driver.findElement(By.id("remove-sauce-labs-onesie")).click();
        driver.findElement(By.id("remove-test.allthethings()-t-shirt-(red)")).click();

        String cartURL = driver.getCurrentUrl();
        driver.findElement(By.id("checkout")).click();

        String Url = driver.getCurrentUrl();
        Assert.assertEquals(cartURL , Url);

    }
    //==================================================================================================================

    @Test
    public void flowLowToHigh() {
        WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(By.className("product_sort_container")));
        filter.click();
        filter.findElement(By.xpath("//option[@value='lohi']")).click();

        List<WebElement> addButtons = driver.findElements(By.xpath("//button[contains(@id,'add-to-cart')]"));
        addButtons.get(0).click();
        addButtons.get(1).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        double price0 = Double.parseDouble(prices.get(0).getText().replace("$",""));
        double price1 = Double.parseDouble(prices.get(1).getText().replace("$",""));

        if(price0 > price1){
            driver.findElement(By.xpath("//button[contains(@id,'remove')]")).click();
        } else {
            driver.findElements(By.xpath("//button[contains(@id,'remove')]")).get(1).click();
        }

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Habiba");
        driver.findElement(By.id("last-name")).sendKeys("Ragab");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        Assert.assertEquals(msg.getText(), "THANK YOU FOR YOUR ORDER");

        driver.findElement(By.id("back-to-products")).click();
    }

    @Test
    public void flowAddRemoveMidway() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("remove-sauce-labs-onesie")).click();

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Habiba");
        driver.findElement(By.id("last-name")).sendKeys("Ragab");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        Assert.assertEquals(msg.getText(), "THANK YOU FOR YOUR ORDER");

        driver.findElement(By.id("back-to-products")).click();
    }

    @Test
    public void flowEmptyCartCheckout() {
        driver.findElement(By.className("shopping_cart_link")).click();

        List<WebElement> removeBtns = driver.findElements(By.xpath("//button[contains(@id,'remove')]"));
        for(WebElement btn : removeBtns){
            btn.click();
        }

        driver.findElement(By.id("checkout")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));

        WebElement continueBtn = driver.findElement(By.id("continue"));
        Assert.assertFalse(continueBtn.isEnabled() || continueBtn.isDisplayed());
    }

    @Test
    public void flowHighToLow() {
        WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(By.className("product_sort_container")));
        filter.click();
        filter.findElement(By.xpath("//option[@value='hilo']")).click();

        List<WebElement> addButtons = driver.findElements(By.xpath("//button[contains(@id,'add-to-cart')]"));
        addButtons.get(0).click();
        addButtons.get(1).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Habiba");
        driver.findElement(By.id("last-name")).sendKeys("Ragab");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        Assert.assertEquals(msg.getText(), "THANK YOU FOR YOUR ORDER");

        driver.findElement(By.id("back-to-products")).click();
    }

    @Test
    public void flowPostPurchaseReview() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Habiba");
        driver.findElement(By.id("last-name")).sendKeys("Ragab");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        Assert.assertEquals(msg.getText(), "THANK YOU FOR YOUR ORDER");

        driver.findElement(By.id("back-to-products")).click();

        List<WebElement> inventory = driver.findElements(By.className("inventory_item"));
        Assert.assertFalse(inventory.isEmpty());

        List<WebElement> addBtns = driver.findElements(By.xpath("//button[contains(@id,'add-to-cart')]"));
        Assert.assertTrue(addBtns.size() > 0);
    }
    @Test
    public void success_process() throws InterruptedException {
        WebElement Sauce_Labs_Backpackbtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        Sauce_Labs_Backpackbtn.click();
        WebElement Sauce_Labs_Bolt_TShirtbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        Sauce_Labs_Bolt_TShirtbtn.click();
        WebElement carticon = driver.findElement(By.cssSelector("a.shopping_cart_link"));
        carticon.click();
        WebElement checkoutbutton = driver.findElement(By.id("checkout"));
        checkoutbutton.click();
        WebElement firstnamefield = driver.findElement(By.id("first-name"));
        WebElement lastnamefield = driver.findElement(By.id("last-name"));
        WebElement postal_code_field = driver.findElement(By.id("postal-code"));
        slowType(firstnamefield,"alaa",50);
        slowType(lastnamefield,"mohammed",50);
        slowType(postal_code_field,"25547",50);
        WebElement continuebutton = driver.findElement(By.id("continue"));
        continuebutton.click();
        WebElement finishbutton = driver.findElement(By.id("finish"));
        finishbutton.click();
    }

    @Test
    public void success_process2() throws InterruptedException {
        WebElement Sauce_Labs_Backpackbtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        WebElement Sauce_Labs_Bike_Lightbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        WebElement Sauce_Labs_Bolt_TShirtbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        WebElement Sauce_Labs_Fleece_Jacketbtn = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        Sauce_Labs_Backpackbtn.click();
        Sauce_Labs_Bike_Lightbtn.click();
        Sauce_Labs_Bolt_TShirtbtn.click();
        Sauce_Labs_Fleece_Jacketbtn.click();
        WebElement carticon = driver.findElement(By.cssSelector("a.shopping_cart_link"));
        carticon.click();
        WebElement removebutton1 = driver.findElement(By.id("remove-sauce-labs-backpack"));
        removebutton1.click();

        WebElement removebutton2 = driver.findElement(By.id("remove-sauce-labs-bike-light"));
        removebutton2.click();

        WebElement checkoutbutton = driver.findElement(By.id("checkout"));
        checkoutbutton.click();

        WebElement firstnamefield = driver.findElement(By.id("first-name"));
        WebElement lastnamefield = driver.findElement(By.id("last-name"));
        WebElement postal_codefield = driver.findElement(By.id("postal-code"));
        slowType(firstnamefield, "sara", 50);
        slowType(lastnamefield, "karam", 50);
        slowType(postal_codefield, "96335", 50);

        WebElement continuebutton = driver.findElement(By.id("continue"));
        continuebutton.click();

        WebElement finishbutton = driver.findElement(By.id("finish"));
        finishbutton.click();


    }

    @Test
    public void wrong_process() throws InterruptedException {
        WebElement Sauce_Labs_Backpackbtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        WebElement Sauce_Labs_Bike_Lightbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        Sauce_Labs_Backpackbtn.click();
        Sauce_Labs_Bike_Lightbtn.click();

        WebElement carticon = driver.findElement(By.cssSelector("a.shopping_cart_link"));
        carticon.click();
        WebElement removebutton = driver.findElement(By.id("remove-sauce-labs-backpack"));
        removebutton.click();

        WebElement removebutton2 = driver.findElement(By.id("remove-sauce-labs-bike-light"));
        removebutton2.click();
        WebElement checkoutbutton = driver.findElement(By.id("checkout"));
        checkoutbutton.click();

        WebElement firstnamefield = driver.findElement(By.id("first-name"));
        WebElement lastnamefield = driver.findElement(By.id("last-name"));
        WebElement postal_codefield = driver.findElement(By.id("postal-code"));
        slowType(firstnamefield, "alaa", 50);
        slowType(lastnamefield, "Ali", 50);
        slowType(postal_codefield, "96335", 50);

        WebElement continuebutton = driver.findElement(By.id("continue"));
        continuebutton.click();

        WebElement finishbutton = driver.findElement(By.id("finish"));
        finishbutton.click();


    }

    @Test
    public void wrong_process2() throws InterruptedException {
        WebElement filter_pricebtn = driver.findElement(By.className("product_sort_container"));
        Select high_to_lowprice = new Select(filter_pricebtn);
        high_to_lowprice.selectByVisibleText("Price (high to low)");

        WebElement addproductbtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addproductbtn.click();
        WebElement carticon = driver.findElement(By.cssSelector("a.shopping_cart_link"));
        carticon.click();
        WebElement checkoutbutton = driver.findElement(By.id("checkout"));
        checkoutbutton.click();

        WebElement firstnamefield = driver.findElement(By.id("first-name"));
        WebElement lastnamefield = driver.findElement(By.id("last-name"));
        WebElement postal_codefield = driver.findElement(By.id("postal-code"));
        slowType(firstnamefield, "alaa", 50);
        slowType(lastnamefield, " ", 50);
        slowType(postal_codefield, " ", 50);

        WebElement continuebutton = driver.findElement(By.id("continue"));
        continuebutton.click();

        WebElement finishbutton = driver.findElement(By.id("finish"));
        finishbutton.click();

    }

    @Test
    public void wrong_process3() throws InterruptedException {
        WebElement Sauce_Labs_Backpackbtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        WebElement Sauce_Labs_Bike_Lightbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        WebElement Sauce_Labs_Bolt_TShirtbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        WebElement Sauce_Labs_Fleece_Jacketbtn = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        Sauce_Labs_Backpackbtn.click();
        Sauce_Labs_Bike_Lightbtn.click();
        Sauce_Labs_Bolt_TShirtbtn.click();
        Sauce_Labs_Fleece_Jacketbtn.click();
        WebElement carticon = driver.findElement(By.cssSelector("a.shopping_cart_link"));
        carticon.click();

        WebElement menu_Btn = driver.findElement(By.id("react-burger-menu-btn"));
        menu_Btn.click();
        WebElement logoutbutton = driver.findElement(By.id("logout_sidebar_link"));
        logoutbutton.click();
        WebElement usernamefield = driver.findElement(By.id("user-name"));
        WebElement passwordfield = driver.findElement(By.id("password"));
        WebElement loginbutton = driver.findElement(By.id("login-button"));
        slowType(usernamefield, "standard_user", 50);
        slowType(passwordfield, "secret_sauce", 50);
        loginbutton.click();
        WebElement carticonT2 = driver.findElement(By.cssSelector("a.shopping_cart_link"));
        carticonT2.click();
        WebElement checkoutbutton = driver.findElement(By.id("checkout"));
        checkoutbutton.click();
        WebElement firstnamefield = driver.findElement(By.id("first-name"));
        WebElement lastnamefield = driver.findElement(By.id("last-name"));
        WebElement postal_codefield = driver.findElement(By.id("postal-code"));
        slowType(firstnamefield, " ", 50);
        slowType(lastnamefield, " ", 50);
        slowType(postal_codefield, " ", 50);
        WebElement continuebutton = driver.findElement(By.id("continue"));
        continuebutton.click();
        WebElement finishbutton = driver.findElement(By.id("finish"));
        finishbutton.click();
    }

    @Test
    public void wrong_process4() throws InterruptedException {
        WebElement Sauce_Labs_Fleece_Jacketbtn = driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div"));
        Sauce_Labs_Fleece_Jacketbtn.click();
        WebElement Sauce_Labs_Fleece_Jacketdetails = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[2]"));
        String detailtext =  Sauce_Labs_Fleece_Jacketdetails.getText();
        Assert.assertTrue(detailtext.contains("quarter-zip fleece jacket"));
        WebElement addBtn = driver.findElement(By.id("add-to-cart"));
        addBtn.click();
        WebElement backBtn = driver.findElement(By.id("back-to-products"));
        backBtn.click();
        WebElement carticon = driver.findElement(By.cssSelector("a.shopping_cart_link"));
        carticon.click();
        WebElement checkoutbutton = driver.findElement(By.id("checkout"));
        checkoutbutton.click();
        WebElement firstnamefield = driver.findElement(By.id("first-name"));
        WebElement lastnamefield = driver.findElement(By.id("last-name"));
        WebElement postal_codefield = driver.findElement(By.id("postal-code"));
        slowType(firstnamefield, "Ahmed", 50);
        slowType(lastnamefield, " Ali", 50);
        slowType(postal_codefield, " ", 50);
        WebElement continuebutton = driver.findElement(By.id("continue"));
        continuebutton.click();
        WebElement finishbutton = driver.findElement(By.id("finish"));
        finishbutton.click();
    }
    @Test
    public void success_process3() throws InterruptedException {
        WebElement filter_z_to_a_btn = driver.findElement(By.className("product_sort_container"));
        Select z_to_a = new Select(filter_z_to_a_btn);
        z_to_a.selectByVisibleText("Name (Z to A)");

        WebElement Sauce_Labs_Bolt_T_Shirtbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        Sauce_Labs_Bolt_T_Shirtbtn.click();
        WebElement Sauce_Labs_Fleece_Jacketbtn = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        Sauce_Labs_Fleece_Jacketbtn.click();
        WebElement carticon = driver.findElement(By.className("shopping_cart_link"));
        String actualnumb = carticon.getText();
        Assert.assertEquals(actualnumb, "2");
        carticon.click();
        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-fleece-jacket"));
        removeBtn.click();
        WebElement carticon2 = driver.findElement(By.cssSelector("a.shopping_cart_link"));
        String actualnumb2 = carticon2.getText();
        Assert.assertEquals(actualnumb2, "1");
        WebElement price = driver.findElement(By.cssSelector("div.inventory_item_price"));
        String actualprice = price.getText();
        Assert.assertEquals(actualprice, "$15.99");
        WebElement checkoutbtn = driver.findElement(By.id("checkout"));
        checkoutbtn.click();
        WebElement firstnamefield = driver.findElement(By.id("first-name"));
        WebElement lastnamefield = driver.findElement(By.id("last-name"));
        WebElement postal_codefield = driver.findElement(By.id("postal-code"));
        slowType(firstnamefield, "Ziad ", 50);
        slowType(lastnamefield, "Ahmed ", 50);
        slowType(postal_codefield, "99999 ", 50);
        WebElement continuebutton = driver.findElement(By.id("continue"));
        continuebutton.click();
        WebElement finishbutton = driver.findElement(By.id("finish"));
        finishbutton.click();
    }*/

    @AfterMethod
    public void resetAfterEachTest() {
        driver.manage().deleteAllCookies();
        driver.navigate().to("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.urlContains("inventory"));
    }

    @AfterClass
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
    }
}
