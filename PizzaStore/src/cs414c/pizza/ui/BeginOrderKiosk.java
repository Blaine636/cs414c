package cs414c.pizza.ui;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import cs414c.pizza.controller.MenuControllerInterface;
import cs414c.pizza.controller.OrderControllerInterface;
import cs414c.pizza.controller.PaymentControllerInterface;

public class BeginOrderKiosk extends BeginOrder{
	
	public static void main(String[] args) {
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry();

			MenuControllerInterface menuStub = (MenuControllerInterface) registry.lookup("MenuController");
			OrderControllerInterface orderStub = (OrderControllerInterface) registry.lookup("OrderController");
			PaymentControllerInterface paymentStub = (PaymentControllerInterface) registry.lookup("PaymentController");
			BeginOrder dialog = new BeginOrderKiosk(menuStub, orderStub, paymentStub);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
