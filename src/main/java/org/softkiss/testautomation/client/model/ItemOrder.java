package org.softkiss.testautomation.client.model;

import java.util.Objects;

public class ItemOrder {
    private String itemName;
    private String quantity;

    public ItemOrder(String itemName, String quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String item) {
        this.itemName = item;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ItemOrder{" +
                "itemName='" + itemName + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOrder itemOrder = (ItemOrder) o;
        return Objects.equals(itemName, itemOrder.itemName) &&
                Objects.equals(quantity, itemOrder.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, quantity);
    }
}
