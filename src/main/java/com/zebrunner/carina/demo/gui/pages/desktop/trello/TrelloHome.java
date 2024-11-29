package com.zebrunner.carina.demo.gui.pages.desktop.trello;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TrelloHome extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"BXP-APP\"]/header[1]/div/div[1]/div[2]/a[1]")
    private ExtendedWebElement signInBtn;

    public TrelloHome(WebDriver driver){
        super(driver);
    }

    public void open(){
        this.getDriver().navigate().to("https://trello.com/");
    }
    public boolean signIn(String email, String pass){
        TrelloLogIn logIn = new TrelloLogIn(this.getDriver());
        return logIn.signIn(email, pass);
    }
    public void logOut(){
        TrelloBoards boards = new TrelloBoards(this.getDriver());
        boards.logOut();
    }
}
