package cs414c.pizza.ui;

import javax.swing.DefaultComboBoxModel;

import cs414c.pizza.controller.OrderControllerInterface;
import cs414c.pizza.controller.PaymentController;

public class RegisterPayment extends PaymentWindow{
	
	public RegisterPayment(OrderControllerInterface orderController, PaymentController paymentController, int orderNumber){
		super(orderController, paymentController, orderNumber);
	}

	@Override
	public String getWindowTitle() {
		return "Register";
	}
	
	@Override
	public DefaultComboBoxModel getModel() {
		return new DefaultComboBoxModel(new String[] {"Cash", "Credit", "Rewards"});
	}

}
