package com.ethnicthv.webprojectcnpmrestful.controler;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Product;
import com.ethnicthv.webprojectcnpmrestful.data.entity.Review;
import com.ethnicthv.webprojectcnpmrestful.data.entity.io.ProductDeleted;
import com.ethnicthv.webprojectcnpmrestful.data.service.ProductService;
import com.ethnicthv.webprojectcnpmrestful.data.service.ReviewService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/products")
public class ProductControler {
    Gson gson = new Gson();
    private final ProductService productService;
    private final ReviewService reviewService;

    private Logger logger = LoggerFactory.getLogger(ProductControler.class);

    @Autowired
    public ProductControler(ProductService productService, ReviewService reviewService) {
        this.productService = productService;
        this.reviewService = reviewService;
    }

    //Review////////////////////////////////////

    @GetMapping("/reviews/{id}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable long id) {
        List<Review> reviews = reviewService.getReviews(id);
        //if (reviews == null) reviews = new ArrayList<>(0);
        logger.info("Get reviews: {} " + reviews, id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public void createReviews(@RequestBody Review review) {
        reviewService.saveReview(review);
        logger.info("Create new review: " + review);
    }

    ////////////////////////////////////////////

    @GetMapping("/{id:.+}")
    public ResponseEntity<String> getProduct(@PathVariable long id) {
        Product product = productService.getProduct(id);
        var productJson = gson.toJson(product);
        return new ResponseEntity<>(productJson, HttpStatus.OK);
    }

    @GetMapping("/con")
    public ResponseEntity<String> getProducts(@RequestParam(name = "limit", defaultValue = "40", required = false) int limit,
                                              @RequestParam(name = "catalog", defaultValue = "", required = false) String catalog,
                                              @RequestParam(name = "skip", defaultValue = "0", required = false) int skip,
                                              @RequestParam(name = "category", defaultValue = "", required = false) String category) {
        List<Product> list;
        if(category.equals("")) list = productService.getProducts();
        else list = productService.getProducts(category);
        var productsJson = limitAndSkip(limit, skip, list);
        return new ResponseEntity<String>(productsJson, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<String> getCategories() {
        var list = productService.getCategories();
        Gson gson = new Gson();
        String categoriesJson = gson.toJson(list);
        return new ResponseEntity<>(categoriesJson, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchProduct(@RequestParam(name = "q", required = true) String q,
                                                @RequestParam(name = "limit", required = false, defaultValue = "20") int limit,
                                                @RequestParam(name = "skip", defaultValue = "0", required = false) int skip,
                                                @RequestParam(name = "category", defaultValue = "", required = false) String category) {
        List<Product> list;
        if(category.equals("")) list = productService.searchProducts(q);
        else list = productService.searchProducts(q, category);
        String productsJson = limitAndSkip(limit, skip, list);
        return new ResponseEntity<>(productsJson, HttpStatus.OK);
    }

    private String limitAndSkip(@RequestParam(name = "limit", required = false, defaultValue = "20") int limit, @RequestParam(name = "skip", defaultValue = "0", required = false) int skip, List<Product> list) {
        if(list.size() > limit + skip) {
            list = list.subList(skip, limit - 1);
        } else if(list.size() > skip) {
            list = list.subList(skip, list.size() - 1);
        }
        String productsJson = gson.toJson(list);
        return productsJson;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> partialUpdateProduct(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Product updatedProduct = productService.partialUpdateProduct(id, fields);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDeleted> deletedProduct(@PathVariable Long id) {
        var deletedProduct = productService.deleteProduct(id);
        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }

    @GetMapping("test")
    public String test() {
        var gson = new Gson();
        Product[] products;
        try {
            products = gson.fromJson(new FileReader("C:\\Users\\HOANG VU\\OneDrive\\webprojectcnpmrestful\\src\\main\\resources\\test_json\\products.json"), Product[].class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Arrays.stream(products).forEach(product -> {
            productService.saveProduct(product);
        });
        return "success";
    }

}
