package cs414c.pizza.controller;

import java.util.List;

import cs414c.pizza.domain.Item;
import cs414c.pizza.domain.Topping;
import cs414c.pizza.util.OrderStatus;

public class MenuController {
	
	public boolean addItemToOrder(int orderId, int itemId, List<Topping> toppingList) {
		//TODO: implement
		return false;
	}
	
	public int orderSize() {
		//TODO: implement
		return 0;
	}
	
	public boolean contains(int itemId) {
		//TODO: implement
		return false;
	}
	
	public Item getItem(int itemId) {
		//TODO: implement
		return null;
	}
	
	public void setStatus(int orderId, OrderStatus newStatus) {
		//TODO: implement
	}

	public void placeOrder(int i) {
		// TODO: implement
		
	}

	public OrderStatus getStatus() {
		// TODO implement
		return null;
	}

	public int createOrder() {
		// TODO implement
		return 0;
	}
	
	public List<Integer> getPlacedOrders() {
		// TODO implement
		return null;
	}

}
