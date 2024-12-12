/*******************************************************************************
 * Copyright 2020-2023 Zebrunner Inc (https://www.zebrunner.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.zebrunner.carina.demo;

import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.ApptoideAppPage;
import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.ApptoideBase;
import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.ApptoidePresentation;
import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.enums.Chip;
import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.enums.TabValue;
import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.tabs.ApptoideApps;
import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.tabs.ApptoideHome;
import com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.tabs.ApptoideStores;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.demo.mobile.gui.pages.common.CarinaDescriptionPageBase;
import com.zebrunner.carina.demo.mobile.gui.pages.common.ContactUsPageBase;
import com.zebrunner.carina.demo.mobile.gui.pages.common.LoginPageBase;
import com.zebrunner.carina.demo.mobile.gui.pages.common.UIElementsPageBase;
import com.zebrunner.carina.demo.mobile.gui.pages.common.WebViewPageBase;
import com.zebrunner.carina.demo.mobile.gui.pages.common.WelcomePageBase;
import com.zebrunner.carina.demo.utils.MobileContextUtils;
import com.zebrunner.carina.demo.utils.MobileContextUtils.View;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class MobileSampleTest implements IAbstractTest, IMobileUtils {

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void testLoginUser() {
        String username = "Test user";
        String password = RandomStringUtils.randomAlphabetic(10);
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        Assert.assertTrue(welcomePage.isPageOpened(), "Welcome page isn't opened");
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        Assert.assertFalse(loginPage.isLoginBtnActive(), "Login button is active when it should be disabled");
        loginPage.typeName(username);
        loginPage.typePassword(password);
        loginPage.selectMaleSex();
        loginPage.checkPrivacyPolicyCheckbox();
        CarinaDescriptionPageBase carinaDescriptionPage = loginPage.clickLoginBtn();
        Assert.assertTrue(carinaDescriptionPage.isPageOpened(), "Carina description page isn't opened");
    }

	@Test()
    @MethodOwner(owner = "qpsdemo")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void testWebView() {
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        loginPage.login();
        WebViewPageBase webViewPageBase = initPage(getDriver(), WebViewPageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();

        contextHelper.switchMobileContext(View.WEB_CARINA);
        ContactUsPageBase contactUsPage = webViewPageBase.goToContactUsPage();

        contextHelper.switchMobileContext(View.WEB_BROWSER, View.WEB_CARINA);

        contactUsPage.typeName("John Doe");
        contactUsPage.typeEmail("some@email.com");
        contactUsPage.typeQuestion("This is a message");
        contactUsPage.submit();
        Assert.assertTrue(contactUsPage.isSuccessMessagePresent());
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestLabel(name = "feature", value = {"mobile", "acceptance"})
    public void testUIElements() {
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        CarinaDescriptionPageBase carinaDescriptionPage = loginPage.login();
        UIElementsPageBase uiElements = carinaDescriptionPage.navigateToUIElementsPage();
        final String text = "some text";
        final String date = "22/10/2018";
        final String email = "some@email.com";
        uiElements.typeText(text);
        Assert.assertEquals(uiElements.getText(), text, "Text was not typed");
        uiElements.typeDate(date);
        Assert.assertEquals(uiElements.getDate(), date, "Date was not typed");
        uiElements.typeEmail(email);
        Assert.assertEquals(uiElements.getEmail(), email, "Email was not typed");
        uiElements.swipeToFemaleRadioButton();
        uiElements.checkCopy();
        Assert.assertTrue(uiElements.isCopyChecked(), "Copy checkbox was not checked");
        uiElements.clickOnFemaleRadioButton();
        Assert.assertTrue(uiElements.isFemaleRadioButtonSelected(), "Female radio button was not selected!");
        uiElements.clickOnOtherRadioButton();
        Assert.assertTrue(uiElements.isOthersRadioButtonSelected(), "Others radio button was not selected!");
    }

    @Test()
    @MethodOwner(owner = "jaco")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void tabsTest() {
        initPage(getDriver(), WelcomePageBase.class);
        ApptoidePresentation apptoidePresentation = new ApptoidePresentation(getDriver());
        ApptoideBase apptoideBase = apptoidePresentation.clickSkipBtn();

        for (TabValue value : TabValue.values()) {
            Assert.assertTrue(apptoideBase.selectPage(value).isPageOpened(), value.getValue() + " isn't open");
        }
    }

    @Test()
    @MethodOwner(owner = "jaco")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void searchTest() {
        initPage(getDriver(), WelcomePageBase.class);
        ApptoidePresentation apptoidePresentation = new ApptoidePresentation(getDriver());
        ApptoideBase apptoideBase = apptoidePresentation.clickSkipBtn();
        String searchValue = "Pixel Dungeon";

        List<WebElement> apps = apptoideBase.search(searchValue);

        WebElement app = apps.stream()
                    .filter(result ->
                            result.findElement(By.xpath("//*[@resource-id='cm.aptoide.pt:id/app_name']")).getText().equals(searchValue))
                    .findFirst()
                    .orElse(null);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(app, null, "search value doesn't exist");

        apps.getFirst().click();
        ApptoideAppPage appPage = new ApptoideAppPage(getDriver());
        for (String s : StringUtils.split(searchValue, " ")) {
            softAssert.assertTrue(appPage.getName().contains(s), "the app doesnt have the name of the search on the title");
        }
        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "jaco")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void testToolBar() {
        initPage(getDriver(), WelcomePageBase.class);
        ApptoidePresentation apptoidePresentation = new ApptoidePresentation(getDriver());
        ApptoideBase apptoideBase = apptoidePresentation.clickSkipBtn();
        for (TabValue value : TabValue.values()) {
            if (!value.equals(TabValue.SEARCH))
                Assert.assertNotNull(apptoideBase.selectPage(value).toolBar, value.getValue() + " doesnt have the tool Bar");
        }
    }

    @Test()
    @MethodOwner(owner = "jaco")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void testHome() {
        initPage(getDriver(), WelcomePageBase.class);
        ApptoidePresentation apptoidePresentation = new ApptoidePresentation(getDriver());
        ApptoideBase apptoideBase = apptoidePresentation.clickSkipBtn();

        for (Chip chip : Chip.values()) {
            ((ApptoideHome)apptoideBase.selectPage(TabValue.HOME)).clickChip(chip);
        }
    }

    @Test()
    @MethodOwner(owner = "jaco")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void testStore() {
        initPage(getDriver(), WelcomePageBase.class);
        ApptoidePresentation apptoidePresentation = new ApptoidePresentation(getDriver());
        ApptoideBase apptoideBase = apptoidePresentation.clickSkipBtn();

        ApptoideStores apptoideStores = (ApptoideStores)apptoideBase.selectPage(TabValue.STORES);
        List<WebElement> followedStores = apptoideStores.getFollowedStores();
        List<WebElement> recommendedStores = apptoideStores.getRecommendedStores();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(!followedStores.isEmpty(), "Follow stores element is empty");
        softAssert.assertTrue(!recommendedStores.isEmpty(), "Follow stores element is empty");
        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "jaco")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void testApps() {
        initPage(getDriver(), WelcomePageBase.class);
        ApptoidePresentation apptoidePresentation = new ApptoidePresentation(getDriver());
        ApptoideBase apptoideBase = apptoidePresentation.clickSkipBtn();

        ApptoideApps apptoideStores = (ApptoideApps)apptoideBase.selectPage(TabValue.APPS);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertAll();
    }
}
