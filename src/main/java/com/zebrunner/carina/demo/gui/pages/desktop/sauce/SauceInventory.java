package com.zebrunner.carina.demo.gui.pages.desktop.sauce;

import com.zebrunner.carina.demo.gui.pages.desktop.sauce.entities.Item;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class SauceInventory extends SauceBase {

    @FindBy(xpath = "//*[@id=\"inventory_container\"]/div")
    private ExtendedWebElement inventoryList;

    public SauceInventory(WebDriver driver){
        super(driver);
    }

    public void addProductToCard(Item item){
        List<WebElement> items = inventoryList.findElements(By.cssSelector("div.inventory_item"));

        WebElement selectedItem = items.stream()
                .filter(e -> e.findElement(By.cssSelector(".inventory_item_name")).getText().equals(item.getName()))
                .findFirst().orElse(null);
        if (selectedItem != null)
            selectedItem.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).click();
        else
            throw new RuntimeException("Product not found");
    }

    public void goToCart(){
        getDriver().findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
    }

    public Item getRndItem(){
        List<WebElement> items = inventoryList.findElements(By.cssSelector("div.inventory_item"));
        Random rand = new Random();
        WebElement item = items.get(rand.nextInt(items.size()));
        return makeItem(item);
    }

    public Item getItem(int index){
        List<WebElement> items = getDriver().findElements(By.cssSelector("div.inventory_item"));
        WebElement item = items.get(index);
        return makeItem(item);
    }

    private Item makeItem(WebElement item){
//        String img = item.findElement(By.cssSelector(".inventory_item_img>a>img")).getAttribute("src");
        String name =  item.findElement(By.cssSelector(".inventory_item_name")).getText();
        float price =  parsePrice(item.findElement(By.cssSelector(".pricebar>div.inventory_item_price")).getText());
        return new Item(name, price);
    }
}
