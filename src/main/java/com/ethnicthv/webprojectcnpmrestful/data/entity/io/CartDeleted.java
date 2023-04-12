package com.ethnicthv.webprojectcnpmrestful.data.entity.io;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Cart;

public class CartDeleted extends Cart {
    private boolean isDeleted;
    private String deletedOn;

    public CartDeleted(Cart cart, boolean isDeleted, String deletedOn) {
        this.setUserId(cart.getUserId());
        this.setId(cart.getId());
        this.setProducts(cart.getProducts());
        this.setTotal(cart.getTotal());
        this.setTotalProducts(cart.getTotalProducts());
        this.setDiscountedTotal(cart.getDiscountedTotal());
        this.setTotalQuantity(cart.getTotalQuantity());
        this.deletedOn = deletedOn;
        this.isDeleted = isDeleted;
    }

    public CartDeleted(String userId, boolean isDeleted, String deletedOn) {
        super(userId);
        this.isDeleted = isDeleted;
        this.deletedOn = deletedOn;
    }

    public CartDeleted(boolean isDeleted, String deletedOn) {
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
