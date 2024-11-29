package com.zebrunner.carina.demo.gui.pages.desktop.sauce;

import com.zebrunner.carina.demo.gui.pages.desktop.SauceBase;
import com.zebrunner.carina.demo.gui.pages.desktop.sauce.entities.Item;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.stream.Collectors;

public class SauceCart extends SauceBase {

    @FindBy(css = ".cart_item")
    private List<ExtendedWebElement> cart;

    public SauceCart(WebDriver driver){
        super(driver);
    }

    public boolean removeItemFromCart(Item item){
        WebElement selectedItem = cart.stream()
                .filter(e -> e.findElement(By.cssSelector(".inventory_item_name")).getText().equals(item.getName()))
                .findFirst().orElse(null);
        if (selectedItem != null){
            selectedItem.findElement(By.cssSelector("#remove-" + item.getName().toLowerCase().replace(" ", "-"))).click();
            return true;
        }
        else
            return false;
    }

    public List<Item> getItemsOnCart(){
        return cart.stream()
                .map(e -> new Item(
                        e.findElement(By.cssSelector(".inventory_item_name")).getText(),
                        parsePrice(e.findElement(By.cssSelector(".item_pricebar>div.inventory_item_price")).getText())
                ))
                .collect(Collectors.toList());
    }

    protected float parsePrice(String priceText) {
        String numericValue = priceText.replaceAll("[^0-9,]", "");
        return Float.parseFloat(numericValue);
    }
}
