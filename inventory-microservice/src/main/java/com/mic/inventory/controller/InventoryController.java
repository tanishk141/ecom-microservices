package com.mic.inventory.controller;

import java.util.List;

import com.mic.inventory.dto.InventoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mic.inventory.dto.InventoryResponse;
import com.mic.inventory.model.Inventory;
import com.mic.inventory.repository.InventoryRepository;
import com.mic.inventory.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController  {
	
	@Autowired
	private InventoryService inventoryService ;

	@Autowired
	private InventoryRepository inventoryRepository;
	
	@GetMapping(value = "/skuCode")
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skuCode) {
		return inventoryService.isInStock(skuCode);
	}

	@PostMapping(value = "/updateProduct")
	public ResponseEntity<String>  updateProduct(@RequestBody List<InventoryRequest> inventoryRequestList ){
		return inventoryService.updateInventory(inventoryRequestList);
	}

	
}
