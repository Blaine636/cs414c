package cs414c.pizza.controller;

import cs414c.pizza.dao.*;
import cs414c.pizza.util.Enum.*;

public class LoginController {
	
	private LoginDAO ldao = null;
	private RewardsDAO rdao = null;
	
	public LoginController(){
		ldao = new LoginDAO();
		rdao = new RewardsDAO();
	}
	
	public boolean authenticateCashier(String username, String password) {
		LoginType lt = ldao.validateLogin(username, password);
		if(lt == LoginType.CASHIER || lt == LoginType.MANAGER)
			return true;
		return false;
	}
	
	public boolean authenticateManager(String username, String password) {
		if(ldao.validateLogin(username, password) == LoginType.MANAGER)
			return true;
		return false;
	}

	public boolean createCashierAccount(String username, String password) {
		return ldao.insertLogin(username, password, LoginType.CASHIER);
	}
	
	public int getRewardPoints(int rewardId) {
		return rdao.getRewardPoints(rewardId);
	}
	
	public boolean modifyCashierPassword(String username, String oldPassword, String newPassword) {
		return ldao.resetPassword(username, oldPassword, newPassword);
	}
	
	public int registerRewardId() {
		return rdao.registerRewardID();
	}
}
