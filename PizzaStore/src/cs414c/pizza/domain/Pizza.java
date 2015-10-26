package cs414c.pizza.domain;

import java.util.List;

import cs414c.pizza.util.PizzaSize;

public class Pizza extends Item {
	
	List<Topping> toppings;
	PizzaSize size;

	public Pizza(String name, double basePrice, String description) {
		super(name, basePrice, description);
		// TODO Auto-generated constructor stub
	}
	
	public Pizza addToppings(List<Topping> toppings) {
		this.toppings.addAll(toppings);
		return this;
	}

	@Override
	public double getCost() {
		double total = basePrice;
		if(size != null) {
			total += size.getCost();
		}
		for(Topping t: toppings) {
			total += t.getCost();
		}
		return total;
	}

}
