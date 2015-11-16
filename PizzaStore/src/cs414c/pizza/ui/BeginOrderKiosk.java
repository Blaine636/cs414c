package cs414c.pizza.ui;

import cs414c.pizza.controller.MenuController;
import cs414c.pizza.controller.OrderControllerInterface;
import cs414c.pizza.controller.PaymentController;

public class BeginOrderKiosk extends BeginOrder{

	public BeginOrderKiosk(MenuController menuController, OrderControllerInterface orderController, PaymentController paymentController) {
		super();
		this.menuController = menuController;
		this.orderController = orderController;
		this.paymentController = paymentController;
	}

	@Override
	public void startOrderBehavior() {
		KioskWindow kiosk = new KioskWindow(textField.getText(),menuController, orderController, paymentController);
		kiosk.setVisible(true);
	}

	@Override
	public String getWindowTitle() {
		return "Kiosk";
	}

}
