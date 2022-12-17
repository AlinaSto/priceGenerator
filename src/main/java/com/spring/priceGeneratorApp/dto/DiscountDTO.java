package com.spring.priceGeneratorApp.dto;

public class DiscountDTO {

    private Double discount;
    private String country;

    public DiscountDTO(Double discount, String country) {
        this.discount = discount;
        this.country = country;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
