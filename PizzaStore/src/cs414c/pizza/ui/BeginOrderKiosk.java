package cs414c.pizza.ui;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import cs414c.pizza.controller.MenuControllerInterface;
import cs414c.pizza.controller.OrderControllerInterface;
import cs414c.pizza.controller.PaymentControllerInterface;

public class BeginOrderKiosk extends BeginOrder{

	public BeginOrderKiosk(MenuControllerInterface menuController, OrderControllerInterface orderController, PaymentControllerInterface paymentController) {
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
