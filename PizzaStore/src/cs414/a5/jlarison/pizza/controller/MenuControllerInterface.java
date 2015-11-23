package cs414.a5.jlarison.pizza.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

import cs414.a5.jlarison.pizza.ui.ItemEntry;
import cs414.a5.jlarison.pizza.ui.PizzaEntry;
import cs414.a5.jlarison.pizza.ui.SizeEntry;

public interface MenuControllerInterface extends Remote {

	UUID addSideItemToMenu(String name, double basePrice, String description) throws RemoteException;

	UUID addToppingToMenu(String name, double price) throws RemoteException;

	UUID addPizzaToMenu(String name, double basePrice, String description, List<ItemEntry> toppings) throws RemoteException;
	
	boolean applyDiscount(UUID itemId, int discountPercent) throws RemoteException; 

	boolean containsItem(UUID itemId) throws RemoteException;

	boolean removePizza(UUID itemId) throws RemoteException;

	boolean removeSide(UUID itemId) throws RemoteException;

	boolean removeTopping(UUID itemId) throws RemoteException;

	boolean modifyItemName(UUID itemId, String string) throws RemoteException;

	boolean modifyItemDescription(UUID itemId, String string) throws RemoteException;

	boolean modifyItemPrice(UUID itemId, double price) throws RemoteException;

	String getItemName(UUID itemId) throws RemoteException;

	double getItemPrice(UUID itemId) throws RemoteException;

	String getItemDescription(UUID itemId) throws RemoteException;

	List<PizzaEntry> getPizzas() throws RemoteException;

	List<ItemEntry> getSides() throws RemoteException;

	List<ItemEntry> getToppings() throws RemoteException;

	List<SizeEntry> getSizes() throws RemoteException;

	ItemEntry getItem(UUID itemId) throws RemoteException;

}