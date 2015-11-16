package cs414c.pizza.ui;

import java.awt.Dimension;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;
import javax.swing.UIManager;

import cs414c.pizza.controller.MenuControllerInterface;
import cs414c.pizza.controller.OrderControllerInterface;
import cs414c.pizza.controller.PaymentControllerInterface;
import cs414c.pizza.dao.MenuDAO;

public class KioskWindow extends OrderWindow {
	
	public KioskWindow(String orderName, MenuControllerInterface menuController,
			OrderControllerInterface orderController, PaymentControllerInterface paymentController) {
		super(orderName, menuController, orderController, paymentController);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	public String getWindowTitle(){
		return "Kiosk";
	}

	@Override
	public boolean paymentBehavior() {
		KioskPayment kioskPayment = new KioskPayment(orderController, paymentController, orderNumber);
		kioskPayment.setVisible(true);
		System.out.println("Open kiosk payment window.");
		return kioskPayment.isPlaceOrderPressed();
	}

	@Override
	public void exitBehavior() {
		BeginOrderKiosk kiosk = new BeginOrderKiosk(menuController, orderController, paymentController);	
		dispose();
		kiosk.setVisible(true);
	}

}
