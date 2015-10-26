package cs414c.pizza.ui;

import cs414c.pizza.controller.MenuController;
import cs414c.pizza.controller.OrderController;
import cs414c.pizza.controller.PaymentController;

public class BeginOrderKiosk extends BeginOrder{

	public BeginOrderKiosk(MenuController menuController, OrderController orderController, PaymentController paymentController) {
		super();
		this.menuController = menuController;
		this.orderController = orderController;
		this.paymentController = paymentController;
	}

	@Override
	public void startOrderBehavior() {
		KioskWindow kiosk = new KioskWindow(menuController, orderController, paymentController, textField.getText());
		kiosk.setVisible(true);
	}

}