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
	
	private MenuController menuController;
	private OrderController orderController;
	private PaymentController paymentController;

	public RegisterWindow(MenuController menuController, OrderController orderController, PaymentController paymentController){
		super();
		this.menuController = menuController;
		this.orderController = orderController;
		this.paymentController = paymentController;
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
	public void paymentBehavior() {
		RegisterPayment registerPayment = new RegisterPayment(orderController, paymentController);
		registerPayment.setVisible(true);
		System.out.println("Open register payment window.");
	}

}
