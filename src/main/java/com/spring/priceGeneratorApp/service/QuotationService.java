package com.spring.priceGeneratorApp.service;

import com.spring.priceGeneratorApp.model.*;
import com.spring.priceGeneratorApp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Service
public class QuotationService {
    private QuotationRepository quotationRepository;

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private CountryRepository countryRepository;
    private DiscountRepository discountRepository;

    @Autowired
    public QuotationService(QuotationRepository quotationRepository, ProductRepository productRepository, UserRepository userRepository, DiscountRepository discountRepository, CountryRepository countryRepository) {
        this.quotationRepository = quotationRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.discountRepository = discountRepository;
        this.countryRepository = countryRepository;
    }

    public Quotation generateQuotation(Long productId) {
        Product foundProduct = productRepository.findProductById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        //1. luam userul logat
        //2. luam varsta userului
        //3. luam pragul produsului
        //4. verificam daca varsta userului se incadreaza pt reducere in functie de prag
        //4.1 calculam reducerea de varsta
        //4.2 asignam valoarea reducerii de varsta unei noi cotatii
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User foundUser = userRepository.findUserByUsername(userDetails.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        Integer foundUserAge = Period.between(foundUser.getDateOfBirth(), LocalDate.now()).getYears();
        Quotation quotation = new Quotation();
        if (foundUserAge >= foundProduct.getProductAgeDiscountThreshold()) {
            quotation.setAgeDiscount(foundProduct.getProductPrice() * 0.2);
        }

        //1. luam tara clientului
        //2. luam procentul de discount de la tara clientului
        //3. calculez reducere de tara
        //4. o asignez cotatiei
        String foundUserCountry = foundUser.getCountry();
        Discount countryDiscount = discountRepository.findDiscountByCountry_CountryNameAndProduct(foundUserCountry, foundProduct);
        quotation.setCountryDiscount(foundProduct.getProductPrice() * countryDiscount.getDiscount()/100);

        quotation.setProduct(foundProduct);
        quotation.setUser(foundUser);
        quotation.setExpireDate(LocalDateTime.now().plusMinutes(5));
        return quotationRepository.save(quotation);

    }

}