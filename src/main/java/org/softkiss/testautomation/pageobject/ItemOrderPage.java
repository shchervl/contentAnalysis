package org.softkiss.testautomation.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Select;

public class ItemOrderPage extends BasePage {

    @FindBy(css = ".prod-product-cta-add-to-cart button")
    private Button buttonAddToCart;

    @FindBy(css = "select[placeholder='Qty: ']")
    private Select selectorQuantity;

    @Step
    public CartPopUpPage addToCart() {
        buttonAddToCart.click();
        return new CartPopUpPage();
    }

    @Step
    public ItemOrderPage setQuantity(String quantity) {
        selectorQuantity.click();
        selectorQuantity.selectByVisibleText(quantity);
        return new ItemOrderPage();
    }

}
