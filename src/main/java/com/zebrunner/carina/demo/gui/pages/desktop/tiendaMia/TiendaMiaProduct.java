package com.zebrunner.carina.demo.gui.pages.desktop.tiendaMia;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class TiendaMiaProduct extends TiendaMiaBase{

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(xpath = "//*[@id=\"add-to-cart\"]")
    private ExtendedWebElement addToCart;

    public TiendaMiaProduct(WebDriver driver){
        super(driver);
    }

    public TiendaMiaProduct(WebDriver driver, String url){
        super(driver);
        open(url);
    }
    public void addProductToCart(){
        addToCart.click();
    }
}
