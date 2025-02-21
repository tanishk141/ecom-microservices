package com.mic.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import com.mic.order.Repository.OrderRepository;
import com.mic.order.controller.OrderController;
import com.mic.order.dto.InventoryResponse;
import com.mic.order.dto.OrderLineItemsDto;
import com.mic.order.dto.OrderRequest;
import com.mic.order.model.Order;
import com.mic.order.model.OrderLineItems;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository ;

	@Autowired
	private KafkaTemplate kafkaTemplate ;
	
	@Autowired
	private  RestClient.Builder restClientBuilder;
 	
	private static final Logger LOGGER =LoggerFactory.getLogger(OrderController.class);
	
	public void placeOrder(OrderRequest orderRequest){
		
	Order order = new Order(); 
	order.setOrderNo(UUID.randomUUID().toString());
	
	List<OrderLineItems> lineItemsList = new ArrayList<>();
	
	List<OrderLineItemsDto> orderLineItemsDto = orderRequest.getOrderLineItemsDto();
	
	for (OrderLineItemsDto orderLineItemsDto2 : orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setId(orderLineItemsDto2.getId());
		orderLineItems.setSkuCode(orderLineItemsDto2.getSkuCode());
		orderLineItems.setPrice(orderLineItemsDto2.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto2.getQuantity());
		
		lineItemsList.add(orderLineItems);
	}
	
	order.setOrderLineItemsList(lineItemsList);
	
	List<String> skuCodes = new ArrayList<>();
	
	List<OrderLineItems> orderLineItemsList = order.getOrderLineItemsList();
	
	for(OrderLineItems orderLineItems:orderLineItemsList) {
		skuCodes.add(orderLineItems.getSkuCode());
	}
	
	
	// call Inventory Service before placing the order , if available then only place the order
    // sync communication using RestClient

	InventoryResponse [] inventoryResponseArray = restClientBuilder.build()
															.get()
                                                            .uri("http://inventory-microservice/api/inventory/skuCode",
					                                                      uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build()) 
  														   	.retrieve()
  															.body(InventoryResponse [].class);
    
    boolean allProductsInStock = true;

    for (InventoryResponse inventoryResponse : inventoryResponseArray) {
        if (!inventoryResponse.isInStock()) {
            allProductsInStock = false;
            break;
        }
    }
    
	if(allProductsInStock) {
		orderRepository.save(order);
	    kafkaTemplate.send("notificationTopic", order.getOrderNo());
		LOGGER.info("Order placed");

	}
	else {
		throw new IllegalArgumentException("Product is not in Stock, please try again later");
	}
	
	}
	

}
