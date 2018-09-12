package org.softkiss.testautomation.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.softkiss.testautomation.client.ClientFactory;
import org.softkiss.testautomation.environment.EnvironmentConfigurator;

public class WallmartLandingPage extends BasePage {

    @FindBy(css = ".GlobalHeaderSearchbar-input")
    private WebElement inputHeadSearch;

    @FindBy(css = ".elc-icon-search-nav")
    private WebElement buttonSearch;

    public WallmartLandingPage() {
        super();
    }

    public WallmartLandingPage openPage() {
        ClientFactory.getInstance().getDriver().get(EnvironmentConfigurator.getInstance().getAppUrl());
        return this;
    }

    public SearchResultsPage searchForGood(String goodName) {
        sendKeys(inputHeadSearch, goodName);
        buttonSearch.click();
        return new SearchResultsPage();
    }

}
