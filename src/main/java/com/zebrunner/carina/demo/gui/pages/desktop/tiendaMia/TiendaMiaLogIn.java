package com.zebrunner.carina.demo.gui.pages.desktop.tiendaMia;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TiendaMiaLogIn extends AbstractPage {

    @FindBy(css = "#email_address_register")
    private ExtendedWebElement emailInput;

    @FindBy(css = "#password-log")
    private ExtendedWebElement passInput;

    @FindBy(xpath = "//*[@id=\"login-button\"]")
    private ExtendedWebElement logInBtn;

    public TiendaMiaLogIn(WebDriver driver){
        super(driver);
        this.getDriver().navigate().to("https://tiendamia.com/ar/ingresar");
    }

    public boolean signIn(String email, String pass){
        emailInput.click();
        emailInput.type(email);
        passInput.type(pass);
        logInBtn.sendKeys(Keys.ENTER);
        return true;
    }
}
