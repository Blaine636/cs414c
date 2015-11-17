package cs414c.pizza.ui;

import java.io.Serializable;
import java.util.UUID;

public abstract class OrderItemEntry implements Serializable {
	
	protected String name;
	protected double price;
	protected UUID uniqueId;
	
	public OrderItemEntry(String name, double price, UUID uniqueId) {
		this.name = name;
		this.price = price;
		this.uniqueId = uniqueId;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public UUID getUUID(){
		return uniqueId;
	}
	
}
