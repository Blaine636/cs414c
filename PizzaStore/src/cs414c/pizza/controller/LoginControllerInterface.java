package cs414c.pizza.controller;

import java.rmi.Remote;

public interface LoginControllerInterface extends Remote{

	boolean authenticateCashier(String username, String password);

	boolean authenticateManager(String username, String password);

	boolean createCashierAccount(String username, String password);

	boolean createManagerAccount(String username, String password);

	double getRewardPoints(int rewardId);

	boolean modifyCashierPassword(String username, String oldPassword, String newPassword);

	int registerRewardId();

}