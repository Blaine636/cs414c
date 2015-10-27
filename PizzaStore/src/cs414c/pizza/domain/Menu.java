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
		List<Item> pizzaList = new ArrayList<Item>();
		for(Item i : menuItems.values()) {
			if(i instanceof Pizza) pizzaList.add(i);
		}
		return pizzaList;
	}
	
	public List<Item> getSides() {
		List<Item> sideList = new ArrayList<Item>();
		for(Item i : menuItems.values()) {
			if(i instanceof SideItem) sideList.add(i);
		}
		return sideList;
	}
	
	public List<Item> getToppings() {
		List<Item> toppingList = new ArrayList<Item>();
		for(Item i : menuItems.values()) {
			if(i instanceof Topping) toppingList.add(i);
		}
		return toppingList;
	}
	
	public void setSpecial(Item item, int discountPercent) {
		currentSpecials.add(new Special(item,discountPercent));
	}
	
	public void removeSpecial(Item item, int discountPercent) {
		
	}

}
