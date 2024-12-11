package com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide;

import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.components.Tabs;
import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.enums.TabValue;
import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.tabs.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ApptoideBase extends ApptoideWorld{

    public ApptoideBase(WebDriver driver) {
        super(driver);
    }

    public ApptoideBase selectPage(TabValue tabValue){
        Tabs tabs = new Tabs(getDriver());
        tabs.clickTab(tabValue);
        return switch (tabValue){
            case HOME -> new ApptoideHome(getDriver());
            case EDITORIAL -> new ApptoideEditorial(getDriver());
            case SEARCH -> new ApptoideSearch(getDriver());
            case STORES -> new ApptoideStores(getDriver());
            case APPS -> new ApptoideApps(getDriver());
        };
    }

    public List<WebElement> search(String searchValue){
        return selectPage(TabValue.SEARCH).search(searchValue);
    }

    @Override
    public boolean isPageOpened() {
        return super.isPageOpened();
    }
}
