package cs414c.pizza.ui;

import cs414c.pizza.controller.MenuControllerInterface;
import cs414c.pizza.controller.OrderControllerInterface;
import cs414c.pizza.controller.PaymentControllerInterface;

public class BeginOrderRegister extends BeginOrder{

	public BeginOrderRegister(MenuControllerInterface menuController, OrderControllerInterface orderController, PaymentControllerInterface paymentController) {
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