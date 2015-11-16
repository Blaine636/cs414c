package cs414c.pizza.controller;

import java.rmi.Remote;

public interface PaymentControllerInterface extends Remote{

	boolean makeCreditPayment(String creditNumber, double charge);

	double makeCashPayment(double amountPaid, double charge);

	double getRewardsBalance(int rewardsId);

	double useRewardsPoints(int rewardsId, double transactionAmount);

}