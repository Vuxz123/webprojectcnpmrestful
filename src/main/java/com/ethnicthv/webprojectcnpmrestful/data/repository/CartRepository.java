package com.ethnicthv.webprojectcnpmrestful.data.repository;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CartRepository extends MongoRepository<Cart, Long> {
    Cart findCartByUserId(String userId);
}
