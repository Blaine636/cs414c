package cs414c.pizza.ui;

public class ItemEntry {
	
	private String name;
	private double price;
	private int itemId;
	
	public ItemEntry(String name, double price, int itemId) {
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

	public int getItemId() {
		return itemId;
	}
	
	public String toString() {
		return name + ": $" + price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemId;
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
