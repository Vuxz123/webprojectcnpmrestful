package com.ethnicthv.webprojectcnpmrestful.data.service;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Product;
import com.ethnicthv.webprojectcnpmrestful.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Product product) {
        productRepository.insert(product);
    }

    public void saveProducts(List<Product> products) {
        products.forEach(product -> {
            productRepository.insert(product);
        });
    }

    public List<Product> getProducts() {
         return productRepository.findAll(Sort.by("id"));
    }

    public Product getProduct(long id) {
        return productRepository.findOneById(id);
    }
}
