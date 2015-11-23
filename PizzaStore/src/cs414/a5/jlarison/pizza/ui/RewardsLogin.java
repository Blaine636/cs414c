package cs414.a5.jlarison.pizza.ui;

import javax.swing.JDialog;

public class RewardsLogin extends Login {
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		try {
			Login dialog = new RewardsLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RewardsLogin(){
		super();
	}

	@Override
	public void okPush(String username, char[] password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getWindowTitle() {
		return "Rewards";
	}

}
