package cs414c.pizza.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cs414c.pizza.util.Enum.*;

public class Order {
	private Map<UUID,OrderedItem> items;
	private OrderStatus status;
	String orderName;
	int orderId;
	
	public Order(String orderName, int orderId) {
		super();
		this.orderName = orderName;
		this.items = new HashMap<UUID,OrderedItem>();
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
		OrderedItem orderedItem = new OrderedItem(i);
		items.put(orderedItem.getOrderItemId(), orderedItem);
		return orderedItem.getOrderItemId();
	}
	
	public void removeItem(UUID id) {
		items.remove(id);
	}
	
	public double calculateTotal() {
		double total = 0;
		for(OrderedItem item: items.values()) {
			total += item.getCost();
		}
		return total;
	}

	public boolean contains(UUID orderedItemUUID) {
		return items.containsKey(orderedItemUUID);
	}
	
	public OrderedItem getItem(UUID itemId) {
		return items.get(itemId);
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
	
	public List<OrderedItem> getAllItems(){
		ArrayList<OrderedItem> allItems = new ArrayList<OrderedItem>();
		for(OrderedItem i : items.values()){
			allItems.add(i);
		}
		return allItems;
	}

}
