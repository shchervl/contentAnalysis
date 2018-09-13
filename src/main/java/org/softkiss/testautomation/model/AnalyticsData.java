package org.softkiss.testautomation.model;

import java.util.Objects;

public class AnalyticsData {
    private String goodName;
    private Double price;
    private Double viewed;
    private Double sold;

    public AnalyticsData() {
    }

    public AnalyticsData(String goodName, Double price, Double viewed, Double sold) {
        this.goodName = goodName;
        this.price = price;
        this.viewed = viewed;
        this.sold = sold;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getViewed() {
        return viewed;
    }

    public void setViewed(Double viewed) {
        this.viewed = viewed;
    }

    public Double getSold() {
        return sold;
    }

    public void setSold(Double sold) {
        this.sold = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalyticsData that = (AnalyticsData) o;
        return Objects.equals(goodName, that.goodName) &&
                Objects.equals(price, that.price) &&
                Objects.equals(viewed, that.viewed) &&
                Objects.equals(sold, that.sold);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodName, price, viewed, sold);
    }


    @Override
    public String toString() {
        return "AnalyticsData{" +
                "goodName='" + goodName + '\'' +
                ", price=" + price +
                ", viewed=" + viewed +
                ", sold=" + sold +
                '}';
    }
}
