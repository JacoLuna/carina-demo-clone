package com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide;

import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.tabs.ApptoideHome;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ApptoidePresentation extends AbstractPage {
    public ApptoidePresentation(WebDriver driver) {
        super(driver);
    }

    public ApptoideBase clickSkipBtn(){
        getDriver().findElement(By.id("cm.aptoide.pt:id/skip_button")).click();
        return new ApptoideHome(getDriver());
    }

    @Override
    public boolean isPageOpened(){
        return false;
    }
}
