package com.ethnicthv.webprojectcnpmrestful.controler;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Cart;
import com.ethnicthv.webprojectcnpmrestful.data.repository.CartRepository;
import com.ethnicthv.webprojectcnpmrestful.data.service.CartService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;

@RestController
@RequestMapping("/carts")
public class CartControler {
    @Autowired
    private CartService cartService;

    @GetMapping("/num/{nume:.+}")
    public ResponseEntity<String> getCartNum(@PathVariable int nume) {
        Gson gson = new Gson();
        FileReader reader = null;
        try {
            reader = new FileReader("C:\\Users\\HOANG VU\\OneDrive\\webprojectcnpmrestful\\src\\main\\resources\\test_json\\test.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Cart cart = gson.fromJson(reader, Cart.class);
        System.out.print(cart);
        String cartJson = gson.toJson(cart);

        return new ResponseEntity<>(cartJson, HttpStatus.OK);
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
        Cart cart = gson.fromJson(reader, Cart.class);
        cartService.saveCart(cart);
        return null;
    }
}
