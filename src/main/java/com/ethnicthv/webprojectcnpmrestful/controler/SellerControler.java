package com.ethnicthv.webprojectcnpmrestful.controler;

import com.ethnicthv.webprojectcnpmrestful.data.entity.SellerProductList;
import com.ethnicthv.webprojectcnpmrestful.data.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/seller")
public class SellerControler {
    private final SellerService sellerService;

    @Autowired
    public SellerControler(SellerService sellerService) {
        this.sellerService = sellerService;
    }


    @RequestMapping("/{id}")
    public ResponseEntity<SellerProductList> isSeller(@PathVariable String id) {
        var optional = sellerService.findById(id);
        return optional.map(sellerProductList -> new ResponseEntity<>(sellerProductList, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @RequestMapping("/create/{id}")
    public ResponseEntity<SellerProductList> makeSeller(@PathVariable String id) {
        if (sellerService.findById(id).isPresent()) return new ResponseEntity<>(null, HttpStatus.CREATED);
        var response = sellerService.save(new SellerProductList(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SellerProductList> updateSeller(@PathVariable String id, @RequestBody SellerProductList sellerProductList) {
        return new ResponseEntity<>(sellerService.save(sellerProductList), HttpStatus.OK);
    }
}
