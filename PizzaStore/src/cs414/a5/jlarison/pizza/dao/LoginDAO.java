package cs414.a5.jlarison.pizza.dao;

import java.sql.*;
import java.util.UUID;

import cs414.a5.jlarison.pizza.util.Enum.*;

public class LoginDAO {

	private final String GET_LOGIN_QUERY = "select * from login where username = ?";
	private final String INSERT_LOGIN_QUERY = "insert into Login([ID],[USERNAME],[PASSWORD],[LOGINTYPE]) VALUES(?,?,?,?)";
	private final String UPDATE_PASSWORD_QUERY = "update Login Set PASSWORD = ? Where USERNAME = ?";

	public LoginDAO() {
	}

	//returns type of Login that is validated
	// if validation fails, returns null
	public LoginType validateLogin(String username, String password) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		if (username == null || password == null)
			return null;
		try {
			connection =  DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			stmt = connection.prepareStatement(GET_LOGIN_QUERY);
			String dbPassword = null;
			String loginType = null;
			LoginType parsedLoginType = null;
			stmt.setString(1, username);
		
			rs = stmt.executeQuery();
			rs.next();
			dbPassword = rs.getString(3);
			loginType = rs.getString(4);
			if(password.equals(dbPassword)){
				if(loginType.equals(LoginType.CASHIER.toString()))
					return LoginType.CASHIER;
				if(loginType.equals(LoginType.MANAGER.toString()))
					return LoginType.MANAGER;			
			}
			return null;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				stmt.close();
				rs.close();
				connection.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void generateLoginTable() {
		Statement stmt = null;
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
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
			Connection connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			stmt = connection.createStatement();
			String sql = "DROP TABLE LOGIN;";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		generateLoginTable();
	}

	public boolean insertLogin(String username, String password, LoginType loginType) {
		// TODO Have Login controller handle string formatting
		if(username==null||password==null)return false;
		if(username.length()==0||password.length()==0)return false;
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			stmt = connection.prepareStatement(INSERT_LOGIN_QUERY);
			stmt.setString(1, UUID.randomUUID().toString());
			stmt.setString(2, username);
			stmt.setString(3, password);
			stmt.setString(4, loginType.toString());
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean resetPassword(String username, String oldPassword, String newPassword){
		if(this.validateLogin(username, oldPassword) != null){
			Connection connection = null;
			PreparedStatement stmt = null;
			try{
				connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
				stmt = connection.prepareStatement(UPDATE_PASSWORD_QUERY);
				stmt.setString(1, newPassword);
				stmt.setString(2, username);
				stmt.executeUpdate();
			}catch(Exception e){
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			} finally {
				try {
					stmt.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		LoginDAO dao = new LoginDAO();
		//dao.insertLogin("Jorsh", "1234", LoginType.CASHIER);
		dao.insertLogin("q", "q", LoginType.MANAGER);
	}
}
