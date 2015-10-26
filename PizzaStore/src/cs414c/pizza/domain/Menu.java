package cs414c.pizza.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Menu {
	
	private Set<Item> itemSet;
	private List<Special> currentSpecials;
	
	public Menu() {
		this.itemSet = new HashSet<Item>();
		this.currentSpecials = new ArrayList<Special>();
		
		// TODO connect to database to set up menu
	}

	public int size() {
		return itemSet.size();
	}
	
	public void addItem(Item i) {
		itemSet.add(i);
	}
	
	public void removeItem(Item i) {
		itemSet.remove(i);
	}
	
	public List<Item> getPizzas() {
		// TODO implement
		return null;
	}
	
	public List<Item> getSides() {
		// TODO implement
		return null;
	}
	
	public List<Item> getToppings() {
		// TODO implement
		return null;
	}
	
	public void setSpecial(Item item, int discountPercent) {
		currentSpecials.add(new Special(item,discountPercent));
	}
	
	public void removeSpecial(Item item, int discountPercent) {
		
	}

}
