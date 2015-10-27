package cs414c.pizza.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cs414c.pizza.domain.Item;
import cs414c.pizza.ui.ItemEntry;
import cs414c.pizza.ui.PizzaEntry;
import cs414c.pizza.ui.SizeEntry;

public class MenuController {
	
	

	public MenuController() {
		
	}

	public int menuSize() {
		// TODO implement
		return 0;
	}

	public int addItemToMenu(String name, double basePrice, String description) {
		// TODO implement
		return 0;
	}

	public boolean containsItem(int itemId) {
		// TODO implement
		return false;
	}

	public boolean removeItem(int itemId) {
		// TODO implement
		return false;
	}

	public String getItemSummary(int itemId) {
		// TODO implement
		return null;
	}

	public boolean modifyItemName(int itemId, String string) {
		// TODO implement
		return false;
	}
	
	public boolean modifyItemPrice(int itemId, double price) {
		// TODO implement
		return false;
	}
	
	public boolean modifyItemDescription(int itemId, String string) {
		// TODO implement
		return false;
	}
	
	public String getItemName(int itemId) {
		// TODO implement
		switch(itemId) {
		case 1: return "deep dish";
		case 2: return "giraffe";
		case 3: return "pepperoni";
		case 4: return "italian sausage";
		}
		return null;
	}
	
	public double getItemPrice(int itemId) {
		// TODO implement
		switch(itemId) {
		case 1: return 3.99;
		case 2: return 500.01;
		case 3: return 0.99;
		case 4: return 2.00;
		}
		return 0;
	}
	
	public String getItemDescription(int itemId) {
		// TODO implement
		return null;
	}

	public List<ItemEntry> getPizzas() {
		List<ItemEntry> test = new ArrayList<ItemEntry>();
		
		test.add(new PizzaEntry("-Build It-", 5.00, 99, new ArrayList<Integer>()));
		
		List<Integer> giraffeList = new ArrayList<Integer>();
		giraffeList.add(432);
		test.add(new PizzaEntry("Super Giraffe Deluxe", 19.99, 123,giraffeList));
		
		List<Integer> jorshList = new ArrayList<Integer>();
		jorshList.add(469);
		test.add(new PizzaEntry("Jorshington Supreme", 69.95, 1,jorshList));
		
		// TODO implement
		return test;
	}
	
	public List<ItemEntry> getSides() {
		List<ItemEntry> test = new ArrayList<ItemEntry>();
		test.add(new ItemEntry("Ranch Dressing", 0.25, 55));
		test.add(new ItemEntry("Liter of Cola", 2.00, 54));
		// TODO implement
		return test;
	}
	
	public List<ItemEntry> getToppings(){
		List<ItemEntry> test = new ArrayList<ItemEntry>();
		test.add(new ItemEntry("pepperoni", 0.99, 432));
		test.add(new ItemEntry("sausage", 0.95, 469));
		// TODO implement
		return test;
	}
	
	public List<SizeEntry> getSizes(){
		List<SizeEntry> test = new ArrayList<SizeEntry>();
		test.add(new SizeEntry("Large", 3.00));
		test.add(new SizeEntry("Medium", 2.00));
		test.add(new SizeEntry("Small", 1.00));
		// TODO implement
		return test;
	}
	
	public ItemEntry getItem(int itemId) {
		switch(itemId) {
		case 432: return new ItemEntry("pepperoni", 0.99, 432);
		case 469: return new ItemEntry("sausage", 0.95, 469);
		}
		return null;
	}

}
