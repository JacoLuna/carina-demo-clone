package com.zebrunner.carina.demo.gui.pages.desktop.sauce;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class SauceDemoLogIn extends AbstractPage {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @FindBy(css = "#user-name")
    private ExtendedWebElement emailInput;

    @FindBy(css = "#password")
    private ExtendedWebElement passInput;

    @FindBy(xpath = "//*[@id=\"login-button\"]")
    private ExtendedWebElement logInBtn;

    public SauceDemoLogIn(WebDriver driver){
        super(driver);
    }

    public void open(){
        this.getDriver().navigate().to("https://www.saucedemo.com/");
    }

    public boolean signIn(String email, String pass){
        emailInput.type(email);
        passInput.type(pass);
        logInBtn.sendKeys(Keys.ENTER);
        return true;
    }
}
