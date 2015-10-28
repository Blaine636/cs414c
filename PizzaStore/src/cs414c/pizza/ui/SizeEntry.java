package cs414c.pizza.ui;

import java.text.NumberFormat;

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
		NumberFormat formatter = NumberFormat.getCurrencyInstance();   
		//System.out.println(formatter.format(4.0));
		return name + ": " + formatter.format(price);
	}
}
