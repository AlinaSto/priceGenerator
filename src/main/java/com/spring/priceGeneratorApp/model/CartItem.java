package com.spring.priceGeneratorApp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int quantity;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="product_id")
    private Product product;

    public CartItem(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}