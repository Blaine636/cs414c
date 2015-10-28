package cs414c.pizza.ui;

import javax.swing.DefaultComboBoxModel;

import cs414c.pizza.controller.OrderController;
import cs414c.pizza.controller.PaymentController;

public class RegisterPayment extends PaymentWindow{
	
	public RegisterPayment(OrderController orderController, PaymentController paymentController, int orderNumber){
		super(orderController, paymentController, orderNumber);
	}

	@Override
	public String getWindowTitle() {
		return "Register";
	}

	@Override
	public void addPaymentPress() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public DefaultComboBoxModel getModel() {
		return new DefaultComboBoxModel(new String[] {"Cash", "Credit", "Rewards"});
	}

}
