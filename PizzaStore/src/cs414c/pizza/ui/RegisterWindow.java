package cs414c.pizza.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class RegisterWindow extends OrderWindow{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterWindow(){
		super();
	}
	
	public static void main(String[] args) {
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
	}
	
	public String getWindowTitle(){
		return "Register";
	}

	@Override
	public void paymentBehavior() {
		System.out.println("Open register payment window.");
	}

}
