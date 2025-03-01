package com.mic.order;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderMicroserviceApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_USER", dotenv.get("DB_USER"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("KAFKA_PORT", dotenv.get("KAFKA_PORT"));
		System.setProperty("KAFKA_HOST", dotenv.get("KAFKA_HOST"));
		System.setProperty("EUREKA_PORT", dotenv.get("EUREKA_PORT"));
		System.setProperty("EUREKA_HOST", dotenv.get("EUREKA_HOST"));
		System.setProperty("DB_HOST", dotenv.get("DB_HOST"));
		System.setProperty("DB_PORT", dotenv.get("DB_PORT"));
		SpringApplication.run(OrderMicroserviceApplication.class, args);
	}

}
