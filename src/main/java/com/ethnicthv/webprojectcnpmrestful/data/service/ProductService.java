package com.ethnicthv.webprojectcnpmrestful.data.service;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Product;
import com.ethnicthv.webprojectcnpmrestful.data.repository.ProductRepository;
import com.ethnicthv.webprojectcnpmrestful.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct(Product product) {
        if (productRepository.existsById(product.getId())) productRepository.deleteById(product.getId());
        productRepository.save(product);
    }

    public void saveProducts(List<Product> products) {
        products.forEach(this::saveProduct);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
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
        return list.stream()
                .map(Product::getCategory)
                .distinct()
                .toList();
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

    public Product updateProduct(long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);
        existingProduct.setTitle(updatedProduct.getTitle());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setDiscountPercentage(updatedProduct.getDiscountPercentage());
        existingProduct.setRating(updatedProduct.getRating());
        existingProduct.setStock(updatedProduct.getStock());
        existingProduct.setBrand(updatedProduct.getBrand());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setThumbnail(updatedProduct.getThumbnail());
        existingProduct.setImages(updatedProduct.getImages());

        saveProduct(existingProduct);
        return existingProduct;
    }

    public Product partialUpdateProduct(Long id, Map<String, Object> fields) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            fields.forEach((key, value) -> {
                switch (key) {
                    case "title" -> product.setTitle((String) value);
                    case "description" -> product.setDescription((String) value);
                    case "discountPercentage" -> product.setDiscountPercentage((Float) value);
                    case "rating" -> product.setRating((Float) value);
                    case "stock" -> product.setStock((Integer) value);
                    case "brand" -> product.setBrand((String) value);
                    case "category" -> product.setCategory((String) value);
                    case "thumbnail" -> product.setThumbnail((String) value);
                    case "images" -> product.setImages((String[]) value);
                    default -> throw new IllegalArgumentException("Invalid field: " + key);
                }
            });
            productRepository.save(product);
            return product;
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

}
