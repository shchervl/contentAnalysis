package org.softkiss.testautomation.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindAll(@FindBy(css = ".product-title-link"))
    List<WebElement> foundItems;

    public ItemOrderPage selectFirstItem() {
        foundItems.get(0).click();
        return new ItemOrderPage();
    }


}
