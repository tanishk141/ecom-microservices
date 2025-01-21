package com.mic.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mic.inventory.dto.InventoryResponse;
import com.mic.inventory.model.Inventory;
import com.mic.inventory.repository.InventoryRepository;


@Service
public class InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepository ;
	
	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		
	List<InventoryResponse> inventoryResponses = new ArrayList<>() ;
		
	List<Inventory> bySkuCodeIn = inventoryRepository.findBySkuCodeIn(skuCode);
		
	for(Inventory inventory :bySkuCodeIn) {
			InventoryResponse inventoryResponse = new InventoryResponse();
			inventoryResponse.setSkuCode(inventory.getSkuCode());
			inventoryResponse.setInStock(inventory.getQuantity() > 0);
			
			inventoryResponses.add(inventoryResponse);
		}
		
	return inventoryResponses;	
	}
	

}
