package cs414c.pizza.test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import cs414c.pizza.controller.LoginController;
import cs414c.pizza.controller.LoginControllerInterface;
import cs414c.pizza.dao.LoginDAO;
import cs414c.pizza.dao.MenuDAO;
import cs414c.pizza.dao.RewardsDAO;

public class LoginControllerTest {
	
	LoginControllerInterface lc;
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
		boolean result=false;
		try {
			result = lc.authenticateCashier("Jorsh", "1234");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(result);
	}
	
	@Test
	public void testAuthenticateInvalid() {
		boolean result=true;
		try {
			result = lc.authenticateCashier("invalidUser", "password");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
	}
	
	@Test
	public void testAuthenticateEmptyUsername() {
		boolean result=true;
		try {
			result = lc.authenticateCashier("", "password");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
	}
	
	@Test
	public void testAuthenticateNullUsername() {
		boolean result=true;
		try {
			result = lc.authenticateCashier(null, "password");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
	}
	
	@Test
	public void testAuthenticateEmptyPassword() {
		boolean result=true;
		try {
			result = lc.authenticateCashier("testUser", "");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
	}
	
	@Test
	public void testAuthenticateNullPassword() {
		boolean result=true;
		try {
			result = lc.authenticateCashier("testUser", null);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
	}
	
	@Test
	public void testCreateAccount() {
		boolean result=false;
		try {
			result = lc.createCashierAccount(generateUsername(), newPassword);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(result);
	}
	
	@Test
	public void testCreateAccountEmptyUsername() {
		boolean result=true;
		try {
			result = lc.createCashierAccount("", newPassword);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
	}
	
	@Test
	public void testCreateAccountNullUsername() {
		boolean result=true;
		try {
			result = lc.createCashierAccount(null, newPassword);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
	}
	
	@Test
	public void testCreateAccountEmptyPassword() {
		boolean result=true;
		try {
			result = lc.createCashierAccount(generateUsername(), "");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
	}
	
	@Test
	public void testCreateAccountNullPassword() {
		boolean result=true;
		try {
			result = lc.createCashierAccount(generateUsername(), "");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
	}
	
	@Test
	public void testCreateAccountDuplicate() {
		String username = generateUsername();
		boolean result=false;
		try {
			result = lc.createCashierAccount(username, newPassword);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result2=true;
		try {
			result2 = lc.createCashierAccount(username, newPassword);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(result);
		assertFalse(result2);
	}
	
	@Test
	public void testModifyPassword() {
		boolean result=false;
		try {
			result = lc.modifyCashierPassword("Jorsh", "1234", "password1");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(result);
		boolean result2=false;
		try {
			result2 = lc.modifyCashierPassword("Jorsh", "password1", "1234");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(result2);
	}
	
	@Test
	public void testModifyPasswordIncorrect() {
		boolean result=true;
		try {
			result = lc.modifyCashierPassword("testUser", "wrongPassword", "password1");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(result);
	}
	
	@Test
	public void testModifyPasswordEmptyUser() {
		boolean result=true;
		try {
			result = lc.modifyCashierPassword("", "password", "password1");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(result);
	}
	
	@Test
	public void testModifyPasswordNullUser() {
		boolean result=true;
		try {
			result = lc.modifyCashierPassword(null, "password", "password1");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(result);
	}
	
	@Test
	public void testModifyPasswordEmpty1() {
		boolean result=true;
		try {
			result = lc.modifyCashierPassword("testUser", "", "password1");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(result);
	}
	
	@Test
	public void testModifyPasswordNull1() {
		boolean result=true;
		try {
			result = lc.modifyCashierPassword("testUser", null, "password1");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(result);
	}
	
	@Test
	public void testModifyPasswordEmpty2() {
		boolean result=true;
		try {
			result = lc.modifyCashierPassword("testUser", "password", "");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(result);
	}
	
	@Test
	public void testModifyPasswordNull2() {
		boolean result=true;
		try {
			result = lc.modifyCashierPassword("testUser", "password", null);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(result);
	}
	
	@Test
	public void testModifyPasswordDuplicateArgs() {
		boolean result=true;
		try {
			result = lc.modifyCashierPassword("testUser", "password", "password");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(result);
	}
	
	
}
