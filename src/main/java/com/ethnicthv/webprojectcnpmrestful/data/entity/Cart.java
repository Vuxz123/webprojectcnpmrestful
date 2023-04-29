package com.ethnicthv.webprojectcnpmrestful.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "carts")
@SuppressWarnings("unused")
public class Cart {
    @Id
    private long id;
    private List<ProductInCart> products;
    private float total;
    private float discountedTotal;
    private String userId;
    private int totalProducts;
    private int totalQuantity;

    public Cart(long id, List<ProductInCart> products, int total, float discountedTotal, String userId, int totalProducts, int totalQuantity) {
        this.id = id;
        this.products = products;
        this.total = total;
        this.discountedTotal = discountedTotal;
        this.userId = userId;
        this.totalProducts = totalProducts;
        this.totalQuantity = totalQuantity;
    }

    public Cart(String userId) {
        this.products = new ArrayList<>();
        this.total = 0;
        this.discountedTotal = 0;
        this.userId = userId;
        this.totalProducts = 0;
        this.totalQuantity = 0;
    }

    public void addProduct(ProductInCart productInCart) {
        this.products.add(productInCart);
        this.total += productInCart.getDiscountedPrice();
        this.discountedTotal += productInCart.getPrice() - productInCart.getDiscountedPrice();
        this.totalProducts = products.isEmpty() ? 0 : (int) this.products.stream().map(ProductInCart::getId).distinct().count();
        this.totalQuantity += productInCart.getTotal();
    }

    public Cart() {
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", products.json=" + products +
                ", total=" + total +
                ", discountedTotal=" + discountedTotal +
                ", userId=" + userId +
                ", totalProducts=" + totalProducts +
                ", totalQuantity=" + totalQuantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id == cart.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
