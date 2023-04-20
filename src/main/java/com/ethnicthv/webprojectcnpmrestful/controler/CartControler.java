package com.ethnicthv.webprojectcnpmrestful.controler;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Cart;
import com.ethnicthv.webprojectcnpmrestful.data.entity.Product;
import com.ethnicthv.webprojectcnpmrestful.data.entity.io.CartDeleted;
import com.ethnicthv.webprojectcnpmrestful.data.entity.io.ProductInCart;
import com.ethnicthv.webprojectcnpmrestful.data.service.CartService;
import com.ethnicthv.webprojectcnpmrestful.data.service.ProductService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(CartControler.class);
    private final CartService cartService;
    private final ProductService productService;

    @Autowired
    public CartControler(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
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

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody Map<String, Object> fields) {
        System.out.println(fields.size());
        System.out.println(fields.keySet());
        Cart cart = cartService.getCartOfUser((String) fields.get("user_id"));
        Product product = productService.getProduct((Integer) fields.get("product_id"));
        ProductInCart productInCart = new ProductInCart(product, (Integer) fields.get("total"));
        System.out.println(productInCart);
        cart.addProduct(productInCart);
        cartService.saveCart(cart);
        return new ResponseEntity<>("success", HttpStatus.OK);
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
