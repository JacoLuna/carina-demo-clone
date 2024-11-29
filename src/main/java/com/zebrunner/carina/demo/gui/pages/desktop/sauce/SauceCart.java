package com.zebrunner.carina.demo.gui.pages.desktop.sauce;

import com.zebrunner.carina.demo.gui.pages.desktop.SauceBase;
import com.zebrunner.carina.demo.gui.pages.desktop.sauce.entities.Item;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class SauceCart extends SauceBase {

    public SauceCart(WebDriver driver){
        super(driver);
    }

    public List<Item> getItemsOnCart(){
//        e.findElement(By.cssSelector(".inventory_item_img>a>img")).getAttribute("src"),
        return getDriver().findElements(By.cssSelector(".cart_item")).stream()
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
