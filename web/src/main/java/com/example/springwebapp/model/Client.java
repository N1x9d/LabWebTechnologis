package com.example.springwebapp.model;

import java.util.ArrayList;

public class Client {
    private String sid;
    private ArrayList<Product> ShopList=new ArrayList<>();
    private Integer ProductCount;

    public Client(final String Sid) {
        this.sid = Sid;
        this.ProductCount = 0;
    }

    public void AddProduct(Product pr) {
        ShopList.add(pr);
        ProductCount = ShopList.size();
    }

    public Integer getProductCount() {
        return this.ProductCount;
    }

    public void setProductCount(final Integer count) {
        this.ProductCount = count;
    }

    public String getSid() { return this.sid; }

    public void setSid(final String sid) { this.sid = sid; }

    public ArrayList<Product> getShopList() { return this.ShopList; }

    public void setShopList(final ArrayList<Product> ShopList) { this.ShopList = ShopList; }

}
