package com.spring.priceGeneratorApp.repository;

import com.spring.priceGeneratorApp.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void findCartItemById(Long cartItemId);
    List<CartItem> findAllByUser_Id(Long userId);
}
