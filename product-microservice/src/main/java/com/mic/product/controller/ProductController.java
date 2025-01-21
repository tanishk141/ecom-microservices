package com.mic.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mic.product.dto.GenericResponse;
import com.mic.product.dto.ProductRequest;
import com.mic.product.dto.ProductResponse;
import com.mic.product.service.ProductService;

@RestController
@RequestMapping(value={"/api/product"})
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("createProduct")
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<GenericResponse> createProduct(@RequestBody ProductRequest productRequest) {
		return productService.createProduct(productRequest);
	}
	
	@GetMapping("getAllProduct")
	public List<ProductResponse> getAllProduct(){
		return productService.getAllProduct();
		
	}
	
	
}
