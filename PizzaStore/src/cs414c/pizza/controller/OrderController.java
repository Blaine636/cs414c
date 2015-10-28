package cs414c.pizza.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cs414c.pizza.domain.Item;
import cs414c.pizza.domain.Menu;
import cs414c.pizza.domain.Order;
import cs414c.pizza.domain.Pizza;
import cs414c.pizza.domain.Topping;
import cs414c.pizza.ui.ItemEntry;
import cs414c.pizza.ui.OrderPizzaEntry;
import cs414c.pizza.ui.OrderSideEntry;
import cs414c.pizza.ui.PizzaEntry;
import cs414c.pizza.ui.SizeEntry;
import cs414c.pizza.util.Enum.*;

public class OrderController {
	private Map<Integer,Order> orderMap;
	private int currentOrderNumber;
	private Menu menu;

	public OrderController() {
		orderMap = new HashMap<Integer,Order>();
		currentOrderNumber = 0;
		menu = new Menu();
	}

	//add item to a created order, returns a unique identifer of the customized item
	public UUID addItemToOrder(int orderId, ItemEntry item) {
		Order currentOrder = orderMap.get(orderId);
		Item orderItem = menu.getItem(item.getItemId());
		return currentOrder.addItem(orderItem);
	}

	//add pizza to order including name, toppings, size
	public UUID addPizzaToOrder(int orderId, PizzaEntry pizza, List<ItemEntry> toppings,  SizeEntry size) {
		
		Order currentOrder = orderMap.get(orderId);
		List<Topping> toppingList = new ArrayList<Topping>();
		for(ItemEntry t : toppings) {
			System.out.println(t.getItemId());
			toppingList.add((Topping)menu.getItem(t.getItemId()));
		}
		
		PizzaSize ps;
		
		switch(size.getName()) {
		case "Large": 
			ps = PizzaSize.LARGE;
			break;
		case "Medium": 
			ps = PizzaSize.MEDIUM;
			break;
		case "Small": 
			ps = PizzaSize.SMALL;
			break;
		default:
			ps = PizzaSize.SMALL;

		}
		return currentOrder.addItem(((Pizza)menu.getItem(pizza.getItemId())).setSize(ps).setToppings(toppingList));
	}
		

	//returns the size of a given order
	public int orderSize(int orderId) {
		return orderMap.get(orderId).size();
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
		Order order = orderMap.get(orderId);
		order.setStatus(OrderStatus.PLACED);
	}

	//returns current status of order
	public OrderStatus getStatus(int orderId) {
		return orderMap.get(orderId).getStatus();
	}

	//creates a new order in the system and returns an identifier to access the order
	public int createOrder(String customerName) {
		int newOrderId = currentOrderNumber++;
		Order newOrder = new Order(customerName, newOrderId);
		
		orderMap.put(newOrderId, newOrder);
		return newOrderId;
	}

	//returns the identifiers of all placed orders
	public List<Integer> getPlacedOrders() {
		List<Integer> placedOrderIds = new ArrayList<Integer>();
		for(Order o : orderMap.values()) {
			if(o.getStatus().equals(OrderStatus.PLACED)) {
				placedOrderIds.add(o.getOrderId());
			}
		}
		return placedOrderIds;
	}

	public String getOrderDescription(int orderId) {
		// TODO implement
		return null;
	}

	public OrderPizzaEntry getOrderItem(int orderId, UUID orderItemId) {
		Order order = orderMap.get(orderId);
		Item i = order.getItem(orderItemId);
		SizeEntry se;
		switch(((Pizza)i).getSize()) {
		case LARGE: 
			se = new SizeEntry("Large", 3.00);
			break;
		case MEDIUM: 
			se = new SizeEntry("Medium", 2.00);
			break;
		case SMALL: 
			se = new SizeEntry("Small", 1.00);
			break;
		default:
			se = new SizeEntry("Small", 1.00);

		}
		if(i instanceof Pizza) {
			return new OrderPizzaEntry(i.getName(), se, ((Pizza)i).getNumToppings(), i.getCost(), orderItemId);
		}
		else {
			return new OrderPizzaEntry(i.getName(), se, 0, i.getCost(), orderItemId);
		}
	}
	
	public OrderSideEntry getOrderSide(int orderId, UUID orderItemId){
		Order order = orderMap.get(orderId);
		Item i = order.getItem(orderItemId);
		return new OrderSideEntry(i.getName(), i.getCost(), orderItemId);
	}

	public double getOrderTotal(int orderId) {
		Order order = orderMap.get(orderId);
		return order.getTotal();
	}
	
	public String getOrderTotalString(int orderId){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		Order order = orderMap.get(orderId);
		return formatter.format(order.getTotal());
	}

	public boolean completeOrder(int orderId) {
		Order order = orderMap.get(orderId);
		if(order.getStatus().equals(OrderStatus.PLACED)) {
			order.setStatus(OrderStatus.COMPLETED);
			return true;
		}
		else return false;
	}

	public boolean removeItemFromOrder(int orderId, UUID orderItemId) {
		Order order = orderMap.get(orderId);
		if(order.contains(orderItemId)) {
			order.removeItem(orderItemId);
			return true;
		}
		else return false;
	}
	
	public Order getOrder(int orderId){
		return orderMap.get(orderId);
	}
}
