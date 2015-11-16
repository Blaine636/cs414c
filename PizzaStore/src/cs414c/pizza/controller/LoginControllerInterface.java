package cs414c.pizza.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginControllerInterface extends Remote{

	boolean authenticateCashier(String username, String password) throws RemoteException;

	boolean authenticateManager(String username, String password) throws RemoteException;

	boolean createCashierAccount(String username, String password) throws RemoteException;

	boolean createManagerAccount(String username, String password) throws RemoteException;

	double getRewardPoints(int rewardId) throws RemoteException;

	boolean modifyCashierPassword(String username, String oldPassword, String newPassword) throws RemoteException;

	int registerRewardId() throws RemoteException;

}