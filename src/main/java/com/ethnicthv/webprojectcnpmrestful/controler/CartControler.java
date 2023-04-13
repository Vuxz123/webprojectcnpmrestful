package com.ethnicthv.webprojectcnpmrestful.controler;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Cart;
import com.ethnicthv.webprojectcnpmrestful.data.entity.io.CartDeleted;
import com.ethnicthv.webprojectcnpmrestful.data.service.CartService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping("/carts")
public class CartControler {
    private final CartService cartService;

    @Autowired
    public CartControler(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("")
    public ResponseEntity<String> getCarts() {
        List<Cart> carts = cartService.getCarts();
        String cartJson = new Gson().toJson(carts);
        return new ResponseEntity<>(cartJson, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(long id) {
        Cart cart = cartService.getCart(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Cart> getCartOfUser(@PathVariable String id) {
        Cart cart = cartService.getCartOfUser(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        Cart updatedCart = cartService.updateCart(id, cart);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    // PATCH method to update some fields of cart
    @PatchMapping("/{id}")
    public ResponseEntity<Cart> partialUpdateCart(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Cart updatedCart = cartService.partialUpdateCart(id, fields);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CartDeleted> deletedCart(@PathVariable Long id) {
        var deletedCart = cartService.deleteCart(id);
        return new ResponseEntity<>(deletedCart, HttpStatus.OK);
    }


    @RequestMapping("/test")
    public String test() {
        Gson gson = new Gson();
        FileReader reader = null;
        try {
            reader = new FileReader("C:\\Users\\HOANG VU\\OneDrive\\webprojectcnpmrestful\\src\\main\\resources\\test_json\\test.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
