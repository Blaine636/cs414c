package cs414.a5.jlarison.pizza.ui;

import java.io.Serializable;
import java.text.NumberFormat;

public class SizeEntry implements Serializable  {
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
