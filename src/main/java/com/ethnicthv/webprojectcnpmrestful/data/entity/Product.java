package com.ethnicthv.webprojectcnpmrestful.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Objects;

@Document(collection = "products")
@SuppressWarnings("unused")
public class Product {
    @Id
    private long id;
    private String title;
    private String description;
    private float discountPercentage;
    private float rating;
    private int stock;
    private String brand;
    private String category;
    private String thumbnail;
    private String[] images;
    private float price;

    public Product(long id, String title, String description, float discountPercentage, float rating, int stock, String brand, String category, String thumbnail, String[] images, float price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.rating = rating;
        this.stock = stock;
        this.brand = brand;
        this.category = category;
        this.thumbnail = thumbnail;
        this.images = images;
        this.price = price;
    }

    public Product() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", rating=" + rating +
                ", stock=" + stock +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", images=" + Arrays.toString(images) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
