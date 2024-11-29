package com.zebrunner.carina.demo.gui.pages.desktop.trello;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TrelloLogIn extends AbstractPage {

    @FindBy(xpath = "//*[@id='username']")
    private ExtendedWebElement emailInput;

    @FindBy(xpath = "//*[@id=\"signup-submit\"]")
    private ExtendedWebElement signBtn;

    public TrelloLogIn(WebDriver driver){
        super(driver);
        this.getDriver().navigate().to("https://trello.com/login");
    }

    public boolean signIn(String email, String pass){
        emailInput.type(email);
        emailInput.sendKeys(Keys.ENTER);

        try {
            getDriver().findElement(By.xpath("//*[@id=\"signup-submit\"]"));
            return false;
        }catch (NoSuchElementException ignored){}

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement passInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='password']")));
        passInput.sendKeys(pass);
        passInput.sendKeys(Keys.ENTER);

        try {
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.printf(e.toString());
        }

        return true;
    }
}
