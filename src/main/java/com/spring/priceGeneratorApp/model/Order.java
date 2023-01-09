package com.spring.priceGeneratorApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column
    private LocalDate createdDate;
    @Column
    private Double totalPrice;
    @ManyToOne
    @JsonBackReference(value = "user-order")
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference(value = "order-orderItem")
    private List<OrderItem> orderItemList;
    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL})
    @JsonManagedReference(value = "order-quotation")
    private List<Quotation> quotationList;

    public Order() {
    }

    public Order(Long id, LocalDate createdDate,Double totalPrice, User user, List<OrderItem> orderItemList, List<Quotation> quotationList) {
        this.id = id;
        this.createdDate = createdDate;
        this.user = user;
        this.orderItemList = orderItemList;
        this.quotationList = quotationList;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Quotation> getQuotationList() {
        if (this.quotationList == null) {
            quotationList = new ArrayList<>();
        }
        return quotationList;
    }

    public List<OrderItem> getOrderItemList() {
        if (this.orderItemList == null) {
            orderItemList = new ArrayList<>();
        }
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public void setQuotationList(List<Quotation> quotationList) {
        this.quotationList = quotationList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
