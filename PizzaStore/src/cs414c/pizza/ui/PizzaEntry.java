package cs414c.pizza.ui;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.UUID;

public class PizzaEntry extends ItemEntry implements Serializable {
	
	private List<ItemEntry> toppings;
	private SizeEntry size;

	public PizzaEntry(String name, double price, UUID itemId, List<ItemEntry> toppings, int itemDiscountPercent) {
		super(name, price, itemId, itemDiscountPercent);
		this.toppings = toppings;
	}

	public List<ItemEntry> getToppings() {
		return toppings;
	}
	
	public void setSize(SizeEntry size){
		this.size = size;
	}
	
	public double getCustomizedCost() {
		double total = price;
		for(ItemEntry topping : toppings) {
			total += topping.getPrice();
		}
		return total;
	}
	
	@Override
	public String toString() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(); 
		return name + ": " + formatter.format(getCustomizedCost());
	}
	
	

}
