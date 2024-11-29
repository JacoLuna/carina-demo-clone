package com.zebrunner.carina.demo.gui.pages.desktop.tiendaMia;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class TiendaMiaBase extends AbstractPage {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(css = "input#amz")
    private ExtendedWebElement searchBar;

    @FindBy(xpath = "//html/body/div[3]/div/div[1]/div[2]/div/div[4]/div[5]/div/div[1]/a")
    private ExtendedWebElement goToCard;


    public TiendaMiaBase(WebDriver driver){
        super(driver);
    }

    public void open(){
        this.getDriver().navigate().to("https://www.tiendamia.com/");
        this.getDriver().findElement(By.cssSelector(".inicio_bandera:first-child")).click();
    }

    public void open(String url){
        this.getDriver().navigate().to(url);
    }

    public void search(String searchValue){
        searchBar.type(searchValue);
        searchBar.sendKeys(Keys.ENTER);

//        TiendaMiaCatalog catalog = new TiendaMiaCatalog(getDriver());
//        catalog.addRndProduct();
    }

    public void goToCard(){
        goToCard.click();
    }

    protected float parsePrice(String priceText) {
        String[] parts = priceText.split(" ");
        for (String part : parts) {
            if (part.matches(".*\\d[,.\\d]*")) { // Match strings containing numeric price formats
                // Clean the numeric value
                String numericValue = part.replaceAll("[^0-9,]", "").replace(",", ".");
                try {
                    return Float.parseFloat(numericValue); // Parse the cleaned price
                } catch (NumberFormatException e) {
                    LOGGER.error("Failed to parse price: " + numericValue, e);
                }
            }
        }
        throw new IllegalArgumentException("No valid price found in: " + priceText);
    }
}
