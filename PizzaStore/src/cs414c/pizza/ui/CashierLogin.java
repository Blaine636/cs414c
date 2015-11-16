package cs414c.pizza.ui;

import java.awt.HeadlessException;
import java.rmi.RemoteException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import cs414c.pizza.controller.LoginControllerInterface;
import cs414c.pizza.controller.MenuControllerInterface;
import cs414c.pizza.controller.OrderControllerInterface;
import cs414c.pizza.controller.PaymentControllerInterface;

public class CashierLogin extends Login{

	private static final long serialVersionUID = 1L;

	private LoginControllerInterface loginController;
	private MenuControllerInterface menuController;
	private OrderControllerInterface orderController;
	private PaymentControllerInterface paymentController;
	
/*	public static void main(String[] args) {
		try {
			Login dialog = new CashierLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public CashierLogin(LoginControllerInterface loginController, MenuControllerInterface menuController, OrderControllerInterface orderController, PaymentControllerInterface paymentController){
		super();
		this.loginController = loginController;
		this.menuController = menuController;
		this.orderController = orderController;
	}
	
	public String getWindowTitle(){
		return "Cashier";
	}
	
	public void okPush(String username, char[] password) throws HeadlessException, RemoteException{
		String pass = new String(password);
		//System.out.println("--Manager info-- \nUname: " + username + "\nPass: " + pass);
		if(loginController.authenticateCashier(username, new String(password))){
			BeginOrderRegister register = new BeginOrderRegister(menuController, orderController, paymentController);
			dispose();
			register.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(contentPanel,
				    "Login Failed",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			super.usernameField.setText("");
			super.passwordField.setText("");
		}
		
	}

}
