package com.mic.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean
	@LoadBalanced
	public WebClient.Builder webClientBuilder() {
		
		return WebClient.builder();
	}
	
	@Bean
	public RestClient restClient() {
		return RestClient.create();
	}
	
	@Bean
	public RestClient.Builder restClientBuilder() {
		return RestClient.builder();
	}
}
