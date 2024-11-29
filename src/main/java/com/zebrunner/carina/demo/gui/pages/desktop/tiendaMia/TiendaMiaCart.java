package com.zebrunner.carina.demo.gui.pages.desktop.tiendaMia;

import com.zebrunner.carina.demo.gui.pages.desktop.tiendaMia.entities.Item;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class TiendaMiaCart extends TiendaMiaBase {

    @FindBy(css = "#quantity-update-cart-form")
    private ExtendedWebElement cart;

    public TiendaMiaCart(WebDriver driver){
        super(driver);
    }

    @Override
    public void open() {
        super.open("https://tiendamia.com/ar/checkout/cart/");
    }

    public List<Item> getItemsOnCart(){
        return getDriver().findElements(By.cssSelector(".item_container")).stream()
                .map(e -> new Item(
                        e.findElement(By.cssSelector(".item_general>h2>a")).getText(),
                        parsePrice(e.findElement(By.cssSelector(".price.currency_price")).getText())
                ))
                .collect(Collectors.toList());
    }

//    e.findElement(By.cssSelector(".product-new-window")).getAttribute("href")
}
