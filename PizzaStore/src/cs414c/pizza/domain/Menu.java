package cs414c.pizza.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Menu {
	
	Map<UUID,Item> menuItems;
	private List<Special> currentSpecials;
	
	public Menu() {
		this.menuItems = new HashMap<UUID,Item>();
		this.currentSpecials = new ArrayList<Special>();
		
		// TODO connect to database to set up menu
	}
	
	public Map<UUID,Item> pullItemsFromDB(){
		//pull item map from db
		return null;
	}

	public int size() {
		return menuItems.size();
	}
	
	public UUID addItem(Item i) {
		//add item to db and list
		return null;
	}
	
	public void removeItem(UUID itemId) {
		menuItems.remove(itemId);
	}
	
	public List<Pizza> getPizzas() {
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		for(Item i : menuItems.values()) {
			if(i instanceof Pizza) pizzaList.add((Pizza)i);
		}
		return pizzaList;
	}
	
	public List<SideItem> getSides() {
		List<SideItem> sideList = new ArrayList<SideItem>();
		for(Item i : menuItems.values()) {
			if(i instanceof SideItem) sideList.add((SideItem)i);
		}
		return sideList;
	}
	
	public List<Topping> getToppings() {
		List<Topping> toppingList = new ArrayList<Topping>();
		for(Item i : menuItems.values()) {
			if(i instanceof Topping) toppingList.add((Topping)i);
		}
		return toppingList;
	}
	
	public void setSpecial(Item item, int discountPercent) {
		currentSpecials.add(new Special(item,discountPercent));
	}
	
	public void removeSpecial(Item item, int discountPercent) {
		
	}
	
	public Item getItem(UUID itemId) {
		return menuItems.get(itemId);
	}

	public boolean contains(UUID itemId) {
		return menuItems.containsKey(itemId);
	}

	public void setItemName(UUID itemId, String name) {
		menuItems.get(itemId).setName(name);;
	}

	public void setItemPrice(UUID itemId, double price) {
		menuItems.get(itemId).setBasePrice(price);
	}

}
