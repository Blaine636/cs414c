package cs414.a5.jlarison.pizza.domain;

import java.util.UUID;

public class Topping extends Item{
	private String name;
	private double price;
	
	public Topping(String name, double price) {
		super(name,price,null);
		this.name = name;
		this.price = price;
	}
	
	public Topping(UUID id, String name, double price) {
		super(id,name,price,null);
		this.name = name;
		this.price = price;
	}

	public double getCost() {
		return price * ((100-discountPercent)/100);
	}
	
	public String toString(){
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Topping other = (Topping) obj;
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