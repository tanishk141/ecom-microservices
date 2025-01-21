package com.mic.product.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mic.product.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
