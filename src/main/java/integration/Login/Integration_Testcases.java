package integration.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

public class Integration_Testcases {
    ChromeOptions options;
    Map<String, Object> prefs;
    WebDriver driver;  {
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
    @BeforeTest
    public void beforeTest() {
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

    }

    @Test(priority=1)
    public void SauceLabsBikeLight() throws InterruptedException {
        WebElement usernamefield = driver.findElement(By.id("user-name"));
        WebElement passwordfield = driver.findElement(By.id("password"));
        WebElement loginbutton = driver.findElement(By.id("login-button"));
        slowType(usernamefield,"visual_user" ,50);
        slowType(passwordfield,"secret_sauce" ,50);
        loginbutton.click();
        Thread.sleep(2000);
        WebElement addproductbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        addproductbtn.click();
        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-bike-light"));
        String actualtext= removeBtn.getText();
        Assert.assertEquals(actualtext,"Remove");
    }
    @Test(priority=2)
    public void SauceLabsBackpack() throws InterruptedException {
        WebElement usernamefield = driver.findElement(By.id("user-name"));
        WebElement passwordfield = driver.findElement(By.id("password"));
        WebElement loginbutton = driver.findElement(By.id("login-button"));
        slowType(usernamefield,"visual_user" ,50);
        slowType(passwordfield,"secret_sauce" ,50);
        loginbutton.click();
        Thread.sleep(1000);
        WebElement filter_pricebtn = driver.findElement(By.className("product_sort_container"));
        Select high_to_lowprice = new Select(filter_pricebtn);
        Thread.sleep(1000);
        high_to_lowprice.selectByVisibleText("Price (high to low)");
        Thread.sleep(200);
        WebElement addproductbtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addproductbtn.click();
        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-backpack"));
        String actualtext= removeBtn.getText();
        Assert.assertEquals(actualtext,"Remove");
        Thread.sleep(1000);
    }
    @Test(priority=3)
    public void Add2products() throws InterruptedException {
        WebElement usernamefield = driver.findElement(By.id("user-name"));
        WebElement passwordfield = driver.findElement(By.id("password"));
        WebElement loginbutton = driver.findElement(By.id("login-button"));
        slowType(usernamefield, "visual_user", 50);
        slowType(passwordfield, "secret_sauce", 50);
        loginbutton.click();
        Thread.sleep(1000);
        WebElement filter_pricebtn = driver.findElement(By.className("product_sort_container"));
        Select low_to_highprice = new Select(filter_pricebtn);
        low_to_highprice.selectByVisibleText("Price (low to high)");
        Thread.sleep(1000);
        WebElement SauceLabsBoltT_Shirtbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        SauceLabsBoltT_Shirtbtn.click();
        WebElement Test_allTheThings_T_Shirt_Redbtn = driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"));
        Test_allTheThings_T_Shirt_Redbtn.click();
        Thread.sleep(1000);
        WebElement carticon = driver.findElement(By.className("shopping_cart_badge"));
        String actualnumb = carticon.getText();
        Assert.assertEquals(actualnumb, "2");
        carticon.click();
    }
    @Test(priority=4)
    public void productDetails() throws InterruptedException {
        WebElement usernamefield = driver.findElement(By.id("user-name"));
        WebElement passwordfield = driver.findElement(By.id("password"));
        WebElement loginbutton = driver.findElement(By.id("login-button"));
        slowType(usernamefield, "visual_user", 50);
        slowType(passwordfield, "secret_sauce", 50);
        loginbutton.click();
        Thread.sleep(1000);
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
    }

    @Test(priority=5)
    public void startcheckout() throws InterruptedException {
        WebElement usernamefield = driver.findElement(By.id("user-name"));
        WebElement passwordfield = driver.findElement(By.id("password"));
        WebElement loginbutton = driver.findElement(By.id("login-button"));
        slowType(usernamefield, "visual_user", 50);
        slowType(passwordfield, "secret_sauce", 50);
        loginbutton.click();
        Thread.sleep(1000);
        WebElement filter_z_to_a_btn = driver.findElement(By.className("product_sort_container"));
        Select z_to_a = new Select(filter_z_to_a_btn);
        z_to_a.selectByVisibleText("Name (Z to A)");
        Thread.sleep(1000);
        WebElement Sauce_Labs_Onesiebtn = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
        Sauce_Labs_Onesiebtn.click();
        WebElement Sauce_Labs_Bolt_T_Shirtbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        Sauce_Labs_Bolt_T_Shirtbtn.click();
        WebElement Sauce_Labs_Fleece_Jacketbtn = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        Sauce_Labs_Fleece_Jacketbtn.click();
        Thread.sleep(1000);
        WebElement carticon = driver.findElement(By.className("shopping_cart_link"));
        String actualnumb = carticon.getText();
        Assert.assertEquals(actualnumb, "3");
        carticon.click();
        Thread.sleep(1000);
        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-fleece-jacket"));
        removeBtn.click();
        Thread.sleep(1000);
        WebElement carticon2 = driver.findElement(By.cssSelector("a.shopping_cart_link"));
        String actualnumb2 = carticon2.getText();
         Assert.assertEquals(actualnumb2, "2");
        Thread.sleep(1000);
        WebElement checkoutbtn = driver.findElement(By.id("checkout"));
        checkoutbtn.click();
    }

    @Test(priority=6)
    public void Wrongprice_within_product () throws InterruptedException {
        WebElement usernamefield = driver.findElement(By.id("user-name"));
        WebElement passwordfield = driver.findElement(By.id("password"));
        WebElement loginbutton = driver.findElement(By.id("login-button"));
        slowType(usernamefield, "visual_user", 50);
        slowType(passwordfield, "secret_sauce", 50);
        loginbutton.click();
        Thread.sleep(1000);
        WebElement Sauce_Labs_Backpackbtn = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div"));
        Sauce_Labs_Backpackbtn.click();
        WebElement wrongprice = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div"));
        String wrongpriceText = wrongprice.getText();
        Assert.assertEquals(wrongpriceText, "$29.99");
    }
@Test(priority=7)
public void nameMismatch_problemuser() throws InterruptedException {
        try {
        WebElement usernamefield = driver.findElement(By.id("user-name"));
        WebElement passwordfield = driver.findElement(By.id("password"));
        WebElement loginbutton = driver.findElement(By.id("login-button"));
        slowType(usernamefield, "problem_user", 50);
        slowType(passwordfield, "secret_sauce", 50);
        loginbutton.click();
        Thread.sleep(300);
        WebElement Sauce_Labs_Bolt_TShirtproduct = driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]/div"));
        Sauce_Labs_Bolt_TShirtproduct.click();
        WebElement Sauce_Labs_Onesieproduct = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]"));
        Assert.assertEquals(Sauce_Labs_Bolt_TShirtproduct, Sauce_Labs_Onesieproduct);
        WebElement addproductbutton = driver.findElement(By.id("add-to-cart"));
        addproductbutton.click();
        Thread.sleep(500);
        String removeproductbutton = addproductbutton.getText();
        Assert.assertEquals(removeproductbutton, "Remove");
        } catch (Exception wrong) {
        System.out.println(" Error happened: " + wrong.getMessage());
        wrong.printStackTrace();
        Assert.fail("Test failed the exception is : " + wrong.getMessage());
    }
}
@Test(priority=8)
public void wrong_image() throws InterruptedException {
    WebElement usernamefield = driver.findElement(By.id("user-name"));
    WebElement passwordfield = driver.findElement(By.id("password"));
    WebElement loginbutton = driver.findElement(By.id("login-button"));
    slowType(usernamefield, "problem_user", 50);
    slowType(passwordfield, "secret_sauce", 50);
    loginbutton.click();
    Thread.sleep(300);
    WebElement imgoutsideSauce_Labs_Backpack = driver.findElement(By.cssSelector("img.inventory_item_img"));
    String outsideimg = imgoutsideSauce_Labs_Backpack.getAttribute("src");
    WebElement Sauce_Labs_Backpackbtn = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div"));
    Sauce_Labs_Backpackbtn.click();
    Thread.sleep(500);
    WebElement insideimgSauce_Labs_Backpack = driver.findElement(By.cssSelector("img.inventory_details_img"));
    String insideimg =  insideimgSauce_Labs_Backpack.getAttribute("src");
    Assert.assertEquals(insideimg, outsideimg);
}
@Test(priority=9)
public void wrongURL() throws InterruptedException {
    WebElement usernamefield = driver.findElement(By.id("user-name"));
    WebElement passwordfield = driver.findElement(By.id("password"));
    WebElement loginbutton = driver.findElement(By.id("login-button"));
    slowType(usernamefield, "error_user", 50);
    slowType(passwordfield, "secret_sauce", 50);
    loginbutton.click();
    Thread.sleep(300);
    WebElement Sauce_Labs_Backpackbtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
    Sauce_Labs_Backpackbtn.click();
    WebElement Sauce_Labs_Bike_Lightbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
    Sauce_Labs_Bike_Lightbtn.click();
    WebElement Sauce_Labs_Bolt_T_Shirttbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
    Sauce_Labs_Bolt_T_Shirttbtn.click();
    Thread.sleep(300);
    WebElement carticon = driver.findElement(By.cssSelector("a.shopping_cart_link"));
    carticon.click();
    Thread.sleep(2000);
    WebElement checkoutbutton = driver.findElement(By.id("checkout"));
    checkoutbutton.click();
    Thread.sleep(200);
    WebElement firstnamefield = driver.findElement(By.id("first-name"));
    WebElement lastnamefield = driver.findElement(By.id("last-name"));
    WebElement postal_code_field = driver.findElement(By.id("postal-code"));
    slowType(firstnamefield,"alaa",50);
    slowType(lastnamefield,"mohammed",50);
    slowType(postal_code_field,"70522",50);
    Thread.sleep(1000);
    WebElement continuebutton = driver.findElement(By.id("continue"));
    continuebutton.click();
    String  currentURL = driver.getCurrentUrl();
    String expectedURL = "https://www.saucedemo.com/checkout-step-one.html";
    Assert.assertEquals(currentURL, expectedURL);
}
    @Test(priority=10)
    public void Wrong_URL2() throws InterruptedException {
        SoftAssert soft = new SoftAssert();
        try {
            WebElement usernamefield = driver.findElement(By.id("user-name"));
            WebElement passwordfield = driver.findElement(By.id("password"));
            WebElement loginbutton = driver.findElement(By.id("login-button"));
            slowType(usernamefield, "performance_glitch_user", 50);
            slowType(passwordfield, "secret_sauce", 50);
            loginbutton.click();
            Thread.sleep(300);
            WebElement Sauce_Labs_Backpackbtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
            Sauce_Labs_Backpackbtn.click();
            WebElement Sauce_Labs_Bike_Lightbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
            Sauce_Labs_Bike_Lightbtn.click();
            Thread.sleep(300);
            WebElement Sauce_Labs_Bolt_T_Shirttbtn = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
            Sauce_Labs_Bolt_T_Shirttbtn.click();
            Thread.sleep(300);
            WebElement Sauce_Labs_Fleece_Jacketbtn = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
            Sauce_Labs_Fleece_Jacketbtn.click();
            Thread.sleep(300);
            WebElement carticon = driver.findElement(By.cssSelector("a.shopping_cart_link"));
            carticon.click();
            Thread.sleep(200);
            WebElement Sauce_Labs_Backpack_remove = driver.findElement(By.id("remove-sauce-labs-backpack"));
            Sauce_Labs_Backpack_remove.click();
            Thread.sleep(200);
            WebElement Sauce_Labs_Bike_Light_remove = driver.findElement(By.id("remove-sauce-labs-bike-light"));
            Sauce_Labs_Bike_Light_remove.click();
            Thread.sleep(200);
            WebElement Sauce_Labs_Bolt_T_Shirt_remove = driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt"));
            Sauce_Labs_Bolt_T_Shirt_remove.click();
            Thread.sleep(200);
            WebElement Sauce_Labs_Fleece_Jacke_remove = driver.findElement(By.id("remove-sauce-labs-fleece-jacket"));
            Sauce_Labs_Fleece_Jacke_remove.click();
            Thread.sleep(200);
            WebElement checkoutbutton = driver.findElement(By.id("checkout"));
            checkoutbutton.click();
            Thread.sleep(200);
            String currentURL = driver.getCurrentUrl();
            String expectedURL = "https://www.saucedemo.com/cart.html";
            soft.assertEquals(currentURL, expectedURL);
            Thread.sleep(500);
            WebElement firstnamefield = driver.findElement(By.id("first-name"));
            WebElement lastnamefield = driver.findElement(By.id("last-name"));
            WebElement postal_code_field = driver.findElement(By.id("postal-code"));
            slowType(firstnamefield, "alaa", 50);
            slowType(lastnamefield, "Ahmed", 50);
            slowType(postal_code_field, "01010", 50);
            Thread.sleep(1000);
            WebElement continuebutton = driver.findElement(By.id("continue"));
            continuebutton.click();
            Thread.sleep(1000);
        } catch (Exception eror) {
            System.out.println(" Error occurred: " + eror.getMessage());
            eror.printStackTrace();
            Assert.fail("Test failed the exception is : " + eror.getMessage());
            }
        soft.assertAll();
            }

    @AfterMethod
    public void afterTest() throws InterruptedException {
        Thread.sleep(1000);
        WebElement menu_Btn = driver.findElement(By.id("react-burger-menu-btn"));
        menu_Btn.click();
        Thread.sleep(300);
        WebElement resetBtn = driver.findElement(By.id("reset_sidebar_link"));
        Thread.sleep(200);
        resetBtn.click();
        driver.navigate().to("https://www.saucedemo.com/");
    }
    @AfterMethod
    public void afterClass() throws InterruptedException {
        driver.quit();
    }
}


