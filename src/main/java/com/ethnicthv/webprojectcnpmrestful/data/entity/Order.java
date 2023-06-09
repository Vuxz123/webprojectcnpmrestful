package com.ethnicthv.webprojectcnpmrestful.data.entity;

import java.time.LocalDateTime;
import java.util.Map;

import com.ethnicthv.webprojectcnpmrestful.util.IDUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {
    @Id
    private Long id;

    private String customer_id;

    private String dest;

    private String src;

    private LocalDateTime orderDate;

    private OrderState state;

    private Map<Product, Integer> products;

    public Order(String customer_id, String dest, String src, LocalDateTime orderDate, OrderState state, Map<Product, Integer> products) {
        this.id = IDUtils.getID();
        this.customer_id = customer_id;
        this.dest = dest;
        this.src = src;
        this.orderDate = orderDate;
        this.state = state;
        this.products = products;
    }

    public Order(Long id, String customer_id, String dest, String src, LocalDateTime orderDate, OrderState state, Map<Product, Integer> products) {
        this.id = id;
        this.customer_id = customer_id;
        this.dest = dest;
        this.src = src;
        this.orderDate = orderDate;
        this.state = state;
        this.products = products;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }
}
