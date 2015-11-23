package cs414.a5.jlarison.pizza.ui;

import cs414.a5.jlarison.pizza.controller.MenuControllerInterface;
import cs414.a5.jlarison.pizza.controller.OrderControllerInterface;
import cs414.a5.jlarison.pizza.controller.PaymentControllerInterface;

public class BeginOrderRegister extends BeginOrder{

	public BeginOrderRegister(MenuControllerInterface menuController, OrderControllerInterface orderController, PaymentControllerInterface paymentController) {
		super();
		this.menuController = menuController;
		this.orderController = orderController;
		this.paymentController = paymentController;
	}

	@Override
	public void startOrderBehavior() {
		OrderTypeDialog dialog = new OrderTypeDialog(textField.getText(), menuController, orderController, paymentController);
		dialog.setVisible(true);
		
		//RegisterWindow register = new RegisterWindow(textField.getText(),menuController, orderController, paymentController );
		//register.setVisible(true);
	}

	@Override
	public String getWindowTitle() {
		return "Register";
	}

}