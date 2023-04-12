package com.ethnicthv.webprojectcnpmrestful.data.service;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Cart;
import com.ethnicthv.webprojectcnpmrestful.data.entity.io.CartDeleted;
import com.ethnicthv.webprojectcnpmrestful.data.entity.io.ProductInCart;
import com.ethnicthv.webprojectcnpmrestful.data.repository.CartRepository;
import com.ethnicthv.webprojectcnpmrestful.util.IDUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    public Cart getCart(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));
    }

    public Cart getCartOfUser(String userId) {
        Optional<Cart> cart = cartRepository.findCartByUserId(userId);
        return cart.orElseGet(() -> createCart(new Cart(userId)));
    }

    public Cart createCart(Cart cart) {
        cart.setId(IDUtils.getID());
        cartRepository.save(cart);
        return cart;
    }

    public Cart updateCart(Long id, Cart cart) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            Cart existingCart = optionalCart.get();
            existingCart.setUserId(cart.getUserId());
            existingCart.setProducts(cart.getProducts());
            existingCart.setTotal(cart.getTotal());
            existingCart.setDiscountedTotal(cart.getDiscountedTotal());
            existingCart.setTotalProducts(cart.getTotalProducts());
            existingCart.setTotalQuantity(cart.getTotalQuantity());
            return cartRepository.save(existingCart);
        } else {
            throw new RuntimeException("Cart not found with id: " + id);
        }
    }

    public Cart partialUpdateCart(Long id, Map<String, Object> fields) {
        Cart cartToUpdate = cartRepository.findById(id).orElse(null);

        if (cartToUpdate == null) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            String json = objectMapper.writeValueAsString(fields);
            JsonNode jsonNode = objectMapper.readTree(json);

            if (jsonNode.has("products")) {
                if (jsonNode.has("merge") && objectMapper.convertValue(jsonNode.get("merge"), new TypeReference<Boolean>() {})) {
                    List<ProductInCart> products = cartToUpdate.getProducts();
                    List<ProductInCart> updated_products = objectMapper.convertValue(jsonNode.get("products"), new TypeReference<List<ProductInCart>>() {});
                    products.addAll(updated_products);
                    products = products.stream().distinct().toList();
                    cartToUpdate.setProducts(products);
                } else {
                    List<ProductInCart> products = objectMapper.convertValue(jsonNode.get("products"), new TypeReference<List<ProductInCart>>() {});
                    cartToUpdate.setProducts(products);
                }
            }

            if (jsonNode.has("total")) {
                int total = jsonNode.get("total").asInt();
                cartToUpdate.setTotal(total);
            }

            if (jsonNode.has("discountedTotal")) {
                float discountedTotal = jsonNode.get("discountedTotal").floatValue();
                cartToUpdate.setDiscountedTotal(discountedTotal);
            }

            if (jsonNode.has("totalProducts")) {
                int totalProducts = jsonNode.get("totalProducts").asInt();
                cartToUpdate.setTotalProducts(totalProducts);
            }

            if (jsonNode.has("totalQuantity")) {
                int totalQuantity = jsonNode.get("totalQuantity").asInt();
                cartToUpdate.setTotalQuantity(totalQuantity);
            }

            return cartRepository.save(cartToUpdate);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public CartDeleted deleteCart(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isPresent()) {
            cartRepository.deleteById(id);
            return new CartDeleted(cart.get(), cartRepository.existsById(id), Instant.now().toString());
        }else {
            return new CartDeleted(false, Instant.now().toString());
        }
    }
}
