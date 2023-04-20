package com.ethnicthv.webprojectcnpmrestful.data.entity.io;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Product;

public class ProductDeleted extends Product {
    private boolean isDeleted;
    private String deletedOn;

    public ProductDeleted(Product product, boolean isDeleted, String deletedOn) {
        super(product.getId(), product.getTitle(), product.getDescription(), product.getDiscountPercentage(), product.getRating(), product.getStock(), product.getBrand(), product.getCategory(), product.getThumbnail(), product.getImages(), product.getPrice());
        this.deletedOn = deletedOn;
        this.isDeleted = isDeleted;
    }

    public ProductDeleted(long id, String title, String description, float discountPercentage, float rating, int stock, String brand, String category, String thumbnail, String[] images, float price, boolean isDeleted, String deletedOn) {
        super(id, title, description, discountPercentage, rating, stock, brand, category, thumbnail, images, price);
        this.isDeleted = isDeleted;
        this.deletedOn = deletedOn;
    }

    public ProductDeleted(boolean isDeleted, String deletedOn) {
        this.isDeleted = isDeleted;
        this.deletedOn = deletedOn;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(String deletedOn) {
        this.deletedOn = deletedOn;
    }
}
