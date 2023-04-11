package com.ethnicthv.webprojectcnpmrestful.controler;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Cart;
import com.ethnicthv.webprojectcnpmrestful.data.entity.Product;
import com.ethnicthv.webprojectcnpmrestful.data.service.ProductService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;

@RestController

public class ProductControler {
    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public ResponseEntity<String> getProducts(@RequestParam(name = "limit", defaultValue = "40", required = false) int limit,
                                      @RequestParam(name = "catalog", defaultValue = "", required = false) String catalog) {
        var list = productService.getProducts();
        Gson gson = new Gson();
        String productsJson = gson.toJson(list);
        return new ResponseEntity<>(productsJson, HttpStatus.OK);
    }

}
