package com.zebrunner.carina.demo;

import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.demo.gui.pages.desktop.sauce.Enums.SortValue;
import com.zebrunner.carina.demo.gui.pages.desktop.sauce.SauceCart;
import com.zebrunner.carina.demo.gui.pages.desktop.sauce.SauceCheckOut;
import com.zebrunner.carina.demo.gui.pages.desktop.sauce.SauceDemoLogIn;
import com.zebrunner.carina.demo.gui.pages.desktop.sauce.SauceInventory;
import com.zebrunner.carina.demo.gui.pages.desktop.sauce.entities.Item;
import com.zebrunner.carina.demo.gui.pages.desktop.sauce.exceptions.SauceException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;

/**
 * Implement at least 5 web tests (more is possible) using the following test cases:
 * (You must find the site/sites for testing yourself. Perhaps one site will not satisfy all test cases, then use several sites. )
 *
 * Checking the filter by parameters.
 * Checking changes to user data (change in username, age or other available data).
 * Search check (Perform a search and check that the products found match)
 * Check opening of the desired product category (3 categories in the test)
 * Your test case (if your site has specific functionality, you can check it, come up with a test case with at least 3 checks and write a test)
 * You come up with the checks yourself, try to make at least 3 in each test, but you don't need to add extra checks, only those that suit the test cases. Learn the difference between Assert and SoftAssert and use them correctly. Also pay closer attention to locators for elements, I want to see short and stable ones.*/

