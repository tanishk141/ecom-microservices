package com.mic.product.service;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mic.product.Repository.ProductRepository;
import com.mic.product.dto.GenericResponse;
import com.mic.product.dto.ProductRequest;
import com.mic.product.dto.ProductResponse;
import com.mic.product.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	public ResponseEntity<GenericResponse> createProduct(ProductRequest productRequest) {

		Product product = new Product();
		product.setName(productRequest.getName());
		product.setDescription(productRequest.getDescription());
		product.setPrice(productRequest.getPrice());

		productRepository.save(product);
		LOGGER.info("Product {} is Saved",product.getId());

		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setStatus("Successfull");
		genericResponse.setMessage("Product Created");
		return new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.CREATED);

	}
	
	public List<ProductResponse> getAllProduct(){
		
		List<ProductResponse> productResponsesList = new ArrayList<>();
		
		List<Product> products = productRepository.findAll();
		
	
		for (Product product : products) {
			ProductResponse productResponse = new ProductResponse();

			productResponse.setId(product.getId());
			productResponse.setName(product.getName());
			productResponse.setDescription(product.getDescription());
			productResponse.setPrice(product.getPrice());
			
			productResponsesList.add(productResponse);
		}
		
		return productResponsesList ; 
		
	}

}
