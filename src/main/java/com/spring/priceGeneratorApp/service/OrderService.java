package com.spring.priceGeneratorApp.service;

import com.spring.priceGeneratorApp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    //Faci o comanda pentru una, doua… sau toate cotatiile (ofertele de pret) active
    //Aici se va aplica, dupa caz, si reducerea pentru numarul de cotatii din comanda, care practic este egal cu numarul de produse (pentru ca o cotatie se genereaza pentru un produs)
//todo pasi:
     //ma folosesc de user ul log at
    //imi creez un cartItem



}
