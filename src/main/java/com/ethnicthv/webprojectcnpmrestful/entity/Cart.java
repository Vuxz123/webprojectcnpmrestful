package com.ethnicthv.webprojectcnpmrestful.entity;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unused")
public class Cart {
    public static long id_now = 0;
    private long id;
    private List<ProductInCart> products;
    private int total;
    private float discountedTotal;
    private final String userId;
    private int totalProducts;
    private int totalQuantity;

    public Cart(String userId) {
        id = id_now;
        id_now++;
        products = new ArrayList<>();
        total = 0;
        discountedTotal = 0;
        this.userId = userId;
        totalProducts = 0;
        totalQuantity = 0;
    }

    public static long getId_now() {
        return id_now;
    }

    public static void setId_now(long id_now) {
        Cart.id_now = id_now;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProductInCart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInCart> products) {
        this.products = products;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public float getDiscountedTotal() {
        return discountedTotal;
    }

    public void setDiscountedTotal(float discountedTotal) {
        this.discountedTotal = discountedTotal;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", products=" + products +
                ", total=" + total +
                ", discountedTotal=" + discountedTotal +
                ", userId=" + userId +
                ", totalProducts=" + totalProducts +
                ", totalQuantity=" + totalQuantity +
                '}';
    }
}
