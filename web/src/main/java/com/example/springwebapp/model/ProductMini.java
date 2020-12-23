package com.example.springwebapp.model;

public class ProductMini {


    private String linc;
    private String Name;
    private Integer Price;
    private Integer id;

    public String getLinc() {
        return linc;
    }

    public void setLinc(String linc) {
        this.linc = linc;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductMini(String linc, String name, Integer price, int id) {
        this.linc = linc;
        Name = name;
        Price = price;
        this.id = id;
    }
}
