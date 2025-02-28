package com.discovery;


import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {

	
	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("EUREKA_HOST", dotenv.get("EUREKA_HOST"));
		System.setProperty("EUREKA_PORT", dotenv.get("EUREKA_PORT"));
		SpringApplication.run(DiscoveryServerApplication.class, args);
	}
}
