package com.spring.priceGeneratorApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @ManyToOne
    @JsonBackReference(value ="country-discount")
    @JoinColumn(name ="country_id")
    private Country country;

    @ManyToOne
    @JsonBackReference(value ="product-discount")
    @JoinColumn(name ="product_id")
    private Product product;

    @Column
    private Double discount;

    public Discount(){}
    public Discount(Long id, Country country, Product product) {
        this.id = id;
        this.country = country;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
