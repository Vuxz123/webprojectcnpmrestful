package com.ethnicthv.webprojectcnpmrestful.data.repository;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, Long> {

    @Query("{_id: ?0}")
    Product findOneById(long id);

    @Query("{ $or: [ { 'name': { $regex: ?0, $options: 'i' } }, { 'description': { $regex: ?0, $options: 'i' } } ] }")
    List<Product> search(String query);

    @Query("{ $or: [ { 'name': { $regex: ?0, $options: 'i' } }, { 'description': { $regex: ?0, $options: 'i' } }, { 'category':  ?1 } ] }")
    List<Product> search(String query, String category);

    List<Product> findAllByCategoryOrderById(String category);

}
