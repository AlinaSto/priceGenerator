package com.spring.priceGeneratorApp.controller;

import com.spring.priceGeneratorApp.model.Quotation;
import com.spring.priceGeneratorApp.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotation")
public class QuotationController {

    private QuotationService quotationService;

    @Autowired
    public QuotationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @PostMapping("/generate/{productId}")
    public Quotation generateQuotation(@PathVariable Long productId) {
        return quotationService.generateQuotation(productId);
    }

    @GetMapping("/active")
    public List<Quotation> getActiveQuotation() {
       return quotationService.getActiveQuotation();
    }

//    @DeleteMapping("/delete")
//    public void deleteQuotationWhenDateExpire() {
//        quotationService.deleteQuotationWhenDateExpire();
//    }
}