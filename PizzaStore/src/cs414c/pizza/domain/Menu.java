package cs414c.pizza.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import cs414c.pizza.dao.MenuDAO;

public class Menu {
	
	Map<UUID,Item> menuItems;
	private List<Special> currentSpecials;
	private MenuDAO dao;
	
	public Menu(MenuDAO menuDAO) {
		this.menuItems = new HashMap<UUID,Item>();
		this.currentSpecials = new ArrayList<Special>();
		dao = menuDAO;
		pullItemsFromDB();
	}
	
	public void pullItemsFromDB(){
		this.menuItems = dao.pullAllItems();
	}

	public int size() {
		return menuItems.size();
	}
	
	public void addItem(Pizza p) {
		dao.addItemToDB(p);
		menuItems.put(p.getItemId(), p);
	}
	public void addItem(SideItem si) {
		dao.addItemToDB(si);
		menuItems.put(si.getItemId(), si);
	}
	public void addItem(Topping t) {
		dao.addItemToDB(t);
		menuItems.put(t.getItemId(), t);
	}
	
	public void removePizza(UUID itemId) {
		menuItems.remove(itemId);
		dao.removePizza(itemId);
	}
	
	public void removeSide(UUID itemId) {
		menuItems.remove(itemId);
		dao.removeSide(itemId);
	}
	
	public void removeTopping(UUID itemId) {
		menuItems.remove(itemId);
		dao.removeTopping(itemId);
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
