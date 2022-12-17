package com.spring.priceGeneratorApp.repository;

import com.spring.priceGeneratorApp.model.Country;
import com.spring.priceGeneratorApp.model.Discount;
import com.spring.priceGeneratorApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Discount findDiscountByCountry_CountryNameAndProduct(String countryName, Product product);
}
