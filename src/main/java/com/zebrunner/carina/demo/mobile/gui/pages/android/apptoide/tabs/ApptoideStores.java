package com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.tabs;

import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.ApptoideBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ApptoideStores extends ApptoideBase {


    public ApptoideStores(WebDriver driver) {
        super(driver);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions
                .elementToBeClickable(
                        By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"cm.aptoide.pt:id/recycler_view\"]")));
    }
    @Override
    public boolean isPageOpened() {
        return getDriver().findElement(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"cm.aptoide.pt:id/recycler_view\"]")).isDisplayed();
    }

    public List<WebElement> getFollowedStores(){
        return getDriver().findElements(By.xpath("//android.widget.FrameLayout[@resource-id=\"cm.aptoide.pt:id/store_main_layout_row\"]"));
    }
    public List<WebElement> getRecommendedStores(){
        return getDriver().findElements(By.xpath("//*[contains(@class, 'android.widget.RelativeLayout')]"));
    }
}
