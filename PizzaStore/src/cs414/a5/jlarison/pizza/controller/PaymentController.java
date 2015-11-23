package cs414.a5.jlarison.pizza.controller;

import cs414.a5.jlarison.pizza.dao.RewardsDAO;

public class PaymentController implements PaymentControllerInterface {
	
	private RewardsDAO dao;
	
	public PaymentController(RewardsDAO rewardsDAO) {
		this.dao = rewardsDAO;
	}
	
	@Override
	public boolean makeCreditPayment(String creditNumber, double charge) {
		if(creditNumber == null) {
			return false;
		}
		else if(creditNumber.length() == 16) {
			return true;
		}
		else return false;
	}
	
	@Override
	public double makeCashPayment(double amountPaid, double charge) {
		if(amountPaid < 0) throw new NumberFormatException();
		if(charge < 0) throw new NumberFormatException();
		return charge - amountPaid;
	}
	
	@Override
	public double getRewardsBalance(int rewardsId) {
		return dao.getRewardPoints(rewardsId);
	}
	
	@Override
	public double useRewardsPoints(int rewardsId, double transactionAmount) {
		try {
			return dao.useRewardPoints(rewardsId, transactionAmount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
