package cs414c.pizza.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import cs414c.pizza.controller.MenuController;
import cs414c.pizza.controller.OrderController;
import cs414c.pizza.dao.MenuDAO;
import cs414c.pizza.domain.Item;
import cs414c.pizza.domain.Order;
import cs414c.pizza.domain.Pizza;
import cs414c.pizza.domain.Topping;
import cs414c.pizza.ui.ItemEntry;
import cs414c.pizza.util.Enum.OrderStatus;

public class OrderControllerTest {
	private OrderController oc;
	private List<Topping> toppingList;
	private int orderId;
	private final int PIZZA_IDENTIFIER = 1;
	MenuController mc;
	ItemEntry pizza;
	
	@Before
	public void setup() {
		MenuDAO menuDAO = new MenuDAO();
		oc = new OrderController(menuDAO);
		toppingList = new ArrayList<Topping>();
		toppingList.add(new Topping("pepperoni",0.99));
		toppingList.add(new Topping("sausage",0.75));
		mc = new MenuController(menuDAO);
		orderId = oc.createOrder("Josh");
		pizza=new ItemEntry("Piz", 6.99,UUID.randomUUID());
		Order order = oc.getOrder(orderId);
		order.addItem(new Pizza("pizza1",6.99,"pizza1Desc"));
	}
	
	@Test
	public void testAddNormal() {
		UUID orderedItemUUID = oc.addItemToOrder(orderId,mc.getPizzas().get(0));
		
		assertTrue(oc.contains(orderId,orderedItemUUID));
		assertNotNull(orderedItemUUID);
	}
	
	
	@Test(expected = NullPointerException.class)
	public void testAddNullItemId() {
		oc.addItemToOrder(orderId,null);
	}
	
	
	@Test
	public void testAddDuplicateItem() {
		UUID item1 = oc.addItemToOrder(orderId,mc.getPizzas().get(0));
		UUID item2 = oc.addItemToOrder(orderId,mc.getPizzas().get(0));
		
		assertFalse(item1.equals(item2));
	}
	
	@Test
	public void testAddReturnValue() {
		UUID item1 = oc.addItemToOrder(orderId,mc.getPizzas().get(0));
		assertNotNull(item1);
	}
	
	
	
	@Test
	public void testPlaceNormal() {
		ItemEntry a= new ItemEntry("Pep", 4.99, UUID.randomUUID());
		oc.addItemToOrder(orderId,a);
		oc.placeOrder(orderId);
		assertEquals(OrderStatus.PLACED,oc.getStatus(orderId));
	}
	
	@Test(expected = NullPointerException.class)
	public void testPlaceInvalid() {
		oc.placeOrder(2);
	}

	
	@Test
	public void testGetPlacedNoOrders() {
		List<Integer> list = oc.getPlacedOrders();
		assertEquals(new ArrayList<Integer>(),list);
	}
	
	
	@Test
	public void testGetOrderDescriptionNull() {
		oc.getOrderDescription(0);
	}
	
	@Test
	public void testGetOrderDescriptionInvalid() {
		oc.getOrderDescription(5);
	}
	
	
	@Test
	public void testGetOrderTotalInvalid() {
		oc.getOrderDescription(5);
	}
	

	
	@Test
	public void testCompleteOrder() {
		oc.addItemToOrder(orderId,mc.getPizzas().get(0));
		oc.placeOrder(orderId);
		boolean result = oc.completeOrder(orderId);
		
		assertTrue(result);
		assertEquals(OrderStatus.COMPLETED,oc.getStatus(orderId));
	}
	
	
	@Test
	public void testCompleteOrderNull() {
		oc.completeOrder(0);
	}
	
	@Test
	public void testCompleteOrderInvalidStatus() {
		boolean result = oc.completeOrder(orderId);
		assertFalse(result);
	}
	

	
	@Test
	public void testRemoveInvalidItem() {
		boolean result = oc.removeItemFromOrder(orderId,UUID.randomUUID());
		
		assertFalse(result);
	}
	
	
	@Test
	public void testCreateOrder() {
		int result = oc.createOrder("Josh");
		assertFalse(result == 0);
	}
	
	@Test
	public void testCreateOrderDuplicate() {
		//not a very thorough test
		int order1 = oc.createOrder("Josh");
		int order2 = oc.createOrder("Josh");
		assertFalse(order1 == order2);
	}
	
	

	
	@Test(expected = NullPointerException.class)
	public void testSizeInvalidOrder() {
		oc.orderSize(5);
	}
}
