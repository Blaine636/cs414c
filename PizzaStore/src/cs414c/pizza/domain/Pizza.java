package cs414c.pizza.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cs414c.pizza.util.Enum.*;

public class Pizza extends Item {
	
	List<Topping> toppings;
	PizzaSize size;

	public Pizza(String name, double basePrice, String description) {
		super(name, basePrice, description);
		toppings = new ArrayList<Topping>();
	}
	
	public Pizza(UUID id, String name, double basePrice, String description) {
		super(id, name, basePrice, description);
		toppings = new ArrayList<Topping>();
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
	
	public Pizza setSize(PizzaSize size){
		this.size = size;
		return this;
	}
	
	public PizzaSize getSize(){
		return size;
	}
	
	public int getNumToppings() {
		return toppings.size();
	}
	
	public List<Topping> getToppings(){
		return toppings;
	}

	public Item setToppings(List<Topping> toppingList) {
		this.toppings = toppingList;
		return this;
	}
	
	public String toString(){
		return size+" "+super.name+". "+toppings.toString();
	}

}
