package cs414c.pizza.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import cs414c.pizza.controller.MenuController;
import cs414c.pizza.domain.Item;
import cs414c.pizza.domain.Pizza;
import cs414c.pizza.domain.Topping;
import cs414c.pizza.util.OrderStatus;

public class MenuTest {
	
	private MenuController mc;
	private List<Topping> toppingList;
	private int orderId;
	private final int PIZZA_IDENTIFIER = 1;
	
	@Before
	public void setup() {
		mc = new MenuController();
		toppingList = new ArrayList<Topping>();
		toppingList.add(new Topping("pepperoni",0.99));
		toppingList.add(new Topping("sausage",0.75));
		orderId = mc.createOrder();
	}
	
	@Test
	public void testAddNormal() {
		UUID orderedItemUUID = mc.addItemToOrder(orderId,PIZZA_IDENTIFIER,toppingList);
		
		assertTrue(mc.contains(orderId,orderedItemUUID));
		assertTrue(mc.orderSize(orderId) == 1);
		assertNotNull(orderedItemUUID);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNullOrderId() {
		mc.addItemToOrder(0,PIZZA_IDENTIFIER,toppingList);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNullItemId() {
		mc.addItemToOrder(orderId,0,toppingList);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNullToppingList() {
		mc.addItemToOrder(orderId,PIZZA_IDENTIFIER,null);
	}
	
	@Test
	public void testEmptyToppingList() {
		UUID orderedItemId = mc.addItemToOrder(orderId,PIZZA_IDENTIFIER,new ArrayList<Topping>());
		assertEquals("Pizza with no toppings",mc.getItemDescription(orderId, orderedItemId));
	}
	
	@Test(expected = Exception.class)
	public void testAddInvalidTopping() {
		toppingList.add(new Topping("pepperoni",0.99));
		mc.addItemToOrder(orderId,PIZZA_IDENTIFIER,toppingList);
	}
	
	@Test(expected = Exception.class)
	public void testAddInvalidOrder() {
		mc.setStatus(1,OrderStatus.COMPLETED);
		mc.addItemToOrder(orderId,PIZZA_IDENTIFIER,toppingList);
	}
	
	@Test(expected = Exception.class)
	public void testAddInvalidItem() {
		mc.addItemToOrder(orderId,PIZZA_IDENTIFIER,toppingList);
		mc.addItemToOrder(orderId,PIZZA_IDENTIFIER,toppingList);
	}
	
	@Test
	public void testPlaceNormal() {
		mc.addItemToOrder(orderId,PIZZA_IDENTIFIER,toppingList);
		mc.placeOrder(orderId);
		assertEquals(OrderStatus.PLACED,mc.getStatus());
		assertTrue(mc.orderSize(orderId) == 1);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPlaceInvalid() {
		mc.placeOrder(2);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPlaceInvalid2() {
		mc.setStatus(orderId, OrderStatus.COMPLETED);
		mc.placeOrder(orderId);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPlaceNull() {
		mc.placeOrder(0);
	}
	
	@Test(expected = Exception.class)
	public void testPlaceEmpty() {
		mc.placeOrder(orderId);
	}
	
	@Test
	public void testGetPlacedNoOrders() {
		List<Integer> list = mc.getPlacedOrders();
		assertEquals(new ArrayList<Integer>(),list);
	}
	
	@Test
	public void testGetPlacedNormal() {
		mc.addItemToOrder(orderId,PIZZA_IDENTIFIER,toppingList);
		mc.placeOrder(orderId);
		
		int order2Id = mc.createOrder();
		mc.addItemToOrder(order2Id, PIZZA_IDENTIFIER, toppingList);
		mc.placeOrder(order2Id);
		
		int order3Id = mc.createOrder();
		mc.addItemToOrder(order3Id, PIZZA_IDENTIFIER, toppingList);
		mc.setStatus(order3Id, OrderStatus.COMPLETED);
		
		List<Integer> list = mc.getPlacedOrders();
		assertEquals(2,list.size());
	}

}
