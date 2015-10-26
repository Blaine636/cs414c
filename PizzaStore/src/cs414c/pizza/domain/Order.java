package cs414c.pizza.domain;

import java.util.ArrayList;
import java.util.List;

import cs414c.pizza.util.OrderStatus;

public class Order {
	private List<Item> items;
	private OrderStatus status;
	String orderName;
	
	public Order(String orderName) {
		super();
		this.orderName = orderName;
		this.items = new ArrayList<Item>();
		this.status = OrderStatus.STARTED;
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
