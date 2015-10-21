package InterfaceComponents;

import javax.swing.JDialog;

public class CashierLogin extends Login{

	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		try {
			Login dialog = new CashierLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CashierLogin(){
		super();
	}
	
	public String getWindowTitle(){
		return "Cashier";
	}
	
	public void okPush(String username, char[] password){
		String pass = new String(password);
		System.out.println("--Cashier info-- \nUname: " + username + "\nPass: " + pass);
		dispose();
	}

}
