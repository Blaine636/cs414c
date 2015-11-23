package cs414.a5.jlarison.pizza.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import cs414.a5.jlarison.pizza.controller.MenuController;
import cs414.a5.jlarison.pizza.controller.MenuControllerInterface;
import cs414.a5.jlarison.pizza.dao.MenuDAO;
import cs414.a5.jlarison.pizza.domain.Item;
import cs414.a5.jlarison.pizza.domain.Menu;
import cs414.a5.jlarison.pizza.domain.Pizza;
import cs414.a5.jlarison.pizza.domain.Topping;
import cs414.a5.jlarison.pizza.ui.ItemEntry;
import cs414.a5.jlarison.pizza.ui.PizzaEntry;

public class MenuControllerTest {
	
	MenuControllerInterface mc;
	String bigString;
	
	@Before
	public void setup() {
		MenuDAO menuDAO = new MenuDAO();
		Menu menu = new Menu(menuDAO);
		mc = new MenuController(menu);
		bigString = generateBigString();
	}

	@Test
	public void testInitializeMenu() {
		int numpizzas=0;
		try {
			numpizzas = mc.getPizzas().size();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertTrue(numpizzas==mc.getPizzas().size());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddItem() {
		int initialSize=0;
		try {
			initialSize = mc.getSides().size();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addSideItemToMenu("Chocolate Cake",6.00, "description");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int resultSize=0;
		try {
			resultSize = mc.getSides().size();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(resultSize == (initialSize + 1));
		try {
			assertTrue(mc.getItemDescription(itemId)=="description");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertTrue(mc.containsItem(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddItemEmptyName() {
		UUID itemId=null;
		try {
			itemId = mc.addSideItemToMenu("",6.00, "description");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(itemId==null);
	}
	
	@Test(expected = Exception.class)
	public void testAddItemNullName() {
		String a=null;
		try {
			UUID itemId = mc.addSideItemToMenu(a,6.00, "description");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddItemLargeName() {
		UUID itemId=null;
		try {
			itemId = mc.addSideItemToMenu(bigString,6.00, "description");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(itemId==null);
	}
	
	@Test
	public void testAddItemZeroPrice() {
		UUID itemId=null;
		try {
			itemId = mc.addSideItemToMenu("Chocolate Cake",0.0, "description");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertTrue(mc.containsItem(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddTopping() {
		int initialSize=0;
		try {
			initialSize = mc.getToppings().size();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addToppingToMenu("Bacon",6.00);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int resultSize=0;
		try {
			resultSize = mc.getToppings().size();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(resultSize == (initialSize + 1));
		try {
			assertTrue(mc.containsItem(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddToppingEmptyName() {
		UUID itemId=null;
		try {
			itemId = mc.addToppingToMenu("",6.00);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(itemId==null);
	}
	
	@Test(expected = Exception.class)
	public void testAddToppingNullName() {
		String a=null;
		try {
			UUID itemId = mc.addToppingToMenu(a,6.00);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddToppingLargeName() {
		UUID itemId=null;
		try {
			itemId = mc.addToppingToMenu(bigString,6.00);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(itemId==null);
	}
	
	@Test
	public void testAddToppingZeroPrice() {
		UUID itemId=null;
		try {
			itemId = mc.addToppingToMenu("Bacon",0.0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertTrue(mc.containsItem(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddItemEmptyDescription() {
		UUID itemId=null;
		try {
			itemId = mc.addSideItemToMenu("Cheesecake",6.00, "");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertTrue(mc.containsItem(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddItemLargeDescription() {
		UUID itemId=null;
		try {
			itemId = mc.addSideItemToMenu("Cheesecake",6.00, bigString);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(itemId==null);
	}
	
	@Test
	public void testAddItemNullDescription() {
		try {
			UUID itemId = mc.addSideItemToMenu("Churro",0.0, "");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddPizza() {
		int initialSize=0;
		try {
			initialSize = mc.getPizzas().size();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int resultSize=0;
		try {
			resultSize = mc.getPizzas().size();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(resultSize == (initialSize + 1));
		try {
			assertTrue(mc.getItemDescription(itemId)=="description");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertTrue(mc.containsItem(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddPizzaEmptyName() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("",6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(itemId==null);
	}
	
	@Test(expected = Exception.class)
	public void testAddPizzaNullName() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String a=null;
		try {
			UUID itemId = mc.addPizzaToMenu(a,6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddPizzaLargeName() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu(bigString,6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(itemId==null);
	}
	
	@Test
	public void testAddPizzaZeroPrice() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("Thin Crust",0.0, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertTrue(mc.containsItem(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddPizzaEmptyDescription() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("Hawiian",6.00, "",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertTrue(mc.containsItem(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddPizzaLargeDescription() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("big",6.00, bigString, top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(itemId==null);
	}
	
	@Test
	public void testAddPizzaNullDescription() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			UUID itemId = mc.addPizzaToMenu("Thin Crust",0.0, "",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveItem() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result=false;
		try {
			result = mc.removePizza(itemId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(result);
	}
	
	@Test
	public void testRemoveInvalidItem() {
		UUID a = UUID.randomUUID();
		boolean result=true;
		try {
			result = mc.removePizza(a);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
	}
	
	@Test
	public void testRemoveNullItem() {
		boolean result=true;
		try {
			result = mc.removePizza(null);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
	}
	
	
	
	@Test
	public void testModifyItemName() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result=false;
		try {
			result = mc.modifyItemName(itemId,"Chicago Style Pizza");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		assertTrue(result);
		try {
			assertEquals("Chicago Style Pizza", mc.getItemName(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModifyItemNameEmpty() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result=true;
		try {
			result = mc.modifyItemName(itemId,"");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
		try {
			assertEquals("Deep Dish Pizza", mc.getItemName(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(expected = Exception.class)
	public void testModifyItemNameNull() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result=true;
		try {
			result = mc.modifyItemName(itemId,null);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
		try {
			assertEquals("Deep Dish Pizza", mc.getItemName(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModifyItemNameBig() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result=true;
		try {
			result = mc.modifyItemName(itemId,bigString);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
		try {
			assertEquals("Deep Dish Pizza", mc.getItemName(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModifyItemDescription() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result=false;
		try {
			result = mc.modifyItemDescription(itemId,"description2");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(result);
		try {
			assertEquals("description2", mc.getItemDescription(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModifyItemDescriptionEmpty() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result=true;
		try {
			result = mc.modifyItemName(itemId,"");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
		try {
			assertEquals("description", mc.getItemDescription(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(expected = Exception.class)
	public void testModifyItemDescriptionNull() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result=true;
		try {
			result = mc.modifyItemDescription(itemId,null);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
		try {
			assertEquals("description", mc.getItemDescription(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModifyItemDescriptionBig() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result=true;
		try {
			result = mc.modifyItemDescription(itemId,bigString);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
		try {
			assertEquals("description", mc.getItemDescription(itemId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModifyItemPrice() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result=false;
		try {
			result = mc.modifyItemPrice(itemId,7.00);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(result);
		try {
			assertEquals(7.00, mc.getItemPrice(itemId),0.01);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModifyItemPriceZero() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result=false;
		try {
			result = mc.modifyItemPrice(itemId,0.00);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(result);
		try {
			assertEquals(0.00, mc.getItemPrice(itemId),0.01);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testGetPizzas() {
		List<ItemEntry> top = new ArrayList<ItemEntry>();
		try {
			top.add(mc.getToppings().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID itemId=null;
		try {
			itemId = mc.addPizzaToMenu("Deep Dish Pizza",6.00, "description",top);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<PizzaEntry> itemIdList=null;
		try {
			itemIdList = mc.getPizzas();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
