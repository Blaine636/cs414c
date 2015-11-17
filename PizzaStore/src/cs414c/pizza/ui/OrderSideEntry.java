package cs414c.pizza.ui;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.UUID;

public class OrderSideEntry extends OrderItemEntry implements Serializable {
	
	public OrderSideEntry(String name, double price, UUID uniqueId) {
		super(name, price, uniqueId);
	}
	
	public String toString(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return name + " " + formatter.format(price);
	}
	

}
