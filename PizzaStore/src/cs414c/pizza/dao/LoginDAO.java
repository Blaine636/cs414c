package cs414c.pizza.dao;

import java.sql.*;
import java.util.UUID;

public class LoginDAO {

	private Connection connection = null;

	private final String GET_PASSWORD_QUERY = "select password from login where username = ?";
	private final String INSERT_LOGIN_QUERY = "insert into Login([ID],[USERNAME],[PASSWORD]) VALUES(?,?,?)";

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

	public boolean validateLogin(String username, String password) throws SQLException {
		if (username == null || password == null)
			return false;

		PreparedStatement stmt = connection.prepareStatement(GET_PASSWORD_QUERY);
		String dbPassword = null;
		stmt.setString(1, username);
		try {
			ResultSet rs = stmt.executeQuery();
			rs.next();
			dbPassword = rs.getString(1);
		} catch (Exception e) {
			return false;
		}
		return password.equals(dbPassword);
	}

	public void generateLoginTable() {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String sql = "CREATE TABLE LOGIN " + "([ID] GUID PRIMARY KEY NOT NULL,"
					+ " [USERNAME]           nvarchar(64)    NOT NULL, "
					+ " [PASSWORD]           nvarchar(64)    NOT NULL, " + "UNIQUE ([USERNAME]))";
			stmt.executeUpdate(sql);
			stmt.close();
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
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		generateLoginTable();
	}

	public boolean insertLogin(String username, String password) {
		// TODO Have Login controller handle string formatting
		try {
			PreparedStatement stmt = connection.prepareStatement(INSERT_LOGIN_QUERY);
			stmt.setString(1, UUID.randomUUID().toString());
			stmt.setString(2, username);
			stmt.setString(3, password);
			if (stmt.executeUpdate() != 1)
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
