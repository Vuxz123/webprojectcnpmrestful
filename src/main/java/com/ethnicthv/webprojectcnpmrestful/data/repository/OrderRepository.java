package com.ethnicthv.webprojectcnpmrestful.data.repository;

import com.ethnicthv.webprojectcnpmrestful.data.entity.io.OrderIO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderIO, Long> {
}
