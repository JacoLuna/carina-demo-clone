package com.zebrunner.carina.demo.gui.pages.desktop.tiendaMia;

import com.zebrunner.carina.demo.gui.pages.desktop.tiendaMia.entities.Item;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Random;

public class TiendaMiaCatalog extends TiendaMiaBase {

    private String searchValue = "something random";
    @FindBy(css = ".search-content")
    private ExtendedWebElement catalog;

    public TiendaMiaCatalog(WebDriver driver){
        super(driver);
    }
    public TiendaMiaCatalog(WebDriver driver, String search){
        super(driver);
        searchValue = search;
    }

    @Override
    public void open() {
        this.getDriver().navigate().to("https://tiendamia.com/ar/e-search?amzs="+searchValue);
    }

    public Item getRndItem(){
        List<WebElement> items = catalog.findElements(By.cssSelector("div.item"));
        Random rand = new Random();
        WebElement item = items.get(rand.nextInt(items.size()));
        return makeItem(item);
    }

    public Item getItem(int index){
        List<WebElement> items = catalog.findElements(By.cssSelector("div.item"));
        WebElement item = items.get(index);
        return makeItem(item);
    }

    private Item makeItem(WebElement item){
        String link = item.findElement(By.cssSelector(".product-new-window")).getAttribute("href");
        String img = item.findElement(By.cssSelector(".main-image")).getText();
        String name =  item.findElement(By.cssSelector(".item-name")).getText();
        float price =  parsePrice(item.findElement(By.cssSelector(".item-price")).getText());
        String brand =  item.findElement(By.cssSelector(".item-brand")).getText();
        return new Item(link, name, price, img, brand);
    }

    public void addProductToCard(Item item){
        TiendaMiaProduct tiendaMiaProduct = new TiendaMiaProduct(getDriver(), item.getLink());
        tiendaMiaProduct.addProductToCart();
//        TiendaMiaCart cart = new TiendaMiaCart(getDriver());
//        cart.open();
//        cart.getItemsOnCart().forEach(e -> LOGGER.info("\n\nITEM {}\n\n", e.toString()) );
    }
}
