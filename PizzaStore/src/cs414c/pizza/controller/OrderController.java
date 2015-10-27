package cs414c.pizza.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

import cs414c.pizza.domain.Item;
import cs414c.pizza.domain.Order;
import cs414c.pizza.domain.Pizza;
import cs414c.pizza.domain.SideItem;
import cs414c.pizza.domain.Topping;
import cs414c.pizza.ui.ItemEntry;
import cs414c.pizza.ui.OrderItemEntry;
import cs414c.pizza.ui.SizeEntry;
import cs414c.pizza.util.Enum;

public class OrderController {
	private Queue<Order> orderQueue;
	private Map<Integer,Order> orderMap;
	private int currentOrderNumber;
	
	public OrderController() {
		orderQueue = new LinkedBlockingQueue<Order>();
		orderMap = new HashMap<Integer,Order>();
		currentOrderNumber = 0;
	}
	
	//add item to a created order, returns a unique identifer of the customized item
	public UUID addItemToOrder(int orderId, ItemEntry item) {
		Order currentOrder = orderMap.get(orderId);
		return currentOrder.addItem(new SideItem(item.getName(), item.getPrice(), ""));
	}
	
	//add pizza to order including name, toppings, size
	public UUID addPizzaToOrder(int orderId, ItemEntry pizza,  SizeEntry size) {
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
		Order order = orderMap.get(orderId);
		return order.contains(orderedItemUUID);
	}

	//returns an English description of an ordered item
	public String getItemDescription(int orderId, UUID orderedItemId) {
		//TODO: implement
		return null;
	}

	//precondition: order is created
	//finalizes the items in an order so it can be passed to chef ui
	public void placeOrder(int orderId) {
		orderMap.get(orderId).setStatus(Enum.OrderStatus.PLACED);
	}

	//returns current status of order
	public Enum.OrderStatus getStatus(int orderId) {
		return orderMap.get(orderId).getStatus();
	}

	//creates a new order in the system and returns an identifier to access the order
	public int createOrder(String customerName) {
		Order newOrder = new Order(customerName);
		int newOrderId = currentOrderNumber++;
		orderMap.put(newOrderId, newOrder);
		return newOrderId;
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
	
	public OrderItemEntry getOrderItem(int orderId, UUID orderItemId) {
		Order order = orderMap.get(orderId);
		Item i = order.getItem(orderItemId);
		if(i instanceof Pizza) {
			return new OrderItemEntry(i.getName(), ((Pizza)i).getNumToppings(), i.getCost(), orderItemId);
		}
		else {
			return new OrderItemEntry(i.getName(), 0, i.getCost(), orderItemId);
		}
	}

	public double getOrderTotal(int orderId) {
		Order order = orderMap.get(orderId);
		return order.getTotal();
	}

	public boolean completeOrder(int orderId) {
		Order order = orderMap.get(orderId);
		if(order.getStatus().equals(Enum.OrderStatus.PLACED)) {
			order.setStatus(Enum.OrderStatus.COMPLETED);
			return true;
		}
		else return false;
	}

	public boolean removeItemFromOrder(int orderId, UUID itemId) {
		Order order = orderMap.get(orderId);
		if(order.contains(itemId)) {
			order.removeItem(itemId);
			return true;
		}
		else return false;
	}
}
