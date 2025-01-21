package com.mic.order.event;


public class OrderPlacedEvent {
	
	private String orderNumber;

	public OrderPlacedEvent(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	

}
