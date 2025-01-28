package com.mic.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mic.inventory.dto.InventoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Transactional
	public ResponseEntity<String> updateInventory(List<InventoryRequest> inventoryRequestList) {

		for (InventoryRequest request : inventoryRequestList) {
			Inventory inventory = inventoryRepository.findBySkuCode(request.getSkuCode())
					.orElse(null);

			if (inventory != null) {
				// SKU exists → Update quantity
				inventory.setQuantity(inventory.getQuantity() + request.getQuantity());
			} else {
				// SKU does not exist → Create new inventory record
				inventory = new Inventory();
				inventory.setSkuCode(request.getSkuCode());
				inventory.setQuantity(request.getQuantity());
			}

			// Save the inventory (either updated or new)
			inventoryRepository.save(inventory);
		}

	return new ResponseEntity<String>("Product updated successfully",HttpStatus.OK);

	}
}
