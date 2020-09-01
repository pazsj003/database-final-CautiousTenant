package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pm4.model.Credit;
import pm4.model.Users;



public class CreditDao {
	protected ConnectionManager connectionManager;

	private static CreditDao instance = null;
	protected CreditDao() {
		connectionManager = new ConnectionManager();
	}
	public static CreditDao getInstance() {
		if(instance == null) {
			instance = new CreditDao();
		}
		return instance;
	}
	public Credit create(Credit credit) throws SQLException {
		String insertCredit =
			"INSERT INTO Credit(Ssn,CreditHistoryMonth,CreditScore,UserName) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCredit, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setLong(1, credit.getSsn());
			insertStmt.setInt(2, credit.getCreditHistoryMonth());
			insertStmt.setInt(3, credit.getCreditScore());
			insertStmt.setString(4, credit.getUser().getUserName());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int creditId = -1;
			if(resultKey.next()) {
				creditId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			credit.setCreditId(creditId);
			return credit;
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
		}
	}
	public Credit getCreditBySsn(Long Ssn) throws SQLException {
		String selectCredit = "SELECT Ssn,CreditHistoryMonth,CreditScore,UserName FROM Credit WHERE Ssn=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCredit);
			selectStmt.setLong(1, Ssn);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			if(results.next()) {
				Long resultSsn = results.getLong("Ssn");
				int creditHistoryMonth = results.getInt("CreditHistoryMonth");
				int creditScore = results.getInt("CreditScore");
				String userName = results.getString("UserName");
				Users user = usersDao.getUserByUserName(userName);
				Credit card = new Credit(resultSsn, creditHistoryMonth, creditScore, user);
				
				return card;
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

	public Credit delete(Credit credit) throws SQLException {
		String deleteCredit = "DELETE FROM Credit WHERE Ssn=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCredit);
			deleteStmt.setLong(1, credit.getSsn());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogComments instance.
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
	
	public List<Credit> getCreditByUserName(String userName)throws SQLException{
		List<Credit> creditList = new ArrayList<>();
		
		String selectCredit =
			"SELECT Ssn,CreditHistoryMonth,CreditScore,UserName FROM Credit WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCredit);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao userDao = UsersDao.getInstance();
			while(results.next()) {
				Users user = userDao.getUserByUserName(userName);
				Long ssn = results.getLong("Ssn");
				int creditHistoryMonth = results.getInt("CreditHistoryMonth");
				int creditScore = results.getInt("CreditScore");
				Credit credit = new Credit(ssn, creditHistoryMonth, creditScore, user);
				creditList.add(credit);
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
		return creditList;
	}
}
