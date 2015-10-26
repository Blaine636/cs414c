package cs414c.pizza.domain;

import java.util.List;

import cs414c.pizza.util.OrderStatus;

public class Order {
	private List<Item> items;
	private OrderStatus status;
	
	public Order(List<Item> items, OrderStatus status) {
		super();
		this.items = items;
		this.status = status;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public void addItem(Item i) {
		items.add(i);
	}
	
	public void removeItem(Item i) {
		items.remove(i);
	}
	
	public double calculateTotal() {
		double total = 0;
		for(Item item: items) {
			total += item.getCost();
		}
		return total;
	}
	
	public String toString(){
		return "Order with " + items.size() + " items";
	}

}
