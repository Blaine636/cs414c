package cs414c.pizza.dao;

import cs414c.pizza.domain.*;
import java.sql.*;

public class MenuDAO {

	private Connection connection = null;

	private final String GET_HIGHEST_ID_QUERY = "Select ID From Item Order By ID DESC Limit 1";
	private final String INSERT_ITEM_QUERY = "insert into item([ID],[NAME],[BASEPRICE],[DESCRIPTION],[TYPE]) VALUES(?,?,?,?,?)";
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
			String sql = "CREATE TABLE SIDEITEM ([SIDEITEMID] INTEGER PRIMARY KEY NOT NULL,"
					+ " [NAME]           	nvarchar(64)    NOT NULL UNIQUE, "
					+ " [BASEPRICE]         decimal(5,2) 	NOT NULL, "
					+ " [DESCRIPTION]		nvarchar(256) 	NOT NULL);"
					+ " CREATE TABLE PIZZA ([PIZZAID] INTEGER PRIMARY KEY NOT NULL,"
					+ " [NAME]           	nvarchar(64)    NOT NULL UNIQUE, "
					+ " [BASEPRICE]         decimal(5,2) 	NOT NULL, "
					+ " [DESCRIPTION]		nvarchar(256) 	NOT NULL);"
					+ " CREATE TABLE TOPPING ([TOPPINGID] INTEGER PRIMARY KEY NOT NULL,"
					+ " [NAME]           	nvarchar(64)    NOT NULL UNIQUE, "
					+ " [BASEPRICE]         decimal(5,2) 	NOT NULL);"
					+ " CREATE TABLE PIZZATOPPINGMAP ([PIZZAID] INTEGER NOT NULL,"
					+ " [TOPPINGID] INTEGER NOT NULL)";
			stmt.executeUpdate(sql);
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
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		generateTables();
	}

	public int addItemToDB(Item i) {
		try {
			PreparedStatement stmt = connection.prepareStatement(GET_HIGHEST_ID_QUERY);
			ResultSet rs = stmt.executeQuery();
			int highestID = 0;
			if(rs.next())
				highestID = rs.getInt(1);				
			stmt = connection.prepareStatement(INSERT_ITEM_QUERY);
			stmt.setInt(1, highestID+1);
			stmt.setString(2, i.getName());
			stmt.setDouble(3, i.getBasePrice());
			stmt.setString(4, i.getDescription());
			stmt.setString(5, Character.toString(getItemType(i)));
			if (stmt.executeUpdate() == 1)
				return highestID+1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}
	
	
	//return of Z is error case
	private char getItemType(Item i){
		if(i instanceof Pizza)
			return 'P';
		if(i instanceof SideItem)
			return 'S';
		if(i instanceof Topping)
			return 'T';
		return 'Z';
	}
	
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
//		temp.dropAndRecreateItemTable();
		temp.generateTables();
		//Item i = new SideItem("JorshenstienSideItem",6.99,"asoiefj");
//		temp.addItemToDB(i);
	}
}