package com.spring.priceGeneratorApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;
    @Column
    private String countryName;

    @Column
    private Double countryDiscount;
    @OneToMany(mappedBy = "country", cascade = {CascadeType.ALL})
    @JsonManagedReference(value = "country-discount")
    private List<Discount> discountList;

    public Country() {
    }

    public Country(Long id, String countryName, List<Discount> discountList, Double countryDiscount) {
        this.id = id;
        this.countryName = countryName;
        this.discountList = discountList;
        this.countryDiscount = countryDiscount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<Discount> getDiscountList() {
        if (this.discountList == null) {
            discountList = new ArrayList<>();
        }
        return discountList;
    }

    public void setDiscountList(List<Discount> discountList) {
        this.discountList = discountList;
    }

    public Double getCountryDiscount() {
        return countryDiscount;
    }

    public void setCountryDiscount(Double countryDiscount) {
        this.countryDiscount = countryDiscount;
    }
}
