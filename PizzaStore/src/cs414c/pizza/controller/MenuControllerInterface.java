package cs414c.pizza.controller;

import java.rmi.Remote;
import java.util.List;
import java.util.UUID;
import cs414c.pizza.ui.ItemEntry;
import cs414c.pizza.ui.PizzaEntry;
import cs414c.pizza.ui.SizeEntry;

public interface MenuControllerInterface extends Remote {
	public UUID addSideItemToMenu(String name, double basePrice, String description);
	public UUID addToppingToMenu(String name, double price);
	public UUID addPizzaToMenu(String name, double basePrice, String description, List<ItemEntry> toppings);
	public boolean containsItem(UUID itemId);
	public boolean removePizza(UUID itemId);
	public boolean removeSide(UUID itemId);
	public boolean removeTopping(UUID itemId);
	public boolean modifyItemName(UUID itemId, String string);
	public boolean modifyItemDescription(UUID itemId, String string);
	public boolean modifyItemPrice(UUID itemId, double price);
	public String getItemName(UUID itemId);
	public double getItemPrice(UUID itemId) ;
	public String getItemDescription(UUID itemId);
	public List<PizzaEntry> getPizzas();
	public List<ItemEntry> getSides();
	public List<ItemEntry> getToppings();
	public List<SizeEntry> getSizes();
	public ItemEntry getItem(UUID itemId);
}
