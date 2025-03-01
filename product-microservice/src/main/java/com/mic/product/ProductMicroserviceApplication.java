package com.mic.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductMicroserviceApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("MONGO_HOST", dotenv.get("MONGO_HOST"));
		System.setProperty("MONGO_PORT", dotenv.get("MONGO_PORT"));
		System.setProperty("EUREKA_HOST", dotenv.get("EUREKA_HOST"));
		System.setProperty("EUREKA_PORT", dotenv.get("EUREKA_PORT"));
		System.setProperty("ZIPKIN_HOST", dotenv.get("ZIPKIN_HOST"));
		System.setProperty("ZIPKIN_PORT", dotenv.get("ZIPKIN_PORT"));
		SpringApplication.run(ProductMicroserviceApplication.class, args);
	}

}
