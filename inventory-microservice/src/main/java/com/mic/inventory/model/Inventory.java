package com.mic.inventory.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	private String skuCode;

	private Integer quantity;

	public Long getId() {
		return id;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", skuCode=" + skuCode + ", quantity=" + quantity + "]";
	}

}
