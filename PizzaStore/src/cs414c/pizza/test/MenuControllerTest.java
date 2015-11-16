package cs414c.pizza.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import cs414c.pizza.controller.MenuController;
import cs414c.pizza.controller.MenuControllerInterface;
import cs414c.pizza.dao.MenuDAO;
import cs414c.pizza.domain.Item;
import cs414c.pizza.domain.Menu;
import cs414c.pizza.domain.Pizza;
import cs414c.pizza.domain.Topping;
import cs414c.pizza.ui.ItemEntry;
import cs414c.pizza.ui.PizzaEntry;

public class MenuControllerTest {
	
	MenuControllerInterface mc;
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
		int initialSize = mc.getSides().size();
		UUID itemId = mc.addSideItemToMenu("Chocolate Cake",6.00, "description");
		int resultSize = mc.getSides().size();
		
		assertTrue(resultSize == (initialSize + 1));
		assertTrue(mc.getItemDescription(itemId)=="description");
		assertTrue(mc.containsItem(itemId));
	}
	
	@Test
	public void testAddItemEmptyName() {
		UUID itemId = mc.addSideItemToMenu("",6.00, "description");
		assertTrue(itemId==null);
	}
	
	@Test(expected = Exception.class)
	public void testAddItemNullName() {
		String a=null;
		UUID itemId = mc.addSideItemToMenu(a,6.00, "description");
	}
	
	@Test
	public void testAddItemLargeName() {
		UUID itemId = mc.addSideItemToMenu(bigString,6.00, "description");
		assertTrue(itemId==null);
	}
	
	@Test
	public void testAddItemZeroPrice() {
		UUID itemId = mc.addSideItemToMenu("Chocolate Cake",0.0, "description");
		assertTrue(mc.containsItem(itemId));
	}
	
	@Test
	public void testAddTopping() {
		int initialSize = mc.getToppings().size();
		UUID itemId = mc.addToppingToMenu("Bacon",6.00);
		int resultSize = mc.getToppings().size();
		
		assertTrue(resultSize == (initialSize + 1));
		assertTrue(mc.containsItem(itemId));
	}
	
	@Test
	public void testAddToppingEmptyName() {
		UUID itemId = mc.addToppingToMenu("",6.00);
		assertTrue(itemId==null);
	}
	
	@Test(expected = Exception.class)
	public void testAddToppingNullName() {
		String a=null;
		UUID itemId = mc.addToppingToMenu(a,6.00);
	}
	
	@Test
	public void testAddToppingLargeName() {
		UUID itemId = mc.addToppingToMenu(bigString,6.00);
		assertTrue(itemId==null);
	}
	
	@Test
	public void testAddToppingZeroPrice() {
		UUID itemId = mc.addToppingToMenu("Bacon",0.0);
		assertTrue(mc.containsItem(itemId));
	}
	
	@Test
	public void testAddItemEmptyDescription() {
		UUID itemId = mc.addSideItemToMenu("Cheesecake",6.00, "");
		assertTrue(mc.containsItem(itemId));
	}
	
	@Test
	public void testAddItemLargeDescription() {
		UUID itemId = mc.addSideItemToMenu("Cheesecake",6.00, bigString);
		
		assertTrue(itemId==null);
	}
	
	@Test
	public void testAddItemNullDescription() {
		UUID itemId = mc.addSideItemToMenu("Churro",0.0, "");
	}
	
	@Test
	public void testAddPizza() {
		int initialSize = mc.getPizzas().size();
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		int resultSize = mc.getPizzas().size();
		
		assertTrue(resultSize == (initialSize + 1));
		assertTrue(mc.getItemDescription(itemId)=="description");
		assertTrue(mc.containsItem(itemId));
	}
	
	@Test
	public void testAddPizzaEmptyName() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("",6.00, "description",top);
		assertTrue(itemId==null);
	}
	
	@Test(expected = Exception.class)
	public void testAddPizzaNullName() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		String a=null;
		UUID itemId = mc.addPizzaToMenu(a,6.00, "description",top);
	}
	
	@Test
	public void testAddPizzaLargeName() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu(bigString,6.00, "description",top);
		assertTrue(itemId==null);
	}
	
	@Test
	public void testAddPizzaZeroPrice() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Thin Crust",0.0, "description",top);
		assertTrue(mc.containsItem(itemId));
	}
	
	@Test
	public void testAddPizzaEmptyDescription() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Hawiian",6.00, "",top);
		assertTrue(mc.containsItem(itemId));
	}
	
	@Test
	public void testAddPizzaLargeDescription() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("big",6.00, bigString, top);
		
		assertTrue(itemId==null);
	}
	
	@Test
	public void testAddPizzaNullDescription() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Thin Crust",0.0, "",top);
	}
	
	@Test
	public void testRemoveItem() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		boolean result = mc.removePizza(itemId);
		
		assertTrue(result);
	}
	
	@Test
	public void testRemoveInvalidItem() {
		UUID a = UUID.randomUUID();
		boolean result = mc.removePizza(a);
		
		assertFalse(result);
	}
	
	@Test
	public void testRemoveNullItem() {
		boolean result = mc.removePizza(null);
		
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
	
	@Test(expected = Exception.class)
	public void testModifyItemNameNull() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		boolean result = mc.modifyItemName(itemId,null);
		
		assertFalse(result);
		assertEquals("Deep Dish Pizza", mc.getItemName(itemId));
	}
	
	@Test
	public void testModifyItemNameBig() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		boolean result = mc.modifyItemName(itemId,bigString);
		
		assertFalse(result);
		assertEquals("Deep Dish Pizza", mc.getItemName(itemId));
	}
	
	@Test
	public void testModifyItemDescription() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		boolean result = mc.modifyItemDescription(itemId,"description2");
		
		assertTrue(result);
		assertEquals("description2", mc.getItemDescription(itemId));
	}
	
	@Test
	public void testModifyItemDescriptionEmpty() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		boolean result = mc.modifyItemName(itemId,"");
		
		assertFalse(result);
		assertEquals("description", mc.getItemDescription(itemId));
	}
	
	@Test(expected = Exception.class)
	public void testModifyItemDescriptionNull() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		boolean result = mc.modifyItemDescription(itemId,null);
		
		assertFalse(result);
		assertEquals("description", mc.getItemDescription(itemId));
	}
	
	@Test
	public void testModifyItemDescriptionBig() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		boolean result = mc.modifyItemDescription(itemId,bigString);
		
		assertFalse(result);
		assertEquals("description", mc.getItemDescription(itemId));
	}
	
	@Test
	public void testModifyItemPrice() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		boolean result = mc.modifyItemPrice(itemId,7.00);
		
		assertTrue(result);
		assertEquals(7.00, mc.getItemPrice(itemId),0.01);
	}
	
	@Test
	public void testModifyItemPriceZero() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		boolean result = mc.modifyItemPrice(itemId,0.00);
		
		assertTrue(result);
		assertEquals(0.00, mc.getItemPrice(itemId),0.01);
	}
	
	public void testGetPizzas() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		top.add(mc.getToppings().get(0));
		UUID itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		List<PizzaEntry> itemIdList = mc.getPizzas();
		
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
