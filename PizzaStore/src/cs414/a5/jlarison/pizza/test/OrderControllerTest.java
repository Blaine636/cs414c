package cs414.a5.jlarison.pizza.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import cs414.a5.jlarison.pizza.controller.MenuController;
import cs414.a5.jlarison.pizza.controller.MenuControllerInterface;
import cs414.a5.jlarison.pizza.controller.OrderController;
import cs414.a5.jlarison.pizza.controller.OrderControllerInterface;
import cs414.a5.jlarison.pizza.dao.MenuDAO;
import cs414.a5.jlarison.pizza.domain.Item;
import cs414.a5.jlarison.pizza.domain.Menu;
import cs414.a5.jlarison.pizza.domain.Order;
import cs414.a5.jlarison.pizza.domain.Pizza;
import cs414.a5.jlarison.pizza.domain.Topping;
import cs414.a5.jlarison.pizza.ui.ItemEntry;
import cs414.a5.jlarison.pizza.ui.OrderEntry;
import cs414.a5.jlarison.pizza.util.Enum.OrderStatus;

public class OrderControllerTest {
	private OrderControllerInterface oc;
	private List<Topping> toppingList;
	private int orderId;
	private final int PIZZA_IDENTIFIER = 1;
	MenuControllerInterface mc;
	ItemEntry pizza;
	
	@Before
	public void setup() throws RemoteException {
		MenuDAO menuDAO = new MenuDAO();
		Menu menu = new Menu(menuDAO);
		oc = new OrderController(menu);
		toppingList = new ArrayList<Topping>();
		toppingList.add(new Topping("pepperoni",0.99));
		toppingList.add(new Topping("sausage",0.75));
		mc = new MenuController(menu);
		orderId = oc.createOrder("Josh");
		pizza=new ItemEntry("Piz", 6.99,UUID.randomUUID(), 20.00);
		Order order = oc.getOrder(orderId);
		order.addItem(new Pizza("pizza1",6.99,"pizza1Desc"));
	}
	
	@Test
	public void testAddNormal() {
		UUID orderedItemUUID=null;
		try {
			orderedItemUUID = oc.addItemToOrder(orderId,mc.getPizzas().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertTrue(oc.contains(orderId,orderedItemUUID));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(orderedItemUUID);
	}
	
	
	@Test(expected = NullPointerException.class)
	public void testAddNullItemId() {
		try {
			oc.addItemToOrder(orderId,null);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testAddDuplicateItem() {
		UUID item1=null;
		try {
			item1 = oc.addItemToOrder(orderId,mc.getPizzas().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UUID item2=null;
		try {
			item2 = oc.addItemToOrder(orderId,mc.getPizzas().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(item1.equals(item2));
	}
	
	@Test
	public void testAddReturnValue() {
		UUID item1=null;
		try {
			item1 = oc.addItemToOrder(orderId,mc.getPizzas().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(item1);
	}
	
	
	
	@Test
	public void testPlaceNormal() {
		ItemEntry a= new ItemEntry("Pep", 4.99, UUID.randomUUID(), 10.00);
		try {
			oc.addItemToOrder(orderId,a);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oc.placeOrder(orderId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertEquals(OrderStatus.PLACED,oc.getStatus(orderId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void testPlaceInvalid() {
		try {
			oc.placeOrder(2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Test
	public void testGetPlacedNoOrders() {
		List<Integer> list=null;
		try {
			list = oc.getPlacedOrders();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(new ArrayList<Integer>(),list);
	}
	
	
	@Test
	public void testGetOrderDescriptionNull() {
		try {
			oc.getOrderDescription(0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetOrderDescriptionInvalid() {
		try {
			oc.getOrderDescription(5);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testGetOrderTotalInvalid() {
		try {
			oc.getOrderDescription(5);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	@Test
	public void testCompleteOrder() {
		try {
			oc.addItemToOrder(orderId,mc.getPizzas().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oc.placeOrder(orderId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result=false;
		try {
			result = oc.completeOrder(orderId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(result);
		try {
			assertEquals(OrderStatus.COMPLETED,oc.getStatus(orderId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testCompleteOrderNull() {
		try {
			oc.completeOrder(0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCompleteOrderInvalidStatus() {
		boolean result=true;
		try {
			result = oc.completeOrder(orderId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(result);
	}
	

	
	@Test
	public void testRemoveInvalidItem() {
		boolean result=true;
		try {
			result = oc.removeItemFromOrder(orderId,UUID.randomUUID());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(result);
	}
	
	
	@Test
	public void testCreateOrder() {
		int result=0;
		try {
			result = oc.createOrder("Josh");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(result == 0);
	}
	
	@Test
	public void testCreateOrderDuplicate() {
		//not a very thorough test
		int order1=0;
		try {
			order1 = oc.createOrder("Josh");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int order2=0;
		try {
			order2 = oc.createOrder("Josh");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(order1 == order2);
	}
	
	

	
	@Test(expected = NullPointerException.class)
	public void testSizeInvalidOrder() {
		try {
			oc.orderSize(5);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
