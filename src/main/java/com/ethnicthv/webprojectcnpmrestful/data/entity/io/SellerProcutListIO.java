package com.ethnicthv.webprojectcnpmrestful.data.entity.io;

import com.ethnicthv.webprojectcnpmrestful.data.entity.SellerProductList;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class SellerProcutListIO {

    @Id
    private String userId;
    private List<Long> productIds;

    public SellerProcutListIO() {
    }

    public SellerProcutListIO(SellerProductList list) {
        userId = list.getUserId();
        productIds = new ArrayList<>();
        list.getProducts().forEach(product -> productIds.add(product.getId()));
    }

    public SellerProcutListIO(String userId, List<Long> productIds) {
        this.userId = userId;
        this.productIds = productIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
