package com.spring.priceGeneratorApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;

@Entity
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column
    private LocalDateTime expireDate;

    @Column
    private Double ageDiscount;

    @Column
    private Double countryDiscount;

    @ManyToOne
    @JsonBackReference(value = "product-quotation")
    @JoinColumn(name ="product_id")
    private Product product;

    @ManyToOne
    @JsonBackReference(value = "user-quotation")
    @JoinColumn(name ="user_id")
    private User user;

    @ManyToOne
    @JsonBackReference(value = "order-quotation")
    @JoinColumn(name ="order_id")
    private Order order;

    public Quotation(){}

    public Quotation(Long id, LocalDateTime expireDate, Double ageDiscount, Double countryDiscount, Product product, User user, Order order) {
        this.id = id;
        this.expireDate = expireDate;
        this.ageDiscount = ageDiscount;
        this.countryDiscount = countryDiscount;
        this.product = product;
        this.user = user;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Double getAgeDiscount() {
        return ageDiscount;
    }

    public void setAgeDiscount(Double ageDiscount) {
        this.ageDiscount = ageDiscount;
    }

    public Double getCountryDiscount() {
        return countryDiscount;
    }

    public void setCountryDiscount(Double countryDiscount) {
        this.countryDiscount = countryDiscount;
    }
}