package cs414c.pizza.ui;

import javax.swing.DefaultComboBoxModel;

import cs414c.pizza.controller.OrderControllerInterface;
import cs414c.pizza.controller.PaymentControllerInterface;

public class KioskPayment extends PaymentWindow {
	
	public KioskPayment(OrderControllerInterface orderController, PaymentControllerInterface paymentController, int orderNumber){
		super(orderController, paymentController, orderNumber);
	}

	@Override
	public String getWindowTitle() {
		return "Kiosk";
	}

	@Override
	public DefaultComboBoxModel getModel() {
		return new DefaultComboBoxModel(new String[] {"Credit", "Rewards"});
	}

}
