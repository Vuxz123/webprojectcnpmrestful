package com.ethnicthv.webprojectcnpmrestful.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "reviews")
public class Review {
    @Id
    private long id;
    private String title;
    private String overview;
    private float rating;
    private String description;
    private String images;
    private long productId;
    private boolean updated = false;

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public String getImages() {
        return images;
    }

    public boolean isUpdated() {
        return updated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id && productId == review.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", images='" + images + '\'' +
                ", productId=" + productId +
                '}';
    }
}
