package cs414c.pizza.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class KioskWindow extends OrderWindow {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KioskWindow(){
		super();
	}
	
	public static void main(String[] args) {
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
	}
	
	public String getWindowTitle(){
		return "Kiosk";
	}

	@Override
	public void paymentBehavior() {
		System.out.println("Open kiosk payment window.");
	}

}
