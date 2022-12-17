package com.spring.priceGeneratorApp.dto;

import com.spring.priceGeneratorApp.model.Order;
import com.spring.priceGeneratorApp.model.Product;
import com.spring.priceGeneratorApp.model.User;

import java.util.Date;

public class QuotationDTO {


    private String product;
    private String user;

    private Date expireDate;

    public QuotationDTO(String product, String user, Date expireDate) {
        this.product = product;
        this.user = user;
        this.expireDate = expireDate;

    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}