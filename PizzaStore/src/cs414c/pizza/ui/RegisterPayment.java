package cs414c.pizza.ui;

import cs414c.pizza.controller.OrderController;
import cs414c.pizza.controller.PaymentController;

public class RegisterPayment extends PaymentWindow{
	
	private OrderController orderController;
	private PaymentController paymentController;
	
	public RegisterPayment(OrderController orderController, PaymentController paymentController){
		super();
		this.orderController = orderController;
		this.paymentController = paymentController;
	}

	@Override
	public String getWindowTitle() {
		return "Register";
	}

}
