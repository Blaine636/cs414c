package cs414c.pizza.controller;

import java.rmi.Remote;
import java.util.List;
import java.util.UUID;

import cs414c.pizza.domain.Order;
import cs414c.pizza.ui.ItemEntry;
import cs414c.pizza.ui.OrderPizzaEntry;
import cs414c.pizza.ui.OrderSideEntry;
import cs414c.pizza.ui.PizzaEntry;
import cs414c.pizza.ui.SizeEntry;
import cs414c.pizza.util.Enum.OrderStatus;

public interface OrderControllerInterface extends Remote{

	//add item to a created order, returns a unique identifer of the customized item
	UUID addItemToOrder(int orderId, ItemEntry item);

	//add pizza to order including name, toppings, size
	UUID addPizzaToOrder(int orderId, PizzaEntry pizza, List<ItemEntry> toppings, SizeEntry size);

	//returns the size of a given order
	int orderSize(int orderId);

	//returns whether or not a given order contains a certain customized item 
	boolean contains(int orderId, UUID orderedItemUUID);

	//returns an English description of an ordered item
	String getItemDescription(int orderId, UUID orderedItemId);

	//precondition: order is created
	//finalizes the items in an order so it can be passed to chef ui
	void placeOrder(int orderId);

	//returns current status of order
	OrderStatus getStatus(int orderId);

	//creates a new order in the system and returns an identifier to access the order
	int createOrder(String customerName);

	//returns the identifiers of all placed orders
	List<Integer> getPlacedOrders();

	String getOrderDescription(int orderId);

	OrderPizzaEntry getOrderItem(int orderId, UUID orderItemId);

	OrderSideEntry getOrderSide(int orderId, UUID orderItemId);

	double getOrderTotal(int orderId);

	String getOrderTotalString(int orderId);

	boolean completeOrder(int orderId);

	boolean removeItemFromOrder(int orderId, UUID orderItemId);

	Order getOrder(int orderId);

}