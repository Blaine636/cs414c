package cs414c.pizza.ui;

import java.util.List;
import java.util.UUID;

public class PizzaEntry extends ItemEntry {
	
	private List<UUID> toppingIds;
	private SizeEntry size;

	public PizzaEntry(String name, double price, UUID itemId, List<UUID> toppingIds) {
		super(name, price, itemId);
		this.toppingIds = toppingIds;
	}

	public List<UUID> getToppingIds() {
		return toppingIds;
	}
	
	public void setSize(SizeEntry size){
		this.size = size;
	}
	
	

}
