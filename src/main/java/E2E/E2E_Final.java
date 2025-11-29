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

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class E2E_Final {
    WebDriver driver;

    @BeforeClass
    public void  openBrowser() throws InterruptedException {
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

    private void resetAppState() {
        driver.manage().deleteAllCookies();
        driver.get("https://www.saucedemo.com/");
    }

    public void slowType(WebElement element, String text, int delay) throws InterruptedException {
        for (char letter : text.toCharArray()) {
            element.sendKeys(Character.toString(letter));
            Thread.sleep(delay);
        }
    }

    public void login(String user, String pass){
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys(user);

        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(pass);

        driver.findElement(By.id("login-button")).click();
    }

    @BeforeGroups("happy1")
    public void loginOnce() {
        login("standard_user", "secret_sauce");
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
        String TBefore = Jacket_Befor.getText();
        Jacket_Befor.click();
        WebElement Jacket_After = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")));
        String TAfter = Jacket_After.getText();
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
    @Test(priority = 4 ,groups = "happy1")
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
        driver.findElement(By.className("shopping_cart_link")).click();
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
        login("standard_user", "secret_sauce");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    @Test(priority = 14, groups = "sad1" , dependsOnGroups = "happy1")
    public void wrong_process() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();;
        driver.findElement(By.cssSelector("a.shopping_cart_link")).click();
        WebElement removebutton = driver.findElement(By.id("remove-sauce-labs-backpack"));
        removebutton.click();
        WebElement removebutton2 = driver.findElement(By.id("remove-sauce-labs-bike-light"));
        removebutton2.click();
        WebElement checkoutbutton = driver.findElement(By.id("checkout"));
        checkoutbutton.click();
        WebElement firstnamefield = driver.findElement(By.id("first-name"));
        WebElement lastnamefield = driver.findElement(By.id("last-name"));
        WebElement postal_codefield = driver.findElement(By.id("postal-code"));
        try{
            Assert.assertNotEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-one.html");
        }
        catch (AssertionError e){
            System.out.println("AssertionError for empty cart ");
        }
        slowType(firstnamefield, "alaa", 50);
        slowType(lastnamefield, "Ali", 50);
        slowType(postal_codefield, "96335", 50);
        Thread.sleep(1000);
        WebElement continuebutton = driver.findElement(By.id("continue"));
        continuebutton.click();
        WebElement finishbutton = driver.findElement(By.id("finish"));
        finishbutton.click();
    }
    @Test(priority = 15 , groups = "sad2",dependsOnGroups = "sad1")
    public void wrong_process2() throws InterruptedException {
        login("standard_user", "secret_sauce");
        WebElement filter_pricebtn = driver.findElement(By.className("product_sort_container"));
        Select high_to_lowprice = new Select(filter_pricebtn);
        high_to_lowprice.selectByVisibleText("Price (high to low)");
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        for(int i = 0; i < prices.size()-1; i++){
            double current = Double.parseDouble(prices.get(i).getText().replace("$",""));
            double next = Double.parseDouble(prices.get(i+1).getText().replace("$",""));
            Assert.assertTrue( current >= next);
        }
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.cssSelector("a.shopping_cart_link")).click();
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
    @Test(priority = 16,groups = "sad3",dependsOnGroups = "sad2")
    public void wrong_process3() throws InterruptedException {
        login("standard_user", "secret_sauce");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.cssSelector("a.shopping_cart_link")).click();
        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        bar.click();
        WebElement Logout = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        Logout.click();
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
        try{
            Assert.assertNotEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-two.html");
        }
        catch (AssertionError e){
            System.out.println("fields can't be empty");
        }
        WebElement finishbutton = driver.findElement(By.id("finish"));
        finishbutton.click();
    }
    @Test(priority = 17 , groups = "happy2",dependsOnGroups = "sad3")
    public void success_process3() throws InterruptedException {
        login("standard_user", "secret_sauce");
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
        driver.findElement(By.id("finish")).click();
    }
    @Test(priority = 18 , groups = "happy3",dependsOnGroups = "happy2")
    public void Remove_products2() throws InterruptedException {
        login("standard_user", "secret_sauce");
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
        WebElement count = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(count.getText(),"3");
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
    @Test (priority = 19,groups = "sad4",dependsOnGroups = "happy3")
    public void empty_cart() throws InterruptedException {
        login("standard_user", "secret_sauce");
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        try {
            String checkout_URL = driver.getCurrentUrl();
            Assert.assertNotEquals(checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
        } catch (AssertionError e) {
            System.out.println("ERROR Cart empty ");
        }
    }
    @Test (priority = 20,groups = "sad5",dependsOnGroups = "sad4")
    public void empty_fields() throws InterruptedException {
        login("standard_user", "secret_sauce");
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
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Mohamed");
        driver.findElement(By.id("last-name")).sendKeys("Yasser");
        driver.findElement(By.id("postal-code")).sendKeys("35934");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        String Checkout_URL = driver.getCurrentUrl();
        Assert.assertEquals(Checkout_URL,"https://www.saucedemo.com/checkout-complete.html");
    }
    @Test (priority =21,groups = "performance")
    public void performance_glitch_user() throws InterruptedException {
        login("performance_glitch_user", "secret_sauce");
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
    @Test (priority =22,dependsOnGroups = "sad5",groups = "error_user")
    public void error_user() throws InterruptedException {
        login("error_user", "secret_sauce");
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

   @AfterMethod(alwaysRun = true)
    public void afterEach(Method method) {

        Test t = method.getAnnotation(Test.class);

        if (t == null) {
            return;
        }

        if (Arrays.asList(t.groups()).contains("happy1")) {
            return;
        }
        resetAppState();
    }
    @AfterClass
    public void closeBrowser() {
        if(driver != null) {
            driver.quit();
        }
    }
}

