package cs414.a5.jlarison.pizza.controller;

import cs414.a5.jlarison.pizza.dao.*;
import cs414.a5.jlarison.pizza.util.Enum.*;

public class LoginController implements LoginControllerInterface{
	
	private LoginDAO ldao = null;
	private RewardsDAO rdao = null;
	
	public LoginController(LoginDAO loginDAO, RewardsDAO rewardsDAO){
		ldao = loginDAO;
		rdao = rewardsDAO;
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
	
	public boolean createManagerAccount(String username, String password) {
		return ldao.insertLogin(username, password, LoginType.MANAGER);
	}
	
	public double getRewardPoints(int rewardId) {
		return rdao.getRewardPoints(rewardId);
	}
	
	public boolean modifyCashierPassword(String username, String oldPassword, String newPassword) {
		return ldao.resetPassword(username, oldPassword, newPassword);
	}
	
	public int registerRewardId() {
		return rdao.registerRewardID();
	}
}
