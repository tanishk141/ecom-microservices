package com.mic.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class NotificationMicroserviceApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationMicroserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NotificationMicroserviceApplication.class, args);
	}

	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
		
		LOGGER.info("Received Notification for Order - {}",orderPlacedEvent.getOrderNumber());
	}
}
