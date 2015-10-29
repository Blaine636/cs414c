package cs414c.pizza.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import cs414c.pizza.controller.MenuController;
import cs414c.pizza.dao.MenuDAO;
import cs414c.pizza.domain.Item;
import cs414c.pizza.domain.Menu;
import cs414c.pizza.domain.Pizza;
import cs414c.pizza.domain.Topping;
import cs414c.pizza.ui.ItemEntry;

public class MenuControllerTest {
	
	MenuController mc;
	String bigString;
	
	@Before
	public void setup() {
		MenuDAO menuDAO = new MenuDAO();
		mc = new MenuController(menuDAO);
		bigString = generateBigString();
	}

	@Test
	public void testInitializeMenu() {
		int numpizzas=mc.getPizzas().size();
		assertTrue(numpizzas==mc.getPizzas().size());
	}
	
	@Test
	public void testAddItem() {
		int initialSize = mc.getPizzas().size();
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		int resultSize = mc.getPizzas().size();
		
		assertTrue(resultSize == (initialSize + 1));
		assertTrue(mc.containsItem(itemId));
	}
	
	@Test
	public void testAddItemEmptyName() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("",6.00, "description",top);
		assertTrue(itemId==null);
	}
	
	@Test(expected = Exception.class)
	public void testAddItemNullName() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		String a=null;
		UUID itemId = mc.addPizzaToMenu(a,6.00, "description",top);
	}
	
	@Test
	public void testAddItemLargeName() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu(bigString,6.00, "description",top);
		assertTrue(itemId==null);
	}
	
	@Test
	public void testAddItemZeroPrice() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Thin Crust",0.0, "description",top);
		assertTrue(mc.containsItem(itemId));
	}
	
	@Test
	public void testAddItemEmptyDescription() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Hawiian",6.00, "",top);
		assertTrue(mc.containsItem(itemId));
	}
	
	@Test(expected = Exception.class)
	public void testAddItemLargeDescription() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("big",6.00, bigString, top);
	}
	
	@Test(expected = Exception.class)
	public void testAddItemNullDescription() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Thin Crust",0.0, "",top);
	}
	
	@Test
	public void testRemoveItem() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		boolean result = mc.removeItem(itemId);
		
		assertTrue(result);
	}
	
	@Test
	public void testRemoveInvalidItem() {
		UUID a = UUID.randomUUID();
		boolean result = mc.removeItem(a);
		
		assertFalse(result);
	}
	
	@Test
	public void testRemoveNullItem() {
		boolean result = mc.removeItem(null);
		
		assertFalse(result);
	}
	
	
	
	@Test
	public void testModifyItemName() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		boolean result = mc.modifyItemName(itemId,"Chicago Style Pizza");
	
		assertTrue(result);
		assertEquals("Chicago Style Pizza", mc.getItemName(itemId));
	}
	
	@Test
	public void testModifyItemNameEmpty() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
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
