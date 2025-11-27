package integration.HomePage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home_Cart_Integration {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("https://www.saucedemo.com/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
    }

    @Test(priority = 1)
    public void home_firstItem_cart_remove() {
        WebElement firstItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='inventory_item_name'])[1]")));
        String name = firstItem.getText();
        firstItem.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'add-to-cart')]"))).click();

        driver.findElement(By.className("shopping_cart_link")).click();
        Assert.assertEquals(driver.findElement(By.className("inventory_item_name")).getText(), name);

        driver.findElement(By.xpath("//button[contains(@id,'remove')]")).click();

        List<WebElement> itemsInCart = driver.findElements(By.className("inventory_item_name"));
        Assert.assertEquals(itemsInCart.size(), 0);
    }

    @Test(priority = 2)
    public void sortLowToHigh_firstItem_cart_remove() {
        Select sort = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.className("product_sort_container"))));
        sort.selectByVisibleText("Price (low to high)");

        WebElement first = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='inventory_item_name'])[1]")));
        String expected = first.getText();
        first.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'add-to-cart')]"))).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        Assert.assertEquals(driver.findElement(By.className("inventory_item_name")).getText(), expected);

        driver.findElement(By.xpath("//button[contains(@id,'remove')]")).click();
        Assert.assertEquals(driver.findElements(By.className("inventory_item_name")).size(), 0);
    }

    @Test(priority = 3)
    public void lastItem_details_cart_remove() {
        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_name")));
        WebElement last = items.get(items.size() - 1);
        String name = last.getText();
        last.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'add-to-cart')]"))).click();

        driver.findElement(By.id("back-to-products")).click();

        driver.findElement(By.className("shopping_cart_link")).click();
        Assert.assertEquals(driver.findElement(By.className("inventory_item_name")).getText(), name);

        driver.findElement(By.xpath("//button[contains(@id,'remove')]")).click();
        Assert.assertEquals(driver.findElements(By.className("inventory_item_name")).size(), 0);
    }
    @Test(priority = 4)
    public void addMultiple_cart_remove() {
        String[] products = {"sauce-labs-backpack","sauce-labs-bike-light","sauce-labs-bolt-t-shirt"};
        for(String p: products){
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-" + p))).click();
        }
        driver.findElement(By.className("shopping_cart_link")).click();

        List<WebElement> cartItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_name")));
        Assert.assertEquals(cartItems.size(), products.length);

        // Remove all
        for(String p: products){
            driver.findElement(By.id("remove-" + p)).click();
        }

        Assert.assertEquals(driver.findElements(By.className("inventory_item_name")).size(), 0);
    }
    @Test(priority = 5)
    public void filterAdd_cart_remove() {
        Select sort = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.className("product_sort_container"))));
        sort.selectByVisibleText("Name (Z to A)");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@id,'add')])[1]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@id,'add')])[2]"))).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_name")));
        Assert.assertEquals(items.size(), 2);

        driver.findElement(By.xpath("(//button[contains(@id,'remove')])[1]")).click();
        driver.findElement(By.xpath("(//button[contains(@id,'remove')])[2]")).click();

        Assert.assertEquals(driver.findElements(By.className("inventory_item_name")).size(), 0);
    }

    @Test(priority = 6)
    public void middleAndFirst_cart_remove() {
        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_name")));
        int mid = items.size() / 2;

        items.get(mid).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'add')]"))).click();

        driver.findElement(By.id("back-to-products")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        List<WebElement> cartItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_name")));
        Assert.assertEquals(cartItems.size(), 2);

        driver.findElement(By.xpath("(//button[contains(@id,'remove')])[1]")).click();
        driver.findElement(By.xpath("(//button[contains(@id,'remove')])[2]")).click();

        Assert.assertEquals(driver.findElements(By.className("inventory_item_name")).size(), 0);
    }

    @Test(priority = 7)
    public void firstAndLast_cart_remove() {
        WebElement first = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='inventory_item_name'])[1]")));
        first.click();
        driver.findElement(By.xpath("//button[contains(@id,'add')]")).click();

        driver.findElement(By.id("back-to-products")).click();

        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_name")));
        WebElement last = items.get(items.size() - 1);
        last.click();
        driver.findElement(By.xpath("//button[contains(@id,'add')]")).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        List<WebElement> cartItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_name")));
        Assert.assertEquals(cartItems.size(), 2);

        driver.findElement(By.xpath("(//button[contains(@id,'remove')])[1]")).click();
        driver.findElement(By.xpath("(//button[contains(@id,'remove')])[2]")).click();

        Assert.assertEquals(driver.findElements(By.className("inventory_item_name")).size(), 0);
    }

    @Test(priority = 8)
    public void addJacket_cart_remove() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-fleece-jacket"))).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        List<WebElement> cartItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_name")));
        Assert.assertEquals(cartItems.size(), 1);

        driver.findElement(By.id("remove-sauce-labs-fleece-jacket")).click();
        Assert.assertEquals(driver.findElements(By.className("inventory_item_name")).size(), 0);
    }

    @Test(priority = 10)
    public void addTestAllTheThings_cart_remove() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"))).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        Assert.assertEquals(driver.findElements(By.className("inventory_item_name")).size(), 1);

        driver.findElement(By.id("remove-test.allthethings()-t-shirt-(red)")).click();
        Assert.assertEquals(driver.findElements(By.className("inventory_item_name")).size(), 0);
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}
