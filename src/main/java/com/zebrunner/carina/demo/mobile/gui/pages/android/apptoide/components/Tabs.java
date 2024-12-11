package com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.components;

import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.enums.TabValue;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Tabs extends AbstractUIObject {

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"cm.aptoide.pt:id/bottom_navigation\"]")
    private ExtendedWebElement tabBar;

    public Tabs(WebDriver driver) {
        super(driver);
    }

    public void clickTab(TabValue tabValue){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.widget.FrameLayout[@content-desc='"+tabValue.getValue()+"']")))
                .click();
    }
}
