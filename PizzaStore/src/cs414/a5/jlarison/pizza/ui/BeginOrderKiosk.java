package cs414.a5.jlarison.pizza.ui;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import cs414.a5.jlarison.pizza.controller.MenuControllerInterface;
import cs414.a5.jlarison.pizza.controller.OrderControllerInterface;
import cs414.a5.jlarison.pizza.controller.PaymentControllerInterface;

public class BeginOrderKiosk extends BeginOrder{

	public BeginOrderKiosk(MenuControllerInterface menuController, OrderControllerInterface orderController, PaymentControllerInterface paymentController) {
		super();
		this.menuController = menuController;
		this.orderController = orderController;
		this.paymentController = paymentController;
	}

	@Override
	public void startOrderBehavior() {
		int orderId;
		try {
			orderId = orderController.createOrder(textField.getText());
			KioskWindow kiosk = new KioskWindow(orderId, menuController, orderController, paymentController);
			kiosk.setVisible(true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getWindowTitle() {
		return "Kiosk";
	}

}
