package cs414c.pizza.domain;

import java.util.UUID;

public class OrderedItem {
	
	private Item item;
	private UUID orderItemId;
	
	public OrderedItem(Item item) {
		this.orderItemId = UUID.randomUUID();
		this.item = item;
		this.orderItemId = orderItemId;
	}
	
	public double getCost() {
		return item.getCost();
	}

	public Item getItem() {
		return item;
	}

	public UUID getOrderItemId() {
		return orderItemId;
	}
	
}
