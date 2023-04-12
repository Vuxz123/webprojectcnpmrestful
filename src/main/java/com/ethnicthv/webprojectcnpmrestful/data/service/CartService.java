package com.ethnicthv.webprojectcnpmrestful.data.service;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Cart;
import com.ethnicthv.webprojectcnpmrestful.data.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    public void saveCart(Cart cart) {
        cartRepository.insert(cart);
    }
}
