package com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.tabs;

import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.ApptoideBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ApptoideSearch extends ApptoideBase {

    @FindBy(id = "cm.aptoide.pt:id/search_src_text")
    private ExtendedWebElement searchInput;

    public ApptoideSearch(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return getDriver().findElement(By.id("cm.aptoide.pt:id/trending_list")).isDisplayed();
    }

    public List<WebElement> search(String searchValue){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        searchInput.type(searchValue);
        WebElement suggestionList = wait.until(ExpectedConditions.elementToBeClickable(By.id("cm.aptoide.pt:id/suggestions_list")));
        suggestionList.findElements(By.xpath("//*[contains(@class, 'android.widget.LinearLayout')]")).getFirst().click();

        return getSearchList();
    }

    private List<WebElement> getSearchList(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions
                .elementToBeClickable(
                        By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"cm.aptoide.pt:id/fragment_search_result_all_stores_app_list\"]")));
        return getDriver().findElements(By.xpath("//*[@resource-id='cm.aptoide.pt:id/fragment_search_result_all_stores_app_list']//android.widget.FrameLayout"));
    }
}
