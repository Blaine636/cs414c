package cs414c.pizza.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cs414c.pizza.controller.MenuController;
import cs414c.pizza.domain.Item;
import cs414c.pizza.domain.Pizza;
import cs414c.pizza.domain.Topping;
import cs414c.pizza.util.OrderStatus;

public class MenuTest {
	
	MenuController mc;
	List<Topping> toppingList;
	
	@Before
	public void setup() {
		mc = new MenuController();
		toppingList = new ArrayList<Topping>();
		toppingList.add(new Topping("pepperoni",0.99));
		toppingList.add(new Topping("sausage",0.75));
		mc.createOrder();
	}
	
	@Test
	public void testAddNormal() {
		mc.addItemToOrder(1,1,toppingList);
		
		assertTrue(mc.contains(1));
		assertTrue(mc.orderSize() == 1);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNullOrderId() {
		mc.addItemToOrder(0,1,toppingList);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNullItemId() {
		mc.addItemToOrder(1,0,toppingList);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNullToppingList() {
		mc.addItemToOrder(1,1,null);
	}
	
	@Test
	public void testEmptyToppingList() {
		mc.addItemToOrder(1,1,new ArrayList<Topping>());
		Item empty = mc.getItem(1);
		assertEquals(empty,new Pizza());
	}
	
	@Test(expected = Exception.class)
	public void testAddInvalidTopping() {
		toppingList.add(new Topping("pepperoni",0.99));
		mc.addItemToOrder(1,1,toppingList);
	}
	
	@Test(expected = Exception.class)
	public void testAddInvalidOrder() {
		mc.setStatus(1,OrderStatus.COMPLETED);
		mc.addItemToOrder(1,1,toppingList);
	}
	
	@Test(expected = Exception.class)
	public void testAddInvalidItem() {
		mc.addItemToOrder(1,1,toppingList);
		mc.addItemToOrder(1,1,toppingList);
	}
	
	@Test
	public void testPlaceNormal() {
		mc.addItemToOrder(1,1,toppingList);
		mc.placeOrder(1);
		assertEquals(OrderStatus.PLACED,mc.getStatus());
		assertTrue(mc.orderSize() == 1);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPlaceInvalid() {
		mc.placeOrder(2);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPlaceInvalid2() {
		mc.setStatus(1, OrderStatus.COMPLETED);
		mc.placeOrder(1);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPlaceNull() {
		mc.placeOrder(0);
	}
	
	@Test(expected = Exception.class)
	public void testPlaceEmpty() {
		mc.placeOrder(1);
	}
	
	@Test
	public void testGetPlacedNoOrders() {
		List<Integer> list = mc.getPlacedOrders();
		assertEquals(new ArrayList<Integer>(),list);
	}
	
	@Test
	public void testGetPlacedNormal() {
		mc.addItemToOrder(1,1,toppingList);
		mc.placeOrder(1);
		
		mc.createOrder();
		mc.addItemToOrder(2, 2, toppingList);
		mc.placeOrder(2);
		
		mc.createOrder();
		mc.addItemToOrder(3, 3, toppingList);
		mc.setStatus(3, OrderStatus.COMPLETED);
		
		List<Integer> list = mc.getPlacedOrders();
		assertEquals(2,list.size());
	}

}
