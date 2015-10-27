package cs414c.pizza.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import cs414c.pizza.util.OrderStatus;

public class Order {
	private Map<UUID,Item> items;
	private OrderStatus status;
	String orderName;
	
	public Order(String orderName) {
		super();
		this.orderName = orderName;
		this.items = new HashMap<UUID,Item>();
		this.status = OrderStatus.STARTED;
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
		return "Order with " + items.size() + " items";
	}

	public boolean contains(UUID orderedItemUUID) {
		return items.containsKey(orderedItemUUID);
	}

}
