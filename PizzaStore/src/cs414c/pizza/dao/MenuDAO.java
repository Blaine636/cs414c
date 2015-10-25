package cs414c.pizza.dao;

import cs414c.pizza.domain.*;
import java.sql.*;
import java.util.*;

public class MenuDAO{
	
	private Connection connection = null;
	
//	private final String GET_PASSWORD_QUERY = "select password from login where username = ?";
//	private final String INSERT_LOGIN_QUERY = "insert into Login([ID],[USERNAME],[PASSWORD]) VALUES(?,?,?)";
	
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
	
	public void generateItemTable(){
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String sql = "CREATE TABLE ITEM " + "([ID] GUID PRIMARY KEY NOT NULL,"
					+ " [NAME]           	nvarchar(64)    NOT NULL, " 
					+ " [BASEPRICE]         decimal(5,2) 	NOT NULL, "
					+ " [DESCRIPTION]		nvarchar(256) 	NOT NULL,"	
					+ "UNIQUE ([NAME]))";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void dropAndRecreateItemTable(){
		Statement stmt = null;
		try{
			stmt = connection.createStatement();
			String sql = "Drop Table Item;";
			stmt.executeUpdate(sql);
			stmt.close();
		}catch (Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		generateItemTable();
	}
	
	public void writeMenuToDB(Menu m){
		Iterator<Item> it = m.ItemSet.iterator();
	     while(it.hasNext()){
	        //TODO write insert each item into Item Table
	     }
	}
	
	
	public static void main(String args[]){
		MenuDAO temp = new MenuDAO();
		temp.dropAndRecreateItemTable();
	}
	
}
