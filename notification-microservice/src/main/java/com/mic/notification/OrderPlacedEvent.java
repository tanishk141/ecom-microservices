package com.mic.notification;

public class OrderPlacedEvent {

	private String orderNumber ;
	
	public OrderPlacedEvent(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
	public String getOrderNumber() {
		return orderNumber;
	}

}
