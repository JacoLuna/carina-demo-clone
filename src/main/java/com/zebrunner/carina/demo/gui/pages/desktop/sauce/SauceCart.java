package com.zebrunner.carina.demo.gui.pages.desktop.sauce;

import com.zebrunner.carina.demo.gui.pages.desktop.sauce.Enums.SortValue;
import com.zebrunner.carina.demo.gui.pages.desktop.sauce.entities.Item;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SauceCart extends SauceCartBase {

    @FindBy(css = ".select_container")
    private ExtendedWebElement sortingSelect;

    public SauceCart(WebDriver driver){
        super(driver);
    }

    public void removeItemFromCart(Item item){
        WebElement selectedItem = cart.stream()
                .filter(e -> e.findElement(By.cssSelector(".inventory_item_name")).getText().equals(item.getName()))
                .findFirst().orElse(null);
        if (selectedItem != null){
            selectedItem.findElement(By.cssSelector("#remove-" + item.getName().toLowerCase().replace(" ", "-"))).click();
        }
    }

//    public List<Item> getItemsOnCart(){
//        return cart.stream()
//                .map(e -> new Item(
//                        e.findElement(By.cssSelector(".inventory_item_name")).getText(),
//                        parsePrice(e.findElement(By.cssSelector(".item_pricebar>div.inventory_item_price")).getText())
//                ))
//                .collect(Collectors.toList());
//    }

    public void checkOut(){
        getDriver().findElement(By.xpath("//*[@id='checkout']")).click();
    }

    public void sort(SortValue sortValue){
        sortingSelect.click();
        sortingSelect.findElement(By.cssSelector("[value='"+sortValue.getName()+"']")).click();
    }
}
