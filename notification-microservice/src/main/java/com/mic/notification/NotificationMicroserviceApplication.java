package com.mic.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
public class NotificationMicroserviceApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationMicroserviceApplication.class);

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("EUREKA_HOST", dotenv.get("EUREKA_HOST"));
		System.setProperty("EUREKA_PORT", dotenv.get("EUREKA_PORT"));
		System.setProperty("KAFKA_HOST", dotenv.get("KAFKA_HOST"));
		System.setProperty("KAFKA_PORT", dotenv.get("KAFKA_PORT"));
		SpringApplication.run(NotificationMicroserviceApplication.class, args);
	}

	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
		
		LOGGER.info("Received Notification for Order - {}",orderPlacedEvent.getOrderNumber());
	}
}
