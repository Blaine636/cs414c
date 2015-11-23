package cs414.a5.jlarison.pizza.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import cs414.a5.jlarison.pizza.controller.PaymentController;
import cs414.a5.jlarison.pizza.controller.PaymentControllerInterface;
import cs414.a5.jlarison.pizza.dao.RewardsDAO;

public class PaymentContollerTest {
	
	PaymentControllerInterface pc;
	String validCredit = "4012888888881881";
	String invalidCredit = "401288888888188199999999";
	
	@Before
	public void setup() {
		RewardsDAO rewardsDAO = new RewardsDAO();
		pc = new PaymentController(rewardsDAO);
	}
	
	@Test
	public void testCreditCard() throws RemoteException  {
		boolean result = pc.makeCreditPayment(validCredit, 4.99);
		
		assertTrue(result);
	}
	
	@Test
	public void testCreditCardInvalid() throws RemoteException {
		boolean result = pc.makeCreditPayment(invalidCredit, 4.99);
		
		assertFalse(result);
	}
	
	@Test
	public void testCreditCardEmpty() throws RemoteException {
		boolean result = pc.makeCreditPayment("", 4.99);
		
		assertFalse(result);
	}
	
	@Test
	public void testCreditCardNull() throws RemoteException {
		boolean result = pc.makeCreditPayment(null, 4.99);
		
		assertFalse(result);
	}
	
	@Test
	public void testCreditCardZeroPayment() throws RemoteException {
		boolean result = pc.makeCreditPayment(validCredit, 0);
		
		assertTrue(result);
	}
	
	@Test
	public void testCashExactPayment() throws RemoteException {
		double result = pc.makeCashPayment(5.00, 5.00);
		
		assertEquals(0,result,0.01);
	}
	
	@Test
	public void testCashUnderPayment() throws RemoteException {
		double result = pc.makeCashPayment(3.00, 5.00);
		
		assertEquals(2,result,0.01);
	}
	
	@Test
	public void testCashOverPayment() throws RemoteException {
		double result = pc.makeCashPayment(20.00, 5.00);
		
		assertEquals(-15,result,0.01);
	}
	
	@Test
	public void testCashZeroPayment() throws RemoteException {
		double result = pc.makeCashPayment(0, 5.00);
		
		assertEquals(5,result,0.01);
	}
	
	@Test
	public void testCashZeroAmount() throws RemoteException {
		double result = pc.makeCashPayment(5.00, 0.00);
		
		assertEquals(-5,result,0.01);
	}
	
	@Test
	public void testCashAllZeroAmount() throws RemoteException {
		double result = pc.makeCashPayment(0.00, 0.00);
		
		assertEquals(0,result,0.01);
	}
	
	@Test(expected = Exception.class)
	public void testCashNegativePayment() throws RemoteException {
		double result = pc.makeCashPayment(-10.00, 5.00);
	}
	
	@Test(expected = Exception.class)
	public void testCashNegativeAmount() throws RemoteException {
		double result = pc.makeCashPayment(5.00, -10.00);
	}

}
