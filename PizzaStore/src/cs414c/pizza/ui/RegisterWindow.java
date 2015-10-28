package cs414c.pizza.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

import cs414c.pizza.controller.MenuController;
import cs414c.pizza.controller.OrderController;
import cs414c.pizza.controller.PaymentController;

public class RegisterWindow extends OrderWindow{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterWindow(MenuController menuController, OrderController orderController, PaymentController paymentController, String orderName){
		super(orderName);
		this.menuController = menuController;
		this.orderController = orderController;
		this.paymentController = paymentController;
		super.createOrder();
	}
	
/*	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
		            UIManager.getSystemLookAndFeelClassName());
			OrderWindow dialog = new RegisterWindow();
			dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			dialog.setMinimumSize(new Dimension(610,423));
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
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
