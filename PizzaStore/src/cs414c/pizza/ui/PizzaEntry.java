package cs414c.pizza.ui;

import java.util.List;

public class PizzaEntry extends ItemEntry {
	
	private List<Integer> toppingIds;
	private SizeEntry size;

	public PizzaEntry(String name, double price, int itemId, List<Integer> toppingIds) {
		super(name, price, itemId);
		this.toppingIds = toppingIds;
	}

	public List<Integer> getToppingIds() {
		return toppingIds;
	}
	
	public void setSize(SizeEntry size){
		this.size = size;
	}
	
	

}
