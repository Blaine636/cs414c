package cs414c.pizza.domain;

public class Special {
	
	private int discountPercent;
	private Item specialItem;
	
	public Special(Item specialItem, int discountPercent) {
		super();
		this.discountPercent = discountPercent;
		this.specialItem = specialItem;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public Item getSpecialItem() {
		return specialItem;
	}
	
    

}
