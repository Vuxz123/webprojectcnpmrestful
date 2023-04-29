package com.ethnicthv.webprojectcnpmrestful.data.repository;

import com.ethnicthv.webprojectcnpmrestful.data.entity.io.SellerProcutListIO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerDataRepository extends MongoRepository<SellerProcutListIO, String> {

}
