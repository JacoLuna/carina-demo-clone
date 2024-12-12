package com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApptoideAppPage extends ApptoideBase{

    public ApptoideAppPage(WebDriver driver) {
        super(driver);
        loadPage();
    }

    public String getName(){
        return getDriver().findElement(By.xpath("//android.widget.TextView[@resource-id='cm.aptoide.pt:id/app_name']")).getText();
    }

    private void loadPage(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cm.aptoide.pt:id/screenshots_list")));
    }
}
