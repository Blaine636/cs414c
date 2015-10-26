package cs414c.pizza.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cs414c.pizza.domain.Item;
import cs414c.pizza.domain.Topping;
import cs414c.pizza.util.OrderStatus;

public class MenuController {

	public void initializeMenu() {
		// TODO implement
		
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

	public List<Integer> getPizzas() {
		List<Integer> test = new ArrayList<Integer>();
		test.add(1);
		test.add(2);
		return test;
	}
	
	public List<Integer> getSides() {
		ArrayList<String> test = new ArrayList<String>();
		return null;
	}
	
	public List<Integer> getToppings(){
		List<Integer> test = new ArrayList<Integer>();
		test.add(3);
		test.add(4);
		// TODO implement
		return test;
	}

}
