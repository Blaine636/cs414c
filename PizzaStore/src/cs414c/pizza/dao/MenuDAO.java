package cs414c.pizza.dao;

import cs414c.pizza.domain.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class MenuDAO {

	private Connection connection = null;

	private final String INSERT_PIZZA_QUERY = "insert into Pizza([PizzaID],[NAME],[BASEPRICE],[DESCRIPTION]) VALUES(?,?,?,?)";
	private final String INSERT_SIDEITEM_QUERY = "insert into SIDEITEM([SIDEITEMID],[NAME],[BASEPRICE],[DESCRIPTION]) VALUES(?,?,?,?)";
	private final String INSERT_TOPPING_QUERY = "insert into Topping([TOPPINGID],[NAME],[BASEPRICE]) VALUES(?,?,?)";
	private final String MAP_TOPPING_QUERY = "Insert into PizzaToppingMap([PIZZAID],[TOPPINGID]) VALUES(?,?)";
	private final String SELECT_PIZZAS_QUERY = "Select * From Pizza";
	private final String SELECT_SIDEITEMS_QUERY = "Select * From SideItem";
	private final String SELECT_ALL_TOPPINGS_QUERY = "Select * From Topping";
	private final String SELECT_PIZZA_TOPPING_QUERY = "Select TOPPINGID From PizzaToppingMap Where PIZZAID = ?";
	private final String SELECT_TOPPING_QUERY = "Select * From Topping Where ToppingID = ?";

	public MenuDAO() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			System.out.println("Opened database successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateTables() {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String sql = "CREATE TABLE SIDEITEM ([SIDEITEMID] GUID PRIMARY KEY NOT NULL,"
					+ " [NAME]           	nvarchar(64)    NOT NULL UNIQUE, "
					+ " [BASEPRICE]         decimal(5,2) 	NOT NULL, "
					+ " [DESCRIPTION]		nvarchar(256) 	NOT NULL);"
					+ " CREATE TABLE PIZZA ([PIZZAID] GUID PRIMARY KEY NOT NULL,"
					+ " [NAME]           	nvarchar(64)    NOT NULL UNIQUE, "
					+ " [BASEPRICE]         decimal(5,2) 	NOT NULL, "
					+ " [DESCRIPTION]		nvarchar(256) 	NOT NULL);"
					+ " CREATE TABLE TOPPING ([TOPPINGID] GUID PRIMARY KEY NOT NULL,"
					+ " [NAME]           	nvarchar(64)    NOT NULL UNIQUE, "
					+ " [BASEPRICE]         decimal(5,2) 	NOT NULL);"
					+ " CREATE TABLE PIZZATOPPINGMAP ([PIZZAID] GUID NOT NULL,"
					+ " [TOPPINGID] INTEGER NOT NULL,"
					+ " FOREIGN KEY (PIZZAID) REFERENCES PIZZA(PIZZAID),"
					+ " FOREIGN KEY (TOPPINGID) REFERENCES TOPPING(TOPPINGID))";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void dropAndRecreateTables() {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String sql = "Drop Table Pizza;"
					+ "Drop Table SideItem;"
					+ "Drop Table Topping;"
					+ "Drop Table PizzaToppingMap";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		generateTables();
	}

	public boolean addItemToDB(Pizza p) {
		try {
			PreparedStatement stmt = connection.prepareStatement(INSERT_PIZZA_QUERY);
			stmt.setString(1, p.getItemId().toString());
			stmt.setString(2, p.getName());
			stmt.setDouble(3, p.getBasePrice());
			stmt.setString(4, p.getDescription());
			if (stmt.executeUpdate() != 1)
				return false;
			//map toppings
			stmt = connection.prepareStatement(MAP_TOPPING_QUERY);
			Iterator<Topping> it = p.getToppings().iterator();
			while(it.hasNext()){
				stmt.setString(1, p.getItemId().toString());
				stmt.setString(2, it.next().getItemId().toString());
				if (stmt.executeUpdate() != 1)
					return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean addItemToDB(SideItem s) {
		try {
			PreparedStatement stmt = connection.prepareStatement(INSERT_SIDEITEM_QUERY);
			stmt.setString(1, s.getItemId().toString());
			stmt.setString(2, s.getName());
			stmt.setDouble(3, s.getBasePrice());
			stmt.setString(4, s.getDescription());
			if (stmt.executeUpdate() == 1)
				return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean addItemToDB(Topping t) {
		try {
			PreparedStatement stmt = connection.prepareStatement(INSERT_TOPPING_QUERY);
			stmt.setString(1, t.getItemId().toString());
			stmt.setString(2, t.getName());
			stmt.setDouble(3, t.getBasePrice());
			if (stmt.executeUpdate() == 1)
				return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public Map<UUID,Item> pullAllItems(){
		Map<UUID,Item> map = new HashMap<UUID,Item>();
		try{
			//TOPPING
			PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_TOPPINGS_QUERY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				map.put(UUID.fromString(rs.getString(1)), new Topping(UUID.fromString(rs.getString(1)),rs.getString(2),rs.getDouble(3)));
			}
			//PIZZA
			stmt = connection.prepareStatement(SELECT_PIZZAS_QUERY);
			rs = stmt.executeQuery();
			while(rs.next()){
				Pizza p = new Pizza(UUID.fromString(rs.getString(1)),rs.getString(2),rs.getDouble(3),rs.getString(4));

				//populate list of toppings related to the pizza in question
				ArrayList<Topping> toppingList = new ArrayList<Topping>();
				stmt = connection.prepareStatement(SELECT_PIZZA_TOPPING_QUERY);
				stmt.setString(1, p.getItemId().toString());
				ResultSet rsPizzaToppings = stmt.executeQuery();
				while(rsPizzaToppings.next()){
					//get topping object from topping table
					PreparedStatement specificToppingStmt = connection.prepareStatement(SELECT_TOPPING_QUERY);
					specificToppingStmt.setString(1, rsPizzaToppings.getString(1));
					ResultSet buildToppingObjectRS = specificToppingStmt.executeQuery();
					Topping t = new Topping(UUID.fromString(buildToppingObjectRS.getString(1)),buildToppingObjectRS.getString(2),buildToppingObjectRS.getDouble(3));
					toppingList.add(t);
				}
				p.addToppings(toppingList);
				map.put(p.getItemId(),p);
			}
			//SIDEITEM
			stmt = connection.prepareStatement(SELECT_SIDEITEMS_QUERY);
			rs = stmt.executeQuery();
			while(rs.next()){
				map.put(UUID.fromString(rs.getString(1)), new SideItem(rs.getString(2),rs.getDouble(3),rs.getString(4)));
			}
		}catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return map;
	}
	
	public static void main(String args[]){
		MenuDAO temp = new MenuDAO();
//		temp.generateTables();
		temp.dropAndRecreateTables();
		Pizza p = new Pizza("Hawaiian",6.99,"Taste Of Honolulu");
		Topping t1 = new Topping("Bacon", 1.99);
		Topping t2 = new Topping("Back Olives", 0.99);
		ArrayList<Topping> tList = new ArrayList<Topping>();
		tList.add(t1);
		tList.add(t2);
		p.addToppings(tList);
		SideItem s = new SideItem("Ranch",0.99,"One person cup of dipping ranch");
		temp.addItemToDB(p);
		temp.addItemToDB(s);
		temp.addItemToDB(t1);
		temp.addItemToDB(t2);
//		Map<UUID,Item> map = temp.pullAllItems();
//		System.out.println(map.get(p.getItemId()).getName());
	}
}