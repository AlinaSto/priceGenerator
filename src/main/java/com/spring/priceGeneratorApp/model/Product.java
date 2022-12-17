package com.spring.priceGeneratorApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;
    @Column
    private ProductType productType;
    @Column
    private Integer productPrice;
    @Column
    private String name;
    @Column
    private Integer productQuantity;

    @Column
    private Integer productAgeDiscountThreshold;
    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    @JsonManagedReference(value = "product-quotation")
    private List<Quotation> quotationList;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    @JsonManagedReference(value = "product-discount")
    private List<Discount> discountList;

    public Product() {
    }

    public Product(ProductType productType, Integer productAgeDiscountThreshold, Long id, List<Quotation> quotationList, List<Discount> discountList, Integer productPrice, Integer productQuantity) {
        this.productType = productType;
        this.id = id;
        this.quotationList = quotationList;
        this.discountList = discountList;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productAgeDiscountThreshold=productAgeDiscountThreshold;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Quotation> getQuotationList() {
        if (this.quotationList == null) {
            quotationList = new ArrayList<>();
        }
        return quotationList;
    }

    public void setQuotationList(List<Quotation> quotationList) {
        this.quotationList = quotationList;
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Integer getProductAgeDiscountThreshold() {
        return productAgeDiscountThreshold;
    }

    public void setProductAgeDiscountThreshold(Integer productAgeDiscountThreshold) {
        this.productAgeDiscountThreshold = productAgeDiscountThreshold;
    }
}
