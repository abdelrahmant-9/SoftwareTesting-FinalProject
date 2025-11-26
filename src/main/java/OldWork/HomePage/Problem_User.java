package OldWork.HomePage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_User {
    WebDriver driver;

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
        driver.navigate().to("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("problem_user");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
    }
//adding to cart
    @Test(priority = 1)
    public void add_Backpack() throws InterruptedException {
        WebElement addBtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addBtn.click();
        Thread.sleep(1000);
        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Assert.assertEquals(removeBtn.getText(),"Remove");
        Thread.sleep(1000);
    }

    @Test(priority = 2)
    public void add_Bike_Light() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(500);
        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-bike-light"));
        Assert.assertEquals(removeBtn.getText(),"Remove");
        Thread.sleep(500);
    }

    @Test(priority = 3)
    public void add_Jacket() throws InterruptedException
    {

        WebElement addBtn = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        addBtn.click();
        Thread.sleep(1000);

        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-fleece-jacket"));
        Assert.assertEquals(removeBtn.getText(),"Remove");
        Thread.sleep(1000);
        removeBtn.click();
        Thread.sleep(1000);
    }

    @Test(priority = 4)
    public void add_Onesie() throws InterruptedException
    {

        WebElement addBtn = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
        addBtn.click();
        Thread.sleep(1000);

        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-onesie"));
        Assert.assertEquals(removeBtn.getText(),"Remove");
        Thread.sleep(1000);
        removeBtn.click();
        Thread.sleep(1000);
    }

    @Test(priority = 5)
    public void add_Bolt_T_shirt() throws InterruptedException {
        WebElement addBtn = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        addBtn.click();
        Thread.sleep(1000);
        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Assert.assertEquals(removeBtn.getText(),"Remove");
        Thread.sleep(1000);
    }

    @Test(priority = 6)
    public void add_Test_allThings() throws InterruptedException
    {

        WebElement addBtn = driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"));
        addBtn.click();
        Thread.sleep(1000);

        WebElement removeBtn = driver.findElement(By.id("remove-test.allthethings()-t-shirt-(red)"));
        Assert.assertEquals(removeBtn.getText(),"Remove");
        Thread.sleep(1000);
        removeBtn.click();
        Thread.sleep(1000);
    }

    @Test(priority = 7)
    public void Remove() throws InterruptedException {
        WebElement addBtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addBtn.click();
        Thread.sleep(1000);
        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-backpack"));
        removeBtn.click();
        Assert.assertEquals(addBtn.getText(),"Add to cart");
        Thread.sleep(1000);
    }

 /*   @Test(priority = 8)
    public void verifyProductDetailsPages() throws InterruptedException {
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        for (int i = 0; i < products.size(); i++) {
            driver.findElements(By.className("inventory_item_name")).get(i).click();
            Thread.sleep(500);

            Assert.assertFalse(driver.findElement(By.className("inventory_details_name")).getText().isEmpty());
            Assert.assertFalse(driver.findElement(By.className("inventory_details_price")).getText().isEmpty());
            Assert.assertFalse(driver.findElement(By.className("inventory_details_img")).getAttribute("src").isEmpty());
            Assert.assertFalse(driver.findElement(By.className("inventory_details_desc")).getText().isEmpty());

            driver.findElement(By.id("back-to-products")).click();
            Thread.sleep(500);
        }
    }*/

    @Test(priority = 8)
    public void verifyCartQuantityUpdates() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(500);
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
        Assert.assertTrue(cartIcon.getText().contains("1"));

        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        Thread.sleep(500);
        Assert.assertEquals(cartIcon.getText(), "");
    }

    @Test(priority = 9)
    public void verifySortingLowToHigh() throws InterruptedException {
        Select sort = new Select(driver.findElement(By.className("product_sort_container")));
        sort.selectByVisibleText("Price (low to high)");
        Thread.sleep(500);

        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        for (int i = 0; i < prices.size() - 1; i++) {
            double current = Double.parseDouble(prices.get(i).getText().replace("$",""));
            double next = Double.parseDouble(prices.get(i+1).getText().replace("$",""));
            Assert.assertTrue(current <= next);
        }
    }

    @Test(priority = 10)
    public void verifySortingHighToLow() throws InterruptedException {
        Select sort = new Select(driver.findElement(By.className("product_sort_container")));
        sort.selectByVisibleText("Price (high to low)");
        Thread.sleep(500);

        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        for (int i = 0; i < prices.size() - 1; i++) {
            double current = Double.parseDouble(prices.get(i).getText().replace("$",""));
            double next = Double.parseDouble(prices.get(i+1).getText().replace("$",""));
            Assert.assertTrue(current >= next);
        }
    }

    @Test(priority = 11)
    public void verifyNoDuplicateProducts() throws InterruptedException {
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        for (int i = 0; i < products.size(); i++) {
            String name1 = products.get(i).getText();
            for (int j = i+1; j < products.size(); j++) {
                String name2 = products.get(j).getText();
                Assert.assertNotEquals(name1, name2, "Duplicate product found: " + name1);
            }
        }
        Thread.sleep(500);
    }



    @AfterMethod
    public void close() throws InterruptedException
    {
        Thread.sleep(1000);
        driver.close();
    }
}
