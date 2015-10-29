package cs414c.pizza.test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import cs414c.pizza.controller.LoginController;
import cs414c.pizza.dao.LoginDAO;
import cs414c.pizza.dao.MenuDAO;
import cs414c.pizza.dao.RewardsDAO;

public class LoginControllerTest {
	
	LoginController lc;
	String newPassword;
	
	private String generateUsername() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
	
	@Before
	public void setup() {
		LoginDAO loginDAO = new LoginDAO();
		RewardsDAO rewardsDAO = new RewardsDAO();
		lc = new LoginController(loginDAO, rewardsDAO);
		newPassword = "password";
	}
	
	@Test
	public void testAuthenticate() {
		boolean result = lc.authenticateCashier("Jorsh", "1234");
		
		assertTrue(result);
	}
	
	@Test
	public void testAuthenticateInvalid() {
		boolean result = lc.authenticateCashier("invalidUser", "password");
		
		assertFalse(result);
	}
	
	@Test
	public void testAuthenticateEmptyUsername() {
		boolean result = lc.authenticateCashier("", "password");
		
		assertFalse(result);
	}
	
	@Test
	public void testAuthenticateNullUsername() {
		boolean result = lc.authenticateCashier(null, "password");
		
		assertFalse(result);
	}
	
	@Test
	public void testAuthenticateEmptyPassword() {
		boolean result = lc.authenticateCashier("testUser", "");
		
		assertFalse(result);
	}
	
	@Test
	public void testAuthenticateNullPassword() {
		boolean result = lc.authenticateCashier("testUser", null);
		
		assertFalse(result);
	}
	
	@Test
	public void testCreateAccount() {
		boolean result = lc.createCashierAccount(generateUsername(), newPassword);
		
		assertTrue(result);
	}
	
	@Test
	public void testCreateAccountEmptyUsername() {
		boolean result = lc.createCashierAccount("", newPassword);
		
		assertFalse(result);
	}
	
	@Test
	public void testCreateAccountNullUsername() {
		boolean result = lc.createCashierAccount(null, newPassword);
		
		assertFalse(result);
	}
	
	@Test
	public void testCreateAccountEmptyPassword() {
		boolean result = lc.createCashierAccount(generateUsername(), "");
		
		assertFalse(result);
	}
	
	@Test
	public void testCreateAccountNullPassword() {
		boolean result = lc.createCashierAccount(generateUsername(), "");
		
		assertFalse(result);
	}
	
	@Test
	public void testCreateAccountDuplicate() {
		String username = generateUsername();
		boolean result = lc.createCashierAccount(username, newPassword);
		boolean result2 = lc.createCashierAccount(username, newPassword);
		
		assertTrue(result);
		assertFalse(result2);
	}
	
	@Test
	public void testModifyPassword() {
		boolean result = lc.modifyCashierPassword("testUser", "password", "password1");
		assertTrue(result);
		boolean result2 = lc.modifyCashierPassword("testUser", "password1", "password");
		assertTrue(result2);
	}
	
	@Test
	public void testModifyPasswordIncorrect() {
		boolean result = lc.modifyCashierPassword("testUser", "wrongPassword", "password1");
		assertFalse(result);
	}
	
	@Test
	public void testModifyPasswordEmptyUser() {
		boolean result = lc.modifyCashierPassword("", "password", "password1");
		assertFalse(result);
	}
	
	@Test
	public void testModifyPasswordNullUser() {
		boolean result = lc.modifyCashierPassword(null, "password", "password1");
		assertFalse(result);
	}
	
	@Test
	public void testModifyPasswordEmpty1() {
		boolean result = lc.modifyCashierPassword("testUser", "", "password1");
		assertFalse(result);
	}
	
	@Test
	public void testModifyPasswordNull1() {
		boolean result = lc.modifyCashierPassword("testUser", null, "password1");
		assertFalse(result);
	}
	
	@Test
	public void testModifyPasswordEmpty2() {
		boolean result = lc.modifyCashierPassword("testUser", "password", "");
		assertFalse(result);
	}
	
	@Test
	public void testModifyPasswordNull2() {
		boolean result = lc.modifyCashierPassword("testUser", "password", null);
		assertFalse(result);
	}
	
	@Test
	public void testModifyPasswordDuplicateArgs() {
		boolean result = lc.modifyCashierPassword("testUser", "password", "password");
		assertFalse(result);
	}
	
	
}
