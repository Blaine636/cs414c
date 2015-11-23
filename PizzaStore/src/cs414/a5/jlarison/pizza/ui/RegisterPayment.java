package cs414.a5.jlarison.pizza.ui;

import javax.swing.DefaultComboBoxModel;

import cs414.a5.jlarison.pizza.controller.OrderControllerInterface;
import cs414.a5.jlarison.pizza.controller.PaymentControllerInterface;

public class RegisterPayment extends PaymentWindow{
	
	public RegisterPayment(OrderControllerInterface orderController, PaymentControllerInterface paymentController, int orderNumber){
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
