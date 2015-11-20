package cs414c.pizza.ui;

import java.awt.Dimension;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;
import javax.swing.UIManager;

import cs414c.pizza.controller.LoginControllerInterface;
import cs414c.pizza.controller.MenuControllerInterface;
import cs414c.pizza.controller.OrderControllerInterface;
import cs414c.pizza.controller.PaymentControllerInterface;
import cs414c.pizza.dao.MenuDAO;

public class RegisterWindow extends OrderWindow{
	
	public RegisterWindow(int orderId,MenuControllerInterface menuController,
			OrderControllerInterface orderController, PaymentControllerInterface paymentController) {
		super(orderId, menuController, orderController, paymentController);
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
			LoginControllerInterface loginStub = (LoginControllerInterface) registry.lookup("LoginController");
			CashierLogin dialog = new CashierLogin(loginStub, menuStub, orderStub, paymentStub);
			dialog.setVisible(true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getWindowTitle(){
		return "Register";
	}

	@Override
	public boolean paymentBehavior() {
		RegisterPayment registerPayment = new RegisterPayment(orderController, paymentController, orderNumber);
		registerPayment.setVisible(true);
		System.out.println("Open register payment window.");
		return registerPayment.isPlaceOrderPressed();
	}

	@Override
	public void exitBehavior() {
		BeginOrderRegister register = new BeginOrderRegister(menuController, orderController, paymentController);
		dispose();
		register.setVisible(true);
	}

}
