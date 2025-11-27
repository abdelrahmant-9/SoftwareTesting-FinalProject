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
import java.util.List;
import java.util.Map;

public class CompleteOrder {

    // Constants
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static final String INVENTORY_URL = "https://www.saucedemo.com/inventory.html";
    private static final String CHECKOUT_COMPLETE_URL = "https://www.saucedemo.com/checkout-complete.html";
    private static final String STANDARD_USER = "standard_user";
    private static final String LOCKED_OUT_USER = "locked_out_user";
    private static final String PROBLEM_USER = "problem_user";
    private static final String PERFORMANCE_GLITCH_USER = "performance_glitch_user";
    private static final String ERROR_USER = "error_user";
    private static final String VISUAL_USER = "visual_user";
    private static final String PASSWORD = "secret_sauce";
    private static final int DEFAULT_TIMEOUT = 10;

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = createChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        driver.manage().window().maximize();
        driver.navigate().to(BASE_URL);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ==================== HELPER METHODS ====================

    private WebDriver createChromeDriver() {
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
        return new ChromeDriver(options);
    }

    private void login(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    private void addProductToCart(String productId) {
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id(productId)));
        addButton.click();
    }

    private void removeProductFromCart(String productId) {
        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.id(productId)));
        removeButton.click();
    }

    private void goToCart() {
        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.className("shopping_cart_link")));
        cartIcon.click();
    }

    private String getCartCount() {
        WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
        return cartBadge.getText();
    }

    private void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueButton.click();
    }

    private void completeCheckout() {
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutButton.click();
        fillCheckoutInformation("Mohamed", "Yasser", "35934");
        WebElement finishButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishButton.click();
    }

    private void logout() {
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        menuButton.click();
        WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        logoutLink.click();
    }

    // ==================== TEST CASES ====================

    @Test(priority = 1, description = "Add multiple products to cart and verify count")
    public void addProducts_toCart_updatesCountCorrectly() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        addProductToCart("add-to-cart-test.allthethings()-t-shirt-(red)");
        addProductToCart("add-to-cart-sauce-labs-bike-light");

        Assert.assertEquals(getCartCount(), "3", "Cart should contain 3 items");
    }

    @Test(priority = 2, description = "View product details and verify information matches")
    public void viewProduct_fromCatalog_displaysCorrectDetails() {
        login(STANDARD_USER, PASSWORD);

        WebElement productTitle = driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div"));
        String titleBeforeClick = productTitle.getText();
        productTitle.click();

        WebElement productDetailTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")));
        String titleAfterClick = productDetailTitle.getText();

        WebElement backButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products")));
        backButton.click();

        Assert.assertEquals(titleAfterClick, titleBeforeClick, "Product title should match on detail page");
    }

    @Test(priority = 3, description = "Add product from detail page")
    public void addProduct_fromDetailPage_addsToCart() {
        login(STANDARD_USER, PASSWORD);

        driver.findElement(By.xpath("//*[@id=\"item_2_title_link\"]/div")).click();
        addProductToCart("add-to-cart");

        WebElement backButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products")));
        backButton.click();
    }

    @Test(priority = 4, description = "Remove product from inventory page")
    public void removeProduct_fromInventory_updatesCart() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-test.allthethings()-t-shirt-(red)");
        removeProductFromCart("remove-test.allthethings()-t-shirt-(red)");
    }

    @Test(priority = 5, description = "Navigate to cart and verify count")
    public void navigateToCart_withItems_displaysCorrectCount() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        addProductToCart("add-to-cart-sauce-labs-bike-light");
        addProductToCart("add-to-cart-sauce-labs-onesie");

        goToCart();
        Assert.assertEquals(getCartCount(), "3", "Cart should display 3 items");
    }

    @Test(priority = 6, description = "Remove product from cart page")
    public void removeProduct_fromCartPage_updatesCount() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        addProductToCart("add-to-cart-sauce-labs-bike-light");

        goToCart();
        removeProductFromCart("remove-sauce-labs-bike-light");

        Assert.assertEquals(getCartCount(), "1", "Cart should contain 1 item after removal");
    }

    @Test(priority = 7, description = "Continue shopping from cart maintains cart state")
    public void continueShopping_fromCart_maintainsItems() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        addProductToCart("add-to-cart-sauce-labs-bike-light");

        goToCart();
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue-shopping")));
        continueButton.click();

        Assert.assertEquals(getCartCount(), "2", "Cart should still contain 2 items");
    }

    @Test(priority = 8, description = "Logout and verify redirect to login page")
    public void logout_fromInventory_redirectsToLogin() {
        login(STANDARD_USER, PASSWORD);

        logout();

        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL, "Should redirect to login page after logout");
    }

    @Test(priority = 9, description = "Cart persists after logout and login")
    public void cartPersistence_afterLogoutLogin_maintainsItems() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        addProductToCart("add-to-cart-sauce-labs-bike-light");

        logout();
        login(STANDARD_USER, PASSWORD);

        Assert.assertEquals(getCartCount(), "2", "Cart should persist items after logout/login");
    }

    @Test(priority = 10, description = "Complete basic checkout flow")
    public void checkout_withValidData_completesSuccessfully() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        goToCart();
        completeCheckout();

        Assert.assertEquals(driver.getCurrentUrl(), CHECKOUT_COMPLETE_URL,
                "Should complete checkout successfully");
    }

    @Test(priority = 11, description = "Complete checkout after removing items from cart")
    public void checkout_afterRemovingItems_completesSuccessfully() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        addProductToCart("add-to-cart-sauce-labs-bike-light");
        addProductToCart("add-to-cart-sauce-labs-bolt-t-shirt");
        addProductToCart("add-to-cart-sauce-labs-fleece-jacket");

        goToCart();
        removeProductFromCart("remove-sauce-labs-backpack");
        removeProductFromCart("remove-sauce-labs-bolt-t-shirt");

        completeCheckout();

        Assert.assertEquals(driver.getCurrentUrl(), CHECKOUT_COMPLETE_URL,
                "Should complete checkout after removing items");
    }

    @Test(priority = 12, description = "Complete checkout after removing item from detail page")
    public void checkout_afterRemovingFromDetailPage_completesSuccessfully() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        addProductToCart("add-to-cart-sauce-labs-bike-light");

        goToCart();

        driver.findElement(By.className("inventory_item_name")).click();
        removeProductFromCart("remove");

        WebElement backButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products")));
        backButton.click();

        goToCart();
        completeCheckout();

        Assert.assertEquals(driver.getCurrentUrl(), CHECKOUT_COMPLETE_URL,
                "Should complete checkout after removing from detail page");
    }

    @Test(priority = 13, description = "Checkout with empty cart should not complete")
    public void checkout_withEmptyCart_doesNotComplete() {
        login(STANDARD_USER, PASSWORD);

        goToCart();

        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutButton.click();

        fillCheckoutInformation("Mohamed", "Yasser", "35934");

        WebElement finishButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishButton.click();

        Assert.assertNotEquals(driver.getCurrentUrl(), CHECKOUT_COMPLETE_URL,
                "Should not complete checkout with empty cart");
    }

    @Test(priority = 14, description = "Checkout with whitespace in fields should not complete")
    public void checkout_withWhitespaceInFields_doesNotComplete() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        goToCart();

        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutButton.click();

        driver.findElement(By.id("first-name")).sendKeys("   ");
        driver.findElement(By.id("last-name")).sendKeys("   ");
        driver.findElement(By.id("postal-code")).sendKeys("   ");

        WebElement continueButton = driver.findElement(By.id("continue"));
        Assert.assertFalse(continueButton.isEnabled(), "Continue button should be disabled with whitespace input");
    }

    @Test(priority = 15, description = "Checkout with empty fields shows validation error")
    public void checkout_withEmptyFields_showsValidationError() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        goToCart();

        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutButton.click();

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueButton.click();

        Assert.assertNotEquals(driver.getCurrentUrl(), CHECKOUT_COMPLETE_URL,
                "Should not proceed with empty checkout fields");
    }

    @Test(priority = 16, description = "Reset app state clears cart")
    public void resetAppState_fromMenu_clearsCart() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        addProductToCart("add-to-cart-sauce-labs-bike-light");

        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        menuButton.click();

        WebElement resetLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link")));
        resetLink.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-cross-btn"))).click();

        WebElement addButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("add-to-cart-sauce-labs-backpack")));
        Assert.assertEquals(addButton.getText(), "Add to cart", "Button should show 'Add to cart' after reset");
    }

    @Test(priority = 17, description = "Checkout with removed products from inventory page")
    public void checkout_afterRemovingFromInventory_completesWithRemainingItems() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-fleece-jacket");
        addProductToCart("add-to-cart-sauce-labs-bolt-t-shirt");

        goToCart();

        driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div")).click();
        removeProductFromCart("remove");

        WebElement backButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products")));
        backButton.click();

        goToCart();
        completeCheckout();

        Assert.assertEquals(driver.getCurrentUrl(), CHECKOUT_COMPLETE_URL,
                "Should complete checkout with remaining items");
    }

    @Test(priority = 18, description = "Add item from detail page during checkout")
    public void addItem_duringCheckout_includesNewItem() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        addProductToCart("add-to-cart-sauce-labs-bike-light");

        goToCart();

        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutButton.click();

        fillCheckoutInformation("Tamer", "Tito", "55555");

        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        menuButton.click();

        WebElement inventoryLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("inventory_sidebar_link")));
        inventoryLink.click();

        addProductToCart("add-to-cart-test.allthethings()-t-shirt-(red)");

        goToCart();
        completeCheckout();

        Assert.assertEquals(driver.getCurrentUrl(), CHECKOUT_COMPLETE_URL,
                "Should complete checkout with additional item");
    }

    @Test(priority = 19, description = "Cannot checkout after removing all items from cart")
    public void checkout_afterRemovingAllItems_staysOnCartPage() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-test.allthethings()-t-shirt-(red)");
        addProductToCart("add-to-cart-sauce-labs-onesie");

        goToCart();

        String cartUrl = driver.getCurrentUrl();

        removeProductFromCart("remove-sauce-labs-onesie");
        removeProductFromCart("remove-test.allthethings()-t-shirt-(red)");

        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutButton.click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, cartUrl, "Should remain on cart page when cart is empty");
    }

    @Test(priority = 20, description = "Verify cart badge disappears after completing checkout")
    public void checkout_completion_removesCartBadge() {
        login(STANDARD_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        goToCart();
        completeCheckout();

        try {
            driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
            Assert.fail("Cart badge should not be present after checkout completion");
        } catch (NoSuchElementException e) {
            // Expected - cart badge should not exist after checkout
        }

        WebElement backButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products")));
        backButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), INVENTORY_URL, "Should return to inventory page");
    }

    @Test(priority = 21, description = "Complete checkout with locked_out_user should fail at login")
    public void checkout_withLockedOutUser_failsAtLogin() {
        login(LOCKED_OUT_USER, PASSWORD);

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='error']")));

        Assert.assertTrue(errorMessage.isDisplayed(), "Error message should appear for locked out user");
    }

    @Test(priority = 22, description = "Complete checkout with problem_user")
    public void checkout_withProblemUser_completesSuccessfully() {
        login(PROBLEM_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        goToCart();
        completeCheckout();

        Assert.assertEquals(driver.getCurrentUrl(), CHECKOUT_COMPLETE_URL,
                "Should complete checkout with problem_user");
    }

    @Test(priority = 23, description = "Complete checkout with performance_glitch_user")
    public void checkout_withPerformanceGlitchUser_completesSuccessfully() {
        login(PERFORMANCE_GLITCH_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        goToCart();
        completeCheckout();

        Assert.assertEquals(driver.getCurrentUrl(), CHECKOUT_COMPLETE_URL,
                "Should complete checkout with performance_glitch_user");
    }

    @Test(priority = 24, description = "Complete checkout with error_user")
    public void checkout_withErrorUser_completesSuccessfully() {
        login(ERROR_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        goToCart();
        completeCheckout();

        Assert.assertEquals(driver.getCurrentUrl(), CHECKOUT_COMPLETE_URL,
                "Should complete checkout with error_user");
    }

    @Test(priority = 25, description = "Complete checkout with visual_user")
    public void checkout_withVisualUser_completesSuccessfully() {
        login(VISUAL_USER, PASSWORD);

        addProductToCart("add-to-cart-sauce-labs-backpack");
        goToCart();
        completeCheckout();

        Assert.assertEquals(driver.getCurrentUrl(), CHECKOUT_COMPLETE_URL,
                "Should complete checkout with visual_user");
    }

    @Test(priority = 26, description = "View and add product in single flow")
    public void viewAndAddProduct_fromDetailPage_addsToCart() {
        login(STANDARD_USER, PASSWORD);

        driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).click();
        addProductToCart("add-to-cart");

        WebElement backButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products")));
        backButton.click();

        addProductToCart("add-to-cart-sauce-labs-bike-light");
        goToCart();

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        Assert.assertFalse(cartItems.isEmpty(), "Cart should contain items");
    }
}

