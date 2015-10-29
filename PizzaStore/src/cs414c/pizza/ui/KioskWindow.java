package cs414c.pizza.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

import cs414c.pizza.controller.MenuController;
import cs414c.pizza.controller.OrderController;
import cs414c.pizza.controller.PaymentController;
import cs414c.pizza.dao.MenuDAO;

public class KioskWindow extends OrderWindow {
	
	public KioskWindow(String orderName, MenuController menuController,
			OrderController orderController, PaymentController paymentController) {
		super(orderName, menuController, orderController, paymentController);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
