package org.softkiss.testautomation.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.Arrays;
import java.util.List;

import org.softkiss.testautomation.BaseTest;
import org.softkiss.testautomation.model.ItemOrder;
import org.softkiss.testautomation.pageobject.WallmartLandingPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


@Feature("Managing orders in cart")
public class TestWallMartAddGoodToCart extends BaseTest {


    @Story("Add good to cart and specify quantity")
    @Test
    public void addGoodToCart() {
        ItemOrder itemOrder = new ItemOrder("illy Ground Drip Medium Roast Coffee, 8.8 Oz", "3");
        List<ItemOrder> actualItemOrders = new WallmartLandingPage()
                .openPage()
                .searchForGood(itemOrder.getItemName())
                .selectFirstItem()
                .setQuantity(itemOrder.getQuantity())
                .addToCart()
                .getItemOrders();

        assertEquals(actualItemOrders.size(), 1, "Number of items isn't expected");
        assertEquals(actualItemOrders, Arrays.asList(itemOrder), "Item isn't in the order list");
    }
}



