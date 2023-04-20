package com.ethnicthv.webprojectcnpmrestful.data.service;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Review;
import com.ethnicthv.webprojectcnpmrestful.data.repository.ReviewRepository;
import com.ethnicthv.webprojectcnpmrestful.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviews(long productId) {
        return reviewRepository.findByProductId(productId);
    }

    public void saveReview(Review review) {
        if(review.getId() == 0) review.setId(IDUtils.getID());
        reviewRepository.save(review);
    }

    public List<Review> getUpdatedReviews() {
        return reviewRepository.updatedProduct();
    }
}
