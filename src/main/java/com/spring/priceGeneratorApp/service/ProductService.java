package com.spring.priceGeneratorApp.service;

import com.spring.priceGeneratorApp.dto.AddProductDTO;
import com.spring.priceGeneratorApp.dto.DiscountDTO;
import com.spring.priceGeneratorApp.dto.QuotationDTO;

import com.spring.priceGeneratorApp.model.*;
import com.spring.priceGeneratorApp.repository.CountryRepository;
import com.spring.priceGeneratorApp.repository.ProductRepository;
import com.spring.priceGeneratorApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //0.verific daca exista tara, daca nu dau exceptie
    //1. construiesc discountul de tip Discount in functie de ce am in DTO
    //1.2. leg discountul de produs (bidirectinoal), leg discountul de tara (aici doar unidirecitonal setCountry)
    public Product addProduct(AddProductDTO addProductDTO) {
        Optional<Product> foundProduct = productRepository.findProductByName(addProductDTO.getProductName());
        if (foundProduct.isPresent()) {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "This product name already exist");
        }
        Product productToBeAdd = new Product();
        productToBeAdd.setName(addProductDTO.getProductName());
        productToBeAdd.setProductType(addProductDTO.getProductType());
        productToBeAdd.setProductPrice(addProductDTO.getProductPrice());
        productToBeAdd.setProductQuantity(addProductDTO.getProductQuantity());
        generateDiscountsToProduct(addProductDTO, productToBeAdd);
        //generateQuotationToProduct(addProductDTO, productToBeAdd);
        return productRepository.save(productToBeAdd);
    }

    private void generateDiscountsToProduct(AddProductDTO addProductDTO, Product productToBeAdd) {
        for (DiscountDTO discountDTO : addProductDTO.getDiscounts()) {
            Country foundCountry = countryRepository.findCountryByCountryName(discountDTO.getCountry());
            if (foundCountry == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, " country not found");
            }
            Discount discount = new Discount();
            discount.setDiscount(discountDTO.getDiscount());
            discount.setProduct(productToBeAdd);
            productToBeAdd.getDiscountList().add(discount);
            discount.setCountry(foundCountry);

        }
    }
//    private void generateQuotationToProduct(AddProductDTO addProductDTO, Product productToBeAdd) {
//        for (QuotationDTO quotationDTO : addProductDTO.getQuotationList()) {
//            Optional<User> foundUser = userRepository.findUserByUsername(quotationDTO.getUser());
//            if (foundUser == null) {
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, " user not found");
//            }
//            Quotation quotation = new Quotation();
//            quotation.setUser(quotation.getUser());
//            quotation.setProduct(productToBeAdd);
//            productToBeAdd.getQuotationList().add(quotation);
//            quotation.setExpireDate(quotation.getExpireDate());
//        }
//    }

    public void deleteProduct(Long productId) {
        //  Product foundProduct = productRepository.findProductByName(addProductDTO.getProductName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        Product foundProduct = productRepository.findProductById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        productRepository.delete(foundProduct);
    }
}
