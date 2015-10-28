package cs414c.pizza.domain;

import java.util.UUID;

public class Topping extends Item{
	private String name;
	private double price;
	
	public Topping(String name, double price, UUID itemId) {
		super(name,price,null,itemId);
		this.name = name;
		this.price = price;
	}

	public double getCost() {
		return price;
	}
}