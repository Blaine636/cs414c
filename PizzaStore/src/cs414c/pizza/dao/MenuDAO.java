package cs414c.pizza.dao;

import cs414c.pizza.domain.*;
import java.sql.*;
import java.util.UUID;

public class MenuDAO {

	private Connection connection = null;

	private final String INSERT_PIZZA_QUERY = "insert into Pizza([PizzaID],[NAME],[BASEPRICE],[DESCRIPTION]) VALUES(?,?,?,?)";
	private final String INSERT_SIDEITEM_QUERY = "insert into SIDEITEM([SIDEITEMID],[NAME],[BASEPRICE],[DESCRIPTION]) VALUES(?,?,?,?)";
	private final String INSERT_TOPPING_QUERY = "insert into Topping([TOPPINGID],[NAME],[BASEPRICE]) VALUES(?,?,?)";
	private final String SELECT_ALL_QUERY = "Select * From Item";

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
					+ " [TOPPINGID] INTEGER NOT NULL)";
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

	public UUID addItemToDB(Pizza p) {
		try {
			PreparedStatement stmt = connection.prepareStatement(INSERT_PIZZA_QUERY);
			UUID id = UUID.randomUUID();
			stmt.setString(1, id.toString());
			stmt.setString(2, p.getName());
			stmt.setDouble(3, p.getBasePrice());
			stmt.setString(4, p.getDescription());
			if (stmt.executeUpdate() == 1)
				return id;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public UUID addItemToDB(SideItem s) {
		try {
			PreparedStatement stmt = connection.prepareStatement(INSERT_SIDEITEM_QUERY);
			UUID id = UUID.randomUUID();
			stmt.setString(1, id.toString());
			stmt.setString(2, s.getName());
			stmt.setDouble(3, s.getBasePrice());
			stmt.setString(4, s.getDescription());
			if (stmt.executeUpdate() == 1)
				return id;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public UUID addItemToDB(Topping t) {
		try {
			PreparedStatement stmt = connection.prepareStatement(INSERT_TOPPING_QUERY);
			UUID id = UUID.randomUUID();
			stmt.setString(1, id.toString());
			stmt.setString(2, t.getName());
			stmt.setDouble(3, t.getBasePrice());
			if (stmt.executeUpdate() == 1)
				return id;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	//return of Z is error case
//	private char getItemType(Item i){
//		if(i instanceof Pizza)
//			return 'P';
//		if(i instanceof SideItem)
//			return 'S';
//		if(i instanceof Topping)
//			return 'T';
//		return 'Z';
//	}
//	
//	public List<Item> readAllItems(){
//		try{
//			Item
//			PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_QUERY);
//			ResultSet rs = stmt.executeQuery();
//			while(rs.next()){
//				rs.getInt(1);
//			}
//		}
//	}
	
	public static void main(String args[]){
		MenuDAO temp = new MenuDAO();
		temp.dropAndRecreateTables();
//		temp.generateTables();
		Pizza p = new Pizza("JorshenstienPizza",6.99,"asoiefj");
		SideItem s = new SideItem("SideItem1",2.99,"some stuff");
		Topping t = new Topping("Topping1", 2.99);
		System.out.println(temp.addItemToDB(p));
		System.out.println(temp.addItemToDB(s));
		System.out.println(temp.addItemToDB(t));
	}
}