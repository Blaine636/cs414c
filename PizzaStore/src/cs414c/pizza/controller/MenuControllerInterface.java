package cs414c.pizza.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;
import cs414c.pizza.ui.ItemEntry;
import cs414c.pizza.ui.PizzaEntry;
import cs414c.pizza.ui.SizeEntry;

public interface MenuControllerInterface extends Remote {
	public UUID addSideItemToMenu(String name, double basePrice, String description) throws RemoteException;
	public UUID addToppingToMenu(String name, double price) throws RemoteException;
	public UUID addPizzaToMenu(String name, double basePrice, String description, List<ItemEntry> toppings) throws RemoteException;
	public boolean containsItem(UUID itemId) throws RemoteException;
	public boolean removePizza(UUID itemId) throws RemoteException;
	public boolean removeSide(UUID itemId) throws RemoteException;
	public boolean removeTopping(UUID itemId) throws RemoteException;
	public boolean modifyItemName(UUID itemId, String string) throws RemoteException;
	public boolean modifyItemDescription(UUID itemId, String string) throws RemoteException;
	public boolean modifyItemPrice(UUID itemId, double price) throws RemoteException;
	public String getItemName(UUID itemId) throws RemoteException;
	public double getItemPrice(UUID itemId) throws RemoteException;
	public String getItemDescription(UUID itemId) throws RemoteException;
	public List<PizzaEntry> getPizzas() throws RemoteException;
	public List<ItemEntry> getSides() throws RemoteException;
	public List<ItemEntry> getToppings() throws RemoteException;
	public List<SizeEntry> getSizes() throws RemoteException;
	public ItemEntry getItem(UUID itemId) throws RemoteException;
}
