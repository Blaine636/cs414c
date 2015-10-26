package cs414c.pizza.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

import cs414c.pizza.domain.Order;
import cs414c.pizza.domain.Pizza;
import cs414c.pizza.domain.SideItem;
import cs414c.pizza.domain.Topping;
import cs414c.pizza.ui.ItemEntry;
import cs414c.pizza.ui.SizeEntry;
import cs414c.pizza.util.OrderStatus;

public class OrderController {
	private Queue<Order> orderQueue;
	private Map<Integer,Order> orderMap;
	
	public OrderController() {
		orderQueue = new LinkedBlockingQueue<Order>();
		orderMap = new HashMap<Integer,Order>();
	}
	
	//add item to a created order, returns a unique identifer of the customized item
	public UUID addItemToOrder(int orderId, ItemEntry item) {
		Order currentOrder = orderMap.get(orderId);
		return currentOrder.addItem(new SideItem(item.getName(), item.getPrice(), ""));
	}
	
	public UUID addPizzaToOrder(int orderId, ItemEntry pizza, List<ItemEntry> toppings, SizeEntry size) {
		Order currentOrder = orderMap.get(orderId);
		return currentOrder.addItem(new Pizza(pizza.getName(), pizza.getPrice(), ""));
	}

	//returns the size of a given order
	public int orderSize(int orderId) {
		//TODO: implement
		return 0;
	}

	//returns whether or not a given order contains a certain customized item 
	public boolean contains(int orderId, UUID orderedItemUUID) {
		//TODO: implement
		return false;
	}

	//returns an English description of an ordered item
	public String getItemDescription(int orderId, UUID orderedItemId) {
		//TODO: implement
		return null;
	}

	//modifies the status of an existing order
	public void setStatus(int orderId, OrderStatus newStatus) {
		//TODO: implement
	}

	//precondition: order is created
	//finalizes the items in an order so it can be passed to chef ui
	public void placeOrder(int orderId) {
		// TODO: implement

	}

	//returns current status of order
	public OrderStatus getStatus() {
		// TODO implement
		return null;
	}

	//creates a new order in the system and returns an identifier to access the order
	public int createOrder(String customerName) {
		// TODO implement
		return 0;
	}

	//returns the identifiers of all placed orders
	public List<Integer> getPlacedOrders() {
		// TODO implement
		return null;
	}

	public String getOrderDescription(int orderId) {
		// TODO implement
		return null;
	}

	public double getOrderTotal() {
		// TODO implement
		//should get the total from each item in Order and also apply specials from Menu
		return 0;
	}

	public boolean completeOrder(int orderId) {
		// TODO implement
		return false;
	}

	public boolean removeItemFromOrder(int orderId, UUID item1) {
		// TODO implement
		return false;
	}
}