public class WebTestLuna implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final SoftAssert softAssert = new SoftAssert();

    @Test()
    @MethodOwner(owner = "Luna")
    public WebDriver testSuccessfulLogin(){
        WebDriver driver = getDriver();
        SauceDemoLogIn logIn = new SauceDemoLogIn(driver);
        logIn.open();
        logIn.signIn("standard_user", "secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html","Inventory page not loaded");
        return driver;
    }

    @Test()
    @MethodOwner(owner = "Luna")
    public void testUnsuccessfulLogin(){
        WebDriver driver = getDriver();

        SauceDemoLogIn logIn = new SauceDemoLogIn(driver);

        logIn.open();
        logIn.signIn("standard_user", "secret_sauc");

        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/","LogIn Page not loaded");
    }

    @Test()
    @MethodOwner(owner = "Luna")
    public void testLogOut(){
        WebDriver driver = testSuccessfulLogin();

        SauceInventory inventory = new SauceInventory(driver);
        inventory.logOut();

        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/","LogIn Page not loaded");
    }

    @Test()
    @MethodOwner(owner = "Luna")
    public void testCompareCatalogToCart(){
        WebDriver driver = testSuccessfulLogin();

        SauceInventory inventory = new SauceInventory(driver);
        Item item = inventory.getRndItem();
        inventory.addProductToCard(item);

        inventory.goToCart();

        SauceCart cart = new SauceCart(driver);

        Assert.assertTrue(cart.getItemsOnCart().contains(item), item.getName() + " is not on the cart");
    }

    @Test()
    @MethodOwner(owner = "Luna")
    public void testAddingProductsToCart(){
        WebDriver driver = testSuccessfulLogin();

        SauceInventory inventory = new SauceInventory(driver);
        Item firstItem = inventory.getItem(0);
        Item secondItem = inventory.getItem(1);
        inventory.addProductToCard(firstItem);
        inventory.addProductToCard(secondItem);

        inventory.goToCart();

        SauceCart cart = new SauceCart(driver);
        List<Item> itemsOnCart = cart.getItemsOnCart();

        softAssert.assertTrue(cart.getItemsOnCart().contains(firstItem), firstItem.getName() + " is not on the cart");
        softAssert.assertTrue(cart.getItemsOnCart().contains(secondItem), secondItem.getName() + " is not on the cart");
        softAssert.assertEquals(itemsOnCart.size(), 2);
        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "Luna")
    public void testRemovingProductsFromCart(){
        WebDriver driver = testSuccessfulLogin();

        SauceInventory inventory = new SauceInventory(driver);
        Item firstItem = inventory.getItem(0);
        Item secondItem = inventory.getItem(1);
        Item thirdItem = inventory.getItem(2);
        inventory.addProductToCard(firstItem);
        inventory.addProductToCard(secondItem);
        inventory.addProductToCard(thirdItem);

        inventory.goToCart();

        SauceCart cart = new SauceCart(driver);

        softAssert.assertTrue(cart.getItemsOnCart().contains(firstItem),  firstItem.getName() + " is not on the cart");
        softAssert.assertTrue(cart.getItemsOnCart().contains(secondItem), secondItem.getName() + " is not  on the cart");
        softAssert.assertTrue(cart.getItemsOnCart().contains(thirdItem),  thirdItem.getName() + " is not  on the cart");
        softAssert.assertEquals(cart.getItemsOnCart().size(), 3, "not all the products are on the cart");

        cart.removeItemFromCart(secondItem);
        softAssert.assertFalse(cart.getItemsOnCart().contains(secondItem), secondItem.getName() + " has not been removed from the cart");
        softAssert.assertEquals(cart.getItemsOnCart().size(), 2, "not all the remaining product are still on the cart");
        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "Luna")
    public void testScrollPage(){
        WebDriver driver = testSuccessfulLogin();
        SauceInventory inventory = new SauceInventory(driver);
        Assert.assertTrue(inventory.scroll() != 0, "The page has not been scrolled");
    }

    @Test()
    @MethodOwner(owner = "Luna")
    public void testSuccessfulOrderForm(){
        WebDriver driver = getDriver();
        testAddingProductsToCart();
        SauceCart cart = new SauceCart(driver);

        cart.checkOut();
        SauceCheckOut checkOut = new SauceCheckOut(driver);
        try {
            checkOut.fillInformation("jaco","luna","1416");
        }catch (SauceException e){
            LOGGER.error(e.toString());
        }

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html", "The order was unSuccessfully filled");
    }

    @Test()
    @MethodOwner(owner = "Luna")
    public void testUnsuccessfulOrderForm() {
        WebDriver driver = getDriver();
        testAddingProductsToCart();
        SauceCart cart = new SauceCart(driver);

        cart.checkOut();
        SauceCheckOut checkOut = new SauceCheckOut(driver);

        for (int i = 0; i < 3; i++) {
            try {
                switch (i){
                    case 0:
                        checkOut.fillInformation("","","");
                        break;
                    case 1:
                        checkOut.fillInformation("jaco","","");
                        break;
                    case 2:
                        checkOut.fillInformation("jaco","Luna","");
                        break;
                }
                softAssert.fail("Expected SauceException was not thrown.");
            }catch (SauceException e){
                switch (i){
                    case 0:
                        Assert.assertTrue(e.getMessage().contains("First Name is required"), "Unexpected error message: " + e.getMessage());
                        break;
                    case 1:
                        Assert.assertTrue(e.getMessage().contains("Last Name is required"), "Unexpected error message: " + e.getMessage());
                        break;
                    case 2:
                        Assert.assertTrue(e.getMessage().contains("Postal Code is required"), "Unexpected error message: " + e.getMessage());
                        break;
                }
            }
        }
        softAssert.assertAll();
    }

    // Checking sorting by all available options.
    @Test()
    @MethodOwner(owner = "Luna")
    public void testSorting(){
        WebDriver driver = testSuccessfulLogin();
        SauceCart cart = new SauceCart(driver);
        Arrays.stream(SortValue.values()).forEach(cart::sort);
    }


    @Test()
    @MethodOwner(owner = "Luna")
    public void testOrderItems(){
        WebDriver driver = getDriver();
        testAddingProductsToCart();
        SauceCart cart = new SauceCart(driver);
        List<Item> cartItems = cart.getItemsOnCart();

        cart.checkOut();
        SauceCheckOut checkOut = new SauceCheckOut(driver);
        try {
            checkOut.fillInformation("jaco","luna","1416");
        }catch (SauceException e){
            LOGGER.error(e.toString());
        }

        List<Item> checkOutItems = checkOut.getItemsOnCart();

        Assert.assertEquals(cartItems, checkOutItems, "The items on the cart are not the same as the items on the ckeckout");
    }
}
