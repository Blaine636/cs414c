package cs414c.pizza.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cs414c.pizza.controller.PaymentController;

public class PaymentContollerTest {
	
	PaymentController pc;
	String validCredit = "4012888888881881";
	String invalidCredit = "401288888888188199999999";
	
	@Before
	public void setup() {
		pc = new PaymentController();
	}
	
	@Test
	public void testCreditCard() {
		boolean result = pc.makeCreditPayment(validCredit, 4.99);
		
		assertTrue(result);
	}
	
	@Test
	public void testCreditCardInvalid() {
		boolean result = pc.makeCreditPayment(invalidCredit, 4.99);
		
		assertFalse(result);
	}
	
	@Test
	public void testCreditCardEmpty() {
		boolean result = pc.makeCreditPayment("", 4.99);
		
		assertFalse(result);
	}
	
	@Test
	public void testCreditCardNull() {
		boolean result = pc.makeCreditPayment(null, 4.99);
		
		assertFalse(result);
	}
	
	@Test
	public void testCreditCardZeroPayment() {
		boolean result = pc.makeCreditPayment(validCredit, 0);
		
		assertFalse(result);
	}
	
	@Test
	public void testCashExactPayment() {
		double result = pc.makeCashPayment(5.00, 5.00);
		
		assertEquals(0,result,0.01);
	}
	
	@Test
	public void testCashUnderPayment() {
		double result = pc.makeCashPayment(3.00, 5.00);
		
		assertEquals(2,result,0.01);
	}
	
	@Test
	public void testCashOverPayment() {
		double result = pc.makeCashPayment(20.00, 5.00);
		
		assertEquals(-15,result,0.01);
	}
	
	@Test
	public void testCashZeroPayment() {
		double result = pc.makeCashPayment(0, 5.00);
		
		assertEquals(5,result,0.01);
	}
	
	@Test
	public void testCashZeroAmount() {
		double result = pc.makeCashPayment(5.00, 0.00);
		
		assertEquals(-5,result,0.01);
	}
	
	@Test
	public void testCashAllZeroAmount() {
		double result = pc.makeCashPayment(0.00, 0.00);
		
		assertEquals(0,result,0.01);
	}
	
	@Test(expected = Exception.class)
	public void testCashNegativePayment() {
		double result = pc.makeCashPayment(-10.00, 5.00);
	}
	
	@Test(expected = Exception.class)
	public void testCashNegativeAmount() {
		double result = pc.makeCashPayment(5.00, -10.00);
	}

}
