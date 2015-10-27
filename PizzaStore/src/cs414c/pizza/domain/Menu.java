package cs414c.pizza.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Menu {
	
	Map<Integer,Item> menuItems;
	private List<Special> currentSpecials;
	private int currentId;
	
	public Menu() {
		this.menuItems = new HashMap<Integer,Item>();
		this.currentSpecials = new ArrayList<Special>();
		
		// TODO connect to database to set up menu
	}

	public int size() {
		return menuItems.size();
	}
	
	public void addItem(Item i) {
		menuItems.put(currentId++,i);
	}
	
	public void removeItem(Item i) {
		menuItems.remove(i);
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
