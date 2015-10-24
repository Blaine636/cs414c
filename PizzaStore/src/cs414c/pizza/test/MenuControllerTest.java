package cs414c.pizza.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import cs414c.pizza.controller.MenuController;
import cs414c.pizza.domain.Item;
import cs414c.pizza.domain.Menu;
import cs414c.pizza.domain.Pizza;
import cs414c.pizza.domain.Topping;
import cs414c.pizza.util.OrderStatus;

public class MenuControllerTest {
	
	MenuController mc;
	String bigString;
	
	@Before
	public void setup() {
		mc = new MenuController();
		bigString = generateBigString();
	}
	
	

	@Test
	public void testInitializeMenu() {
		mc.initializeMenu();
		
		assertTrue(mc.menuSize() > 0);
	}
	
	@Test
	public void testAddItem() {
		int initialSize = mc.menuSize();
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, "description");
		int resultSize = mc.menuSize();
		
		assertTrue(resultSize == (initialSize + 1));
		assertTrue(mc.containsItem(itemId));
	}
	
	@Test(expected = Exception.class)
	public void testAddItemEmptyName() {
		int itemId = mc.addItemToMenu("",6.00, "description");
	}
	
	@Test(expected = Exception.class)
	public void testAddItemNullName() {
		int itemId = mc.addItemToMenu(null,6.00, "description");
	}
	
	@Test(expected = Exception.class)
	public void testAddItemLargeName() {
		int itemId = mc.addItemToMenu(bigString,6.00, "description");
	}
	
	@Test
	public void testAddItemZeroPrice() {
		int itemId = mc.addItemToMenu("Water",0.00, "cup of water");
		assertTrue(mc.containsItem(itemId));
	}
	
	@Test
	public void testAddItemEmptyDescription() {
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, "");
		assertTrue(mc.containsItem(itemId));
	}
	
	@Test(expected = Exception.class)
	public void testAddItemLargeDescription() {
		int itemId = mc.addItemToMenu("big",6.00, bigString);
	}
	
	@Test(expected = Exception.class)
	public void testAddItemNullDescription() {
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, null);
		assertTrue(mc.containsItem(itemId));
	}
	
	@Test
	public void testRemoveItem() {
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, "description");
		boolean result = mc.removeItem(itemId);
		
		assertTrue(result);
	}
	
	@Test
	public void testRemoveInvalidItem() {
		boolean result = mc.removeItem(1245);
		
		assertFalse(result);
	}
	
	
	private String generateBigString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<1000;i++ ) {
			sb.append("a");
		}
		return sb.toString();
	}
}
