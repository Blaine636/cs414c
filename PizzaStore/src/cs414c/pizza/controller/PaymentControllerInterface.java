package cs414c.pizza.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PaymentControllerInterface extends Remote{

	boolean makeCreditPayment(String creditNumber, double charge) throws RemoteException;

	double makeCashPayment(double amountPaid, double charge) throws RemoteException;

	double getRewardsBalance(int rewardsId) throws RemoteException;

	double useRewardsPoints(int rewardsId, double transactionAmount) throws RemoteException;

}