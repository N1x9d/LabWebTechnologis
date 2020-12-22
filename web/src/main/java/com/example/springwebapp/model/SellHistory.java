package com.example.springwebapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class SellHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date dateOfBying;
    private String Email;
    private Integer prodId;
    private Integer price;
    private String selType;
    public SellHistory() {
    }

    public SellHistory(Date dateOfBying, String email, Integer prodId, Integer price, String st) {
        this.dateOfBying = dateOfBying;
        Email = email;
        this.prodId = prodId;
        this.price = price;
        this.selType=st;
    }

    public Date getDateOfBying() {
        return dateOfBying;
    }

    public void setDateOfBying(Date dateOfBying) {
        this.dateOfBying = dateOfBying;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
