package pm4.dal;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pm4.model.Owners;
import pm4.model.Users;

public class OwnersDao extends UsersDao {
	private static OwnersDao instance = null;
	protected OwnersDao() {
		super();
	}
	public static OwnersDao getInstance() {
		if(instance == null) {
			instance = new OwnersDao();
		}
		return instance;
	}

	public Owners create(Owners owner) throws SQLException {
		// Insert into the superclass table first.
		create(new Users(owner.getUserName(),owner.getPassWord(),owner.getFirstName(),owner.getLastName(),owner.getEmail(),owner.getPhone(),
				owner.getAge(),owner.getStreet1(),owner.getStreet2(),owner.getCity(),owner.getState(),owner.getZip()));


		String insertOwner = "INSERT INTO Owners(UserName,houseOwned) "
				+ "VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertOwner, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, owner.getUserName());
			insertStmt.setInt(2, owner.getHouseOwned());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int ownerId = -1;
			if(resultKey.next()) {
				ownerId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			owner.setOwnerId(ownerId);
			return owner;
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

	/**
	 * Delete the Owners instance.
	 * This runs a DELETE statement.
	 */
	public Owners delete(Owners owner) throws SQLException {
		String deleteTenant = "DELETE FROM Owners WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTenant);
			deleteStmt.setString(1, owner.getUserName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + owner.getUserName());
			}

			super.delete(owner);

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

	public Owners getOwnerFromUserName(String userName) throws SQLException {
		// To build an Tenant object, we need the Persons record, too.
		String selectTenant =
			"SELECT Owners.UserName AS UserName,PassWord,FirstName,LastName,Email,Phone,Age,Street1,Street2,City,State,Zip,OwnerId,HouseOwned " +
			"FROM Owners INNER JOIN Users " +
			"  ON Owners.UserName = Users.UserName " +
			"WHERE Owners.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTenant);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String passWord = results.getString("PassWord");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("Phone");
				int age = results.getInt("Age");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				int ownerId = results.getInt("OwnerId");
				int houseOwned = results.getInt("HouseOwned");
				Owners owner = new Owners(resultUserName,  passWord, firstName, lastName, email, phone, age, street1, street2,
						city, state, zip, ownerId, houseOwned);
				return owner;
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
	
	public Owners getOwnerFromOwnerId(int ownerId) throws SQLException {
		// To build an Tenant object, we need the Persons record, too.
		String selectOwner =
			"SELECT Owners.UserName AS UserName,Password,FirstName,LastName,Email,Phone,Age,Street1,Street2,City,State,Zip,OwnerId,HouseOwned "+
			"FROM Owners INNER JOIN Users "+
			"  ON Owners.UserName = Users.UserName "+
		    "WHERE OwnerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOwner);
			selectStmt.setInt(1, ownerId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String userName = results.getString("UserName");
				String passWord = results.getString("PassWord");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("Phone");
				int age = results.getInt("Age");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				int resultOwnerId = results.getInt("OwnerId");
				int houseOwned = results.getInt("HouseOwned");
				Owners owner = new Owners(userName,  passWord, firstName, lastName, email, phone, age, street1, street2,
						city, state, zip, resultOwnerId, houseOwned);
				return owner;
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

	public List<Owners> getOwnersFromFirstName(String firstName)
			throws SQLException {
		List<Owners> ownerList = new ArrayList<Owners>();
		String selectOwners =
			"SELECT Owners.UserName AS UserName,PassWord,FirstName,LastName,Email,Phone,Age,Street1,Street2,"
			+ "City,State,Zip,OwnerId,HouseOwned " +
			"FROM Owners INNER JOIN Users " +
			"  ON Owners.UserName = Users.UserName " +
			"WHERE Users.FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOwners);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				String passWord = results.getString("PassWord");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("Phone");
				int age = results.getInt("Age");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				int ownerId = results.getInt("OwnerId");
				int houseOwned = results.getInt("HouseOwned");
				Owners owner = new Owners(userName,  passWord, resultFirstName, lastName, email, phone, age, street1, street2,
						city, state, zip, ownerId, houseOwned);
				ownerList.add(owner);
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
		return ownerList;
	}
}
