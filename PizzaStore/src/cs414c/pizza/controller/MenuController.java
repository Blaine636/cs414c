package cs414c.pizza.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cs414c.pizza.dao.MenuDAO;
import cs414c.pizza.domain.Item;
import cs414c.pizza.domain.Menu;
import cs414c.pizza.domain.Pizza;
import cs414c.pizza.domain.SideItem;
import cs414c.pizza.domain.Topping;
import cs414c.pizza.ui.ItemEntry;
import cs414c.pizza.ui.PizzaEntry;
import cs414c.pizza.ui.SizeEntry;
import cs414c.pizza.util.Enum.PizzaSize;

public class MenuController implements MenuControllerInterface {
	
	private Menu menu;

	public MenuController(MenuDAO dao) {
		menu = new Menu(dao);
	}

	public UUID addSideItemToMenu(String name, double basePrice, String description) {
		if(name.length()==0||name.length()>64||description.length()>64)return null;
		SideItem i = new SideItem(name,basePrice,description);
		menu.addItem(i);
		return i.getItemId();
	}
	
	public UUID addToppingToMenu(String name, double price) {
		if(name.length()==0||name.length()>64)return null;
		Topping t = new Topping(name,price);
		menu.addItem(t);
		return t.getItemId();
	}
	

	public UUID addPizzaToMenu(String name, double basePrice, String description, List<ItemEntry> toppings) {
		if(name.length()==0||name.length()>64||description.length()>64)return null;
		List<Topping> toppingList = new ArrayList<Topping>();
		for(ItemEntry topping: toppings) {
			toppingList.add((Topping)menu.getItem(topping.getItemId()));
		}
		Pizza pizza =  new Pizza(name,basePrice,description).addToppings(toppingList);
		menu.addItem(pizza);
		return pizza.getItemId();
	}

	public boolean containsItem(UUID itemId) {
		return menu.contains(itemId);
	}

	public boolean removePizza(UUID itemId) {
		if(menu.contains(itemId)){
			menu.removePizza(itemId);
			return true;
		}
		else return false;
	}
	public boolean removeSide(UUID itemId) {
		if(menu.contains(itemId)){
			menu.removeSide(itemId);
			return true;
		}
		else return false;
	}
	public boolean removeTopping(UUID itemId) {
		if(menu.contains(itemId)){
			menu.removeTopping(itemId);
			return true;
		}
		else return false;
	}

	public boolean modifyItemName(UUID itemId, String string) {
		if(string.length()==0||string.length()>64)return false;
		menu.setItemName(itemId,string);
		return true;
	}
	
	public boolean modifyItemDescription(UUID itemId, String string) {
		if(string.length()==0||string.length()>64)return false;
		menu.setItemDescription(itemId, string);
		return true;
	}
	
	public boolean modifyItemPrice(UUID itemId, double price) {
		menu.setItemPrice(itemId,price);
		return true;
	}
	
	public String getItemName(UUID itemId) {
		return menu.getItem(itemId).getName();
	}
	
	public double getItemPrice(UUID itemId) {
		return menu.getItem(itemId).getBasePrice();
	}
	
	public String getItemDescription(UUID itemId) {
		return menu.getItem(itemId).getDescription();
	}

	public List<PizzaEntry> getPizzas() {
		List<Pizza> pizzaList = menu.getPizzas();
		List<PizzaEntry> entryList = new ArrayList<PizzaEntry>();
		for(Pizza p : pizzaList) {
			List<ItemEntry> toppings = new ArrayList<ItemEntry>();
			for(Topping t : p.getToppings()) {
				toppings.add(new ItemEntry(t.getName(),t.getBasePrice(),t.getItemId()));
			}
			entryList.add(new PizzaEntry(p.getName(), p.getBasePrice(), p.getItemId(), toppings));
		}
		return entryList;
	}
	
	public List<ItemEntry> getSides() {
		List<SideItem> sideList = menu.getSides();
		List<ItemEntry> entryList = new ArrayList<ItemEntry>();
		for(SideItem s: sideList) {
			entryList.add(new ItemEntry(s.getName(),s.getBasePrice(),s.getItemId()));
		}
		return entryList;
	}
	
	public List<ItemEntry> getToppings(){
		List<Topping> toppingList = menu.getToppings();
		List<ItemEntry> entryList = new ArrayList<ItemEntry>();
		for(Topping t: toppingList) {
			entryList.add(new ItemEntry(t.getName(),t.getBasePrice(),t.getItemId()));
		}
		return entryList;
	}
	
	public List<SizeEntry> getSizes(){
		List<SizeEntry> sizeList = new ArrayList<SizeEntry>();
		for(PizzaSize size : PizzaSize.values()) {
			sizeList.add(new SizeEntry(size.getName(),size.getCost()));
		}
		return sizeList;
	}
	
	public ItemEntry getItem(UUID itemId) {
		Item i = menu.getItem(itemId);
		return new ItemEntry(i.getName(),i.getBasePrice(),i.getItemId());
	}

}
