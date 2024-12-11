package com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.tabs;

import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.ApptoideBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ApptoideEditorial extends ApptoideBase {
    //androidx.recyclerview.widget.RecyclerView[@resource-id="cm.aptoide.pt:id/editorial_list"]
    public ApptoideEditorial(WebDriver driver) {
        super(driver);
    }
    @Override
    public boolean isPageOpened() {
        return getDriver().findElement(
                By.id("cm.aptoide.pt:id/editorial_list")).isDisplayed();

    }
}
