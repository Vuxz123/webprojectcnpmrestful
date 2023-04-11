package com.ethnicthv.webprojectcnpmrestful.data.repository;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProductRepository extends MongoRepository<Product, Long> {

    @Query("{_id: ?0}")
    Product findOneById(long id);
}
