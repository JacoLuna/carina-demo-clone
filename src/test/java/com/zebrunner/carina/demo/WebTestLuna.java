package com.zebrunner.carina.demo;

import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.demo.gui.pages.desktop.sauce.SauceCart;
import com.zebrunner.carina.demo.gui.pages.desktop.sauce.SauceDemoLogIn;
import com.zebrunner.carina.demo.gui.pages.desktop.sauce.SauceInventory;
import com.zebrunner.carina.demo.gui.pages.desktop.sauce.entities.Item;
import com.zebrunner.carina.demo.gui.pages.desktop.tiendaMia.TiendaMiaBase;
import com.zebrunner.carina.demo.gui.pages.desktop.tiendaMia.TiendaMiaCart;
import com.zebrunner.carina.demo.gui.pages.desktop.tiendaMia.TiendaMiaCatalog;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * Implement at least 5 web tests (more is possible) using the following test cases:
 * (You must find the site/sites for testing yourself. Perhaps one site will not satisfy all test cases, then use several sites. )
 *
 * Check successful login, login with incorrect data, logout. •
 * Check product card (Select an element on the product page, open it and check that the data is the same on both pages) •
 * Check adding products to the cart (Add two different products to the cart, check that both are in the cart, check the total amount) •
 * Check removing products from the cart (Add several products to the cart, remove products, check that they are missing and that the total amount of products in the cart has changed and that it is correct)
 * Check that any of the pages can be scrolled.
 * Checking the Order Filling form (Checking that all fields are present, that they are filled in with an error and that the order has not been created)
 * Checking sorting by all available options.
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
    public void sampleTest(){
        WebDriver driver = getDriver(); //return drivers, which are instance of our browser
//        driver.navigate().to("url");
//        Assert.assertEquals(driver.getCurrentUrl(), "url", "can url page");

        driver.navigate().to("https://www.compragamer.com/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.compragamer.com/", "compraGamer page");

//        driver.findElement(By.id("elementID")).click();
//        //xpath is less common but more powerful
    }

    //POM - Page object model

    //Asserts should be do here
    //but actions that are repeated in in the same page should be done on the page java class, for example OurHomePage

    @Test()
    @MethodOwner(owner = "Luna")
    public void testSuccessfulLogin(){
        WebDriver driver = getDriver();

        SauceDemoLogIn logIn = new SauceDemoLogIn(driver);

        logIn.open();
        logIn.signIn("standard_user", "secret_sauce");

        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html","Inventory page");
    }

    @Test()
    @MethodOwner(owner = "Luna")
    public void testUnsuccessfulLogin(){
        WebDriver driver = getDriver();

        SauceDemoLogIn logIn = new SauceDemoLogIn(driver);

        logIn.open();
        logIn.signIn("standard_user", "secret_sauc");

        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/","LogIn Page");
    }

    @Test()
    @MethodOwner(owner = "Luna")
    public void testLogOut(){
        WebDriver driver = getDriver();

        SauceDemoLogIn logIn = new SauceDemoLogIn(driver);

        logIn.open();
        logIn.signIn("standard_user", "secret_sauce");

        SauceInventory inventory = new SauceInventory(driver);
        inventory.logOut();

        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/","LogIn Page");
    }

    @Test()
    @MethodOwner(owner = "Luna")
    public void testCompareCatalogToCart(){
        WebDriver driver = getDriver();
        SauceDemoLogIn logIn = new SauceDemoLogIn(driver);

        logIn.open();
        logIn.signIn("standard_user", "secret_sauce");

        SauceInventory inventory = new SauceInventory(driver);
        Item item = inventory.getRndItem();
        inventory.addProductToCard(item);

        inventory.goToCart();

        SauceCart cart = new SauceCart(driver);

        Assert.assertTrue(cart.getItemsOnCart().contains(item));
    }

    @Test()
    @MethodOwner(owner = "Luna")
    public void testAddingProductsToCart(){
        WebDriver driver = getDriver();
        SauceDemoLogIn logIn = new SauceDemoLogIn(driver);

        logIn.open();
        logIn.signIn("standard_user", "secret_sauce");

        SauceInventory inventory = new SauceInventory(driver);
        Item firstItem = inventory.getItem(0);
        Item secondItem = inventory.getItem(1);
        inventory.addProductToCard(firstItem);
        inventory.addProductToCard(secondItem);

        inventory.goToCart();

        SauceCart cart = new SauceCart(driver);
        List<Item> itemsOnCart = cart.getItemsOnCart();

        softAssert.assertTrue(cart.getItemsOnCart().contains(firstItem));
        softAssert.assertTrue(cart.getItemsOnCart().contains(secondItem));
        softAssert.assertEquals(itemsOnCart.size(), 2);
        softAssert.assertAll();
    }
}
