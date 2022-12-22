package com.spring.priceGeneratorApp.controller;

import com.spring.priceGeneratorApp.dto.AddProductDTO;
import com.spring.priceGeneratorApp.model.Product;
import com.spring.priceGeneratorApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody AddProductDTO addProductDTO) {
        return productService.addProduct(addProductDTO);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

    @PutMapping("/update/{productId}")
    public Product updateProductQuantity(@RequestBody AddProductDTO addProductDTO, @PathVariable Long productId) {
        return productService.updateProductQuantity(addProductDTO, productId);
    }
}
