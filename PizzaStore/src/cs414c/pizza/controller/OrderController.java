package cs414c.pizza.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cs414c.pizza.dao.MenuDAO;
import cs414c.pizza.domain.DeliveryOrder;
import cs414c.pizza.domain.Item;
import cs414c.pizza.domain.Menu;
import cs414c.pizza.domain.Order;
import cs414c.pizza.domain.OrderedItem;
import cs414c.pizza.domain.Pizza;
import cs414c.pizza.domain.Topping;
import cs414c.pizza.ui.ItemEntry;
import cs414c.pizza.ui.OrderEntry;
import cs414c.pizza.ui.OrderItemEntry;
import cs414c.pizza.ui.OrderPizzaEntry;
import cs414c.pizza.ui.OrderSideEntry;
import cs414c.pizza.ui.PizzaEntry;
import cs414c.pizza.ui.SizeEntry;
import cs414c.pizza.util.Enum.*;

public class OrderController implements OrderControllerInterface {
	private Map<Integer,Order> orderMap;
	private int currentOrderNumber;
	private Menu menu;

	public OrderController(MenuDAO menuDAO) {
		orderMap = new HashMap<Integer,Order>();
		currentOrderNumber = 0;
		this.menu = new Menu(menuDAO);
	}

	//add item to a created order, returns a unique identifer of the customized item
	@Override
	public UUID addItemToOrder(int orderId, ItemEntry item) {
		Order currentOrder = orderMap.get(orderId);
		Item orderItem = menu.getItem(item.getItemId());
		return currentOrder.addItem(orderItem);
	}

	//add pizza to order including name, toppings, size
	@Override
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
	@Override
	public int orderSize(int orderId) {
		return orderMap.get(orderId).size();
	}

	//returns whether or not a given order contains a certain customized item 
	@Override
	public boolean contains(int orderId, UUID orderedItemUUID) {
		Order order = orderMap.get(orderId);
		return order.contains(orderedItemUUID);
	}

	//returns an English description of an ordered item
	@Override
	public String getItemDescription(int orderId, UUID orderedItemId) {
		//TODO: implement
		return null;
	}

	//precondition: order is created
	//finalizes the items in an order so it can be passed to chef ui
	@Override
	public void placeOrder(int orderId) {
		Order order = orderMap.get(orderId);
		order.setStatus(OrderStatus.PLACED);
	}

	//returns current status of order
	@Override
	public OrderStatus getStatus(int orderId) {
		return orderMap.get(orderId).getStatus();
	}

	//creates a new order in the system and returns an identifier to access the order
	@Override
	public int createOrder(String customerName) {
		int newOrderId = currentOrderNumber++;
		Order newOrder = new Order(customerName, newOrderId);
		
		orderMap.put(newOrderId, newOrder);
		return newOrderId;
	}
	
	@Override
	public int createDeliveryOrder(String customerName, String address, String phoneNumber) {
		int newOrderId = currentOrderNumber++;
		Order newOrder = new DeliveryOrder(customerName, newOrderId,address,phoneNumber);
		
		orderMap.put(newOrderId, newOrder);
		return newOrderId;
	}

	//returns the identifiers of all placed orders
	@Override
	public List<Integer> getPlacedOrders() {
		List<Integer> placedOrderIds = new ArrayList<Integer>();
		for(Order o : orderMap.values()) {
//			placedOrderIds.add(o.getOrderId());
			if(o.getStatus().equals(OrderStatus.PLACED)) {
				placedOrderIds.add(o.getOrderId());
			}
		}
		return placedOrderIds;
	}

	@Override
	public String getOrderDescription(int orderId) {
		// TODO implement
		return null;
	}

	@Override
	public OrderPizzaEntry getOrderPizza(int orderId, UUID orderItemId) {
		Order order = orderMap.get(orderId);
		OrderedItem i = order.getItem(orderItemId);
		SizeEntry se;
		switch(((Pizza)i.getItem()).getSize()) {
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
		List<ItemEntry> toppingEntries = new ArrayList<ItemEntry>();
		for(Item topping : ((Pizza)i.getItem()).getToppings()) {
			toppingEntries.add(new ItemEntry(topping.getName(),topping.getCost(),topping.getItemId(), topping.getDiscountPercent()));
		}
		return new OrderPizzaEntry(i.getItem().getName(), se, toppingEntries, i.getCost(), orderItemId);
	}
	
	@Override
	public OrderSideEntry getOrderSide(int orderId, UUID orderItemId){
		Order order = orderMap.get(orderId);
		OrderedItem i = order.getItem(orderItemId);
		return new OrderSideEntry(i.getItem().getName(), i.getCost(), orderItemId);
	}

	@Override
	public double getOrderTotal(int orderId) {
		Order order = orderMap.get(orderId);
		return order.calculateTotal();
	}
	
	@Override
	public String getOrderTotalString(int orderId){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		Order order = orderMap.get(orderId);
		return formatter.format(order.calculateTotal());
	}

	@Override
	public boolean completeOrder(int orderId) {
		Order order = orderMap.get(orderId);
		if(order.getStatus().equals(OrderStatus.PLACED)) {
			order.setStatus(OrderStatus.COMPLETED);
			return true;
		}
		else return false;
	}

	@Override
	public boolean removeItemFromOrder(int orderId, UUID orderItemId) {
		Order order = orderMap.get(orderId);
		if(order.contains(orderItemId)) {
			order.removeItem(orderItemId);
			return true;
		}
		else return false;
	}
	
	@Override
	public OrderEntry getFullOrder(int orderId){
		Order o = orderMap.get(orderId);
		List<OrderItemEntry> orderItems = new ArrayList<OrderItemEntry>();
		for(OrderedItem orderedItem : o.getAllItems()) {
			Item i = orderedItem.getItem();
			if(i instanceof Pizza) {
				orderItems.add(getOrderPizza(orderId, orderedItem.getOrderItemId()));
			}
			else orderItems.add(getOrderSide(orderId,orderedItem.getOrderItemId()));
		}
		return new OrderEntry(o.getName(),o.getOrderId(),orderItems);
	}
	
	public static void main(String[] args) {
		OrderController oc = new OrderController(new MenuDAO());
		System.out.println(oc.createOrder("1"));
		System.out.println(oc.createOrder("2"));
		System.out.println(oc.createOrder("3"));
		System.out.println(Arrays.toString(oc.orderMap.keySet().toArray()));
		
	}
}
