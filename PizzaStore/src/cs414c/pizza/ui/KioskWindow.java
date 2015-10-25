package cs414c.pizza.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

import cs414c.pizza.controller.MenuController;
import cs414c.pizza.controller.OrderController;
import cs414c.pizza.controller.PaymentController;

public class KioskWindow extends OrderWindow {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MenuController menuController;
	private OrderController orderController;
	private PaymentController paymentController;

	public KioskWindow(MenuController menuController, OrderController orderController, PaymentController paymentController){
		super();
		this.menuController = menuController;
		this.orderController = orderController;
		this.paymentController = paymentController;
	}
	
/*	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
		            UIManager.getSystemLookAndFeelClassName());
			OrderWindow dialog = new KioskWindow();
			dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			dialog.setMinimumSize(new Dimension(610,423));
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public String getWindowTitle(){
		return "Kiosk";
	}

	@Override
	public void paymentBehavior() {
		KioskPayment kioskPayment = new KioskPayment(orderController, paymentController);
		kioskPayment.setVisible(true);
		System.out.println("Open kiosk payment window.");
	}

}
