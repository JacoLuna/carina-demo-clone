package com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.tabs;

import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.ApptoideBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ApptoideHome extends ApptoideBase {
    public ApptoideHome(WebDriver driver) {
        super(driver);
    }
    @Override
    public boolean isPageOpened() {
        return getDriver().findElement(
                By.xpath("//android.widget.FrameLayout[@resource-id='cm.aptoide.pt:id/main_home_container_content']")).isDisplayed();
    }
}
