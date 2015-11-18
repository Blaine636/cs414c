package cs414c.pizza.ui;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.UUID;

public class OrderPizzaEntry extends OrderItemEntry implements Serializable {
	
	private SizeEntry size;
	private List<ItemEntry> toppings;
	
	public OrderPizzaEntry(String name, SizeEntry size, List<ItemEntry> toppings, double totalPrice, UUID uniqueId) {
		super(name, totalPrice, uniqueId);
		this.toppings = toppings;
		this.size = size;
	}

	public int getNumToppings() {
		return toppings.size();
	}
	
	public String toString(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return size.getName() + " " + getNumToppings() + " Topping " + name + " " + formatter.format(price) + " (" + getToppingsString() + ")";
	}
	
	private String getToppingsString() {
		StringBuilder result = new StringBuilder();
	    for(ItemEntry topping : toppings) {
	        result.append(topping.getName());
	        result.append(",");
	    }
	    return result.length() > 0 ? result.substring(0, result.length() - 1): "";
	}
	

}
