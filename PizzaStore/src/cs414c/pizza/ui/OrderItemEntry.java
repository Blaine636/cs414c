package cs414c.pizza.ui;

import java.util.UUID;

public class OrderItemEntry {
	
	private String name;
	private int numToppings;
	private double totalPrice;
	private UUID uniqueId;
	
	public OrderItemEntry(String name, int numToppings, double totalPrice, UUID uniqueId) {
		super();
		this.name = name;
		this.numToppings = numToppings;
		this.totalPrice = totalPrice;
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
	
	public String toString(){
		return name + " Toppings: " + numToppings + " $" + totalPrice;
	}
	
	

}
