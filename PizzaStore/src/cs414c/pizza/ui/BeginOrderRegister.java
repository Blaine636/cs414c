package cs414c.pizza.ui;

import cs414c.pizza.controller.MenuController;
import cs414c.pizza.controller.OrderController;
import cs414c.pizza.controller.PaymentController;

public class BeginOrderRegister extends BeginOrder{

	public BeginOrderRegister(MenuController menuController, OrderController orderController, PaymentController paymentController) {
		super();
		this.menuController = menuController;
		this.orderController = orderController;
		this.paymentController = paymentController;
	}

	@Override
	public void startOrderBehavior() {
		RegisterWindow register = new RegisterWindow(textField.getText(),menuController, orderController, paymentController );
		register.setVisible(true);
	}

	@Override
	public String getWindowTitle() {
		return "Register";
	}

}