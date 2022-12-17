package com.spring.priceGeneratorApp.repository;

import com.spring.priceGeneratorApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByName(String productName);

    Optional< Product> findProductById(Long productId);

    void deleteByName(String productName);
}
