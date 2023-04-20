package com.ethnicthv.webprojectcnpmrestful.data.entity.io;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Product;

import java.util.Objects;

@SuppressWarnings("unused")
public class ProductInCart {
    private long id;
    private String title;
    private float price;
    private int quantity;
    private float total;
    private float discountPercentage;
    private float discountedPrice;

    public ProductInCart(Product product, int quantity) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.quantity = quantity;
        this.total = this.price * this.quantity;
        this.discountPercentage = product.getDiscountPercentage();
        this.discountedPrice = this.total * (100 - this.discountPercentage) / 100f;
    }

    public ProductInCart(long id, String title, float price, int quantity, float total, float discountPercentage, float discountedPrice) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.discountPercentage = discountPercentage;
        this.discountedPrice = discountedPrice;
    }

    public ProductInCart() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public float getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(float discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    @Override
    public String toString() {
        return "ProductInCart{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", total=" + total +
                ", discountPercentage=" + discountPercentage +
                ", discountedPrice=" + discountedPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInCart that = (ProductInCart) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
