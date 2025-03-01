package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("PRODUCT_SVC_PORT", dotenv.get("PRODUCT_SVC_PORT"));
		System.setProperty("PRODUCT_SVC_HOST", dotenv.get("PRODUCT_SVC_HOST"));
		System.setProperty("ORDER_SVC_PORT", dotenv.get("ORDER_SVC_PORT"));
		System.setProperty("ORDER_SVC_HOST", dotenv.get("ORDER_SVC_HOST"));
		System.setProperty("INVENTORY_SVC_PORT", dotenv.get("INVENTORY_SVC_PORT"));
		System.setProperty("INVENTORY_SVC_HOST", dotenv.get("INVENTORY_SVC_HOST"));
		System.setProperty("API_SVC_HOST", dotenv.get("API_SVC_HOST"));
		System.setProperty("API_SVC_PORT", dotenv.get("API_SVC_PORT"));
		System.setProperty("EUREKA_HOST", dotenv.get("EUREKA_HOST"));
		System.setProperty("EUREKA_PORT", dotenv.get("EUREKA_PORT"));
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
