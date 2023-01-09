package com.spring.priceGeneratorApp.service;

import com.spring.priceGeneratorApp.model.*;
import com.spring.priceGeneratorApp.repository.CartItemRepository;
import com.spring.priceGeneratorApp.repository.OrderItemRepository;
import com.spring.priceGeneratorApp.repository.OrderRepository;
import com.spring.priceGeneratorApp.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private UserService userService;
    private CartItemService cartItemService;
    private CartItemRepository cartItemRepository;
    private OrderItemRepository orderItemRepository;
    private QuotationRepository quotationRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, QuotationRepository quotationRepository, CartItemRepository cartItemRepository, UserService userService, CartItemService cartItemService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.cartItemRepository = cartItemRepository;
        this.quotationRepository = quotationRepository;
        this.orderItemRepository = orderItemRepository;
    }

    //Faci o comanda pentru una, doua… sau toate cotatiile (ofertele de pret) active
    //Aici se va aplica, dupa caz, si reducerea pentru numarul de cotatii din comanda, care practic este egal cu numarul de produse (pentru ca o cotatie se genereaza pentru un produs)
//todo pasi:
    //ma folosesc de user ul log at
    //imi creez un cartItem
    public Order placeOrder() {
        User foundUser = userService.findLoggedInUser();
        List<CartItem> cartItemList = cartItemRepository.findAllByUser(foundUser);
        List<Quotation> quotationList = quotationRepository.findAllByUser(foundUser);
        List<OrderItem> orderItemList = orderItemRepository.findAllByUser(foundUser);

        Order order = new Order();
        order.setCreatedDate(LocalDate.now());
        order.setUser(foundUser);
        order.setQuotationList(quotationList);
        for (CartItem cartItem : cartItemList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            order.getOrderItemList().add(orderItem);
            //  cartItemService.deleteAllUserCartItems(foundUser.getId());
           generateDiscountPerNoOfProduct(orderItemList, order, orderItem);
        }
        return orderRepository.save(order);
    }

    private void generateDiscountPerNoOfProduct(List<OrderItem> orderItemList, Order order, OrderItem orderItem) {
        for (OrderItem newOrderItem : orderItemList) {
            double discountPerProduct = orderItem.getProduct().getProductPrice() * 0.005;
            if (orderItemList.size() > 3) {
                order.setTotalPrice(order.getTotalPrice() + discountPerProduct);
            }
        }
    }
}