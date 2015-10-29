package cs414c.pizza.dao;

import java.sql.*;
import java.util.UUID;

public class RewardsDAO {

	private final String GET_POINTS_QUERY = "Select Points From Rewards Where RewardID = ?";
	private final String INSERT_REWARD_QUERY = "Insert Into Rewards(REWARDID,POINTS) VALUES(?,?)";
	private final String ADD_POINTS_QUERY = "UPDATE Rewards SET Points = Points + ? WHERE RewardID = ?";
	private final String USE_POINTS_QUERY = "UPDATE Rewards SET Points = Points - ? WHERE RewardID = ?";
	private final String GET_HIGHEST_ID_QUERY = "Select REWARDID From Rewards Order By REWARDID DESC Limit 1";
	private final int rewardsScaleFactor = 1000;

	public RewardsDAO() {
	}

	public void generateRewardsTable() {
		Statement stmt = null;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			stmt = connection.createStatement();
			String sql = "CREATE TABLE REWARDS " + "([REWARDID] INTEGER PRIMARY KEY NOT NULL,"
					+ " [POINTS]	INTEGER		NOT NULL)";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void dropAndRecreateRewardsTable() {
		Statement stmt = null;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			stmt = connection.createStatement();
			String sql = "Drop Table Rewards";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
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
		generateRewardsTable();
	}

	public double getRewardPoints(int RewardID) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			stmt = connection.prepareStatement(GET_POINTS_QUERY);
			stmt.setInt(1, RewardID);
			rs = stmt.executeQuery();
			rs.next();
			double discountAvailable = ((double)(rs.getInt(1)))/rewardsScaleFactor;
			double round = discountAvailable*100;
			double rounded = Math.round(round);
			double retVal = rounded/100;
			return retVal;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
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

	public int registerRewardID() {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			stmt = connection.prepareStatement(GET_HIGHEST_ID_QUERY);
			rs = stmt.executeQuery();
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
		return -1;
	}

	// Adds reward points to RewardID based off of the dollar amount spent
	public boolean addRewardPoints(int RewardID, double TotalAmountSpent) {
		
		 Connection connection = null;
		 PreparedStatement stmt = null;
		 
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			stmt = connection.prepareStatement(ADD_POINTS_QUERY);
			stmt.setInt(1, (int) (TotalAmountSpent * 100));
			stmt.setInt(2, RewardID);
		} catch (Exception e) {
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

	// Subtracts reward points until TotalOrderAmount is met or Points reach 0
	// returns monetary discount achieved by applying rewards
	public double useRewardPoints(int RewardID, double totalOrderAmount) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		double discountAvailable = getRewardPoints(RewardID);
		double discountToUse = discountAvailable - (discountAvailable - totalOrderAmount);
		if (discountToUse > discountAvailable) {
			discountToUse = discountAvailable;
		}
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Pizza.db");
			stmt = connection.prepareStatement(USE_POINTS_QUERY);
			stmt.setInt(1, (int)(discountToUse*rewardsScaleFactor));
			stmt.setInt(2, RewardID);
			if (stmt.executeUpdate() != 1) {
				throw new Exception("Unable To Pull Reward Points from Database");
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return -1;
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//round to 2 decimal places
		double round = discountToUse*100;
		double rounded = Math.round(round);
		double retVal = rounded/100;
		return retVal;
	}
	
	public static void main(String args[]){
		RewardsDAO temp = new RewardsDAO();
		System.out.println(temp.getRewardPoints(1));
	}
}
