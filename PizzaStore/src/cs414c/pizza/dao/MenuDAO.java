package cs414c.pizza.dao;

import cs414c.pizza.domain.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class MenuDAO {

	private final String INSERT_PIZZA_QUERY = "insert into Pizza([PizzaID],[NAME],[BASEPRICE],[DESCRIPTION]) VALUES(?,?,?,?)";
	private final String INSERT_SIDEITEM_QUERY = "insert into SIDEITEM([SIDEITEMID],[NAME],[BASEPRICE],[DESCRIPTION]) VALUES(?,?,?,?)";
	private final String INSERT_TOPPING_QUERY = "insert into Topping([TOPPINGID],[NAME],[BASEPRICE]) VALUES(?,?,?)";
	private final String MAP_TOPPING_QUERY = "Insert into PizzaToppingMap([PIZZAID],[TOPPINGID]) VALUES(?,?)";
	private final String SELECT_PIZZAS_QUERY = "select pizzaid,name,baseprice,description,discount_percent from pizza "
		+ " left join discount on pizza.PIZZAID = discount.ITEMID";
	private final String SELECT_SIDEITEMS_QUERY = "select sideitemid, name, baseprice, description, discount_percent from sideitem "
		+ " left join discount on sideitemid = itemid";
	private final String SELECT_ALL_TOPPINGS_QUERY = "select toppingid, name, baseprice, discount_percent from topping "
		+ "left join discount on toppingid = itemid";
	private final String SELECT_PIZZA_TOPPING_QUERY = "Select TOPPINGID From PizzaToppingMap Where PIZZAID = ?";
	private final String SELECT_TOPPING_QUERY = "Select * From Topping Where ToppingID = ?";
	private final String REMOVE_PIZZA_QUERY = "delete from pizza where pizzaid = ?";
	private final String REMOVE_PIZZA_MAP_QUERY = "delete from pizzatoppingmap where pizzaid = ?";
	private final String REMOVE_TOPPING_QUERY = "delete from topping where toppingid = ?";
	private final String REMOVE_SIDE_QUERY = "delete from sideitem where sideitemid = ?";
	private final String SELECT_MAP_QUERY = "select pm.toppingid,t.NAME,t.BASEPRICE from pizzatoppingmap pm"
+ " inner join topping t on pm.TOPPINGID = t.TOPPINGID where pm.pizzaid = ?";
	
	

	public MenuDAO() {
	}

	public void generateTables() {
		System.out.println("Generating menu tables");
		Statement stmt = null;
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
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
					+ " CREATE TABLE PIZZATOPPINGMAP ([PIZZAID] GUID NOT NULL," + " [TOPPINGID] INTEGER NOT NULL,"
					+ " FOREIGN KEY (PIZZAID) REFERENCES PIZZA(PIZZAID),"
					+ " FOREIGN KEY (TOPPINGID) REFERENCES TOPPING(TOPPINGID))";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void dropAndRecreateTables() {
		System.out.println("Drop/recreate menu tables");
		Statement stmt = null;
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			stmt = connection.createStatement();
			String sql = "Drop Table Pizza;" + "Drop Table SideItem;" + "Drop Table Topping;"
					+ "Drop Table PizzaToppingMap";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		generateTables();
	}

	public boolean addItemToDB(Pizza p) {
		System.out.println("Adding item to menu db");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			PreparedStatement stmt = connection.prepareStatement(INSERT_PIZZA_QUERY);
			stmt.setString(1, p.getItemId().toString());
			stmt.setString(2, p.getName());
			stmt.setDouble(3, p.getBasePrice());
			stmt.setString(4, p.getDescription());
			if (stmt.executeUpdate() != 1)
				return false;
			stmt.close();
			// map toppings
			PreparedStatement stmt2 = connection.prepareStatement(MAP_TOPPING_QUERY);
			Iterator<Topping> it = p.getToppings().iterator();
			while (it.hasNext()) {
				stmt2.setString(1, p.getItemId().toString());
				stmt2.setString(2, it.next().getItemId().toString());
				if (stmt2.executeUpdate() != 1)
					return false;
				stmt2.close();
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean addItemToDB(SideItem s) {
		System.out.println("Adding item to menu db");
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
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
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			PreparedStatement stmt = connection.prepareStatement(INSERT_TOPPING_QUERY);
			stmt.setString(1, t.getItemId().toString());
			stmt.setString(2, t.getName());
			stmt.setDouble(3, t.getBasePrice());
			if (stmt.executeUpdate() == 1)
				return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public Map<UUID, Item> pullAllItems() {
		Map<UUID, Item> map = new HashMap<UUID, Item>();
		Connection connection = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		PreparedStatement stmtPizzaToppings = null;
		ResultSet rsPizzaToppings = null;
		try {
			// TOPPING
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			stmt = connection.prepareStatement(SELECT_ALL_TOPPINGS_QUERY);
			rs = stmt.executeQuery();
			while (rs.next()) {
				map.put(UUID.fromString(rs.getString(1)),
						new Topping(UUID.fromString(rs.getString(1)), rs.getString(2), rs.getDouble(3)));
			}
			// PIZZA
			stmt2 = connection.prepareStatement(SELECT_PIZZAS_QUERY);
			rs2 = stmt2.executeQuery();
			while (rs2.next()) {
				Pizza p = new Pizza(UUID.fromString(rs2.getString(1)), rs2.getString(2), rs2.getDouble(3),
						"");
				p.setDiscount(rs2.getInt(5));
				
				// populate list of toppings related to the pizza in question
				ArrayList<Topping> toppingList = new ArrayList<Topping>();
				stmtPizzaToppings = connection.prepareStatement(SELECT_MAP_QUERY);
				stmtPizzaToppings.setString(1, p.getItemId().toString());
				rsPizzaToppings = stmtPizzaToppings.executeQuery();
				while (rsPizzaToppings.next()) {
					// get topping object from topping table
					toppingList.add(new Topping(UUID.fromString(rsPizzaToppings.getString(1)),rsPizzaToppings.getString(2),rsPizzaToppings.getDouble(3)));
				}
				
				p.addToppings(toppingList);
				map.put(p.getItemId(), p);
			}
			// SIDEITEM
			stmt3 = connection.prepareStatement(SELECT_SIDEITEMS_QUERY);
			rs3 = stmt3.executeQuery();
			while (rs3.next()) {
				map.put(UUID.fromString(rs3.getString(1)), new SideItem(UUID.fromString(rs3.getString(1)),
						rs3.getString(2), rs3.getDouble(3), rs3.getString(4)));
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {
				stmt.close();
				stmt2.close();
				stmt3.close();
				stmtPizzaToppings.close();
				rs.close();
				rs2.close();
				rs3.close();
				rsPizzaToppings.close();
				connection.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}
	
	public boolean removePizza(UUID itemId) {
		Connection connection = null;
		PreparedStatement removePizzaStmt = null;
		PreparedStatement removeMapStmt = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			removePizzaStmt = connection.prepareStatement(REMOVE_PIZZA_QUERY);
			removePizzaStmt.setString(1, itemId.toString());
			removePizzaStmt.executeUpdate();
			removeMapStmt = connection.prepareStatement(REMOVE_PIZZA_MAP_QUERY);
			removeMapStmt.setString(1, itemId.toString());
			removeMapStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				removePizzaStmt.close();
				removeMapStmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean removeSide(UUID itemId) {
		Connection connection = null;
		PreparedStatement removeSideStmt = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			removeSideStmt = connection.prepareStatement(REMOVE_SIDE_QUERY);
			removeSideStmt.setString(1, itemId.toString());
			removeSideStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				removeSideStmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean removeTopping(UUID itemId) {
		Connection connection = null;
		PreparedStatement removeToppingStmt = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			removeToppingStmt = connection.prepareStatement(REMOVE_TOPPING_QUERY);
			removeToppingStmt.setString(1, itemId.toString());
			removeToppingStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				removeToppingStmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	public static void main(String args[]) {
		MenuDAO temp = new MenuDAO();
		temp.pullAllItems();
		temp.addItemToDB(new SideItem("Water",0.0,"cup of water"));
	}
}