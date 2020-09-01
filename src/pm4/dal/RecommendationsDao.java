package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pm4.model.Houses;
import pm4.model.Recommendations;
import pm4.model.Users;


public class RecommendationsDao {
	protected ConnectionManager connectionManager;

	private static RecommendationsDao instance = null;
	protected RecommendationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecommendationsDao getInstance() {
		if(instance == null) {
			instance = new RecommendationsDao();
		}
		return instance;
	}
	
	public Recommendations create(Recommendations recommendation) throws SQLException{
	    String insertrecommendation =
			"INSERT INTO Recommendations(UserName,HouseId) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertrecommendation,
				Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, recommendation.getUsers().getUserName());
			insertStmt.setInt(2, recommendation.getHouse().getHouseId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int RecommendationId = -1;
			if(resultKey.next()) {
				RecommendationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recommendation.setRecommendationId(RecommendationId);
			return recommendation;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
}
	
	public Recommendations getRecommendationById(int recommendationId) throws SQLException{
		String selectRecommendations =
				"SELECT RecommendationId,UserName,HouseId " +
				"FROM Recommendations " +
				"WHERE RecommendationId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRecommendations);
				selectStmt.setInt(1, recommendationId);
			
				results = selectStmt.executeQuery();
				UsersDao usersDao = UsersDao.getInstance();
				HousesDao housesDao = HousesDao.getInstance();
				if(results.next()) {
					
					int RecommendationId = results.getInt("RecommendationId");
					String UserName = results.getString("UserName");
					int HouseId = results.getInt("HouseId");
					
					
					
					Users User = usersDao.getUserByUserName(UserName);
					Houses Houses = housesDao.getHouseFromHouseId(HouseId);
					Recommendations Recommendations = new Recommendations(RecommendationId,Houses,User);
					return Recommendations;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return null;
	}
	
	public List<Recommendations> getRecommendationsByUserName(String userName)throws SQLException{
		List<Recommendations> RecommendationsList = new ArrayList<Recommendations>();
		String selectRecommendations =
				"SELECT RecommendationId,UserName,HouseId " +
				"FROM Recommendations " +
				"WHERE UserName=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRecommendations);
				selectStmt.setString(1, userName);
			
				results = selectStmt.executeQuery();
				UsersDao usersDao = UsersDao.getInstance();
				HousesDao housesDao = HousesDao.getInstance();
				while(results.next()) {
					
					int RecommendationId = results.getInt("RecommendationId");
					String UserName = results.getString("UserName");
					int HouseId = results.getInt("HouseId");

					
					Users User = usersDao.getUserByUserName(UserName);
					Houses Houses = housesDao.getHouseFromHouseId(HouseId);
					Recommendations Recommendations = new Recommendations(RecommendationId,Houses,User);
					
					RecommendationsList.add(Recommendations);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return RecommendationsList;
	}
	
	public List<Recommendations> getRecommendationsByHouseId(int houseId)throws SQLException{
		List<Recommendations> RecommendationsList = new ArrayList<Recommendations>();
		String selectRecommendations =
				"SELECT RecommendationId,UserName,HouseId " +
				"FROM Recommendations " +
				"WHERE HouseId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRecommendations);
				selectStmt.setInt(1, houseId);
			
				results = selectStmt.executeQuery();
				UsersDao usersDao = UsersDao.getInstance();
				HousesDao housesDao = HousesDao.getInstance();
				while(results.next()) {
					
					int RecommendationId = results.getInt("RecommendationId");
					String UserName = results.getString("UserName");
                    int HouseId = results.getInt("HouseId");
					
					
					
					Users User = usersDao.getUserByUserName(UserName);
					Houses Houses = housesDao.getHouseFromHouseId(HouseId);
					Recommendations Recommendations = new Recommendations(RecommendationId,Houses,User);
					RecommendationsList.add(Recommendations);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return RecommendationsList;
	}
	
	
	public Recommendations delete(Recommendations recommendation) throws SQLException{
		String deleterecommendation = "DELETE FROM Recommendations WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleterecommendation);
			deleteStmt.setInt(1, recommendation.getRecommendationId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

}
