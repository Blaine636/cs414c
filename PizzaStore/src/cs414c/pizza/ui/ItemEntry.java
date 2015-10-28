package cs414c.pizza.ui;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.UUID;

public class ItemEntry {
	
	private String name;
	private double price;
	private UUID itemId;
	
	public ItemEntry(String name, double price, UUID itemId) {
		super();
		this.name = name;
		this.price = price;
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public UUID getItemId() {
		return itemId;
	}
	
	public String toString() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();   
		//System.out.println(formatter.format(4.0));
		return name + ": " + formatter.format(price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemEntry other = (ItemEntry) obj;
		if (itemId != other.itemId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

}
