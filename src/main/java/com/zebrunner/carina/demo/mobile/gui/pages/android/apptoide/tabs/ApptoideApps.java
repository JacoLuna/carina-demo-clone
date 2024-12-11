package com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.tabs;

import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.ApptoideBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ApptoideApps extends ApptoideBase {
    public ApptoideApps(WebDriver driver) {
        super(driver);
    }
    @Override
    public boolean isPageOpened() {
        return getDriver().findElement(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"cm.aptoide.pt:id/fragment_apps_recycler_view\"]")).isDisplayed();
    }
}
