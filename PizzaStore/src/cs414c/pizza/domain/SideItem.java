package cs414c.pizza.domain;

import java.util.UUID;

public class SideItem extends Item {

	public SideItem(String name, double basePrice, String description) {
		super(name, basePrice, description);
		// TODO Auto-generated constructor stub
	}

	public SideItem(UUID id, String name, double basePrice, String description) {
		super(id, name, basePrice, description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double getCost() {
		return basePrice;
	}
	
	public String toString(){
		return super.name;
	}

}
