package cs414c.pizza.domain;

import java.util.UUID;

public class SideItem extends Item {

	public SideItem(String name, double basePrice, String description, UUID itemId) {
		super(name, basePrice, description, itemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getCost() {
		return basePrice;
	}

}
