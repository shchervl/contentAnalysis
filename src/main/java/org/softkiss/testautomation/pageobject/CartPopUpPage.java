package org.softkiss.testautomation.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.softkiss.testautomation.client.model.ItemOrder;

import java.util.ArrayList;
import java.util.List;

public class CartPopUpPage extends BasePage {

    @FindAll(@FindBy(css = ".cart-item-container"))
    private List<WebElement> items;

    @Step
    public List<ItemOrder> getItemOrders() {
        List<ItemOrder> itemOrders = new ArrayList<>();
        for (WebElement item : items) {
            itemOrders.add(
                    new ItemOrder(item.findElement(By.cssSelector(".cart-item-name a")).getText(),
                            item.findElement(By.cssSelector(".cart-saved-item-qty")).getText()));
        }
        return itemOrders;
    }
}
