package com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.tabs;

import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.ApptoideBase;
import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.enums.Chip;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApptoideHome extends ApptoideBase {

    @FindBy(xpath = "//android.widget.CheckBox[@resource-id=\"cm.aptoide.pt:id/games_chip\"]")
    private ExtendedWebElement gamesChip;

    @FindBy(xpath = "//android.widget.CheckBox[@resource-id=\"cm.aptoide.pt:id/apps_chip\"]")
    private ExtendedWebElement appsChip;

    public ApptoideHome(WebDriver driver) {
        super(driver);
    }
    @Override
    public boolean isPageOpened() {
        return getDriver().findElement(
                By.xpath("//android.widget.FrameLayout[@resource-id='cm.aptoide.pt:id/main_home_container_content']")).isDisplayed();
    }

    public void clickChip(Chip chip){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        switch (chip){
            case Chip.GAMES -> gamesChip.click();
            case Chip.APPS -> appsChip.click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"cm.aptoide.pt:id/more_bundles_list\"]")));
    }
}
