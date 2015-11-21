package cs414c.pizza.ui;

import java.awt.HeadlessException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import cs414c.pizza.controller.LoginControllerInterface;
import cs414c.pizza.controller.OrderControllerInterface;

public class OrderManagementLogin extends Login {

	private static final long serialVersionUID = 1L;

	private OrderControllerInterface orderController;
	private LoginControllerInterface loginController;

	/*
	 * public static void main(String[] args) { try { Login dialog = new
	 * ManagerLogin();
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	public OrderManagementLogin(LoginControllerInterface loginController, OrderControllerInterface orderController) {
		super();
		this.orderController = orderController;
		this.loginController = loginController;
	}

	public String getWindowTitle() {
		return "Order Management";
	}

	public void okPush(String username, char[] password) throws HeadlessException, RemoteException {
		String pass = new String(password);
		if (loginController.authenticateCashier(username, new String(password))) {
			OrderManagement om = new OrderManagement(orderController);
			dispose();
			om.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(contentPanel, "Login Failed", "Error", JOptionPane.ERROR_MESSAGE);
			super.usernameField.setText("");
			super.passwordField.setText("");
		}

	}

}