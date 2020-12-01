package com.example.springwebapp.model;


import java.util.ArrayList;

public class Autor {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String name;
    private Double ratyByOurCompany;
    private ArrayList<Product> portfolio;

    public Autor(final String Name, final  ArrayList<Product> products, final Double rate){
        this.portfolio=products;
        this.name=Name;
        if(rate<=5.0 && rate>=0)
            this.ratyByOurCompany=rate;
    }
    public Autor(final String Name, final Double rate){
        this.name=Name;
        if(rate<=5.0 && rate>=0)
            this.ratyByOurCompany=rate;
    }
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getRate() {
        return this.ratyByOurCompany;
    }

    public void setRate(final double Rate) {
        if(Rate<=5.0 && Rate>=0)
            this.ratyByOurCompany=Rate;
    }

    public  ArrayList<Product> getPortfolio() {
        return this.portfolio;
    }

    public void setPortfolio(final  ArrayList<Product> prods) {
        this.portfolio=prods;
    }
    public void addPortfolio(final Product pr){
        if(portfolio!=null)
        this.portfolio.add(pr);
        else
            {
                portfolio=new ArrayList<Product>();
                this.portfolio.add(pr);
            }
    }
}
