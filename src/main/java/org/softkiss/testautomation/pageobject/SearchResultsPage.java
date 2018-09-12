package org.softkiss.testautomation.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindAll(@FindBy(css = ".product-title-link"))
    List<WebElement> foundItems;

    @Step
    public ItemOrderPage selectFirstItem() {
        foundItems.get(0).click();
        return new ItemOrderPage();
    }


}
