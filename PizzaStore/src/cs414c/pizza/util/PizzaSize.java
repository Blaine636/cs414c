package cs414c.pizza.util;

public enum PizzaSize {
	LARGE,MEDIUM,SMALL;
	
	private double cost;
	
	static {
        LARGE.cost = 3.00;
        MEDIUM.cost = 2.00;
        SMALL.cost = 1.00;
    }
	
	public double getCost() {
		return cost;
	}
}
