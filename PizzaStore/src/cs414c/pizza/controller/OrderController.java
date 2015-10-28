package cs414c.pizza.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
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
import cs414c.pizza.ui.OrderPizzaEntry;
import cs414c.pizza.ui.OrderSideEntry;
import cs414c.pizza.ui.PizzaEntry;
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
	public UUID addPizzaToOrder(int orderId, PizzaEntry pizza, List<ItemEntry> toppings,  SizeEntry size) {
		Order currentOrder = orderMap.get(orderId);
		/*		List<Integer> toppings = pizza.getToppingIds();
		List<Topping> toppingList = new ArrayList<>();
		for(int i : toppings){
			toppingList.add();
		}*/
		List<Topping> toppingList = new ArrayList<Topping>();
		for(ItemEntry t : toppings) {
			toppingList.add(new Topping(t.getName(),t.getPrice()));
		}
		
		Enum.PizzaSize ps;
		
		switch(size.getName()) {
		case "Large": 
			ps = Enum.PizzaSize.LARGE;
			break;
		case "Medium": 
			ps = Enum.PizzaSize.MEDIUM;
			break;
		case "Small": 
			ps = Enum.PizzaSize.SMALL;
			break;
		default:
			ps = Enum.PizzaSize.SMALL;

		}
		return currentOrder.addItem(new Pizza(pizza.getName(), pizza.getPrice(), "").addToppings(toppingList).setSize(ps));
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
