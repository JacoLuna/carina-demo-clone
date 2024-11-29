package com.zebrunner.carina.demo.gui.pages.desktop.trello;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TrelloBoards extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"header\"]/div[2]/div[4]/button")
    private ExtendedWebElement accountBtn;
    @FindBy(xpath = "/html/body/div[3]/div[3]/section/div/div/div[4]/nav/ul/li[2]/button")
    private ExtendedWebElement boardLogOutBtn;
    @FindBy(xpath = "//*[@id=\"logout-submit\"]")
    private ExtendedWebElement logOutBtn;

    public TrelloBoards(WebDriver driver){
        super(driver);
    }

    public void logOut(){
        accountBtn.click();
        boardLogOutBtn.click();
        logOutBtn.click();
    }
}
