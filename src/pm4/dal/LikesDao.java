package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pm4.model.Houses;
import pm4.model.Likes;
import pm4.model.Recommendations;
import pm4.model.Users;

public class LikesDao {
	protected ConnectionManager connectionManager;

	private static LikesDao instance = null;
	protected LikesDao() {
		connectionManager = new ConnectionManager();
	}
	public static LikesDao getInstance() {
		if(instance == null) {
			instance = new LikesDao();
		}
		return instance;
	}
	
	public Likes create(Likes likes) throws SQLException{
	    String insertrecommendation =
			"INSERT INTO Likes(UserName,HouseId,Liked) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertrecommendation,
				Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, likes.getUsers().getUserName());
			insertStmt.setInt(2, likes.getHouse().getHouseId());
			insertStmt.setBoolean(3, likes.isLiked());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int LikeId = -1;
			if(resultKey.next()) {
				LikeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			likes.setLikeId(LikeId);
			return likes;
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
	
	public Likes getLikesById(int likeId) throws SQLException{
		String selectLikes =
				"SELECT LikeId,UserName,HouseId,Liked " +
				"FROM Likes " +
				"WHERE LikeId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectLikes);
				selectStmt.setInt(1, likeId);
			 
				results = selectStmt.executeQuery();
				UsersDao usersDao = UsersDao.getInstance();
				HousesDao housesDao = HousesDao.getInstance();
				if(results.next()) {
					
					int LikeId = results.getInt("LikeId");
					String UserName = results.getString("UserName");
					int HouseId = results.getInt("HouseId");
					boolean liked = results.getBoolean("Liked");
					
					
					
					Users User = usersDao.getUserByUserName(UserName);
					Houses Houses = housesDao.getHouseFromHouseId(HouseId);
					Likes Likes = new Likes(LikeId,Houses,User,liked);
					return Likes;
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
	
	public List<Likes> getLikesByUserName(String userName)throws SQLException{
		List<Likes> LikesList = new ArrayList<Likes>();
		String selectLikes =
				"SELECT LikeId,UserName,HouseId,Liked " +
				"FROM Likes " +
				"WHERE UserName=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectLikes);
				selectStmt.setString(1, userName);
			    
				results = selectStmt.executeQuery();
				UsersDao usersDao = UsersDao.getInstance();
				HousesDao housesDao = HousesDao.getInstance();
				while(results.next()) {
					int LikeId = results.getInt("LikeId");
					String UserName = results.getString("UserName");
					int HouseId = results.getInt("HouseId");
					boolean liked = results.getBoolean("Liked");
					

					Users User = usersDao.getUserByUserName(UserName);
					Houses Houses = housesDao.getHouseFromHouseId(HouseId);
					Likes Likes = new Likes(LikeId,Houses,User,liked);
					LikesList.add(Likes);
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
			return LikesList;
	}
	
	public List<Likes> getLikesByHouseId(int houseId)throws SQLException{
		List<Likes> LikesList = new ArrayList<Likes>();
		String selectLikes =
				"SELECT LikeId,UserName,HouseId,Liked " +
				"FROM Likes " +
				"WHERE HouseId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectLikes);
				selectStmt.setInt(1, houseId);
			
				results = selectStmt.executeQuery();
				UsersDao usersDao = UsersDao.getInstance();
				HousesDao housesDao = HousesDao.getInstance();
				while(results.next()) {
					
					int LikeId = results.getInt("LikeId");
					String UserName = results.getString("UserName");
					int HouseId = results.getInt("HouseId");
					boolean liked = results.getBoolean("Liked");
					
					
					
					Users User = usersDao.getUserByUserName(UserName);
					Houses Houses = housesDao.getHouseFromHouseId(HouseId);
					Likes Likes = new Likes(LikeId,Houses,User,liked);
					LikesList.add(Likes);
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
			return LikesList;
	}
	
	
	public Likes delete(Likes Likes) throws SQLException{
		String deleteLikes = "DELETE FROM Likes WHERE LikeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLikes);
			deleteStmt.setInt(1, Likes.getLikeId());
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
