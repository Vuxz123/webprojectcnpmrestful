package com.ethnicthv.webprojectcnpmrestful.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ProductControler {

    @RequestMapping("/products")
    public ResponseEntity<String> getProducts(@RequestParam(name = "limit", defaultValue = "40", required = false) int limit,
                                      @RequestParam(name = "catalog", defaultValue = "", required = false) String catalog) {

        return null;
    }

}
