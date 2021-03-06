package cs414c.pizza.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cs414c.pizza.util.Enum.*;

public class Order {
	private Map<UUID,Item> items;
	private OrderStatus status;
	String orderName;
	int orderId;
	
	public Order(String orderName, int orderId) {
		super();
		this.orderName = orderName;
		this.items = new HashMap<UUID,Item>();
		this.status = OrderStatus.STARTED;
		this.orderId = orderId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public UUID addItem(Item i) {
		UUID itemId = UUID.randomUUID();
		items.put(itemId, i);
		return itemId;
	}
	
	public void removeItem(UUID id) {
		items.remove(id);
	}
	
	public double calculateTotal() {
		double total = 0;
		for(Item item: items.values()) {
			total += item.getCost();
		}
		return total;
	}
	
	public String toString(){
		return "Order " + this.orderId + ": " + this.orderName + ", " + items.size() + " items";
	}

	public boolean contains(UUID orderedItemUUID) {
		return items.containsKey(orderedItemUUID);
	}
	
	public Item getItem(UUID itemId) {
		return items.get(itemId);
	}

	public double getTotal() {
		double total = 0;
		for(Item i : items.values()) {
			total += i.getCost();
		}
		return total;
	}

	public int size() {
		return items.size();
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public String getName(){
		return orderName;
	}
	
	public List<Item> getAllItems(){
		ArrayList<Item> allItems = new ArrayList<Item>();
		for(Item i : items.values()){
			allItems.add(i);
		}
		return allItems;
	}

}
