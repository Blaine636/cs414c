package cs414c.pizza.ui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import cs414c.pizza.controller.LoginController;
import cs414c.pizza.controller.MenuController;

public class ManagerLogin extends Login{

	private static final long serialVersionUID = 1L;
	
	private MenuController menuController;
	private LoginController loginController;
	
/*	public static void main(String[] args) {
		try {
			Login dialog = new ManagerLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public ManagerLogin(LoginController loginController, MenuController menuController){
		super();
		this.menuController = menuController;
		this.loginController = loginController;
	}
	
	public String getWindowTitle(){
		return "Manager";
	}
	
	public void okPush(String username, char[] password){
		String pass = new String(password);
		//System.out.println("--Manager info-- \nUname: " + username + "\nPass: " + pass);
		if(loginController.authenticateManager(username, new String(password))){
			ManagerWindow manager = new ManagerWindow(menuController);
			manager.setVisible(true);
			dispose();
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
