package com.zebrunner.carina.demo.gui.pages.desktop.sauce;

import com.zebrunner.carina.demo.gui.pages.desktop.sauce.exceptions.SauceException;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SauceCheckOut extends SauceCartBase{

    @FindBy(xpath = "//*[@id='first-name']")
    private ExtendedWebElement firstNameInput;
    @FindBy(xpath = "//*[@id='last-name']")
    private ExtendedWebElement lastNameInput;
    @FindBy(xpath = "//*[@id='postal-code']")
    private ExtendedWebElement postalCodeInput;
    @FindBy(xpath = "//*[@id='continue']")
    private ExtendedWebElement continueBtn;
    @FindBy(css = ".error-message-container")
    private ExtendedWebElement errorMsg;

    public SauceCheckOut(WebDriver driver){
        super(driver);
    }

    public void fillInformation(String name, String lastName, String postalCode) throws SauceException {
        firstNameInput.type(name);
        lastNameInput.type(lastName);
        postalCodeInput.type(postalCode);
        continueBtn.click();

        String errorMessage = "";
        try{
            errorMessage = errorMsg.findElement(By.cssSelector("h3")).getText();
        }catch (Exception ignore){}
        throw new SauceException(errorMessage);
    }

    public void finishCheckOut(){
        getDriver().findElement(By.xpath("//*[@id='finish']")).click();
    }
}
