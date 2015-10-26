package cs414c.pizza.dao;

import java.sql.*;
import java.util.UUID;

public class RewardsDAO {

	private Connection connection = null;

	private final String GET_POINTS_QUERY = "Select Points From Rewards Where RewardID = ?";
	private final String INSERT_REWARD_QUERY = "Insert Into Rewards(REWARDID,POINTS) VALUES(?,?)";
	private final String ADD_POINTS_QUERY = "UPDATE Rewards SET Points = Points + ? WHERE RewardID = ?";
	private final String USE_POINTS_QUERY = "UPDATE Rewards SET Points = Points - ? WHERE RewardID = ?";

	RewardsDAO() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			System.out.println("Opened database successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateRewardsTable() {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String sql = "CREATE TABLE REWARDS " + "([REWARDID] INTEGER PRIMARY KEY NOT NULL,"
					+ " [POINTS]	INTEGER		NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void dropAndRecreateRewardsTable() {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String sql = "Drop Table Rewards";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		generateRewardsTable();
	}

	public int getRewardPoints(int RewardID) {
		try {
			PreparedStatement stmt = connection.prepareStatement(GET_POINTS_QUERY);
			stmt.setString(1, "" + RewardID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return Integer.parseInt(rs.getString(1));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	public boolean registerRewardID(int RewardID) {
		try {
			PreparedStatement stmt = connection.prepareStatement(INSERT_REWARD_QUERY);
			stmt.setString(1, "" + RewardID);
			stmt.setString(2, "0");
			if (stmt.executeUpdate() != 1)
				return false;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		return true;
	}

	// Adds reward points to RewardID based off of the dollar amount spent
	public boolean addRewardPoints(int RewardID, double TotalAmountSpent) {
		try {
			PreparedStatement stmt = connection.prepareStatement(ADD_POINTS_QUERY);
			stmt.setString(1, "" + (TotalAmountSpent * 100));
			stmt.setString(2, "" + RewardID);
			if (stmt.executeUpdate() != 1)
				return false;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		return true;
	}

	// Subtracts reward points until TotalOrderAmount is met or Points reach 0
	// returns monetary discount achieved by applying rewards
	public double useRewardPoints(int RewardID, double TotalOrderAmount) throws Exception {
		int pointsAvailable = getRewardPoints(RewardID);
		int pointsNeeded = (int) (TotalOrderAmount * 100);
		int pointsToUse = pointsAvailable - (pointsAvailable - pointsNeeded);
		if (pointsToUse > pointsAvailable) {
			pointsToUse = pointsAvailable;
		}
		double monetaryDiscount = ((double) pointsToUse) / 100;
		try {
			PreparedStatement stmt = connection.prepareStatement(USE_POINTS_QUERY);
			stmt.setString(1, "" + pointsToUse);
			stmt.setString(2, "" + RewardID);
			if (stmt.executeUpdate() != 1) {
				throw new Exception("Unable To Pull Reward Points from Database");
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			throw e;
		}
		return monetaryDiscount;
	}
}
