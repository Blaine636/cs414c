package cs414.a5.jlarison.pizza.ui;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.UUID;

public class PizzaEntry extends ItemEntry implements Serializable {
	
	private List<ItemEntry> toppings;
	private SizeEntry size;

	public PizzaEntry(String name, double price, UUID itemId, List<ItemEntry> toppings, double d) {
		super(name, price, itemId, d);
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
		String discountString = "(-" + (int)itemDiscountPercent +"%) ";
		if (itemDiscountPercent > 0) {
			return discountString + name + ": " + formatter.format(getCustomizedCost());
		}
		else return name + ": " + formatter.format(getCustomizedCost());
		
	}
	
	

}