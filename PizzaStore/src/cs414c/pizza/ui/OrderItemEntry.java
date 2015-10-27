package cs414c.pizza.ui;

import java.text.NumberFormat;
import java.util.UUID;

public class OrderItemEntry {
	
	private String name;
	private int numToppings;
	private double totalPrice;
	private UUID uniqueId;
	private SizeEntry size;
	
	public OrderItemEntry(String name, SizeEntry size, int numToppings, double totalPrice, UUID uniqueId) {
		super();
		this.name = name;
		this.numToppings = numToppings;
		this.totalPrice = totalPrice;
		this.size = size;
		this.uniqueId = uniqueId;
	}

	public String getName() {
		return name;
	}

	public int getNumToppings() {
		return numToppings;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
	public UUID getUUID(){
		return uniqueId;
	}
	
	public String toString(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return size.getName() + " " + numToppings + " Topping " + name + " " + formatter.format(totalPrice);
	}
	

}
