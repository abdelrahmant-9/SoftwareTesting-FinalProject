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

public class Standard_User
{

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
            driver.manage().window().maximize();

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            Thread.sleep(1000);
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            Thread.sleep(1000);
            driver.findElement(By.id("login-button")).click();
            Thread.sleep(1000);

    }

    //ADD TO CART

    @Test(priority = 1)
    public void add_Backpack() throws InterruptedException {

       WebElement addBtn =driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
       addBtn.click();
        Thread.sleep(1000);

        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Assert.assertEquals(removeBtn.getText(),"Remove");
        Thread.sleep(1000);
        removeBtn.click();
        Thread.sleep(1000);
    }
    @Test(priority = 2)
    public void add_Bike_light() throws InterruptedException {

       WebElement addBtn = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
                addBtn.click();
        Thread.sleep(1000);

        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-bike-light"));
        Assert.assertEquals(removeBtn.getText(),"Remove");
        Thread.sleep(1000);
        removeBtn.click();
        Thread.sleep(1000);
    }
    @Test(priority = 3)
    public void add_Tshirt() throws InterruptedException
    {

       WebElement addBtn =  driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        addBtn.click();
        Thread.sleep(1000);

        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt"));
        Assert.assertEquals(removeBtn.getText(),"Remove");
        Thread.sleep(1000);
        removeBtn.click();
        Thread.sleep(1000);
    }
    @Test(priority = 4)
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
    @Test(priority = 5)
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

    //DROPDOWN MENU CHECHING

    @Test(priority = 7)
    public void Z_A_sort() throws InterruptedException {
        Select sort = new Select(driver.findElement(By.className("product_sort_container")));
        sort.selectByVisibleText("Name (Z to A)");
        Thread.sleep(1000);

        List<WebElement> names = driver.findElements(By.className("inventory_item_name"));
        for(int i = 0; i < names.size()-1; i++){
            String current = names.get(i).getText();
            String next = names.get(i+1).getText();
            Assert.assertTrue(current.compareTo(next) > 0);
    }
    }

    @Test(priority = 8)
    public void A_Z_sort() throws InterruptedException {
            Select sort = new Select(driver.findElement(By.className("product_sort_container")));
            sort.selectByVisibleText("Name (A to Z)");
            Thread.sleep(1000);

            List<WebElement> names = driver.findElements(By.className("inventory_item_name"));
            for(int i = 0; i < names.size()-1; i++){
                String current = names.get(i).getText();
                String next = names.get(i+1).getText();
                Assert.assertTrue(current.compareTo(next) < 0);
            }
    }
    @Test(priority = 9)
    public void Low_High_sort() throws InterruptedException {
    Select sort = new Select(driver.findElement(By.className("product_sort_container")));
    sort.selectByVisibleText("Price (low to high)");
    Thread.sleep(1000);

    List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
    for(int i = 0; i < prices.size()-1; i++){
        double current = Double.parseDouble(prices.get(i).getText().replace("$",""));
        double next = Double.parseDouble(prices.get(i+1).getText().replace("$",""));
        Assert.assertTrue(current <= next);
    }



    }

    @Test(priority = 10)
    public void High_Low_sort() throws InterruptedException {
            Select sort = new Select(driver.findElement(By.className("product_sort_container")));
            sort.selectByVisibleText("Price (high to low)");
            Thread.sleep(1000);

            List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
            for(int i = 0; i < prices.size()-1; i++){
                double current = Double.parseDouble(prices.get(i).getText().replace("$",""));
                double next = Double.parseDouble(prices.get(i+1).getText().replace("$",""));
                Assert.assertTrue(current >= next);
            }
    }

    //product description page

    @Test(priority = 11)
    public void check_product_page() throws InterruptedException
    {
        driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).click();
        Thread.sleep(1000);
        WebElement addBtn = driver.findElement(By.id("add-to-cart"));
        addBtn.click();
        WebElement removeBtn = driver.findElement(By.id("remove"));
        Assert.assertEquals(removeBtn.getText(),"Remove");
        Thread.sleep(1000);

        driver.findElement(By.id("back-to-products")).click();
        Thread.sleep(500);
    }

    @Test(priority = 12)
    public void Bike_light_checkName() throws  InterruptedException{
            String item = driver.findElement(By.xpath("//*[@id=\"item_0_title_link\"]/div")).getText();
            driver.findElement(By.xpath("//*[@id=\"item_0_title_link\"]/div")).click();
            Thread.sleep(1000);

            String product_name = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]"))
                    .getText();
            Assert.assertEquals(product_name, item);
}

    @Test(priority = 13)
    public void Backpack_checkName() throws  InterruptedException{
        String item = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();
        driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).click();
        Thread.sleep(1000);

        String product_name = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]"))
                .getText();
        Assert.assertEquals(product_name, item);
    }

    @Test(priority = 14)
    public void   Bolt_T_Shirt_checkName() throws  InterruptedException{
        String item = driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]/div")).getText();
        driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]/div")).click();
        Thread.sleep(1000);

        String product_name = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]"))
                .getText();
        Assert.assertEquals(product_name, item);
    }

    @Test(priority = 15)
    public void   Fleece_Jacket_checkName() throws  InterruptedException{
        String item = driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div")).getText();
        driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div")).click();
        Thread.sleep(1000);

        String product_name = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]"))
                .getText();
        Assert.assertEquals(product_name, item);
    }

    @Test(priority = 16)
    public void   Onesie_checkName() throws  InterruptedException{
        String item = driver.findElement(By.xpath("//*[@id=\"item_2_title_link\"]/div")).getText();
        driver.findElement(By.xpath("//*[@id=\"item_2_title_link\"]/div")).click();
        Thread.sleep(1000);

        String product_name = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]"))
                .getText();
        Assert.assertEquals(product_name, item);
    }

    @Test(priority = 17)
    public void   Test_allTheThings_checkName() throws  InterruptedException{
        String item = driver.findElement(By.xpath("//*[@id=\"item_3_title_link\"]/div")).getText();
        driver.findElement(By.xpath("//*[@id=\"item_3_title_link\"]/div")).click();
        Thread.sleep(1000);

        String product_name = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]"))
                .getText();
        Assert.assertEquals(product_name, item);
    }


    @AfterMethod
    public void close() throws InterruptedException
    {
        Thread.sleep(1000);
        driver.close();
    }


}
