package com.ethnicthv.webprojectcnpmrestful.data.entity;

import java.util.List;

public class SellerProductList {
    private String userId;
    private List<Product> products;

    public SellerProductList(String userId, List<Product> products) {
        this.userId = userId;
        this.products = products;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
