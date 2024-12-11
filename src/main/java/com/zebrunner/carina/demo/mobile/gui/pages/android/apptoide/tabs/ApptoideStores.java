package com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.tabs;

import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.ApptoideBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ApptoideStores extends ApptoideBase {

    public ApptoideStores(WebDriver driver) {
        super(driver);
    }
    @Override
    public boolean isPageOpened() {
        return getDriver().findElement(
                By.xpath("//android.view.ViewGroup[@resource-id=\"cm.aptoide.pt:id/swipe_container\"]")).isDisplayed();
    }
}
