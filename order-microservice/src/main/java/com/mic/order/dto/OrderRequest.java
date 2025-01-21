package com.mic.order.dto;

import java.util.List;


public class OrderRequest {
	
	private List<OrderLineItemsDto> orderLineItemsDto ;

	public List<OrderLineItemsDto> getOrderLineItemsDto() {
		return orderLineItemsDto;
	}

	public void setOrderLineItemsDto(List<OrderLineItemsDto> orderLineItemsDto) {
		this.orderLineItemsDto = orderLineItemsDto;
	}
	

}
