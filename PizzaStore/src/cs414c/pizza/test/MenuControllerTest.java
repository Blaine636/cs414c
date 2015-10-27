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
	
	@Test
	public void testRemoveNullItem() {
		boolean result = mc.removeItem(0);
		
		assertFalse(result);
	}
	
	@Test
	public void testGetItemSummary() {
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, "description");
		String itemDesc = mc.getItemSummary(itemId);
		
		assertNotNull(itemDesc);
		assertFalse(itemDesc == "");
	}
	
	@Test(expected = Exception.class)
	public void testGetInvalidItemSummary() {
		String itemDesc = mc.getItemSummary(1234);
	}
	
	@Test
	public void testModifyItemName() {
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, "description");
		boolean result = mc.modifyItemName(itemId,"Chicago Style Pizza");
		
		assertTrue(result);
		assertEquals("Chicago Style Pizza", mc.getItemName(itemId));
	}
	
	@Test
	public void testModifyItemNameEmpty() {
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, "description");
		boolean result = mc.modifyItemName(itemId,"");
		
		assertFalse(result);
		assertEquals("Deep Dish Pizza", mc.getItemName(itemId));
	}
	
	@Test
	public void testModifyItemNameNull() {
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, "description");
		boolean result = mc.modifyItemName(itemId,null);
		
		assertFalse(result);
		assertEquals("Deep Dish Pizza", mc.getItemName(itemId));
	}
	
	@Test
	public void testModifyItemNameBig() {
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, "description");
		boolean result = mc.modifyItemName(itemId,bigString);
		
		assertFalse(result);
		assertEquals("Deep Dish Pizza", mc.getItemName(itemId));
	}
	
	@Test
	public void testModifyItemDescription() {
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, "description");
		boolean result = mc.modifyItemDescription(itemId,"description2");
		
		assertTrue(result);
		assertEquals("description2", mc.getItemName(itemId));
	}
	
	@Test
	public void testModifyItemDescriptionEmpty() {
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, "description");
		boolean result = mc.modifyItemName(itemId,"");
		
		assertFalse(result);
		assertEquals("Deep Dish Pizza", mc.getItemDescription(itemId));
	}
	
	@Test
	public void testModifyItemDescriptionNull() {
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, "description" );
		boolean result = mc.modifyItemDescription(itemId,null);
		
		assertFalse(result);
		assertEquals("Deep Dish Pizza", mc.getItemDescription(itemId));
	}
	
	@Test
	public void testModifyItemDescriptionBig() {
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, "description");
		boolean result = mc.modifyItemDescription(itemId,bigString);
		
		assertFalse(result);
		assertEquals("Deep Dish Pizza", mc.getItemDescription(itemId));
	}
	
	@Test
	public void testModifyItemPrice() {
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, "description");
		boolean result = mc.modifyItemPrice(itemId,7.00);
		
		assertTrue(result);
		assertEquals(7.00, mc.getItemPrice(itemId),0.01);
	}
	
	@Test
	public void testModifyItemPriceZero() {
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, "description");
		boolean result = mc.modifyItemPrice(itemId,0.00);
		
		assertTrue(result);
		assertEquals(0.00, mc.getItemPrice(itemId),0.01);
	}
	
	public void testGetMenuItems() {
		int itemId = mc.addItemToMenu("Deep Dish Pizza",6.00, "description");
		List<Integer> itemIdList = mc.getMenuItems();
		
		assertEquals(mc.menuSize(),itemIdList.size());
		assertTrue(itemIdList.contains(itemId));
	}
	
	private String generateBigString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<1000;i++ ) {
			sb.append("a");
		}
		return sb.toString();
	}
}
