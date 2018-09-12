package org.softkiss.testautomation.pageobject;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Select;

public class ItemOrderPage extends BasePage {

    @FindBy(css = ".prod-product-cta-add-to-cart button")
    private Button buttonAddToCart;

    @FindBy(css = "select[placeholder='Qty: ']")
    private Select selectorQuantity;

    public CartPopUpPage addToCart() {
        buttonAddToCart.click();
        return new CartPopUpPage();
    }

    public ItemOrderPage setQuantity(String quantity) {
        selectorQuantity.click();
        selectorQuantity.selectByVisibleText(quantity);
        return new ItemOrderPage();
    }

    public Select getSelectorQuantity() {
        return selectorQuantity;
    }
}
