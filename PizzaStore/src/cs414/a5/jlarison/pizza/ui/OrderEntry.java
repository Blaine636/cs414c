package cs414.a5.jlarison.pizza.ui;

import java.io.Serializable;
import java.util.List;

public class OrderEntry implements Serializable {
	
	private String orderName;
	private int orderId;
	private List<OrderItemEntry> items;
	
	public OrderEntry(String orderName, int orderId, List<OrderItemEntry> items) {
		this.orderName = orderName;
		this.orderId = orderId;
		this.items = items;
	}

	public String getOrderName() {
		return orderName;
	}

	public int getOrderId() {
		return orderId;
	}

	public List<OrderItemEntry> getItems() {
		return items;
	}
	
	@Override
	public String toString(){
		return "Order " + this.orderId + ": " + this.orderName + ", " + items.size() + " items";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + orderId;
		result = prime * result + ((orderName == null) ? 0 : orderName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderEntry other = (OrderEntry) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (orderId != other.orderId)
			return false;
		if (orderName == null) {
			if (other.orderName != null)
				return false;
		} else if (!orderName.equals(other.orderName))
			return false;
		return true;
	}
	
	

}
