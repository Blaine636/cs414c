package cs414c.pizza.ui;

public class ItemEntry {
	
	String name;
	double price;
	int itemId;
	
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

}
