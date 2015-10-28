package cs414c.pizza.domain;

import java.util.UUID;

public abstract class Item {
	
	protected String name;
	protected double basePrice;
	protected String description;
	protected UUID itemId;
	
	public Item(String name, double basePrice, String description, UUID id) {
		this.name = name;
		this.basePrice = basePrice;
		this.description = description;
		this.itemId = id;
	}
	
	public abstract double getCost();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public UUID getItemId() {
		return itemId;
	}
	

}
