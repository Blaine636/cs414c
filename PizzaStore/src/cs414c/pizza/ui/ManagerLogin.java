package cs414c.pizza.ui;

import javax.swing.JDialog;

public class ManagerLogin extends Login{

	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		try {
			Login dialog = new ManagerLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ManagerLogin(){
		super();
	}
	
	public String getWindowTitle(){
		return "Manager";
	}
	
	public void okPush(String username, char[] password){
		String pass = new String(password);
		System.out.println("--Manager info-- \nUname: " + username + "\nPass: " + pass);
		ManagerWindow manager = new ManagerWindow();
		manager.setVisible(true);
		dispose();
	}

}
