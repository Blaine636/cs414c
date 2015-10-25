package cs414c.pizza.dao;

import cs414c.pizza.domain.*;
import java.sql.*;
import java.util.*;

public class MenuDAO {

	private Connection connection = null;

	// private final String GET_PASSWORD_QUERY = "select password from login
	// where username = ?";
	private final String INSERT_ITEM_QUERY = "insert into item([ID],[NAME],[BASEPRICE],[DESCRIPTION]) VALUES(?,?,?,?)";

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

	public void generateItemTable() {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String sql = "CREATE TABLE ITEM " + "([ID] GUID PRIMARY KEY NOT NULL,"
					+ " [NAME]           	nvarchar(64)    NOT NULL, "
					+ " [BASEPRICE]         decimal(5,2) 	NOT NULL, "
					+ " [DESCRIPTION]		nvarchar(256) 	NOT NULL," + "UNIQUE ([NAME]))";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void dropAndRecreateItemTable() {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String sql = "Drop Table Item;";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		generateItemTable();
	}

//	public void writeMenuToDB(Menu m) {
//		Iterator<Item> it = m.ItemSet.iterator();
//		Item i = null;
//		while (it.hasNext()) {
//
//			try {
//				i = it.next();
//				PreparedStatement stmt = connection.prepareStatement(INSERT_ITEM_QUERY);
//				stmt.setString(1, UUID.randomUUID().toString());
//				stmt.setString(2, i.getName());
//				stmt.setString(3, "" + i.getBasePrice());
//				stmt.setString(4, i.getDescription());
//				if (stmt.executeUpdate() != 1)
//					return false;
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//				return false;
//			}
//			return true;
//
//		}
//	}

	public boolean addItemToDB(Item i) {
		//TODO untested
		try {
			PreparedStatement stmt = connection.prepareStatement(INSERT_ITEM_QUERY);
			stmt.setString(1, UUID.randomUUID().toString());
			stmt.setString(2, i.getName());
			stmt.setString(3, "" + i.getBasePrice());
			stmt.setString(4, i.getDescription());
			if (stmt.executeUpdate() != 1)
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public static void main(String args[]) {
		MenuDAO temp = new MenuDAO();
		temp.dropAndRecreateItemTable();
	}

}
