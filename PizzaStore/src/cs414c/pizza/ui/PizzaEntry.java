package cs414c.pizza.ui;

import java.util.List;

public class PizzaEntry extends ItemEntry {
	
	private List<Integer> toppingIds;

	public PizzaEntry(String name, double price, int itemId, List<Integer> toppingIds) {
		super(name, price, itemId);
		this.toppingIds = toppingIds;
	}

}
