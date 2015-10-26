package cs414c.pizza.domain;

public class Topping {
	private String name;
	private double price;
	
	public Topping(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public double getCost() {
		return price;
	}
}