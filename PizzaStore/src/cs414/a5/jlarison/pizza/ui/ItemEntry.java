package cs414.a5.jlarison.pizza.ui;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.UUID;

public class ItemEntry implements Serializable {
	
	protected String name;
	protected double price;
	protected UUID itemId;
	protected double itemDiscountPercent;
	
	public ItemEntry(String name, double price, UUID itemId, double itemDiscountPercent) {
		super();
		this.name = name;
		this.price = price;
		this.itemId = itemId;
		this.itemDiscountPercent = itemDiscountPercent;
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
	
	public double getItemDiscountPercent(){
		return itemDiscountPercent;
	}
	
	public void setItemDiscountPercent(int itemDiscountPercent){
		this.itemDiscountPercent = itemDiscountPercent;
	}
	
	public String toString() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();   
		String discountString = "(-" + (int)itemDiscountPercent +"%) ";
		if (itemDiscountPercent > 0) {
			return discountString + name + ": " + formatter.format(price);
		}
		else return name + ": " + formatter.format(price);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ItemEntry other = (ItemEntry) obj;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
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
