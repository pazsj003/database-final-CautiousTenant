package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pm4.model.Houses;
import pm4.model.Reviews;
import pm4.model.Users;


public class ReviewsDao {
	protected ConnectionManager connectionManager;

	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	
	public Reviews create(Reviews review) throws SQLException{
		String insertReviews =
				"INSERT INTO Reviews(Created,Content,Rating,UserName,HouseId) " +
				"VALUES(?,?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertReviews,
					Statement.RETURN_GENERATED_KEYS);
				insertStmt.setTimestamp(1, new Timestamp(review.getCreated().getTime()));
				insertStmt.setString(2, review.getContent());
				insertStmt.setFloat(3, review.getRating());
				insertStmt.setString(4, review.getUsers().getUserName());
				insertStmt.setInt(5, review.getHouse().getHouseId());
				insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				resultKey = insertStmt.getGeneratedKeys();
				int ReviewId = -1;
				if(resultKey.next()) {
					ReviewId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				review.setReviewId(ReviewId);
				return review;
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
	
	
	public Reviews getReviewById(int reviewId) throws SQLException{
		String selectReviews =
				"SELECT ReviewId,Created,Content,Rating,UserName,HouseId " +
				"FROM Reviews " +
				"WHERE ReviewId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectReviews);
				selectStmt.setInt(1, reviewId);
			
				results = selectStmt.executeQuery();
				UsersDao usersDao = UsersDao.getInstance();
				HousesDao houseDao = HousesDao.getInstance();
				if(results.next()) {
					
					int resultReviewId = results.getInt("ReviewId");
					Date Created = new Date(results.getTimestamp("Created").getTime());
					String Content = results.getString("Content");
					float Rating = results.getFloat("Rating");
					String UserName = results.getString("UserName");
					int HouseId = results.getInt("HouseId");
					
					
					
					Users User = usersDao.getUserByUserName(UserName);
					Houses house = houseDao.getHouseFromHouseId(HouseId);
					Reviews Reviews = new Reviews(resultReviewId,Created, Content,Rating,house,User);
					return Reviews;
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
	
	
	public List<Reviews> getReviewsByUserName(String userName)throws SQLException{
		List<Reviews> ReviewsList = new ArrayList<Reviews>();
		String selectReviews =
				"SELECT ReviewId,Created,Content,Rating,UserName,HouseId " +
						"FROM Reviews " +
						"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			HousesDao houseDao = HousesDao.getInstance();
			
			while(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				Date Created = new Date(results.getTimestamp("Created").getTime());
				String Content = results.getString("Content");
				float Rating = results.getFloat("Rating");
				String UserName = results.getString("UserName");
				int HouseId = results.getInt("HouseId");
				
				
				
				Users User = usersDao.getUserByUserName(UserName);
				Houses house = houseDao.getHouseFromHouseId(HouseId);
				Reviews Reviews = new Reviews(resultReviewId,Created, Content,Rating,house,User);
				ReviewsList.add(Reviews);
			
				
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
		return ReviewsList;
	}
	
	
	public List<Reviews> getReviewsByHouseId(int restaurantId)throws SQLException{
		List<Reviews> ReviewsList = new ArrayList<Reviews>();
		String selectReviews =
				"SELECT ReviewId,Created,Content,Rating,UserName,HouseId " +
						"FROM Reviews " +
						"WHERE HouseId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, restaurantId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			HousesDao houseDao = HousesDao.getInstance();
			
			while(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				Date Created = new Date(results.getTimestamp("Created").getTime());
				String Content = results.getString("Content");
				float Rating = results.getFloat("Rating");
				String UserName = results.getString("UserName");
				int HouseId = results.getInt("HouseId");
				
				
				
				Users User = usersDao.getUserByUserName(UserName);
				Houses house = houseDao.getHouseFromHouseId(HouseId);
				Reviews Reviews = new Reviews(resultReviewId,Created, Content,Rating,house,User);
				ReviewsList.add(Reviews);
			
				
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
		return ReviewsList;
	}
	
	
	public Reviews delete(Reviews review) throws SQLException{
		String deleteReviews = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReviews);
			deleteStmt.setInt(1, review.getReviewId());
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
