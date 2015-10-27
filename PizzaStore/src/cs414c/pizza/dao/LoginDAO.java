package cs414c.pizza.dao;

import cs414c.pizza.util.Enum;
import cs414c.pizza.util.Enum.LoginType;

import java.sql.*;
import java.util.UUID;

public class LoginDAO {

	private Connection connection = null;

	private final String GET_LOGIN_QUERY = "select * from login where username = ?";
	private final String INSERT_LOGIN_QUERY = "insert into Login([ID],[USERNAME],[PASSWORD],[LOGINTYPE]) VALUES(?,?,?,?)";

	public LoginDAO() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			System.out.println("Opened database successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//returns type of Login that is validated
	// if validation fails, returns null
	public Enum.LoginType validateLogin(String username, String password) throws SQLException {
		if (username == null || password == null)
			return null;

		PreparedStatement stmt = connection.prepareStatement(GET_LOGIN_QUERY);
		String dbPassword = null;
		String loginType = null;
		Enum.LoginType parsedLoginType = null;
		stmt.setString(1, username);
		try {
			ResultSet rs = stmt.executeQuery();
			rs.next();
			dbPassword = rs.getString(1);
			loginType = rs.getString(2);
			if(loginType.equals(Enum.LoginType.CASHIER.toString()))
				return Enum.LoginType.CASHIER;
			if(loginType.equals(Enum.LoginType.MANAGER.toString()))
				return Enum.LoginType.MANAGER;
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public void generateLoginTable() {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String sql = "CREATE TABLE LOGIN " + "([ID] GUID PRIMARY KEY NOT NULL,"
					+ " [USERNAME]           nvarchar(64)    NOT NULL UNIQUE, "
					+ " [PASSWORD]           nvarchar(64)    NOT NULL,"
					+ " [LOGINTYPE]			nvarchar(16)	NOT NULL)";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void dropAndRecreateLoginTable() {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String sql = "DROP TABLE LOGIN;";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		generateLoginTable();
	}

	public boolean insertLogin(String username, String password, Enum.LoginType loginType) {
		// TODO Have Login controller handle string formatting
		try {
			PreparedStatement stmt = connection.prepareStatement(INSERT_LOGIN_QUERY);
			stmt.setString(1, UUID.randomUUID().toString());
			stmt.setString(2, username);
			stmt.setString(3, password);
			stmt.setString(4, loginType.toString());
			if (stmt.executeUpdate() != 1)
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public static void main(String args[]){
		LoginDAO temp = new LoginDAO();
		temp.dropAndRecreateLoginTable();
		
	}
}
