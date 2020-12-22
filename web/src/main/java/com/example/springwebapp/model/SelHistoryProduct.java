package com.example.springwebapp.model;

public class SelHistoryProduct {
    private Product product;
    private SellHistory sellHistory;

    public SelHistoryProduct(Product product, SellHistory sellHistory) {
        this.product = product;
        this.sellHistory = sellHistory;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public SellHistory getSellHistory() {
        return sellHistory;
    }

    public void setSellHistory(SellHistory sellHistory) {
        this.sellHistory = sellHistory;
    }
}
