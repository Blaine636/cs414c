package cs414c.pizza.util;

public class Enum{
	public enum OrderStatus {
		STARTED,
		PLACED,
		COMPLETED,
		CANCELLED
	}
	
	public enum PizzaSize {
		LARGE,MEDIUM,SMALL;
		
		private double cost;
		private String name;
		
		static {
	        LARGE.cost = 3.00;
	        MEDIUM.cost = 2.00;
	        SMALL.cost = 1.00;
	        LARGE.name = "Large";
	        MEDIUM.name = "Medium";
	        SMALL.name = "Small";
	    }
		
		public double getCost() {
			return cost;
		}
		
		public String getName() {
			return name;
		}
	}
	
	public enum LoginType{
		CASHIER,
		MANAGER;
	}
}



