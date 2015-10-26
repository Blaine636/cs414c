package cs414c.pizza.ui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import cs414c.pizza.controller.LoginController;
import cs414c.pizza.controller.MenuController;
import cs414c.pizza.controller.OrderController;
import cs414c.pizza.controller.PaymentController;

public class CashierLogin extends Login{

	private static final long serialVersionUID = 1L;

	private LoginController loginController;
	private MenuController menuController;
	private OrderController orderController;
	private PaymentController paymentController;
	
/*	public static void main(String[] args) {
		try {
			Login dialog = new CashierLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public CashierLogin(LoginController loginController, MenuController menuController, OrderController orderController, PaymentController paymentController){
		super();
		this.loginController = loginController;
	}
	
	public String getWindowTitle(){
		return "Cashier";
	}
	
	public void okPush(String username, char[] password){
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
