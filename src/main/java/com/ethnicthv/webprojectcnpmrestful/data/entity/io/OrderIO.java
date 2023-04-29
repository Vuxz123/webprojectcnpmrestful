package com.ethnicthv.webprojectcnpmrestful.data.entity.io;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Order;
import com.ethnicthv.webprojectcnpmrestful.data.entity.OrderState;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class OrderIO {
    @Id
    private Long id;

    private String customer_id;

    private String dest;

    private String src;

    private LocalDateTime orderDate;

    private OrderState state;

    private Map<Long, Integer> products;

    public OrderIO(Order order) {
        this.id = order.getId();
        this.customer_id = order.getCustomer_id();
        this.dest = order.getDest();
        this.src = order.getSrc();
        this.orderDate = order.getOrderDate();
        this.state = order.getState();
        this.products = new HashMap<>();
        order.getProducts().forEach((product, integer) -> products.put(product.getId(), integer));
    }

    public OrderIO(Long id, String customer_id, String dest, String src, LocalDateTime orderDate, OrderState state, Map<Long, Integer> products) {
        this.id = id;
        this.customer_id = customer_id;
        this.dest = dest;
        this.src = src;
        this.orderDate = orderDate;
        this.state = state;
        this.products = products;
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

    public Map<Long, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Long, Integer> products) {
        this.products = products;
    }
}

