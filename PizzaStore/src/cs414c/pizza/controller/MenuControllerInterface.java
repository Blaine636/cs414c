package cs414c.pizza.controller;

import java.rmi.Remote;
import java.util.List;
import java.util.UUID;

import cs414c.pizza.ui.ItemEntry;
import cs414c.pizza.ui.PizzaEntry;
import cs414c.pizza.ui.SizeEntry;

public interface MenuControllerInterface extends Remote {

	UUID addSideItemToMenu(String name, double basePrice, String description);

	UUID addToppingToMenu(String name, double price);

	UUID addPizzaToMenu(String name, double basePrice, String description, List<ItemEntry> toppings);

	boolean containsItem(UUID itemId);

	boolean removePizza(UUID itemId);

	boolean removeSide(UUID itemId);

	boolean removeTopping(UUID itemId);

	boolean modifyItemName(UUID itemId, String string);

	boolean modifyItemDescription(UUID itemId, String string);

	boolean modifyItemPrice(UUID itemId, double price);

	String getItemName(UUID itemId);

	double getItemPrice(UUID itemId);

	String getItemDescription(UUID itemId);

	List<PizzaEntry> getPizzas();

	List<ItemEntry> getSides();

	List<ItemEntry> getToppings();

	List<SizeEntry> getSizes();

	ItemEntry getItem(UUID itemId);

}