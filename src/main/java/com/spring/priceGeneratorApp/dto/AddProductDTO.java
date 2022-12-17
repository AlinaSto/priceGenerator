package com.spring.priceGeneratorApp.dto;

import com.spring.priceGeneratorApp.model.Discount;
import com.spring.priceGeneratorApp.model.ProductType;
import com.spring.priceGeneratorApp.model.Quotation;

import java.util.List;

public class AddProductDTO {

    private String productName;
    private ProductType productType;
    private Integer productPrice;
    private Integer productQuantity;
    private List<DiscountDTO> discounts;


    public AddProductDTO(String productName, ProductType productType, Integer productPrice, Integer productQuantity, List<DiscountDTO> discounts) {
        this.productName = productName;
        this.productType = productType;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.discounts = discounts;

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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

    public List<DiscountDTO> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountDTO> discounts) {
        this.discounts = discounts;
    }



}
