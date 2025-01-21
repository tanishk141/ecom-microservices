package com.mic.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mic.inventory.dto.InventoryResponse;
import com.mic.inventory.model.Inventory;
import com.mic.inventory.repository.InventoryRepository;
import com.mic.inventory.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController implements CommandLineRunner {
	
	@Autowired
	private InventoryService inventoryService ;

	@Autowired
	private InventoryRepository inventoryRepository;
	
	@GetMapping(value = "/skuCode")
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skuCode) {
		return inventoryService.isInStock(skuCode);
	}
	

	@Override
	public void run(String... args) throws Exception {
		Inventory inventory = new Inventory();
		inventory.setSkuCode("Lava Agni 15");
		inventory.setQuantity(12);
		
		Inventory inventory2 = new Inventory();
		inventory2.setSkuCode("Nokia 3110");
		inventory2.setQuantity(13);
		
		inventoryRepository.save(inventory);
		inventoryRepository.save(inventory2);
		
	}
	
}
