package HomePage;


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

import java.util.*;

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
        WebElement sorting = driver.findElement(By.className("product_sort_container"));
        Select option = new Select(sorting);
        Assert.assertFalse(option.isMultiple());
        Assert.assertEquals(4, option.getOptions().size());

        Thread.sleep(1000);
        option.selectByVisibleText("Name (Z to A)");
        Thread.sleep(1000);

        List<WebElement>  afterFilter = driver.findElements(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[2]"));
        List<String> name = new ArrayList<>();
        for( WebElement idx : afterFilter) name.add(idx.getText()); //here name has items after sorting
        List<String> defValue = new ArrayList<>(name);
        Collections.sort(defValue); //return item to initial value to compare them
        Assert.assertEquals(name, defValue);
    }

    @Test(dependsOnMethods = "Z_A_sort", priority = 8)
    public void A_Z_sort() throws InterruptedException {
        WebElement sorting = driver.findElement(By.className("product_sort_container"));
        Select option = new Select(sorting);
        Assert.assertFalse(option.isMultiple());
        Assert.assertEquals(4, option.getOptions().size());

        Thread.sleep(1000);
        option.selectByVisibleText("Name (A to Z)");
        Thread.sleep(1000);

        List<WebElement>  afterFilter = driver.findElements(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[1]"));
        List<String> name = new ArrayList<>();
        for( WebElement idx : afterFilter) name.add(idx.getText()); //here name has items after sorting
        List<String> defValue = new ArrayList<>(name);
        Collections.sort(defValue);
        Assert.assertEquals(name, defValue);
        Thread.sleep(1000);

    }
    @Test(priority = 9)
    public void Low_High_sort() throws InterruptedException {
        WebElement sorting = driver.findElement(By.className("product_sort_container"));
        Select option = new Select(sorting);
        Assert.assertFalse(option.isMultiple());
        Assert.assertEquals(4, option.getOptions().size());

        Thread.sleep(1000);
        option.selectByVisibleText("Price (low to high)");
        Thread.sleep(1000);

        List<WebElement>  afterFilter = driver.findElements(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[3]"));
        List<String> name = new ArrayList<>();
        for( WebElement idx : afterFilter) name.add(idx.getText()); //here name has items after sorting
        List<String> defValue = new ArrayList<>(name);
        Collections.sort(defValue); //return item to initial value to compare them
        Assert.assertEquals(name, defValue);
    }

    @Test(priority = 10)
    public void High_Low_sort() throws InterruptedException {
        WebElement sorting = driver.findElement(By.className("product_sort_container"));
        Select option = new Select(sorting);
        Assert.assertFalse(option.isMultiple());
        Assert.assertEquals(4, option.getOptions().size());

        Thread.sleep(1000);
        option.selectByVisibleText("Price (high to low)");
        Thread.sleep(1000);

        List<WebElement>  afterFilter = driver.findElements(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[4]"));
        List<String> name = new ArrayList<>();
        for( WebElement idx : afterFilter) name.add(idx.getText()); //here name has items after sorting
        List<String> defValue = new ArrayList<>(name);
        Collections.sort(defValue); //return item to initial value to compare them
        Assert.assertEquals(name, defValue);
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

    @AfterMethod
    public void close() throws InterruptedException
    {
        Thread.sleep(1000);
        driver.close();
    }


}
