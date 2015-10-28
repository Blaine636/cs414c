package cs414c.pizza.dao;

import java.sql.*;
import java.util.UUID;

public class RewardsDAO {

	private Connection connection = null;

	private final String GET_POINTS_QUERY = "Select Points From Rewards Where RewardID = ?";
	private final String INSERT_REWARD_QUERY = "Insert Into Rewards(REWARDID,POINTS) VALUES(?,?)";
	private final String ADD_POINTS_QUERY = "UPDATE Rewards SET Points = Points + ? WHERE RewardID = ?";
	private final String USE_POINTS_QUERY = "UPDATE Rewards SET Points = Points - ? WHERE RewardID = ?";
	private final String GET_HIGHEST_ID_QUERY = "Select REWARDID From Rewards Order By REWARDID DESC Limit 1";

	public RewardsDAO() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			System.out.println("RewardsDAO opened database successfully");
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
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		generateRewardsTable();
	}

	public int getRewardPoints(int RewardID) {
		try {
			PreparedStatement stmt = connection.prepareStatement(GET_POINTS_QUERY);
			stmt.setInt(1, RewardID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	public int registerRewardID() {
		try {
			PreparedStatement stmt = connection.prepareStatement(GET_HIGHEST_ID_QUERY);
			ResultSet rs = stmt.executeQuery();
			int highestID = 0;
			if (rs.next())
				highestID = rs.getInt(1);
			stmt = connection.prepareStatement(INSERT_REWARD_QUERY);
			stmt.setInt(1, highestID + 1);
			stmt.setInt(2, 0);
			if (stmt.executeUpdate() == 1)
				return highestID + 1;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return -1;
	}

	// Adds reward points to RewardID based off of the dollar amount spent
	public boolean addRewardPoints(int RewardID, double TotalAmountSpent) {
		try {
			PreparedStatement stmt = connection.prepareStatement(ADD_POINTS_QUERY);
			stmt.setInt(1, (int) (TotalAmountSpent * 100));
			stmt.setInt(2, RewardID);
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
			stmt.setInt(1, pointsToUse);
			stmt.setInt(2, RewardID);
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
