package com.spring.priceGeneratorApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;
    @Column
    private LocalDate dateOfBirth;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;

    @Column
    private String country;

    @ManyToMany
    @JsonIgnoreProperties("userList")
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList;

    //O tara are mai multe discounturi. Un discount este la o tara.
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @JsonManagedReference(value = "user-order")
    private List<Order> orderList;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @JsonManagedReference(value = "user-quotation")
    private List<Quotation> quotationList;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @JsonManagedReference(value = "user-cartItem")
    private List<CartItem> cartItems;
    public User() {
    }

    public User(Long id, LocalDate dateOfBirth, String username, String password, String email, String country, List<Role> roleList, List<Order> orderList, List<Quotation> quotationList, List<CartItem> cartItems) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.email = email;
        this.country = country;
        this.roleList = roleList;
        this.orderList = orderList;
        this.quotationList = quotationList;
        this.cartItems = cartItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoleList() {
        if (this.roleList == null) {
            roleList = new ArrayList<>();
        }
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Order> getOrderList() {
        if (this.orderList == null) {
            orderList = new ArrayList<>();
        }
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Quotation> getQuotationList() {
        return quotationList;
    }

    public void setQuotationList(List<Quotation> quotationList) {
        this.quotationList = quotationList;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}