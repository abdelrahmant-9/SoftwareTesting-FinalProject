package Unit.RemainingPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Login {

    WebDriver driver;

    public void slowType(WebElement element, String text, int delay) throws InterruptedException {
        for(char letter : text.toCharArray()) {
            element.sendKeys(Character.toString(letter));
            Thread.sleep(delay);
        }
    }

    @BeforeTest
    public void beforeTest () {

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test
    public void loginwith_standard_user() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        slowType(usernameField,"standard_user",70);
        slowType(passwordField,"secret_sauce",70);
        loginBtn.click();
    }

    @Test
    public void loginwith_problem_user() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        slowType(usernameField,"problem_user",70);
        slowType(passwordField,"secret_sauce",70);
        loginBtn.click();
    }

    @Test
    public void loginwith_locked_out_user() throws InterruptedException {
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        slowType(username,"locked_out_user",70);
        slowType(password,"secret_sauce",70);
        loginBtn.click();
    }

    @Test
    public void loginwith_performance_glitch_user() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        slowType(usernameField, "performance_glitch_user", 70);
        slowType(passwordField, "secret_sauce", 70);
        loginBtn.click();
    }

    @Test
    public void loginwith_error_user() throws InterruptedException {
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement  password = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        slowType(username,"error_user",70);
        slowType(password,"secret_sauce",70);
        loginBtn.click();
    }

    @Test
    public void loginwith_visual_user() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField =  driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        slowType(usernameField,"visual_user",70);
        slowType(passwordField,"secret_sauce",70);
        loginBtn.click();
    }

    @Test
    public void loginwithEmptypassword() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        slowType(usernameField,"standard_user",70);
        slowType(passwordField,"  ",70);
        loginBtn.click();
    }

    @Test
    public void loginwith_specialcharacter() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        slowType(usernameField,"kar$@im",70);
        slowType(passwordField,"st&!sauc",70);
        loginBtn.click();
    }

    @AfterMethod
    public void aftermethod() throws InterruptedException {
        Thread.sleep(3000);
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @AfterTest
    public void aftertest(){
        driver.quit();
    }
}
