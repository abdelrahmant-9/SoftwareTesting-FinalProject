package E2E;

import io.github.bonigarcia.wdm.WebDriverManager;
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
    public void openBrowser() {
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

    public class E2E {
        WebDriver driver;

        @BeforeGroups("happy1")
        public void openBrowser() {
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

        //Selem
        /*@Test(groups = "happy1",priority = 1)
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
    }*/
//======================================================================================================================
            //Akram
            /*@Test(priority = 1)
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

        }*/
//======================================================================================================================
        //Abdelrahman
        /*public class E2EforCart {

            WebDriver driver;
            WebDriverWait wait;
            int expectedCartCount = 0;

            public void waitForElement(By locator) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            }

            public void clickWhenReady(By locator) {
                wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            }

            public void smallWait() {
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @BeforeClass
            public void openBrowser() {
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
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

                wait = new WebDriverWait(driver, Duration.ofSeconds(15));

                driver.get("https://www.saucedemo.com/");
                smallWait();

                driver.findElement(By.id("user-name")).sendKeys("standard_user");
                driver.findElement(By.id("password")).sendKeys("secret_sauce");
                clickWhenReady(By.id("login-button"));
            }

            @Test(priority = 1, groups = {"e2e"})
            public void CartShouldBeEmptyAtStart() {
                try {
                    smallWait();
                    boolean badgeExists = driver.findElements(By.className("shopping_cart_badge")).size() > 0;
                    Assert.assertFalse(badgeExists);
                } catch (Exception e) {
                    Assert.fail("CartShouldBeEmptyAtStart failed: " + e.getMessage());
                }
            }

            @Test(priority = 2, groups = {"e2e"})
            public void AddFirstProduct() {
                try {
                    smallWait();
                    clickWhenReady(By.id("add-to-cart-sauce-labs-backpack"));
                    expectedCartCount++;

                    waitForElement(By.className("shopping_cart_badge"));
                    WebElement badge = driver.findElement(By.className("shopping_cart_badge"));

                    Assert.assertEquals(badge.getText(), String.valueOf(expectedCartCount));
                } catch (Exception e) {
                    Assert.fail("AddFirstProduct failed: " + e.getMessage());
                }
            }

            @Test(priority = 3, groups = {"e2e"})
            public void AddMoreProducts() {
                try {
                    smallWait();
                    clickWhenReady(By.id("add-to-cart-sauce-labs-bike-light"));
                    expectedCartCount++;

                    smallWait();
                    clickWhenReady(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
                    expectedCartCount++;

                    waitForElement(By.className("shopping_cart_badge"));
                    WebElement badge = driver.findElement(By.className("shopping_cart_badge"));

                    Assert.assertEquals(badge.getText(), String.valueOf(expectedCartCount));
                } catch (Exception e) {
                    Assert.fail("AddMoreProducts failed: " + e.getMessage());
                }
            }

            @Test(priority = 4, groups = {"e2e"})
            public void GoToCartAndValidateItems() {
                try {
                    smallWait();
                    clickWhenReady(By.className("shopping_cart_link"));

                    waitForElement(By.linkText("Sauce Labs Backpack"));
                    Assert.assertTrue(driver.findElement(By.linkText("Sauce Labs Backpack")).isDisplayed());
                    Assert.assertTrue(driver.findElement(By.linkText("Sauce Labs Bike Light")).isDisplayed());
                    Assert.assertTrue(driver.findElement(By.linkText("Sauce Labs Bolt T-Shirt")).isDisplayed());
                } catch (Exception e) {
                    Assert.fail("GoToCartAndValidateItems failed: " + e.getMessage());
                }
            }

            @Test(priority = 5, groups = {"e2e"})
            public void RemoveItemAndValidateCount() {
                try {
                    smallWait();
                    clickWhenReady(By.id("remove-sauce-labs-bike-light"));
                    expectedCartCount--;

                    waitForElement(By.className("shopping_cart_badge"));
                    WebElement badge = driver.findElement(By.className("shopping_cart_badge"));

                    Assert.assertEquals(badge.getText(), String.valueOf(expectedCartCount));
                } catch (Exception e) {
                    Assert.fail("RemoveItemAndValidateCount failed: " + e.getMessage());
                }
            }

            @Test(priority = 6, groups = {"e2e"})
            public void ProceedToCheckout() {
                try {
                    smallWait();
                    waitForElement(By.id("checkout"));
                    clickWhenReady(By.id("checkout"));

                    waitForElement(By.className("title"));
                    WebElement title = driver.findElement(By.className("title"));

                    Assert.assertEquals(title.getText(), "Checkout: Your Information");
                } catch (Exception e) {
                    Assert.fail("ProceedToCheckout failed: " + e.getMessage());
                }
            }

            @Test(priority = 7, groups = {"e2e"})
            public void CompleteCheckout() {
                try {
                    smallWait();
                    waitForElement(By.id("first-name"));
                    driver.findElement(By.id("first-name")).sendKeys("Abdelrahman");

                    driver.findElement(By.id("last-name")).sendKeys("Tarek");
                    driver.findElement(By.id("postal-code")).sendKeys("35111");

                    smallWait();
                    clickWhenReady(By.id("continue"));

                    smallWait();
                    clickWhenReady(By.id("finish"));

                    Assert.assertTrue(driver.getCurrentUrl().contains("checkout-complete"));
                } catch (Exception e) {
                    Assert.fail("CompleteCheckout failed: " + e.getMessage());
                }
            }

            @AfterClass
            public void closeBrowser() {
                driver.quit();
            }
        }*/
//======================================================================================================================
        //Yasser
        //verify that checkout process complete with username: standard_user
        /*@Test (priority = 1 )
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
    }*/
//======================================================================================================================
        //Habiba
        /*public class EndToEnd {

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
                wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                driver.manage().window().maximize();
                driver.get("https://www.saucedemo.com/");

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
                driver.findElement(By.id("password")).sendKeys("secret_sauce");
                driver.findElement(By.id("login-button")).click();
            }

            @Test(priority = 1)
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

            @Test(priority = 2)
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

            @Test(priority = 3)
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

            @Test(priority = 4)
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

            @Test(priority = 5)
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

            @AfterMethod
            public void teardown() {
                driver.quit();
            }
        }*/
//======================================================================================================================
        //Alaa
        /*public class E2E_Testcases {
            ChromeOptions options;
            Map<String, Object> prefs;
            WebDriver driver; {
                options = new ChromeOptions();
                prefs = new HashMap<>();
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
            }

            public void slowType(WebElement element, String text, int delay) throws InterruptedException {
                for (char letter : text.toCharArray()) {
                    element.sendKeys(Character.toString(letter));
                    Thread.sleep(delay);
                }
            }
            @BeforeMethod
            public void beforemethod () throws InterruptedException {
                driver.manage().window().maximize();
                driver.navigate().to("https://www.saucedemo.com/");
                WebElement usernamefield = driver.findElement(By.id("user-name"));
                WebElement passwordfield = driver.findElement(By.id("password"));
                WebElement loginbutton = driver.findElement(By.id("login-button"));
                slowType(usernamefield, "standard_user", 50);
                slowType(passwordfield, "secret_sauce", 50);
                loginbutton.click();
                Thread.sleep(1000);
            }



            @Test(priority = 1)
            public void success_process() throws InterruptedException {
                WebElement Sauce_Labs_Backpackbtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
                Sauce_Labs_Backpackbtn.click();
                WebElement Sauce_Labs_Bolt_TShirtbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
                Sauce_Labs_Bolt_TShirtbtn.click();
                Thread.sleep(1000);
                WebElement carticon = driver.findElement(By.cssSelector("a.shopping_cart_link"));
                carticon.click();
                Thread.sleep(2000);
                WebElement checkoutbutton = driver.findElement(By.id("checkout"));
                checkoutbutton.click();
                Thread.sleep(1000);
                WebElement firstnamefield = driver.findElement(By.id("first-name"));
                WebElement lastnamefield = driver.findElement(By.id("last-name"));
                WebElement postal_code_field = driver.findElement(By.id("postal-code"));
                slowType(firstnamefield,"alaa",50);
                slowType(lastnamefield,"mohammed",50);
                slowType(postal_code_field,"25547",50);
                Thread.sleep(1000);
                WebElement continuebutton = driver.findElement(By.id("continue"));
                continuebutton.click();
                Thread.sleep(1000);
                WebElement finishbutton = driver.findElement(By.id("finish"));
                finishbutton.click();
                Thread.sleep(1000);
            }

            @Test(priority = 2)
            public void success_process2() throws InterruptedException {
                WebElement Sauce_Labs_Backpackbtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
                WebElement Sauce_Labs_Bike_Lightbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
                WebElement Sauce_Labs_Bolt_TShirtbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
                WebElement Sauce_Labs_Fleece_Jacketbtn = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
                Sauce_Labs_Backpackbtn.click();
                Thread.sleep(300);
                Sauce_Labs_Bike_Lightbtn.click();
                Thread.sleep(300);
                Sauce_Labs_Bolt_TShirtbtn.click();
                Thread.sleep(300);
                Sauce_Labs_Fleece_Jacketbtn.click();
                Thread.sleep(300);
                WebElement carticon = driver.findElement(By.cssSelector("a.shopping_cart_link"));
                carticon.click();
                WebElement removebutton1 = driver.findElement(By.id("remove-sauce-labs-backpack"));
                removebutton1.click();
                Thread.sleep(1000);
                WebElement removebutton2 = driver.findElement(By.id("remove-sauce-labs-bike-light"));
                removebutton2.click();
                Thread.sleep(900);
                WebElement checkoutbutton = driver.findElement(By.id("checkout"));
                checkoutbutton.click();
                Thread.sleep(1000);
                WebElement firstnamefield = driver.findElement(By.id("first-name"));
                WebElement lastnamefield = driver.findElement(By.id("last-name"));
                WebElement postal_codefield = driver.findElement(By.id("postal-code"));
                slowType(firstnamefield, "sara", 50);
                slowType(lastnamefield, "karam", 50);
                slowType(postal_codefield, "96335", 50);
                Thread.sleep(1000);
                WebElement continuebutton = driver.findElement(By.id("continue"));
                continuebutton.click();
                Thread.sleep(1000);
                WebElement finishbutton = driver.findElement(By.id("finish"));
                finishbutton.click();
                Thread.sleep(1000);

            }
            @Test(priority = 3)
            public void wrong_process() throws InterruptedException {
                WebElement Sauce_Labs_Backpackbtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
                WebElement Sauce_Labs_Bike_Lightbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
                Sauce_Labs_Backpackbtn.click();
                Sauce_Labs_Bike_Lightbtn.click();
                Thread.sleep(900);
                WebElement carticon = driver.findElement(By.cssSelector("a.shopping_cart_link"));
                carticon.click();
                Thread.sleep(300);
                WebElement removebutton = driver.findElement(By.id("remove-sauce-labs-backpack"));
                removebutton.click();
                Thread.sleep(900);
                WebElement removebutton2 = driver.findElement(By.id("remove-sauce-labs-bike-light"));
                removebutton2.click();
                WebElement checkoutbutton = driver.findElement(By.id("checkout"));
                checkoutbutton.click();
                Thread.sleep(1000);
                WebElement firstnamefield = driver.findElement(By.id("first-name"));
                WebElement lastnamefield = driver.findElement(By.id("last-name"));
                WebElement postal_codefield = driver.findElement(By.id("postal-code"));
                slowType(firstnamefield, "alaa", 50);
                slowType(lastnamefield, "Ali", 50);
                slowType(postal_codefield, "96335", 50);
                Thread.sleep(1000);
                WebElement continuebutton = driver.findElement(By.id("continue"));
                continuebutton.click();
                Thread.sleep(1000);
                WebElement finishbutton = driver.findElement(By.id("finish"));
                finishbutton.click();
                Thread.sleep(900);

            }
            @Test(priority = 4)
            public void wrong_process2() throws InterruptedException {
                WebElement filter_pricebtn = driver.findElement(By.className("product_sort_container"));
                Select high_to_lowprice = new Select(filter_pricebtn);
                high_to_lowprice.selectByVisibleText("Price (high to low)");
                Thread.sleep(900);
                WebElement addproductbtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
                addproductbtn.click();
                Thread.sleep(800);
                WebElement carticon = driver.findElement(By.cssSelector("a.shopping_cart_link"));
                carticon.click();
                Thread.sleep(800);
                WebElement checkoutbutton = driver.findElement(By.id("checkout"));
                checkoutbutton.click();
                Thread.sleep(1000);
                WebElement firstnamefield = driver.findElement(By.id("first-name"));
                WebElement lastnamefield = driver.findElement(By.id("last-name"));
                WebElement postal_codefield = driver.findElement(By.id("postal-code"));
                slowType(firstnamefield, "alaa", 50);
                slowType(lastnamefield, " ", 50);
                slowType(postal_codefield, " ", 50);
                Thread.sleep(1000);
                WebElement continuebutton = driver.findElement(By.id("continue"));
                continuebutton.click();
                Thread.sleep(1000);
                WebElement finishbutton = driver.findElement(By.id("finish"));
                finishbutton.click();
                Thread.sleep(200);

            }
            @Test(priority = 5)
            public void wrong_process3() throws InterruptedException {
                WebElement Sauce_Labs_Backpackbtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
                WebElement Sauce_Labs_Bike_Lightbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
                WebElement Sauce_Labs_Bolt_TShirtbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
                WebElement Sauce_Labs_Fleece_Jacketbtn = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
                Sauce_Labs_Backpackbtn.click();
                Thread.sleep(200);
                Sauce_Labs_Bike_Lightbtn.click();
                Thread.sleep(200);
                Sauce_Labs_Bolt_TShirtbtn.click();
                Thread.sleep(200);
                Sauce_Labs_Fleece_Jacketbtn.click();
                Thread.sleep(200);
                WebElement carticon = driver.findElement(By.cssSelector("a.shopping_cart_link"));
                carticon.click();
                Thread.sleep(900);
                WebElement menu_Btn = driver.findElement(By.id("react-burger-menu-btn"));
                menu_Btn.click();
                Thread.sleep(600);
                WebElement logoutbutton = driver.findElement(By.id("logout_sidebar_link"));
                logoutbutton.click();
                Thread.sleep(500);
                WebElement usernamefield = driver.findElement(By.id("user-name"));
                WebElement passwordfield = driver.findElement(By.id("password"));
                WebElement loginbutton = driver.findElement(By.id("login-button"));
                slowType(usernamefield, "standard_user", 50);
                slowType(passwordfield, "secret_sauce", 50);
                loginbutton.click();
                Thread.sleep(300);
                WebElement carticonT2 = driver.findElement(By.cssSelector("a.shopping_cart_link"));
                carticonT2.click();
                Thread.sleep(1000);
                WebElement checkoutbutton = driver.findElement(By.id("checkout"));
                checkoutbutton.click();
                Thread.sleep(1000);
                WebElement firstnamefield = driver.findElement(By.id("first-name"));
                WebElement lastnamefield = driver.findElement(By.id("last-name"));
                WebElement postal_codefield = driver.findElement(By.id("postal-code"));
                slowType(firstnamefield, " ", 50);
                slowType(lastnamefield, " ", 50);
                slowType(postal_codefield, " ", 50);
                WebElement continuebutton = driver.findElement(By.id("continue"));
                continuebutton.click();
                Thread.sleep(1000);
                WebElement finishbutton = driver.findElement(By.id("finish"));
                finishbutton.click();
                Thread.sleep(200);
            }
            @Test(priority = 6)
            public void wrong_process4() throws InterruptedException {
                WebElement Sauce_Labs_Fleece_Jacketbtn = driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div"));
                Sauce_Labs_Fleece_Jacketbtn.click();
                Thread.sleep(1000);
                WebElement Sauce_Labs_Fleece_Jacketdetails = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[2]"));
                String detailtext =  Sauce_Labs_Fleece_Jacketdetails.getText();
                Assert.assertTrue(detailtext.contains("quarter-zip fleece jacket"));
                WebElement addBtn = driver.findElement(By.id("add-to-cart"));
                addBtn.click();
                Thread.sleep(2000);
                WebElement backBtn = driver.findElement(By.id("back-to-products"));
                backBtn.click();
                Thread.sleep(1000);
                WebElement carticon = driver.findElement(By.cssSelector("a.shopping_cart_link"));
                carticon.click();
                Thread.sleep(900);
                WebElement checkoutbutton = driver.findElement(By.id("checkout"));
                checkoutbutton.click();
                Thread.sleep(1000);
                WebElement firstnamefield = driver.findElement(By.id("first-name"));
                WebElement lastnamefield = driver.findElement(By.id("last-name"));
                WebElement postal_codefield = driver.findElement(By.id("postal-code"));
                slowType(firstnamefield, "Ahmed", 50);
                slowType(lastnamefield, " Ali", 50);
                slowType(postal_codefield, " ", 50);
                Thread.sleep(1000);
                WebElement continuebutton = driver.findElement(By.id("continue"));
                continuebutton.click();
                Thread.sleep(1000);
                WebElement finishbutton = driver.findElement(By.id("finish"));
                finishbutton.click();
                Thread.sleep(200);
            }
            @Test(priority = 7)
            public void success_process3() throws InterruptedException {
                WebElement filter_z_to_a_btn = driver.findElement(By.className("product_sort_container"));
                Select z_to_a = new Select(filter_z_to_a_btn);
                z_to_a.selectByVisibleText("Name (Z to A)");
                Thread.sleep(900);
                WebElement Sauce_Labs_Bolt_T_Shirtbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
                Sauce_Labs_Bolt_T_Shirtbtn.click();
                Thread.sleep(300);
                WebElement Sauce_Labs_Fleece_Jacketbtn = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
                Sauce_Labs_Fleece_Jacketbtn.click();
                Thread.sleep(1000);
                WebElement carticon = driver.findElement(By.className("shopping_cart_link"));
                String actualnumb = carticon.getText();
                Assert.assertEquals(actualnumb, "2");
                carticon.click();
                Thread.sleep(1000);
                WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-fleece-jacket"));
                removeBtn.click();
                Thread.sleep(1000);
                WebElement carticon2 = driver.findElement(By.cssSelector("a.shopping_cart_link"));
                String actualnumb2 = carticon2.getText();
                Assert.assertEquals(actualnumb2, "1");
                Thread.sleep(1000);
                WebElement price = driver.findElement(By.cssSelector("div.inventory_item_price"));
                String actualprice = price.getText();
                Assert.assertEquals(actualprice, "$15.99");
                WebElement checkoutbtn = driver.findElement(By.id("checkout"));
                checkoutbtn.click();
                Thread.sleep(500);
                WebElement firstnamefield = driver.findElement(By.id("first-name"));
                WebElement lastnamefield = driver.findElement(By.id("last-name"));
                WebElement postal_codefield = driver.findElement(By.id("postal-code"));
                slowType(firstnamefield, "Ziad ", 50);
                slowType(lastnamefield, "Ahmed ", 50);
                slowType(postal_codefield, "99999 ", 50);
                WebElement continuebutton = driver.findElement(By.id("continue"));
                continuebutton.click();
                Thread.sleep(1000);
                WebElement finishbutton = driver.findElement(By.id("finish"));
                finishbutton.click();
            }
            @AfterMethod
            public void afterTest() throws InterruptedException {
                Thread.sleep(900);
                WebElement menu_Btn = driver.findElement(By.id("react-burger-menu-btn"));
                menu_Btn.click();
                Thread.sleep(300);
                WebElement resetBtn = driver.findElement(By.id("reset_sidebar_link"));
                Thread.sleep(200);
                resetBtn.click();
                driver.navigate().to("https://www.saucedemo.com/");
            }
        }*/