package com.zebrunner.carina.demo.gui.pages.desktop.sauce;

import com.zebrunner.carina.demo.gui.pages.desktop.sauce.entities.Item;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SauceCartBase extends SauceBase{

    @FindBy(css = ".cart_item")
    protected List<ExtendedWebElement> cart;

    public SauceCartBase(WebDriver driver){
        super(driver);
    }

    public List<Item> getItemsOnCart(){
        return cart.stream()
                .map(e -> new Item(
                        e.findElement(By.cssSelector(".inventory_item_name")).getText(),
                        parsePrice(e.findElement(By.cssSelector(".item_pricebar>div.inventory_item_price")).getText())
                ))
                .collect(Collectors.toList());
    }
}
