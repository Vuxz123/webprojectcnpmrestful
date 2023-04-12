package com.ethnicthv.webprojectcnpmrestful.data.service;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Product;
import com.ethnicthv.webprojectcnpmrestful.data.repository.ProductRepository;
import com.ethnicthv.webprojectcnpmrestful.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Product product) {
        if (productRepository.existsById(product.getId())) productRepository.deleteById(product.getId());
        productRepository.save(product);
    }

    public void saveProducts(List<Product> products) {
        products.forEach(this::saveProduct);
    }

    public List<Product> getProducts() {
        return productRepository.findAll(Sort.by("id"));
    }

    public List<Product> getProducts(String category) {
        return productRepository.findAllByCategoryOrderById(category);
    }

    public Product getProductById(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    public Product getProduct(long id) {
        return productRepository.findOneById(id);
    }

    public List<String> getCategories() {
        var list = productRepository.findAll();
        var res_list = list.stream()
                .map(Product::getCategory)
                .distinct()
                .toList();
        return res_list;
    }

    public List<Product> searchProducts(String query) {
        return productRepository.search(query);
    }

    public List<Product> searchProducts(String query, String category) {
        return productRepository.search(query, category);
    }

    public Product createProduct(Product product) {
        product.setId(IDUtils.getID());
        productRepository.save(product);
        return product;
    }
}
