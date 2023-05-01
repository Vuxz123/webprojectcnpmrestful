package com.ethnicthv.webprojectcnpmrestful;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Product;
import com.ethnicthv.webprojectcnpmrestful.data.entity.Review;
import com.ethnicthv.webprojectcnpmrestful.data.service.ProductService;
import com.ethnicthv.webprojectcnpmrestful.data.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
public class UndateTimer {
    private final Logger logger = LoggerFactory.getLogger(UndateTimer.class);
    private final ReviewService reviewService;
    private final ProductService productService;

    @Autowired
    public UndateTimer(ReviewService reviewService, ProductService productService) {
        this.reviewService = reviewService;
        this.productService = productService;
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void updateRating() {
        logger.info("Start updating rating!");
        List<Review> reviews = reviewService.getUpdatedReviews();
        logger.info(reviews.toString());
        if(reviews == null || reviews.isEmpty()) {
            logger.info("Nothing to update!");
            return;
        }
        reviews.stream().parallel()
                .forEach(review -> {
                    review.setUpdated(true);
                    var productId = review.getProductId();
                    Product product;
                    try {
                        product = productService.getProduct(productId);
                    } catch (RuntimeException ignored) {
                        return;
                    }
                    var add = review.getRating() + product.getRating();
                    var _new = add/2;
                    if(_new == review.getRating()) return;
                    product.setRating(_new);
                    productService.saveProduct(product);
                });
    }
}
