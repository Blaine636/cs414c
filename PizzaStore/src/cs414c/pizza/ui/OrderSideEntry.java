package cs414c.pizza.ui;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.UUID;

public class OrderSideEntry implements Serializable {
	
	private String name;
	private double totalPrice;
	private UUID uniqueId;
	
	public OrderSideEntry(String name, double totalPrice, UUID uniqueId) {
		super();
		this.name = name;
		this.totalPrice = totalPrice;
		this.uniqueId = uniqueId;
	}

	public String getName() {
		return name;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
	public UUID getUUID(){
		return uniqueId;
	}
	
	public String toString(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return name + " " + formatter.format(totalPrice);
	}
	

}
