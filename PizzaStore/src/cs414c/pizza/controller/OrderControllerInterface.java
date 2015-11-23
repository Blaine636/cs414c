package cs414c.pizza.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

import cs414c.pizza.domain.Order;
import cs414c.pizza.ui.ItemEntry;
import cs414c.pizza.ui.OrderEntry;
import cs414c.pizza.ui.OrderItemEntry;
import cs414c.pizza.ui.OrderPizzaEntry;
import cs414c.pizza.ui.OrderSideEntry;
import cs414c.pizza.ui.PizzaEntry;
import cs414c.pizza.ui.SizeEntry;
import cs414c.pizza.util.Enum.OrderStatus;

public interface OrderControllerInterface extends Remote{

	//add item to a created order, returns a unique identifer of the customized item
	UUID addItemToOrder(int orderId, ItemEntry item) throws RemoteException;

	//add pizza to order including name, toppings, size
	UUID addPizzaToOrder(int orderId, PizzaEntry pizza, List<ItemEntry> toppings, SizeEntry size) throws RemoteException;

	//returns the size of a given order
	int orderSize(int orderId) throws RemoteException;

	//returns whether or not a given order contains a certain customized item 
	boolean contains(int orderId, UUID orderedItemUUID) throws RemoteException;

	//returns an English description of an ordered item
	String getItemDescription(int orderId, UUID orderedItemId) throws RemoteException;

	//precondition: order is created
	//finalizes the items in an order so it can be passed to chef ui
	void placeOrder(int orderId) throws RemoteException;

	//returns current status of order
	OrderStatus getStatus(int orderId) throws RemoteException;

	//creates a new order in the system and returns an identifier to access the order
	int createOrder(String customerName) throws RemoteException;

	//returns the identifiers of all placed orders
	List<Integer> getPlacedOrders() throws RemoteException;
	
	List<Integer> getOrders() throws RemoteException;

	String getOrderDescription(int orderId) throws RemoteException;

	OrderPizzaEntry getOrderPizza(int orderId, UUID orderItemId) throws RemoteException;

	OrderSideEntry getOrderSide(int orderId, UUID orderItemId) throws RemoteException;

	double getOrderTotal(int orderId) throws RemoteException;

	String getOrderTotalString(int orderId) throws RemoteException;

	boolean completeOrder(int orderId) throws RemoteException;
	
	void setOrderStatus(int orderId, OrderStatus os) throws RemoteException;

	boolean removeItemFromOrder(int orderId, UUID orderItemId) throws RemoteException;

	OrderEntry getFullOrder(int orderId) throws RemoteException;

	int createDeliveryOrder(String customerName, String address, String phoneNumber) throws RemoteException;
	
	Order getOrder(int orderId) throws RemoteException;

}