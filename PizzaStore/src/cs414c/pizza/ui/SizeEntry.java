package cs414c.pizza.ui;	

public class SizeEntry {
	private String name;
	private double price;
	
	public SizeEntry(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	
	public String toString(){
		return name + ": +$" + price;	
	}
}
