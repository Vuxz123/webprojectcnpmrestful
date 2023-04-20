package com.ethnicthv.webprojectcnpmrestful.data.repository;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, Long> {
    @Query("{'productId': ?0}")
    List<Review> findByProductId(long productId);

    @Query("{'updated': false}")
    List<Review> updatedProduct();
}
