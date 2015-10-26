package cs414c.pizza.controller;

public class PaymentController {
	
	public boolean makeCreditPayment(String creditNumber, double charge) {
		if(creditNumber == null) {
			return false;
		}
		else if(creditNumber.length() == 16) {
			return true;
		}
		else return false;
	}
	
	public double makeCashPayment(double amountPaid, double charge) {
		if(amountPaid < 0) throw new NumberFormatException();
		if(charge < 0) throw new NumberFormatException();
		return charge - amountPaid;
	}

}
