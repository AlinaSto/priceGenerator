package com.spring.priceGeneratorApp.config;

import com.spring.priceGeneratorApp.model.Quotation;
import com.spring.priceGeneratorApp.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuotationDeletionConfig {

    private QuotationRepository quotationRepository;


    @Autowired
    public QuotationDeletionConfig(QuotationRepository quotationRepository) {
        this.quotationRepository = quotationRepository;
    }

    @Scheduled(cron = "0 * * * * *")
    public void scheduleTaskUsingCronExpression() {

        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "schedule tasks using cron jobs - " + now);

        //verficam fiecare quotation din db daca e expirat sau nu
        //un quotation e expirat daca data actuala este dupa data expirarii
        //cu cele pe care le gasim ca sunt expirate, le stergem din db
        List<Quotation> allQuotations = quotationRepository.findAll();
//        List<Quotation> expiredQuotations = allQuotations.stream()
//                .filter(quotation -> !quotation.getExpireDate().isAfter(LocalDateTime.now()))
//                .collect(Collectors.toList());
//        quotationRepository.deleteAll(expiredQuotations);
        for (Quotation quotation : allQuotations) {
            if (!quotation.getExpireDate().isAfter(LocalDateTime.now())) {
                quotationRepository.delete(quotation);
            }
        }
    }
}
