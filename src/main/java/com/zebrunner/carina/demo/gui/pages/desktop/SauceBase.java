package com.zebrunner.carina.demo.gui.pages.desktop;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SauceBase extends AbstractPage {

    @FindBy(xpath = "//*[@id='react-burger-menu-btn']")
    private ExtendedWebElement sideMenu;

    public SauceBase(WebDriver driver){
        super(driver);
    }

    public void logOut(){
        sideMenu.click();
        getDriver().findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]")).click();
    }

    protected float parsePrice(String priceText) {
        String numericValue = priceText.replaceAll("[^0-9,]", "");
        return Float.parseFloat(numericValue);
    }

    public double scroll(){
        JavascriptExecutor  js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        return Double.parseDouble(js.executeScript("return document.documentElement.scrollTop").toString());
    }
}
