package com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide;

import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.components.Tabs;
import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.enums.TabValue;
import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.tabs.*;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ApptoideBase extends ApptoideWorld{

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"cm.aptoide.pt:id/toolbar\"]")
    public ExtendedWebElement toolBar;

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

    @Override
    public boolean isPageOpened() {
        return super.isPageOpened();
    }

    public List<WebElement> search(String searchValue){
        return selectPage(TabValue.SEARCH).search(searchValue);
    }
}
